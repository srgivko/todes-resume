package by.sivko.resume.builder;

import by.sivko.resume.builder.factories.AbstractOperatorFactory;
import by.sivko.resume.builder.operators.Operator;
import by.sivko.resume.builder.operators.binary.BinaryOperator;
import by.sivko.resume.builder.operators.conditions.ConditionOperator;
import by.sivko.resume.builder.operators.selects.SelectOperator;

import java.util.ArrayList;
import java.util.List;

public abstract class QueryBuilder {

    protected List<Operator> buildingSteps = new ArrayList<>();

    protected AbstractOperatorFactory operatorFactory;

    public abstract QueryBuilder select(Class aClass);

    public abstract QueryBuilder equal(String columnName, String value);

    public abstract QueryBuilder like(String columnName, String value);

    public abstract QueryBuilder and();

    public abstract QueryBuilder or();

    public abstract Query build();

    protected void addBuildingStep(Operator operator) {
        this.buildingSteps.add(operator);
    }

    protected void isValidBuildingSteps() throws IllegalArgumentException {
        if (this.buildingSteps.isEmpty()) {
            throw new IllegalArgumentException("Builder hasn't building steps");
        }

        final Operator firstOperator = this.buildingSteps.get(0);
        if (!(firstOperator instanceof SelectOperator)) {
            throw new IllegalArgumentException("The first operation must be select");
        }

        for (int indexOfBuildingStep = 1; indexOfBuildingStep < this.buildingSteps.size(); indexOfBuildingStep++) {
            if (!isValidOperation(indexOfBuildingStep, this.buildingSteps.get(indexOfBuildingStep))) {
                throw new IllegalArgumentException("Wrong order of building steps");
            }
        }
    }

    private boolean isValidOperation(int index, Operator operator) {
        if (index % 2 == 1 && !(operator instanceof ConditionOperator)) {
            return false;
        }
        return index % 2 != 0 || operator instanceof BinaryOperator;
    }
}
