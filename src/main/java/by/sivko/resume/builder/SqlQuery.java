package by.sivko.resume.builder;

import by.sivko.resume.builder.operations.Operator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

public class SqlQuery implements Query {

    private StringBuilder query = new StringBuilder();
    private List<QueryParameter> queryParameters = new ArrayList<>();
    private List<Operator> buildSteps = new ArrayList<>();

    public String getQuery() {
        return query.toString();
    }

    @Override
    public List<QueryParameter> getQueryParameters() {
        return queryParameters;
    }

    public static QueryBuilder builder() {
        return new SqlQuery().new SqlBuilder();
    }

    private void addParameter(QueryParameter queryParameter) {
        this.queryParameters.add(queryParameter);
    }

    private void appendQuery(String text) {
        this.query.append(text);
    }

    @Override
    public String toString() {
        return this.query.toString();
    }

    class SqlBuilder implements QueryBuilder {

        private final String WHERE = " WHERE ";
        private final String SELECT_FROM = "SELECT * FROM %s" + WHERE;
        private final String EQUAL = "%s = '%s'";
        private final String AND = " AND ";
        private final String OR = " OR ";
        private final String LIKE = "%s LIKE '%s'";

        @Override
        public QueryBuilder select(Class aClass) {
            String tableName = null;
            if (aClass.isAnnotationPresent(Table.class)) {
                final Table tableAnnotation = (Table) aClass.getAnnotation(Table.class);
                tableName = tableAnnotation.name();
            }
            if (tableName == null && aClass.isAnnotationPresent(Entity.class)) {
                tableName = aClass.getSimpleName().toLowerCase();
            }
            if(tableName == null) {
                throw new IllegalArgumentException(String.format("Class [%s] isn't a entity", aClass));
            }
            appendQuery(String.format(SELECT_FROM, tableName));
            return this;
        }

        @Override
        public QueryBuilder equal(String columnName, String value) {
            addParameter(new QueryParameter(QueryOperation.EQUAL, columnName, value));
            appendQuery(String.format(EQUAL, columnName, value));
            return this;
        }

        @Override
        public QueryBuilder like(String columnName, String value) {
            addParameter(new QueryParameter(QueryOperation.LIKE, columnName, value));
            appendQuery(String.format(LIKE, columnName, value));
            return this;
        }

        @Override
        public QueryBuilder and() {
            appendQuery(AND);
            return this;
        }

        @Override
        public QueryBuilder or() {
            appendQuery(OR);
            return this;
        }

        @Override
        public Query build() {
            removeWhere();
            return SqlQuery.this;
        }

        private void removeWhere() {
            if (queryParameters.isEmpty()) {
                query = new StringBuilder(query.substring(0, query.length() - WHERE.length()));
            }
        }

    }
}
