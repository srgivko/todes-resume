package by.sivko.resume;

import by.sivko.resume.convertors.GenderConverter;
import by.sivko.resume.entities.Contact;
import by.sivko.resume.entities.Resume;
import by.sivko.resume.entities.Technology;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Properties prop = new Properties();

                prop.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/resume");

                //You can use any database you want, I had it configured for Postgres
                prop.setProperty("dialect", "org.hibernate.dialect.PostgresSQL");

                prop.setProperty("hibernate.connection.username", "postgres");
                prop.setProperty("hibernate.connection.password", "root");
                prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");

                final Configuration configuration = new Configuration()
                        .addProperties(prop)
                        .addAnnotatedClass(Resume.class)
                        .addAnnotatedClass(Technology.class)
                        .addAnnotatedClass(Contact.class);
                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
