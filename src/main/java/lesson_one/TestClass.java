package lesson_one;

import lesson_one.annotations.*;

public class TestClass {
    @BeforeTest
    void beforeTest(){
        System.out.println("BeforeTest");
    }

    @AfterTest
    void afterTest(){
        System.out.println("afterTest\n");
    }

    @Test(priority = 1)
    public void runTestAnnotatedMethodOne() {
        System.out.println("runTestAnnotatedMethodOne priority 1");
    }

    @Test
    public void runTestAnnotatedMethodTwo() {
        System.out.println("runTestAnnotatedMethodOne priority 5");
    }

    @Test(priority = 1)
    public void runTestAnnotatedMethodThree() {
        System.out.println("runTestAnnotatedMethodOne priority 1");
    }

    @AfterSuite
    public static void runAfterSuite() {
        System.out.println("runAfterSuite\n");
    }

    @BeforeSuite
    public static void runBeforeSuite() {
        System.out.println("runBeforeSuite\n");
    }

    @CsvSource("10,Java,20,true")
    public void csvSourceTest(Integer a, String b, Integer c, boolean d) {
        System.out.println(String.format("csvSourceTestOne a = %s, b = %s, c = %s, d = %s", a, b, c, d));
    };

}
