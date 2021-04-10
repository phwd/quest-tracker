package defpackage;

import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: oK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4047oK implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ Runnable F;
    public final /* synthetic */ C4389qK G;

    public C4047oK(C4389qK qKVar, Runnable runnable) {
        this.G = qKVar;
        this.F = runnable;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        String str;
        C4389qK qKVar = this.G;
        if (qKVar.M != i) {
            C4729sK sKVar = qKVar.G;
            CharSequence charSequence = (CharSequence) qKVar.N.getItem(i);
            Objects.requireNonNull(sKVar);
            if (charSequence == null) {
                str = null;
            } else {
                str = (String) sKVar.f.get(charSequence.toString());
            }
            C4389qK qKVar2 = this.G;
            C4729sK sKVar2 = qKVar2.G;
            CharSequence charSequence2 = sKVar2.t;
            if (charSequence2 != null && i == 0) {
                str = null;
            }
            qKVar2.M = i;
            Runnable runnable = this.F;
            if (!(str == null && charSequence2 == null)) {
                sKVar2.s = str;
                Callback callback = sKVar2.u;
                if (callback != null) {
                    callback.onResult(new Pair(str, runnable));
                }
            }
            C4389qK qKVar3 = this.G;
            qKVar3.G.n = null;
            qKVar3.c(false);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }
}
