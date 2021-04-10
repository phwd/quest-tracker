package com.android.server.job;

import android.app.job.StopReasonEnum;
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

public final class DataSetProto extends GeneratedMessageLite<DataSetProto, Builder> implements DataSetProtoOrBuilder {
    private static final DataSetProto DEFAULT_INSTANCE = new DataSetProto();
    public static final int ELAPSED_TIME_MS_FIELD_NUMBER = 2;
    public static final int MAX_CONCURRENCY_FIELD_NUMBER = 5;
    public static final int MAX_FOREGROUND_CONCURRENCY_FIELD_NUMBER = 6;
    public static final int PACKAGE_ENTRIES_FIELD_NUMBER = 4;
    private static volatile Parser<DataSetProto> PARSER = null;
    public static final int PERIOD_MS_FIELD_NUMBER = 3;
    public static final int START_CLOCK_TIME_MS_FIELD_NUMBER = 1;
    private int bitField0_;
    private long elapsedTimeMs_ = 0;
    private int maxConcurrency_ = 0;
    private int maxForegroundConcurrency_ = 0;
    private Internal.ProtobufList<PackageEntryProto> packageEntries_ = emptyProtobufList();
    private long periodMs_ = 0;
    private long startClockTimeMs_ = 0;

    public interface PackageEntryProtoOrBuilder extends MessageLiteOrBuilder {
        boolean getActive();

        PackageEntryProto.State getActiveState();

        boolean getActiveTop();

        PackageEntryProto.State getActiveTopState();

        String getPackageName();

        ByteString getPackageNameBytes();

        boolean getPending();

        PackageEntryProto.State getPendingState();

        PackageEntryProto.StopReasonCount getStopReasons(int i);

        int getStopReasonsCount();

        List<PackageEntryProto.StopReasonCount> getStopReasonsList();

        int getUid();

        boolean hasActive();

        boolean hasActiveState();

        boolean hasActiveTop();

        boolean hasActiveTopState();

        boolean hasPackageName();

        boolean hasPending();

        boolean hasPendingState();

        boolean hasUid();
    }

    private DataSetProto() {
    }

    public static final class PackageEntryProto extends GeneratedMessageLite<PackageEntryProto, Builder> implements PackageEntryProtoOrBuilder {
        public static final int ACTIVE_FIELD_NUMBER = 7;
        public static final int ACTIVE_STATE_FIELD_NUMBER = 4;
        public static final int ACTIVE_TOP_FIELD_NUMBER = 8;
        public static final int ACTIVE_TOP_STATE_FIELD_NUMBER = 5;
        private static final PackageEntryProto DEFAULT_INSTANCE = new PackageEntryProto();
        public static final int PACKAGE_NAME_FIELD_NUMBER = 2;
        private static volatile Parser<PackageEntryProto> PARSER = null;
        public static final int PENDING_FIELD_NUMBER = 6;
        public static final int PENDING_STATE_FIELD_NUMBER = 3;
        public static final int STOP_REASONS_FIELD_NUMBER = 9;
        public static final int UID_FIELD_NUMBER = 1;
        private State activeState_;
        private State activeTopState_;
        private boolean activeTop_ = false;
        private boolean active_ = false;
        private int bitField0_;
        private String packageName_ = "";
        private State pendingState_;
        private boolean pending_ = false;
        private Internal.ProtobufList<StopReasonCount> stopReasons_ = emptyProtobufList();
        private int uid_ = 0;

        public interface StateOrBuilder extends MessageLiteOrBuilder {
            int getCount();

            long getDurationMs();

            boolean hasCount();

            boolean hasDurationMs();
        }

        public interface StopReasonCountOrBuilder extends MessageLiteOrBuilder {
            int getCount();

            StopReasonEnum getReason();

            boolean hasCount();

            boolean hasReason();
        }

        private PackageEntryProto() {
        }

        public static final class State extends GeneratedMessageLite<State, Builder> implements StateOrBuilder {
            public static final int COUNT_FIELD_NUMBER = 2;
            private static final State DEFAULT_INSTANCE = new State();
            public static final int DURATION_MS_FIELD_NUMBER = 1;
            private static volatile Parser<State> PARSER;
            private int bitField0_;
            private int count_ = 0;
            private long durationMs_ = 0;

            private State() {
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProto.StateOrBuilder
            public boolean hasDurationMs() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProto.StateOrBuilder
            public long getDurationMs() {
                return this.durationMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setDurationMs(long value) {
                this.bitField0_ |= 1;
                this.durationMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearDurationMs() {
                this.bitField0_ &= -2;
                this.durationMs_ = 0;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProto.StateOrBuilder
            public boolean hasCount() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProto.StateOrBuilder
            public int getCount() {
                return this.count_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCount(int value) {
                this.bitField0_ |= 2;
                this.count_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearCount() {
                this.bitField0_ &= -3;
                this.count_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt64(1, this.durationMs_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.count_);
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
                    size2 = 0 + CodedOutputStream.computeInt64Size(1, this.durationMs_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.count_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static State parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (State) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static State parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (State) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static State parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (State) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static State parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (State) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static State parseFrom(InputStream input) throws IOException {
                return (State) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static State parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (State) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static State parseDelimitedFrom(InputStream input) throws IOException {
                return (State) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static State parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (State) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static State parseFrom(CodedInputStream input) throws IOException {
                return (State) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static State parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (State) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(State prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<State, Builder> implements StateOrBuilder {
                private Builder() {
                    super(State.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.DataSetProto.PackageEntryProto.StateOrBuilder
                public boolean hasDurationMs() {
                    return ((State) this.instance).hasDurationMs();
                }

                @Override // com.android.server.job.DataSetProto.PackageEntryProto.StateOrBuilder
                public long getDurationMs() {
                    return ((State) this.instance).getDurationMs();
                }

                public Builder setDurationMs(long value) {
                    copyOnWrite();
                    ((State) this.instance).setDurationMs(value);
                    return this;
                }

                public Builder clearDurationMs() {
                    copyOnWrite();
                    ((State) this.instance).clearDurationMs();
                    return this;
                }

                @Override // com.android.server.job.DataSetProto.PackageEntryProto.StateOrBuilder
                public boolean hasCount() {
                    return ((State) this.instance).hasCount();
                }

                @Override // com.android.server.job.DataSetProto.PackageEntryProto.StateOrBuilder
                public int getCount() {
                    return ((State) this.instance).getCount();
                }

                public Builder setCount(int value) {
                    copyOnWrite();
                    ((State) this.instance).setCount(value);
                    return this;
                }

                public Builder clearCount() {
                    copyOnWrite();
                    ((State) this.instance).clearCount();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new State();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        State other = (State) arg1;
                        this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
                        this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
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
                                    this.durationMs_ = input.readInt64();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.count_ = input.readInt32();
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
                            synchronized (State.class) {
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

            public static State getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<State> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        public static final class StopReasonCount extends GeneratedMessageLite<StopReasonCount, Builder> implements StopReasonCountOrBuilder {
            public static final int COUNT_FIELD_NUMBER = 2;
            private static final StopReasonCount DEFAULT_INSTANCE = new StopReasonCount();
            private static volatile Parser<StopReasonCount> PARSER = null;
            public static final int REASON_FIELD_NUMBER = 1;
            private int bitField0_;
            private int count_ = 0;
            private int reason_ = -1;

            private StopReasonCount() {
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProto.StopReasonCountOrBuilder
            public boolean hasReason() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProto.StopReasonCountOrBuilder
            public StopReasonEnum getReason() {
                StopReasonEnum result = StopReasonEnum.forNumber(this.reason_);
                return result == null ? StopReasonEnum.STOP_REASON_UNKNOWN : result;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setReason(StopReasonEnum value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.reason_ = value.getNumber();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearReason() {
                this.bitField0_ &= -2;
                this.reason_ = -1;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProto.StopReasonCountOrBuilder
            public boolean hasCount() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProto.StopReasonCountOrBuilder
            public int getCount() {
                return this.count_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCount(int value) {
                this.bitField0_ |= 2;
                this.count_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearCount() {
                this.bitField0_ &= -3;
                this.count_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeEnum(1, this.reason_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.count_);
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
                    size2 = 0 + CodedOutputStream.computeEnumSize(1, this.reason_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.count_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static StopReasonCount parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (StopReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static StopReasonCount parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (StopReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static StopReasonCount parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (StopReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static StopReasonCount parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (StopReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static StopReasonCount parseFrom(InputStream input) throws IOException {
                return (StopReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static StopReasonCount parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (StopReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static StopReasonCount parseDelimitedFrom(InputStream input) throws IOException {
                return (StopReasonCount) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static StopReasonCount parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (StopReasonCount) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static StopReasonCount parseFrom(CodedInputStream input) throws IOException {
                return (StopReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static StopReasonCount parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (StopReasonCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(StopReasonCount prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<StopReasonCount, Builder> implements StopReasonCountOrBuilder {
                private Builder() {
                    super(StopReasonCount.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.job.DataSetProto.PackageEntryProto.StopReasonCountOrBuilder
                public boolean hasReason() {
                    return ((StopReasonCount) this.instance).hasReason();
                }

                @Override // com.android.server.job.DataSetProto.PackageEntryProto.StopReasonCountOrBuilder
                public StopReasonEnum getReason() {
                    return ((StopReasonCount) this.instance).getReason();
                }

                public Builder setReason(StopReasonEnum value) {
                    copyOnWrite();
                    ((StopReasonCount) this.instance).setReason(value);
                    return this;
                }

                public Builder clearReason() {
                    copyOnWrite();
                    ((StopReasonCount) this.instance).clearReason();
                    return this;
                }

                @Override // com.android.server.job.DataSetProto.PackageEntryProto.StopReasonCountOrBuilder
                public boolean hasCount() {
                    return ((StopReasonCount) this.instance).hasCount();
                }

                @Override // com.android.server.job.DataSetProto.PackageEntryProto.StopReasonCountOrBuilder
                public int getCount() {
                    return ((StopReasonCount) this.instance).getCount();
                }

                public Builder setCount(int value) {
                    copyOnWrite();
                    ((StopReasonCount) this.instance).setCount(value);
                    return this;
                }

                public Builder clearCount() {
                    copyOnWrite();
                    ((StopReasonCount) this.instance).clearCount();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new StopReasonCount();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        StopReasonCount other = (StopReasonCount) arg1;
                        this.reason_ = visitor.visitInt(hasReason(), this.reason_, other.hasReason(), other.reason_);
                        this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
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
                                    int rawValue = input.readEnum();
                                    if (StopReasonEnum.forNumber(rawValue) == null) {
                                        super.mergeVarintField(1, rawValue);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.reason_ = rawValue;
                                    }
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.count_ = input.readInt32();
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
                            synchronized (StopReasonCount.class) {
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

            public static StopReasonCount getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<StopReasonCount> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 1;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -2;
            this.uid_ = 0;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageName(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackageName() {
            this.bitField0_ &= -3;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.packageName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean hasPendingState() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public State getPendingState() {
            State state = this.pendingState_;
            return state == null ? State.getDefaultInstance() : state;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPendingState(State value) {
            if (value != null) {
                this.pendingState_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPendingState(State.Builder builderForValue) {
            this.pendingState_ = (State) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergePendingState(State value) {
            State state = this.pendingState_;
            if (state == null || state == State.getDefaultInstance()) {
                this.pendingState_ = value;
            } else {
                this.pendingState_ = (State) ((State.Builder) State.newBuilder(this.pendingState_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPendingState() {
            this.pendingState_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean hasActiveState() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public State getActiveState() {
            State state = this.activeState_;
            return state == null ? State.getDefaultInstance() : state;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveState(State value) {
            if (value != null) {
                this.activeState_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveState(State.Builder builderForValue) {
            this.activeState_ = (State) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeActiveState(State value) {
            State state = this.activeState_;
            if (state == null || state == State.getDefaultInstance()) {
                this.activeState_ = value;
            } else {
                this.activeState_ = (State) ((State.Builder) State.newBuilder(this.activeState_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveState() {
            this.activeState_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean hasActiveTopState() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public State getActiveTopState() {
            State state = this.activeTopState_;
            return state == null ? State.getDefaultInstance() : state;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveTopState(State value) {
            if (value != null) {
                this.activeTopState_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveTopState(State.Builder builderForValue) {
            this.activeTopState_ = (State) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeActiveTopState(State value) {
            State state = this.activeTopState_;
            if (state == null || state == State.getDefaultInstance()) {
                this.activeTopState_ = value;
            } else {
                this.activeTopState_ = (State) ((State.Builder) State.newBuilder(this.activeTopState_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveTopState() {
            this.activeTopState_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean hasPending() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean getPending() {
            return this.pending_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPending(boolean value) {
            this.bitField0_ |= 32;
            this.pending_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPending() {
            this.bitField0_ &= -33;
            this.pending_ = false;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean hasActive() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean getActive() {
            return this.active_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActive(boolean value) {
            this.bitField0_ |= 64;
            this.active_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActive() {
            this.bitField0_ &= -65;
            this.active_ = false;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean hasActiveTop() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public boolean getActiveTop() {
            return this.activeTop_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveTop(boolean value) {
            this.bitField0_ |= 128;
            this.activeTop_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveTop() {
            this.bitField0_ &= -129;
            this.activeTop_ = false;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public List<StopReasonCount> getStopReasonsList() {
            return this.stopReasons_;
        }

        public List<? extends StopReasonCountOrBuilder> getStopReasonsOrBuilderList() {
            return this.stopReasons_;
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public int getStopReasonsCount() {
            return this.stopReasons_.size();
        }

        @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
        public StopReasonCount getStopReasons(int index) {
            return this.stopReasons_.get(index);
        }

        public StopReasonCountOrBuilder getStopReasonsOrBuilder(int index) {
            return this.stopReasons_.get(index);
        }

        private void ensureStopReasonsIsMutable() {
            if (!this.stopReasons_.isModifiable()) {
                this.stopReasons_ = GeneratedMessageLite.mutableCopy(this.stopReasons_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStopReasons(int index, StopReasonCount value) {
            if (value != null) {
                ensureStopReasonsIsMutable();
                this.stopReasons_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStopReasons(int index, StopReasonCount.Builder builderForValue) {
            ensureStopReasonsIsMutable();
            this.stopReasons_.set(index, (StopReasonCount) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStopReasons(StopReasonCount value) {
            if (value != null) {
                ensureStopReasonsIsMutable();
                this.stopReasons_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStopReasons(int index, StopReasonCount value) {
            if (value != null) {
                ensureStopReasonsIsMutable();
                this.stopReasons_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStopReasons(StopReasonCount.Builder builderForValue) {
            ensureStopReasonsIsMutable();
            this.stopReasons_.add((StopReasonCount) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStopReasons(int index, StopReasonCount.Builder builderForValue) {
            ensureStopReasonsIsMutable();
            this.stopReasons_.add(index, (StopReasonCount) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllStopReasons(Iterable<? extends StopReasonCount> values) {
            ensureStopReasonsIsMutable();
            AbstractMessageLite.addAll(values, this.stopReasons_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStopReasons() {
            this.stopReasons_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeStopReasons(int index) {
            ensureStopReasonsIsMutable();
            this.stopReasons_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getPackageName());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getPendingState());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getActiveState());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, getActiveTopState());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBool(6, this.pending_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeBool(7, this.active_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeBool(8, this.activeTop_);
            }
            for (int i = 0; i < this.stopReasons_.size(); i++) {
                output.writeMessage(9, this.stopReasons_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getPackageName());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getPendingState());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getActiveState());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, getActiveTopState());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBoolSize(6, this.pending_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeBoolSize(7, this.active_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeBoolSize(8, this.activeTop_);
            }
            for (int i = 0; i < this.stopReasons_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(9, this.stopReasons_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PackageEntryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PackageEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageEntryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageEntryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PackageEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageEntryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageEntryProto parseFrom(InputStream input) throws IOException {
            return (PackageEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageEntryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PackageEntryProto parseDelimitedFrom(InputStream input) throws IOException {
            return (PackageEntryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageEntryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageEntryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PackageEntryProto parseFrom(CodedInputStream input) throws IOException {
            return (PackageEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageEntryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PackageEntryProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PackageEntryProto, Builder> implements PackageEntryProtoOrBuilder {
            private Builder() {
                super(PackageEntryProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean hasUid() {
                return ((PackageEntryProto) this.instance).hasUid();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public int getUid() {
                return ((PackageEntryProto) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearUid();
                return this;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean hasPackageName() {
                return ((PackageEntryProto) this.instance).hasPackageName();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public String getPackageName() {
                return ((PackageEntryProto) this.instance).getPackageName();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public ByteString getPackageNameBytes() {
                return ((PackageEntryProto) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setPackageName(value);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setPackageNameBytes(value);
                return this;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean hasPendingState() {
                return ((PackageEntryProto) this.instance).hasPendingState();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public State getPendingState() {
                return ((PackageEntryProto) this.instance).getPendingState();
            }

            public Builder setPendingState(State value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setPendingState((PackageEntryProto) value);
                return this;
            }

            public Builder setPendingState(State.Builder builderForValue) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setPendingState((PackageEntryProto) builderForValue);
                return this;
            }

            public Builder mergePendingState(State value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).mergePendingState(value);
                return this;
            }

            public Builder clearPendingState() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearPendingState();
                return this;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean hasActiveState() {
                return ((PackageEntryProto) this.instance).hasActiveState();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public State getActiveState() {
                return ((PackageEntryProto) this.instance).getActiveState();
            }

            public Builder setActiveState(State value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setActiveState((PackageEntryProto) value);
                return this;
            }

            public Builder setActiveState(State.Builder builderForValue) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setActiveState((PackageEntryProto) builderForValue);
                return this;
            }

            public Builder mergeActiveState(State value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).mergeActiveState(value);
                return this;
            }

            public Builder clearActiveState() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearActiveState();
                return this;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean hasActiveTopState() {
                return ((PackageEntryProto) this.instance).hasActiveTopState();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public State getActiveTopState() {
                return ((PackageEntryProto) this.instance).getActiveTopState();
            }

            public Builder setActiveTopState(State value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setActiveTopState((PackageEntryProto) value);
                return this;
            }

            public Builder setActiveTopState(State.Builder builderForValue) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setActiveTopState((PackageEntryProto) builderForValue);
                return this;
            }

            public Builder mergeActiveTopState(State value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).mergeActiveTopState(value);
                return this;
            }

            public Builder clearActiveTopState() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearActiveTopState();
                return this;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean hasPending() {
                return ((PackageEntryProto) this.instance).hasPending();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean getPending() {
                return ((PackageEntryProto) this.instance).getPending();
            }

            public Builder setPending(boolean value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setPending(value);
                return this;
            }

            public Builder clearPending() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearPending();
                return this;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean hasActive() {
                return ((PackageEntryProto) this.instance).hasActive();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean getActive() {
                return ((PackageEntryProto) this.instance).getActive();
            }

            public Builder setActive(boolean value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setActive(value);
                return this;
            }

            public Builder clearActive() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearActive();
                return this;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean hasActiveTop() {
                return ((PackageEntryProto) this.instance).hasActiveTop();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public boolean getActiveTop() {
                return ((PackageEntryProto) this.instance).getActiveTop();
            }

            public Builder setActiveTop(boolean value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setActiveTop(value);
                return this;
            }

            public Builder clearActiveTop() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearActiveTop();
                return this;
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public List<StopReasonCount> getStopReasonsList() {
                return Collections.unmodifiableList(((PackageEntryProto) this.instance).getStopReasonsList());
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public int getStopReasonsCount() {
                return ((PackageEntryProto) this.instance).getStopReasonsCount();
            }

            @Override // com.android.server.job.DataSetProto.PackageEntryProtoOrBuilder
            public StopReasonCount getStopReasons(int index) {
                return ((PackageEntryProto) this.instance).getStopReasons(index);
            }

            public Builder setStopReasons(int index, StopReasonCount value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setStopReasons((PackageEntryProto) index, (int) value);
                return this;
            }

            public Builder setStopReasons(int index, StopReasonCount.Builder builderForValue) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).setStopReasons((PackageEntryProto) index, (int) builderForValue);
                return this;
            }

            public Builder addStopReasons(StopReasonCount value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).addStopReasons((PackageEntryProto) value);
                return this;
            }

            public Builder addStopReasons(int index, StopReasonCount value) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).addStopReasons((PackageEntryProto) index, (int) value);
                return this;
            }

            public Builder addStopReasons(StopReasonCount.Builder builderForValue) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).addStopReasons((PackageEntryProto) builderForValue);
                return this;
            }

            public Builder addStopReasons(int index, StopReasonCount.Builder builderForValue) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).addStopReasons((PackageEntryProto) index, (int) builderForValue);
                return this;
            }

            public Builder addAllStopReasons(Iterable<? extends StopReasonCount> values) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).addAllStopReasons(values);
                return this;
            }

            public Builder clearStopReasons() {
                copyOnWrite();
                ((PackageEntryProto) this.instance).clearStopReasons();
                return this;
            }

            public Builder removeStopReasons(int index) {
                copyOnWrite();
                ((PackageEntryProto) this.instance).removeStopReasons(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PackageEntryProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.stopReasons_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PackageEntryProto other = (PackageEntryProto) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                    this.pendingState_ = (State) visitor.visitMessage(this.pendingState_, other.pendingState_);
                    this.activeState_ = (State) visitor.visitMessage(this.activeState_, other.activeState_);
                    this.activeTopState_ = (State) visitor.visitMessage(this.activeTopState_, other.activeTopState_);
                    this.pending_ = visitor.visitBoolean(hasPending(), this.pending_, other.hasPending(), other.pending_);
                    this.active_ = visitor.visitBoolean(hasActive(), this.active_, other.hasActive(), other.active_);
                    this.activeTop_ = visitor.visitBoolean(hasActiveTop(), this.activeTop_, other.hasActiveTop(), other.activeTop_);
                    this.stopReasons_ = visitor.visitList(this.stopReasons_, other.stopReasons_);
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
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.uid_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.packageName_ = s;
                            } else if (tag == 26) {
                                State.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (State.Builder) this.pendingState_.toBuilder();
                                }
                                this.pendingState_ = (State) input.readMessage(State.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.pendingState_);
                                    this.pendingState_ = (State) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                State.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder2 = (State.Builder) this.activeState_.toBuilder();
                                }
                                this.activeState_ = (State) input.readMessage(State.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.activeState_);
                                    this.activeState_ = (State) subBuilder2.buildPartial();
                                }
                                this.bitField0_ = 8 | this.bitField0_;
                            } else if (tag == 42) {
                                State.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder3 = (State.Builder) this.activeTopState_.toBuilder();
                                }
                                this.activeTopState_ = (State) input.readMessage(State.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.activeTopState_);
                                    this.activeTopState_ = (State) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 16;
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.pending_ = input.readBool();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.active_ = input.readBool();
                            } else if (tag == 64) {
                                this.bitField0_ |= 128;
                                this.activeTop_ = input.readBool();
                            } else if (tag == 74) {
                                if (!this.stopReasons_.isModifiable()) {
                                    this.stopReasons_ = GeneratedMessageLite.mutableCopy(this.stopReasons_);
                                }
                                this.stopReasons_.add((StopReasonCount) input.readMessage(StopReasonCount.parser(), extensionRegistry));
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
                        synchronized (PackageEntryProto.class) {
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

        public static PackageEntryProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PackageEntryProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public boolean hasStartClockTimeMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public long getStartClockTimeMs() {
        return this.startClockTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartClockTimeMs(long value) {
        this.bitField0_ |= 1;
        this.startClockTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartClockTimeMs() {
        this.bitField0_ &= -2;
        this.startClockTimeMs_ = 0;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public boolean hasElapsedTimeMs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public long getElapsedTimeMs() {
        return this.elapsedTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setElapsedTimeMs(long value) {
        this.bitField0_ |= 2;
        this.elapsedTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearElapsedTimeMs() {
        this.bitField0_ &= -3;
        this.elapsedTimeMs_ = 0;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public boolean hasPeriodMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public long getPeriodMs() {
        return this.periodMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPeriodMs(long value) {
        this.bitField0_ |= 4;
        this.periodMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPeriodMs() {
        this.bitField0_ &= -5;
        this.periodMs_ = 0;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public List<PackageEntryProto> getPackageEntriesList() {
        return this.packageEntries_;
    }

    public List<? extends PackageEntryProtoOrBuilder> getPackageEntriesOrBuilderList() {
        return this.packageEntries_;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public int getPackageEntriesCount() {
        return this.packageEntries_.size();
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public PackageEntryProto getPackageEntries(int index) {
        return this.packageEntries_.get(index);
    }

    public PackageEntryProtoOrBuilder getPackageEntriesOrBuilder(int index) {
        return this.packageEntries_.get(index);
    }

    private void ensurePackageEntriesIsMutable() {
        if (!this.packageEntries_.isModifiable()) {
            this.packageEntries_ = GeneratedMessageLite.mutableCopy(this.packageEntries_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageEntries(int index, PackageEntryProto value) {
        if (value != null) {
            ensurePackageEntriesIsMutable();
            this.packageEntries_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageEntries(int index, PackageEntryProto.Builder builderForValue) {
        ensurePackageEntriesIsMutable();
        this.packageEntries_.set(index, (PackageEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageEntries(PackageEntryProto value) {
        if (value != null) {
            ensurePackageEntriesIsMutable();
            this.packageEntries_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageEntries(int index, PackageEntryProto value) {
        if (value != null) {
            ensurePackageEntriesIsMutable();
            this.packageEntries_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageEntries(PackageEntryProto.Builder builderForValue) {
        ensurePackageEntriesIsMutable();
        this.packageEntries_.add((PackageEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageEntries(int index, PackageEntryProto.Builder builderForValue) {
        ensurePackageEntriesIsMutable();
        this.packageEntries_.add(index, (PackageEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPackageEntries(Iterable<? extends PackageEntryProto> values) {
        ensurePackageEntriesIsMutable();
        AbstractMessageLite.addAll(values, this.packageEntries_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageEntries() {
        this.packageEntries_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePackageEntries(int index) {
        ensurePackageEntriesIsMutable();
        this.packageEntries_.remove(index);
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public boolean hasMaxConcurrency() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public int getMaxConcurrency() {
        return this.maxConcurrency_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxConcurrency(int value) {
        this.bitField0_ |= 8;
        this.maxConcurrency_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxConcurrency() {
        this.bitField0_ &= -9;
        this.maxConcurrency_ = 0;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public boolean hasMaxForegroundConcurrency() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.job.DataSetProtoOrBuilder
    public int getMaxForegroundConcurrency() {
        return this.maxForegroundConcurrency_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxForegroundConcurrency(int value) {
        this.bitField0_ |= 16;
        this.maxForegroundConcurrency_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxForegroundConcurrency() {
        this.bitField0_ &= -17;
        this.maxForegroundConcurrency_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.startClockTimeMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.elapsedTimeMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.periodMs_);
        }
        for (int i = 0; i < this.packageEntries_.size(); i++) {
            output.writeMessage(4, this.packageEntries_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(5, this.maxConcurrency_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(6, this.maxForegroundConcurrency_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.startClockTimeMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.elapsedTimeMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.periodMs_);
        }
        for (int i = 0; i < this.packageEntries_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.packageEntries_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(5, this.maxConcurrency_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(6, this.maxForegroundConcurrency_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DataSetProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DataSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DataSetProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DataSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DataSetProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DataSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DataSetProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DataSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DataSetProto parseFrom(InputStream input) throws IOException {
        return (DataSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DataSetProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DataSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DataSetProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DataSetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DataSetProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DataSetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DataSetProto parseFrom(CodedInputStream input) throws IOException {
        return (DataSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DataSetProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DataSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DataSetProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DataSetProto, Builder> implements DataSetProtoOrBuilder {
        private Builder() {
            super(DataSetProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public boolean hasStartClockTimeMs() {
            return ((DataSetProto) this.instance).hasStartClockTimeMs();
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public long getStartClockTimeMs() {
            return ((DataSetProto) this.instance).getStartClockTimeMs();
        }

        public Builder setStartClockTimeMs(long value) {
            copyOnWrite();
            ((DataSetProto) this.instance).setStartClockTimeMs(value);
            return this;
        }

        public Builder clearStartClockTimeMs() {
            copyOnWrite();
            ((DataSetProto) this.instance).clearStartClockTimeMs();
            return this;
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public boolean hasElapsedTimeMs() {
            return ((DataSetProto) this.instance).hasElapsedTimeMs();
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public long getElapsedTimeMs() {
            return ((DataSetProto) this.instance).getElapsedTimeMs();
        }

        public Builder setElapsedTimeMs(long value) {
            copyOnWrite();
            ((DataSetProto) this.instance).setElapsedTimeMs(value);
            return this;
        }

        public Builder clearElapsedTimeMs() {
            copyOnWrite();
            ((DataSetProto) this.instance).clearElapsedTimeMs();
            return this;
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public boolean hasPeriodMs() {
            return ((DataSetProto) this.instance).hasPeriodMs();
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public long getPeriodMs() {
            return ((DataSetProto) this.instance).getPeriodMs();
        }

        public Builder setPeriodMs(long value) {
            copyOnWrite();
            ((DataSetProto) this.instance).setPeriodMs(value);
            return this;
        }

        public Builder clearPeriodMs() {
            copyOnWrite();
            ((DataSetProto) this.instance).clearPeriodMs();
            return this;
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public List<PackageEntryProto> getPackageEntriesList() {
            return Collections.unmodifiableList(((DataSetProto) this.instance).getPackageEntriesList());
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public int getPackageEntriesCount() {
            return ((DataSetProto) this.instance).getPackageEntriesCount();
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public PackageEntryProto getPackageEntries(int index) {
            return ((DataSetProto) this.instance).getPackageEntries(index);
        }

        public Builder setPackageEntries(int index, PackageEntryProto value) {
            copyOnWrite();
            ((DataSetProto) this.instance).setPackageEntries((DataSetProto) index, (int) value);
            return this;
        }

        public Builder setPackageEntries(int index, PackageEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((DataSetProto) this.instance).setPackageEntries((DataSetProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPackageEntries(PackageEntryProto value) {
            copyOnWrite();
            ((DataSetProto) this.instance).addPackageEntries((DataSetProto) value);
            return this;
        }

        public Builder addPackageEntries(int index, PackageEntryProto value) {
            copyOnWrite();
            ((DataSetProto) this.instance).addPackageEntries((DataSetProto) index, (int) value);
            return this;
        }

        public Builder addPackageEntries(PackageEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((DataSetProto) this.instance).addPackageEntries((DataSetProto) builderForValue);
            return this;
        }

        public Builder addPackageEntries(int index, PackageEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((DataSetProto) this.instance).addPackageEntries((DataSetProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPackageEntries(Iterable<? extends PackageEntryProto> values) {
            copyOnWrite();
            ((DataSetProto) this.instance).addAllPackageEntries(values);
            return this;
        }

        public Builder clearPackageEntries() {
            copyOnWrite();
            ((DataSetProto) this.instance).clearPackageEntries();
            return this;
        }

        public Builder removePackageEntries(int index) {
            copyOnWrite();
            ((DataSetProto) this.instance).removePackageEntries(index);
            return this;
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public boolean hasMaxConcurrency() {
            return ((DataSetProto) this.instance).hasMaxConcurrency();
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public int getMaxConcurrency() {
            return ((DataSetProto) this.instance).getMaxConcurrency();
        }

        public Builder setMaxConcurrency(int value) {
            copyOnWrite();
            ((DataSetProto) this.instance).setMaxConcurrency(value);
            return this;
        }

        public Builder clearMaxConcurrency() {
            copyOnWrite();
            ((DataSetProto) this.instance).clearMaxConcurrency();
            return this;
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public boolean hasMaxForegroundConcurrency() {
            return ((DataSetProto) this.instance).hasMaxForegroundConcurrency();
        }

        @Override // com.android.server.job.DataSetProtoOrBuilder
        public int getMaxForegroundConcurrency() {
            return ((DataSetProto) this.instance).getMaxForegroundConcurrency();
        }

        public Builder setMaxForegroundConcurrency(int value) {
            copyOnWrite();
            ((DataSetProto) this.instance).setMaxForegroundConcurrency(value);
            return this;
        }

        public Builder clearMaxForegroundConcurrency() {
            copyOnWrite();
            ((DataSetProto) this.instance).clearMaxForegroundConcurrency();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DataSetProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.packageEntries_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DataSetProto other = (DataSetProto) arg1;
                this.startClockTimeMs_ = visitor.visitLong(hasStartClockTimeMs(), this.startClockTimeMs_, other.hasStartClockTimeMs(), other.startClockTimeMs_);
                this.elapsedTimeMs_ = visitor.visitLong(hasElapsedTimeMs(), this.elapsedTimeMs_, other.hasElapsedTimeMs(), other.elapsedTimeMs_);
                this.periodMs_ = visitor.visitLong(hasPeriodMs(), this.periodMs_, other.hasPeriodMs(), other.periodMs_);
                this.packageEntries_ = visitor.visitList(this.packageEntries_, other.packageEntries_);
                this.maxConcurrency_ = visitor.visitInt(hasMaxConcurrency(), this.maxConcurrency_, other.hasMaxConcurrency(), other.maxConcurrency_);
                this.maxForegroundConcurrency_ = visitor.visitInt(hasMaxForegroundConcurrency(), this.maxForegroundConcurrency_, other.hasMaxForegroundConcurrency(), other.maxForegroundConcurrency_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.startClockTimeMs_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.elapsedTimeMs_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.periodMs_ = input.readInt64();
                        } else if (tag == 34) {
                            if (!this.packageEntries_.isModifiable()) {
                                this.packageEntries_ = GeneratedMessageLite.mutableCopy(this.packageEntries_);
                            }
                            this.packageEntries_.add((PackageEntryProto) input.readMessage(PackageEntryProto.parser(), extensionRegistry));
                        } else if (tag == 40) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.maxConcurrency_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 16;
                            this.maxForegroundConcurrency_ = input.readInt32();
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
                    synchronized (DataSetProto.class) {
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

    public static DataSetProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DataSetProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
