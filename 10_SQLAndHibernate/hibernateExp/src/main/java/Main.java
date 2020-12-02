import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        String idCourse;
        do {
            System.out.println("Введите id курса ");
            idCourse = scn.nextLine();
        }
        while (!isNumber(idCourse));
        int idNum = Integer.parseInt(idCourse);
        Course course = session.get(Course.class, idNum);
        System.out.println("Название курса - " + course.getName() + "Количество студентов на куре - " + course.getStudentsCount());


        sessionFactory.close();
    }

    public static boolean isNumber(String a) {
        try {
            int i = Integer.parseInt(a);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}

