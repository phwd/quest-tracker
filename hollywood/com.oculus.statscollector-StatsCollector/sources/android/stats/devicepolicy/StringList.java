package android.stats.devicepolicy;

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

public final class StringList extends GeneratedMessageLite<StringList, Builder> implements StringListOrBuilder {
    private static final StringList DEFAULT_INSTANCE = new StringList();
    private static volatile Parser<StringList> PARSER = null;
    public static final int STRING_VALUE_FIELD_NUMBER = 1;
    private Internal.ProtobufList<String> stringValue_ = GeneratedMessageLite.emptyProtobufList();

    private StringList() {
    }

    @Override // android.stats.devicepolicy.StringListOrBuilder
    public List<String> getStringValueList() {
        return this.stringValue_;
    }

    @Override // android.stats.devicepolicy.StringListOrBuilder
    public int getStringValueCount() {
        return this.stringValue_.size();
    }

    @Override // android.stats.devicepolicy.StringListOrBuilder
    public String getStringValue(int index) {
        return this.stringValue_.get(index);
    }

    @Override // android.stats.devicepolicy.StringListOrBuilder
    public ByteString getStringValueBytes(int index) {
        return ByteString.copyFromUtf8(this.stringValue_.get(index));
    }

    private void ensureStringValueIsMutable() {
        if (!this.stringValue_.isModifiable()) {
            this.stringValue_ = GeneratedMessageLite.mutableCopy(this.stringValue_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStringValue(int index, String value) {
        if (value != null) {
            ensureStringValueIsMutable();
            this.stringValue_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStringValue(String value) {
        if (value != null) {
            ensureStringValueIsMutable();
            this.stringValue_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStringValue(Iterable<String> values) {
        ensureStringValueIsMutable();
        AbstractMessageLite.addAll(values, this.stringValue_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStringValue() {
        this.stringValue_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStringValueBytes(ByteString value) {
        if (value != null) {
            ensureStringValueIsMutable();
            this.stringValue_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.stringValue_.size(); i++) {
            output.writeString(1, this.stringValue_.get(i));
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
        for (int i = 0; i < this.stringValue_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.stringValue_.get(i));
        }
        int size2 = 0 + dataSize + (getStringValueList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size2;
        return size2;
    }

    public static StringList parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StringList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StringList parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StringList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StringList parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StringList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StringList parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StringList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StringList parseFrom(InputStream input) throws IOException {
        return (StringList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StringList parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StringList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StringList parseDelimitedFrom(InputStream input) throws IOException {
        return (StringList) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StringList parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StringList) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StringList parseFrom(CodedInputStream input) throws IOException {
        return (StringList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StringList parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StringList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StringList prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StringList, Builder> implements StringListOrBuilder {
        private Builder() {
            super(StringList.DEFAULT_INSTANCE);
        }

        @Override // android.stats.devicepolicy.StringListOrBuilder
        public List<String> getStringValueList() {
            return Collections.unmodifiableList(((StringList) this.instance).getStringValueList());
        }

        @Override // android.stats.devicepolicy.StringListOrBuilder
        public int getStringValueCount() {
            return ((StringList) this.instance).getStringValueCount();
        }

        @Override // android.stats.devicepolicy.StringListOrBuilder
        public String getStringValue(int index) {
            return ((StringList) this.instance).getStringValue(index);
        }

        @Override // android.stats.devicepolicy.StringListOrBuilder
        public ByteString getStringValueBytes(int index) {
            return ((StringList) this.instance).getStringValueBytes(index);
        }

        public Builder setStringValue(int index, String value) {
            copyOnWrite();
            ((StringList) this.instance).setStringValue(index, value);
            return this;
        }

        public Builder addStringValue(String value) {
            copyOnWrite();
            ((StringList) this.instance).addStringValue(value);
            return this;
        }

        public Builder addAllStringValue(Iterable<String> values) {
            copyOnWrite();
            ((StringList) this.instance).addAllStringValue(values);
            return this;
        }

        public Builder clearStringValue() {
            copyOnWrite();
            ((StringList) this.instance).clearStringValue();
            return this;
        }

        public Builder addStringValueBytes(ByteString value) {
            copyOnWrite();
            ((StringList) this.instance).addStringValueBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new StringList();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.stringValue_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.stringValue_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.stringValue_, ((StringList) arg1).stringValue_);
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
                            if (!this.stringValue_.isModifiable()) {
                                this.stringValue_ = GeneratedMessageLite.mutableCopy(this.stringValue_);
                            }
                            this.stringValue_.add(s);
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
                    synchronized (StringList.class) {
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

    public static StringList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StringList> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
