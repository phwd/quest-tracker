package androidx.media.app;

import X.AnonymousClass2C;
import X.AnonymousClass3Y;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import com.oculus.unifiedtelemetry.R;

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

    @Override // X.AnonymousClass3p, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass2C.LIBRARY})
    public void apply(AnonymousClass3Y r2) {
        throw null;
    }

    @Override // X.AnonymousClass3p, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass2C.LIBRARY})
    public RemoteViews makeBigContentView(AnonymousClass3Y r2) {
        return null;
    }

    @Override // X.AnonymousClass3p, androidx.media.app.NotificationCompat$MediaStyle
    @RestrictTo({AnonymousClass2C.LIBRARY})
    public RemoteViews makeContentView(AnonymousClass3Y r2) {
        return null;
    }

    @Override // X.AnonymousClass3p
    @RestrictTo({AnonymousClass2C.LIBRARY})
    public RemoteViews makeHeadsUpContentView(AnonymousClass3Y r2) {
        return null;
    }
}
