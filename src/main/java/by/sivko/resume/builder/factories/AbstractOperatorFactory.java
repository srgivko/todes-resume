package by.sivko.resume.builder.factories;

import by.sivko.resume.builder.operators.binary.AndOperator;
import by.sivko.resume.builder.operators.binary.OrOperator;
import by.sivko.resume.builder.operators.conditions.EqualOperator;
import by.sivko.resume.builder.operators.conditions.LikeOperator;
import by.sivko.resume.builder.operators.selects.SelectOperator;

public abstract class AbstractOperatorFactory {

    public abstract SelectOperator createSelectOperator(Class aClass);

    public abstract LikeOperator createLikeOperator(String column, String value);

    public abstract EqualOperator createEqualOperator(String column, String value);

    public abstract AndOperator createAndOperator();

    public abstract OrOperator createOrOperator();
}
