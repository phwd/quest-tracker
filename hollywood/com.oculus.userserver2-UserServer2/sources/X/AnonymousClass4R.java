package X;

/* renamed from: X.4R  reason: invalid class name */
public class AnonymousClass4R {
    public static CharSequence A00(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 5120) {
            return charSequence;
        }
        return charSequence.subSequence(0, 5120);
    }
}
