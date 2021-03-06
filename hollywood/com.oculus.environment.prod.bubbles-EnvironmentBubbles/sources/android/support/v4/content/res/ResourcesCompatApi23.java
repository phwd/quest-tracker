package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;

class ResourcesCompatApi23 {
    ResourcesCompatApi23() {
    }

    public static int getColor(Resources resources, int i, Resources.Theme theme) throws Resources.NotFoundException {
        return resources.getColor(i, theme);
    }

    public static ColorStateList getColorStateList(Resources resources, int i, Resources.Theme theme) throws Resources.NotFoundException {
        return resources.getColorStateList(i, theme);
    }
}
