package oculus.internal.remote;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.EnumSet;
import java.util.Set;

public final class RemoteStatus implements Parcelable {
    public static final Parcelable.Creator<RemoteStatus> CREATOR = new Parcelable.Creator<RemoteStatus>() {
        /* class oculus.internal.remote.RemoteStatus.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public RemoteStatus createFromParcel(Parcel parcel) {
            return new RemoteStatus(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public RemoteStatus[] newArray(int i) {
            return new RemoteStatus[i];
        }
    };
    public int batteryLevel;
    private Set<Error> errors;
    public String firmwareVersion;
    public String identifier;
    public double lastConnectedTimestamp;
    private Model model;
    private Status status;

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: private */
    public enum Status {
        DISABLED,
        SEARCHING,
        UPDATING,
        CONNECTED,
        BLOCKED;
        
        private static Status[] values = values();

        static Status fromInt(int i) {
            return values[i];
        }
    }

    /* access modifiers changed from: private */
    public enum Error {
        BATTERY_DEAD,
        UPDATE_REQUIRED,
        UPDATE_FAILED,
        TRACKING_FAILED;
        
        private static Error[] values = values();

        static Error fromInt(int i) {
            return values[i];
        }
    }

    /* access modifiers changed from: private */
    public enum Model {
        CONTROLLER_LCON,
        CONTROLLER_JEDI,
        CONTROLLER_STARLET,
        STYLUS_PROTO,
        CONTROLLER_PACIFIC;
        
        private static Model[] values = values();

        static Model fromInt(int i) {
            return values[i];
        }
    }

    public RemoteStatus() {
    }

    private RemoteStatus(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeString(this.firmwareVersion);
        parcel.writeDouble(this.lastConnectedTimestamp);
        parcel.writeInt(this.batteryLevel);
        parcel.writeInt(this.status.ordinal());
        parcel.writeInt(this.errors.size());
        for (Error error : this.errors) {
            parcel.writeInt(error.ordinal());
        }
        parcel.writeInt(this.model.ordinal());
    }

    public void readFromParcel(Parcel parcel) {
        this.identifier = parcel.readString();
        this.firmwareVersion = parcel.readString();
        this.lastConnectedTimestamp = parcel.readDouble();
        this.batteryLevel = parcel.readInt();
        this.status = Status.fromInt(parcel.readInt());
        this.errors = EnumSet.noneOf(Error.class);
        for (int readInt = parcel.readInt(); readInt > 0; readInt--) {
            this.errors.add(Error.fromInt(parcel.readInt()));
        }
        this.model = Model.fromInt(parcel.readInt());
    }
}
