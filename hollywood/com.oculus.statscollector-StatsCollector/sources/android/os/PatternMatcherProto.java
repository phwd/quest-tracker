package android.os;

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

public final class PatternMatcherProto extends GeneratedMessageLite<PatternMatcherProto, Builder> implements PatternMatcherProtoOrBuilder {
    private static final PatternMatcherProto DEFAULT_INSTANCE = new PatternMatcherProto();
    private static volatile Parser<PatternMatcherProto> PARSER = null;
    public static final int PATTERN_FIELD_NUMBER = 1;
    public static final int TYPE_FIELD_NUMBER = 2;
    private int bitField0_;
    private String pattern_ = "";
    private int type_ = 0;

    private PatternMatcherProto() {
    }

    public enum Type implements Internal.EnumLite {
        TYPE_LITERAL(0),
        TYPE_PREFIX(1),
        TYPE_SIMPLE_GLOB(2),
        TYPE_ADVANCED_GLOB(3);
        
        public static final int TYPE_ADVANCED_GLOB_VALUE = 3;
        public static final int TYPE_LITERAL_VALUE = 0;
        public static final int TYPE_PREFIX_VALUE = 1;
        public static final int TYPE_SIMPLE_GLOB_VALUE = 2;
        private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
            /* class android.os.PatternMatcherProto.Type.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Type findValueByNumber(int number) {
                return Type.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Type valueOf(int value2) {
            return forNumber(value2);
        }

        public static Type forNumber(int value2) {
            if (value2 == 0) {
                return TYPE_LITERAL;
            }
            if (value2 == 1) {
                return TYPE_PREFIX;
            }
            if (value2 == 2) {
                return TYPE_SIMPLE_GLOB;
            }
            if (value2 != 3) {
                return null;
            }
            return TYPE_ADVANCED_GLOB;
        }

        public static Internal.EnumLiteMap<Type> internalGetValueMap() {
            return internalValueMap;
        }

        private Type(int value2) {
            this.value = value2;
        }
    }

    @Override // android.os.PatternMatcherProtoOrBuilder
    public boolean hasPattern() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.PatternMatcherProtoOrBuilder
    public String getPattern() {
        return this.pattern_;
    }

    @Override // android.os.PatternMatcherProtoOrBuilder
    public ByteString getPatternBytes() {
        return ByteString.copyFromUtf8(this.pattern_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPattern(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.pattern_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPattern() {
        this.bitField0_ &= -2;
        this.pattern_ = getDefaultInstance().getPattern();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPatternBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.pattern_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.PatternMatcherProtoOrBuilder
    public boolean hasType() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.PatternMatcherProtoOrBuilder
    public Type getType() {
        Type result = Type.forNumber(this.type_);
        return result == null ? Type.TYPE_LITERAL : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setType(Type value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.type_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearType() {
        this.bitField0_ &= -3;
        this.type_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getPattern());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.type_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getPattern());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.type_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PatternMatcherProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PatternMatcherProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PatternMatcherProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PatternMatcherProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PatternMatcherProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PatternMatcherProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PatternMatcherProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PatternMatcherProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PatternMatcherProto parseFrom(InputStream input) throws IOException {
        return (PatternMatcherProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PatternMatcherProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PatternMatcherProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PatternMatcherProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PatternMatcherProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PatternMatcherProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PatternMatcherProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PatternMatcherProto parseFrom(CodedInputStream input) throws IOException {
        return (PatternMatcherProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PatternMatcherProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PatternMatcherProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PatternMatcherProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PatternMatcherProto, Builder> implements PatternMatcherProtoOrBuilder {
        private Builder() {
            super(PatternMatcherProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.PatternMatcherProtoOrBuilder
        public boolean hasPattern() {
            return ((PatternMatcherProto) this.instance).hasPattern();
        }

        @Override // android.os.PatternMatcherProtoOrBuilder
        public String getPattern() {
            return ((PatternMatcherProto) this.instance).getPattern();
        }

        @Override // android.os.PatternMatcherProtoOrBuilder
        public ByteString getPatternBytes() {
            return ((PatternMatcherProto) this.instance).getPatternBytes();
        }

        public Builder setPattern(String value) {
            copyOnWrite();
            ((PatternMatcherProto) this.instance).setPattern(value);
            return this;
        }

        public Builder clearPattern() {
            copyOnWrite();
            ((PatternMatcherProto) this.instance).clearPattern();
            return this;
        }

        public Builder setPatternBytes(ByteString value) {
            copyOnWrite();
            ((PatternMatcherProto) this.instance).setPatternBytes(value);
            return this;
        }

        @Override // android.os.PatternMatcherProtoOrBuilder
        public boolean hasType() {
            return ((PatternMatcherProto) this.instance).hasType();
        }

        @Override // android.os.PatternMatcherProtoOrBuilder
        public Type getType() {
            return ((PatternMatcherProto) this.instance).getType();
        }

        public Builder setType(Type value) {
            copyOnWrite();
            ((PatternMatcherProto) this.instance).setType(value);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((PatternMatcherProto) this.instance).clearType();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PatternMatcherProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PatternMatcherProto other = (PatternMatcherProto) arg1;
                this.pattern_ = visitor.visitString(hasPattern(), this.pattern_, other.hasPattern(), other.pattern_);
                this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
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
                            this.pattern_ = s;
                        } else if (tag == 16) {
                            int rawValue = input.readEnum();
                            if (Type.forNumber(rawValue) == null) {
                                super.mergeVarintField(2, rawValue);
                            } else {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.type_ = rawValue;
                            }
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
                    synchronized (PatternMatcherProto.class) {
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

    public static PatternMatcherProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PatternMatcherProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
