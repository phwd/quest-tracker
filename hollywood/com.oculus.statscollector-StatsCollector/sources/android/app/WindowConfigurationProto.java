package android.app;

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

public final class WindowConfigurationProto extends GeneratedMessageLite<WindowConfigurationProto, Builder> implements WindowConfigurationProtoOrBuilder {
    public static final int ACTIVITY_TYPE_FIELD_NUMBER = 3;
    public static final int APP_BOUNDS_FIELD_NUMBER = 1;
    public static final int BOUNDS_FIELD_NUMBER = 4;
    private static final WindowConfigurationProto DEFAULT_INSTANCE = new WindowConfigurationProto();
    private static volatile Parser<WindowConfigurationProto> PARSER = null;
    public static final int WINDOWING_MODE_FIELD_NUMBER = 2;
    private int activityType_ = 0;
    private RectProto appBounds_;
    private int bitField0_;
    private RectProto bounds_;
    private int windowingMode_ = 0;

    private WindowConfigurationProto() {
    }

    @Override // android.app.WindowConfigurationProtoOrBuilder
    public boolean hasAppBounds() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.app.WindowConfigurationProtoOrBuilder
    public RectProto getAppBounds() {
        RectProto rectProto = this.appBounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppBounds(RectProto value) {
        if (value != null) {
            this.appBounds_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppBounds(RectProto.Builder builderForValue) {
        this.appBounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAppBounds(RectProto value) {
        RectProto rectProto = this.appBounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.appBounds_ = value;
        } else {
            this.appBounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.appBounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppBounds() {
        this.appBounds_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.app.WindowConfigurationProtoOrBuilder
    public boolean hasWindowingMode() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.app.WindowConfigurationProtoOrBuilder
    public int getWindowingMode() {
        return this.windowingMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowingMode(int value) {
        this.bitField0_ |= 2;
        this.windowingMode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowingMode() {
        this.bitField0_ &= -3;
        this.windowingMode_ = 0;
    }

    @Override // android.app.WindowConfigurationProtoOrBuilder
    public boolean hasActivityType() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.app.WindowConfigurationProtoOrBuilder
    public int getActivityType() {
        return this.activityType_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivityType(int value) {
        this.bitField0_ |= 4;
        this.activityType_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivityType() {
        this.bitField0_ &= -5;
        this.activityType_ = 0;
    }

    @Override // android.app.WindowConfigurationProtoOrBuilder
    public boolean hasBounds() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.app.WindowConfigurationProtoOrBuilder
    public RectProto getBounds() {
        RectProto rectProto = this.bounds_;
        return rectProto == null ? RectProto.getDefaultInstance() : rectProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBounds(RectProto value) {
        if (value != null) {
            this.bounds_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBounds(RectProto.Builder builderForValue) {
        this.bounds_ = (RectProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBounds(RectProto value) {
        RectProto rectProto = this.bounds_;
        if (rectProto == null || rectProto == RectProto.getDefaultInstance()) {
            this.bounds_ = value;
        } else {
            this.bounds_ = (RectProto) ((RectProto.Builder) RectProto.newBuilder(this.bounds_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBounds() {
        this.bounds_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getAppBounds());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.windowingMode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.activityType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getBounds());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getAppBounds());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.windowingMode_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.activityType_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getBounds());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowConfigurationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowConfigurationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowConfigurationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowConfigurationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowConfigurationProto parseFrom(InputStream input) throws IOException {
        return (WindowConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowConfigurationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowConfigurationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowConfigurationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowConfigurationProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowConfigurationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowConfigurationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowConfigurationProto, Builder> implements WindowConfigurationProtoOrBuilder {
        private Builder() {
            super(WindowConfigurationProto.DEFAULT_INSTANCE);
        }

        @Override // android.app.WindowConfigurationProtoOrBuilder
        public boolean hasAppBounds() {
            return ((WindowConfigurationProto) this.instance).hasAppBounds();
        }

        @Override // android.app.WindowConfigurationProtoOrBuilder
        public RectProto getAppBounds() {
            return ((WindowConfigurationProto) this.instance).getAppBounds();
        }

        public Builder setAppBounds(RectProto value) {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).setAppBounds((WindowConfigurationProto) value);
            return this;
        }

        public Builder setAppBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).setAppBounds((WindowConfigurationProto) builderForValue);
            return this;
        }

        public Builder mergeAppBounds(RectProto value) {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).mergeAppBounds(value);
            return this;
        }

        public Builder clearAppBounds() {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).clearAppBounds();
            return this;
        }

        @Override // android.app.WindowConfigurationProtoOrBuilder
        public boolean hasWindowingMode() {
            return ((WindowConfigurationProto) this.instance).hasWindowingMode();
        }

        @Override // android.app.WindowConfigurationProtoOrBuilder
        public int getWindowingMode() {
            return ((WindowConfigurationProto) this.instance).getWindowingMode();
        }

        public Builder setWindowingMode(int value) {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).setWindowingMode(value);
            return this;
        }

        public Builder clearWindowingMode() {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).clearWindowingMode();
            return this;
        }

        @Override // android.app.WindowConfigurationProtoOrBuilder
        public boolean hasActivityType() {
            return ((WindowConfigurationProto) this.instance).hasActivityType();
        }

        @Override // android.app.WindowConfigurationProtoOrBuilder
        public int getActivityType() {
            return ((WindowConfigurationProto) this.instance).getActivityType();
        }

        public Builder setActivityType(int value) {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).setActivityType(value);
            return this;
        }

        public Builder clearActivityType() {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).clearActivityType();
            return this;
        }

        @Override // android.app.WindowConfigurationProtoOrBuilder
        public boolean hasBounds() {
            return ((WindowConfigurationProto) this.instance).hasBounds();
        }

        @Override // android.app.WindowConfigurationProtoOrBuilder
        public RectProto getBounds() {
            return ((WindowConfigurationProto) this.instance).getBounds();
        }

        public Builder setBounds(RectProto value) {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).setBounds((WindowConfigurationProto) value);
            return this;
        }

        public Builder setBounds(RectProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).setBounds((WindowConfigurationProto) builderForValue);
            return this;
        }

        public Builder mergeBounds(RectProto value) {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).mergeBounds(value);
            return this;
        }

        public Builder clearBounds() {
            copyOnWrite();
            ((WindowConfigurationProto) this.instance).clearBounds();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowConfigurationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowConfigurationProto other = (WindowConfigurationProto) arg1;
                this.appBounds_ = (RectProto) visitor.visitMessage(this.appBounds_, other.appBounds_);
                this.windowingMode_ = visitor.visitInt(hasWindowingMode(), this.windowingMode_, other.hasWindowingMode(), other.windowingMode_);
                this.activityType_ = visitor.visitInt(hasActivityType(), this.activityType_, other.hasActivityType(), other.activityType_);
                this.bounds_ = (RectProto) visitor.visitMessage(this.bounds_, other.bounds_);
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
                                subBuilder = (RectProto.Builder) this.appBounds_.toBuilder();
                            }
                            this.appBounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.appBounds_);
                                this.appBounds_ = (RectProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.windowingMode_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.activityType_ = input.readInt32();
                        } else if (tag == 34) {
                            RectProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder2 = (RectProto.Builder) this.bounds_.toBuilder();
                            }
                            this.bounds_ = (RectProto) input.readMessage(RectProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.bounds_);
                                this.bounds_ = (RectProto) subBuilder2.buildPartial();
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
                    synchronized (WindowConfigurationProto.class) {
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

    public static WindowConfigurationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowConfigurationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
