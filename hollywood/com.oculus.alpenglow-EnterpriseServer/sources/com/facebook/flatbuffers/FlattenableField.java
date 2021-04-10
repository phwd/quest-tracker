package com.facebook.flatbuffers;

import X.AnonymousClass0bh;
import X.C03150bi;
import com.facebook.flatbuffers.Flattenable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface FlattenableField {
    Class<? extends Flattener<?>> keyFlattener() default C03150bi.class;

    Class<? extends Flattenable.VirtualFlattenableResolver> keyTypeResolver() default AnonymousClass0bh.class;

    Class<? extends Flattener<?>> valueFlattener() default C03150bi.class;

    Class<? extends Flattenable.VirtualFlattenableResolver> valueTypeResolver() default AnonymousClass0bh.class;
}
