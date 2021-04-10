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

public final class JobPackageHistoryProto extends GeneratedMessageLite<JobPackageHistoryProto, Builder> implements JobPackageHistoryProtoOrBuilder {
    private static final JobPackageHistoryProto DEFAULT_INSTANCE = new JobPackageHistoryProto();
    public static final int HISTORY_EVENT_FIELD_NUMBER = 1;
    private static volatile Parser<JobPackageHistoryProto> PARSER;
    private Internal.ProtobufList<HistoryEvent> historyEvent_ = emptyProtobufList();

    public interface HistoryEventOrBuilder extends MessageLiteOrBuilder {
        Event getEvent();

        int getJobId();

        StopReasonEnum getStopReason();

        String getTag();

        ByteString getTagBytes();

        long getTimeSinceEventMs();

        int getUid();

        boolean hasEvent();

        boolean hasJobId();

        boolean hasStopReason();

        boolean hasTag();

        boolean hasTimeSinceEventMs();

        boolean hasUid();
    }

    private JobPackageHistoryProto() {
    }

    public enum Event implements Internal.EnumLite {
        UNKNOWN(0),
        START_JOB(1),
        STOP_JOB(2),
        START_PERIODIC_JOB(3),
        STOP_PERIODIC_JOB(4);
        
        public static final int START_JOB_VALUE = 1;
        public static final int START_PERIODIC_JOB_VALUE = 3;
        public static final int STOP_JOB_VALUE = 2;
        public static final int STOP_PERIODIC_JOB_VALUE = 4;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<Event> internalValueMap = new Internal.EnumLiteMap<Event>() {
            /* class com.android.server.job.JobPackageHistoryProto.Event.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Event findValueByNumber(int number) {
                return Event.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Event valueOf(int value2) {
            return forNumber(value2);
        }

        public static Event forNumber(int value2) {
            if (value2 == 0) {
                return UNKNOWN;
            }
            if (value2 == 1) {
                return START_JOB;
            }
            if (value2 == 2) {
                return STOP_JOB;
            }
            if (value2 == 3) {
                return START_PERIODIC_JOB;
            }
            if (value2 != 4) {
                return null;
            }
            return STOP_PERIODIC_JOB;
        }

        public static Internal.EnumLiteMap<Event> internalGetValueMap() {
            return internalValueMap;
        }

        private Event(int value2) {
            this.value = value2;
        }
    }

    public static final class HistoryEvent extends GeneratedMessageLite<HistoryEvent, Builder> implements HistoryEventOrBuilder {
        private static final HistoryEvent DEFAULT_INSTANCE = new HistoryEvent();
        public static final int EVENT_FIELD_NUMBER = 1;
        public static final int JOB_ID_FIELD_NUMBER = 4;
        private static volatile Parser<HistoryEvent> PARSER = null;
        public static final int STOP_REASON_FIELD_NUMBER = 6;
        public static final int TAG_FIELD_NUMBER = 5;
        public static final int TIME_SINCE_EVENT_MS_FIELD_NUMBER = 2;
        public static final int UID_FIELD_NUMBER = 3;
        private int bitField0_;
        private int event_ = 0;
        private int jobId_ = 0;
        private int stopReason_ = -1;
        private String tag_ = "";
        private long timeSinceEventMs_ = 0;
        private int uid_ = 0;

        private HistoryEvent() {
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public boolean hasEvent() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public Event getEvent() {
            Event result = Event.forNumber(this.event_);
            return result == null ? Event.UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEvent(Event value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.event_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEvent() {
            this.bitField0_ &= -2;
            this.event_ = 0;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public boolean hasTimeSinceEventMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public long getTimeSinceEventMs() {
            return this.timeSinceEventMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimeSinceEventMs(long value) {
            this.bitField0_ |= 2;
            this.timeSinceEventMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimeSinceEventMs() {
            this.bitField0_ &= -3;
            this.timeSinceEventMs_ = 0;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 4;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -5;
            this.uid_ = 0;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public boolean hasJobId() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public int getJobId() {
            return this.jobId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setJobId(int value) {
            this.bitField0_ |= 8;
            this.jobId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearJobId() {
            this.bitField0_ &= -9;
            this.jobId_ = 0;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public boolean hasTag() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public String getTag() {
            return this.tag_;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public ByteString getTagBytes() {
            return ByteString.copyFromUtf8(this.tag_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTag(String value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.tag_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTag() {
            this.bitField0_ &= -17;
            this.tag_ = getDefaultInstance().getTag();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTagBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.tag_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public boolean hasStopReason() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
        public StopReasonEnum getStopReason() {
            StopReasonEnum result = StopReasonEnum.forNumber(this.stopReason_);
            return result == null ? StopReasonEnum.STOP_REASON_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStopReason(StopReasonEnum value) {
            if (value != null) {
                this.bitField0_ |= 32;
                this.stopReason_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStopReason() {
            this.bitField0_ &= -33;
            this.stopReason_ = -1;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.event_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.timeSinceEventMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.uid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.jobId_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeString(5, getTag());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeEnum(6, this.stopReason_);
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.event_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.timeSinceEventMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.uid_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.jobId_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeStringSize(5, getTag());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeEnumSize(6, this.stopReason_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static HistoryEvent parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (HistoryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HistoryEvent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HistoryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HistoryEvent parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (HistoryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HistoryEvent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HistoryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HistoryEvent parseFrom(InputStream input) throws IOException {
            return (HistoryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HistoryEvent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HistoryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HistoryEvent parseDelimitedFrom(InputStream input) throws IOException {
            return (HistoryEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static HistoryEvent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HistoryEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HistoryEvent parseFrom(CodedInputStream input) throws IOException {
            return (HistoryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HistoryEvent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HistoryEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(HistoryEvent prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<HistoryEvent, Builder> implements HistoryEventOrBuilder {
            private Builder() {
                super(HistoryEvent.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public boolean hasEvent() {
                return ((HistoryEvent) this.instance).hasEvent();
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public Event getEvent() {
                return ((HistoryEvent) this.instance).getEvent();
            }

            public Builder setEvent(Event value) {
                copyOnWrite();
                ((HistoryEvent) this.instance).setEvent(value);
                return this;
            }

            public Builder clearEvent() {
                copyOnWrite();
                ((HistoryEvent) this.instance).clearEvent();
                return this;
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public boolean hasTimeSinceEventMs() {
                return ((HistoryEvent) this.instance).hasTimeSinceEventMs();
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public long getTimeSinceEventMs() {
                return ((HistoryEvent) this.instance).getTimeSinceEventMs();
            }

            public Builder setTimeSinceEventMs(long value) {
                copyOnWrite();
                ((HistoryEvent) this.instance).setTimeSinceEventMs(value);
                return this;
            }

            public Builder clearTimeSinceEventMs() {
                copyOnWrite();
                ((HistoryEvent) this.instance).clearTimeSinceEventMs();
                return this;
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public boolean hasUid() {
                return ((HistoryEvent) this.instance).hasUid();
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public int getUid() {
                return ((HistoryEvent) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((HistoryEvent) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((HistoryEvent) this.instance).clearUid();
                return this;
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public boolean hasJobId() {
                return ((HistoryEvent) this.instance).hasJobId();
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public int getJobId() {
                return ((HistoryEvent) this.instance).getJobId();
            }

            public Builder setJobId(int value) {
                copyOnWrite();
                ((HistoryEvent) this.instance).setJobId(value);
                return this;
            }

            public Builder clearJobId() {
                copyOnWrite();
                ((HistoryEvent) this.instance).clearJobId();
                return this;
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public boolean hasTag() {
                return ((HistoryEvent) this.instance).hasTag();
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public String getTag() {
                return ((HistoryEvent) this.instance).getTag();
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public ByteString getTagBytes() {
                return ((HistoryEvent) this.instance).getTagBytes();
            }

            public Builder setTag(String value) {
                copyOnWrite();
                ((HistoryEvent) this.instance).setTag(value);
                return this;
            }

            public Builder clearTag() {
                copyOnWrite();
                ((HistoryEvent) this.instance).clearTag();
                return this;
            }

            public Builder setTagBytes(ByteString value) {
                copyOnWrite();
                ((HistoryEvent) this.instance).setTagBytes(value);
                return this;
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public boolean hasStopReason() {
                return ((HistoryEvent) this.instance).hasStopReason();
            }

            @Override // com.android.server.job.JobPackageHistoryProto.HistoryEventOrBuilder
            public StopReasonEnum getStopReason() {
                return ((HistoryEvent) this.instance).getStopReason();
            }

            public Builder setStopReason(StopReasonEnum value) {
                copyOnWrite();
                ((HistoryEvent) this.instance).setStopReason(value);
                return this;
            }

            public Builder clearStopReason() {
                copyOnWrite();
                ((HistoryEvent) this.instance).clearStopReason();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new HistoryEvent();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    HistoryEvent other = (HistoryEvent) arg1;
                    this.event_ = visitor.visitInt(hasEvent(), this.event_, other.hasEvent(), other.event_);
                    this.timeSinceEventMs_ = visitor.visitLong(hasTimeSinceEventMs(), this.timeSinceEventMs_, other.hasTimeSinceEventMs(), other.timeSinceEventMs_);
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.jobId_ = visitor.visitInt(hasJobId(), this.jobId_, other.hasJobId(), other.jobId_);
                    this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                    this.stopReason_ = visitor.visitInt(hasStopReason(), this.stopReason_, other.hasStopReason(), other.stopReason_);
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
                                if (Event.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.event_ = rawValue;
                                }
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.timeSinceEventMs_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.uid_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.jobId_ = input.readInt32();
                            } else if (tag == 42) {
                                String s = input.readString();
                                this.bitField0_ = 16 | this.bitField0_;
                                this.tag_ = s;
                            } else if (tag == 48) {
                                int rawValue2 = input.readEnum();
                                if (StopReasonEnum.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(6, rawValue2);
                                } else {
                                    this.bitField0_ = 32 | this.bitField0_;
                                    this.stopReason_ = rawValue2;
                                }
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
                        synchronized (HistoryEvent.class) {
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

        public static HistoryEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HistoryEvent> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.job.JobPackageHistoryProtoOrBuilder
    public List<HistoryEvent> getHistoryEventList() {
        return this.historyEvent_;
    }

    public List<? extends HistoryEventOrBuilder> getHistoryEventOrBuilderList() {
        return this.historyEvent_;
    }

    @Override // com.android.server.job.JobPackageHistoryProtoOrBuilder
    public int getHistoryEventCount() {
        return this.historyEvent_.size();
    }

    @Override // com.android.server.job.JobPackageHistoryProtoOrBuilder
    public HistoryEvent getHistoryEvent(int index) {
        return this.historyEvent_.get(index);
    }

    public HistoryEventOrBuilder getHistoryEventOrBuilder(int index) {
        return this.historyEvent_.get(index);
    }

    private void ensureHistoryEventIsMutable() {
        if (!this.historyEvent_.isModifiable()) {
            this.historyEvent_ = GeneratedMessageLite.mutableCopy(this.historyEvent_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoryEvent(int index, HistoryEvent value) {
        if (value != null) {
            ensureHistoryEventIsMutable();
            this.historyEvent_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoryEvent(int index, HistoryEvent.Builder builderForValue) {
        ensureHistoryEventIsMutable();
        this.historyEvent_.set(index, (HistoryEvent) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoryEvent(HistoryEvent value) {
        if (value != null) {
            ensureHistoryEventIsMutable();
            this.historyEvent_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoryEvent(int index, HistoryEvent value) {
        if (value != null) {
            ensureHistoryEventIsMutable();
            this.historyEvent_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoryEvent(HistoryEvent.Builder builderForValue) {
        ensureHistoryEventIsMutable();
        this.historyEvent_.add((HistoryEvent) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoryEvent(int index, HistoryEvent.Builder builderForValue) {
        ensureHistoryEventIsMutable();
        this.historyEvent_.add(index, (HistoryEvent) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllHistoryEvent(Iterable<? extends HistoryEvent> values) {
        ensureHistoryEventIsMutable();
        AbstractMessageLite.addAll(values, this.historyEvent_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHistoryEvent() {
        this.historyEvent_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeHistoryEvent(int index) {
        ensureHistoryEventIsMutable();
        this.historyEvent_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.historyEvent_.size(); i++) {
            output.writeMessage(1, this.historyEvent_.get(i));
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
        for (int i = 0; i < this.historyEvent_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.historyEvent_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static JobPackageHistoryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (JobPackageHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobPackageHistoryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobPackageHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobPackageHistoryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (JobPackageHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static JobPackageHistoryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (JobPackageHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static JobPackageHistoryProto parseFrom(InputStream input) throws IOException {
        return (JobPackageHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobPackageHistoryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobPackageHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobPackageHistoryProto parseDelimitedFrom(InputStream input) throws IOException {
        return (JobPackageHistoryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static JobPackageHistoryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobPackageHistoryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static JobPackageHistoryProto parseFrom(CodedInputStream input) throws IOException {
        return (JobPackageHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static JobPackageHistoryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (JobPackageHistoryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JobPackageHistoryProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JobPackageHistoryProto, Builder> implements JobPackageHistoryProtoOrBuilder {
        private Builder() {
            super(JobPackageHistoryProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.job.JobPackageHistoryProtoOrBuilder
        public List<HistoryEvent> getHistoryEventList() {
            return Collections.unmodifiableList(((JobPackageHistoryProto) this.instance).getHistoryEventList());
        }

        @Override // com.android.server.job.JobPackageHistoryProtoOrBuilder
        public int getHistoryEventCount() {
            return ((JobPackageHistoryProto) this.instance).getHistoryEventCount();
        }

        @Override // com.android.server.job.JobPackageHistoryProtoOrBuilder
        public HistoryEvent getHistoryEvent(int index) {
            return ((JobPackageHistoryProto) this.instance).getHistoryEvent(index);
        }

        public Builder setHistoryEvent(int index, HistoryEvent value) {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).setHistoryEvent((JobPackageHistoryProto) index, (int) value);
            return this;
        }

        public Builder setHistoryEvent(int index, HistoryEvent.Builder builderForValue) {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).setHistoryEvent((JobPackageHistoryProto) index, (int) builderForValue);
            return this;
        }

        public Builder addHistoryEvent(HistoryEvent value) {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).addHistoryEvent((JobPackageHistoryProto) value);
            return this;
        }

        public Builder addHistoryEvent(int index, HistoryEvent value) {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).addHistoryEvent((JobPackageHistoryProto) index, (int) value);
            return this;
        }

        public Builder addHistoryEvent(HistoryEvent.Builder builderForValue) {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).addHistoryEvent((JobPackageHistoryProto) builderForValue);
            return this;
        }

        public Builder addHistoryEvent(int index, HistoryEvent.Builder builderForValue) {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).addHistoryEvent((JobPackageHistoryProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllHistoryEvent(Iterable<? extends HistoryEvent> values) {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).addAllHistoryEvent(values);
            return this;
        }

        public Builder clearHistoryEvent() {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).clearHistoryEvent();
            return this;
        }

        public Builder removeHistoryEvent(int index) {
            copyOnWrite();
            ((JobPackageHistoryProto) this.instance).removeHistoryEvent(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new JobPackageHistoryProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.historyEvent_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.historyEvent_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.historyEvent_, ((JobPackageHistoryProto) arg1).historyEvent_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                            if (!this.historyEvent_.isModifiable()) {
                                this.historyEvent_ = GeneratedMessageLite.mutableCopy(this.historyEvent_);
                            }
                            this.historyEvent_.add((HistoryEvent) input.readMessage(HistoryEvent.parser(), extensionRegistry));
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
                    synchronized (JobPackageHistoryProto.class) {
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

    public static JobPackageHistoryProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JobPackageHistoryProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
