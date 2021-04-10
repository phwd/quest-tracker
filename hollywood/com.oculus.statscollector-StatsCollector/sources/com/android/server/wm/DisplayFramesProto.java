package com.android.server.wm;

import android.graphics.RectProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class DisplayFramesProto extends GeneratedMessageLite<DisplayFramesProto, Builder> implements DisplayFramesProtoOrBuilder {
    private static final DisplayFramesProto DEFAULT_INSTANCE = new DisplayFramesProto();
    private static volatile Parser<DisplayFramesProto> PARSER = null;
    public static final int STABLE_BOUNDS_FIELD_NUMBER = 1;
    private int bitField0_;
    private RectProto stableBounds_;

    private DisplayFramesProto() {
    }

    @Override // com.android.server.wm.DisplayFramesProtoOrBuilder
    public boolean hasStableBounds() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.DisplayFramesProtoOrBuilder
    public RectProto getStableBounds() {
        RectProto rectProto = this.stableBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableBounds(RectProto value) {
        if (value != null) {
            this.stableBounds_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableBounds(RectProto.Builder builderForValue) {
        this.stableBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStableBounds(RectProto value) {
        RectProto rectProto = this.stableBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.stableBounds_ = value;
        } else {
            this.stableBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.stableBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStableBounds() {
        this.stableBounds_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getStableBounds());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getStableBounds());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DisplayFramesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DisplayFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayFramesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayFramesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DisplayFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DisplayFramesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DisplayFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DisplayFramesProto parseFrom(InputStream input) throws IOException {
        return (DisplayFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayFramesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayFramesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DisplayFramesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayFramesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayFramesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DisplayFramesProto parseFrom(CodedInputStream input) throws IOException {
        return (DisplayFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DisplayFramesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DisplayFramesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DisplayFramesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DisplayFramesProto, Builder> implements DisplayFramesProtoOrBuilder {
        private Builder() {
            super(DisplayFramesProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.DisplayFramesProtoOrBuilder
        public boolean hasStableBounds() {
            return ((DisplayFramesProto) this.instance).hasStableBounds();
        }

        @Override // com.android.server.wm.DisplayFramesProtoOrBuilder
        public RectProto getStableBounds() {
            return ((DisplayFramesProto) this.instance).getStableBounds();
        }

        public Builder setStableBounds(RectProto value) {
            copyOnWrite();
            ((DisplayFramesProto) this.instance).setStableBounds((DisplayFramesProto) value);
            return this;
        }

        public Builder setStableBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((DisplayFramesProto) this.instance).setStableBounds((DisplayFramesProto) builderForValue);
            return this;
        }

        public Builder mergeStableBounds(RectProto value) {
            copyOnWrite();
            ((DisplayFramesProto) this.instance).mergeStableBounds(value);
            return this;
        }

        public Builder clearStableBounds() {
            copyOnWrite();
            ((DisplayFramesProto) this.instance).clearStableBounds();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DisplayFramesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DisplayFramesProto other = (DisplayFramesProto) arg1;
                this.stableBounds_ = (RectProto) visitor.visitMessage(this.stableBounds_, other.stableBounds_);
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
                            RectProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (RectProto.Builder) this.stableBounds_.toBuilder();
                            }
                            this.stableBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.stableBounds_);
                                this.stableBounds_ = (RectProto) subBuilder.buildPartial();
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
                    synchronized (DisplayFramesProto.class) {
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

    public static DisplayFramesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DisplayFramesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
