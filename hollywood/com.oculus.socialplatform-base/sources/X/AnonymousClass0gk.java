package X;

import com.google.common.base.Optional;
import java.lang.reflect.Method;
import java.util.List;

/* renamed from: X.0gk  reason: invalid class name */
public final class AnonymousClass0gk extends AbstractC04590qj {
    @Override // X.AbstractC04590qj
    public final List<AnonymousClass0Og> A00(AnonymousClass0HM r5, AbstractC04010oz r6, List<AnonymousClass0Og> list) {
        Class<?> type;
        for (int i = 0; i < list.size(); i++) {
            AnonymousClass0Og r2 = list.get(i);
            Method method = r2.A09;
            if (method != null) {
                type = method.getReturnType();
            } else {
                type = r2.A08.getType();
            }
            if (Optional.class.isAssignableFrom(type)) {
                list.set(i, new C00510Bs(r2));
            }
        }
        return list;
    }
}
