package defpackage;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: dI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2163dI0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C2163dI0 f9768a = new C2163dI0();
    public final VO0 b = new C2377ec0();
    public final ConcurrentMap c = new ConcurrentHashMap();

    public UO0 a(Class cls) {
        UO0 uo0;
        C4281pk0 pk0;
        Class cls2;
        Charset charset = F30.f7992a;
        Objects.requireNonNull(cls, "messageType");
        UO0 uo02 = (UO0) this.c.get(cls);
        if (uo02 != null) {
            return uo02;
        }
        C2377ec0 ec0 = (C2377ec0) this.b;
        Objects.requireNonNull(ec0);
        Class cls3 = WO0.f9145a;
        if (AbstractC2360eV.class.isAssignableFrom(cls) || (cls2 = WO0.f9145a) == null || cls2.isAssignableFrom(cls)) {
            AbstractC1003Qj0 a2 = ec0.b.a(cls);
            UJ0 uj0 = (UJ0) a2;
            if ((uj0.d & 2) == 2) {
                if (AbstractC2360eV.class.isAssignableFrom(cls)) {
                    pk0 = new C4281pk0(WO0.d, WM.f9142a, uj0.f9022a);
                } else {
                    Ap1 ap1 = WO0.b;
                    VM vm = WM.b;
                    if (vm != null) {
                        pk0 = new C4281pk0(ap1, vm, uj0.f9022a);
                    } else {
                        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                    }
                }
                uo0 = pk0;
            } else if (AbstractC2360eV.class.isAssignableFrom(cls)) {
                if (C2377ec0.a(a2)) {
                    uo0 = C3597lk0.w(a2, AbstractC1133Sn0.b, N80.b, WO0.d, WM.f9142a, AbstractC0190Dc0.b);
                } else {
                    uo0 = C3597lk0.w(a2, AbstractC1133Sn0.b, N80.b, WO0.d, null, AbstractC0190Dc0.b);
                }
            } else if (C2377ec0.a(a2)) {
                C1072Rn0 rn0 = AbstractC1133Sn0.f8917a;
                N80 n80 = N80.f8530a;
                Ap1 ap12 = WO0.b;
                VM vm2 = WM.b;
                if (vm2 != null) {
                    uo0 = C3597lk0.w(a2, rn0, n80, ap12, vm2, AbstractC0190Dc0.f7898a);
                } else {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
            } else {
                uo0 = C3597lk0.w(a2, AbstractC1133Sn0.f8917a, N80.f8530a, WO0.c, null, AbstractC0190Dc0.f7898a);
            }
            UO0 uo03 = (UO0) this.c.putIfAbsent(cls, uo0);
            return uo03 != null ? uo03 : uo0;
        }
        throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
    }

    public UO0 b(Object obj) {
        return a(obj.getClass());
    }
}
