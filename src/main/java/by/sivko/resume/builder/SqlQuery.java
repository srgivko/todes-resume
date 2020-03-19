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
        public QueryBuilder select(Class aClass) {
            super.addBuildingStep(super.operatorFactory.createSelectOperator(aClass));
            return this;
        }

        @Override
        public QueryBuilder equal(String columnName, String value) {
            final ConditionOperator conditionOperator = super.operatorFactory.createEqualOperator(columnName, value);
            super.addBuildingStep(conditionOperator);
            SqlQuery.super.addConditionOperator(conditionOperator);
            return this;
        }

        @Override
        public QueryBuilder like(String columnName, String value) {
            final ConditionOperator conditionOperator = super.operatorFactory.createLikeOperator(columnName, value);
            super.addBuildingStep(conditionOperator);
            SqlQuery.super.addConditionOperator(conditionOperator);
            return this;
        }

        @Override
        public QueryBuilder and() {
            super.addBuildingStep(super.operatorFactory.createAndOperator());
            return this;
        }

        @Override
        public QueryBuilder or() {
            super.addBuildingStep(super.operatorFactory.createOrOperator());
            return this;
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
                SqlQuery.super.appendQuery(super.buildingSteps.get(indexOfBuildingStep).getStringExpression());
            }

            return SqlQuery.this;
        }

        private void addWhereClause() {
            String WHERE = " WHERE ";
            SqlQuery.super.appendQuery(WHERE);
        }

    }
}
