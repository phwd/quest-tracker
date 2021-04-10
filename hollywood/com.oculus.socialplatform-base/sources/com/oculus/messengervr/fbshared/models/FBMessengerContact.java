package com.oculus.messengervr.fbshared.models;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.fbshared.utils.BlockedByViewerStatusUtils;
import com.oculus.messengervr.interfaces.BlockedByViewerStatus;
import com.oculus.messengervr.interfaces.MessengerContact;
import java.util.Objects;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBMessengerContact implements MessengerContact {
    public final BlockedByViewerStatus mBlockedByViewerStatus;
    public final long mContactId;
    public final String mName;
    public final String mProfilePictureUrl;

    @Override // com.oculus.messengervr.interfaces.MessengerContact
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof FBMessengerContact)) {
            return false;
        }
        FBMessengerContact fBMessengerContact = (FBMessengerContact) obj;
        if (getContactId() != fBMessengerContact.getContactId() || !getName().equals(fBMessengerContact.getName()) || !getProfilePictureUrl().equals(fBMessengerContact.getProfilePictureUrl()) || getBlockedByViewerStatus() != fBMessengerContact.getBlockedByViewerStatus()) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerContact
    public BlockedByViewerStatus getBlockedByViewerStatus() {
        return this.mBlockedByViewerStatus;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerContact
    public long getContactId() {
        return this.mContactId;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerContact
    public String getName() {
        return this.mName;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerContact
    public String getProfilePictureUrl() {
        return this.mProfilePictureUrl;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerContact
    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mContactId), this.mName, this.mProfilePictureUrl, this.mBlockedByViewerStatus);
    }

    public FBMessengerContact(long j, String str, String str2, int i) {
        this.mContactId = j;
        this.mName = str;
        this.mProfilePictureUrl = str2;
        this.mBlockedByViewerStatus = BlockedByViewerStatusUtils.transformBlockedByViewerStatus(Integer.valueOf(i));
    }
}
