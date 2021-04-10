package X;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class PE {
    public static final ThreadLocal<PE> A01 = new PD();
    public byte A00 = 0;
}
