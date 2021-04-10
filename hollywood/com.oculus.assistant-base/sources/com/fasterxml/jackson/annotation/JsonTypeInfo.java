package com.fasterxml.jackson.annotation;

import X.NE;
import X.NF;
import X.NG;

public @interface JsonTypeInfo {
    Class defaultImpl() default NG.class;

    NE include() default NE.PROPERTY;

    String property() default "";

    NF use();

    boolean visible() default false;
}
