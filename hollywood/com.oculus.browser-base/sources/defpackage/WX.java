package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

/* renamed from: WX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WX extends LayerDrawable {
    public WX(Drawable[] drawableArr) {
        super(drawableArr);
    }

    public static WX a(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{16843284});
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return new WX(new Drawable[]{drawable});
    }

    public void onBoundsChange(Rect rect) {
        setLayerInset(0, 0, rect.height() - getDrawable(0).getIntrinsicHeight(), 0, 0);
        super.onBoundsChange(rect);
    }
}
