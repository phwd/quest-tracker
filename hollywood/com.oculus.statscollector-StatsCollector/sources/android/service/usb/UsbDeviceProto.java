package android.service.usb;

import android.service.usb.UsbConfigurationProto;
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

public final class UsbDeviceProto extends GeneratedMessageLite<UsbDeviceProto, Builder> implements UsbDeviceProtoOrBuilder {
    public static final int CLASS_FIELD_NUMBER = 4;
    public static final int CONFIGURATIONS_FIELD_NUMBER = 11;
    private static final UsbDeviceProto DEFAULT_INSTANCE = new UsbDeviceProto();
    public static final int MANUFACTURER_NAME_FIELD_NUMBER = 7;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<UsbDeviceProto> PARSER = null;
    public static final int PRODUCT_ID_FIELD_NUMBER = 3;
    public static final int PRODUCT_NAME_FIELD_NUMBER = 8;
    public static final int PROTOCOL_FIELD_NUMBER = 6;
    public static final int SERIAL_NUMBER_FIELD_NUMBER = 10;
    public static final int SUBCLASS_FIELD_NUMBER = 5;
    public static final int VENDOR_ID_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 9;
    private int bitField0_;
    private int class__ = 0;
    private Internal.ProtobufList<UsbConfigurationProto> configurations_ = emptyProtobufList();
    private String manufacturerName_ = "";
    private String name_ = "";
    private int productId_ = 0;
    private String productName_ = "";
    private int protocol_ = 0;
    private String serialNumber_ = "";
    private int subclass_ = 0;
    private int vendorId_ = 0;
    private String version_ = "";

    private UsbDeviceProto() {
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -2;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasVendorId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public int getVendorId() {
        return this.vendorId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVendorId(int value) {
        this.bitField0_ |= 2;
        this.vendorId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVendorId() {
        this.bitField0_ &= -3;
        this.vendorId_ = 0;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasProductId() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public int getProductId() {
        return this.productId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProductId(int value) {
        this.bitField0_ |= 4;
        this.productId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProductId() {
        this.bitField0_ &= -5;
        this.productId_ = 0;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasClass_() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public int getClass_() {
        return this.class__;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClass_(int value) {
        this.bitField0_ |= 8;
        this.class__ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClass_() {
        this.bitField0_ &= -9;
        this.class__ = 0;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasSubclass() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public int getSubclass() {
        return this.subclass_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSubclass(int value) {
        this.bitField0_ |= 16;
        this.subclass_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSubclass() {
        this.bitField0_ &= -17;
        this.subclass_ = 0;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasProtocol() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public int getProtocol() {
        return this.protocol_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProtocol(int value) {
        this.bitField0_ |= 32;
        this.protocol_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProtocol() {
        this.bitField0_ &= -33;
        this.protocol_ = 0;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasManufacturerName() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public String getManufacturerName() {
        return this.manufacturerName_;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public ByteString getManufacturerNameBytes() {
        return ByteString.copyFromUtf8(this.manufacturerName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManufacturerName(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.manufacturerName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearManufacturerName() {
        this.bitField0_ &= -65;
        this.manufacturerName_ = getDefaultInstance().getManufacturerName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManufacturerNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.manufacturerName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasProductName() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public String getProductName() {
        return this.productName_;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public ByteString getProductNameBytes() {
        return ByteString.copyFromUtf8(this.productName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProductName(String value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.productName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProductName() {
        this.bitField0_ &= -129;
        this.productName_ = getDefaultInstance().getProductName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProductNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.productName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasVersion() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public String getVersion() {
        return this.version_;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public ByteString getVersionBytes() {
        return ByteString.copyFromUtf8(this.version_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.version_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersion() {
        this.bitField0_ &= -257;
        this.version_ = getDefaultInstance().getVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.version_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public boolean hasSerialNumber() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public String getSerialNumber() {
        return this.serialNumber_;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public ByteString getSerialNumberBytes() {
        return ByteString.copyFromUtf8(this.serialNumber_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSerialNumber(String value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.serialNumber_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSerialNumber() {
        this.bitField0_ &= -513;
        this.serialNumber_ = getDefaultInstance().getSerialNumber();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSerialNumberBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.serialNumber_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public List<UsbConfigurationProto> getConfigurationsList() {
        return this.configurations_;
    }

    public List<? extends UsbConfigurationProtoOrBuilder> getConfigurationsOrBuilderList() {
        return this.configurations_;
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public int getConfigurationsCount() {
        return this.configurations_.size();
    }

    @Override // android.service.usb.UsbDeviceProtoOrBuilder
    public UsbConfigurationProto getConfigurations(int index) {
        return this.configurations_.get(index);
    }

    public UsbConfigurationProtoOrBuilder getConfigurationsOrBuilder(int index) {
        return this.configurations_.get(index);
    }

    private void ensureConfigurationsIsMutable() {
        if (!this.configurations_.isModifiable()) {
            this.configurations_ = GeneratedMessageLite.mutableCopy(this.configurations_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigurations(int index, UsbConfigurationProto value) {
        if (value != null) {
            ensureConfigurationsIsMutable();
            this.configurations_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigurations(int index, UsbConfigurationProto.Builder builderForValue) {
        ensureConfigurationsIsMutable();
        this.configurations_.set(index, (UsbConfigurationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigurations(UsbConfigurationProto value) {
        if (value != null) {
            ensureConfigurationsIsMutable();
            this.configurations_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigurations(int index, UsbConfigurationProto value) {
        if (value != null) {
            ensureConfigurationsIsMutable();
            this.configurations_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigurations(UsbConfigurationProto.Builder builderForValue) {
        ensureConfigurationsIsMutable();
        this.configurations_.add((UsbConfigurationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigurations(int index, UsbConfigurationProto.Builder builderForValue) {
        ensureConfigurationsIsMutable();
        this.configurations_.add(index, (UsbConfigurationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllConfigurations(Iterable<? extends UsbConfigurationProto> values) {
        ensureConfigurationsIsMutable();
        AbstractMessageLite.addAll(values, this.configurations_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigurations() {
        this.configurations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeConfigurations(int index) {
        ensureConfigurationsIsMutable();
        this.configurations_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.vendorId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.productId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.class__);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.subclass_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.protocol_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(7, getManufacturerName());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeString(8, getProductName());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeString(9, getVersion());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeString(10, getSerialNumber());
        }
        for (int i = 0; i < this.configurations_.size(); i++) {
            output.writeMessage(11, this.configurations_.get(i));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.vendorId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.productId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.class__);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.subclass_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.protocol_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(7, getManufacturerName());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeStringSize(8, getProductName());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeStringSize(9, getVersion());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeStringSize(10, getSerialNumber());
        }
        for (int i = 0; i < this.configurations_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(11, this.configurations_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbDeviceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbDeviceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbDeviceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbDeviceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbDeviceProto parseFrom(InputStream input) throws IOException {
        return (UsbDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbDeviceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbDeviceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbDeviceProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbDeviceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbDeviceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbDeviceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbDeviceProto, Builder> implements UsbDeviceProtoOrBuilder {
        private Builder() {
            super(UsbDeviceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasName() {
            return ((UsbDeviceProto) this.instance).hasName();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public String getName() {
            return ((UsbDeviceProto) this.instance).getName();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public ByteString getNameBytes() {
            return ((UsbDeviceProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasVendorId() {
            return ((UsbDeviceProto) this.instance).hasVendorId();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public int getVendorId() {
            return ((UsbDeviceProto) this.instance).getVendorId();
        }

        public Builder setVendorId(int value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setVendorId(value);
            return this;
        }

        public Builder clearVendorId() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearVendorId();
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasProductId() {
            return ((UsbDeviceProto) this.instance).hasProductId();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public int getProductId() {
            return ((UsbDeviceProto) this.instance).getProductId();
        }

        public Builder setProductId(int value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setProductId(value);
            return this;
        }

        public Builder clearProductId() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearProductId();
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasClass_() {
            return ((UsbDeviceProto) this.instance).hasClass_();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public int getClass_() {
            return ((UsbDeviceProto) this.instance).getClass_();
        }

        public Builder setClass_(int value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setClass_(value);
            return this;
        }

        public Builder clearClass_() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearClass_();
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasSubclass() {
            return ((UsbDeviceProto) this.instance).hasSubclass();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public int getSubclass() {
            return ((UsbDeviceProto) this.instance).getSubclass();
        }

        public Builder setSubclass(int value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setSubclass(value);
            return this;
        }

        public Builder clearSubclass() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearSubclass();
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasProtocol() {
            return ((UsbDeviceProto) this.instance).hasProtocol();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public int getProtocol() {
            return ((UsbDeviceProto) this.instance).getProtocol();
        }

        public Builder setProtocol(int value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setProtocol(value);
            return this;
        }

        public Builder clearProtocol() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearProtocol();
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasManufacturerName() {
            return ((UsbDeviceProto) this.instance).hasManufacturerName();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public String getManufacturerName() {
            return ((UsbDeviceProto) this.instance).getManufacturerName();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public ByteString getManufacturerNameBytes() {
            return ((UsbDeviceProto) this.instance).getManufacturerNameBytes();
        }

        public Builder setManufacturerName(String value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setManufacturerName(value);
            return this;
        }

        public Builder clearManufacturerName() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearManufacturerName();
            return this;
        }

        public Builder setManufacturerNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setManufacturerNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasProductName() {
            return ((UsbDeviceProto) this.instance).hasProductName();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public String getProductName() {
            return ((UsbDeviceProto) this.instance).getProductName();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public ByteString getProductNameBytes() {
            return ((UsbDeviceProto) this.instance).getProductNameBytes();
        }

        public Builder setProductName(String value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setProductName(value);
            return this;
        }

        public Builder clearProductName() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearProductName();
            return this;
        }

        public Builder setProductNameBytes(ByteString value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setProductNameBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasVersion() {
            return ((UsbDeviceProto) this.instance).hasVersion();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public String getVersion() {
            return ((UsbDeviceProto) this.instance).getVersion();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public ByteString getVersionBytes() {
            return ((UsbDeviceProto) this.instance).getVersionBytes();
        }

        public Builder setVersion(String value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setVersion(value);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearVersion();
            return this;
        }

        public Builder setVersionBytes(ByteString value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setVersionBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public boolean hasSerialNumber() {
            return ((UsbDeviceProto) this.instance).hasSerialNumber();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public String getSerialNumber() {
            return ((UsbDeviceProto) this.instance).getSerialNumber();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public ByteString getSerialNumberBytes() {
            return ((UsbDeviceProto) this.instance).getSerialNumberBytes();
        }

        public Builder setSerialNumber(String value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setSerialNumber(value);
            return this;
        }

        public Builder clearSerialNumber() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearSerialNumber();
            return this;
        }

        public Builder setSerialNumberBytes(ByteString value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setSerialNumberBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public List<UsbConfigurationProto> getConfigurationsList() {
            return Collections.unmodifiableList(((UsbDeviceProto) this.instance).getConfigurationsList());
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public int getConfigurationsCount() {
            return ((UsbDeviceProto) this.instance).getConfigurationsCount();
        }

        @Override // android.service.usb.UsbDeviceProtoOrBuilder
        public UsbConfigurationProto getConfigurations(int index) {
            return ((UsbDeviceProto) this.instance).getConfigurations(index);
        }

        public Builder setConfigurations(int index, UsbConfigurationProto value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setConfigurations((UsbDeviceProto) index, (int) value);
            return this;
        }

        public Builder setConfigurations(int index, UsbConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).setConfigurations((UsbDeviceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addConfigurations(UsbConfigurationProto value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).addConfigurations((UsbDeviceProto) value);
            return this;
        }

        public Builder addConfigurations(int index, UsbConfigurationProto value) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).addConfigurations((UsbDeviceProto) index, (int) value);
            return this;
        }

        public Builder addConfigurations(UsbConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).addConfigurations((UsbDeviceProto) builderForValue);
            return this;
        }

        public Builder addConfigurations(int index, UsbConfigurationProto.Builder builderForValue) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).addConfigurations((UsbDeviceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllConfigurations(Iterable<? extends UsbConfigurationProto> values) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).addAllConfigurations(values);
            return this;
        }

        public Builder clearConfigurations() {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).clearConfigurations();
            return this;
        }

        public Builder removeConfigurations(int index) {
            copyOnWrite();
            ((UsbDeviceProto) this.instance).removeConfigurations(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbDeviceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.configurations_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbDeviceProto other = (UsbDeviceProto) arg1;
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.vendorId_ = visitor.visitInt(hasVendorId(), this.vendorId_, other.hasVendorId(), other.vendorId_);
                this.productId_ = visitor.visitInt(hasProductId(), this.productId_, other.hasProductId(), other.productId_);
                this.class__ = visitor.visitInt(hasClass_(), this.class__, other.hasClass_(), other.class__);
                this.subclass_ = visitor.visitInt(hasSubclass(), this.subclass_, other.hasSubclass(), other.subclass_);
                this.protocol_ = visitor.visitInt(hasProtocol(), this.protocol_, other.hasProtocol(), other.protocol_);
                this.manufacturerName_ = visitor.visitString(hasManufacturerName(), this.manufacturerName_, other.hasManufacturerName(), other.manufacturerName_);
                this.productName_ = visitor.visitString(hasProductName(), this.productName_, other.hasProductName(), other.productName_);
                this.version_ = visitor.visitString(hasVersion(), this.version_, other.hasVersion(), other.version_);
                this.serialNumber_ = visitor.visitString(hasSerialNumber(), this.serialNumber_, other.hasSerialNumber(), other.serialNumber_);
                this.configurations_ = visitor.visitList(this.configurations_, other.configurations_);
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
                                this.name_ = s;
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.vendorId_ = input.readInt32();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.productId_ = input.readInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.class__ = input.readInt32();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.subclass_ = input.readInt32();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.protocol_ = input.readInt32();
                                break;
                            case 58:
                                String s2 = input.readString();
                                this.bitField0_ |= 64;
                                this.manufacturerName_ = s2;
                                break;
                            case 66:
                                String s3 = input.readString();
                                this.bitField0_ |= 128;
                                this.productName_ = s3;
                                break;
                            case 74:
                                String s4 = input.readString();
                                this.bitField0_ |= 256;
                                this.version_ = s4;
                                break;
                            case 82:
                                String s5 = input.readString();
                                this.bitField0_ |= 512;
                                this.serialNumber_ = s5;
                                break;
                            case 90:
                                if (!this.configurations_.isModifiable()) {
                                    this.configurations_ = GeneratedMessageLite.mutableCopy(this.configurations_);
                                }
                                this.configurations_.add((UsbConfigurationProto) input.readMessage(UsbConfigurationProto.parser(), extensionRegistry));
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
                    synchronized (UsbDeviceProto.class) {
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

    public static UsbDeviceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbDeviceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
