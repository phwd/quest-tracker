package android.service.notification;

import android.content.ComponentNameProto;
import android.content.ComponentNameProtoOrBuilder;
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

public final class ListenersDisablingEffectsProto extends GeneratedMessageLite<ListenersDisablingEffectsProto, Builder> implements ListenersDisablingEffectsProtoOrBuilder {
    private static final ListenersDisablingEffectsProto DEFAULT_INSTANCE = new ListenersDisablingEffectsProto();
    public static final int HINT_FIELD_NUMBER = 1;
    public static final int LISTENER_COMPONENTS_FIELD_NUMBER = 3;
    private static volatile Parser<ListenersDisablingEffectsProto> PARSER;
    private int bitField0_;
    private int hint_ = 0;
    private Internal.ProtobufList<ComponentNameProto> listenerComponents_ = emptyProtobufList();

    private ListenersDisablingEffectsProto() {
    }

    @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
    public boolean hasHint() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
    public int getHint() {
        return this.hint_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHint(int value) {
        this.bitField0_ |= 1;
        this.hint_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHint() {
        this.bitField0_ &= -2;
        this.hint_ = 0;
    }

    @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
    public List<ComponentNameProto> getListenerComponentsList() {
        return this.listenerComponents_;
    }

    public List<? extends ComponentNameProtoOrBuilder> getListenerComponentsOrBuilderList() {
        return this.listenerComponents_;
    }

    @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
    public int getListenerComponentsCount() {
        return this.listenerComponents_.size();
    }

    @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
    public ComponentNameProto getListenerComponents(int index) {
        return this.listenerComponents_.get(index);
    }

    public ComponentNameProtoOrBuilder getListenerComponentsOrBuilder(int index) {
        return this.listenerComponents_.get(index);
    }

    private void ensureListenerComponentsIsMutable() {
        if (!this.listenerComponents_.isModifiable()) {
            this.listenerComponents_ = GeneratedMessageLite.mutableCopy(this.listenerComponents_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenerComponents(int index, ComponentNameProto value) {
        if (value != null) {
            ensureListenerComponentsIsMutable();
            this.listenerComponents_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenerComponents(int index, ComponentNameProto.Builder builderForValue) {
        ensureListenerComponentsIsMutable();
        this.listenerComponents_.set(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addListenerComponents(ComponentNameProto value) {
        if (value != null) {
            ensureListenerComponentsIsMutable();
            this.listenerComponents_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addListenerComponents(int index, ComponentNameProto value) {
        if (value != null) {
            ensureListenerComponentsIsMutable();
            this.listenerComponents_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addListenerComponents(ComponentNameProto.Builder builderForValue) {
        ensureListenerComponentsIsMutable();
        this.listenerComponents_.add((ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addListenerComponents(int index, ComponentNameProto.Builder builderForValue) {
        ensureListenerComponentsIsMutable();
        this.listenerComponents_.add(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllListenerComponents(Iterable<? extends ComponentNameProto> values) {
        ensureListenerComponentsIsMutable();
        AbstractMessageLite.addAll(values, this.listenerComponents_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearListenerComponents() {
        this.listenerComponents_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeListenerComponents(int index) {
        ensureListenerComponentsIsMutable();
        this.listenerComponents_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.hint_);
        }
        for (int i = 0; i < this.listenerComponents_.size(); i++) {
            output.writeMessage(3, this.listenerComponents_.get(i));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.hint_);
        }
        for (int i = 0; i < this.listenerComponents_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.listenerComponents_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ListenersDisablingEffectsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ListenersDisablingEffectsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenersDisablingEffectsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenersDisablingEffectsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenersDisablingEffectsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ListenersDisablingEffectsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ListenersDisablingEffectsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ListenersDisablingEffectsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ListenersDisablingEffectsProto parseFrom(InputStream input) throws IOException {
        return (ListenersDisablingEffectsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenersDisablingEffectsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenersDisablingEffectsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListenersDisablingEffectsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ListenersDisablingEffectsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenersDisablingEffectsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenersDisablingEffectsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ListenersDisablingEffectsProto parseFrom(CodedInputStream input) throws IOException {
        return (ListenersDisablingEffectsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ListenersDisablingEffectsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ListenersDisablingEffectsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListenersDisablingEffectsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListenersDisablingEffectsProto, Builder> implements ListenersDisablingEffectsProtoOrBuilder {
        private Builder() {
            super(ListenersDisablingEffectsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
        public boolean hasHint() {
            return ((ListenersDisablingEffectsProto) this.instance).hasHint();
        }

        @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
        public int getHint() {
            return ((ListenersDisablingEffectsProto) this.instance).getHint();
        }

        public Builder setHint(int value) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).setHint(value);
            return this;
        }

        public Builder clearHint() {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).clearHint();
            return this;
        }

        @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
        public List<ComponentNameProto> getListenerComponentsList() {
            return Collections.unmodifiableList(((ListenersDisablingEffectsProto) this.instance).getListenerComponentsList());
        }

        @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
        public int getListenerComponentsCount() {
            return ((ListenersDisablingEffectsProto) this.instance).getListenerComponentsCount();
        }

        @Override // android.service.notification.ListenersDisablingEffectsProtoOrBuilder
        public ComponentNameProto getListenerComponents(int index) {
            return ((ListenersDisablingEffectsProto) this.instance).getListenerComponents(index);
        }

        public Builder setListenerComponents(int index, ComponentNameProto value) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).setListenerComponents((ListenersDisablingEffectsProto) index, (int) value);
            return this;
        }

        public Builder setListenerComponents(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).setListenerComponents((ListenersDisablingEffectsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addListenerComponents(ComponentNameProto value) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).addListenerComponents((ListenersDisablingEffectsProto) value);
            return this;
        }

        public Builder addListenerComponents(int index, ComponentNameProto value) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).addListenerComponents((ListenersDisablingEffectsProto) index, (int) value);
            return this;
        }

        public Builder addListenerComponents(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).addListenerComponents((ListenersDisablingEffectsProto) builderForValue);
            return this;
        }

        public Builder addListenerComponents(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).addListenerComponents((ListenersDisablingEffectsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllListenerComponents(Iterable<? extends ComponentNameProto> values) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).addAllListenerComponents(values);
            return this;
        }

        public Builder clearListenerComponents() {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).clearListenerComponents();
            return this;
        }

        public Builder removeListenerComponents(int index) {
            copyOnWrite();
            ((ListenersDisablingEffectsProto) this.instance).removeListenerComponents(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ListenersDisablingEffectsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.listenerComponents_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ListenersDisablingEffectsProto other = (ListenersDisablingEffectsProto) arg1;
                this.hint_ = visitor.visitInt(hasHint(), this.hint_, other.hasHint(), other.hint_);
                this.listenerComponents_ = visitor.visitList(this.listenerComponents_, other.listenerComponents_);
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
                            this.hint_ = input.readInt32();
                        } else if (tag == 26) {
                            if (!this.listenerComponents_.isModifiable()) {
                                this.listenerComponents_ = GeneratedMessageLite.mutableCopy(this.listenerComponents_);
                            }
                            this.listenerComponents_.add((ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry));
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
                    synchronized (ListenersDisablingEffectsProto.class) {
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

    public static ListenersDisablingEffectsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListenersDisablingEffectsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
