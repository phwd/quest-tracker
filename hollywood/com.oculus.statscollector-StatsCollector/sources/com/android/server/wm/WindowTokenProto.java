package com.android.server.wm;

import com.android.server.wm.WindowContainerProto;
import com.android.server.wm.WindowStateProto;
import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

public final class WindowTokenProto extends GeneratedMessageLite<WindowTokenProto, Builder> implements WindowTokenProtoOrBuilder {
    private static final WindowTokenProto DEFAULT_INSTANCE = new WindowTokenProto();
    public static final int HASH_CODE_FIELD_NUMBER = 2;
    public static final int HIDDEN_FIELD_NUMBER = 4;
    private static volatile Parser<WindowTokenProto> PARSER = null;
    public static final int PAUSED_FIELD_NUMBER = 6;
    public static final int WAITING_TO_SHOW_FIELD_NUMBER = 5;
    public static final int WINDOWS_FIELD_NUMBER = 3;
    public static final int WINDOW_CONTAINER_FIELD_NUMBER = 1;
    private int bitField0_;
    private int hashCode_ = 0;
    private boolean hidden_ = false;
    private boolean paused_ = false;
    private boolean waitingToShow_ = false;
    private WindowContainerProto windowContainer_;
    private Internal.ProtobufList<WindowStateProto> windows_ = emptyProtobufList();

    private WindowTokenProto() {
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public boolean hasWindowContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public WindowContainerProto getWindowContainer() {
        WindowContainerProto windowContainerProto = this.windowContainer_;
        return windowContainerProto == null ? WindowContainerProto.getDefaultInstance() : windowContainerProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowContainer(WindowContainerProto value) {
        if (value != null) {
            this.windowContainer_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindowContainer(WindowContainerProto.Builder builderForValue) {
        this.windowContainer_ = (WindowContainerProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindowContainer(WindowContainerProto value) {
        WindowContainerProto windowContainerProto = this.windowContainer_;
        if (windowContainerProto == null || windowContainerProto == WindowContainerProto.getDefaultInstance()) {
            this.windowContainer_ = value;
        } else {
            this.windowContainer_ = (WindowContainerProto) ((WindowContainerProto.Builder) WindowContainerProto.newBuilder(this.windowContainer_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindowContainer() {
        this.windowContainer_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public boolean hasHashCode() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public int getHashCode() {
        return this.hashCode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHashCode(int value) {
        this.bitField0_ |= 2;
        this.hashCode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHashCode() {
        this.bitField0_ &= -3;
        this.hashCode_ = 0;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public List<WindowStateProto> getWindowsList() {
        return this.windows_;
    }

    public List<? extends WindowStateProtoOrBuilder> getWindowsOrBuilderList() {
        return this.windows_;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public int getWindowsCount() {
        return this.windows_.size();
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public WindowStateProto getWindows(int index) {
        return this.windows_.get(index);
    }

    public WindowStateProtoOrBuilder getWindowsOrBuilder(int index) {
        return this.windows_.get(index);
    }

    private void ensureWindowsIsMutable() {
        if (!this.windows_.isModifiable()) {
            this.windows_ = GeneratedMessageLite.mutableCopy(this.windows_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindows(int index, WindowStateProto value) {
        if (value != null) {
            ensureWindowsIsMutable();
            this.windows_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindows(int index, WindowStateProto.Builder builderForValue) {
        ensureWindowsIsMutable();
        this.windows_.set(index, (WindowStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWindows(WindowStateProto value) {
        if (value != null) {
            ensureWindowsIsMutable();
            this.windows_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWindows(int index, WindowStateProto value) {
        if (value != null) {
            ensureWindowsIsMutable();
            this.windows_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWindows(WindowStateProto.Builder builderForValue) {
        ensureWindowsIsMutable();
        this.windows_.add((WindowStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWindows(int index, WindowStateProto.Builder builderForValue) {
        ensureWindowsIsMutable();
        this.windows_.add(index, (WindowStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWindows(Iterable<? extends WindowStateProto> values) {
        ensureWindowsIsMutable();
        AbstractMessageLite.addAll(values, this.windows_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindows() {
        this.windows_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWindows(int index) {
        ensureWindowsIsMutable();
        this.windows_.remove(index);
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public boolean hasHidden() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public boolean getHidden() {
        return this.hidden_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHidden(boolean value) {
        this.bitField0_ |= 4;
        this.hidden_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHidden() {
        this.bitField0_ &= -5;
        this.hidden_ = false;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public boolean hasWaitingToShow() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public boolean getWaitingToShow() {
        return this.waitingToShow_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWaitingToShow(boolean value) {
        this.bitField0_ |= 8;
        this.waitingToShow_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWaitingToShow() {
        this.bitField0_ &= -9;
        this.waitingToShow_ = false;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public boolean hasPaused() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.wm.WindowTokenProtoOrBuilder
    public boolean getPaused() {
        return this.paused_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPaused(boolean value) {
        this.bitField0_ |= 16;
        this.paused_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPaused() {
        this.bitField0_ &= -17;
        this.paused_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getWindowContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.hashCode_);
        }
        for (int i = 0; i < this.windows_.size(); i++) {
            output.writeMessage(3, this.windows_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(4, this.hidden_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(5, this.waitingToShow_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(6, this.paused_);
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getWindowContainer());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.hashCode_);
        }
        for (int i = 0; i < this.windows_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.windows_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(4, this.hidden_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(5, this.waitingToShow_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(6, this.paused_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WindowTokenProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowTokenProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowTokenProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WindowTokenProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WindowTokenProto parseFrom(InputStream input) throws IOException {
        return (WindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowTokenProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowTokenProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WindowTokenProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowTokenProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowTokenProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WindowTokenProto parseFrom(CodedInputStream input) throws IOException {
        return (WindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WindowTokenProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WindowTokenProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WindowTokenProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WindowTokenProto, Builder> implements WindowTokenProtoOrBuilder {
        private Builder() {
            super(WindowTokenProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public boolean hasWindowContainer() {
            return ((WindowTokenProto) this.instance).hasWindowContainer();
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public WindowContainerProto getWindowContainer() {
            return ((WindowTokenProto) this.instance).getWindowContainer();
        }

        public Builder setWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).setWindowContainer((WindowTokenProto) value);
            return this;
        }

        public Builder setWindowContainer(WindowContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).setWindowContainer((WindowTokenProto) builderForValue);
            return this;
        }

        public Builder mergeWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).mergeWindowContainer(value);
            return this;
        }

        public Builder clearWindowContainer() {
            copyOnWrite();
            ((WindowTokenProto) this.instance).clearWindowContainer();
            return this;
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public boolean hasHashCode() {
            return ((WindowTokenProto) this.instance).hasHashCode();
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public int getHashCode() {
            return ((WindowTokenProto) this.instance).getHashCode();
        }

        public Builder setHashCode(int value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).setHashCode(value);
            return this;
        }

        public Builder clearHashCode() {
            copyOnWrite();
            ((WindowTokenProto) this.instance).clearHashCode();
            return this;
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public List<WindowStateProto> getWindowsList() {
            return Collections.unmodifiableList(((WindowTokenProto) this.instance).getWindowsList());
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public int getWindowsCount() {
            return ((WindowTokenProto) this.instance).getWindowsCount();
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public WindowStateProto getWindows(int index) {
            return ((WindowTokenProto) this.instance).getWindows(index);
        }

        public Builder setWindows(int index, WindowStateProto value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).setWindows((WindowTokenProto) index, (int) value);
            return this;
        }

        public Builder setWindows(int index, WindowStateProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).setWindows((WindowTokenProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWindows(WindowStateProto value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).addWindows((WindowTokenProto) value);
            return this;
        }

        public Builder addWindows(int index, WindowStateProto value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).addWindows((WindowTokenProto) index, (int) value);
            return this;
        }

        public Builder addWindows(WindowStateProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).addWindows((WindowTokenProto) builderForValue);
            return this;
        }

        public Builder addWindows(int index, WindowStateProto.Builder builderForValue) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).addWindows((WindowTokenProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWindows(Iterable<? extends WindowStateProto> values) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).addAllWindows(values);
            return this;
        }

        public Builder clearWindows() {
            copyOnWrite();
            ((WindowTokenProto) this.instance).clearWindows();
            return this;
        }

        public Builder removeWindows(int index) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).removeWindows(index);
            return this;
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public boolean hasHidden() {
            return ((WindowTokenProto) this.instance).hasHidden();
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public boolean getHidden() {
            return ((WindowTokenProto) this.instance).getHidden();
        }

        public Builder setHidden(boolean value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).setHidden(value);
            return this;
        }

        public Builder clearHidden() {
            copyOnWrite();
            ((WindowTokenProto) this.instance).clearHidden();
            return this;
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public boolean hasWaitingToShow() {
            return ((WindowTokenProto) this.instance).hasWaitingToShow();
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public boolean getWaitingToShow() {
            return ((WindowTokenProto) this.instance).getWaitingToShow();
        }

        public Builder setWaitingToShow(boolean value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).setWaitingToShow(value);
            return this;
        }

        public Builder clearWaitingToShow() {
            copyOnWrite();
            ((WindowTokenProto) this.instance).clearWaitingToShow();
            return this;
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public boolean hasPaused() {
            return ((WindowTokenProto) this.instance).hasPaused();
        }

        @Override // com.android.server.wm.WindowTokenProtoOrBuilder
        public boolean getPaused() {
            return ((WindowTokenProto) this.instance).getPaused();
        }

        public Builder setPaused(boolean value) {
            copyOnWrite();
            ((WindowTokenProto) this.instance).setPaused(value);
            return this;
        }

        public Builder clearPaused() {
            copyOnWrite();
            ((WindowTokenProto) this.instance).clearPaused();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WindowTokenProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.windows_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WindowTokenProto other = (WindowTokenProto) arg1;
                this.windowContainer_ = (WindowContainerProto) visitor.visitMessage(this.windowContainer_, other.windowContainer_);
                this.hashCode_ = visitor.visitInt(hasHashCode(), this.hashCode_, other.hasHashCode(), other.hashCode_);
                this.windows_ = visitor.visitList(this.windows_, other.windows_);
                this.hidden_ = visitor.visitBoolean(hasHidden(), this.hidden_, other.hasHidden(), other.hidden_);
                this.waitingToShow_ = visitor.visitBoolean(hasWaitingToShow(), this.waitingToShow_, other.hasWaitingToShow(), other.waitingToShow_);
                this.paused_ = visitor.visitBoolean(hasPaused(), this.paused_, other.hasPaused(), other.paused_);
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
                            WindowContainerProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (WindowContainerProto.Builder) this.windowContainer_.toBuilder();
                            }
                            this.windowContainer_ = (WindowContainerProto) input.readMessage(WindowContainerProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.windowContainer_);
                                this.windowContainer_ = (WindowContainerProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.hashCode_ = input.readInt32();
                        } else if (tag == 26) {
                            if (!this.windows_.isModifiable()) {
                                this.windows_ = GeneratedMessageLite.mutableCopy(this.windows_);
                            }
                            this.windows_.add((WindowStateProto) input.readMessage(WindowStateProto.parser(), extensionRegistry));
                        } else if (tag == 32) {
                            this.bitField0_ |= 4;
                            this.hidden_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ |= 8;
                            this.waitingToShow_ = input.readBool();
                        } else if (tag == 48) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.paused_ = input.readBool();
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
                    synchronized (WindowTokenProto.class) {
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

    public static WindowTokenProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WindowTokenProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
