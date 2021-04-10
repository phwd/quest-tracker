package defpackage;

import J.N;
import android.os.Debug;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import java.util.Objects;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.base.task.PostTask;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* renamed from: Zo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1561Zo implements Runnable {
    public final /* synthetic */ C1732ap F;

    public RunnableC1561Zo(C1732ap apVar) {
        this.F = apVar;
    }

    public void run() {
        String[] strArr;
        C1732ap apVar;
        FileDescriptorInfo[] fileDescriptorInfoArr;
        try {
            synchronized (this.F.l) {
                while (true) {
                    C1732ap apVar2 = this.F;
                    strArr = apVar2.m;
                    if (strArr == null) {
                        apVar2.l.wait();
                    }
                }
            }
            AbstractC1575Zv.h(strArr);
            if (AbstractC1575Zv.e().g("renderer-wait-for-java-debugger")) {
                Debug.waitForDebugger();
            }
            C1732ap apVar3 = this.F;
            ((ContentChildProcessServiceDelegate) apVar3.d).b(apVar3.f);
            synchronized (this.F.h) {
                C1732ap apVar4 = this.F;
                apVar4.o = true;
                apVar4.h.notifyAll();
            }
            synchronized (this.F.l) {
                this.F.l.notifyAll();
                while (true) {
                    apVar = this.F;
                    fileDescriptorInfoArr = apVar.n;
                    if (fileDescriptorInfoArr != null) {
                        break;
                    }
                    apVar.l.wait();
                }
            }
            SparseArray sparseArray = ((ContentChildProcessServiceDelegate) apVar.d).d;
            int[] iArr = new int[fileDescriptorInfoArr.length];
            String[] strArr2 = new String[fileDescriptorInfoArr.length];
            int[] iArr2 = new int[fileDescriptorInfoArr.length];
            long[] jArr = new long[fileDescriptorInfoArr.length];
            long[] jArr2 = new long[fileDescriptorInfoArr.length];
            int i = 0;
            while (true) {
                FileDescriptorInfo[] fileDescriptorInfoArr2 = this.F.n;
                if (i >= fileDescriptorInfoArr2.length) {
                    break;
                }
                FileDescriptorInfo fileDescriptorInfo = fileDescriptorInfoArr2[i];
                String str = sparseArray != null ? (String) sparseArray.get(fileDescriptorInfo.F) : null;
                if (str != null) {
                    strArr2[i] = str;
                } else {
                    iArr[i] = fileDescriptorInfo.F;
                }
                iArr2[i] = fileDescriptorInfo.G.detachFd();
                jArr[i] = fileDescriptorInfo.H;
                jArr2[i] = fileDescriptorInfo.I;
                i++;
            }
            N.Ma6rsNQO(strArr2, iArr, iArr2, jArr, jArr2);
            ContentChildProcessServiceDelegate contentChildProcessServiceDelegate = (ContentChildProcessServiceDelegate) this.F.d;
            N.MBlO3kR9(contentChildProcessServiceDelegate, contentChildProcessServiceDelegate.b, contentChildProcessServiceDelegate.c);
            PostTask.b(Zo1.f9374a, new RunnableC0667Ky(), 0);
            long uptimeMillis = SystemClock.uptimeMillis() - Process.getStartUptimeMillis();
            String str2 = Process.isIsolated() ? ".Isolated" : ".NotIsolated";
            AbstractC3364kK0.j("Android.ChildProcessStartTimeV2.All", uptimeMillis);
            AbstractC3364kK0.j("Android.ChildProcessStartTimeV2" + str2, uptimeMillis);
            Objects.requireNonNull((ContentChildProcessServiceDelegate) this.F.d);
            N.M1Y_XVCN(false);
            try {
                this.F.q.a();
            } catch (RemoteException e) {
                AbstractC1220Ua0.a("ChildProcessService", "Failed to call clean exit callback.", e);
            }
            N.McvJWQ0j();
        } catch (Throwable th) {
            try {
                this.F.q.j0(C1732ap.class.getName() + "\n" + Log.getStackTraceString(th));
            } catch (RemoteException e2) {
                AbstractC1220Ua0.a("ChildProcessService", "Failed to call reportExceptionInInit.", e2);
            }
            throw new RuntimeException(th);
        }
    }
}
