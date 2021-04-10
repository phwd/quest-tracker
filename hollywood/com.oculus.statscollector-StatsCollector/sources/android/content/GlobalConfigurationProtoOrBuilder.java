package android.content;

import com.google.protobuf.MessageLiteOrBuilder;

public interface GlobalConfigurationProtoOrBuilder extends MessageLiteOrBuilder {
    DeviceConfigurationProto getDevice();

    ResourcesConfigurationProto getResources();

    boolean hasDevice();

    boolean hasResources();
}
