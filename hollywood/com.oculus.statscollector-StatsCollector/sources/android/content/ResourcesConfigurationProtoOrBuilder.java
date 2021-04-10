package android.content;

import com.google.protobuf.MessageLiteOrBuilder;

public interface ResourcesConfigurationProtoOrBuilder extends MessageLiteOrBuilder {
    ConfigurationProto getConfiguration();

    int getScreenHeightPx();

    int getScreenWidthPx();

    int getSdkVersion();

    boolean hasConfiguration();

    boolean hasScreenHeightPx();

    boolean hasScreenWidthPx();

    boolean hasSdkVersion();
}
