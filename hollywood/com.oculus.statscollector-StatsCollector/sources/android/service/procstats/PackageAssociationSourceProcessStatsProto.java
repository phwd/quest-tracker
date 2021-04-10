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

public final class PackageAssociationSourceProcessStatsProto extends GeneratedMessageLite<PackageAssociationSourceProcessStatsProto, Builder> implements PackageAssociationSourceProcessStatsProtoOrBuilder {
    public static final int ACTIVE_COUNT_FIELD_NUMBER = 5;
    public static final int ACTIVE_STATE_STATS_FIELD_NUMBER = 6;
    private static final PackageAssociationSourceProcessStatsProto DEFAULT_INSTANCE = new PackageAssociationSourceProcessStatsProto();
    public static final int PACKAGE_NAME_FIELD_NUMBER = 7;
    private static volatile Parser<PackageAssociationSourceProcessStatsProto> PARSER = null;
    public static final int PROCESS_NAME_FIELD_NUMBER = 2;
    public static final int PROCESS_UID_FIELD_NUMBER = 1;
    public static final int TOTAL_COUNT_FIELD_NUMBER = 3;
    public static final int TOTAL_DURATION_MS_FIELD_NUMBER = 4;
    private int activeCount_ = 0;
    private Internal.ProtobufList<StateStats> activeStateStats_ = emptyProtobufList();
    private int bitField0_;
    private String packageName_ = "";
    private String processName_ = "";
    private int processUid_ = 0;
    private int totalCount_ = 0;
    private long totalDurationMs_ = 0;

    public interface StateStatsOrBuilder extends MessageLiteOrBuilder {
        long getDurationMs();

        ProcessState getProcessState();

        long getRealtimeDurationMs();

        boolean hasDurationMs();

        boolean hasProcessState();

        boolean hasRealtimeDurationMs();
    }

    private PackageAssociationSourceProcessStatsProto() {
    }

    public static final class StateStats extends GeneratedMessageLite<StateStats, Builder> implements StateStatsOrBuilder {
        private static final StateStats DEFAULT_INSTANCE = new StateStats();
        public static final int DURATION_MS_FIELD_NUMBER = 2;
        private static volatile Parser<StateStats> PARSER = null;
        public static final int PROCESS_STATE_FIELD_NUMBER = 1;
        public static final int REALTIME_DURATION_MS_FIELD_NUMBER = 3;
        private int bitField0_;
        private long durationMs_ = 0;
        private int processState_ = 0;
        private long realtimeDurationMs_ = 0;

        private StateStats() {
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
        public boolean hasProcessState() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
        public ProcessState getProcessState() {
            ProcessState result = ProcessState.forNumber(this.processState_);
            return result == null ? ProcessState.PROCESS_STATE_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessState(ProcessState value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.processState_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcessState() {
            this.bitField0_ &= -2;
            this.processState_ = 0;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
        public boolean hasDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
        public long getDurationMs() {
            return this.durationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMs(long value) {
            this.bitField0_ |= 2;
            this.durationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMs() {
            this.bitField0_ &= -3;
            this.durationMs_ = 0;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
        public boolean hasRealtimeDurationMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
        public long getRealtimeDurationMs() {
            return this.realtimeDurationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRealtimeDurationMs(long value) {
            this.bitField0_ |= 4;
            this.realtimeDurationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRealtimeDurationMs() {
            this.bitField0_ &= -5;
            this.realtimeDurationMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.processState_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.durationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.realtimeDurationMs_);
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.processState_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.durationMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.realtimeDurationMs_);
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

            @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
            public boolean hasProcessState() {
                return ((StateStats) this.instance).hasProcessState();
            }

            @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
            public ProcessState getProcessState() {
                return ((StateStats) this.instance).getProcessState();
            }

            public Builder setProcessState(ProcessState value) {
                copyOnWrite();
                ((StateStats) this.instance).setProcessState(value);
                return this;
            }

            public Builder clearProcessState() {
                copyOnWrite();
                ((StateStats) this.instance).clearProcessState();
                return this;
            }

            @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
            public boolean hasDurationMs() {
                return ((StateStats) this.instance).hasDurationMs();
            }

            @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
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

            @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
            public boolean hasRealtimeDurationMs() {
                return ((StateStats) this.instance).hasRealtimeDurationMs();
            }

            @Override // android.service.procstats.PackageAssociationSourceProcessStatsProto.StateStatsOrBuilder
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
                    this.processState_ = visitor.visitInt(hasProcessState(), this.processState_, other.hasProcessState(), other.processState_);
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
                                if (ProcessState.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.processState_ = rawValue;
                                }
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.durationMs_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
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

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public boolean hasProcessUid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public int getProcessUid() {
        return this.processUid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessUid(int value) {
        this.bitField0_ |= 1;
        this.processUid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessUid() {
        this.bitField0_ &= -2;
        this.processUid_ = 0;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public boolean hasProcessName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public String getProcessName() {
        return this.processName_;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public ByteString getProcessNameBytes() {
        return ByteString.copyFromUtf8(this.processName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.processName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessName() {
        this.bitField0_ &= -3;
        this.processName_ = getDefaultInstance().getProcessName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.processName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public boolean hasPackageName() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public String getPackageName() {
        return this.packageName_;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public ByteString getPackageNameBytes() {
        return ByteString.copyFromUtf8(this.packageName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageName(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.packageName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageName() {
        this.bitField0_ &= -5;
        this.packageName_ = getDefaultInstance().getPackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.packageName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public boolean hasTotalCount() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public int getTotalCount() {
        return this.totalCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalCount(int value) {
        this.bitField0_ |= 8;
        this.totalCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalCount() {
        this.bitField0_ &= -9;
        this.totalCount_ = 0;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public boolean hasTotalDurationMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public long getTotalDurationMs() {
        return this.totalDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalDurationMs(long value) {
        this.bitField0_ |= 16;
        this.totalDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalDurationMs() {
        this.bitField0_ &= -17;
        this.totalDurationMs_ = 0;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public boolean hasActiveCount() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public int getActiveCount() {
        return this.activeCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveCount(int value) {
        this.bitField0_ |= 32;
        this.activeCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveCount() {
        this.bitField0_ &= -33;
        this.activeCount_ = 0;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public List<StateStats> getActiveStateStatsList() {
        return this.activeStateStats_;
    }

    public List<? extends StateStatsOrBuilder> getActiveStateStatsOrBuilderList() {
        return this.activeStateStats_;
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public int getActiveStateStatsCount() {
        return this.activeStateStats_.size();
    }

    @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
    public StateStats getActiveStateStats(int index) {
        return this.activeStateStats_.get(index);
    }

    public StateStatsOrBuilder getActiveStateStatsOrBuilder(int index) {
        return this.activeStateStats_.get(index);
    }

    private void ensureActiveStateStatsIsMutable() {
        if (!this.activeStateStats_.isModifiable()) {
            this.activeStateStats_ = GeneratedMessageLite.mutableCopy(this.activeStateStats_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveStateStats(int index, StateStats value) {
        if (value != null) {
            ensureActiveStateStatsIsMutable();
            this.activeStateStats_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveStateStats(int index, StateStats.Builder builderForValue) {
        ensureActiveStateStatsIsMutable();
        this.activeStateStats_.set(index, (StateStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveStateStats(StateStats value) {
        if (value != null) {
            ensureActiveStateStatsIsMutable();
            this.activeStateStats_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveStateStats(int index, StateStats value) {
        if (value != null) {
            ensureActiveStateStatsIsMutable();
            this.activeStateStats_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveStateStats(StateStats.Builder builderForValue) {
        ensureActiveStateStatsIsMutable();
        this.activeStateStats_.add((StateStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveStateStats(int index, StateStats.Builder builderForValue) {
        ensureActiveStateStatsIsMutable();
        this.activeStateStats_.add(index, (StateStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActiveStateStats(Iterable<? extends StateStats> values) {
        ensureActiveStateStatsIsMutable();
        AbstractMessageLite.addAll(values, this.activeStateStats_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveStateStats() {
        this.activeStateStats_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActiveStateStats(int index) {
        ensureActiveStateStatsIsMutable();
        this.activeStateStats_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.processUid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getProcessName());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(3, this.totalCount_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(4, this.totalDurationMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(5, this.activeCount_);
        }
        for (int i = 0; i < this.activeStateStats_.size(); i++) {
            output.writeMessage(6, this.activeStateStats_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(7, getPackageName());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.processUid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getProcessName());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(3, this.totalCount_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(4, this.totalDurationMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(5, this.activeCount_);
        }
        for (int i = 0; i < this.activeStateStats_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.activeStateStats_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(7, getPackageName());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PackageAssociationSourceProcessStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PackageAssociationSourceProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageAssociationSourceProcessStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageAssociationSourceProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageAssociationSourceProcessStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PackageAssociationSourceProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageAssociationSourceProcessStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageAssociationSourceProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageAssociationSourceProcessStatsProto parseFrom(InputStream input) throws IOException {
        return (PackageAssociationSourceProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageAssociationSourceProcessStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageAssociationSourceProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageAssociationSourceProcessStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PackageAssociationSourceProcessStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageAssociationSourceProcessStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageAssociationSourceProcessStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageAssociationSourceProcessStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (PackageAssociationSourceProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageAssociationSourceProcessStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageAssociationSourceProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PackageAssociationSourceProcessStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PackageAssociationSourceProcessStatsProto, Builder> implements PackageAssociationSourceProcessStatsProtoOrBuilder {
        private Builder() {
            super(PackageAssociationSourceProcessStatsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public boolean hasProcessUid() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).hasProcessUid();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public int getProcessUid() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getProcessUid();
        }

        public Builder setProcessUid(int value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setProcessUid(value);
            return this;
        }

        public Builder clearProcessUid() {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).clearProcessUid();
            return this;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public boolean hasProcessName() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).hasProcessName();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public String getProcessName() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getProcessName();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public ByteString getProcessNameBytes() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getProcessNameBytes();
        }

        public Builder setProcessName(String value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setProcessName(value);
            return this;
        }

        public Builder clearProcessName() {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).clearProcessName();
            return this;
        }

        public Builder setProcessNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setProcessNameBytes(value);
            return this;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public boolean hasPackageName() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).hasPackageName();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public String getPackageName() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getPackageName();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getPackageNameBytes();
        }

        public Builder setPackageName(String value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setPackageName(value);
            return this;
        }

        public Builder clearPackageName() {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).clearPackageName();
            return this;
        }

        public Builder setPackageNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setPackageNameBytes(value);
            return this;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public boolean hasTotalCount() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).hasTotalCount();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public int getTotalCount() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getTotalCount();
        }

        public Builder setTotalCount(int value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setTotalCount(value);
            return this;
        }

        public Builder clearTotalCount() {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).clearTotalCount();
            return this;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public boolean hasTotalDurationMs() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).hasTotalDurationMs();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public long getTotalDurationMs() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getTotalDurationMs();
        }

        public Builder setTotalDurationMs(long value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setTotalDurationMs(value);
            return this;
        }

        public Builder clearTotalDurationMs() {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).clearTotalDurationMs();
            return this;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public boolean hasActiveCount() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).hasActiveCount();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public int getActiveCount() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getActiveCount();
        }

        public Builder setActiveCount(int value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setActiveCount(value);
            return this;
        }

        public Builder clearActiveCount() {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).clearActiveCount();
            return this;
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public List<StateStats> getActiveStateStatsList() {
            return Collections.unmodifiableList(((PackageAssociationSourceProcessStatsProto) this.instance).getActiveStateStatsList());
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public int getActiveStateStatsCount() {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getActiveStateStatsCount();
        }

        @Override // android.service.procstats.PackageAssociationSourceProcessStatsProtoOrBuilder
        public StateStats getActiveStateStats(int index) {
            return ((PackageAssociationSourceProcessStatsProto) this.instance).getActiveStateStats(index);
        }

        public Builder setActiveStateStats(int index, StateStats value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setActiveStateStats((PackageAssociationSourceProcessStatsProto) index, (int) value);
            return this;
        }

        public Builder setActiveStateStats(int index, StateStats.Builder builderForValue) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).setActiveStateStats((PackageAssociationSourceProcessStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActiveStateStats(StateStats value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).addActiveStateStats((PackageAssociationSourceProcessStatsProto) value);
            return this;
        }

        public Builder addActiveStateStats(int index, StateStats value) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).addActiveStateStats((PackageAssociationSourceProcessStatsProto) index, (int) value);
            return this;
        }

        public Builder addActiveStateStats(StateStats.Builder builderForValue) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).addActiveStateStats((PackageAssociationSourceProcessStatsProto) builderForValue);
            return this;
        }

        public Builder addActiveStateStats(int index, StateStats.Builder builderForValue) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).addActiveStateStats((PackageAssociationSourceProcessStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActiveStateStats(Iterable<? extends StateStats> values) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).addAllActiveStateStats(values);
            return this;
        }

        public Builder clearActiveStateStats() {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).clearActiveStateStats();
            return this;
        }

        public Builder removeActiveStateStats(int index) {
            copyOnWrite();
            ((PackageAssociationSourceProcessStatsProto) this.instance).removeActiveStateStats(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PackageAssociationSourceProcessStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.activeStateStats_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PackageAssociationSourceProcessStatsProto other = (PackageAssociationSourceProcessStatsProto) arg1;
                this.processUid_ = visitor.visitInt(hasProcessUid(), this.processUid_, other.hasProcessUid(), other.processUid_);
                this.processName_ = visitor.visitString(hasProcessName(), this.processName_, other.hasProcessName(), other.processName_);
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                this.totalCount_ = visitor.visitInt(hasTotalCount(), this.totalCount_, other.hasTotalCount(), other.totalCount_);
                this.totalDurationMs_ = visitor.visitLong(hasTotalDurationMs(), this.totalDurationMs_, other.hasTotalDurationMs(), other.totalDurationMs_);
                this.activeCount_ = visitor.visitInt(hasActiveCount(), this.activeCount_, other.hasActiveCount(), other.activeCount_);
                this.activeStateStats_ = visitor.visitList(this.activeStateStats_, other.activeStateStats_);
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
                            this.processUid_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.processName_ = s;
                        } else if (tag == 24) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.totalCount_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ |= 16;
                            this.totalDurationMs_ = input.readInt64();
                        } else if (tag == 40) {
                            this.bitField0_ = 32 | this.bitField0_;
                            this.activeCount_ = input.readInt32();
                        } else if (tag == 50) {
                            if (!this.activeStateStats_.isModifiable()) {
                                this.activeStateStats_ = GeneratedMessageLite.mutableCopy(this.activeStateStats_);
                            }
                            this.activeStateStats_.add((StateStats) input.readMessage(StateStats.parser(), extensionRegistry));
                        } else if (tag == 58) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.packageName_ = s2;
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
                    synchronized (PackageAssociationSourceProcessStatsProto.class) {
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

    public static PackageAssociationSourceProcessStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PackageAssociationSourceProcessStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
