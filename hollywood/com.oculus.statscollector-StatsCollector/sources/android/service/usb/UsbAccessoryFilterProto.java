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

public final class UsbAccessoryFilterProto extends GeneratedMessageLite<UsbAccessoryFilterProto, Builder> implements UsbAccessoryFilterProtoOrBuilder {
    private static final UsbAccessoryFilterProto DEFAULT_INSTANCE = new UsbAccessoryFilterProto();
    public static final int MANUFACTURER_FIELD_NUMBER = 1;
    public static final int MODEL_FIELD_NUMBER = 2;
    private static volatile Parser<UsbAccessoryFilterProto> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 3;
    private int bitField0_;
    private String manufacturer_ = "";
    private String model_ = "";
    private String version_ = "";

    private UsbAccessoryFilterProto() {
    }

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
    public boolean hasManufacturer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
    public String getManufacturer() {
        return this.manufacturer_;
    }

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
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

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
    public boolean hasModel() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
    public String getModel() {
        return this.model_;
    }

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
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

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
    public boolean hasVersion() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
    public String getVersion() {
        return this.version_;
    }

    @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
    public ByteString getVersionBytes() {
        return ByteString.copyFromUtf8(this.version_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.version_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersion() {
        this.bitField0_ &= -5;
        this.version_ = getDefaultInstance().getVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.version_ = value.toStringUtf8();
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
            output.writeString(3, getVersion());
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
            size2 += CodedOutputStream.computeStringSize(3, getVersion());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static UsbAccessoryFilterProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UsbAccessoryFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAccessoryFilterProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAccessoryFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAccessoryFilterProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UsbAccessoryFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UsbAccessoryFilterProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UsbAccessoryFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UsbAccessoryFilterProto parseFrom(InputStream input) throws IOException {
        return (UsbAccessoryFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryFilterProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAccessoryFilterProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UsbAccessoryFilterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryFilterProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryFilterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UsbAccessoryFilterProto parseFrom(CodedInputStream input) throws IOException {
        return (UsbAccessoryFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UsbAccessoryFilterProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UsbAccessoryFilterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UsbAccessoryFilterProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsbAccessoryFilterProto, Builder> implements UsbAccessoryFilterProtoOrBuilder {
        private Builder() {
            super(UsbAccessoryFilterProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public boolean hasManufacturer() {
            return ((UsbAccessoryFilterProto) this.instance).hasManufacturer();
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public String getManufacturer() {
            return ((UsbAccessoryFilterProto) this.instance).getManufacturer();
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public ByteString getManufacturerBytes() {
            return ((UsbAccessoryFilterProto) this.instance).getManufacturerBytes();
        }

        public Builder setManufacturer(String value) {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).setManufacturer(value);
            return this;
        }

        public Builder clearManufacturer() {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).clearManufacturer();
            return this;
        }

        public Builder setManufacturerBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).setManufacturerBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public boolean hasModel() {
            return ((UsbAccessoryFilterProto) this.instance).hasModel();
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public String getModel() {
            return ((UsbAccessoryFilterProto) this.instance).getModel();
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public ByteString getModelBytes() {
            return ((UsbAccessoryFilterProto) this.instance).getModelBytes();
        }

        public Builder setModel(String value) {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).setModel(value);
            return this;
        }

        public Builder clearModel() {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).clearModel();
            return this;
        }

        public Builder setModelBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).setModelBytes(value);
            return this;
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public boolean hasVersion() {
            return ((UsbAccessoryFilterProto) this.instance).hasVersion();
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public String getVersion() {
            return ((UsbAccessoryFilterProto) this.instance).getVersion();
        }

        @Override // android.service.usb.UsbAccessoryFilterProtoOrBuilder
        public ByteString getVersionBytes() {
            return ((UsbAccessoryFilterProto) this.instance).getVersionBytes();
        }

        public Builder setVersion(String value) {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).setVersion(value);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).clearVersion();
            return this;
        }

        public Builder setVersionBytes(ByteString value) {
            copyOnWrite();
            ((UsbAccessoryFilterProto) this.instance).setVersionBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UsbAccessoryFilterProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UsbAccessoryFilterProto other = (UsbAccessoryFilterProto) arg1;
                this.manufacturer_ = visitor.visitString(hasManufacturer(), this.manufacturer_, other.hasManufacturer(), other.manufacturer_);
                this.model_ = visitor.visitString(hasModel(), this.model_, other.hasModel(), other.model_);
                this.version_ = visitor.visitString(hasVersion(), this.version_, other.hasVersion(), other.version_);
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
                            this.version_ = s3;
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
                    synchronized (UsbAccessoryFilterProto.class) {
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

    public static UsbAccessoryFilterProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UsbAccessoryFilterProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
