import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            List<Object[]> departments = session.createNativeQuery("SELECT Students.id AS Students_id, Courses.id AS Courses_id from PurchaseList JOIN Students ON Students.name = PurchaseList.student_name \n" +
                    "JOIN Courses ON Courses.name = PurchaseList.course_name;").list();
            for (Object[] objects : departments) {
                Integer studentId = (Integer) objects[0];
                Integer coursesId = (Integer) objects[1];
                LinkedPurchase linkedPurchase = new LinkedPurchase(studentId,coursesId);
                session.save(linkedPurchase);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                sessionFactory.close();
                session.close();
            }
        }

    }
}

