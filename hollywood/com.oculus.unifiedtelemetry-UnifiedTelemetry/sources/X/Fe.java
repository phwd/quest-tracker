package X;

import android.content.Context;
import com.facebook.flexiblesampling.SamplingResult;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.concurrent.TimeUnit;

@NullsafeStrict
public final class Fe {
    public static final HN A0E = new HN(0, 0, 0);
    public static final HN A0F;
    public static final HN A0G = new HN(0, 0, 0);
    public static final HN A0H;
    public Z4 A00;
    public C0261Yl A01;
    public final ZC A02;
    public final C0273Yx A03;
    public final H0 A04;
    public final SS A05;
    public final SU A06;
    public final AbstractC00126j<? extends GK> A07;
    public final Fc A08;
    public final Fg A09;
    public final GK A0A;
    public final C0258Yg A0B;
    public final MG A0C;
    public final Context A0D;

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        A0H = new HN(timeUnit.toMillis(15), timeUnit.toMillis(45), timeUnit.toMillis(30));
        TimeUnit timeUnit2 = TimeUnit.MINUTES;
        A0F = new HN(timeUnit2.toMillis(15), timeUnit2.toMillis(45), timeUnit2.toMillis(30));
    }

    public Fe(Fd fd) {
        C0259Yh.A01 = this;
        C0259Yh yh = C0259Yh.A02;
        if (yh == null) {
            yh = new C0259Yh();
            C0259Yh.A02 = yh;
        }
        this.A0A = yh;
        this.A07 = new It(6);
        Fg fg = fd.A01;
        if (fg != null) {
            this.A09 = fg;
            ZC zc = fd.A00;
            if (zc != null) {
                this.A02 = zc;
                MG A002 = MG.A00();
                this.A0C = A002;
                H0 h0 = fd.A04;
                if (h0 != null) {
                    this.A04 = h0;
                    this.A01 = new C0261Yl();
                    Z4 z4 = new Z4();
                    this.A00 = z4;
                    Context context = fd.A08;
                    if (context != null) {
                        this.A0D = context;
                        SS ss = fd.A05;
                        if (ss != null) {
                            this.A05 = ss;
                            SU su = fd.A06;
                            if (su != null) {
                                this.A06 = su;
                                Class<? extends AbstractC0090Hb> cls = fd.A07;
                                if (cls != null) {
                                    AbstractC0274Yy yy = fd.A03;
                                    AbstractC0274Yy yy2 = fd.A02;
                                    if (!(ss == null || su == null)) {
                                        C0273Yx yx = new C0273Yx(context, cls, yy, yy2, new G5(A002, ss, su), A002, fg, new C0255Yd(A0H, A0F), new C0255Yd(A0G, A0E), new C0256Ye(50), new C0256Ye(1), z4);
                                        this.A03 = yx;
                                        this.A08 = yx;
                                        this.A0B = new C0258Yg(h0, yx, new C0257Yf());
                                        return;
                                    }
                                }
                            }
                        }
                        throw null;
                    }
                }
            }
        }
        throw null;
    }

    @Deprecated
    public final GK A00(Fb fb) {
        String str = fb.A02;
        String str2 = fb.A01;
        GO go = fb.A00;
        boolean z = fb.A03;
        SamplingResult samplingResult = SamplingResult.A02;
        if (samplingResult == null) {
            O5 o5 = new O5();
            o5.A01 = true;
            o5.A00 = 1;
            samplingResult = new SamplingResult(o5);
            SamplingResult.A02 = samplingResult;
        }
        int i = samplingResult.A00;
        if (i == 0 || SamplingResult.A03.nextInt(i) != 0) {
            return this.A0A;
        }
        GK gk = (GK) this.A07.A10();
        if (gk == null) {
            gk = new GK();
        }
        gk.A04 = this;
        gk.A0B = str;
        gk.A0A = str2;
        gk.A05 = go;
        gk.A0D = z;
        YE A022 = this.A0C.A02();
        gk.A06 = A022;
        A022.A02 = YD.A00();
        if (!gk.A0E) {
            gk.A0E = true;
            gk.A09 = Integer.valueOf(samplingResult.A00);
            if (samplingResult.A01) {
                gk.A03 = GV.HAS_DOWNLOADED_SAMPLING_POLICY.getTag() | gk.A03;
            }
            return gk;
        }
        throw new IllegalStateException("Expected immutability");
    }
}
