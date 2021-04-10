package com.android.server.wm;

import com.android.server.wm.IdentifierProto;
import com.android.server.wm.RootWindowContainerProto;
import com.android.server.wm.WindowManagerPolicyProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class WindowManagerServiceDumpProto extends GeneratedMessageLite<WindowManagerServiceDumpProto, Builder> implements WindowManagerServiceDumpProtoOrBuilder {
    private static final WindowManagerServiceDumpProto DEFAULT_INSTANCE = new WindowManagerServiceDumpProto();
    public static final int DISPLAY_FROZEN_FIELD_NUMBER = 6;
    public static final int FOCUSED_APP_FIELD_NUMBER = 4;
    public static final int FOCUSED_WINDOW_FIELD_NUMBER = 3;
    public static final int INPUT_METHOD_WINDOW_FIELD_NUMBER = 5;
    public static final int LAST_ORIENTATION_FIELD_NUMBER = 8;
    private static volatile Parser<WindowManagerServiceDumpProto> PARSER = null;
    public static final int POLICY_FIELD_NUMBER = 1;
    public static final int ROOT_WINDOW_CONTAINER_FIELD_NUMBER = 2;
    public static final int ROTATION_FIELD_NUMBER = 7;
    private int bitField0_;
    private boolean displayFrozen_ = false;
    private String focusedApp_ = "";
    private IdentifierProto focusedWindow_;
    private IdentifierProto inputMethodWindow_;
    private int lastOrientation_ = 0;
    private WindowManagerPolicyProto policy_;
    private RootWindowContainerProto rootWindowContainer_;
    private int rotation_ = 0;

    private WindowManagerServiceDumpProto() {
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean hasPolicy() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public WindowManagerPolicyProto getPolicy() {
        WindowManagerPolicyProto windowManagerPolicyProto = this.policy_;
        return windowManagerPolicyProto == null ? WindowManagerPolicyProto.getDefaultInstance() : windowManagerPolicyProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPolicy(WindowManagerPolicyProto value) {
        if (value != null) {
            this.policy_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPolicy(WindowManagerPolicyProto.Builder builderForValue) {
        this.policy_ = (WindowManagerPolicyProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePolicy(WindowManagerPolicyProto value) {
        WindowManagerPolicyProto windowManagerPolicyProto = this.policy_;
        if (windowManagerPolicyProto == null || windowManagerPolicyProto == WindowManagerPolicyProto.getDefaultInstance()) {
            this.policy_ = value;
        } else {
            this.policy_ = (WindowManagerPolicyProto) ((WindowManagerPolicyProto.Builder) WindowManagerPolicyProto.newBuilder(this.policy_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPolicy() {
        this.policy_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean hasRootWindowContainer() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public RootWindowContainerProto getRootWindowContainer() {
        RootWindowContainerProto rootWindowContainerProto = this.rootWindowContainer_;
        return rootWindowContainerProto == null ? RootWindowContainerProto.getDefaultInstance() : rootWindowContainerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRootWindowContainer(RootWindowContainerProto value) {
        if (value != null) {
            this.rootWindowContainer_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRootWindowContainer(RootWindowContainerProto.Builder builderForValue) {
        this.rootWindowContainer_ = (RootWindowContainerProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRootWindowContainer(RootWindowContainerProto value) {
        RootWindowContainerProto rootWindowContainerProto = this.rootWindowContainer_;
        if (rootWindowContainerProto == null || rootWindowContainerProto == RootWindowContainerProto.getDefaultInstance()) {
            this.rootWindowContainer_ = value;
        } else {
            this.rootWindowContainer_ = (RootWindowContainerProto) ((RootWindowContainerProto.Builder) RootWindowContainerProto.newBuilder(this.rootWindowContainer_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRootWindowContainer() {
        this.rootWindowContainer_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean hasFocusedWindow() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public IdentifierProto getFocusedWindow() {
        IdentifierProto identifierProto = this.focusedWindow_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedWindow(IdentifierProto value) {
        if (value != null) {
            this.focusedWindow_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedWindow(IdentifierProto.Builder builderForValue) {
        this.focusedWindow_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFocusedWindow(IdentifierProto value) {
        IdentifierProto identifierProto = this.focusedWindow_;
        if (identifierProto == null || identifierProto == IdentifierProto.getDefaultInstance()) {
            this.focusedWindow_ = value;
        } else {
            this.focusedWindow_ = (IdentifierProto) ((IdentifierProto.Builder) IdentifierProto.newBuilder(this.focusedWindow_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFocusedWindow() {
        this.focusedWindow_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean hasFocusedApp() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public String getFocusedApp() {
        return this.focusedApp_;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public ByteString getFocusedAppBytes() {
        return ByteString.copyFromUtf8(this.focusedApp_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedApp(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.focusedApp_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFocusedApp() {
        this.bitField0_ &= -9;
        this.focusedApp_ = getDefaultInstance().getFocusedApp();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFocusedAppBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.focusedApp_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean hasInputMethodWindow() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public IdentifierProto getInputMethodWindow() {
        IdentifierProto identifierProto = this.inputMethodWindow_;
        return identifierProto == null ? IdentifierProto.getDefaultInstance() : identifierProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInputMethodWindow(IdentifierProto value) {
        if (value != null) {
            this.inputMethodWindow_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInputMethodWindow(IdentifierProto.Builder builderForValue) {
        this.inputMethodWindow_ = (IdentifierProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeInputMethodWindow(IdentifierProto value) {
        IdentifierProto identifierProto = this.inputMethodWindow_;
        if (identifierProto == null || identifierProto == IdentifierProto.getDefaultInstance()) {
            this.inputMethodWindow_ = value;
        } else {
            this.inputMethodWindow_ = (IdentifierProto) ((IdentifierProto.Builder) IdentifierProto.newBuilder(this.inputMethodWindow_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInputMethodWindow() {
        this.inputMethodWindow_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean hasDisplayFrozen() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean getDisplayFrozen() {
        return this.displayFrozen_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayFrozen(boolean value) {
        this.bitField0_ |= 32;
        this.displayFrozen_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayFrozen() {
        this.bitField0_ &= -33;
        this.displayFrozen_ = false;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean hasRotation() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public int getRotation() {
        return this.rotation_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRotation(int value) {
        this.bitField0_ |= 64;
        this.rotation_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRotation() {
        this.bitField0_ &= -65;
        this.rotation_ = 0;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public boolean hasLastOrientation() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
    public int getLastOrientation() {
        return this.lastOrientation_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastOrientation(int value) {
        this.bitField0_ |= 128;
        this.lastOrientation_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastOrientation() {
        this.bitField0_ &= -129;
        this.lastOrientation_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getPolicy());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getRootWindowContainer());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getFocusedWindow());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getFocusedApp());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(5, getInputMethodWindow());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.displayFrozen_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.rotation_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.lastOrientation_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getPolicy());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getRootWindowContainer());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getFocusedWindow());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getFocusedApp());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(5, getInputMethodWindow());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.displayFrozen_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.rotation_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.lastOrientation_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowManagerServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowManagerServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowManagerServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowManagerServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowManagerServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (WindowManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowManagerServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowManagerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowManagerServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowManagerServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowManagerServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowManagerServiceDumpProto, Builder> implements WindowManagerServiceDumpProtoOrBuilder {
        private Builder() {
            super(WindowManagerServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean hasPolicy() {
            return ((WindowManagerServiceDumpProto) this.instance).hasPolicy();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public WindowManagerPolicyProto getPolicy() {
            return ((WindowManagerServiceDumpProto) this.instance).getPolicy();
        }

        public Builder setPolicy(WindowManagerPolicyProto value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setPolicy((WindowManagerServiceDumpProto) value);
            return this;
        }

        public Builder setPolicy(WindowManagerPolicyProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setPolicy((WindowManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergePolicy(WindowManagerPolicyProto value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).mergePolicy(value);
            return this;
        }

        public Builder clearPolicy() {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).clearPolicy();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean hasRootWindowContainer() {
            return ((WindowManagerServiceDumpProto) this.instance).hasRootWindowContainer();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public RootWindowContainerProto getRootWindowContainer() {
            return ((WindowManagerServiceDumpProto) this.instance).getRootWindowContainer();
        }

        public Builder setRootWindowContainer(RootWindowContainerProto value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setRootWindowContainer((WindowManagerServiceDumpProto) value);
            return this;
        }

        public Builder setRootWindowContainer(RootWindowContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setRootWindowContainer((WindowManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeRootWindowContainer(RootWindowContainerProto value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).mergeRootWindowContainer(value);
            return this;
        }

        public Builder clearRootWindowContainer() {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).clearRootWindowContainer();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean hasFocusedWindow() {
            return ((WindowManagerServiceDumpProto) this.instance).hasFocusedWindow();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public IdentifierProto getFocusedWindow() {
            return ((WindowManagerServiceDumpProto) this.instance).getFocusedWindow();
        }

        public Builder setFocusedWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setFocusedWindow((WindowManagerServiceDumpProto) value);
            return this;
        }

        public Builder setFocusedWindow(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setFocusedWindow((WindowManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeFocusedWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).mergeFocusedWindow(value);
            return this;
        }

        public Builder clearFocusedWindow() {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).clearFocusedWindow();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean hasFocusedApp() {
            return ((WindowManagerServiceDumpProto) this.instance).hasFocusedApp();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public String getFocusedApp() {
            return ((WindowManagerServiceDumpProto) this.instance).getFocusedApp();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public ByteString getFocusedAppBytes() {
            return ((WindowManagerServiceDumpProto) this.instance).getFocusedAppBytes();
        }

        public Builder setFocusedApp(String value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setFocusedApp(value);
            return this;
        }

        public Builder clearFocusedApp() {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).clearFocusedApp();
            return this;
        }

        public Builder setFocusedAppBytes(ByteString value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setFocusedAppBytes(value);
            return this;
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean hasInputMethodWindow() {
            return ((WindowManagerServiceDumpProto) this.instance).hasInputMethodWindow();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public IdentifierProto getInputMethodWindow() {
            return ((WindowManagerServiceDumpProto) this.instance).getInputMethodWindow();
        }

        public Builder setInputMethodWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setInputMethodWindow((WindowManagerServiceDumpProto) value);
            return this;
        }

        public Builder setInputMethodWindow(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setInputMethodWindow((WindowManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeInputMethodWindow(IdentifierProto value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).mergeInputMethodWindow(value);
            return this;
        }

        public Builder clearInputMethodWindow() {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).clearInputMethodWindow();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean hasDisplayFrozen() {
            return ((WindowManagerServiceDumpProto) this.instance).hasDisplayFrozen();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean getDisplayFrozen() {
            return ((WindowManagerServiceDumpProto) this.instance).getDisplayFrozen();
        }

        public Builder setDisplayFrozen(boolean value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setDisplayFrozen(value);
            return this;
        }

        public Builder clearDisplayFrozen() {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).clearDisplayFrozen();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean hasRotation() {
            return ((WindowManagerServiceDumpProto) this.instance).hasRotation();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public int getRotation() {
            return ((WindowManagerServiceDumpProto) this.instance).getRotation();
        }

        public Builder setRotation(int value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setRotation(value);
            return this;
        }

        public Builder clearRotation() {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).clearRotation();
            return this;
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public boolean hasLastOrientation() {
            return ((WindowManagerServiceDumpProto) this.instance).hasLastOrientation();
        }

        @Override // com.android.server.wm.WindowManagerServiceDumpProtoOrBuilder
        public int getLastOrientation() {
            return ((WindowManagerServiceDumpProto) this.instance).getLastOrientation();
        }

        public Builder setLastOrientation(int value) {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).setLastOrientation(value);
            return this;
        }

        public Builder clearLastOrientation() {
            copyOnWrite();
            ((WindowManagerServiceDumpProto) this.instance).clearLastOrientation();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowManagerServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowManagerServiceDumpProto other = (WindowManagerServiceDumpProto) arg1;
                this.policy_ = (WindowManagerPolicyProto) visitor.visitMessage(this.policy_, other.policy_);
                this.rootWindowContainer_ = (RootWindowContainerProto) visitor.visitMessage(this.rootWindowContainer_, other.rootWindowContainer_);
                this.focusedWindow_ = (IdentifierProto) visitor.visitMessage(this.focusedWindow_, other.focusedWindow_);
                this.focusedApp_ = visitor.visitString(hasFocusedApp(), this.focusedApp_, other.hasFocusedApp(), other.focusedApp_);
                this.inputMethodWindow_ = (IdentifierProto) visitor.visitMessage(this.inputMethodWindow_, other.inputMethodWindow_);
                this.displayFrozen_ = visitor.visitBoolean(hasDisplayFrozen(), this.displayFrozen_, other.hasDisplayFrozen(), other.displayFrozen_);
                this.rotation_ = visitor.visitInt(hasRotation(), this.rotation_, other.hasRotation(), other.rotation_);
                this.lastOrientation_ = visitor.visitInt(hasLastOrientation(), this.lastOrientation_, other.hasLastOrientation(), other.lastOrientation_);
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
                            WindowManagerPolicyProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (WindowManagerPolicyProto.Builder) this.policy_.toBuilder();
                            }
                            this.policy_ = (WindowManagerPolicyProto) input.readMessage(WindowManagerPolicyProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.policy_);
                                this.policy_ = (WindowManagerPolicyProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            RootWindowContainerProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (RootWindowContainerProto.Builder) this.rootWindowContainer_.toBuilder();
                            }
                            this.rootWindowContainer_ = (RootWindowContainerProto) input.readMessage(RootWindowContainerProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.rootWindowContainer_);
                                this.rootWindowContainer_ = (RootWindowContainerProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            IdentifierProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder3 = (IdentifierProto.Builder) this.focusedWindow_.toBuilder();
                            }
                            this.focusedWindow_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.focusedWindow_);
                                this.focusedWindow_ = (IdentifierProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 34) {
                            String s = input.readString();
                            this.bitField0_ |= 8;
                            this.focusedApp_ = s;
                        } else if (tag == 42) {
                            IdentifierProto.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder4 = (IdentifierProto.Builder) this.inputMethodWindow_.toBuilder();
                            }
                            this.inputMethodWindow_ = (IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.inputMethodWindow_);
                                this.inputMethodWindow_ = (IdentifierProto) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 16;
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.displayFrozen_ = input.readBool();
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.rotation_ = input.readInt32();
                        } else if (tag == 64) {
                            this.bitField0_ |= 128;
                            this.lastOrientation_ = input.readInt32();
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
                    synchronized (WindowManagerServiceDumpProto.class) {
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

    public static WindowManagerServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowManagerServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
