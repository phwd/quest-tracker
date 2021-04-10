package com.oculus.license;

import java.util.function.ToIntFunction;

/* renamed from: com.oculus.license.-$$Lambda$8mOKOqXzzDAx6qzRBsxkofEK82A  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$8mOKOqXzzDAx6qzRBsxkofEK82A implements ToIntFunction {
    public static final /* synthetic */ $$Lambda$8mOKOqXzzDAx6qzRBsxkofEK82A INSTANCE = new $$Lambda$8mOKOqXzzDAx6qzRBsxkofEK82A();

    private /* synthetic */ $$Lambda$8mOKOqXzzDAx6qzRBsxkofEK82A() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((FilterConfig) obj).size();
    }
}
