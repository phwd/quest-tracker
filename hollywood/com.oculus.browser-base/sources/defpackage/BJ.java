package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

/* renamed from: BJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BJ {
    public FJ F;

    public BJ(Context context, View view) {
        this.F = new EJ(context, view);
    }

    public void a() {
        ((EJ) this.F).L.K.dismiss();
    }

    public ListView b() {
        return ((EJ) this.F).O;
    }
}
