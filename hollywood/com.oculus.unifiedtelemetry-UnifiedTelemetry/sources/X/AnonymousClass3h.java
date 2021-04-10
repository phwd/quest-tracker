package X;

/* renamed from: X.3h  reason: invalid class name */
public class AnonymousClass3h {
    public static CharSequence A00(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 5120) {
            return charSequence;
        }
        return charSequence.subSequence(0, 5120);
    }
}
