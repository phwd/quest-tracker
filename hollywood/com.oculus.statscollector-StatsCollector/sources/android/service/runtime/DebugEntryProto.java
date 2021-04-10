package android.service.runtime;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class DebugEntryProto extends GeneratedMessageLite<DebugEntryProto, Builder> implements DebugEntryProtoOrBuilder {
    private static final DebugEntryProto DEFAULT_INSTANCE = new DebugEntryProto();
    public static final int KEY_FIELD_NUMBER = 1;
    private static volatile Parser<DebugEntryProto> PARSER = null;
    public static final int STRING_VALUE_FIELD_NUMBER = 2;
    private int bitField0_;
    private String key_ = "";
    private String stringValue_ = "";

    private DebugEntryProto() {
    }

    @Override // android.service.runtime.DebugEntryProtoOrBuilder
    public boolean hasKey() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.runtime.DebugEntryProtoOrBuilder
    public String getKey() {
        return this.key_;
    }

    @Override // android.service.runtime.DebugEntryProtoOrBuilder
    public ByteString getKeyBytes() {
        return ByteString.copyFromUtf8(this.key_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKey(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.key_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKey() {
        this.bitField0_ &= -2;
        this.key_ = getDefaultInstance().getKey();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.key_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.runtime.DebugEntryProtoOrBuilder
    public boolean hasStringValue() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.runtime.DebugEntryProtoOrBuilder
    public String getStringValue() {
        return this.stringValue_;
    }

    @Override // android.service.runtime.DebugEntryProtoOrBuilder
    public ByteString getStringValueBytes() {
        return ByteString.copyFromUtf8(this.stringValue_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStringValue(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.stringValue_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStringValue() {
        this.bitField0_ &= -3;
        this.stringValue_ = getDefaultInstance().getStringValue();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStringValueBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.stringValue_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getKey());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getStringValue());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getKey());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getStringValue());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DebugEntryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DebugEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DebugEntryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DebugEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DebugEntryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DebugEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DebugEntryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DebugEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DebugEntryProto parseFrom(InputStream input) throws IOException {
        return (DebugEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DebugEntryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DebugEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DebugEntryProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DebugEntryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DebugEntryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DebugEntryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DebugEntryProto parseFrom(CodedInputStream input) throws IOException {
        return (DebugEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DebugEntryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DebugEntryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DebugEntryProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DebugEntryProto, Builder> implements DebugEntryProtoOrBuilder {
        private Builder() {
            super(DebugEntryProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.runtime.DebugEntryProtoOrBuilder
        public boolean hasKey() {
            return ((DebugEntryProto) this.instance).hasKey();
        }

        @Override // android.service.runtime.DebugEntryProtoOrBuilder
        public String getKey() {
            return ((DebugEntryProto) this.instance).getKey();
        }

        @Override // android.service.runtime.DebugEntryProtoOrBuilder
        public ByteString getKeyBytes() {
            return ((DebugEntryProto) this.instance).getKeyBytes();
        }

        public Builder setKey(String value) {
            copyOnWrite();
            ((DebugEntryProto) this.instance).setKey(value);
            return this;
        }

        public Builder clearKey() {
            copyOnWrite();
            ((DebugEntryProto) this.instance).clearKey();
            return this;
        }

        public Builder setKeyBytes(ByteString value) {
            copyOnWrite();
            ((DebugEntryProto) this.instance).setKeyBytes(value);
            return this;
        }

        @Override // android.service.runtime.DebugEntryProtoOrBuilder
        public boolean hasStringValue() {
            return ((DebugEntryProto) this.instance).hasStringValue();
        }

        @Override // android.service.runtime.DebugEntryProtoOrBuilder
        public String getStringValue() {
            return ((DebugEntryProto) this.instance).getStringValue();
        }

        @Override // android.service.runtime.DebugEntryProtoOrBuilder
        public ByteString getStringValueBytes() {
            return ((DebugEntryProto) this.instance).getStringValueBytes();
        }

        public Builder setStringValue(String value) {
            copyOnWrite();
            ((DebugEntryProto) this.instance).setStringValue(value);
            return this;
        }

        public Builder clearStringValue() {
            copyOnWrite();
            ((DebugEntryProto) this.instance).clearStringValue();
            return this;
        }

        public Builder setStringValueBytes(ByteString value) {
            copyOnWrite();
            ((DebugEntryProto) this.instance).setStringValueBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DebugEntryProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DebugEntryProto other = (DebugEntryProto) arg1;
                this.key_ = visitor.visitString(hasKey(), this.key_, other.hasKey(), other.key_);
                this.stringValue_ = visitor.visitString(hasStringValue(), this.stringValue_, other.hasStringValue(), other.stringValue_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
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
                            this.bitField0_ |= 1;
                            this.key_ = s;
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.stringValue_ = s2;
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
                    synchronized (DebugEntryProto.class) {
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

    public static DebugEntryProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DebugEntryProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
