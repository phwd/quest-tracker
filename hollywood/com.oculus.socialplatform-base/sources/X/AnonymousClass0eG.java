package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* renamed from: X.0eG  reason: invalid class name */
public class AnonymousClass0eG implements AnonymousClass143<T> {
    public final /* synthetic */ AnonymousClass13k A00;
    public final /* synthetic */ Type A01;

    public AnonymousClass0eG(AnonymousClass13k r1, Type type) {
        this.A00 = r1;
        this.A01 = type;
    }

    @Override // X.AnonymousClass143
    public final T A2F() {
        Type type = this.A01;
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return (T) EnumSet.noneOf((Class) type2);
            }
            throw new AnonymousClass0eV(AnonymousClass006.A07("Invalid EnumSet type: ", type.toString()));
        }
        throw new AnonymousClass0eV(AnonymousClass006.A07("Invalid EnumSet type: ", type.toString()));
    }
}
