package X;

import com.facebook.graphservice.interfaces.Tree;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.google.common.base.Preconditions;
import java.util.Collection;

public final class CF {
    public static void A00(AbstractC1012qg qgVar, AbstractC1031r2 r2Var, Collection collection) {
        if (collection != null) {
            qgVar.A0B();
            for (Object obj : collection) {
                if (obj != null) {
                    Class<?> cls = obj.getClass();
                    boolean z = obj instanceof Tree;
                    if (z) {
                        throw new NullPointerException("isValidGraphServicesJNIModel");
                    } else if (EH.class.isAssignableFrom(cls)) {
                        Preconditions.checkArgument(z);
                        throw new NullPointerException("getTypeTag");
                    } else if (EG.class.isAssignableFrom(cls) && !AbstractC0565c2.class.isAssignableFrom(cls)) {
                        StaticGraphServiceFactory.getTreeSerializer();
                        throw new NullPointerException("serializeTreeToByteBuffer");
                    } else if (OB.class.isAssignableFrom(cls)) {
                        ((OB) obj).A4x(qgVar, r2Var);
                    } else if (cls.isEnum()) {
                        qgVar.A0P(((Enum) obj).name());
                    } else if (Collection.class.isAssignableFrom(cls)) {
                        A00(qgVar, r2Var, (Collection) obj);
                    } else {
                        qgVar.A0L(obj);
                    }
                }
            }
            qgVar.A08();
        }
    }
}
