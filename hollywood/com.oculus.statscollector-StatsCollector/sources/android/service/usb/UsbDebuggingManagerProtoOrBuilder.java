package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbDebuggingManagerProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getConnectedToAdb();

    String getLastKeyReceived();

    ByteString getLastKeyReceivedBytes();

    String getSystemKeys();

    ByteString getSystemKeysBytes();

    String getUserKeys();

    ByteString getUserKeysBytes();

    boolean hasConnectedToAdb();

    boolean hasLastKeyReceived();

    boolean hasSystemKeys();

    boolean hasUserKeys();
}
