package lesson_one;

public class Application {
    public static void main(String[] args) {
        TestRunner testRunner = new TestRunner();
        try {
            testRunner.runTest(TestClass.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
