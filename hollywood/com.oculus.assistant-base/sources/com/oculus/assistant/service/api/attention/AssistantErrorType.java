package com.oculus.assistant.service.api.attention;

import X.YV;
import android.os.Parcel;
import android.os.Parcelable;

public enum AssistantErrorType implements Parcelable {
    UNKNOWN,
    MIC_NOT_AVAILABLE,
    CONNECTION_NOT_AVAILABLE,
    NOT_LOGGED_IN,
    SERVER_ERROR,
    INCOMPATIBLE_DEVICE,
    NOT_AVAILABLE_IN_GAME,
    IN_GAME_VOLUME_LOW,
    OLD_OS_VERSION,
    MIC_MUTED,
    NOT_AVAILABLE_IN_THIS_GAME,
    FAILED_TO_INIT_CLIENT,
    CLIENT_PROCESSING_TIMEOUT,
    GENERIC_ERROR_RESPONSE_FROM_SERVER,
    TTS_FAILED,
    CLIENT_ERROR,
    RUNTIME,
    PRIVACY_NOT_ACCEPTED;
    
    public static final Parcelable.Creator CREATOR = new YV();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
