package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.os.Build;
import com.facebook.ultralight.UL;

public abstract class SQLiteDatabaseCompat {
    private static final SQLiteDatabaseCompat sInstance;

    public @interface SQLiteOpenOptions {
    }

    static {
        if (Build.VERSION.SDK_INT >= 16) {
            sInstance = new JellyBeanAndBeyondImpl();
        } else if (Build.VERSION.SDK_INT >= 11) {
            sInstance = new HoneycombImpl();
        } else {
            sInstance = new NoopImpl();
        }
    }

    @TargetApi(UL.id._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID)
    private static class JellyBeanAndBeyondImpl extends SQLiteDatabaseCompat {
        private JellyBeanAndBeyondImpl() {
        }
    }

    @TargetApi(11)
    private static class HoneycombImpl extends SQLiteDatabaseCompat {
        private HoneycombImpl() {
        }
    }

    private static class NoopImpl extends SQLiteDatabaseCompat {
        private NoopImpl() {
        }
    }
}
