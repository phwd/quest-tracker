package org.chromium.content.browser;

import android.app.Activity;
import android.util.SparseArray;
import org.chromium.base.Callback;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NfcHost extends Vy1 {
    public static final SparseArray F = new SparseArray();
    public final WebContents G;
    public final int H;
    public Callback I;

    public NfcHost(WebContents webContents, int i) {
        this.G = webContents;
        this.H = i;
        F.put(i, this);
    }

    public static void create(WebContents webContents, int i) {
        new NfcHost(webContents, i);
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void x(WindowAndroid windowAndroid) {
        this.I.onResult(windowAndroid != null ? (Activity) windowAndroid.s0().get() : null);
    }
}
