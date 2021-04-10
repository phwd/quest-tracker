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

public final class WindowAnimationSpecProto extends GeneratedMessageLite<WindowAnimationSpecProto, Builder> implements WindowAnimationSpecProtoOrBuilder {
    public static final int ANIMATION_FIELD_NUMBER = 1;
    private static final WindowAnimationSpecProto DEFAULT_INSTANCE = new WindowAnimationSpecProto();
    private static volatile Parser<WindowAnimationSpecProto> PARSER;
    private String animation_ = "";
    private int bitField0_;

    private WindowAnimationSpecProto() {
    }

    @Override // com.android.server.wm.WindowAnimationSpecProtoOrBuilder
    public boolean hasAnimation() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowAnimationSpecProtoOrBuilder
    public String getAnimation() {
        return this.animation_;
    }

    @Override // com.android.server.wm.WindowAnimationSpecProtoOrBuilder
    public ByteString getAnimationBytes() {
        return ByteString.copyFromUtf8(this.animation_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimation(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.animation_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAnimation() {
        this.bitField0_ &= -2;
        this.animation_ = getDefaultInstance().getAnimation();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAnimationBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.animation_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getAnimation());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getAnimation());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowAnimationSpecProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowAnimationSpecProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowAnimationSpecProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowAnimationSpecProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowAnimationSpecProto parseFrom(InputStream input) throws IOException {
        return (WindowAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowAnimationSpecProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowAnimationSpecProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowAnimationSpecProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowAnimationSpecProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowAnimationSpecProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowAnimationSpecProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowAnimationSpecProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowAnimationSpecProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowAnimationSpecProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowAnimationSpecProto, Builder> implements WindowAnimationSpecProtoOrBuilder {
        private Builder() {
            super(WindowAnimationSpecProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowAnimationSpecProtoOrBuilder
        public boolean hasAnimation() {
            return ((WindowAnimationSpecProto) this.instance).hasAnimation();
        }

        @Override // com.android.server.wm.WindowAnimationSpecProtoOrBuilder
        public String getAnimation() {
            return ((WindowAnimationSpecProto) this.instance).getAnimation();
        }

        @Override // com.android.server.wm.WindowAnimationSpecProtoOrBuilder
        public ByteString getAnimationBytes() {
            return ((WindowAnimationSpecProto) this.instance).getAnimationBytes();
        }

        public Builder setAnimation(String value) {
            copyOnWrite();
            ((WindowAnimationSpecProto) this.instance).setAnimation(value);
            return this;
        }

        public Builder clearAnimation() {
            copyOnWrite();
            ((WindowAnimationSpecProto) this.instance).clearAnimation();
            return this;
        }

        public Builder setAnimationBytes(ByteString value) {
            copyOnWrite();
            ((WindowAnimationSpecProto) this.instance).setAnimationBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowAnimationSpecProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowAnimationSpecProto other = (WindowAnimationSpecProto) arg1;
                this.animation_ = visitor.visitString(hasAnimation(), this.animation_, other.hasAnimation(), other.animation_);
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
                        } else if (tag == 10) {
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.animation_ = s;
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
                    synchronized (WindowAnimationSpecProto.class) {
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

    public static WindowAnimationSpecProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowAnimationSpecProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
