package defpackage;

import java.util.List;

/* renamed from: fb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2546fb1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4766sb1 f9931a;

    public C2546fb1(C4766sb1 sb1) {
        this.f9931a = sb1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        List list = (List) obj;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                this.f9931a.f((String) list.get(i), true);
            }
        }
    }
}
