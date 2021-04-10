package defpackage;

import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import java.util.Iterator;

/* renamed from: Dl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0215Dl0 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ MaterialCalendarGridView F;
    public final /* synthetic */ C0337Fl0 G;

    public C0215Dl0(C0337Fl0 fl0, MaterialCalendarGridView materialCalendarGridView) {
        this.G = fl0;
        this.F = materialCalendarGridView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        C0032Al0 a2 = this.F.getAdapter();
        boolean z = true;
        if (i >= a2.a() && i <= a2.c()) {
            C0678Lc0 lc0 = this.G.K;
            long longValue = this.F.getAdapter().getItem(i).longValue();
            C1104Sc0 sc0 = lc0.f8428a;
            if (longValue < ((DateValidatorPointForward) sc0.B0.I).F) {
                z = false;
            }
            if (z) {
                sc0.A0.k0(longValue);
                Iterator it = lc0.f8428a.y0.iterator();
                while (it.hasNext()) {
                    ((AbstractC1021Qs0) it.next()).a(lc0.f8428a.A0.T());
                }
                lc0.f8428a.G0.T.F.b();
                RecyclerView recyclerView = lc0.f8428a.F0;
                if (recyclerView != null) {
                    recyclerView.T.F.b();
                }
            }
        }
    }
}
