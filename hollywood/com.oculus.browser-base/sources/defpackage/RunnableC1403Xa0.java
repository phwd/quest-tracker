package defpackage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl;

/* renamed from: Xa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1403Xa0 implements Runnable {
    public final File F;

    public RunnableC1403Xa0(File file) {
        this.F = file;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x0074 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.List, java.util.LinkedList] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.util.List] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List a() {
        /*
        // Method dump skipped, instructions count: 253
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.RunnableC1403Xa0.a():java.util.List");
    }

    public void b(boolean z) {
        AbstractC1220Ua0.d("LogcatExtraction", "Trying to extract logcat for minidump %s.", this.F.getName());
        C1619aB aBVar = new C1619aB(ContextUtils.getApplicationContext().getCacheDir());
        File file = this.F;
        try {
            file = new C0091Bk0(aBVar, this.F, a()).b();
            AbstractC1220Ua0.d("LogcatExtraction", "Succeeded extracting logcat to %s.", file.getName());
        } catch (IOException | InterruptedException e) {
            AbstractC1220Ua0.f("LogcatExtraction", e.toString(), new Object[0]);
        }
        if (z) {
            try {
                MinidumpUploadServiceImpl.e(file);
            } catch (SecurityException e2) {
                AbstractC1220Ua0.f("LogcatExtraction", e2.toString(), new Object[0]);
                if (!z) {
                    throw e2;
                }
            }
        } else {
            AtomicBoolean atomicBoolean = MinidumpUploadServiceImpl.b;
            MinidumpUploadServiceImpl.d();
        }
    }

    public void run() {
        b(false);
    }
}
