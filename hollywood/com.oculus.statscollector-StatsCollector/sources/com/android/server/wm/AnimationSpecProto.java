package com.android.server.wm;

import com.android.server.wm.AlphaAnimationSpecProto;
import com.android.server.wm.MoveAnimationSpecProto;
import com.android.server.wm.WindowAnimationSpecProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AnimationSpecProto extends GeneratedMessageLite<AnimationSpecProto, Builder> implements AnimationSpecProtoOrBuilder {
    public static final int ALPHA_FIELD_NUMBER = 3;
    private static final AnimationSpecProto DEFAULT_INSTANCE = new AnimationSpecProto();
    public static final int MOVE_FIELD_NUMBER = 2;
    private static volatile Parser<AnimationSpecProto> PARSER = null;
    public static final int WINDOW_FIELD_NUMBER = 1;
    private AlphaAnimationSpecProto alpha_;
    private int bitField0_;
    private MoveAnimationSpecProto move_;
    private WindowAnimationSpecProto window_;

    private AnimationSpecProto() {
    }

    @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
    public boolean hasWindow() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
    public WindowAnimationSpecProto getWindow() {
        WindowAnimationSpecProto windowAnimationSpecProto = this.window_;
        return windowAnimationSpecProto == null ? WindowAnimationSpecProto.getDefaultInstance() : windowAnimationSpecProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindow(WindowAnimationSpecProto value) {
        if (value != null) {
            this.window_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindow(WindowAnimationSpecProto.Builder builderForValue) {
        this.window_ = (WindowAnimationSpecProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindow(WindowAnimationSpecProto value) {
        WindowAnimationSpecProto windowAnimationSpecProto = this.window_;
        if (windowAnimationSpecProto == null || windowAnimationSpecProto == WindowAnimationSpecProto.getDefaultInstance()) {
            this.window_ = value;
        } else {
            this.window_ = (WindowAnimationSpecProto) ((WindowAnimationSpecProto.Builder) WindowAnimationSpecProto.newBuilder(this.window_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindow() {
        this.window_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
    public boolean hasMove() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
    public MoveAnimationSpecProto getMove() {
        MoveAnimationSpecProto moveAnimationSpecProto = this.move_;
        return moveAnimationSpecProto == null ? MoveAnimationSpecProto.getDefaultInstance() : moveAnimationSpecProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMove(MoveAnimationSpecProto value) {
        if (value != null) {
            this.move_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMove(MoveAnimationSpecProto.Builder builderForValue) {
        this.move_ = (MoveAnimationSpecProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMove(MoveAnimationSpecProto value) {
        MoveAnimationSpecProto moveAnimationSpecProto = this.move_;
        if (moveAnimationSpecProto == null || moveAnimationSpecProto == MoveAnimationSpecProto.getDefaultInstance()) {
            this.move_ = value;
        } else {
            this.move_ = (MoveAnimationSpecProto) ((MoveAnimationSpecProto.Builder) MoveAnimationSpecProto.newBuilder(this.move_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMove() {
        this.move_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
    public boolean hasAlpha() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
    public AlphaAnimationSpecProto getAlpha() {
        AlphaAnimationSpecProto alphaAnimationSpecProto = this.alpha_;
        return alphaAnimationSpecProto == null ? AlphaAnimationSpecProto.getDefaultInstance() : alphaAnimationSpecProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlpha(AlphaAnimationSpecProto value) {
        if (value != null) {
            this.alpha_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlpha(AlphaAnimationSpecProto.Builder builderForValue) {
        this.alpha_ = (AlphaAnimationSpecProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAlpha(AlphaAnimationSpecProto value) {
        AlphaAnimationSpecProto alphaAnimationSpecProto = this.alpha_;
        if (alphaAnimationSpecProto == null || alphaAnimationSpecProto == AlphaAnimationSpecProto.getDefaultInstance()) {
            this.alpha_ = value;
        } else {
            this.alpha_ = (AlphaAnimationSpecProto) ((AlphaAnimationSpecProto.Builder) AlphaAnimationSpecProto.newBuilder(this.alpha_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlpha() {
        this.alpha_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getWindow());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getMove());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getAlpha());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getWindow());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getMove());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getAlpha());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AnimationSpecProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AnimationSpecProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AnimationSpecProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AnimationSpecProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AnimationSpecProto parseFrom(InputStream input) throws IOException {
        return (AnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AnimationSpecProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AnimationSpecProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AnimationSpecProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AnimationSpecProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AnimationSpecProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AnimationSpecProto parseFrom(CodedInputStream input) throws IOException {
        return (AnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AnimationSpecProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AnimationSpecProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AnimationSpecProto, Builder> implements AnimationSpecProtoOrBuilder {
        private Builder() {
            super(AnimationSpecProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
        public boolean hasWindow() {
            return ((AnimationSpecProto) this.instance).hasWindow();
        }

        @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
        public WindowAnimationSpecProto getWindow() {
            return ((AnimationSpecProto) this.instance).getWindow();
        }

        public Builder setWindow(WindowAnimationSpecProto value) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).setWindow((AnimationSpecProto) value);
            return this;
        }

        public Builder setWindow(WindowAnimationSpecProto.Builder builderForValue) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).setWindow((AnimationSpecProto) builderForValue);
            return this;
        }

        public Builder mergeWindow(WindowAnimationSpecProto value) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).mergeWindow(value);
            return this;
        }

        public Builder clearWindow() {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).clearWindow();
            return this;
        }

        @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
        public boolean hasMove() {
            return ((AnimationSpecProto) this.instance).hasMove();
        }

        @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
        public MoveAnimationSpecProto getMove() {
            return ((AnimationSpecProto) this.instance).getMove();
        }

        public Builder setMove(MoveAnimationSpecProto value) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).setMove((AnimationSpecProto) value);
            return this;
        }

        public Builder setMove(MoveAnimationSpecProto.Builder builderForValue) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).setMove((AnimationSpecProto) builderForValue);
            return this;
        }

        public Builder mergeMove(MoveAnimationSpecProto value) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).mergeMove(value);
            return this;
        }

        public Builder clearMove() {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).clearMove();
            return this;
        }

        @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
        public boolean hasAlpha() {
            return ((AnimationSpecProto) this.instance).hasAlpha();
        }

        @Override // com.android.server.wm.AnimationSpecProtoOrBuilder
        public AlphaAnimationSpecProto getAlpha() {
            return ((AnimationSpecProto) this.instance).getAlpha();
        }

        public Builder setAlpha(AlphaAnimationSpecProto value) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).setAlpha((AnimationSpecProto) value);
            return this;
        }

        public Builder setAlpha(AlphaAnimationSpecProto.Builder builderForValue) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).setAlpha((AnimationSpecProto) builderForValue);
            return this;
        }

        public Builder mergeAlpha(AlphaAnimationSpecProto value) {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).mergeAlpha(value);
            return this;
        }

        public Builder clearAlpha() {
            copyOnWrite();
            ((AnimationSpecProto) this.instance).clearAlpha();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AnimationSpecProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AnimationSpecProto other = (AnimationSpecProto) arg1;
                this.window_ = (WindowAnimationSpecProto) visitor.visitMessage(this.window_, other.window_);
                this.move_ = (MoveAnimationSpecProto) visitor.visitMessage(this.move_, other.move_);
                this.alpha_ = (AlphaAnimationSpecProto) visitor.visitMessage(this.alpha_, other.alpha_);
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
                            WindowAnimationSpecProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (WindowAnimationSpecProto.Builder) this.window_.toBuilder();
                            }
                            this.window_ = (WindowAnimationSpecProto) input.readMessage(WindowAnimationSpecProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.window_);
                                this.window_ = (WindowAnimationSpecProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            MoveAnimationSpecProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (MoveAnimationSpecProto.Builder) this.move_.toBuilder();
                            }
                            this.move_ = (MoveAnimationSpecProto) input.readMessage(MoveAnimationSpecProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.move_);
                                this.move_ = (MoveAnimationSpecProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            AlphaAnimationSpecProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (AlphaAnimationSpecProto.Builder) this.alpha_.toBuilder();
                            }
                            this.alpha_ = (AlphaAnimationSpecProto) input.readMessage(AlphaAnimationSpecProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.alpha_);
                                this.alpha_ = (AlphaAnimationSpecProto) subBuilder3.buildPartial();
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
                    synchronized (AnimationSpecProto.class) {
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

    public static AnimationSpecProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AnimationSpecProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
