package by.sivko.resume.builder.operators.selects;

import by.sivko.resume.builder.operators.Operator;

public abstract class SelectOperator implements Operator {
    protected Class aClass;

    public SelectOperator(Class aClass) {
        this.aClass = aClass;
    }
}
