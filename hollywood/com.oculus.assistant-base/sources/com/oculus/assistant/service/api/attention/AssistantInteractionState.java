package com.oculus.assistant.service.api.attention;

import X.YX;
import android.os.Parcel;
import android.os.Parcelable;

public enum AssistantInteractionState implements Parcelable {
    INACTIVE,
    LISTENING,
    FINISHED_PROCESSING,
    PROCESSING,
    RESPONDING,
    SPEECH_PREPARED,
    SPEECH_STARTING,
    SPEECH_ENDING,
    ACTIVATED,
    UNKNOWN;
    
    public static final Parcelable.Creator CREATOR = new YX();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
