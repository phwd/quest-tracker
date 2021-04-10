package com.oculus.appsafety;

import com.oculus.os.PackageMetadata;
import java.util.function.Function;

/* renamed from: com.oculus.appsafety.-$$Lambda$BinaryCheckJobService$z-0xGWwiJcw9XhBIezugApnAZ3g  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$BinaryCheckJobService$z0xGWwiJcw9XhBIezugApnAZ3g implements Function {
    public static final /* synthetic */ $$Lambda$BinaryCheckJobService$z0xGWwiJcw9XhBIezugApnAZ3g INSTANCE = new $$Lambda$BinaryCheckJobService$z0xGWwiJcw9XhBIezugApnAZ3g();

    private /* synthetic */ $$Lambda$BinaryCheckJobService$z0xGWwiJcw9XhBIezugApnAZ3g() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((PackageMetadata.Signature) obj).fingerprint;
    }
}
