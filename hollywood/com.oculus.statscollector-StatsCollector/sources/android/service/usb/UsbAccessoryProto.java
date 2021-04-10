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

public final class UsbAccessoryProto extends GeneratedMessageLite<UsbAccessoryProto, Builder> implements UsbAccessoryProtoOrBuilder {
    private static final UsbAccessoryProto DEFAULT_INSTANCE = new UsbAccessoryProto();
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int MANUFACTURER_FIELD_NUMBER = 1;
    public static final int MODEL_FIELD_NUMBER = 2;
    private static volatile Parser<UsbAccessoryProto> PARSER = null;
    public static final int SERIAL_FIELD_NUMBER = 6;
    public static final int URI_FIELD_NUMBER = 5;
    public static final int VERSION_FIELD_NUMBER = 4;
    private int bitField0_;
    private String description_ = "";
    private String manufacturer_ = "";
    private String model_ = "";
    private String serial_ = "";
    private String uri_ = "";
    private String version_ = "";

    private UsbAccessoryProto() {
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public boolean hasManufacturer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public String getManufacturer() {
        return this.manufacturer_;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public ByteString getManufacturerBytes() {
        return ByteString.copyFromUtf8(this.manufacturer_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManufacturer(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.manufacturer_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearManufacturer() {
        this.bitField0_ &= -2;
        this.manufacturer_ = getDefaultInstance().getManufacturer();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setManufacturerBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.manufacturer_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public boolean hasModel() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public String getModel() {
        return this.model_;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public ByteString getModelBytes() {
        return ByteString.copyFromUtf8(this.model_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModel(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.model_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearModel() {
        this.bitField0_ &= -3;
        this.model_ = getDefaultInstance().getModel();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setModelBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.model_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public boolean hasDescription() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
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

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public boolean hasVersion() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public String getVersion() {
        return this.version_;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public ByteString getVersionBytes() {
        return ByteString.copyFromUtf8(this.version_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.version_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersion() {
        this.bitField0_ &= -9;
        this.version_ = getDefaultInstance().getVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.version_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public boolean hasUri() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public String getUri() {
        return this.uri_;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public ByteString getUriBytes() {
        return ByteString.copyFromUtf8(this.uri_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUri(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.uri_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUri() {
        this.bitField0_ &= -17;
        this.uri_ = getDefaultInstance().getUri();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUriBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.uri_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public boolean hasSerial() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public String getSerial() {
        return this.serial_;
    }

    @Override // android.service.usb.UsbAccessoryProtoOrBuilder
    public ByteString getSerialBytes() {
        return ByteString.copyFromUtf8(this.serial_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSerial(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.serial_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSerial() {
        this.bitField0_ &= -33;
        this.serial_ = getDefaultInstance().getSerial();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSerialBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.serial_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getManufacturer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getModel());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getDescription());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getVersion());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getUri());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(6, getSerial());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getManufacturer());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getModel());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getDescription());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getVersion());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getUri());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeStringSize(6, getSerial());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbAccessoryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbAccessoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAccessoryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAccessoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAccessoryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbAccessoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAccessoryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAccessoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAccessoryProto parseFrom(InputStream input) throws IOException {
        return (UsbAccessoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAccessoryProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbAccessoryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAccessoryProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbAccessoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbAccessoryProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbAccessoryProto, Builder> implements UsbAccessoryProtoOrBuilder {
        private Builder() {
            super(UsbAccessoryProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public boolean hasManufacturer() {
            return ((UsbAccessoryProto) this.instance).hasManufacturer();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public String getManufacturer() {
            return ((UsbAccessoryProto) this.instance).getManufacturer();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public ByteString getManufacturerBytes() {
            return ((UsbAccessoryProto) this.instance).getManufacturerBytes();
        }

        public Builder setManufacturer(String value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setManufacturer(value);
            return this;
        }

        public Builder clearManufacturer() {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).clearManufacturer();
            return this;
        }

        public Builder setManufacturerBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setManufacturerBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public boolean hasModel() {
            return ((UsbAccessoryProto) this.instance).hasModel();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public String getModel() {
            return ((UsbAccessoryProto) this.instance).getModel();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public ByteString getModelBytes() {
            return ((UsbAccessoryProto) this.instance).getModelBytes();
        }

        public Builder setModel(String value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setModel(value);
            return this;
        }

        public Builder clearModel() {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).clearModel();
            return this;
        }

        public Builder setModelBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setModelBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public boolean hasDescription() {
            return ((UsbAccessoryProto) this.instance).hasDescription();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public String getDescription() {
            return ((UsbAccessoryProto) this.instance).getDescription();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public ByteString getDescriptionBytes() {
            return ((UsbAccessoryProto) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setDescription(value);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).clearDescription();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setDescriptionBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public boolean hasVersion() {
            return ((UsbAccessoryProto) this.instance).hasVersion();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public String getVersion() {
            return ((UsbAccessoryProto) this.instance).getVersion();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public ByteString getVersionBytes() {
            return ((UsbAccessoryProto) this.instance).getVersionBytes();
        }

        public Builder setVersion(String value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setVersion(value);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).clearVersion();
            return this;
        }

        public Builder setVersionBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setVersionBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public boolean hasUri() {
            return ((UsbAccessoryProto) this.instance).hasUri();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public String getUri() {
            return ((UsbAccessoryProto) this.instance).getUri();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public ByteString getUriBytes() {
            return ((UsbAccessoryProto) this.instance).getUriBytes();
        }

        public Builder setUri(String value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setUri(value);
            return this;
        }

        public Builder clearUri() {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).clearUri();
            return this;
        }

        public Builder setUriBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setUriBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public boolean hasSerial() {
            return ((UsbAccessoryProto) this.instance).hasSerial();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public String getSerial() {
            return ((UsbAccessoryProto) this.instance).getSerial();
        }

        @Override // android.service.usb.UsbAccessoryProtoOrBuilder
        public ByteString getSerialBytes() {
            return ((UsbAccessoryProto) this.instance).getSerialBytes();
        }

        public Builder setSerial(String value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setSerial(value);
            return this;
        }

        public Builder clearSerial() {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).clearSerial();
            return this;
        }

        public Builder setSerialBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryProto) this.instance).setSerialBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbAccessoryProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbAccessoryProto other = (UsbAccessoryProto) arg1;
                this.manufacturer_ = visitor.visitString(hasManufacturer(), this.manufacturer_, other.hasManufacturer(), other.manufacturer_);
                this.model_ = visitor.visitString(hasModel(), this.model_, other.hasModel(), other.model_);
                this.description_ = visitor.visitString(hasDescription(), this.description_, other.hasDescription(), other.description_);
                this.version_ = visitor.visitString(hasVersion(), this.version_, other.hasVersion(), other.version_);
                this.uri_ = visitor.visitString(hasUri(), this.uri_, other.hasUri(), other.uri_);
                this.serial_ = visitor.visitString(hasSerial(), this.serial_, other.hasSerial(), other.serial_);
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
                        } else if (tag == 10) {
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.manufacturer_ = s;
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.model_ = s2;
                        } else if (tag == 26) {
                            String s3 = input.readString();
                            this.bitField0_ |= 4;
                            this.description_ = s3;
                        } else if (tag == 34) {
                            String s4 = input.readString();
                            this.bitField0_ |= 8;
                            this.version_ = s4;
                        } else if (tag == 42) {
                            String s5 = input.readString();
                            this.bitField0_ |= 16;
                            this.uri_ = s5;
                        } else if (tag == 50) {
                            String s6 = input.readString();
                            this.bitField0_ |= 32;
                            this.serial_ = s6;
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
                    synchronized (UsbAccessoryProto.class) {
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

    public static UsbAccessoryProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbAccessoryProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
