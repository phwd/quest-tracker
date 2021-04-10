package defpackage;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: T21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T21 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ X21 F;

    public T21(X21 x21) {
        this.F = x21;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.F.u.dismiss();
        if (i == 0) {
            this.F.d.g(false, false);
        }
    }
}
