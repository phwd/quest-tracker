package androidx.media;

import androidx.versionedparcelable.VersionedParcelable;

/* access modifiers changed from: package-private */
public interface AudioAttributesImpl extends VersionedParcelable {

    public interface Builder {
        AudioAttributesImpl build();

        Builder setLegacyStreamType(int i);
    }
}
