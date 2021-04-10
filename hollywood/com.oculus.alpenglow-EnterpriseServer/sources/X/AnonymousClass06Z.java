package X;

import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: X.06Z  reason: invalid class name */
public class AnonymousClass06Z implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.app.ActivityRecreator$3";
    public final /* synthetic */ Object A00;
    public final /* synthetic */ Object A01;

    public AnonymousClass06Z(Object obj, Object obj2) {
        this.A00 = obj;
        this.A01 = obj2;
    }

    public final void run() {
        try {
            Method method = C006406b.A05;
            if (method != null) {
                method.invoke(this.A00, this.A01, false, "AppCompat recreation");
            } else {
                C006406b.A04.invoke(this.A00, this.A01, false);
            }
        } catch (RuntimeException e) {
            if (e.getClass() == RuntimeException.class && e.getMessage() != null && e.getMessage().startsWith("Unable to stop")) {
                throw e;
            }
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
        }
    }
}
