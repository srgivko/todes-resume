package by.sivko.resume.builder.operators.conditions;

import by.sivko.resume.builder.operators.Operator;
import lombok.ToString;

@ToString
public abstract class ConditionOperator implements Operator {

    protected final String column;
    protected final String value;
    protected final ConditionOperationType type;

    public ConditionOperator(String column, String value, ConditionOperationType type) {
        this.column = column;
        this.value = value;
        this.type = type;
    }
}
