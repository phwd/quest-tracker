package com.fasterxml.jackson.annotation;

import X.EnumC0243Mu;

public @interface JsonFormat {
    String locale() default "##default";

    String pattern() default "";

    EnumC0243Mu shape() default EnumC0243Mu.ANY;

    String timezone() default "##default";
}
