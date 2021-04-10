package X;

public abstract class O6 {
    public final NN A02(PJ pj) {
        NN nn = (NN) Q5.A02(pj.A00, A04().A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS));
        Class cls = pj.A01;
        if (!(nn instanceof AnonymousClass2x)) {
            AbstractC1008qc qcVar = (AbstractC1008qc) nn;
            if (cls != qcVar._scope) {
                return new AnonymousClass2B(cls);
            }
            return qcVar;
        }
        AnonymousClass2x r2 = (AnonymousClass2x) nn;
        if (cls != r2._scope) {
            return new AnonymousClass2x(cls, r2._property);
        }
        return r2;
    }

    public final AbstractC1032r3 A04() {
        if (!(this instanceof AbstractC1031r2)) {
            return ((AbstractC1022qr) this)._config;
        }
        return ((AbstractC1031r2) this)._config;
    }

    public final C0300Pw A05() {
        if (!(this instanceof AbstractC1031r2)) {
            return ((AbstractC1022qr) this)._config._base._typeFactory;
        }
        return ((AbstractC1031r2) this)._config._base._typeFactory;
    }

    public final void A06(Object obj) {
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (cls != AbstractC1072ro.class && cls != OR.class) {
                if (Q6.class.isAssignableFrom(cls)) {
                    Q5.A02(cls, A04().A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS));
                    return;
                }
                throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<Converter>"));
            }
            return;
        }
        throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned Converter definition of type ", obj.getClass().getName(), "; expected type Converter or Class<Converter> instead"));
    }

    public final AbstractC1024qt A03(AbstractC1024qt qtVar, Class cls) {
        return A04()._base._typeFactory.A07(qtVar, cls);
    }
}
