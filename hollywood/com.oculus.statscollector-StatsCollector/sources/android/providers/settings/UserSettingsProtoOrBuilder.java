package android.providers.settings;

import com.google.protobuf.MessageLiteOrBuilder;

public interface UserSettingsProtoOrBuilder extends MessageLiteOrBuilder {
    SecureSettingsProto getSecureSettings();

    SystemSettingsProto getSystemSettings();

    int getUserId();

    boolean hasSecureSettings();

    boolean hasSystemSettings();

    boolean hasUserId();
}
