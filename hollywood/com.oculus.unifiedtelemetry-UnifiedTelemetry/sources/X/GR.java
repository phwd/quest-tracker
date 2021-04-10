package X;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import java.io.File;
import javax.annotation.Nullable;

public class GR extends Handler {
    @Nullable
    public G0 A00;
    public final Object A01 = new Object();
    public final Object A02 = new Object();
    public final /* synthetic */ GS A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GR(GS gs, @Nullable Looper looper) {
        super(looper);
        this.A03 = gs;
    }

    private GH A00() {
        GS gs = this.A03;
        GH gh = gs.A00;
        if (gh != null) {
            return gh;
        }
        GI gi = gs.A02;
        Context context = gi.A03;
        Fz<Object> A002 = Fz.A00(!HW.A00(context).A01());
        File file = new File(context.getDir("analytics", 0), gi.A0B);
        C0256Ye ye = gi.A07;
        int i = ye.A01;
        int i2 = ye.A00;
        Fx fx = gi.A05;
        MG mg = gi.A09;
        String A003 = MX.A00();
        if (A003 == null) {
            A003 = "unknown";
        }
        Z1 z1 = new Z1(i, i2, fx, mg, new File(file, A003), A002, gi.A01);
        int i3 = gi.A00;
        H8 h8 = new H8(file, gi.A06);
        Fg fg = gi.A04;
        Class<? extends HandlerThreadFactory> cls = gi.A0A;
        C0255Yd yd = gi.A08;
        GH gh2 = new GH(z1, new YX(context, i3, h8, fg, cls, yd.A01, yd.A00, gi.A02));
        gs.A00 = gh2;
        return gh2;
    }

    private GH A01() {
        GS gs = this.A03;
        if (gs.A01 == null) {
            GI gi = gs.A02;
            C0256Ye ye = gi.A07;
            int i = ye.A01;
            int i2 = ye.A00;
            Fx fx = gi.A05;
            MG mg = gi.A09;
            GH gh = new GH(new C0275Yz(i, i2, fx, mg), new C0269Yt(gi.A03, mg, gi.A06));
            gs.A01 = gh;
            gh.A00.A02(this.A00);
        }
        return gs.A01;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:40|41|(2:43|(3:45|46|51)(2:47|48))|52|53|54|55|56|57|(1:59)|60) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:80|81|82) */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0114, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0115, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0116, code lost:
        android.os.Trace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0119, code lost:
        if (r2 != null) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x011b, code lost:
        r2.A00(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x011e, code lost:
        r3.A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0139, code lost:
        if (r1 != null) goto L_0x0143;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        A01();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x013f, code lost:
        r1 = r5.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0141, code lost:
        if (r1 == null) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0143, code lost:
        r1.A00(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0147, code lost:
        r3.A00();
        android.os.Trace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0154, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x015a, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x015b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x015c, code lost:
        r1 = r5.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x015e, code lost:
        if (r1 != null) goto L_0x0160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0160, code lost:
        r1.A00(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0164, code lost:
        r3.A00();
        android.os.Trace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x016a, code lost:
        throw r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00f6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:80:0x013c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleMessage(android.os.Message r7) {
        /*
        // Method dump skipped, instructions count: 384
        */
        throw new UnsupportedOperationException("Method not decompiled: X.GR.handleMessage(android.os.Message):void");
    }
}
