package android.service.usb;

import android.service.usb.UsbPortStatusProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UsbPortStatusRoleCombinationProto extends GeneratedMessageLite<UsbPortStatusRoleCombinationProto, Builder> implements UsbPortStatusRoleCombinationProtoOrBuilder {
    public static final int DATA_ROLE_FIELD_NUMBER = 2;
    private static final UsbPortStatusRoleCombinationProto DEFAULT_INSTANCE = new UsbPortStatusRoleCombinationProto();
    private static volatile Parser<UsbPortStatusRoleCombinationProto> PARSER = null;
    public static final int POWER_ROLE_FIELD_NUMBER = 1;
    private int bitField0_;
    private int dataRole_ = 0;
    private int powerRole_ = 0;

    private UsbPortStatusRoleCombinationProto() {
    }

    @Override // android.service.usb.UsbPortStatusRoleCombinationProtoOrBuilder
    public boolean hasPowerRole() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbPortStatusRoleCombinationProtoOrBuilder
    public UsbPortStatusProto.PowerRole getPowerRole() {
        UsbPortStatusProto.PowerRole result = UsbPortStatusProto.PowerRole.forNumber(this.powerRole_);
        return result == null ? UsbPortStatusProto.PowerRole.POWER_ROLE_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPowerRole(UsbPortStatusProto.PowerRole value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.powerRole_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPowerRole() {
        this.bitField0_ &= -2;
        this.powerRole_ = 0;
    }

    @Override // android.service.usb.UsbPortStatusRoleCombinationProtoOrBuilder
    public boolean hasDataRole() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbPortStatusRoleCombinationProtoOrBuilder
    public UsbPortStatusProto.DataRole getDataRole() {
        UsbPortStatusProto.DataRole result = UsbPortStatusProto.DataRole.forNumber(this.dataRole_);
        return result == null ? UsbPortStatusProto.DataRole.DATA_ROLE_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataRole(UsbPortStatusProto.DataRole value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.dataRole_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataRole() {
        this.bitField0_ &= -3;
        this.dataRole_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.powerRole_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.dataRole_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.powerRole_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.dataRole_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbPortStatusRoleCombinationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbPortStatusRoleCombinationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortStatusRoleCombinationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortStatusRoleCombinationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortStatusRoleCombinationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbPortStatusRoleCombinationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortStatusRoleCombinationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortStatusRoleCombinationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortStatusRoleCombinationProto parseFrom(InputStream input) throws IOException {
        return (UsbPortStatusRoleCombinationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortStatusRoleCombinationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortStatusRoleCombinationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortStatusRoleCombinationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbPortStatusRoleCombinationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortStatusRoleCombinationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortStatusRoleCombinationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortStatusRoleCombinationProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbPortStatusRoleCombinationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortStatusRoleCombinationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortStatusRoleCombinationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbPortStatusRoleCombinationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbPortStatusRoleCombinationProto, Builder> implements UsbPortStatusRoleCombinationProtoOrBuilder {
        private Builder() {
            super(UsbPortStatusRoleCombinationProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbPortStatusRoleCombinationProtoOrBuilder
        public boolean hasPowerRole() {
            return ((UsbPortStatusRoleCombinationProto) this.instance).hasPowerRole();
        }

        @Override // android.service.usb.UsbPortStatusRoleCombinationProtoOrBuilder
        public UsbPortStatusProto.PowerRole getPowerRole() {
            return ((UsbPortStatusRoleCombinationProto) this.instance).getPowerRole();
        }

        public Builder setPowerRole(UsbPortStatusProto.PowerRole value) {
            copyOnWrite();
            ((UsbPortStatusRoleCombinationProto) this.instance).setPowerRole(value);
            return this;
        }

        public Builder clearPowerRole() {
            copyOnWrite();
            ((UsbPortStatusRoleCombinationProto) this.instance).clearPowerRole();
            return this;
        }

        @Override // android.service.usb.UsbPortStatusRoleCombinationProtoOrBuilder
        public boolean hasDataRole() {
            return ((UsbPortStatusRoleCombinationProto) this.instance).hasDataRole();
        }

        @Override // android.service.usb.UsbPortStatusRoleCombinationProtoOrBuilder
        public UsbPortStatusProto.DataRole getDataRole() {
            return ((UsbPortStatusRoleCombinationProto) this.instance).getDataRole();
        }

        public Builder setDataRole(UsbPortStatusProto.DataRole value) {
            copyOnWrite();
            ((UsbPortStatusRoleCombinationProto) this.instance).setDataRole(value);
            return this;
        }

        public Builder clearDataRole() {
            copyOnWrite();
            ((UsbPortStatusRoleCombinationProto) this.instance).clearDataRole();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbPortStatusRoleCombinationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbPortStatusRoleCombinationProto other = (UsbPortStatusRoleCombinationProto) arg1;
                this.powerRole_ = visitor.visitInt(hasPowerRole(), this.powerRole_, other.hasPowerRole(), other.powerRole_);
                this.dataRole_ = visitor.visitInt(hasDataRole(), this.dataRole_, other.hasDataRole(), other.dataRole_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            if (UsbPortStatusProto.PowerRole.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.powerRole_ = rawValue;
                            }
                        } else if (tag == 16) {
                            int rawValue2 = input.readEnum();
                            if (UsbPortStatusProto.DataRole.forNumber(rawValue2) == null) {
                                super.mergeVarintField(2, rawValue2);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.dataRole_ = rawValue2;
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
                    synchronized (UsbPortStatusRoleCombinationProto.class) {
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

    public static UsbPortStatusRoleCombinationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbPortStatusRoleCombinationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
