package defpackage;

import J.N;
import android.widget.ScrollView;
import java.util.HashMap;
import java.util.Map;
import org.chromium.components.page_info.ConnectionInfoView;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Px  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0969Px implements AbstractC0847Nx, AbstractC3087il0 {
    public ConnectionInfoView F;
    public UH0 G;
    public final C2746gl0 H;
    public WebContents I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC6022zx1 f8723J;

    public C0969Px(C2746gl0 gl0, WebContents webContents) {
        this.H = gl0;
        this.I = webContents;
        this.f8723J = new C0908Ox(this, webContents);
    }

    @Override // defpackage.AbstractC0847Nx
    public void a(int i) {
        this.H.b(this.G, i);
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
    }

    @Override // defpackage.AbstractC0847Nx
    public void e(ConnectionInfoView connectionInfoView) {
        this.F = connectionInfoView;
        ScrollView scrollView = new ScrollView(connectionInfoView.F);
        scrollView.addView(connectionInfoView.H);
        Map c = UH0.c(AbstractC3258jl0.r);
        OH0 oh0 = AbstractC3258jl0.f10235a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = this;
        HashMap hashMap = (HashMap) c;
        hashMap.put(oh0, lh0);
        TH0 th0 = AbstractC3258jl0.f;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = scrollView;
        hashMap.put(th0, lh02);
        QH0 qh0 = AbstractC3258jl0.m;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(qh0, gh0);
        UH0 uh0 = new UH0(c, null);
        this.G = uh0;
        this.H.i(uh0, 0, true);
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        ConnectionInfoView connectionInfoView = this.F;
        N.MISU_God(connectionInfoView.L, connectionInfoView);
        this.f8723J.destroy();
        this.G = null;
    }
}
