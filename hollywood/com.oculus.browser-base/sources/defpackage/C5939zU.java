package defpackage;

import android.content.Context;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.device.gamepad.GamepadList;

/* renamed from: zU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5939zU extends Vy1 implements Qr1 {
    public final Context F;

    public C5939zU(WebContents webContents) {
        this.F = ((WebContentsImpl) webContents).u0();
        Zy1.t0(webContents).r0(this);
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003f A[SYNTHETIC] */
    @Override // defpackage.Wy1, defpackage.Vy1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAttachedToWindow() {
        /*
            r8 = this;
            android.content.Context r0 = r8.F
            org.chromium.device.gamepad.GamepadList r1 = defpackage.CU.f7812a
            int r2 = r1.d
            int r3 = r2 + 1
            r1.d = r3
            if (r2 != 0) goto L_0x004f
            java.lang.String r2 = "input"
            java.lang.Object r0 = r0.getSystemService(r2)
            android.hardware.input.InputManager r0 = (android.hardware.input.InputManager) r0
            r1.c = r0
            java.lang.Object r0 = r1.f10954a
            monitor-enter(r0)
            android.hardware.input.InputManager r2 = r1.c     // Catch:{ all -> 0x004c }
            int[] r2 = r2.getInputDeviceIds()     // Catch:{ all -> 0x004c }
            r3 = 0
            r4 = r3
        L_0x0021:
            int r5 = r2.length     // Catch:{ all -> 0x004c }
            if (r4 >= r5) goto L_0x0042
            r5 = r2[r4]     // Catch:{ all -> 0x004c }
            android.view.InputDevice r5 = android.view.InputDevice.getDevice(r5)     // Catch:{ all -> 0x004c }
            if (r5 != 0) goto L_0x002d
            goto L_0x0039
        L_0x002d:
            int r6 = r5.getSources()     // Catch:{ all -> 0x004c }
            r7 = 16777232(0x1000010, float:2.3509932E-38)
            r6 = r6 & r7
            if (r6 != r7) goto L_0x0039
            r6 = 1
            goto L_0x003a
        L_0x0039:
            r6 = r3
        L_0x003a:
            if (r6 == 0) goto L_0x003f
            r1.c(r5)     // Catch:{ all -> 0x004c }
        L_0x003f:
            int r4 = r4 + 1
            goto L_0x0021
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x004c }
            android.hardware.input.InputManager r0 = r1.c
            android.hardware.input.InputManager$InputDeviceListener r1 = r1.f
            r2 = 0
            r0.registerInputDeviceListener(r1, r2)
            goto L_0x004f
        L_0x004c:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5939zU.onAttachedToWindow():void");
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void onDetachedFromWindow() {
        GamepadList gamepadList = CU.f7812a;
        int i = gamepadList.d - 1;
        gamepadList.d = i;
        if (i == 0) {
            synchronized (gamepadList.f10954a) {
                for (int i2 = 0; i2 < 4; i2++) {
                    gamepadList.b[i2] = null;
                }
            }
            gamepadList.c.unregisterInputDeviceListener(gamepadList.f);
            gamepadList.c = null;
        }
    }
}
