package defpackage;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.content_public.browser.WebContents;

/* renamed from: eG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2326eG implements V10 {
    public Window F;
    public int G = 0;
    public W10 H;
    public final C2668gG I;

    public C2326eG(C2668gG gGVar) {
        this.I = gGVar;
        b();
    }

    public static int a(int i, float f) {
        return (int) Math.ceil((double) (((float) i) / f));
    }

    public void b() {
        W10 w10;
        Activity activity = (Activity) this.I.f9986a.i().s0().get();
        if (this.H == null && activity != null) {
            Activity activity2 = (Activity) this.I.f9986a.i().s0().get();
            if (activity2 == null) {
                w10 = null;
            } else {
                w10 = ((ChromeActivity) activity2).L0;
            }
            this.H = w10;
            if (w10 != null) {
                w10.H.b(this);
                this.F = activity.getWindow();
            }
        }
    }

    public void c() {
        if (Build.VERSION.SDK_INT >= 28) {
            Window window = this.F;
            WindowManager.LayoutParams attributes = window == null ? null : window.getAttributes();
            if (attributes != null) {
                int i = 0;
                if (this.I.f9986a.isUserInteractable()) {
                    int i2 = this.G;
                    if (i2 == 1) {
                        i = 2;
                    } else if (i2 == 2 || i2 == 3) {
                        i = 1;
                    }
                }
                if (attributes.layoutInDisplayCutoutMode != i) {
                    attributes.layoutInDisplayCutoutMode = i;
                    this.F.setAttributes(attributes);
                }
            }
        }
    }

    @Override // defpackage.V10
    public void f(Rect rect) {
        WebContents a2 = this.I.a();
        if (a2 != null) {
            float f = this.I.a().I().I.e;
            rect.set(a(rect.left, f), a(rect.top, f), a(rect.right, f), a(rect.bottom, f));
            a2.j0(rect);
        }
    }

    @Override // defpackage.V10
    public void g(int i, int i2, int i3, int i4) {
    }
}
