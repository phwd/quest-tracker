package defpackage;

/* renamed from: sc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4768sc0 {
    public static String a(String str, int i) {
        if (i == 0) {
            return str;
        }
        if (i == 1) {
            return AbstractC2531fV.g(str, ".", "Passwords");
        }
        if (i == 2) {
            return AbstractC2531fV.g(str, ".", "CreditCards");
        }
        if (i != 3) {
            return i != 4 ? "" : AbstractC2531fV.g(str, ".", "TouchToFill");
        }
        return AbstractC2531fV.g(str, ".", "Addresses");
    }

    public static void b(int i, int i2) {
        AbstractC3364kK0.g(a("KeyboardAccessory.AccessorySheetTriggered", i), i2, 3);
        if (i != 0) {
            AbstractC3364kK0.g(a("KeyboardAccessory.AccessorySheetTriggered", 0), i2, 3);
        }
    }
}
