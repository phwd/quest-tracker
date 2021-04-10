package X;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.PowerManager;
import android.os.RemoteException;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class HV {
    @GuardedBy("UploadServiceLogic.class")
    @Nullable
    public static HV A02;
    public static final long A03 = TimeUnit.SECONDS.toMillis(60);
    public final Context A00;
    public final HE A01;

    public final int A02(Intent intent, HU hu) {
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                Context context = this.A00;
                HT A002 = HT.A00(extras, context);
                StringBuilder sb = new StringBuilder("UploadServiceLogic-");
                sb.append(intent.getComponent().getShortClassName());
                sb.append("-service-");
                sb.append(A002.A02);
                PowerManager.WakeLock newWakeLock = ((PowerManager) A002.A03.getApplicationContext().getSystemService("power")).newWakeLock(1, sb.toString());
                A002.A00 = newWakeLock;
                newWakeLock.setReferenceCounted(false);
                A002.A00.acquire(A03);
                Messenger messenger = A002.A05;
                if (messenger != null) {
                    try {
                        messenger.send(Message.obtain());
                    } catch (RemoteException unused) {
                        Mu.A01("UploadServiceLogic", "The peer died unexpectedly, possible wakelock gap detected.");
                    }
                }
                H8 h8 = A002.A06;
                String str = h8.A08;
                if (str != null) {
                    G7 A003 = G7.A00(context);
                    G7.A01(A003, A003.A02, str);
                }
                A01(this, intent.getAction(), A002, hu);
                return !h8.A0A ? 3 : 2;
            } catch (IllegalArgumentException e) {
                Mu.A03("UploadServiceLogic", "Failure in runJobNow", e);
                hu.A01.stopSelf(hu.A00);
                return 2;
            } catch (Gc e2) {
                Mu.A03("UploadServiceLogic", "Misunderstood service intent: %s", e2);
                hu.A01.stopSelf(hu.A00);
                return 2;
            }
        } else {
            throw new Gc("Received a null intent in runJobFromService, did you ever return START_STICKY?");
        }
    }

    public static synchronized HV A00(Context context) {
        HV hv;
        synchronized (HV.class) {
            hv = A02;
            if (hv == null) {
                hv = new HV(context);
                A02 = hv;
            }
        }
        return hv;
    }

    public static void A01(HV hv, String str, @Nullable HT ht, @Nullable HU hu) throws IllegalArgumentException {
        HE he;
        H9 h9;
        if ("com.facebook.analytics2.logger.UPLOAD_NOW".equals(str)) {
            he = hv.A01;
            P6.A00(he);
            h9 = new H9(ht.A02, ht.A06, ht.A08);
        } else if ("com.facebook.analytics2.logger.USER_LOGOUT".equals(str)) {
            Bundle bundle = ht.A04;
            P6.A00(bundle);
            new HR(bundle);
            he = hv.A01;
            P6.A00(he);
            int i = ht.A02;
            H8 h8 = ht.A06;
            String str2 = ht.A08;
            P6.A00(str2);
            h9 = new H9(i, h8, str2);
        } else {
            throw new IllegalArgumentException(AnonymousClass06.A04("Unknown action=", str));
        }
        HA ha = ht.A01;
        if (ha == null) {
            ha = new YT(ht, HL.A00(ht.A03));
            ht.A01 = ha;
        }
        YS ys = new YS(ha, hu);
        synchronized (he) {
            HD hd = he.A01.get(h9.A00);
            if (hd != null) {
                if (hd.A00 != null) {
                    HC hc = new HC(he, h9, ys);
                    ArrayDeque<Runnable> arrayDeque = hd.A01;
                    if (arrayDeque == null) {
                        arrayDeque = new ArrayDeque<>();
                        hd.A01 = arrayDeque;
                    }
                    arrayDeque.offer(hc);
                }
            }
            HE.A00(he, h9, ys);
        }
    }

    public HV(Context context) {
        this.A00 = context.getApplicationContext();
        this.A01 = new HE(context);
    }
}
