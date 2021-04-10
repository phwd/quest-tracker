package defpackage;

import com.oculus.os.Version;

/* renamed from: fg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2561fg1 implements AbstractC2732gg1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C2561fg1 f9941a = new C2561fg1();

    @Override // defpackage.AbstractC2732gg1
    public int a(CharSequence charSequence, int i, int i2) {
        int i3 = i2 + i;
        int i4 = 2;
        while (i < i3 && i4 == 2) {
            byte directionality = Character.getDirectionality(charSequence.charAt(i));
            AbstractC2903hg1 hg1 = AbstractC3244jg1.f10228a;
            if (directionality != 0) {
                if (!(directionality == 1 || directionality == 2)) {
                    switch (directionality) {
                        case Version.VERSION_14:
                        case Version.VERSION_15:
                            break;
                        case Version.VERSION_16:
                        case Version.VERSION_17:
                            break;
                        default:
                            i4 = 2;
                            break;
                    }
                    i++;
                }
                i4 = 0;
                i++;
            }
            i4 = 1;
            i++;
        }
        return i4;
    }
}
