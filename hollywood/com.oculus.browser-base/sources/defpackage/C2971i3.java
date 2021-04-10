package defpackage;

import J.N;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: i3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2971i3 extends WindowAndroid implements Z9 {
    public int b0;
    public SparseArray c0 = new SparseArray();
    public boolean d0;
    public YZ e0;

    public C2971i3(Context context) {
        super(context);
        Activity a2 = ContextUtils.a(context);
        if (a2 != null) {
            this.d0 = true;
            ApplicationStatus.g(this, a2);
            L2 J0 = J0();
            this.G = J0;
            C3493l60.F = J0;
            this.R = new I2(s0());
            return;
        }
        throw new IllegalArgumentException("Context is not and does not wrap an Activity");
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public boolean B0(Ky1 ky1) {
        int indexOfValue = this.c0.indexOfValue(ky1);
        if (indexOfValue < 0) {
            return false;
        }
        this.c0.remove(indexOfValue);
        this.K.remove(Integer.valueOf(indexOfValue));
        return true;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public int E0(PendingIntent pendingIntent, Ky1 ky1, Integer num) {
        int K0 = K0();
        IntentSender intentSender = pendingIntent.getIntentSender();
        Activity activity = (Activity) s0().get();
        boolean z = false;
        if (activity != null) {
            try {
                activity.startIntentSenderForResult(intentSender, K0, new Intent(), 0, 0, 0);
                z = true;
            } catch (IntentSender.SendIntentException unused) {
            }
        }
        if (!z) {
            return -1;
        }
        L0(K0, ky1, num);
        return K0;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public int F0(Intent intent, Ky1 ky1, Integer num) {
        int K0 = K0();
        Activity activity = (Activity) s0().get();
        boolean z = false;
        if (activity != null) {
            try {
                activity.startActivityForResult(intent, K0);
                z = true;
            } catch (ActivityNotFoundException unused) {
            }
        }
        if (!z) {
            return -1;
        }
        L0(K0, ky1, num);
        return K0;
    }

    public L2 J0() {
        return new L2(s0());
    }

    public final int K0() {
        int i = this.b0;
        int i2 = i + 1000;
        this.b0 = (i + 1) % 100;
        return i2;
    }

    public final void L0(int i, Ky1 ky1, Integer num) {
        String str;
        this.c0.put(i, ky1);
        HashMap hashMap = this.K;
        Integer valueOf = Integer.valueOf(i);
        if (num == null) {
            str = null;
        } else {
            str = ContextUtils.getApplicationContext().getString(num.intValue());
        }
        hashMap.put(valueOf, str);
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public WeakReference s0() {
        if (this.e0 == null) {
            this.e0 = new YZ(ContextUtils.a((Context) this.f11022J.get()));
        }
        return this.e0;
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 5) {
            long j = this.H;
            if (j != 0) {
                N.MMLuxHp6(j, this);
            }
        } else if (i == 2) {
            long j2 = this.H;
            if (j2 != 0) {
                N.MbyUPhMo(j2, this);
            }
        } else if (i == 4) {
            Iterator it = this.W.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((Jy1) uq0.next()).f();
                } else {
                    return;
                }
            }
        } else if (i == 3) {
            Iterator it2 = this.W.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (uq02.hasNext()) {
                    ((Jy1) uq02.next()).g();
                } else {
                    return;
                }
            }
        }
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public int t0() {
        if (this.d0) {
            return ApplicationStatus.e((Activity) s0().get());
        }
        return 6;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public C3493l60 u0() {
        return (L2) this.G;
    }
}
