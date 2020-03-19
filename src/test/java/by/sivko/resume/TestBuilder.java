package by.sivko.resume;

import by.sivko.resume.builder.Query;
import by.sivko.resume.builder.SqlQuery;
import by.sivko.resume.entities.Gender;
import by.sivko.resume.entities.Resume;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBuilder {

    @Test
    public void findByEqualFirstNameAndEqualLastNameAndEqualMiddleName() {
        final String firstName = "Петр";
        final String lastName = "Петров";
        final String middleName = "Петрович";
        Query query = SqlQuery.builder()
                .select(Resume.class)
                .equal("first_name", firstName)
                .and()
                .equal("last_name", lastName)
                .and()
                .equal("middle_name", middleName)
                .build();
        System.out.println(query.getQuery());
        query.getQueryParameters().forEach(System.out::println);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        NativeQuery<Resume> nativeQuery = session.createNativeQuery(query.getQuery(), Resume.class);
        final List<Resume> resultList = nativeQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        assertEquals(1, resultList.size());
        assertEquals(firstName, resultList.get(0).getFirstName());
        assertEquals(lastName, resultList.get(0).getLastName());
        assertEquals(middleName, resultList.get(0).getMiddleName());
    }

    @Test
    public void findByLikeLastNameOrEqualGender() {
        final String like = "%ов";
        final String femaleName = Gender.FEMALE.getName();
        Query query = SqlQuery.builder()
                .select(Resume.class)
                .like("last_name", like)
                .or()
                .equal("gender", femaleName)
                .build();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        NativeQuery<Resume> nativeQuery = session.createNativeQuery(query.getQuery(), Resume.class);
        final List<Resume> resultList = nativeQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        assertEquals(3, resultList.size());
    }
}
