package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
//        Stream<Person> stream = persons.stream()
        long stream = persons.stream()
                .filter(i->i.getAge()<18)
                .count();


        List<String> stream2 = persons.stream()
                .filter(s->s.getSex()==Sex.MAN)
                .filter(i->i.getAge()>18 && i.getAge()<28)
                .map(Person::getFamily)

                .collect(Collectors.toList());


        List<Person> stream3 = persons.stream()
                .filter(s->s.getSex()==Sex.WOMAN)
                .filter(i->i.getAge()>18 && i.getAge()<60)
                .filter(s->s.getSex()==Sex.MAN)
                .filter(i->i.getAge()>18 && i.getAge()<65)
                .filter(s->s.getEducation()==Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
