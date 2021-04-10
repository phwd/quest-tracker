package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;

/* renamed from: Oc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0860Oc0 extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0337Fl0 f8635a;
    public final /* synthetic */ MaterialButton b;
    public final /* synthetic */ C1104Sc0 c;

    public C0860Oc0(C1104Sc0 sc0, C0337Fl0 fl0, MaterialButton materialButton) {
        this.c = sc0;
        this.f8635a = fl0;
        this.b = materialButton;
    }

    @Override // defpackage.MK0
    public void a(RecyclerView recyclerView, int i) {
        if (i == 0) {
            recyclerView.announceForAccessibility(this.b.getText());
        }
    }

    @Override // defpackage.MK0
    public void b(RecyclerView recyclerView, int i, int i2) {
        int i3;
        if (i < 0) {
            i3 = this.c.e1().k1();
        } else {
            i3 = this.c.e1().l1();
        }
        this.c.C0 = this.f8635a.s(i3);
        this.b.setText(this.f8635a.I.F.t(i3).G);
    }
}
