package X;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0vr  reason: invalid class name and case insensitive filesystem */
public final class C05510vr {
    public static boolean A00(AbstractC05490vp<?> r6, @NullableDecl Object obj) {
        if (obj != r6) {
            if (obj instanceof AbstractC05490vp) {
                AbstractC05490vp r7 = (AbstractC05490vp) obj;
                if (r6.size() == r7.size() && r6.entrySet().size() == r7.entrySet().size()) {
                    for (AnonymousClass0f2 r2 : r7.entrySet()) {
                        if (r6.A2J(r2.A01()) != r2.A00()) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }
}
