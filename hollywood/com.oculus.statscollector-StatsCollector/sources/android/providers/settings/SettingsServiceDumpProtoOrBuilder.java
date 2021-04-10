package android.providers.settings;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface SettingsServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    GlobalSettingsProto getGlobalSettings();

    UserSettingsProto getUserSettings(int i);

    int getUserSettingsCount();

    List<UserSettingsProto> getUserSettingsList();

    boolean hasGlobalSettings();
}
