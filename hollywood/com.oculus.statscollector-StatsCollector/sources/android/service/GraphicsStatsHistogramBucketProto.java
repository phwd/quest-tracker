package android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class GraphicsStatsHistogramBucketProto extends GeneratedMessageLite<GraphicsStatsHistogramBucketProto, Builder> implements GraphicsStatsHistogramBucketProtoOrBuilder {
    private static final GraphicsStatsHistogramBucketProto DEFAULT_INSTANCE = new GraphicsStatsHistogramBucketProto();
    public static final int FRAME_COUNT_FIELD_NUMBER = 2;
    private static volatile Parser<GraphicsStatsHistogramBucketProto> PARSER = null;
    public static final int RENDER_MILLIS_FIELD_NUMBER = 1;
    private int bitField0_;
    private int frameCount_ = 0;
    private int renderMillis_ = 0;

    private GraphicsStatsHistogramBucketProto() {
    }

    @Override // android.service.GraphicsStatsHistogramBucketProtoOrBuilder
    public boolean hasRenderMillis() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.GraphicsStatsHistogramBucketProtoOrBuilder
    public int getRenderMillis() {
        return this.renderMillis_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRenderMillis(int value) {
        this.bitField0_ |= 1;
        this.renderMillis_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRenderMillis() {
        this.bitField0_ &= -2;
        this.renderMillis_ = 0;
    }

    @Override // android.service.GraphicsStatsHistogramBucketProtoOrBuilder
    public boolean hasFrameCount() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.GraphicsStatsHistogramBucketProtoOrBuilder
    public int getFrameCount() {
        return this.frameCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrameCount(int value) {
        this.bitField0_ |= 2;
        this.frameCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFrameCount() {
        this.bitField0_ &= -3;
        this.frameCount_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.renderMillis_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.frameCount_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.renderMillis_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.frameCount_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static GraphicsStatsHistogramBucketProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (GraphicsStatsHistogramBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GraphicsStatsHistogramBucketProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GraphicsStatsHistogramBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GraphicsStatsHistogramBucketProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (GraphicsStatsHistogramBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GraphicsStatsHistogramBucketProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GraphicsStatsHistogramBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GraphicsStatsHistogramBucketProto parseFrom(InputStream input) throws IOException {
        return (GraphicsStatsHistogramBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsHistogramBucketProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsHistogramBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GraphicsStatsHistogramBucketProto parseDelimitedFrom(InputStream input) throws IOException {
        return (GraphicsStatsHistogramBucketProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsHistogramBucketProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsHistogramBucketProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GraphicsStatsHistogramBucketProto parseFrom(CodedInputStream input) throws IOException {
        return (GraphicsStatsHistogramBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsHistogramBucketProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsHistogramBucketProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GraphicsStatsHistogramBucketProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GraphicsStatsHistogramBucketProto, Builder> implements GraphicsStatsHistogramBucketProtoOrBuilder {
        private Builder() {
            super(GraphicsStatsHistogramBucketProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.GraphicsStatsHistogramBucketProtoOrBuilder
        public boolean hasRenderMillis() {
            return ((GraphicsStatsHistogramBucketProto) this.instance).hasRenderMillis();
        }

        @Override // android.service.GraphicsStatsHistogramBucketProtoOrBuilder
        public int getRenderMillis() {
            return ((GraphicsStatsHistogramBucketProto) this.instance).getRenderMillis();
        }

        public Builder setRenderMillis(int value) {
            copyOnWrite();
            ((GraphicsStatsHistogramBucketProto) this.instance).setRenderMillis(value);
            return this;
        }

        public Builder clearRenderMillis() {
            copyOnWrite();
            ((GraphicsStatsHistogramBucketProto) this.instance).clearRenderMillis();
            return this;
        }

        @Override // android.service.GraphicsStatsHistogramBucketProtoOrBuilder
        public boolean hasFrameCount() {
            return ((GraphicsStatsHistogramBucketProto) this.instance).hasFrameCount();
        }

        @Override // android.service.GraphicsStatsHistogramBucketProtoOrBuilder
        public int getFrameCount() {
            return ((GraphicsStatsHistogramBucketProto) this.instance).getFrameCount();
        }

        public Builder setFrameCount(int value) {
            copyOnWrite();
            ((GraphicsStatsHistogramBucketProto) this.instance).setFrameCount(value);
            return this;
        }

        public Builder clearFrameCount() {
            copyOnWrite();
            ((GraphicsStatsHistogramBucketProto) this.instance).clearFrameCount();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new GraphicsStatsHistogramBucketProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                GraphicsStatsHistogramBucketProto other = (GraphicsStatsHistogramBucketProto) arg1;
                this.renderMillis_ = visitor.visitInt(hasRenderMillis(), this.renderMillis_, other.hasRenderMillis(), other.renderMillis_);
                this.frameCount_ = visitor.visitInt(hasFrameCount(), this.frameCount_, other.hasFrameCount(), other.frameCount_);
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
                            this.renderMillis_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.frameCount_ = input.readInt32();
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
                    synchronized (GraphicsStatsHistogramBucketProto.class) {
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

    public static GraphicsStatsHistogramBucketProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GraphicsStatsHistogramBucketProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
