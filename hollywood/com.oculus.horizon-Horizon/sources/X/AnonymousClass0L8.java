package X;

import com.facebook.infer.annotation.TrueOnNull;

/* renamed from: X.0L8  reason: invalid class name */
public final class AnonymousClass0L8 {
    @TrueOnNull
    public static boolean A00(CharSequence... charSequenceArr) {
        for (CharSequence charSequence : charSequenceArr) {
            if (charSequence == null || charSequence.length() == 0) {
                return true;
            }
        }
        return false;
    }
}
