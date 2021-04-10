package com.facebook.graphservice.interfaces;

public interface Tree {

    public enum DeepEqualGuess {
        TRUE,
        FALSE,
        UNKNOWN
    }

    public enum FieldType {
        NONE,
        BOOL,
        DOUBLE,
        INT,
        TIME,
        STRING,
        DYNAMIC,
        BOOL_LIST,
        DOUBLE_LIST,
        INT_LIST,
        TIME_LIST,
        STRING_LIST,
        TREE,
        TREE_LIST
    }
}
