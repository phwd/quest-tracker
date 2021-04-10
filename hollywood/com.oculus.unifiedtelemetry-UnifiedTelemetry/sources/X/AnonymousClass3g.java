package X;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.3g  reason: invalid class name */
public final class AnonymousClass3g {
    public static boolean A00(AnonymousClass34<?> r6, @NullableDecl Object obj) {
        if (obj != r6) {
            if (obj instanceof AnonymousClass34) {
                AnonymousClass34 r7 = (AnonymousClass34) obj;
                if (r6.size() == r7.size() && r6.entrySet().size() == r7.entrySet().size()) {
                    for (AbstractC0181Ug ug : r7.entrySet()) {
                        if (r6.A1c(ug.A01()) != ug.A00()) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }
}
