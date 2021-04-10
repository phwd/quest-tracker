package com.android.server.wm;

import com.android.server.wm.DisplayContentProto;
import com.android.server.wm.IdentifierProto;
import com.android.server.wm.WindowContainerProto;
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

public final class RootWindowContainerProto extends GeneratedMessageLite<RootWindowContainerProto, Builder> implements RootWindowContainerProtoOrBuilder {
    private static final RootWindowContainerProto DEFAULT_INSTANCE = new RootWindowContainerProto();
    public static final int DISPLAYS_FIELD_NUMBER = 2;
    private static volatile Parser<RootWindowContainerProto> PARSER = null;
    public static final int WINDOWS_FIELD_NUMBER = 3;
    public static final int WINDOW_CONTAINER_FIELD_NUMBER = 1;
    private int bitField0_;
    private Internal.ProtobufList<DisplayContentProto> displays_ = emptyProtobufList();
    private WindowContainerProto windowContainer_;
    private Internal.ProtobufList<IdentifierProto> windows_ = emptyProtobufList();

    private RootWindowContainerProto() {
    }

    @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
    public boolean hasWindowContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
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

    @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
    public List<DisplayContentProto> getDisplaysList() {
        return this.displays_;
    }

    public List<? extends DisplayContentProtoOrBuilder> getDisplaysOrBuilderList() {
        return this.displays_;
    }

    @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
    public int getDisplaysCount() {
        return this.displays_.size();
    }

    @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
    public DisplayContentProto getDisplays(int index) {
        return this.displays_.get(index);
    }

    public DisplayContentProtoOrBuilder getDisplaysOrBuilder(int index) {
        return this.displays_.get(index);
    }

    private void ensureDisplaysIsMutable() {
        if (!this.displays_.isModifiable()) {
            this.displays_ = GeneratedMessageLite.mutableCopy(this.displays_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplays(int index, DisplayContentProto value) {
        if (value != null) {
            ensureDisplaysIsMutable();
            this.displays_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplays(int index, DisplayContentProto.Builder builderForValue) {
        ensureDisplaysIsMutable();
        this.displays_.set(index, (DisplayContentProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisplays(DisplayContentProto value) {
        if (value != null) {
            ensureDisplaysIsMutable();
            this.displays_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisplays(int index, DisplayContentProto value) {
        if (value != null) {
            ensureDisplaysIsMutable();
            this.displays_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisplays(DisplayContentProto.Builder builderForValue) {
        ensureDisplaysIsMutable();
        this.displays_.add((DisplayContentProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisplays(int index, DisplayContentProto.Builder builderForValue) {
        ensureDisplaysIsMutable();
        this.displays_.add(index, (DisplayContentProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDisplays(Iterable<? extends DisplayContentProto> values) {
        ensureDisplaysIsMutable();
        AbstractMessageLite.addAll(values, this.displays_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplays() {
        this.displays_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDisplays(int index) {
        ensureDisplaysIsMutable();
        this.displays_.remove(index);
    }

    @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
    public List<IdentifierProto> getWindowsList() {
        return this.windows_;
    }

    public List<? extends IdentifierProtoOrBuilder> getWindowsOrBuilderList() {
        return this.windows_;
    }

    @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
    public int getWindowsCount() {
        return this.windows_.size();
    }

    @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
    public IdentifierProto getWindows(int index) {
        return this.windows_.get(index);
    }

    public IdentifierProtoOrBuilder getWindowsOrBuilder(int index) {
        return this.windows_.get(index);
    }

    private void ensureWindowsIsMutable() {
        if (!this.windows_.isModifiable()) {
            this.windows_ = GeneratedMessageLite.mutableCopy(this.windows_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindows(int index, IdentifierProto value) {
        if (value != null) {
            ensureWindowsIsMutable();
            this.windows_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindows(int index, IdentifierProto.Builder builderForValue) {
        ensureWindowsIsMutable();
        this.windows_.set(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWindows(IdentifierProto value) {
        if (value != null) {
            ensureWindowsIsMutable();
            this.windows_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWindows(int index, IdentifierProto value) {
        if (value != null) {
            ensureWindowsIsMutable();
            this.windows_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWindows(IdentifierProto.Builder builderForValue) {
        ensureWindowsIsMutable();
        this.windows_.add((IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWindows(int index, IdentifierProto.Builder builderForValue) {
        ensureWindowsIsMutable();
        this.windows_.add(index, (IdentifierProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWindows(Iterable<? extends IdentifierProto> values) {
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

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getWindowContainer());
        }
        for (int i = 0; i < this.displays_.size(); i++) {
            output.writeMessage(2, this.displays_.get(i));
        }
        for (int i2 = 0; i2 < this.windows_.size(); i2++) {
            output.writeMessage(3, this.windows_.get(i2));
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
        for (int i = 0; i < this.displays_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.displays_.get(i));
        }
        for (int i2 = 0; i2 < this.windows_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.windows_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RootWindowContainerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RootWindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RootWindowContainerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RootWindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RootWindowContainerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RootWindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RootWindowContainerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RootWindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RootWindowContainerProto parseFrom(InputStream input) throws IOException {
        return (RootWindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RootWindowContainerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RootWindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RootWindowContainerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RootWindowContainerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RootWindowContainerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RootWindowContainerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RootWindowContainerProto parseFrom(CodedInputStream input) throws IOException {
        return (RootWindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RootWindowContainerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RootWindowContainerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RootWindowContainerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RootWindowContainerProto, Builder> implements RootWindowContainerProtoOrBuilder {
        private Builder() {
            super(RootWindowContainerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
        public boolean hasWindowContainer() {
            return ((RootWindowContainerProto) this.instance).hasWindowContainer();
        }

        @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
        public WindowContainerProto getWindowContainer() {
            return ((RootWindowContainerProto) this.instance).getWindowContainer();
        }

        public Builder setWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).setWindowContainer((RootWindowContainerProto) value);
            return this;
        }

        public Builder setWindowContainer(WindowContainerProto.Builder builderForValue) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).setWindowContainer((RootWindowContainerProto) builderForValue);
            return this;
        }

        public Builder mergeWindowContainer(WindowContainerProto value) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).mergeWindowContainer(value);
            return this;
        }

        public Builder clearWindowContainer() {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).clearWindowContainer();
            return this;
        }

        @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
        public List<DisplayContentProto> getDisplaysList() {
            return Collections.unmodifiableList(((RootWindowContainerProto) this.instance).getDisplaysList());
        }

        @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
        public int getDisplaysCount() {
            return ((RootWindowContainerProto) this.instance).getDisplaysCount();
        }

        @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
        public DisplayContentProto getDisplays(int index) {
            return ((RootWindowContainerProto) this.instance).getDisplays(index);
        }

        public Builder setDisplays(int index, DisplayContentProto value) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).setDisplays((RootWindowContainerProto) index, (int) value);
            return this;
        }

        public Builder setDisplays(int index, DisplayContentProto.Builder builderForValue) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).setDisplays((RootWindowContainerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDisplays(DisplayContentProto value) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addDisplays((RootWindowContainerProto) value);
            return this;
        }

        public Builder addDisplays(int index, DisplayContentProto value) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addDisplays((RootWindowContainerProto) index, (int) value);
            return this;
        }

        public Builder addDisplays(DisplayContentProto.Builder builderForValue) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addDisplays((RootWindowContainerProto) builderForValue);
            return this;
        }

        public Builder addDisplays(int index, DisplayContentProto.Builder builderForValue) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addDisplays((RootWindowContainerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDisplays(Iterable<? extends DisplayContentProto> values) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addAllDisplays(values);
            return this;
        }

        public Builder clearDisplays() {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).clearDisplays();
            return this;
        }

        public Builder removeDisplays(int index) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).removeDisplays(index);
            return this;
        }

        @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
        public List<IdentifierProto> getWindowsList() {
            return Collections.unmodifiableList(((RootWindowContainerProto) this.instance).getWindowsList());
        }

        @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
        public int getWindowsCount() {
            return ((RootWindowContainerProto) this.instance).getWindowsCount();
        }

        @Override // com.android.server.wm.RootWindowContainerProtoOrBuilder
        public IdentifierProto getWindows(int index) {
            return ((RootWindowContainerProto) this.instance).getWindows(index);
        }

        public Builder setWindows(int index, IdentifierProto value) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).setWindows((RootWindowContainerProto) index, (int) value);
            return this;
        }

        public Builder setWindows(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).setWindows((RootWindowContainerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWindows(IdentifierProto value) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addWindows((RootWindowContainerProto) value);
            return this;
        }

        public Builder addWindows(int index, IdentifierProto value) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addWindows((RootWindowContainerProto) index, (int) value);
            return this;
        }

        public Builder addWindows(IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addWindows((RootWindowContainerProto) builderForValue);
            return this;
        }

        public Builder addWindows(int index, IdentifierProto.Builder builderForValue) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addWindows((RootWindowContainerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWindows(Iterable<? extends IdentifierProto> values) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).addAllWindows(values);
            return this;
        }

        public Builder clearWindows() {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).clearWindows();
            return this;
        }

        public Builder removeWindows(int index) {
            copyOnWrite();
            ((RootWindowContainerProto) this.instance).removeWindows(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RootWindowContainerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.displays_.makeImmutable();
                this.windows_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RootWindowContainerProto other = (RootWindowContainerProto) arg1;
                this.windowContainer_ = (WindowContainerProto) visitor.visitMessage(this.windowContainer_, other.windowContainer_);
                this.displays_ = visitor.visitList(this.displays_, other.displays_);
                this.windows_ = visitor.visitList(this.windows_, other.windows_);
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
                        } else if (tag == 18) {
                            if (!this.displays_.isModifiable()) {
                                this.displays_ = GeneratedMessageLite.mutableCopy(this.displays_);
                            }
                            this.displays_.add((DisplayContentProto) input.readMessage(DisplayContentProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.windows_.isModifiable()) {
                                this.windows_ = GeneratedMessageLite.mutableCopy(this.windows_);
                            }
                            this.windows_.add((IdentifierProto) input.readMessage(IdentifierProto.parser(), extensionRegistry));
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
                    synchronized (RootWindowContainerProto.class) {
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

    public static RootWindowContainerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RootWindowContainerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
