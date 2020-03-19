package by.sivko.resume.builder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryParameter {
    private final QueryOperation operation;
    private final String column;
    private final String value;
}
