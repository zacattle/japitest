package junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;

/**
 * 方法参数数据源测试
 * @author zacattle
 *
 */
public class MethodParamsSourceTest {
	
    
    //
    
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }
    
    
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithEnumSource(TimeUnit timeUnit) {
        assertNotNull(timeUnit);
    }
    
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
    	// 以下代码会报错，因为只能是DAYS或者HOURS
        assertTrue(EnumSet.of(TimeUnit.MINUTES, TimeUnit.HOURS).contains(timeUnit));
    }

    
    /**
     * @MethodSource 会执行stringProvider方法得到数据源，必须是流、可迭代、迭代器或参数数组类型
     * 迭代stringProvider方法返回值执行测试方法
     * @MethodSource 必须是静态方法
     * @param argument
     */
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithSimpleMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }

    
    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
    	System.out.println("数据："+argument);
        assertNotEquals(9, argument);
    }

    /**
     * DoubleStream、IntStream和LongStream 同样适用
     * @return
     */
    static IntStream range() {
    	IntStream intStream =  IntStream.range(0, 20).skip(10);
    	return intStream;
    }
    
    
    /**
     * 多个参数时
     * @param str
     * @param num
     * @param list
     */
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(3, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }
    

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
            Arguments.of("foo", 1, Arrays.asList("a", "b")),
            Arguments.of("bar", 2, Arrays.asList("x", "y"))
        );
    }
    
    
    /**
     * 外部类方法 必须是静态方法
     * @param blankString
     */
    @ParameterizedTest
    @MethodSource("junit5.StringsProviders#blankStrings")
    void testWithExternalMethodSource(String blankString) {
        // test with blank string
    	System.out.println(blankString.trim().length());
    	assertTrue(blankString.trim().isEmpty());
    }

    
    
    /**
     * 逗号分隔解析数据源,默认是逗号分隔
     * @param first
     * @param second
     */
    @ParameterizedTest
    @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
    void testWithCsvSource(String first, int second) {
    	System.out.println("前："+first+" 后："+second);
        assertNotNull(first);
        assertNotEquals(0, second);
    }
    
    @ParameterizedTest
    @CsvSource(value={ "foo&bar" },delimiter='&')
    void testWithCsvSource1(String first, String second) {
    	System.out.println("前："+first+" 后："+second);
        assertNotNull(first);
    }
    
    /**
     * 外部csv文件
     * @param first
     * @param second
     */
    @ParameterizedTest
    @CsvFileSource(resources = "./two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSource(String first, int second) {
    	System.out.println("前："+first+" 后："+second);
        assertNotNull(first);
        assertNotEquals(0, second);
    }
    
    
    /**
     * 实现ArgumentsProvider接口的数据源
     * @param argument
     */
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertNotNull(argument);
    }
    
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider1.class)
    void testWithArgumentsSource1(String str, int num, List<String> list) {
        assertEquals(3, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }
    
    
    
    /**
     * 自动类型转换，会调用类的构造方法，或者普通方法构建
     * @param book
     */
    @ParameterizedTest
    @ValueSource(strings = "42 Cats")
    void testWithImplicitFallbackArgumentConversion(Book book) {
    	System.out.println(book.getTitle());
        assertEquals("42 Cats", book.getTitle());
    }
    
    
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithExplicitArgumentConversion(
            @ConvertWith(ToStringArgumentConverter.class) String argument) {
    	System.out.println(argument);// 查看输出得到的是一个列表，即 枚举类型自动会得到迭代
        assertNotNull(TimeUnit.valueOf(argument));
    }
    
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithExplicitArgumentConversion1(
            @ConvertWith(ToStringArgumentConverter1.class) Book book) {
    	System.out.println(book.getTitle());//
        assertNotNull(book);
    }

    
    /**
     * 查看 JavaTimeConversionPattern 注解，使用系统内置的参数转换器 JavaTimeArgumentConverter
     * @param argument
     */
    @ParameterizedTest
    @ValueSource(strings = { "01.01.2017", "31.12.2017" })
    void testWithExplicitJavaTimeConverter(
            @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
        assertEquals(2017, argument.getYear());
    }
    
    /**
     * 使用参数访问器
     * @param arguments
     */
    @ParameterizedTest
    @CsvSource({
        "Jane, Doe, F, 1990-05-20",
        "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
        assertEquals("Doe", arguments.get(1));
        assertEquals(1990, arguments.get(3,LocalDate.class).getYear());
    }
    
    
    /**
     * 自定义参数访问器转换规则
     * @param book
     */
    @ParameterizedTest
    @CsvSource({
        "Jane, Doe, F, 1990-05-20",
        "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAggregator(@AggregateWith(BookAggregator.class) Book book) {
        // perform assertions against person
    	System.out.println(book.getTitle());
    	assertNotNull(book);
    }
    
    
    
    
    /**
     * 自定义测试方法的名字及输出的参数的格式
     * @param first
     * @param second
     */
    @DisplayName("当前方法显示的名字")
    @ParameterizedTest(name = "当前参数索引：{index} ==> first=''{0}'', second={1}")
    @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
    void testWithCustomDisplayNames(String first, int second) {
    	System.out.println("前："+first + " 后："+second);
    }
    
    
    
    
    
    
    

}


class StringsProviders {
    static Stream<String> blankStrings() {
    	return Stream.of("", " ", " \n ");
    }
}


class MyArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of("foo", "bar").map(Arguments::of);
    }
}

class MyArgumentsProvider1 implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("foo", 1, Arrays.asList("a", "b")),
                Arguments.of("bar", 2, Arrays.asList("x", "y")));
    }
}

class Book {

    private final String title;

    public Book(String title) {
        this.title = title;
    }

    public static Book fromTitle(String title) {
        return new Book("[同时存在一个String类型参数的普通方法和构造方法时，调用普通方法构建] title:"+title);
    }

    public String getTitle() {
        return this.title;
    }
}

// 自定义类型转换
class ToStringArgumentConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
    	//
        assertEquals(String.class, targetType, "Can only convert to String");
        return String.valueOf(source);
    }
}

class ToStringArgumentConverter1 extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
    	//
        return new Book(String.valueOf(source));
    }
}

class BookAggregator implements ArgumentsAggregator {
    @Override
    public Book aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        return new Book(arguments.getString(0)+":"+
                          arguments.getString(1)+":"+
                          arguments.get(2)+":"+
                          arguments.get(3, LocalDate.class));
    }
}






