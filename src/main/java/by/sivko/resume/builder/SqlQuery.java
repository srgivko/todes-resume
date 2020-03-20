package by.sivko.resume.builder;

import by.sivko.resume.builder.factories.sql.SqlOperatorFactory;
import by.sivko.resume.builder.operators.Operator;
import by.sivko.resume.builder.operators.conditions.ConditionOperator;

public class SqlQuery extends Query {

    public static QueryBuilder builder() {
        return new SqlQuery().new SqlBuilder();
    }

    class SqlBuilder extends QueryBuilder {

        public SqlBuilder() {
            super.operatorFactory = new SqlOperatorFactory();
        }

        @Override
        public Query build() {
            super.isValidBuildingSteps();

            final Operator selectOperator = super.buildingSteps.get(0);
            SqlQuery.super.appendQuery(selectOperator.getStringExpression());

            if (super.buildingSteps.size() > 1) {
                addWhereClause();
            }

            for (int indexOfBuildingStep = 1; indexOfBuildingStep < super.buildingSteps.size(); indexOfBuildingStep++) {
                this.appendQuery(super.buildingSteps.get(indexOfBuildingStep).getStringExpression());
            }

            return getQuery();
        }

        @Override
        protected void addConditionOperatorToQuery(ConditionOperator operator) {
            SqlQuery.super.addConditionOperator(operator);
        }

        @Override
        protected void appendQuery(String expression) {
            SqlQuery.super.appendQuery(expression);
        }

        @Override
        protected Query getQuery() {
            return SqlQuery.this;
        }

        private void addWhereClause() {
            String WHERE = " WHERE ";
            SqlQuery.super.appendQuery(WHERE);
        }

    }
}
