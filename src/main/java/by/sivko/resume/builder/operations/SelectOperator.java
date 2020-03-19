package by.sivko.resume.builder.operations;

public abstract class SelectOperator implements Operator {
    protected Class aClass;

    public SelectOperator(Class aClass) {
        this.aClass = aClass;
    }
}
