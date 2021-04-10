package com.fasterxml.jackson.databind;

import java.io.Serializable;

public abstract class PropertyNamingStrategy implements Serializable {
    public static final PropertyNamingStrategy CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES = new LowerCaseWithUnderscoresStrategy();
    public static final PropertyNamingStrategy PASCAL_CASE_TO_CAMEL_CASE = new PascalCaseStrategy();

    public static class LowerCaseWithUnderscoresStrategy extends PropertyNamingStrategyBase {
    }

    public static class PascalCaseStrategy extends PropertyNamingStrategyBase {
    }

    public static abstract class PropertyNamingStrategyBase extends PropertyNamingStrategy {
    }
}
