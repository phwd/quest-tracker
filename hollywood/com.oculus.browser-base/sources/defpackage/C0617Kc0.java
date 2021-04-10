package defpackage;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: Kc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0617Kc0 extends LinearLayoutManager {
    public final /* synthetic */ int G;
    public final /* synthetic */ C1104Sc0 H;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0617Kc0(C1104Sc0 sc0, Context context, int i, boolean z, int i2) {
        super(i, z);
        this.H = sc0;
        this.G = i2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.IK0
    public void X0(RecyclerView recyclerView, VK0 vk0, int i) {
        C2367eY0 ey0 = new C2367eY0(this, recyclerView.getContext());
        ey0.f7937a = i;
        Y0(ey0);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void a1(VK0 vk0, int[] iArr) {
        if (this.G == 0) {
            iArr[0] = this.H.G0.getWidth();
            iArr[1] = this.H.G0.getWidth();
            return;
        }
        iArr[0] = this.H.G0.getHeight();
        iArr[1] = this.H.G0.getHeight();
    }
}
