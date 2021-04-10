package defpackage;

import android.widget.ListView;

/* renamed from: O80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O80 implements Runnable {
    public final /* synthetic */ Q80 F;

    public O80(Q80 q80) {
        this.F = q80;
    }

    public void run() {
        ListView listView = this.F.C0;
        listView.focusableViewAvailable(listView);
    }
}
