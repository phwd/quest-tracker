package defpackage;

/* renamed from: tf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4946tf {

    /* renamed from: a  reason: collision with root package name */
    public String f11357a;
    public int b;
    public int c;

    public C4946tf(String str, int i, int i2) {
        this.f11357a = str;
        this.b = i;
        this.c = i2;
    }

    public static C4946tf a(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length == 3 && !split[0].isEmpty() && !split[1].isEmpty() && !split[2].isEmpty()) {
            try {
                return new C4946tf(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public String toString() {
        return this.f11357a + ":" + this.b + ":" + this.c;
    }
}
