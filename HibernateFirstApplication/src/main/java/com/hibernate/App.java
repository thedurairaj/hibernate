package com.hibernate;

import com.hibernate.entity.Student;
import com.hibernate.repository.StudentRepository;
import com.hibernate.repository.dao.StudentDao;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        StudentDao studentDao = new StudentRepository();

        String[] names = {
                "Ravi", "Amit", "Suresh", "Rahul", "Kiran"
        };


        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int id = random.nextInt(names.length);
            String randomName = names[id];

            id += random.nextInt();
            int mark = random.nextInt(100);

            studentDao.save(new Student(id, randomName, "male", mark));
        }

        new StudentRepository().close();

    }
}
