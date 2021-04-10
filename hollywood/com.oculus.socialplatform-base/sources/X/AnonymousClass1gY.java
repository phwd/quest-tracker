package X;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1gY  reason: invalid class name */
public interface AnonymousClass1gY<Z> extends AbstractC09221gz<ImageView, Z> {
    @Nullable
    public Animatable A00;

    private default void A00(@Nullable Z z) {
        if (!(this instanceof C09161go)) {
            ((ImageView) ((AbstractC09221gz) this).A00).setImageBitmap(z);
        } else {
            ((ImageView) ((AbstractC09221gz) this).A00).setImageDrawable(z);
        }
        if (z instanceof Animatable) {
            Z z2 = z;
            this.A00 = z2;
            z2.start();
            return;
        }
        this.A00 = null;
    }

    @Override // X.AbstractC08781fH
    final default void onResourceReady(@NonNull Z z, @Nullable AbstractC08911fj<? super Z> r3) {
        if (r3 == null || !r3.AAl(z, this)) {
            A00(z);
        } else if (z instanceof Animatable) {
            Z z2 = z;
            this.A00 = z2;
            z2.start();
        } else {
            this.A00 = null;
        }
    }

    @Override // X.AbstractC08781fH, X.AbstractC09221gz, X.AbstractC09191gs
    final default void onLoadCleared(@Nullable Drawable drawable) {
        super.onLoadCleared(drawable);
        Animatable animatable = this.A00;
        if (animatable != null) {
            animatable.stop();
        }
        A00(null);
        ((ImageView) ((AbstractC09221gz) this).A00).setImageDrawable(drawable);
    }

    @Override // X.AbstractC08781fH, X.AbstractC09191gs
    final default void onLoadFailed(@Nullable Drawable drawable) {
        super.onLoadFailed(drawable);
        A00(null);
        ((ImageView) ((AbstractC09221gz) this).A00).setImageDrawable(drawable);
    }

    @Override // X.AbstractC08781fH, X.AbstractC09221gz, X.AbstractC09191gs
    final default void onLoadStarted(@Nullable Drawable drawable) {
        super.onLoadStarted(drawable);
        A00(null);
        ((ImageView) ((AbstractC09221gz) this).A00).setImageDrawable(drawable);
    }

    default AnonymousClass1gY(ImageView imageView) {
        super(imageView);
    }
}
