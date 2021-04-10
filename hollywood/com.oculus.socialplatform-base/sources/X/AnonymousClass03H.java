package X;

import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: X.03H  reason: invalid class name */
public class AnonymousClass03H implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.app.ActivityRecreator$3";
    public final /* synthetic */ Object A00;
    public final /* synthetic */ Object A01;

    public AnonymousClass03H(Object obj, Object obj2) {
        this.A00 = obj;
        this.A01 = obj2;
    }

    public final void run() {
        try {
            Method method = AnonymousClass03J.A05;
            if (method != null) {
                method.invoke(this.A00, this.A01, false, "AppCompat recreation");
            } else {
                AnonymousClass03J.A04.invoke(this.A00, this.A01, false);
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
