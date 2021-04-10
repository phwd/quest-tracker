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

public final class PageRangeProto extends GeneratedMessageLite<PageRangeProto, Builder> implements PageRangeProtoOrBuilder {
    private static final PageRangeProto DEFAULT_INSTANCE = new PageRangeProto();
    public static final int END_FIELD_NUMBER = 2;
    private static volatile Parser<PageRangeProto> PARSER = null;
    public static final int START_FIELD_NUMBER = 1;
    private int bitField0_;
    private int end_ = 0;
    private int start_ = 0;

    private PageRangeProto() {
    }

    @Override // android.service.print.PageRangeProtoOrBuilder
    public boolean hasStart() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PageRangeProtoOrBuilder
    public int getStart() {
        return this.start_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStart(int value) {
        this.bitField0_ |= 1;
        this.start_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStart() {
        this.bitField0_ &= -2;
        this.start_ = 0;
    }

    @Override // android.service.print.PageRangeProtoOrBuilder
    public boolean hasEnd() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PageRangeProtoOrBuilder
    public int getEnd() {
        return this.end_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnd(int value) {
        this.bitField0_ |= 2;
        this.end_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEnd() {
        this.bitField0_ &= -3;
        this.end_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.start_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.end_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.start_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.end_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PageRangeProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PageRangeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PageRangeProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PageRangeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PageRangeProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PageRangeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PageRangeProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PageRangeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PageRangeProto parseFrom(InputStream input) throws IOException {
        return (PageRangeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PageRangeProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PageRangeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PageRangeProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PageRangeProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PageRangeProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PageRangeProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PageRangeProto parseFrom(CodedInputStream input) throws IOException {
        return (PageRangeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PageRangeProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PageRangeProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PageRangeProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PageRangeProto, Builder> implements PageRangeProtoOrBuilder {
        private Builder() {
            super(PageRangeProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PageRangeProtoOrBuilder
        public boolean hasStart() {
            return ((PageRangeProto) this.instance).hasStart();
        }

        @Override // android.service.print.PageRangeProtoOrBuilder
        public int getStart() {
            return ((PageRangeProto) this.instance).getStart();
        }

        public Builder setStart(int value) {
            copyOnWrite();
            ((PageRangeProto) this.instance).setStart(value);
            return this;
        }

        public Builder clearStart() {
            copyOnWrite();
            ((PageRangeProto) this.instance).clearStart();
            return this;
        }

        @Override // android.service.print.PageRangeProtoOrBuilder
        public boolean hasEnd() {
            return ((PageRangeProto) this.instance).hasEnd();
        }

        @Override // android.service.print.PageRangeProtoOrBuilder
        public int getEnd() {
            return ((PageRangeProto) this.instance).getEnd();
        }

        public Builder setEnd(int value) {
            copyOnWrite();
            ((PageRangeProto) this.instance).setEnd(value);
            return this;
        }

        public Builder clearEnd() {
            copyOnWrite();
            ((PageRangeProto) this.instance).clearEnd();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PageRangeProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PageRangeProto other = (PageRangeProto) arg1;
                this.start_ = visitor.visitInt(hasStart(), this.start_, other.hasStart(), other.start_);
                this.end_ = visitor.visitInt(hasEnd(), this.end_, other.hasEnd(), other.end_);
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
                            this.start_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.end_ = input.readInt32();
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
                    synchronized (PageRangeProto.class) {
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

    public static PageRangeProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PageRangeProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
