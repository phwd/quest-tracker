package defpackage;

import java.util.HashMap;
import java.util.Map;

/* renamed from: ty1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5005ty1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C6025zy1 f11378a;
    public final /* synthetic */ C5175uy1 b;

    public C5005ty1(C5175uy1 uy1, C6025zy1 zy1) {
        this.b = uy1;
        this.f11378a = zy1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        for (Map.Entry entry : ((HashMap) obj).entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                this.b.f11450a.d(str, null).f10318J = (V90) entry.getValue();
            }
        }
        this.f11378a.a();
    }
}
