package by.sivko.resume.builder.operations;

public abstract class BinaryOperator implements Operator {

    protected final String column;
    protected final String value;
    protected final BinaryOperationType type;

    public BinaryOperator(String column, String value, BinaryOperationType type) {
        this.column = column;
        this.value = value;
        this.type = type;
    }
}
