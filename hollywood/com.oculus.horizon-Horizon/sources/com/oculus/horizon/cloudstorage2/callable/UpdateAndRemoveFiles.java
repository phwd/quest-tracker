package com.oculus.horizon.cloudstorage2.callable;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.library.model.App;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID"})
public class UpdateAndRemoveFiles extends SyncWork<Void> {
    public final App mApp;
    @Inject
    @Eager
    public final CloudStorageManager mCloudStorageManager;
    public final Map<String, String> mMoveFiles;
    public final String[] mRemoveFiles;

    /* renamed from: A00 */
    public final Void call() throws IOException {
        String formatStrLocaleSafe;
        String A02 = this.mCloudStorageManager.A02(this.mApp);
        File file = new File(A02);
        if (file.exists() || file.mkdirs()) {
            Object[] objArr = new Object[2];
            int i = 0;
            objArr[0] = Integer.valueOf(this.mMoveFiles.size());
            String[] strArr = this.mRemoveFiles;
            if (strArr != null) {
                i = strArr.length;
            }
            objArr[1] = Integer.valueOf(i);
            for (Map.Entry<String, String> entry : this.mMoveFiles.entrySet()) {
                File file2 = new File(entry.getKey());
                File file3 = new File(A02, entry.getValue());
                File parentFile = file3.getParentFile();
                Object[] objArr2 = {file3, file2};
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("Failed to create destination directory for file %s", file3);
                } else if (!file2.renameTo(file3)) {
                    formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("Failed to move downloaded file from %s to %s", file2, file3);
                }
            }
            String[] strArr2 = this.mRemoveFiles;
            if (strArr2 == null) {
                return null;
            }
            for (String str : strArr2) {
                File file4 = new File(A02, str);
                new Object[1][0] = file4;
                file4.delete();
            }
            return null;
        }
        formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("Failed to create root sync directory [%s]", A02);
        throw new IOException(formatStrLocaleSafe);
    }

    @Inject
    public UpdateAndRemoveFiles(AbstractC06640p5 r2, @Assisted App app, @Assisted Map<String, String> map, @Assisted String[] strArr, @Assisted Reporter reporter) {
        super(reporter);
        this.mCloudStorageManager = (CloudStorageManager) AnonymousClass117.A00(73, r2);
        this.mApp = app;
        this.mMoveFiles = map;
        this.mRemoveFiles = strArr;
    }
}
