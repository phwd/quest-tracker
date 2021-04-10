package defpackage;

import java.io.File;
import java.util.LinkedList;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* renamed from: TP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TP implements AbstractC3511lC0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Callback f8956a = new HP();
    public BS0 b = PostTask.a(C3070if1.f);
    public LinkedList c = new LinkedList();

    public static File e(int i, String str) {
        return new File(IP.f8224a, String.format(Locale.ENGLISH, "%d%s", Integer.valueOf(i), str));
    }

    public static Boolean f(int i) {
        P21 f0 = P21.f0();
        try {
            if (e(i, EnumC3169jC0.a(C5383wB.class, false).R).exists()) {
                Boolean bool = Boolean.FALSE;
                f0.close();
                return bool;
            } else if (e(i, EnumC3169jC0.a(C5383wB.class, true).R).exists()) {
                Boolean bool2 = Boolean.TRUE;
                f0.close();
                return bool2;
            } else {
                f0.close();
                return null;
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractC3511lC0
    public void a(int i, String str, Callback callback) {
        Object obj = ThreadUtils.f10596a;
        this.c.add(new OP(this, i, str, callback));
        g();
    }

    @Override // defpackage.AbstractC3511lC0
    public void b(int i, String str) {
        Callback callback = f8956a;
        Object obj = ThreadUtils.f10596a;
        this.c.add(new LP(this, i, str, callback));
        g();
    }

    @Override // defpackage.AbstractC3511lC0
    public byte[] c(int i, String str) {
        return new OP(this, i, str, null).b();
    }

    @Override // defpackage.AbstractC3511lC0
    public void d(int i, String str, byte[] bArr) {
        h(i, str, bArr, f8956a);
    }

    public void g() {
        if (!this.c.isEmpty()) {
            ((SP) this.c.poll()).a().e(this.b);
        }
    }

    public void h(int i, String str, byte[] bArr, Callback callback) {
        Object obj = ThreadUtils.f10596a;
        RP rp = new RP(this, i, str, bArr, callback);
        this.c.remove(rp);
        this.c.add(rp);
        g();
    }
}
