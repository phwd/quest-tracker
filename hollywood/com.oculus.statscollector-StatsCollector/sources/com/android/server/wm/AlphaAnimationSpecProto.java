package com.android.server.wm;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AlphaAnimationSpecProto extends GeneratedMessageLite<AlphaAnimationSpecProto, Builder> implements AlphaAnimationSpecProtoOrBuilder {
    private static final AlphaAnimationSpecProto DEFAULT_INSTANCE = new AlphaAnimationSpecProto();
    public static final int DURATION_MS_FIELD_NUMBER = 3;
    public static final int FROM_FIELD_NUMBER = 1;
    private static volatile Parser<AlphaAnimationSpecProto> PARSER = null;
    public static final int TO_FIELD_NUMBER = 2;
    private int bitField0_;
    private long durationMs_ = 0;
    private float from_ = 0.0f;
    private float to_ = 0.0f;

    private AlphaAnimationSpecProto() {
    }

    @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
    public boolean hasFrom() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
    public float getFrom() {
        return this.from_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrom(float value) {
        this.bitField0_ |= 1;
        this.from_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFrom() {
        this.bitField0_ &= -2;
        this.from_ = 0.0f;
    }

    @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
    public boolean hasTo() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
    public float getTo() {
        return this.to_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTo(float value) {
        this.bitField0_ |= 2;
        this.to_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTo() {
        this.bitField0_ &= -3;
        this.to_ = 0.0f;
    }

    @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
    public boolean hasDurationMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
    public long getDurationMs() {
        return this.durationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDurationMs(long value) {
        this.bitField0_ |= 4;
        this.durationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDurationMs() {
        this.bitField0_ &= -5;
        this.durationMs_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeFloat(1, this.from_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeFloat(2, this.to_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.durationMs_);
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
            size2 = 0 + CodedOutputStream.computeFloatSize(1, this.from_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeFloatSize(2, this.to_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.durationMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AlphaAnimationSpecProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AlphaAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlphaAnimationSpecProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlphaAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlphaAnimationSpecProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AlphaAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AlphaAnimationSpecProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AlphaAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AlphaAnimationSpecProto parseFrom(InputStream input) throws IOException {
        return (AlphaAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlphaAnimationSpecProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlphaAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlphaAnimationSpecProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AlphaAnimationSpecProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AlphaAnimationSpecProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlphaAnimationSpecProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AlphaAnimationSpecProto parseFrom(CodedInputStream input) throws IOException {
        return (AlphaAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AlphaAnimationSpecProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AlphaAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AlphaAnimationSpecProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AlphaAnimationSpecProto, Builder> implements AlphaAnimationSpecProtoOrBuilder {
        private Builder() {
            super(AlphaAnimationSpecProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
        public boolean hasFrom() {
            return ((AlphaAnimationSpecProto) this.instance).hasFrom();
        }

        @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
        public float getFrom() {
            return ((AlphaAnimationSpecProto) this.instance).getFrom();
        }

        public Builder setFrom(float value) {
            copyOnWrite();
            ((AlphaAnimationSpecProto) this.instance).setFrom(value);
            return this;
        }

        public Builder clearFrom() {
            copyOnWrite();
            ((AlphaAnimationSpecProto) this.instance).clearFrom();
            return this;
        }

        @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
        public boolean hasTo() {
            return ((AlphaAnimationSpecProto) this.instance).hasTo();
        }

        @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
        public float getTo() {
            return ((AlphaAnimationSpecProto) this.instance).getTo();
        }

        public Builder setTo(float value) {
            copyOnWrite();
            ((AlphaAnimationSpecProto) this.instance).setTo(value);
            return this;
        }

        public Builder clearTo() {
            copyOnWrite();
            ((AlphaAnimationSpecProto) this.instance).clearTo();
            return this;
        }

        @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
        public boolean hasDurationMs() {
            return ((AlphaAnimationSpecProto) this.instance).hasDurationMs();
        }

        @Override // com.android.server.wm.AlphaAnimationSpecProtoOrBuilder
        public long getDurationMs() {
            return ((AlphaAnimationSpecProto) this.instance).getDurationMs();
        }

        public Builder setDurationMs(long value) {
            copyOnWrite();
            ((AlphaAnimationSpecProto) this.instance).setDurationMs(value);
            return this;
        }

        public Builder clearDurationMs() {
            copyOnWrite();
            ((AlphaAnimationSpecProto) this.instance).clearDurationMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AlphaAnimationSpecProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AlphaAnimationSpecProto other = (AlphaAnimationSpecProto) arg1;
                this.from_ = visitor.visitFloat(hasFrom(), this.from_, other.hasFrom(), other.from_);
                this.to_ = visitor.visitFloat(hasTo(), this.to_, other.hasTo(), other.to_);
                this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
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
                        } else if (tag == 13) {
                            this.bitField0_ |= 1;
                            this.from_ = input.readFloat();
                        } else if (tag == 21) {
                            this.bitField0_ |= 2;
                            this.to_ = input.readFloat();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.durationMs_ = input.readInt64();
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
                    synchronized (AlphaAnimationSpecProto.class) {
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

    public static AlphaAnimationSpecProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AlphaAnimationSpecProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
