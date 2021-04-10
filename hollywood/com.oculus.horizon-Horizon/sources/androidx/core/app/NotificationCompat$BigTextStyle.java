package androidx.core.app;

import X.AnonymousClass02C;
import X.AnonymousClass03Y;
import X.AnonymousClass03h;
import X.AnonymousClass03p;
import android.app.Notification;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigTextStyle extends AnonymousClass03p {
    public CharSequence mBigText;

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass03Y r3) {
        Notification.BigTextStyle bigText = new Notification.BigTextStyle(r3.A3C()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
        if (this.mSummaryTextSet) {
            bigText.setSummaryText(this.mSummaryText);
        }
    }

    public NotificationCompat$BigTextStyle bigText(CharSequence charSequence) {
        this.mBigText = AnonymousClass03h.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass03h.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass03h.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$BigTextStyle() {
    }

    public NotificationCompat$BigTextStyle(AnonymousClass03h r1) {
        setBuilder(r1);
    }
}
