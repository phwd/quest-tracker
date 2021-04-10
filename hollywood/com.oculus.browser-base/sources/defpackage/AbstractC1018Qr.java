package defpackage;

import android.content.Intent;
import android.os.IBinder;

/* renamed from: Qr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1018Qr extends AbstractC1677aZ0 {
    public int b;

    public AbstractC1018Qr(int i) {
        this.b = i;
    }

    @Override // defpackage.AbstractC1677aZ0
    public IBinder a(Intent intent) {
        return null;
    }

    @Override // defpackage.AbstractC1677aZ0
    public void c() {
        C5624xe0 a2 = AbstractC0196De0.a(this.b);
        if (a2 != null) {
            a2.f11621a = null;
        }
        int i = this.b;
        C5624xe0 a3 = AbstractC0196De0.a(i);
        if (a3 != null) {
            a3.b();
            AbstractC0196De0.f7901a.remove(i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c0  */
    @Override // defpackage.AbstractC1677aZ0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int e(android.content.Intent r6, int r7, int r8) {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1018Qr.e(android.content.Intent, int, int):int");
    }
}
