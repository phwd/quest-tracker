package defpackage;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.net.AndroidCellularSignalStrength;

/* renamed from: R4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R4 extends PhoneStateListener implements AbstractC1678aa {

    /* renamed from: a  reason: collision with root package name */
    public final TelephonyManager f8810a;
    public final /* synthetic */ AndroidCellularSignalStrength b;

    public R4(AndroidCellularSignalStrength androidCellularSignalStrength) {
        this.b = androidCellularSignalStrength;
        Object obj = ThreadUtils.f10596a;
        TelephonyManager telephonyManager = (TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone");
        this.f8810a = telephonyManager;
        if (telephonyManager.getSimState() == 5) {
            ApplicationStatus.h.b(this);
            a(ApplicationStatus.getStateForApplication());
        }
    }

    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        if (i == 1) {
            this.f8810a.listen(this, 256);
        } else if (i == 2) {
            this.b.b = Integer.MIN_VALUE;
            this.f8810a.listen(this, 0);
        }
    }

    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        if (ApplicationStatus.getStateForApplication() == 1) {
            try {
                this.b.b = signalStrength.getLevel();
            } catch (SecurityException unused) {
                this.b.b = Integer.MIN_VALUE;
            }
        }
    }
}
