package X;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0rC  reason: invalid class name */
public final class AnonymousClass0rC {
    public static boolean A00(AnonymousClass0r9<?> r6, @NullableDecl Object obj) {
        if (obj != r6) {
            if (obj instanceof AnonymousClass0r9) {
                AnonymousClass0r9 r7 = (AnonymousClass0r9) obj;
                if (r6.size() == r7.size() && r6.entrySet().size() == r7.entrySet().size()) {
                    for (AnonymousClass0dN r2 : r7.entrySet()) {
                        if (r6.A1v(r2.A01()) != r2.A00()) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }
}
