package com.fasterxml.jackson.annotation;

import X.N3;

public @interface JsonInclude {
    N3 value() default N3.ALWAYS;
}
