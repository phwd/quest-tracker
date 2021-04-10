package android.service.restricted_image;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class RestrictedImageProto extends GeneratedMessageLite<RestrictedImageProto, Builder> implements RestrictedImageProtoOrBuilder {
    private static final RestrictedImageProto DEFAULT_INSTANCE = new RestrictedImageProto();
    public static final int IMAGE_DATA_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 3;
    public static final int MIME_TYPE_FIELD_NUMBER = 1;
    private static volatile Parser<RestrictedImageProto> PARSER;
    private int bitField0_;
    private ByteString imageData_ = ByteString.EMPTY;
    private ByteString metadata_ = ByteString.EMPTY;
    private String mimeType_ = "";

    private RestrictedImageProto() {
    }

    @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
    public boolean hasMimeType() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
    public String getMimeType() {
        return this.mimeType_;
    }

    @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
    public ByteString getMimeTypeBytes() {
        return ByteString.copyFromUtf8(this.mimeType_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMimeType(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.mimeType_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMimeType() {
        this.bitField0_ &= -2;
        this.mimeType_ = getDefaultInstance().getMimeType();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMimeTypeBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.mimeType_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
    public boolean hasImageData() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
    public ByteString getImageData() {
        return this.imageData_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setImageData(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.imageData_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearImageData() {
        this.bitField0_ &= -3;
        this.imageData_ = getDefaultInstance().getImageData();
    }

    @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
    public boolean hasMetadata() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
    public ByteString getMetadata() {
        return this.metadata_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMetadata(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.metadata_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMetadata() {
        this.bitField0_ &= -5;
        this.metadata_ = getDefaultInstance().getMetadata();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getMimeType());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBytes(2, this.imageData_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBytes(3, this.metadata_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getMimeType());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBytesSize(2, this.imageData_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBytesSize(3, this.metadata_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RestrictedImageProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageProto parseFrom(InputStream input) throws IOException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImageProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RestrictedImageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImageProto parseFrom(CodedInputStream input) throws IOException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RestrictedImageProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictedImageProto, Builder> implements RestrictedImageProtoOrBuilder {
        private Builder() {
            super(RestrictedImageProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
        public boolean hasMimeType() {
            return ((RestrictedImageProto) this.instance).hasMimeType();
        }

        @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
        public String getMimeType() {
            return ((RestrictedImageProto) this.instance).getMimeType();
        }

        @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
        public ByteString getMimeTypeBytes() {
            return ((RestrictedImageProto) this.instance).getMimeTypeBytes();
        }

        public Builder setMimeType(String value) {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).setMimeType(value);
            return this;
        }

        public Builder clearMimeType() {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).clearMimeType();
            return this;
        }

        public Builder setMimeTypeBytes(ByteString value) {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).setMimeTypeBytes(value);
            return this;
        }

        @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
        public boolean hasImageData() {
            return ((RestrictedImageProto) this.instance).hasImageData();
        }

        @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
        public ByteString getImageData() {
            return ((RestrictedImageProto) this.instance).getImageData();
        }

        public Builder setImageData(ByteString value) {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).setImageData(value);
            return this;
        }

        public Builder clearImageData() {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).clearImageData();
            return this;
        }

        @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
        public boolean hasMetadata() {
            return ((RestrictedImageProto) this.instance).hasMetadata();
        }

        @Override // android.service.restricted_image.RestrictedImageProtoOrBuilder
        public ByteString getMetadata() {
            return ((RestrictedImageProto) this.instance).getMetadata();
        }

        public Builder setMetadata(ByteString value) {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).setMetadata(value);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).clearMetadata();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RestrictedImageProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RestrictedImageProto other = (RestrictedImageProto) arg1;
                this.mimeType_ = visitor.visitString(hasMimeType(), this.mimeType_, other.hasMimeType(), other.mimeType_);
                this.imageData_ = visitor.visitByteString(hasImageData(), this.imageData_, other.hasImageData(), other.imageData_);
                this.metadata_ = visitor.visitByteString(hasMetadata(), this.metadata_, other.hasMetadata(), other.metadata_);
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
                            this.mimeType_ = s;
                        } else if (tag == 18) {
                            this.bitField0_ |= 2;
                            this.imageData_ = input.readBytes();
                        } else if (tag == 26) {
                            this.bitField0_ |= 4;
                            this.metadata_ = input.readBytes();
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
                    synchronized (RestrictedImageProto.class) {
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

    public static RestrictedImageProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RestrictedImageProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
