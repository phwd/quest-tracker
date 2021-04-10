package com.facebook.drawee.interfaces;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface DraweeHierarchy {
    Rect getBounds();

    Drawable getTopLevelDrawable();
}
