package com.facebook.crudolib.prefs;

import android.content.Context;
import android.os.Looper;
import android.os.StrictMode;
import com.facebook.crudolib.processname.ProcessNameHelper;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.NullsafeStrict;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@NullsafeStrict
@ThreadSafe
public class LightSharedPreferencesFactory {
    private static final String LIGHT_PREFS_DIR = "light_prefs";
    private static final String TAG = "LightSharedPreferencesFactory";
    private static final String UNKNOWN_PROCESS_DIR = "default";
    private final int mCommitOnMainThreadPolicy;
    private final Executor mExecutor;
    private final Map<String, LightSharedPreferences> mInstances = new HashMap();
    private final File mPrivateDir;

    protected LightSharedPreferencesFactory(Executor executor, File file, int i) {
        this.mExecutor = executor;
        this.mPrivateDir = file;
        this.mCommitOnMainThreadPolicy = i;
    }

    /* access modifiers changed from: private */
    public static Executor resolveExecutor(@Nullable Executor executor) {
        return executor != null ? executor : Executors.newSingleThreadExecutor();
    }

    static File createProcessPrivateDir(Context context) {
        StrictMode.ThreadPolicy allowThreadDiskReads = Looper.myLooper() == Looper.getMainLooper() ? StrictMode.allowThreadDiskReads() : null;
        try {
            String myFullProcessName = ProcessNameHelper.getMyFullProcessName();
            if (myFullProcessName == null) {
                myFullProcessName = "default";
            }
            File file = new File(context.getDir(LIGHT_PREFS_DIR, 0), myFullProcessName);
            file.mkdirs();
            return file;
        } finally {
            if (allowThreadDiskReads != null) {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        }
    }

    private void maybeCreateBaseDir(File file) {
        StrictMode.ThreadPolicy allowThreadDiskReads = Looper.myLooper() == Looper.getMainLooper() ? StrictMode.allowThreadDiskReads() : null;
        try {
            File file2 = (File) Assertions.assertNotNull(file.getParentFile(), "expecting a file which is always under some dir");
            if (file2.exists() && !file2.isDirectory()) {
                BLog.w(TAG, "cannot create directory %s, a file already exists with that name", file2.getAbsolutePath());
            }
            if (!file2.exists()) {
                file2.mkdirs();
            }
        } finally {
            if (allowThreadDiskReads != null) {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        }
    }

    public synchronized LightSharedPreferences getSharedPreferences(String str) {
        LightSharedPreferences lightSharedPreferences;
        lightSharedPreferences = this.mInstances.get(str);
        if (lightSharedPreferences == null) {
            File sharedPrefsFile = getSharedPrefsFile(str);
            maybeCreateBaseDir(sharedPrefsFile);
            LightSharedPreferencesImpl lightSharedPreferencesImpl = new LightSharedPreferencesImpl(sharedPrefsFile, this.mExecutor, this.mCommitOnMainThreadPolicy);
            this.mInstances.put(str, lightSharedPreferencesImpl);
            lightSharedPreferences = lightSharedPreferencesImpl;
        }
        return lightSharedPreferences;
    }

    public void setSharedPrefsWriteMode(int i) {
        LightSharedPreferencesStorage.setSharedPrefsWriteMode(i);
    }

    /* access modifiers changed from: package-private */
    public File getPrivateDir() {
        return this.mPrivateDir;
    }

    public static class Builder {
        int mCommitOnMainThreadPolicy = 0;
        final Context mContext;
        @Nullable
        Executor mExecutor;
        @Nullable
        File mOverridePrivateDir;

        public Builder(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public Builder executor(Executor executor) {
            this.mExecutor = executor;
            return this;
        }

        @Deprecated
        public Builder experimentalPrivateDir(File file) {
            this.mOverridePrivateDir = file;
            return this;
        }

        public Builder commitOnMainThreadPolicy(int i) {
            this.mCommitOnMainThreadPolicy = i;
            return this;
        }

        public LightSharedPreferencesFactory build() {
            Executor resolveExecutor = LightSharedPreferencesFactory.resolveExecutor(this.mExecutor);
            File file = this.mOverridePrivateDir;
            if (file == null) {
                file = LightSharedPreferencesFactory.createProcessPrivateDir(this.mContext);
            }
            return new LightSharedPreferencesFactory(resolveExecutor, file, this.mCommitOnMainThreadPolicy);
        }
    }

    /* access modifiers changed from: package-private */
    public File getSharedPrefsFile(String str) {
        return new File(this.mPrivateDir, str);
    }
}
