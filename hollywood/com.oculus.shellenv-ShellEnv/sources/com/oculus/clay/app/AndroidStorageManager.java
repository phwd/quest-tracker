package com.oculus.clay.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ObbInfo;
import android.content.res.ObbScanner;
import android.os.storage.OnObbStateChangeListener;
import android.os.storage.StorageManager;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class AndroidStorageManager {
    private final Context a;
    private final AssetManager b;

    class a extends OnObbStateChangeListener {
        private CompletableFuture a;

        private a() {
            this.a = new CompletableFuture();
        }

        /* synthetic */ a(AndroidStorageManager androidStorageManager, byte b2) {
            this();
        }

        public final int a() {
            String str;
            Throwable e;
            try {
                return ((Integer) this.a.get(10, TimeUnit.SECONDS)).intValue();
            } catch (TimeoutException e2) {
                e = e2;
                str = "AndroidStorageManager threw TimeoutException from ObbMountListener.get";
                Log.e("Clay", str, e);
                return 20;
            } catch (ExecutionException e3) {
                e = e3;
                str = "AndroidStorageManager threw ExecutionException from ObbMountListener.get";
                Log.e("Clay", str, e);
                return 20;
            } catch (InterruptedException e4) {
                e = e4;
                str = "AndroidStorageManager threw InterruptedException from ObbMountListener.get";
                Log.e("Clay", str, e);
                return 20;
            }
        }

        public final void onObbStateChange(String str, int i) {
            String.format("AndroidStorageManager onObbStateChange(path=%s, state=%d)", str, Integer.valueOf(i));
            AndroidStorageManager androidStorageManager = AndroidStorageManager.this;
            AndroidStorageManager.a(str.isEmpty(), "path.isEmpty()");
            super.onObbStateChange(str, i);
            this.a.complete(Integer.valueOf(i));
        }
    }

    public AndroidStorageManager(Context context) {
        this.a = context;
        this.b = context.getResources().getAssets();
    }

    private StorageManager a() {
        return (StorageManager) this.a.getSystemService("storage");
    }

    /* access modifiers changed from: private */
    public static void a(boolean z, String str) {
        if (z) {
            Log.wtf("Clay", "AndroidStorageManager " + str);
        }
    }

    public final String getMountedObbPath(String str) {
        String.format("AndroidStorageManager getMountedObbPath(path=%s)", str);
        a(str.isEmpty(), "path.isEmpty()");
        return a().getMountedObbPath(str);
    }

    public final ObbInfo getObbInfo(String str) {
        String.format("AndroidStorageManager getObbInfo(path=%s)", str);
        a(str.isEmpty(), "path.isEmpty()");
        try {
            return ObbScanner.getObbInfo(str);
        } catch (IOException e) {
            Log.e("Clay", "AndroidStorageManager threw IOException from ObbScanner.getObbInfo", e);
            return null;
        }
    }

    public final boolean isObbMounted(String str) {
        String.format("AndroidStorageManager isObbMounted(path=%s)", str);
        a(str.isEmpty(), "path.isEmpty()");
        return a().isObbMounted(str);
    }

    public final String[] listAssets(String str) {
        String.format("AndroidStorageManager listAssets(path=%s)", str);
        try {
            return this.b.list(str);
        } catch (IOException e) {
            Log.e("Clay", "AndroidStorageManager threw IOException from AssetManager.list", e);
            return null;
        }
    }

    public final int mountObb(String str, String str2) {
        String.format("AndroidStorageManager mountObb(path=%s, key=%s)", str, str2);
        a(str.isEmpty(), "path.isEmpty()");
        a aVar = new a(this, (byte) 0);
        if (!a().mountObb(str, str2, aVar)) {
            return 20;
        }
        return aVar.a();
    }

    public final int unmountObb(String str, boolean z) {
        String.format("AndroidStorageManager unmountObb(path=%s, force=%b)", str, Boolean.valueOf(z));
        a(str.isEmpty(), "path.isEmpty()");
        a aVar = new a(this, (byte) 0);
        if (!a().unmountObb(str, z, aVar)) {
            return 20;
        }
        return aVar.a();
    }
}
