package com.oculus.license;

import java.util.function.ToIntFunction;

/* renamed from: com.oculus.license.-$$Lambda$-Wx55pojjtjGl8w_U8K2xUCbfbY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$Wx55pojjtjGl8w_U8K2xUCbfbY implements ToIntFunction {
    public static final /* synthetic */ $$Lambda$Wx55pojjtjGl8w_U8K2xUCbfbY INSTANCE = new $$Lambda$Wx55pojjtjGl8w_U8K2xUCbfbY();

    private /* synthetic */ $$Lambda$Wx55pojjtjGl8w_U8K2xUCbfbY() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((Rule) obj).size();
    }
}
