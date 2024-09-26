package ru.itis.test;

import ru.itis.ticket1.task2.LexicographicalComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        Dog dog = new Dog("Рекс");
//        dog.play();

//        Playable playable = new Playable() {
//            @Override
//            public void play() {
//                System.out.println("Пример анонимного класса");
//            }
//        };
//
//      playable.play();

        //    Playable playable = () -> System.out.println("Пример лямбда выражения");
//
//        playable.play();
//
//        playable = PlayableImpl::someMethod;
//
//        playable.play();
//
//
        List<Integer> integers = new ArrayList<>();
        integers.add(new Integer(4));
        integers.add(new Integer(3));
        integers.add(new Integer(2));
        integers.add(new Integer(1));


//
        System.out.println(integers);
//
        Collections.sort(integers);
//
        System.out.println(integers);



//        Отрицательное число, если o1 должно быть меньше o2.
//                Ноль, если o1 и o2 считаются равными.
//                Положительное число, если o1 должно быть больше o2.
        Comparator<Integer> comparator = (Integer o1, Integer o2) -> {
            //o1>o2
            if (o1 - o2 > 0) {
                return o1 - o2;
                //o1<o2
            } else if (o1 - o2 < 0) {
                return o1 - o2;
            }
            //o1 = o2
            return 0;
        };
//
//
//        Collections.sort(integers, comparator);
//
//        System.out.println(integers);


//        List<Integer> integers = new ArrayList<>();
//
//        integers.add(234);
//        integers.add(123456);
//
//        LexicographicalComparator lexicographicalComparator= new LexicographicalComparator();
//
//        Collections.sort(integers, lexicographicalComparator);
//
//        System.out.println(integers);


    }

}
