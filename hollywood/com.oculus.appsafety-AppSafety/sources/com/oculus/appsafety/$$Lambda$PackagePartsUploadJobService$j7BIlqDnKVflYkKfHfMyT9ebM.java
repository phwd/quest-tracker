package com.oculus.appsafety;

import com.oculus.appsafety.PackagePartsQueueManager;

/* renamed from: com.oculus.appsafety.-$$Lambda$PackagePartsUploadJobService$j7-BIlq-DnKVflYkKfHfMyT9ebM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$PackagePartsUploadJobService$j7BIlqDnKVflYkKfHfMyT9ebM implements PackagePartsQueueManager.PackagePartFactory {
    public static final /* synthetic */ $$Lambda$PackagePartsUploadJobService$j7BIlqDnKVflYkKfHfMyT9ebM INSTANCE = new $$Lambda$PackagePartsUploadJobService$j7BIlqDnKVflYkKfHfMyT9ebM();

    private /* synthetic */ $$Lambda$PackagePartsUploadJobService$j7BIlqDnKVflYkKfHfMyT9ebM() {
    }

    @Override // com.oculus.appsafety.PackagePartsQueueManager.PackagePartFactory
    public final PackagePart create(PackagePartConfig packagePartConfig) {
        return packagePartConfig.create();
    }
}
