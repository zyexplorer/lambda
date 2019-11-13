package com.jdk.lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Title: LambdaPractice</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author ZY
 * <p> Just go on !!!</p>
 * @date 2019年11月12日  13:29
 */
public class LambdaTest {

    /**
     * http的GET请求参数：index.do?itemId=1&userId=1003&type=2&token=sqwer1234wsdf&key=api
     */

    /**
     * 将get请求的请求参数转换成map
     */
    @Test
    public void test1() {
        String queryStr = "itemId=1&userId=1003&type=2&token=sqwer1234wsdf&key=api";
        // 1.先将字符串按&分割成字符串数组 ["itemId=1","userId=1003","type=2","token=sqwer1234wsdf","key=api"]
        String[] splitStr = queryStr.split("&");
        // 2.将字符串数组转成流，流中的元素就是数组中的元素(字符串)
        Stream<String> strStream = Stream.of(splitStr);
        // 3.对上面得到的流(每个元素为字符串)进行操作，将流中的每个元素再按=分割为数组，变成另一种流(每个元素为字符串数组)
        Stream<String[]> strArrStream = strStream.map(str -> str.split("="));
        // 4.再对元素为字符串数组的流进行collect操作转换成map
        Map<String, String> params1 = strArrStream.collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
        System.out.println(JSON.toJSONString(params1));

        System.out.println("=======================================================");

        // 一行代码搞定
        Map<String, String> params2 = Stream.of(queryStr.split("&")).map(str -> str.split("="))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
        System.out.println(JSON.toJSONString(params2));
    }

    /**
     * 将list转成map
     */
    @Test
    public void test2() {
        // 1.将list转成Stream流
        Stream<Book> bookStream = this.getBookList().stream();

        // 2.通过Stream将list转成map
        Map<Integer, Book> map = bookStream.collect(Collectors.toMap(Book::getId, book -> book));
        System.out.println(map);
    }

    /**
     * 将map转成list： key转list ， value转list
     */
    @Test
    public void test3() {
        // 1.将map的key值转换成Stream流
        Stream<String> stream2 = this.getMap().keySet().stream();
        // 2.通过Stream将map转成list
        List<String> list2 = stream2.collect(Collectors.toList());
        System.out.println(list2);

        // 1.将map的值转成Stream流
        Stream<Object> stream1 = this.getMap().values().stream();
        // 2.通过Stream将map转成list
        List<Object> list1 = stream1.collect(Collectors.toList());
        System.out.println(list1);
    }

    /**
     * 将所有图书的id放到list中
     */
    @Test
    public void test4() {
        Stream<Book> stream = this.getBookList().stream();
        List<Integer> idList = stream.map(Book::getId).collect(Collectors.toList());
        System.out.println(idList);
    }

    /**
     * 将所有图书的id拼接成 (1,2,3……) 的字符串形式
     * mybatis中使用in查询的时候这样转换可行
     */
    @Test
    public void test5() {
        Stream<Book> stream = this.getBookList().stream();
        Stream<String> strStream = stream.map(book -> book.getId() + "");
        String idStr = strStream.collect(Collectors.joining(",", "(", ")"));
        System.out.println(idStr);

        System.out.println("===========================================");

        String str1 = this.getBookList().stream().map(book -> book.getId() + "").collect(Collectors.joining(","));
        System.out.println(str1);
        String str2 = this.getBookList().stream().map(book -> book.getId() + "").collect(Collectors.joining(",", "(", ")"));
        System.out.println(str2);
        String str3 = this.getBookList().stream().map(book -> "'" + book.getId() + "'").collect(Collectors.joining(",", "(", ")"));
        System.out.println(str3);
    }

    /**
     * 找出所有图书的类型(去重)
     */
    @Test
    public void test6() {
        List<String> typeList1 = this.getBookList().stream().map(Book::getType).collect(Collectors.toList());
        System.out.println(typeList1);

        // 去重
        List<String> typeList2 = this.getBookList().stream().map(Book::getType).distinct().collect(Collectors.toList());
        System.out.println(typeList2);

        // 去重
        Set<String> typeSet = this.getBookList().stream().map(Book::getType).collect(Collectors.toSet());
        System.out.println(typeSet);
    }

    /**
     * 排序
     * 将所有图书按照价格排序，再按照出版时间排序
     */
    @Test
    public void test7() {
        List<Book> bookList = this.getBookList().stream().sorted(Comparator.comparing(Book::getPrice))
                .collect(Collectors.toList());
        System.out.println(bookList);

        System.out.println("===========================================");

        // 默认价格从低到高(默认各字段都是从小到大排序的)
        this.getBookList().stream().sorted(Comparator.comparing(Book::getPrice))
                .collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("============================================");

        // 价格从高到低
        this.getBookList().stream().sorted(Comparator.comparing(Book::getPrice).reversed())
                .collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("============================================");

        // 价格相同再按时间由大到小排序
        this.getBookList().stream().sorted(Comparator.comparing(Book::getPrice)
                .thenComparing(Comparator.comparing(Book::getPublishDate).reversed()))
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    /**
     * 统计所有图书的平均价格
     */
    @Test
    public void test8() {
        Double avg = this.getBookList().stream().collect(Collectors.averagingDouble(Book::getPrice));
        System.out.println(avg);
    }

    /**
     * 找出所有书籍中价格最高(最低)的，如果价格相同就按发布时间最近的
     */
    @Test
    public void test9() {
        // 1.将list转成Stream流
        Stream<Book> bookStream1 = this.getBookList().stream();
        Stream<Book> bookStream2 = this.getBookList().stream();
        // 2.找出最高价格的书籍(如果价格相同再按发布时间排序:最近或最早)
        // 发布时间最近
        Optional<Book> maxPriceBookOpt1 = bookStream1.max(Comparator.comparing(Book::getPrice)
                .thenComparing(Book::getPublishDate));
        Book maxPriceBook1 = maxPriceBookOpt1.get();
        System.out.println(maxPriceBook1);
        // 发布时间最早
        Optional<Book> maxPriceBookOpt2 = bookStream2.max(Comparator.comparing(Book::getPrice)
                .thenComparing(Comparator.comparing(Book::getPublishDate).reversed()));

        Book maxPriceBook2 = maxPriceBookOpt2.get();
        System.out.println(maxPriceBook2);
    }

    /**
     * 找出所有图书各类型及其对应的数量
     */
    @Test
    public void test10() {
        // 根据书籍类型来分类
        Map<String, List<Book>> bookMap = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType));
        bookMap.keySet().forEach(key -> {
            System.out.println(key);
            System.out.println(bookMap.get(key));
            System.out.println("=============================");
        });

        Map<String, Long> bookCountMap = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType, Collectors.counting()));
        System.out.println(bookCountMap);

        // 每种图书的总数据统计(数量、总和、最大、最小、平均)
        Map<String, DoubleSummaryStatistics> m1 = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType, Collectors.summarizingDouble(Book::getPrice)));
        System.out.println(m1);

        // 每种图书的总价格
        Map<String, Double> m2 = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType, Collectors.summingDouble(Book::getPrice)));
        System.out.println(m2);

        // 每种图书的平均价格
        Map<String, Double> m3 = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType, Collectors.averagingDouble(Book::getPrice)));
        System.out.println(m3);

        Map<String, Optional<Book>> mm;

        // 找出每种类型图书中最贵的
        mm = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing(Book::getPrice))));
        System.out.println(mm);

        // 找出每种类型图书中最便宜的
        mm = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType, Collectors.minBy(Comparator.comparing(Book::getPrice))));
        System.out.println(mm);

        // 找出每种图书出版最晚的(出版时间最大)
        mm = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing(Book::getPublishDate))));
        System.out.println(mm);

        // 找出每种图书出版最早的(出版时间最小)
        Map<String, Optional<Book>> mmm;
        mmm = this.getBookList().stream().collect(Collectors.groupingBy(Book::getType, Collectors.minBy(Comparator.comparing(Book::getPublishDate))));
        System.out.println(mmm);
    }

    /**
     * 过滤
     */
    @Test
    public void test11() {
        List<Book> list = this.getBookList().stream().filter(book -> book.getPrice() >= 100).sorted(Comparator.comparing(Book::getPublishDate)).collect(Collectors.toList());
        System.out.println(list);

        this.getBookList().stream().filter(book -> book.getPrice() > 100).sorted(Comparator.comparing(Book::getPublishDate)).forEach(System.out::println);
    }

    /**
     * 过滤掉list中的null值
     */
    @Test
    public void test12() {
        List list = Lists.newArrayList();
        list.add(null);
        list.add("张三");
        list.add(123);
        list.add(null);
        System.out.println(list);

        List l = (List) list.stream().filter(x -> x != null).collect(Collectors.toList());
        System.out.println(l);
    }




    public List<Book> getBookList() {
        List<Book> bookList = Lists.newArrayList();
        bookList.add(new Book(1, "tomcat", 50d, "服务器", LocalDate.parse("2015-10-11")));
        bookList.add(new Book(2, "nginx", 60d, "服务器", LocalDate.parse("2009-12-23")));
        bookList.add(new Book(3, "jetty", 50d, "服务器", LocalDate.parse("2000-01-12")));
        bookList.add(new Book(4, "java", 150d, "编程语言", LocalDate.parse("2006-12-15")));
        bookList.add(new Book(5, "C#", 120d, "编程语言", LocalDate.parse("2012-12-31")));
        bookList.add(new Book(6, "python", 150d, "编程语言", LocalDate.parse("2014-08-14")));
        bookList.add(new Book(7, "Go", 100d, "编程语言", LocalDate.parse("2011-09-23")));
        bookList.add(new Book(8, "ruby", 130d, "编程语言", LocalDate.parse("2003-12-15")));
        bookList.add(new Book(9, "oracle", 100d, "数据库", LocalDate.parse("2012-12-01")));
        bookList.add(new Book(10, "mysql", 80d, "数据库", LocalDate.parse("2007-01-01")));
        bookList.add(new Book(11, "mongoDB", 75d, "数据库", LocalDate.parse("2004-06-09")));
        bookList.add(new Book(12, "SQL server", 60d, "数据库", LocalDate.parse("2016-09-08")));
        bookList.add(new Book(13, "设计模式", 115d, "其他", LocalDate.parse("2018-08-05")));
        bookList.add(new Book(14, "算法导论", 120d, "其他", LocalDate.parse("2019-10-12")));
        bookList.add(new Book(15, "敏捷开发", 80d, "其他", LocalDate.parse("2004-06-16")));
        return bookList;
    }

    public Map getMap() {
        Map map = Maps.newHashMap();
        map.put("id", 1);
        map.put("name", "测试");
        map.put("price", 100d);
        map.put("publishDate", LocalDate.parse("2019-11-12"));
        return map;
    }
}


@Data
class Book implements Serializable {
    private static final long serialVersionUID = -6662879407036462907L;
    int id;
    String name;
    Double price;
    String type;
    LocalDate publishDate;

    public Book(int id, String name, Double price, String type, LocalDate publishDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.publishDate = publishDate;
    }
}