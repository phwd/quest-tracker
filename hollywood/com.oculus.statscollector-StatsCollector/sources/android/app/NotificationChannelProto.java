package android.app;

import android.media.AudioAttributesProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class NotificationChannelProto extends GeneratedMessageLite<NotificationChannelProto, Builder> implements NotificationChannelProtoOrBuilder {
    public static final int ALLOW_APP_OVERLAY_FIELD_NUMBER = 19;
    public static final int AUDIO_ATTRIBUTES_FIELD_NUMBER = 16;
    public static final int CAN_BYPASS_DND_FIELD_NUMBER = 5;
    private static final NotificationChannelProto DEFAULT_INSTANCE = new NotificationChannelProto();
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int FG_SERVICE_SHOWN_FIELD_NUMBER = 18;
    public static final int GROUP_FIELD_NUMBER = 15;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int IMPORTANCE_FIELD_NUMBER = 4;
    public static final int IS_BLOCKABLE_SYSTEM_FIELD_NUMBER = 17;
    public static final int IS_DELETED_FIELD_NUMBER = 14;
    public static final int IS_VIBRATION_ENABLED_FIELD_NUMBER = 12;
    public static final int LIGHT_COLOR_FIELD_NUMBER = 9;
    public static final int LOCKSCREEN_VISIBILITY_FIELD_NUMBER = 6;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser<NotificationChannelProto> PARSER = null;
    public static final int SHOW_BADGE_FIELD_NUMBER = 13;
    public static final int SOUND_FIELD_NUMBER = 7;
    public static final int USER_LOCKED_FIELDS_FIELD_NUMBER = 11;
    public static final int USE_LIGHTS_FIELD_NUMBER = 8;
    public static final int VIBRATION_FIELD_NUMBER = 10;
    private boolean allowAppOverlay_ = false;
    private AudioAttributesProto audioAttributes_;
    private int bitField0_;
    private boolean canBypassDnd_ = false;
    private String description_ = "";
    private boolean fgServiceShown_ = false;
    private String group_ = "";
    private String id_ = "";
    private int importance_ = 0;
    private boolean isBlockableSystem_ = false;
    private boolean isDeleted_ = false;
    private boolean isVibrationEnabled_ = false;
    private int lightColor_ = 0;
    private int lockscreenVisibility_ = 0;
    private String name_ = "";
    private boolean showBadge_ = false;
    private String sound_ = "";
    private boolean useLights_ = false;
    private int userLockedFields_ = 0;
    private Internal.LongList vibration_ = emptyLongList();

    private NotificationChannelProto() {
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.id_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearId() {
        this.bitField0_ &= -2;
        this.id_ = getDefaultInstance().getId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.id_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -3;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasDescription() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDescription(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.description_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDescription() {
        this.bitField0_ &= -5;
        this.description_ = getDefaultInstance().getDescription();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDescriptionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.description_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasImportance() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public int getImportance() {
        return this.importance_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImportance(int value) {
        this.bitField0_ |= 8;
        this.importance_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearImportance() {
        this.bitField0_ &= -9;
        this.importance_ = 0;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasCanBypassDnd() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean getCanBypassDnd() {
        return this.canBypassDnd_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCanBypassDnd(boolean value) {
        this.bitField0_ |= 16;
        this.canBypassDnd_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCanBypassDnd() {
        this.bitField0_ &= -17;
        this.canBypassDnd_ = false;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasLockscreenVisibility() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public int getLockscreenVisibility() {
        return this.lockscreenVisibility_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLockscreenVisibility(int value) {
        this.bitField0_ |= 32;
        this.lockscreenVisibility_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLockscreenVisibility() {
        this.bitField0_ &= -33;
        this.lockscreenVisibility_ = 0;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasSound() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public String getSound() {
        return this.sound_;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public ByteString getSoundBytes() {
        return ByteString.copyFromUtf8(this.sound_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSound(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.sound_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSound() {
        this.bitField0_ &= -65;
        this.sound_ = getDefaultInstance().getSound();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSoundBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.sound_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasUseLights() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean getUseLights() {
        return this.useLights_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUseLights(boolean value) {
        this.bitField0_ |= 128;
        this.useLights_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUseLights() {
        this.bitField0_ &= -129;
        this.useLights_ = false;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasLightColor() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public int getLightColor() {
        return this.lightColor_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLightColor(int value) {
        this.bitField0_ |= 256;
        this.lightColor_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLightColor() {
        this.bitField0_ &= -257;
        this.lightColor_ = 0;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public List<Long> getVibrationList() {
        return this.vibration_;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public int getVibrationCount() {
        return this.vibration_.size();
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public long getVibration(int index) {
        return this.vibration_.getLong(index);
    }

    private void ensureVibrationIsMutable() {
        if (!this.vibration_.isModifiable()) {
            this.vibration_ = GeneratedMessageLite.mutableCopy(this.vibration_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVibration(int index, long value) {
        ensureVibrationIsMutable();
        this.vibration_.setLong(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addVibration(long value) {
        ensureVibrationIsMutable();
        this.vibration_.addLong(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllVibration(Iterable<? extends Long> values) {
        ensureVibrationIsMutable();
        AbstractMessageLite.addAll(values, this.vibration_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVibration() {
        this.vibration_ = emptyLongList();
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasUserLockedFields() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public int getUserLockedFields() {
        return this.userLockedFields_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserLockedFields(int value) {
        this.bitField0_ |= 512;
        this.userLockedFields_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserLockedFields() {
        this.bitField0_ &= -513;
        this.userLockedFields_ = 0;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasIsVibrationEnabled() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean getIsVibrationEnabled() {
        return this.isVibrationEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsVibrationEnabled(boolean value) {
        this.bitField0_ |= 1024;
        this.isVibrationEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsVibrationEnabled() {
        this.bitField0_ &= -1025;
        this.isVibrationEnabled_ = false;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasShowBadge() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean getShowBadge() {
        return this.showBadge_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShowBadge(boolean value) {
        this.bitField0_ |= 2048;
        this.showBadge_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearShowBadge() {
        this.bitField0_ &= -2049;
        this.showBadge_ = false;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasIsDeleted() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean getIsDeleted() {
        return this.isDeleted_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDeleted(boolean value) {
        this.bitField0_ |= 4096;
        this.isDeleted_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDeleted() {
        this.bitField0_ &= -4097;
        this.isDeleted_ = false;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasGroup() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public String getGroup() {
        return this.group_;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public ByteString getGroupBytes() {
        return ByteString.copyFromUtf8(this.group_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGroup(String value) {
        if (value != null) {
            this.bitField0_ |= 8192;
            this.group_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGroup() {
        this.bitField0_ &= -8193;
        this.group_ = getDefaultInstance().getGroup();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGroupBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8192;
            this.group_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasAudioAttributes() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public AudioAttributesProto getAudioAttributes() {
        AudioAttributesProto audioAttributesProto = this.audioAttributes_;
        return audioAttributesProto == null ? AudioAttributesProto.getDefaultInstance() : audioAttributesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudioAttributes(AudioAttributesProto value) {
        if (value != null) {
            this.audioAttributes_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudioAttributes(AudioAttributesProto.Builder builderForValue) {
        this.audioAttributes_ = (AudioAttributesProto) builderForValue.build();
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAudioAttributes(AudioAttributesProto value) {
        AudioAttributesProto audioAttributesProto = this.audioAttributes_;
        if (audioAttributesProto == null || audioAttributesProto == AudioAttributesProto.getDefaultInstance()) {
            this.audioAttributes_ = value;
        } else {
            this.audioAttributes_ = (AudioAttributesProto) ((AudioAttributesProto.Builder) AudioAttributesProto.newBuilder(this.audioAttributes_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAudioAttributes() {
        this.audioAttributes_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasIsBlockableSystem() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean getIsBlockableSystem() {
        return this.isBlockableSystem_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsBlockableSystem(boolean value) {
        this.bitField0_ |= 32768;
        this.isBlockableSystem_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsBlockableSystem() {
        this.bitField0_ &= -32769;
        this.isBlockableSystem_ = false;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasFgServiceShown() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean getFgServiceShown() {
        return this.fgServiceShown_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFgServiceShown(boolean value) {
        this.bitField0_ |= 65536;
        this.fgServiceShown_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFgServiceShown() {
        this.bitField0_ &= -65537;
        this.fgServiceShown_ = false;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean hasAllowAppOverlay() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // android.app.NotificationChannelProtoOrBuilder
    public boolean getAllowAppOverlay() {
        return this.allowAppOverlay_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowAppOverlay(boolean value) {
        this.bitField0_ |= 131072;
        this.allowAppOverlay_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAllowAppOverlay() {
        this.bitField0_ &= -131073;
        this.allowAppOverlay_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getDescription());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.importance_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.canBypassDnd_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.lockscreenVisibility_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(7, getSound());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(8, this.useLights_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeInt32(9, this.lightColor_);
        }
        for (int i = 0; i < this.vibration_.size(); i++) {
            output.writeInt64(10, this.vibration_.getLong(i));
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeInt32(11, this.userLockedFields_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeBool(12, this.isVibrationEnabled_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(13, this.showBadge_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeBool(14, this.isDeleted_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeString(15, getGroup());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(16, getAudioAttributes());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeBool(17, this.isBlockableSystem_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeBool(18, this.fgServiceShown_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeBool(19, this.allowAppOverlay_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeStringSize(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getDescription());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.importance_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.canBypassDnd_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.lockscreenVisibility_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(7, getSound());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeBoolSize(8, this.useLights_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeInt32Size(9, this.lightColor_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.vibration_.size(); i++) {
            dataSize += CodedOutputStream.computeInt64SizeNoTag(this.vibration_.getLong(i));
        }
        int size3 = size2 + dataSize + (getVibrationList().size() * 1);
        if ((this.bitField0_ & 512) == 512) {
            size3 += CodedOutputStream.computeInt32Size(11, this.userLockedFields_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size3 += CodedOutputStream.computeBoolSize(12, this.isVibrationEnabled_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size3 += CodedOutputStream.computeBoolSize(13, this.showBadge_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size3 += CodedOutputStream.computeBoolSize(14, this.isDeleted_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size3 += CodedOutputStream.computeStringSize(15, getGroup());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size3 += CodedOutputStream.computeMessageSize(16, getAudioAttributes());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size3 += CodedOutputStream.computeBoolSize(17, this.isBlockableSystem_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size3 += CodedOutputStream.computeBoolSize(18, this.fgServiceShown_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size3 += CodedOutputStream.computeBoolSize(19, this.allowAppOverlay_);
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static NotificationChannelProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NotificationChannelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationChannelProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationChannelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationChannelProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NotificationChannelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationChannelProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationChannelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationChannelProto parseFrom(InputStream input) throws IOException {
        return (NotificationChannelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationChannelProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationChannelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationChannelProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NotificationChannelProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationChannelProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationChannelProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationChannelProto parseFrom(CodedInputStream input) throws IOException {
        return (NotificationChannelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationChannelProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationChannelProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NotificationChannelProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NotificationChannelProto, Builder> implements NotificationChannelProtoOrBuilder {
        private Builder() {
            super(NotificationChannelProto.DEFAULT_INSTANCE);
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasId() {
            return ((NotificationChannelProto) this.instance).hasId();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public String getId() {
            return ((NotificationChannelProto) this.instance).getId();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public ByteString getIdBytes() {
            return ((NotificationChannelProto) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setIdBytes(value);
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasName() {
            return ((NotificationChannelProto) this.instance).hasName();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public String getName() {
            return ((NotificationChannelProto) this.instance).getName();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public ByteString getNameBytes() {
            return ((NotificationChannelProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasDescription() {
            return ((NotificationChannelProto) this.instance).hasDescription();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public String getDescription() {
            return ((NotificationChannelProto) this.instance).getDescription();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public ByteString getDescriptionBytes() {
            return ((NotificationChannelProto) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setDescription(value);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearDescription();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setDescriptionBytes(value);
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasImportance() {
            return ((NotificationChannelProto) this.instance).hasImportance();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public int getImportance() {
            return ((NotificationChannelProto) this.instance).getImportance();
        }

        public Builder setImportance(int value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setImportance(value);
            return this;
        }

        public Builder clearImportance() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearImportance();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasCanBypassDnd() {
            return ((NotificationChannelProto) this.instance).hasCanBypassDnd();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean getCanBypassDnd() {
            return ((NotificationChannelProto) this.instance).getCanBypassDnd();
        }

        public Builder setCanBypassDnd(boolean value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setCanBypassDnd(value);
            return this;
        }

        public Builder clearCanBypassDnd() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearCanBypassDnd();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasLockscreenVisibility() {
            return ((NotificationChannelProto) this.instance).hasLockscreenVisibility();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public int getLockscreenVisibility() {
            return ((NotificationChannelProto) this.instance).getLockscreenVisibility();
        }

        public Builder setLockscreenVisibility(int value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setLockscreenVisibility(value);
            return this;
        }

        public Builder clearLockscreenVisibility() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearLockscreenVisibility();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasSound() {
            return ((NotificationChannelProto) this.instance).hasSound();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public String getSound() {
            return ((NotificationChannelProto) this.instance).getSound();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public ByteString getSoundBytes() {
            return ((NotificationChannelProto) this.instance).getSoundBytes();
        }

        public Builder setSound(String value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setSound(value);
            return this;
        }

        public Builder clearSound() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearSound();
            return this;
        }

        public Builder setSoundBytes(ByteString value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setSoundBytes(value);
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasUseLights() {
            return ((NotificationChannelProto) this.instance).hasUseLights();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean getUseLights() {
            return ((NotificationChannelProto) this.instance).getUseLights();
        }

        public Builder setUseLights(boolean value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setUseLights(value);
            return this;
        }

        public Builder clearUseLights() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearUseLights();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasLightColor() {
            return ((NotificationChannelProto) this.instance).hasLightColor();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public int getLightColor() {
            return ((NotificationChannelProto) this.instance).getLightColor();
        }

        public Builder setLightColor(int value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setLightColor(value);
            return this;
        }

        public Builder clearLightColor() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearLightColor();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public List<Long> getVibrationList() {
            return Collections.unmodifiableList(((NotificationChannelProto) this.instance).getVibrationList());
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public int getVibrationCount() {
            return ((NotificationChannelProto) this.instance).getVibrationCount();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public long getVibration(int index) {
            return ((NotificationChannelProto) this.instance).getVibration(index);
        }

        public Builder setVibration(int index, long value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setVibration(index, value);
            return this;
        }

        public Builder addVibration(long value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).addVibration(value);
            return this;
        }

        public Builder addAllVibration(Iterable<? extends Long> values) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).addAllVibration(values);
            return this;
        }

        public Builder clearVibration() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearVibration();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasUserLockedFields() {
            return ((NotificationChannelProto) this.instance).hasUserLockedFields();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public int getUserLockedFields() {
            return ((NotificationChannelProto) this.instance).getUserLockedFields();
        }

        public Builder setUserLockedFields(int value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setUserLockedFields(value);
            return this;
        }

        public Builder clearUserLockedFields() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearUserLockedFields();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasIsVibrationEnabled() {
            return ((NotificationChannelProto) this.instance).hasIsVibrationEnabled();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean getIsVibrationEnabled() {
            return ((NotificationChannelProto) this.instance).getIsVibrationEnabled();
        }

        public Builder setIsVibrationEnabled(boolean value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setIsVibrationEnabled(value);
            return this;
        }

        public Builder clearIsVibrationEnabled() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearIsVibrationEnabled();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasShowBadge() {
            return ((NotificationChannelProto) this.instance).hasShowBadge();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean getShowBadge() {
            return ((NotificationChannelProto) this.instance).getShowBadge();
        }

        public Builder setShowBadge(boolean value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setShowBadge(value);
            return this;
        }

        public Builder clearShowBadge() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearShowBadge();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasIsDeleted() {
            return ((NotificationChannelProto) this.instance).hasIsDeleted();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean getIsDeleted() {
            return ((NotificationChannelProto) this.instance).getIsDeleted();
        }

        public Builder setIsDeleted(boolean value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setIsDeleted(value);
            return this;
        }

        public Builder clearIsDeleted() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearIsDeleted();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasGroup() {
            return ((NotificationChannelProto) this.instance).hasGroup();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public String getGroup() {
            return ((NotificationChannelProto) this.instance).getGroup();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public ByteString getGroupBytes() {
            return ((NotificationChannelProto) this.instance).getGroupBytes();
        }

        public Builder setGroup(String value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setGroup(value);
            return this;
        }

        public Builder clearGroup() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearGroup();
            return this;
        }

        public Builder setGroupBytes(ByteString value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setGroupBytes(value);
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasAudioAttributes() {
            return ((NotificationChannelProto) this.instance).hasAudioAttributes();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public AudioAttributesProto getAudioAttributes() {
            return ((NotificationChannelProto) this.instance).getAudioAttributes();
        }

        public Builder setAudioAttributes(AudioAttributesProto value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setAudioAttributes((NotificationChannelProto) value);
            return this;
        }

        public Builder setAudioAttributes(AudioAttributesProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setAudioAttributes((NotificationChannelProto) builderForValue);
            return this;
        }

        public Builder mergeAudioAttributes(AudioAttributesProto value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).mergeAudioAttributes(value);
            return this;
        }

        public Builder clearAudioAttributes() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearAudioAttributes();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasIsBlockableSystem() {
            return ((NotificationChannelProto) this.instance).hasIsBlockableSystem();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean getIsBlockableSystem() {
            return ((NotificationChannelProto) this.instance).getIsBlockableSystem();
        }

        public Builder setIsBlockableSystem(boolean value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setIsBlockableSystem(value);
            return this;
        }

        public Builder clearIsBlockableSystem() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearIsBlockableSystem();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasFgServiceShown() {
            return ((NotificationChannelProto) this.instance).hasFgServiceShown();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean getFgServiceShown() {
            return ((NotificationChannelProto) this.instance).getFgServiceShown();
        }

        public Builder setFgServiceShown(boolean value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setFgServiceShown(value);
            return this;
        }

        public Builder clearFgServiceShown() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearFgServiceShown();
            return this;
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean hasAllowAppOverlay() {
            return ((NotificationChannelProto) this.instance).hasAllowAppOverlay();
        }

        @Override // android.app.NotificationChannelProtoOrBuilder
        public boolean getAllowAppOverlay() {
            return ((NotificationChannelProto) this.instance).getAllowAppOverlay();
        }

        public Builder setAllowAppOverlay(boolean value) {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).setAllowAppOverlay(value);
            return this;
        }

        public Builder clearAllowAppOverlay() {
            copyOnWrite();
            ((NotificationChannelProto) this.instance).clearAllowAppOverlay();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NotificationChannelProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.vibration_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NotificationChannelProto other = (NotificationChannelProto) arg1;
                this.id_ = visitor.visitString(hasId(), this.id_, other.hasId(), other.id_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.description_ = visitor.visitString(hasDescription(), this.description_, other.hasDescription(), other.description_);
                this.importance_ = visitor.visitInt(hasImportance(), this.importance_, other.hasImportance(), other.importance_);
                this.canBypassDnd_ = visitor.visitBoolean(hasCanBypassDnd(), this.canBypassDnd_, other.hasCanBypassDnd(), other.canBypassDnd_);
                this.lockscreenVisibility_ = visitor.visitInt(hasLockscreenVisibility(), this.lockscreenVisibility_, other.hasLockscreenVisibility(), other.lockscreenVisibility_);
                this.sound_ = visitor.visitString(hasSound(), this.sound_, other.hasSound(), other.sound_);
                this.useLights_ = visitor.visitBoolean(hasUseLights(), this.useLights_, other.hasUseLights(), other.useLights_);
                this.lightColor_ = visitor.visitInt(hasLightColor(), this.lightColor_, other.hasLightColor(), other.lightColor_);
                this.vibration_ = visitor.visitLongList(this.vibration_, other.vibration_);
                this.userLockedFields_ = visitor.visitInt(hasUserLockedFields(), this.userLockedFields_, other.hasUserLockedFields(), other.userLockedFields_);
                this.isVibrationEnabled_ = visitor.visitBoolean(hasIsVibrationEnabled(), this.isVibrationEnabled_, other.hasIsVibrationEnabled(), other.isVibrationEnabled_);
                this.showBadge_ = visitor.visitBoolean(hasShowBadge(), this.showBadge_, other.hasShowBadge(), other.showBadge_);
                this.isDeleted_ = visitor.visitBoolean(hasIsDeleted(), this.isDeleted_, other.hasIsDeleted(), other.isDeleted_);
                this.group_ = visitor.visitString(hasGroup(), this.group_, other.hasGroup(), other.group_);
                this.audioAttributes_ = (AudioAttributesProto) visitor.visitMessage(this.audioAttributes_, other.audioAttributes_);
                this.isBlockableSystem_ = visitor.visitBoolean(hasIsBlockableSystem(), this.isBlockableSystem_, other.hasIsBlockableSystem(), other.isBlockableSystem_);
                this.fgServiceShown_ = visitor.visitBoolean(hasFgServiceShown(), this.fgServiceShown_, other.hasFgServiceShown(), other.fgServiceShown_);
                this.allowAppOverlay_ = visitor.visitBoolean(hasAllowAppOverlay(), this.allowAppOverlay_, other.hasAllowAppOverlay(), other.allowAppOverlay_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 10:
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.id_ = s;
                                break;
                            case 18:
                                String s2 = input.readString();
                                this.bitField0_ |= 2;
                                this.name_ = s2;
                                break;
                            case 26:
                                String s3 = input.readString();
                                this.bitField0_ |= 4;
                                this.description_ = s3;
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.importance_ = input.readInt32();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.canBypassDnd_ = input.readBool();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.lockscreenVisibility_ = input.readInt32();
                                break;
                            case 58:
                                String s4 = input.readString();
                                this.bitField0_ |= 64;
                                this.sound_ = s4;
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.useLights_ = input.readBool();
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.lightColor_ = input.readInt32();
                                break;
                            case 80:
                                if (!this.vibration_.isModifiable()) {
                                    this.vibration_ = GeneratedMessageLite.mutableCopy(this.vibration_);
                                }
                                this.vibration_.addLong(input.readInt64());
                                break;
                            case 82:
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.vibration_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.vibration_ = GeneratedMessageLite.mutableCopy(this.vibration_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.vibration_.addLong(input.readInt64());
                                }
                                input.popLimit(limit);
                                break;
                            case 88:
                                this.bitField0_ |= 512;
                                this.userLockedFields_ = input.readInt32();
                                break;
                            case 96:
                                this.bitField0_ |= 1024;
                                this.isVibrationEnabled_ = input.readBool();
                                break;
                            case 104:
                                this.bitField0_ |= 2048;
                                this.showBadge_ = input.readBool();
                                break;
                            case 112:
                                this.bitField0_ |= 4096;
                                this.isDeleted_ = input.readBool();
                                break;
                            case 122:
                                String s5 = input.readString();
                                this.bitField0_ |= 8192;
                                this.group_ = s5;
                                break;
                            case 130:
                                AudioAttributesProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder = (AudioAttributesProto.Builder) this.audioAttributes_.toBuilder();
                                }
                                this.audioAttributes_ = (AudioAttributesProto) input.readMessage(AudioAttributesProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.audioAttributes_);
                                    this.audioAttributes_ = (AudioAttributesProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 16384;
                                break;
                            case 136:
                                this.bitField0_ |= 32768;
                                this.isBlockableSystem_ = input.readBool();
                                break;
                            case 144:
                                this.bitField0_ |= 65536;
                                this.fgServiceShown_ = input.readBool();
                                break;
                            case 152:
                                this.bitField0_ |= 131072;
                                this.allowAppOverlay_ = input.readBool();
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (NotificationChannelProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static NotificationChannelProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NotificationChannelProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
