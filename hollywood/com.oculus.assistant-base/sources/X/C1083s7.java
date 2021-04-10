package X;

import android.content.Context;
import android.os.Looper;

/* renamed from: X.s7  reason: case insensitive filesystem */
public class C1083s7 extends C0306Qc {
    public final AbstractC1084s8 A00(Context context, Looper looper, RQ rq, Object obj, AbstractC1086sA sAVar, AbstractC1087sB sBVar) {
        if (this instanceof E6) {
            throw new NoSuchMethodError();
        } else if (this instanceof E8) {
            return new C0643dj(context, looper, rq, sAVar, sBVar);
        } else {
            if (this instanceof G0) {
                return new C0654dz(context, looper, rq, sAVar, sBVar);
            }
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }
}
