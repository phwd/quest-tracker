package X;

import android.os.Handler;
import java.util.concurrent.Callable;

/* renamed from: X.09b  reason: invalid class name */
public class AnonymousClass09b implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.provider.SelfDestructiveThread$2";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ AnonymousClass09d A01;
    public final /* synthetic */ AnonymousClass09e A02;
    public final /* synthetic */ Callable A03;

    public AnonymousClass09b(AnonymousClass09e r1, Callable callable, Handler handler, AnonymousClass09d r4) {
        this.A02 = r1;
        this.A03 = callable;
        this.A00 = handler;
        this.A01 = r4;
    }

    public final void run() {
        Object obj;
        try {
            obj = this.A03.call();
        } catch (Exception unused) {
            obj = null;
        }
        this.A00.post(new AnonymousClass09a(this, obj));
    }
}
