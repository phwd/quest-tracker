package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* renamed from: X.0XF  reason: invalid class name */
public class AnonymousClass0XF implements AbstractC01170Du<T> {
    public final /* synthetic */ AnonymousClass0Cp A00;
    public final /* synthetic */ Type A01;

    public AnonymousClass0XF(AnonymousClass0Cp r1, Type type) {
        this.A00 = r1;
        this.A01 = type;
    }

    @Override // X.AbstractC01170Du
    public final T A1o() {
        Type type = this.A01;
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
        }
        throw new AnonymousClass0XU("Invalid EnumSet type: " + type.toString());
    }
}
