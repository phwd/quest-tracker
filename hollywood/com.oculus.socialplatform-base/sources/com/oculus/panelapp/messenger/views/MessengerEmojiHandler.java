package com.oculus.panelapp.messenger.views;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.messenger.util.EmojiReplacer;

public class MessengerEmojiHandler {
    public static final String TAG = LoggingUtil.tag(MessengerEmojiHandler.class);
    public final Resources mResources;

    public void destroy() {
    }

    @Nullable
    public Drawable getEmojiDrawableFromString(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return new BitmapDrawable(this.mResources, EmojiReplacer.sFallbackSpanProvider.getBitmap(str));
    }

    public MessengerEmojiHandler(Resources resources) {
        this.mResources = resources;
    }
}
