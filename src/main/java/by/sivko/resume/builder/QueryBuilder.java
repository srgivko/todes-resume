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

    public QueryBuilder select(Class aClass) {
        this.addBuildingStep(this.operatorFactory.createSelectOperator(aClass));
        return this;
    }

    public QueryBuilder equal(String columnName, String value) {
        final ConditionOperator conditionOperator = this.operatorFactory.createEqualOperator(columnName, value);
        this.addBuildingStep(conditionOperator);
        this.addConditionOperatorToQuery(conditionOperator);
        return this;
    }

    public QueryBuilder like(String columnName, String value) {
        final ConditionOperator conditionOperator = this.operatorFactory.createLikeOperator(columnName, value);
        this.addBuildingStep(conditionOperator);
        this.addConditionOperatorToQuery(conditionOperator);
        return this;
    }

    public QueryBuilder and() {
        this.addBuildingStep(this.operatorFactory.createAndOperator());
        return this;
    }

    public QueryBuilder or() {
        this.addBuildingStep(this.operatorFactory.createOrOperator());
        return this;
    }

    public Query build() {
        this.isValidBuildingSteps();

        final Operator selectOperator = this.buildingSteps.get(0);
        this.appendQuery(selectOperator.getStringExpression());

        for (int indexOfBuildingStep = 0; indexOfBuildingStep < this.buildingSteps.size(); indexOfBuildingStep++) {
            this.appendQuery(this.buildingSteps.get(indexOfBuildingStep).getStringExpression());
        }

        return this.getQuery();
    }

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

    protected abstract void addConditionOperatorToQuery(ConditionOperator operator);

    protected abstract void appendQuery(String expression);

    protected abstract Query getQuery();

}
