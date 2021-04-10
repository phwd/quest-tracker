package com.facebook.flatbuffers;

import X.C03530nG;
import X.C03540nI;
import com.facebook.flatbuffers.Flattenable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface FlattenableField {
    Class<? extends Flattener<?>> keyFlattener() default C03540nI.class;

    Class<? extends Flattenable.VirtualFlattenableResolver> keyTypeResolver() default C03530nG.class;

    Class<? extends Flattener<?>> valueFlattener() default C03540nI.class;

    Class<? extends Flattenable.VirtualFlattenableResolver> valueTypeResolver() default C03530nG.class;
}
