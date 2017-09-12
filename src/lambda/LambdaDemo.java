package lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 */
public class LambdaDemo {

    public static void main(String[] args) {
        LambdaDemo lambdaDemo = new LambdaDemo();
//        lambdaDemo.demo1();
//        lambdaDemo.demo2();
//        lambdaDemo.demo3();
//        lambdaDemo.demo4();
//        lambdaDemo.demo5();
//        lambdaDemo.demo6();
//        lambdaDemo.demo7();
//        lambdaDemo.demo8();
//        lambdaDemo.demo9();
        lambdaDemo.demo10();
    }

    public void text1(int data1, int data2) {
        System.out.println("text1=" + (data1 + data2));
    }

    public int text2(int data1, int data2) {
        return data1 + data2;
    }

    /**
     * 使用() -> {} 替代匿名类：
     */
    private void demo1() {
        //无参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("use default");
            }
        }).start();
        new Thread(() -> System.out.println("use lambda")).start();
        //有参数
        new Thread(() -> text1(1, 2)).start();
        //有返回值
        new Thread(() -> System.out.println("text2=" + text2(1, 2))).start();
    }

    /**
     * 事件监听处理
     */
    private void demo2() {
        JButton show = new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("without lambda expression is boring");
            }
        });
        show.addActionListener(e -> System.out.println("Action !! Lambda expressions Rocks"));
    }

    /**
     * 使用lambda表达式遍历list集合
     */
    private void demo3() {
        List<String> dataList = Arrays.asList("data1", "data2", "data3", "data4");
        for (String data : dataList) {
            System.out.println(data);
        }
//        dataList.forEach(data-> System.out.println(data));
        //方法引用是使用两个冒号::这个操作符号。
        dataList.forEach(System.out::println);
    }

    /**
     * 使用lambda表达函数接口
     * 为了支持函数编程，Java 8加入了一个新的包java.util.function，
     * 其中有一个接口java.util.function.Predicate是支持Lambda函数编程
     */
    private void demo4() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> (str).startsWith("J"));
        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> (str).endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> (str).length() > 4);
    }

    private void filter(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name)))
                .forEach((name) -> {
                    System.out.println(name + " ");
                });
    }

    /**
     * 复杂的结合Predicate 使用
     * java.util.function.Predicate提供and(), or() 和 xor()可以进行逻辑操作，
     * 比如为了得到一串字符串中以"J"开头的4个长度：
     */
    private void demo5() {
        List<String> name = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        name.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("\nName, which starts with 'J' and four letter long is : " + n));
    }

    /**
     * 使用Lambda实现Map 和 Reduce
     * 最流行的函数编程概念是map，它允许你改变你的对象，
     * 在这个案例中，我们将costBeforeTeax集合中每个元素改变了增加一定的数值，
     * 我们将Lambda表达式 x -> x*x传送map()方法，这将应用到stream中所有元素。
     * 然后我们使用 forEach() 打印出这个集合的元素.
     */
    private void demo6() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map(cost -> cost + .12 * cost).
                forEach(System.out::println);
    }

    /**
     * 通过filtering 创建一个字符串String的集合
     * Filtering是对大型Collection操作的一个通用操作，
     * Stream提供filter()方法，接受一个Predicate对象，
     * 意味着你能传送lambda表达式作为一个过滤逻辑进入这个方法：
     */
    private void demo7() {
        List<String> strList = Arrays.asList(" ", "abc", "cxz", "qwer", "a", "s", "yy");
        List<String> filtered = strList.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
    }

    /**
     * 对集合中每个元素应用函数
     * 对集合中的元素进行操作
     */
    private void demo8() {
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        //将字符串转换为大写，然后使用逗号串起来。
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(","));
        System.out.println(G7Countries);
    }

    /**
     * 通过复制不同的值创建一个子列表
     * 使用Stream的distinct()方法过滤集合中重复元素。
     */
    private void demo9() {
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Original List :" + numbers + "  Square Without duplicates :" + distinct);
    }

    /**
     * 计算List中的元素的最大值，最小值，总和及平均值
     */
    private void demo10() {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }
}
