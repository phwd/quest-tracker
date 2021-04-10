package com.android.server.am;

import android.content.IntentProto;
import com.android.server.am.BroadcastRecordProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class BroadcastQueueProto extends GeneratedMessageLite<BroadcastQueueProto, Builder> implements BroadcastQueueProtoOrBuilder {
    private static final BroadcastQueueProto DEFAULT_INSTANCE = new BroadcastQueueProto();
    public static final int HISTORICAL_BROADCASTS_FIELD_NUMBER = 5;
    public static final int HISTORICAL_BROADCASTS_SUMMARY_FIELD_NUMBER = 6;
    public static final int ORDERED_BROADCASTS_FIELD_NUMBER = 3;
    public static final int PARALLEL_BROADCASTS_FIELD_NUMBER = 2;
    private static volatile Parser<BroadcastQueueProto> PARSER = null;
    public static final int PENDING_BROADCAST_FIELD_NUMBER = 4;
    public static final int QUEUE_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private Internal.ProtobufList<BroadcastSummary> historicalBroadcastsSummary_ = emptyProtobufList();
    private Internal.ProtobufList<BroadcastRecordProto> historicalBroadcasts_ = emptyProtobufList();
    private Internal.ProtobufList<BroadcastRecordProto> orderedBroadcasts_ = emptyProtobufList();
    private Internal.ProtobufList<BroadcastRecordProto> parallelBroadcasts_ = emptyProtobufList();
    private BroadcastRecordProto pendingBroadcast_;
    private String queueName_ = "";

    public interface BroadcastSummaryOrBuilder extends MessageLiteOrBuilder {
        long getDispatchClockTimeMs();

        long getEnqueueClockTimeMs();

        long getFinishClockTimeMs();

        IntentProto getIntent();

        boolean hasDispatchClockTimeMs();

        boolean hasEnqueueClockTimeMs();

        boolean hasFinishClockTimeMs();

        boolean hasIntent();
    }

    private BroadcastQueueProto() {
    }

    public static final class BroadcastSummary extends GeneratedMessageLite<BroadcastSummary, Builder> implements BroadcastSummaryOrBuilder {
        private static final BroadcastSummary DEFAULT_INSTANCE = new BroadcastSummary();
        public static final int DISPATCH_CLOCK_TIME_MS_FIELD_NUMBER = 3;
        public static final int ENQUEUE_CLOCK_TIME_MS_FIELD_NUMBER = 2;
        public static final int FINISH_CLOCK_TIME_MS_FIELD_NUMBER = 4;
        public static final int INTENT_FIELD_NUMBER = 1;
        private static volatile Parser<BroadcastSummary> PARSER;
        private int bitField0_;
        private long dispatchClockTimeMs_ = 0;
        private long enqueueClockTimeMs_ = 0;
        private long finishClockTimeMs_ = 0;
        private IntentProto intent_;

        private BroadcastSummary() {
        }

        @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
        public boolean hasIntent() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
        public IntentProto getIntent() {
            IntentProto intentProto = this.intent_;
            return intentProto == null ? IntentProto.getDefaultInstance() : intentProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntent(IntentProto value) {
            if (value != null) {
                this.intent_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntent(IntentProto.Builder builderForValue) {
            this.intent_ = (IntentProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeIntent(IntentProto value) {
            IntentProto intentProto = this.intent_;
            if (intentProto == null || intentProto == IntentProto.getDefaultInstance()) {
                this.intent_ = value;
            } else {
                this.intent_ = (IntentProto) ((IntentProto.Builder) IntentProto.newBuilder(this.intent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIntent() {
            this.intent_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
        public boolean hasEnqueueClockTimeMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
        public long getEnqueueClockTimeMs() {
            return this.enqueueClockTimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEnqueueClockTimeMs(long value) {
            this.bitField0_ |= 2;
            this.enqueueClockTimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEnqueueClockTimeMs() {
            this.bitField0_ &= -3;
            this.enqueueClockTimeMs_ = 0;
        }

        @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
        public boolean hasDispatchClockTimeMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
        public long getDispatchClockTimeMs() {
            return this.dispatchClockTimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDispatchClockTimeMs(long value) {
            this.bitField0_ |= 4;
            this.dispatchClockTimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDispatchClockTimeMs() {
            this.bitField0_ &= -5;
            this.dispatchClockTimeMs_ = 0;
        }

        @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
        public boolean hasFinishClockTimeMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
        public long getFinishClockTimeMs() {
            return this.finishClockTimeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFinishClockTimeMs(long value) {
            this.bitField0_ |= 8;
            this.finishClockTimeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFinishClockTimeMs() {
            this.bitField0_ &= -9;
            this.finishClockTimeMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getIntent());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.enqueueClockTimeMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.dispatchClockTimeMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.finishClockTimeMs_);
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getIntent());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.enqueueClockTimeMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.dispatchClockTimeMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.finishClockTimeMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static BroadcastSummary parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BroadcastSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BroadcastSummary parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BroadcastSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BroadcastSummary parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BroadcastSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BroadcastSummary parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BroadcastSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BroadcastSummary parseFrom(InputStream input) throws IOException {
            return (BroadcastSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BroadcastSummary parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BroadcastSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BroadcastSummary parseDelimitedFrom(InputStream input) throws IOException {
            return (BroadcastSummary) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BroadcastSummary parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BroadcastSummary) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BroadcastSummary parseFrom(CodedInputStream input) throws IOException {
            return (BroadcastSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BroadcastSummary parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BroadcastSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BroadcastSummary prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BroadcastSummary, Builder> implements BroadcastSummaryOrBuilder {
            private Builder() {
                super(BroadcastSummary.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
            public boolean hasIntent() {
                return ((BroadcastSummary) this.instance).hasIntent();
            }

            @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
            public IntentProto getIntent() {
                return ((BroadcastSummary) this.instance).getIntent();
            }

            public Builder setIntent(IntentProto value) {
                copyOnWrite();
                ((BroadcastSummary) this.instance).setIntent((BroadcastSummary) value);
                return this;
            }

            public Builder setIntent(IntentProto.Builder builderForValue) {
                copyOnWrite();
                ((BroadcastSummary) this.instance).setIntent((BroadcastSummary) builderForValue);
                return this;
            }

            public Builder mergeIntent(IntentProto value) {
                copyOnWrite();
                ((BroadcastSummary) this.instance).mergeIntent(value);
                return this;
            }

            public Builder clearIntent() {
                copyOnWrite();
                ((BroadcastSummary) this.instance).clearIntent();
                return this;
            }

            @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
            public boolean hasEnqueueClockTimeMs() {
                return ((BroadcastSummary) this.instance).hasEnqueueClockTimeMs();
            }

            @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
            public long getEnqueueClockTimeMs() {
                return ((BroadcastSummary) this.instance).getEnqueueClockTimeMs();
            }

            public Builder setEnqueueClockTimeMs(long value) {
                copyOnWrite();
                ((BroadcastSummary) this.instance).setEnqueueClockTimeMs(value);
                return this;
            }

            public Builder clearEnqueueClockTimeMs() {
                copyOnWrite();
                ((BroadcastSummary) this.instance).clearEnqueueClockTimeMs();
                return this;
            }

            @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
            public boolean hasDispatchClockTimeMs() {
                return ((BroadcastSummary) this.instance).hasDispatchClockTimeMs();
            }

            @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
            public long getDispatchClockTimeMs() {
                return ((BroadcastSummary) this.instance).getDispatchClockTimeMs();
            }

            public Builder setDispatchClockTimeMs(long value) {
                copyOnWrite();
                ((BroadcastSummary) this.instance).setDispatchClockTimeMs(value);
                return this;
            }

            public Builder clearDispatchClockTimeMs() {
                copyOnWrite();
                ((BroadcastSummary) this.instance).clearDispatchClockTimeMs();
                return this;
            }

            @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
            public boolean hasFinishClockTimeMs() {
                return ((BroadcastSummary) this.instance).hasFinishClockTimeMs();
            }

            @Override // com.android.server.am.BroadcastQueueProto.BroadcastSummaryOrBuilder
            public long getFinishClockTimeMs() {
                return ((BroadcastSummary) this.instance).getFinishClockTimeMs();
            }

            public Builder setFinishClockTimeMs(long value) {
                copyOnWrite();
                ((BroadcastSummary) this.instance).setFinishClockTimeMs(value);
                return this;
            }

            public Builder clearFinishClockTimeMs() {
                copyOnWrite();
                ((BroadcastSummary) this.instance).clearFinishClockTimeMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new BroadcastSummary();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    BroadcastSummary other = (BroadcastSummary) arg1;
                    this.intent_ = (IntentProto) visitor.visitMessage(this.intent_, other.intent_);
                    this.enqueueClockTimeMs_ = visitor.visitLong(hasEnqueueClockTimeMs(), this.enqueueClockTimeMs_, other.hasEnqueueClockTimeMs(), other.enqueueClockTimeMs_);
                    this.dispatchClockTimeMs_ = visitor.visitLong(hasDispatchClockTimeMs(), this.dispatchClockTimeMs_, other.hasDispatchClockTimeMs(), other.dispatchClockTimeMs_);
                    this.finishClockTimeMs_ = visitor.visitLong(hasFinishClockTimeMs(), this.finishClockTimeMs_, other.hasFinishClockTimeMs(), other.finishClockTimeMs_);
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
                                IntentProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (IntentProto.Builder) this.intent_.toBuilder();
                                }
                                this.intent_ = (IntentProto) input.readMessage(IntentProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.intent_);
                                    this.intent_ = (IntentProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.enqueueClockTimeMs_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.dispatchClockTimeMs_ = input.readInt64();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.finishClockTimeMs_ = input.readInt64();
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
                        synchronized (BroadcastSummary.class) {
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

        public static BroadcastSummary getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BroadcastSummary> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public boolean hasQueueName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public String getQueueName() {
        return this.queueName_;
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public ByteString getQueueNameBytes() {
        return ByteString.copyFromUtf8(this.queueName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setQueueName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.queueName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearQueueName() {
        this.bitField0_ &= -2;
        this.queueName_ = getDefaultInstance().getQueueName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setQueueNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.queueName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public List<BroadcastRecordProto> getParallelBroadcastsList() {
        return this.parallelBroadcasts_;
    }

    public List<? extends BroadcastRecordProtoOrBuilder> getParallelBroadcastsOrBuilderList() {
        return this.parallelBroadcasts_;
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public int getParallelBroadcastsCount() {
        return this.parallelBroadcasts_.size();
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public BroadcastRecordProto getParallelBroadcasts(int index) {
        return this.parallelBroadcasts_.get(index);
    }

    public BroadcastRecordProtoOrBuilder getParallelBroadcastsOrBuilder(int index) {
        return this.parallelBroadcasts_.get(index);
    }

    private void ensureParallelBroadcastsIsMutable() {
        if (!this.parallelBroadcasts_.isModifiable()) {
            this.parallelBroadcasts_ = GeneratedMessageLite.mutableCopy(this.parallelBroadcasts_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParallelBroadcasts(int index, BroadcastRecordProto value) {
        if (value != null) {
            ensureParallelBroadcastsIsMutable();
            this.parallelBroadcasts_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParallelBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
        ensureParallelBroadcastsIsMutable();
        this.parallelBroadcasts_.set(index, (BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addParallelBroadcasts(BroadcastRecordProto value) {
        if (value != null) {
            ensureParallelBroadcastsIsMutable();
            this.parallelBroadcasts_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addParallelBroadcasts(int index, BroadcastRecordProto value) {
        if (value != null) {
            ensureParallelBroadcastsIsMutable();
            this.parallelBroadcasts_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addParallelBroadcasts(BroadcastRecordProto.Builder builderForValue) {
        ensureParallelBroadcastsIsMutable();
        this.parallelBroadcasts_.add((BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addParallelBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
        ensureParallelBroadcastsIsMutable();
        this.parallelBroadcasts_.add(index, (BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllParallelBroadcasts(Iterable<? extends BroadcastRecordProto> values) {
        ensureParallelBroadcastsIsMutable();
        AbstractMessageLite.addAll(values, this.parallelBroadcasts_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearParallelBroadcasts() {
        this.parallelBroadcasts_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeParallelBroadcasts(int index) {
        ensureParallelBroadcastsIsMutable();
        this.parallelBroadcasts_.remove(index);
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public List<BroadcastRecordProto> getOrderedBroadcastsList() {
        return this.orderedBroadcasts_;
    }

    public List<? extends BroadcastRecordProtoOrBuilder> getOrderedBroadcastsOrBuilderList() {
        return this.orderedBroadcasts_;
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public int getOrderedBroadcastsCount() {
        return this.orderedBroadcasts_.size();
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public BroadcastRecordProto getOrderedBroadcasts(int index) {
        return this.orderedBroadcasts_.get(index);
    }

    public BroadcastRecordProtoOrBuilder getOrderedBroadcastsOrBuilder(int index) {
        return this.orderedBroadcasts_.get(index);
    }

    private void ensureOrderedBroadcastsIsMutable() {
        if (!this.orderedBroadcasts_.isModifiable()) {
            this.orderedBroadcasts_ = GeneratedMessageLite.mutableCopy(this.orderedBroadcasts_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrderedBroadcasts(int index, BroadcastRecordProto value) {
        if (value != null) {
            ensureOrderedBroadcastsIsMutable();
            this.orderedBroadcasts_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrderedBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
        ensureOrderedBroadcastsIsMutable();
        this.orderedBroadcasts_.set(index, (BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOrderedBroadcasts(BroadcastRecordProto value) {
        if (value != null) {
            ensureOrderedBroadcastsIsMutable();
            this.orderedBroadcasts_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOrderedBroadcasts(int index, BroadcastRecordProto value) {
        if (value != null) {
            ensureOrderedBroadcastsIsMutable();
            this.orderedBroadcasts_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOrderedBroadcasts(BroadcastRecordProto.Builder builderForValue) {
        ensureOrderedBroadcastsIsMutable();
        this.orderedBroadcasts_.add((BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOrderedBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
        ensureOrderedBroadcastsIsMutable();
        this.orderedBroadcasts_.add(index, (BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllOrderedBroadcasts(Iterable<? extends BroadcastRecordProto> values) {
        ensureOrderedBroadcastsIsMutable();
        AbstractMessageLite.addAll(values, this.orderedBroadcasts_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOrderedBroadcasts() {
        this.orderedBroadcasts_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeOrderedBroadcasts(int index) {
        ensureOrderedBroadcastsIsMutable();
        this.orderedBroadcasts_.remove(index);
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public boolean hasPendingBroadcast() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public BroadcastRecordProto getPendingBroadcast() {
        BroadcastRecordProto broadcastRecordProto = this.pendingBroadcast_;
        return broadcastRecordProto == null ? BroadcastRecordProto.getDefaultInstance() : broadcastRecordProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingBroadcast(BroadcastRecordProto value) {
        if (value != null) {
            this.pendingBroadcast_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingBroadcast(BroadcastRecordProto.Builder builderForValue) {
        this.pendingBroadcast_ = (BroadcastRecordProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePendingBroadcast(BroadcastRecordProto value) {
        BroadcastRecordProto broadcastRecordProto = this.pendingBroadcast_;
        if (broadcastRecordProto == null || broadcastRecordProto == BroadcastRecordProto.getDefaultInstance()) {
            this.pendingBroadcast_ = value;
        } else {
            this.pendingBroadcast_ = (BroadcastRecordProto) ((BroadcastRecordProto.Builder) BroadcastRecordProto.newBuilder(this.pendingBroadcast_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingBroadcast() {
        this.pendingBroadcast_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public List<BroadcastRecordProto> getHistoricalBroadcastsList() {
        return this.historicalBroadcasts_;
    }

    public List<? extends BroadcastRecordProtoOrBuilder> getHistoricalBroadcastsOrBuilderList() {
        return this.historicalBroadcasts_;
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public int getHistoricalBroadcastsCount() {
        return this.historicalBroadcasts_.size();
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public BroadcastRecordProto getHistoricalBroadcasts(int index) {
        return this.historicalBroadcasts_.get(index);
    }

    public BroadcastRecordProtoOrBuilder getHistoricalBroadcastsOrBuilder(int index) {
        return this.historicalBroadcasts_.get(index);
    }

    private void ensureHistoricalBroadcastsIsMutable() {
        if (!this.historicalBroadcasts_.isModifiable()) {
            this.historicalBroadcasts_ = GeneratedMessageLite.mutableCopy(this.historicalBroadcasts_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoricalBroadcasts(int index, BroadcastRecordProto value) {
        if (value != null) {
            ensureHistoricalBroadcastsIsMutable();
            this.historicalBroadcasts_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoricalBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
        ensureHistoricalBroadcastsIsMutable();
        this.historicalBroadcasts_.set(index, (BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalBroadcasts(BroadcastRecordProto value) {
        if (value != null) {
            ensureHistoricalBroadcastsIsMutable();
            this.historicalBroadcasts_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalBroadcasts(int index, BroadcastRecordProto value) {
        if (value != null) {
            ensureHistoricalBroadcastsIsMutable();
            this.historicalBroadcasts_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalBroadcasts(BroadcastRecordProto.Builder builderForValue) {
        ensureHistoricalBroadcastsIsMutable();
        this.historicalBroadcasts_.add((BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
        ensureHistoricalBroadcastsIsMutable();
        this.historicalBroadcasts_.add(index, (BroadcastRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllHistoricalBroadcasts(Iterable<? extends BroadcastRecordProto> values) {
        ensureHistoricalBroadcastsIsMutable();
        AbstractMessageLite.addAll(values, this.historicalBroadcasts_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHistoricalBroadcasts() {
        this.historicalBroadcasts_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeHistoricalBroadcasts(int index) {
        ensureHistoricalBroadcastsIsMutable();
        this.historicalBroadcasts_.remove(index);
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public List<BroadcastSummary> getHistoricalBroadcastsSummaryList() {
        return this.historicalBroadcastsSummary_;
    }

    public List<? extends BroadcastSummaryOrBuilder> getHistoricalBroadcastsSummaryOrBuilderList() {
        return this.historicalBroadcastsSummary_;
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public int getHistoricalBroadcastsSummaryCount() {
        return this.historicalBroadcastsSummary_.size();
    }

    @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
    public BroadcastSummary getHistoricalBroadcastsSummary(int index) {
        return this.historicalBroadcastsSummary_.get(index);
    }

    public BroadcastSummaryOrBuilder getHistoricalBroadcastsSummaryOrBuilder(int index) {
        return this.historicalBroadcastsSummary_.get(index);
    }

    private void ensureHistoricalBroadcastsSummaryIsMutable() {
        if (!this.historicalBroadcastsSummary_.isModifiable()) {
            this.historicalBroadcastsSummary_ = GeneratedMessageLite.mutableCopy(this.historicalBroadcastsSummary_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoricalBroadcastsSummary(int index, BroadcastSummary value) {
        if (value != null) {
            ensureHistoricalBroadcastsSummaryIsMutable();
            this.historicalBroadcastsSummary_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoricalBroadcastsSummary(int index, BroadcastSummary.Builder builderForValue) {
        ensureHistoricalBroadcastsSummaryIsMutable();
        this.historicalBroadcastsSummary_.set(index, (BroadcastSummary) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalBroadcastsSummary(BroadcastSummary value) {
        if (value != null) {
            ensureHistoricalBroadcastsSummaryIsMutable();
            this.historicalBroadcastsSummary_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalBroadcastsSummary(int index, BroadcastSummary value) {
        if (value != null) {
            ensureHistoricalBroadcastsSummaryIsMutable();
            this.historicalBroadcastsSummary_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalBroadcastsSummary(BroadcastSummary.Builder builderForValue) {
        ensureHistoricalBroadcastsSummaryIsMutable();
        this.historicalBroadcastsSummary_.add((BroadcastSummary) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalBroadcastsSummary(int index, BroadcastSummary.Builder builderForValue) {
        ensureHistoricalBroadcastsSummaryIsMutable();
        this.historicalBroadcastsSummary_.add(index, (BroadcastSummary) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllHistoricalBroadcastsSummary(Iterable<? extends BroadcastSummary> values) {
        ensureHistoricalBroadcastsSummaryIsMutable();
        AbstractMessageLite.addAll(values, this.historicalBroadcastsSummary_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHistoricalBroadcastsSummary() {
        this.historicalBroadcastsSummary_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeHistoricalBroadcastsSummary(int index) {
        ensureHistoricalBroadcastsSummaryIsMutable();
        this.historicalBroadcastsSummary_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getQueueName());
        }
        for (int i = 0; i < this.parallelBroadcasts_.size(); i++) {
            output.writeMessage(2, this.parallelBroadcasts_.get(i));
        }
        for (int i2 = 0; i2 < this.orderedBroadcasts_.size(); i2++) {
            output.writeMessage(3, this.orderedBroadcasts_.get(i2));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(4, getPendingBroadcast());
        }
        for (int i3 = 0; i3 < this.historicalBroadcasts_.size(); i3++) {
            output.writeMessage(5, this.historicalBroadcasts_.get(i3));
        }
        for (int i4 = 0; i4 < this.historicalBroadcastsSummary_.size(); i4++) {
            output.writeMessage(6, this.historicalBroadcastsSummary_.get(i4));
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getQueueName());
        }
        for (int i = 0; i < this.parallelBroadcasts_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.parallelBroadcasts_.get(i));
        }
        for (int i2 = 0; i2 < this.orderedBroadcasts_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.orderedBroadcasts_.get(i2));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(4, getPendingBroadcast());
        }
        for (int i3 = 0; i3 < this.historicalBroadcasts_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.historicalBroadcasts_.get(i3));
        }
        for (int i4 = 0; i4 < this.historicalBroadcastsSummary_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.historicalBroadcastsSummary_.get(i4));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BroadcastQueueProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BroadcastQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BroadcastQueueProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BroadcastQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BroadcastQueueProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BroadcastQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BroadcastQueueProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BroadcastQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BroadcastQueueProto parseFrom(InputStream input) throws IOException {
        return (BroadcastQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastQueueProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BroadcastQueueProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BroadcastQueueProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastQueueProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastQueueProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BroadcastQueueProto parseFrom(CodedInputStream input) throws IOException {
        return (BroadcastQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BroadcastQueueProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BroadcastQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BroadcastQueueProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BroadcastQueueProto, Builder> implements BroadcastQueueProtoOrBuilder {
        private Builder() {
            super(BroadcastQueueProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public boolean hasQueueName() {
            return ((BroadcastQueueProto) this.instance).hasQueueName();
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public String getQueueName() {
            return ((BroadcastQueueProto) this.instance).getQueueName();
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public ByteString getQueueNameBytes() {
            return ((BroadcastQueueProto) this.instance).getQueueNameBytes();
        }

        public Builder setQueueName(String value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setQueueName(value);
            return this;
        }

        public Builder clearQueueName() {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).clearQueueName();
            return this;
        }

        public Builder setQueueNameBytes(ByteString value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setQueueNameBytes(value);
            return this;
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public List<BroadcastRecordProto> getParallelBroadcastsList() {
            return Collections.unmodifiableList(((BroadcastQueueProto) this.instance).getParallelBroadcastsList());
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public int getParallelBroadcastsCount() {
            return ((BroadcastQueueProto) this.instance).getParallelBroadcastsCount();
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public BroadcastRecordProto getParallelBroadcasts(int index) {
            return ((BroadcastQueueProto) this.instance).getParallelBroadcasts(index);
        }

        public Builder setParallelBroadcasts(int index, BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setParallelBroadcasts((BroadcastQueueProto) index, (int) value);
            return this;
        }

        public Builder setParallelBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setParallelBroadcasts((BroadcastQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addParallelBroadcasts(BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addParallelBroadcasts((BroadcastQueueProto) value);
            return this;
        }

        public Builder addParallelBroadcasts(int index, BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addParallelBroadcasts((BroadcastQueueProto) index, (int) value);
            return this;
        }

        public Builder addParallelBroadcasts(BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addParallelBroadcasts((BroadcastQueueProto) builderForValue);
            return this;
        }

        public Builder addParallelBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addParallelBroadcasts((BroadcastQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllParallelBroadcasts(Iterable<? extends BroadcastRecordProto> values) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addAllParallelBroadcasts(values);
            return this;
        }

        public Builder clearParallelBroadcasts() {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).clearParallelBroadcasts();
            return this;
        }

        public Builder removeParallelBroadcasts(int index) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).removeParallelBroadcasts(index);
            return this;
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public List<BroadcastRecordProto> getOrderedBroadcastsList() {
            return Collections.unmodifiableList(((BroadcastQueueProto) this.instance).getOrderedBroadcastsList());
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public int getOrderedBroadcastsCount() {
            return ((BroadcastQueueProto) this.instance).getOrderedBroadcastsCount();
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public BroadcastRecordProto getOrderedBroadcasts(int index) {
            return ((BroadcastQueueProto) this.instance).getOrderedBroadcasts(index);
        }

        public Builder setOrderedBroadcasts(int index, BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setOrderedBroadcasts((BroadcastQueueProto) index, (int) value);
            return this;
        }

        public Builder setOrderedBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setOrderedBroadcasts((BroadcastQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addOrderedBroadcasts(BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addOrderedBroadcasts((BroadcastQueueProto) value);
            return this;
        }

        public Builder addOrderedBroadcasts(int index, BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addOrderedBroadcasts((BroadcastQueueProto) index, (int) value);
            return this;
        }

        public Builder addOrderedBroadcasts(BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addOrderedBroadcasts((BroadcastQueueProto) builderForValue);
            return this;
        }

        public Builder addOrderedBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addOrderedBroadcasts((BroadcastQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllOrderedBroadcasts(Iterable<? extends BroadcastRecordProto> values) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addAllOrderedBroadcasts(values);
            return this;
        }

        public Builder clearOrderedBroadcasts() {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).clearOrderedBroadcasts();
            return this;
        }

        public Builder removeOrderedBroadcasts(int index) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).removeOrderedBroadcasts(index);
            return this;
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public boolean hasPendingBroadcast() {
            return ((BroadcastQueueProto) this.instance).hasPendingBroadcast();
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public BroadcastRecordProto getPendingBroadcast() {
            return ((BroadcastQueueProto) this.instance).getPendingBroadcast();
        }

        public Builder setPendingBroadcast(BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setPendingBroadcast((BroadcastQueueProto) value);
            return this;
        }

        public Builder setPendingBroadcast(BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setPendingBroadcast((BroadcastQueueProto) builderForValue);
            return this;
        }

        public Builder mergePendingBroadcast(BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).mergePendingBroadcast(value);
            return this;
        }

        public Builder clearPendingBroadcast() {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).clearPendingBroadcast();
            return this;
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public List<BroadcastRecordProto> getHistoricalBroadcastsList() {
            return Collections.unmodifiableList(((BroadcastQueueProto) this.instance).getHistoricalBroadcastsList());
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public int getHistoricalBroadcastsCount() {
            return ((BroadcastQueueProto) this.instance).getHistoricalBroadcastsCount();
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public BroadcastRecordProto getHistoricalBroadcasts(int index) {
            return ((BroadcastQueueProto) this.instance).getHistoricalBroadcasts(index);
        }

        public Builder setHistoricalBroadcasts(int index, BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setHistoricalBroadcasts((BroadcastQueueProto) index, (int) value);
            return this;
        }

        public Builder setHistoricalBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setHistoricalBroadcasts((BroadcastQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addHistoricalBroadcasts(BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addHistoricalBroadcasts((BroadcastQueueProto) value);
            return this;
        }

        public Builder addHistoricalBroadcasts(int index, BroadcastRecordProto value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addHistoricalBroadcasts((BroadcastQueueProto) index, (int) value);
            return this;
        }

        public Builder addHistoricalBroadcasts(BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addHistoricalBroadcasts((BroadcastQueueProto) builderForValue);
            return this;
        }

        public Builder addHistoricalBroadcasts(int index, BroadcastRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addHistoricalBroadcasts((BroadcastQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllHistoricalBroadcasts(Iterable<? extends BroadcastRecordProto> values) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addAllHistoricalBroadcasts(values);
            return this;
        }

        public Builder clearHistoricalBroadcasts() {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).clearHistoricalBroadcasts();
            return this;
        }

        public Builder removeHistoricalBroadcasts(int index) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).removeHistoricalBroadcasts(index);
            return this;
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public List<BroadcastSummary> getHistoricalBroadcastsSummaryList() {
            return Collections.unmodifiableList(((BroadcastQueueProto) this.instance).getHistoricalBroadcastsSummaryList());
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public int getHistoricalBroadcastsSummaryCount() {
            return ((BroadcastQueueProto) this.instance).getHistoricalBroadcastsSummaryCount();
        }

        @Override // com.android.server.am.BroadcastQueueProtoOrBuilder
        public BroadcastSummary getHistoricalBroadcastsSummary(int index) {
            return ((BroadcastQueueProto) this.instance).getHistoricalBroadcastsSummary(index);
        }

        public Builder setHistoricalBroadcastsSummary(int index, BroadcastSummary value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setHistoricalBroadcastsSummary((BroadcastQueueProto) index, (int) value);
            return this;
        }

        public Builder setHistoricalBroadcastsSummary(int index, BroadcastSummary.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).setHistoricalBroadcastsSummary((BroadcastQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addHistoricalBroadcastsSummary(BroadcastSummary value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addHistoricalBroadcastsSummary((BroadcastQueueProto) value);
            return this;
        }

        public Builder addHistoricalBroadcastsSummary(int index, BroadcastSummary value) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addHistoricalBroadcastsSummary((BroadcastQueueProto) index, (int) value);
            return this;
        }

        public Builder addHistoricalBroadcastsSummary(BroadcastSummary.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addHistoricalBroadcastsSummary((BroadcastQueueProto) builderForValue);
            return this;
        }

        public Builder addHistoricalBroadcastsSummary(int index, BroadcastSummary.Builder builderForValue) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addHistoricalBroadcastsSummary((BroadcastQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllHistoricalBroadcastsSummary(Iterable<? extends BroadcastSummary> values) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).addAllHistoricalBroadcastsSummary(values);
            return this;
        }

        public Builder clearHistoricalBroadcastsSummary() {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).clearHistoricalBroadcastsSummary();
            return this;
        }

        public Builder removeHistoricalBroadcastsSummary(int index) {
            copyOnWrite();
            ((BroadcastQueueProto) this.instance).removeHistoricalBroadcastsSummary(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BroadcastQueueProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.parallelBroadcasts_.makeImmutable();
                this.orderedBroadcasts_.makeImmutable();
                this.historicalBroadcasts_.makeImmutable();
                this.historicalBroadcastsSummary_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BroadcastQueueProto other = (BroadcastQueueProto) arg1;
                this.queueName_ = visitor.visitString(hasQueueName(), this.queueName_, other.hasQueueName(), other.queueName_);
                this.parallelBroadcasts_ = visitor.visitList(this.parallelBroadcasts_, other.parallelBroadcasts_);
                this.orderedBroadcasts_ = visitor.visitList(this.orderedBroadcasts_, other.orderedBroadcasts_);
                this.pendingBroadcast_ = (BroadcastRecordProto) visitor.visitMessage(this.pendingBroadcast_, other.pendingBroadcast_);
                this.historicalBroadcasts_ = visitor.visitList(this.historicalBroadcasts_, other.historicalBroadcasts_);
                this.historicalBroadcastsSummary_ = visitor.visitList(this.historicalBroadcastsSummary_, other.historicalBroadcastsSummary_);
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
                            this.queueName_ = s;
                        } else if (tag == 18) {
                            if (!this.parallelBroadcasts_.isModifiable()) {
                                this.parallelBroadcasts_ = GeneratedMessageLite.mutableCopy(this.parallelBroadcasts_);
                            }
                            this.parallelBroadcasts_.add((BroadcastRecordProto) input.readMessage(BroadcastRecordProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.orderedBroadcasts_.isModifiable()) {
                                this.orderedBroadcasts_ = GeneratedMessageLite.mutableCopy(this.orderedBroadcasts_);
                            }
                            this.orderedBroadcasts_.add((BroadcastRecordProto) input.readMessage(BroadcastRecordProto.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            BroadcastRecordProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = (BroadcastRecordProto.Builder) this.pendingBroadcast_.toBuilder();
                            }
                            this.pendingBroadcast_ = (BroadcastRecordProto) input.readMessage(BroadcastRecordProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.pendingBroadcast_);
                                this.pendingBroadcast_ = (BroadcastRecordProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 42) {
                            if (!this.historicalBroadcasts_.isModifiable()) {
                                this.historicalBroadcasts_ = GeneratedMessageLite.mutableCopy(this.historicalBroadcasts_);
                            }
                            this.historicalBroadcasts_.add((BroadcastRecordProto) input.readMessage(BroadcastRecordProto.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            if (!this.historicalBroadcastsSummary_.isModifiable()) {
                                this.historicalBroadcastsSummary_ = GeneratedMessageLite.mutableCopy(this.historicalBroadcastsSummary_);
                            }
                            this.historicalBroadcastsSummary_.add((BroadcastSummary) input.readMessage(BroadcastSummary.parser(), extensionRegistry));
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
                    synchronized (BroadcastQueueProto.class) {
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

    public static BroadcastQueueProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BroadcastQueueProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
