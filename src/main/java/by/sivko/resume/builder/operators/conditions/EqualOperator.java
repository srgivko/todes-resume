package by.sivko.resume.builder.operators.conditions;

public abstract class EqualOperator extends ConditionOperator {
    public EqualOperator(String column, String value) {
        super(column, value, ConditionOperationType.EQUAL);
    }
}
