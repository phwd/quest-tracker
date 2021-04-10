package android.service.notification;

import android.media.AudioAttributesProto;
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

public final class NotificationRecordProto extends GeneratedMessageLite<NotificationRecordProto, Builder> implements NotificationRecordProtoOrBuilder {
    public static final int AUDIO_ATTRIBUTES_FIELD_NUMBER = 6;
    public static final int CAN_SHOW_LIGHT_FIELD_NUMBER = 8;
    public static final int CAN_VIBRATE_FIELD_NUMBER = 7;
    public static final int CHANNEL_ID_FIELD_NUMBER = 4;
    private static final NotificationRecordProto DEFAULT_INSTANCE = new NotificationRecordProto();
    public static final int DELEGATE_PACKAGE_FIELD_NUMBER = 12;
    public static final int FLAGS_FIELD_NUMBER = 3;
    public static final int GROUP_KEY_FIELD_NUMBER = 9;
    public static final int IMPORTANCE_FIELD_NUMBER = 10;
    public static final int KEY_FIELD_NUMBER = 1;
    public static final int PACKAGE_FIELD_NUMBER = 11;
    private static volatile Parser<NotificationRecordProto> PARSER = null;
    public static final int SOUND_FIELD_NUMBER = 5;
    public static final int STATE_FIELD_NUMBER = 2;
    private AudioAttributesProto audioAttributes_;
    private int bitField0_;
    private boolean canShowLight_ = false;
    private boolean canVibrate_ = false;
    private String channelId_ = "";
    private String delegatePackage_ = "";
    private int flags_ = 0;
    private String groupKey_ = "";
    private int importance_ = 0;
    private String key_ = "";
    private String package_ = "";
    private String sound_ = "";
    private int state_ = 0;

    private NotificationRecordProto() {
    }

    public enum State implements Internal.EnumLite {
        ENQUEUED(0),
        POSTED(1),
        SNOOZED(2);
        
        public static final int ENQUEUED_VALUE = 0;
        public static final int POSTED_VALUE = 1;
        public static final int SNOOZED_VALUE = 2;
        private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() {
            /* class android.service.notification.NotificationRecordProto.State.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public State findValueByNumber(int number) {
                return State.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static State valueOf(int value2) {
            return forNumber(value2);
        }

        public static State forNumber(int value2) {
            if (value2 == 0) {
                return ENQUEUED;
            }
            if (value2 == 1) {
                return POSTED;
            }
            if (value2 != 2) {
                return null;
            }
            return SNOOZED;
        }

        public static Internal.EnumLiteMap<State> internalGetValueMap() {
            return internalValueMap;
        }

        private State(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasKey() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public String getKey() {
        return this.key_;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public ByteString getKeyBytes() {
        return ByteString.copyFromUtf8(this.key_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKey(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.key_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKey() {
        this.bitField0_ &= -2;
        this.key_ = getDefaultInstance().getKey();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.key_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasState() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public State getState() {
        State result = State.forNumber(this.state_);
        return result == null ? State.ENQUEUED : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(State value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.state_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearState() {
        this.bitField0_ &= -3;
        this.state_ = 0;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 4;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -5;
        this.flags_ = 0;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasChannelId() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public String getChannelId() {
        return this.channelId_;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public ByteString getChannelIdBytes() {
        return ByteString.copyFromUtf8(this.channelId_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChannelId(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.channelId_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearChannelId() {
        this.bitField0_ &= -9;
        this.channelId_ = getDefaultInstance().getChannelId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setChannelIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.channelId_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasSound() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public String getSound() {
        return this.sound_;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public ByteString getSoundBytes() {
        return ByteString.copyFromUtf8(this.sound_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSound(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.sound_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSound() {
        this.bitField0_ &= -17;
        this.sound_ = getDefaultInstance().getSound();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSoundBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.sound_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasAudioAttributes() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public AudioAttributesProto getAudioAttributes() {
        AudioAttributesProto audioAttributesProto = this.audioAttributes_;
        return audioAttributesProto == null ? AudioAttributesProto.getDefaultInstance() : audioAttributesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudioAttributes(AudioAttributesProto value) {
        if (value != null) {
            this.audioAttributes_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudioAttributes(AudioAttributesProto.Builder builderForValue) {
        this.audioAttributes_ = (AudioAttributesProto) builderForValue.build();
        this.bitField0_ |= 32;
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
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAudioAttributes() {
        this.audioAttributes_ = null;
        this.bitField0_ &= -33;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasCanVibrate() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean getCanVibrate() {
        return this.canVibrate_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCanVibrate(boolean value) {
        this.bitField0_ |= 64;
        this.canVibrate_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCanVibrate() {
        this.bitField0_ &= -65;
        this.canVibrate_ = false;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasCanShowLight() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean getCanShowLight() {
        return this.canShowLight_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCanShowLight(boolean value) {
        this.bitField0_ |= 128;
        this.canShowLight_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCanShowLight() {
        this.bitField0_ &= -129;
        this.canShowLight_ = false;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasGroupKey() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public String getGroupKey() {
        return this.groupKey_;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public ByteString getGroupKeyBytes() {
        return ByteString.copyFromUtf8(this.groupKey_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGroupKey(String value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.groupKey_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGroupKey() {
        this.bitField0_ &= -257;
        this.groupKey_ = getDefaultInstance().getGroupKey();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGroupKeyBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.groupKey_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasImportance() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public int getImportance() {
        return this.importance_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImportance(int value) {
        this.bitField0_ |= 512;
        this.importance_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearImportance() {
        this.bitField0_ &= -513;
        this.importance_ = 0;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasPackage() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public String getPackage() {
        return this.package_;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public ByteString getPackageBytes() {
        return ByteString.copyFromUtf8(this.package_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackage(String value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.package_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackage() {
        this.bitField0_ &= -1025;
        this.package_ = getDefaultInstance().getPackage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.package_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public boolean hasDelegatePackage() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public String getDelegatePackage() {
        return this.delegatePackage_;
    }

    @Override // android.service.notification.NotificationRecordProtoOrBuilder
    public ByteString getDelegatePackageBytes() {
        return ByteString.copyFromUtf8(this.delegatePackage_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDelegatePackage(String value) {
        if (value != null) {
            this.bitField0_ |= 2048;
            this.delegatePackage_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDelegatePackage() {
        this.bitField0_ &= -2049;
        this.delegatePackage_ = getDefaultInstance().getDelegatePackage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDelegatePackageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2048;
            this.delegatePackage_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getKey());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.state_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.flags_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getChannelId());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getSound());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getAudioAttributes());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.canVibrate_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(8, this.canShowLight_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeString(9, getGroupKey());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeSInt32(10, this.importance_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeString(11, getPackage());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeString(12, getDelegatePackage());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getKey());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.state_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.flags_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getChannelId());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getSound());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getAudioAttributes());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.canVibrate_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeBoolSize(8, this.canShowLight_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeStringSize(9, getGroupKey());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeSInt32Size(10, this.importance_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeStringSize(11, getPackage());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeStringSize(12, getDelegatePackage());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NotificationRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NotificationRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NotificationRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationRecordProto parseFrom(InputStream input) throws IOException {
        return (NotificationRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NotificationRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (NotificationRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NotificationRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NotificationRecordProto, Builder> implements NotificationRecordProtoOrBuilder {
        private Builder() {
            super(NotificationRecordProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasKey() {
            return ((NotificationRecordProto) this.instance).hasKey();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public String getKey() {
            return ((NotificationRecordProto) this.instance).getKey();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public ByteString getKeyBytes() {
            return ((NotificationRecordProto) this.instance).getKeyBytes();
        }

        public Builder setKey(String value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setKey(value);
            return this;
        }

        public Builder clearKey() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearKey();
            return this;
        }

        public Builder setKeyBytes(ByteString value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setKeyBytes(value);
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasState() {
            return ((NotificationRecordProto) this.instance).hasState();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public State getState() {
            return ((NotificationRecordProto) this.instance).getState();
        }

        public Builder setState(State value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearState();
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasFlags() {
            return ((NotificationRecordProto) this.instance).hasFlags();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public int getFlags() {
            return ((NotificationRecordProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearFlags();
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasChannelId() {
            return ((NotificationRecordProto) this.instance).hasChannelId();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public String getChannelId() {
            return ((NotificationRecordProto) this.instance).getChannelId();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public ByteString getChannelIdBytes() {
            return ((NotificationRecordProto) this.instance).getChannelIdBytes();
        }

        public Builder setChannelId(String value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setChannelId(value);
            return this;
        }

        public Builder clearChannelId() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearChannelId();
            return this;
        }

        public Builder setChannelIdBytes(ByteString value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setChannelIdBytes(value);
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasSound() {
            return ((NotificationRecordProto) this.instance).hasSound();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public String getSound() {
            return ((NotificationRecordProto) this.instance).getSound();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public ByteString getSoundBytes() {
            return ((NotificationRecordProto) this.instance).getSoundBytes();
        }

        public Builder setSound(String value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setSound(value);
            return this;
        }

        public Builder clearSound() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearSound();
            return this;
        }

        public Builder setSoundBytes(ByteString value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setSoundBytes(value);
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasAudioAttributes() {
            return ((NotificationRecordProto) this.instance).hasAudioAttributes();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public AudioAttributesProto getAudioAttributes() {
            return ((NotificationRecordProto) this.instance).getAudioAttributes();
        }

        public Builder setAudioAttributes(AudioAttributesProto value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setAudioAttributes((NotificationRecordProto) value);
            return this;
        }

        public Builder setAudioAttributes(AudioAttributesProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setAudioAttributes((NotificationRecordProto) builderForValue);
            return this;
        }

        public Builder mergeAudioAttributes(AudioAttributesProto value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).mergeAudioAttributes(value);
            return this;
        }

        public Builder clearAudioAttributes() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearAudioAttributes();
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasCanVibrate() {
            return ((NotificationRecordProto) this.instance).hasCanVibrate();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean getCanVibrate() {
            return ((NotificationRecordProto) this.instance).getCanVibrate();
        }

        public Builder setCanVibrate(boolean value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setCanVibrate(value);
            return this;
        }

        public Builder clearCanVibrate() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearCanVibrate();
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasCanShowLight() {
            return ((NotificationRecordProto) this.instance).hasCanShowLight();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean getCanShowLight() {
            return ((NotificationRecordProto) this.instance).getCanShowLight();
        }

        public Builder setCanShowLight(boolean value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setCanShowLight(value);
            return this;
        }

        public Builder clearCanShowLight() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearCanShowLight();
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasGroupKey() {
            return ((NotificationRecordProto) this.instance).hasGroupKey();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public String getGroupKey() {
            return ((NotificationRecordProto) this.instance).getGroupKey();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public ByteString getGroupKeyBytes() {
            return ((NotificationRecordProto) this.instance).getGroupKeyBytes();
        }

        public Builder setGroupKey(String value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setGroupKey(value);
            return this;
        }

        public Builder clearGroupKey() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearGroupKey();
            return this;
        }

        public Builder setGroupKeyBytes(ByteString value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setGroupKeyBytes(value);
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasImportance() {
            return ((NotificationRecordProto) this.instance).hasImportance();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public int getImportance() {
            return ((NotificationRecordProto) this.instance).getImportance();
        }

        public Builder setImportance(int value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setImportance(value);
            return this;
        }

        public Builder clearImportance() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearImportance();
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasPackage() {
            return ((NotificationRecordProto) this.instance).hasPackage();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public String getPackage() {
            return ((NotificationRecordProto) this.instance).getPackage();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public ByteString getPackageBytes() {
            return ((NotificationRecordProto) this.instance).getPackageBytes();
        }

        public Builder setPackage(String value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setPackage(value);
            return this;
        }

        public Builder clearPackage() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearPackage();
            return this;
        }

        public Builder setPackageBytes(ByteString value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setPackageBytes(value);
            return this;
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public boolean hasDelegatePackage() {
            return ((NotificationRecordProto) this.instance).hasDelegatePackage();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public String getDelegatePackage() {
            return ((NotificationRecordProto) this.instance).getDelegatePackage();
        }

        @Override // android.service.notification.NotificationRecordProtoOrBuilder
        public ByteString getDelegatePackageBytes() {
            return ((NotificationRecordProto) this.instance).getDelegatePackageBytes();
        }

        public Builder setDelegatePackage(String value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setDelegatePackage(value);
            return this;
        }

        public Builder clearDelegatePackage() {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).clearDelegatePackage();
            return this;
        }

        public Builder setDelegatePackageBytes(ByteString value) {
            copyOnWrite();
            ((NotificationRecordProto) this.instance).setDelegatePackageBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NotificationRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NotificationRecordProto other = (NotificationRecordProto) arg1;
                this.key_ = visitor.visitString(hasKey(), this.key_, other.hasKey(), other.key_);
                this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.channelId_ = visitor.visitString(hasChannelId(), this.channelId_, other.hasChannelId(), other.channelId_);
                this.sound_ = visitor.visitString(hasSound(), this.sound_, other.hasSound(), other.sound_);
                this.audioAttributes_ = (AudioAttributesProto) visitor.visitMessage(this.audioAttributes_, other.audioAttributes_);
                this.canVibrate_ = visitor.visitBoolean(hasCanVibrate(), this.canVibrate_, other.hasCanVibrate(), other.canVibrate_);
                this.canShowLight_ = visitor.visitBoolean(hasCanShowLight(), this.canShowLight_, other.hasCanShowLight(), other.canShowLight_);
                this.groupKey_ = visitor.visitString(hasGroupKey(), this.groupKey_, other.hasGroupKey(), other.groupKey_);
                this.importance_ = visitor.visitInt(hasImportance(), this.importance_, other.hasImportance(), other.importance_);
                this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                this.delegatePackage_ = visitor.visitString(hasDelegatePackage(), this.delegatePackage_, other.hasDelegatePackage(), other.delegatePackage_);
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
                                this.key_ = s;
                                break;
                            case 16:
                                int rawValue = input.readEnum();
                                if (State.forNumber(rawValue) != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.state_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(2, rawValue);
                                    break;
                                }
                            case 24:
                                this.bitField0_ |= 4;
                                this.flags_ = input.readInt32();
                                break;
                            case 34:
                                String s2 = input.readString();
                                this.bitField0_ |= 8;
                                this.channelId_ = s2;
                                break;
                            case 42:
                                String s3 = input.readString();
                                this.bitField0_ |= 16;
                                this.sound_ = s3;
                                break;
                            case 50:
                                AudioAttributesProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder = (AudioAttributesProto.Builder) this.audioAttributes_.toBuilder();
                                }
                                this.audioAttributes_ = (AudioAttributesProto) input.readMessage(AudioAttributesProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.audioAttributes_);
                                    this.audioAttributes_ = (AudioAttributesProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.canVibrate_ = input.readBool();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.canShowLight_ = input.readBool();
                                break;
                            case 74:
                                String s4 = input.readString();
                                this.bitField0_ |= 256;
                                this.groupKey_ = s4;
                                break;
                            case 80:
                                this.bitField0_ |= 512;
                                this.importance_ = input.readSInt32();
                                break;
                            case 90:
                                String s5 = input.readString();
                                this.bitField0_ |= 1024;
                                this.package_ = s5;
                                break;
                            case 98:
                                String s6 = input.readString();
                                this.bitField0_ |= 2048;
                                this.delegatePackage_ = s6;
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
                    synchronized (NotificationRecordProto.class) {
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

    public static NotificationRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NotificationRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
