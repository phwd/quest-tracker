package com.oculus.assistant.service.api.attention;

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
    NOT_AVAILABLE_IN_THIS_GAME;
    
    public static final Parcelable.Creator<AssistantErrorType> CREATOR = new Parcelable.Creator<AssistantErrorType>() {
        /* class com.oculus.assistant.service.api.attention.AssistantErrorType.AnonymousClass1 */

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
