package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.components.crash.CrashKeys;

/* renamed from: ea  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2370ea implements AbstractC1678aa {

    /* renamed from: a  reason: collision with root package name */
    public String f9860a;

    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        Object obj = ThreadUtils.f10596a;
        boolean z = true;
        if (!(i == 1 || i == 2)) {
            z = false;
        }
        String str = z ? "app_foreground" : "app_background";
        if (!str.equals(this.f9860a)) {
            this.f9860a = str;
            CrashKeys.getInstance().set(2, str);
        }
    }
}
