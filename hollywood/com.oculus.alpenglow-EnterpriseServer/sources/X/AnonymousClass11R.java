package X;

import android.app.Service;
import android.content.Intent;
import android.os.Looper;
import com.facebook.rti.push.service.FbnsService;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.11R  reason: invalid class name */
public abstract class AnonymousClass11R extends Service {
    public boolean A00;
    public final Object A01 = new Object();
    public volatile AnonymousClass11Q A02;

    public abstract void A0D();

    public final void onStart(Intent intent, int i) {
        onStartCommand(intent, -1, i);
    }

    public final void A0B() {
        String str;
        synchronized (this.A01) {
            if (!this.A00) {
                AbstractServiceC08280wx r2 = (AbstractServiceC08280wx) this;
                boolean z = false;
                if (r2.A0A == null) {
                    z = true;
                }
                C08170wh.A01(z);
                r2.A0A = r2.A0F();
                r2.A0H();
                r2.A0G();
                r2.A01.A8B(new C09580zh(r2));
                AbstractServiceC08280wx.A0A(r2, "doCreate");
                AnonymousClass0x2 r4 = r2.A0E;
                String A05 = AnonymousClass006.A05(AnonymousClass0vG.A00(AnonymousClass007.A01), ".SERVICE_CREATE");
                if (!(r2 instanceof FbnsService)) {
                    str = "N/A";
                } else {
                    str = "FBNS_ALWAYS";
                }
                C09340zG r8 = C09340zG.A00;
                r4.A06(A05, str, null, r8, r8, r2.A0B.get(), r2.A06.A06.get(), r2.A06.A02());
                this.A00 = true;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01b7, code lost:
        if (r1 != null) goto L_0x0010;
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0C(android.content.Intent r18, int r19, int r20) {
        /*
        // Method dump skipped, instructions count: 518
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass11R.A0C(android.content.Intent, int, int):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0012 A[LOOP:0: B:5:0x0012->B:17:0x0012, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy() {
        /*
            r2 = this;
            X.11Q r1 = r2.A02
            boolean r0 = r1 instanceof X.AnonymousClass11U
            if (r0 != 0) goto L_0x001d
            r0 = 3
            android.os.Message r0 = r1.obtainMessage(r0)
            boolean r0 = r1.sendMessage(r0)
            if (r0 == 0) goto L_0x0026
            monitor-enter(r1)
        L_0x0012:
            boolean r0 = r1.A00     // Catch:{ all -> 0x001a }
            if (r0 != 0) goto L_0x0025
            r1.wait()     // Catch:{ InterruptedException -> 0x0012 }
            goto L_0x0012
        L_0x001a:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x001d:
            X.11U r1 = (X.AnonymousClass11U) r1
            X.11R r0 = r1.A00
            r0.A0D()
            goto L_0x0026
        L_0x0025:
            monitor-exit(r1)
        L_0x0026:
            super.onDestroy()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass11R.onDestroy():void");
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        AnonymousClass11Q r1 = this.A02;
        if (!(r1 instanceof AnonymousClass11U)) {
            r1.sendMessage(r1.obtainMessage(2, i, i2, intent));
            return 1;
        }
        ((AnonymousClass11U) r1).A00.A0C(intent, i, i2);
        return 1;
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        A0B();
        A0E(fileDescriptor, printWriter, strArr);
    }

    public void onCreate() {
        super.onCreate();
        this.A02 = new AnonymousClass11U(this, Looper.getMainLooper());
        AnonymousClass11Q r1 = this.A02;
        if (!(r1 instanceof AnonymousClass11U)) {
            r1.sendMessage(r1.obtainMessage(1));
        } else {
            ((AnonymousClass11U) r1).A00.A0B();
        }
    }

    public void A0E(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
    }
}
