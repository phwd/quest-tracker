package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* renamed from: X.0av  reason: invalid class name and case insensitive filesystem */
public class C02630av implements AbstractC09010zC<T> {
    public final /* synthetic */ C08920yx A00;
    public final /* synthetic */ Type A01;

    public C02630av(C08920yx r1, Type type) {
        this.A00 = r1;
        this.A01 = type;
    }

    @Override // X.AbstractC09010zC
    public final T A1q() {
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
        throw new AnonymousClass0c9(sb.toString());
    }
}
