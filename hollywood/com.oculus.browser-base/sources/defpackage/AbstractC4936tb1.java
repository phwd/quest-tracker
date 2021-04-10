package defpackage;

import java.io.File;

/* renamed from: tb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4936tb1 {
    public void a(int i, boolean z) {
        File a2 = AbstractC1224Ub1.a(b(), i, z);
        if (a2.exists() && !a2.delete()) {
            AbstractC1220Ua0.a("TabState", AbstractC2531fV.e("Failed to delete TabState: ", a2), new Object[0]);
        }
    }

    public abstract File b();
}
