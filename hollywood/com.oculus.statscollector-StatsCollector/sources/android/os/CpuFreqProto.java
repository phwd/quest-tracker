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

public final class CpuFreqProto extends GeneratedMessageLite<CpuFreqProto, Builder> implements CpuFreqProtoOrBuilder {
    public static final int CPU_FREQS_FIELD_NUMBER = 2;
    private static final CpuFreqProto DEFAULT_INSTANCE = new CpuFreqProto();
    public static final int JIFFY_HZ_FIELD_NUMBER = 1;
    private static volatile Parser<CpuFreqProto> PARSER;
    private int bitField0_;
    private Internal.ProtobufList<Stats> cpuFreqs_ = emptyProtobufList();
    private int jiffyHz_ = 0;

    public interface StatsOrBuilder extends MessageLiteOrBuilder {
        String getCpuName();

        ByteString getCpuNameBytes();

        Stats.TimeInState getTimes(int i);

        int getTimesCount();

        List<Stats.TimeInState> getTimesList();

        boolean hasCpuName();
    }

    private CpuFreqProto() {
    }

    public static final class Stats extends GeneratedMessageLite<Stats, Builder> implements StatsOrBuilder {
        public static final int CPU_NAME_FIELD_NUMBER = 1;
        private static final Stats DEFAULT_INSTANCE = new Stats();
        private static volatile Parser<Stats> PARSER = null;
        public static final int TIMES_FIELD_NUMBER = 2;
        private int bitField0_;
        private String cpuName_ = "";
        private Internal.ProtobufList<TimeInState> times_ = emptyProtobufList();

        public interface TimeInStateOrBuilder extends MessageLiteOrBuilder {
            int getStateKhz();

            long getTimeJiffy();

            boolean hasStateKhz();

            boolean hasTimeJiffy();
        }

        private Stats() {
        }

        public static final class TimeInState extends GeneratedMessageLite<TimeInState, Builder> implements TimeInStateOrBuilder {
            private static final TimeInState DEFAULT_INSTANCE = new TimeInState();
            private static volatile Parser<TimeInState> PARSER = null;
            public static final int STATE_KHZ_FIELD_NUMBER = 1;
            public static final int TIME_JIFFY_FIELD_NUMBER = 2;
            private int bitField0_;
            private int stateKhz_ = 0;
            private long timeJiffy_ = 0;

            private TimeInState() {
            }

            @Override // android.os.CpuFreqProto.Stats.TimeInStateOrBuilder
            public boolean hasStateKhz() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // android.os.CpuFreqProto.Stats.TimeInStateOrBuilder
            public int getStateKhz() {
                return this.stateKhz_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStateKhz(int value) {
                this.bitField0_ |= 1;
                this.stateKhz_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStateKhz() {
                this.bitField0_ &= -2;
                this.stateKhz_ = 0;
            }

            @Override // android.os.CpuFreqProto.Stats.TimeInStateOrBuilder
            public boolean hasTimeJiffy() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // android.os.CpuFreqProto.Stats.TimeInStateOrBuilder
            public long getTimeJiffy() {
                return this.timeJiffy_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setTimeJiffy(long value) {
                this.bitField0_ |= 2;
                this.timeJiffy_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearTimeJiffy() {
                this.bitField0_ &= -3;
                this.timeJiffy_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.stateKhz_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.timeJiffy_);
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
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.stateKhz_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt64Size(2, this.timeJiffy_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static TimeInState parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (TimeInState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TimeInState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TimeInState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TimeInState parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (TimeInState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static TimeInState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (TimeInState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static TimeInState parseFrom(InputStream input) throws IOException {
                return (TimeInState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TimeInState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TimeInState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TimeInState parseDelimitedFrom(InputStream input) throws IOException {
                return (TimeInState) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static TimeInState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TimeInState) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static TimeInState parseFrom(CodedInputStream input) throws IOException {
                return (TimeInState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static TimeInState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (TimeInState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(TimeInState prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<TimeInState, Builder> implements TimeInStateOrBuilder {
                private Builder() {
                    super(TimeInState.DEFAULT_INSTANCE);
                }

                @Override // android.os.CpuFreqProto.Stats.TimeInStateOrBuilder
                public boolean hasStateKhz() {
                    return ((TimeInState) this.instance).hasStateKhz();
                }

                @Override // android.os.CpuFreqProto.Stats.TimeInStateOrBuilder
                public int getStateKhz() {
                    return ((TimeInState) this.instance).getStateKhz();
                }

                public Builder setStateKhz(int value) {
                    copyOnWrite();
                    ((TimeInState) this.instance).setStateKhz(value);
                    return this;
                }

                public Builder clearStateKhz() {
                    copyOnWrite();
                    ((TimeInState) this.instance).clearStateKhz();
                    return this;
                }

                @Override // android.os.CpuFreqProto.Stats.TimeInStateOrBuilder
                public boolean hasTimeJiffy() {
                    return ((TimeInState) this.instance).hasTimeJiffy();
                }

                @Override // android.os.CpuFreqProto.Stats.TimeInStateOrBuilder
                public long getTimeJiffy() {
                    return ((TimeInState) this.instance).getTimeJiffy();
                }

                public Builder setTimeJiffy(long value) {
                    copyOnWrite();
                    ((TimeInState) this.instance).setTimeJiffy(value);
                    return this;
                }

                public Builder clearTimeJiffy() {
                    copyOnWrite();
                    ((TimeInState) this.instance).clearTimeJiffy();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new TimeInState();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        TimeInState other = (TimeInState) arg1;
                        this.stateKhz_ = visitor.visitInt(hasStateKhz(), this.stateKhz_, other.hasStateKhz(), other.stateKhz_);
                        this.timeJiffy_ = visitor.visitLong(hasTimeJiffy(), this.timeJiffy_, other.hasTimeJiffy(), other.timeJiffy_);
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
                                    this.stateKhz_ = input.readInt32();
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.timeJiffy_ = input.readInt64();
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
                            synchronized (TimeInState.class) {
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

            public static TimeInState getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<TimeInState> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // android.os.CpuFreqProto.StatsOrBuilder
        public boolean hasCpuName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.CpuFreqProto.StatsOrBuilder
        public String getCpuName() {
            return this.cpuName_;
        }

        @Override // android.os.CpuFreqProto.StatsOrBuilder
        public ByteString getCpuNameBytes() {
            return ByteString.copyFromUtf8(this.cpuName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCpuName(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.cpuName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCpuName() {
            this.bitField0_ &= -2;
            this.cpuName_ = getDefaultInstance().getCpuName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCpuNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.cpuName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.CpuFreqProto.StatsOrBuilder
        public List<TimeInState> getTimesList() {
            return this.times_;
        }

        public List<? extends TimeInStateOrBuilder> getTimesOrBuilderList() {
            return this.times_;
        }

        @Override // android.os.CpuFreqProto.StatsOrBuilder
        public int getTimesCount() {
            return this.times_.size();
        }

        @Override // android.os.CpuFreqProto.StatsOrBuilder
        public TimeInState getTimes(int index) {
            return this.times_.get(index);
        }

        public TimeInStateOrBuilder getTimesOrBuilder(int index) {
            return this.times_.get(index);
        }

        private void ensureTimesIsMutable() {
            if (!this.times_.isModifiable()) {
                this.times_ = GeneratedMessageLite.mutableCopy(this.times_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimes(int index, TimeInState value) {
            if (value != null) {
                ensureTimesIsMutable();
                this.times_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimes(int index, TimeInState.Builder builderForValue) {
            ensureTimesIsMutable();
            this.times_.set(index, (TimeInState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTimes(TimeInState value) {
            if (value != null) {
                ensureTimesIsMutable();
                this.times_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTimes(int index, TimeInState value) {
            if (value != null) {
                ensureTimesIsMutable();
                this.times_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTimes(TimeInState.Builder builderForValue) {
            ensureTimesIsMutable();
            this.times_.add((TimeInState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addTimes(int index, TimeInState.Builder builderForValue) {
            ensureTimesIsMutable();
            this.times_.add(index, (TimeInState) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllTimes(Iterable<? extends TimeInState> values) {
            ensureTimesIsMutable();
            AbstractMessageLite.addAll(values, this.times_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimes() {
            this.times_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeTimes(int index) {
            ensureTimesIsMutable();
            this.times_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getCpuName());
            }
            for (int i = 0; i < this.times_.size(); i++) {
                output.writeMessage(2, this.times_.get(i));
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getCpuName());
            }
            for (int i = 0; i < this.times_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.times_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Stats parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Stats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Stats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Stats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Stats parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Stats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Stats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Stats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Stats parseFrom(InputStream input) throws IOException {
            return (Stats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Stats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Stats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Stats parseDelimitedFrom(InputStream input) throws IOException {
            return (Stats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Stats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Stats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Stats parseFrom(CodedInputStream input) throws IOException {
            return (Stats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Stats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Stats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Stats prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Stats, Builder> implements StatsOrBuilder {
            private Builder() {
                super(Stats.DEFAULT_INSTANCE);
            }

            @Override // android.os.CpuFreqProto.StatsOrBuilder
            public boolean hasCpuName() {
                return ((Stats) this.instance).hasCpuName();
            }

            @Override // android.os.CpuFreqProto.StatsOrBuilder
            public String getCpuName() {
                return ((Stats) this.instance).getCpuName();
            }

            @Override // android.os.CpuFreqProto.StatsOrBuilder
            public ByteString getCpuNameBytes() {
                return ((Stats) this.instance).getCpuNameBytes();
            }

            public Builder setCpuName(String value) {
                copyOnWrite();
                ((Stats) this.instance).setCpuName(value);
                return this;
            }

            public Builder clearCpuName() {
                copyOnWrite();
                ((Stats) this.instance).clearCpuName();
                return this;
            }

            public Builder setCpuNameBytes(ByteString value) {
                copyOnWrite();
                ((Stats) this.instance).setCpuNameBytes(value);
                return this;
            }

            @Override // android.os.CpuFreqProto.StatsOrBuilder
            public List<TimeInState> getTimesList() {
                return Collections.unmodifiableList(((Stats) this.instance).getTimesList());
            }

            @Override // android.os.CpuFreqProto.StatsOrBuilder
            public int getTimesCount() {
                return ((Stats) this.instance).getTimesCount();
            }

            @Override // android.os.CpuFreqProto.StatsOrBuilder
            public TimeInState getTimes(int index) {
                return ((Stats) this.instance).getTimes(index);
            }

            public Builder setTimes(int index, TimeInState value) {
                copyOnWrite();
                ((Stats) this.instance).setTimes((Stats) index, (int) value);
                return this;
            }

            public Builder setTimes(int index, TimeInState.Builder builderForValue) {
                copyOnWrite();
                ((Stats) this.instance).setTimes((Stats) index, (int) builderForValue);
                return this;
            }

            public Builder addTimes(TimeInState value) {
                copyOnWrite();
                ((Stats) this.instance).addTimes((Stats) value);
                return this;
            }

            public Builder addTimes(int index, TimeInState value) {
                copyOnWrite();
                ((Stats) this.instance).addTimes((Stats) index, (int) value);
                return this;
            }

            public Builder addTimes(TimeInState.Builder builderForValue) {
                copyOnWrite();
                ((Stats) this.instance).addTimes((Stats) builderForValue);
                return this;
            }

            public Builder addTimes(int index, TimeInState.Builder builderForValue) {
                copyOnWrite();
                ((Stats) this.instance).addTimes((Stats) index, (int) builderForValue);
                return this;
            }

            public Builder addAllTimes(Iterable<? extends TimeInState> values) {
                copyOnWrite();
                ((Stats) this.instance).addAllTimes(values);
                return this;
            }

            public Builder clearTimes() {
                copyOnWrite();
                ((Stats) this.instance).clearTimes();
                return this;
            }

            public Builder removeTimes(int index) {
                copyOnWrite();
                ((Stats) this.instance).removeTimes(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Stats();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.times_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Stats other = (Stats) arg1;
                    this.cpuName_ = visitor.visitString(hasCpuName(), this.cpuName_, other.hasCpuName(), other.cpuName_);
                    this.times_ = visitor.visitList(this.times_, other.times_);
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
                                this.cpuName_ = s;
                            } else if (tag == 18) {
                                if (!this.times_.isModifiable()) {
                                    this.times_ = GeneratedMessageLite.mutableCopy(this.times_);
                                }
                                this.times_.add((TimeInState) input.readMessage(TimeInState.parser(), extensionRegistry));
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
                        synchronized (Stats.class) {
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

        public static Stats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Stats> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.CpuFreqProtoOrBuilder
    public boolean hasJiffyHz() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.CpuFreqProtoOrBuilder
    public int getJiffyHz() {
        return this.jiffyHz_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJiffyHz(int value) {
        this.bitField0_ |= 1;
        this.jiffyHz_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJiffyHz() {
        this.bitField0_ &= -2;
        this.jiffyHz_ = 0;
    }

    @Override // android.os.CpuFreqProtoOrBuilder
    public List<Stats> getCpuFreqsList() {
        return this.cpuFreqs_;
    }

    public List<? extends StatsOrBuilder> getCpuFreqsOrBuilderList() {
        return this.cpuFreqs_;
    }

    @Override // android.os.CpuFreqProtoOrBuilder
    public int getCpuFreqsCount() {
        return this.cpuFreqs_.size();
    }

    @Override // android.os.CpuFreqProtoOrBuilder
    public Stats getCpuFreqs(int index) {
        return this.cpuFreqs_.get(index);
    }

    public StatsOrBuilder getCpuFreqsOrBuilder(int index) {
        return this.cpuFreqs_.get(index);
    }

    private void ensureCpuFreqsIsMutable() {
        if (!this.cpuFreqs_.isModifiable()) {
            this.cpuFreqs_ = GeneratedMessageLite.mutableCopy(this.cpuFreqs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuFreqs(int index, Stats value) {
        if (value != null) {
            ensureCpuFreqsIsMutable();
            this.cpuFreqs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuFreqs(int index, Stats.Builder builderForValue) {
        ensureCpuFreqsIsMutable();
        this.cpuFreqs_.set(index, (Stats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuFreqs(Stats value) {
        if (value != null) {
            ensureCpuFreqsIsMutable();
            this.cpuFreqs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuFreqs(int index, Stats value) {
        if (value != null) {
            ensureCpuFreqsIsMutable();
            this.cpuFreqs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuFreqs(Stats.Builder builderForValue) {
        ensureCpuFreqsIsMutable();
        this.cpuFreqs_.add((Stats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuFreqs(int index, Stats.Builder builderForValue) {
        ensureCpuFreqsIsMutable();
        this.cpuFreqs_.add(index, (Stats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCpuFreqs(Iterable<? extends Stats> values) {
        ensureCpuFreqsIsMutable();
        AbstractMessageLite.addAll(values, this.cpuFreqs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuFreqs() {
        this.cpuFreqs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeCpuFreqs(int index) {
        ensureCpuFreqsIsMutable();
        this.cpuFreqs_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.jiffyHz_);
        }
        for (int i = 0; i < this.cpuFreqs_.size(); i++) {
            output.writeMessage(2, this.cpuFreqs_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.jiffyHz_);
        }
        for (int i = 0; i < this.cpuFreqs_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.cpuFreqs_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static CpuFreqProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (CpuFreqProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CpuFreqProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CpuFreqProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CpuFreqProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (CpuFreqProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CpuFreqProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CpuFreqProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CpuFreqProto parseFrom(InputStream input) throws IOException {
        return (CpuFreqProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CpuFreqProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CpuFreqProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CpuFreqProto parseDelimitedFrom(InputStream input) throws IOException {
        return (CpuFreqProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static CpuFreqProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CpuFreqProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CpuFreqProto parseFrom(CodedInputStream input) throws IOException {
        return (CpuFreqProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CpuFreqProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CpuFreqProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CpuFreqProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CpuFreqProto, Builder> implements CpuFreqProtoOrBuilder {
        private Builder() {
            super(CpuFreqProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.CpuFreqProtoOrBuilder
        public boolean hasJiffyHz() {
            return ((CpuFreqProto) this.instance).hasJiffyHz();
        }

        @Override // android.os.CpuFreqProtoOrBuilder
        public int getJiffyHz() {
            return ((CpuFreqProto) this.instance).getJiffyHz();
        }

        public Builder setJiffyHz(int value) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).setJiffyHz(value);
            return this;
        }

        public Builder clearJiffyHz() {
            copyOnWrite();
            ((CpuFreqProto) this.instance).clearJiffyHz();
            return this;
        }

        @Override // android.os.CpuFreqProtoOrBuilder
        public List<Stats> getCpuFreqsList() {
            return Collections.unmodifiableList(((CpuFreqProto) this.instance).getCpuFreqsList());
        }

        @Override // android.os.CpuFreqProtoOrBuilder
        public int getCpuFreqsCount() {
            return ((CpuFreqProto) this.instance).getCpuFreqsCount();
        }

        @Override // android.os.CpuFreqProtoOrBuilder
        public Stats getCpuFreqs(int index) {
            return ((CpuFreqProto) this.instance).getCpuFreqs(index);
        }

        public Builder setCpuFreqs(int index, Stats value) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).setCpuFreqs((CpuFreqProto) index, (int) value);
            return this;
        }

        public Builder setCpuFreqs(int index, Stats.Builder builderForValue) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).setCpuFreqs((CpuFreqProto) index, (int) builderForValue);
            return this;
        }

        public Builder addCpuFreqs(Stats value) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).addCpuFreqs((CpuFreqProto) value);
            return this;
        }

        public Builder addCpuFreqs(int index, Stats value) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).addCpuFreqs((CpuFreqProto) index, (int) value);
            return this;
        }

        public Builder addCpuFreqs(Stats.Builder builderForValue) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).addCpuFreqs((CpuFreqProto) builderForValue);
            return this;
        }

        public Builder addCpuFreqs(int index, Stats.Builder builderForValue) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).addCpuFreqs((CpuFreqProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllCpuFreqs(Iterable<? extends Stats> values) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).addAllCpuFreqs(values);
            return this;
        }

        public Builder clearCpuFreqs() {
            copyOnWrite();
            ((CpuFreqProto) this.instance).clearCpuFreqs();
            return this;
        }

        public Builder removeCpuFreqs(int index) {
            copyOnWrite();
            ((CpuFreqProto) this.instance).removeCpuFreqs(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new CpuFreqProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.cpuFreqs_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                CpuFreqProto other = (CpuFreqProto) arg1;
                this.jiffyHz_ = visitor.visitInt(hasJiffyHz(), this.jiffyHz_, other.hasJiffyHz(), other.jiffyHz_);
                this.cpuFreqs_ = visitor.visitList(this.cpuFreqs_, other.cpuFreqs_);
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
                            this.jiffyHz_ = input.readInt32();
                        } else if (tag == 18) {
                            if (!this.cpuFreqs_.isModifiable()) {
                                this.cpuFreqs_ = GeneratedMessageLite.mutableCopy(this.cpuFreqs_);
                            }
                            this.cpuFreqs_.add((Stats) input.readMessage(Stats.parser(), extensionRegistry));
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
                    synchronized (CpuFreqProto.class) {
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

    public static CpuFreqProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CpuFreqProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
