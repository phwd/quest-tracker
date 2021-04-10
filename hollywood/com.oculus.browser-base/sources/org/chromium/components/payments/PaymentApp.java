package org.chromium.components.payments;

import android.graphics.drawable.Drawable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class PaymentApp extends C1997cK {
    public boolean k;

    public PaymentApp(String str, String str2, String str3, Drawable drawable) {
        super(str, str2, str3, null, drawable);
    }

    public boolean A() {
        return true;
    }

    public boolean B(String str, C1401Wz0 wz0) {
        return p().contains(str);
    }

    public boolean C() {
        return false;
    }

    public void D() {
    }

    public void E(PaymentHandlerHost paymentHandlerHost) {
    }

    public void F(C4530rA0 ra0) {
    }

    public void g(AbstractC1216Ty0 ty0) {
        PostTask.b(Zo1.f9374a, new RunnableC1155Sy0(this, ty0), 0);
    }

    public String h() {
        return null;
    }

    public boolean i() {
        return true;
    }

    public boolean j() {
        return true;
    }

    public void k() {
    }

    public abstract void l();

    public String m() {
        return null;
    }

    public Set n() {
        return null;
    }

    public String o() {
        return null;
    }

    public abstract Set p();

    public int q() {
        return 0;
    }

    public long r() {
        return 0;
    }

    public boolean s() {
        return false;
    }

    public boolean t() {
        return false;
    }

    public boolean u() {
        return false;
    }

    public boolean v() {
        return false;
    }

    public void w(String str, String str2, String str3, String str4, byte[][] bArr, Map map, C1035Qz0 qz0, List list, Map map2, C1523Yz0 yz0, List list2, AbstractC1277Uy0 uy0) {
    }

    public boolean x() {
        return false;
    }

    public boolean y() {
        return false;
    }

    public boolean z() {
        return false;
    }
}
