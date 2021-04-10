package defpackage;

import J.N;
import android.os.Binder;
import android.os.Process;
import android.text.TextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: Yo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BinderC1500Yo extends AbstractBinderC5097uY {
    public final /* synthetic */ C1732ap b;

    public BinderC1500Yo(C1732ap apVar) {
        this.b = apVar;
    }

    @Override // defpackage.AbstractC5267vY
    public boolean J(String str) {
        synchronized (this.b.g) {
            int callingPid = Binder.getCallingPid();
            C1732ap apVar = this.b;
            int i = apVar.j;
            if (i == 0 && apVar.k == null) {
                apVar.j = callingPid;
                apVar.k = str;
            } else if (i != callingPid) {
                AbstractC1220Ua0.a("ChildProcessService", "Service is already bound by pid %d, cannot bind for pid %d", Integer.valueOf(i), Integer.valueOf(callingPid));
                return false;
            } else if (!TextUtils.equals(apVar.k, str)) {
                AbstractC1220Ua0.f("ChildProcessService", "Service is already bound by %s, cannot bind for %s", this.b.k, str);
                return false;
            }
            return true;
        }
    }

    @Override // defpackage.AbstractC5267vY
    public void p0(int i) {
        ThreadUtils.d(new RunnableC1439Xo(i));
    }

    @Override // defpackage.AbstractC5267vY
    public void q0() {
        Process.killProcess(Process.myPid());
    }

    @Override // defpackage.AbstractC5267vY
    public void w() {
        synchronized (this.b.h) {
            if (!this.b.o) {
                AbstractC1220Ua0.a("ChildProcessService", "Cannot dump process stack before native is loaded", new Object[0]);
            } else {
                N.M6Y7Jzgj();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        r7.N(android.os.Process.myPid());
        r0 = defpackage.C1732ap.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r0 == 0) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r7.j(r0, defpackage.C1732ap.c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        r0 = r5.b;
        r0.q = r7;
        r6.setClassLoader(r0.f.getClassLoader());
        r7 = r0.l;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        if (r0.m != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r0.m = r6.getStringArray("org.chromium.base.process_launcher.extra.command_line");
        r0.l.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0051, code lost:
        r1 = r6.getParcelableArray("org.chromium.base.process_launcher.extra.extraFiles");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        if (r1 == null) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        r2 = new org.chromium.base.process_launcher.FileDescriptorInfo[r1.length];
        r0.n = r2;
        java.lang.System.arraycopy(r1, 0, r2, 0, r1.length);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0064, code lost:
        ((org.chromium.content.app.ContentChildProcessServiceDelegate) r0.d).c(r6, r8);
        r0.l.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006e, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006f, code lost:
        return;
     */
    @Override // defpackage.AbstractC5267vY
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void y(android.os.Bundle r6, defpackage.AbstractC2198dZ r7, java.util.List r8) {
        /*
        // Method dump skipped, instructions count: 118
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.BinderC1500Yo.y(android.os.Bundle, dZ, java.util.List):void");
    }
}
