package com.facebook.assistant.clientplatform.clientstate;

import X.AnonymousClass8Z;
import android.os.Parcel;
import android.os.Parcelable;

public enum AssistantErrorType implements Parcelable {
    UNKNOWN,
    MIC_NOT_AVAILABLE,
    CONNECTION_NOT_AVAILABLE,
    NOT_LOGGED_IN,
    SERVER_ERROR,
    FAILED_TO_INIT_CLIENT,
    CLIENT_PROCESSING_TIMEOUT,
    GENERIC_ERROR_RESPONSE_FROM_SERVER,
    TTS_FAILED,
    CLIENT_ERROR,
    RUNTIME;
    
    public static final Parcelable.Creator CREATOR = new AnonymousClass8Z();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
