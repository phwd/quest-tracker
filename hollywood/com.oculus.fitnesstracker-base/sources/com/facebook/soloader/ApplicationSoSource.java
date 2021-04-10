package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public final class ApplicationSoSource extends SoSource {
    private Context applicationContext;
    private int flags;
    private DirectorySoSource soSource;

    public ApplicationSoSource(Context context, int i) {
        this.applicationContext = context.getApplicationContext();
        if (this.applicationContext == null) {
            Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.");
            this.applicationContext = context;
        }
        this.flags = i;
        this.soSource = new DirectorySoSource(new File(this.applicationContext.getApplicationInfo().nativeLibraryDir), i);
    }

    public final boolean checkAndMaybeUpdate() throws IOException {
        File file = this.soSource.soDirectory;
        Context updatedContext = getUpdatedContext();
        File nativeLibDirFromContext = getNativeLibDirFromContext(updatedContext);
        if (file.equals(nativeLibDirFromContext)) {
            return false;
        }
        Log.d("SoLoader", "Native library directory updated from " + file + " to " + nativeLibDirFromContext);
        this.flags = this.flags | 1;
        this.soSource = new DirectorySoSource(nativeLibDirFromContext, this.flags);
        this.soSource.prepare(this.flags);
        this.applicationContext = updatedContext;
        return true;
    }

    public final Context getUpdatedContext() {
        try {
            return this.applicationContext.createPackageContext(this.applicationContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getNativeLibDirFromContext(Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir);
    }

    @Override // com.facebook.soloader.SoSource
    public final int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return this.soSource.loadLibrary(str, i, threadPolicy);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.SoSource
    public final void prepare(int i) throws IOException {
        this.soSource.prepare(i);
    }

    @Override // com.facebook.soloader.SoSource
    public final String toString() {
        return this.soSource.toString();
    }
}
