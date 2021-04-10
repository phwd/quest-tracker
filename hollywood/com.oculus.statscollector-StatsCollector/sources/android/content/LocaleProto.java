package android.content;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class LocaleProto extends GeneratedMessageLite<LocaleProto, Builder> implements LocaleProtoOrBuilder {
    public static final int COUNTRY_FIELD_NUMBER = 2;
    private static final LocaleProto DEFAULT_INSTANCE = new LocaleProto();
    public static final int LANGUAGE_FIELD_NUMBER = 1;
    private static volatile Parser<LocaleProto> PARSER = null;
    public static final int SCRIPT_FIELD_NUMBER = 4;
    public static final int VARIANT_FIELD_NUMBER = 3;
    private int bitField0_;
    private String country_ = "";
    private String language_ = "";
    private String script_ = "";
    private String variant_ = "";

    private LocaleProto() {
    }

    @Override // android.content.LocaleProtoOrBuilder
    public boolean hasLanguage() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.LocaleProtoOrBuilder
    public String getLanguage() {
        return this.language_;
    }

    @Override // android.content.LocaleProtoOrBuilder
    public ByteString getLanguageBytes() {
        return ByteString.copyFromUtf8(this.language_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLanguage(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.language_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLanguage() {
        this.bitField0_ &= -2;
        this.language_ = getDefaultInstance().getLanguage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLanguageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.language_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.LocaleProtoOrBuilder
    public boolean hasCountry() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.LocaleProtoOrBuilder
    public String getCountry() {
        return this.country_;
    }

    @Override // android.content.LocaleProtoOrBuilder
    public ByteString getCountryBytes() {
        return ByteString.copyFromUtf8(this.country_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCountry(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.country_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCountry() {
        this.bitField0_ &= -3;
        this.country_ = getDefaultInstance().getCountry();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCountryBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.country_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.LocaleProtoOrBuilder
    public boolean hasVariant() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.LocaleProtoOrBuilder
    public String getVariant() {
        return this.variant_;
    }

    @Override // android.content.LocaleProtoOrBuilder
    public ByteString getVariantBytes() {
        return ByteString.copyFromUtf8(this.variant_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVariant(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.variant_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVariant() {
        this.bitField0_ &= -5;
        this.variant_ = getDefaultInstance().getVariant();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVariantBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.variant_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.LocaleProtoOrBuilder
    public boolean hasScript() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.content.LocaleProtoOrBuilder
    public String getScript() {
        return this.script_;
    }

    @Override // android.content.LocaleProtoOrBuilder
    public ByteString getScriptBytes() {
        return ByteString.copyFromUtf8(this.script_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScript(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.script_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScript() {
        this.bitField0_ &= -9;
        this.script_ = getDefaultInstance().getScript();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScriptBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.script_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getLanguage());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getCountry());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getVariant());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getScript());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getLanguage());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getCountry());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getVariant());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getScript());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static LocaleProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (LocaleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LocaleProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LocaleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LocaleProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (LocaleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LocaleProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LocaleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LocaleProto parseFrom(InputStream input) throws IOException {
        return (LocaleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LocaleProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocaleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LocaleProto parseDelimitedFrom(InputStream input) throws IOException {
        return (LocaleProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static LocaleProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocaleProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LocaleProto parseFrom(CodedInputStream input) throws IOException {
        return (LocaleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LocaleProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LocaleProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LocaleProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LocaleProto, Builder> implements LocaleProtoOrBuilder {
        private Builder() {
            super(LocaleProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.LocaleProtoOrBuilder
        public boolean hasLanguage() {
            return ((LocaleProto) this.instance).hasLanguage();
        }

        @Override // android.content.LocaleProtoOrBuilder
        public String getLanguage() {
            return ((LocaleProto) this.instance).getLanguage();
        }

        @Override // android.content.LocaleProtoOrBuilder
        public ByteString getLanguageBytes() {
            return ((LocaleProto) this.instance).getLanguageBytes();
        }

        public Builder setLanguage(String value) {
            copyOnWrite();
            ((LocaleProto) this.instance).setLanguage(value);
            return this;
        }

        public Builder clearLanguage() {
            copyOnWrite();
            ((LocaleProto) this.instance).clearLanguage();
            return this;
        }

        public Builder setLanguageBytes(ByteString value) {
            copyOnWrite();
            ((LocaleProto) this.instance).setLanguageBytes(value);
            return this;
        }

        @Override // android.content.LocaleProtoOrBuilder
        public boolean hasCountry() {
            return ((LocaleProto) this.instance).hasCountry();
        }

        @Override // android.content.LocaleProtoOrBuilder
        public String getCountry() {
            return ((LocaleProto) this.instance).getCountry();
        }

        @Override // android.content.LocaleProtoOrBuilder
        public ByteString getCountryBytes() {
            return ((LocaleProto) this.instance).getCountryBytes();
        }

        public Builder setCountry(String value) {
            copyOnWrite();
            ((LocaleProto) this.instance).setCountry(value);
            return this;
        }

        public Builder clearCountry() {
            copyOnWrite();
            ((LocaleProto) this.instance).clearCountry();
            return this;
        }

        public Builder setCountryBytes(ByteString value) {
            copyOnWrite();
            ((LocaleProto) this.instance).setCountryBytes(value);
            return this;
        }

        @Override // android.content.LocaleProtoOrBuilder
        public boolean hasVariant() {
            return ((LocaleProto) this.instance).hasVariant();
        }

        @Override // android.content.LocaleProtoOrBuilder
        public String getVariant() {
            return ((LocaleProto) this.instance).getVariant();
        }

        @Override // android.content.LocaleProtoOrBuilder
        public ByteString getVariantBytes() {
            return ((LocaleProto) this.instance).getVariantBytes();
        }

        public Builder setVariant(String value) {
            copyOnWrite();
            ((LocaleProto) this.instance).setVariant(value);
            return this;
        }

        public Builder clearVariant() {
            copyOnWrite();
            ((LocaleProto) this.instance).clearVariant();
            return this;
        }

        public Builder setVariantBytes(ByteString value) {
            copyOnWrite();
            ((LocaleProto) this.instance).setVariantBytes(value);
            return this;
        }

        @Override // android.content.LocaleProtoOrBuilder
        public boolean hasScript() {
            return ((LocaleProto) this.instance).hasScript();
        }

        @Override // android.content.LocaleProtoOrBuilder
        public String getScript() {
            return ((LocaleProto) this.instance).getScript();
        }

        @Override // android.content.LocaleProtoOrBuilder
        public ByteString getScriptBytes() {
            return ((LocaleProto) this.instance).getScriptBytes();
        }

        public Builder setScript(String value) {
            copyOnWrite();
            ((LocaleProto) this.instance).setScript(value);
            return this;
        }

        public Builder clearScript() {
            copyOnWrite();
            ((LocaleProto) this.instance).clearScript();
            return this;
        }

        public Builder setScriptBytes(ByteString value) {
            copyOnWrite();
            ((LocaleProto) this.instance).setScriptBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new LocaleProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                LocaleProto other = (LocaleProto) arg1;
                this.language_ = visitor.visitString(hasLanguage(), this.language_, other.hasLanguage(), other.language_);
                this.country_ = visitor.visitString(hasCountry(), this.country_, other.hasCountry(), other.country_);
                this.variant_ = visitor.visitString(hasVariant(), this.variant_, other.hasVariant(), other.variant_);
                this.script_ = visitor.visitString(hasScript(), this.script_, other.hasScript(), other.script_);
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
                            this.language_ = s;
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.country_ = s2;
                        } else if (tag == 26) {
                            String s3 = input.readString();
                            this.bitField0_ |= 4;
                            this.variant_ = s3;
                        } else if (tag == 34) {
                            String s4 = input.readString();
                            this.bitField0_ |= 8;
                            this.script_ = s4;
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
                    synchronized (LocaleProto.class) {
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

    public static LocaleProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LocaleProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
