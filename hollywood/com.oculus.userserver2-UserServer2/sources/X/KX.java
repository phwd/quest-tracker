package X;

import com.facebook.acra.ErrorReporter;
import com.facebook.common.objectpool.ObjectPool;

public final class KX<T> {
    public int A00 = 16;
    public int A01 = ErrorReporter.SIGQUIT_PROCESS_NAME_BUFFER_SIZE;
    public int A02 = 16;
    public long A03 = 60000;
    public ObjectPool.Allocator<T> A04;
    public Kt A05;
    public Class<T> A06;

    public KX(Class<T> cls, Kt kt) {
        this.A06 = cls;
        this.A05 = kt;
    }
}
