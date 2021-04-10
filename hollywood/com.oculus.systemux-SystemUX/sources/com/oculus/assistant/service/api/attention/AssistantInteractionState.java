package com.oculus.assistant.service.api.attention;

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
    
    public static final Parcelable.Creator<AssistantInteractionState> CREATOR = new Parcelable.Creator<AssistantInteractionState>() {
        /* class com.oculus.assistant.service.api.attention.AssistantInteractionState.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AssistantInteractionState createFromParcel(Parcel parcel) {
            try {
                return (AssistantInteractionState) AssistantInteractionState.valueOf(AssistantInteractionState.class, parcel.readString());
            } catch (IllegalArgumentException unused) {
                return AssistantInteractionState.UNKNOWN;
            }
        }

        @Override // android.os.Parcelable.Creator
        public AssistantInteractionState[] newArray(int i) {
            return new AssistantInteractionState[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
