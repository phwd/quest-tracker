package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class GZippedFileProto extends GeneratedMessageLite<GZippedFileProto, Builder> implements GZippedFileProtoOrBuilder {
    private static final GZippedFileProto DEFAULT_INSTANCE = new GZippedFileProto();
    public static final int FILENAME_FIELD_NUMBER = 1;
    public static final int GZIPPED_DATA_FIELD_NUMBER = 2;
    private static volatile Parser<GZippedFileProto> PARSER;
    private int bitField0_;
    private String filename_ = "";
    private ByteString gzippedData_ = ByteString.EMPTY;

    private GZippedFileProto() {
    }

    @Override // android.os.GZippedFileProtoOrBuilder
    public boolean hasFilename() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.GZippedFileProtoOrBuilder
    public String getFilename() {
        return this.filename_;
    }

    @Override // android.os.GZippedFileProtoOrBuilder
    public ByteString getFilenameBytes() {
        return ByteString.copyFromUtf8(this.filename_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilename(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.filename_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFilename() {
        this.bitField0_ &= -2;
        this.filename_ = getDefaultInstance().getFilename();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilenameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.filename_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.GZippedFileProtoOrBuilder
    public boolean hasGzippedData() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.GZippedFileProtoOrBuilder
    public ByteString getGzippedData() {
        return this.gzippedData_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGzippedData(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.gzippedData_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGzippedData() {
        this.bitField0_ &= -3;
        this.gzippedData_ = getDefaultInstance().getGzippedData();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getFilename());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBytes(2, this.gzippedData_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getFilename());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBytesSize(2, this.gzippedData_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static GZippedFileProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (GZippedFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GZippedFileProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GZippedFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GZippedFileProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (GZippedFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GZippedFileProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GZippedFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GZippedFileProto parseFrom(InputStream input) throws IOException {
        return (GZippedFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GZippedFileProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GZippedFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GZippedFileProto parseDelimitedFrom(InputStream input) throws IOException {
        return (GZippedFileProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static GZippedFileProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GZippedFileProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GZippedFileProto parseFrom(CodedInputStream input) throws IOException {
        return (GZippedFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GZippedFileProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GZippedFileProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GZippedFileProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GZippedFileProto, Builder> implements GZippedFileProtoOrBuilder {
        private Builder() {
            super(GZippedFileProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.GZippedFileProtoOrBuilder
        public boolean hasFilename() {
            return ((GZippedFileProto) this.instance).hasFilename();
        }

        @Override // android.os.GZippedFileProtoOrBuilder
        public String getFilename() {
            return ((GZippedFileProto) this.instance).getFilename();
        }

        @Override // android.os.GZippedFileProtoOrBuilder
        public ByteString getFilenameBytes() {
            return ((GZippedFileProto) this.instance).getFilenameBytes();
        }

        public Builder setFilename(String value) {
            copyOnWrite();
            ((GZippedFileProto) this.instance).setFilename(value);
            return this;
        }

        public Builder clearFilename() {
            copyOnWrite();
            ((GZippedFileProto) this.instance).clearFilename();
            return this;
        }

        public Builder setFilenameBytes(ByteString value) {
            copyOnWrite();
            ((GZippedFileProto) this.instance).setFilenameBytes(value);
            return this;
        }

        @Override // android.os.GZippedFileProtoOrBuilder
        public boolean hasGzippedData() {
            return ((GZippedFileProto) this.instance).hasGzippedData();
        }

        @Override // android.os.GZippedFileProtoOrBuilder
        public ByteString getGzippedData() {
            return ((GZippedFileProto) this.instance).getGzippedData();
        }

        public Builder setGzippedData(ByteString value) {
            copyOnWrite();
            ((GZippedFileProto) this.instance).setGzippedData(value);
            return this;
        }

        public Builder clearGzippedData() {
            copyOnWrite();
            ((GZippedFileProto) this.instance).clearGzippedData();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new GZippedFileProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                GZippedFileProto other = (GZippedFileProto) arg1;
                this.filename_ = visitor.visitString(hasFilename(), this.filename_, other.hasFilename(), other.filename_);
                this.gzippedData_ = visitor.visitByteString(hasGzippedData(), this.gzippedData_, other.hasGzippedData(), other.gzippedData_);
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
                            this.filename_ = s;
                        } else if (tag == 18) {
                            this.bitField0_ |= 2;
                            this.gzippedData_ = input.readBytes();
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
                    synchronized (GZippedFileProto.class) {
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

    public static GZippedFileProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GZippedFileProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
