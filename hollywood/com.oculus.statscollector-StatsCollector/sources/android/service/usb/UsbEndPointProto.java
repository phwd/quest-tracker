package android.service.usb;

import android.service.UsbEndPointDirection;
import android.service.UsbEndPointType;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UsbEndPointProto extends GeneratedMessageLite<UsbEndPointProto, Builder> implements UsbEndPointProtoOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 3;
    public static final int ATTRIBUTES_FIELD_NUMBER = 5;
    private static final UsbEndPointProto DEFAULT_INSTANCE = new UsbEndPointProto();
    public static final int DIRECTION_FIELD_NUMBER = 2;
    public static final int ENDPOINT_NUMBER_FIELD_NUMBER = 1;
    public static final int INTERVAL_FIELD_NUMBER = 7;
    public static final int MAX_PACKET_SIZE_FIELD_NUMBER = 6;
    private static volatile Parser<UsbEndPointProto> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 4;
    private int address_ = 0;
    private int attributes_ = 0;
    private int bitField0_;
    private int direction_ = 0;
    private int endpointNumber_ = 0;
    private int interval_ = 0;
    private int maxPacketSize_ = 0;
    private int type_ = 0;

    private UsbEndPointProto() {
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public boolean hasEndpointNumber() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public int getEndpointNumber() {
        return this.endpointNumber_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndpointNumber(int value) {
        this.bitField0_ |= 1;
        this.endpointNumber_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndpointNumber() {
        this.bitField0_ &= -2;
        this.endpointNumber_ = 0;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public boolean hasDirection() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public UsbEndPointDirection getDirection() {
        UsbEndPointDirection result = UsbEndPointDirection.forNumber(this.direction_);
        return result == null ? UsbEndPointDirection.USB_ENDPOINT_DIR_OUT : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDirection(UsbEndPointDirection value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.direction_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDirection() {
        this.bitField0_ &= -3;
        this.direction_ = 0;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public boolean hasAddress() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public int getAddress() {
        return this.address_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAddress(int value) {
        this.bitField0_ |= 4;
        this.address_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAddress() {
        this.bitField0_ &= -5;
        this.address_ = 0;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public boolean hasType() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public UsbEndPointType getType() {
        UsbEndPointType result = UsbEndPointType.forNumber(this.type_);
        return result == null ? UsbEndPointType.USB_ENDPOINT_TYPE_XFER_CONTROL : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(UsbEndPointType value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.type_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearType() {
        this.bitField0_ &= -9;
        this.type_ = 0;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public boolean hasAttributes() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public int getAttributes() {
        return this.attributes_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAttributes(int value) {
        this.bitField0_ |= 16;
        this.attributes_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAttributes() {
        this.bitField0_ &= -17;
        this.attributes_ = 0;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public boolean hasMaxPacketSize() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public int getMaxPacketSize() {
        return this.maxPacketSize_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxPacketSize(int value) {
        this.bitField0_ |= 32;
        this.maxPacketSize_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxPacketSize() {
        this.bitField0_ &= -33;
        this.maxPacketSize_ = 0;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public boolean hasInterval() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.usb.UsbEndPointProtoOrBuilder
    public int getInterval() {
        return this.interval_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInterval(int value) {
        this.bitField0_ |= 64;
        this.interval_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInterval() {
        this.bitField0_ &= -65;
        this.interval_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.endpointNumber_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.direction_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.address_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(4, this.type_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeUInt32(5, this.attributes_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.maxPacketSize_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.interval_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.endpointNumber_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.direction_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.address_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(4, this.type_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeUInt32Size(5, this.attributes_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.maxPacketSize_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.interval_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbEndPointProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbEndPointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbEndPointProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbEndPointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbEndPointProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbEndPointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbEndPointProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbEndPointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbEndPointProto parseFrom(InputStream input) throws IOException {
        return (UsbEndPointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbEndPointProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbEndPointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbEndPointProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbEndPointProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbEndPointProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbEndPointProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbEndPointProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbEndPointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbEndPointProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbEndPointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbEndPointProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbEndPointProto, Builder> implements UsbEndPointProtoOrBuilder {
        private Builder() {
            super(UsbEndPointProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public boolean hasEndpointNumber() {
            return ((UsbEndPointProto) this.instance).hasEndpointNumber();
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public int getEndpointNumber() {
            return ((UsbEndPointProto) this.instance).getEndpointNumber();
        }

        public Builder setEndpointNumber(int value) {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).setEndpointNumber(value);
            return this;
        }

        public Builder clearEndpointNumber() {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).clearEndpointNumber();
            return this;
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public boolean hasDirection() {
            return ((UsbEndPointProto) this.instance).hasDirection();
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public UsbEndPointDirection getDirection() {
            return ((UsbEndPointProto) this.instance).getDirection();
        }

        public Builder setDirection(UsbEndPointDirection value) {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).setDirection(value);
            return this;
        }

        public Builder clearDirection() {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).clearDirection();
            return this;
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public boolean hasAddress() {
            return ((UsbEndPointProto) this.instance).hasAddress();
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public int getAddress() {
            return ((UsbEndPointProto) this.instance).getAddress();
        }

        public Builder setAddress(int value) {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).setAddress(value);
            return this;
        }

        public Builder clearAddress() {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).clearAddress();
            return this;
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public boolean hasType() {
            return ((UsbEndPointProto) this.instance).hasType();
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public UsbEndPointType getType() {
            return ((UsbEndPointProto) this.instance).getType();
        }

        public Builder setType(UsbEndPointType value) {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).clearType();
            return this;
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public boolean hasAttributes() {
            return ((UsbEndPointProto) this.instance).hasAttributes();
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public int getAttributes() {
            return ((UsbEndPointProto) this.instance).getAttributes();
        }

        public Builder setAttributes(int value) {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).setAttributes(value);
            return this;
        }

        public Builder clearAttributes() {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).clearAttributes();
            return this;
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public boolean hasMaxPacketSize() {
            return ((UsbEndPointProto) this.instance).hasMaxPacketSize();
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public int getMaxPacketSize() {
            return ((UsbEndPointProto) this.instance).getMaxPacketSize();
        }

        public Builder setMaxPacketSize(int value) {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).setMaxPacketSize(value);
            return this;
        }

        public Builder clearMaxPacketSize() {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).clearMaxPacketSize();
            return this;
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public boolean hasInterval() {
            return ((UsbEndPointProto) this.instance).hasInterval();
        }

        @Override // android.service.usb.UsbEndPointProtoOrBuilder
        public int getInterval() {
            return ((UsbEndPointProto) this.instance).getInterval();
        }

        public Builder setInterval(int value) {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).setInterval(value);
            return this;
        }

        public Builder clearInterval() {
            copyOnWrite();
            ((UsbEndPointProto) this.instance).clearInterval();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbEndPointProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbEndPointProto other = (UsbEndPointProto) arg1;
                this.endpointNumber_ = visitor.visitInt(hasEndpointNumber(), this.endpointNumber_, other.hasEndpointNumber(), other.endpointNumber_);
                this.direction_ = visitor.visitInt(hasDirection(), this.direction_, other.hasDirection(), other.direction_);
                this.address_ = visitor.visitInt(hasAddress(), this.address_, other.hasAddress(), other.address_);
                this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                this.attributes_ = visitor.visitInt(hasAttributes(), this.attributes_, other.hasAttributes(), other.attributes_);
                this.maxPacketSize_ = visitor.visitInt(hasMaxPacketSize(), this.maxPacketSize_, other.hasMaxPacketSize(), other.maxPacketSize_);
                this.interval_ = visitor.visitInt(hasInterval(), this.interval_, other.hasInterval(), other.interval_);
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
                            this.bitField0_ |= 1;
                            this.endpointNumber_ = input.readInt32();
                        } else if (tag == 16) {
                            int rawValue = input.readEnum();
                            if (UsbEndPointDirection.forNumber(rawValue) == null) {
                                super.mergeVarintField(2, rawValue);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.direction_ = rawValue;
                            }
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.address_ = input.readInt32();
                        } else if (tag == 32) {
                            int rawValue2 = input.readEnum();
                            if (UsbEndPointType.forNumber(rawValue2) == null) {
                                super.mergeVarintField(4, rawValue2);
                            } else {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.type_ = rawValue2;
                            }
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.attributes_ = input.readUInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.maxPacketSize_ = input.readInt32();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.interval_ = input.readInt32();
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
                    synchronized (UsbEndPointProto.class) {
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

    public static UsbEndPointProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbEndPointProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
