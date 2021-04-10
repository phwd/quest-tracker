package android.service.usb;

import android.service.usb.UsbPortProto;
import android.service.usb.UsbPortStatusProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbPortStatusProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getConnected();

    ContaminantPresenceStatus getContaminantPresenceStatus();

    UsbPortProto.Mode getCurrentMode();

    UsbPortStatusProto.DataRole getDataRole();

    UsbPortStatusProto.PowerRole getPowerRole();

    UsbPortStatusRoleCombinationProto getRoleCombinations(int i);

    int getRoleCombinationsCount();

    List<UsbPortStatusRoleCombinationProto> getRoleCombinationsList();

    boolean hasConnected();

    boolean hasContaminantPresenceStatus();

    boolean hasCurrentMode();

    boolean hasDataRole();

    boolean hasPowerRole();
}
