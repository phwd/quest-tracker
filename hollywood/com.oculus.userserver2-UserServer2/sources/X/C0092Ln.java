package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* renamed from: X.Ln  reason: case insensitive filesystem */
public class C0092Ln implements hL<T> {
    public final /* synthetic */ C0232hV A00;
    public final /* synthetic */ Type A01;

    public C0092Ln(C0232hV hVVar, Type type) {
        this.A00 = hVVar;
        this.A01 = type;
    }

    @Override // X.hL
    public final T A1B() {
        Type type = this.A01;
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid EnumSet type: ");
        sb.append(type.toString());
        throw new M4(sb.toString());
    }
}
