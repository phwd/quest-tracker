package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class TimerProto extends GeneratedMessageLite<TimerProto, Builder> implements TimerProtoOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 2;
    public static final int CURRENT_DURATION_MS_FIELD_NUMBER = 4;
    private static final TimerProto DEFAULT_INSTANCE = new TimerProto();
    public static final int DURATION_MS_FIELD_NUMBER = 1;
    public static final int MAX_DURATION_MS_FIELD_NUMBER = 3;
    private static volatile Parser<TimerProto> PARSER = null;
    public static final int TOTAL_DURATION_MS_FIELD_NUMBER = 5;
    private int bitField0_;
    private long count_ = 0;
    private long currentDurationMs_ = 0;
    private long durationMs_ = 0;
    private long maxDurationMs_ = 0;
    private long totalDurationMs_ = 0;

    private TimerProto() {
    }

    @Override // android.os.TimerProtoOrBuilder
    public boolean hasDurationMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.TimerProtoOrBuilder
    public long getDurationMs() {
        return this.durationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDurationMs(long value) {
        this.bitField0_ |= 1;
        this.durationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDurationMs() {
        this.bitField0_ &= -2;
        this.durationMs_ = 0;
    }

    @Override // android.os.TimerProtoOrBuilder
    public boolean hasCount() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.TimerProtoOrBuilder
    public long getCount() {
        return this.count_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCount(long value) {
        this.bitField0_ |= 2;
        this.count_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCount() {
        this.bitField0_ &= -3;
        this.count_ = 0;
    }

    @Override // android.os.TimerProtoOrBuilder
    public boolean hasMaxDurationMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.TimerProtoOrBuilder
    public long getMaxDurationMs() {
        return this.maxDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxDurationMs(long value) {
        this.bitField0_ |= 4;
        this.maxDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxDurationMs() {
        this.bitField0_ &= -5;
        this.maxDurationMs_ = 0;
    }

    @Override // android.os.TimerProtoOrBuilder
    public boolean hasCurrentDurationMs() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.TimerProtoOrBuilder
    public long getCurrentDurationMs() {
        return this.currentDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentDurationMs(long value) {
        this.bitField0_ |= 8;
        this.currentDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCurrentDurationMs() {
        this.bitField0_ &= -9;
        this.currentDurationMs_ = 0;
    }

    @Override // android.os.TimerProtoOrBuilder
    public boolean hasTotalDurationMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.os.TimerProtoOrBuilder
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

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.durationMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.count_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.maxDurationMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.currentDurationMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.totalDurationMs_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.durationMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.count_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.maxDurationMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.currentDurationMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.totalDurationMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static TimerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (TimerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TimerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TimerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TimerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (TimerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static TimerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (TimerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static TimerProto parseFrom(InputStream input) throws IOException {
        return (TimerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TimerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TimerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TimerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (TimerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static TimerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TimerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static TimerProto parseFrom(CodedInputStream input) throws IOException {
        return (TimerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static TimerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (TimerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TimerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TimerProto, Builder> implements TimerProtoOrBuilder {
        private Builder() {
            super(TimerProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.TimerProtoOrBuilder
        public boolean hasDurationMs() {
            return ((TimerProto) this.instance).hasDurationMs();
        }

        @Override // android.os.TimerProtoOrBuilder
        public long getDurationMs() {
            return ((TimerProto) this.instance).getDurationMs();
        }

        public Builder setDurationMs(long value) {
            copyOnWrite();
            ((TimerProto) this.instance).setDurationMs(value);
            return this;
        }

        public Builder clearDurationMs() {
            copyOnWrite();
            ((TimerProto) this.instance).clearDurationMs();
            return this;
        }

        @Override // android.os.TimerProtoOrBuilder
        public boolean hasCount() {
            return ((TimerProto) this.instance).hasCount();
        }

        @Override // android.os.TimerProtoOrBuilder
        public long getCount() {
            return ((TimerProto) this.instance).getCount();
        }

        public Builder setCount(long value) {
            copyOnWrite();
            ((TimerProto) this.instance).setCount(value);
            return this;
        }

        public Builder clearCount() {
            copyOnWrite();
            ((TimerProto) this.instance).clearCount();
            return this;
        }

        @Override // android.os.TimerProtoOrBuilder
        public boolean hasMaxDurationMs() {
            return ((TimerProto) this.instance).hasMaxDurationMs();
        }

        @Override // android.os.TimerProtoOrBuilder
        public long getMaxDurationMs() {
            return ((TimerProto) this.instance).getMaxDurationMs();
        }

        public Builder setMaxDurationMs(long value) {
            copyOnWrite();
            ((TimerProto) this.instance).setMaxDurationMs(value);
            return this;
        }

        public Builder clearMaxDurationMs() {
            copyOnWrite();
            ((TimerProto) this.instance).clearMaxDurationMs();
            return this;
        }

        @Override // android.os.TimerProtoOrBuilder
        public boolean hasCurrentDurationMs() {
            return ((TimerProto) this.instance).hasCurrentDurationMs();
        }

        @Override // android.os.TimerProtoOrBuilder
        public long getCurrentDurationMs() {
            return ((TimerProto) this.instance).getCurrentDurationMs();
        }

        public Builder setCurrentDurationMs(long value) {
            copyOnWrite();
            ((TimerProto) this.instance).setCurrentDurationMs(value);
            return this;
        }

        public Builder clearCurrentDurationMs() {
            copyOnWrite();
            ((TimerProto) this.instance).clearCurrentDurationMs();
            return this;
        }

        @Override // android.os.TimerProtoOrBuilder
        public boolean hasTotalDurationMs() {
            return ((TimerProto) this.instance).hasTotalDurationMs();
        }

        @Override // android.os.TimerProtoOrBuilder
        public long getTotalDurationMs() {
            return ((TimerProto) this.instance).getTotalDurationMs();
        }

        public Builder setTotalDurationMs(long value) {
            copyOnWrite();
            ((TimerProto) this.instance).setTotalDurationMs(value);
            return this;
        }

        public Builder clearTotalDurationMs() {
            copyOnWrite();
            ((TimerProto) this.instance).clearTotalDurationMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new TimerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                TimerProto other = (TimerProto) arg1;
                this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
                this.count_ = visitor.visitLong(hasCount(), this.count_, other.hasCount(), other.count_);
                this.maxDurationMs_ = visitor.visitLong(hasMaxDurationMs(), this.maxDurationMs_, other.hasMaxDurationMs(), other.maxDurationMs_);
                this.currentDurationMs_ = visitor.visitLong(hasCurrentDurationMs(), this.currentDurationMs_, other.hasCurrentDurationMs(), other.currentDurationMs_);
                this.totalDurationMs_ = visitor.visitLong(hasTotalDurationMs(), this.totalDurationMs_, other.hasTotalDurationMs(), other.totalDurationMs_);
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
                            this.durationMs_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.count_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.maxDurationMs_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.currentDurationMs_ = input.readInt64();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.totalDurationMs_ = input.readInt64();
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
                    synchronized (TimerProto.class) {
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

    public static TimerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TimerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
