package by.sivko.resume.builder.operators.conditions;

public abstract class LikeOperator extends ConditionOperator {
    public LikeOperator(String column, String value) {
        super(column, value, ConditionOperationType.LIKE);
    }
}
