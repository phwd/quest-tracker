package X;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.2es  reason: invalid class name */
public abstract class AnonymousClass2es implements AnonymousClass2eu {
    @Override // X.AnonymousClass2eu
    public final Matrix A54(Matrix matrix, Rect rect, int i, int i2, float f, float f2) {
        float f3;
        int i3;
        float f4;
        float f5;
        float max;
        float f6;
        float f7;
        float f8;
        int i4;
        float f9;
        float f10;
        float f11;
        float f12;
        float height;
        float f13 = (float) i;
        float width = ((float) rect.width()) / f13;
        float f14 = (float) i2;
        float height2 = ((float) rect.height()) / f14;
        if (!(this instanceof AnonymousClass2er)) {
            if (!(this instanceof AnonymousClass2f1)) {
                if (!(this instanceof AnonymousClass2f3)) {
                    if (this instanceof AnonymousClass2f2) {
                        float f15 = (float) rect.left;
                        float height3 = ((float) rect.top) + ((((float) rect.height()) - (f14 * width)) * 0.5f);
                        matrix.setScale(width, width);
                        f3 = (float) ((int) (f15 + 0.5f));
                        i3 = (int) (height3 + 0.5f);
                    } else if (!(this instanceof AnonymousClass2ez)) {
                        if (!(this instanceof AnonymousClass2et)) {
                            if (this instanceof AnonymousClass2ew) {
                                f11 = Math.min(width, height2);
                            } else if (this instanceof AnonymousClass2ey) {
                                f9 = Math.min(width, height2);
                                f10 = (float) rect.left;
                            } else if (this instanceof AnonymousClass2ex) {
                                f11 = Math.min(Math.min(width, height2), 1.0f);
                            } else if (!(this instanceof AnonymousClass2f0)) {
                                matrix.setTranslate((float) ((int) (((float) rect.left) + (((float) (rect.width() - i)) * 0.5f) + 0.5f)), (float) ((int) (((float) rect.top) + (((float) (rect.height() - i2)) * 0.5f) + 0.5f)));
                                return matrix;
                            } else {
                                if (height2 > width) {
                                    f12 = ((float) rect.left) + ((((float) rect.width()) - (f13 * height2)) * 0.5f);
                                    height = (float) rect.top;
                                    width = height2;
                                } else {
                                    f12 = (float) rect.left;
                                    height = ((((float) rect.height()) - (f14 * width)) * 0.5f) + ((float) rect.top);
                                }
                                matrix.setScale(width, width);
                                f3 = (float) ((int) (f12 + 0.5f));
                                i3 = (int) (height + 0.5f);
                            }
                            float width2 = ((float) rect.left) + ((((float) rect.width()) - (f13 * f11)) * 0.5f);
                            float height4 = ((float) rect.top) + ((((float) rect.height()) - (f14 * f11)) * 0.5f);
                            matrix.setScale(f11, f11);
                            f3 = (float) ((int) (width2 + 0.5f));
                            f6 = height4 + 0.5f;
                        } else {
                            f9 = Math.min(width, height2);
                            f10 = ((float) rect.left) + (((float) rect.width()) - (f13 * f9));
                        }
                        float height5 = ((float) rect.top) + (((float) rect.height()) - (f14 * f9));
                        matrix.setScale(f9, f9);
                        f3 = (float) ((int) (f10 + 0.5f));
                        i3 = (int) (height5 + 0.5f);
                    } else {
                        float min = Math.min(width, height2);
                        float f16 = (float) rect.left;
                        f7 = (float) rect.top;
                        matrix.setScale(min, min);
                        f8 = 0.5f;
                        i4 = (int) (f16 + 0.5f);
                    }
                    matrix.postTranslate(f3, (float) i3);
                    return matrix;
                }
                float f17 = (float) rect.left;
                f7 = (float) rect.top;
                matrix.setScale(width, height2);
                f8 = 0.5f;
                i4 = (int) (f17 + 0.5f);
                f3 = (float) i4;
                f4 = f7 + f8;
            } else {
                float width3 = ((float) rect.left) + ((((float) rect.width()) - (f13 * height2)) * 0.5f);
                float f18 = (float) rect.top;
                matrix.setScale(height2, height2);
                f3 = (float) ((int) (width3 + 0.5f));
                f6 = f18 + 0.5f;
            }
            i3 = (int) f6;
            matrix.postTranslate(f3, (float) i3);
            return matrix;
        }
        if (height2 > width) {
            float f19 = f13 * height2;
            f5 = ((float) rect.left) + Math.max(Math.min((((float) rect.width()) * 0.5f) - (f * f19), (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z), ((float) rect.width()) - f19);
            max = (float) rect.top;
            width = height2;
        } else {
            f5 = (float) rect.left;
            float f20 = f14 * width;
            max = Math.max(Math.min((((float) rect.height()) * 0.5f) - (f2 * f20), (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z), ((float) rect.height()) - f20) + ((float) rect.top);
        }
        matrix.setScale(width, width);
        f3 = (float) ((int) (f5 + 0.5f));
        f4 = max + 0.5f;
        i3 = (int) f4;
        matrix.postTranslate(f3, (float) i3);
        return matrix;
    }
}
