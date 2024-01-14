package com.mjia;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;

import java.util.Comparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ModernJavaInAction {

    public static void main(String[] args) throws IOException {
        String ia = "A";
        Printable lambdaPrintable
                = (a) -> System.out.println("Meow!!!.s: "+a);
        //System.out.println("Hello World!");
        lambdaPrintable.print("Hi");

        Printable lambdaPrintable1
                = (String p) -> System.out.println("Meow.p"+p);
        printThing(lambdaPrintable1);

        System.out.println("Xxxxxxxxxxxxxxxxxxxxxx");
        Printable lambdaPrintStatement = (String st) ->
         { 
             st = "Statement "+st;
             //return st;
         };
        lambdaPrintStatement.print("g");
        
        
        System.out.println("BUFERED READER ######");
        String oneLine = processFile((BufferedReader br)
            -> br.readLine());
        System.out.println("ONE LINE: "+oneLine);
        String twoLines = processFile( (BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println("TWO LINES: "+twoLines);
        System.out.println("PREDICATE");
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("");
        listOfStrings.add("one");
        listOfStrings.add("2");
        listOfStrings.add("  ");
        Predicate<String> nonEmptyStringPredicate = 
            (String s) -> !s.isEmpty();
        List<String> nonEmpty = 
            filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println("nonEmpty.size: "+nonEmpty.size());
        for(String s: nonEmpty){
            System.out.println(s.toString());
        }

   System.out.println("######CONSUMER ##########");
     forEach( Arrays.asList("e","2","3","4","5"), (String i) 
             -> System.out.println(i+" _ T")
     );

     System.out.println("########## FUNCTION INTERFACE #########");
     List<Integer> l = 
         map(
        Arrays.asList("lambdas", "in", "action"),
    (String s) -> s.length()
    );
     for(Integer i: l){
        System.out.println(i);
    }
    System.out.println("Boxing-Unboxing");
    IntPredicate evenNumbers = (int i) -> i% 2 == 0;
    System.out.println(evenNumbers.test(1000));

    Predicate<Integer> oddNumbers = (Integer i) ->
        i % 2 != 0;
    System.out.println(oddNumbers.test(1000));
    // Apple a = () -> new Apple("Red", 10);
    //
    System.out.println("#same lambda different functional interfaces #");
    Comparator<Apple> c1 = (Apple a1, Apple a2) ->
        a1.color().compareTo(a2.color()); 
        System.out.println("C1 "+c1);
        
    }

    static void printThing(Printable print) {
        print.print("ss");
    }

    static public String processFile(BufferedReaderProcessor p) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }

    }

    static public <T> List<T> filter(List<T> list, Predicate<T> p){

      List<T> results = new ArrayList<>();
      for(T t: list){
        if(p.test(t)){
            results.add(t);
        }
      }
      return results;
    }
    static public <T> void forEach(List<T> list, Consumer<T> c){
       for(T t: list){
           c.accept(t);
       }
       System.out.println("size.consumer: "+list.size());
    }
    
    static public <T, R> List<R> map(List<T> list, Function<T, R> f){

     List<R> result = new ArrayList<>();
     for(T t: list){
        System.out.println("map.T: "+t); 
        result.add( f.apply(t) );
     }
     System.out.println("size: "+result.size());
     for(R t: result){
      System.out.println("list.Result: "+t);
     }
     return result;
    }

}
