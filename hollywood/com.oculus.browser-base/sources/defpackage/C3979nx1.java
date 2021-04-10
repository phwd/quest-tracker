package defpackage;

import android.util.SparseArray;
import android.view.accessibility.AccessibilityEvent;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;

/* renamed from: nx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3979nx1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WebContentsAccessibilityImpl f10522a;

    public C3979nx1(WebContentsAccessibilityImpl webContentsAccessibilityImpl) {
        this.f10522a = webContentsAccessibilityImpl;
    }

    public boolean a(int i, int i2) {
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = this.f10522a;
        SparseArray sparseArray = WebContentsAccessibilityImpl.F;
        AccessibilityEvent b = webContentsAccessibilityImpl.b(i, i2);
        if (b == null) {
            return false;
        }
        this.f10522a.q(b);
        if (i2 != 128) {
            return true;
        }
        WebContentsAccessibilityImpl webContentsAccessibilityImpl2 = this.f10522a;
        AccessibilityEvent b2 = webContentsAccessibilityImpl2.b(webContentsAccessibilityImpl2.N, 256);
        if (b2 != null) {
            this.f10522a.q(b2);
            this.f10522a.N = i;
            return true;
        } else if (i == -1) {
            return true;
        } else {
            WebContentsAccessibilityImpl webContentsAccessibilityImpl3 = this.f10522a;
            if (webContentsAccessibilityImpl3.N == i) {
                return true;
            }
            webContentsAccessibilityImpl3.N = i;
            return true;
        }
    }

    public void b(Runnable runnable) {
        this.f10522a.P.removeCallbacks(runnable);
    }
}
