package androidx.core.app;

import X.AbstractC000803d;
import X.AnonymousClass02C;
import X.AnonymousClass03m;
import X.AnonymousClass03u;
import android.app.Notification;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationCompat$InboxStyle extends AnonymousClass03u {
    public ArrayList<CharSequence> mTexts = new ArrayList<>();

    public NotificationCompat$InboxStyle addLine(CharSequence charSequence) {
        this.mTexts.add(AnonymousClass03m.A00(charSequence));
        return this;
    }

    @Override // X.AnonymousClass03u
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AbstractC000803d r4) {
        Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(r4.A3T()).setBigContentTitle(this.mBigContentTitle);
        if (this.mSummaryTextSet) {
            bigContentTitle.setSummaryText(this.mSummaryText);
        }
        Iterator<CharSequence> it = this.mTexts.iterator();
        while (it.hasNext()) {
            bigContentTitle.addLine(it.next());
        }
    }

    public NotificationCompat$InboxStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass03m.A00(charSequence);
        return this;
    }

    public NotificationCompat$InboxStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass03m.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$InboxStyle() {
    }

    public NotificationCompat$InboxStyle(AnonymousClass03m r2) {
        setBuilder(r2);
    }
}
