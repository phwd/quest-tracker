package android.util;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class BinaryLogEntry extends GeneratedMessageLite<BinaryLogEntry, Builder> implements BinaryLogEntryOrBuilder {
    private static final BinaryLogEntry DEFAULT_INSTANCE = new BinaryLogEntry();
    public static final int ELEMS_FIELD_NUMBER = 7;
    public static final int NANOSEC_FIELD_NUMBER = 2;
    private static volatile Parser<BinaryLogEntry> PARSER = null;
    public static final int PID_FIELD_NUMBER = 4;
    public static final int SEC_FIELD_NUMBER = 1;
    public static final int TAG_INDEX_FIELD_NUMBER = 6;
    public static final int TID_FIELD_NUMBER = 5;
    public static final int UID_FIELD_NUMBER = 3;
    private int bitField0_;
    private Internal.ProtobufList<Elem> elems_ = emptyProtobufList();
    private long nanosec_ = 0;
    private int pid_ = 0;
    private long sec_ = 0;
    private int tagIndex_ = 0;
    private int tid_ = 0;
    private int uid_ = 0;

    public interface ElemOrBuilder extends MessageLiteOrBuilder {
        Elem.Type getType();

        float getValFloat();

        int getValInt32();

        long getValInt64();

        String getValString();

        ByteString getValStringBytes();

        Elem.ValueCase getValueCase();

        boolean hasType();

        boolean hasValFloat();

        boolean hasValInt32();

        boolean hasValInt64();

        boolean hasValString();
    }

    private BinaryLogEntry() {
    }

    public static final class Elem extends GeneratedMessageLite<Elem, Builder> implements ElemOrBuilder {
        private static final Elem DEFAULT_INSTANCE = new Elem();
        private static volatile Parser<Elem> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 1;
        public static final int VAL_FLOAT_FIELD_NUMBER = 5;
        public static final int VAL_INT32_FIELD_NUMBER = 2;
        public static final int VAL_INT64_FIELD_NUMBER = 3;
        public static final int VAL_STRING_FIELD_NUMBER = 4;
        private int bitField0_;
        private int type_ = 63;
        private int valueCase_ = 0;
        private Object value_;

        private Elem() {
        }

        public enum Type implements Internal.EnumLite {
            EVENT_TYPE_LIST_STOP(10),
            EVENT_TYPE_UNKNOWN(63),
            EVENT_TYPE_INT(0),
            EVENT_TYPE_LONG(1),
            EVENT_TYPE_STRING(2),
            EVENT_TYPE_LIST(3),
            EVENT_TYPE_FLOAT(4);
            
            public static final int EVENT_TYPE_FLOAT_VALUE = 4;
            public static final int EVENT_TYPE_INT_VALUE = 0;
            public static final int EVENT_TYPE_LIST_STOP_VALUE = 10;
            public static final int EVENT_TYPE_LIST_VALUE = 3;
            public static final int EVENT_TYPE_LONG_VALUE = 1;
            public static final int EVENT_TYPE_STRING_VALUE = 2;
            public static final int EVENT_TYPE_UNKNOWN_VALUE = 63;
            private static final Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
                /* class android.util.BinaryLogEntry.Elem.Type.AnonymousClass1 */

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
                    return EVENT_TYPE_INT;
                }
                if (value2 == 1) {
                    return EVENT_TYPE_LONG;
                }
                if (value2 == 2) {
                    return EVENT_TYPE_STRING;
                }
                if (value2 == 3) {
                    return EVENT_TYPE_LIST;
                }
                if (value2 == 4) {
                    return EVENT_TYPE_FLOAT;
                }
                if (value2 == 10) {
                    return EVENT_TYPE_LIST_STOP;
                }
                if (value2 != 63) {
                    return null;
                }
                return EVENT_TYPE_UNKNOWN;
            }

            public static Internal.EnumLiteMap<Type> internalGetValueMap() {
                return internalValueMap;
            }

            private Type(int value2) {
                this.value = value2;
            }
        }

        public enum ValueCase implements Internal.EnumLite {
            VAL_INT32(2),
            VAL_INT64(3),
            VAL_STRING(4),
            VAL_FLOAT(5),
            VALUE_NOT_SET(0);
            
            private final int value;

            private ValueCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static ValueCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static ValueCase forNumber(int value2) {
                if (value2 == 0) {
                    return VALUE_NOT_SET;
                }
                if (value2 == 2) {
                    return VAL_INT32;
                }
                if (value2 == 3) {
                    return VAL_INT64;
                }
                if (value2 == 4) {
                    return VAL_STRING;
                }
                if (value2 != 5) {
                    return null;
                }
                return VAL_FLOAT;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public ValueCase getValueCase() {
            return ValueCase.forNumber(this.valueCase_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public Type getType() {
            Type result = Type.forNumber(this.type_);
            return result == null ? Type.EVENT_TYPE_UNKNOWN : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setType(Type value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.type_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearType() {
            this.bitField0_ &= -2;
            this.type_ = 63;
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public boolean hasValInt32() {
            return this.valueCase_ == 2;
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public int getValInt32() {
            if (this.valueCase_ == 2) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValInt32(int value) {
            this.valueCase_ = 2;
            this.value_ = Integer.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValInt32() {
            if (this.valueCase_ == 2) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public boolean hasValInt64() {
            return this.valueCase_ == 3;
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public long getValInt64() {
            if (this.valueCase_ == 3) {
                return ((Long) this.value_).longValue();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValInt64(long value) {
            this.valueCase_ = 3;
            this.value_ = Long.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValInt64() {
            if (this.valueCase_ == 3) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public boolean hasValString() {
            return this.valueCase_ == 4;
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public String getValString() {
            if (this.valueCase_ == 4) {
                return (String) this.value_;
            }
            return "";
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public ByteString getValStringBytes() {
            String ref = "";
            if (this.valueCase_ == 4) {
                ref = (String) this.value_;
            }
            return ByteString.copyFromUtf8(ref);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValString(String value) {
            if (value != null) {
                this.valueCase_ = 4;
                this.value_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValString() {
            if (this.valueCase_ == 4) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValStringBytes(ByteString value) {
            if (value != null) {
                this.valueCase_ = 4;
                this.value_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public boolean hasValFloat() {
            return this.valueCase_ == 5;
        }

        @Override // android.util.BinaryLogEntry.ElemOrBuilder
        public float getValFloat() {
            if (this.valueCase_ == 5) {
                return ((Float) this.value_).floatValue();
            }
            return 0.0f;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setValFloat(float value) {
            this.valueCase_ = 5;
            this.value_ = Float.valueOf(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearValFloat() {
            if (this.valueCase_ == 5) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.type_);
            }
            if (this.valueCase_ == 2) {
                output.writeInt32(2, ((Integer) this.value_).intValue());
            }
            if (this.valueCase_ == 3) {
                output.writeInt64(3, ((Long) this.value_).longValue());
            }
            if (this.valueCase_ == 4) {
                output.writeString(4, getValString());
            }
            if (this.valueCase_ == 5) {
                output.writeFloat(5, ((Float) this.value_).floatValue());
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
            }
            if (this.valueCase_ == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, ((Integer) this.value_).intValue());
            }
            if (this.valueCase_ == 3) {
                size2 += CodedOutputStream.computeInt64Size(3, ((Long) this.value_).longValue());
            }
            if (this.valueCase_ == 4) {
                size2 += CodedOutputStream.computeStringSize(4, getValString());
            }
            if (this.valueCase_ == 5) {
                size2 += CodedOutputStream.computeFloatSize(5, ((Float) this.value_).floatValue());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Elem parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Elem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Elem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Elem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Elem parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Elem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Elem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Elem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Elem parseFrom(InputStream input) throws IOException {
            return (Elem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Elem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Elem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Elem parseDelimitedFrom(InputStream input) throws IOException {
            return (Elem) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Elem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Elem) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Elem parseFrom(CodedInputStream input) throws IOException {
            return (Elem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Elem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Elem) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Elem prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Elem, Builder> implements ElemOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(Elem.DEFAULT_INSTANCE);
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public ValueCase getValueCase() {
                return ((Elem) this.instance).getValueCase();
            }

            public Builder clearValue() {
                copyOnWrite();
                ((Elem) this.instance).clearValue();
                return this;
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public boolean hasType() {
                return ((Elem) this.instance).hasType();
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public Type getType() {
                return ((Elem) this.instance).getType();
            }

            public Builder setType(Type value) {
                copyOnWrite();
                ((Elem) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((Elem) this.instance).clearType();
                return this;
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public boolean hasValInt32() {
                return ((Elem) this.instance).hasValInt32();
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public int getValInt32() {
                return ((Elem) this.instance).getValInt32();
            }

            public Builder setValInt32(int value) {
                copyOnWrite();
                ((Elem) this.instance).setValInt32(value);
                return this;
            }

            public Builder clearValInt32() {
                copyOnWrite();
                ((Elem) this.instance).clearValInt32();
                return this;
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public boolean hasValInt64() {
                return ((Elem) this.instance).hasValInt64();
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public long getValInt64() {
                return ((Elem) this.instance).getValInt64();
            }

            public Builder setValInt64(long value) {
                copyOnWrite();
                ((Elem) this.instance).setValInt64(value);
                return this;
            }

            public Builder clearValInt64() {
                copyOnWrite();
                ((Elem) this.instance).clearValInt64();
                return this;
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public boolean hasValString() {
                return ((Elem) this.instance).hasValString();
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public String getValString() {
                return ((Elem) this.instance).getValString();
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public ByteString getValStringBytes() {
                return ((Elem) this.instance).getValStringBytes();
            }

            public Builder setValString(String value) {
                copyOnWrite();
                ((Elem) this.instance).setValString(value);
                return this;
            }

            public Builder clearValString() {
                copyOnWrite();
                ((Elem) this.instance).clearValString();
                return this;
            }

            public Builder setValStringBytes(ByteString value) {
                copyOnWrite();
                ((Elem) this.instance).setValStringBytes(value);
                return this;
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public boolean hasValFloat() {
                return ((Elem) this.instance).hasValFloat();
            }

            @Override // android.util.BinaryLogEntry.ElemOrBuilder
            public float getValFloat() {
                return ((Elem) this.instance).getValFloat();
            }

            public Builder setValFloat(float value) {
                copyOnWrite();
                ((Elem) this.instance).setValFloat(value);
                return this;
            }

            public Builder clearValFloat() {
                copyOnWrite();
                ((Elem) this.instance).clearValFloat();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            boolean z = true;
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Elem();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Elem other = (Elem) arg1;
                    this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                    int i = AnonymousClass1.$SwitchMap$android$util$BinaryLogEntry$Elem$ValueCase[other.getValueCase().ordinal()];
                    if (i == 1) {
                        if (this.valueCase_ != 2) {
                            z = false;
                        }
                        this.value_ = visitor.visitOneofInt(z, this.value_, other.value_);
                    } else if (i == 2) {
                        if (this.valueCase_ != 3) {
                            z = false;
                        }
                        this.value_ = visitor.visitOneofLong(z, this.value_, other.value_);
                    } else if (i == 3) {
                        if (this.valueCase_ != 4) {
                            z = false;
                        }
                        this.value_ = visitor.visitOneofString(z, this.value_, other.value_);
                    } else if (i == 4) {
                        if (this.valueCase_ != 5) {
                            z = false;
                        }
                        this.value_ = visitor.visitOneofFloat(z, this.value_, other.value_);
                    } else if (i == 5) {
                        if (this.valueCase_ == 0) {
                            z = false;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = other.valueCase_;
                        if (i2 != 0) {
                            this.valueCase_ = i2;
                        }
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
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                if (Type.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.type_ = rawValue;
                                }
                            } else if (tag == 16) {
                                this.valueCase_ = 2;
                                this.value_ = Integer.valueOf(input.readInt32());
                            } else if (tag == 24) {
                                this.valueCase_ = 3;
                                this.value_ = Long.valueOf(input.readInt64());
                            } else if (tag == 34) {
                                String s = input.readString();
                                this.valueCase_ = 4;
                                this.value_ = s;
                            } else if (tag == 45) {
                                this.valueCase_ = 5;
                                this.value_ = Float.valueOf(input.readFloat());
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
                        synchronized (Elem.class) {
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

        public static Elem getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Elem> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: android.util.BinaryLogEntry$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$util$BinaryLogEntry$Elem$ValueCase = new int[Elem.ValueCase.values().length];

        static {
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$util$BinaryLogEntry$Elem$ValueCase[Elem.ValueCase.VAL_INT32.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$util$BinaryLogEntry$Elem$ValueCase[Elem.ValueCase.VAL_INT64.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$util$BinaryLogEntry$Elem$ValueCase[Elem.ValueCase.VAL_STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$android$util$BinaryLogEntry$Elem$ValueCase[Elem.ValueCase.VAL_FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$android$util$BinaryLogEntry$Elem$ValueCase[Elem.ValueCase.VALUE_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public boolean hasSec() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public long getSec() {
        return this.sec_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSec(long value) {
        this.bitField0_ |= 1;
        this.sec_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSec() {
        this.bitField0_ &= -2;
        this.sec_ = 0;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public boolean hasNanosec() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public long getNanosec() {
        return this.nanosec_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNanosec(long value) {
        this.bitField0_ |= 2;
        this.nanosec_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNanosec() {
        this.bitField0_ &= -3;
        this.nanosec_ = 0;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public boolean hasUid() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public int getUid() {
        return this.uid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUid(int value) {
        this.bitField0_ |= 4;
        this.uid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUid() {
        this.bitField0_ &= -5;
        this.uid_ = 0;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public boolean hasPid() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public int getPid() {
        return this.pid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPid(int value) {
        this.bitField0_ |= 8;
        this.pid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPid() {
        this.bitField0_ &= -9;
        this.pid_ = 0;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public boolean hasTid() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public int getTid() {
        return this.tid_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTid(int value) {
        this.bitField0_ |= 16;
        this.tid_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTid() {
        this.bitField0_ &= -17;
        this.tid_ = 0;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public boolean hasTagIndex() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public int getTagIndex() {
        return this.tagIndex_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagIndex(int value) {
        this.bitField0_ |= 32;
        this.tagIndex_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTagIndex() {
        this.bitField0_ &= -33;
        this.tagIndex_ = 0;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public List<Elem> getElemsList() {
        return this.elems_;
    }

    public List<? extends ElemOrBuilder> getElemsOrBuilderList() {
        return this.elems_;
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public int getElemsCount() {
        return this.elems_.size();
    }

    @Override // android.util.BinaryLogEntryOrBuilder
    public Elem getElems(int index) {
        return this.elems_.get(index);
    }

    public ElemOrBuilder getElemsOrBuilder(int index) {
        return this.elems_.get(index);
    }

    private void ensureElemsIsMutable() {
        if (!this.elems_.isModifiable()) {
            this.elems_ = GeneratedMessageLite.mutableCopy(this.elems_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setElems(int index, Elem value) {
        if (value != null) {
            ensureElemsIsMutable();
            this.elems_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setElems(int index, Elem.Builder builderForValue) {
        ensureElemsIsMutable();
        this.elems_.set(index, (Elem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addElems(Elem value) {
        if (value != null) {
            ensureElemsIsMutable();
            this.elems_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addElems(int index, Elem value) {
        if (value != null) {
            ensureElemsIsMutable();
            this.elems_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addElems(Elem.Builder builderForValue) {
        ensureElemsIsMutable();
        this.elems_.add((Elem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addElems(int index, Elem.Builder builderForValue) {
        ensureElemsIsMutable();
        this.elems_.add(index, (Elem) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllElems(Iterable<? extends Elem> values) {
        ensureElemsIsMutable();
        AbstractMessageLite.addAll(values, this.elems_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearElems() {
        this.elems_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeElems(int index) {
        ensureElemsIsMutable();
        this.elems_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeUInt64(1, this.sec_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeUInt64(2, this.nanosec_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.uid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.pid_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.tid_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeUInt32(6, this.tagIndex_);
        }
        for (int i = 0; i < this.elems_.size(); i++) {
            output.writeMessage(7, this.elems_.get(i));
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
            size2 = 0 + CodedOutputStream.computeUInt64Size(1, this.sec_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeUInt64Size(2, this.nanosec_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.uid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.pid_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.tid_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeUInt32Size(6, this.tagIndex_);
        }
        for (int i = 0; i < this.elems_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(7, this.elems_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BinaryLogEntry parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BinaryLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BinaryLogEntry parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BinaryLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BinaryLogEntry parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BinaryLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BinaryLogEntry parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BinaryLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BinaryLogEntry parseFrom(InputStream input) throws IOException {
        return (BinaryLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BinaryLogEntry parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BinaryLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BinaryLogEntry parseDelimitedFrom(InputStream input) throws IOException {
        return (BinaryLogEntry) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BinaryLogEntry parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BinaryLogEntry) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BinaryLogEntry parseFrom(CodedInputStream input) throws IOException {
        return (BinaryLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BinaryLogEntry parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BinaryLogEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BinaryLogEntry prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BinaryLogEntry, Builder> implements BinaryLogEntryOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(BinaryLogEntry.DEFAULT_INSTANCE);
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public boolean hasSec() {
            return ((BinaryLogEntry) this.instance).hasSec();
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public long getSec() {
            return ((BinaryLogEntry) this.instance).getSec();
        }

        public Builder setSec(long value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).setSec(value);
            return this;
        }

        public Builder clearSec() {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).clearSec();
            return this;
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public boolean hasNanosec() {
            return ((BinaryLogEntry) this.instance).hasNanosec();
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public long getNanosec() {
            return ((BinaryLogEntry) this.instance).getNanosec();
        }

        public Builder setNanosec(long value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).setNanosec(value);
            return this;
        }

        public Builder clearNanosec() {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).clearNanosec();
            return this;
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public boolean hasUid() {
            return ((BinaryLogEntry) this.instance).hasUid();
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public int getUid() {
            return ((BinaryLogEntry) this.instance).getUid();
        }

        public Builder setUid(int value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).setUid(value);
            return this;
        }

        public Builder clearUid() {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).clearUid();
            return this;
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public boolean hasPid() {
            return ((BinaryLogEntry) this.instance).hasPid();
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public int getPid() {
            return ((BinaryLogEntry) this.instance).getPid();
        }

        public Builder setPid(int value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).setPid(value);
            return this;
        }

        public Builder clearPid() {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).clearPid();
            return this;
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public boolean hasTid() {
            return ((BinaryLogEntry) this.instance).hasTid();
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public int getTid() {
            return ((BinaryLogEntry) this.instance).getTid();
        }

        public Builder setTid(int value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).setTid(value);
            return this;
        }

        public Builder clearTid() {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).clearTid();
            return this;
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public boolean hasTagIndex() {
            return ((BinaryLogEntry) this.instance).hasTagIndex();
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public int getTagIndex() {
            return ((BinaryLogEntry) this.instance).getTagIndex();
        }

        public Builder setTagIndex(int value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).setTagIndex(value);
            return this;
        }

        public Builder clearTagIndex() {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).clearTagIndex();
            return this;
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public List<Elem> getElemsList() {
            return Collections.unmodifiableList(((BinaryLogEntry) this.instance).getElemsList());
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public int getElemsCount() {
            return ((BinaryLogEntry) this.instance).getElemsCount();
        }

        @Override // android.util.BinaryLogEntryOrBuilder
        public Elem getElems(int index) {
            return ((BinaryLogEntry) this.instance).getElems(index);
        }

        public Builder setElems(int index, Elem value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).setElems((BinaryLogEntry) index, (int) value);
            return this;
        }

        public Builder setElems(int index, Elem.Builder builderForValue) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).setElems((BinaryLogEntry) index, (int) builderForValue);
            return this;
        }

        public Builder addElems(Elem value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).addElems((BinaryLogEntry) value);
            return this;
        }

        public Builder addElems(int index, Elem value) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).addElems((BinaryLogEntry) index, (int) value);
            return this;
        }

        public Builder addElems(Elem.Builder builderForValue) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).addElems((BinaryLogEntry) builderForValue);
            return this;
        }

        public Builder addElems(int index, Elem.Builder builderForValue) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).addElems((BinaryLogEntry) index, (int) builderForValue);
            return this;
        }

        public Builder addAllElems(Iterable<? extends Elem> values) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).addAllElems(values);
            return this;
        }

        public Builder clearElems() {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).clearElems();
            return this;
        }

        public Builder removeElems(int index) {
            copyOnWrite();
            ((BinaryLogEntry) this.instance).removeElems(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BinaryLogEntry();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.elems_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BinaryLogEntry other = (BinaryLogEntry) arg1;
                this.sec_ = visitor.visitLong(hasSec(), this.sec_, other.hasSec(), other.sec_);
                this.nanosec_ = visitor.visitLong(hasNanosec(), this.nanosec_, other.hasNanosec(), other.nanosec_);
                this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                this.tid_ = visitor.visitInt(hasTid(), this.tid_, other.hasTid(), other.tid_);
                this.tagIndex_ = visitor.visitInt(hasTagIndex(), this.tagIndex_, other.hasTagIndex(), other.tagIndex_);
                this.elems_ = visitor.visitList(this.elems_, other.elems_);
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
                            this.sec_ = input.readUInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.nanosec_ = input.readUInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.uid_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.pid_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.tid_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.tagIndex_ = input.readUInt32();
                        } else if (tag == 58) {
                            if (!this.elems_.isModifiable()) {
                                this.elems_ = GeneratedMessageLite.mutableCopy(this.elems_);
                            }
                            this.elems_.add((Elem) input.readMessage(Elem.parser(), extensionRegistry));
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
                    synchronized (BinaryLogEntry.class) {
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

    public static BinaryLogEntry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BinaryLogEntry> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
