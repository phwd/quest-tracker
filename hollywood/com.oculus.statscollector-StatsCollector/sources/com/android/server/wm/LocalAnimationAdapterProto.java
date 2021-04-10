package com.android.server.wm;

import com.android.server.wm.AnimationSpecProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class LocalAnimationAdapterProto extends GeneratedMessageLite<LocalAnimationAdapterProto, Builder> implements LocalAnimationAdapterProtoOrBuilder {
    public static final int ANIMATION_SPEC_FIELD_NUMBER = 1;
    private static final LocalAnimationAdapterProto DEFAULT_INSTANCE = new LocalAnimationAdapterProto();
    private static volatile Parser<LocalAnimationAdapterProto> PARSER;
    private AnimationSpecProto animationSpec_;
    private int bitField0_;

    private LocalAnimationAdapterProto() {
    }

    @Override // com.android.server.wm.LocalAnimationAdapterProtoOrBuilder
    public boolean hasAnimationSpec() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.LocalAnimationAdapterProtoOrBuilder
    public AnimationSpecProto getAnimationSpec() {
        AnimationSpecProto animationSpecProto = this.animationSpec_;
        return animationSpecProto == null ? AnimationSpecProto.getDefaultInstance() : animationSpecProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimationSpec(AnimationSpecProto value) {
        if (value != null) {
            this.animationSpec_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimationSpec(AnimationSpecProto.Builder builderForValue) {
        this.animationSpec_ = (AnimationSpecProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAnimationSpec(AnimationSpecProto value) {
        AnimationSpecProto animationSpecProto = this.animationSpec_;
        if (animationSpecProto == null || animationSpecProto == AnimationSpecProto.getDefaultInstance()) {
            this.animationSpec_ = value;
        } else {
            this.animationSpec_ = (AnimationSpecProto) ((AnimationSpecProto.Builder) AnimationSpecProto.newBuilder(this.animationSpec_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimationSpec() {
        this.animationSpec_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getAnimationSpec());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getAnimationSpec());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static LocalAnimationAdapterProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (LocalAnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LocalAnimationAdapterProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LocalAnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LocalAnimationAdapterProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (LocalAnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LocalAnimationAdapterProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LocalAnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LocalAnimationAdapterProto parseFrom(InputStream input) throws IOException {
        return (LocalAnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LocalAnimationAdapterProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocalAnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LocalAnimationAdapterProto parseDelimitedFrom(InputStream input) throws IOException {
        return (LocalAnimationAdapterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static LocalAnimationAdapterProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocalAnimationAdapterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LocalAnimationAdapterProto parseFrom(CodedInputStream input) throws IOException {
        return (LocalAnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LocalAnimationAdapterProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocalAnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LocalAnimationAdapterProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LocalAnimationAdapterProto, Builder> implements LocalAnimationAdapterProtoOrBuilder {
        private Builder() {
            super(LocalAnimationAdapterProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.LocalAnimationAdapterProtoOrBuilder
        public boolean hasAnimationSpec() {
            return ((LocalAnimationAdapterProto) this.instance).hasAnimationSpec();
        }

        @Override // com.android.server.wm.LocalAnimationAdapterProtoOrBuilder
        public AnimationSpecProto getAnimationSpec() {
            return ((LocalAnimationAdapterProto) this.instance).getAnimationSpec();
        }

        public Builder setAnimationSpec(AnimationSpecProto value) {
            copyOnWrite();
            ((LocalAnimationAdapterProto) this.instance).setAnimationSpec((LocalAnimationAdapterProto) value);
            return this;
        }

        public Builder setAnimationSpec(AnimationSpecProto.Builder builderForValue) {
            copyOnWrite();
            ((LocalAnimationAdapterProto) this.instance).setAnimationSpec((LocalAnimationAdapterProto) builderForValue);
            return this;
        }

        public Builder mergeAnimationSpec(AnimationSpecProto value) {
            copyOnWrite();
            ((LocalAnimationAdapterProto) this.instance).mergeAnimationSpec(value);
            return this;
        }

        public Builder clearAnimationSpec() {
            copyOnWrite();
            ((LocalAnimationAdapterProto) this.instance).clearAnimationSpec();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new LocalAnimationAdapterProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                LocalAnimationAdapterProto other = (LocalAnimationAdapterProto) arg1;
                this.animationSpec_ = (AnimationSpecProto) visitor.visitMessage(this.animationSpec_, other.animationSpec_);
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
                            AnimationSpecProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (AnimationSpecProto.Builder) this.animationSpec_.toBuilder();
                            }
                            this.animationSpec_ = (AnimationSpecProto) input.readMessage(AnimationSpecProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.animationSpec_);
                                this.animationSpec_ = (AnimationSpecProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
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
                    synchronized (LocalAnimationAdapterProto.class) {
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

    public static LocalAnimationAdapterProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LocalAnimationAdapterProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
