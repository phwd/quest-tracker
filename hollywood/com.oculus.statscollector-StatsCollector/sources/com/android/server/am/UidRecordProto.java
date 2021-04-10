package com.android.server.am;

import android.app.ProcessStateEnum;
import android.util.Duration;
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
import java.util.List;

public final class UidRecordProto extends GeneratedMessageLite<UidRecordProto, Builder> implements UidRecordProtoOrBuilder {
    public static final int CURRENT_FIELD_NUMBER = 2;
    private static final UidRecordProto DEFAULT_INSTANCE = new UidRecordProto();
    public static final int EPHEMERAL_FIELD_NUMBER = 3;
    public static final int FG_SERVICES_FIELD_NUMBER = 4;
    public static final int IDLE_FIELD_NUMBER = 7;
    public static final int LAST_BACKGROUND_TIME_FIELD_NUMBER = 6;
    public static final int LAST_REPORTED_CHANGES_FIELD_NUMBER = 8;
    public static final int NETWORK_STATE_UPDATE_FIELD_NUMBER = 10;
    public static final int NUM_PROCS_FIELD_NUMBER = 9;
    private static volatile Parser<UidRecordProto> PARSER = null;
    public static final int UID_FIELD_NUMBER = 1;
    public static final int WHILELIST_FIELD_NUMBER = 5;
    private static final Internal.ListAdapter.Converter<Integer, Change> lastReportedChanges_converter_ = new Internal.ListAdapter.Converter<Integer, Change>() {
        /* class com.android.server.am.UidRecordProto.AnonymousClass1 */

        public Change convert(Integer from) {
            Change result = Change.forNumber(from.intValue());
            return result == null ? Change.CHANGE_GONE : result;
        }
    };
    private int bitField0_;
    private int current_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
    private boolean ephemeral_ = false;
    private boolean fgServices_ = false;
    private boolean idle_ = false;
    private Duration lastBackgroundTime_;
    private Internal.IntList lastReportedChanges_ = emptyIntList();
    private ProcStateSequence networkStateUpdate_;
    private int numProcs_ = 0;
    private int uid_ = 0;
    private boolean whilelist_ = false;

    public interface ProcStateSequenceOrBuilder extends MessageLiteOrBuilder {
        long getCururent();

        long getLastDispatched();

        long getLastNetworkUpdated();

        boolean hasCururent();

        boolean hasLastDispatched();

        boolean hasLastNetworkUpdated();
    }

    private UidRecordProto() {
    }

    public enum Change implements Internal.EnumLite {
        CHANGE_GONE(0),
        CHANGE_IDLE(1),
        CHANGE_ACTIVE(2),
        CHANGE_CACHED(3),
        CHANGE_UNCACHED(4);
        
        public static final int CHANGE_ACTIVE_VALUE = 2;
        public static final int CHANGE_CACHED_VALUE = 3;
        public static final int CHANGE_GONE_VALUE = 0;
        public static final int CHANGE_IDLE_VALUE = 1;
        public static final int CHANGE_UNCACHED_VALUE = 4;
        private static final Internal.EnumLiteMap<Change> internalValueMap = new Internal.EnumLiteMap<Change>() {
            /* class com.android.server.am.UidRecordProto.Change.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Change findValueByNumber(int number) {
                return Change.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Change valueOf(int value2) {
            return forNumber(value2);
        }

        public static Change forNumber(int value2) {
            if (value2 == 0) {
                return CHANGE_GONE;
            }
            if (value2 == 1) {
                return CHANGE_IDLE;
            }
            if (value2 == 2) {
                return CHANGE_ACTIVE;
            }
            if (value2 == 3) {
                return CHANGE_CACHED;
            }
            if (value2 != 4) {
                return null;
            }
            return CHANGE_UNCACHED;
        }

        public static Internal.EnumLiteMap<Change> internalGetValueMap() {
            return internalValueMap;
        }

        private Change(int value2) {
            this.value = value2;
        }
    }

    public static final class ProcStateSequence extends GeneratedMessageLite<ProcStateSequence, Builder> implements ProcStateSequenceOrBuilder {
        public static final int CURURENT_FIELD_NUMBER = 1;
        private static final ProcStateSequence DEFAULT_INSTANCE = new ProcStateSequence();
        public static final int LAST_DISPATCHED_FIELD_NUMBER = 3;
        public static final int LAST_NETWORK_UPDATED_FIELD_NUMBER = 2;
        private static volatile Parser<ProcStateSequence> PARSER;
        private int bitField0_;
        private long cururent_ = 0;
        private long lastDispatched_ = 0;
        private long lastNetworkUpdated_ = 0;

        private ProcStateSequence() {
        }

        @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
        public boolean hasCururent() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
        public long getCururent() {
            return this.cururent_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCururent(long value) {
            this.bitField0_ |= 1;
            this.cururent_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCururent() {
            this.bitField0_ &= -2;
            this.cururent_ = 0;
        }

        @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
        public boolean hasLastNetworkUpdated() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
        public long getLastNetworkUpdated() {
            return this.lastNetworkUpdated_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastNetworkUpdated(long value) {
            this.bitField0_ |= 2;
            this.lastNetworkUpdated_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastNetworkUpdated() {
            this.bitField0_ &= -3;
            this.lastNetworkUpdated_ = 0;
        }

        @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
        public boolean hasLastDispatched() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
        public long getLastDispatched() {
            return this.lastDispatched_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastDispatched(long value) {
            this.bitField0_ |= 4;
            this.lastDispatched_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastDispatched() {
            this.bitField0_ &= -5;
            this.lastDispatched_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt64(1, this.cururent_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.lastNetworkUpdated_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.lastDispatched_);
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
                size2 = 0 + CodedOutputStream.computeInt64Size(1, this.cururent_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.lastNetworkUpdated_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.lastDispatched_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ProcStateSequence parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ProcStateSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProcStateSequence parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProcStateSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProcStateSequence parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ProcStateSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProcStateSequence parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProcStateSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProcStateSequence parseFrom(InputStream input) throws IOException {
            return (ProcStateSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcStateSequence parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcStateSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProcStateSequence parseDelimitedFrom(InputStream input) throws IOException {
            return (ProcStateSequence) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcStateSequence parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcStateSequence) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProcStateSequence parseFrom(CodedInputStream input) throws IOException {
            return (ProcStateSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcStateSequence parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcStateSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ProcStateSequence prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ProcStateSequence, Builder> implements ProcStateSequenceOrBuilder {
            private Builder() {
                super(ProcStateSequence.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
            public boolean hasCururent() {
                return ((ProcStateSequence) this.instance).hasCururent();
            }

            @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
            public long getCururent() {
                return ((ProcStateSequence) this.instance).getCururent();
            }

            public Builder setCururent(long value) {
                copyOnWrite();
                ((ProcStateSequence) this.instance).setCururent(value);
                return this;
            }

            public Builder clearCururent() {
                copyOnWrite();
                ((ProcStateSequence) this.instance).clearCururent();
                return this;
            }

            @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
            public boolean hasLastNetworkUpdated() {
                return ((ProcStateSequence) this.instance).hasLastNetworkUpdated();
            }

            @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
            public long getLastNetworkUpdated() {
                return ((ProcStateSequence) this.instance).getLastNetworkUpdated();
            }

            public Builder setLastNetworkUpdated(long value) {
                copyOnWrite();
                ((ProcStateSequence) this.instance).setLastNetworkUpdated(value);
                return this;
            }

            public Builder clearLastNetworkUpdated() {
                copyOnWrite();
                ((ProcStateSequence) this.instance).clearLastNetworkUpdated();
                return this;
            }

            @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
            public boolean hasLastDispatched() {
                return ((ProcStateSequence) this.instance).hasLastDispatched();
            }

            @Override // com.android.server.am.UidRecordProto.ProcStateSequenceOrBuilder
            public long getLastDispatched() {
                return ((ProcStateSequence) this.instance).getLastDispatched();
            }

            public Builder setLastDispatched(long value) {
                copyOnWrite();
                ((ProcStateSequence) this.instance).setLastDispatched(value);
                return this;
            }

            public Builder clearLastDispatched() {
                copyOnWrite();
                ((ProcStateSequence) this.instance).clearLastDispatched();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ProcStateSequence();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ProcStateSequence other = (ProcStateSequence) arg1;
                    this.cururent_ = visitor.visitLong(hasCururent(), this.cururent_, other.hasCururent(), other.cururent_);
                    this.lastNetworkUpdated_ = visitor.visitLong(hasLastNetworkUpdated(), this.lastNetworkUpdated_, other.hasLastNetworkUpdated(), other.lastNetworkUpdated_);
                    this.lastDispatched_ = visitor.visitLong(hasLastDispatched(), this.lastDispatched_, other.hasLastDispatched(), other.lastDispatched_);
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
                                this.cururent_ = input.readInt64();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.lastNetworkUpdated_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.lastDispatched_ = input.readInt64();
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
                        synchronized (ProcStateSequence.class) {
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

        public static ProcStateSequence getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ProcStateSequence> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
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

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasCurrent() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public ProcessStateEnum getCurrent() {
        ProcessStateEnum result = ProcessStateEnum.forNumber(this.current_);
        return result == null ? ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrent(ProcessStateEnum value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.current_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrent() {
        this.bitField0_ &= -3;
        this.current_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasEphemeral() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean getEphemeral() {
        return this.ephemeral_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEphemeral(boolean value) {
        this.bitField0_ |= 4;
        this.ephemeral_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEphemeral() {
        this.bitField0_ &= -5;
        this.ephemeral_ = false;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasFgServices() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean getFgServices() {
        return this.fgServices_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFgServices(boolean value) {
        this.bitField0_ |= 8;
        this.fgServices_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFgServices() {
        this.bitField0_ &= -9;
        this.fgServices_ = false;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasWhilelist() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean getWhilelist() {
        return this.whilelist_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhilelist(boolean value) {
        this.bitField0_ |= 16;
        this.whilelist_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWhilelist() {
        this.bitField0_ &= -17;
        this.whilelist_ = false;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasLastBackgroundTime() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public Duration getLastBackgroundTime() {
        Duration duration = this.lastBackgroundTime_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastBackgroundTime(Duration value) {
        if (value != null) {
            this.lastBackgroundTime_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastBackgroundTime(Duration.Builder builderForValue) {
        this.lastBackgroundTime_ = (Duration) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLastBackgroundTime(Duration value) {
        Duration duration = this.lastBackgroundTime_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.lastBackgroundTime_ = value;
        } else {
            this.lastBackgroundTime_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.lastBackgroundTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastBackgroundTime() {
        this.lastBackgroundTime_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasIdle() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean getIdle() {
        return this.idle_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdle(boolean value) {
        this.bitField0_ |= 64;
        this.idle_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIdle() {
        this.bitField0_ &= -65;
        this.idle_ = false;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public List<Change> getLastReportedChangesList() {
        return new Internal.ListAdapter(this.lastReportedChanges_, lastReportedChanges_converter_);
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public int getLastReportedChangesCount() {
        return this.lastReportedChanges_.size();
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public Change getLastReportedChanges(int index) {
        return lastReportedChanges_converter_.convert(Integer.valueOf(this.lastReportedChanges_.getInt(index)));
    }

    private void ensureLastReportedChangesIsMutable() {
        if (!this.lastReportedChanges_.isModifiable()) {
            this.lastReportedChanges_ = GeneratedMessageLite.mutableCopy(this.lastReportedChanges_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastReportedChanges(int index, Change value) {
        if (value != null) {
            ensureLastReportedChangesIsMutable();
            this.lastReportedChanges_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLastReportedChanges(Change value) {
        if (value != null) {
            ensureLastReportedChangesIsMutable();
            this.lastReportedChanges_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllLastReportedChanges(Iterable<? extends Change> values) {
        ensureLastReportedChangesIsMutable();
        for (Change value : values) {
            this.lastReportedChanges_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastReportedChanges() {
        this.lastReportedChanges_ = emptyIntList();
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasNumProcs() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public int getNumProcs() {
        return this.numProcs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumProcs(int value) {
        this.bitField0_ |= 128;
        this.numProcs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNumProcs() {
        this.bitField0_ &= -129;
        this.numProcs_ = 0;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public boolean hasNetworkStateUpdate() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.am.UidRecordProtoOrBuilder
    public ProcStateSequence getNetworkStateUpdate() {
        ProcStateSequence procStateSequence = this.networkStateUpdate_;
        return procStateSequence == null ? ProcStateSequence.getDefaultInstance() : procStateSequence;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkStateUpdate(ProcStateSequence value) {
        if (value != null) {
            this.networkStateUpdate_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetworkStateUpdate(ProcStateSequence.Builder builderForValue) {
        this.networkStateUpdate_ = (ProcStateSequence) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNetworkStateUpdate(ProcStateSequence value) {
        ProcStateSequence procStateSequence = this.networkStateUpdate_;
        if (procStateSequence == null || procStateSequence == ProcStateSequence.getDefaultInstance()) {
            this.networkStateUpdate_ = value;
        } else {
            this.networkStateUpdate_ = (ProcStateSequence) ((ProcStateSequence.Builder) ProcStateSequence.newBuilder(this.networkStateUpdate_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetworkStateUpdate() {
        this.networkStateUpdate_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.uid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.current_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(3, this.ephemeral_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.fgServices_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.whilelist_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getLastBackgroundTime());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.idle_);
        }
        for (int i = 0; i < this.lastReportedChanges_.size(); i++) {
            output.writeEnum(8, this.lastReportedChanges_.getInt(i));
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(9, this.numProcs_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(10, getNetworkStateUpdate());
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
            size2 += CodedOutputStream.computeEnumSize(2, this.current_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(3, this.ephemeral_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.fgServices_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.whilelist_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getLastBackgroundTime());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.idle_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.lastReportedChanges_.size(); i++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.lastReportedChanges_.getInt(i));
        }
        int size3 = size2 + dataSize + (this.lastReportedChanges_.size() * 1);
        if ((this.bitField0_ & 128) == 128) {
            size3 += CodedOutputStream.computeInt32Size(9, this.numProcs_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size3 += CodedOutputStream.computeMessageSize(10, getNetworkStateUpdate());
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static UidRecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (UidRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UidRecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UidRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UidRecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (UidRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static UidRecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (UidRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static UidRecordProto parseFrom(InputStream input) throws IOException {
        return (UidRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UidRecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UidRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UidRecordProto parseDelimitedFrom(InputStream input) throws IOException {
        return (UidRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static UidRecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UidRecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static UidRecordProto parseFrom(CodedInputStream input) throws IOException {
        return (UidRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static UidRecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (UidRecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UidRecordProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UidRecordProto, Builder> implements UidRecordProtoOrBuilder {
        private Builder() {
            super(UidRecordProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasUid() {
            return ((UidRecordProto) this.instance).hasUid();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public int getUid() {
            return ((UidRecordProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearUid();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasCurrent() {
            return ((UidRecordProto) this.instance).hasCurrent();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public ProcessStateEnum getCurrent() {
            return ((UidRecordProto) this.instance).getCurrent();
        }

        public Builder setCurrent(ProcessStateEnum value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setCurrent(value);
            return this;
        }

        public Builder clearCurrent() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearCurrent();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasEphemeral() {
            return ((UidRecordProto) this.instance).hasEphemeral();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean getEphemeral() {
            return ((UidRecordProto) this.instance).getEphemeral();
        }

        public Builder setEphemeral(boolean value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setEphemeral(value);
            return this;
        }

        public Builder clearEphemeral() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearEphemeral();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasFgServices() {
            return ((UidRecordProto) this.instance).hasFgServices();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean getFgServices() {
            return ((UidRecordProto) this.instance).getFgServices();
        }

        public Builder setFgServices(boolean value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setFgServices(value);
            return this;
        }

        public Builder clearFgServices() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearFgServices();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasWhilelist() {
            return ((UidRecordProto) this.instance).hasWhilelist();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean getWhilelist() {
            return ((UidRecordProto) this.instance).getWhilelist();
        }

        public Builder setWhilelist(boolean value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setWhilelist(value);
            return this;
        }

        public Builder clearWhilelist() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearWhilelist();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasLastBackgroundTime() {
            return ((UidRecordProto) this.instance).hasLastBackgroundTime();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public Duration getLastBackgroundTime() {
            return ((UidRecordProto) this.instance).getLastBackgroundTime();
        }

        public Builder setLastBackgroundTime(Duration value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setLastBackgroundTime((UidRecordProto) value);
            return this;
        }

        public Builder setLastBackgroundTime(Duration.Builder builderForValue) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setLastBackgroundTime((UidRecordProto) builderForValue);
            return this;
        }

        public Builder mergeLastBackgroundTime(Duration value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).mergeLastBackgroundTime(value);
            return this;
        }

        public Builder clearLastBackgroundTime() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearLastBackgroundTime();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasIdle() {
            return ((UidRecordProto) this.instance).hasIdle();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean getIdle() {
            return ((UidRecordProto) this.instance).getIdle();
        }

        public Builder setIdle(boolean value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setIdle(value);
            return this;
        }

        public Builder clearIdle() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearIdle();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public List<Change> getLastReportedChangesList() {
            return ((UidRecordProto) this.instance).getLastReportedChangesList();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public int getLastReportedChangesCount() {
            return ((UidRecordProto) this.instance).getLastReportedChangesCount();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public Change getLastReportedChanges(int index) {
            return ((UidRecordProto) this.instance).getLastReportedChanges(index);
        }

        public Builder setLastReportedChanges(int index, Change value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setLastReportedChanges(index, value);
            return this;
        }

        public Builder addLastReportedChanges(Change value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).addLastReportedChanges(value);
            return this;
        }

        public Builder addAllLastReportedChanges(Iterable<? extends Change> values) {
            copyOnWrite();
            ((UidRecordProto) this.instance).addAllLastReportedChanges(values);
            return this;
        }

        public Builder clearLastReportedChanges() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearLastReportedChanges();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasNumProcs() {
            return ((UidRecordProto) this.instance).hasNumProcs();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public int getNumProcs() {
            return ((UidRecordProto) this.instance).getNumProcs();
        }

        public Builder setNumProcs(int value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setNumProcs(value);
            return this;
        }

        public Builder clearNumProcs() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearNumProcs();
            return this;
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public boolean hasNetworkStateUpdate() {
            return ((UidRecordProto) this.instance).hasNetworkStateUpdate();
        }

        @Override // com.android.server.am.UidRecordProtoOrBuilder
        public ProcStateSequence getNetworkStateUpdate() {
            return ((UidRecordProto) this.instance).getNetworkStateUpdate();
        }

        public Builder setNetworkStateUpdate(ProcStateSequence value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setNetworkStateUpdate((UidRecordProto) value);
            return this;
        }

        public Builder setNetworkStateUpdate(ProcStateSequence.Builder builderForValue) {
            copyOnWrite();
            ((UidRecordProto) this.instance).setNetworkStateUpdate((UidRecordProto) builderForValue);
            return this;
        }

        public Builder mergeNetworkStateUpdate(ProcStateSequence value) {
            copyOnWrite();
            ((UidRecordProto) this.instance).mergeNetworkStateUpdate(value);
            return this;
        }

        public Builder clearNetworkStateUpdate() {
            copyOnWrite();
            ((UidRecordProto) this.instance).clearNetworkStateUpdate();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new UidRecordProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.lastReportedChanges_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                UidRecordProto other = (UidRecordProto) arg1;
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.current_ = visitor.visitInt(hasCurrent(), this.current_, other.hasCurrent(), other.current_);
                this.ephemeral_ = visitor.visitBoolean(hasEphemeral(), this.ephemeral_, other.hasEphemeral(), other.ephemeral_);
                this.fgServices_ = visitor.visitBoolean(hasFgServices(), this.fgServices_, other.hasFgServices(), other.fgServices_);
                this.whilelist_ = visitor.visitBoolean(hasWhilelist(), this.whilelist_, other.hasWhilelist(), other.whilelist_);
                this.lastBackgroundTime_ = (Duration) visitor.visitMessage(this.lastBackgroundTime_, other.lastBackgroundTime_);
                this.idle_ = visitor.visitBoolean(hasIdle(), this.idle_, other.hasIdle(), other.idle_);
                this.lastReportedChanges_ = visitor.visitIntList(this.lastReportedChanges_, other.lastReportedChanges_);
                this.numProcs_ = visitor.visitInt(hasNumProcs(), this.numProcs_, other.hasNumProcs(), other.numProcs_);
                this.networkStateUpdate_ = (ProcStateSequence) visitor.visitMessage(this.networkStateUpdate_, other.networkStateUpdate_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.uid_ = input.readInt32();
                                break;
                            case 16:
                                int rawValue = input.readEnum();
                                if (ProcessStateEnum.forNumber(rawValue) != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.current_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(2, rawValue);
                                    break;
                                }
                            case 24:
                                this.bitField0_ |= 4;
                                this.ephemeral_ = input.readBool();
                                break;
                            case 32:
                                this.bitField0_ = 8 | this.bitField0_;
                                this.fgServices_ = input.readBool();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.whilelist_ = input.readBool();
                                break;
                            case 50:
                                Duration.Builder subBuilder = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder = (Duration.Builder) this.lastBackgroundTime_.toBuilder();
                                }
                                this.lastBackgroundTime_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.lastBackgroundTime_);
                                    this.lastBackgroundTime_ = (Duration) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.idle_ = input.readBool();
                                break;
                            case 64:
                                if (!this.lastReportedChanges_.isModifiable()) {
                                    this.lastReportedChanges_ = GeneratedMessageLite.mutableCopy(this.lastReportedChanges_);
                                }
                                int rawValue2 = input.readEnum();
                                if (Change.forNumber(rawValue2) != null) {
                                    this.lastReportedChanges_.addInt(rawValue2);
                                    break;
                                } else {
                                    super.mergeVarintField(8, rawValue2);
                                    break;
                                }
                            case 66:
                                if (!this.lastReportedChanges_.isModifiable()) {
                                    this.lastReportedChanges_ = GeneratedMessageLite.mutableCopy(this.lastReportedChanges_);
                                }
                                int oldLimit = input.pushLimit(input.readRawVarint32());
                                while (input.getBytesUntilLimit() > 0) {
                                    int rawValue3 = input.readEnum();
                                    if (Change.forNumber(rawValue3) == null) {
                                        super.mergeVarintField(8, rawValue3);
                                    } else {
                                        this.lastReportedChanges_.addInt(rawValue3);
                                    }
                                }
                                input.popLimit(oldLimit);
                                break;
                            case 72:
                                this.bitField0_ |= 128;
                                this.numProcs_ = input.readInt32();
                                break;
                            case 82:
                                ProcStateSequence.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder2 = (ProcStateSequence.Builder) this.networkStateUpdate_.toBuilder();
                                }
                                this.networkStateUpdate_ = (ProcStateSequence) input.readMessage(ProcStateSequence.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.networkStateUpdate_);
                                    this.networkStateUpdate_ = (ProcStateSequence) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 256;
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
                    synchronized (UidRecordProto.class) {
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

    public static UidRecordProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UidRecordProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
