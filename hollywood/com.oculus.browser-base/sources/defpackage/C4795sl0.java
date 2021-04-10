package defpackage;

import java.util.Objects;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContextUtils;

/* renamed from: sl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4795sl0 extends Y10 {

    /* renamed from: a  reason: collision with root package name */
    public Y10 f11298a;
    public C2682gL b;
    public final String c;

    public C4795sl0(String str) {
        C2682gL gLVar = new C2682gL();
        this.c = str;
        this.b = gLVar;
    }

    @Override // defpackage.Y10
    public void a(String str, AbstractC1593a20 a20) {
        d().a(str, a20);
    }

    @Override // defpackage.Y10
    public void b(String str) {
        d().b(str);
    }

    @Override // defpackage.Y10
    public boolean c(String str) {
        if (BundleUtils.c(ContextUtils.getApplicationContext(), str)) {
            return true;
        }
        try {
            P21 f0 = P21.f0();
            try {
                ContextUtils.getApplicationContext().getClassLoader().loadClass(this.c);
                f0.close();
                return true;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } catch (ClassNotFoundException unused) {
            return false;
        }
        throw th;
    }

    public final Y10 d() {
        if (this.f11298a == null) {
            Objects.requireNonNull(this.b);
            Boolean bool = BundleUtils.f10583a;
            this.f11298a = new C5031u7();
        }
        return this.f11298a;
    }
}
