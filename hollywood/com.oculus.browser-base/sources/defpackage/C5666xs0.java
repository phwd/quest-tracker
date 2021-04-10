package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: xs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5666xs0 extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5836ys0 f11644a;

    public C5666xs0(C5836ys0 ys0, C4816ss0 ss0) {
        this.f11644a = ys0;
    }

    @Override // defpackage.MK0
    public void a(RecyclerView recyclerView, int i) {
        AbstractC5496ws0 ws0;
        if (i == 1 && (ws0 = this.f11644a.r1) != null) {
            C2379ed edVar = (C2379ed) ws0;
            if (edVar.R && edVar.g0.k) {
                ((C3909na0) edVar.G).I.i(false, false);
            }
        }
    }

    @Override // defpackage.MK0
    public void b(RecyclerView recyclerView, int i, int i2) {
    }
}
