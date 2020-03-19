package by.sivko.resume.builder.operations.sql;

import by.sivko.resume.builder.operations.SelectOperator;

import javax.persistence.Entity;
import javax.persistence.Table;

public class SqlSelectOperator extends SelectOperator {

    private static final String SQL_SELECT_STRING = "SELECT * FROM %s";

    public SqlSelectOperator(Class aClass) {
        super(aClass);
    }

    @Override
    public String getStringExpression() {
        String tableName = null;
        if (aClass.isAnnotationPresent(Table.class)) {
            final Table tableAnnotation = (Table) aClass.getAnnotation(Table.class);
            tableName = tableAnnotation.name();
        }

        if (tableName == null && aClass.isAnnotationPresent(Entity.class)) {
            tableName = aClass.getSimpleName().toLowerCase();
        }

        if(tableName == null) {
            throw new IllegalArgumentException(String.format("Class [%s] isn't a entity", aClass.getName()));
        }
        return String.format(SQL_SELECT_STRING, aClass.getSimpleName());
    }
}
