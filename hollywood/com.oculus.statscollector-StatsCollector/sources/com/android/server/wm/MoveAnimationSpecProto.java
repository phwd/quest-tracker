package com.android.server.wm;

import android.graphics.PointProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class MoveAnimationSpecProto extends GeneratedMessageLite<MoveAnimationSpecProto, Builder> implements MoveAnimationSpecProtoOrBuilder {
    private static final MoveAnimationSpecProto DEFAULT_INSTANCE = new MoveAnimationSpecProto();
    public static final int DURATION_MS_FIELD_NUMBER = 3;
    public static final int FROM_FIELD_NUMBER = 1;
    private static volatile Parser<MoveAnimationSpecProto> PARSER = null;
    public static final int TO_FIELD_NUMBER = 2;
    private int bitField0_;
    private long durationMs_ = 0;
    private PointProto from_;
    private PointProto to_;

    private MoveAnimationSpecProto() {
    }

    @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
    public boolean hasFrom() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
    public PointProto getFrom() {
        PointProto pointProto = this.from_;
        return pointProto == null ? PointProto.getDefaultInstance() : pointProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrom(PointProto value) {
        if (value != null) {
            this.from_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFrom(PointProto.Builder builderForValue) {
        this.from_ = (PointProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFrom(PointProto value) {
        PointProto pointProto = this.from_;
        if (pointProto == null || pointProto == PointProto.getDefaultInstance()) {
            this.from_ = value;
        } else {
            this.from_ = (PointProto) ((PointProto.Builder) PointProto.newBuilder(this.from_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFrom() {
        this.from_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
    public boolean hasTo() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
    public PointProto getTo() {
        PointProto pointProto = this.to_;
        return pointProto == null ? PointProto.getDefaultInstance() : pointProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTo(PointProto value) {
        if (value != null) {
            this.to_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTo(PointProto.Builder builderForValue) {
        this.to_ = (PointProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTo(PointProto value) {
        PointProto pointProto = this.to_;
        if (pointProto == null || pointProto == PointProto.getDefaultInstance()) {
            this.to_ = value;
        } else {
            this.to_ = (PointProto) ((PointProto.Builder) PointProto.newBuilder(this.to_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTo() {
        this.to_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
    public boolean hasDurationMs() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
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
            output.writeMessage(1, getFrom());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getTo());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getFrom());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getTo());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.durationMs_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static MoveAnimationSpecProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MoveAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MoveAnimationSpecProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MoveAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MoveAnimationSpecProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MoveAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MoveAnimationSpecProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MoveAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MoveAnimationSpecProto parseFrom(InputStream input) throws IOException {
        return (MoveAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MoveAnimationSpecProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MoveAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MoveAnimationSpecProto parseDelimitedFrom(InputStream input) throws IOException {
        return (MoveAnimationSpecProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MoveAnimationSpecProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MoveAnimationSpecProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MoveAnimationSpecProto parseFrom(CodedInputStream input) throws IOException {
        return (MoveAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MoveAnimationSpecProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MoveAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MoveAnimationSpecProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MoveAnimationSpecProto, Builder> implements MoveAnimationSpecProtoOrBuilder {
        private Builder() {
            super(MoveAnimationSpecProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
        public boolean hasFrom() {
            return ((MoveAnimationSpecProto) this.instance).hasFrom();
        }

        @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
        public PointProto getFrom() {
            return ((MoveAnimationSpecProto) this.instance).getFrom();
        }

        public Builder setFrom(PointProto value) {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).setFrom((MoveAnimationSpecProto) value);
            return this;
        }

        public Builder setFrom(PointProto.Builder builderForValue) {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).setFrom((MoveAnimationSpecProto) builderForValue);
            return this;
        }

        public Builder mergeFrom(PointProto value) {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).mergeFrom(value);
            return this;
        }

        public Builder clearFrom() {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).clearFrom();
            return this;
        }

        @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
        public boolean hasTo() {
            return ((MoveAnimationSpecProto) this.instance).hasTo();
        }

        @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
        public PointProto getTo() {
            return ((MoveAnimationSpecProto) this.instance).getTo();
        }

        public Builder setTo(PointProto value) {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).setTo((MoveAnimationSpecProto) value);
            return this;
        }

        public Builder setTo(PointProto.Builder builderForValue) {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).setTo((MoveAnimationSpecProto) builderForValue);
            return this;
        }

        public Builder mergeTo(PointProto value) {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).mergeTo(value);
            return this;
        }

        public Builder clearTo() {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).clearTo();
            return this;
        }

        @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
        public boolean hasDurationMs() {
            return ((MoveAnimationSpecProto) this.instance).hasDurationMs();
        }

        @Override // com.android.server.wm.MoveAnimationSpecProtoOrBuilder
        public long getDurationMs() {
            return ((MoveAnimationSpecProto) this.instance).getDurationMs();
        }

        public Builder setDurationMs(long value) {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).setDurationMs(value);
            return this;
        }

        public Builder clearDurationMs() {
            copyOnWrite();
            ((MoveAnimationSpecProto) this.instance).clearDurationMs();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new MoveAnimationSpecProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                MoveAnimationSpecProto other = (MoveAnimationSpecProto) arg1;
                this.from_ = (PointProto) visitor.visitMessage(this.from_, other.from_);
                this.to_ = (PointProto) visitor.visitMessage(this.to_, other.to_);
                this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
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
                        } else if (tag == 10) {
                            PointProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (PointProto.Builder) this.from_.toBuilder();
                            }
                            this.from_ = (PointProto) input.readMessage(PointProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.from_);
                                this.from_ = (PointProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            PointProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (PointProto.Builder) this.to_.toBuilder();
                            }
                            this.to_ = (PointProto) input.readMessage(PointProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.to_);
                                this.to_ = (PointProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
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
                    synchronized (MoveAnimationSpecProto.class) {
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

    public static MoveAnimationSpecProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MoveAnimationSpecProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
