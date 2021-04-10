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

public final class ConstantsProto extends GeneratedMessageLite<ConstantsProto, Builder> implements ConstantsProtoOrBuilder {
    public static final int ALLOW_WHILE_IDLE_LONG_DURATION_MS_FIELD_NUMBER = 5;
    public static final int ALLOW_WHILE_IDLE_SHORT_DURATION_MS_FIELD_NUMBER = 4;
    public static final int ALLOW_WHILE_IDLE_WHITELIST_DURATION_MS_FIELD_NUMBER = 6;
    private static final ConstantsProto DEFAULT_INSTANCE = new ConstantsProto();
    public static final int LISTENER_TIMEOUT_DURATION_MS_FIELD_NUMBER = 3;
    public static final int MAX_INTERVAL_DURATION_MS_FIELD_NUMBER = 7;
    public static final int MIN_FUTURITY_DURATION_MS_FIELD_NUMBER = 1;
    public static final int MIN_INTERVAL_DURATION_MS_FIELD_NUMBER = 2;
    private static volatile Parser<ConstantsProto> PARSER;
    private long allowWhileIdleLongDurationMs_ = 0;
    private long allowWhileIdleShortDurationMs_ = 0;
    private long allowWhileIdleWhitelistDurationMs_ = 0;
    private int bitField0_;
    private long listenerTimeoutDurationMs_ = 0;
    private long maxIntervalDurationMs_ = 0;
    private long minFuturityDurationMs_ = 0;
    private long minIntervalDurationMs_ = 0;

    private ConstantsProto() {
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public boolean hasMinFuturityDurationMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public long getMinFuturityDurationMs() {
        return this.minFuturityDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinFuturityDurationMs(long value) {
        this.bitField0_ |= 1;
        this.minFuturityDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinFuturityDurationMs() {
        this.bitField0_ &= -2;
        this.minFuturityDurationMs_ = 0;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public boolean hasMinIntervalDurationMs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public long getMinIntervalDurationMs() {
        return this.minIntervalDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinIntervalDurationMs(long value) {
        this.bitField0_ |= 2;
        this.minIntervalDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinIntervalDurationMs() {
        this.bitField0_ &= -3;
        this.minIntervalDurationMs_ = 0;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public boolean hasListenerTimeoutDurationMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public long getListenerTimeoutDurationMs() {
        return this.listenerTimeoutDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenerTimeoutDurationMs(long value) {
        this.bitField0_ |= 4;
        this.listenerTimeoutDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearListenerTimeoutDurationMs() {
        this.bitField0_ &= -5;
        this.listenerTimeoutDurationMs_ = 0;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public boolean hasAllowWhileIdleShortDurationMs() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public long getAllowWhileIdleShortDurationMs() {
        return this.allowWhileIdleShortDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowWhileIdleShortDurationMs(long value) {
        this.bitField0_ |= 8;
        this.allowWhileIdleShortDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAllowWhileIdleShortDurationMs() {
        this.bitField0_ &= -9;
        this.allowWhileIdleShortDurationMs_ = 0;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public boolean hasAllowWhileIdleLongDurationMs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public long getAllowWhileIdleLongDurationMs() {
        return this.allowWhileIdleLongDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowWhileIdleLongDurationMs(long value) {
        this.bitField0_ |= 16;
        this.allowWhileIdleLongDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAllowWhileIdleLongDurationMs() {
        this.bitField0_ &= -17;
        this.allowWhileIdleLongDurationMs_ = 0;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public boolean hasAllowWhileIdleWhitelistDurationMs() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public long getAllowWhileIdleWhitelistDurationMs() {
        return this.allowWhileIdleWhitelistDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAllowWhileIdleWhitelistDurationMs(long value) {
        this.bitField0_ |= 32;
        this.allowWhileIdleWhitelistDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAllowWhileIdleWhitelistDurationMs() {
        this.bitField0_ &= -33;
        this.allowWhileIdleWhitelistDurationMs_ = 0;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public boolean hasMaxIntervalDurationMs() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.ConstantsProtoOrBuilder
    public long getMaxIntervalDurationMs() {
        return this.maxIntervalDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxIntervalDurationMs(long value) {
        this.bitField0_ |= 64;
        this.maxIntervalDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxIntervalDurationMs() {
        this.bitField0_ &= -65;
        this.maxIntervalDurationMs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.minFuturityDurationMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.minIntervalDurationMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.listenerTimeoutDurationMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.allowWhileIdleShortDurationMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.allowWhileIdleLongDurationMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(6, this.allowWhileIdleWhitelistDurationMs_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt64(7, this.maxIntervalDurationMs_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.minFuturityDurationMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.minIntervalDurationMs_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.listenerTimeoutDurationMs_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.allowWhileIdleShortDurationMs_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.allowWhileIdleLongDurationMs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(6, this.allowWhileIdleWhitelistDurationMs_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt64Size(7, this.maxIntervalDurationMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ConstantsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConstantsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConstantsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ConstantsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ConstantsProto parseFrom(InputStream input) throws IOException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConstantsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConstantsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ConstantsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ConstantsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConstantsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ConstantsProto parseFrom(CodedInputStream input) throws IOException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ConstantsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConstantsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ConstantsProto, Builder> implements ConstantsProtoOrBuilder {
        private Builder() {
            super(ConstantsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public boolean hasMinFuturityDurationMs() {
            return ((ConstantsProto) this.instance).hasMinFuturityDurationMs();
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public long getMinFuturityDurationMs() {
            return ((ConstantsProto) this.instance).getMinFuturityDurationMs();
        }

        public Builder setMinFuturityDurationMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinFuturityDurationMs(value);
            return this;
        }

        public Builder clearMinFuturityDurationMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinFuturityDurationMs();
            return this;
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public boolean hasMinIntervalDurationMs() {
            return ((ConstantsProto) this.instance).hasMinIntervalDurationMs();
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public long getMinIntervalDurationMs() {
            return ((ConstantsProto) this.instance).getMinIntervalDurationMs();
        }

        public Builder setMinIntervalDurationMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMinIntervalDurationMs(value);
            return this;
        }

        public Builder clearMinIntervalDurationMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMinIntervalDurationMs();
            return this;
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public boolean hasListenerTimeoutDurationMs() {
            return ((ConstantsProto) this.instance).hasListenerTimeoutDurationMs();
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public long getListenerTimeoutDurationMs() {
            return ((ConstantsProto) this.instance).getListenerTimeoutDurationMs();
        }

        public Builder setListenerTimeoutDurationMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setListenerTimeoutDurationMs(value);
            return this;
        }

        public Builder clearListenerTimeoutDurationMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearListenerTimeoutDurationMs();
            return this;
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public boolean hasAllowWhileIdleShortDurationMs() {
            return ((ConstantsProto) this.instance).hasAllowWhileIdleShortDurationMs();
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public long getAllowWhileIdleShortDurationMs() {
            return ((ConstantsProto) this.instance).getAllowWhileIdleShortDurationMs();
        }

        public Builder setAllowWhileIdleShortDurationMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setAllowWhileIdleShortDurationMs(value);
            return this;
        }

        public Builder clearAllowWhileIdleShortDurationMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearAllowWhileIdleShortDurationMs();
            return this;
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public boolean hasAllowWhileIdleLongDurationMs() {
            return ((ConstantsProto) this.instance).hasAllowWhileIdleLongDurationMs();
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public long getAllowWhileIdleLongDurationMs() {
            return ((ConstantsProto) this.instance).getAllowWhileIdleLongDurationMs();
        }

        public Builder setAllowWhileIdleLongDurationMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setAllowWhileIdleLongDurationMs(value);
            return this;
        }

        public Builder clearAllowWhileIdleLongDurationMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearAllowWhileIdleLongDurationMs();
            return this;
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public boolean hasAllowWhileIdleWhitelistDurationMs() {
            return ((ConstantsProto) this.instance).hasAllowWhileIdleWhitelistDurationMs();
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public long getAllowWhileIdleWhitelistDurationMs() {
            return ((ConstantsProto) this.instance).getAllowWhileIdleWhitelistDurationMs();
        }

        public Builder setAllowWhileIdleWhitelistDurationMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setAllowWhileIdleWhitelistDurationMs(value);
            return this;
        }

        public Builder clearAllowWhileIdleWhitelistDurationMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearAllowWhileIdleWhitelistDurationMs();
            return this;
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public boolean hasMaxIntervalDurationMs() {
            return ((ConstantsProto) this.instance).hasMaxIntervalDurationMs();
        }

        @Override // com.android.server.ConstantsProtoOrBuilder
        public long getMaxIntervalDurationMs() {
            return ((ConstantsProto) this.instance).getMaxIntervalDurationMs();
        }

        public Builder setMaxIntervalDurationMs(long value) {
            copyOnWrite();
            ((ConstantsProto) this.instance).setMaxIntervalDurationMs(value);
            return this;
        }

        public Builder clearMaxIntervalDurationMs() {
            copyOnWrite();
            ((ConstantsProto) this.instance).clearMaxIntervalDurationMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ConstantsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ConstantsProto other = (ConstantsProto) arg1;
                this.minFuturityDurationMs_ = visitor.visitLong(hasMinFuturityDurationMs(), this.minFuturityDurationMs_, other.hasMinFuturityDurationMs(), other.minFuturityDurationMs_);
                this.minIntervalDurationMs_ = visitor.visitLong(hasMinIntervalDurationMs(), this.minIntervalDurationMs_, other.hasMinIntervalDurationMs(), other.minIntervalDurationMs_);
                this.listenerTimeoutDurationMs_ = visitor.visitLong(hasListenerTimeoutDurationMs(), this.listenerTimeoutDurationMs_, other.hasListenerTimeoutDurationMs(), other.listenerTimeoutDurationMs_);
                this.allowWhileIdleShortDurationMs_ = visitor.visitLong(hasAllowWhileIdleShortDurationMs(), this.allowWhileIdleShortDurationMs_, other.hasAllowWhileIdleShortDurationMs(), other.allowWhileIdleShortDurationMs_);
                this.allowWhileIdleLongDurationMs_ = visitor.visitLong(hasAllowWhileIdleLongDurationMs(), this.allowWhileIdleLongDurationMs_, other.hasAllowWhileIdleLongDurationMs(), other.allowWhileIdleLongDurationMs_);
                this.allowWhileIdleWhitelistDurationMs_ = visitor.visitLong(hasAllowWhileIdleWhitelistDurationMs(), this.allowWhileIdleWhitelistDurationMs_, other.hasAllowWhileIdleWhitelistDurationMs(), other.allowWhileIdleWhitelistDurationMs_);
                this.maxIntervalDurationMs_ = visitor.visitLong(hasMaxIntervalDurationMs(), this.maxIntervalDurationMs_, other.hasMaxIntervalDurationMs(), other.maxIntervalDurationMs_);
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
                            this.minFuturityDurationMs_ = input.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.minIntervalDurationMs_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.listenerTimeoutDurationMs_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.allowWhileIdleShortDurationMs_ = input.readInt64();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.allowWhileIdleLongDurationMs_ = input.readInt64();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.allowWhileIdleWhitelistDurationMs_ = input.readInt64();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.maxIntervalDurationMs_ = input.readInt64();
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
                    synchronized (ConstantsProto.class) {
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

    public static ConstantsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConstantsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
