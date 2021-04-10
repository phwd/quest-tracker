package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class UsbDeviceFilterProto extends GeneratedMessageLite<UsbDeviceFilterProto, Builder> implements UsbDeviceFilterProtoOrBuilder {
    public static final int CLASS_FIELD_NUMBER = 3;
    private static final UsbDeviceFilterProto DEFAULT_INSTANCE = new UsbDeviceFilterProto();
    public static final int MANUFACTURER_NAME_FIELD_NUMBER = 6;
    private static volatile Parser<UsbDeviceFilterProto> PARSER = null;
    public static final int PRODUCT_ID_FIELD_NUMBER = 2;
    public static final int PRODUCT_NAME_FIELD_NUMBER = 7;
    public static final int PROTOCOL_FIELD_NUMBER = 5;
    public static final int SERIAL_NUMBER_FIELD_NUMBER = 8;
    public static final int SUBCLASS_FIELD_NUMBER = 4;
    public static final int VENDOR_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private int class__ = 0;
    private String manufacturerName_ = "";
    private int productId_ = 0;
    private String productName_ = "";
    private int protocol_ = 0;
    private String serialNumber_ = "";
    private int subclass_ = 0;
    private int vendorId_ = 0;

    private UsbDeviceFilterProto() {
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public boolean hasVendorId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public int getVendorId() {
        return this.vendorId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVendorId(int value) {
        this.bitField0_ |= 1;
        this.vendorId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVendorId() {
        this.bitField0_ &= -2;
        this.vendorId_ = 0;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public boolean hasProductId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public int getProductId() {
        return this.productId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProductId(int value) {
        this.bitField0_ |= 2;
        this.productId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProductId() {
        this.bitField0_ &= -3;
        this.productId_ = 0;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public boolean hasClass_() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public int getClass_() {
        return this.class__;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClass_(int value) {
        this.bitField0_ |= 4;
        this.class__ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClass_() {
        this.bitField0_ &= -5;
        this.class__ = 0;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public boolean hasSubclass() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public int getSubclass() {
        return this.subclass_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSubclass(int value) {
        this.bitField0_ |= 8;
        this.subclass_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSubclass() {
        this.bitField0_ &= -9;
        this.subclass_ = 0;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public boolean hasProtocol() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public int getProtocol() {
        return this.protocol_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProtocol(int value) {
        this.bitField0_ |= 16;
        this.protocol_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProtocol() {
        this.bitField0_ &= -17;
        this.protocol_ = 0;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public boolean hasManufacturerName() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public String getManufacturerName() {
        return this.manufacturerName_;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public ByteString getManufacturerNameBytes() {
        return ByteString.copyFromUtf8(this.manufacturerName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManufacturerName(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.manufacturerName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearManufacturerName() {
        this.bitField0_ &= -33;
        this.manufacturerName_ = getDefaultInstance().getManufacturerName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManufacturerNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.manufacturerName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public boolean hasProductName() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public String getProductName() {
        return this.productName_;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public ByteString getProductNameBytes() {
        return ByteString.copyFromUtf8(this.productName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProductName(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.productName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProductName() {
        this.bitField0_ &= -65;
        this.productName_ = getDefaultInstance().getProductName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProductNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.productName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public boolean hasSerialNumber() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public String getSerialNumber() {
        return this.serialNumber_;
    }

    @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
    public ByteString getSerialNumberBytes() {
        return ByteString.copyFromUtf8(this.serialNumber_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSerialNumber(String value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.serialNumber_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSerialNumber() {
        this.bitField0_ &= -129;
        this.serialNumber_ = getDefaultInstance().getSerialNumber();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSerialNumberBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.serialNumber_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.vendorId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.productId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.class__);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.subclass_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.protocol_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(6, getManufacturerName());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(7, getProductName());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeString(8, getSerialNumber());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.vendorId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.productId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.class__);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.subclass_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.protocol_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeStringSize(6, getManufacturerName());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(7, getProductName());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeStringSize(8, getSerialNumber());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbDeviceFilterProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbDeviceFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbDeviceFilterProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbDeviceFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbDeviceFilterProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbDeviceFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbDeviceFilterProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbDeviceFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbDeviceFilterProto parseFrom(InputStream input) throws IOException {
        return (UsbDeviceFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceFilterProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbDeviceFilterProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbDeviceFilterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceFilterProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceFilterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbDeviceFilterProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbDeviceFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceFilterProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbDeviceFilterProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbDeviceFilterProto, Builder> implements UsbDeviceFilterProtoOrBuilder {
        private Builder() {
            super(UsbDeviceFilterProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public boolean hasVendorId() {
            return ((UsbDeviceFilterProto) this.instance).hasVendorId();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public int getVendorId() {
            return ((UsbDeviceFilterProto) this.instance).getVendorId();
        }

        public Builder setVendorId(int value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setVendorId(value);
            return this;
        }

        public Builder clearVendorId() {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).clearVendorId();
            return this;
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public boolean hasProductId() {
            return ((UsbDeviceFilterProto) this.instance).hasProductId();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public int getProductId() {
            return ((UsbDeviceFilterProto) this.instance).getProductId();
        }

        public Builder setProductId(int value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setProductId(value);
            return this;
        }

        public Builder clearProductId() {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).clearProductId();
            return this;
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public boolean hasClass_() {
            return ((UsbDeviceFilterProto) this.instance).hasClass_();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public int getClass_() {
            return ((UsbDeviceFilterProto) this.instance).getClass_();
        }

        public Builder setClass_(int value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setClass_(value);
            return this;
        }

        public Builder clearClass_() {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).clearClass_();
            return this;
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public boolean hasSubclass() {
            return ((UsbDeviceFilterProto) this.instance).hasSubclass();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public int getSubclass() {
            return ((UsbDeviceFilterProto) this.instance).getSubclass();
        }

        public Builder setSubclass(int value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setSubclass(value);
            return this;
        }

        public Builder clearSubclass() {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).clearSubclass();
            return this;
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public boolean hasProtocol() {
            return ((UsbDeviceFilterProto) this.instance).hasProtocol();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public int getProtocol() {
            return ((UsbDeviceFilterProto) this.instance).getProtocol();
        }

        public Builder setProtocol(int value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setProtocol(value);
            return this;
        }

        public Builder clearProtocol() {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).clearProtocol();
            return this;
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public boolean hasManufacturerName() {
            return ((UsbDeviceFilterProto) this.instance).hasManufacturerName();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public String getManufacturerName() {
            return ((UsbDeviceFilterProto) this.instance).getManufacturerName();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public ByteString getManufacturerNameBytes() {
            return ((UsbDeviceFilterProto) this.instance).getManufacturerNameBytes();
        }

        public Builder setManufacturerName(String value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setManufacturerName(value);
            return this;
        }

        public Builder clearManufacturerName() {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).clearManufacturerName();
            return this;
        }

        public Builder setManufacturerNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setManufacturerNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public boolean hasProductName() {
            return ((UsbDeviceFilterProto) this.instance).hasProductName();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public String getProductName() {
            return ((UsbDeviceFilterProto) this.instance).getProductName();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public ByteString getProductNameBytes() {
            return ((UsbDeviceFilterProto) this.instance).getProductNameBytes();
        }

        public Builder setProductName(String value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setProductName(value);
            return this;
        }

        public Builder clearProductName() {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).clearProductName();
            return this;
        }

        public Builder setProductNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setProductNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public boolean hasSerialNumber() {
            return ((UsbDeviceFilterProto) this.instance).hasSerialNumber();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public String getSerialNumber() {
            return ((UsbDeviceFilterProto) this.instance).getSerialNumber();
        }

        @Override // android.service.usb.UsbDeviceFilterProtoOrBuilder
        public ByteString getSerialNumberBytes() {
            return ((UsbDeviceFilterProto) this.instance).getSerialNumberBytes();
        }

        public Builder setSerialNumber(String value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setSerialNumber(value);
            return this;
        }

        public Builder clearSerialNumber() {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).clearSerialNumber();
            return this;
        }

        public Builder setSerialNumberBytes(ByteString value) {
            copyOnWrite();
            ((UsbDeviceFilterProto) this.instance).setSerialNumberBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbDeviceFilterProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbDeviceFilterProto other = (UsbDeviceFilterProto) arg1;
                this.vendorId_ = visitor.visitInt(hasVendorId(), this.vendorId_, other.hasVendorId(), other.vendorId_);
                this.productId_ = visitor.visitInt(hasProductId(), this.productId_, other.hasProductId(), other.productId_);
                this.class__ = visitor.visitInt(hasClass_(), this.class__, other.hasClass_(), other.class__);
                this.subclass_ = visitor.visitInt(hasSubclass(), this.subclass_, other.hasSubclass(), other.subclass_);
                this.protocol_ = visitor.visitInt(hasProtocol(), this.protocol_, other.hasProtocol(), other.protocol_);
                this.manufacturerName_ = visitor.visitString(hasManufacturerName(), this.manufacturerName_, other.hasManufacturerName(), other.manufacturerName_);
                this.productName_ = visitor.visitString(hasProductName(), this.productName_, other.hasProductName(), other.productName_);
                this.serialNumber_ = visitor.visitString(hasSerialNumber(), this.serialNumber_, other.hasSerialNumber(), other.serialNumber_);
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
                            this.vendorId_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.productId_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.class__ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.subclass_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.protocol_ = input.readInt32();
                        } else if (tag == 50) {
                            String s = input.readString();
                            this.bitField0_ |= 32;
                            this.manufacturerName_ = s;
                        } else if (tag == 58) {
                            String s2 = input.readString();
                            this.bitField0_ |= 64;
                            this.productName_ = s2;
                        } else if (tag == 66) {
                            String s3 = input.readString();
                            this.bitField0_ |= 128;
                            this.serialNumber_ = s3;
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
                    synchronized (UsbDeviceFilterProto.class) {
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

    public static UsbDeviceFilterProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbDeviceFilterProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
