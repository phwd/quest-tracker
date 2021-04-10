package X;

import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import com.oculus.userserver2.R;

/* renamed from: X.4Z  reason: invalid class name */
public abstract class AnonymousClass4Z {
    public CharSequence mBigContentTitle;
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public AnonymousClass4R mBuilder;
    public CharSequence mSummaryText;
    public boolean mSummaryTextSet = false;

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void addCompatExtras(Bundle bundle) {
    }

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void apply(AnonymousClass4I r1) {
    }

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeBigContentView(AnonymousClass4I r2) {
        return null;
    }

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeContentView(AnonymousClass4I r2) {
        return null;
    }

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public RemoteViews makeHeadsUpContentView(AnonymousClass4I r2) {
        return null;
    }

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void restoreFromCompatExtras(Bundle bundle) {
    }

    public void setBuilder(AnonymousClass4R r2) {
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

    public Notification build() {
        return null;
    }

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
        hideNormalContent(remoteViews);
        remoteViews.removeAllViews(R.id.notification_main_column);
        remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
        remoteViews.setViewVisibility(R.id.notification_main_column, 0);
        throw null;
    }

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public RemoteViews applyStandardTemplate(boolean z, int i, boolean z2) {
        throw null;
    }

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public Bitmap createColoredBitmap(int i, int i2) {
        throw null;
    }

    public Bitmap createColoredBitmap(IconCompat iconCompat, int i) {
        throw null;
    }
}
