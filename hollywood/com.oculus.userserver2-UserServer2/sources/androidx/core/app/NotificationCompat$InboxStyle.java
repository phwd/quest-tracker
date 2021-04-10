package androidx.core.app;

import X.AnonymousClass2O;
import X.AnonymousClass4I;
import X.AnonymousClass4R;
import X.AnonymousClass4Z;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

public class NotificationCompat$InboxStyle extends AnonymousClass4Z {
    public ArrayList<CharSequence> mTexts = new ArrayList<>();

    public NotificationCompat$InboxStyle addLine(CharSequence charSequence) {
        this.mTexts.add(AnonymousClass4R.A00(charSequence));
        return this;
    }

    public NotificationCompat$InboxStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass4R.A00(charSequence);
        return this;
    }

    public NotificationCompat$InboxStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass4R.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass4I r2) {
        throw null;
    }

    public NotificationCompat$InboxStyle() {
    }

    public NotificationCompat$InboxStyle(AnonymousClass4R r2) {
        setBuilder(r2);
    }
}
