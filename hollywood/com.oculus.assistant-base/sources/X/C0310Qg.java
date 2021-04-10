package X;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;

/* renamed from: X.Qg  reason: case insensitive filesystem */
public class C0310Qg {
    public final int A00;
    public final Context A01;
    public final AbstractC0305Qb A02;
    public final C0307Qd A03;
    public final C0315Qm A04;
    public final C0319Qs A05;
    public final Qz A06;
    public final String A07;
    public final Looper A08;
    public final AbstractC0311Qi A09;

    public static final RP A00(C0310Qg qg) {
        RP rp = new RP();
        Set emptySet = Collections.emptySet();
        C00010d r0 = rp.A00;
        if (r0 == null) {
            r0 = new C00010d();
            rp.A00 = r0;
        }
        r0.addAll(emptySet);
        Context context = qg.A01;
        rp.A03 = context.getClass().getName();
        rp.A02 = context.getPackageName();
        return rp;
    }

    public C0310Qg(Context context, C0307Qd qd, AbstractC0305Qb qb, C0309Qf qf) {
        String str;
        RZ.A02(context, "Null context is not permitted.");
        RZ.A02(qd, "Api must not be null.");
        RZ.A02(qf, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.A01 = context.getApplicationContext();
        if (S6.A02()) {
            try {
                str = (String) Context.class.getMethod("getAttributionTag", new Class[0]).invoke(context, new Object[0]);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
            this.A07 = str;
            this.A03 = qd;
            this.A02 = qb;
            this.A08 = qf.A00;
            this.A04 = new C0315Qm(qd, qb, str);
            this.A09 = new Ij(this);
            C0319Qs A022 = C0319Qs.A02(this.A01);
            this.A05 = A022;
            this.A00 = A022.A0A.getAndIncrement();
            this.A06 = qf.A01;
            Handler handler = this.A05.A05;
            handler.sendMessage(handler.obtainMessage(7, this));
        }
        str = null;
        this.A07 = str;
        this.A03 = qd;
        this.A02 = qb;
        this.A08 = qf.A00;
        this.A04 = new C0315Qm(qd, qb, str);
        this.A09 = new Ij(this);
        C0319Qs A0222 = C0319Qs.A02(this.A01);
        this.A05 = A0222;
        this.A00 = A0222.A0A.getAndIncrement();
        this.A06 = qf.A01;
        Handler handler2 = this.A05.A05;
        handler2.sendMessage(handler2.obtainMessage(7, this));
    }
}
