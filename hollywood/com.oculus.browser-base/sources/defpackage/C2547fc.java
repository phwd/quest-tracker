package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ScaleDrawable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.Callback;

/* renamed from: fc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2547fc extends YI {
    public boolean G;

    public C2547fc(Drawable drawable) {
        super(drawable);
        a(this, new C1466Yb());
    }

    public static void a(Drawable drawable, Callback callback) {
        if (drawable != null) {
            if (drawable instanceof Animatable) {
                callback.onResult((Animatable) drawable);
                return;
            }
            if (drawable != drawable.getCurrent()) {
                a(drawable.getCurrent(), callback);
            }
            if (drawable instanceof DrawableWrapper) {
                a(((DrawableWrapper) drawable).getDrawable(), callback);
            } else if (drawable instanceof YI) {
                a(((YI) drawable).F, callback);
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                for (int i = 0; i < layerDrawable.getNumberOfLayers(); i++) {
                    a(layerDrawable.getDrawable(i), callback);
                }
            } else if (drawable instanceof InsetDrawable) {
                a(((InsetDrawable) drawable).getDrawable(), callback);
            } else if (drawable instanceof RotateDrawable) {
                a(((RotateDrawable) drawable).getDrawable(), callback);
            } else if (drawable instanceof ScaleDrawable) {
                a(((ScaleDrawable) drawable).getDrawable(), callback);
            }
        }
    }

    public static Drawable b(Drawable drawable) {
        if (drawable != null) {
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            a(drawable, new C1405Xb(atomicBoolean));
            if (atomicBoolean.get()) {
                return new C2547fc(drawable);
            }
        }
        return drawable;
    }

    @Override // defpackage.YI
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            a(this, new C1344Wb());
        } else if (visible || z2 || !this.G) {
            a(this, new C1283Vb());
        }
        this.G = true;
        return visible;
    }
}
