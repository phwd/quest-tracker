package X;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1qP  reason: invalid class name and case insensitive filesystem */
public final class C11001qP {
    public C11101qc A00;
    @NonNull
    public final ImageView A01;

    public final void A00() {
        ImageView imageView = this.A01;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            C10811pi.A01(drawable);
            C11101qc r1 = this.A00;
            if (r1 != null) {
                C10821pj.A02(drawable, r1, imageView.getDrawableState());
            }
        }
    }

    public final void A01(int i) {
        ImageView imageView;
        Drawable drawable;
        if (i != 0) {
            imageView = this.A01;
            drawable = AnonymousClass1pW.A00(imageView.getContext(), i);
            if (drawable != null) {
                C10811pi.A01(drawable);
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
        throw new UnsupportedOperationException("Method not decompiled: X.C11001qP.A02(android.util.AttributeSet, int):void");
    }

    public C11001qP(@NonNull ImageView imageView) {
        this.A01 = imageView;
    }
}
