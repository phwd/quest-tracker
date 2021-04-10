package defpackage;

import android.text.TextUtils;
import android.util.Pair;
import java.util.Map;

/* renamed from: Zq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Zq1 extends AbstractC5079uP {

    /* renamed from: a  reason: collision with root package name */
    public final String f9376a;

    public Zq1(String str) {
        this.f9376a = str;
    }

    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        if (TextUtils.isEmpty(this.f9376a)) {
            return null;
        }
        return AbstractC0417Gv.c(Pair.create("URL", this.f9376a));
    }
}
