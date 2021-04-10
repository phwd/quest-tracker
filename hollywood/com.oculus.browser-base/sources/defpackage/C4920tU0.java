package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: tU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4920tU0 extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11346a;
    public String b;

    public C4920tU0(String str) {
        this.b = str;
    }

    @Override // defpackage.MK0
    public void a(RecyclerView recyclerView, int i) {
        if (!this.f11346a && i == 1) {
            AbstractC3535lK0.a(this.b);
            this.f11346a = true;
        }
    }
}
