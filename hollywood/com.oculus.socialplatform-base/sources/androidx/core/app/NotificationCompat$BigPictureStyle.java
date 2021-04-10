package androidx.core.app;

import X.AbstractC000803d;
import X.AnonymousClass02C;
import X.AnonymousClass03m;
import X.AnonymousClass03u;
import android.app.Notification;
import android.graphics.Bitmap;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigPictureStyle extends AnonymousClass03u {
    public Bitmap mBigLargeIcon;
    public boolean mBigLargeIconSet;
    public Bitmap mPicture;

    public NotificationCompat$BigPictureStyle bigLargeIcon(Bitmap bitmap) {
        this.mBigLargeIcon = bitmap;
        this.mBigLargeIconSet = true;
        return this;
    }

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AbstractC000803d r3) {
        Notification.BigPictureStyle bigPicture = new Notification.BigPictureStyle(r3.A3T()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
        if (this.mBigLargeIconSet) {
            bigPicture.bigLargeIcon(this.mBigLargeIcon);
        }
        if (this.mSummaryTextSet) {
            bigPicture.setSummaryText(this.mSummaryText);
        }
    }

    public NotificationCompat$BigPictureStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass03m.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigPictureStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass03m.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$BigPictureStyle bigPicture(Bitmap bitmap) {
        this.mPicture = bitmap;
        return this;
    }

    public NotificationCompat$BigPictureStyle() {
    }

    public NotificationCompat$BigPictureStyle(AnonymousClass03m r1) {
        setBuilder(r1);
    }
}
