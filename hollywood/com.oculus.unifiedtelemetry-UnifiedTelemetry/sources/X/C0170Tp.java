package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* renamed from: X.Tp  reason: case insensitive filesystem */
public class C0170Tp implements VE<T> {
    public final /* synthetic */ TW A00;
    public final /* synthetic */ Type A01;

    public C0170Tp(TW tw, Type type) {
        this.A00 = tw;
        this.A01 = type;
    }

    @Override // X.VE
    public final T A1b() {
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
        throw new U4(sb.toString());
    }
}
