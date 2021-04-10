package defpackage;

import org.chromium.net.AndroidCellularSignalStrength;

/* renamed from: Q4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q4 implements Runnable {
    public final /* synthetic */ AndroidCellularSignalStrength F;

    public Q4(AndroidCellularSignalStrength androidCellularSignalStrength) {
        this.F = androidCellularSignalStrength;
    }

    public void run() {
        new R4(this.F);
    }
}
