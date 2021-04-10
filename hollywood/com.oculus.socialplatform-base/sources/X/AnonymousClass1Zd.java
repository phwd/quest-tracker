package X;

/* renamed from: X.1Zd  reason: invalid class name */
public class AnonymousClass1Zd implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.msys.mca.MailboxTaskImpl$1";
    public final /* synthetic */ AnonymousClass1Zb A00;

    public AnonymousClass1Zd(AnonymousClass1Zb r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass1Zb r2 = this.A00;
        synchronized (r2) {
            AnonymousClass1YZ<T> r1 = r2.A00;
            if (r1 != null) {
                r2.A00 = null;
                r1.onCompletion(r2.A02);
            }
        }
    }
}
