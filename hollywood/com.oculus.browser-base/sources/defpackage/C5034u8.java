package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* renamed from: u8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5034u8 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11388a = {16843067, 16843068};
    public final ProgressBar b;
    public Bitmap c;

    public C5034u8(ProgressBar progressBar) {
        this.b = progressBar;
    }

    public void a(AttributeSet attributeSet, int i) {
        C0514Ii1 q = C0514Ii1.q(this.b.getContext(), attributeSet, f11388a, i, 0);
        Drawable h = q.h(0);
        if (h != null) {
            ProgressBar progressBar = this.b;
            if (h instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) h;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i2 = 0; i2 < numberOfFrames; i2++) {
                    Drawable b2 = b(animationDrawable.getFrame(i2), true);
                    b2.setLevel(10000);
                    animationDrawable2.addFrame(b2, animationDrawable.getDuration(i2));
                }
                animationDrawable2.setLevel(10000);
                h = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(h);
        }
        Drawable h2 = q.h(1);
        if (h2 != null) {
            this.b.setProgressDrawable(b(h2, false));
        }
        q.b.recycle();
    }

    public final Drawable b(Drawable drawable, boolean z) {
        if (drawable instanceof AbstractC4156oz1) {
            AbstractC4327pz1 pz1 = (AbstractC4327pz1) ((AbstractC4156oz1) drawable);
            Drawable drawable2 = pz1.F;
            if (drawable2 != null) {
                pz1.a(b(drawable2, z));
            }
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; i++) {
                int id = layerDrawable.getId(i);
                drawableArr[i] = b(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
            }
            LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                layerDrawable2.setId(i2, layerDrawable.getId(i2));
            }
            return layerDrawable2;
        } else if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (this.c == null) {
                this.c = bitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        return drawable;
    }
}
