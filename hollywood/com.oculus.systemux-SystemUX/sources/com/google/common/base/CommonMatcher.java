package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class CommonMatcher {
    /* access modifiers changed from: package-private */
    public abstract int end();

    /* access modifiers changed from: package-private */
    public abstract boolean find();

    /* access modifiers changed from: package-private */
    public abstract boolean find(int i);

    /* access modifiers changed from: package-private */
    public abstract boolean matches();

    /* access modifiers changed from: package-private */
    public abstract String replaceAll(String str);

    /* access modifiers changed from: package-private */
    public abstract int start();

    CommonMatcher() {
    }
}
