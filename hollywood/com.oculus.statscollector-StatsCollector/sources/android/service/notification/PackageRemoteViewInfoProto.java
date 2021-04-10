package android.service.notification;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PackageRemoteViewInfoProto extends GeneratedMessageLite<PackageRemoteViewInfoProto, Builder> implements PackageRemoteViewInfoProtoOrBuilder {
    private static final PackageRemoteViewInfoProto DEFAULT_INSTANCE = new PackageRemoteViewInfoProto();
    public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
    private static volatile Parser<PackageRemoteViewInfoProto> PARSER;
    private int bitField0_;
    private String packageName_ = "";

    private PackageRemoteViewInfoProto() {
    }

    @Override // android.service.notification.PackageRemoteViewInfoProtoOrBuilder
    public boolean hasPackageName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.PackageRemoteViewInfoProtoOrBuilder
    public String getPackageName() {
        return this.packageName_;
    }

    @Override // android.service.notification.PackageRemoteViewInfoProtoOrBuilder
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

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getPackageName());
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
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PackageRemoteViewInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PackageRemoteViewInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageRemoteViewInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageRemoteViewInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageRemoteViewInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PackageRemoteViewInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageRemoteViewInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageRemoteViewInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageRemoteViewInfoProto parseFrom(InputStream input) throws IOException {
        return (PackageRemoteViewInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageRemoteViewInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageRemoteViewInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageRemoteViewInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PackageRemoteViewInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageRemoteViewInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageRemoteViewInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageRemoteViewInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (PackageRemoteViewInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageRemoteViewInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageRemoteViewInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PackageRemoteViewInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PackageRemoteViewInfoProto, Builder> implements PackageRemoteViewInfoProtoOrBuilder {
        private Builder() {
            super(PackageRemoteViewInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.PackageRemoteViewInfoProtoOrBuilder
        public boolean hasPackageName() {
            return ((PackageRemoteViewInfoProto) this.instance).hasPackageName();
        }

        @Override // android.service.notification.PackageRemoteViewInfoProtoOrBuilder
        public String getPackageName() {
            return ((PackageRemoteViewInfoProto) this.instance).getPackageName();
        }

        @Override // android.service.notification.PackageRemoteViewInfoProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ((PackageRemoteViewInfoProto) this.instance).getPackageNameBytes();
        }

        public Builder setPackageName(String value) {
            copyOnWrite();
            ((PackageRemoteViewInfoProto) this.instance).setPackageName(value);
            return this;
        }

        public Builder clearPackageName() {
            copyOnWrite();
            ((PackageRemoteViewInfoProto) this.instance).clearPackageName();
            return this;
        }

        public Builder setPackageNameBytes(ByteString value) {
            copyOnWrite();
            ((PackageRemoteViewInfoProto) this.instance).setPackageNameBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PackageRemoteViewInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PackageRemoteViewInfoProto other = (PackageRemoteViewInfoProto) arg1;
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
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
                    synchronized (PackageRemoteViewInfoProto.class) {
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

    public static PackageRemoteViewInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PackageRemoteViewInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
