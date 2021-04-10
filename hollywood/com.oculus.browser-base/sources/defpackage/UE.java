package defpackage;

import android.app.Dialog;
import android.os.IBinder;
import android.view.WindowManager;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.androidoverlay.DialogOverlayImpl;
import org.chromium.gfx.mojom.Rect;

/* renamed from: UE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UE {

    /* renamed from: a  reason: collision with root package name */
    public DialogOverlayImpl f9014a;
    public Dialog b;
    public TE c;
    public WindowManager.LayoutParams d;
    public boolean e;

    public final boolean a(Rect rect) {
        WindowManager.LayoutParams layoutParams = this.d;
        int i = layoutParams.x;
        int i2 = rect.d;
        if (i == i2 && layoutParams.y == rect.e && layoutParams.width == rect.f && layoutParams.height == rect.g) {
            return false;
        }
        layoutParams.x = i2;
        layoutParams.y = rect.e;
        layoutParams.width = rect.f;
        layoutParams.height = rect.g;
        return true;
    }

    public final void b() {
        Dialog dialog = this.b;
        if (dialog != null && dialog.isShowing()) {
            try {
                this.b.dismiss();
            } catch (Exception unused) {
                AbstractC1220Ua0.f("DSCore", "Failed to dismiss overlay dialog.  \"WindowLeaked\" is ignorable.", new Object[0]);
            }
        }
        this.b = null;
        this.c = null;
    }

    public void c(IBinder iBinder) {
        DialogOverlayImpl dialogOverlayImpl;
        WindowManager.LayoutParams layoutParams;
        IBinder iBinder2;
        Dialog dialog = this.b;
        if (dialog != null && (dialogOverlayImpl = this.f9014a) != null) {
            if (iBinder == null || !((iBinder2 = (layoutParams = this.d).token) == null || iBinder == iBinder2)) {
                Object obj = ThreadUtils.f10596a;
                if (dialogOverlayImpl.H != null) {
                    dialogOverlayImpl.g0();
                    dialogOverlayImpl.f0();
                }
                this.f9014a = null;
                b();
            } else if (iBinder2 != iBinder) {
                layoutParams.token = iBinder;
                dialog.getWindow().setAttributes(this.d);
                this.c = new TE(this, null);
                this.b.getWindow().takeSurface(this.c);
                this.b.show();
            }
        }
    }
}
