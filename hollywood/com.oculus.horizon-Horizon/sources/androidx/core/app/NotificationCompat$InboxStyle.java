package androidx.core.app;

import X.AnonymousClass02C;
import X.AnonymousClass03Y;
import X.AnonymousClass03h;
import X.AnonymousClass03p;
import android.app.Notification;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationCompat$InboxStyle extends AnonymousClass03p {
    public ArrayList<CharSequence> mTexts = new ArrayList<>();

    public NotificationCompat$InboxStyle addLine(CharSequence charSequence) {
        this.mTexts.add(AnonymousClass03h.A00(charSequence));
        return this;
    }

    @Override // X.AnonymousClass03p
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass03Y r4) {
        Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(r4.A3C()).setBigContentTitle(this.mBigContentTitle);
        if (this.mSummaryTextSet) {
            bigContentTitle.setSummaryText(this.mSummaryText);
        }
        Iterator<CharSequence> it = this.mTexts.iterator();
        while (it.hasNext()) {
            bigContentTitle.addLine(it.next());
        }
    }

    public NotificationCompat$InboxStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass03h.A00(charSequence);
        return this;
    }

    public NotificationCompat$InboxStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass03h.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$InboxStyle() {
    }

    public NotificationCompat$InboxStyle(AnonymousClass03h r2) {
        setBuilder(r2);
    }
}
