package defpackage;

/* renamed from: ja  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3224ja extends AbstractC0685Le1 {

    /* renamed from: a  reason: collision with root package name */
    public static volatile C3224ja f10214a;
    public AbstractC0685Le1 b;
    public AbstractC0685Le1 c;

    public C3224ja() {
        C1628aE aEVar = new C1628aE();
        this.c = aEVar;
        this.b = aEVar;
    }

    public static C3224ja b() {
        if (f10214a != null) {
            return f10214a;
        }
        synchronized (C3224ja.class) {
            if (f10214a == null) {
                f10214a = new C3224ja();
            }
        }
        return f10214a;
    }

    @Override // defpackage.AbstractC0685Le1
    public boolean a() {
        return this.b.a();
    }
}
