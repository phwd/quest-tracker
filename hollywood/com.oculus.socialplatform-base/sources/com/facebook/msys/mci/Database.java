package com.facebook.msys.mci;

import X.AnonymousClass006;
import X.AnonymousClass1NU;
import X.AnonymousClass1NX;
import android.annotation.SuppressLint;
import android.os.Trace;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.DatabaseHealthMonitor;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class Database {
    public static boolean sConfigured;
    public DatabaseHealthMonitor mDatabaseHealthMonitor;
    @DoNotStrip
    public NativeHolder mNativeHolder;
    @DoNotStrip
    @Nullable
    public volatile DatabaseConnection mReadOnlyConnection;
    @DoNotStrip
    @Nullable
    public volatile DatabaseConnection mReadWriteConnection;

    @DoNotStrip
    public static class InitializedCallback {
        @DoNotStrip
        public void onInit(SqliteHolder sqliteHolder) {
        }
    }

    @DoNotStrip
    public interface SchemaDeployer {
        @DoNotStrip
        int upgrade(SqliteHolder sqliteHolder);
    }

    public Database(long j, String str, NotificationCenter notificationCenter, SchemaDeployer schemaDeployer, @Nullable SchemaDeployer schemaDeployer2, @Nullable SchemaDeployer schemaDeployer3, @Nullable DatabaseHealthMonitor.FatalErrorCallback fatalErrorCallback) {
        if (str == null) {
            throw null;
        } else if (notificationCenter == null) {
            throw null;
        } else if (schemaDeployer != null) {
            this.mDatabaseHealthMonitor = new DatabaseHealthMonitor(str, AnonymousClass006.A07(str, "_health.dat"), null);
            Trace.beginSection("Database.init");
            try {
                synchronized (Database.class) {
                    if (!sConfigured) {
                        throw new RuntimeException("Database.config() has to be called before creating any instance of this class");
                    }
                }
                this.mNativeHolder = initNativeHolder(j, str, notificationCenter, schemaDeployer, schemaDeployer2, schemaDeployer3);
            } finally {
                Trace.endSection();
            }
        } else {
            throw null;
        }
    }

    @DoNotStrip
    public static native void configNative(String str);

    @DoNotStrip
    public static native void enableInteractiveReadOnlyConnection(boolean z);

    @DoNotStrip
    public static native void enableReadOnlyConnection(boolean z);

    @DoNotStrip
    public static native void enableSqliteErrorLogs();

    @DoNotStrip
    public static native void enableSqliteOndemandLoading();

    @DoNotStrip
    public static native NativeHolder initNativeHolder(long j, String str, NotificationCenter notificationCenter, SchemaDeployer schemaDeployer, @Nullable SchemaDeployer schemaDeployer2, @Nullable SchemaDeployer schemaDeployer3);

    @DoNotStrip
    private native void openNative(OpenCallback openCallback, @Nullable InitializedCallback initializedCallback);

    @DoNotStrip
    public native void delete(Runnable runnable);

    @DoNotStrip
    public native long getFacebookUserId();

    @DoNotStrip
    public native String getFile();

    static {
        AnonymousClass1NX.A00();
        Execution.initialize();
    }

    public void open(OpenCallback openCallback, @Nullable InitializedCallback initializedCallback) {
        if (Execution.getExecutionContext() == 2) {
            this.mDatabaseHealthMonitor.fixAll();
        } else {
            Execution.executeAsyncWithPriority(new AnonymousClass1NU(this), 2, 1000);
        }
        openNative(openCallback, initializedCallback);
    }

    @DoNotStrip
    public static class OpenCallback {
        @DoNotStrip
        public void onConfig(SqliteHolder sqliteHolder, int i, DatabaseConnectionSettings databaseConnectionSettings) {
        }

        @DoNotStrip
        public void onOpen(boolean z, @Nullable Throwable th) {
        }

        @DoNotStrip
        public boolean onConfig(SqliteHolder sqliteHolder, int i, boolean z, long j, DatabaseConnectionSettings databaseConnectionSettings) {
            return true;
        }
    }
}
