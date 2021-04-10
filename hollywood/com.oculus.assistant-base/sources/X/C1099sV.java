package X;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.zaaa;

/* renamed from: X.sV  reason: case insensitive filesystem */
public final class C1099sV extends C0310Qg implements AbstractC0328Ri {
    public static final C1083s7 A00;
    public static final C1085s9 A01;
    public static final C0307Qd A02;

    static {
        C1085s9 s9Var = new C1085s9();
        A01 = s9Var;
        G0 g0 = new G0();
        A00 = g0;
        A02 = new C0307Qd("ClientTelemetry.API", g0, s9Var);
    }

    public C1099sV(Context context) {
        super(context, A02, AbstractC0305Qb.A00, C0309Qf.A02);
    }

    @Override // X.AbstractC0328Ri
    public final Sk A62(zaaa zaaa) {
        R0 r0 = new R0();
        Feature[] featureArr = {SP.A00};
        r0.A02 = featureArr;
        r0.A01 = false;
        r0.A00 = new sU(zaaa);
        C1093sJ sJVar = new C1093sJ(r0, featureArr, false);
        Sl sl = new Sl();
        C0319Qs qs = this.A05;
        IR ir = new IR(sJVar, sl, this.A06);
        Handler handler = qs.A05;
        handler.sendMessage(handler.obtainMessage(4, new RA(ir, qs.A0B.get(), this)));
        return sl.A00;
    }
}
