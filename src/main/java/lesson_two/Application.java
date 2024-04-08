package lesson_two;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Application {

    enum Position {
        ENGINEER,
        NOT_ENGINEER
    }
    public static class Employee {
        public Employee(Position postion, int age, String name) {

            this.postion = postion;
            this.age = age;
            this.name = name;
        }
        private final Position postion;
        private final int age;
        private final String name;

        public Position getPostion() {
            return postion;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        //Реализуйте удаление из листа всех дубликатов
        List<Integer> listWithDuplicates = List.of(1, 3, 3, 1, 4,4,5,2);
        List<Integer> listWithoutDuplicates = listWithDuplicates.stream().distinct().toList();
        assert listWithoutDuplicates.size() == 5;
        assert listWithoutDuplicates.containsAll(List.of(1, 2, 3, 4, 5));

        //  Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        List<Integer> listToFindThirdMaximum = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        Integer thirdMaximum = listToFindThirdMaximum.stream()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("Массив содержит элементов меньше трёх"));
        assert thirdMaximum == 10;

        //Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
        // в отличие от прошлой задачи здесь разные 10 считает за одно число)
        List<Integer> listToFindThirdMaximumWithDuplicates = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        Integer thirdMaximumWithDuplicates = listToFindThirdMaximum.stream()
                .sorted(Collections.reverseOrder())
                .distinct()
                .limit(3)
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("Массив содержит элементов меньше трёх"));
        assert thirdMaximumWithDuplicates == 9;

        //`Имеется список объектов типа Сотрудник (имя, возраст, должность),
        // необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        List<Employee> employees = List.of(
                new Employee(Position.ENGINEER, 53, "Василий"),
                new Employee(Position.ENGINEER, 27, "Алёша"),
                new Employee(Position.ENGINEER, 42, "Дмитрий"),
                new Employee(Position.NOT_ENGINEER, 40, "Караматулло")
        );
        List<String> engineersList = employees.stream()
                .filter(employee -> employee.getPostion().equals(Position.ENGINEER))
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .map(Employee::getName)
                .toList();
        assert engineersList.size() == 3;
        assert engineersList.get(0).equals("Василий");
        assert engineersList.get(1).equals("Дмитрий");
        assert engineersList.get(2).equals("Алёша");


        //`Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
        double averageAge = employees.stream()
                .filter(employee -> employee.getPostion().equals(Position.ENGINEER))
                .mapToInt(Employee::getAge)
                .average()
                .orElseThrow(() -> new RuntimeException("Список пуст"));
        assert averageAge == (double) 122 / 3;

        //Найдите в списке слов самое длинное
        String firstWord = "ewqgfeqwrgihasdiough";
        String secondWord = "asf";
        String thirdWord = "bedalkfgbvdsnacvnmasognmvaopsdgniuraehgiadsskcgm";
        String fourthWord = "aedalkfgbvdsnacvnmasognmvaopsdgniuraehgiadsskcgd";
        List<String> listOfWords = List.of(firstWord, secondWord, thirdWord, fourthWord);
        Set<String> maxWordsSet = listOfWords.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .orElseThrow().getValue();
        assert maxWordsSet.size() == 2;
        assert maxWordsSet.containsAll(Set.of(fourthWord, thirdWord));

        // Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы,
        // в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        String sentence = "one two two three four three five five five five five";
        var countingMap = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        assert countingMap.get("one") == 1;
        assert countingMap.get("two") == 2;
        assert countingMap.get("three") == 2;
        assert countingMap.get("four") == 1;
        assert countingMap.get("five") == 5;

        //Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины,
        // то должен быть сохранен алфавитный порядок
        var sortedByLength = listOfWords.stream()
                .sorted((o1, o2) -> {
                    if (o1.length() < o2.length()) {
                        return -1;
                    }
                    if (o1.length() > o2.length()) {
                        return 1;
                    }
                    return o1.compareTo(o2);
                })
                .toList();
        assert sortedByLength.get(2).equals(fourthWord);
        assert sortedByLength.get(3).equals(thirdWord);

        //Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом,
        // найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        List<String> arrayOfStings = List.of(
                "sfs sadga rj yk rtyuk",
                "agfgg opom giagonaosgnon vnodn ffffffffffffffffffffffffffffffffff",
                "afij kdsk jsdf h sad ");
        var wordWithMaxLength = arrayOfStings.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparing(String::length))
                .orElseThrow();
        assert wordWithMaxLength.equals("ffffffffffffffffffffffffffffffffff");
    }
}

