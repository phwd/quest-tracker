package defpackage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: iu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3113iu extends ThreadPoolExecutor {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10170a;
    public static final int b;
    public static final int c;
    public static final ThreadFactory d = new ThreadFactoryC2943hu();
    public static final BlockingQueue e = new ArrayBlockingQueue(128);

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f10170a = availableProcessors;
        b = Math.max(2, Math.min(availableProcessors - 1, 4));
        c = (availableProcessors * 2) + 1;
    }

    public C3113iu() {
        super(b, c, 30, TimeUnit.SECONDS, e, d);
        allowCoreThreadTimeOut(true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(java.lang.Runnable r11) {
        /*
        // Method dump skipped, instructions count: 191
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3113iu.execute(java.lang.Runnable):void");
    }
}
