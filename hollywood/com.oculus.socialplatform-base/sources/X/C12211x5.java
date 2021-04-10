package X;

import java.util.concurrent.ThreadFactory;

/* renamed from: X.1x5  reason: invalid class name and case insensitive filesystem */
public final class C12211x5 extends AbstractC12361xL {
    public static final ThreadFactoryC06661ap A01 = new ThreadFactoryC06661ap("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())), false);
    public final ThreadFactory A00 = A01;
}
