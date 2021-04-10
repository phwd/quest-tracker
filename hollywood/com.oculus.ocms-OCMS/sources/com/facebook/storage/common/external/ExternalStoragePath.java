package com.facebook.storage.common.external;

import android.annotation.TargetApi;
import android.os.Environment;
import androidx.annotation.Nullable;
import java.io.File;

public class ExternalStoragePath {
    @Nullable
    private static Runnable mBadUseCallback;

    public @interface Reason {
        public static final int PATH_VALIDATION = 3;
        public static final int SAMPLE_APP = 5;
        public static final int STATISTICS = 0;
        public static final int STORAGE__CUSTOM_DEVICE = 6;
        public static final int STORAGE__SELF_VERIFIED = 7;
        public static final int STORAGE__UNSAFE = 1;
        public static final int STORAGE__WHITELISTED = 4;
        public static final int TEST_FRAMEWORK = 2;
    }

    public static File getDirectory(@Reason int i) {
        maybeReportBadUse(i);
        return Environment.getExternalStorageDirectory();
    }

    @TargetApi(8)
    public static File getPublicDirectory(@Nullable String str, @Reason int i) {
        if (str == null) {
            return getDirectory(i);
        }
        maybeReportBadUse(i);
        return Environment.getExternalStoragePublicDirectory(str);
    }

    public static synchronized void setBadUseCallbackDebugOnly(@Nullable Runnable runnable) {
        synchronized (ExternalStoragePath.class) {
            mBadUseCallback = runnable;
        }
    }

    private static synchronized void maybeReportBadUse(@Reason int i) {
        synchronized (ExternalStoragePath.class) {
            if (i == 1) {
                if (mBadUseCallback != null) {
                    mBadUseCallback.run();
                }
            }
        }
    }
}
