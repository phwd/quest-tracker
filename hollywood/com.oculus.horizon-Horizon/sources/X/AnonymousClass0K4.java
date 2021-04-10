package X;

import android.annotation.SuppressLint;
import android.os.Process;
import android.util.Log;
import com.facebook.common.internal.DoNotStrip;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0K4  reason: invalid class name */
public final class AnonymousClass0K4 implements Thread.UncaughtExceptionHandler {
    @Nullable
    public static AnonymousClass0K4 A04;
    public final boolean A00 = true;
    public final Object A01 = new Object();
    public final Thread.UncaughtExceptionHandler A02;
    public volatile List<AnonymousClass0K3> A03 = Collections.unmodifiableList(new ArrayList());
    @DoNotStrip
    @Nullable
    public byte[] mOomReservation = null;

    public AnonymousClass0K4(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.A02 = uncaughtExceptionHandler;
        this.mOomReservation = new byte[4096];
    }

    public static synchronized AnonymousClass0K4 A00() {
        AnonymousClass0K4 r0;
        synchronized (AnonymousClass0K4.class) {
            r0 = A04;
            if (r0 == null) {
                AnonymousClass0K4 r02 = new AnonymousClass0K4(Thread.getDefaultUncaughtExceptionHandler());
                A04 = r02;
                Thread.setDefaultUncaughtExceptionHandler(r02);
                r0 = A04;
            }
        }
        return r0;
    }

    public static synchronized void A02(AnonymousClass0K5 r4, int i) {
        synchronized (AnonymousClass0K4.class) {
            AnonymousClass0K4 A002 = A00();
            synchronized (A002) {
                ArrayList arrayList = new ArrayList(A002.A03);
                AnonymousClass0K3 r0 = new AnonymousClass0K3();
                r0.A01 = r4;
                r0.A00 = i;
                arrayList.add(r0);
                if (A002.A00) {
                    Collections.sort(arrayList);
                }
                A002.A03 = Collections.unmodifiableList(arrayList);
            }
        }
    }

    @SuppressLint({"CatchGeneralException"})
    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (this.A01) {
            try {
                Process.setThreadPriority(-10);
            } catch (Exception unused) {
            }
            this.mOomReservation = null;
            List<AnonymousClass0K3> list = this.A03;
            try {
                for (int size = list.size() - 1; size >= 0; size--) {
                    try {
                        list.get(size).A01.handleUncaughtException(thread, th, null);
                    } catch (Throwable th2) {
                        if (th instanceof OutOfMemoryError) {
                            Log.e("ExceptionHandlerManager", "Error during handling OOM");
                        } else {
                            Log.e("ExceptionHandlerManager", "Error during exception handling", th2);
                        }
                    }
                }
                try {
                    A03(th);
                    this.A02.uncaughtException(thread, th);
                } catch (Throwable th3) {
                    A01();
                    throw th3;
                }
                A01();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } catch (Throwable th4) {
                Log.e("ExceptionHandlerManager", "Error during exception handling", th4);
            }
        }
        A01();
        throw th;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed compute block dominance frontier
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominanceFrontier(BlockProcessor.java:300)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:77)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        Caused by: java.lang.IndexOutOfBoundsException: Index: 9, Size: 9
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeBlockDF(BlockProcessor.java:325)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominanceFrontier(BlockProcessor.java:298)
        	... 2 more
        */
    public static void A01() {
        /*
            int r0 = android.os.Process.myPid()     // Catch:{ all -> 0x0008 }
            android.os.Process.killProcess(r0)     // Catch:{ all -> 0x0008 }
            goto L_0x0010
        L_0x0008:
            r2 = move-exception
            java.lang.String r1 = "ExceptionHandlerManager"
            java.lang.String r0 = "Error during exception handling"
            android.util.Log.e(r1, r0, r2)
        L_0x0010:
            r0 = 10
            java.lang.System.exit(r0)     // Catch:{ all -> 0x0016 }
            goto L_0x001e
        L_0x0016:
            r2 = move-exception
            java.lang.String r1 = "ExceptionHandlerManager"
            java.lang.String r0 = "Error during exception handling"
            android.util.Log.e(r1, r0, r2)
        L_0x001e:
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0K4.A01():void");
    }

    public static void A03(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i = length + 1;
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[i];
        System.arraycopy(stackTrace, 0, stackTraceElementArr, 0, length);
        stackTraceElementArr[i - 1] = new StackTraceElement("Z", "init", "unknown", -1);
        th.setStackTrace(stackTraceElementArr);
    }
}
