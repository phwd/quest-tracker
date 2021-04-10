package X;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1co  reason: invalid class name and case insensitive filesystem */
public final class C07661co {
    public int A00;
    public int A01;
    public String A02;
    @NonNull
    public AbstractC08931fl A03 = AbstractC08931fl.A00;
    public final boolean A04;

    public final ExecutorServiceC07671cp A00() {
        if (!TextUtils.isEmpty(this.A02)) {
            return new ExecutorServiceC07671cp(new ThreadPoolExecutor(this.A00, this.A01, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new ThreadFactoryC07721cu(this.A02, this.A03, this.A04)));
        }
        throw new IllegalArgumentException(AnonymousClass006.A07("Name must be non-null and non-empty, but given: ", this.A02));
    }

    public C07661co(boolean z) {
        this.A04 = z;
    }
}
