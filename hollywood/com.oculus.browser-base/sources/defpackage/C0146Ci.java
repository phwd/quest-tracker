package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: Ci  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0146Ci implements Q31 {
    public final RecyclerView F;

    public C0146Ci(RecyclerView recyclerView) {
        this.F = recyclerView;
    }

    @Override // defpackage.Q31
    public Object get() {
        return Integer.valueOf(this.F.computeHorizontalScrollOffset());
    }
}
