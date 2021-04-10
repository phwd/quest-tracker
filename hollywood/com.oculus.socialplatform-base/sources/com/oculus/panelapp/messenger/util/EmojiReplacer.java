package com.oculus.panelapp.messenger.util;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.tablet.emoji.MessengerVrEmojiUtil;
import com.oculus.socialplatform.tablet.emoji.MessengerVrFallbackSpanProvider;

public class EmojiReplacer {
    public static final String PATH_TO_FACEBOOK_EMOJI_FONT = "/system/fonts/FacebookEmoji64.ttf";
    public static final String TAG = "EmojiReplacer";
    @Nullable
    public static MessengerVrEmojiUtil sEmojiUtil;
    @Nullable
    public static MessengerVrFallbackSpanProvider sFallbackSpanProvider;

    public static void destroy() {
        sEmojiUtil = null;
        sFallbackSpanProvider = null;
    }

    @Nullable
    public static MessengerVrEmojiUtil getEmojiUtil() {
        return sEmojiUtil;
    }

    @Nullable
    public static MessengerVrFallbackSpanProvider getFallbackSpanProvider() {
        return sFallbackSpanProvider;
    }

    public static void initialize(Context context) {
        try {
            MessengerVrFallbackSpanProvider messengerVrFallbackSpanProvider = new MessengerVrFallbackSpanProvider(PATH_TO_FACEBOOK_EMOJI_FONT);
            sFallbackSpanProvider = messengerVrFallbackSpanProvider;
            sEmojiUtil = new MessengerVrEmojiUtil(context, messengerVrFallbackSpanProvider);
        } catch (Exception e) {
            Log.e(TAG, "Exception initializing EmojiReplacer", e);
        }
    }
}
