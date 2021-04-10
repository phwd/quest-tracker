package defpackage;

import J.N;
import android.hardware.display.DisplayManager;
import android.view.Display;
import org.chromium.base.ContextUtils;
import org.chromium.ui.display.DisplayAndroidManager;

/* renamed from: aG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1634aG implements DisplayManager.DisplayListener {
    public final /* synthetic */ DisplayAndroidManager F;

    public C1634aG(DisplayAndroidManager displayAndroidManager, ZF zf) {
        this.F = displayAndroidManager;
    }

    public void onDisplayAdded(int i) {
    }

    public void onDisplayChanged(int i) {
        C5047uC0 uc0 = (C5047uC0) this.F.d.get(i);
        Display display = ((DisplayManager) ContextUtils.getApplicationContext().getSystemService("display")).getDisplay(i);
        if (uc0 != null && display != null) {
            uc0.f(display);
        }
    }

    public void onDisplayRemoved(int i) {
        DisplayAndroidManager displayAndroidManager = this.F;
        if (i != displayAndroidManager.c && ((YF) displayAndroidManager.d.get(i)) != null) {
            DisplayAndroidManager displayAndroidManager2 = this.F;
            long j = displayAndroidManager2.b;
            if (j != 0) {
                N.MyzQIqd_(j, displayAndroidManager2, i);
            }
            this.F.d.remove(i);
        }
    }
}
