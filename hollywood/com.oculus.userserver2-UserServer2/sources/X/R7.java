package X;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class R7 {
    public static boolean A00(AbstractC0120Qz<?> qz, @NullableDecl Object obj) {
        if (obj != qz) {
            if (obj instanceof AbstractC0120Qz) {
                AbstractC0120Qz qz2 = (AbstractC0120Qz) obj;
                if (qz.size() == qz2.size() && qz.entrySet().size() == qz2.entrySet().size()) {
                    for (Mh mh : qz2.entrySet()) {
                        if (qz.A1D(mh.A01()) != mh.A00()) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }
}
