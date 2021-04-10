package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import org.chromium.base.ContextUtils;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.components.thinwebview.internal.CompositorViewImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Zg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1544Zg1 extends FrameLayout implements AbstractC1422Xg1 {
    public final AbstractC0845Nw F;
    public WindowAndroid G;
    public long H;
    public WebContents I;

    /* renamed from: J  reason: collision with root package name */
    public View f9360J;

    public C1544Zg1(Context context, C1483Yg1 yg1) {
        super(context);
        if (ContextUtils.a(context) != null) {
            this.G = new C2971i3(context);
        } else {
            this.G = new WindowAndroid(context);
        }
        CompositorViewImpl compositorViewImpl = new CompositorViewImpl(context, this.G, yg1);
        this.F = compositorViewImpl;
        addView(compositorViewImpl.b, new FrameLayout.LayoutParams(-1, -1));
        this.H = N.M$XqDO$W(this, compositorViewImpl, this.G);
    }

    public void a(WebContents webContents, View view, WebContentsDelegateAndroid webContentsDelegateAndroid) {
        if (this.H != 0) {
            this.I = webContents;
            View view2 = this.f9360J;
            if (view2 != view) {
                if (view2 != null) {
                    removeViewAt(1);
                }
                this.f9360J = view;
                if (view != null) {
                    addView(view, 1);
                }
            }
            N.M9Q7LfVV(this.H, this, this.I, webContentsDelegateAndroid);
            this.I.O();
        }
    }

    public void b() {
        if (this.H != 0) {
            View view = this.f9360J;
            if (view != null) {
                removeView(view);
                this.f9360J = null;
            }
            CompositorViewImpl compositorViewImpl = (CompositorViewImpl) this.F;
            long j = compositorViewImpl.d;
            if (j != 0) {
                N.M_L66GG1(j, compositorViewImpl);
                compositorViewImpl.d = 0;
            }
            N.Mi0zHYZ4(this.H, this);
            this.H = 0;
            this.G.destroy();
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        long j = this.H;
        if (j != 0) {
            if (i != i3 || i2 != i4) {
                N.MgG98$5a(j, this, i, i2);
            }
        }
    }

    public void setAlpha(float f) {
        CompositorViewImpl compositorViewImpl = (CompositorViewImpl) this.F;
        if (compositorViewImpl.d != 0) {
            compositorViewImpl.b.setAlpha(f);
        }
    }
}
