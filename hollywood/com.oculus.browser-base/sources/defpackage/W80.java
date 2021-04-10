package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/* renamed from: W80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class W80 implements View.OnClickListener {
    public final X80 F;
    public final int G;
    public final ViewGroup H;

    public W80(X80 x80, int i, ViewGroup viewGroup) {
        this.F = x80;
        this.G = i;
        this.H = viewGroup;
    }

    public void onClick(View view) {
        X80 x80 = this.F;
        int i = this.G;
        ((ListView) this.H).performItemClick(view, i, (long) ((C4765sb0) x80.F.G.get(i)).b.f(Y80.f));
    }
}
