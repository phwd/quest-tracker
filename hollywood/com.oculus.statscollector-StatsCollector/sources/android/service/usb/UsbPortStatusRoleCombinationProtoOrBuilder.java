package android.service.usb;

import android.service.usb.UsbPortStatusProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbPortStatusRoleCombinationProtoOrBuilder extends MessageLiteOrBuilder {
    UsbPortStatusProto.DataRole getDataRole();

    UsbPortStatusProto.PowerRole getPowerRole();

    boolean hasDataRole();

    boolean hasPowerRole();
}
