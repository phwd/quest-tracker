package defpackage;

import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* renamed from: uq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5151uq1 extends AbstractC2032cb {
    public final Context i = ContextUtils.getApplicationContext();
    public final Runnable j;
    public C5321vq1 k;

    public C5151uq1(Runnable runnable) {
        this.j = runnable;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x018b, code lost:
        if (r0 == false) goto L_0x018e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x017f A[SYNTHETIC, Splitter:B:76:0x017f] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0197  */
    @Override // defpackage.AbstractC2032cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c() {
        /*
        // Method dump skipped, instructions count: 488
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5151uq1.c():java.lang.Object");
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.k = (C5321vq1) obj;
        PostTask.b(Zo1.f9374a, this.j, 0);
    }
}
