package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.assistant.clientplatform.clientstate.AssistantErrorType;

/* renamed from: X.8Z  reason: invalid class name */
public final class AnonymousClass8Z implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        try {
            return AssistantErrorType.valueOf(AssistantErrorType.class, parcel.readString());
        } catch (IllegalArgumentException unused) {
            return AssistantErrorType.UNKNOWN;
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new AssistantErrorType[i];
    }
}
