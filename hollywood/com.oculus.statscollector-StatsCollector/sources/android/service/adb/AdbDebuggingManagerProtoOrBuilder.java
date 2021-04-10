package android.service.adb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface AdbDebuggingManagerProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getConnectedToAdb();

    String getKeystore();

    ByteString getKeystoreBytes();

    String getLastKeyRecevied();

    ByteString getLastKeyReceviedBytes();

    String getSystemKeys();

    ByteString getSystemKeysBytes();

    String getUserKeys();

    ByteString getUserKeysBytes();

    boolean hasConnectedToAdb();

    boolean hasKeystore();

    boolean hasLastKeyRecevied();

    boolean hasSystemKeys();

    boolean hasUserKeys();
}
