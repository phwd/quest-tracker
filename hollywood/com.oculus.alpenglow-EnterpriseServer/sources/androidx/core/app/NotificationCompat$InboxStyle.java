package androidx.core.app;

import X.AnonymousClass02D;
import X.AnonymousClass06v;
import X.AnonymousClass074;
import X.AnonymousClass07C;
import android.app.Notification;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationCompat$InboxStyle extends AnonymousClass07C {
    public ArrayList<CharSequence> mTexts = new ArrayList<>();

    public NotificationCompat$InboxStyle addLine(CharSequence charSequence) {
        this.mTexts.add(AnonymousClass074.A00(charSequence));
        return this;
    }

    @Override // X.AnonymousClass07C
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass06v r4) {
        Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(r4.A3A()).setBigContentTitle(this.mBigContentTitle);
        if (this.mSummaryTextSet) {
            bigContentTitle.setSummaryText(this.mSummaryText);
        }
        Iterator<CharSequence> it = this.mTexts.iterator();
        while (it.hasNext()) {
            bigContentTitle.addLine(it.next());
        }
    }

    public NotificationCompat$InboxStyle setBigContentTitle(CharSequence charSequence) {
        this.mBigContentTitle = AnonymousClass074.A00(charSequence);
        return this;
    }

    public NotificationCompat$InboxStyle setSummaryText(CharSequence charSequence) {
        this.mSummaryText = AnonymousClass074.A00(charSequence);
        this.mSummaryTextSet = true;
        return this;
    }

    public NotificationCompat$InboxStyle() {
    }

    public NotificationCompat$InboxStyle(AnonymousClass074 r2) {
        setBuilder(r2);
    }
}
