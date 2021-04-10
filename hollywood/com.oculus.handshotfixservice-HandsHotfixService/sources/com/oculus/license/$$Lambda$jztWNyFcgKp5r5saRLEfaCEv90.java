package com.oculus.license;

import com.oculus.license.Signer;
import java.util.function.ToIntFunction;

/* renamed from: com.oculus.license.-$$Lambda$jz-tWNyFcgKp5r5saRLEfaCEv90  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$jztWNyFcgKp5r5saRLEfaCEv90 implements ToIntFunction {
    public static final /* synthetic */ $$Lambda$jztWNyFcgKp5r5saRLEfaCEv90 INSTANCE = new $$Lambda$jztWNyFcgKp5r5saRLEfaCEv90();

    private /* synthetic */ $$Lambda$jztWNyFcgKp5r5saRLEfaCEv90() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((Signer.Digest) obj).size();
    }
}
