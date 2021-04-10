package X;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import java.io.Serializable;

public final class Rw extends AbstractC1020qp implements Serializable {
    public static final long serialVersionUID = 1;

    public static final PT A00(Rw rw, AbstractC1032r3 r3Var, P9 p9) {
        PT stdTypeResolverBuilder;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) p9.A0L(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) p9.A0L(JsonTypeResolver.class);
        PS ps = null;
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo != null) {
                stdTypeResolverBuilder = (PT) Q5.A02(jsonTypeResolver.value(), r3Var.A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS));
            }
            return null;
        }
        if (jsonTypeInfo != null) {
            NF use = jsonTypeInfo.use();
            NF nf = NF.NONE;
            if (use == nf) {
                StdTypeResolverBuilder stdTypeResolverBuilder2 = new StdTypeResolverBuilder();
                stdTypeResolverBuilder2._idType = nf;
                stdTypeResolverBuilder2._customIdResolver = null;
                stdTypeResolverBuilder2._typeProperty = nf.getDefaultPropertyName();
                return stdTypeResolverBuilder2;
            }
            stdTypeResolverBuilder = new StdTypeResolverBuilder();
        }
        return null;
        JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver) p9.A0L(JsonTypeIdResolver.class);
        if (jsonTypeIdResolver != null) {
            ps = (PS) Q5.A02(jsonTypeIdResolver.value(), r3Var.A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS));
        }
        stdTypeResolverBuilder.A3J(jsonTypeInfo.use(), ps);
        NE include = jsonTypeInfo.include();
        if (include == NE.EXTERNAL_PROPERTY && (p9 instanceof C1043rI)) {
            include = NE.PROPERTY;
        }
        stdTypeResolverBuilder.A3H(include);
        stdTypeResolverBuilder.A5J(jsonTypeInfo.property());
        Class defaultImpl = jsonTypeInfo.defaultImpl();
        if (defaultImpl != NG.class) {
            stdTypeResolverBuilder.A1e(defaultImpl);
        }
        stdTypeResolverBuilder.A5I(jsonTypeInfo.visible());
        return stdTypeResolverBuilder;
    }
}
