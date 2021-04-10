package defpackage;

import J.N;
import java.util.Iterator;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: A00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A00 extends P00 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0124Ca1 f7648a;

    public A00(AbstractC0124Ca1 ca1) {
        this.f7648a = ca1;
    }

    @Override // defpackage.P00
    public void a() {
        boolean z;
        Profile b;
        if (!Z00.a()) {
            Iterator it = K00.a().b.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((J00) it.next()).isActiveModel()) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z && (b = ((AbstractC0246Ea1) this.f7648a).l(true).b()) != null) {
                N.MScIZBOB(b.b, b);
            }
        }
    }
}
