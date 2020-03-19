package by.sivko.resume.builder;

import by.sivko.resume.builder.operators.conditions.ConditionOperator;

import java.util.ArrayList;
import java.util.List;

public abstract class Query {

    protected StringBuilder query = new StringBuilder();
    protected List<ConditionOperator> queryParameters = new ArrayList<>();

    protected Query() {
    }

    public String getQuery() {
        return query.toString();
    }

    public List<ConditionOperator> getQueryParameters() {
        return queryParameters;
    }

    protected void appendQuery(String text) {
        this.query.append(text);
    }

    protected void addConditionOperator(ConditionOperator conditionOperator) {
        this.queryParameters.add(conditionOperator);
    }

}
