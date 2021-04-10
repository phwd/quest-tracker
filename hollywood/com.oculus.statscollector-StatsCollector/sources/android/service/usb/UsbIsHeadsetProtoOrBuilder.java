package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbIsHeadsetProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getIn();

    boolean getOut();

    boolean hasIn();

    boolean hasOut();
}
