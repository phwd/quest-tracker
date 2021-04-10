package com.oculus.appsafety;

import android.content.pm.PackageInfo;
import java.util.function.Predicate;

/* renamed from: com.oculus.appsafety.-$$Lambda$MissingPackageInfoJobService$cbLIOxjh9kxLtQmgYlmvfZnBS44  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MissingPackageInfoJobService$cbLIOxjh9kxLtQmgYlmvfZnBS44 implements Predicate {
    public static final /* synthetic */ $$Lambda$MissingPackageInfoJobService$cbLIOxjh9kxLtQmgYlmvfZnBS44 INSTANCE = new $$Lambda$MissingPackageInfoJobService$cbLIOxjh9kxLtQmgYlmvfZnBS44();

    private /* synthetic */ $$Lambda$MissingPackageInfoJobService$cbLIOxjh9kxLtQmgYlmvfZnBS44() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return MissingPackageInfoJobService.lambda$onStartJob$0((PackageInfo) obj);
    }
}
