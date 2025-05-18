package org.example.queries;

import java.io.Serializable;

public record QueryWrapper(QueryType queryType) implements Serializable {

    public QueryType getQueryType() {
        return this.queryType;
    }
}