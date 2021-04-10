package android.service.print;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PrintDocumentInfoProto extends GeneratedMessageLite<PrintDocumentInfoProto, Builder> implements PrintDocumentInfoProtoOrBuilder {
    public static final int CONTENT_TYPE_FIELD_NUMBER = 3;
    public static final int DATA_SIZE_FIELD_NUMBER = 4;
    private static final PrintDocumentInfoProto DEFAULT_INSTANCE = new PrintDocumentInfoProto();
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int PAGE_COUNT_FIELD_NUMBER = 2;
    private static volatile Parser<PrintDocumentInfoProto> PARSER;
    private int bitField0_;
    private int contentType_ = 0;
    private long dataSize_ = 0;
    private String name_ = "";
    private int pageCount_ = 0;

    private PrintDocumentInfoProto() {
    }

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
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

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
    public boolean hasPageCount() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
    public int getPageCount() {
        return this.pageCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPageCount(int value) {
        this.bitField0_ |= 2;
        this.pageCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPageCount() {
        this.bitField0_ &= -3;
        this.pageCount_ = 0;
    }

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
    public boolean hasContentType() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
    public int getContentType() {
        return this.contentType_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setContentType(int value) {
        this.bitField0_ |= 4;
        this.contentType_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearContentType() {
        this.bitField0_ &= -5;
        this.contentType_ = 0;
    }

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
    public boolean hasDataSize() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
    public long getDataSize() {
        return this.dataSize_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDataSize(long value) {
        this.bitField0_ |= 8;
        this.dataSize_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDataSize() {
        this.bitField0_ &= -9;
        this.dataSize_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.pageCount_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.contentType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.dataSize_);
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
            size2 += CodedOutputStream.computeInt32Size(2, this.pageCount_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.contentType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.dataSize_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PrintDocumentInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrintDocumentInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintDocumentInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintDocumentInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintDocumentInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrintDocumentInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintDocumentInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintDocumentInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintDocumentInfoProto parseFrom(InputStream input) throws IOException {
        return (PrintDocumentInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintDocumentInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintDocumentInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintDocumentInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrintDocumentInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintDocumentInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintDocumentInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintDocumentInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (PrintDocumentInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintDocumentInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintDocumentInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrintDocumentInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrintDocumentInfoProto, Builder> implements PrintDocumentInfoProtoOrBuilder {
        private Builder() {
            super(PrintDocumentInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public boolean hasName() {
            return ((PrintDocumentInfoProto) this.instance).hasName();
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public String getName() {
            return ((PrintDocumentInfoProto) this.instance).getName();
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public ByteString getNameBytes() {
            return ((PrintDocumentInfoProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public boolean hasPageCount() {
            return ((PrintDocumentInfoProto) this.instance).hasPageCount();
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public int getPageCount() {
            return ((PrintDocumentInfoProto) this.instance).getPageCount();
        }

        public Builder setPageCount(int value) {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).setPageCount(value);
            return this;
        }

        public Builder clearPageCount() {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).clearPageCount();
            return this;
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public boolean hasContentType() {
            return ((PrintDocumentInfoProto) this.instance).hasContentType();
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public int getContentType() {
            return ((PrintDocumentInfoProto) this.instance).getContentType();
        }

        public Builder setContentType(int value) {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).setContentType(value);
            return this;
        }

        public Builder clearContentType() {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).clearContentType();
            return this;
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public boolean hasDataSize() {
            return ((PrintDocumentInfoProto) this.instance).hasDataSize();
        }

        @Override // android.service.print.PrintDocumentInfoProtoOrBuilder
        public long getDataSize() {
            return ((PrintDocumentInfoProto) this.instance).getDataSize();
        }

        public Builder setDataSize(long value) {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).setDataSize(value);
            return this;
        }

        public Builder clearDataSize() {
            copyOnWrite();
            ((PrintDocumentInfoProto) this.instance).clearDataSize();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrintDocumentInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrintDocumentInfoProto other = (PrintDocumentInfoProto) arg1;
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.pageCount_ = visitor.visitInt(hasPageCount(), this.pageCount_, other.hasPageCount(), other.pageCount_);
                this.contentType_ = visitor.visitInt(hasContentType(), this.contentType_, other.hasContentType(), other.contentType_);
                this.dataSize_ = visitor.visitLong(hasDataSize(), this.dataSize_, other.hasDataSize(), other.dataSize_);
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
                            this.name_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.pageCount_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.contentType_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.dataSize_ = input.readInt64();
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
                    synchronized (PrintDocumentInfoProto.class) {
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

    public static PrintDocumentInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrintDocumentInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
