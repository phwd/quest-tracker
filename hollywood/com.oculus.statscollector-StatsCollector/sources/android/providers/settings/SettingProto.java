package android.providers.settings;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class SettingProto extends GeneratedMessageLite<SettingProto, Builder> implements SettingProtoOrBuilder {
    public static final int DEFAULT_FROM_SYSTEM_FIELD_NUMBER = 6;
    private static final SettingProto DEFAULT_INSTANCE = new SettingProto();
    public static final int DEFAULT_VALUE_FIELD_NUMBER = 5;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser<SettingProto> PARSER = null;
    public static final int PKG_FIELD_NUMBER = 3;
    public static final int VALUE_FIELD_NUMBER = 4;
    private int bitField0_;
    private boolean defaultFromSystem_ = false;
    private String defaultValue_ = "";
    private String id_ = "";
    private String name_ = "";
    private String pkg_ = "";
    private String value_ = "";

    private SettingProto() {
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public boolean hasId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setId(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.id_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearId() {
        this.bitField0_ &= -2;
        this.id_ = getDefaultInstance().getId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.id_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -3;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public boolean hasPkg() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public String getPkg() {
        return this.pkg_;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public ByteString getPkgBytes() {
        return ByteString.copyFromUtf8(this.pkg_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPkg(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.pkg_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPkg() {
        this.bitField0_ &= -5;
        this.pkg_ = getDefaultInstance().getPkg();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPkgBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.pkg_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public boolean hasValue() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public String getValue() {
        return this.value_;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public ByteString getValueBytes() {
        return ByteString.copyFromUtf8(this.value_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setValue(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.value_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearValue() {
        this.bitField0_ &= -9;
        this.value_ = getDefaultInstance().getValue();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setValueBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.value_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public boolean hasDefaultValue() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public String getDefaultValue() {
        return this.defaultValue_;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public ByteString getDefaultValueBytes() {
        return ByteString.copyFromUtf8(this.defaultValue_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultValue(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.defaultValue_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDefaultValue() {
        this.bitField0_ &= -17;
        this.defaultValue_ = getDefaultInstance().getDefaultValue();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultValueBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.defaultValue_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public boolean hasDefaultFromSystem() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.providers.settings.SettingProtoOrBuilder
    public boolean getDefaultFromSystem() {
        return this.defaultFromSystem_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDefaultFromSystem(boolean value) {
        this.bitField0_ |= 32;
        this.defaultFromSystem_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDefaultFromSystem() {
        this.bitField0_ &= -33;
        this.defaultFromSystem_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getPkg());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getValue());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getDefaultValue());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.defaultFromSystem_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getId());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getName());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getPkg());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getValue());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getDefaultValue());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.defaultFromSystem_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static SettingProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SettingProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SettingProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SettingProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SettingProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SettingProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SettingProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SettingProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SettingProto parseFrom(InputStream input) throws IOException {
        return (SettingProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SettingProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SettingProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SettingProto parseFrom(CodedInputStream input) throws IOException {
        return (SettingProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SettingProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SettingProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SettingProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SettingProto, Builder> implements SettingProtoOrBuilder {
        private Builder() {
            super(SettingProto.DEFAULT_INSTANCE);
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public boolean hasId() {
            return ((SettingProto) this.instance).hasId();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public String getId() {
            return ((SettingProto) this.instance).getId();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public ByteString getIdBytes() {
            return ((SettingProto) this.instance).getIdBytes();
        }

        public Builder setId(String value) {
            copyOnWrite();
            ((SettingProto) this.instance).setId(value);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((SettingProto) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString value) {
            copyOnWrite();
            ((SettingProto) this.instance).setIdBytes(value);
            return this;
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public boolean hasName() {
            return ((SettingProto) this.instance).hasName();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public String getName() {
            return ((SettingProto) this.instance).getName();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public ByteString getNameBytes() {
            return ((SettingProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((SettingProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((SettingProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((SettingProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public boolean hasPkg() {
            return ((SettingProto) this.instance).hasPkg();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public String getPkg() {
            return ((SettingProto) this.instance).getPkg();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public ByteString getPkgBytes() {
            return ((SettingProto) this.instance).getPkgBytes();
        }

        public Builder setPkg(String value) {
            copyOnWrite();
            ((SettingProto) this.instance).setPkg(value);
            return this;
        }

        public Builder clearPkg() {
            copyOnWrite();
            ((SettingProto) this.instance).clearPkg();
            return this;
        }

        public Builder setPkgBytes(ByteString value) {
            copyOnWrite();
            ((SettingProto) this.instance).setPkgBytes(value);
            return this;
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public boolean hasValue() {
            return ((SettingProto) this.instance).hasValue();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public String getValue() {
            return ((SettingProto) this.instance).getValue();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public ByteString getValueBytes() {
            return ((SettingProto) this.instance).getValueBytes();
        }

        public Builder setValue(String value) {
            copyOnWrite();
            ((SettingProto) this.instance).setValue(value);
            return this;
        }

        public Builder clearValue() {
            copyOnWrite();
            ((SettingProto) this.instance).clearValue();
            return this;
        }

        public Builder setValueBytes(ByteString value) {
            copyOnWrite();
            ((SettingProto) this.instance).setValueBytes(value);
            return this;
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public boolean hasDefaultValue() {
            return ((SettingProto) this.instance).hasDefaultValue();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public String getDefaultValue() {
            return ((SettingProto) this.instance).getDefaultValue();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public ByteString getDefaultValueBytes() {
            return ((SettingProto) this.instance).getDefaultValueBytes();
        }

        public Builder setDefaultValue(String value) {
            copyOnWrite();
            ((SettingProto) this.instance).setDefaultValue(value);
            return this;
        }

        public Builder clearDefaultValue() {
            copyOnWrite();
            ((SettingProto) this.instance).clearDefaultValue();
            return this;
        }

        public Builder setDefaultValueBytes(ByteString value) {
            copyOnWrite();
            ((SettingProto) this.instance).setDefaultValueBytes(value);
            return this;
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public boolean hasDefaultFromSystem() {
            return ((SettingProto) this.instance).hasDefaultFromSystem();
        }

        @Override // android.providers.settings.SettingProtoOrBuilder
        public boolean getDefaultFromSystem() {
            return ((SettingProto) this.instance).getDefaultFromSystem();
        }

        public Builder setDefaultFromSystem(boolean value) {
            copyOnWrite();
            ((SettingProto) this.instance).setDefaultFromSystem(value);
            return this;
        }

        public Builder clearDefaultFromSystem() {
            copyOnWrite();
            ((SettingProto) this.instance).clearDefaultFromSystem();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SettingProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SettingProto other = (SettingProto) arg1;
                this.id_ = visitor.visitString(hasId(), this.id_, other.hasId(), other.id_);
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.pkg_ = visitor.visitString(hasPkg(), this.pkg_, other.hasPkg(), other.pkg_);
                this.value_ = visitor.visitString(hasValue(), this.value_, other.hasValue(), other.value_);
                this.defaultValue_ = visitor.visitString(hasDefaultValue(), this.defaultValue_, other.hasDefaultValue(), other.defaultValue_);
                this.defaultFromSystem_ = visitor.visitBoolean(hasDefaultFromSystem(), this.defaultFromSystem_, other.hasDefaultFromSystem(), other.defaultFromSystem_);
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
                            this.id_ = s;
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.name_ = s2;
                        } else if (tag == 26) {
                            String s3 = input.readString();
                            this.bitField0_ |= 4;
                            this.pkg_ = s3;
                        } else if (tag == 34) {
                            String s4 = input.readString();
                            this.bitField0_ |= 8;
                            this.value_ = s4;
                        } else if (tag == 42) {
                            String s5 = input.readString();
                            this.bitField0_ |= 16;
                            this.defaultValue_ = s5;
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.defaultFromSystem_ = input.readBool();
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
                    synchronized (SettingProto.class) {
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

    public static SettingProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SettingProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
