package defpackage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/* renamed from: P80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P80 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ Q80 F;

    public P80(Q80 q80) {
        this.F = q80;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ListView listView = (ListView) adapterView;
        this.F.f1();
    }
}
