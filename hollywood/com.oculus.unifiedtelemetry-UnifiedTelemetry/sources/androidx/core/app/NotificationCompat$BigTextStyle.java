package androidx.core.app;

import X.AnonymousClass2C;
import X.AnonymousClass3Y;
import X.AnonymousClass3h;
import X.AnonymousClass3p;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigTextStyle extends AnonymousClass3p {
    public CharSequence mBigText;

    public NotificationCompat$BigTextStyle bigText(CharSequence charSequence) {
        this.mBigText = AnonymousClass3h.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass3h.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass3h.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    @Override // X.AnonymousClass3p
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass3Y r2) {
        throw null;
    }

    public NotificationCompat$BigTextStyle() {
    }

    public NotificationCompat$BigTextStyle(AnonymousClass3h r1) {
        setBuilder(r1);
    }
}
