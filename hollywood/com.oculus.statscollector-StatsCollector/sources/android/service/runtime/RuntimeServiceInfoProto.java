package android.service.runtime;

import android.service.runtime.DebugEntryProto;
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

public final class RuntimeServiceInfoProto extends GeneratedMessageLite<RuntimeServiceInfoProto, Builder> implements RuntimeServiceInfoProtoOrBuilder {
    public static final int DEBUG_ENTRY_FIELD_NUMBER = 1;
    private static final RuntimeServiceInfoProto DEFAULT_INSTANCE = new RuntimeServiceInfoProto();
    private static volatile Parser<RuntimeServiceInfoProto> PARSER;
    private Internal.ProtobufList<DebugEntryProto> debugEntry_ = emptyProtobufList();

    private RuntimeServiceInfoProto() {
    }

    @Override // android.service.runtime.RuntimeServiceInfoProtoOrBuilder
    public List<DebugEntryProto> getDebugEntryList() {
        return this.debugEntry_;
    }

    public List<? extends DebugEntryProtoOrBuilder> getDebugEntryOrBuilderList() {
        return this.debugEntry_;
    }

    @Override // android.service.runtime.RuntimeServiceInfoProtoOrBuilder
    public int getDebugEntryCount() {
        return this.debugEntry_.size();
    }

    @Override // android.service.runtime.RuntimeServiceInfoProtoOrBuilder
    public DebugEntryProto getDebugEntry(int index) {
        return this.debugEntry_.get(index);
    }

    public DebugEntryProtoOrBuilder getDebugEntryOrBuilder(int index) {
        return this.debugEntry_.get(index);
    }

    private void ensureDebugEntryIsMutable() {
        if (!this.debugEntry_.isModifiable()) {
            this.debugEntry_ = GeneratedMessageLite.mutableCopy(this.debugEntry_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebugEntry(int index, DebugEntryProto value) {
        if (value != null) {
            ensureDebugEntryIsMutable();
            this.debugEntry_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDebugEntry(int index, DebugEntryProto.Builder builderForValue) {
        ensureDebugEntryIsMutable();
        this.debugEntry_.set(index, (DebugEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDebugEntry(DebugEntryProto value) {
        if (value != null) {
            ensureDebugEntryIsMutable();
            this.debugEntry_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDebugEntry(int index, DebugEntryProto value) {
        if (value != null) {
            ensureDebugEntryIsMutable();
            this.debugEntry_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDebugEntry(DebugEntryProto.Builder builderForValue) {
        ensureDebugEntryIsMutable();
        this.debugEntry_.add((DebugEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDebugEntry(int index, DebugEntryProto.Builder builderForValue) {
        ensureDebugEntryIsMutable();
        this.debugEntry_.add(index, (DebugEntryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDebugEntry(Iterable<? extends DebugEntryProto> values) {
        ensureDebugEntryIsMutable();
        AbstractMessageLite.addAll(values, this.debugEntry_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDebugEntry() {
        this.debugEntry_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDebugEntry(int index) {
        ensureDebugEntryIsMutable();
        this.debugEntry_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.debugEntry_.size(); i++) {
            output.writeMessage(1, this.debugEntry_.get(i));
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
        for (int i = 0; i < this.debugEntry_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.debugEntry_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RuntimeServiceInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RuntimeServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RuntimeServiceInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RuntimeServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RuntimeServiceInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RuntimeServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RuntimeServiceInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RuntimeServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RuntimeServiceInfoProto parseFrom(InputStream input) throws IOException {
        return (RuntimeServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RuntimeServiceInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RuntimeServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RuntimeServiceInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RuntimeServiceInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RuntimeServiceInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RuntimeServiceInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RuntimeServiceInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (RuntimeServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RuntimeServiceInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RuntimeServiceInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RuntimeServiceInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RuntimeServiceInfoProto, Builder> implements RuntimeServiceInfoProtoOrBuilder {
        private Builder() {
            super(RuntimeServiceInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.runtime.RuntimeServiceInfoProtoOrBuilder
        public List<DebugEntryProto> getDebugEntryList() {
            return Collections.unmodifiableList(((RuntimeServiceInfoProto) this.instance).getDebugEntryList());
        }

        @Override // android.service.runtime.RuntimeServiceInfoProtoOrBuilder
        public int getDebugEntryCount() {
            return ((RuntimeServiceInfoProto) this.instance).getDebugEntryCount();
        }

        @Override // android.service.runtime.RuntimeServiceInfoProtoOrBuilder
        public DebugEntryProto getDebugEntry(int index) {
            return ((RuntimeServiceInfoProto) this.instance).getDebugEntry(index);
        }

        public Builder setDebugEntry(int index, DebugEntryProto value) {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).setDebugEntry((RuntimeServiceInfoProto) index, (int) value);
            return this;
        }

        public Builder setDebugEntry(int index, DebugEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).setDebugEntry((RuntimeServiceInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDebugEntry(DebugEntryProto value) {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).addDebugEntry((RuntimeServiceInfoProto) value);
            return this;
        }

        public Builder addDebugEntry(int index, DebugEntryProto value) {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).addDebugEntry((RuntimeServiceInfoProto) index, (int) value);
            return this;
        }

        public Builder addDebugEntry(DebugEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).addDebugEntry((RuntimeServiceInfoProto) builderForValue);
            return this;
        }

        public Builder addDebugEntry(int index, DebugEntryProto.Builder builderForValue) {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).addDebugEntry((RuntimeServiceInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDebugEntry(Iterable<? extends DebugEntryProto> values) {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).addAllDebugEntry(values);
            return this;
        }

        public Builder clearDebugEntry() {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).clearDebugEntry();
            return this;
        }

        public Builder removeDebugEntry(int index) {
            copyOnWrite();
            ((RuntimeServiceInfoProto) this.instance).removeDebugEntry(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RuntimeServiceInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.debugEntry_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.debugEntry_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.debugEntry_, ((RuntimeServiceInfoProto) arg1).debugEntry_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                            if (!this.debugEntry_.isModifiable()) {
                                this.debugEntry_ = GeneratedMessageLite.mutableCopy(this.debugEntry_);
                            }
                            this.debugEntry_.add((DebugEntryProto) input.readMessage(DebugEntryProto.parser(), extensionRegistry));
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
                    synchronized (RuntimeServiceInfoProto.class) {
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

    public static RuntimeServiceInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RuntimeServiceInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
