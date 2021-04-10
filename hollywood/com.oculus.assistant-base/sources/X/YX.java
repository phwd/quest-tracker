package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.assistant.service.api.attention.AssistantInteractionState;

public final class YX implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        try {
            return AssistantInteractionState.valueOf(AssistantInteractionState.class, parcel.readString());
        } catch (IllegalArgumentException unused) {
            return AssistantInteractionState.UNKNOWN;
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new AssistantInteractionState[i];
    }
}
