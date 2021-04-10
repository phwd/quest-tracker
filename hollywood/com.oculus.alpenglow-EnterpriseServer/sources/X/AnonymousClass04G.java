package X;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.04G  reason: invalid class name */
public final class AnonymousClass04G {
    public AnonymousClass05X A00;
    @NonNull
    public final ImageView A01;

    public final void A00() {
        ImageView imageView = this.A01;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            C002704d.A01(drawable);
            AnonymousClass05X r1 = this.A00;
            if (r1 != null) {
                AnonymousClass17F.A02(drawable, r1, imageView.getDrawableState());
            }
        }
    }

    public final void A01(int i) {
        ImageView imageView;
        Drawable drawable;
        if (i != 0) {
            imageView = this.A01;
            drawable = AnonymousClass17E.A00(imageView.getContext(), i);
            if (drawable != null) {
                C002704d.A01(drawable);
            }
        } else {
            imageView = this.A01;
            drawable = null;
        }
        imageView.setImageDrawable(drawable);
        A00();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b A[Catch:{ all -> 0x009f }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c A[Catch:{ all -> 0x009f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A02(android.util.AttributeSet r11, int r12) {
        /*
        // Method dump skipped, instructions count: 164
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass04G.A02(android.util.AttributeSet, int):void");
    }

    public AnonymousClass04G(@NonNull ImageView imageView) {
        this.A01 = imageView;
    }
}
