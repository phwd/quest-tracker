package X;

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

/* renamed from: X.1ph  reason: invalid class name and case insensitive filesystem */
public class C10801ph {
    public static final int[] A02 = {16843067, 16843068};
    public Bitmap A00;
    public final ProgressBar A01;

    private Drawable A00(Drawable drawable, boolean z) {
        boolean z2;
        if (drawable instanceof AnonymousClass05O) {
            Drawable wrappedDrawable = ((AnonymousClass05O) drawable).getWrappedDrawable();
            if (wrappedDrawable == null) {
                return drawable;
            }
            A00(wrappedDrawable, z);
            throw new NullPointerException("setWrappedDrawable");
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; i++) {
                int id = layerDrawable.getId(i);
                Drawable drawable2 = layerDrawable.getDrawable(i);
                if (id != 16908301) {
                    z2 = false;
                    if (id != 16908303) {
                        drawableArr[i] = A00(drawable2, z2);
                    }
                }
                z2 = true;
                drawableArr[i] = A00(drawable2, z2);
            }
            LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                layerDrawable2.setId(i2, layerDrawable.getId(i2));
            }
            return layerDrawable2;
        } else if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        } else {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (this.A00 == null) {
                this.A00 = bitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            if (z) {
                return new ClipDrawable(shapeDrawable, 3, 1);
            }
            return shapeDrawable;
        }
    }

    public void A01(AttributeSet attributeSet, int i) {
        ProgressBar progressBar = this.A01;
        C10901qA A002 = C10901qA.A00(progressBar.getContext(), attributeSet, A02, i, 0);
        Drawable A03 = A002.A03(0);
        if (A03 != null) {
            if (A03 instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) A03;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i2 = 0; i2 < numberOfFrames; i2++) {
                    Drawable A003 = A00(animationDrawable.getFrame(i2), true);
                    A003.setLevel(10000);
                    animationDrawable2.addFrame(A003, animationDrawable.getDuration(i2));
                }
                animationDrawable2.setLevel(10000);
                A03 = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(A03);
        }
        Drawable A032 = A002.A03(1);
        if (A032 != null) {
            progressBar.setProgressDrawable(A00(A032, false));
        }
        A002.A04();
    }

    public C10801ph(ProgressBar progressBar) {
        this.A01 = progressBar;
    }
}
