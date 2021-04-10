package android.service.adb;

import com.google.protobuf.MessageLiteOrBuilder;

public interface AdbServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    AdbDebuggingManagerProto getDebuggingManager();

    boolean hasDebuggingManager();
}
