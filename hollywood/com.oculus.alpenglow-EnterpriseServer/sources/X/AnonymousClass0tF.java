package X;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0tF  reason: invalid class name */
public final class AnonymousClass0tF {
    public static boolean A00(AnonymousClass0tC<?> r6, @NullableDecl Object obj) {
        if (obj != r6) {
            if (obj instanceof AnonymousClass0tC) {
                AnonymousClass0tC r7 = (AnonymousClass0tC) obj;
                if (r6.size() == r7.size() && r6.entrySet().size() == r7.entrySet().size()) {
                    for (AnonymousClass0Y0 r2 : r7.entrySet()) {
                        if (r6.A1s(r2.A01()) != r2.A00()) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }
}
