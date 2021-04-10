package android.service.usb;

import android.service.UsbEndPointDirection;
import android.service.UsbEndPointType;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbEndPointProtoOrBuilder extends MessageLiteOrBuilder {
    int getAddress();

    int getAttributes();

    UsbEndPointDirection getDirection();

    int getEndpointNumber();

    int getInterval();

    int getMaxPacketSize();

    UsbEndPointType getType();

    boolean hasAddress();

    boolean hasAttributes();

    boolean hasDirection();

    boolean hasEndpointNumber();

    boolean hasInterval();

    boolean hasMaxPacketSize();

    boolean hasType();
}
