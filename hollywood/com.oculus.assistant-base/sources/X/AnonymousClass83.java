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

/* renamed from: X.83  reason: invalid class name */
public final class AnonymousClass83 {
    public static AnonymousClass83 A02;
    public static final long A03 = TimeUnit.SECONDS.toMillis(60);
    public final Context A00;
    public final AnonymousClass7p A01;

    public final int A02(Intent intent, AnonymousClass82 r10) {
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                Context context = this.A00;
                AnonymousClass81 A002 = AnonymousClass81.A00(extras, context);
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
                        C0139Dd.A0D("UploadServiceLogic", "The peer died unexpectedly, possible wakelock gap detected.");
                    }
                }
                AnonymousClass7j r3 = A002.A06;
                String str = r3.A08;
                if (str != null) {
                    AnonymousClass6k A003 = AnonymousClass6k.A00(context);
                    AnonymousClass6k.A02(A003, A003.A04, str);
                }
                A01(this, intent.getAction(), A002, r10);
                return !r3.A0A ? 3 : 2;
            } catch (IllegalArgumentException e) {
                C0139Dd.A0M("UploadServiceLogic", "Failure in runJobNow", e);
                r10.A01.stopSelf(r10.A00);
                return 2;
            } catch (AnonymousClass7D e2) {
                C0139Dd.A0M("UploadServiceLogic", "Misunderstood service intent: %s", e2);
                r10.A01.stopSelf(r10.A00);
                return 2;
            }
        } else {
            throw new AnonymousClass7D("Received a null intent in runJobFromService, did you ever return START_STICKY?");
        }
    }

    public static synchronized AnonymousClass83 A00(Context context) {
        AnonymousClass83 r0;
        synchronized (AnonymousClass83.class) {
            r0 = A02;
            if (r0 == null) {
                r0 = new AnonymousClass83(context);
                A02 = r0;
            }
        }
        return r0;
    }

    public static void A01(AnonymousClass83 r4, String str, AnonymousClass81 r6, AnonymousClass82 r7) {
        AnonymousClass7p r42;
        AnonymousClass7k r5;
        AnonymousClass7u r0;
        if ("com.facebook.analytics2.logger.UPLOAD_NOW".equals(str)) {
            r42 = r4.A01;
            Er.A00(r42);
            r5 = new AnonymousClass7k(r6.A02, r6.A06, r6.A08);
        } else if ("com.facebook.analytics2.logger.USER_LOGOUT".equals(str)) {
            Bundle bundle = r6.A04;
            Er.A00(bundle);
            new AnonymousClass80(bundle);
            r42 = r4.A01;
            Er.A00(r42);
            int i = r6.A02;
            AnonymousClass7j r1 = r6.A06;
            String str2 = r6.A08;
            Er.A00(str2);
            r5 = new AnonymousClass7k(i, r1, str2);
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A04("Unknown action=", str));
        }
        AnonymousClass7l r12 = r6.A01;
        if (r12 == null) {
            Context context = r6.A03;
            synchronized (AnonymousClass7u.class) {
                r0 = AnonymousClass7u.A00;
                if (r0 == null) {
                    r0 = new fD(context);
                    AnonymousClass7u.A00 = r0;
                }
            }
            r12 = new fa(r6, r0);
            r6.A01 = r12;
        }
        C0695fb fbVar = new C0695fb(r12, r7);
        synchronized (r42) {
            AnonymousClass7o r2 = (AnonymousClass7o) r42.A01.get(r5.A00);
            if (r2 != null) {
                if (r2.A00 != null) {
                    AnonymousClass7n r13 = new AnonymousClass7n(r42, r5, fbVar);
                    ArrayDeque arrayDeque = r2.A01;
                    if (arrayDeque == null) {
                        arrayDeque = new ArrayDeque();
                        r2.A01 = arrayDeque;
                    }
                    arrayDeque.offer(r13);
                }
            }
            AnonymousClass7p.A00(r42, r5, fbVar);
        }
    }

    public final void A04(int i, String str, AnonymousClass7j r8, AbstractC00517z r9) {
        boolean z;
        String str2 = r8.A08;
        if (str2 != null) {
            AnonymousClass6k A002 = AnonymousClass6k.A00(this.A00);
            AnonymousClass6k.A02(A002, A002.A04, str2);
        }
        AnonymousClass7p r4 = this.A01;
        Er.A00(r4);
        AnonymousClass7k r3 = new AnonymousClass7k(i, r8, str);
        C0694fZ fZVar = new C0694fZ(r9);
        synchronized (r4) {
            AnonymousClass7o r0 = (AnonymousClass7o) r4.A01.get(r3.A00);
            if (r0 != null) {
                if (r0.A00 != null) {
                    z = false;
                }
            }
            AnonymousClass7p.A00(r4, r3, fZVar);
            z = true;
        }
        if (!z) {
            r9.A4R(true);
        }
    }

    public AnonymousClass83(Context context) {
        this.A00 = context.getApplicationContext();
        this.A01 = new AnonymousClass7p(context);
    }

    public final void A03(int i) {
        AnonymousClass7m r1;
        C0139Dd.A0F("UploadServiceLogic", "stopScheduledJob called with jobId: %d", Integer.valueOf(i));
        AnonymousClass7p r2 = this.A01;
        Er.A00(r2);
        synchronized (r2) {
            AnonymousClass7o r0 = (AnonymousClass7o) r2.A01.get(i);
            if (!(r0 == null || (r1 = r0.A00) == null)) {
                r1.sendMessageAtFrontOfQueue(r1.obtainMessage(3));
            }
        }
    }
}
