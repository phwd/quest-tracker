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

public final class BundleProto extends GeneratedMessageLite<BundleProto, Builder> implements BundleProtoOrBuilder {
    private static final BundleProto DEFAULT_INSTANCE = new BundleProto();
    public static final int MAP_DATA_FIELD_NUMBER = 2;
    public static final int PARCELLED_DATA_SIZE_FIELD_NUMBER = 1;
    private static volatile Parser<BundleProto> PARSER;
    private int bitField0_;
    private int dataCase_ = 0;
    private Object data_;

    private BundleProto() {
    }

    public enum DataCase implements Internal.EnumLite {
        PARCELLED_DATA_SIZE(1),
        MAP_DATA(2),
        DATA_NOT_SET(0);
        
        private final int value;

        private DataCase(int value2) {
            this.value = value2;
        }

        @Deprecated
        public static DataCase valueOf(int value2) {
            return forNumber(value2);
        }

        public static DataCase forNumber(int value2) {
            if (value2 == 0) {
                return DATA_NOT_SET;
            }
            if (value2 == 1) {
                return PARCELLED_DATA_SIZE;
            }
            if (value2 != 2) {
                return null;
            }
            return MAP_DATA;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    @Override // android.os.BundleProtoOrBuilder
    public DataCase getDataCase() {
        return DataCase.forNumber(this.dataCase_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearData() {
        this.dataCase_ = 0;
        this.data_ = null;
    }

    @Override // android.os.BundleProtoOrBuilder
    public boolean hasParcelledDataSize() {
        return this.dataCase_ == 1;
    }

    @Override // android.os.BundleProtoOrBuilder
    public int getParcelledDataSize() {
        if (this.dataCase_ == 1) {
            return ((Integer) this.data_).intValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParcelledDataSize(int value) {
        this.dataCase_ = 1;
        this.data_ = Integer.valueOf(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearParcelledDataSize() {
        if (this.dataCase_ == 1) {
            this.dataCase_ = 0;
            this.data_ = null;
        }
    }

    @Override // android.os.BundleProtoOrBuilder
    public boolean hasMapData() {
        return this.dataCase_ == 2;
    }

    @Override // android.os.BundleProtoOrBuilder
    public String getMapData() {
        if (this.dataCase_ == 2) {
            return (String) this.data_;
        }
        return "";
    }

    @Override // android.os.BundleProtoOrBuilder
    public ByteString getMapDataBytes() {
        String ref = "";
        if (this.dataCase_ == 2) {
            ref = (String) this.data_;
        }
        return ByteString.copyFromUtf8(ref);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMapData(String value) {
        if (value != null) {
            this.dataCase_ = 2;
            this.data_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMapData() {
        if (this.dataCase_ == 2) {
            this.dataCase_ = 0;
            this.data_ = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMapDataBytes(ByteString value) {
        if (value != null) {
            this.dataCase_ = 2;
            this.data_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if (this.dataCase_ == 1) {
            output.writeInt32(1, ((Integer) this.data_).intValue());
        }
        if (this.dataCase_ == 2) {
            output.writeString(2, getMapData());
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
        if (this.dataCase_ == 1) {
            size2 = 0 + CodedOutputStream.computeInt32Size(1, ((Integer) this.data_).intValue());
        }
        if (this.dataCase_ == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getMapData());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BundleProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BundleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundleProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundleProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BundleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BundleProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BundleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BundleProto parseFrom(InputStream input) throws IOException {
        return (BundleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundleProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BundleProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BundleProto parseFrom(CodedInputStream input) throws IOException {
        return (BundleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BundleProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BundleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BundleProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundleProto, Builder> implements BundleProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(BundleProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.BundleProtoOrBuilder
        public DataCase getDataCase() {
            return ((BundleProto) this.instance).getDataCase();
        }

        public Builder clearData() {
            copyOnWrite();
            ((BundleProto) this.instance).clearData();
            return this;
        }

        @Override // android.os.BundleProtoOrBuilder
        public boolean hasParcelledDataSize() {
            return ((BundleProto) this.instance).hasParcelledDataSize();
        }

        @Override // android.os.BundleProtoOrBuilder
        public int getParcelledDataSize() {
            return ((BundleProto) this.instance).getParcelledDataSize();
        }

        public Builder setParcelledDataSize(int value) {
            copyOnWrite();
            ((BundleProto) this.instance).setParcelledDataSize(value);
            return this;
        }

        public Builder clearParcelledDataSize() {
            copyOnWrite();
            ((BundleProto) this.instance).clearParcelledDataSize();
            return this;
        }

        @Override // android.os.BundleProtoOrBuilder
        public boolean hasMapData() {
            return ((BundleProto) this.instance).hasMapData();
        }

        @Override // android.os.BundleProtoOrBuilder
        public String getMapData() {
            return ((BundleProto) this.instance).getMapData();
        }

        @Override // android.os.BundleProtoOrBuilder
        public ByteString getMapDataBytes() {
            return ((BundleProto) this.instance).getMapDataBytes();
        }

        public Builder setMapData(String value) {
            copyOnWrite();
            ((BundleProto) this.instance).setMapData(value);
            return this;
        }

        public Builder clearMapData() {
            copyOnWrite();
            ((BundleProto) this.instance).clearMapData();
            return this;
        }

        public Builder setMapDataBytes(ByteString value) {
            copyOnWrite();
            ((BundleProto) this.instance).setMapDataBytes(value);
            return this;
        }
    }

    /* renamed from: android.os.BundleProto$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$os$BundleProto$DataCase = new int[DataCase.values().length];

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
                $SwitchMap$android$os$BundleProto$DataCase[DataCase.PARCELLED_DATA_SIZE.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$os$BundleProto$DataCase[DataCase.MAP_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$os$BundleProto$DataCase[DataCase.DATA_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        boolean z = true;
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BundleProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BundleProto other = (BundleProto) arg1;
                int i = AnonymousClass1.$SwitchMap$android$os$BundleProto$DataCase[other.getDataCase().ordinal()];
                if (i == 1) {
                    if (this.dataCase_ != 1) {
                        z = false;
                    }
                    this.data_ = visitor.visitOneofInt(z, this.data_, other.data_);
                } else if (i == 2) {
                    if (this.dataCase_ != 2) {
                        z = false;
                    }
                    this.data_ = visitor.visitOneofString(z, this.data_, other.data_);
                } else if (i == 3) {
                    if (this.dataCase_ == 0) {
                        z = false;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    int i2 = other.dataCase_;
                    if (i2 != 0) {
                        this.dataCase_ = i2;
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
                            this.dataCase_ = 1;
                            this.data_ = Integer.valueOf(input.readInt32());
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.dataCase_ = 2;
                            this.data_ = s;
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
                    synchronized (BundleProto.class) {
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

    public static BundleProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundleProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
