package by.sivko.resume.builder.operators.conditions.sql;

import by.sivko.resume.builder.operators.conditions.EqualOperator;

public class SqlEqualOperator extends EqualOperator {

    private final static String SQL_EQUAL_STRING = "%s = '%s'";

    public SqlEqualOperator(String column, String value) {
        super(column, value);
    }

    @Override
    public String getStringExpression() {
        return String.format(SQL_EQUAL_STRING, super.column, this.value);
    }
}
