package X;

import android.content.Context;
import com.facebook.flexiblesampling.SamplingResult;
import java.util.concurrent.TimeUnit;

/* renamed from: X.6J  reason: invalid class name */
public final class AnonymousClass6J {
    public static final AnonymousClass7w A0E = new AnonymousClass7w(0, 0, 0);
    public static final AnonymousClass7w A0F;
    public static final AnonymousClass7w A0G = new AnonymousClass7w(0, 0, 0);
    public static final AnonymousClass7w A0H;
    public C0673ev A00;
    public C0684fJ A01;
    public final AnonymousClass1g A02;
    public final f2 A03;
    public final f2 A04;
    public final C0686fN A05;
    public final HJ A06;
    public final C1396yX A07;
    public final yY A08;
    public final C1397ya A09;
    public final C0669en A0A;
    public final C00446t A0B;
    public final DR A0C;
    public final Context A0D;

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        A0H = new AnonymousClass7w(timeUnit.toMillis(15), timeUnit.toMillis(45), timeUnit.toMillis(30));
        TimeUnit timeUnit2 = TimeUnit.MINUTES;
        A0F = new AnonymousClass7w(timeUnit2.toMillis(15), timeUnit2.toMillis(45), timeUnit2.toMillis(30));
    }

    public static final C00446t A00(AnonymousClass6J r4, String str, String str2, EnumC00486y r7) {
        C0848js jsVar;
        C00446t r3 = (C00446t) r4.A02.A18();
        if (r3 == null) {
            r3 = new C00446t();
        }
        r3.A03 = r4;
        r3.A07 = str;
        r3.A06 = str2;
        r3.A04 = r7;
        C0847jr A002 = r4.A0C.A00();
        r3.A05 = A002;
        synchronized (C0848js.class) {
            jsVar = C0848js.A00;
            if (jsVar == null) {
                jsVar = new C0848js();
                C0848js.A00 = jsVar;
            }
        }
        A002.A02 = jsVar;
        if (!r3.A09) {
            r3.A09 = true;
            return r3;
        }
        throw new IllegalStateException("Expected immutability");
    }

    public final C00446t A01(String str) {
        SamplingResult A002 = this.A0A.A00();
        C00446t A003 = A00(this, null, str, EnumC00486y.CLIENT_EVENT);
        A003.A05();
        if (A002.A01) {
            A003.A02 = AnonymousClass75.HAS_DOWNLOADED_SAMPLING_POLICY.getTag() | A003.A02;
        }
        return A003;
    }

    public AnonymousClass6J(AnonymousClass6I r15) {
        C0685fM.A01 = this;
        C0685fM fMVar = C0685fM.A02;
        if (fMVar == null) {
            fMVar = new C0685fM();
            C0685fM.A02 = fMVar;
        }
        this.A0B = fMVar;
        this.A02 = new C0833jc(6);
        yY yYVar = r15.A03;
        if (yYVar != null) {
            this.A08 = yYVar;
            C0669en enVar = r15.A00;
            if (enVar != null) {
                this.A0A = enVar;
                DR dr = new DR();
                this.A0C = dr;
                C1397ya yaVar = r15.A04;
                if (yaVar != null) {
                    this.A09 = yaVar;
                    this.A01 = new C0684fJ();
                    C0673ev evVar = new C0673ev();
                    this.A00 = evVar;
                    Context context = r15.A06;
                    if (context != null) {
                        this.A0D = context;
                        C1396yX yXVar = r15.A02;
                        if (yXVar != null) {
                            this.A07 = yXVar;
                            HJ hj = r15.A01;
                            if (hj != null) {
                                this.A06 = hj;
                                if (r15.A05 == null) {
                                    throw null;
                                } else if (yXVar == null) {
                                    throw null;
                                } else if (hj != null) {
                                    f2 f2Var = new f2(context, new C00376i(dr, yXVar, hj), dr, yYVar, new C0689fQ(A0H, A0F), new C0689fQ(A0G, A0E), new C0688fP(), new C0688fP(), evVar);
                                    this.A04 = f2Var;
                                    this.A03 = f2Var;
                                    this.A05 = new C0686fN(yaVar, f2Var, new C0687fO());
                                } else {
                                    throw null;
                                }
                            } else {
                                throw null;
                            }
                        } else {
                            throw null;
                        }
                    } else {
                        throw null;
                    }
                } else {
                    throw null;
                }
            } else {
                throw null;
            }
        } else {
            throw null;
        }
    }
}
