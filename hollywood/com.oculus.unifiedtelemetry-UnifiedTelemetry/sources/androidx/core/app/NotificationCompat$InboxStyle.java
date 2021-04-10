package androidx.core.app;

import X.AnonymousClass2C;
import X.AnonymousClass3Y;
import X.AnonymousClass3h;
import X.AnonymousClass3p;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

public class NotificationCompat$InboxStyle extends AnonymousClass3p {
    public ArrayList<CharSequence> mTexts = new ArrayList<>();

    public NotificationCompat$InboxStyle addLine(CharSequence charSequence) {
        this.mTexts.add(AnonymousClass3h.A00(charSequence));
        return this;
    }

    public NotificationCompat$InboxStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass3h.A00(charSequence);
        return this;
    }

    public NotificationCompat$InboxStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass3h.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    @Override // X.AnonymousClass3p
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass3Y r2) {
        throw null;
    }

    public NotificationCompat$InboxStyle() {
    }

    public NotificationCompat$InboxStyle(AnonymousClass3h r2) {
        setBuilder(r2);
    }
}
