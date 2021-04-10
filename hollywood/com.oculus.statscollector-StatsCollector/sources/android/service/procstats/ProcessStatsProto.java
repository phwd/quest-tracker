package android.service.procstats;

import android.service.procstats.ProcessStatsStateProto;
import android.util.AggStats;
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

public final class ProcessStatsProto extends GeneratedMessageLite<ProcessStatsProto, Builder> implements ProcessStatsProtoOrBuilder {
    private static final ProcessStatsProto DEFAULT_INSTANCE = new ProcessStatsProto();
    public static final int KILL_FIELD_NUMBER = 3;
    private static volatile Parser<ProcessStatsProto> PARSER = null;
    public static final int PROCESS_FIELD_NUMBER = 1;
    public static final int STATES_FIELD_NUMBER = 5;
    public static final int TOTAL_RUNNING_STATE_FIELD_NUMBER = 6;
    public static final int UID_FIELD_NUMBER = 2;
    private int bitField0_;
    private Kill kill_;
    private String process_ = "";
    private Internal.ProtobufList<ProcessStatsStateProto> states_ = emptyProtobufList();
    private ProcessStatsStateProto totalRunningState_;
    private int uid_ = 0;

    public interface KillOrBuilder extends MessageLiteOrBuilder {
        int getCached();

        AggStats getCachedPss();

        int getCpu();

        boolean hasCached();

        boolean hasCachedPss();

        boolean hasCpu();
    }

    private ProcessStatsProto() {
    }

    public static final class Kill extends GeneratedMessageLite<Kill, Builder> implements KillOrBuilder {
        public static final int CACHED_FIELD_NUMBER = 2;
        public static final int CACHED_PSS_FIELD_NUMBER = 3;
        public static final int CPU_FIELD_NUMBER = 1;
        private static final Kill DEFAULT_INSTANCE = new Kill();
        private static volatile Parser<Kill> PARSER;
        private int bitField0_;
        private AggStats cachedPss_;
        private int cached_ = 0;
        private int cpu_ = 0;

        private Kill() {
        }

        @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
        public boolean hasCpu() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
        public int getCpu() {
            return this.cpu_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCpu(int value) {
            this.bitField0_ |= 1;
            this.cpu_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCpu() {
            this.bitField0_ &= -2;
            this.cpu_ = 0;
        }

        @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
        public boolean hasCached() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
        public int getCached() {
            return this.cached_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCached(int value) {
            this.bitField0_ |= 2;
            this.cached_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCached() {
            this.bitField0_ &= -3;
            this.cached_ = 0;
        }

        @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
        public boolean hasCachedPss() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
        public AggStats getCachedPss() {
            AggStats aggStats = this.cachedPss_;
            return aggStats == null ? AggStats.getDefaultInstance() : aggStats;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCachedPss(AggStats value) {
            if (value != null) {
                this.cachedPss_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCachedPss(AggStats.Builder builderForValue) {
            this.cachedPss_ = (AggStats) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeCachedPss(AggStats value) {
            AggStats aggStats = this.cachedPss_;
            if (aggStats == null || aggStats == AggStats.getDefaultInstance()) {
                this.cachedPss_ = value;
            } else {
                this.cachedPss_ = (AggStats) ((AggStats.Builder) AggStats.newBuilder(this.cachedPss_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCachedPss() {
            this.cachedPss_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.cpu_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.cached_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getCachedPss());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.cpu_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.cached_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getCachedPss());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Kill parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Kill) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Kill parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Kill) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Kill parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Kill) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Kill parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Kill) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Kill parseFrom(InputStream input) throws IOException {
            return (Kill) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Kill parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Kill) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Kill parseDelimitedFrom(InputStream input) throws IOException {
            return (Kill) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Kill parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Kill) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Kill parseFrom(CodedInputStream input) throws IOException {
            return (Kill) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Kill parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Kill) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Kill prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Kill, Builder> implements KillOrBuilder {
            private Builder() {
                super(Kill.DEFAULT_INSTANCE);
            }

            @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
            public boolean hasCpu() {
                return ((Kill) this.instance).hasCpu();
            }

            @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
            public int getCpu() {
                return ((Kill) this.instance).getCpu();
            }

            public Builder setCpu(int value) {
                copyOnWrite();
                ((Kill) this.instance).setCpu(value);
                return this;
            }

            public Builder clearCpu() {
                copyOnWrite();
                ((Kill) this.instance).clearCpu();
                return this;
            }

            @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
            public boolean hasCached() {
                return ((Kill) this.instance).hasCached();
            }

            @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
            public int getCached() {
                return ((Kill) this.instance).getCached();
            }

            public Builder setCached(int value) {
                copyOnWrite();
                ((Kill) this.instance).setCached(value);
                return this;
            }

            public Builder clearCached() {
                copyOnWrite();
                ((Kill) this.instance).clearCached();
                return this;
            }

            @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
            public boolean hasCachedPss() {
                return ((Kill) this.instance).hasCachedPss();
            }

            @Override // android.service.procstats.ProcessStatsProto.KillOrBuilder
            public AggStats getCachedPss() {
                return ((Kill) this.instance).getCachedPss();
            }

            public Builder setCachedPss(AggStats value) {
                copyOnWrite();
                ((Kill) this.instance).setCachedPss((Kill) value);
                return this;
            }

            public Builder setCachedPss(AggStats.Builder builderForValue) {
                copyOnWrite();
                ((Kill) this.instance).setCachedPss((Kill) builderForValue);
                return this;
            }

            public Builder mergeCachedPss(AggStats value) {
                copyOnWrite();
                ((Kill) this.instance).mergeCachedPss(value);
                return this;
            }

            public Builder clearCachedPss() {
                copyOnWrite();
                ((Kill) this.instance).clearCachedPss();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Kill();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Kill other = (Kill) arg1;
                    this.cpu_ = visitor.visitInt(hasCpu(), this.cpu_, other.hasCpu(), other.cpu_);
                    this.cached_ = visitor.visitInt(hasCached(), this.cached_, other.hasCached(), other.cached_);
                    this.cachedPss_ = (AggStats) visitor.visitMessage(this.cachedPss_, other.cachedPss_);
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
                                this.cpu_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.cached_ = input.readInt32();
                            } else if (tag == 26) {
                                AggStats.Builder subBuilder = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder = (AggStats.Builder) this.cachedPss_.toBuilder();
                                }
                                this.cachedPss_ = (AggStats) input.readMessage(AggStats.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.cachedPss_);
                                    this.cachedPss_ = (AggStats) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
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
                        synchronized (Kill.class) {
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

        public static Kill getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Kill> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public boolean hasProcess() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public String getProcess() {
        return this.process_;
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public ByteString getProcessBytes() {
        return ByteString.copyFromUtf8(this.process_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcess(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.process_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcess() {
        this.bitField0_ &= -2;
        this.process_ = getDefaultInstance().getProcess();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.process_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 2;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -3;
        this.uid_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public boolean hasKill() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public Kill getKill() {
        Kill kill = this.kill_;
        return kill == null ? Kill.getDefaultInstance() : kill;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKill(Kill value) {
        if (value != null) {
            this.kill_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKill(Kill.Builder builderForValue) {
        this.kill_ = (Kill) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeKill(Kill value) {
        Kill kill = this.kill_;
        if (kill == null || kill == Kill.getDefaultInstance()) {
            this.kill_ = value;
        } else {
            this.kill_ = (Kill) ((Kill.Builder) Kill.newBuilder(this.kill_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKill() {
        this.kill_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public List<ProcessStatsStateProto> getStatesList() {
        return this.states_;
    }

    public List<? extends ProcessStatsStateProtoOrBuilder> getStatesOrBuilderList() {
        return this.states_;
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public int getStatesCount() {
        return this.states_.size();
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public ProcessStatsStateProto getStates(int index) {
        return this.states_.get(index);
    }

    public ProcessStatsStateProtoOrBuilder getStatesOrBuilder(int index) {
        return this.states_.get(index);
    }

    private void ensureStatesIsMutable() {
        if (!this.states_.isModifiable()) {
            this.states_ = GeneratedMessageLite.mutableCopy(this.states_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStates(int index, ProcessStatsStateProto value) {
        if (value != null) {
            ensureStatesIsMutable();
            this.states_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStates(int index, ProcessStatsStateProto.Builder builderForValue) {
        ensureStatesIsMutable();
        this.states_.set(index, (ProcessStatsStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(ProcessStatsStateProto value) {
        if (value != null) {
            ensureStatesIsMutable();
            this.states_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(int index, ProcessStatsStateProto value) {
        if (value != null) {
            ensureStatesIsMutable();
            this.states_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(ProcessStatsStateProto.Builder builderForValue) {
        ensureStatesIsMutable();
        this.states_.add((ProcessStatsStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStates(int index, ProcessStatsStateProto.Builder builderForValue) {
        ensureStatesIsMutable();
        this.states_.add(index, (ProcessStatsStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStates(Iterable<? extends ProcessStatsStateProto> values) {
        ensureStatesIsMutable();
        AbstractMessageLite.addAll(values, this.states_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStates() {
        this.states_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeStates(int index) {
        ensureStatesIsMutable();
        this.states_.remove(index);
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public boolean hasTotalRunningState() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.procstats.ProcessStatsProtoOrBuilder
    public ProcessStatsStateProto getTotalRunningState() {
        ProcessStatsStateProto processStatsStateProto = this.totalRunningState_;
        return processStatsStateProto == null ? ProcessStatsStateProto.getDefaultInstance() : processStatsStateProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalRunningState(ProcessStatsStateProto value) {
        if (value != null) {
            this.totalRunningState_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalRunningState(ProcessStatsStateProto.Builder builderForValue) {
        this.totalRunningState_ = (ProcessStatsStateProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTotalRunningState(ProcessStatsStateProto value) {
        ProcessStatsStateProto processStatsStateProto = this.totalRunningState_;
        if (processStatsStateProto == null || processStatsStateProto == ProcessStatsStateProto.getDefaultInstance()) {
            this.totalRunningState_ = value;
        } else {
            this.totalRunningState_ = (ProcessStatsStateProto) ((ProcessStatsStateProto.Builder) ProcessStatsStateProto.newBuilder(this.totalRunningState_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalRunningState() {
        this.totalRunningState_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getProcess());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.uid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getKill());
        }
        for (int i = 0; i < this.states_.size(); i++) {
            output.writeMessage(5, this.states_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(6, getTotalRunningState());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getProcess());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.uid_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getKill());
        }
        for (int i = 0; i < this.states_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.states_.get(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(6, getTotalRunningState());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcessStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsProto parseFrom(InputStream input) throws IOException {
        return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessStatsProto, Builder> implements ProcessStatsProtoOrBuilder {
        private Builder() {
            super(ProcessStatsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public boolean hasProcess() {
            return ((ProcessStatsProto) this.instance).hasProcess();
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public String getProcess() {
            return ((ProcessStatsProto) this.instance).getProcess();
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public ByteString getProcessBytes() {
            return ((ProcessStatsProto) this.instance).getProcessBytes();
        }

        public Builder setProcess(String value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setProcess(value);
            return this;
        }

        public Builder clearProcess() {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).clearProcess();
            return this;
        }

        public Builder setProcessBytes(ByteString value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setProcessBytes(value);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public boolean hasUid() {
            return ((ProcessStatsProto) this.instance).hasUid();
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public int getUid() {
            return ((ProcessStatsProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).clearUid();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public boolean hasKill() {
            return ((ProcessStatsProto) this.instance).hasKill();
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public Kill getKill() {
            return ((ProcessStatsProto) this.instance).getKill();
        }

        public Builder setKill(Kill value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setKill((ProcessStatsProto) value);
            return this;
        }

        public Builder setKill(Kill.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setKill((ProcessStatsProto) builderForValue);
            return this;
        }

        public Builder mergeKill(Kill value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).mergeKill(value);
            return this;
        }

        public Builder clearKill() {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).clearKill();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public List<ProcessStatsStateProto> getStatesList() {
            return Collections.unmodifiableList(((ProcessStatsProto) this.instance).getStatesList());
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public int getStatesCount() {
            return ((ProcessStatsProto) this.instance).getStatesCount();
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public ProcessStatsStateProto getStates(int index) {
            return ((ProcessStatsProto) this.instance).getStates(index);
        }

        public Builder setStates(int index, ProcessStatsStateProto value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setStates((ProcessStatsProto) index, (int) value);
            return this;
        }

        public Builder setStates(int index, ProcessStatsStateProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setStates((ProcessStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStates(ProcessStatsStateProto value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).addStates((ProcessStatsProto) value);
            return this;
        }

        public Builder addStates(int index, ProcessStatsStateProto value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).addStates((ProcessStatsProto) index, (int) value);
            return this;
        }

        public Builder addStates(ProcessStatsStateProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).addStates((ProcessStatsProto) builderForValue);
            return this;
        }

        public Builder addStates(int index, ProcessStatsStateProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).addStates((ProcessStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStates(Iterable<? extends ProcessStatsStateProto> values) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).addAllStates(values);
            return this;
        }

        public Builder clearStates() {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).clearStates();
            return this;
        }

        public Builder removeStates(int index) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).removeStates(index);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public boolean hasTotalRunningState() {
            return ((ProcessStatsProto) this.instance).hasTotalRunningState();
        }

        @Override // android.service.procstats.ProcessStatsProtoOrBuilder
        public ProcessStatsStateProto getTotalRunningState() {
            return ((ProcessStatsProto) this.instance).getTotalRunningState();
        }

        public Builder setTotalRunningState(ProcessStatsStateProto value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setTotalRunningState((ProcessStatsProto) value);
            return this;
        }

        public Builder setTotalRunningState(ProcessStatsStateProto.Builder builderForValue) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).setTotalRunningState((ProcessStatsProto) builderForValue);
            return this;
        }

        public Builder mergeTotalRunningState(ProcessStatsStateProto value) {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).mergeTotalRunningState(value);
            return this;
        }

        public Builder clearTotalRunningState() {
            copyOnWrite();
            ((ProcessStatsProto) this.instance).clearTotalRunningState();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.states_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessStatsProto other = (ProcessStatsProto) arg1;
                this.process_ = visitor.visitString(hasProcess(), this.process_, other.hasProcess(), other.process_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.kill_ = (Kill) visitor.visitMessage(this.kill_, other.kill_);
                this.states_ = visitor.visitList(this.states_, other.states_);
                this.totalRunningState_ = (ProcessStatsStateProto) visitor.visitMessage(this.totalRunningState_, other.totalRunningState_);
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
                            this.process_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.uid_ = input.readInt32();
                        } else if (tag == 26) {
                            Kill.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (Kill.Builder) this.kill_.toBuilder();
                            }
                            this.kill_ = (Kill) input.readMessage(Kill.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.kill_);
                                this.kill_ = (Kill) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 42) {
                            if (!this.states_.isModifiable()) {
                                this.states_ = GeneratedMessageLite.mutableCopy(this.states_);
                            }
                            this.states_.add((ProcessStatsStateProto) input.readMessage(ProcessStatsStateProto.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            ProcessStatsStateProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder2 = (ProcessStatsStateProto.Builder) this.totalRunningState_.toBuilder();
                            }
                            this.totalRunningState_ = (ProcessStatsStateProto) input.readMessage(ProcessStatsStateProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.totalRunningState_);
                                this.totalRunningState_ = (ProcessStatsStateProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 8;
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
                    synchronized (ProcessStatsProto.class) {
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

    public static ProcessStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
