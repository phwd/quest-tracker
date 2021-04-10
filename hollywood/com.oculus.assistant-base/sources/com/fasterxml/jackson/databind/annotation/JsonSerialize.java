package com.fasterxml.jackson.databind.annotation;

import X.AbstractC1072ro;
import X.OL;
import X.OM;
import X.OR;
import com.fasterxml.jackson.databind.JsonSerializer;

public @interface JsonSerialize {
    Class as() default OR.class;

    Class contentAs() default OR.class;

    Class contentConverter() default AbstractC1072ro.class;

    Class contentUsing() default JsonSerializer.None.class;

    Class converter() default AbstractC1072ro.class;

    OL include() default OL.ALWAYS;

    Class keyAs() default OR.class;

    Class keyUsing() default JsonSerializer.None.class;

    OM typing() default OM.DYNAMIC;

    Class using() default JsonSerializer.None.class;
}
