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

/* renamed from: X.0Hx  reason: invalid class name and case insensitive filesystem */
public final class C00990Hx {
    @GuardedBy("UploadServiceLogic.class")
    @Nullable
    public static C00990Hx A02;
    public static final long A03 = TimeUnit.SECONDS.toMillis(60);
    public final Context A00;
    public final C00890Hd A01;

    public final int A02(Intent intent, C00980Hw r10) {
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                Context context = this.A00;
                C00970Hv A002 = C00970Hv.A00(extras, context);
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
                        AnonymousClass0NO.A09("UploadServiceLogic", "The peer died unexpectedly, possible wakelock gap detected.");
                    }
                }
                AnonymousClass0HX r3 = A002.A06;
                String str = r3.A08;
                if (str != null) {
                    AnonymousClass0GM A003 = AnonymousClass0GM.A00(context);
                    AnonymousClass0GM.A02(A003, A003.A04, str);
                }
                A01(this, intent.getAction(), A002, r10);
                return !r3.A0A ? 3 : 2;
            } catch (IllegalArgumentException e) {
                AnonymousClass0NO.A0C("UploadServiceLogic", "Failure in runJobNow", e);
                r10.A01.stopSelf(r10.A00);
                return 2;
            } catch (C00860Gv e2) {
                AnonymousClass0NO.A0C("UploadServiceLogic", "Misunderstood service intent: %s", e2);
                r10.A01.stopSelf(r10.A00);
                return 2;
            }
        } else {
            throw new C00860Gv("Received a null intent in runJobFromService, did you ever return START_STICKY?");
        }
    }

    public static synchronized C00990Hx A00(Context context) {
        C00990Hx r0;
        synchronized (C00990Hx.class) {
            r0 = A02;
            if (r0 == null) {
                r0 = new C00990Hx(context);
                A02 = r0;
            }
        }
        return r0;
    }

    public static void A01(C00990Hx r4, String str, @Nullable C00970Hv r6, @Nullable C00980Hw r7) throws IllegalArgumentException {
        C00890Hd r42;
        AnonymousClass0HY r5;
        AnonymousClass0Hl r0;
        if ("com.facebook.analytics2.logger.UPLOAD_NOW".equals(str)) {
            r42 = r4.A01;
            AnonymousClass0P5.A00(r42);
            r5 = new AnonymousClass0HY(r6.A02, r6.A06, r6.A08);
        } else if ("com.facebook.analytics2.logger.USER_LOGOUT".equals(str)) {
            Bundle bundle = r6.A04;
            AnonymousClass0P5.A00(bundle);
            new C00960Hs(bundle);
            r42 = r4.A01;
            AnonymousClass0P5.A00(r42);
            int i = r6.A02;
            AnonymousClass0HX r1 = r6.A06;
            String str2 = r6.A08;
            AnonymousClass0P5.A00(str2);
            r5 = new AnonymousClass0HY(i, r1, str2);
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A05("Unknown action=", str));
        }
        AnonymousClass0HZ r12 = r6.A01;
        if (r12 == null) {
            Context context = r6.A03;
            synchronized (AnonymousClass0Hl.class) {
                r0 = AnonymousClass0Hl.A00;
                if (r0 == null) {
                    r0 = new C06880qC(context);
                    AnonymousClass0Hl.A00 = r0;
                }
            }
            r12 = new C06840q1(r6, r0);
            r6.A01 = r12;
        }
        C06830q0 r3 = new C06830q0(r12, r7);
        synchronized (r42) {
            AnonymousClass0Hc r2 = r42.A01.get(r5.A00);
            if (r2 != null) {
                if (r2.A00 != null) {
                    AnonymousClass0Hb r13 = new AnonymousClass0Hb(r42, r5, r3);
                    ArrayDeque<Runnable> arrayDeque = r2.A01;
                    if (arrayDeque == null) {
                        arrayDeque = new ArrayDeque<>();
                        r2.A01 = arrayDeque;
                    }
                    arrayDeque.offer(r13);
                }
            }
            C00890Hd.A00(r42, r5, r3);
        }
    }

    public C00990Hx(Context context) {
        this.A00 = context.getApplicationContext();
        this.A01 = new C00890Hd(context);
    }
}
