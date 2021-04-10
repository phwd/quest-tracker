package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* renamed from: AY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AY0 extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7675a = false;
    public final /* synthetic */ CY0 b;

    public AY0(CY0 cy0) {
        this.b = cy0;
    }

    @Override // defpackage.MK0
    public void a(RecyclerView recyclerView, int i) {
        if (i == 0 && this.f7675a) {
            this.f7675a = false;
            this.b.f();
        }
    }

    @Override // defpackage.MK0
    public void b(RecyclerView recyclerView, int i, int i2) {
        if (i != 0 || i2 != 0) {
            this.f7675a = true;
        }
    }
}
