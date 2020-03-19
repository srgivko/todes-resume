package by.sivko.resume;

import by.sivko.resume.builder.Query;
import by.sivko.resume.builder.SqlQuery;
import by.sivko.resume.entities.Resume;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class Main {
    public static void main(String[] args) {
        Query query = SqlQuery.builder()
                .select(Resume.class)
                .equal("first_name", "Петр")
                .and()
                .equal("last_name", "Петров")
                .and()
                .equal("middle_name", "Петрович")
                .build();
        System.out.println(query.getQuery());
        System.out.println(query.getQueryParameters());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        NativeQuery<Resume> nativeQuery = session.createNativeQuery(query.getQuery(), Resume.class);
        nativeQuery.getResultList().forEach(System.out::println);
        session.getTransaction().commit();
        session.close();

        query = SqlQuery.builder()
                .select(Resume.class)
                .like("last_name", "%ов")
                .or()
                .equal("gender", "женщина")
                .build();
        System.out.println(query.getQuery());
        System.out.println(query.getQueryParameters());
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        nativeQuery = session.createNativeQuery(query.getQuery(), Resume.class);
        nativeQuery.getResultList().forEach(System.out::println);
        session.getTransaction().commit();
        session.close();


    }
}
