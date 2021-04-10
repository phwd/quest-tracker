package com.oculus.socialplatform.tablet.emoji;

import X.AnonymousClass0MD;
import X.AnonymousClass1oj;
import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ui.emoji.model.Emoji;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MessengerVrEmojiUtil extends AnonymousClass1oj {
    public static final String TAG = "MessengerVrEmojiUtil";
    public MessengerVrFallbackSpanProvider mFallbackSpanProvider;
    public Resources mResources;

    @Override // X.AnonymousClass1oj
    @Nullable
    public Object createTypefaceEmojiSpan(int i) {
        return null;
    }

    @Override // X.AnonymousClass1oj
    @Nullable
    public Object createTypefaceEmoticonSpan(Emoji emoji, int i) {
        return null;
    }

    @Override // X.AnonymousClass1oj
    @Nullable
    public String getEmojiFromEmoticon(String str) {
        return null;
    }

    @Override // X.AnonymousClass1oj
    public boolean isInDebugMode() {
        return false;
    }

    @Override // X.AnonymousClass1oj
    public boolean useBitmapFallback() {
        return true;
    }

    @Override // X.AnonymousClass1oj
    public void addDebugSpan(Spannable spannable, int i, int i2) {
        throw new UnsupportedOperationException("Debug spans aren't supported.");
    }

    @Override // X.AnonymousClass1oj
    public void complainLoudly(String str, Object... objArr) {
        AnonymousClass0MD.A0B(TAG, str, objArr);
    }

    @Override // X.AnonymousClass1oj
    @Nullable
    public Object createFallbackSpan(CharSequence charSequence, int i, int i2, int i3) {
        return this.mFallbackSpanProvider.getEmojiSpan(this.mResources, charSequence, i, i2, i3);
    }

    public MessengerVrEmojiUtil(Context context, MessengerVrFallbackSpanProvider messengerVrFallbackSpanProvider) {
        this.mResources = context.getResources();
        this.mFallbackSpanProvider = messengerVrFallbackSpanProvider;
    }
}
