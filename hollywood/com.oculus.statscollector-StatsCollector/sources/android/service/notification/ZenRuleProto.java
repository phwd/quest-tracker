package android.service.notification;

import android.content.ComponentNameProto;
import android.service.notification.ConditionProto;
import android.service.notification.ZenPolicyProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ZenRuleProto extends GeneratedMessageLite<ZenRuleProto, Builder> implements ZenRuleProtoOrBuilder {
    public static final int COMPONENT_FIELD_NUMBER = 10;
    public static final int CONDITION_FIELD_NUMBER = 9;
    public static final int CONDITION_ID_FIELD_NUMBER = 8;
    public static final int CREATION_TIME_MS_FIELD_NUMBER = 3;
    private static final ZenRuleProto DEFAULT_INSTANCE = new ZenRuleProto();
    public static final int ENABLED_FIELD_NUMBER = 4;
    public static final int ENABLER_FIELD_NUMBER = 5;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int IS_SNOOZING_FIELD_NUMBER = 6;
    public static final int MODIFIED_FIELD_NUMBER = 12;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser<ZenRuleProto> PARSER = null;
    public static final int ZENPOLICY_FIELD_NUMBER = 11;
    public static final int ZEN_MODE_FIELD_NUMBER = 7;
    private int bitField0_;
    private ComponentNameProto component_;
    private String conditionId_ = "";
    private ConditionProto condition_;
    private long creationTimeMs_ = 0;
    private boolean enabled_ = false;
    private String enabler_ = "";
    private String id_ = "";
    private boolean isSnoozing_ = false;
    private boolean modified_ = false;
    private String name_ = "";
    private int zenMode_ = 0;
    private ZenPolicyProto zenPolicy_;

    private ZenRuleProto() {
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
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

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
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

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasCreationTimeMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public long getCreationTimeMs() {
        return this.creationTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCreationTimeMs(long value) {
        this.bitField0_ |= 4;
        this.creationTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCreationTimeMs() {
        this.bitField0_ &= -5;
        this.creationTimeMs_ = 0;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasEnabled() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean getEnabled() {
        return this.enabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabled(boolean value) {
        this.bitField0_ |= 8;
        this.enabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEnabled() {
        this.bitField0_ &= -9;
        this.enabled_ = false;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasEnabler() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public String getEnabler() {
        return this.enabler_;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public ByteString getEnablerBytes() {
        return ByteString.copyFromUtf8(this.enabler_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabler(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.enabler_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEnabler() {
        this.bitField0_ &= -17;
        this.enabler_ = getDefaultInstance().getEnabler();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnablerBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.enabler_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasIsSnoozing() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean getIsSnoozing() {
        return this.isSnoozing_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsSnoozing(boolean value) {
        this.bitField0_ |= 32;
        this.isSnoozing_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsSnoozing() {
        this.bitField0_ &= -33;
        this.isSnoozing_ = false;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasZenMode() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public ZenMode getZenMode() {
        ZenMode result = ZenMode.forNumber(this.zenMode_);
        return result == null ? ZenMode.ZEN_MODE_OFF : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZenMode(ZenMode value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.zenMode_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearZenMode() {
        this.bitField0_ &= -65;
        this.zenMode_ = 0;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasConditionId() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public String getConditionId() {
        return this.conditionId_;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public ByteString getConditionIdBytes() {
        return ByteString.copyFromUtf8(this.conditionId_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConditionId(String value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.conditionId_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConditionId() {
        this.bitField0_ &= -129;
        this.conditionId_ = getDefaultInstance().getConditionId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConditionIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.conditionId_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasCondition() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public ConditionProto getCondition() {
        ConditionProto conditionProto = this.condition_;
        return conditionProto == null ? ConditionProto.getDefaultInstance() : conditionProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCondition(ConditionProto value) {
        if (value != null) {
            this.condition_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCondition(ConditionProto.Builder builderForValue) {
        this.condition_ = (ConditionProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCondition(ConditionProto value) {
        ConditionProto conditionProto = this.condition_;
        if (conditionProto == null || conditionProto == ConditionProto.getDefaultInstance()) {
            this.condition_ = value;
        } else {
            this.condition_ = (ConditionProto) ((ConditionProto.Builder) ConditionProto.newBuilder(this.condition_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCondition() {
        this.condition_ = null;
        this.bitField0_ &= -257;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasComponent() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public ComponentNameProto getComponent() {
        ComponentNameProto componentNameProto = this.component_;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponent(ComponentNameProto value) {
        if (value != null) {
            this.component_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponent(ComponentNameProto.Builder builderForValue) {
        this.component_ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeComponent(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.component_;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.component_ = value;
        } else {
            this.component_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.component_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearComponent() {
        this.component_ = null;
        this.bitField0_ &= -513;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasZenPolicy() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public ZenPolicyProto getZenPolicy() {
        ZenPolicyProto zenPolicyProto = this.zenPolicy_;
        return zenPolicyProto == null ? ZenPolicyProto.getDefaultInstance() : zenPolicyProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZenPolicy(ZenPolicyProto value) {
        if (value != null) {
            this.zenPolicy_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZenPolicy(ZenPolicyProto.Builder builderForValue) {
        this.zenPolicy_ = (ZenPolicyProto) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeZenPolicy(ZenPolicyProto value) {
        ZenPolicyProto zenPolicyProto = this.zenPolicy_;
        if (zenPolicyProto == null || zenPolicyProto == ZenPolicyProto.getDefaultInstance()) {
            this.zenPolicy_ = value;
        } else {
            this.zenPolicy_ = (ZenPolicyProto) ((ZenPolicyProto.Builder) ZenPolicyProto.newBuilder(this.zenPolicy_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearZenPolicy() {
        this.zenPolicy_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean hasModified() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.service.notification.ZenRuleProtoOrBuilder
    public boolean getModified() {
        return this.modified_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModified(boolean value) {
        this.bitField0_ |= 2048;
        this.modified_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModified() {
        this.bitField0_ &= -2049;
        this.modified_ = false;
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
            output.writeInt64(3, this.creationTimeMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.enabled_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getEnabler());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.isSnoozing_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeEnum(7, this.zenMode_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeString(8, getConditionId());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getCondition());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(10, getComponent());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(11, getZenPolicy());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(12, this.modified_);
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
            size2 += CodedOutputStream.computeInt64Size(3, this.creationTimeMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.enabled_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getEnabler());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.isSnoozing_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeEnumSize(7, this.zenMode_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeStringSize(8, getConditionId());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getCondition());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(10, getComponent());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(11, getZenPolicy());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeBoolSize(12, this.modified_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ZenRuleProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ZenRuleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ZenRuleProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ZenRuleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ZenRuleProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ZenRuleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ZenRuleProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ZenRuleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ZenRuleProto parseFrom(InputStream input) throws IOException {
        return (ZenRuleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenRuleProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenRuleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ZenRuleProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ZenRuleProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenRuleProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenRuleProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ZenRuleProto parseFrom(CodedInputStream input) throws IOException {
        return (ZenRuleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenRuleProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenRuleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ZenRuleProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ZenRuleProto, Builder> implements ZenRuleProtoOrBuilder {
        private Builder() {
            super(ZenRuleProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasId() {
            return ((ZenRuleProto) this.instance).hasId();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public String getId() {
            return ((ZenRuleProto) this.instance).getId();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public ByteString getIdBytes() {
            return ((ZenRuleProto) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setIdBytes(value);
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasName() {
            return ((ZenRuleProto) this.instance).hasName();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public String getName() {
            return ((ZenRuleProto) this.instance).getName();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public ByteString getNameBytes() {
            return ((ZenRuleProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasCreationTimeMs() {
            return ((ZenRuleProto) this.instance).hasCreationTimeMs();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public long getCreationTimeMs() {
            return ((ZenRuleProto) this.instance).getCreationTimeMs();
        }

        public Builder setCreationTimeMs(long value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setCreationTimeMs(value);
            return this;
        }

        public Builder clearCreationTimeMs() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearCreationTimeMs();
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasEnabled() {
            return ((ZenRuleProto) this.instance).hasEnabled();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean getEnabled() {
            return ((ZenRuleProto) this.instance).getEnabled();
        }

        public Builder setEnabled(boolean value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setEnabled(value);
            return this;
        }

        public Builder clearEnabled() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearEnabled();
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasEnabler() {
            return ((ZenRuleProto) this.instance).hasEnabler();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public String getEnabler() {
            return ((ZenRuleProto) this.instance).getEnabler();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public ByteString getEnablerBytes() {
            return ((ZenRuleProto) this.instance).getEnablerBytes();
        }

        public Builder setEnabler(String value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setEnabler(value);
            return this;
        }

        public Builder clearEnabler() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearEnabler();
            return this;
        }

        public Builder setEnablerBytes(ByteString value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setEnablerBytes(value);
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasIsSnoozing() {
            return ((ZenRuleProto) this.instance).hasIsSnoozing();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean getIsSnoozing() {
            return ((ZenRuleProto) this.instance).getIsSnoozing();
        }

        public Builder setIsSnoozing(boolean value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setIsSnoozing(value);
            return this;
        }

        public Builder clearIsSnoozing() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearIsSnoozing();
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasZenMode() {
            return ((ZenRuleProto) this.instance).hasZenMode();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public ZenMode getZenMode() {
            return ((ZenRuleProto) this.instance).getZenMode();
        }

        public Builder setZenMode(ZenMode value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setZenMode(value);
            return this;
        }

        public Builder clearZenMode() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearZenMode();
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasConditionId() {
            return ((ZenRuleProto) this.instance).hasConditionId();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public String getConditionId() {
            return ((ZenRuleProto) this.instance).getConditionId();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public ByteString getConditionIdBytes() {
            return ((ZenRuleProto) this.instance).getConditionIdBytes();
        }

        public Builder setConditionId(String value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setConditionId(value);
            return this;
        }

        public Builder clearConditionId() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearConditionId();
            return this;
        }

        public Builder setConditionIdBytes(ByteString value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setConditionIdBytes(value);
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasCondition() {
            return ((ZenRuleProto) this.instance).hasCondition();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public ConditionProto getCondition() {
            return ((ZenRuleProto) this.instance).getCondition();
        }

        public Builder setCondition(ConditionProto value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setCondition((ZenRuleProto) value);
            return this;
        }

        public Builder setCondition(ConditionProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setCondition((ZenRuleProto) builderForValue);
            return this;
        }

        public Builder mergeCondition(ConditionProto value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).mergeCondition(value);
            return this;
        }

        public Builder clearCondition() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearCondition();
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasComponent() {
            return ((ZenRuleProto) this.instance).hasComponent();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public ComponentNameProto getComponent() {
            return ((ZenRuleProto) this.instance).getComponent();
        }

        public Builder setComponent(ComponentNameProto value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setComponent((ZenRuleProto) value);
            return this;
        }

        public Builder setComponent(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setComponent((ZenRuleProto) builderForValue);
            return this;
        }

        public Builder mergeComponent(ComponentNameProto value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).mergeComponent(value);
            return this;
        }

        public Builder clearComponent() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearComponent();
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasZenPolicy() {
            return ((ZenRuleProto) this.instance).hasZenPolicy();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public ZenPolicyProto getZenPolicy() {
            return ((ZenRuleProto) this.instance).getZenPolicy();
        }

        public Builder setZenPolicy(ZenPolicyProto value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setZenPolicy((ZenRuleProto) value);
            return this;
        }

        public Builder setZenPolicy(ZenPolicyProto.Builder builderForValue) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setZenPolicy((ZenRuleProto) builderForValue);
            return this;
        }

        public Builder mergeZenPolicy(ZenPolicyProto value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).mergeZenPolicy(value);
            return this;
        }

        public Builder clearZenPolicy() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearZenPolicy();
            return this;
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean hasModified() {
            return ((ZenRuleProto) this.instance).hasModified();
        }

        @Override // android.service.notification.ZenRuleProtoOrBuilder
        public boolean getModified() {
            return ((ZenRuleProto) this.instance).getModified();
        }

        public Builder setModified(boolean value) {
            copyOnWrite();
            ((ZenRuleProto) this.instance).setModified(value);
            return this;
        }

        public Builder clearModified() {
            copyOnWrite();
            ((ZenRuleProto) this.instance).clearModified();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ZenRuleProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ZenRuleProto other = (ZenRuleProto) arg1;
                this.id_ = visitor.visitString(hasId(), this.id_, other.hasId(), other.id_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.creationTimeMs_ = visitor.visitLong(hasCreationTimeMs(), this.creationTimeMs_, other.hasCreationTimeMs(), other.creationTimeMs_);
                this.enabled_ = visitor.visitBoolean(hasEnabled(), this.enabled_, other.hasEnabled(), other.enabled_);
                this.enabler_ = visitor.visitString(hasEnabler(), this.enabler_, other.hasEnabler(), other.enabler_);
                this.isSnoozing_ = visitor.visitBoolean(hasIsSnoozing(), this.isSnoozing_, other.hasIsSnoozing(), other.isSnoozing_);
                this.zenMode_ = visitor.visitInt(hasZenMode(), this.zenMode_, other.hasZenMode(), other.zenMode_);
                this.conditionId_ = visitor.visitString(hasConditionId(), this.conditionId_, other.hasConditionId(), other.conditionId_);
                this.condition_ = (ConditionProto) visitor.visitMessage(this.condition_, other.condition_);
                this.component_ = (ComponentNameProto) visitor.visitMessage(this.component_, other.component_);
                this.zenPolicy_ = (ZenPolicyProto) visitor.visitMessage(this.zenPolicy_, other.zenPolicy_);
                this.modified_ = visitor.visitBoolean(hasModified(), this.modified_, other.hasModified(), other.modified_);
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
                            case 24:
                                this.bitField0_ |= 4;
                                this.creationTimeMs_ = input.readInt64();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.enabled_ = input.readBool();
                                break;
                            case 42:
                                String s3 = input.readString();
                                this.bitField0_ |= 16;
                                this.enabler_ = s3;
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.isSnoozing_ = input.readBool();
                                break;
                            case 56:
                                int rawValue = input.readEnum();
                                if (ZenMode.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 64;
                                    this.zenMode_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(7, rawValue);
                                    break;
                                }
                            case 66:
                                String s4 = input.readString();
                                this.bitField0_ |= 128;
                                this.conditionId_ = s4;
                                break;
                            case 74:
                                ConditionProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder = (ConditionProto.Builder) this.condition_.toBuilder();
                                }
                                this.condition_ = (ConditionProto) input.readMessage(ConditionProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.condition_);
                                    this.condition_ = (ConditionProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                ComponentNameProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder2 = (ComponentNameProto.Builder) this.component_.toBuilder();
                                }
                                this.component_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.component_);
                                    this.component_ = (ComponentNameProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 90:
                                ZenPolicyProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder3 = (ZenPolicyProto.Builder) this.zenPolicy_.toBuilder();
                                }
                                this.zenPolicy_ = (ZenPolicyProto) input.readMessage(ZenPolicyProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.zenPolicy_);
                                    this.zenPolicy_ = (ZenPolicyProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.modified_ = input.readBool();
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
                    synchronized (ZenRuleProto.class) {
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

    public static ZenRuleProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ZenRuleProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
