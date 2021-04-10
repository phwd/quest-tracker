package defpackage;

/* renamed from: pb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4254pb0 {
    public static float a(float[] fArr, float f, float f2) {
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        int min = Math.min((int) (((float) (fArr.length - 1)) * f2), fArr.length - 2);
        return AbstractC2531fV.a(fArr[min + 1], fArr[min], (f2 - (((float) min) * f)) / f, fArr[min]);
    }
}
