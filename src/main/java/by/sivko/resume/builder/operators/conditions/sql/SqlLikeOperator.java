package by.sivko.resume.builder.operators.conditions.sql;

import by.sivko.resume.builder.operators.conditions.LikeOperator;

public class SqlLikeOperator extends LikeOperator {

    private final static String SQL_LIKE_STRING = "%s LIKE '%s'";

    public SqlLikeOperator(String column, String value) {
        super(column, value);
    }

    @Override
    public String getStringExpression() {
        return String.format(SQL_LIKE_STRING, super.column, super.value);
    }
}
