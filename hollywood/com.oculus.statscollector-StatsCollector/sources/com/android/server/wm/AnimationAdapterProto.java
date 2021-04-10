package com.android.server.wm;

import com.android.server.wm.LocalAnimationAdapterProto;
import com.android.server.wm.RemoteAnimationAdapterWrapperProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AnimationAdapterProto extends GeneratedMessageLite<AnimationAdapterProto, Builder> implements AnimationAdapterProtoOrBuilder {
    private static final AnimationAdapterProto DEFAULT_INSTANCE = new AnimationAdapterProto();
    public static final int LOCAL_FIELD_NUMBER = 1;
    private static volatile Parser<AnimationAdapterProto> PARSER = null;
    public static final int REMOTE_FIELD_NUMBER = 2;
    private int bitField0_;
    private LocalAnimationAdapterProto local_;
    private RemoteAnimationAdapterWrapperProto remote_;

    private AnimationAdapterProto() {
    }

    @Override // com.android.server.wm.AnimationAdapterProtoOrBuilder
    public boolean hasLocal() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.AnimationAdapterProtoOrBuilder
    public LocalAnimationAdapterProto getLocal() {
        LocalAnimationAdapterProto localAnimationAdapterProto = this.local_;
        return localAnimationAdapterProto == null ? LocalAnimationAdapterProto.getDefaultInstance() : localAnimationAdapterProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLocal(LocalAnimationAdapterProto value) {
        if (value != null) {
            this.local_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLocal(LocalAnimationAdapterProto.Builder builderForValue) {
        this.local_ = (LocalAnimationAdapterProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLocal(LocalAnimationAdapterProto value) {
        LocalAnimationAdapterProto localAnimationAdapterProto = this.local_;
        if (localAnimationAdapterProto == null || localAnimationAdapterProto == LocalAnimationAdapterProto.getDefaultInstance()) {
            this.local_ = value;
        } else {
            this.local_ = (LocalAnimationAdapterProto) ((LocalAnimationAdapterProto.Builder) LocalAnimationAdapterProto.newBuilder(this.local_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLocal() {
        this.local_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.AnimationAdapterProtoOrBuilder
    public boolean hasRemote() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.AnimationAdapterProtoOrBuilder
    public RemoteAnimationAdapterWrapperProto getRemote() {
        RemoteAnimationAdapterWrapperProto remoteAnimationAdapterWrapperProto = this.remote_;
        return remoteAnimationAdapterWrapperProto == null ? RemoteAnimationAdapterWrapperProto.getDefaultInstance() : remoteAnimationAdapterWrapperProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRemote(RemoteAnimationAdapterWrapperProto value) {
        if (value != null) {
            this.remote_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRemote(RemoteAnimationAdapterWrapperProto.Builder builderForValue) {
        this.remote_ = (RemoteAnimationAdapterWrapperProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRemote(RemoteAnimationAdapterWrapperProto value) {
        RemoteAnimationAdapterWrapperProto remoteAnimationAdapterWrapperProto = this.remote_;
        if (remoteAnimationAdapterWrapperProto == null || remoteAnimationAdapterWrapperProto == RemoteAnimationAdapterWrapperProto.getDefaultInstance()) {
            this.remote_ = value;
        } else {
            this.remote_ = (RemoteAnimationAdapterWrapperProto) ((RemoteAnimationAdapterWrapperProto.Builder) RemoteAnimationAdapterWrapperProto.newBuilder(this.remote_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRemote() {
        this.remote_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getLocal());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getRemote());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getLocal());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getRemote());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AnimationAdapterProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AnimationAdapterProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AnimationAdapterProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AnimationAdapterProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AnimationAdapterProto parseFrom(InputStream input) throws IOException {
        return (AnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AnimationAdapterProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AnimationAdapterProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AnimationAdapterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AnimationAdapterProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AnimationAdapterProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AnimationAdapterProto parseFrom(CodedInputStream input) throws IOException {
        return (AnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AnimationAdapterProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AnimationAdapterProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AnimationAdapterProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AnimationAdapterProto, Builder> implements AnimationAdapterProtoOrBuilder {
        private Builder() {
            super(AnimationAdapterProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.AnimationAdapterProtoOrBuilder
        public boolean hasLocal() {
            return ((AnimationAdapterProto) this.instance).hasLocal();
        }

        @Override // com.android.server.wm.AnimationAdapterProtoOrBuilder
        public LocalAnimationAdapterProto getLocal() {
            return ((AnimationAdapterProto) this.instance).getLocal();
        }

        public Builder setLocal(LocalAnimationAdapterProto value) {
            copyOnWrite();
            ((AnimationAdapterProto) this.instance).setLocal((AnimationAdapterProto) value);
            return this;
        }

        public Builder setLocal(LocalAnimationAdapterProto.Builder builderForValue) {
            copyOnWrite();
            ((AnimationAdapterProto) this.instance).setLocal((AnimationAdapterProto) builderForValue);
            return this;
        }

        public Builder mergeLocal(LocalAnimationAdapterProto value) {
            copyOnWrite();
            ((AnimationAdapterProto) this.instance).mergeLocal(value);
            return this;
        }

        public Builder clearLocal() {
            copyOnWrite();
            ((AnimationAdapterProto) this.instance).clearLocal();
            return this;
        }

        @Override // com.android.server.wm.AnimationAdapterProtoOrBuilder
        public boolean hasRemote() {
            return ((AnimationAdapterProto) this.instance).hasRemote();
        }

        @Override // com.android.server.wm.AnimationAdapterProtoOrBuilder
        public RemoteAnimationAdapterWrapperProto getRemote() {
            return ((AnimationAdapterProto) this.instance).getRemote();
        }

        public Builder setRemote(RemoteAnimationAdapterWrapperProto value) {
            copyOnWrite();
            ((AnimationAdapterProto) this.instance).setRemote((AnimationAdapterProto) value);
            return this;
        }

        public Builder setRemote(RemoteAnimationAdapterWrapperProto.Builder builderForValue) {
            copyOnWrite();
            ((AnimationAdapterProto) this.instance).setRemote((AnimationAdapterProto) builderForValue);
            return this;
        }

        public Builder mergeRemote(RemoteAnimationAdapterWrapperProto value) {
            copyOnWrite();
            ((AnimationAdapterProto) this.instance).mergeRemote(value);
            return this;
        }

        public Builder clearRemote() {
            copyOnWrite();
            ((AnimationAdapterProto) this.instance).clearRemote();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AnimationAdapterProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AnimationAdapterProto other = (AnimationAdapterProto) arg1;
                this.local_ = (LocalAnimationAdapterProto) visitor.visitMessage(this.local_, other.local_);
                this.remote_ = (RemoteAnimationAdapterWrapperProto) visitor.visitMessage(this.remote_, other.remote_);
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
                            LocalAnimationAdapterProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (LocalAnimationAdapterProto.Builder) this.local_.toBuilder();
                            }
                            this.local_ = (LocalAnimationAdapterProto) input.readMessage(LocalAnimationAdapterProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.local_);
                                this.local_ = (LocalAnimationAdapterProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            RemoteAnimationAdapterWrapperProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (RemoteAnimationAdapterWrapperProto.Builder) this.remote_.toBuilder();
                            }
                            this.remote_ = (RemoteAnimationAdapterWrapperProto) input.readMessage(RemoteAnimationAdapterWrapperProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.remote_);
                                this.remote_ = (RemoteAnimationAdapterWrapperProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
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
                    synchronized (AnimationAdapterProto.class) {
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

    public static AnimationAdapterProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AnimationAdapterProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
