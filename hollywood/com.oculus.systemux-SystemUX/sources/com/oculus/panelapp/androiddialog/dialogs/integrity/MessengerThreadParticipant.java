package com.oculus.panelapp.androiddialog.dialogs.integrity;

public class MessengerThreadParticipant {
    private final String mId;
    private final String mName;
    private final String mProfilePictureUri;

    public MessengerThreadParticipant(String str, String str2, String str3) {
        this.mId = str;
        this.mProfilePictureUri = str2;
        this.mName = str3;
    }

    public String getId() {
        return this.mId;
    }

    public String getProfilePictureUri() {
        return this.mProfilePictureUri;
    }

    public String getName() {
        return this.mName;
    }
}
