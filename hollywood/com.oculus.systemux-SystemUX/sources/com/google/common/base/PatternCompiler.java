package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;

/* access modifiers changed from: package-private */
@GwtIncompatible
public interface PatternCompiler {
    CommonPattern compile(String str);
}
