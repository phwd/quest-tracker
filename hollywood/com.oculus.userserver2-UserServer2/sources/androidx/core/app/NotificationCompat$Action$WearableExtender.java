package androidx.core.app;

import X.AnonymousClass4K;
import X.AnonymousClass4L;
import X.AnonymousClass4N;
import android.os.Bundle;

public final class NotificationCompat$Action$WearableExtender implements AnonymousClass4L {
    public static final int DEFAULT_FLAGS = 1;
    public static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    public static final int FLAG_AVAILABLE_OFFLINE = 1;
    public static final int FLAG_HINT_DISPLAY_INLINE = 4;
    public static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
    public static final String KEY_CANCEL_LABEL = "cancelLabel";
    public static final String KEY_CONFIRM_LABEL = "confirmLabel";
    public static final String KEY_FLAGS = "flags";
    public static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
    public CharSequence mCancelLabel;
    public CharSequence mConfirmLabel;
    public int mFlags = 1;
    public CharSequence mInProgressLabel;

    public NotificationCompat$Action$WearableExtender setAvailableOffline(boolean z) {
        setFlag(1, z);
        return this;
    }

    public NotificationCompat$Action$WearableExtender setHintDisplayActionInline(boolean z) {
        setFlag(4, z);
        return this;
    }

    public NotificationCompat$Action$WearableExtender setHintLaunchesActivity(boolean z) {
        setFlag(2, z);
        return this;
    }

    private void setFlag(int i, boolean z) {
        int i2;
        if (z) {
            i2 = i | this.mFlags;
        } else {
            i2 = (i ^ -1) & this.mFlags;
        }
        this.mFlags = i2;
    }

    public AnonymousClass4K extend(AnonymousClass4K r4) {
        Bundle bundle = new Bundle();
        int i = this.mFlags;
        if (i != 1) {
            bundle.putInt("flags", i);
        }
        CharSequence charSequence = this.mInProgressLabel;
        if (charSequence != null) {
            bundle.putCharSequence(KEY_IN_PROGRESS_LABEL, charSequence);
        }
        CharSequence charSequence2 = this.mConfirmLabel;
        if (charSequence2 != null) {
            bundle.putCharSequence(KEY_CONFIRM_LABEL, charSequence2);
        }
        CharSequence charSequence3 = this.mCancelLabel;
        if (charSequence3 != null) {
            bundle.putCharSequence(KEY_CANCEL_LABEL, charSequence3);
            throw null;
        }
        throw null;
    }

    public boolean getHintDisplayActionInline() {
        if ((this.mFlags & 4) != 0) {
            return true;
        }
        return false;
    }

    public boolean getHintLaunchesActivity() {
        if ((this.mFlags & 2) != 0) {
            return true;
        }
        return false;
    }

    public boolean isAvailableOffline() {
        if ((this.mFlags & 1) == 0) {
            return false;
        }
        return true;
    }

    @Deprecated
    public CharSequence getCancelLabel() {
        return this.mCancelLabel;
    }

    @Deprecated
    public CharSequence getConfirmLabel() {
        return this.mConfirmLabel;
    }

    @Deprecated
    public CharSequence getInProgressLabel() {
        return this.mInProgressLabel;
    }

    @Deprecated
    public NotificationCompat$Action$WearableExtender setCancelLabel(CharSequence charSequence) {
        this.mCancelLabel = charSequence;
        return this;
    }

    @Deprecated
    public NotificationCompat$Action$WearableExtender setConfirmLabel(CharSequence charSequence) {
        this.mConfirmLabel = charSequence;
        return this;
    }

    @Deprecated
    public NotificationCompat$Action$WearableExtender setInProgressLabel(CharSequence charSequence) {
        this.mInProgressLabel = charSequence;
        return this;
    }

    public NotificationCompat$Action$WearableExtender() {
    }

    public NotificationCompat$Action$WearableExtender(AnonymousClass4N r4) {
        Bundle bundle = r4.A05.getBundle("android.wearable.EXTENSIONS");
        if (bundle != null) {
            this.mFlags = bundle.getInt("flags", 1);
            this.mInProgressLabel = bundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
            this.mConfirmLabel = bundle.getCharSequence(KEY_CONFIRM_LABEL);
            this.mCancelLabel = bundle.getCharSequence(KEY_CANCEL_LABEL);
        }
    }

    public NotificationCompat$Action$WearableExtender clone() {
        NotificationCompat$Action$WearableExtender notificationCompat$Action$WearableExtender = new NotificationCompat$Action$WearableExtender();
        notificationCompat$Action$WearableExtender.mFlags = this.mFlags;
        notificationCompat$Action$WearableExtender.mInProgressLabel = this.mInProgressLabel;
        notificationCompat$Action$WearableExtender.mConfirmLabel = this.mConfirmLabel;
        notificationCompat$Action$WearableExtender.mCancelLabel = this.mCancelLabel;
        return notificationCompat$Action$WearableExtender;
    }
}
