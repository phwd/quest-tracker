package com.oculus.panelapp.messenger.fetchers;

import androidx.annotation.Nullable;

public class MessengerContact {
    @Nullable
    public final String mProfilePictureUrl;
    public final String mUserId;
    public final String mUserName;

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof MessengerContact)) {
            return false;
        }
        MessengerContact messengerContact = (MessengerContact) obj;
        String str = this.mUserId;
        if (str == null || !str.equals(messengerContact.mUserId)) {
            return false;
        }
        return true;
    }

    @Nullable
    public String getProfilePictureUrl() {
        return this.mProfilePictureUrl;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public int hashCode() {
        return this.mUserId.hashCode();
    }

    public MessengerContact(String str, String str2, String str3) {
        this.mUserId = str;
        this.mProfilePictureUrl = str2;
        this.mUserName = str3;
    }
}
