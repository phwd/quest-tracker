package com.android.server;

import android.app.AlarmManagerProto;
import android.app.PendingIntentProto;
import android.os.WorkSourceProto;
import com.android.server.BroadcastStatsProto;
import com.android.server.FilterStatsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class InFlightProto extends GeneratedMessageLite<InFlightProto, Builder> implements InFlightProtoOrBuilder {
    public static final int ALARM_TYPE_FIELD_NUMBER = 4;
    public static final int BROADCAST_STATS_FIELD_NUMBER = 6;
    private static final InFlightProto DEFAULT_INSTANCE = new InFlightProto();
    public static final int FILTER_STATS_FIELD_NUMBER = 7;
    private static volatile Parser<InFlightProto> PARSER = null;
    public static final int PENDING_INTENT_FIELD_NUMBER = 5;
    public static final int TAG_FIELD_NUMBER = 2;
    public static final int UID_FIELD_NUMBER = 1;
    public static final int WHEN_ELAPSED_MS_FIELD_NUMBER = 3;
    public static final int WORK_SOURCE_FIELD_NUMBER = 8;
    private int alarmType_ = 0;
    private int bitField0_;
    private BroadcastStatsProto broadcastStats_;
    private FilterStatsProto filterStats_;
    private PendingIntentProto pendingIntent_;
    private String tag_ = "";
    private int uid_ = 0;
    private long whenElapsedMs_ = 0;
    private WorkSourceProto workSource_;

    private InFlightProto() {
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
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

    @Override // com.android.server.InFlightProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public ByteString getTagBytes() {
        return ByteString.copyFromUtf8(this.tag_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTag(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.tag_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTag() {
        this.bitField0_ &= -3;
        this.tag_ = getDefaultInstance().getTag();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.tag_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public boolean hasWhenElapsedMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public long getWhenElapsedMs() {
        return this.whenElapsedMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhenElapsedMs(long value) {
        this.bitField0_ |= 4;
        this.whenElapsedMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWhenElapsedMs() {
        this.bitField0_ &= -5;
        this.whenElapsedMs_ = 0;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public boolean hasAlarmType() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public AlarmManagerProto.AlarmType getAlarmType() {
        AlarmManagerProto.AlarmType result = AlarmManagerProto.AlarmType.forNumber(this.alarmType_);
        return result == null ? AlarmManagerProto.AlarmType.RTC_WAKEUP : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarmType(AlarmManagerProto.AlarmType value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.alarmType_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlarmType() {
        this.bitField0_ &= -9;
        this.alarmType_ = 0;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public boolean hasPendingIntent() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public PendingIntentProto getPendingIntent() {
        PendingIntentProto pendingIntentProto = this.pendingIntent_;
        return pendingIntentProto == null ? PendingIntentProto.getDefaultInstance() : pendingIntentProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingIntent(PendingIntentProto value) {
        if (value != null) {
            this.pendingIntent_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPendingIntent(PendingIntentProto.Builder builderForValue) {
        this.pendingIntent_ = (PendingIntentProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePendingIntent(PendingIntentProto value) {
        PendingIntentProto pendingIntentProto = this.pendingIntent_;
        if (pendingIntentProto == null || pendingIntentProto == PendingIntentProto.getDefaultInstance()) {
            this.pendingIntent_ = value;
        } else {
            this.pendingIntent_ = (PendingIntentProto) ((PendingIntentProto.Builder) PendingIntentProto.newBuilder(this.pendingIntent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPendingIntent() {
        this.pendingIntent_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public boolean hasBroadcastStats() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public BroadcastStatsProto getBroadcastStats() {
        BroadcastStatsProto broadcastStatsProto = this.broadcastStats_;
        return broadcastStatsProto == null ? BroadcastStatsProto.getDefaultInstance() : broadcastStatsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcastStats(BroadcastStatsProto value) {
        if (value != null) {
            this.broadcastStats_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcastStats(BroadcastStatsProto.Builder builderForValue) {
        this.broadcastStats_ = (BroadcastStatsProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBroadcastStats(BroadcastStatsProto value) {
        BroadcastStatsProto broadcastStatsProto = this.broadcastStats_;
        if (broadcastStatsProto == null || broadcastStatsProto == BroadcastStatsProto.getDefaultInstance()) {
            this.broadcastStats_ = value;
        } else {
            this.broadcastStats_ = (BroadcastStatsProto) ((BroadcastStatsProto.Builder) BroadcastStatsProto.newBuilder(this.broadcastStats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBroadcastStats() {
        this.broadcastStats_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public boolean hasFilterStats() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public FilterStatsProto getFilterStats() {
        FilterStatsProto filterStatsProto = this.filterStats_;
        return filterStatsProto == null ? FilterStatsProto.getDefaultInstance() : filterStatsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilterStats(FilterStatsProto value) {
        if (value != null) {
            this.filterStats_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFilterStats(FilterStatsProto.Builder builderForValue) {
        this.filterStats_ = (FilterStatsProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFilterStats(FilterStatsProto value) {
        FilterStatsProto filterStatsProto = this.filterStats_;
        if (filterStatsProto == null || filterStatsProto == FilterStatsProto.getDefaultInstance()) {
            this.filterStats_ = value;
        } else {
            this.filterStats_ = (FilterStatsProto) ((FilterStatsProto.Builder) FilterStatsProto.newBuilder(this.filterStats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFilterStats() {
        this.filterStats_ = null;
        this.bitField0_ &= -65;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public boolean hasWorkSource() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.InFlightProtoOrBuilder
    public WorkSourceProto getWorkSource() {
        WorkSourceProto workSourceProto = this.workSource_;
        return workSourceProto == null ? WorkSourceProto.getDefaultInstance() : workSourceProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWorkSource(WorkSourceProto value) {
        if (value != null) {
            this.workSource_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWorkSource(WorkSourceProto.Builder builderForValue) {
        this.workSource_ = (WorkSourceProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWorkSource(WorkSourceProto value) {
        WorkSourceProto workSourceProto = this.workSource_;
        if (workSourceProto == null || workSourceProto == WorkSourceProto.getDefaultInstance()) {
            this.workSource_ = value;
        } else {
            this.workSource_ = (WorkSourceProto) ((WorkSourceProto.Builder) WorkSourceProto.newBuilder(this.workSource_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWorkSource() {
        this.workSource_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.uid_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getTag());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.whenElapsedMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(4, this.alarmType_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getPendingIntent());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(6, getBroadcastStats());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(7, getFilterStats());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(8, getWorkSource());
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
            size2 += CodedOutputStream.computeStringSize(2, getTag());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.whenElapsedMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(4, this.alarmType_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getPendingIntent());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(6, getBroadcastStats());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(7, getFilterStats());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(8, getWorkSource());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static InFlightProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (InFlightProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static InFlightProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (InFlightProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static InFlightProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (InFlightProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static InFlightProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (InFlightProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static InFlightProto parseFrom(InputStream input) throws IOException {
        return (InFlightProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static InFlightProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (InFlightProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static InFlightProto parseDelimitedFrom(InputStream input) throws IOException {
        return (InFlightProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static InFlightProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (InFlightProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static InFlightProto parseFrom(CodedInputStream input) throws IOException {
        return (InFlightProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static InFlightProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (InFlightProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(InFlightProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<InFlightProto, Builder> implements InFlightProtoOrBuilder {
        private Builder() {
            super(InFlightProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public boolean hasUid() {
            return ((InFlightProto) this.instance).hasUid();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public int getUid() {
            return ((InFlightProto) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((InFlightProto) this.instance).clearUid();
            return this;
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public boolean hasTag() {
            return ((InFlightProto) this.instance).hasTag();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public String getTag() {
            return ((InFlightProto) this.instance).getTag();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public ByteString getTagBytes() {
            return ((InFlightProto) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((InFlightProto) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setTagBytes(value);
            return this;
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public boolean hasWhenElapsedMs() {
            return ((InFlightProto) this.instance).hasWhenElapsedMs();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public long getWhenElapsedMs() {
            return ((InFlightProto) this.instance).getWhenElapsedMs();
        }

        public Builder setWhenElapsedMs(long value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setWhenElapsedMs(value);
            return this;
        }

        public Builder clearWhenElapsedMs() {
            copyOnWrite();
            ((InFlightProto) this.instance).clearWhenElapsedMs();
            return this;
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public boolean hasAlarmType() {
            return ((InFlightProto) this.instance).hasAlarmType();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public AlarmManagerProto.AlarmType getAlarmType() {
            return ((InFlightProto) this.instance).getAlarmType();
        }

        public Builder setAlarmType(AlarmManagerProto.AlarmType value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setAlarmType(value);
            return this;
        }

        public Builder clearAlarmType() {
            copyOnWrite();
            ((InFlightProto) this.instance).clearAlarmType();
            return this;
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public boolean hasPendingIntent() {
            return ((InFlightProto) this.instance).hasPendingIntent();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public PendingIntentProto getPendingIntent() {
            return ((InFlightProto) this.instance).getPendingIntent();
        }

        public Builder setPendingIntent(PendingIntentProto value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setPendingIntent((InFlightProto) value);
            return this;
        }

        public Builder setPendingIntent(PendingIntentProto.Builder builderForValue) {
            copyOnWrite();
            ((InFlightProto) this.instance).setPendingIntent((InFlightProto) builderForValue);
            return this;
        }

        public Builder mergePendingIntent(PendingIntentProto value) {
            copyOnWrite();
            ((InFlightProto) this.instance).mergePendingIntent(value);
            return this;
        }

        public Builder clearPendingIntent() {
            copyOnWrite();
            ((InFlightProto) this.instance).clearPendingIntent();
            return this;
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public boolean hasBroadcastStats() {
            return ((InFlightProto) this.instance).hasBroadcastStats();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public BroadcastStatsProto getBroadcastStats() {
            return ((InFlightProto) this.instance).getBroadcastStats();
        }

        public Builder setBroadcastStats(BroadcastStatsProto value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setBroadcastStats((InFlightProto) value);
            return this;
        }

        public Builder setBroadcastStats(BroadcastStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((InFlightProto) this.instance).setBroadcastStats((InFlightProto) builderForValue);
            return this;
        }

        public Builder mergeBroadcastStats(BroadcastStatsProto value) {
            copyOnWrite();
            ((InFlightProto) this.instance).mergeBroadcastStats(value);
            return this;
        }

        public Builder clearBroadcastStats() {
            copyOnWrite();
            ((InFlightProto) this.instance).clearBroadcastStats();
            return this;
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public boolean hasFilterStats() {
            return ((InFlightProto) this.instance).hasFilterStats();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public FilterStatsProto getFilterStats() {
            return ((InFlightProto) this.instance).getFilterStats();
        }

        public Builder setFilterStats(FilterStatsProto value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setFilterStats((InFlightProto) value);
            return this;
        }

        public Builder setFilterStats(FilterStatsProto.Builder builderForValue) {
            copyOnWrite();
            ((InFlightProto) this.instance).setFilterStats((InFlightProto) builderForValue);
            return this;
        }

        public Builder mergeFilterStats(FilterStatsProto value) {
            copyOnWrite();
            ((InFlightProto) this.instance).mergeFilterStats(value);
            return this;
        }

        public Builder clearFilterStats() {
            copyOnWrite();
            ((InFlightProto) this.instance).clearFilterStats();
            return this;
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public boolean hasWorkSource() {
            return ((InFlightProto) this.instance).hasWorkSource();
        }

        @Override // com.android.server.InFlightProtoOrBuilder
        public WorkSourceProto getWorkSource() {
            return ((InFlightProto) this.instance).getWorkSource();
        }

        public Builder setWorkSource(WorkSourceProto value) {
            copyOnWrite();
            ((InFlightProto) this.instance).setWorkSource((InFlightProto) value);
            return this;
        }

        public Builder setWorkSource(WorkSourceProto.Builder builderForValue) {
            copyOnWrite();
            ((InFlightProto) this.instance).setWorkSource((InFlightProto) builderForValue);
            return this;
        }

        public Builder mergeWorkSource(WorkSourceProto value) {
            copyOnWrite();
            ((InFlightProto) this.instance).mergeWorkSource(value);
            return this;
        }

        public Builder clearWorkSource() {
            copyOnWrite();
            ((InFlightProto) this.instance).clearWorkSource();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new InFlightProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                InFlightProto other = (InFlightProto) arg1;
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.whenElapsedMs_ = visitor.visitLong(hasWhenElapsedMs(), this.whenElapsedMs_, other.hasWhenElapsedMs(), other.whenElapsedMs_);
                this.alarmType_ = visitor.visitInt(hasAlarmType(), this.alarmType_, other.hasAlarmType(), other.alarmType_);
                this.pendingIntent_ = (PendingIntentProto) visitor.visitMessage(this.pendingIntent_, other.pendingIntent_);
                this.broadcastStats_ = (BroadcastStatsProto) visitor.visitMessage(this.broadcastStats_, other.broadcastStats_);
                this.filterStats_ = (FilterStatsProto) visitor.visitMessage(this.filterStats_, other.filterStats_);
                this.workSource_ = (WorkSourceProto) visitor.visitMessage(this.workSource_, other.workSource_);
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
                            this.tag_ = s;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.whenElapsedMs_ = input.readInt64();
                        } else if (tag == 32) {
                            int rawValue = input.readEnum();
                            if (AlarmManagerProto.AlarmType.forNumber(rawValue) == null) {
                                super.mergeVarintField(4, rawValue);
                            } else {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.alarmType_ = rawValue;
                            }
                        } else if (tag == 42) {
                            PendingIntentProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder = (PendingIntentProto.Builder) this.pendingIntent_.toBuilder();
                            }
                            this.pendingIntent_ = (PendingIntentProto) input.readMessage(PendingIntentProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.pendingIntent_);
                                this.pendingIntent_ = (PendingIntentProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 16;
                        } else if (tag == 50) {
                            BroadcastStatsProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 32) == 32) {
                                subBuilder2 = (BroadcastStatsProto.Builder) this.broadcastStats_.toBuilder();
                            }
                            this.broadcastStats_ = (BroadcastStatsProto) input.readMessage(BroadcastStatsProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.broadcastStats_);
                                this.broadcastStats_ = (BroadcastStatsProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ = 32 | this.bitField0_;
                        } else if (tag == 58) {
                            FilterStatsProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 64) == 64) {
                                subBuilder3 = (FilterStatsProto.Builder) this.filterStats_.toBuilder();
                            }
                            this.filterStats_ = (FilterStatsProto) input.readMessage(FilterStatsProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.filterStats_);
                                this.filterStats_ = (FilterStatsProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 64;
                        } else if (tag == 66) {
                            WorkSourceProto.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 128) == 128) {
                                subBuilder4 = (WorkSourceProto.Builder) this.workSource_.toBuilder();
                            }
                            this.workSource_ = (WorkSourceProto) input.readMessage(WorkSourceProto.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.workSource_);
                                this.workSource_ = (WorkSourceProto) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 128;
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
                    synchronized (InFlightProto.class) {
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

    public static InFlightProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<InFlightProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
