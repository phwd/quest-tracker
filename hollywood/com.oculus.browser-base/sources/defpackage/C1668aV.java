package defpackage;

/* renamed from: aV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1668aV implements AbstractC1064Rj0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C1668aV f9432a = new C1668aV();

    @Override // defpackage.AbstractC1064Rj0
    public AbstractC1003Qj0 a(Class cls) {
        if (!AbstractC2360eV.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (AbstractC1003Qj0) AbstractC2360eV.f(cls.asSubclass(AbstractC2360eV.class)).d(EnumC2190dV.BUILD_MESSAGE_INFO);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }

    @Override // defpackage.AbstractC1064Rj0
    public boolean b(Class cls) {
        return AbstractC2360eV.class.isAssignableFrom(cls);
    }
}
