package com.oculus.socialplatform.tablet.emoji;

import X.AnonymousClass1oi;
import android.text.Spannable;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MessengerVrEmojiTextWatcher extends AnonymousClass1oi {
    @Nullable
    public final MessengerVrEmojiUtil mEmojiUtil;
    public final int mTextSize;

    @Override // X.AnonymousClass1oi
    public boolean replaceEmoticonsWithEmojis(Spannable spannable, int i, int i2) {
        return false;
    }

    @Override // X.AnonymousClass1oi
    public boolean supportEmoji() {
        return true;
    }

    @Override // X.AnonymousClass1oi
    public boolean supportEmoticons() {
        return false;
    }

    @Override // X.AnonymousClass1oi
    public boolean addSmallEmojiSpans(Spannable spannable, int i, int i2) {
        MessengerVrEmojiUtil messengerVrEmojiUtil = this.mEmojiUtil;
        if (messengerVrEmojiUtil == null || !messengerVrEmojiUtil.addEmojiSpans(spannable, this.mTextSize, i, i2, false)) {
            return false;
        }
        return true;
    }

    public MessengerVrEmojiTextWatcher(MessengerVrEmojiUtil messengerVrEmojiUtil, int i) {
        this.mEmojiUtil = messengerVrEmojiUtil;
        this.mTextSize = i;
    }
}
