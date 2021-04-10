package com.oculus.clay.app;

import android.app.ActivityManager;
import android.app.NativeActivity;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import com.facebook.soloader.SysUtil;
import com.facebook.soloader.i;
import java.io.IOException;

public class ClayAppActivity extends NativeActivity {
    private static a a = new a("Clay", "ClayAppActivity");
    private AndroidStorageManager b;
    private ContentUriProvider c;
    private boolean d;

    protected static void a() {
        Log.e("Clay", "Shutting down process via System.exit", new Exception());
        System.exit(0);
    }

    protected static void a(Boolean bool, String str) {
        a.a(bool);
        SysUtil.a(bool.booleanValue(), str);
    }

    public final boolean b() {
        return this.d;
    }

    public AndroidStorageManager getAndroidStorageManager() {
        return this.b;
    }

    public ContentUriProvider getContentUriProvider() {
        return this.c;
    }

    public ActivityManager.MemoryInfo getMemoryInfo() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) getApplicationContext().getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        a.a("onCreate");
        SysUtil.c().d();
        try {
            a.a("SoLoader");
            try {
                i.a(this, 2);
            } catch (IOException e) {
                Log.e("Clay", "ClayAppActivity threw IOException during SoLoader.init", e);
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
            this.b = new AndroidStorageManager(getApplicationContext());
            this.c = new ContentUriProvider(getApplicationContext());
            super.onCreate(bundle);
            Trace.endSection();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        a.a("onDestroy");
        SysUtil.c().k();
        try {
            this.b = null;
            super.onDestroy();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        a.a("onPause");
        SysUtil.c().j();
        super.onPause();
        this.d = false;
        Trace.endSection();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        a.a("onResume");
        super.onResume();
        SysUtil.c().e();
        this.d = true;
        Trace.endSection();
    }
}
