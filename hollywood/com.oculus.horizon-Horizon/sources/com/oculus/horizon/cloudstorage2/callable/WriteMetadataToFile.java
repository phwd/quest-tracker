package com.oculus.horizon.cloudstorage2.callable;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata;
import com.oculus.library.model.App;
import java.io.File;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID"})
public class WriteMetadataToFile extends SyncWork<File> {
    public static final Class<?> TAG = WriteMetadataToFile.class;
    public AnonymousClass0QC _UL_mInjectionContext;
    public final App mApp;
    public final CloudStorage2Metadata mMetadata;

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c A[SYNTHETIC, Splitter:B:18:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b A[SYNTHETIC, Splitter:B:27:0x005b] */
    /* renamed from: A00 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.File call() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cloudstorage2.callable.WriteMetadataToFile.call():java.io.File");
    }

    @Inject
    public WriteMetadataToFile(AbstractC06640p5 r3, @Assisted App app, @Assisted CloudStorage2Metadata cloudStorage2Metadata, @Assisted Reporter reporter) {
        super(reporter);
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mApp = app;
        this.mMetadata = cloudStorage2Metadata;
    }
}
