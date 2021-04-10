package defpackage;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: j90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3160j90 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ AbstractC4186p90 F;

    public C3160j90(AbstractC4186p90 p90) {
        this.F = p90;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        C1823bJ bJVar;
        if (i != -1 && (bJVar = this.F.f11052J) != null) {
            bJVar.N = false;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }
}
