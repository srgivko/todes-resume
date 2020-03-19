package by.sivko.resume.builder;

public interface QueryBuilder {
    QueryBuilder select(Class aClass);
    QueryBuilder equal(String columnName, String value);
    QueryBuilder like(String columnName, String value);
    QueryBuilder and();
    QueryBuilder or();
    Query build();
}
