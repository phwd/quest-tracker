package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: MB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MB extends C2003cN {
    public final String e;
    public final YM f;
    public final Is1 g;

    public MB(Tab tab, YM ym, Is1 is1, int i) {
        super(tab);
        this.e = T51.j(tab).G;
        this.f = ym;
        this.g = is1;
    }

    @Override // defpackage.C2003cN, defpackage.AbstractC1652aN
    public void a(Intent intent) {
    }

    @Override // defpackage.C2003cN, defpackage.AbstractC1652aN
    public boolean b(Intent intent) {
        if (TextUtils.isEmpty(this.e)) {
            return false;
        }
        this.f.b(this.e);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A[Catch:{ SecurityException -> 0x0064, RuntimeException -> 0x0046 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004e A[Catch:{ SecurityException -> 0x0064, RuntimeException -> 0x0046 }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002d A[SYNTHETIC, Splitter:B:9:0x002d] */
    @Override // defpackage.C2003cN, defpackage.AbstractC1652aN
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int c(android.content.Intent r7, boolean r8) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.MB.c(android.content.Intent, boolean):int");
    }

    @Override // defpackage.C2003cN, defpackage.AbstractC1652aN
    public boolean d(String str) {
        Is1 is1 = this.g;
        return is1 != null && is1.a(str);
    }
}
