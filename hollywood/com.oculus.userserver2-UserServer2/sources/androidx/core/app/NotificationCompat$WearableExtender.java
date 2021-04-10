package androidx.core.app;

import X.AnonymousClass4N;
import X.AnonymousClass4R;
import X.AnonymousClass4U;
import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class NotificationCompat$WearableExtender implements AnonymousClass4U {
    public static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
    public static final int DEFAULT_FLAGS = 1;
    public static final int DEFAULT_GRAVITY = 80;
    public static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    public static final int FLAG_BIG_PICTURE_AMBIENT = 32;
    public static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
    public static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
    public static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
    public static final int FLAG_HINT_HIDE_ICON = 2;
    public static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
    public static final int FLAG_START_SCROLL_BOTTOM = 8;
    public static final String KEY_ACTIONS = "actions";
    public static final String KEY_BACKGROUND = "background";
    public static final String KEY_BRIDGE_TAG = "bridgeTag";
    public static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
    public static final String KEY_CONTENT_ICON = "contentIcon";
    public static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
    public static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
    public static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
    public static final String KEY_DISMISSAL_ID = "dismissalId";
    public static final String KEY_DISPLAY_INTENT = "displayIntent";
    public static final String KEY_FLAGS = "flags";
    public static final String KEY_GRAVITY = "gravity";
    public static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
    public static final String KEY_PAGES = "pages";
    @Deprecated
    public static final int SCREEN_TIMEOUT_LONG = -1;
    @Deprecated
    public static final int SCREEN_TIMEOUT_SHORT = 0;
    @Deprecated
    public static final int SIZE_DEFAULT = 0;
    @Deprecated
    public static final int SIZE_FULL_SCREEN = 5;
    @Deprecated
    public static final int SIZE_LARGE = 4;
    @Deprecated
    public static final int SIZE_MEDIUM = 3;
    @Deprecated
    public static final int SIZE_SMALL = 2;
    @Deprecated
    public static final int SIZE_XSMALL = 1;
    public static final int UNSET_ACTION_INDEX = -1;
    public ArrayList<AnonymousClass4N> mActions = new ArrayList<>();
    public Bitmap mBackground;
    public String mBridgeTag;
    public int mContentActionIndex = -1;
    public int mContentIcon;
    public int mContentIconGravity = DEFAULT_CONTENT_ICON_GRAVITY;
    public int mCustomContentHeight;
    public int mCustomSizePreset = 0;
    public String mDismissalId;
    public PendingIntent mDisplayIntent;
    public int mFlags = 1;
    public int mGravity = 80;
    public int mHintScreenTimeout;
    public ArrayList<Notification> mPages = new ArrayList<>();

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @androidx.annotation.RequiresApi(20)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.app.Notification.Action getActionFromActionCompat(X.AnonymousClass4N r5) {
        /*
            r4 = 0
            androidx.core.graphics.drawable.IconCompat r3 = r5.A02
            if (r3 != 0) goto L_0x0055
            int r2 = r5.A00
            if (r2 == 0) goto L_0x0055
            java.lang.String r1 = ""
            r0 = 2
            androidx.core.graphics.drawable.IconCompat r3 = new androidx.core.graphics.drawable.IconCompat
            r3.<init>(r0)
            r3.A00 = r2
            r3.A06 = r1
            r5.A02 = r3
        L_0x0017:
            android.graphics.drawable.Icon r2 = androidx.core.graphics.drawable.IconCompat.A02(r3)
        L_0x001b:
            java.lang.CharSequence r1 = r5.A03
            android.app.PendingIntent r0 = r5.A01
            android.app.Notification$Action$Builder r3 = new android.app.Notification$Action$Builder
            r3.<init>(r2, r1, r0)
            android.os.Bundle r0 = r5.A05
            if (r0 == 0) goto L_0x004f
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>(r0)
        L_0x002d:
            boolean r1 = r5.A04
            java.lang.String r0 = "android.support.allowGeneratedReplies"
            r2.putBoolean(r0, r1)
            boolean r0 = r5.A04
            r3.setAllowGeneratedReplies(r0)
            r3.addExtras(r2)
            X.4p[] r0 = r5.A07
            if (r0 == 0) goto L_0x0059
            android.app.RemoteInput[] r2 = X.AnonymousClass4p.A00(r0)
            int r1 = r2.length
        L_0x0045:
            if (r4 >= r1) goto L_0x0059
            r0 = r2[r4]
            r3.addRemoteInput(r0)
            int r4 = r4 + 1
            goto L_0x0045
        L_0x004f:
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            goto L_0x002d
        L_0x0055:
            if (r3 != 0) goto L_0x0017
            r2 = 0
            goto L_0x001b
        L_0x0059:
            android.app.Notification$Action r0 = r3.build()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat$WearableExtender.getActionFromActionCompat(X.4N):android.app.Notification$Action");
    }

    public NotificationCompat$WearableExtender setContentIntentAvailableOffline(boolean z) {
        setFlag(1, z);
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setHintHideIcon(boolean z) {
        setFlag(2, z);
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setHintShowBackgroundOnly(boolean z) {
        setFlag(4, z);
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

    public NotificationCompat$WearableExtender addAction(AnonymousClass4N r2) {
        this.mActions.add(r2);
        return this;
    }

    public NotificationCompat$WearableExtender addActions(List<AnonymousClass4N> list) {
        this.mActions.addAll(list);
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender addPage(Notification notification) {
        this.mPages.add(notification);
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender addPages(List<Notification> list) {
        this.mPages.addAll(list);
        return this;
    }

    public NotificationCompat$WearableExtender clearActions() {
        this.mActions.clear();
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender clearPages() {
        this.mPages.clear();
        return this;
    }

    public AnonymousClass4R extend(AnonymousClass4R r6) {
        Bundle bundle = new Bundle();
        ArrayList<AnonymousClass4N> arrayList = this.mActions;
        if (!arrayList.isEmpty()) {
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(arrayList.size());
            Iterator<AnonymousClass4N> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(getActionFromActionCompat(it.next()));
            }
            bundle.putParcelableArrayList(KEY_ACTIONS, arrayList2);
        }
        int i = this.mFlags;
        if (i != 1) {
            bundle.putInt("flags", i);
        }
        PendingIntent pendingIntent = this.mDisplayIntent;
        if (pendingIntent != null) {
            bundle.putParcelable(KEY_DISPLAY_INTENT, pendingIntent);
        }
        ArrayList<Notification> arrayList3 = this.mPages;
        if (!arrayList3.isEmpty()) {
            bundle.putParcelableArray(KEY_PAGES, (Parcelable[]) arrayList3.toArray(new Notification[arrayList3.size()]));
        }
        Bitmap bitmap = this.mBackground;
        if (bitmap != null) {
            bundle.putParcelable(KEY_BACKGROUND, bitmap);
        }
        int i2 = this.mContentIcon;
        if (i2 != 0) {
            bundle.putInt(KEY_CONTENT_ICON, i2);
        }
        int i3 = this.mContentIconGravity;
        if (i3 != 8388613) {
            bundle.putInt(KEY_CONTENT_ICON_GRAVITY, i3);
        }
        int i4 = this.mContentActionIndex;
        if (i4 != -1) {
            bundle.putInt(KEY_CONTENT_ACTION_INDEX, i4);
        }
        int i5 = this.mCustomSizePreset;
        if (i5 != 0) {
            bundle.putInt(KEY_CUSTOM_SIZE_PRESET, i5);
        }
        int i6 = this.mCustomContentHeight;
        if (i6 != 0) {
            bundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, i6);
        }
        int i7 = this.mGravity;
        if (i7 != 80) {
            bundle.putInt(KEY_GRAVITY, i7);
        }
        int i8 = this.mHintScreenTimeout;
        if (i8 != 0) {
            bundle.putInt(KEY_HINT_SCREEN_TIMEOUT, i8);
        }
        String str = this.mDismissalId;
        if (str != null) {
            bundle.putString(KEY_DISMISSAL_ID, str);
        }
        String str2 = this.mBridgeTag;
        if (str2 != null) {
            bundle.putString(KEY_BRIDGE_TAG, str2);
            throw null;
        }
        throw null;
    }

    public boolean getContentIntentAvailableOffline() {
        if ((this.mFlags & 1) == 0) {
            return false;
        }
        return true;
    }

    @Deprecated
    public boolean getHintAmbientBigPicture() {
        if ((this.mFlags & 32) != 0) {
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean getHintAvoidBackgroundClipping() {
        if ((this.mFlags & 16) != 0) {
            return true;
        }
        return false;
    }

    public boolean getHintContentIntentLaunchesActivity() {
        if ((this.mFlags & 64) != 0) {
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean getHintHideIcon() {
        if ((this.mFlags & 2) != 0) {
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean getHintShowBackgroundOnly() {
        if ((this.mFlags & 4) != 0) {
            return true;
        }
        return false;
    }

    public boolean getStartScrollBottom() {
        if ((this.mFlags & 8) != 0) {
            return true;
        }
        return false;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setHintAmbientBigPicture(boolean z) {
        setFlag(32, z);
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setHintAvoidBackgroundClipping(boolean z) {
        setFlag(16, z);
        return this;
    }

    public NotificationCompat$WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
        setFlag(64, z);
        return this;
    }

    public NotificationCompat$WearableExtender setStartScrollBottom(boolean z) {
        setFlag(8, z);
        return this;
    }

    public List<AnonymousClass4N> getActions() {
        return this.mActions;
    }

    @Deprecated
    public Bitmap getBackground() {
        return this.mBackground;
    }

    public String getBridgeTag() {
        return this.mBridgeTag;
    }

    public int getContentAction() {
        return this.mContentActionIndex;
    }

    @Deprecated
    public int getContentIcon() {
        return this.mContentIcon;
    }

    @Deprecated
    public int getContentIconGravity() {
        return this.mContentIconGravity;
    }

    @Deprecated
    public int getCustomContentHeight() {
        return this.mCustomContentHeight;
    }

    @Deprecated
    public int getCustomSizePreset() {
        return this.mCustomSizePreset;
    }

    public String getDismissalId() {
        return this.mDismissalId;
    }

    @Deprecated
    public PendingIntent getDisplayIntent() {
        return this.mDisplayIntent;
    }

    @Deprecated
    public int getGravity() {
        return this.mGravity;
    }

    @Deprecated
    public int getHintScreenTimeout() {
        return this.mHintScreenTimeout;
    }

    @Deprecated
    public List<Notification> getPages() {
        return this.mPages;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setBackground(Bitmap bitmap) {
        this.mBackground = bitmap;
        return this;
    }

    public NotificationCompat$WearableExtender setBridgeTag(String str) {
        this.mBridgeTag = str;
        return this;
    }

    public NotificationCompat$WearableExtender setContentAction(int i) {
        this.mContentActionIndex = i;
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setContentIcon(int i) {
        this.mContentIcon = i;
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setContentIconGravity(int i) {
        this.mContentIconGravity = i;
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setCustomContentHeight(int i) {
        this.mCustomContentHeight = i;
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setCustomSizePreset(int i) {
        this.mCustomSizePreset = i;
        return this;
    }

    public NotificationCompat$WearableExtender setDismissalId(String str) {
        this.mDismissalId = str;
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setDisplayIntent(PendingIntent pendingIntent) {
        this.mDisplayIntent = pendingIntent;
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setGravity(int i) {
        this.mGravity = i;
        return this;
    }

    @Deprecated
    public NotificationCompat$WearableExtender setHintScreenTimeout(int i) {
        this.mHintScreenTimeout = i;
        return this;
    }

    public NotificationCompat$WearableExtender() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006c, code lost:
        if (r5.getAllowGeneratedReplies() != false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0155, code lost:
        if (r7 != null) goto L_0x0157;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NotificationCompat$WearableExtender(android.app.Notification r35) {
        /*
        // Method dump skipped, instructions count: 433
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat$WearableExtender.<init>(android.app.Notification):void");
    }

    public NotificationCompat$WearableExtender clone() {
        NotificationCompat$WearableExtender notificationCompat$WearableExtender = new NotificationCompat$WearableExtender();
        notificationCompat$WearableExtender.mActions = new ArrayList<>(this.mActions);
        notificationCompat$WearableExtender.mFlags = this.mFlags;
        notificationCompat$WearableExtender.mDisplayIntent = this.mDisplayIntent;
        notificationCompat$WearableExtender.mPages = new ArrayList<>(this.mPages);
        notificationCompat$WearableExtender.mBackground = this.mBackground;
        notificationCompat$WearableExtender.mContentIcon = this.mContentIcon;
        notificationCompat$WearableExtender.mContentIconGravity = this.mContentIconGravity;
        notificationCompat$WearableExtender.mContentActionIndex = this.mContentActionIndex;
        notificationCompat$WearableExtender.mCustomSizePreset = this.mCustomSizePreset;
        notificationCompat$WearableExtender.mCustomContentHeight = this.mCustomContentHeight;
        notificationCompat$WearableExtender.mGravity = this.mGravity;
        notificationCompat$WearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
        notificationCompat$WearableExtender.mDismissalId = this.mDismissalId;
        notificationCompat$WearableExtender.mBridgeTag = this.mBridgeTag;
        return notificationCompat$WearableExtender;
    }
}
