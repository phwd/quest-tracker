package defpackage;

import J.N;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.components.page_info.ConnectionInfoView;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.PageInfoRowView;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Du0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0233Du0 implements AbstractC0174Cv0, AbstractC0847Nx {
    public AbstractC5675xv0 F;
    public final WebContents G;
    public final AbstractC3292jw1 H;
    public PageInfoRowView I;

    /* renamed from: J  reason: collision with root package name */
    public String f7923J;
    public ConnectionInfoView K;
    public ViewGroup L;

    public C0233Du0(AbstractC5675xv0 xv0, PageInfoRowView pageInfoRowView, WebContents webContents, AbstractC3292jw1 jw1) {
        this.F = xv0;
        this.G = webContents;
        this.H = jw1;
        this.I = pageInfoRowView;
    }

    @Override // defpackage.AbstractC0847Nx
    public void a(int i) {
        ((PageInfoController) this.F).c();
    }

    @Override // defpackage.AbstractC0174Cv0
    public String b() {
        return this.f7923J;
    }

    @Override // defpackage.AbstractC0174Cv0
    public View c(ViewGroup viewGroup) {
        this.L = new FrameLayout(this.I.getContext());
        this.K = new ConnectionInfoView(this.I.getContext(), this.G, this, this.H);
        return this.L;
    }

    @Override // defpackage.AbstractC0174Cv0
    public void d() {
        this.L = null;
        ConnectionInfoView connectionInfoView = this.K;
        N.MISU_God(connectionInfoView.L, connectionInfoView);
    }

    @Override // defpackage.AbstractC0847Nx
    public void e(ConnectionInfoView connectionInfoView) {
        ViewGroup viewGroup = this.L;
        if (viewGroup != null) {
            viewGroup.addView(connectionInfoView.H);
        }
    }
}
