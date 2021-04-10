package defpackage;

import android.view.View;
import java.util.ArrayList;

/* renamed from: xT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5597xT implements AbstractC2753gn1 {
    public final /* synthetic */ View F;
    public final /* synthetic */ ArrayList G;

    public C5597xT(CT ct, View view, ArrayList arrayList) {
        this.F = view;
        this.G = arrayList;
    }

    @Override // defpackage.AbstractC2753gn1
    public void a(AbstractC2924hn1 hn1) {
        hn1.w(this);
        hn1.a(this);
    }

    @Override // defpackage.AbstractC2753gn1
    public void b(AbstractC2924hn1 hn1) {
    }

    @Override // defpackage.AbstractC2753gn1
    public void c(AbstractC2924hn1 hn1) {
        hn1.w(this);
        this.F.setVisibility(8);
        int size = this.G.size();
        for (int i = 0; i < size; i++) {
            ((View) this.G.get(i)).setVisibility(0);
        }
    }

    @Override // defpackage.AbstractC2753gn1
    public void d(AbstractC2924hn1 hn1) {
    }

    @Override // defpackage.AbstractC2753gn1
    public void e(AbstractC2924hn1 hn1) {
    }
}
