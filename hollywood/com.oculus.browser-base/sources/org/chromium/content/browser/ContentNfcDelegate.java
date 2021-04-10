package org.chromium.content.browser;

import android.app.Activity;
import android.util.SparseArray;
import org.chromium.base.Callback;
import org.chromium.device.nfc.NfcDelegate;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContentNfcDelegate implements NfcDelegate {
    public static ContentNfcDelegate create() {
        return new ContentNfcDelegate();
    }

    @Override // org.chromium.device.nfc.NfcDelegate
    public void a(int i) {
        SparseArray sparseArray = NfcHost.F;
        NfcHost nfcHost = (NfcHost) sparseArray.get(i);
        nfcHost.I = null;
        Zy1 t0 = Zy1.t0(nfcHost.G);
        if (t0 != null) {
            t0.F.c(nfcHost);
        }
        sparseArray.remove(nfcHost.H);
    }

    @Override // org.chromium.device.nfc.NfcDelegate
    public void b(int i, Callback callback) {
        NfcHost nfcHost = (NfcHost) NfcHost.F.get(i);
        nfcHost.I = callback;
        Zy1 t0 = Zy1.t0(nfcHost.G);
        if (t0 != null) {
            t0.F.b(nfcHost);
        }
        WindowAndroid I = nfcHost.G.I();
        nfcHost.I.onResult(I != null ? (Activity) I.s0().get() : null);
    }
}
