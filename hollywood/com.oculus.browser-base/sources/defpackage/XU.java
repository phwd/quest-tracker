package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import java.util.List;

/* renamed from: XU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class XU implements Runnable {
    public final String F;
    public final Bundle G;
    public final List H;
    public final long I;

    /* renamed from: J  reason: collision with root package name */
    public final MG1 f9208J;
    public final Messenger K;
    public final /* synthetic */ YU L;

    public XU(YU yu, String str, IBinder iBinder, Bundle bundle, long j, List list) {
        MG1 mg1;
        this.L = yu;
        this.F = str;
        if (iBinder == null) {
            mg1 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.gcm.INetworkTaskCallback");
            if (queryLocalInterface instanceof MG1) {
                mg1 = (MG1) queryLocalInterface;
            } else {
                mg1 = new MG1(iBinder);
            }
        }
        this.f9208J = mg1;
        this.G = bundle;
        this.I = j;
        this.H = list;
        this.K = null;
    }

    public final void a(int i) {
        synchronized (this.L.F) {
            try {
                YU yu = this.L;
                if (yu.K.d(this.F, yu.f9275J.getClassName())) {
                    YU yu2 = this.L;
                    yu2.K.b(this.F, yu2.f9275J.getClassName());
                    if (!b()) {
                        YU yu3 = this.L;
                        if (!yu3.K.c(yu3.f9275J.getClassName())) {
                            YU yu4 = this.L;
                            yu4.stopSelf(yu4.G);
                        }
                    }
                    return;
                }
                if (b()) {
                    Messenger messenger = this.K;
                    Message obtain = Message.obtain();
                    obtain.what = 3;
                    obtain.arg1 = i;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("component", this.L.f9275J);
                    bundle.putString("tag", this.F);
                    obtain.setData(bundle);
                    messenger.send(obtain);
                } else {
                    this.f9208J.c(i);
                }
                YU yu5 = this.L;
                yu5.K.b(this.F, yu5.f9275J.getClassName());
                if (!b()) {
                    YU yu6 = this.L;
                    if (!yu6.K.c(yu6.f9275J.getClassName())) {
                        YU yu7 = this.L;
                        yu7.stopSelf(yu7.G);
                    }
                }
            } catch (RemoteException unused) {
                String valueOf = String.valueOf(this.F);
                Log.e("GcmTaskService", valueOf.length() != 0 ? "Error reporting result of operation to scheduler for ".concat(valueOf) : new String("Error reporting result of operation to scheduler for "));
                YU yu8 = this.L;
                yu8.K.b(this.F, yu8.f9275J.getClassName());
                if (!b()) {
                    YU yu9 = this.L;
                    if (!yu9.K.c(yu9.f9275J.getClassName())) {
                        YU yu10 = this.L;
                        yu10.stopSelf(yu10.G);
                    }
                }
            } catch (Throwable th) {
                YU yu11 = this.L;
                yu11.K.b(this.F, yu11.f9275J.getClassName());
                if (!b()) {
                    YU yu12 = this.L;
                    if (!yu12.K.c(yu12.f9275J.getClassName())) {
                        YU yu13 = this.L;
                        yu13.stopSelf(yu13.G);
                    }
                }
                throw th;
            }
        }
    }

    public final boolean b() {
        return this.K != null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
        defpackage.AbstractC3701mI1.f10413a.a(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0050, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r8 = this;
            gI1 r0 = new gI1
            java.lang.String r1 = r8.F
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            java.lang.String r3 = "nts:client:onRunTask:"
            if (r2 == 0) goto L_0x0015
            java.lang.String r1 = r3.concat(r1)
            goto L_0x001a
        L_0x0015:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r3)
        L_0x001a:
            r0.<init>(r1)
            df1 r1 = new df1     // Catch:{ all -> 0x0043 }
            java.lang.String r3 = r8.F     // Catch:{ all -> 0x0043 }
            android.os.Bundle r4 = r8.G     // Catch:{ all -> 0x0043 }
            long r5 = r8.I     // Catch:{ all -> 0x0043 }
            java.util.List r7 = r8.H     // Catch:{ all -> 0x0043 }
            r2 = r1
            r2.<init>(r3, r4, r5, r7)     // Catch:{ all -> 0x0043 }
            YU r2 = r8.L     // Catch:{ all -> 0x0043 }
            XH1 r2 = r2.L     // Catch:{ all -> 0x0043 }
            java.util.Objects.requireNonNull(r2)     // Catch:{ all -> 0x0043 }
            YU r2 = r8.L     // Catch:{ all -> 0x003f }
            int r1 = r2.b(r1)     // Catch:{ all -> 0x003f }
            r8.a(r1)
            r0.close()
            return
        L_0x003f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r1 = move-exception
            throw r1
        L_0x0043:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x004a }
            goto L_0x0050
        L_0x004a:
            r0 = move-exception
            uI1 r3 = defpackage.AbstractC3701mI1.f10413a
            r3.a(r1, r0)
        L_0x0050:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.XU.run():void");
    }

    public XU(YU yu, String str, Messenger messenger, Bundle bundle, long j, List list) {
        this.L = yu;
        this.F = str;
        this.K = messenger;
        this.G = bundle;
        this.I = j;
        this.H = list;
        this.f9208J = null;
    }
}
