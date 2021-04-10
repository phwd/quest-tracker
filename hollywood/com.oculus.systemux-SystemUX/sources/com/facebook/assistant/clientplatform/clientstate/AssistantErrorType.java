package com.facebook.assistant.clientplatform.clientstate;

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
    
    public static final Parcelable.Creator<AssistantErrorType> CREATOR = new Parcelable.Creator<AssistantErrorType>() {
        /* class com.facebook.assistant.clientplatform.clientstate.AssistantErrorType.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AssistantErrorType createFromParcel(Parcel parcel) {
            try {
                return (AssistantErrorType) AssistantErrorType.valueOf(AssistantErrorType.class, parcel.readString());
            } catch (IllegalArgumentException unused) {
                return AssistantErrorType.UNKNOWN;
            }
        }

        @Override // android.os.Parcelable.Creator
        public AssistantErrorType[] newArray(int i) {
            return new AssistantErrorType[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
