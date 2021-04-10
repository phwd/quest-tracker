package android.service.usb;

import android.service.usb.UsbPortProto;
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

public final class UsbPortInfoProto extends GeneratedMessageLite<UsbPortInfoProto, Builder> implements UsbPortInfoProtoOrBuilder {
    public static final int CAN_CHANGE_DATA_ROLE_FIELD_NUMBER = 5;
    public static final int CAN_CHANGE_MODE_FIELD_NUMBER = 3;
    public static final int CAN_CHANGE_POWER_ROLE_FIELD_NUMBER = 4;
    public static final int CONNECTED_AT_MILLIS_FIELD_NUMBER = 6;
    private static final UsbPortInfoProto DEFAULT_INSTANCE = new UsbPortInfoProto();
    public static final int LAST_CONNECT_DURATION_MILLIS_FIELD_NUMBER = 7;
    private static volatile Parser<UsbPortInfoProto> PARSER = null;
    public static final int PORT_FIELD_NUMBER = 1;
    public static final int STATUS_FIELD_NUMBER = 2;
    private int bitField0_;
    private boolean canChangeDataRole_ = false;
    private boolean canChangeMode_ = false;
    private boolean canChangePowerRole_ = false;
    private long connectedAtMillis_ = 0;
    private long lastConnectDurationMillis_ = 0;
    private UsbPortProto port_;
    private UsbPortStatusProto status_;

    private UsbPortInfoProto() {
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean hasPort() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public UsbPortProto getPort() {
        UsbPortProto usbPortProto = this.port_;
        return usbPortProto == null ? UsbPortProto.getDefaultInstance() : usbPortProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPort(UsbPortProto value) {
        if (value != null) {
            this.port_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPort(UsbPortProto.Builder builderForValue) {
        this.port_ = (UsbPortProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePort(UsbPortProto value) {
        UsbPortProto usbPortProto = this.port_;
        if (usbPortProto == null || usbPortProto == UsbPortProto.getDefaultInstance()) {
            this.port_ = value;
        } else {
            this.port_ = (UsbPortProto) ((UsbPortProto.Builder) UsbPortProto.newBuilder(this.port_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPort() {
        this.port_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean hasStatus() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public UsbPortStatusProto getStatus() {
        UsbPortStatusProto usbPortStatusProto = this.status_;
        return usbPortStatusProto == null ? UsbPortStatusProto.getDefaultInstance() : usbPortStatusProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatus(UsbPortStatusProto value) {
        if (value != null) {
            this.status_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatus(UsbPortStatusProto.Builder builderForValue) {
        this.status_ = (UsbPortStatusProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStatus(UsbPortStatusProto value) {
        UsbPortStatusProto usbPortStatusProto = this.status_;
        if (usbPortStatusProto == null || usbPortStatusProto == UsbPortStatusProto.getDefaultInstance()) {
            this.status_ = value;
        } else {
            this.status_ = (UsbPortStatusProto) ((UsbPortStatusProto.Builder) UsbPortStatusProto.newBuilder(this.status_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatus() {
        this.status_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean hasCanChangeMode() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean getCanChangeMode() {
        return this.canChangeMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCanChangeMode(boolean value) {
        this.bitField0_ |= 4;
        this.canChangeMode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCanChangeMode() {
        this.bitField0_ &= -5;
        this.canChangeMode_ = false;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean hasCanChangePowerRole() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean getCanChangePowerRole() {
        return this.canChangePowerRole_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCanChangePowerRole(boolean value) {
        this.bitField0_ |= 8;
        this.canChangePowerRole_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCanChangePowerRole() {
        this.bitField0_ &= -9;
        this.canChangePowerRole_ = false;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean hasCanChangeDataRole() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean getCanChangeDataRole() {
        return this.canChangeDataRole_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCanChangeDataRole(boolean value) {
        this.bitField0_ |= 16;
        this.canChangeDataRole_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCanChangeDataRole() {
        this.bitField0_ &= -17;
        this.canChangeDataRole_ = false;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean hasConnectedAtMillis() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public long getConnectedAtMillis() {
        return this.connectedAtMillis_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConnectedAtMillis(long value) {
        this.bitField0_ |= 32;
        this.connectedAtMillis_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConnectedAtMillis() {
        this.bitField0_ &= -33;
        this.connectedAtMillis_ = 0;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public boolean hasLastConnectDurationMillis() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.usb.UsbPortInfoProtoOrBuilder
    public long getLastConnectDurationMillis() {
        return this.lastConnectDurationMillis_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastConnectDurationMillis(long value) {
        this.bitField0_ |= 64;
        this.lastConnectDurationMillis_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastConnectDurationMillis() {
        this.bitField0_ &= -65;
        this.lastConnectDurationMillis_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getPort());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getStatus());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(3, this.canChangeMode_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.canChangePowerRole_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.canChangeDataRole_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(6, this.connectedAtMillis_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt64(7, this.lastConnectDurationMillis_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getPort());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getStatus());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(3, this.canChangeMode_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.canChangePowerRole_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.canChangeDataRole_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(6, this.connectedAtMillis_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt64Size(7, this.lastConnectDurationMillis_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbPortInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbPortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbPortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbPortInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbPortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbPortInfoProto parseFrom(InputStream input) throws IOException {
        return (UsbPortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbPortInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbPortInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbPortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbPortInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbPortInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbPortInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbPortInfoProto, Builder> implements UsbPortInfoProtoOrBuilder {
        private Builder() {
            super(UsbPortInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean hasPort() {
            return ((UsbPortInfoProto) this.instance).hasPort();
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public UsbPortProto getPort() {
            return ((UsbPortInfoProto) this.instance).getPort();
        }

        public Builder setPort(UsbPortProto value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setPort((UsbPortInfoProto) value);
            return this;
        }

        public Builder setPort(UsbPortProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setPort((UsbPortInfoProto) builderForValue);
            return this;
        }

        public Builder mergePort(UsbPortProto value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).mergePort(value);
            return this;
        }

        public Builder clearPort() {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).clearPort();
            return this;
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean hasStatus() {
            return ((UsbPortInfoProto) this.instance).hasStatus();
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public UsbPortStatusProto getStatus() {
            return ((UsbPortInfoProto) this.instance).getStatus();
        }

        public Builder setStatus(UsbPortStatusProto value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setStatus((UsbPortInfoProto) value);
            return this;
        }

        public Builder setStatus(UsbPortStatusProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setStatus((UsbPortInfoProto) builderForValue);
            return this;
        }

        public Builder mergeStatus(UsbPortStatusProto value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).mergeStatus(value);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).clearStatus();
            return this;
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean hasCanChangeMode() {
            return ((UsbPortInfoProto) this.instance).hasCanChangeMode();
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean getCanChangeMode() {
            return ((UsbPortInfoProto) this.instance).getCanChangeMode();
        }

        public Builder setCanChangeMode(boolean value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setCanChangeMode(value);
            return this;
        }

        public Builder clearCanChangeMode() {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).clearCanChangeMode();
            return this;
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean hasCanChangePowerRole() {
            return ((UsbPortInfoProto) this.instance).hasCanChangePowerRole();
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean getCanChangePowerRole() {
            return ((UsbPortInfoProto) this.instance).getCanChangePowerRole();
        }

        public Builder setCanChangePowerRole(boolean value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setCanChangePowerRole(value);
            return this;
        }

        public Builder clearCanChangePowerRole() {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).clearCanChangePowerRole();
            return this;
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean hasCanChangeDataRole() {
            return ((UsbPortInfoProto) this.instance).hasCanChangeDataRole();
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean getCanChangeDataRole() {
            return ((UsbPortInfoProto) this.instance).getCanChangeDataRole();
        }

        public Builder setCanChangeDataRole(boolean value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setCanChangeDataRole(value);
            return this;
        }

        public Builder clearCanChangeDataRole() {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).clearCanChangeDataRole();
            return this;
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean hasConnectedAtMillis() {
            return ((UsbPortInfoProto) this.instance).hasConnectedAtMillis();
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public long getConnectedAtMillis() {
            return ((UsbPortInfoProto) this.instance).getConnectedAtMillis();
        }

        public Builder setConnectedAtMillis(long value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setConnectedAtMillis(value);
            return this;
        }

        public Builder clearConnectedAtMillis() {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).clearConnectedAtMillis();
            return this;
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public boolean hasLastConnectDurationMillis() {
            return ((UsbPortInfoProto) this.instance).hasLastConnectDurationMillis();
        }

        @Override // android.service.usb.UsbPortInfoProtoOrBuilder
        public long getLastConnectDurationMillis() {
            return ((UsbPortInfoProto) this.instance).getLastConnectDurationMillis();
        }

        public Builder setLastConnectDurationMillis(long value) {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).setLastConnectDurationMillis(value);
            return this;
        }

        public Builder clearLastConnectDurationMillis() {
            copyOnWrite();
            ((UsbPortInfoProto) this.instance).clearLastConnectDurationMillis();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbPortInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbPortInfoProto other = (UsbPortInfoProto) arg1;
                this.port_ = (UsbPortProto) visitor.visitMessage(this.port_, other.port_);
                this.status_ = (UsbPortStatusProto) visitor.visitMessage(this.status_, other.status_);
                this.canChangeMode_ = visitor.visitBoolean(hasCanChangeMode(), this.canChangeMode_, other.hasCanChangeMode(), other.canChangeMode_);
                this.canChangePowerRole_ = visitor.visitBoolean(hasCanChangePowerRole(), this.canChangePowerRole_, other.hasCanChangePowerRole(), other.canChangePowerRole_);
                this.canChangeDataRole_ = visitor.visitBoolean(hasCanChangeDataRole(), this.canChangeDataRole_, other.hasCanChangeDataRole(), other.canChangeDataRole_);
                this.connectedAtMillis_ = visitor.visitLong(hasConnectedAtMillis(), this.connectedAtMillis_, other.hasConnectedAtMillis(), other.connectedAtMillis_);
                this.lastConnectDurationMillis_ = visitor.visitLong(hasLastConnectDurationMillis(), this.lastConnectDurationMillis_, other.hasLastConnectDurationMillis(), other.lastConnectDurationMillis_);
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
                        } else if (tag == 10) {
                            UsbPortProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (UsbPortProto.Builder) this.port_.toBuilder();
                            }
                            this.port_ = (UsbPortProto) input.readMessage(UsbPortProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.port_);
                                this.port_ = (UsbPortProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            UsbPortStatusProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (UsbPortStatusProto.Builder) this.status_.toBuilder();
                            }
                            this.status_ = (UsbPortStatusProto) input.readMessage(UsbPortStatusProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.status_);
                                this.status_ = (UsbPortStatusProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.canChangeMode_ = input.readBool();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.canChangePowerRole_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.canChangeDataRole_ = input.readBool();
                        } else if (tag == 48) {
                            this.bitField0_ = 32 | this.bitField0_;
                            this.connectedAtMillis_ = input.readInt64();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.lastConnectDurationMillis_ = input.readInt64();
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
                    synchronized (UsbPortInfoProto.class) {
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

    public static UsbPortInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbPortInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
