package by.sivko.resume.builder.operators.binary.sql;

import by.sivko.resume.builder.operators.binary.AndOperator;

public class SqlAndOperator implements AndOperator {

    private static final String SQL_AND_STRING = " AND ";

    @Override
    public String getStringExpression() {
        return SQL_AND_STRING;
    }
}
