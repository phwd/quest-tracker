package defpackage;

import android.view.View;
import android.widget.AdapterView;
import org.chromium.base.Callback;

/* renamed from: X61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class X61 implements AdapterView.OnItemClickListener {
    public final C1789b71 F;

    public X61(C1789b71 b71) {
        this.F = b71;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        C1789b71 b71 = this.F;
        Callback callback = b71.c;
        if (callback != null) {
            callback.onResult(Integer.valueOf((int) j));
        }
        b71.d.K.dismiss();
    }
}
