package androidx.core.app;

import X.AnonymousClass2C;
import X.AnonymousClass3Y;
import X.AnonymousClass3h;
import X.AnonymousClass3p;
import android.graphics.Bitmap;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigPictureStyle extends AnonymousClass3p {
    public Bitmap mBigLargeIcon;
    public boolean mBigLargeIconSet;
    public Bitmap mPicture;

    public NotificationCompat$BigPictureStyle bigLargeIcon(Bitmap bitmap) {
        this.mBigLargeIcon = bitmap;
        this.mBigLargeIconSet = true;
        return this;
    }

    public NotificationCompat$BigPictureStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass3h.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigPictureStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass3h.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    @Override // X.AnonymousClass3p
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass3Y r2) {
        throw null;
    }

    public NotificationCompat$BigPictureStyle bigPicture(Bitmap bitmap) {
        this.mPicture = bitmap;
        return this;
    }

    public NotificationCompat$BigPictureStyle() {
    }

    public NotificationCompat$BigPictureStyle(AnonymousClass3h r1) {
        setBuilder(r1);
    }
}
