package androidx.core.app;

import android.app.PendingIntent;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcelable;

public final class RemoteActionCompat implements VersionedParcelable {
    @RestrictTo
    public PendingIntent mActionIntent;
    @RestrictTo
    public CharSequence mContentDescription;
    @RestrictTo
    public boolean mEnabled;
    @RestrictTo
    public IconCompat mIcon;
    @RestrictTo
    public boolean mShouldShowIcon;
    @RestrictTo
    public CharSequence mTitle;
}
