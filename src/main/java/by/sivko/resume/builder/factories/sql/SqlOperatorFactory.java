package by.sivko.resume.builder.factories.sql;

import by.sivko.resume.builder.factories.AbstractOperatorFactory;
import by.sivko.resume.builder.operators.binary.AndOperator;
import by.sivko.resume.builder.operators.binary.OrOperator;
import by.sivko.resume.builder.operators.binary.sql.SqlAndOperator;
import by.sivko.resume.builder.operators.binary.sql.SqlOrOperator;
import by.sivko.resume.builder.operators.conditions.EqualOperator;
import by.sivko.resume.builder.operators.conditions.LikeOperator;
import by.sivko.resume.builder.operators.conditions.sql.SqlEqualOperator;
import by.sivko.resume.builder.operators.conditions.sql.SqlLikeOperator;
import by.sivko.resume.builder.operators.selects.SelectOperator;
import by.sivko.resume.builder.operators.selects.sql.SqlSelectOperator;

public class SqlOperatorFactory extends AbstractOperatorFactory {

    @Override
    public SelectOperator createSelectOperator(Class aClass) {
        return new SqlSelectOperator(aClass);
    }

    @Override
    public LikeOperator createLikeOperator(String column, String value) {
        return new SqlLikeOperator(column, value);
    }

    @Override
    public EqualOperator createEqualOperator(String column, String value) {
        return new SqlEqualOperator(column, value);
    }

    @Override
    public AndOperator createAndOperator() {
        return new SqlAndOperator();
    }

    @Override
    public OrOperator createOrOperator() {
        return new SqlOrOperator();
    }
}

