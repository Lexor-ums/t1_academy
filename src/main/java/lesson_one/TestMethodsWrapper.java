package lesson_one;

import lesson_one.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestMethodsWrapper {
    Map<Integer, List<Method>> testAnnotationsMethods = new TreeMap<Integer, List<Method>>();

    public Map<Integer, List<Method>> getTestAnnotationsMethods() {
        return testAnnotationsMethods;
    }

    public void addTestAnnotationMethod(Method[] classMethods) {
        for (Method classMethod : classMethods) {
            Test annotation = classMethod.getDeclaredAnnotation(Test.class);
            if (annotation != null) {
                Integer priority = annotation.priority();
                if (testAnnotationsMethods.containsKey(priority)) {
                    var methodsList = testAnnotationsMethods.get(priority);
                    methodsList.add(classMethod);
                    testAnnotationsMethods.put(priority, methodsList);
                }
                else {
                    testAnnotationsMethods.computeIfAbsent(priority, integer -> new ArrayList<>()).add(classMethod);
                }
            }
        }
    }
}
