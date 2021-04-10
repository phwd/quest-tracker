package com.android.server;

import android.app.AlarmClockInfoProto;
import android.app.AlarmManagerProto;
import android.app.PendingIntentProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AlarmProto extends GeneratedMessageLite<AlarmProto, Builder> implements AlarmProtoOrBuilder {
    public static final int ALARM_CLOCK_FIELD_NUMBER = 8;
    public static final int COUNT_FIELD_NUMBER = 6;
    private static final AlarmProto DEFAULT_INSTANCE = new AlarmProto();
    public static final int FLAGS_FIELD_NUMBER = 7;
    public static final int LISTENER_FIELD_NUMBER = 10;
    public static final int OPERATION_FIELD_NUMBER = 9;
    private static volatile Parser<AlarmProto> PARSER = null;
    public static final int REPEAT_INTERVAL_MS_FIELD_NUMBER = 5;
    public static final int TAG_FIELD_NUMBER = 1;
    public static final int TIME_UNTIL_WHEN_ELAPSED_MS_FIELD_NUMBER = 3;
    public static final int TYPE_FIELD_NUMBER = 2;
    public static final int WINDOW_LENGTH_MS_FIELD_NUMBER = 4;
    private AlarmClockInfoProto alarmClock_;
    private int bitField0_;
    private int count_ = 0;
    private int flags_ = 0;
    private String listener_ = "";
    private PendingIntentProto operation_;
    private long repeatIntervalMs_ = 0;
    private String tag_ = "";
    private long timeUntilWhenElapsedMs_ = 0;
    private int type_ = 0;
    private long windowLengthMs_ = 0;

    private AlarmProto() {
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public ByteString getTagBytes() {
        return ByteString.copyFromUtf8(this.tag_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTag(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.tag_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTag() {
        this.bitField0_ &= -2;
        this.tag_ = getDefaultInstance().getTag();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.tag_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasType() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public AlarmManagerProto.AlarmType getType() {
        AlarmManagerProto.AlarmType result = AlarmManagerProto.AlarmType.forNumber(this.type_);
        return result == null ? AlarmManagerProto.AlarmType.RTC_WAKEUP : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(AlarmManagerProto.AlarmType value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.type_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearType() {
        this.bitField0_ &= -3;
        this.type_ = 0;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasTimeUntilWhenElapsedMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public long getTimeUntilWhenElapsedMs() {
        return this.timeUntilWhenElapsedMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTimeUntilWhenElapsedMs(long value) {
        this.bitField0_ |= 4;
        this.timeUntilWhenElapsedMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTimeUntilWhenElapsedMs() {
        this.bitField0_ &= -5;
        this.timeUntilWhenElapsedMs_ = 0;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasWindowLengthMs() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public long getWindowLengthMs() {
        return this.windowLengthMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowLengthMs(long value) {
        this.bitField0_ |= 8;
        this.windowLengthMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowLengthMs() {
        this.bitField0_ &= -9;
        this.windowLengthMs_ = 0;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasRepeatIntervalMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public long getRepeatIntervalMs() {
        return this.repeatIntervalMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRepeatIntervalMs(long value) {
        this.bitField0_ |= 16;
        this.repeatIntervalMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRepeatIntervalMs() {
        this.bitField0_ &= -17;
        this.repeatIntervalMs_ = 0;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasCount() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public int getCount() {
        return this.count_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCount(int value) {
        this.bitField0_ |= 32;
        this.count_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCount() {
        this.bitField0_ &= -33;
        this.count_ = 0;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 64;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -65;
        this.flags_ = 0;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasAlarmClock() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public AlarmClockInfoProto getAlarmClock() {
        AlarmClockInfoProto alarmClockInfoProto = this.alarmClock_;
        return alarmClockInfoProto == null ? AlarmClockInfoProto.getDefaultInstance() : alarmClockInfoProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarmClock(AlarmClockInfoProto value) {
        if (value != null) {
            this.alarmClock_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarmClock(AlarmClockInfoProto.Builder builderForValue) {
        this.alarmClock_ = (AlarmClockInfoProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAlarmClock(AlarmClockInfoProto value) {
        AlarmClockInfoProto alarmClockInfoProto = this.alarmClock_;
        if (alarmClockInfoProto == null || alarmClockInfoProto == AlarmClockInfoProto.getDefaultInstance()) {
            this.alarmClock_ = value;
        } else {
            this.alarmClock_ = (AlarmClockInfoProto) ((AlarmClockInfoProto.Builder) AlarmClockInfoProto.newBuilder(this.alarmClock_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlarmClock() {
        this.alarmClock_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasOperation() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public PendingIntentProto getOperation() {
        PendingIntentProto pendingIntentProto = this.operation_;
        return pendingIntentProto == null ? PendingIntentProto.getDefaultInstance() : pendingIntentProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOperation(PendingIntentProto value) {
        if (value != null) {
            this.operation_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOperation(PendingIntentProto.Builder builderForValue) {
        this.operation_ = (PendingIntentProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeOperation(PendingIntentProto value) {
        PendingIntentProto pendingIntentProto = this.operation_;
        if (pendingIntentProto == null || pendingIntentProto == PendingIntentProto.getDefaultInstance()) {
            this.operation_ = value;
        } else {
            this.operation_ = (PendingIntentProto) ((PendingIntentProto.Builder) PendingIntentProto.newBuilder(this.operation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOperation() {
        this.operation_ = null;
        this.bitField0_ &= -257;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public boolean hasListener() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public String getListener() {
        return this.listener_;
    }

    @Override // com.android.server.AlarmProtoOrBuilder
    public ByteString getListenerBytes() {
        return ByteString.copyFromUtf8(this.listener_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListener(String value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.listener_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearListener() {
        this.bitField0_ &= -513;
        this.listener_ = getDefaultInstance().getListener();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenerBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.listener_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getTag());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.type_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.timeUntilWhenElapsedMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.windowLengthMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.repeatIntervalMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.count_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.flags_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(8, getAlarmClock());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(9, getOperation());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeString(10, getListener());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getTag());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.type_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.timeUntilWhenElapsedMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.windowLengthMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.repeatIntervalMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.count_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.flags_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(8, getAlarmClock());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(9, getOperation());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeStringSize(10, getListener());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AlarmProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AlarmProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AlarmProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlarmProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlarmProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlarmProto parseFrom(InputStream input) throws IOException {
        return (AlarmProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AlarmProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlarmProto parseFrom(CodedInputStream input) throws IOException {
        return (AlarmProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlarmProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlarmProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AlarmProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AlarmProto, Builder> implements AlarmProtoOrBuilder {
        private Builder() {
            super(AlarmProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasTag() {
            return ((AlarmProto) this.instance).hasTag();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public String getTag() {
            return ((AlarmProto) this.instance).getTag();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public ByteString getTagBytes() {
            return ((AlarmProto) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setTagBytes(value);
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasType() {
            return ((AlarmProto) this.instance).hasType();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public AlarmManagerProto.AlarmType getType() {
            return ((AlarmProto) this.instance).getType();
        }

        public Builder setType(AlarmManagerProto.AlarmType value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearType();
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasTimeUntilWhenElapsedMs() {
            return ((AlarmProto) this.instance).hasTimeUntilWhenElapsedMs();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public long getTimeUntilWhenElapsedMs() {
            return ((AlarmProto) this.instance).getTimeUntilWhenElapsedMs();
        }

        public Builder setTimeUntilWhenElapsedMs(long value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setTimeUntilWhenElapsedMs(value);
            return this;
        }

        public Builder clearTimeUntilWhenElapsedMs() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearTimeUntilWhenElapsedMs();
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasWindowLengthMs() {
            return ((AlarmProto) this.instance).hasWindowLengthMs();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public long getWindowLengthMs() {
            return ((AlarmProto) this.instance).getWindowLengthMs();
        }

        public Builder setWindowLengthMs(long value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setWindowLengthMs(value);
            return this;
        }

        public Builder clearWindowLengthMs() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearWindowLengthMs();
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasRepeatIntervalMs() {
            return ((AlarmProto) this.instance).hasRepeatIntervalMs();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public long getRepeatIntervalMs() {
            return ((AlarmProto) this.instance).getRepeatIntervalMs();
        }

        public Builder setRepeatIntervalMs(long value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setRepeatIntervalMs(value);
            return this;
        }

        public Builder clearRepeatIntervalMs() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearRepeatIntervalMs();
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasCount() {
            return ((AlarmProto) this.instance).hasCount();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public int getCount() {
            return ((AlarmProto) this.instance).getCount();
        }

        public Builder setCount(int value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setCount(value);
            return this;
        }

        public Builder clearCount() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearCount();
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasFlags() {
            return ((AlarmProto) this.instance).hasFlags();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public int getFlags() {
            return ((AlarmProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearFlags();
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasAlarmClock() {
            return ((AlarmProto) this.instance).hasAlarmClock();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public AlarmClockInfoProto getAlarmClock() {
            return ((AlarmProto) this.instance).getAlarmClock();
        }

        public Builder setAlarmClock(AlarmClockInfoProto value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setAlarmClock((AlarmProto) value);
            return this;
        }

        public Builder setAlarmClock(AlarmClockInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmProto) this.instance).setAlarmClock((AlarmProto) builderForValue);
            return this;
        }

        public Builder mergeAlarmClock(AlarmClockInfoProto value) {
            copyOnWrite();
            ((AlarmProto) this.instance).mergeAlarmClock(value);
            return this;
        }

        public Builder clearAlarmClock() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearAlarmClock();
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasOperation() {
            return ((AlarmProto) this.instance).hasOperation();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public PendingIntentProto getOperation() {
            return ((AlarmProto) this.instance).getOperation();
        }

        public Builder setOperation(PendingIntentProto value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setOperation((AlarmProto) value);
            return this;
        }

        public Builder setOperation(PendingIntentProto.Builder builderForValue) {
            copyOnWrite();
            ((AlarmProto) this.instance).setOperation((AlarmProto) builderForValue);
            return this;
        }

        public Builder mergeOperation(PendingIntentProto value) {
            copyOnWrite();
            ((AlarmProto) this.instance).mergeOperation(value);
            return this;
        }

        public Builder clearOperation() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearOperation();
            return this;
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public boolean hasListener() {
            return ((AlarmProto) this.instance).hasListener();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public String getListener() {
            return ((AlarmProto) this.instance).getListener();
        }

        @Override // com.android.server.AlarmProtoOrBuilder
        public ByteString getListenerBytes() {
            return ((AlarmProto) this.instance).getListenerBytes();
        }

        public Builder setListener(String value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setListener(value);
            return this;
        }

        public Builder clearListener() {
            copyOnWrite();
            ((AlarmProto) this.instance).clearListener();
            return this;
        }

        public Builder setListenerBytes(ByteString value) {
            copyOnWrite();
            ((AlarmProto) this.instance).setListenerBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AlarmProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AlarmProto other = (AlarmProto) arg1;
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                this.timeUntilWhenElapsedMs_ = visitor.visitLong(hasTimeUntilWhenElapsedMs(), this.timeUntilWhenElapsedMs_, other.hasTimeUntilWhenElapsedMs(), other.timeUntilWhenElapsedMs_);
                this.windowLengthMs_ = visitor.visitLong(hasWindowLengthMs(), this.windowLengthMs_, other.hasWindowLengthMs(), other.windowLengthMs_);
                this.repeatIntervalMs_ = visitor.visitLong(hasRepeatIntervalMs(), this.repeatIntervalMs_, other.hasRepeatIntervalMs(), other.repeatIntervalMs_);
                this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.alarmClock_ = (AlarmClockInfoProto) visitor.visitMessage(this.alarmClock_, other.alarmClock_);
                this.operation_ = (PendingIntentProto) visitor.visitMessage(this.operation_, other.operation_);
                this.listener_ = visitor.visitString(hasListener(), this.listener_, other.hasListener(), other.listener_);
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
                            case 10:
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.tag_ = s;
                                break;
                            case 16:
                                int rawValue = input.readEnum();
                                if (AlarmManagerProto.AlarmType.forNumber(rawValue) != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.type_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(2, rawValue);
                                    break;
                                }
                            case 24:
                                this.bitField0_ |= 4;
                                this.timeUntilWhenElapsedMs_ = input.readInt64();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.windowLengthMs_ = input.readInt64();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.repeatIntervalMs_ = input.readInt64();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.count_ = input.readInt32();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.flags_ = input.readInt32();
                                break;
                            case 66:
                                AlarmClockInfoProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder = (AlarmClockInfoProto.Builder) this.alarmClock_.toBuilder();
                                }
                                this.alarmClock_ = (AlarmClockInfoProto) input.readMessage(AlarmClockInfoProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.alarmClock_);
                                    this.alarmClock_ = (AlarmClockInfoProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 74:
                                PendingIntentProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder2 = (PendingIntentProto.Builder) this.operation_.toBuilder();
                                }
                                this.operation_ = (PendingIntentProto) input.readMessage(PendingIntentProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.operation_);
                                    this.operation_ = (PendingIntentProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 82:
                                String s2 = input.readString();
                                this.bitField0_ |= 512;
                                this.listener_ = s2;
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
                    synchronized (AlarmProto.class) {
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

    public static AlarmProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AlarmProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
