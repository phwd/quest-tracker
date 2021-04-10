package com.oculus.appsafety;

import android.content.pm.PackageInfo;
import java.util.function.Predicate;

/* renamed from: com.oculus.appsafety.-$$Lambda$MissingPackageInfoJobService$kZ_pt16_8JG6jo5xGUpD1gWS2js  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MissingPackageInfoJobService$kZ_pt16_8JG6jo5xGUpD1gWS2js implements Predicate {
    public static final /* synthetic */ $$Lambda$MissingPackageInfoJobService$kZ_pt16_8JG6jo5xGUpD1gWS2js INSTANCE = new $$Lambda$MissingPackageInfoJobService$kZ_pt16_8JG6jo5xGUpD1gWS2js();

    private /* synthetic */ $$Lambda$MissingPackageInfoJobService$kZ_pt16_8JG6jo5xGUpD1gWS2js() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return MissingPackageInfoJobService.lambda$onStartJob$1((PackageInfo) obj);
    }
}
