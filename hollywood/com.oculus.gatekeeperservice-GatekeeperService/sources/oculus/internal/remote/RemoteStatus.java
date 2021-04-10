package oculus.internal.remote;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.EnumSet;
import java.util.Set;

public final class RemoteStatus implements Parcelable {
    public static final Parcelable.Creator<RemoteStatus> CREATOR = new Parcelable.Creator<RemoteStatus>() {
        /* class oculus.internal.remote.RemoteStatus.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public RemoteStatus createFromParcel(Parcel in) {
            return new RemoteStatus(in);
        }

        @Override // android.os.Parcelable.Creator
        public RemoteStatus[] newArray(int size) {
            return new RemoteStatus[size];
        }
    };
    private static final String TAG = "RemoteStatus";
    public int batteryLevel;
    private Set<Error> errors;
    public String firmwareVersion;
    public String identifier;
    public double lastConnectedTimestamp;
    private Model model;
    private Status status;

    /* access modifiers changed from: private */
    public enum Status {
        DISABLED,
        SEARCHING,
        UPDATING,
        CONNECTED,
        BLOCKED;
        
        private static Status[] values = values();

        static Status fromInt(int val) {
            return values[val];
        }
    }

    /* access modifiers changed from: private */
    public enum Error {
        BATTERY_DEAD,
        UPDATE_REQUIRED,
        UPDATE_FAILED,
        TRACKING_FAILED;
        
        private static Error[] values = values();

        static Error fromInt(int val) {
            return values[val];
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

        static Model fromInt(int val) {
            return values[val];
        }
    }

    public RemoteStatus() {
    }

    private RemoteStatus(Parcel in) {
        readFromParcel(in);
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.identifier);
        out.writeString(this.firmwareVersion);
        out.writeDouble(this.lastConnectedTimestamp);
        out.writeInt(this.batteryLevel);
        out.writeInt(this.status.ordinal());
        out.writeInt(this.errors.size());
        for (Error err : this.errors) {
            out.writeInt(err.ordinal());
        }
        out.writeInt(this.model.ordinal());
    }

    public void readFromParcel(Parcel in) {
        this.identifier = in.readString();
        this.firmwareVersion = in.readString();
        this.lastConnectedTimestamp = in.readDouble();
        this.batteryLevel = in.readInt();
        this.status = Status.fromInt(in.readInt());
        this.errors = EnumSet.noneOf(Error.class);
        for (int errorsToRead = in.readInt(); errorsToRead > 0; errorsToRead--) {
            this.errors.add(Error.fromInt(in.readInt()));
        }
        this.model = Model.fromInt(in.readInt());
    }

    public int describeContents() {
        return 0;
    }

    public boolean isConnected() {
        return this.status == Status.CONNECTED;
    }

    public boolean isUpdating() {
        return this.status == Status.UPDATING;
    }

    public boolean isDisabled() {
        return this.status == Status.DISABLED;
    }

    public boolean isSearching() {
        return this.status == Status.SEARCHING;
    }

    /* renamed from: oculus.internal.remote.RemoteStatus$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$oculus$internal$remote$RemoteStatus$Status = new int[Status.values().length];

        static {
            try {
                $SwitchMap$oculus$internal$remote$RemoteStatus$Status[Status.DISABLED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$oculus$internal$remote$RemoteStatus$Status[Status.SEARCHING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$oculus$internal$remote$RemoteStatus$Status[Status.BLOCKED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$oculus$internal$remote$RemoteStatus$Status[Status.CONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$oculus$internal$remote$RemoteStatus$Status[Status.UPDATING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public boolean isDisconnected() {
        int i = AnonymousClass2.$SwitchMap$oculus$internal$remote$RemoteStatus$Status[this.status.ordinal()];
        if (i == 1 || i == 2) {
            return true;
        }
        if (i == 3 || i == 4 || i == 5) {
            return false;
        }
        Log.wtf(TAG, "isDisconnected(): unhandled state " + this.status.ordinal() + " " + this.status.name());
        return false;
    }

    public boolean isBatteryDead() {
        return this.status == Status.BLOCKED && this.errors.contains(Error.BATTERY_DEAD);
    }

    public boolean isUpdateRequired() {
        return this.status == Status.BLOCKED && this.errors.contains(Error.UPDATE_REQUIRED) && this.errors.size() == 1;
    }

    public boolean isUpdateFailed() {
        return this.status == Status.BLOCKED && this.errors.contains(Error.UPDATE_FAILED) && !this.errors.contains(Error.BATTERY_DEAD);
    }
}
