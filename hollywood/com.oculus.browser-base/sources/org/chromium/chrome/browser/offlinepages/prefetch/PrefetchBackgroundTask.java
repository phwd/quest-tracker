package org.chromium.chrome.browser.offlinepages.prefetch;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrefetchBackgroundTask extends AbstractC4798sm0 {
    public long f;
    public AbstractC0804Ne g;
    public boolean h = true;
    public boolean i;

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        if (this.i) {
            PrefetchBackgroundTaskScheduler.scheduleTaskLimitless(0);
        } else {
            PrefetchBackgroundTaskScheduler.scheduleTask(0);
        }
    }

    public void doneProcessing(boolean z) {
        this.h = z;
        this.g.a(z);
        setNativeTask(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0046 A[RETURN] */
    @Override // defpackage.AbstractC4798sm0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int e(android.content.Context r3, defpackage.C2046cf1 r4, defpackage.AbstractC0804Ne r5) {
        /*
            r2 = this;
            r2.g = r5
            android.os.Bundle r4 = r4.b
            java.lang.String r5 = "limitlessPrefetching"
            boolean r4 = r4.getBoolean(r5)
            r2.i = r4
            vE r3 = defpackage.C5222vE.b(r3)
            r4 = 0
            if (r3 == 0) goto L_0x0046
            boolean r5 = r3.c
            r0 = 1
            if (r5 != 0) goto L_0x0022
            boolean r5 = r3.f11469a
            if (r5 != 0) goto L_0x0026
            int r5 = r3.b
            r1 = 50
            if (r5 >= r1) goto L_0x0026
        L_0x0022:
            boolean r5 = r2.i
            if (r5 == 0) goto L_0x0028
        L_0x0026:
            r5 = r0
            goto L_0x0029
        L_0x0028:
            r5 = r4
        L_0x0029:
            if (r5 == 0) goto L_0x0045
            boolean r5 = r2.i
            if (r5 == 0) goto L_0x0038
            int r3 = r3.d
            r5 = 6
            if (r3 == r5) goto L_0x0036
        L_0x0034:
            r3 = r0
            goto L_0x0042
        L_0x0036:
            r3 = r4
            goto L_0x0042
        L_0x0038:
            boolean r5 = r3.e
            if (r5 != 0) goto L_0x0036
            int r3 = r3.d
            r5 = 2
            if (r3 != r5) goto L_0x0036
            goto L_0x0034
        L_0x0042:
            if (r3 == 0) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            return r0
        L_0x0046:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.offlinepages.prefetch.PrefetchBackgroundTask.e(android.content.Context, cf1, Ne):int");
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        if (this.f == 0) {
            N.M_yiIXuu(this);
        }
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return this.h;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        long j = this.f;
        if (j == 0) {
            return this.h;
        }
        return N.MgpkLR_Z(j, this);
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean j() {
        return CachedFeatureFlags.isEnabled("ServiceManagerForBackgroundPrefetch");
    }

    public void setNativeTask(long j) {
        this.f = j;
    }
}
