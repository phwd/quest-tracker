package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;
import java.io.File;

public final class b extends k {
    private Context a;
    private int b;
    private c c;

    public b(Context context, int i) {
        this.a = context.getApplicationContext();
        if (this.a == null) {
            Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.");
            this.a = context;
        }
        this.b = i;
        this.c = new c(new File(this.a.getApplicationInfo().nativeLibraryDir), i);
    }

    public static File a(Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir);
    }

    @Override // com.facebook.soloader.k
    public final int a(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        return this.c.a(str, i, threadPolicy);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.k
    public final void a(int i) {
        this.c.a(i);
    }

    public final boolean a() {
        File file = this.c.a;
        Context b2 = b();
        File a2 = a(b2);
        if (file.equals(a2)) {
            return false;
        }
        StringBuilder sb = new StringBuilder("Native library directory updated from ");
        sb.append(file);
        sb.append(" to ");
        sb.append(a2);
        this.b |= 1;
        this.c = new c(a2, this.b);
        this.c.a(this.b);
        this.a = b2;
        return true;
    }

    public final Context b() {
        try {
            return this.a.createPackageContext(this.a.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.facebook.soloader.k
    public final String toString() {
        return this.c.toString();
    }
}
