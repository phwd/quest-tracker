package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wH  reason: invalid class name */
public final class AnonymousClass0wH {
    public final AbstractC09110yg A00;

    public AnonymousClass0wH(AnonymousClass0z2 r3) {
        String str;
        switch (r3.ordinal()) {
            case 2:
                this.A00 = new AnonymousClass15W();
                return;
            case 3:
                str = "Not yet implemented";
                break;
            default:
                str = "Unable to initialize";
                break;
        }
        throw new RuntimeException(str);
    }

    public AnonymousClass0wH(String str, String str2, AnonymousClass0z2 r6) {
        String str3;
        switch (r6.ordinal()) {
            case 2:
                this.A00 = new AnonymousClass15W(AnonymousClass0yX.A02(str), AnonymousClass0yX.A02(str2));
                return;
            case 3:
                str3 = "Not yet implemented";
                break;
            default:
                str3 = "Unable to initialize";
                break;
        }
        throw new RuntimeException(str3);
    }
}
