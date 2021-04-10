package X;

import com.facebook.graphservice.interfaces.Tree;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Collection;

/* renamed from: X.0Iq  reason: invalid class name */
public final class AnonymousClass0Iq {
    public static void A00(AbstractC02300iS r3, AbstractC02120i3 r4, Object obj) throws IOException, C03620oC {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            boolean z = obj instanceof Tree;
            if (z) {
                throw new NullPointerException("isValidGraphServicesJNIModel");
            } else if (AnonymousClass0Nj.class.isAssignableFrom(cls)) {
                Preconditions.checkArgument(z);
                throw new NullPointerException("getTypeTag");
            } else if (AnonymousClass0Nh.class.isAssignableFrom(cls) && !AnonymousClass0VN.class.isAssignableFrom(cls)) {
                StaticGraphServiceFactory.getTreeSerializer();
                throw new NullPointerException("serializeTreeToByteBuffer");
            } else if (AnonymousClass0p5.class.isAssignableFrom(cls)) {
                ((AnonymousClass0p5) obj).A9c(r3, r4);
            } else if (cls.isEnum()) {
                r3.A0U(((Enum) obj).name());
            } else if (Collection.class.isAssignableFrom(cls)) {
                Collection<Object> collection = (Collection) obj;
                if (collection != null) {
                    r3.A0H();
                    for (Object obj2 : collection) {
                        A00(r3, r4, obj2);
                    }
                    r3.A0E();
                }
            } else {
                r3.A0C(obj);
            }
        }
    }

    public static void A01(AbstractC02300iS r0, String str, String str2) throws IOException, C03620oC {
        if (str2 != null) {
            r0.A0R(str);
            r0.A0U(str2);
        }
    }
}
