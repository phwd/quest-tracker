package android.service.notification;

import android.media.AudioAttributesProto;
import android.service.notification.NotificationRecordProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface NotificationRecordProtoOrBuilder extends MessageLiteOrBuilder {
    AudioAttributesProto getAudioAttributes();

    boolean getCanShowLight();

    boolean getCanVibrate();

    String getChannelId();

    ByteString getChannelIdBytes();

    String getDelegatePackage();

    ByteString getDelegatePackageBytes();

    int getFlags();

    String getGroupKey();

    ByteString getGroupKeyBytes();

    int getImportance();

    String getKey();

    ByteString getKeyBytes();

    String getPackage();

    ByteString getPackageBytes();

    String getSound();

    ByteString getSoundBytes();

    NotificationRecordProto.State getState();

    boolean hasAudioAttributes();

    boolean hasCanShowLight();

    boolean hasCanVibrate();

    boolean hasChannelId();

    boolean hasDelegatePackage();

    boolean hasFlags();

    boolean hasGroupKey();

    boolean hasImportance();

    boolean hasKey();

    boolean hasPackage();

    boolean hasSound();

    boolean hasState();
}
