package com.android.server.wm;

import android.graphics.RectProto;
import com.android.server.wm.WindowSurfaceControllerProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WindowStateAnimatorProto extends GeneratedMessageLite<WindowStateAnimatorProto, Builder> implements WindowStateAnimatorProtoOrBuilder {
    private static final WindowStateAnimatorProto DEFAULT_INSTANCE = new WindowStateAnimatorProto();
    public static final int DRAW_STATE_FIELD_NUMBER = 3;
    public static final int LAST_CLIP_RECT_FIELD_NUMBER = 1;
    private static volatile Parser<WindowStateAnimatorProto> PARSER = null;
    public static final int SURFACE_FIELD_NUMBER = 2;
    public static final int SYSTEM_DECOR_RECT_FIELD_NUMBER = 4;
    private int bitField0_;
    private int drawState_ = 0;
    private RectProto lastClipRect_;
    private WindowSurfaceControllerProto surface_;
    private RectProto systemDecorRect_;

    private WindowStateAnimatorProto() {
    }

    public enum DrawState implements Internal.EnumLite {
        NO_SURFACE(0),
        DRAW_PENDING(1),
        COMMIT_DRAW_PENDING(2),
        READY_TO_SHOW(3),
        HAS_DRAWN(4);
        
        public static final int COMMIT_DRAW_PENDING_VALUE = 2;
        public static final int DRAW_PENDING_VALUE = 1;
        public static final int HAS_DRAWN_VALUE = 4;
        public static final int NO_SURFACE_VALUE = 0;
        public static final int READY_TO_SHOW_VALUE = 3;
        private static final Internal.EnumLiteMap<DrawState> internalValueMap = new Internal.EnumLiteMap<DrawState>() {
            /* class com.android.server.wm.WindowStateAnimatorProto.DrawState.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DrawState findValueByNumber(int number) {
                return DrawState.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static DrawState valueOf(int value2) {
            return forNumber(value2);
        }

        public static DrawState forNumber(int value2) {
            if (value2 == 0) {
                return NO_SURFACE;
            }
            if (value2 == 1) {
                return DRAW_PENDING;
            }
            if (value2 == 2) {
                return COMMIT_DRAW_PENDING;
            }
            if (value2 == 3) {
                return READY_TO_SHOW;
            }
            if (value2 != 4) {
                return null;
            }
            return HAS_DRAWN;
        }

        public static Internal.EnumLiteMap<DrawState> internalGetValueMap() {
            return internalValueMap;
        }

        private DrawState(int value2) {
            this.value = value2;
        }
    }

    @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
    public boolean hasLastClipRect() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
    public RectProto getLastClipRect() {
        RectProto rectProto = this.lastClipRect_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastClipRect(RectProto value) {
        if (value != null) {
            this.lastClipRect_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastClipRect(RectProto.Builder builderForValue) {
        this.lastClipRect_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLastClipRect(RectProto value) {
        RectProto rectProto = this.lastClipRect_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.lastClipRect_ = value;
        } else {
            this.lastClipRect_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.lastClipRect_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastClipRect() {
        this.lastClipRect_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
    public boolean hasSurface() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
    public WindowSurfaceControllerProto getSurface() {
        WindowSurfaceControllerProto windowSurfaceControllerProto = this.surface_;
        return windowSurfaceControllerProto == null ? WindowSurfaceControllerProto.getDefaultInstance() : windowSurfaceControllerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurface(WindowSurfaceControllerProto value) {
        if (value != null) {
            this.surface_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurface(WindowSurfaceControllerProto.Builder builderForValue) {
        this.surface_ = (WindowSurfaceControllerProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSurface(WindowSurfaceControllerProto value) {
        WindowSurfaceControllerProto windowSurfaceControllerProto = this.surface_;
        if (windowSurfaceControllerProto == null || windowSurfaceControllerProto == WindowSurfaceControllerProto.getDefaultInstance()) {
            this.surface_ = value;
        } else {
            this.surface_ = (WindowSurfaceControllerProto) ((WindowSurfaceControllerProto.Builder) WindowSurfaceControllerProto.newBuilder(this.surface_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSurface() {
        this.surface_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
    public boolean hasDrawState() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
    public DrawState getDrawState() {
        DrawState result = DrawState.forNumber(this.drawState_);
        return result == null ? DrawState.NO_SURFACE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDrawState(DrawState value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.drawState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDrawState() {
        this.bitField0_ &= -5;
        this.drawState_ = 0;
    }

    @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
    public boolean hasSystemDecorRect() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
    public RectProto getSystemDecorRect() {
        RectProto rectProto = this.systemDecorRect_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemDecorRect(RectProto value) {
        if (value != null) {
            this.systemDecorRect_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemDecorRect(RectProto.Builder builderForValue) {
        this.systemDecorRect_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSystemDecorRect(RectProto value) {
        RectProto rectProto = this.systemDecorRect_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.systemDecorRect_ = value;
        } else {
            this.systemDecorRect_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.systemDecorRect_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemDecorRect() {
        this.systemDecorRect_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getLastClipRect());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getSurface());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.drawState_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getSystemDecorRect());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getLastClipRect());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getSurface());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.drawState_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getSystemDecorRect());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowStateAnimatorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowStateAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowStateAnimatorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowStateAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowStateAnimatorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowStateAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowStateAnimatorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowStateAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowStateAnimatorProto parseFrom(InputStream input) throws IOException {
        return (WindowStateAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowStateAnimatorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowStateAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowStateAnimatorProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowStateAnimatorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowStateAnimatorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowStateAnimatorProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowStateAnimatorProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowStateAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowStateAnimatorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowStateAnimatorProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowStateAnimatorProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowStateAnimatorProto, Builder> implements WindowStateAnimatorProtoOrBuilder {
        private Builder() {
            super(WindowStateAnimatorProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
        public boolean hasLastClipRect() {
            return ((WindowStateAnimatorProto) this.instance).hasLastClipRect();
        }

        @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
        public RectProto getLastClipRect() {
            return ((WindowStateAnimatorProto) this.instance).getLastClipRect();
        }

        public Builder setLastClipRect(RectProto value) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).setLastClipRect((WindowStateAnimatorProto) value);
            return this;
        }

        public Builder setLastClipRect(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).setLastClipRect((WindowStateAnimatorProto) builderForValue);
            return this;
        }

        public Builder mergeLastClipRect(RectProto value) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).mergeLastClipRect(value);
            return this;
        }

        public Builder clearLastClipRect() {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).clearLastClipRect();
            return this;
        }

        @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
        public boolean hasSurface() {
            return ((WindowStateAnimatorProto) this.instance).hasSurface();
        }

        @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
        public WindowSurfaceControllerProto getSurface() {
            return ((WindowStateAnimatorProto) this.instance).getSurface();
        }

        public Builder setSurface(WindowSurfaceControllerProto value) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).setSurface((WindowStateAnimatorProto) value);
            return this;
        }

        public Builder setSurface(WindowSurfaceControllerProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).setSurface((WindowStateAnimatorProto) builderForValue);
            return this;
        }

        public Builder mergeSurface(WindowSurfaceControllerProto value) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).mergeSurface(value);
            return this;
        }

        public Builder clearSurface() {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).clearSurface();
            return this;
        }

        @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
        public boolean hasDrawState() {
            return ((WindowStateAnimatorProto) this.instance).hasDrawState();
        }

        @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
        public DrawState getDrawState() {
            return ((WindowStateAnimatorProto) this.instance).getDrawState();
        }

        public Builder setDrawState(DrawState value) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).setDrawState(value);
            return this;
        }

        public Builder clearDrawState() {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).clearDrawState();
            return this;
        }

        @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
        public boolean hasSystemDecorRect() {
            return ((WindowStateAnimatorProto) this.instance).hasSystemDecorRect();
        }

        @Override // com.android.server.wm.WindowStateAnimatorProtoOrBuilder
        public RectProto getSystemDecorRect() {
            return ((WindowStateAnimatorProto) this.instance).getSystemDecorRect();
        }

        public Builder setSystemDecorRect(RectProto value) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).setSystemDecorRect((WindowStateAnimatorProto) value);
            return this;
        }

        public Builder setSystemDecorRect(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).setSystemDecorRect((WindowStateAnimatorProto) builderForValue);
            return this;
        }

        public Builder mergeSystemDecorRect(RectProto value) {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).mergeSystemDecorRect(value);
            return this;
        }

        public Builder clearSystemDecorRect() {
            copyOnWrite();
            ((WindowStateAnimatorProto) this.instance).clearSystemDecorRect();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowStateAnimatorProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowStateAnimatorProto other = (WindowStateAnimatorProto) arg1;
                this.lastClipRect_ = (RectProto) visitor.visitMessage(this.lastClipRect_, other.lastClipRect_);
                this.surface_ = (WindowSurfaceControllerProto) visitor.visitMessage(this.surface_, other.surface_);
                this.drawState_ = visitor.visitInt(hasDrawState(), this.drawState_, other.hasDrawState(), other.drawState_);
                this.systemDecorRect_ = (RectProto) visitor.visitMessage(this.systemDecorRect_, other.systemDecorRect_);
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
                                subBuilder = (RectProto.Builder) this.lastClipRect_.toBuilder();
                            }
                            this.lastClipRect_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.lastClipRect_);
                                this.lastClipRect_ = (RectProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            WindowSurfaceControllerProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (WindowSurfaceControllerProto.Builder) this.surface_.toBuilder();
                            }
                            this.surface_ = (WindowSurfaceControllerProto) input.readMessage(WindowSurfaceControllerProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.surface_);
                                this.surface_ = (WindowSurfaceControllerProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 24) {
                            int rawValue = input.readEnum();
                            if (DrawState.forNumber(rawValue) == null) {
                                super.mergeVarintField(3, rawValue);
                            } else {
                                this.bitField0_ |= 4;
                                this.drawState_ = rawValue;
                            }
                        } else if (tag == 34) {
                            RectProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder3 = (RectProto.Builder) this.systemDecorRect_.toBuilder();
                            }
                            this.systemDecorRect_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.systemDecorRect_);
                                this.systemDecorRect_ = (RectProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 8;
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
                    synchronized (WindowStateAnimatorProto.class) {
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

    public static WindowStateAnimatorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowStateAnimatorProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
