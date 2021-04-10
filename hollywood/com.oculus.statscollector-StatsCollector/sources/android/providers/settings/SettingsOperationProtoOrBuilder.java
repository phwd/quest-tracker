package android.providers.settings;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SettingsOperationProtoOrBuilder extends MessageLiteOrBuilder {
    String getOperation();

    ByteString getOperationBytes();

    String getSetting();

    ByteString getSettingBytes();

    long getTimestamp();

    boolean hasOperation();

    boolean hasSetting();

    boolean hasTimestamp();
}
