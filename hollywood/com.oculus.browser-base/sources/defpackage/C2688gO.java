package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: gO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2688gO extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3200jO f9993a;

    public C2688gO(C3200jO jOVar) {
        this.f9993a = jOVar;
    }

    @Override // defpackage.MK0
    public void b(RecyclerView recyclerView, int i, int i2) {
        C3200jO jOVar = this.f9993a;
        int computeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int computeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        int computeVerticalScrollRange = jOVar.u.computeVerticalScrollRange();
        int i3 = jOVar.t;
        jOVar.v = computeVerticalScrollRange - i3 > 0 && i3 >= jOVar.c;
        int computeHorizontalScrollRange = jOVar.u.computeHorizontalScrollRange();
        int i4 = jOVar.s;
        boolean z = computeHorizontalScrollRange - i4 > 0 && i4 >= jOVar.c;
        jOVar.w = z;
        boolean z2 = jOVar.v;
        if (z2 || z) {
            if (z2) {
                float f = (float) i3;
                jOVar.n = (int) ((((f / 2.0f) + ((float) computeVerticalScrollOffset)) * f) / ((float) computeVerticalScrollRange));
                jOVar.m = Math.min(i3, (i3 * i3) / computeVerticalScrollRange);
            }
            if (jOVar.w) {
                float f2 = (float) computeHorizontalScrollOffset;
                float f3 = (float) i4;
                jOVar.q = (int) ((((f3 / 2.0f) + f2) * f3) / ((float) computeHorizontalScrollRange));
                jOVar.p = Math.min(i4, (i4 * i4) / computeHorizontalScrollRange);
            }
            int i5 = jOVar.x;
            if (i5 == 0 || i5 == 1) {
                jOVar.n(1);
            }
        } else if (jOVar.x != 0) {
            jOVar.n(0);
        }
    }
}
