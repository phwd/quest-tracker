package X;

import android.content.Context;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.22M  reason: invalid class name */
public final class AnonymousClass22M {
    @GuardedBy("member reference guarded by this")
    public AnonymousClass22N A00;
    @GuardedBy("member reference guarded by this")
    public C145123p A01;
    public InetAddress A02;
    public InetAddress A03;
    @GuardedBy("member reference guarded by this")
    public Socket A04;
    public final Context A05;
    public final RealtimeSinceBootClock A06;
    public final AnonymousClass1QK A07;
    public final C143923b A08;
    public final AnonymousClass1P7 A09;
    public final AnonymousClass22T A0A;
    public final AnonymousClass1Jd A0B;
    public final ScheduledExecutorService A0C;
    public final AnonymousClass1PJ A0D;
    public volatile AnonymousClass247 A0E;
    public volatile C141922h A0F;
    @GuardedBy("this")
    public volatile boolean A0G = false;

    public static int A01(DataOutputStream dataOutputStream, int i) throws IOException {
        int i2 = 0;
        do {
            int i3 = i % 128;
            i >>= 7;
            if (i > 0) {
                i3 |= 128;
            }
            dataOutputStream.writeByte(i3);
            i2++;
        } while (i > 0);
        return i2;
    }

    public static int A00(AnonymousClass23P r2) {
        int i = (r2.A03.mValue << 4) | 0;
        if (r2.A04) {
            i |= 8;
        }
        int i2 = i | (r2.A02 << 1);
        if (r2.A01) {
            return i2 | 1;
        }
        return i2;
    }

    public static byte[] A03(String str) {
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public final void A04() {
        Socket socket = this.A04;
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
        }
        synchronized (this) {
            this.A04 = null;
            this.A00 = null;
            this.A01 = null;
            this.A0F.A00.A0X = AnonymousClass007.A04;
        }
        this.A0F.A00();
    }

    public AnonymousClass22M(C143923b r2, AnonymousClass1QK r3, AnonymousClass22T r4, RealtimeSinceBootClock realtimeSinceBootClock, AnonymousClass1PJ r6, ScheduledExecutorService scheduledExecutorService, AnonymousClass1P7 r8, AnonymousClass1Jd r9, Context context, @Nullable String str) {
        this.A08 = r2;
        this.A07 = r3;
        this.A0A = r4;
        this.A06 = realtimeSinceBootClock;
        this.A0D = r6;
        this.A0C = scheduledExecutorService;
        this.A09 = r8;
        this.A0B = r9;
        this.A05 = context;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x02fe  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0309  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x030f  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0335  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x033e  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02a1  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02a9  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x02f4  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x02f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A02(X.AnonymousClass22M r22, X.C145123p r23, X.C143522x r24) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1928
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass22M.A02(X.22M, X.23p, X.22x):void");
    }
}
