package defpackage;

/* renamed from: dc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2207dc0 implements AbstractC1064Rj0 {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC1064Rj0[] f9794a;

    public C2207dc0(AbstractC1064Rj0... rj0Arr) {
        this.f9794a = rj0Arr;
    }

    @Override // defpackage.AbstractC1064Rj0
    public AbstractC1003Qj0 a(Class cls) {
        AbstractC1064Rj0[] rj0Arr = this.f9794a;
        for (AbstractC1064Rj0 rj0 : rj0Arr) {
            if (rj0.b(cls)) {
                return rj0.a(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }

    @Override // defpackage.AbstractC1064Rj0
    public boolean b(Class cls) {
        for (AbstractC1064Rj0 rj0 : this.f9794a) {
            if (rj0.b(cls)) {
                return true;
            }
        }
        return false;
    }
}
