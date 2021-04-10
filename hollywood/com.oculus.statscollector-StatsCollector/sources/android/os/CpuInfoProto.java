package android.os;

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

public final class CpuInfoProto extends GeneratedMessageLite<CpuInfoProto, Builder> implements CpuInfoProtoOrBuilder {
    public static final int CPU_USAGE_FIELD_NUMBER = 4;
    private static final CpuInfoProto DEFAULT_INSTANCE = new CpuInfoProto();
    public static final int MEM_FIELD_NUMBER = 2;
    private static volatile Parser<CpuInfoProto> PARSER = null;
    public static final int SWAP_FIELD_NUMBER = 3;
    public static final int TASKS_FIELD_NUMBER = 5;
    public static final int TASK_STATS_FIELD_NUMBER = 1;
    private int bitField0_;
    private CpuUsage cpuUsage_;
    private MemStats mem_;
    private MemStats swap_;
    private TaskStats taskStats_;
    private Internal.ProtobufList<Task> tasks_ = emptyProtobufList();

    public interface CpuUsageOrBuilder extends MessageLiteOrBuilder {
        int getCpu();

        int getHost();

        int getIdle();

        int getIow();

        int getIrq();

        int getNice();

        int getSirq();

        int getSys();

        int getUser();

        boolean hasCpu();

        boolean hasHost();

        boolean hasIdle();

        boolean hasIow();

        boolean hasIrq();

        boolean hasNice();

        boolean hasSirq();

        boolean hasSys();

        boolean hasUser();
    }

    public interface MemStatsOrBuilder extends MessageLiteOrBuilder {
        int getBuffers();

        int getCached();

        int getFree();

        int getTotal();

        int getUsed();

        boolean hasBuffers();

        boolean hasCached();

        boolean hasFree();

        boolean hasTotal();

        boolean hasUsed();
    }

    public interface TaskOrBuilder extends MessageLiteOrBuilder {
        String getCmd();

        ByteString getCmdBytes();

        float getCpu();

        String getName();

        ByteString getNameBytes();

        int getNi();

        Task.Policy getPcy();

        int getPid();

        String getPr();

        ByteString getPrBytes();

        String getRes();

        ByteString getResBytes();

        Task.Status getS();

        int getTid();

        String getUser();

        ByteString getUserBytes();

        String getVirt();

        ByteString getVirtBytes();

        boolean hasCmd();

        boolean hasCpu();

        boolean hasName();

        boolean hasNi();

        boolean hasPcy();

        boolean hasPid();

        boolean hasPr();

        boolean hasRes();

        boolean hasS();

        boolean hasTid();

        boolean hasUser();

        boolean hasVirt();
    }

    public interface TaskStatsOrBuilder extends MessageLiteOrBuilder {
        int getRunning();

        int getSleeping();

        int getStopped();

        int getTotal();

        int getZombie();

        boolean hasRunning();

        boolean hasSleeping();

        boolean hasStopped();

        boolean hasTotal();

        boolean hasZombie();
    }

    private CpuInfoProto() {
    }

    public static final class TaskStats extends GeneratedMessageLite<TaskStats, Builder> implements TaskStatsOrBuilder {
        private static final TaskStats DEFAULT_INSTANCE = new TaskStats();
        private static volatile Parser<TaskStats> PARSER = null;
        public static final int RUNNING_FIELD_NUMBER = 2;
        public static final int SLEEPING_FIELD_NUMBER = 3;
        public static final int STOPPED_FIELD_NUMBER = 4;
        public static final int TOTAL_FIELD_NUMBER = 1;
        public static final int ZOMBIE_FIELD_NUMBER = 5;
        private int bitField0_;
        private int running_ = 0;
        private int sleeping_ = 0;
        private int stopped_ = 0;
        private int total_ = 0;
        private int zombie_ = 0;

        private TaskStats() {
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public int getTotal() {
            return this.total_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(int value) {
            this.bitField0_ |= 1;
            this.total_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotal() {
            this.bitField0_ &= -2;
            this.total_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public boolean hasRunning() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public int getRunning() {
            return this.running_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRunning(int value) {
            this.bitField0_ |= 2;
            this.running_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRunning() {
            this.bitField0_ &= -3;
            this.running_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public boolean hasSleeping() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public int getSleeping() {
            return this.sleeping_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSleeping(int value) {
            this.bitField0_ |= 4;
            this.sleeping_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSleeping() {
            this.bitField0_ &= -5;
            this.sleeping_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public boolean hasStopped() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public int getStopped() {
            return this.stopped_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStopped(int value) {
            this.bitField0_ |= 8;
            this.stopped_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStopped() {
            this.bitField0_ &= -9;
            this.stopped_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public boolean hasZombie() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
        public int getZombie() {
            return this.zombie_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setZombie(int value) {
            this.bitField0_ |= 16;
            this.zombie_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearZombie() {
            this.bitField0_ &= -17;
            this.zombie_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.total_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.running_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.sleeping_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.stopped_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.zombie_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.total_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.running_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.sleeping_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.stopped_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.zombie_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static TaskStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (TaskStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TaskStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TaskStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TaskStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (TaskStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TaskStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TaskStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TaskStats parseFrom(InputStream input) throws IOException {
            return (TaskStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TaskStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TaskStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TaskStats parseDelimitedFrom(InputStream input) throws IOException {
            return (TaskStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static TaskStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TaskStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TaskStats parseFrom(CodedInputStream input) throws IOException {
            return (TaskStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TaskStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TaskStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TaskStats prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TaskStats, Builder> implements TaskStatsOrBuilder {
            private Builder() {
                super(TaskStats.DEFAULT_INSTANCE);
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public boolean hasTotal() {
                return ((TaskStats) this.instance).hasTotal();
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public int getTotal() {
                return ((TaskStats) this.instance).getTotal();
            }

            public Builder setTotal(int value) {
                copyOnWrite();
                ((TaskStats) this.instance).setTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((TaskStats) this.instance).clearTotal();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public boolean hasRunning() {
                return ((TaskStats) this.instance).hasRunning();
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public int getRunning() {
                return ((TaskStats) this.instance).getRunning();
            }

            public Builder setRunning(int value) {
                copyOnWrite();
                ((TaskStats) this.instance).setRunning(value);
                return this;
            }

            public Builder clearRunning() {
                copyOnWrite();
                ((TaskStats) this.instance).clearRunning();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public boolean hasSleeping() {
                return ((TaskStats) this.instance).hasSleeping();
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public int getSleeping() {
                return ((TaskStats) this.instance).getSleeping();
            }

            public Builder setSleeping(int value) {
                copyOnWrite();
                ((TaskStats) this.instance).setSleeping(value);
                return this;
            }

            public Builder clearSleeping() {
                copyOnWrite();
                ((TaskStats) this.instance).clearSleeping();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public boolean hasStopped() {
                return ((TaskStats) this.instance).hasStopped();
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public int getStopped() {
                return ((TaskStats) this.instance).getStopped();
            }

            public Builder setStopped(int value) {
                copyOnWrite();
                ((TaskStats) this.instance).setStopped(value);
                return this;
            }

            public Builder clearStopped() {
                copyOnWrite();
                ((TaskStats) this.instance).clearStopped();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public boolean hasZombie() {
                return ((TaskStats) this.instance).hasZombie();
            }

            @Override // android.os.CpuInfoProto.TaskStatsOrBuilder
            public int getZombie() {
                return ((TaskStats) this.instance).getZombie();
            }

            public Builder setZombie(int value) {
                copyOnWrite();
                ((TaskStats) this.instance).setZombie(value);
                return this;
            }

            public Builder clearZombie() {
                copyOnWrite();
                ((TaskStats) this.instance).clearZombie();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new TaskStats();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    TaskStats other = (TaskStats) arg1;
                    this.total_ = visitor.visitInt(hasTotal(), this.total_, other.hasTotal(), other.total_);
                    this.running_ = visitor.visitInt(hasRunning(), this.running_, other.hasRunning(), other.running_);
                    this.sleeping_ = visitor.visitInt(hasSleeping(), this.sleeping_, other.hasSleeping(), other.sleeping_);
                    this.stopped_ = visitor.visitInt(hasStopped(), this.stopped_, other.hasStopped(), other.stopped_);
                    this.zombie_ = visitor.visitInt(hasZombie(), this.zombie_, other.hasZombie(), other.zombie_);
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
                                this.total_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.running_ = input.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.sleeping_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.stopped_ = input.readInt32();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.zombie_ = input.readInt32();
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
                        synchronized (TaskStats.class) {
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

        public static TaskStats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TaskStats> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class MemStats extends GeneratedMessageLite<MemStats, Builder> implements MemStatsOrBuilder {
        public static final int BUFFERS_FIELD_NUMBER = 4;
        public static final int CACHED_FIELD_NUMBER = 5;
        private static final MemStats DEFAULT_INSTANCE = new MemStats();
        public static final int FREE_FIELD_NUMBER = 3;
        private static volatile Parser<MemStats> PARSER = null;
        public static final int TOTAL_FIELD_NUMBER = 1;
        public static final int USED_FIELD_NUMBER = 2;
        private int bitField0_;
        private int buffers_ = 0;
        private int cached_ = 0;
        private int free_ = 0;
        private int total_ = 0;
        private int used_ = 0;

        private MemStats() {
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public boolean hasTotal() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public int getTotal() {
            return this.total_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotal(int value) {
            this.bitField0_ |= 1;
            this.total_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotal() {
            this.bitField0_ &= -2;
            this.total_ = 0;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public boolean hasUsed() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public int getUsed() {
            return this.used_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUsed(int value) {
            this.bitField0_ |= 2;
            this.used_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUsed() {
            this.bitField0_ &= -3;
            this.used_ = 0;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public boolean hasFree() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public int getFree() {
            return this.free_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFree(int value) {
            this.bitField0_ |= 4;
            this.free_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFree() {
            this.bitField0_ &= -5;
            this.free_ = 0;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public boolean hasBuffers() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public int getBuffers() {
            return this.buffers_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBuffers(int value) {
            this.bitField0_ |= 8;
            this.buffers_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBuffers() {
            this.bitField0_ &= -9;
            this.buffers_ = 0;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public boolean hasCached() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.CpuInfoProto.MemStatsOrBuilder
        public int getCached() {
            return this.cached_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCached(int value) {
            this.bitField0_ |= 16;
            this.cached_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCached() {
            this.bitField0_ &= -17;
            this.cached_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.total_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.used_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.free_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.buffers_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.cached_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.total_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.used_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.free_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.buffers_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.cached_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static MemStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MemStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MemStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MemStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MemStats parseFrom(InputStream input) throws IOException {
            return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MemStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MemStats parseDelimitedFrom(InputStream input) throws IOException {
            return (MemStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MemStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MemStats parseFrom(CodedInputStream input) throws IOException {
            return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MemStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MemStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MemStats prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MemStats, Builder> implements MemStatsOrBuilder {
            private Builder() {
                super(MemStats.DEFAULT_INSTANCE);
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public boolean hasTotal() {
                return ((MemStats) this.instance).hasTotal();
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public int getTotal() {
                return ((MemStats) this.instance).getTotal();
            }

            public Builder setTotal(int value) {
                copyOnWrite();
                ((MemStats) this.instance).setTotal(value);
                return this;
            }

            public Builder clearTotal() {
                copyOnWrite();
                ((MemStats) this.instance).clearTotal();
                return this;
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public boolean hasUsed() {
                return ((MemStats) this.instance).hasUsed();
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public int getUsed() {
                return ((MemStats) this.instance).getUsed();
            }

            public Builder setUsed(int value) {
                copyOnWrite();
                ((MemStats) this.instance).setUsed(value);
                return this;
            }

            public Builder clearUsed() {
                copyOnWrite();
                ((MemStats) this.instance).clearUsed();
                return this;
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public boolean hasFree() {
                return ((MemStats) this.instance).hasFree();
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public int getFree() {
                return ((MemStats) this.instance).getFree();
            }

            public Builder setFree(int value) {
                copyOnWrite();
                ((MemStats) this.instance).setFree(value);
                return this;
            }

            public Builder clearFree() {
                copyOnWrite();
                ((MemStats) this.instance).clearFree();
                return this;
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public boolean hasBuffers() {
                return ((MemStats) this.instance).hasBuffers();
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public int getBuffers() {
                return ((MemStats) this.instance).getBuffers();
            }

            public Builder setBuffers(int value) {
                copyOnWrite();
                ((MemStats) this.instance).setBuffers(value);
                return this;
            }

            public Builder clearBuffers() {
                copyOnWrite();
                ((MemStats) this.instance).clearBuffers();
                return this;
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public boolean hasCached() {
                return ((MemStats) this.instance).hasCached();
            }

            @Override // android.os.CpuInfoProto.MemStatsOrBuilder
            public int getCached() {
                return ((MemStats) this.instance).getCached();
            }

            public Builder setCached(int value) {
                copyOnWrite();
                ((MemStats) this.instance).setCached(value);
                return this;
            }

            public Builder clearCached() {
                copyOnWrite();
                ((MemStats) this.instance).clearCached();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new MemStats();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    MemStats other = (MemStats) arg1;
                    this.total_ = visitor.visitInt(hasTotal(), this.total_, other.hasTotal(), other.total_);
                    this.used_ = visitor.visitInt(hasUsed(), this.used_, other.hasUsed(), other.used_);
                    this.free_ = visitor.visitInt(hasFree(), this.free_, other.hasFree(), other.free_);
                    this.buffers_ = visitor.visitInt(hasBuffers(), this.buffers_, other.hasBuffers(), other.buffers_);
                    this.cached_ = visitor.visitInt(hasCached(), this.cached_, other.hasCached(), other.cached_);
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
                                this.total_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.used_ = input.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.free_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.buffers_ = input.readInt32();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.cached_ = input.readInt32();
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
                        synchronized (MemStats.class) {
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

        public static MemStats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MemStats> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class CpuUsage extends GeneratedMessageLite<CpuUsage, Builder> implements CpuUsageOrBuilder {
        public static final int CPU_FIELD_NUMBER = 1;
        private static final CpuUsage DEFAULT_INSTANCE = new CpuUsage();
        public static final int HOST_FIELD_NUMBER = 9;
        public static final int IDLE_FIELD_NUMBER = 5;
        public static final int IOW_FIELD_NUMBER = 6;
        public static final int IRQ_FIELD_NUMBER = 7;
        public static final int NICE_FIELD_NUMBER = 3;
        private static volatile Parser<CpuUsage> PARSER = null;
        public static final int SIRQ_FIELD_NUMBER = 8;
        public static final int SYS_FIELD_NUMBER = 4;
        public static final int USER_FIELD_NUMBER = 2;
        private int bitField0_;
        private int cpu_ = 0;
        private int host_ = 0;
        private int idle_ = 0;
        private int iow_ = 0;
        private int irq_ = 0;
        private int nice_ = 0;
        private int sirq_ = 0;
        private int sys_ = 0;
        private int user_ = 0;

        private CpuUsage() {
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasCpu() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
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

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasUser() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public int getUser() {
            return this.user_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUser(int value) {
            this.bitField0_ |= 2;
            this.user_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUser() {
            this.bitField0_ &= -3;
            this.user_ = 0;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasNice() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public int getNice() {
            return this.nice_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNice(int value) {
            this.bitField0_ |= 4;
            this.nice_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNice() {
            this.bitField0_ &= -5;
            this.nice_ = 0;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasSys() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public int getSys() {
            return this.sys_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSys(int value) {
            this.bitField0_ |= 8;
            this.sys_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSys() {
            this.bitField0_ &= -9;
            this.sys_ = 0;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasIdle() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public int getIdle() {
            return this.idle_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIdle(int value) {
            this.bitField0_ |= 16;
            this.idle_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIdle() {
            this.bitField0_ &= -17;
            this.idle_ = 0;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasIow() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public int getIow() {
            return this.iow_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIow(int value) {
            this.bitField0_ |= 32;
            this.iow_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIow() {
            this.bitField0_ &= -33;
            this.iow_ = 0;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasIrq() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public int getIrq() {
            return this.irq_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIrq(int value) {
            this.bitField0_ |= 64;
            this.irq_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIrq() {
            this.bitField0_ &= -65;
            this.irq_ = 0;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasSirq() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public int getSirq() {
            return this.sirq_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSirq(int value) {
            this.bitField0_ |= 128;
            this.sirq_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSirq() {
            this.bitField0_ &= -129;
            this.sirq_ = 0;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public boolean hasHost() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
        public int getHost() {
            return this.host_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHost(int value) {
            this.bitField0_ |= 256;
            this.host_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHost() {
            this.bitField0_ &= -257;
            this.host_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.cpu_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.user_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.nice_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.sys_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.idle_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.iow_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.irq_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.sirq_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt32(9, this.host_);
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
                size2 += CodedOutputStream.computeInt32Size(2, this.user_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.nice_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.sys_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.idle_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.iow_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.irq_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.sirq_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt32Size(9, this.host_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static CpuUsage parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CpuUsage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CpuUsage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CpuUsage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CpuUsage parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CpuUsage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CpuUsage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CpuUsage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CpuUsage parseFrom(InputStream input) throws IOException {
            return (CpuUsage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CpuUsage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CpuUsage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CpuUsage parseDelimitedFrom(InputStream input) throws IOException {
            return (CpuUsage) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CpuUsage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CpuUsage) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CpuUsage parseFrom(CodedInputStream input) throws IOException {
            return (CpuUsage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CpuUsage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CpuUsage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CpuUsage prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CpuUsage, Builder> implements CpuUsageOrBuilder {
            private Builder() {
                super(CpuUsage.DEFAULT_INSTANCE);
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasCpu() {
                return ((CpuUsage) this.instance).hasCpu();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getCpu() {
                return ((CpuUsage) this.instance).getCpu();
            }

            public Builder setCpu(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setCpu(value);
                return this;
            }

            public Builder clearCpu() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearCpu();
                return this;
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasUser() {
                return ((CpuUsage) this.instance).hasUser();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getUser() {
                return ((CpuUsage) this.instance).getUser();
            }

            public Builder setUser(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setUser(value);
                return this;
            }

            public Builder clearUser() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearUser();
                return this;
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasNice() {
                return ((CpuUsage) this.instance).hasNice();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getNice() {
                return ((CpuUsage) this.instance).getNice();
            }

            public Builder setNice(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setNice(value);
                return this;
            }

            public Builder clearNice() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearNice();
                return this;
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasSys() {
                return ((CpuUsage) this.instance).hasSys();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getSys() {
                return ((CpuUsage) this.instance).getSys();
            }

            public Builder setSys(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setSys(value);
                return this;
            }

            public Builder clearSys() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearSys();
                return this;
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasIdle() {
                return ((CpuUsage) this.instance).hasIdle();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getIdle() {
                return ((CpuUsage) this.instance).getIdle();
            }

            public Builder setIdle(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setIdle(value);
                return this;
            }

            public Builder clearIdle() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearIdle();
                return this;
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasIow() {
                return ((CpuUsage) this.instance).hasIow();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getIow() {
                return ((CpuUsage) this.instance).getIow();
            }

            public Builder setIow(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setIow(value);
                return this;
            }

            public Builder clearIow() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearIow();
                return this;
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasIrq() {
                return ((CpuUsage) this.instance).hasIrq();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getIrq() {
                return ((CpuUsage) this.instance).getIrq();
            }

            public Builder setIrq(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setIrq(value);
                return this;
            }

            public Builder clearIrq() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearIrq();
                return this;
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasSirq() {
                return ((CpuUsage) this.instance).hasSirq();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getSirq() {
                return ((CpuUsage) this.instance).getSirq();
            }

            public Builder setSirq(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setSirq(value);
                return this;
            }

            public Builder clearSirq() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearSirq();
                return this;
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public boolean hasHost() {
                return ((CpuUsage) this.instance).hasHost();
            }

            @Override // android.os.CpuInfoProto.CpuUsageOrBuilder
            public int getHost() {
                return ((CpuUsage) this.instance).getHost();
            }

            public Builder setHost(int value) {
                copyOnWrite();
                ((CpuUsage) this.instance).setHost(value);
                return this;
            }

            public Builder clearHost() {
                copyOnWrite();
                ((CpuUsage) this.instance).clearHost();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new CpuUsage();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    CpuUsage other = (CpuUsage) arg1;
                    this.cpu_ = visitor.visitInt(hasCpu(), this.cpu_, other.hasCpu(), other.cpu_);
                    this.user_ = visitor.visitInt(hasUser(), this.user_, other.hasUser(), other.user_);
                    this.nice_ = visitor.visitInt(hasNice(), this.nice_, other.hasNice(), other.nice_);
                    this.sys_ = visitor.visitInt(hasSys(), this.sys_, other.hasSys(), other.sys_);
                    this.idle_ = visitor.visitInt(hasIdle(), this.idle_, other.hasIdle(), other.idle_);
                    this.iow_ = visitor.visitInt(hasIow(), this.iow_, other.hasIow(), other.iow_);
                    this.irq_ = visitor.visitInt(hasIrq(), this.irq_, other.hasIrq(), other.irq_);
                    this.sirq_ = visitor.visitInt(hasSirq(), this.sirq_, other.hasSirq(), other.sirq_);
                    this.host_ = visitor.visitInt(hasHost(), this.host_, other.hasHost(), other.host_);
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
                                this.cpu_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.user_ = input.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.nice_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.sys_ = input.readInt32();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.idle_ = input.readInt32();
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.iow_ = input.readInt32();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.irq_ = input.readInt32();
                            } else if (tag == 64) {
                                this.bitField0_ |= 128;
                                this.sirq_ = input.readInt32();
                            } else if (tag == 72) {
                                this.bitField0_ |= 256;
                                this.host_ = input.readInt32();
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
                        synchronized (CpuUsage.class) {
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

        public static CpuUsage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CpuUsage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Task extends GeneratedMessageLite<Task, Builder> implements TaskOrBuilder {
        public static final int CMD_FIELD_NUMBER = 11;
        public static final int CPU_FIELD_NUMBER = 6;
        private static final Task DEFAULT_INSTANCE = new Task();
        public static final int NAME_FIELD_NUMBER = 12;
        public static final int NI_FIELD_NUMBER = 5;
        private static volatile Parser<Task> PARSER = null;
        public static final int PCY_FIELD_NUMBER = 10;
        public static final int PID_FIELD_NUMBER = 1;
        public static final int PR_FIELD_NUMBER = 4;
        public static final int RES_FIELD_NUMBER = 9;
        public static final int S_FIELD_NUMBER = 7;
        public static final int TID_FIELD_NUMBER = 2;
        public static final int USER_FIELD_NUMBER = 3;
        public static final int VIRT_FIELD_NUMBER = 8;
        private int bitField0_;
        private String cmd_ = "";
        private float cpu_ = 0.0f;
        private String name_ = "";
        private int ni_ = 0;
        private int pcy_ = 0;
        private int pid_ = 0;
        private String pr_ = "";
        private String res_ = "";
        private int s_ = 0;
        private int tid_ = 0;
        private String user_ = "";
        private String virt_ = "";

        private Task() {
        }

        public enum Status implements Internal.EnumLite {
            STATUS_UNKNOWN(0),
            STATUS_D(1),
            STATUS_R(2),
            STATUS_S(3),
            STATUS_T(4),
            STATUS_Z(5);
            
            public static final int STATUS_D_VALUE = 1;
            public static final int STATUS_R_VALUE = 2;
            public static final int STATUS_S_VALUE = 3;
            public static final int STATUS_T_VALUE = 4;
            public static final int STATUS_UNKNOWN_VALUE = 0;
            public static final int STATUS_Z_VALUE = 5;
            private static final Internal.EnumLiteMap<Status> internalValueMap = new Internal.EnumLiteMap<Status>() {
                /* class android.os.CpuInfoProto.Task.Status.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Status findValueByNumber(int number) {
                    return Status.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Status valueOf(int value2) {
                return forNumber(value2);
            }

            public static Status forNumber(int value2) {
                if (value2 == 0) {
                    return STATUS_UNKNOWN;
                }
                if (value2 == 1) {
                    return STATUS_D;
                }
                if (value2 == 2) {
                    return STATUS_R;
                }
                if (value2 == 3) {
                    return STATUS_S;
                }
                if (value2 == 4) {
                    return STATUS_T;
                }
                if (value2 != 5) {
                    return null;
                }
                return STATUS_Z;
            }

            public static Internal.EnumLiteMap<Status> internalGetValueMap() {
                return internalValueMap;
            }

            private Status(int value2) {
                this.value = value2;
            }
        }

        public enum Policy implements Internal.EnumLite {
            POLICY_UNKNOWN(0),
            POLICY_fg(1),
            POLICY_bg(2),
            POLICY_ta(3);
            
            public static final int POLICY_UNKNOWN_VALUE = 0;
            public static final int POLICY_bg_VALUE = 2;
            public static final int POLICY_fg_VALUE = 1;
            public static final int POLICY_ta_VALUE = 3;
            private static final Internal.EnumLiteMap<Policy> internalValueMap = new Internal.EnumLiteMap<Policy>() {
                /* class android.os.CpuInfoProto.Task.Policy.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Policy findValueByNumber(int number) {
                    return Policy.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Policy valueOf(int value2) {
                return forNumber(value2);
            }

            public static Policy forNumber(int value2) {
                if (value2 == 0) {
                    return POLICY_UNKNOWN;
                }
                if (value2 == 1) {
                    return POLICY_fg;
                }
                if (value2 == 2) {
                    return POLICY_bg;
                }
                if (value2 != 3) {
                    return null;
                }
                return POLICY_ta;
            }

            public static Internal.EnumLiteMap<Policy> internalGetValueMap() {
                return internalValueMap;
            }

            private Policy(int value2) {
                this.value = value2;
            }
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasPid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public int getPid() {
            return this.pid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPid(int value) {
            this.bitField0_ |= 1;
            this.pid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPid() {
            this.bitField0_ &= -2;
            this.pid_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasTid() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public int getTid() {
            return this.tid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTid(int value) {
            this.bitField0_ |= 2;
            this.tid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTid() {
            this.bitField0_ &= -3;
            this.tid_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasUser() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public String getUser() {
            return this.user_;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public ByteString getUserBytes() {
            return ByteString.copyFromUtf8(this.user_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUser(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.user_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUser() {
            this.bitField0_ &= -5;
            this.user_ = getDefaultInstance().getUser();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUserBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.user_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasPr() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public String getPr() {
            return this.pr_;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public ByteString getPrBytes() {
            return ByteString.copyFromUtf8(this.pr_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPr(String value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.pr_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPr() {
            this.bitField0_ &= -9;
            this.pr_ = getDefaultInstance().getPr();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPrBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.pr_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasNi() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public int getNi() {
            return this.ni_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNi(int value) {
            this.bitField0_ |= 16;
            this.ni_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNi() {
            this.bitField0_ &= -17;
            this.ni_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasCpu() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public float getCpu() {
            return this.cpu_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCpu(float value) {
            this.bitField0_ |= 32;
            this.cpu_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCpu() {
            this.bitField0_ &= -33;
            this.cpu_ = 0.0f;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasS() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public Status getS() {
            Status result = Status.forNumber(this.s_);
            return result == null ? Status.STATUS_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setS(Status value) {
            if (value != null) {
                this.bitField0_ |= 64;
                this.s_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearS() {
            this.bitField0_ &= -65;
            this.s_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasVirt() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public String getVirt() {
            return this.virt_;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public ByteString getVirtBytes() {
            return ByteString.copyFromUtf8(this.virt_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVirt(String value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.virt_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVirt() {
            this.bitField0_ &= -129;
            this.virt_ = getDefaultInstance().getVirt();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVirtBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 128;
                this.virt_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasRes() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public String getRes() {
            return this.res_;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public ByteString getResBytes() {
            return ByteString.copyFromUtf8(this.res_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRes(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.res_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRes() {
            this.bitField0_ &= -257;
            this.res_ = getDefaultInstance().getRes();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setResBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.res_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasPcy() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public Policy getPcy() {
            Policy result = Policy.forNumber(this.pcy_);
            return result == null ? Policy.POLICY_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPcy(Policy value) {
            if (value != null) {
                this.bitField0_ |= 512;
                this.pcy_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPcy() {
            this.bitField0_ &= -513;
            this.pcy_ = 0;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasCmd() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public String getCmd() {
            return this.cmd_;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public ByteString getCmdBytes() {
            return ByteString.copyFromUtf8(this.cmd_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCmd(String value) {
            if (value != null) {
                this.bitField0_ |= 1024;
                this.cmd_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCmd() {
            this.bitField0_ &= -1025;
            this.cmd_ = getDefaultInstance().getCmd();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCmdBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1024;
                this.cmd_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.CpuInfoProto.TaskOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(String value) {
            if (value != null) {
                this.bitField0_ |= 2048;
                this.name_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearName() {
            this.bitField0_ &= -2049;
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2048;
                this.name_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.pid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.tid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getUser());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeString(4, getPr());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeSInt32(5, this.ni_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeFloat(6, this.cpu_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeEnum(7, this.s_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeString(8, getVirt());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getRes());
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeEnum(10, this.pcy_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeString(11, getCmd());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeString(12, getName());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.pid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.tid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getUser());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeStringSize(4, getPr());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeSInt32Size(5, this.ni_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeFloatSize(6, this.cpu_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeEnumSize(7, this.s_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeStringSize(8, getVirt());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getRes());
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeEnumSize(10, this.pcy_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeStringSize(11, getCmd());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeStringSize(12, getName());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Task parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Task) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Task parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Task) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Task parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Task) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Task parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Task) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Task parseFrom(InputStream input) throws IOException {
            return (Task) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Task parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Task) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Task parseDelimitedFrom(InputStream input) throws IOException {
            return (Task) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Task parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Task) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Task parseFrom(CodedInputStream input) throws IOException {
            return (Task) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Task parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Task) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Task prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Task, Builder> implements TaskOrBuilder {
            private Builder() {
                super(Task.DEFAULT_INSTANCE);
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasPid() {
                return ((Task) this.instance).hasPid();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public int getPid() {
                return ((Task) this.instance).getPid();
            }

            public Builder setPid(int value) {
                copyOnWrite();
                ((Task) this.instance).setPid(value);
                return this;
            }

            public Builder clearPid() {
                copyOnWrite();
                ((Task) this.instance).clearPid();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasTid() {
                return ((Task) this.instance).hasTid();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public int getTid() {
                return ((Task) this.instance).getTid();
            }

            public Builder setTid(int value) {
                copyOnWrite();
                ((Task) this.instance).setTid(value);
                return this;
            }

            public Builder clearTid() {
                copyOnWrite();
                ((Task) this.instance).clearTid();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasUser() {
                return ((Task) this.instance).hasUser();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public String getUser() {
                return ((Task) this.instance).getUser();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public ByteString getUserBytes() {
                return ((Task) this.instance).getUserBytes();
            }

            public Builder setUser(String value) {
                copyOnWrite();
                ((Task) this.instance).setUser(value);
                return this;
            }

            public Builder clearUser() {
                copyOnWrite();
                ((Task) this.instance).clearUser();
                return this;
            }

            public Builder setUserBytes(ByteString value) {
                copyOnWrite();
                ((Task) this.instance).setUserBytes(value);
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasPr() {
                return ((Task) this.instance).hasPr();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public String getPr() {
                return ((Task) this.instance).getPr();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public ByteString getPrBytes() {
                return ((Task) this.instance).getPrBytes();
            }

            public Builder setPr(String value) {
                copyOnWrite();
                ((Task) this.instance).setPr(value);
                return this;
            }

            public Builder clearPr() {
                copyOnWrite();
                ((Task) this.instance).clearPr();
                return this;
            }

            public Builder setPrBytes(ByteString value) {
                copyOnWrite();
                ((Task) this.instance).setPrBytes(value);
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasNi() {
                return ((Task) this.instance).hasNi();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public int getNi() {
                return ((Task) this.instance).getNi();
            }

            public Builder setNi(int value) {
                copyOnWrite();
                ((Task) this.instance).setNi(value);
                return this;
            }

            public Builder clearNi() {
                copyOnWrite();
                ((Task) this.instance).clearNi();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasCpu() {
                return ((Task) this.instance).hasCpu();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public float getCpu() {
                return ((Task) this.instance).getCpu();
            }

            public Builder setCpu(float value) {
                copyOnWrite();
                ((Task) this.instance).setCpu(value);
                return this;
            }

            public Builder clearCpu() {
                copyOnWrite();
                ((Task) this.instance).clearCpu();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasS() {
                return ((Task) this.instance).hasS();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public Status getS() {
                return ((Task) this.instance).getS();
            }

            public Builder setS(Status value) {
                copyOnWrite();
                ((Task) this.instance).setS(value);
                return this;
            }

            public Builder clearS() {
                copyOnWrite();
                ((Task) this.instance).clearS();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasVirt() {
                return ((Task) this.instance).hasVirt();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public String getVirt() {
                return ((Task) this.instance).getVirt();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public ByteString getVirtBytes() {
                return ((Task) this.instance).getVirtBytes();
            }

            public Builder setVirt(String value) {
                copyOnWrite();
                ((Task) this.instance).setVirt(value);
                return this;
            }

            public Builder clearVirt() {
                copyOnWrite();
                ((Task) this.instance).clearVirt();
                return this;
            }

            public Builder setVirtBytes(ByteString value) {
                copyOnWrite();
                ((Task) this.instance).setVirtBytes(value);
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasRes() {
                return ((Task) this.instance).hasRes();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public String getRes() {
                return ((Task) this.instance).getRes();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public ByteString getResBytes() {
                return ((Task) this.instance).getResBytes();
            }

            public Builder setRes(String value) {
                copyOnWrite();
                ((Task) this.instance).setRes(value);
                return this;
            }

            public Builder clearRes() {
                copyOnWrite();
                ((Task) this.instance).clearRes();
                return this;
            }

            public Builder setResBytes(ByteString value) {
                copyOnWrite();
                ((Task) this.instance).setResBytes(value);
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasPcy() {
                return ((Task) this.instance).hasPcy();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public Policy getPcy() {
                return ((Task) this.instance).getPcy();
            }

            public Builder setPcy(Policy value) {
                copyOnWrite();
                ((Task) this.instance).setPcy(value);
                return this;
            }

            public Builder clearPcy() {
                copyOnWrite();
                ((Task) this.instance).clearPcy();
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasCmd() {
                return ((Task) this.instance).hasCmd();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public String getCmd() {
                return ((Task) this.instance).getCmd();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public ByteString getCmdBytes() {
                return ((Task) this.instance).getCmdBytes();
            }

            public Builder setCmd(String value) {
                copyOnWrite();
                ((Task) this.instance).setCmd(value);
                return this;
            }

            public Builder clearCmd() {
                copyOnWrite();
                ((Task) this.instance).clearCmd();
                return this;
            }

            public Builder setCmdBytes(ByteString value) {
                copyOnWrite();
                ((Task) this.instance).setCmdBytes(value);
                return this;
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public boolean hasName() {
                return ((Task) this.instance).hasName();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public String getName() {
                return ((Task) this.instance).getName();
            }

            @Override // android.os.CpuInfoProto.TaskOrBuilder
            public ByteString getNameBytes() {
                return ((Task) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((Task) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Task) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((Task) this.instance).setNameBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Task();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Task other = (Task) arg1;
                    this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                    this.tid_ = visitor.visitInt(hasTid(), this.tid_, other.hasTid(), other.tid_);
                    this.user_ = visitor.visitString(hasUser(), this.user_, other.hasUser(), other.user_);
                    this.pr_ = visitor.visitString(hasPr(), this.pr_, other.hasPr(), other.pr_);
                    this.ni_ = visitor.visitInt(hasNi(), this.ni_, other.hasNi(), other.ni_);
                    this.cpu_ = visitor.visitFloat(hasCpu(), this.cpu_, other.hasCpu(), other.cpu_);
                    this.s_ = visitor.visitInt(hasS(), this.s_, other.hasS(), other.s_);
                    this.virt_ = visitor.visitString(hasVirt(), this.virt_, other.hasVirt(), other.virt_);
                    this.res_ = visitor.visitString(hasRes(), this.res_, other.hasRes(), other.res_);
                    this.pcy_ = visitor.visitInt(hasPcy(), this.pcy_, other.hasPcy(), other.pcy_);
                    this.cmd_ = visitor.visitString(hasCmd(), this.cmd_, other.hasCmd(), other.cmd_);
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.pid_ = input.readInt32();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.tid_ = input.readInt32();
                                    break;
                                case 26:
                                    String s = input.readString();
                                    this.bitField0_ |= 4;
                                    this.user_ = s;
                                    break;
                                case 34:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 8;
                                    this.pr_ = s2;
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.ni_ = input.readSInt32();
                                    break;
                                case 53:
                                    this.bitField0_ |= 32;
                                    this.cpu_ = input.readFloat();
                                    break;
                                case 56:
                                    int rawValue = input.readEnum();
                                    if (Status.forNumber(rawValue) != null) {
                                        this.bitField0_ |= 64;
                                        this.s_ = rawValue;
                                        break;
                                    } else {
                                        super.mergeVarintField(7, rawValue);
                                        break;
                                    }
                                case 66:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 128;
                                    this.virt_ = s3;
                                    break;
                                case 74:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 256;
                                    this.res_ = s4;
                                    break;
                                case 80:
                                    int rawValue2 = input.readEnum();
                                    if (Policy.forNumber(rawValue2) != null) {
                                        this.bitField0_ |= 512;
                                        this.pcy_ = rawValue2;
                                        break;
                                    } else {
                                        super.mergeVarintField(10, rawValue2);
                                        break;
                                    }
                                case 90:
                                    String s5 = input.readString();
                                    this.bitField0_ |= 1024;
                                    this.cmd_ = s5;
                                    break;
                                case 98:
                                    String s6 = input.readString();
                                    this.bitField0_ |= 2048;
                                    this.name_ = s6;
                                    break;
                                default:
                                    if (parseUnknownField(tag, input)) {
                                        break;
                                    } else {
                                        done = true;
                                        break;
                                    }
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
                        synchronized (Task.class) {
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

        public static Task getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Task> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public boolean hasTaskStats() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public TaskStats getTaskStats() {
        TaskStats taskStats = this.taskStats_;
        return taskStats == null ? TaskStats.getDefaultInstance() : taskStats;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTaskStats(TaskStats value) {
        if (value != null) {
            this.taskStats_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTaskStats(TaskStats.Builder builderForValue) {
        this.taskStats_ = (TaskStats) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTaskStats(TaskStats value) {
        TaskStats taskStats = this.taskStats_;
        if (taskStats == null || taskStats == TaskStats.getDefaultInstance()) {
            this.taskStats_ = value;
        } else {
            this.taskStats_ = (TaskStats) ((TaskStats.Builder) TaskStats.newBuilder(this.taskStats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTaskStats() {
        this.taskStats_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public boolean hasMem() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public MemStats getMem() {
        MemStats memStats = this.mem_;
        return memStats == null ? MemStats.getDefaultInstance() : memStats;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMem(MemStats value) {
        if (value != null) {
            this.mem_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMem(MemStats.Builder builderForValue) {
        this.mem_ = (MemStats) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMem(MemStats value) {
        MemStats memStats = this.mem_;
        if (memStats == null || memStats == MemStats.getDefaultInstance()) {
            this.mem_ = value;
        } else {
            this.mem_ = (MemStats) ((MemStats.Builder) MemStats.newBuilder(this.mem_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMem() {
        this.mem_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public boolean hasSwap() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public MemStats getSwap() {
        MemStats memStats = this.swap_;
        return memStats == null ? MemStats.getDefaultInstance() : memStats;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSwap(MemStats value) {
        if (value != null) {
            this.swap_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSwap(MemStats.Builder builderForValue) {
        this.swap_ = (MemStats) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSwap(MemStats value) {
        MemStats memStats = this.swap_;
        if (memStats == null || memStats == MemStats.getDefaultInstance()) {
            this.swap_ = value;
        } else {
            this.swap_ = (MemStats) ((MemStats.Builder) MemStats.newBuilder(this.swap_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSwap() {
        this.swap_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public boolean hasCpuUsage() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public CpuUsage getCpuUsage() {
        CpuUsage cpuUsage = this.cpuUsage_;
        return cpuUsage == null ? CpuUsage.getDefaultInstance() : cpuUsage;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuUsage(CpuUsage value) {
        if (value != null) {
            this.cpuUsage_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuUsage(CpuUsage.Builder builderForValue) {
        this.cpuUsage_ = (CpuUsage) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCpuUsage(CpuUsage value) {
        CpuUsage cpuUsage = this.cpuUsage_;
        if (cpuUsage == null || cpuUsage == CpuUsage.getDefaultInstance()) {
            this.cpuUsage_ = value;
        } else {
            this.cpuUsage_ = (CpuUsage) ((CpuUsage.Builder) CpuUsage.newBuilder(this.cpuUsage_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuUsage() {
        this.cpuUsage_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public List<Task> getTasksList() {
        return this.tasks_;
    }

    public List<? extends TaskOrBuilder> getTasksOrBuilderList() {
        return this.tasks_;
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public int getTasksCount() {
        return this.tasks_.size();
    }

    @Override // android.os.CpuInfoProtoOrBuilder
    public Task getTasks(int index) {
        return this.tasks_.get(index);
    }

    public TaskOrBuilder getTasksOrBuilder(int index) {
        return this.tasks_.get(index);
    }

    private void ensureTasksIsMutable() {
        if (!this.tasks_.isModifiable()) {
            this.tasks_ = GeneratedMessageLite.mutableCopy(this.tasks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTasks(int index, Task value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTasks(int index, Task.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.set(index, (Task) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(Task value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(int index, Task value) {
        if (value != null) {
            ensureTasksIsMutable();
            this.tasks_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(Task.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.add((Task) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTasks(int index, Task.Builder builderForValue) {
        ensureTasksIsMutable();
        this.tasks_.add(index, (Task) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTasks(Iterable<? extends Task> values) {
        ensureTasksIsMutable();
        AbstractMessageLite.addAll(values, this.tasks_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTasks() {
        this.tasks_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTasks(int index) {
        ensureTasksIsMutable();
        this.tasks_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getTaskStats());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getMem());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getSwap());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getCpuUsage());
        }
        for (int i = 0; i < this.tasks_.size(); i++) {
            output.writeMessage(5, this.tasks_.get(i));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getTaskStats());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getMem());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getSwap());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getCpuUsage());
        }
        for (int i = 0; i < this.tasks_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.tasks_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static CpuInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (CpuInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CpuInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CpuInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CpuInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (CpuInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CpuInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CpuInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CpuInfoProto parseFrom(InputStream input) throws IOException {
        return (CpuInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CpuInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CpuInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CpuInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (CpuInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static CpuInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CpuInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CpuInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (CpuInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CpuInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CpuInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CpuInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CpuInfoProto, Builder> implements CpuInfoProtoOrBuilder {
        private Builder() {
            super(CpuInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public boolean hasTaskStats() {
            return ((CpuInfoProto) this.instance).hasTaskStats();
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public TaskStats getTaskStats() {
            return ((CpuInfoProto) this.instance).getTaskStats();
        }

        public Builder setTaskStats(TaskStats value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setTaskStats((CpuInfoProto) value);
            return this;
        }

        public Builder setTaskStats(TaskStats.Builder builderForValue) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setTaskStats((CpuInfoProto) builderForValue);
            return this;
        }

        public Builder mergeTaskStats(TaskStats value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).mergeTaskStats(value);
            return this;
        }

        public Builder clearTaskStats() {
            copyOnWrite();
            ((CpuInfoProto) this.instance).clearTaskStats();
            return this;
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public boolean hasMem() {
            return ((CpuInfoProto) this.instance).hasMem();
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public MemStats getMem() {
            return ((CpuInfoProto) this.instance).getMem();
        }

        public Builder setMem(MemStats value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setMem((CpuInfoProto) value);
            return this;
        }

        public Builder setMem(MemStats.Builder builderForValue) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setMem((CpuInfoProto) builderForValue);
            return this;
        }

        public Builder mergeMem(MemStats value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).mergeMem(value);
            return this;
        }

        public Builder clearMem() {
            copyOnWrite();
            ((CpuInfoProto) this.instance).clearMem();
            return this;
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public boolean hasSwap() {
            return ((CpuInfoProto) this.instance).hasSwap();
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public MemStats getSwap() {
            return ((CpuInfoProto) this.instance).getSwap();
        }

        public Builder setSwap(MemStats value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setSwap((CpuInfoProto) value);
            return this;
        }

        public Builder setSwap(MemStats.Builder builderForValue) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setSwap((CpuInfoProto) builderForValue);
            return this;
        }

        public Builder mergeSwap(MemStats value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).mergeSwap(value);
            return this;
        }

        public Builder clearSwap() {
            copyOnWrite();
            ((CpuInfoProto) this.instance).clearSwap();
            return this;
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public boolean hasCpuUsage() {
            return ((CpuInfoProto) this.instance).hasCpuUsage();
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public CpuUsage getCpuUsage() {
            return ((CpuInfoProto) this.instance).getCpuUsage();
        }

        public Builder setCpuUsage(CpuUsage value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setCpuUsage((CpuInfoProto) value);
            return this;
        }

        public Builder setCpuUsage(CpuUsage.Builder builderForValue) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setCpuUsage((CpuInfoProto) builderForValue);
            return this;
        }

        public Builder mergeCpuUsage(CpuUsage value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).mergeCpuUsage(value);
            return this;
        }

        public Builder clearCpuUsage() {
            copyOnWrite();
            ((CpuInfoProto) this.instance).clearCpuUsage();
            return this;
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public List<Task> getTasksList() {
            return Collections.unmodifiableList(((CpuInfoProto) this.instance).getTasksList());
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public int getTasksCount() {
            return ((CpuInfoProto) this.instance).getTasksCount();
        }

        @Override // android.os.CpuInfoProtoOrBuilder
        public Task getTasks(int index) {
            return ((CpuInfoProto) this.instance).getTasks(index);
        }

        public Builder setTasks(int index, Task value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setTasks((CpuInfoProto) index, (int) value);
            return this;
        }

        public Builder setTasks(int index, Task.Builder builderForValue) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).setTasks((CpuInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTasks(Task value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).addTasks((CpuInfoProto) value);
            return this;
        }

        public Builder addTasks(int index, Task value) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).addTasks((CpuInfoProto) index, (int) value);
            return this;
        }

        public Builder addTasks(Task.Builder builderForValue) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).addTasks((CpuInfoProto) builderForValue);
            return this;
        }

        public Builder addTasks(int index, Task.Builder builderForValue) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).addTasks((CpuInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTasks(Iterable<? extends Task> values) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).addAllTasks(values);
            return this;
        }

        public Builder clearTasks() {
            copyOnWrite();
            ((CpuInfoProto) this.instance).clearTasks();
            return this;
        }

        public Builder removeTasks(int index) {
            copyOnWrite();
            ((CpuInfoProto) this.instance).removeTasks(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new CpuInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.tasks_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                CpuInfoProto other = (CpuInfoProto) arg1;
                this.taskStats_ = (TaskStats) visitor.visitMessage(this.taskStats_, other.taskStats_);
                this.mem_ = (MemStats) visitor.visitMessage(this.mem_, other.mem_);
                this.swap_ = (MemStats) visitor.visitMessage(this.swap_, other.swap_);
                this.cpuUsage_ = (CpuUsage) visitor.visitMessage(this.cpuUsage_, other.cpuUsage_);
                this.tasks_ = visitor.visitList(this.tasks_, other.tasks_);
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
                            TaskStats.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (TaskStats.Builder) this.taskStats_.toBuilder();
                            }
                            this.taskStats_ = (TaskStats) input.readMessage(TaskStats.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.taskStats_);
                                this.taskStats_ = (TaskStats) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            MemStats.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (MemStats.Builder) this.mem_.toBuilder();
                            }
                            this.mem_ = (MemStats) input.readMessage(MemStats.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.mem_);
                                this.mem_ = (MemStats) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            MemStats.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (MemStats.Builder) this.swap_.toBuilder();
                            }
                            this.swap_ = (MemStats) input.readMessage(MemStats.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.swap_);
                                this.swap_ = (MemStats) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            CpuUsage.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder4 = (CpuUsage.Builder) this.cpuUsage_.toBuilder();
                            }
                            this.cpuUsage_ = (CpuUsage) input.readMessage(CpuUsage.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.cpuUsage_);
                                this.cpuUsage_ = (CpuUsage) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (tag == 42) {
                            if (!this.tasks_.isModifiable()) {
                                this.tasks_ = GeneratedMessageLite.mutableCopy(this.tasks_);
                            }
                            this.tasks_.add((Task) input.readMessage(Task.parser(), extensionRegistry));
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
                    synchronized (CpuInfoProto.class) {
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

    public static CpuInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CpuInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
