package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: Ay1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ay1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C6025zy1 f7710a;
    public final /* synthetic */ By1 b;

    public Ay1(By1 by1, C6025zy1 zy1) {
        this.b = by1;
        this.f7710a = zy1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Iterator it = ((ArrayList) obj).iterator();
        while (it.hasNext()) {
            I21 i21 = (I21) it.next();
            String str = i21.F;
            if (str != null) {
                this.b.f7776a.d(str, null).K.add(i21);
            }
        }
        this.f7710a.a();
    }
}
