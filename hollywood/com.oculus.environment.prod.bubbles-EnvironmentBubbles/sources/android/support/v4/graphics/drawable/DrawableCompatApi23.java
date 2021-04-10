package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompatApi23 {
    DrawableCompatApi23() {
    }

    public static boolean setLayoutDirection(Drawable drawable, int i) {
        return drawable.setLayoutDirection(i);
    }

    public static int getLayoutDirection(Drawable drawable) {
        return drawable.getLayoutDirection();
    }
}
