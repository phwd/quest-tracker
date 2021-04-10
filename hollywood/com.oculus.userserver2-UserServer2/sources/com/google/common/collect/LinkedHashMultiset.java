package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class LinkedHashMultiset<E> extends AbstractMapBasedMultiset<E> {
}
