package com.android.server;

import com.android.server.AlarmProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class BatchProto extends GeneratedMessageLite<BatchProto, Builder> implements BatchProtoOrBuilder {
    public static final int ALARMS_FIELD_NUMBER = 4;
    private static final BatchProto DEFAULT_INSTANCE = new BatchProto();
    public static final int END_REALTIME_FIELD_NUMBER = 2;
    public static final int FLAGS_FIELD_NUMBER = 3;
    private static volatile Parser<BatchProto> PARSER = null;
    public static final int START_REALTIME_FIELD_NUMBER = 1;
    private Internal.ProtobufList<AlarmProto> alarms_ = emptyProtobufList();
    private int bitField0_;
    private long endRealtime_ = 0;
    private int flags_ = 0;
    private long startRealtime_ = 0;

    private BatchProto() {
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public boolean hasStartRealtime() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public long getStartRealtime() {
        return this.startRealtime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartRealtime(long value) {
        this.bitField0_ |= 1;
        this.startRealtime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartRealtime() {
        this.bitField0_ &= -2;
        this.startRealtime_ = 0;
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public boolean hasEndRealtime() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public long getEndRealtime() {
        return this.endRealtime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndRealtime(long value) {
        this.bitField0_ |= 2;
        this.endRealtime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndRealtime() {
        this.bitField0_ &= -3;
        this.endRealtime_ = 0;
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 4;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -5;
        this.flags_ = 0;
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public List<AlarmProto> getAlarmsList() {
        return this.alarms_;
    }

    public List<? extends AlarmProtoOrBuilder> getAlarmsOrBuilderList() {
        return this.alarms_;
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public int getAlarmsCount() {
        return this.alarms_.size();
    }

    @Override // com.android.server.BatchProtoOrBuilder
    public AlarmProto getAlarms(int index) {
        return this.alarms_.get(index);
    }

    public AlarmProtoOrBuilder getAlarmsOrBuilder(int index) {
        return this.alarms_.get(index);
    }

    private void ensureAlarmsIsMutable() {
        if (!this.alarms_.isModifiable()) {
            this.alarms_ = GeneratedMessageLite.mutableCopy(this.alarms_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarms(int index, AlarmProto value) {
        if (value != null) {
            ensureAlarmsIsMutable();
            this.alarms_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarms(int index, AlarmProto.Builder builderForValue) {
        ensureAlarmsIsMutable();
        this.alarms_.set(index, (AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlarms(AlarmProto value) {
        if (value != null) {
            ensureAlarmsIsMutable();
            this.alarms_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlarms(int index, AlarmProto value) {
        if (value != null) {
            ensureAlarmsIsMutable();
            this.alarms_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlarms(AlarmProto.Builder builderForValue) {
        ensureAlarmsIsMutable();
        this.alarms_.add((AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAlarms(int index, AlarmProto.Builder builderForValue) {
        ensureAlarmsIsMutable();
        this.alarms_.add(index, (AlarmProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAlarms(Iterable<? extends AlarmProto> values) {
        ensureAlarmsIsMutable();
        AbstractMessageLite.addAll(values, this.alarms_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlarms() {
        this.alarms_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAlarms(int index) {
        ensureAlarmsIsMutable();
        this.alarms_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.startRealtime_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.endRealtime_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.flags_);
        }
        for (int i = 0; i < this.alarms_.size(); i++) {
            output.writeMessage(4, this.alarms_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.startRealtime_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.endRealtime_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.flags_);
        }
        for (int i = 0; i < this.alarms_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.alarms_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BatchProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BatchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatchProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatchProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BatchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatchProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatchProto parseFrom(InputStream input) throws IOException {
        return (BatchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatchProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatchProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BatchProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BatchProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatchProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatchProto parseFrom(CodedInputStream input) throws IOException {
        return (BatchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatchProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatchProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BatchProto, Builder> implements BatchProtoOrBuilder {
        private Builder() {
            super(BatchProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public boolean hasStartRealtime() {
            return ((BatchProto) this.instance).hasStartRealtime();
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public long getStartRealtime() {
            return ((BatchProto) this.instance).getStartRealtime();
        }

        public Builder setStartRealtime(long value) {
            copyOnWrite();
            ((BatchProto) this.instance).setStartRealtime(value);
            return this;
        }

        public Builder clearStartRealtime() {
            copyOnWrite();
            ((BatchProto) this.instance).clearStartRealtime();
            return this;
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public boolean hasEndRealtime() {
            return ((BatchProto) this.instance).hasEndRealtime();
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public long getEndRealtime() {
            return ((BatchProto) this.instance).getEndRealtime();
        }

        public Builder setEndRealtime(long value) {
            copyOnWrite();
            ((BatchProto) this.instance).setEndRealtime(value);
            return this;
        }

        public Builder clearEndRealtime() {
            copyOnWrite();
            ((BatchProto) this.instance).clearEndRealtime();
            return this;
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public boolean hasFlags() {
            return ((BatchProto) this.instance).hasFlags();
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public int getFlags() {
            return ((BatchProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((BatchProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((BatchProto) this.instance).clearFlags();
            return this;
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public List<AlarmProto> getAlarmsList() {
            return Collections.unmodifiableList(((BatchProto) this.instance).getAlarmsList());
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public int getAlarmsCount() {
            return ((BatchProto) this.instance).getAlarmsCount();
        }

        @Override // com.android.server.BatchProtoOrBuilder
        public AlarmProto getAlarms(int index) {
            return ((BatchProto) this.instance).getAlarms(index);
        }

        public Builder setAlarms(int index, AlarmProto value) {
            copyOnWrite();
            ((BatchProto) this.instance).setAlarms((BatchProto) index, (int) value);
            return this;
        }

        public Builder setAlarms(int index, AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((BatchProto) this.instance).setAlarms((BatchProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAlarms(AlarmProto value) {
            copyOnWrite();
            ((BatchProto) this.instance).addAlarms((BatchProto) value);
            return this;
        }

        public Builder addAlarms(int index, AlarmProto value) {
            copyOnWrite();
            ((BatchProto) this.instance).addAlarms((BatchProto) index, (int) value);
            return this;
        }

        public Builder addAlarms(AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((BatchProto) this.instance).addAlarms((BatchProto) builderForValue);
            return this;
        }

        public Builder addAlarms(int index, AlarmProto.Builder builderForValue) {
            copyOnWrite();
            ((BatchProto) this.instance).addAlarms((BatchProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAlarms(Iterable<? extends AlarmProto> values) {
            copyOnWrite();
            ((BatchProto) this.instance).addAllAlarms(values);
            return this;
        }

        public Builder clearAlarms() {
            copyOnWrite();
            ((BatchProto) this.instance).clearAlarms();
            return this;
        }

        public Builder removeAlarms(int index) {
            copyOnWrite();
            ((BatchProto) this.instance).removeAlarms(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BatchProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.alarms_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BatchProto other = (BatchProto) arg1;
                this.startRealtime_ = visitor.visitLong(hasStartRealtime(), this.startRealtime_, other.hasStartRealtime(), other.startRealtime_);
                this.endRealtime_ = visitor.visitLong(hasEndRealtime(), this.endRealtime_, other.hasEndRealtime(), other.endRealtime_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                this.alarms_ = visitor.visitList(this.alarms_, other.alarms_);
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
                            this.startRealtime_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.endRealtime_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.flags_ = input.readInt32();
                        } else if (tag == 34) {
                            if (!this.alarms_.isModifiable()) {
                                this.alarms_ = GeneratedMessageLite.mutableCopy(this.alarms_);
                            }
                            this.alarms_.add((AlarmProto) input.readMessage(AlarmProto.parser(), extensionRegistry));
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
                    synchronized (BatchProto.class) {
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

    public static BatchProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
