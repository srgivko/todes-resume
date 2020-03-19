package by.sivko.resume.builder.operations.sql;

import by.sivko.resume.builder.operations.BinaryOperationType;
import by.sivko.resume.builder.operations.BinaryOperator;

public class SqlEqualOperator extends BinaryOperator {

    private final static String SQL_EQUAL_STRING = "%s = '%s'";

    public SqlEqualOperator(String column, String value, BinaryOperationType type) {
        super(column, value, type);
    }

    public String getStringExpression() {
        return String.format(super.column, this.value);
    }
}
