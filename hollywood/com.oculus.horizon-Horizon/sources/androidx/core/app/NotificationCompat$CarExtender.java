package androidx.core.app;

import X.AbstractC001003k;
import X.AnonymousClass02C;
import X.AnonymousClass03h;
import X.AnonymousClass03j;
import X.AnonymousClass045;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

public final class NotificationCompat$CarExtender implements AbstractC001003k {
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
    public static final String EXTRA_COLOR = "app_color";
    public static final String EXTRA_CONVERSATION = "car_conversation";
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
    public static final String EXTRA_LARGE_ICON = "large_icon";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_MESSAGES = "messages";
    public static final String KEY_ON_READ = "on_read";
    public static final String KEY_ON_REPLY = "on_reply";
    public static final String KEY_PARTICIPANTS = "participants";
    public static final String KEY_REMOTE_INPUT = "remote_input";
    public static final String KEY_TEXT = "text";
    public static final String KEY_TIMESTAMP = "timestamp";
    public int mColor = 0;
    public Bitmap mLargeIcon;
    public AnonymousClass03j mUnreadConversation;

    @RequiresApi(21)
    public static AnonymousClass03j getUnreadConversationFromBundle(@Nullable Bundle bundle) {
        String[] strArr;
        int i;
        AnonymousClass045 r7 = null;
        if (bundle != null) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("messages");
            if (parcelableArray != null) {
                int length = parcelableArray.length;
                strArr = new String[length];
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        if (!(parcelableArray[i2] instanceof Bundle)) {
                            break;
                        }
                        strArr[i2] = ((Bundle) parcelableArray[i2]).getString(KEY_TEXT);
                        if (strArr[i2] == null) {
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(KEY_ON_READ);
            PendingIntent pendingIntent2 = (PendingIntent) bundle.getParcelable(KEY_ON_REPLY);
            RemoteInput remoteInput = (RemoteInput) bundle.getParcelable(KEY_REMOTE_INPUT);
            String[] stringArray = bundle.getStringArray(KEY_PARTICIPANTS);
            if (stringArray != null && stringArray.length == 1) {
                if (remoteInput != null) {
                    String resultKey = remoteInput.getResultKey();
                    CharSequence label = remoteInput.getLabel();
                    CharSequence[] choices = remoteInput.getChoices();
                    boolean allowFreeFormInput = remoteInput.getAllowFreeFormInput();
                    if (Build.VERSION.SDK_INT >= 29) {
                        i = remoteInput.getEditChoicesBeforeSending();
                    } else {
                        i = 0;
                    }
                    r7 = new AnonymousClass045(resultKey, label, choices, allowFreeFormInput, i, remoteInput.getExtras());
                }
                return new AnonymousClass03j(strArr, r7, pendingIntent2, pendingIntent, stringArray, bundle.getLong(KEY_TIMESTAMP));
            }
        }
        return null;
    }

    @RequiresApi(21)
    public static Bundle getBundleForUnreadConversation(@NonNull AnonymousClass03j r10) {
        String str;
        Bundle bundle = new Bundle();
        String[] strArr = r10.A05;
        if (strArr == null || strArr.length <= 1) {
            str = null;
        } else {
            str = strArr[0];
        }
        String[] strArr2 = r10.A04;
        int length = strArr2.length;
        Parcelable[] parcelableArr = new Parcelable[length];
        for (int i = 0; i < length; i++) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(KEY_TEXT, strArr2[i]);
            bundle2.putString(KEY_AUTHOR, str);
            parcelableArr[i] = bundle2;
        }
        bundle.putParcelableArray("messages", parcelableArr);
        AnonymousClass045 r4 = r10.A03;
        if (r4 != null) {
            bundle.putParcelable(KEY_REMOTE_INPUT, new RemoteInput.Builder(r4.A03).setLabel(r4.A02).setChoices(r4.A05).setAllowFreeFormInput(r4.A04).addExtras(r4.A01).build());
        }
        bundle.putParcelable(KEY_ON_REPLY, r10.A02);
        bundle.putParcelable(KEY_ON_READ, r10.A01);
        bundle.putStringArray(KEY_PARTICIPANTS, strArr);
        bundle.putLong(KEY_TIMESTAMP, r10.A00);
        return bundle;
    }

    public AnonymousClass03h extend(AnonymousClass03h r4) {
        Bundle bundle = new Bundle();
        Bitmap bitmap = this.mLargeIcon;
        if (bitmap != null) {
            bundle.putParcelable(EXTRA_LARGE_ICON, bitmap);
        }
        int i = this.mColor;
        if (i != 0) {
            bundle.putInt(EXTRA_COLOR, i);
        }
        AnonymousClass03j r0 = this.mUnreadConversation;
        if (r0 != null) {
            bundle.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(r0));
        }
        Bundle bundle2 = r4.A0C;
        if (bundle2 == null) {
            bundle2 = new Bundle();
            r4.A0C = bundle2;
        }
        bundle2.putBundle(EXTRA_CAR_EXTENDER, bundle);
        return r4;
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    public Bitmap getLargeIcon() {
        return this.mLargeIcon;
    }

    @Deprecated
    public AnonymousClass03j getUnreadConversation() {
        return this.mUnreadConversation;
    }

    public NotificationCompat$CarExtender setColor(@ColorInt int i) {
        this.mColor = i;
        return this;
    }

    public NotificationCompat$CarExtender setLargeIcon(Bitmap bitmap) {
        this.mLargeIcon = bitmap;
        return this;
    }

    @Deprecated
    public NotificationCompat$CarExtender setUnreadConversation(AnonymousClass03j r1) {
        this.mUnreadConversation = r1;
        return this;
    }

    public NotificationCompat$CarExtender() {
    }

    public NotificationCompat$CarExtender(Notification notification) {
        Bundle bundle;
        Bundle bundle2 = notification.extras;
        if (bundle2 != null && (bundle = bundle2.getBundle(EXTRA_CAR_EXTENDER)) != null) {
            this.mLargeIcon = (Bitmap) bundle.getParcelable(EXTRA_LARGE_ICON);
            this.mColor = bundle.getInt(EXTRA_COLOR, 0);
            this.mUnreadConversation = getUnreadConversationFromBundle(bundle.getBundle(EXTRA_CONVERSATION));
        }
    }
}
