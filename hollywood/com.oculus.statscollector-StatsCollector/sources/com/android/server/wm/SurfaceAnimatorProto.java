package com.android.server.wm;

import android.view.SurfaceControlProto;
import com.android.server.wm.AnimationAdapterProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class SurfaceAnimatorProto extends GeneratedMessageLite<SurfaceAnimatorProto, Builder> implements SurfaceAnimatorProtoOrBuilder {
    public static final int ANIMATION_ADAPTER_FIELD_NUMBER = 3;
    public static final int ANIMATION_START_DELAYED_FIELD_NUMBER = 2;
    private static final SurfaceAnimatorProto DEFAULT_INSTANCE = new SurfaceAnimatorProto();
    public static final int LEASH_FIELD_NUMBER = 1;
    private static volatile Parser<SurfaceAnimatorProto> PARSER;
    private AnimationAdapterProto animationAdapter_;
    private boolean animationStartDelayed_ = false;
    private int bitField0_;
    private SurfaceControlProto leash_;

    private SurfaceAnimatorProto() {
    }

    @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
    public boolean hasLeash() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
    public SurfaceControlProto getLeash() {
        SurfaceControlProto surfaceControlProto = this.leash_;
        return surfaceControlProto == null ? SurfaceControlProto.getDefaultInstance() : surfaceControlProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLeash(SurfaceControlProto value) {
        if (value != null) {
            this.leash_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLeash(SurfaceControlProto.Builder builderForValue) {
        this.leash_ = (SurfaceControlProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLeash(SurfaceControlProto value) {
        SurfaceControlProto surfaceControlProto = this.leash_;
        if (surfaceControlProto == null || surfaceControlProto == SurfaceControlProto.getDefaultInstance()) {
            this.leash_ = value;
        } else {
            this.leash_ = (SurfaceControlProto) ((SurfaceControlProto.Builder) SurfaceControlProto.newBuilder(this.leash_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLeash() {
        this.leash_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
    public boolean hasAnimationStartDelayed() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
    public boolean getAnimationStartDelayed() {
        return this.animationStartDelayed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimationStartDelayed(boolean value) {
        this.bitField0_ |= 2;
        this.animationStartDelayed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimationStartDelayed() {
        this.bitField0_ &= -3;
        this.animationStartDelayed_ = false;
    }

    @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
    public boolean hasAnimationAdapter() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
    public AnimationAdapterProto getAnimationAdapter() {
        AnimationAdapterProto animationAdapterProto = this.animationAdapter_;
        return animationAdapterProto == null ? AnimationAdapterProto.getDefaultInstance() : animationAdapterProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimationAdapter(AnimationAdapterProto value) {
        if (value != null) {
            this.animationAdapter_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimationAdapter(AnimationAdapterProto.Builder builderForValue) {
        this.animationAdapter_ = (AnimationAdapterProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAnimationAdapter(AnimationAdapterProto value) {
        AnimationAdapterProto animationAdapterProto = this.animationAdapter_;
        if (animationAdapterProto == null || animationAdapterProto == AnimationAdapterProto.getDefaultInstance()) {
            this.animationAdapter_ = value;
        } else {
            this.animationAdapter_ = (AnimationAdapterProto) ((AnimationAdapterProto.Builder) AnimationAdapterProto.newBuilder(this.animationAdapter_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimationAdapter() {
        this.animationAdapter_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getLeash());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.animationStartDelayed_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getAnimationAdapter());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getLeash());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.animationStartDelayed_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getAnimationAdapter());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static SurfaceAnimatorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SurfaceAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SurfaceAnimatorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SurfaceAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SurfaceAnimatorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SurfaceAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SurfaceAnimatorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SurfaceAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SurfaceAnimatorProto parseFrom(InputStream input) throws IOException {
        return (SurfaceAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceAnimatorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SurfaceAnimatorProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SurfaceAnimatorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceAnimatorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceAnimatorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SurfaceAnimatorProto parseFrom(CodedInputStream input) throws IOException {
        return (SurfaceAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceAnimatorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SurfaceAnimatorProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SurfaceAnimatorProto, Builder> implements SurfaceAnimatorProtoOrBuilder {
        private Builder() {
            super(SurfaceAnimatorProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
        public boolean hasLeash() {
            return ((SurfaceAnimatorProto) this.instance).hasLeash();
        }

        @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
        public SurfaceControlProto getLeash() {
            return ((SurfaceAnimatorProto) this.instance).getLeash();
        }

        public Builder setLeash(SurfaceControlProto value) {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).setLeash((SurfaceAnimatorProto) value);
            return this;
        }

        public Builder setLeash(SurfaceControlProto.Builder builderForValue) {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).setLeash((SurfaceAnimatorProto) builderForValue);
            return this;
        }

        public Builder mergeLeash(SurfaceControlProto value) {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).mergeLeash(value);
            return this;
        }

        public Builder clearLeash() {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).clearLeash();
            return this;
        }

        @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
        public boolean hasAnimationStartDelayed() {
            return ((SurfaceAnimatorProto) this.instance).hasAnimationStartDelayed();
        }

        @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
        public boolean getAnimationStartDelayed() {
            return ((SurfaceAnimatorProto) this.instance).getAnimationStartDelayed();
        }

        public Builder setAnimationStartDelayed(boolean value) {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).setAnimationStartDelayed(value);
            return this;
        }

        public Builder clearAnimationStartDelayed() {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).clearAnimationStartDelayed();
            return this;
        }

        @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
        public boolean hasAnimationAdapter() {
            return ((SurfaceAnimatorProto) this.instance).hasAnimationAdapter();
        }

        @Override // com.android.server.wm.SurfaceAnimatorProtoOrBuilder
        public AnimationAdapterProto getAnimationAdapter() {
            return ((SurfaceAnimatorProto) this.instance).getAnimationAdapter();
        }

        public Builder setAnimationAdapter(AnimationAdapterProto value) {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).setAnimationAdapter((SurfaceAnimatorProto) value);
            return this;
        }

        public Builder setAnimationAdapter(AnimationAdapterProto.Builder builderForValue) {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).setAnimationAdapter((SurfaceAnimatorProto) builderForValue);
            return this;
        }

        public Builder mergeAnimationAdapter(AnimationAdapterProto value) {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).mergeAnimationAdapter(value);
            return this;
        }

        public Builder clearAnimationAdapter() {
            copyOnWrite();
            ((SurfaceAnimatorProto) this.instance).clearAnimationAdapter();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SurfaceAnimatorProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SurfaceAnimatorProto other = (SurfaceAnimatorProto) arg1;
                this.leash_ = (SurfaceControlProto) visitor.visitMessage(this.leash_, other.leash_);
                this.animationStartDelayed_ = visitor.visitBoolean(hasAnimationStartDelayed(), this.animationStartDelayed_, other.hasAnimationStartDelayed(), other.animationStartDelayed_);
                this.animationAdapter_ = (AnimationAdapterProto) visitor.visitMessage(this.animationAdapter_, other.animationAdapter_);
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
                            SurfaceControlProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (SurfaceControlProto.Builder) this.leash_.toBuilder();
                            }
                            this.leash_ = (SurfaceControlProto) input.readMessage(SurfaceControlProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.leash_);
                                this.leash_ = (SurfaceControlProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.animationStartDelayed_ = input.readBool();
                        } else if (tag == 26) {
                            AnimationAdapterProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder2 = (AnimationAdapterProto.Builder) this.animationAdapter_.toBuilder();
                            }
                            this.animationAdapter_ = (AnimationAdapterProto) input.readMessage(AnimationAdapterProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.animationAdapter_);
                                this.animationAdapter_ = (AnimationAdapterProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 4;
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
                    synchronized (SurfaceAnimatorProto.class) {
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

    public static SurfaceAnimatorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SurfaceAnimatorProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
