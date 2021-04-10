package X;

/* renamed from: X.0kV  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05190kV {
    public abstract AbstractC03910gQ<?> A03();

    /* JADX WARN: Incorrect return type in method signature: (LX/0lg;Ljava/lang/Object;)LX/0mv<Ljava/lang/Object;Ljava/lang/Object;>; */
    public final void A02(Object obj) throws C03990gZ {
        StringBuilder sb;
        String str;
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (cls != AnonymousClass0fm.class && cls != C05340ku.class) {
                if (AbstractC06340mv.class.isAssignableFrom(cls)) {
                    C06330mu.A02(cls, A03().A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS));
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
