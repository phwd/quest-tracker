package androidx.core.app;

import X.AbstractC000803d;
import X.AnonymousClass02C;
import X.AnonymousClass03m;
import X.AnonymousClass03u;
import android.app.Notification;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigTextStyle extends AnonymousClass03u {
    public CharSequence mBigText;

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AbstractC000803d r3) {
        Notification.BigTextStyle bigText = new Notification.BigTextStyle(r3.A3T()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
        if (this.mSummaryTextSet) {
            bigText.setSummaryText(this.mSummaryText);
        }
    }

    public NotificationCompat$BigTextStyle bigText(CharSequence charSequence) {
        this.mBigText = AnonymousClass03m.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass03m.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass03m.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$BigTextStyle() {
    }

    public NotificationCompat$BigTextStyle(AnonymousClass03m r1) {
        setBuilder(r1);
    }
}
