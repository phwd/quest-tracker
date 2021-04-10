package androidx.core.app;

import X.AnonymousClass02C;
import X.AnonymousClass03Y;
import X.AnonymousClass03h;
import X.AnonymousClass03p;
import android.app.Notification;
import android.graphics.Bitmap;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigPictureStyle extends AnonymousClass03p {
    public Bitmap mBigLargeIcon;
    public boolean mBigLargeIconSet;
    public Bitmap mPicture;

    public NotificationCompat$BigPictureStyle bigLargeIcon(Bitmap bitmap) {
        this.mBigLargeIcon = bitmap;
        this.mBigLargeIconSet = true;
        return this;
    }

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass03Y r3) {
        Notification.BigPictureStyle bigPicture = new Notification.BigPictureStyle(r3.A3C()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
        if (this.mBigLargeIconSet) {
            bigPicture.bigLargeIcon(this.mBigLargeIcon);
        }
        if (this.mSummaryTextSet) {
            bigPicture.setSummaryText(this.mSummaryText);
        }
    }

    public NotificationCompat$BigPictureStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass03h.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigPictureStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass03h.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$BigPictureStyle bigPicture(Bitmap bitmap) {
        this.mPicture = bitmap;
        return this;
    }

    public NotificationCompat$BigPictureStyle() {
    }

    public NotificationCompat$BigPictureStyle(AnonymousClass03h r1) {
        setBuilder(r1);
    }
}
