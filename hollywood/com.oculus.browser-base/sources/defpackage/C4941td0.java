package defpackage;

import android.content.Intent;
import android.os.IBinder;
import android.util.SparseIntArray;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ContextUtils;

/* renamed from: td0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4941td0 extends AbstractC1677aZ0 {
    public AbstractC0711Lp0 b;
    public PU0 c;
    public final SparseIntArray d = new SparseIntArray();

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0045 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void i(android.content.Context r5, int r6, org.chromium.content_public.browser.WebContents r7, org.chromium.url.GURL r8) {
        /*
        // Method dump skipped, instructions count: 132
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4941td0.i(android.content.Context, int, org.chromium.content_public.browser.WebContents, org.chromium.url.GURL):void");
    }

    @Override // defpackage.AbstractC1677aZ0
    public IBinder a(Intent intent) {
        return null;
    }

    @Override // defpackage.AbstractC1677aZ0
    public void b() {
        this.b = new C0771Mp0(ContextUtils.getApplicationContext());
        this.c = NU0.f8549a;
    }

    @Override // defpackage.AbstractC1677aZ0
    public void c() {
        h();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        if ((r16.d.get(r4) != r7) == false) goto L_0x019f;
     */
    @Override // defpackage.AbstractC1677aZ0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int e(android.content.Intent r17, int r18, int r19) {
        /*
        // Method dump skipped, instructions count: 424
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4941td0.e(android.content.Intent, int, int):int");
    }

    @Override // defpackage.AbstractC1677aZ0
    public boolean g(Intent intent) {
        h();
        return AbstractServiceC1857bZ0.b(this.f9437a, intent);
    }

    public final void h() {
        Set<String> k = this.c.k("WebRTCNotificationIds", null);
        if (k != null) {
            for (String str : k) {
                AbstractC0711Lp0 lp0 = this.b;
                ((C0771Mp0) lp0).b.cancel("MediaCaptureNotificationService", Integer.parseInt(str));
            }
            this.c.l("WebRTCNotificationIds");
        }
    }

    public final void j(int i, boolean z) {
        HashSet hashSet = new HashSet(this.c.k("WebRTCNotificationIds", new HashSet()));
        if (z && !hashSet.isEmpty() && hashSet.contains(String.valueOf(i))) {
            hashSet.remove(String.valueOf(i));
        } else if (!z) {
            hashSet.add(String.valueOf(i));
        }
        this.c.q("WebRTCNotificationIds", hashSet);
    }
}
