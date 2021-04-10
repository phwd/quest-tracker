package defpackage;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* renamed from: nK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3877nK0 implements TypeEvaluator {
    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f, Object obj, Object obj2) {
        Rect rect = (Rect) obj;
        Rect rect2 = (Rect) obj2;
        int i = rect.left;
        int i2 = i + ((int) (((float) (rect2.left - i)) * f));
        int i3 = rect.top;
        int i4 = i3 + ((int) (((float) (rect2.top - i3)) * f));
        int i5 = rect.right;
        int i6 = rect.bottom;
        return new Rect(i2, i4, i5 + ((int) (((float) (rect2.right - i5)) * f)), i6 + ((int) (((float) (rect2.bottom - i6)) * f)));
    }
}
