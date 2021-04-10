package defpackage;

import android.view.KeyEvent;
import android.view.MotionEvent;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;

/* renamed from: M40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M40 implements Qr1, WZ {
    public final EventForwarder F;
    public boolean G = true;

    public M40(WebContents webContents, J40 j40) {
        this.F = webContents.n0();
        ImeAdapterImpl.s0(webContents).N.add(this);
    }

    public static float b(MotionEvent motionEvent, int i) {
        float axisValue = motionEvent.getAxisValue(i);
        if (Math.abs(axisValue) <= 0.2f) {
            return 0.0f;
        }
        return -axisValue;
    }

    @Override // defpackage.WZ
    public void a() {
    }

    @Override // defpackage.WZ
    public void d(KeyEvent keyEvent) {
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    @Override // defpackage.WZ
    public void i(boolean z, boolean z2) {
        this.G = !z;
    }
}
