package android.content.pm;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PackageItemInfoProto extends GeneratedMessageLite<PackageItemInfoProto, Builder> implements PackageItemInfoProtoOrBuilder {
    public static final int BANNER_FIELD_NUMBER = 6;
    private static final PackageItemInfoProto DEFAULT_INSTANCE = new PackageItemInfoProto();
    public static final int ICON_FIELD_NUMBER = 5;
    public static final int LABEL_RES_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int NON_LOCALIZED_LABEL_FIELD_NUMBER = 4;
    public static final int PACKAGE_NAME_FIELD_NUMBER = 2;
    private static volatile Parser<PackageItemInfoProto> PARSER;
    private int banner_ = 0;
    private int bitField0_;
    private int icon_ = 0;
    private int labelRes_ = 0;
    private String name_ = "";
    private String nonLocalizedLabel_ = "";
    private String packageName_ = "";

    private PackageItemInfoProto() {
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearName() {
        this.bitField0_ &= -2;
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.name_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public boolean hasPackageName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public String getPackageName() {
        return this.packageName_;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public ByteString getPackageNameBytes() {
        return ByteString.copyFromUtf8(this.packageName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.packageName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageName() {
        this.bitField0_ &= -3;
        this.packageName_ = getDefaultInstance().getPackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.packageName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public boolean hasLabelRes() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public int getLabelRes() {
        return this.labelRes_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabelRes(int value) {
        this.bitField0_ |= 4;
        this.labelRes_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLabelRes() {
        this.bitField0_ &= -5;
        this.labelRes_ = 0;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public boolean hasNonLocalizedLabel() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public String getNonLocalizedLabel() {
        return this.nonLocalizedLabel_;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public ByteString getNonLocalizedLabelBytes() {
        return ByteString.copyFromUtf8(this.nonLocalizedLabel_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNonLocalizedLabel(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.nonLocalizedLabel_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNonLocalizedLabel() {
        this.bitField0_ &= -9;
        this.nonLocalizedLabel_ = getDefaultInstance().getNonLocalizedLabel();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNonLocalizedLabelBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.nonLocalizedLabel_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public boolean hasIcon() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public int getIcon() {
        return this.icon_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIcon(int value) {
        this.bitField0_ |= 16;
        this.icon_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIcon() {
        this.bitField0_ &= -17;
        this.icon_ = 0;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public boolean hasBanner() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.content.pm.PackageItemInfoProtoOrBuilder
    public int getBanner() {
        return this.banner_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBanner(int value) {
        this.bitField0_ |= 32;
        this.banner_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBanner() {
        this.bitField0_ &= -33;
        this.banner_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getPackageName());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.labelRes_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getNonLocalizedLabel());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.icon_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt32(6, this.banner_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getPackageName());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.labelRes_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getNonLocalizedLabel());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.icon_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt32Size(6, this.banner_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PackageItemInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PackageItemInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageItemInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageItemInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageItemInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PackageItemInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageItemInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageItemInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageItemInfoProto parseFrom(InputStream input) throws IOException {
        return (PackageItemInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageItemInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageItemInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageItemInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PackageItemInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageItemInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageItemInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageItemInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (PackageItemInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageItemInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageItemInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PackageItemInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PackageItemInfoProto, Builder> implements PackageItemInfoProtoOrBuilder {
        private Builder() {
            super(PackageItemInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public boolean hasName() {
            return ((PackageItemInfoProto) this.instance).hasName();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public String getName() {
            return ((PackageItemInfoProto) this.instance).getName();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public ByteString getNameBytes() {
            return ((PackageItemInfoProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public boolean hasPackageName() {
            return ((PackageItemInfoProto) this.instance).hasPackageName();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public String getPackageName() {
            return ((PackageItemInfoProto) this.instance).getPackageName();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ((PackageItemInfoProto) this.instance).getPackageNameBytes();
        }

        public Builder setPackageName(String value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setPackageName(value);
            return this;
        }

        public Builder clearPackageName() {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).clearPackageName();
            return this;
        }

        public Builder setPackageNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setPackageNameBytes(value);
            return this;
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public boolean hasLabelRes() {
            return ((PackageItemInfoProto) this.instance).hasLabelRes();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public int getLabelRes() {
            return ((PackageItemInfoProto) this.instance).getLabelRes();
        }

        public Builder setLabelRes(int value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setLabelRes(value);
            return this;
        }

        public Builder clearLabelRes() {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).clearLabelRes();
            return this;
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public boolean hasNonLocalizedLabel() {
            return ((PackageItemInfoProto) this.instance).hasNonLocalizedLabel();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public String getNonLocalizedLabel() {
            return ((PackageItemInfoProto) this.instance).getNonLocalizedLabel();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public ByteString getNonLocalizedLabelBytes() {
            return ((PackageItemInfoProto) this.instance).getNonLocalizedLabelBytes();
        }

        public Builder setNonLocalizedLabel(String value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setNonLocalizedLabel(value);
            return this;
        }

        public Builder clearNonLocalizedLabel() {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).clearNonLocalizedLabel();
            return this;
        }

        public Builder setNonLocalizedLabelBytes(ByteString value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setNonLocalizedLabelBytes(value);
            return this;
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public boolean hasIcon() {
            return ((PackageItemInfoProto) this.instance).hasIcon();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public int getIcon() {
            return ((PackageItemInfoProto) this.instance).getIcon();
        }

        public Builder setIcon(int value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setIcon(value);
            return this;
        }

        public Builder clearIcon() {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).clearIcon();
            return this;
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public boolean hasBanner() {
            return ((PackageItemInfoProto) this.instance).hasBanner();
        }

        @Override // android.content.pm.PackageItemInfoProtoOrBuilder
        public int getBanner() {
            return ((PackageItemInfoProto) this.instance).getBanner();
        }

        public Builder setBanner(int value) {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).setBanner(value);
            return this;
        }

        public Builder clearBanner() {
            copyOnWrite();
            ((PackageItemInfoProto) this.instance).clearBanner();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PackageItemInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PackageItemInfoProto other = (PackageItemInfoProto) arg1;
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                this.labelRes_ = visitor.visitInt(hasLabelRes(), this.labelRes_, other.hasLabelRes(), other.labelRes_);
                this.nonLocalizedLabel_ = visitor.visitString(hasNonLocalizedLabel(), this.nonLocalizedLabel_, other.hasNonLocalizedLabel(), other.nonLocalizedLabel_);
                this.icon_ = visitor.visitInt(hasIcon(), this.icon_, other.hasIcon(), other.icon_);
                this.banner_ = visitor.visitInt(hasBanner(), this.banner_, other.hasBanner(), other.banner_);
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
                            this.name_ = s;
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.packageName_ = s2;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.labelRes_ = input.readInt32();
                        } else if (tag == 34) {
                            String s3 = input.readString();
                            this.bitField0_ |= 8;
                            this.nonLocalizedLabel_ = s3;
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.icon_ = input.readInt32();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.banner_ = input.readInt32();
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
                    synchronized (PackageItemInfoProto.class) {
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

    public static PackageItemInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PackageItemInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
