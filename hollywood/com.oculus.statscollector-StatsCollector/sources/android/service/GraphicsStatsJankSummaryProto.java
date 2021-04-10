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

public final class GraphicsStatsJankSummaryProto extends GeneratedMessageLite<GraphicsStatsJankSummaryProto, Builder> implements GraphicsStatsJankSummaryProtoOrBuilder {
    private static final GraphicsStatsJankSummaryProto DEFAULT_INSTANCE = new GraphicsStatsJankSummaryProto();
    public static final int HIGH_INPUT_LATENCY_COUNT_FIELD_NUMBER = 4;
    public static final int JANKY_FRAMES_FIELD_NUMBER = 2;
    public static final int MISSED_DEADLINE_COUNT_FIELD_NUMBER = 8;
    public static final int MISSED_VSYNC_COUNT_FIELD_NUMBER = 3;
    private static volatile Parser<GraphicsStatsJankSummaryProto> PARSER = null;
    public static final int SLOW_BITMAP_UPLOAD_COUNT_FIELD_NUMBER = 6;
    public static final int SLOW_DRAW_COUNT_FIELD_NUMBER = 7;
    public static final int SLOW_UI_THREAD_COUNT_FIELD_NUMBER = 5;
    public static final int TOTAL_FRAMES_FIELD_NUMBER = 1;
    private int bitField0_;
    private int highInputLatencyCount_ = 0;
    private int jankyFrames_ = 0;
    private int missedDeadlineCount_ = 0;
    private int missedVsyncCount_ = 0;
    private int slowBitmapUploadCount_ = 0;
    private int slowDrawCount_ = 0;
    private int slowUiThreadCount_ = 0;
    private int totalFrames_ = 0;

    private GraphicsStatsJankSummaryProto() {
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public boolean hasTotalFrames() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public int getTotalFrames() {
        return this.totalFrames_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalFrames(int value) {
        this.bitField0_ |= 1;
        this.totalFrames_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalFrames() {
        this.bitField0_ &= -2;
        this.totalFrames_ = 0;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public boolean hasJankyFrames() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public int getJankyFrames() {
        return this.jankyFrames_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJankyFrames(int value) {
        this.bitField0_ |= 2;
        this.jankyFrames_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJankyFrames() {
        this.bitField0_ &= -3;
        this.jankyFrames_ = 0;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public boolean hasMissedVsyncCount() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public int getMissedVsyncCount() {
        return this.missedVsyncCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMissedVsyncCount(int value) {
        this.bitField0_ |= 4;
        this.missedVsyncCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMissedVsyncCount() {
        this.bitField0_ &= -5;
        this.missedVsyncCount_ = 0;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public boolean hasHighInputLatencyCount() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public int getHighInputLatencyCount() {
        return this.highInputLatencyCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHighInputLatencyCount(int value) {
        this.bitField0_ |= 8;
        this.highInputLatencyCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHighInputLatencyCount() {
        this.bitField0_ &= -9;
        this.highInputLatencyCount_ = 0;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public boolean hasSlowUiThreadCount() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public int getSlowUiThreadCount() {
        return this.slowUiThreadCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSlowUiThreadCount(int value) {
        this.bitField0_ |= 16;
        this.slowUiThreadCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSlowUiThreadCount() {
        this.bitField0_ &= -17;
        this.slowUiThreadCount_ = 0;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public boolean hasSlowBitmapUploadCount() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public int getSlowBitmapUploadCount() {
        return this.slowBitmapUploadCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSlowBitmapUploadCount(int value) {
        this.bitField0_ |= 32;
        this.slowBitmapUploadCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSlowBitmapUploadCount() {
        this.bitField0_ &= -33;
        this.slowBitmapUploadCount_ = 0;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public boolean hasSlowDrawCount() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public int getSlowDrawCount() {
        return this.slowDrawCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSlowDrawCount(int value) {
        this.bitField0_ |= 64;
        this.slowDrawCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSlowDrawCount() {
        this.bitField0_ &= -65;
        this.slowDrawCount_ = 0;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public boolean hasMissedDeadlineCount() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
    public int getMissedDeadlineCount() {
        return this.missedDeadlineCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMissedDeadlineCount(int value) {
        this.bitField0_ |= 128;
        this.missedDeadlineCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMissedDeadlineCount() {
        this.bitField0_ &= -129;
        this.missedDeadlineCount_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.totalFrames_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.jankyFrames_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.missedVsyncCount_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.highInputLatencyCount_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.slowUiThreadCount_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.slowBitmapUploadCount_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.slowDrawCount_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.missedDeadlineCount_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.totalFrames_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.jankyFrames_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.missedVsyncCount_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.highInputLatencyCount_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.slowUiThreadCount_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.slowBitmapUploadCount_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.slowDrawCount_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.missedDeadlineCount_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static GraphicsStatsJankSummaryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (GraphicsStatsJankSummaryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GraphicsStatsJankSummaryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GraphicsStatsJankSummaryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GraphicsStatsJankSummaryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (GraphicsStatsJankSummaryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GraphicsStatsJankSummaryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GraphicsStatsJankSummaryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GraphicsStatsJankSummaryProto parseFrom(InputStream input) throws IOException {
        return (GraphicsStatsJankSummaryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsJankSummaryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsJankSummaryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GraphicsStatsJankSummaryProto parseDelimitedFrom(InputStream input) throws IOException {
        return (GraphicsStatsJankSummaryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsJankSummaryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsJankSummaryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GraphicsStatsJankSummaryProto parseFrom(CodedInputStream input) throws IOException {
        return (GraphicsStatsJankSummaryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsJankSummaryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsJankSummaryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GraphicsStatsJankSummaryProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GraphicsStatsJankSummaryProto, Builder> implements GraphicsStatsJankSummaryProtoOrBuilder {
        private Builder() {
            super(GraphicsStatsJankSummaryProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public boolean hasTotalFrames() {
            return ((GraphicsStatsJankSummaryProto) this.instance).hasTotalFrames();
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public int getTotalFrames() {
            return ((GraphicsStatsJankSummaryProto) this.instance).getTotalFrames();
        }

        public Builder setTotalFrames(int value) {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).setTotalFrames(value);
            return this;
        }

        public Builder clearTotalFrames() {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).clearTotalFrames();
            return this;
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public boolean hasJankyFrames() {
            return ((GraphicsStatsJankSummaryProto) this.instance).hasJankyFrames();
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public int getJankyFrames() {
            return ((GraphicsStatsJankSummaryProto) this.instance).getJankyFrames();
        }

        public Builder setJankyFrames(int value) {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).setJankyFrames(value);
            return this;
        }

        public Builder clearJankyFrames() {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).clearJankyFrames();
            return this;
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public boolean hasMissedVsyncCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).hasMissedVsyncCount();
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public int getMissedVsyncCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).getMissedVsyncCount();
        }

        public Builder setMissedVsyncCount(int value) {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).setMissedVsyncCount(value);
            return this;
        }

        public Builder clearMissedVsyncCount() {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).clearMissedVsyncCount();
            return this;
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public boolean hasHighInputLatencyCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).hasHighInputLatencyCount();
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public int getHighInputLatencyCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).getHighInputLatencyCount();
        }

        public Builder setHighInputLatencyCount(int value) {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).setHighInputLatencyCount(value);
            return this;
        }

        public Builder clearHighInputLatencyCount() {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).clearHighInputLatencyCount();
            return this;
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public boolean hasSlowUiThreadCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).hasSlowUiThreadCount();
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public int getSlowUiThreadCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).getSlowUiThreadCount();
        }

        public Builder setSlowUiThreadCount(int value) {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).setSlowUiThreadCount(value);
            return this;
        }

        public Builder clearSlowUiThreadCount() {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).clearSlowUiThreadCount();
            return this;
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public boolean hasSlowBitmapUploadCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).hasSlowBitmapUploadCount();
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public int getSlowBitmapUploadCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).getSlowBitmapUploadCount();
        }

        public Builder setSlowBitmapUploadCount(int value) {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).setSlowBitmapUploadCount(value);
            return this;
        }

        public Builder clearSlowBitmapUploadCount() {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).clearSlowBitmapUploadCount();
            return this;
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public boolean hasSlowDrawCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).hasSlowDrawCount();
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public int getSlowDrawCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).getSlowDrawCount();
        }

        public Builder setSlowDrawCount(int value) {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).setSlowDrawCount(value);
            return this;
        }

        public Builder clearSlowDrawCount() {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).clearSlowDrawCount();
            return this;
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public boolean hasMissedDeadlineCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).hasMissedDeadlineCount();
        }

        @Override // android.service.GraphicsStatsJankSummaryProtoOrBuilder
        public int getMissedDeadlineCount() {
            return ((GraphicsStatsJankSummaryProto) this.instance).getMissedDeadlineCount();
        }

        public Builder setMissedDeadlineCount(int value) {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).setMissedDeadlineCount(value);
            return this;
        }

        public Builder clearMissedDeadlineCount() {
            copyOnWrite();
            ((GraphicsStatsJankSummaryProto) this.instance).clearMissedDeadlineCount();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new GraphicsStatsJankSummaryProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                GraphicsStatsJankSummaryProto other = (GraphicsStatsJankSummaryProto) arg1;
                this.totalFrames_ = visitor.visitInt(hasTotalFrames(), this.totalFrames_, other.hasTotalFrames(), other.totalFrames_);
                this.jankyFrames_ = visitor.visitInt(hasJankyFrames(), this.jankyFrames_, other.hasJankyFrames(), other.jankyFrames_);
                this.missedVsyncCount_ = visitor.visitInt(hasMissedVsyncCount(), this.missedVsyncCount_, other.hasMissedVsyncCount(), other.missedVsyncCount_);
                this.highInputLatencyCount_ = visitor.visitInt(hasHighInputLatencyCount(), this.highInputLatencyCount_, other.hasHighInputLatencyCount(), other.highInputLatencyCount_);
                this.slowUiThreadCount_ = visitor.visitInt(hasSlowUiThreadCount(), this.slowUiThreadCount_, other.hasSlowUiThreadCount(), other.slowUiThreadCount_);
                this.slowBitmapUploadCount_ = visitor.visitInt(hasSlowBitmapUploadCount(), this.slowBitmapUploadCount_, other.hasSlowBitmapUploadCount(), other.slowBitmapUploadCount_);
                this.slowDrawCount_ = visitor.visitInt(hasSlowDrawCount(), this.slowDrawCount_, other.hasSlowDrawCount(), other.slowDrawCount_);
                this.missedDeadlineCount_ = visitor.visitInt(hasMissedDeadlineCount(), this.missedDeadlineCount_, other.hasMissedDeadlineCount(), other.missedDeadlineCount_);
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
                            this.totalFrames_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.jankyFrames_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.missedVsyncCount_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.highInputLatencyCount_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.slowUiThreadCount_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.slowBitmapUploadCount_ = input.readInt32();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.slowDrawCount_ = input.readInt32();
                        } else if (tag == 64) {
                            this.bitField0_ |= 128;
                            this.missedDeadlineCount_ = input.readInt32();
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
                    synchronized (GraphicsStatsJankSummaryProto.class) {
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

    public static GraphicsStatsJankSummaryProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GraphicsStatsJankSummaryProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
