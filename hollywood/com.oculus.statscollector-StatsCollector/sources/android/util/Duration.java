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

public final class Duration extends GeneratedMessageLite<Duration, Builder> implements DurationOrBuilder {
    private static final Duration DEFAULT_INSTANCE = new Duration();
    public static final int END_MS_FIELD_NUMBER = 2;
    private static volatile Parser<Duration> PARSER = null;
    public static final int START_MS_FIELD_NUMBER = 1;
    private int bitField0_;
    private long endMs_ = 0;
    private long startMs_ = 0;

    private Duration() {
    }

    @Override // android.util.DurationOrBuilder
    public boolean hasStartMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.util.DurationOrBuilder
    public long getStartMs() {
        return this.startMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartMs(long value) {
        this.bitField0_ |= 1;
        this.startMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartMs() {
        this.bitField0_ &= -2;
        this.startMs_ = 0;
    }

    @Override // android.util.DurationOrBuilder
    public boolean hasEndMs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.util.DurationOrBuilder
    public long getEndMs() {
        return this.endMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndMs(long value) {
        this.bitField0_ |= 2;
        this.endMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndMs() {
        this.bitField0_ &= -3;
        this.endMs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.startMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.endMs_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.startMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.endMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static Duration parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Duration parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Duration parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static Duration parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static Duration parseFrom(InputStream input) throws IOException {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Duration parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Duration parseDelimitedFrom(InputStream input) throws IOException {
        return (Duration) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static Duration parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Duration) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Duration parseFrom(CodedInputStream input) throws IOException {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static Duration parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Duration prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Duration, Builder> implements DurationOrBuilder {
        private Builder() {
            super(Duration.DEFAULT_INSTANCE);
        }

        @Override // android.util.DurationOrBuilder
        public boolean hasStartMs() {
            return ((Duration) this.instance).hasStartMs();
        }

        @Override // android.util.DurationOrBuilder
        public long getStartMs() {
            return ((Duration) this.instance).getStartMs();
        }

        public Builder setStartMs(long value) {
            copyOnWrite();
            ((Duration) this.instance).setStartMs(value);
            return this;
        }

        public Builder clearStartMs() {
            copyOnWrite();
            ((Duration) this.instance).clearStartMs();
            return this;
        }

        @Override // android.util.DurationOrBuilder
        public boolean hasEndMs() {
            return ((Duration) this.instance).hasEndMs();
        }

        @Override // android.util.DurationOrBuilder
        public long getEndMs() {
            return ((Duration) this.instance).getEndMs();
        }

        public Builder setEndMs(long value) {
            copyOnWrite();
            ((Duration) this.instance).setEndMs(value);
            return this;
        }

        public Builder clearEndMs() {
            copyOnWrite();
            ((Duration) this.instance).clearEndMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new Duration();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                Duration other = (Duration) arg1;
                this.startMs_ = visitor.visitLong(hasStartMs(), this.startMs_, other.hasStartMs(), other.startMs_);
                this.endMs_ = visitor.visitLong(hasEndMs(), this.endMs_, other.hasEndMs(), other.endMs_);
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
                            this.startMs_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.endMs_ = input.readInt64();
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
                    synchronized (Duration.class) {
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

    public static Duration getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Duration> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
