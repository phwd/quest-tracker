package X;

public abstract class jk {
    public abstract WZ<?> A04();

    /* JADX WARN: Incorrect return type in method signature: (LX/VV;Ljava/lang/Object;)LX/MH<Ljava/lang/Object;Ljava/lang/Object;>; */
    public final void A03(Object obj) throws C0223Wj {
        StringBuilder sb;
        String str;
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (cls != AbstractC0213Vx.class && cls != dY.class) {
                if (MH.class.isAssignableFrom(cls)) {
                    Mv.A02(cls, A04().A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS));
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
}
