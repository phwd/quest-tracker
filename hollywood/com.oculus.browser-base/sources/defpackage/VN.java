package defpackage;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;

/* renamed from: VN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VN {

    /* renamed from: a  reason: collision with root package name */
    public Paint f9079a = new Paint();
    public Matrix b = new Matrix();
    public Shader c;

    public VN(int i) {
        float[] fArr = new float[8];
        int[] iArr = new int[8];
        int i2 = 16777215 & i;
        int alpha = Color.alpha(i);
        for (int i3 = 0; i3 < 8; i3++) {
            float f = ((float) i3) / 7.0f;
            float f2 = 1.0f - (2.2f * f);
            float f3 = 1.8f - (0.6f * f);
            fArr[i3] = f;
            iArr[i3] = (Math.round(((float) alpha) * (((f * f) * f3) + f2)) << 24) | i2;
        }
        this.c = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, iArr, fArr, Shader.TileMode.CLAMP);
    }
}
