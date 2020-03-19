package by.sivko.resume.builder.operators.binary.sql;

import by.sivko.resume.builder.operators.binary.OrOperator;

public class SqlOrOperator implements OrOperator {

    private static final String SQL_AND_STRING = " OR ";

    @Override
    public String getStringExpression() {
        return SQL_AND_STRING;
    }
}
