package com.android.server.wm;

import com.android.server.wm.ConfigurationContainerProto;
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

public final class WindowContainerProto extends GeneratedMessageLite<WindowContainerProto, Builder> implements WindowContainerProtoOrBuilder {
    public static final int CONFIGURATION_CONTAINER_FIELD_NUMBER = 1;
    private static final WindowContainerProto DEFAULT_INSTANCE = new WindowContainerProto();
    public static final int ORIENTATION_FIELD_NUMBER = 2;
    private static volatile Parser<WindowContainerProto> PARSER = null;
    public static final int SURFACE_ANIMATOR_FIELD_NUMBER = 4;
    public static final int VISIBLE_FIELD_NUMBER = 3;
    private int bitField0_;
    private ConfigurationContainerProto configurationContainer_;
    private int orientation_ = 0;
    private SurfaceAnimatorProto surfaceAnimator_;
    private boolean visible_ = false;

    private WindowContainerProto() {
    }

    @Override // com.android.server.wm.WindowContainerProtoOrBuilder
    public boolean hasConfigurationContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowContainerProtoOrBuilder
    public ConfigurationContainerProto getConfigurationContainer() {
        ConfigurationContainerProto configurationContainerProto = this.configurationContainer_;
        return configurationContainerProto == null ? ConfigurationContainerProto.getDefaultInstance() : configurationContainerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigurationContainer(ConfigurationContainerProto value) {
        if (value != null) {
            this.configurationContainer_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigurationContainer(ConfigurationContainerProto.Builder builderForValue) {
        this.configurationContainer_ = (ConfigurationContainerProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeConfigurationContainer(ConfigurationContainerProto value) {
        ConfigurationContainerProto configurationContainerProto = this.configurationContainer_;
        if (configurationContainerProto == null || configurationContainerProto == ConfigurationContainerProto.getDefaultInstance()) {
            this.configurationContainer_ = value;
        } else {
            this.configurationContainer_ = (ConfigurationContainerProto) ((ConfigurationContainerProto.Builder) ConfigurationContainerProto.newBuilder(this.configurationContainer_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigurationContainer() {
        this.configurationContainer_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.WindowContainerProtoOrBuilder
    public boolean hasOrientation() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowContainerProtoOrBuilder
    public int getOrientation() {
        return this.orientation_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOrientation(int value) {
        this.bitField0_ |= 2;
        this.orientation_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOrientation() {
        this.bitField0_ &= -3;
        this.orientation_ = 0;
    }

    @Override // com.android.server.wm.WindowContainerProtoOrBuilder
    public boolean hasVisible() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowContainerProtoOrBuilder
    public boolean getVisible() {
        return this.visible_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVisible(boolean value) {
        this.bitField0_ |= 4;
        this.visible_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVisible() {
        this.bitField0_ &= -5;
        this.visible_ = false;
    }

    @Override // com.android.server.wm.WindowContainerProtoOrBuilder
    public boolean hasSurfaceAnimator() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.WindowContainerProtoOrBuilder
    public SurfaceAnimatorProto getSurfaceAnimator() {
        SurfaceAnimatorProto surfaceAnimatorProto = this.surfaceAnimator_;
        return surfaceAnimatorProto == null ? SurfaceAnimatorProto.getDefaultInstance() : surfaceAnimatorProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceAnimator(SurfaceAnimatorProto value) {
        if (value != null) {
            this.surfaceAnimator_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSurfaceAnimator(SurfaceAnimatorProto.Builder builderForValue) {
        this.surfaceAnimator_ = (SurfaceAnimatorProto) builderForValue.build();
        this.bitField0_ |= 8;
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
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSurfaceAnimator() {
        this.surfaceAnimator_ = null;
        this.bitField0_ &= -9;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getConfigurationContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.orientation_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(3, this.visible_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getSurfaceAnimator());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getConfigurationContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.orientation_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(3, this.visible_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getSurfaceAnimator());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowContainerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowContainerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowContainerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowContainerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowContainerProto parseFrom(InputStream input) throws IOException {
        return (WindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowContainerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowContainerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowContainerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowContainerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowContainerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowContainerProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowContainerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowContainerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowContainerProto, Builder> implements WindowContainerProtoOrBuilder {
        private Builder() {
            super(WindowContainerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowContainerProtoOrBuilder
        public boolean hasConfigurationContainer() {
            return ((WindowContainerProto) this.instance).hasConfigurationContainer();
        }

        @Override // com.android.server.wm.WindowContainerProtoOrBuilder
        public ConfigurationContainerProto getConfigurationContainer() {
            return ((WindowContainerProto) this.instance).getConfigurationContainer();
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((WindowContainerProto) this.instance).setConfigurationContainer((WindowContainerProto) value);
            return this;
        }

        public Builder setConfigurationContainer(ConfigurationContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowContainerProto) this.instance).setConfigurationContainer((WindowContainerProto) builderForValue);
            return this;
        }

        public Builder mergeConfigurationContainer(ConfigurationContainerProto value) {
            copyOnWrite();
            ((WindowContainerProto) this.instance).mergeConfigurationContainer(value);
            return this;
        }

        public Builder clearConfigurationContainer() {
            copyOnWrite();
            ((WindowContainerProto) this.instance).clearConfigurationContainer();
            return this;
        }

        @Override // com.android.server.wm.WindowContainerProtoOrBuilder
        public boolean hasOrientation() {
            return ((WindowContainerProto) this.instance).hasOrientation();
        }

        @Override // com.android.server.wm.WindowContainerProtoOrBuilder
        public int getOrientation() {
            return ((WindowContainerProto) this.instance).getOrientation();
        }

        public Builder setOrientation(int value) {
            copyOnWrite();
            ((WindowContainerProto) this.instance).setOrientation(value);
            return this;
        }

        public Builder clearOrientation() {
            copyOnWrite();
            ((WindowContainerProto) this.instance).clearOrientation();
            return this;
        }

        @Override // com.android.server.wm.WindowContainerProtoOrBuilder
        public boolean hasVisible() {
            return ((WindowContainerProto) this.instance).hasVisible();
        }

        @Override // com.android.server.wm.WindowContainerProtoOrBuilder
        public boolean getVisible() {
            return ((WindowContainerProto) this.instance).getVisible();
        }

        public Builder setVisible(boolean value) {
            copyOnWrite();
            ((WindowContainerProto) this.instance).setVisible(value);
            return this;
        }

        public Builder clearVisible() {
            copyOnWrite();
            ((WindowContainerProto) this.instance).clearVisible();
            return this;
        }

        @Override // com.android.server.wm.WindowContainerProtoOrBuilder
        public boolean hasSurfaceAnimator() {
            return ((WindowContainerProto) this.instance).hasSurfaceAnimator();
        }

        @Override // com.android.server.wm.WindowContainerProtoOrBuilder
        public SurfaceAnimatorProto getSurfaceAnimator() {
            return ((WindowContainerProto) this.instance).getSurfaceAnimator();
        }

        public Builder setSurfaceAnimator(SurfaceAnimatorProto value) {
            copyOnWrite();
            ((WindowContainerProto) this.instance).setSurfaceAnimator((WindowContainerProto) value);
            return this;
        }

        public Builder setSurfaceAnimator(SurfaceAnimatorProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowContainerProto) this.instance).setSurfaceAnimator((WindowContainerProto) builderForValue);
            return this;
        }

        public Builder mergeSurfaceAnimator(SurfaceAnimatorProto value) {
            copyOnWrite();
            ((WindowContainerProto) this.instance).mergeSurfaceAnimator(value);
            return this;
        }

        public Builder clearSurfaceAnimator() {
            copyOnWrite();
            ((WindowContainerProto) this.instance).clearSurfaceAnimator();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowContainerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowContainerProto other = (WindowContainerProto) arg1;
                this.configurationContainer_ = (ConfigurationContainerProto) visitor.visitMessage(this.configurationContainer_, other.configurationContainer_);
                this.orientation_ = visitor.visitInt(hasOrientation(), this.orientation_, other.hasOrientation(), other.orientation_);
                this.visible_ = visitor.visitBoolean(hasVisible(), this.visible_, other.hasVisible(), other.visible_);
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
                        } else if (tag == 10) {
                            ConfigurationContainerProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ConfigurationContainerProto.Builder) this.configurationContainer_.toBuilder();
                            }
                            this.configurationContainer_ = (ConfigurationContainerProto) input.readMessage(ConfigurationContainerProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.configurationContainer_);
                                this.configurationContainer_ = (ConfigurationContainerProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.orientation_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.visible_ = input.readBool();
                        } else if (tag == 34) {
                            SurfaceAnimatorProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder2 = (SurfaceAnimatorProto.Builder) this.surfaceAnimator_.toBuilder();
                            }
                            this.surfaceAnimator_ = (SurfaceAnimatorProto) input.readMessage(SurfaceAnimatorProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.surfaceAnimator_);
                                this.surfaceAnimator_ = (SurfaceAnimatorProto) subBuilder2.buildPartial();
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
                    synchronized (WindowContainerProto.class) {
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

    public static WindowContainerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowContainerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
