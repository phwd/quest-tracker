package android.service.usb;

import android.service.UsbConnectionRecordMode;
import android.service.usb.UsbIsHeadsetProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UsbConnectionRecordProto extends GeneratedMessageLite<UsbConnectionRecordProto, Builder> implements UsbConnectionRecordProtoOrBuilder {
    private static final UsbConnectionRecordProto DEFAULT_INSTANCE = new UsbConnectionRecordProto();
    public static final int DEVICE_ADDRESS_FIELD_NUMBER = 1;
    public static final int IS_HEADSET_FIELD_NUMBER = 6;
    public static final int MANUFACTURER_FIELD_NUMBER = 4;
    public static final int MODE_FIELD_NUMBER = 2;
    private static volatile Parser<UsbConnectionRecordProto> PARSER = null;
    public static final int PRODUCT_FIELD_NUMBER = 5;
    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    private int bitField0_;
    private String deviceAddress_ = "";
    private UsbIsHeadsetProto isHeadset_;
    private int manufacturer_ = 0;
    private int mode_ = 0;
    private int product_ = 0;
    private long timestamp_ = 0;

    private UsbConnectionRecordProto() {
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public boolean hasDeviceAddress() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public String getDeviceAddress() {
        return this.deviceAddress_;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public ByteString getDeviceAddressBytes() {
        return ByteString.copyFromUtf8(this.deviceAddress_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceAddress(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.deviceAddress_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceAddress() {
        this.bitField0_ &= -2;
        this.deviceAddress_ = getDefaultInstance().getDeviceAddress();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceAddressBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.deviceAddress_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public boolean hasMode() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public UsbConnectionRecordMode getMode() {
        UsbConnectionRecordMode result = UsbConnectionRecordMode.forNumber(this.mode_);
        return result == null ? UsbConnectionRecordMode.USB_CONNECTION_RECORD_MODE_CONNECT : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMode(UsbConnectionRecordMode value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.mode_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMode() {
        this.bitField0_ &= -3;
        this.mode_ = 0;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public boolean hasTimestamp() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public long getTimestamp() {
        return this.timestamp_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimestamp(long value) {
        this.bitField0_ |= 4;
        this.timestamp_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimestamp() {
        this.bitField0_ &= -5;
        this.timestamp_ = 0;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public boolean hasManufacturer() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public int getManufacturer() {
        return this.manufacturer_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManufacturer(int value) {
        this.bitField0_ |= 8;
        this.manufacturer_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearManufacturer() {
        this.bitField0_ &= -9;
        this.manufacturer_ = 0;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public boolean hasProduct() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public int getProduct() {
        return this.product_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProduct(int value) {
        this.bitField0_ |= 16;
        this.product_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProduct() {
        this.bitField0_ &= -17;
        this.product_ = 0;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public boolean hasIsHeadset() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
    public UsbIsHeadsetProto getIsHeadset() {
        UsbIsHeadsetProto usbIsHeadsetProto = this.isHeadset_;
        return usbIsHeadsetProto == null ? UsbIsHeadsetProto.getDefaultInstance() : usbIsHeadsetProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHeadset(UsbIsHeadsetProto value) {
        if (value != null) {
            this.isHeadset_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHeadset(UsbIsHeadsetProto.Builder builderForValue) {
        this.isHeadset_ = (UsbIsHeadsetProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeIsHeadset(UsbIsHeadsetProto value) {
        UsbIsHeadsetProto usbIsHeadsetProto = this.isHeadset_;
        if (usbIsHeadsetProto == null || usbIsHeadsetProto == UsbIsHeadsetProto.getDefaultInstance()) {
            this.isHeadset_ = value;
        } else {
            this.isHeadset_ = (UsbIsHeadsetProto) ((UsbIsHeadsetProto.Builder) UsbIsHeadsetProto.newBuilder(this.isHeadset_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsHeadset() {
        this.isHeadset_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getDeviceAddress());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.mode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.timestamp_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.manufacturer_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.product_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getIsHeadset());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getDeviceAddress());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.mode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.timestamp_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.manufacturer_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.product_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getIsHeadset());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbConnectionRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbConnectionRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbConnectionRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbConnectionRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbConnectionRecordProto parseFrom(InputStream input) throws IOException {
        return (UsbConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbConnectionRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbConnectionRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbConnectionRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbConnectionRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbConnectionRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbConnectionRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbConnectionRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbConnectionRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbConnectionRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbConnectionRecordProto, Builder> implements UsbConnectionRecordProtoOrBuilder {
        private Builder() {
            super(UsbConnectionRecordProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public boolean hasDeviceAddress() {
            return ((UsbConnectionRecordProto) this.instance).hasDeviceAddress();
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public String getDeviceAddress() {
            return ((UsbConnectionRecordProto) this.instance).getDeviceAddress();
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public ByteString getDeviceAddressBytes() {
            return ((UsbConnectionRecordProto) this.instance).getDeviceAddressBytes();
        }

        public Builder setDeviceAddress(String value) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).setDeviceAddress(value);
            return this;
        }

        public Builder clearDeviceAddress() {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).clearDeviceAddress();
            return this;
        }

        public Builder setDeviceAddressBytes(ByteString value) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).setDeviceAddressBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public boolean hasMode() {
            return ((UsbConnectionRecordProto) this.instance).hasMode();
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public UsbConnectionRecordMode getMode() {
            return ((UsbConnectionRecordProto) this.instance).getMode();
        }

        public Builder setMode(UsbConnectionRecordMode value) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).setMode(value);
            return this;
        }

        public Builder clearMode() {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).clearMode();
            return this;
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public boolean hasTimestamp() {
            return ((UsbConnectionRecordProto) this.instance).hasTimestamp();
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public long getTimestamp() {
            return ((UsbConnectionRecordProto) this.instance).getTimestamp();
        }

        public Builder setTimestamp(long value) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).setTimestamp(value);
            return this;
        }

        public Builder clearTimestamp() {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).clearTimestamp();
            return this;
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public boolean hasManufacturer() {
            return ((UsbConnectionRecordProto) this.instance).hasManufacturer();
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public int getManufacturer() {
            return ((UsbConnectionRecordProto) this.instance).getManufacturer();
        }

        public Builder setManufacturer(int value) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).setManufacturer(value);
            return this;
        }

        public Builder clearManufacturer() {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).clearManufacturer();
            return this;
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public boolean hasProduct() {
            return ((UsbConnectionRecordProto) this.instance).hasProduct();
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public int getProduct() {
            return ((UsbConnectionRecordProto) this.instance).getProduct();
        }

        public Builder setProduct(int value) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).setProduct(value);
            return this;
        }

        public Builder clearProduct() {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).clearProduct();
            return this;
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public boolean hasIsHeadset() {
            return ((UsbConnectionRecordProto) this.instance).hasIsHeadset();
        }

        @Override // android.service.usb.UsbConnectionRecordProtoOrBuilder
        public UsbIsHeadsetProto getIsHeadset() {
            return ((UsbConnectionRecordProto) this.instance).getIsHeadset();
        }

        public Builder setIsHeadset(UsbIsHeadsetProto value) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).setIsHeadset((UsbConnectionRecordProto) value);
            return this;
        }

        public Builder setIsHeadset(UsbIsHeadsetProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).setIsHeadset((UsbConnectionRecordProto) builderForValue);
            return this;
        }

        public Builder mergeIsHeadset(UsbIsHeadsetProto value) {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).mergeIsHeadset(value);
            return this;
        }

        public Builder clearIsHeadset() {
            copyOnWrite();
            ((UsbConnectionRecordProto) this.instance).clearIsHeadset();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbConnectionRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbConnectionRecordProto other = (UsbConnectionRecordProto) arg1;
                this.deviceAddress_ = visitor.visitString(hasDeviceAddress(), this.deviceAddress_, other.hasDeviceAddress(), other.deviceAddress_);
                this.mode_ = visitor.visitInt(hasMode(), this.mode_, other.hasMode(), other.mode_);
                this.timestamp_ = visitor.visitLong(hasTimestamp(), this.timestamp_, other.hasTimestamp(), other.timestamp_);
                this.manufacturer_ = visitor.visitInt(hasManufacturer(), this.manufacturer_, other.hasManufacturer(), other.manufacturer_);
                this.product_ = visitor.visitInt(hasProduct(), this.product_, other.hasProduct(), other.product_);
                this.isHeadset_ = (UsbIsHeadsetProto) visitor.visitMessage(this.isHeadset_, other.isHeadset_);
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
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.deviceAddress_ = s;
                        } else if (tag == 16) {
                            int rawValue = input.readEnum();
                            if (UsbConnectionRecordMode.forNumber(rawValue) == null) {
                                super.mergeVarintField(2, rawValue);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.mode_ = rawValue;
                            }
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.timestamp_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.manufacturer_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.product_ = input.readInt32();
                        } else if (tag == 50) {
                            UsbIsHeadsetProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 32) == 32) {
                                subBuilder = (UsbIsHeadsetProto.Builder) this.isHeadset_.toBuilder();
                            }
                            this.isHeadset_ = (UsbIsHeadsetProto) input.readMessage(UsbIsHeadsetProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.isHeadset_);
                                this.isHeadset_ = (UsbIsHeadsetProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ = 32 | this.bitField0_;
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
                    synchronized (UsbConnectionRecordProto.class) {
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

    public static UsbConnectionRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbConnectionRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
