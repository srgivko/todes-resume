package by.sivko.resume.builder;

import java.util.List;

public interface Query {
    String getQuery();

    List<QueryParameter> getQueryParameters();
}
