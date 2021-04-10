package defpackage;

import android.content.Context;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContextUtils;

/* renamed from: ql0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4455ql0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f11160a;
    public final Class b;
    public final String c;
    public Object d;
    public Y10 e;
    public boolean f;

    public C4455ql0(String str, Class cls, String str2) {
        this.f11160a = str;
        this.b = cls;
        this.c = str2;
    }

    public static Object e(String str, String str2) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (BundleUtils.c(applicationContext, str)) {
            applicationContext = BundleUtils.a(applicationContext, str);
        }
        try {
            P21 f0 = P21.f0();
            try {
                Object newInstance = applicationContext.getClassLoader().loadClass(str2).newInstance();
                f0.close();
                return newInstance;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public void a() {
        if (!this.f) {
            Boolean bool = BundleUtils.f10583a;
            this.f = true;
        }
    }

    public Object b() {
        C0270Ei1 ei1 = new C0270Ei1();
        try {
            Object obj = this.d;
            if (obj != null) {
                ei1.close();
                return obj;
            }
            String str = this.f11160a;
            Boolean bool = BundleUtils.f10583a;
            Object e2 = e(str, this.c);
            try {
                Object cast = this.b.cast(e2);
                this.d = cast;
                ei1.close();
                return cast;
            } catch (ClassCastException e3) {
                ClassLoader classLoader = this.b.getClassLoader();
                ClassLoader classLoader2 = e2.getClass().getClassLoader();
                throw new RuntimeException("Failure casting " + this.f11160a + " module class, interface ClassLoader: " + classLoader + " (parent " + classLoader.getParent() + "), impl ClassLoader: " + classLoader2 + " (parent " + classLoader2.getParent() + "), equal: " + classLoader.equals(classLoader2) + " (parents equal: " + classLoader.getParent().equals(classLoader2.getParent()) + ")", e3);
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public Y10 c() {
        if (this.e == null) {
            C0270Ei1 ei1 = new C0270Ei1();
            try {
                this.e = new C4795sl0(this.c);
                ei1.close();
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        return this.e;
        throw th;
    }

    public void d(AbstractC1593a20 a20) {
        C0270Ei1 ei1 = new C0270Ei1();
        try {
            c().a(this.f11160a, a20);
            ei1.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public boolean f() {
        C0270Ei1 ei1 = new C0270Ei1();
        try {
            boolean c2 = c().c(this.f11160a);
            ei1.close();
            return c2;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
