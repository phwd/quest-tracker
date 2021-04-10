package defpackage;

import android.content.Intent;
import org.chromium.base.task.PostTask;

/* renamed from: Vp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1320Vp0 extends XY0 {
    public static final String b = "Vp0";

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(android.content.Intent r17) {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1320Vp0.c(android.content.Intent):void");
    }

    @Override // defpackage.XY0
    public void a(Intent intent) {
        if (intent.hasExtra("notification_id") && intent.hasExtra("notification_info_origin")) {
            PostTask.c(Zo1.f9374a, new RunnableC1259Up0(intent));
        }
    }
}
