package androidx.core.app;

import X.AnonymousClass02D;
import X.AnonymousClass06v;
import X.AnonymousClass074;
import X.AnonymousClass07C;
import android.app.Notification;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigTextStyle extends AnonymousClass07C {
    public CharSequence mBigText;

    @Override // X.AnonymousClass07C
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass06v r3) {
        Notification.BigTextStyle bigText = new Notification.BigTextStyle(r3.A3A()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
        if (this.mSummaryTextSet) {
            bigText.setSummaryText(this.mSummaryText);
        }
    }

    public NotificationCompat$BigTextStyle bigText(CharSequence charSequence) {
        this.mBigText = AnonymousClass074.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass074.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass074.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$BigTextStyle() {
    }

    public NotificationCompat$BigTextStyle(AnonymousClass074 r1) {
        setBuilder(r1);
    }
}
