package android.app;

import android.media.AudioAttributesProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NotificationChannelProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAllowAppOverlay();

    AudioAttributesProto getAudioAttributes();

    boolean getCanBypassDnd();

    String getDescription();

    ByteString getDescriptionBytes();

    boolean getFgServiceShown();

    String getGroup();

    ByteString getGroupBytes();

    String getId();

    ByteString getIdBytes();

    int getImportance();

    boolean getIsBlockableSystem();

    boolean getIsDeleted();

    boolean getIsVibrationEnabled();

    int getLightColor();

    int getLockscreenVisibility();

    String getName();

    ByteString getNameBytes();

    boolean getShowBadge();

    String getSound();

    ByteString getSoundBytes();

    boolean getUseLights();

    int getUserLockedFields();

    long getVibration(int i);

    int getVibrationCount();

    List<Long> getVibrationList();

    boolean hasAllowAppOverlay();

    boolean hasAudioAttributes();

    boolean hasCanBypassDnd();

    boolean hasDescription();

    boolean hasFgServiceShown();

    boolean hasGroup();

    boolean hasId();

    boolean hasImportance();

    boolean hasIsBlockableSystem();

    boolean hasIsDeleted();

    boolean hasIsVibrationEnabled();

    boolean hasLightColor();

    boolean hasLockscreenVisibility();

    boolean hasName();

    boolean hasShowBadge();

    boolean hasSound();

    boolean hasUseLights();

    boolean hasUserLockedFields();
}
