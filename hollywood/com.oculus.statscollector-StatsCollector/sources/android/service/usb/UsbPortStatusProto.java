package android.service.usb;

import android.service.usb.UsbPortProto;
import android.service.usb.UsbPortStatusRoleCombinationProto;
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

public final class UsbPortStatusProto extends GeneratedMessageLite<UsbPortStatusProto, Builder> implements UsbPortStatusProtoOrBuilder {
    public static final int CONNECTED_FIELD_NUMBER = 1;
    public static final int CONTAMINANT_PRESENCE_STATUS_FIELD_NUMBER = 6;
    public static final int CURRENT_MODE_FIELD_NUMBER = 2;
    public static final int DATA_ROLE_FIELD_NUMBER = 4;
    private static final UsbPortStatusProto DEFAULT_INSTANCE = new UsbPortStatusProto();
    private static volatile Parser<UsbPortStatusProto> PARSER = null;
    public static final int POWER_ROLE_FIELD_NUMBER = 3;
    public static final int ROLE_COMBINATIONS_FIELD_NUMBER = 5;
    private int bitField0_;
    private boolean connected_ = false;
    private int contaminantPresenceStatus_ = 0;
    private int currentMode_ = 0;
    private int dataRole_ = 0;
    private int powerRole_ = 0;
    private Internal.ProtobufList<UsbPortStatusRoleCombinationProto> roleCombinations_ = emptyProtobufList();

    private UsbPortStatusProto() {
    }

    public enum PowerRole implements Internal.EnumLite {
        POWER_ROLE_NONE(0),
        POWER_ROLE_SOURCE(1),
        POWER_ROLE_SINK(2);
        
        public static final int POWER_ROLE_NONE_VALUE = 0;
        public static final int POWER_ROLE_SINK_VALUE = 2;
        public static final int POWER_ROLE_SOURCE_VALUE = 1;
        private static final Internal.EnumLiteMap<PowerRole> internalValueMap = new Internal.EnumLiteMap<PowerRole>() {
            /* class android.service.usb.UsbPortStatusProto.PowerRole.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PowerRole findValueByNumber(int number) {
                return PowerRole.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PowerRole valueOf(int value2) {
            return forNumber(value2);
        }

        public static PowerRole forNumber(int value2) {
            if (value2 == 0) {
                return POWER_ROLE_NONE;
            }
            if (value2 == 1) {
                return POWER_ROLE_SOURCE;
            }
            if (value2 != 2) {
                return null;
            }
            return POWER_ROLE_SINK;
        }

        public static Internal.EnumLiteMap<PowerRole> internalGetValueMap() {
            return internalValueMap;
        }

        private PowerRole(int value2) {
            this.value = value2;
        }
    }

    public enum DataRole implements Internal.EnumLite {
        DATA_ROLE_NONE(0),
        DATA_ROLE_HOST(1),
        DATA_ROLE_DEVICE(2);
        
        public static final int DATA_ROLE_DEVICE_VALUE = 2;
        public static final int DATA_ROLE_HOST_VALUE = 1;
        public static final int DATA_ROLE_NONE_VALUE = 0;
        private static final Internal.EnumLiteMap<DataRole> internalValueMap = new Internal.EnumLiteMap<DataRole>() {
            /* class android.service.usb.UsbPortStatusProto.DataRole.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DataRole findValueByNumber(int number) {
                return DataRole.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static DataRole valueOf(int value2) {
            return forNumber(value2);
        }

        public static DataRole forNumber(int value2) {
            if (value2 == 0) {
                return DATA_ROLE_NONE;
            }
            if (value2 == 1) {
                return DATA_ROLE_HOST;
            }
            if (value2 != 2) {
                return null;
            }
            return DATA_ROLE_DEVICE;
        }

        public static Internal.EnumLiteMap<DataRole> internalGetValueMap() {
            return internalValueMap;
        }

        private DataRole(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public boolean hasConnected() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public boolean getConnected() {
        return this.connected_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnected(boolean value) {
        this.bitField0_ |= 1;
        this.connected_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnected() {
        this.bitField0_ &= -2;
        this.connected_ = false;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public boolean hasCurrentMode() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public UsbPortProto.Mode getCurrentMode() {
        UsbPortProto.Mode result = UsbPortProto.Mode.forNumber(this.currentMode_);
        return result == null ? UsbPortProto.Mode.MODE_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentMode(UsbPortProto.Mode value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.currentMode_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentMode() {
        this.bitField0_ &= -3;
        this.currentMode_ = 0;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public boolean hasPowerRole() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public PowerRole getPowerRole() {
        PowerRole result = PowerRole.forNumber(this.powerRole_);
        return result == null ? PowerRole.POWER_ROLE_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerRole(PowerRole value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.powerRole_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPowerRole() {
        this.bitField0_ &= -5;
        this.powerRole_ = 0;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public boolean hasDataRole() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public DataRole getDataRole() {
        DataRole result = DataRole.forNumber(this.dataRole_);
        return result == null ? DataRole.DATA_ROLE_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataRole(DataRole value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.dataRole_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataRole() {
        this.bitField0_ &= -9;
        this.dataRole_ = 0;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public List<UsbPortStatusRoleCombinationProto> getRoleCombinationsList() {
        return this.roleCombinations_;
    }

    public List<? extends UsbPortStatusRoleCombinationProtoOrBuilder> getRoleCombinationsOrBuilderList() {
        return this.roleCombinations_;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public int getRoleCombinationsCount() {
        return this.roleCombinations_.size();
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public UsbPortStatusRoleCombinationProto getRoleCombinations(int index) {
        return this.roleCombinations_.get(index);
    }

    public UsbPortStatusRoleCombinationProtoOrBuilder getRoleCombinationsOrBuilder(int index) {
        return this.roleCombinations_.get(index);
    }

    private void ensureRoleCombinationsIsMutable() {
        if (!this.roleCombinations_.isModifiable()) {
            this.roleCombinations_ = GeneratedMessageLite.mutableCopy(this.roleCombinations_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRoleCombinations(int index, UsbPortStatusRoleCombinationProto value) {
        if (value != null) {
            ensureRoleCombinationsIsMutable();
            this.roleCombinations_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRoleCombinations(int index, UsbPortStatusRoleCombinationProto.Builder builderForValue) {
        ensureRoleCombinationsIsMutable();
        this.roleCombinations_.set(index, (UsbPortStatusRoleCombinationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRoleCombinations(UsbPortStatusRoleCombinationProto value) {
        if (value != null) {
            ensureRoleCombinationsIsMutable();
            this.roleCombinations_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRoleCombinations(int index, UsbPortStatusRoleCombinationProto value) {
        if (value != null) {
            ensureRoleCombinationsIsMutable();
            this.roleCombinations_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRoleCombinations(UsbPortStatusRoleCombinationProto.Builder builderForValue) {
        ensureRoleCombinationsIsMutable();
        this.roleCombinations_.add((UsbPortStatusRoleCombinationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRoleCombinations(int index, UsbPortStatusRoleCombinationProto.Builder builderForValue) {
        ensureRoleCombinationsIsMutable();
        this.roleCombinations_.add(index, (UsbPortStatusRoleCombinationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRoleCombinations(Iterable<? extends UsbPortStatusRoleCombinationProto> values) {
        ensureRoleCombinationsIsMutable();
        AbstractMessageLite.addAll(values, this.roleCombinations_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRoleCombinations() {
        this.roleCombinations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRoleCombinations(int index) {
        ensureRoleCombinationsIsMutable();
        this.roleCombinations_.remove(index);
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public boolean hasContaminantPresenceStatus() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbPortStatusProtoOrBuilder
    public ContaminantPresenceStatus getContaminantPresenceStatus() {
        ContaminantPresenceStatus result = ContaminantPresenceStatus.forNumber(this.contaminantPresenceStatus_);
        return result == null ? ContaminantPresenceStatus.CONTAMINANT_STATUS_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContaminantPresenceStatus(ContaminantPresenceStatus value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.contaminantPresenceStatus_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContaminantPresenceStatus() {
        this.bitField0_ &= -17;
        this.contaminantPresenceStatus_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.connected_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.currentMode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.powerRole_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(4, this.dataRole_);
        }
        for (int i = 0; i < this.roleCombinations_.size(); i++) {
            output.writeMessage(5, this.roleCombinations_.get(i));
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeEnum(6, this.contaminantPresenceStatus_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.connected_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.currentMode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.powerRole_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(4, this.dataRole_);
        }
        for (int i = 0; i < this.roleCombinations_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.roleCombinations_.get(i));
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeEnumSize(6, this.contaminantPresenceStatus_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbPortStatusProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbPortStatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortStatusProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortStatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortStatusProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbPortStatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortStatusProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortStatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortStatusProto parseFrom(InputStream input) throws IOException {
        return (UsbPortStatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortStatusProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortStatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortStatusProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbPortStatusProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortStatusProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortStatusProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortStatusProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbPortStatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortStatusProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortStatusProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbPortStatusProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbPortStatusProto, Builder> implements UsbPortStatusProtoOrBuilder {
        private Builder() {
            super(UsbPortStatusProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public boolean hasConnected() {
            return ((UsbPortStatusProto) this.instance).hasConnected();
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public boolean getConnected() {
            return ((UsbPortStatusProto) this.instance).getConnected();
        }

        public Builder setConnected(boolean value) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).setConnected(value);
            return this;
        }

        public Builder clearConnected() {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).clearConnected();
            return this;
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public boolean hasCurrentMode() {
            return ((UsbPortStatusProto) this.instance).hasCurrentMode();
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public UsbPortProto.Mode getCurrentMode() {
            return ((UsbPortStatusProto) this.instance).getCurrentMode();
        }

        public Builder setCurrentMode(UsbPortProto.Mode value) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).setCurrentMode(value);
            return this;
        }

        public Builder clearCurrentMode() {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).clearCurrentMode();
            return this;
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public boolean hasPowerRole() {
            return ((UsbPortStatusProto) this.instance).hasPowerRole();
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public PowerRole getPowerRole() {
            return ((UsbPortStatusProto) this.instance).getPowerRole();
        }

        public Builder setPowerRole(PowerRole value) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).setPowerRole(value);
            return this;
        }

        public Builder clearPowerRole() {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).clearPowerRole();
            return this;
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public boolean hasDataRole() {
            return ((UsbPortStatusProto) this.instance).hasDataRole();
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public DataRole getDataRole() {
            return ((UsbPortStatusProto) this.instance).getDataRole();
        }

        public Builder setDataRole(DataRole value) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).setDataRole(value);
            return this;
        }

        public Builder clearDataRole() {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).clearDataRole();
            return this;
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public List<UsbPortStatusRoleCombinationProto> getRoleCombinationsList() {
            return Collections.unmodifiableList(((UsbPortStatusProto) this.instance).getRoleCombinationsList());
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public int getRoleCombinationsCount() {
            return ((UsbPortStatusProto) this.instance).getRoleCombinationsCount();
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public UsbPortStatusRoleCombinationProto getRoleCombinations(int index) {
            return ((UsbPortStatusProto) this.instance).getRoleCombinations(index);
        }

        public Builder setRoleCombinations(int index, UsbPortStatusRoleCombinationProto value) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).setRoleCombinations((UsbPortStatusProto) index, (int) value);
            return this;
        }

        public Builder setRoleCombinations(int index, UsbPortStatusRoleCombinationProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).setRoleCombinations((UsbPortStatusProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRoleCombinations(UsbPortStatusRoleCombinationProto value) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).addRoleCombinations((UsbPortStatusProto) value);
            return this;
        }

        public Builder addRoleCombinations(int index, UsbPortStatusRoleCombinationProto value) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).addRoleCombinations((UsbPortStatusProto) index, (int) value);
            return this;
        }

        public Builder addRoleCombinations(UsbPortStatusRoleCombinationProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).addRoleCombinations((UsbPortStatusProto) builderForValue);
            return this;
        }

        public Builder addRoleCombinations(int index, UsbPortStatusRoleCombinationProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).addRoleCombinations((UsbPortStatusProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRoleCombinations(Iterable<? extends UsbPortStatusRoleCombinationProto> values) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).addAllRoleCombinations(values);
            return this;
        }

        public Builder clearRoleCombinations() {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).clearRoleCombinations();
            return this;
        }

        public Builder removeRoleCombinations(int index) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).removeRoleCombinations(index);
            return this;
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public boolean hasContaminantPresenceStatus() {
            return ((UsbPortStatusProto) this.instance).hasContaminantPresenceStatus();
        }

        @Override // android.service.usb.UsbPortStatusProtoOrBuilder
        public ContaminantPresenceStatus getContaminantPresenceStatus() {
            return ((UsbPortStatusProto) this.instance).getContaminantPresenceStatus();
        }

        public Builder setContaminantPresenceStatus(ContaminantPresenceStatus value) {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).setContaminantPresenceStatus(value);
            return this;
        }

        public Builder clearContaminantPresenceStatus() {
            copyOnWrite();
            ((UsbPortStatusProto) this.instance).clearContaminantPresenceStatus();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbPortStatusProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.roleCombinations_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbPortStatusProto other = (UsbPortStatusProto) arg1;
                this.connected_ = visitor.visitBoolean(hasConnected(), this.connected_, other.hasConnected(), other.connected_);
                this.currentMode_ = visitor.visitInt(hasCurrentMode(), this.currentMode_, other.hasCurrentMode(), other.currentMode_);
                this.powerRole_ = visitor.visitInt(hasPowerRole(), this.powerRole_, other.hasPowerRole(), other.powerRole_);
                this.dataRole_ = visitor.visitInt(hasDataRole(), this.dataRole_, other.hasDataRole(), other.dataRole_);
                this.roleCombinations_ = visitor.visitList(this.roleCombinations_, other.roleCombinations_);
                this.contaminantPresenceStatus_ = visitor.visitInt(hasContaminantPresenceStatus(), this.contaminantPresenceStatus_, other.hasContaminantPresenceStatus(), other.contaminantPresenceStatus_);
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
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.connected_ = input.readBool();
                        } else if (tag == 16) {
                            int rawValue = input.readEnum();
                            if (UsbPortProto.Mode.forNumber(rawValue) == null) {
                                super.mergeVarintField(2, rawValue);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.currentMode_ = rawValue;
                            }
                        } else if (tag == 24) {
                            int rawValue2 = input.readEnum();
                            if (PowerRole.forNumber(rawValue2) == null) {
                                super.mergeVarintField(3, rawValue2);
                            } else {
                                this.bitField0_ |= 4;
                                this.powerRole_ = rawValue2;
                            }
                        } else if (tag == 32) {
                            int rawValue3 = input.readEnum();
                            if (DataRole.forNumber(rawValue3) == null) {
                                super.mergeVarintField(4, rawValue3);
                            } else {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.dataRole_ = rawValue3;
                            }
                        } else if (tag == 42) {
                            if (!this.roleCombinations_.isModifiable()) {
                                this.roleCombinations_ = GeneratedMessageLite.mutableCopy(this.roleCombinations_);
                            }
                            this.roleCombinations_.add((UsbPortStatusRoleCombinationProto) input.readMessage(UsbPortStatusRoleCombinationProto.parser(), extensionRegistry));
                        } else if (tag == 48) {
                            int rawValue4 = input.readEnum();
                            if (ContaminantPresenceStatus.forNumber(rawValue4) == null) {
                                super.mergeVarintField(6, rawValue4);
                            } else {
                                this.bitField0_ = 16 | this.bitField0_;
                                this.contaminantPresenceStatus_ = rawValue4;
                            }
                        } else if (!parseUnknownField(tag, input)) {
                            done = true;
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
                    synchronized (UsbPortStatusProto.class) {
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

    public static UsbPortStatusProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbPortStatusProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
