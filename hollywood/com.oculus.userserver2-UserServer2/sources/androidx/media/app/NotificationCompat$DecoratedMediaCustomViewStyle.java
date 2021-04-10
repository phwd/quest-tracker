package androidx.media.app;

import X.AnonymousClass2O;
import X.AnonymousClass4I;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import com.oculus.userserver2.R;

public class NotificationCompat$DecoratedMediaCustomViewStyle extends NotificationCompat$MediaStyle {
    @Override // androidx.media.app.NotificationCompat$MediaStyle
    public int getBigContentViewLayoutResource(int i) {
        if (i <= 3) {
            return R.layout.notification_template_big_media_narrow_custom;
        }
        return R.layout.notification_template_big_media_custom;
    }

    @Override // androidx.media.app.NotificationCompat$MediaStyle
    public int getContentViewLayoutResource() {
        throw null;
    }

    private void setBackgroundColor(RemoteViews remoteViews) {
        throw null;
    }

    @Override // X.AnonymousClass4Z, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public void apply(AnonymousClass4I r2) {
        throw null;
    }

    @Override // X.AnonymousClass4Z, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public RemoteViews makeBigContentView(AnonymousClass4I r2) {
        return null;
    }

    @Override // X.AnonymousClass4Z, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public RemoteViews makeContentView(AnonymousClass4I r2) {
        return null;
    }

    @Override // X.AnonymousClass4Z
    @RestrictTo({AnonymousClass2O.LIBRARY})
    public RemoteViews makeHeadsUpContentView(AnonymousClass4I r2) {
        return null;
    }
}
