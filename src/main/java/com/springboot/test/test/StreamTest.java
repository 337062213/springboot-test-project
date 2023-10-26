package com.springboot.test.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args){
        List<Double>  list100 = generateRandom(100);
        System.out.println(Arrays.toString(list100.toArray()));
        List<Integer>  iterate100 = iterate100(100);
        System.out.println(Arrays.toString(iterate100.toArray()));
        List<String>  of100 = of100();
        System.out.println(Arrays.toString(of100.toArray()));
        String  reduce100 = reduce100();
        System.out.println(reduce100);
    }
    private static List<Double>  generateRandom(int limitNumber){
        List<Double> list100 = Stream.generate(new Supplier<Double>()

        {
            @Override
            public Double get() {
                return Math.random();
            }
        }).limit(limitNumber).collect(Collectors.toList());
        return list100;
    }
    private static List<Integer>  iterate100(int limitNumber){
        List<Integer> list100 = Stream.iterate(1,item->item+1).limit(limitNumber).collect(Collectors.toList());
        return list100;
    }
    private static List<String>  of100(){
        Stream<List<String>> listStreama = Stream.of(
                Arrays.asList("a"),Arrays.asList("b","c"),Arrays.asList("d","e","f")
        );
        Stream<List<String>> listStreamb = Stream.of(
                Arrays.asList("1"),Arrays.asList("2","3"),Arrays.asList("4","5","6")
        );
        Stream<List<String>> listStream = Stream.concat(listStreama,listStreamb);
        Stream<String> list = listStream.flatMap((child)->child.stream());
        List<String> list100 = list.collect(Collectors.toList());
        return list100;
    }
    private static String  reduce100(){
        Stream<List<String>> listStream = Stream.of(
                Arrays.asList("a"),Arrays.asList("b","c"),Arrays.asList("d","e","f")
        );
        Stream<String> list = listStream.flatMap((child)->child.stream());
        Optional<String> list100 = list.reduce((v1, v2)->v1+","+v2);
        return list100.get();
    }
}
