package androidx.core.app;

import X.AnonymousClass02D;
import X.AnonymousClass06v;
import X.AnonymousClass074;
import X.AnonymousClass07C;
import android.app.Notification;
import android.graphics.Bitmap;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigPictureStyle extends AnonymousClass07C {
    public Bitmap mBigLargeIcon;
    public boolean mBigLargeIconSet;
    public Bitmap mPicture;

    public NotificationCompat$BigPictureStyle bigLargeIcon(Bitmap bitmap) {
        this.mBigLargeIcon = bitmap;
        this.mBigLargeIconSet = true;
        return this;
    }

    @Override // X.AnonymousClass07C
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass06v r3) {
        Notification.BigPictureStyle bigPicture = new Notification.BigPictureStyle(r3.A3A()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
        if (this.mBigLargeIconSet) {
            bigPicture.bigLargeIcon(this.mBigLargeIcon);
        }
        if (this.mSummaryTextSet) {
            bigPicture.setSummaryText(this.mSummaryText);
        }
    }

    public NotificationCompat$BigPictureStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass074.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigPictureStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass074.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$BigPictureStyle bigPicture(Bitmap bitmap) {
        this.mPicture = bitmap;
        return this;
    }

    public NotificationCompat$BigPictureStyle() {
    }

    public NotificationCompat$BigPictureStyle(AnonymousClass074 r1) {
        setBuilder(r1);
    }
}
