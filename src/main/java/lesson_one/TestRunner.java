package lesson_one;

import lesson_one.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {
    @SuppressWarnings("unchecked")
    public void runTest(Class<?> c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object targetClass = c.getDeclaredConstructor().newInstance();

        TestMethodsWrapper testMethodsWrapper = new TestMethodsWrapper();
        Method[] classMethods = c.getDeclaredMethods();

        Method beforeSuiteAnnotationMethod = scanForSingleStaticMethodAnnotation(classMethods, BeforeSuite.class);
        Method beforeTestAnnotationMethod = scanForSingleAnnotation(classMethods, BeforeTest.class);
        Method afterTestAnnotationMethod = scanForSingleAnnotation(classMethods, AfterTest.class);
        Method afterSuiteAnnotationMethod = scanForSingleStaticMethodAnnotation(classMethods, AfterSuite.class);
        testMethodsWrapper.addTestAnnotationMethod(classMethods);

        if (beforeSuiteAnnotationMethod != null) {
            beforeSuiteAnnotationMethod.invoke(targetClass);
        }

        for (List<Method> priorityMethodsList : testMethodsWrapper.getTestAnnotationsMethods().values()) {
            for (Method method : priorityMethodsList) {
                if (beforeTestAnnotationMethod != null) {
                    beforeTestAnnotationMethod.invoke(targetClass);
                }
                method.invoke(targetClass);
                if (afterTestAnnotationMethod != null) {
                    afterTestAnnotationMethod.invoke(targetClass);
                }
            }
        }

        if (afterSuiteAnnotationMethod != null) {
            afterSuiteAnnotationMethod.invoke(targetClass);
        }
        scanForCsv(classMethods, targetClass);
    }

    private <T extends Annotation > Method scanForSingleAnnotation(Method[] methods, Class<T> targetAnnotation) {
        Method discoveredMethod = null;
        for (Method method : methods) {
            if (method.getDeclaredAnnotation(targetAnnotation) != null) {
                if (discoveredMethod != null) {
                    throw new RuntimeException(String.format("Методов помеченных аннотацией %s больше одного", targetAnnotation.getTypeName()));
                }
                discoveredMethod = method;
            }
        }
        return discoveredMethod;
    }

    private <T extends Annotation > Method scanForSingleStaticMethodAnnotation(Method[] methods, Class<T> targetAnnotation) {
        Method discoveredMethod = null;
        for (Method method : methods) {
            if ( (method.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
                if (method.getDeclaredAnnotation(targetAnnotation) != null) {
                    if (discoveredMethod != null) {
                        throw new RuntimeException(String.format("Методов помеченных аннотацией %s больше одного", targetAnnotation.getTypeName()));
                    }
                    discoveredMethod = method;
                }
            }
        }
        return discoveredMethod;
    }

    private void scanForCsv(Method[] methods, Object targetClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.getDeclaredAnnotation(CsvSource.class) != null) {
                CsvSource annotation = method.getAnnotation(CsvSource.class);
                String[] parseAnnotationValue =  Arrays.stream(annotation.value().split(",")).map(String::trim).toArray(String[]::new);
                Parameter[] parameters = method.getParameters();
                if (parameters.length != parseAnnotationValue.length) {
                    throw new RuntimeException(String.format(
                            "Количество элементов строки аннотации %s не совпадает в количеством аргументов",
                            CsvSource.class.getTypeName()
                    ));
                }
                Object[] args = new Object[parameters.length];

                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i].getType().equals(boolean.class)) {
                        args[i] = Boolean.valueOf(parseAnnotationValue[i]);
                    }
                    else {
                        args[i] = parameters[i].getType().getDeclaredConstructor(String.class).newInstance(parseAnnotationValue[i]);
                    }
                }
                method.invoke(targetClass, args);
                System.out.print("\n");
            }
        }
    }
}
