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

public final class ComponentNameProto extends GeneratedMessageLite<ComponentNameProto, Builder> implements ComponentNameProtoOrBuilder {
    public static final int CLASS_NAME_FIELD_NUMBER = 2;
    private static final ComponentNameProto DEFAULT_INSTANCE = new ComponentNameProto();
    public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
    private static volatile Parser<ComponentNameProto> PARSER;
    private int bitField0_;
    private String className_ = "";
    private String packageName_ = "";

    private ComponentNameProto() {
    }

    @Override // android.content.ComponentNameProtoOrBuilder
    public boolean hasPackageName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.ComponentNameProtoOrBuilder
    public String getPackageName() {
        return this.packageName_;
    }

    @Override // android.content.ComponentNameProtoOrBuilder
    public ByteString getPackageNameBytes() {
        return ByteString.copyFromUtf8(this.packageName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.packageName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageName() {
        this.bitField0_ &= -2;
        this.packageName_ = getDefaultInstance().getPackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.packageName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.ComponentNameProtoOrBuilder
    public boolean hasClassName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.ComponentNameProtoOrBuilder
    public String getClassName() {
        return this.className_;
    }

    @Override // android.content.ComponentNameProtoOrBuilder
    public ByteString getClassNameBytes() {
        return ByteString.copyFromUtf8(this.className_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClassName(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.className_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearClassName() {
        this.bitField0_ &= -3;
        this.className_ = getDefaultInstance().getClassName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setClassNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.className_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getPackageName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getClassName());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getPackageName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getClassName());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ComponentNameProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ComponentNameProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ComponentNameProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ComponentNameProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ComponentNameProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ComponentNameProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ComponentNameProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ComponentNameProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ComponentNameProto parseFrom(InputStream input) throws IOException {
        return (ComponentNameProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ComponentNameProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ComponentNameProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ComponentNameProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ComponentNameProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ComponentNameProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ComponentNameProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ComponentNameProto parseFrom(CodedInputStream input) throws IOException {
        return (ComponentNameProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ComponentNameProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ComponentNameProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ComponentNameProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ComponentNameProto, Builder> implements ComponentNameProtoOrBuilder {
        private Builder() {
            super(ComponentNameProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.ComponentNameProtoOrBuilder
        public boolean hasPackageName() {
            return ((ComponentNameProto) this.instance).hasPackageName();
        }

        @Override // android.content.ComponentNameProtoOrBuilder
        public String getPackageName() {
            return ((ComponentNameProto) this.instance).getPackageName();
        }

        @Override // android.content.ComponentNameProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ((ComponentNameProto) this.instance).getPackageNameBytes();
        }

        public Builder setPackageName(String value) {
            copyOnWrite();
            ((ComponentNameProto) this.instance).setPackageName(value);
            return this;
        }

        public Builder clearPackageName() {
            copyOnWrite();
            ((ComponentNameProto) this.instance).clearPackageName();
            return this;
        }

        public Builder setPackageNameBytes(ByteString value) {
            copyOnWrite();
            ((ComponentNameProto) this.instance).setPackageNameBytes(value);
            return this;
        }

        @Override // android.content.ComponentNameProtoOrBuilder
        public boolean hasClassName() {
            return ((ComponentNameProto) this.instance).hasClassName();
        }

        @Override // android.content.ComponentNameProtoOrBuilder
        public String getClassName() {
            return ((ComponentNameProto) this.instance).getClassName();
        }

        @Override // android.content.ComponentNameProtoOrBuilder
        public ByteString getClassNameBytes() {
            return ((ComponentNameProto) this.instance).getClassNameBytes();
        }

        public Builder setClassName(String value) {
            copyOnWrite();
            ((ComponentNameProto) this.instance).setClassName(value);
            return this;
        }

        public Builder clearClassName() {
            copyOnWrite();
            ((ComponentNameProto) this.instance).clearClassName();
            return this;
        }

        public Builder setClassNameBytes(ByteString value) {
            copyOnWrite();
            ((ComponentNameProto) this.instance).setClassNameBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ComponentNameProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ComponentNameProto other = (ComponentNameProto) arg1;
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                this.className_ = visitor.visitString(hasClassName(), this.className_, other.hasClassName(), other.className_);
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
                            this.packageName_ = s;
                        } else if (tag == 18) {
                            String s2 = input.readString();
                            this.bitField0_ |= 2;
                            this.className_ = s2;
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
                    synchronized (ComponentNameProto.class) {
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

    public static ComponentNameProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ComponentNameProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
