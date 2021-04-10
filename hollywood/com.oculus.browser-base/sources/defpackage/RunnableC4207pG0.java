package defpackage;

import android.content.Context;
import java.io.File;

/* renamed from: pG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4207pG0 implements Runnable {
    public final Context F;

    public RunnableC4207pG0(Context context) {
        this.F = context;
    }

    public void run() {
        File codeCacheDir = this.F.getCodeCacheDir();
        if (codeCacheDir != null) {
            File file = new File(codeCacheDir, "com.android.opengl.shaders_cache");
            if (file.exists()) {
                long length = file.length() / 1024;
                if (length < 1) {
                    length = 1;
                }
                if (length >= 2560) {
                    length = 2559;
                }
                AbstractC3364kK0.e("Memory.Experimental.Browser.EGLShaderCacheSize.Android", (int) length, 1, 2560, 50);
            }
        }
    }
}
