package X;

import android.content.Context;

@Deprecated
/* renamed from: X.1Fp  reason: invalid class name */
public final class AnonymousClass1Fp {
    public final Context A00;

    public static void A00(Class<?> cls, Exception exc) {
        StringBuilder sb = new StringBuilder("Unable to instantiate GlideModule implementation for ");
        sb.append(cls);
        throw new RuntimeException(sb.toString(), exc);
    }

    public AnonymousClass1Fp(Context context) {
        this.A00 = context;
    }
}
