package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.PointerIcon;

class PointerIconCompatApi24 {
    PointerIconCompatApi24() {
    }

    public static Object getSystemIcon(Context context, int i) {
        return PointerIcon.getSystemIcon(context, i);
    }

    public static Object create(Bitmap bitmap, float f, float f2) {
        return PointerIcon.create(bitmap, f, f2);
    }

    public static Object load(Resources resources, int i) {
        return PointerIcon.load(resources, i);
    }
}
