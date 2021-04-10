package X;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.Gravity;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* renamed from: X.0w3  reason: invalid class name */
public final class AnonymousClass0w3 extends AnonymousClass05L {
    @Override // X.AnonymousClass05L
    public final void A02(int i, int i2, int i3, Rect rect, Rect rect2) {
        Gravity.apply(i, i2, i3, rect, rect2, 0);
    }

    public final void getOutline(@NonNull Outline outline) {
        A00();
        outline.setRoundRect(this.A07, this.A00);
    }

    public AnonymousClass0w3(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }
}
