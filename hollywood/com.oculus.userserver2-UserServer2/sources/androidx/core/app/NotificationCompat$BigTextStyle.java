package androidx.core.app;

import X.AnonymousClass2O;
import X.AnonymousClass4I;
import X.AnonymousClass4R;
import X.AnonymousClass4Z;
import androidx.annotation.RestrictTo;

public class NotificationCompat$BigTextStyle extends AnonymousClass4Z {
    public CharSequence mBigText;

    public NotificationCompat$BigTextStyle bigText(CharSequence charSequence) {
        this.mBigText = AnonymousClass4R.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass4R.A00(charSequence);
        return this;
    }

    public NotificationCompat$BigTextStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass4R.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass4I r2) {
        throw null;
    }

    public NotificationCompat$BigTextStyle() {
    }

    public NotificationCompat$BigTextStyle(AnonymousClass4R r1) {
        setBuilder(r1);
    }
}
