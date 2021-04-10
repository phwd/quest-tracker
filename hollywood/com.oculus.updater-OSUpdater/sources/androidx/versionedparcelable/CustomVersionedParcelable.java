package androidx.versionedparcelable;

import androidx.annotation.RestrictTo;

@RestrictTo
public abstract class CustomVersionedParcelable implements VersionedParcelable {
    @RestrictTo
    public void onPostParceling() {
    }

    @RestrictTo
    public void onPreParceling(boolean z) {
    }
}
