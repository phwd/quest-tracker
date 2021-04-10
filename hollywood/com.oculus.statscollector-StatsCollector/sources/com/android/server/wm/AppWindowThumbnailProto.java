package com.android.server.wm;

import com.android.server.wm.SurfaceAnimatorProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AppWindowThumbnailProto extends GeneratedMessageLite<AppWindowThumbnailProto, Builder> implements AppWindowThumbnailProtoOrBuilder {
    private static final AppWindowThumbnailProto DEFAULT_INSTANCE = new AppWindowThumbnailProto();
    public static final int HEIGHT_FIELD_NUMBER = 2;
    private static volatile Parser<AppWindowThumbnailProto> PARSER = null;
    public static final int SURFACE_ANIMATOR_FIELD_NUMBER = 3;
    public static final int WIDTH_FIELD_NUMBER = 1;
    private int bitField0_;
    private int height_ = 0;
    private SurfaceAnimatorProto surfaceAnimator_;
    private int width_ = 0;

    private AppWindowThumbnailProto() {
    }

    @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
    public boolean hasWidth() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
    public int getWidth() {
        return this.width_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWidth(int value) {
        this.bitField0_ |= 1;
        this.width_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWidth() {
        this.bitField0_ &= -2;
        this.width_ = 0;
    }

    @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
    public boolean hasHeight() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
    public int getHeight() {
        return this.height_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeight(int value) {
        this.bitField0_ |= 2;
        this.height_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHeight() {
        this.bitField0_ &= -3;
        this.height_ = 0;
    }

    @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
    public boolean hasSurfaceAnimator() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
    public SurfaceAnimatorProto getSurfaceAnimator() {
        SurfaceAnimatorProto surfaceAnimatorProto = this.surfaceAnimator_;
        return surfaceAnimatorProto == null ? SurfaceAnimatorProto.getDefaultInstance() : surfaceAnimatorProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceAnimator(SurfaceAnimatorProto value) {
        if (value != null) {
            this.surfaceAnimator_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceAnimator(SurfaceAnimatorProto.Builder builderForValue) {
        this.surfaceAnimator_ = (SurfaceAnimatorProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSurfaceAnimator(SurfaceAnimatorProto value) {
        SurfaceAnimatorProto surfaceAnimatorProto = this.surfaceAnimator_;
        if (surfaceAnimatorProto == null || surfaceAnimatorProto == SurfaceAnimatorProto.getDefaultInstance()) {
            this.surfaceAnimator_ = value;
        } else {
            this.surfaceAnimator_ = (SurfaceAnimatorProto) ((SurfaceAnimatorProto.Builder) SurfaceAnimatorProto.newBuilder(this.surfaceAnimator_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSurfaceAnimator() {
        this.surfaceAnimator_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.width_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.height_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getSurfaceAnimator());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.width_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.height_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getSurfaceAnimator());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AppWindowThumbnailProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AppWindowThumbnailProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppWindowThumbnailProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppWindowThumbnailProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppWindowThumbnailProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AppWindowThumbnailProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppWindowThumbnailProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppWindowThumbnailProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppWindowThumbnailProto parseFrom(InputStream input) throws IOException {
        return (AppWindowThumbnailProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWindowThumbnailProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWindowThumbnailProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppWindowThumbnailProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AppWindowThumbnailProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWindowThumbnailProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWindowThumbnailProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppWindowThumbnailProto parseFrom(CodedInputStream input) throws IOException {
        return (AppWindowThumbnailProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppWindowThumbnailProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppWindowThumbnailProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AppWindowThumbnailProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AppWindowThumbnailProto, Builder> implements AppWindowThumbnailProtoOrBuilder {
        private Builder() {
            super(AppWindowThumbnailProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
        public boolean hasWidth() {
            return ((AppWindowThumbnailProto) this.instance).hasWidth();
        }

        @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
        public int getWidth() {
            return ((AppWindowThumbnailProto) this.instance).getWidth();
        }

        public Builder setWidth(int value) {
            copyOnWrite();
            ((AppWindowThumbnailProto) this.instance).setWidth(value);
            return this;
        }

        public Builder clearWidth() {
            copyOnWrite();
            ((AppWindowThumbnailProto) this.instance).clearWidth();
            return this;
        }

        @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
        public boolean hasHeight() {
            return ((AppWindowThumbnailProto) this.instance).hasHeight();
        }

        @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
        public int getHeight() {
            return ((AppWindowThumbnailProto) this.instance).getHeight();
        }

        public Builder setHeight(int value) {
            copyOnWrite();
            ((AppWindowThumbnailProto) this.instance).setHeight(value);
            return this;
        }

        public Builder clearHeight() {
            copyOnWrite();
            ((AppWindowThumbnailProto) this.instance).clearHeight();
            return this;
        }

        @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
        public boolean hasSurfaceAnimator() {
            return ((AppWindowThumbnailProto) this.instance).hasSurfaceAnimator();
        }

        @Override // com.android.server.wm.AppWindowThumbnailProtoOrBuilder
        public SurfaceAnimatorProto getSurfaceAnimator() {
            return ((AppWindowThumbnailProto) this.instance).getSurfaceAnimator();
        }

        public Builder setSurfaceAnimator(SurfaceAnimatorProto value) {
            copyOnWrite();
            ((AppWindowThumbnailProto) this.instance).setSurfaceAnimator((AppWindowThumbnailProto) value);
            return this;
        }

        public Builder setSurfaceAnimator(SurfaceAnimatorProto.Builder builderForValue) {
            copyOnWrite();
            ((AppWindowThumbnailProto) this.instance).setSurfaceAnimator((AppWindowThumbnailProto) builderForValue);
            return this;
        }

        public Builder mergeSurfaceAnimator(SurfaceAnimatorProto value) {
            copyOnWrite();
            ((AppWindowThumbnailProto) this.instance).mergeSurfaceAnimator(value);
            return this;
        }

        public Builder clearSurfaceAnimator() {
            copyOnWrite();
            ((AppWindowThumbnailProto) this.instance).clearSurfaceAnimator();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AppWindowThumbnailProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AppWindowThumbnailProto other = (AppWindowThumbnailProto) arg1;
                this.width_ = visitor.visitInt(hasWidth(), this.width_, other.hasWidth(), other.width_);
                this.height_ = visitor.visitInt(hasHeight(), this.height_, other.hasHeight(), other.height_);
                this.surfaceAnimator_ = (SurfaceAnimatorProto) visitor.visitMessage(this.surfaceAnimator_, other.surfaceAnimator_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.width_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.height_ = input.readInt32();
                        } else if (tag == 26) {
                            SurfaceAnimatorProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (SurfaceAnimatorProto.Builder) this.surfaceAnimator_.toBuilder();
                            }
                            this.surfaceAnimator_ = (SurfaceAnimatorProto) input.readMessage(SurfaceAnimatorProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.surfaceAnimator_);
                                this.surfaceAnimator_ = (SurfaceAnimatorProto) subBuilder.buildPartial();
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
                    synchronized (AppWindowThumbnailProto.class) {
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

    public static AppWindowThumbnailProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AppWindowThumbnailProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
