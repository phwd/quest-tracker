package androidx.core.app;

import X.AnonymousClass2O;
import X.AnonymousClass4I;
import X.AnonymousClass4R;
import X.AnonymousClass4Z;
import android.graphics.Bitmap;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigPictureStyle extends AnonymousClass4Z {
    public Bitmap mBigLargeIcon;
    public boolean mBigLargeIconSet;
    public Bitmap mPicture;

    public NotificationCompat$BigPictureStyle bigLargeIcon(Bitmap bitmap) {
        this.mBigLargeIcon = bitmap;
        this.mBigLargeIconSet = true;
        return this;
    }

    public NotificationCompat$BigPictureStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass4R.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigPictureStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass4R.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass4I r2) {
        throw null;
    }

    public NotificationCompat$BigPictureStyle bigPicture(Bitmap bitmap) {
        this.mPicture = bitmap;
        return this;
    }

    public NotificationCompat$BigPictureStyle() {
    }

    public NotificationCompat$BigPictureStyle(AnonymousClass4R r1) {
        setBuilder(r1);
    }
}
