package android.util;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AggStats extends GeneratedMessageLite<AggStats, Builder> implements AggStatsOrBuilder {
    public static final int AVERAGE_FIELD_NUMBER = 2;
    private static final AggStats DEFAULT_INSTANCE = new AggStats();
    public static final int MAX_FIELD_NUMBER = 3;
    public static final int MIN_FIELD_NUMBER = 1;
    private static volatile Parser<AggStats> PARSER;
    private long average_ = 0;
    private int bitField0_;
    private long max_ = 0;
    private long min_ = 0;

    private AggStats() {
    }

    @Override // android.util.AggStatsOrBuilder
    public boolean hasMin() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.util.AggStatsOrBuilder
    public long getMin() {
        return this.min_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMin(long value) {
        this.bitField0_ |= 1;
        this.min_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMin() {
        this.bitField0_ &= -2;
        this.min_ = 0;
    }

    @Override // android.util.AggStatsOrBuilder
    public boolean hasAverage() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.util.AggStatsOrBuilder
    public long getAverage() {
        return this.average_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAverage(long value) {
        this.bitField0_ |= 2;
        this.average_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAverage() {
        this.bitField0_ &= -3;
        this.average_ = 0;
    }

    @Override // android.util.AggStatsOrBuilder
    public boolean hasMax() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.util.AggStatsOrBuilder
    public long getMax() {
        return this.max_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMax(long value) {
        this.bitField0_ |= 4;
        this.max_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMax() {
        this.bitField0_ &= -5;
        this.max_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.min_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.average_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.max_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.min_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.average_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.max_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AggStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AggStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AggStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AggStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AggStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AggStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AggStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AggStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AggStats parseFrom(InputStream input) throws IOException {
        return (AggStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AggStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AggStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AggStats parseDelimitedFrom(InputStream input) throws IOException {
        return (AggStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AggStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AggStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AggStats parseFrom(CodedInputStream input) throws IOException {
        return (AggStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AggStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AggStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AggStats prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AggStats, Builder> implements AggStatsOrBuilder {
        private Builder() {
            super(AggStats.DEFAULT_INSTANCE);
        }

        @Override // android.util.AggStatsOrBuilder
        public boolean hasMin() {
            return ((AggStats) this.instance).hasMin();
        }

        @Override // android.util.AggStatsOrBuilder
        public long getMin() {
            return ((AggStats) this.instance).getMin();
        }

        public Builder setMin(long value) {
            copyOnWrite();
            ((AggStats) this.instance).setMin(value);
            return this;
        }

        public Builder clearMin() {
            copyOnWrite();
            ((AggStats) this.instance).clearMin();
            return this;
        }

        @Override // android.util.AggStatsOrBuilder
        public boolean hasAverage() {
            return ((AggStats) this.instance).hasAverage();
        }

        @Override // android.util.AggStatsOrBuilder
        public long getAverage() {
            return ((AggStats) this.instance).getAverage();
        }

        public Builder setAverage(long value) {
            copyOnWrite();
            ((AggStats) this.instance).setAverage(value);
            return this;
        }

        public Builder clearAverage() {
            copyOnWrite();
            ((AggStats) this.instance).clearAverage();
            return this;
        }

        @Override // android.util.AggStatsOrBuilder
        public boolean hasMax() {
            return ((AggStats) this.instance).hasMax();
        }

        @Override // android.util.AggStatsOrBuilder
        public long getMax() {
            return ((AggStats) this.instance).getMax();
        }

        public Builder setMax(long value) {
            copyOnWrite();
            ((AggStats) this.instance).setMax(value);
            return this;
        }

        public Builder clearMax() {
            copyOnWrite();
            ((AggStats) this.instance).clearMax();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AggStats();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AggStats other = (AggStats) arg1;
                this.min_ = visitor.visitLong(hasMin(), this.min_, other.hasMin(), other.min_);
                this.average_ = visitor.visitLong(hasAverage(), this.average_, other.hasAverage(), other.average_);
                this.max_ = visitor.visitLong(hasMax(), this.max_, other.hasMax(), other.max_);
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
                            this.min_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.average_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.max_ = input.readInt64();
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
                    synchronized (AggStats.class) {
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

    public static AggStats getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AggStats> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
