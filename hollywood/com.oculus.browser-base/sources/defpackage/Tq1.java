package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;

/* renamed from: Tq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Tq1 implements Iq1, Jq1, TextWatcher {
    public final UH0 F;
    public Callback G;
    public boolean H;
    public Pq1 I;

    /* renamed from: J  reason: collision with root package name */
    public int f8989J = 0;
    public int K = 0;
    public final List L = new ArrayList();
    public final List M = new ArrayList();

    public Tq1(UH0 uh0, Callback callback) {
        this.F = uh0;
        this.G = callback;
        uh0.m(Wq1.e, new Rq1(this));
        uh0.j(Wq1.f, false);
        uh0.m(Wq1.g, this);
        uh0.m(Wq1.j, this);
        uh0.m(Wq1.k, this);
        d(true);
    }

    public static String a(String str, String str2) {
        int indexOf;
        int indexOf2 = str.indexOf(str2);
        if (indexOf2 != -1 && (indexOf = str.indexOf(47, indexOf2)) > 0) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    public void afterTextChanged(Editable editable) {
        for (int i = 0; i < this.M.size(); i++) {
            ((TextWatcher) this.M.get(i)).afterTextChanged(editable);
        }
    }

    @Override // defpackage.Jq1
    public void b(String str, String str2) {
        for (int i = 0; i < this.L.size(); i++) {
            ((Jq1) this.L.get(i)).b(str, str2);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        for (int i4 = 0; i4 < this.M.size(); i4++) {
            ((TextWatcher) this.M.get(i4)).beforeTextChanged(charSequence, i, i2, i3);
        }
    }

    public final void c() {
        CharSequence charSequence;
        String str;
        boolean z = this.H;
        Pq1 pq1 = this.I;
        if (!z) {
            charSequence = pq1.e;
        } else {
            String str2 = pq1.f;
            charSequence = str2 != null ? str2 : pq1.e;
        }
        String str3 = (z || TextUtils.isEmpty(charSequence) || (str = this.I.d) == null) ? charSequence : str;
        int i = this.H ? 0 : this.f8989J;
        if (charSequence == null) {
            charSequence = "";
        }
        this.F.m(Wq1.h, new Vq1(charSequence, str3, i, this.I.h, this.K));
    }

    public boolean d(boolean z) {
        UH0 uh0 = this.F;
        QH0 qh0 = Wq1.l;
        boolean h = uh0.h(qh0);
        this.F.j(qh0, z);
        return h != z;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        for (int i4 = 0; i4 < this.M.size(); i4++) {
            ((TextWatcher) this.M.get(i4)).onTextChanged(charSequence, i, i2, i3);
        }
    }
}
