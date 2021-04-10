package X;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class ME {
    public static final ThreadLocal<ME> A01 = new MD();
    public final StringBuilder A00 = new StringBuilder(20);
}
