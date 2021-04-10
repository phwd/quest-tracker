package com.android.internal.util;

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

public final class LocalLogProto extends GeneratedMessageLite<LocalLogProto, Builder> implements LocalLogProtoOrBuilder {
    private static final LocalLogProto DEFAULT_INSTANCE = new LocalLogProto();
    public static final int LINES_FIELD_NUMBER = 1;
    private static volatile Parser<LocalLogProto> PARSER;
    private Internal.ProtobufList<String> lines_ = GeneratedMessageLite.emptyProtobufList();

    private LocalLogProto() {
    }

    @Override // com.android.internal.util.LocalLogProtoOrBuilder
    public List<String> getLinesList() {
        return this.lines_;
    }

    @Override // com.android.internal.util.LocalLogProtoOrBuilder
    public int getLinesCount() {
        return this.lines_.size();
    }

    @Override // com.android.internal.util.LocalLogProtoOrBuilder
    public String getLines(int index) {
        return this.lines_.get(index);
    }

    @Override // com.android.internal.util.LocalLogProtoOrBuilder
    public ByteString getLinesBytes(int index) {
        return ByteString.copyFromUtf8(this.lines_.get(index));
    }

    private void ensureLinesIsMutable() {
        if (!this.lines_.isModifiable()) {
            this.lines_ = GeneratedMessageLite.mutableCopy(this.lines_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLines(int index, String value) {
        if (value != null) {
            ensureLinesIsMutable();
            this.lines_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLines(String value) {
        if (value != null) {
            ensureLinesIsMutable();
            this.lines_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllLines(Iterable<String> values) {
        ensureLinesIsMutable();
        AbstractMessageLite.addAll(values, this.lines_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLines() {
        this.lines_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addLinesBytes(ByteString value) {
        if (value != null) {
            ensureLinesIsMutable();
            this.lines_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.lines_.size(); i++) {
            output.writeString(1, this.lines_.get(i));
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int dataSize = 0;
        for (int i = 0; i < this.lines_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.lines_.get(i));
        }
        int size2 = 0 + dataSize + (getLinesList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size2;
        return size2;
    }

    public static LocalLogProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (LocalLogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LocalLogProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LocalLogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LocalLogProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (LocalLogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LocalLogProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LocalLogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LocalLogProto parseFrom(InputStream input) throws IOException {
        return (LocalLogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LocalLogProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocalLogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LocalLogProto parseDelimitedFrom(InputStream input) throws IOException {
        return (LocalLogProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static LocalLogProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocalLogProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LocalLogProto parseFrom(CodedInputStream input) throws IOException {
        return (LocalLogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LocalLogProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocalLogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LocalLogProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LocalLogProto, Builder> implements LocalLogProtoOrBuilder {
        private Builder() {
            super(LocalLogProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.internal.util.LocalLogProtoOrBuilder
        public List<String> getLinesList() {
            return Collections.unmodifiableList(((LocalLogProto) this.instance).getLinesList());
        }

        @Override // com.android.internal.util.LocalLogProtoOrBuilder
        public int getLinesCount() {
            return ((LocalLogProto) this.instance).getLinesCount();
        }

        @Override // com.android.internal.util.LocalLogProtoOrBuilder
        public String getLines(int index) {
            return ((LocalLogProto) this.instance).getLines(index);
        }

        @Override // com.android.internal.util.LocalLogProtoOrBuilder
        public ByteString getLinesBytes(int index) {
            return ((LocalLogProto) this.instance).getLinesBytes(index);
        }

        public Builder setLines(int index, String value) {
            copyOnWrite();
            ((LocalLogProto) this.instance).setLines(index, value);
            return this;
        }

        public Builder addLines(String value) {
            copyOnWrite();
            ((LocalLogProto) this.instance).addLines(value);
            return this;
        }

        public Builder addAllLines(Iterable<String> values) {
            copyOnWrite();
            ((LocalLogProto) this.instance).addAllLines(values);
            return this;
        }

        public Builder clearLines() {
            copyOnWrite();
            ((LocalLogProto) this.instance).clearLines();
            return this;
        }

        public Builder addLinesBytes(ByteString value) {
            copyOnWrite();
            ((LocalLogProto) this.instance).addLinesBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new LocalLogProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.lines_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.lines_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.lines_, ((LocalLogProto) arg1).lines_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            String s = input.readString();
                            if (!this.lines_.isModifiable()) {
                                this.lines_ = GeneratedMessageLite.mutableCopy(this.lines_);
                            }
                            this.lines_.add(s);
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
                    synchronized (LocalLogProto.class) {
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

    public static LocalLogProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LocalLogProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
