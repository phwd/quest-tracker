package defpackage;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import org.chromium.base.Callback;

/* renamed from: OM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OM0 implements AdapterView.OnItemClickListener {
    public final SM0 F;
    public final Activity G;
    public final Callback H;

    public OM0(SM0 sm0, Activity activity, Callback callback) {
        this.F = sm0;
        this.G = activity;
        this.H = callback;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.F.a((int) j, this.G, this.H);
    }
}
