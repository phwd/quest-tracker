package X;

import androidx.annotation.NonNull;
import java.util.concurrent.ThreadFactory;

/* renamed from: X.1cu  reason: invalid class name and case insensitive filesystem */
public final class ThreadFactoryC07721cu implements ThreadFactory {
    public int A00;
    public final AbstractC08931fl A01;
    public final boolean A02;
    public final String A03;

    public final synchronized Thread newThread(@NonNull Runnable runnable) {
        C07711ct r1;
        r1 = new C07711ct(this, runnable, AnonymousClass006.A0A("glide-", this.A03, "-thread-", this.A00));
        this.A00++;
        return r1;
    }

    public ThreadFactoryC07721cu(String str, AbstractC08931fl r2, boolean z) {
        this.A03 = str;
        this.A01 = r2;
        this.A02 = z;
    }
}
