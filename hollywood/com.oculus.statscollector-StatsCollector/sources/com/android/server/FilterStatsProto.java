package com.android.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class FilterStatsProto extends GeneratedMessageLite<FilterStatsProto, Builder> implements FilterStatsProtoOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 4;
    private static final FilterStatsProto DEFAULT_INSTANCE = new FilterStatsProto();
    public static final int LAST_FLIGHT_TIME_REALTIME_FIELD_NUMBER = 2;
    public static final int NESTING_FIELD_NUMBER = 7;
    private static volatile Parser<FilterStatsProto> PARSER = null;
    public static final int START_TIME_REALTIME_FIELD_NUMBER = 6;
    public static final int TAG_FIELD_NUMBER = 1;
    public static final int TOTAL_FLIGHT_DURATION_MS_FIELD_NUMBER = 3;
    public static final int WAKEUP_COUNT_FIELD_NUMBER = 5;
    private int bitField0_;
    private int count_ = 0;
    private long lastFlightTimeRealtime_ = 0;
    private int nesting_ = 0;
    private long startTimeRealtime_ = 0;
    private String tag_ = "";
    private long totalFlightDurationMs_ = 0;
    private int wakeupCount_ = 0;

    private FilterStatsProto() {
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
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

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public boolean hasLastFlightTimeRealtime() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public long getLastFlightTimeRealtime() {
        return this.lastFlightTimeRealtime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastFlightTimeRealtime(long value) {
        this.bitField0_ |= 2;
        this.lastFlightTimeRealtime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastFlightTimeRealtime() {
        this.bitField0_ &= -3;
        this.lastFlightTimeRealtime_ = 0;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public boolean hasTotalFlightDurationMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public long getTotalFlightDurationMs() {
        return this.totalFlightDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalFlightDurationMs(long value) {
        this.bitField0_ |= 4;
        this.totalFlightDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalFlightDurationMs() {
        this.bitField0_ &= -5;
        this.totalFlightDurationMs_ = 0;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public boolean hasCount() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public int getCount() {
        return this.count_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCount(int value) {
        this.bitField0_ |= 8;
        this.count_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCount() {
        this.bitField0_ &= -9;
        this.count_ = 0;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public boolean hasWakeupCount() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public int getWakeupCount() {
        return this.wakeupCount_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeupCount(int value) {
        this.bitField0_ |= 16;
        this.wakeupCount_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWakeupCount() {
        this.bitField0_ &= -17;
        this.wakeupCount_ = 0;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public boolean hasStartTimeRealtime() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public long getStartTimeRealtime() {
        return this.startTimeRealtime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartTimeRealtime(long value) {
        this.bitField0_ |= 32;
        this.startTimeRealtime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartTimeRealtime() {
        this.bitField0_ &= -33;
        this.startTimeRealtime_ = 0;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public boolean hasNesting() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.FilterStatsProtoOrBuilder
    public int getNesting() {
        return this.nesting_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNesting(int value) {
        this.bitField0_ |= 64;
        this.nesting_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNesting() {
        this.bitField0_ &= -65;
        this.nesting_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getTag());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.lastFlightTimeRealtime_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.totalFlightDurationMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.count_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.wakeupCount_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(6, this.startTimeRealtime_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.nesting_);
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
            size2 += CodedOutputStream.computeInt64Size(2, this.lastFlightTimeRealtime_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.totalFlightDurationMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.count_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.wakeupCount_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(6, this.startTimeRealtime_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.nesting_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static FilterStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (FilterStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FilterStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FilterStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FilterStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (FilterStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FilterStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FilterStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FilterStatsProto parseFrom(InputStream input) throws IOException {
        return (FilterStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FilterStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FilterStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FilterStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (FilterStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static FilterStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FilterStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FilterStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (FilterStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FilterStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FilterStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FilterStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FilterStatsProto, Builder> implements FilterStatsProtoOrBuilder {
        private Builder() {
            super(FilterStatsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public boolean hasTag() {
            return ((FilterStatsProto) this.instance).hasTag();
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public String getTag() {
            return ((FilterStatsProto) this.instance).getTag();
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public ByteString getTagBytes() {
            return ((FilterStatsProto) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((FilterStatsProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((FilterStatsProto) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((FilterStatsProto) this.instance).setTagBytes(value);
            return this;
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public boolean hasLastFlightTimeRealtime() {
            return ((FilterStatsProto) this.instance).hasLastFlightTimeRealtime();
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public long getLastFlightTimeRealtime() {
            return ((FilterStatsProto) this.instance).getLastFlightTimeRealtime();
        }

        public Builder setLastFlightTimeRealtime(long value) {
            copyOnWrite();
            ((FilterStatsProto) this.instance).setLastFlightTimeRealtime(value);
            return this;
        }

        public Builder clearLastFlightTimeRealtime() {
            copyOnWrite();
            ((FilterStatsProto) this.instance).clearLastFlightTimeRealtime();
            return this;
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public boolean hasTotalFlightDurationMs() {
            return ((FilterStatsProto) this.instance).hasTotalFlightDurationMs();
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public long getTotalFlightDurationMs() {
            return ((FilterStatsProto) this.instance).getTotalFlightDurationMs();
        }

        public Builder setTotalFlightDurationMs(long value) {
            copyOnWrite();
            ((FilterStatsProto) this.instance).setTotalFlightDurationMs(value);
            return this;
        }

        public Builder clearTotalFlightDurationMs() {
            copyOnWrite();
            ((FilterStatsProto) this.instance).clearTotalFlightDurationMs();
            return this;
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public boolean hasCount() {
            return ((FilterStatsProto) this.instance).hasCount();
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public int getCount() {
            return ((FilterStatsProto) this.instance).getCount();
        }

        public Builder setCount(int value) {
            copyOnWrite();
            ((FilterStatsProto) this.instance).setCount(value);
            return this;
        }

        public Builder clearCount() {
            copyOnWrite();
            ((FilterStatsProto) this.instance).clearCount();
            return this;
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public boolean hasWakeupCount() {
            return ((FilterStatsProto) this.instance).hasWakeupCount();
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public int getWakeupCount() {
            return ((FilterStatsProto) this.instance).getWakeupCount();
        }

        public Builder setWakeupCount(int value) {
            copyOnWrite();
            ((FilterStatsProto) this.instance).setWakeupCount(value);
            return this;
        }

        public Builder clearWakeupCount() {
            copyOnWrite();
            ((FilterStatsProto) this.instance).clearWakeupCount();
            return this;
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public boolean hasStartTimeRealtime() {
            return ((FilterStatsProto) this.instance).hasStartTimeRealtime();
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public long getStartTimeRealtime() {
            return ((FilterStatsProto) this.instance).getStartTimeRealtime();
        }

        public Builder setStartTimeRealtime(long value) {
            copyOnWrite();
            ((FilterStatsProto) this.instance).setStartTimeRealtime(value);
            return this;
        }

        public Builder clearStartTimeRealtime() {
            copyOnWrite();
            ((FilterStatsProto) this.instance).clearStartTimeRealtime();
            return this;
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public boolean hasNesting() {
            return ((FilterStatsProto) this.instance).hasNesting();
        }

        @Override // com.android.server.FilterStatsProtoOrBuilder
        public int getNesting() {
            return ((FilterStatsProto) this.instance).getNesting();
        }

        public Builder setNesting(int value) {
            copyOnWrite();
            ((FilterStatsProto) this.instance).setNesting(value);
            return this;
        }

        public Builder clearNesting() {
            copyOnWrite();
            ((FilterStatsProto) this.instance).clearNesting();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new FilterStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                FilterStatsProto other = (FilterStatsProto) arg1;
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.lastFlightTimeRealtime_ = visitor.visitLong(hasLastFlightTimeRealtime(), this.lastFlightTimeRealtime_, other.hasLastFlightTimeRealtime(), other.lastFlightTimeRealtime_);
                this.totalFlightDurationMs_ = visitor.visitLong(hasTotalFlightDurationMs(), this.totalFlightDurationMs_, other.hasTotalFlightDurationMs(), other.totalFlightDurationMs_);
                this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
                this.wakeupCount_ = visitor.visitInt(hasWakeupCount(), this.wakeupCount_, other.hasWakeupCount(), other.wakeupCount_);
                this.startTimeRealtime_ = visitor.visitLong(hasStartTimeRealtime(), this.startTimeRealtime_, other.hasStartTimeRealtime(), other.startTimeRealtime_);
                this.nesting_ = visitor.visitInt(hasNesting(), this.nesting_, other.hasNesting(), other.nesting_);
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
                        } else if (tag == 10) {
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.tag_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.lastFlightTimeRealtime_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.totalFlightDurationMs_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.count_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.wakeupCount_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.startTimeRealtime_ = input.readInt64();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.nesting_ = input.readInt32();
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
                    synchronized (FilterStatsProto.class) {
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

    public static FilterStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FilterStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
