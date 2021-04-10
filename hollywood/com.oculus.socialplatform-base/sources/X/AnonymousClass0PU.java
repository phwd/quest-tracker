package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.cli.HelpFormatter;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0PU  reason: invalid class name */
public final class AnonymousClass0PU implements ThreadFactory {
    public final int A00 = 10;
    public final String A01;
    public final AtomicInteger A02 = new AtomicInteger(1);
    public final boolean A03;

    public AnonymousClass0PU(String str) {
        this.A01 = str;
        this.A03 = true;
    }

    public final Thread newThread(Runnable runnable) {
        String str;
        AnonymousClass0PT r3 = new AnonymousClass0PT(this, runnable);
        if (this.A03) {
            str = AnonymousClass006.A08(this.A01, HelpFormatter.DEFAULT_OPT_PREFIX, this.A02.getAndIncrement());
        } else {
            str = this.A01;
        }
        return new Thread(r3, str);
    }
}
