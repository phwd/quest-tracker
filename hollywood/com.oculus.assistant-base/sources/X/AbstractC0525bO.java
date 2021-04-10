package X;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import java.lang.ref.Reference;

/* renamed from: X.bO  reason: case insensitive filesystem */
public abstract class AbstractC0525bO implements AbstractC0858kr {
    public static final Object A00 = new Object();
    public static final Bh A01 = new Bh(new C0857kq());

    public static AbstractC0525bO get(Context context) {
        Reference reference = (Reference) A01.A01.get(context);
        if (reference != null && reference.get() != null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof Application) {
            applicationContext.getApplicationContext();
        }
        StringBuilder sb = new StringBuilder("Injector is not supported in process ");
        sb.append(CV.A00().A01);
        sb.append(". Current thread is: ");
        sb.append(Thread.currentThread());
        sb.append(" and current Looper is: ");
        sb.append(Looper.myLooper());
        throw new UnsupportedOperationException(sb.toString());
    }

    public AbstractC0525bO() {
        throw null;
    }
}
