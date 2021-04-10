package X;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;

@Deprecated
/* renamed from: X.1gs  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09191gs<Z> implements AbstractC08781fH<Z> {
    public AnonymousClass1h5 A00;

    @Override // X.AbstractC08541eo
    public final void onDestroy() {
    }

    @Override // X.AbstractC08781fH
    public void onLoadCleared(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08781fH
    public void onLoadFailed(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08781fH
    public void onLoadStarted(@Nullable Drawable drawable) {
    }

    @Override // X.AbstractC08781fH
    @Nullable
    public final AnonymousClass1h5 getRequest() {
        if (!(this instanceof AbstractC09221gz)) {
            return this.A00;
        }
        Object tag = ((AbstractC09221gz) this).A00.getTag(R.id.glide_custom_view_target_tag);
        if (tag == null) {
            return null;
        }
        if (tag instanceof AnonymousClass1h5) {
            return (AnonymousClass1h5) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    @Override // X.AbstractC08541eo
    public final void onStart() {
        Animatable animatable;
        if ((this instanceof AnonymousClass1gY) && (animatable = ((AnonymousClass1gY) this).A00) != null) {
            animatable.start();
        }
    }

    @Override // X.AbstractC08541eo
    public final void onStop() {
        Animatable animatable;
        if ((this instanceof AnonymousClass1gY) && (animatable = ((AnonymousClass1gY) this).A00) != null) {
            animatable.stop();
        }
    }

    @Override // X.AbstractC08781fH
    public final void setRequest(@Nullable AnonymousClass1h5 r3) {
        if (!(this instanceof AbstractC09221gz)) {
            this.A00 = r3;
        } else {
            ((AbstractC09221gz) this).A00.setTag(R.id.glide_custom_view_target_tag, r3);
        }
    }
}
