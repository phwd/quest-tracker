package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.lang.Comparable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public abstract class RangeGwtSerializationDependencies<C extends Comparable> implements Serializable {
    RangeGwtSerializationDependencies() {
    }
}
