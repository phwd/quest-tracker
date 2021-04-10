package X;

import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import com.oculus.unifiedtelemetry.R;

/* renamed from: X.3p  reason: invalid class name */
public abstract class AnonymousClass3p {
    public CharSequence mBigContentTitle;
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public AnonymousClass3h mBuilder;
    public CharSequence mSummaryText;
    public boolean mSummaryTextSet = false;

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public void addCompatExtras(Bundle bundle) {
    }

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass3Y r1) {
    }

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeBigContentView(AnonymousClass3Y r2) {
        return null;
    }

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeContentView(AnonymousClass3Y r2) {
        return null;
    }

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeHeadsUpContentView(AnonymousClass3Y r2) {
        return null;
    }

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public void restoreFromCompatExtras(Bundle bundle) {
    }

    public void setBuilder(AnonymousClass3h r2) {
        if (null != r2) {
            this.mBuilder = r2;
            throw null;
        }
    }

    private void hideNormalContent(RemoteViews remoteViews) {
        remoteViews.setViewVisibility(R.id.title, 8);
        remoteViews.setViewVisibility(R.id.text2, 8);
        remoteViews.setViewVisibility(R.id.text, 8);
    }

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
        hideNormalContent(remoteViews);
        remoteViews.removeAllViews(R.id.notification_main_column);
        remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
        remoteViews.setViewVisibility(R.id.notification_main_column, 0);
        throw null;
    }

    public Notification build() {
        return null;
    }

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public RemoteViews applyStandardTemplate(boolean z, int i, boolean z2) {
        throw null;
    }

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public Bitmap createColoredBitmap(int i, int i2) {
        throw null;
    }

    public Bitmap createColoredBitmap(IconCompat iconCompat, int i) {
        throw null;
    }
}
