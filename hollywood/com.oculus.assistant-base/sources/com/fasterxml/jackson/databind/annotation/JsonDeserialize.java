package com.fasterxml.jackson.databind.annotation;

import X.AbstractC1026qx;
import X.AbstractC1072ro;
import X.OR;
import com.fasterxml.jackson.databind.JsonDeserializer;

public @interface JsonDeserialize {
    Class as() default OR.class;

    Class builder() default OR.class;

    Class contentAs() default OR.class;

    Class contentConverter() default AbstractC1072ro.class;

    Class contentUsing() default JsonDeserializer.None.class;

    Class converter() default AbstractC1072ro.class;

    Class keyAs() default OR.class;

    Class keyUsing() default AbstractC1026qx.class;

    Class using() default JsonDeserializer.None.class;
}
