package by.sivko.resume.builder.operations.sql;

import by.sivko.resume.builder.operations.Operator;

public class SqlOrOperator implements Operator {

    private static final String SQL_AND_STRING = " OR ";

    @Override
    public String getStringExpression() {
        return SQL_AND_STRING;
    }
}
