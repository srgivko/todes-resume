package by.sivko.resume.builder.operations.sql;

import by.sivko.resume.builder.operations.BinaryOperationType;
import by.sivko.resume.builder.operations.BinaryOperator;

public class SqlLikeOperator extends BinaryOperator {

    private final static String SQL_LIKE_STRING = "%s LIKE '%s'";

    public SqlLikeOperator(String column, String value, BinaryOperationType type) {
        super(column, value, type);
    }

    @Override
    public String getStringExpression() {
        return String.format(SQL_LIKE_STRING, super.column, super.value);
    }
}
