package X;

import com.facebook.systrace.Systrace;
import java.io.IOException;

/* renamed from: X.0J6  reason: invalid class name */
public class AnonymousClass0J6 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.common.appunpacker.AppUnpacker$2";
    public final /* synthetic */ AnonymousClass0JA A00;

    public AnonymousClass0J6(AnonymousClass0JA r1) {
        this.A00 = r1;
    }

    public final void run() {
        Systrace.A01(2147483648L, "AppUnpacker.finishUnpackingOnBackgroundThread()");
        try {
            AnonymousClass0JA r0 = this.A00;
            AnonymousClass0JA.A00(r0);
            AnonymousClass0JA.A01(r0);
            Systrace.A00(2147483648L);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            Systrace.A00(2147483648L);
            throw th;
        }
    }
}
