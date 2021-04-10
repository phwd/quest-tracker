package android.service;

import android.service.GraphicsStatsHistogramBucketProto;
import android.service.GraphicsStatsJankSummaryProto;
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

public final class GraphicsStatsProto extends GeneratedMessageLite<GraphicsStatsProto, Builder> implements GraphicsStatsProtoOrBuilder {
    private static final GraphicsStatsProto DEFAULT_INSTANCE = new GraphicsStatsProto();
    public static final int HISTOGRAM_FIELD_NUMBER = 6;
    public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
    private static volatile Parser<GraphicsStatsProto> PARSER = null;
    public static final int STATS_END_FIELD_NUMBER = 4;
    public static final int STATS_START_FIELD_NUMBER = 3;
    public static final int SUMMARY_FIELD_NUMBER = 5;
    public static final int VERSION_CODE_FIELD_NUMBER = 2;
    private int bitField0_;
    private Internal.ProtobufList<GraphicsStatsHistogramBucketProto> histogram_ = emptyProtobufList();
    private String packageName_ = "";
    private long statsEnd_ = 0;
    private long statsStart_ = 0;
    private GraphicsStatsJankSummaryProto summary_;
    private long versionCode_ = 0;

    private GraphicsStatsProto() {
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public boolean hasPackageName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public String getPackageName() {
        return this.packageName_;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public ByteString getPackageNameBytes() {
        return ByteString.copyFromUtf8(this.packageName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.packageName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageName() {
        this.bitField0_ &= -2;
        this.packageName_ = getDefaultInstance().getPackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.packageName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public boolean hasVersionCode() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public long getVersionCode() {
        return this.versionCode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersionCode(long value) {
        this.bitField0_ |= 2;
        this.versionCode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersionCode() {
        this.bitField0_ &= -3;
        this.versionCode_ = 0;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public boolean hasStatsStart() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public long getStatsStart() {
        return this.statsStart_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatsStart(long value) {
        this.bitField0_ |= 4;
        this.statsStart_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatsStart() {
        this.bitField0_ &= -5;
        this.statsStart_ = 0;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public boolean hasStatsEnd() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public long getStatsEnd() {
        return this.statsEnd_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatsEnd(long value) {
        this.bitField0_ |= 8;
        this.statsEnd_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatsEnd() {
        this.bitField0_ &= -9;
        this.statsEnd_ = 0;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public boolean hasSummary() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public GraphicsStatsJankSummaryProto getSummary() {
        GraphicsStatsJankSummaryProto graphicsStatsJankSummaryProto = this.summary_;
        return graphicsStatsJankSummaryProto == null ? GraphicsStatsJankSummaryProto.getDefaultInstance() : graphicsStatsJankSummaryProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSummary(GraphicsStatsJankSummaryProto value) {
        if (value != null) {
            this.summary_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSummary(GraphicsStatsJankSummaryProto.Builder builderForValue) {
        this.summary_ = (GraphicsStatsJankSummaryProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSummary(GraphicsStatsJankSummaryProto value) {
        GraphicsStatsJankSummaryProto graphicsStatsJankSummaryProto = this.summary_;
        if (graphicsStatsJankSummaryProto == null || graphicsStatsJankSummaryProto == GraphicsStatsJankSummaryProto.getDefaultInstance()) {
            this.summary_ = value;
        } else {
            this.summary_ = (GraphicsStatsJankSummaryProto) ((GraphicsStatsJankSummaryProto.Builder) GraphicsStatsJankSummaryProto.newBuilder(this.summary_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSummary() {
        this.summary_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public List<GraphicsStatsHistogramBucketProto> getHistogramList() {
        return this.histogram_;
    }

    public List<? extends GraphicsStatsHistogramBucketProtoOrBuilder> getHistogramOrBuilderList() {
        return this.histogram_;
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public int getHistogramCount() {
        return this.histogram_.size();
    }

    @Override // android.service.GraphicsStatsProtoOrBuilder
    public GraphicsStatsHistogramBucketProto getHistogram(int index) {
        return this.histogram_.get(index);
    }

    public GraphicsStatsHistogramBucketProtoOrBuilder getHistogramOrBuilder(int index) {
        return this.histogram_.get(index);
    }

    private void ensureHistogramIsMutable() {
        if (!this.histogram_.isModifiable()) {
            this.histogram_ = GeneratedMessageLite.mutableCopy(this.histogram_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistogram(int index, GraphicsStatsHistogramBucketProto value) {
        if (value != null) {
            ensureHistogramIsMutable();
            this.histogram_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistogram(int index, GraphicsStatsHistogramBucketProto.Builder builderForValue) {
        ensureHistogramIsMutable();
        this.histogram_.set(index, (GraphicsStatsHistogramBucketProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistogram(GraphicsStatsHistogramBucketProto value) {
        if (value != null) {
            ensureHistogramIsMutable();
            this.histogram_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistogram(int index, GraphicsStatsHistogramBucketProto value) {
        if (value != null) {
            ensureHistogramIsMutable();
            this.histogram_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistogram(GraphicsStatsHistogramBucketProto.Builder builderForValue) {
        ensureHistogramIsMutable();
        this.histogram_.add((GraphicsStatsHistogramBucketProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistogram(int index, GraphicsStatsHistogramBucketProto.Builder builderForValue) {
        ensureHistogramIsMutable();
        this.histogram_.add(index, (GraphicsStatsHistogramBucketProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllHistogram(Iterable<? extends GraphicsStatsHistogramBucketProto> values) {
        ensureHistogramIsMutable();
        AbstractMessageLite.addAll(values, this.histogram_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHistogram() {
        this.histogram_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeHistogram(int index) {
        ensureHistogramIsMutable();
        this.histogram_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getPackageName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.versionCode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.statsStart_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.statsEnd_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getSummary());
        }
        for (int i = 0; i < this.histogram_.size(); i++) {
            output.writeMessage(6, this.histogram_.get(i));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getPackageName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.versionCode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.statsStart_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.statsEnd_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getSummary());
        }
        for (int i = 0; i < this.histogram_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.histogram_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static GraphicsStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (GraphicsStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GraphicsStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GraphicsStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GraphicsStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (GraphicsStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static GraphicsStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (GraphicsStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static GraphicsStatsProto parseFrom(InputStream input) throws IOException {
        return (GraphicsStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GraphicsStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (GraphicsStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static GraphicsStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (GraphicsStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static GraphicsStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (GraphicsStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GraphicsStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GraphicsStatsProto, Builder> implements GraphicsStatsProtoOrBuilder {
        private Builder() {
            super(GraphicsStatsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public boolean hasPackageName() {
            return ((GraphicsStatsProto) this.instance).hasPackageName();
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public String getPackageName() {
            return ((GraphicsStatsProto) this.instance).getPackageName();
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ((GraphicsStatsProto) this.instance).getPackageNameBytes();
        }

        public Builder setPackageName(String value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setPackageName(value);
            return this;
        }

        public Builder clearPackageName() {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).clearPackageName();
            return this;
        }

        public Builder setPackageNameBytes(ByteString value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setPackageNameBytes(value);
            return this;
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public boolean hasVersionCode() {
            return ((GraphicsStatsProto) this.instance).hasVersionCode();
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public long getVersionCode() {
            return ((GraphicsStatsProto) this.instance).getVersionCode();
        }

        public Builder setVersionCode(long value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setVersionCode(value);
            return this;
        }

        public Builder clearVersionCode() {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).clearVersionCode();
            return this;
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public boolean hasStatsStart() {
            return ((GraphicsStatsProto) this.instance).hasStatsStart();
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public long getStatsStart() {
            return ((GraphicsStatsProto) this.instance).getStatsStart();
        }

        public Builder setStatsStart(long value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setStatsStart(value);
            return this;
        }

        public Builder clearStatsStart() {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).clearStatsStart();
            return this;
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public boolean hasStatsEnd() {
            return ((GraphicsStatsProto) this.instance).hasStatsEnd();
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public long getStatsEnd() {
            return ((GraphicsStatsProto) this.instance).getStatsEnd();
        }

        public Builder setStatsEnd(long value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setStatsEnd(value);
            return this;
        }

        public Builder clearStatsEnd() {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).clearStatsEnd();
            return this;
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public boolean hasSummary() {
            return ((GraphicsStatsProto) this.instance).hasSummary();
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public GraphicsStatsJankSummaryProto getSummary() {
            return ((GraphicsStatsProto) this.instance).getSummary();
        }

        public Builder setSummary(GraphicsStatsJankSummaryProto value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setSummary((GraphicsStatsProto) value);
            return this;
        }

        public Builder setSummary(GraphicsStatsJankSummaryProto.Builder builderForValue) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setSummary((GraphicsStatsProto) builderForValue);
            return this;
        }

        public Builder mergeSummary(GraphicsStatsJankSummaryProto value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).mergeSummary(value);
            return this;
        }

        public Builder clearSummary() {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).clearSummary();
            return this;
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public List<GraphicsStatsHistogramBucketProto> getHistogramList() {
            return Collections.unmodifiableList(((GraphicsStatsProto) this.instance).getHistogramList());
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public int getHistogramCount() {
            return ((GraphicsStatsProto) this.instance).getHistogramCount();
        }

        @Override // android.service.GraphicsStatsProtoOrBuilder
        public GraphicsStatsHistogramBucketProto getHistogram(int index) {
            return ((GraphicsStatsProto) this.instance).getHistogram(index);
        }

        public Builder setHistogram(int index, GraphicsStatsHistogramBucketProto value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setHistogram((GraphicsStatsProto) index, (int) value);
            return this;
        }

        public Builder setHistogram(int index, GraphicsStatsHistogramBucketProto.Builder builderForValue) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).setHistogram((GraphicsStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addHistogram(GraphicsStatsHistogramBucketProto value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).addHistogram((GraphicsStatsProto) value);
            return this;
        }

        public Builder addHistogram(int index, GraphicsStatsHistogramBucketProto value) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).addHistogram((GraphicsStatsProto) index, (int) value);
            return this;
        }

        public Builder addHistogram(GraphicsStatsHistogramBucketProto.Builder builderForValue) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).addHistogram((GraphicsStatsProto) builderForValue);
            return this;
        }

        public Builder addHistogram(int index, GraphicsStatsHistogramBucketProto.Builder builderForValue) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).addHistogram((GraphicsStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllHistogram(Iterable<? extends GraphicsStatsHistogramBucketProto> values) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).addAllHistogram(values);
            return this;
        }

        public Builder clearHistogram() {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).clearHistogram();
            return this;
        }

        public Builder removeHistogram(int index) {
            copyOnWrite();
            ((GraphicsStatsProto) this.instance).removeHistogram(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new GraphicsStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.histogram_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                GraphicsStatsProto other = (GraphicsStatsProto) arg1;
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                this.versionCode_ = visitor.visitLong(hasVersionCode(), this.versionCode_, other.hasVersionCode(), other.versionCode_);
                this.statsStart_ = visitor.visitLong(hasStatsStart(), this.statsStart_, other.hasStatsStart(), other.statsStart_);
                this.statsEnd_ = visitor.visitLong(hasStatsEnd(), this.statsEnd_, other.hasStatsEnd(), other.statsEnd_);
                this.summary_ = (GraphicsStatsJankSummaryProto) visitor.visitMessage(this.summary_, other.summary_);
                this.histogram_ = visitor.visitList(this.histogram_, other.histogram_);
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
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.packageName_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.versionCode_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.statsStart_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.statsEnd_ = input.readInt64();
                        } else if (tag == 42) {
                            GraphicsStatsJankSummaryProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder = (GraphicsStatsJankSummaryProto.Builder) this.summary_.toBuilder();
                            }
                            this.summary_ = (GraphicsStatsJankSummaryProto) input.readMessage(GraphicsStatsJankSummaryProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.summary_);
                                this.summary_ = (GraphicsStatsJankSummaryProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ = 16 | this.bitField0_;
                        } else if (tag == 50) {
                            if (!this.histogram_.isModifiable()) {
                                this.histogram_ = GeneratedMessageLite.mutableCopy(this.histogram_);
                            }
                            this.histogram_.add((GraphicsStatsHistogramBucketProto) input.readMessage(GraphicsStatsHistogramBucketProto.parser(), extensionRegistry));
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
                    synchronized (GraphicsStatsProto.class) {
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

    public static GraphicsStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GraphicsStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
