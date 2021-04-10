package defpackage;

import android.text.TextUtils;
import android.util.Pair;
import java.util.Map;

/* renamed from: sP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4739sP extends AbstractC5079uP {

    /* renamed from: a  reason: collision with root package name */
    public final String f11270a;

    public C4739sP(String str) {
        this.f11270a = str;
    }

    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        if (TextUtils.isEmpty(this.f11270a)) {
            return null;
        }
        return AbstractC0417Gv.c(Pair.create("Feedback Context", this.f11270a));
    }
}
