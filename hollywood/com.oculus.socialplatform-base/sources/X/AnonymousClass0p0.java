package X;

/* renamed from: X.0p0  reason: invalid class name */
public abstract class AnonymousClass0p0 {
    public abstract AbstractC02110i2<?> A06();

    public abstract AnonymousClass0r9 A07();

    /* JADX WARN: Incorrect args count in method signature: (LX/0qA;LX/0qJ;)LX/0nz<*>; */
    public final AbstractC03600nz A03(C04480qJ r4) throws C02180iD {
        return ((AbstractC03600nz) C04810rI.A02(r4.A00, A06().A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS))).A01(r4.A01);
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0qA;Ljava/lang/Object;)LX/0rJ<Ljava/lang/Object;Ljava/lang/Object;>; */
    public final void A05(Object obj) throws C02180iD {
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (cls != AbstractC01760gy.class && cls != C04130pP.class) {
                if (AnonymousClass0rJ.class.isAssignableFrom(cls)) {
                    C04810rI.A02(cls, A06().A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS));
                    return;
                }
                throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<Converter>"));
            }
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A09("AnnotationIntrospector returned Converter definition of type ", obj.getClass().getName(), "; expected type Converter or Class<Converter> instead"));
    }

    public final AbstractC02190iF A04(AbstractC02190iF r2, Class<?> cls) {
        return A06()._base._typeFactory.A07(r2, cls);
    }
}
