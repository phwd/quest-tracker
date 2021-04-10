package android.service.procstats;

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

public final class PackageServiceOperationStatsProto extends GeneratedMessageLite<PackageServiceOperationStatsProto, Builder> implements PackageServiceOperationStatsProtoOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 2;
    private static final PackageServiceOperationStatsProto DEFAULT_INSTANCE = new PackageServiceOperationStatsProto();
    public static final int OPERATION_FIELD_NUMBER = 1;
    private static volatile Parser<PackageServiceOperationStatsProto> PARSER = null;
    public static final int STATE_STATS_FIELD_NUMBER = 3;
    private int bitField0_;
    private int count_ = 0;
    private int operation_ = 0;
    private Internal.ProtobufList<StateStats> stateStats_ = emptyProtobufList();

    public interface StateStatsOrBuilder extends MessageLiteOrBuilder {
        long getDurationMs();

        MemoryState getMemoryState();

        long getRealtimeDurationMs();

        ScreenState getScreenState();

        boolean hasDurationMs();

        boolean hasMemoryState();

        boolean hasRealtimeDurationMs();

        boolean hasScreenState();
    }

    private PackageServiceOperationStatsProto() {
    }

    public static final class StateStats extends GeneratedMessageLite<StateStats, Builder> implements StateStatsOrBuilder {
        private static final StateStats DEFAULT_INSTANCE = new StateStats();
        public static final int DURATION_MS_FIELD_NUMBER = 3;
        public static final int MEMORY_STATE_FIELD_NUMBER = 2;
        private static volatile Parser<StateStats> PARSER = null;
        public static final int REALTIME_DURATION_MS_FIELD_NUMBER = 4;
        public static final int SCREEN_STATE_FIELD_NUMBER = 1;
        private int bitField0_;
        private long durationMs_ = 0;
        private int memoryState_ = 0;
        private long realtimeDurationMs_ = 0;
        private int screenState_ = 0;

        private StateStats() {
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
        public boolean hasScreenState() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
        public ScreenState getScreenState() {
            ScreenState result = ScreenState.forNumber(this.screenState_);
            return result == null ? ScreenState.SCREEN_STATE_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setScreenState(ScreenState value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.screenState_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearScreenState() {
            this.bitField0_ &= -2;
            this.screenState_ = 0;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
        public boolean hasMemoryState() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
        public MemoryState getMemoryState() {
            MemoryState result = MemoryState.forNumber(this.memoryState_);
            return result == null ? MemoryState.MEMORY_STATE_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMemoryState(MemoryState value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.memoryState_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMemoryState() {
            this.bitField0_ &= -3;
            this.memoryState_ = 0;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
        public boolean hasDurationMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
        public long getDurationMs() {
            return this.durationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMs(long value) {
            this.bitField0_ |= 4;
            this.durationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMs() {
            this.bitField0_ &= -5;
            this.durationMs_ = 0;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
        public boolean hasRealtimeDurationMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
        public long getRealtimeDurationMs() {
            return this.realtimeDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRealtimeDurationMs(long value) {
            this.bitField0_ |= 8;
            this.realtimeDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRealtimeDurationMs() {
            this.bitField0_ &= -9;
            this.realtimeDurationMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.screenState_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.memoryState_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.durationMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.realtimeDurationMs_);
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.screenState_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.memoryState_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.durationMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.realtimeDurationMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static StateStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StateStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StateStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StateStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StateStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StateStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StateStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StateStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StateStats parseFrom(InputStream input) throws IOException {
            return (StateStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StateStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StateStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StateStats parseDelimitedFrom(InputStream input) throws IOException {
            return (StateStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StateStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StateStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StateStats parseFrom(CodedInputStream input) throws IOException {
            return (StateStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StateStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StateStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StateStats prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StateStats, Builder> implements StateStatsOrBuilder {
            private Builder() {
                super(StateStats.DEFAULT_INSTANCE);
            }

            @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
            public boolean hasScreenState() {
                return ((StateStats) this.instance).hasScreenState();
            }

            @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
            public ScreenState getScreenState() {
                return ((StateStats) this.instance).getScreenState();
            }

            public Builder setScreenState(ScreenState value) {
                copyOnWrite();
                ((StateStats) this.instance).setScreenState(value);
                return this;
            }

            public Builder clearScreenState() {
                copyOnWrite();
                ((StateStats) this.instance).clearScreenState();
                return this;
            }

            @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
            public boolean hasMemoryState() {
                return ((StateStats) this.instance).hasMemoryState();
            }

            @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
            public MemoryState getMemoryState() {
                return ((StateStats) this.instance).getMemoryState();
            }

            public Builder setMemoryState(MemoryState value) {
                copyOnWrite();
                ((StateStats) this.instance).setMemoryState(value);
                return this;
            }

            public Builder clearMemoryState() {
                copyOnWrite();
                ((StateStats) this.instance).clearMemoryState();
                return this;
            }

            @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
            public boolean hasDurationMs() {
                return ((StateStats) this.instance).hasDurationMs();
            }

            @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
            public long getDurationMs() {
                return ((StateStats) this.instance).getDurationMs();
            }

            public Builder setDurationMs(long value) {
                copyOnWrite();
                ((StateStats) this.instance).setDurationMs(value);
                return this;
            }

            public Builder clearDurationMs() {
                copyOnWrite();
                ((StateStats) this.instance).clearDurationMs();
                return this;
            }

            @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
            public boolean hasRealtimeDurationMs() {
                return ((StateStats) this.instance).hasRealtimeDurationMs();
            }

            @Override // android.service.procstats.PackageServiceOperationStatsProto.StateStatsOrBuilder
            public long getRealtimeDurationMs() {
                return ((StateStats) this.instance).getRealtimeDurationMs();
            }

            public Builder setRealtimeDurationMs(long value) {
                copyOnWrite();
                ((StateStats) this.instance).setRealtimeDurationMs(value);
                return this;
            }

            public Builder clearRealtimeDurationMs() {
                copyOnWrite();
                ((StateStats) this.instance).clearRealtimeDurationMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StateStats();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StateStats other = (StateStats) arg1;
                    this.screenState_ = visitor.visitInt(hasScreenState(), this.screenState_, other.hasScreenState(), other.screenState_);
                    this.memoryState_ = visitor.visitInt(hasMemoryState(), this.memoryState_, other.hasMemoryState(), other.memoryState_);
                    this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
                    this.realtimeDurationMs_ = visitor.visitLong(hasRealtimeDurationMs(), this.realtimeDurationMs_, other.hasRealtimeDurationMs(), other.realtimeDurationMs_);
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
                                if (ScreenState.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.screenState_ = rawValue;
                                }
                            } else if (tag == 16) {
                                int rawValue2 = input.readEnum();
                                if (MemoryState.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(2, rawValue2);
                                } else {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.memoryState_ = rawValue2;
                                }
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.durationMs_ = input.readInt64();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.realtimeDurationMs_ = input.readInt64();
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
                        synchronized (StateStats.class) {
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

        public static StateStats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StateStats> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
    public boolean hasOperation() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
    public ServiceOperationState getOperation() {
        ServiceOperationState result = ServiceOperationState.forNumber(this.operation_);
        return result == null ? ServiceOperationState.SERVICE_OPERATION_STATE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOperation(ServiceOperationState value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.operation_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOperation() {
        this.bitField0_ &= -2;
        this.operation_ = 0;
    }

    @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
    public boolean hasCount() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
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

    @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
    public List<StateStats> getStateStatsList() {
        return this.stateStats_;
    }

    public List<? extends StateStatsOrBuilder> getStateStatsOrBuilderList() {
        return this.stateStats_;
    }

    @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
    public int getStateStatsCount() {
        return this.stateStats_.size();
    }

    @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
    public StateStats getStateStats(int index) {
        return this.stateStats_.get(index);
    }

    public StateStatsOrBuilder getStateStatsOrBuilder(int index) {
        return this.stateStats_.get(index);
    }

    private void ensureStateStatsIsMutable() {
        if (!this.stateStats_.isModifiable()) {
            this.stateStats_ = GeneratedMessageLite.mutableCopy(this.stateStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStateStats(int index, StateStats value) {
        if (value != null) {
            ensureStateStatsIsMutable();
            this.stateStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStateStats(int index, StateStats.Builder builderForValue) {
        ensureStateStatsIsMutable();
        this.stateStats_.set(index, (StateStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStateStats(StateStats value) {
        if (value != null) {
            ensureStateStatsIsMutable();
            this.stateStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStateStats(int index, StateStats value) {
        if (value != null) {
            ensureStateStatsIsMutable();
            this.stateStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStateStats(StateStats.Builder builderForValue) {
        ensureStateStatsIsMutable();
        this.stateStats_.add((StateStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStateStats(int index, StateStats.Builder builderForValue) {
        ensureStateStatsIsMutable();
        this.stateStats_.add(index, (StateStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStateStats(Iterable<? extends StateStats> values) {
        ensureStateStatsIsMutable();
        AbstractMessageLite.addAll(values, this.stateStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStateStats() {
        this.stateStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeStateStats(int index) {
        ensureStateStatsIsMutable();
        this.stateStats_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.operation_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.count_);
        }
        for (int i = 0; i < this.stateStats_.size(); i++) {
            output.writeMessage(3, this.stateStats_.get(i));
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.operation_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.count_);
        }
        for (int i = 0; i < this.stateStats_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.stateStats_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PackageServiceOperationStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PackageServiceOperationStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageServiceOperationStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageServiceOperationStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageServiceOperationStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PackageServiceOperationStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageServiceOperationStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageServiceOperationStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageServiceOperationStatsProto parseFrom(InputStream input) throws IOException {
        return (PackageServiceOperationStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceOperationStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceOperationStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageServiceOperationStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PackageServiceOperationStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceOperationStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceOperationStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageServiceOperationStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (PackageServiceOperationStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceOperationStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceOperationStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PackageServiceOperationStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PackageServiceOperationStatsProto, Builder> implements PackageServiceOperationStatsProtoOrBuilder {
        private Builder() {
            super(PackageServiceOperationStatsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
        public boolean hasOperation() {
            return ((PackageServiceOperationStatsProto) this.instance).hasOperation();
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
        public ServiceOperationState getOperation() {
            return ((PackageServiceOperationStatsProto) this.instance).getOperation();
        }

        public Builder setOperation(ServiceOperationState value) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).setOperation(value);
            return this;
        }

        public Builder clearOperation() {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).clearOperation();
            return this;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
        public boolean hasCount() {
            return ((PackageServiceOperationStatsProto) this.instance).hasCount();
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
        public int getCount() {
            return ((PackageServiceOperationStatsProto) this.instance).getCount();
        }

        public Builder setCount(int value) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).setCount(value);
            return this;
        }

        public Builder clearCount() {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).clearCount();
            return this;
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
        public List<StateStats> getStateStatsList() {
            return Collections.unmodifiableList(((PackageServiceOperationStatsProto) this.instance).getStateStatsList());
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
        public int getStateStatsCount() {
            return ((PackageServiceOperationStatsProto) this.instance).getStateStatsCount();
        }

        @Override // android.service.procstats.PackageServiceOperationStatsProtoOrBuilder
        public StateStats getStateStats(int index) {
            return ((PackageServiceOperationStatsProto) this.instance).getStateStats(index);
        }

        public Builder setStateStats(int index, StateStats value) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).setStateStats((PackageServiceOperationStatsProto) index, (int) value);
            return this;
        }

        public Builder setStateStats(int index, StateStats.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).setStateStats((PackageServiceOperationStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStateStats(StateStats value) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).addStateStats((PackageServiceOperationStatsProto) value);
            return this;
        }

        public Builder addStateStats(int index, StateStats value) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).addStateStats((PackageServiceOperationStatsProto) index, (int) value);
            return this;
        }

        public Builder addStateStats(StateStats.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).addStateStats((PackageServiceOperationStatsProto) builderForValue);
            return this;
        }

        public Builder addStateStats(int index, StateStats.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).addStateStats((PackageServiceOperationStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStateStats(Iterable<? extends StateStats> values) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).addAllStateStats(values);
            return this;
        }

        public Builder clearStateStats() {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).clearStateStats();
            return this;
        }

        public Builder removeStateStats(int index) {
            copyOnWrite();
            ((PackageServiceOperationStatsProto) this.instance).removeStateStats(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PackageServiceOperationStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.stateStats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PackageServiceOperationStatsProto other = (PackageServiceOperationStatsProto) arg1;
                this.operation_ = visitor.visitInt(hasOperation(), this.operation_, other.hasOperation(), other.operation_);
                this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
                this.stateStats_ = visitor.visitList(this.stateStats_, other.stateStats_);
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
                            int rawValue = input.readEnum();
                            if (ServiceOperationState.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.operation_ = rawValue;
                            }
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.count_ = input.readInt32();
                        } else if (tag == 26) {
                            if (!this.stateStats_.isModifiable()) {
                                this.stateStats_ = GeneratedMessageLite.mutableCopy(this.stateStats_);
                            }
                            this.stateStats_.add((StateStats) input.readMessage(StateStats.parser(), extensionRegistry));
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
                    synchronized (PackageServiceOperationStatsProto.class) {
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

    public static PackageServiceOperationStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PackageServiceOperationStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
