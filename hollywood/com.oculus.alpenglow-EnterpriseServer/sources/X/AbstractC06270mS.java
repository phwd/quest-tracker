package X;

/* renamed from: X.0mS  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC06270mS {
    public abstract AnonymousClass0a7<?> A06();

    public abstract C07040od A07();

    /* JADX WARN: Incorrect args count in method signature: (LX/0nb;LX/0nk;)LX/0lR<*>; */
    public final AnonymousClass0lR A03(C06720nk r4) throws AnonymousClass0aG {
        return ((AnonymousClass0lR) C07130om.A02(r4.A00, A06().A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS))).A01(r4.A01);
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0nb;Ljava/lang/Object;)LX/0on<Ljava/lang/Object;Ljava/lang/Object;>; */
    public final void A05(Object obj) throws AnonymousClass0aG {
        StringBuilder sb;
        String str;
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (cls != AnonymousClass0ZO.class && cls != C06410mq.class) {
                if (AbstractC07140on.class.isAssignableFrom(cls)) {
                    C07130om.A02(cls, A06().A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS));
                    return;
                }
                sb = new StringBuilder("AnnotationIntrospector returned Class ");
                sb.append(cls.getName());
                str = "; expected Class<Converter>";
            } else {
                return;
            }
        } else {
            sb = new StringBuilder("AnnotationIntrospector returned Converter definition of type ");
            sb.append(obj.getClass().getName());
            str = "; expected type Converter or Class<Converter> instead";
        }
        sb.append(str);
        throw new IllegalStateException(sb.toString());
    }

    public final AnonymousClass0aI A04(AnonymousClass0aI r2, Class<?> cls) {
        return A06()._base._typeFactory.A07(r2, cls);
    }
}
