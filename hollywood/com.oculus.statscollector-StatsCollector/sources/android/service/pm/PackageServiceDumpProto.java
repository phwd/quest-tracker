package android.service.pm;

import android.content.pm.FeatureInfoProto;
import android.content.pm.FeatureInfoProtoOrBuilder;
import android.service.pm.PackageProto;
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

public final class PackageServiceDumpProto extends GeneratedMessageLite<PackageServiceDumpProto, Builder> implements PackageServiceDumpProtoOrBuilder {
    private static final PackageServiceDumpProto DEFAULT_INSTANCE = new PackageServiceDumpProto();
    public static final int FEATURES_FIELD_NUMBER = 4;
    public static final int MESSAGES_FIELD_NUMBER = 7;
    public static final int PACKAGES_FIELD_NUMBER = 5;
    private static volatile Parser<PackageServiceDumpProto> PARSER = null;
    public static final int REQUIRED_VERIFIER_PACKAGE_FIELD_NUMBER = 1;
    public static final int SHARED_LIBRARIES_FIELD_NUMBER = 3;
    public static final int SHARED_USERS_FIELD_NUMBER = 6;
    public static final int VERIFIER_PACKAGE_FIELD_NUMBER = 2;
    private int bitField0_;
    private Internal.ProtobufList<FeatureInfoProto> features_ = emptyProtobufList();
    private Internal.ProtobufList<String> messages_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<PackageProto> packages_ = emptyProtobufList();
    private PackageShortProto requiredVerifierPackage_;
    private Internal.ProtobufList<SharedLibraryProto> sharedLibraries_ = emptyProtobufList();
    private Internal.ProtobufList<SharedUserProto> sharedUsers_ = emptyProtobufList();
    private PackageShortProto verifierPackage_;

    public interface PackageShortProtoOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        int getUid();

        boolean hasName();

        boolean hasUid();
    }

    public interface SharedLibraryProtoOrBuilder extends MessageLiteOrBuilder {
        String getApk();

        ByteString getApkBytes();

        boolean getIsJar();

        String getName();

        ByteString getNameBytes();

        String getPath();

        ByteString getPathBytes();

        boolean hasApk();

        boolean hasIsJar();

        boolean hasName();

        boolean hasPath();
    }

    public interface SharedUserProtoOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        int getUid();

        boolean hasName();

        boolean hasUid();
    }

    private PackageServiceDumpProto() {
    }

    public static final class PackageShortProto extends GeneratedMessageLite<PackageShortProto, Builder> implements PackageShortProtoOrBuilder {
        private static final PackageShortProto DEFAULT_INSTANCE = new PackageShortProto();
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<PackageShortProto> PARSER = null;
        public static final int UID_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private int uid_ = 0;

        private PackageShortProto() {
        }

        @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
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

        @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 2;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -3;
            this.uid_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.uid_);
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
                size2 += CodedOutputStream.computeInt32Size(2, this.uid_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PackageShortProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PackageShortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageShortProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageShortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageShortProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PackageShortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageShortProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageShortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageShortProto parseFrom(InputStream input) throws IOException {
            return (PackageShortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageShortProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageShortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PackageShortProto parseDelimitedFrom(InputStream input) throws IOException {
            return (PackageShortProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageShortProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageShortProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PackageShortProto parseFrom(CodedInputStream input) throws IOException {
            return (PackageShortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageShortProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageShortProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PackageShortProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PackageShortProto, Builder> implements PackageShortProtoOrBuilder {
            private Builder() {
                super(PackageShortProto.DEFAULT_INSTANCE);
            }

            @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
            public boolean hasName() {
                return ((PackageShortProto) this.instance).hasName();
            }

            @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
            public String getName() {
                return ((PackageShortProto) this.instance).getName();
            }

            @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
            public ByteString getNameBytes() {
                return ((PackageShortProto) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((PackageShortProto) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((PackageShortProto) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((PackageShortProto) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
            public boolean hasUid() {
                return ((PackageShortProto) this.instance).hasUid();
            }

            @Override // android.service.pm.PackageServiceDumpProto.PackageShortProtoOrBuilder
            public int getUid() {
                return ((PackageShortProto) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((PackageShortProto) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((PackageShortProto) this.instance).clearUid();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PackageShortProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PackageShortProto other = (PackageShortProto) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
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
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.uid_ = input.readInt32();
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
                        synchronized (PackageShortProto.class) {
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

        public static PackageShortProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PackageShortProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class SharedLibraryProto extends GeneratedMessageLite<SharedLibraryProto, Builder> implements SharedLibraryProtoOrBuilder {
        public static final int APK_FIELD_NUMBER = 4;
        private static final SharedLibraryProto DEFAULT_INSTANCE = new SharedLibraryProto();
        public static final int IS_JAR_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<SharedLibraryProto> PARSER = null;
        public static final int PATH_FIELD_NUMBER = 3;
        private String apk_ = "";
        private int bitField0_;
        private boolean isJar_ = false;
        private String name_ = "";
        private String path_ = "";

        private SharedLibraryProto() {
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
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

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public boolean hasIsJar() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public boolean getIsJar() {
            return this.isJar_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsJar(boolean value) {
            this.bitField0_ |= 2;
            this.isJar_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsJar() {
            this.bitField0_ &= -3;
            this.isJar_ = false;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public boolean hasPath() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public String getPath() {
            return this.path_;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public ByteString getPathBytes() {
            return ByteString.copyFromUtf8(this.path_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPath(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.path_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPath() {
            this.bitField0_ &= -5;
            this.path_ = getDefaultInstance().getPath();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPathBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.path_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public boolean hasApk() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public String getApk() {
            return this.apk_;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
        public ByteString getApkBytes() {
            return ByteString.copyFromUtf8(this.apk_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setApk(String value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.apk_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearApk() {
            this.bitField0_ &= -9;
            this.apk_ = getDefaultInstance().getApk();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setApkBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.apk_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isJar_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getPath());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeString(4, getApk());
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
                size2 += CodedOutputStream.computeBoolSize(2, this.isJar_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getPath());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeStringSize(4, getApk());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static SharedLibraryProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SharedLibraryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SharedLibraryProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SharedLibraryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SharedLibraryProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SharedLibraryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SharedLibraryProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SharedLibraryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SharedLibraryProto parseFrom(InputStream input) throws IOException {
            return (SharedLibraryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SharedLibraryProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SharedLibraryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SharedLibraryProto parseDelimitedFrom(InputStream input) throws IOException {
            return (SharedLibraryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SharedLibraryProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SharedLibraryProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SharedLibraryProto parseFrom(CodedInputStream input) throws IOException {
            return (SharedLibraryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SharedLibraryProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SharedLibraryProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SharedLibraryProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SharedLibraryProto, Builder> implements SharedLibraryProtoOrBuilder {
            private Builder() {
                super(SharedLibraryProto.DEFAULT_INSTANCE);
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public boolean hasName() {
                return ((SharedLibraryProto) this.instance).hasName();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public String getName() {
                return ((SharedLibraryProto) this.instance).getName();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public ByteString getNameBytes() {
                return ((SharedLibraryProto) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public boolean hasIsJar() {
                return ((SharedLibraryProto) this.instance).hasIsJar();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public boolean getIsJar() {
                return ((SharedLibraryProto) this.instance).getIsJar();
            }

            public Builder setIsJar(boolean value) {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).setIsJar(value);
                return this;
            }

            public Builder clearIsJar() {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).clearIsJar();
                return this;
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public boolean hasPath() {
                return ((SharedLibraryProto) this.instance).hasPath();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public String getPath() {
                return ((SharedLibraryProto) this.instance).getPath();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public ByteString getPathBytes() {
                return ((SharedLibraryProto) this.instance).getPathBytes();
            }

            public Builder setPath(String value) {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).setPath(value);
                return this;
            }

            public Builder clearPath() {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).clearPath();
                return this;
            }

            public Builder setPathBytes(ByteString value) {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).setPathBytes(value);
                return this;
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public boolean hasApk() {
                return ((SharedLibraryProto) this.instance).hasApk();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public String getApk() {
                return ((SharedLibraryProto) this.instance).getApk();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedLibraryProtoOrBuilder
            public ByteString getApkBytes() {
                return ((SharedLibraryProto) this.instance).getApkBytes();
            }

            public Builder setApk(String value) {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).setApk(value);
                return this;
            }

            public Builder clearApk() {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).clearApk();
                return this;
            }

            public Builder setApkBytes(ByteString value) {
                copyOnWrite();
                ((SharedLibraryProto) this.instance).setApkBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SharedLibraryProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SharedLibraryProto other = (SharedLibraryProto) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.isJar_ = visitor.visitBoolean(hasIsJar(), this.isJar_, other.hasIsJar(), other.isJar_);
                    this.path_ = visitor.visitString(hasPath(), this.path_, other.hasPath(), other.path_);
                    this.apk_ = visitor.visitString(hasApk(), this.apk_, other.hasApk(), other.apk_);
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
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isJar_ = input.readBool();
                            } else if (tag == 26) {
                                String s2 = input.readString();
                                this.bitField0_ |= 4;
                                this.path_ = s2;
                            } else if (tag == 34) {
                                String s3 = input.readString();
                                this.bitField0_ |= 8;
                                this.apk_ = s3;
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
                        synchronized (SharedLibraryProto.class) {
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

        public static SharedLibraryProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SharedLibraryProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class SharedUserProto extends GeneratedMessageLite<SharedUserProto, Builder> implements SharedUserProtoOrBuilder {
        private static final SharedUserProto DEFAULT_INSTANCE = new SharedUserProto();
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<SharedUserProto> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private String name_ = "";
        private int uid_ = 0;

        private SharedUserProto() {
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 1;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -2;
            this.uid_ = 0;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
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

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getName());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getName());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static SharedUserProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (SharedUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SharedUserProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SharedUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SharedUserProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (SharedUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static SharedUserProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (SharedUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static SharedUserProto parseFrom(InputStream input) throws IOException {
            return (SharedUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SharedUserProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SharedUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SharedUserProto parseDelimitedFrom(InputStream input) throws IOException {
            return (SharedUserProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static SharedUserProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SharedUserProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static SharedUserProto parseFrom(CodedInputStream input) throws IOException {
            return (SharedUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static SharedUserProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (SharedUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(SharedUserProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<SharedUserProto, Builder> implements SharedUserProtoOrBuilder {
            private Builder() {
                super(SharedUserProto.DEFAULT_INSTANCE);
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
            public boolean hasUid() {
                return ((SharedUserProto) this.instance).hasUid();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
            public int getUid() {
                return ((SharedUserProto) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((SharedUserProto) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((SharedUserProto) this.instance).clearUid();
                return this;
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
            public boolean hasName() {
                return ((SharedUserProto) this.instance).hasName();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
            public String getName() {
                return ((SharedUserProto) this.instance).getName();
            }

            @Override // android.service.pm.PackageServiceDumpProto.SharedUserProtoOrBuilder
            public ByteString getNameBytes() {
                return ((SharedUserProto) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((SharedUserProto) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((SharedUserProto) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((SharedUserProto) this.instance).setNameBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new SharedUserProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    SharedUserProto other = (SharedUserProto) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
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
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.uid_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.name_ = s;
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
                        synchronized (SharedUserProto.class) {
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

        public static SharedUserProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<SharedUserProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public boolean hasRequiredVerifierPackage() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public PackageShortProto getRequiredVerifierPackage() {
        PackageShortProto packageShortProto = this.requiredVerifierPackage_;
        return packageShortProto == null ? PackageShortProto.getDefaultInstance() : packageShortProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequiredVerifierPackage(PackageShortProto value) {
        if (value != null) {
            this.requiredVerifierPackage_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRequiredVerifierPackage(PackageShortProto.Builder builderForValue) {
        this.requiredVerifierPackage_ = (PackageShortProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRequiredVerifierPackage(PackageShortProto value) {
        PackageShortProto packageShortProto = this.requiredVerifierPackage_;
        if (packageShortProto == null || packageShortProto == PackageShortProto.getDefaultInstance()) {
            this.requiredVerifierPackage_ = value;
        } else {
            this.requiredVerifierPackage_ = (PackageShortProto) ((PackageShortProto.Builder) PackageShortProto.newBuilder(this.requiredVerifierPackage_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRequiredVerifierPackage() {
        this.requiredVerifierPackage_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public boolean hasVerifierPackage() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public PackageShortProto getVerifierPackage() {
        PackageShortProto packageShortProto = this.verifierPackage_;
        return packageShortProto == null ? PackageShortProto.getDefaultInstance() : packageShortProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVerifierPackage(PackageShortProto value) {
        if (value != null) {
            this.verifierPackage_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVerifierPackage(PackageShortProto.Builder builderForValue) {
        this.verifierPackage_ = (PackageShortProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVerifierPackage(PackageShortProto value) {
        PackageShortProto packageShortProto = this.verifierPackage_;
        if (packageShortProto == null || packageShortProto == PackageShortProto.getDefaultInstance()) {
            this.verifierPackage_ = value;
        } else {
            this.verifierPackage_ = (PackageShortProto) ((PackageShortProto.Builder) PackageShortProto.newBuilder(this.verifierPackage_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVerifierPackage() {
        this.verifierPackage_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public List<SharedLibraryProto> getSharedLibrariesList() {
        return this.sharedLibraries_;
    }

    public List<? extends SharedLibraryProtoOrBuilder> getSharedLibrariesOrBuilderList() {
        return this.sharedLibraries_;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public int getSharedLibrariesCount() {
        return this.sharedLibraries_.size();
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public SharedLibraryProto getSharedLibraries(int index) {
        return this.sharedLibraries_.get(index);
    }

    public SharedLibraryProtoOrBuilder getSharedLibrariesOrBuilder(int index) {
        return this.sharedLibraries_.get(index);
    }

    private void ensureSharedLibrariesIsMutable() {
        if (!this.sharedLibraries_.isModifiable()) {
            this.sharedLibraries_ = GeneratedMessageLite.mutableCopy(this.sharedLibraries_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSharedLibraries(int index, SharedLibraryProto value) {
        if (value != null) {
            ensureSharedLibrariesIsMutable();
            this.sharedLibraries_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSharedLibraries(int index, SharedLibraryProto.Builder builderForValue) {
        ensureSharedLibrariesIsMutable();
        this.sharedLibraries_.set(index, (SharedLibraryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedLibraries(SharedLibraryProto value) {
        if (value != null) {
            ensureSharedLibrariesIsMutable();
            this.sharedLibraries_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedLibraries(int index, SharedLibraryProto value) {
        if (value != null) {
            ensureSharedLibrariesIsMutable();
            this.sharedLibraries_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedLibraries(SharedLibraryProto.Builder builderForValue) {
        ensureSharedLibrariesIsMutable();
        this.sharedLibraries_.add((SharedLibraryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedLibraries(int index, SharedLibraryProto.Builder builderForValue) {
        ensureSharedLibrariesIsMutable();
        this.sharedLibraries_.add(index, (SharedLibraryProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSharedLibraries(Iterable<? extends SharedLibraryProto> values) {
        ensureSharedLibrariesIsMutable();
        AbstractMessageLite.addAll(values, this.sharedLibraries_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSharedLibraries() {
        this.sharedLibraries_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSharedLibraries(int index) {
        ensureSharedLibrariesIsMutable();
        this.sharedLibraries_.remove(index);
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public List<FeatureInfoProto> getFeaturesList() {
        return this.features_;
    }

    public List<? extends FeatureInfoProtoOrBuilder> getFeaturesOrBuilderList() {
        return this.features_;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public int getFeaturesCount() {
        return this.features_.size();
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public FeatureInfoProto getFeatures(int index) {
        return this.features_.get(index);
    }

    public FeatureInfoProtoOrBuilder getFeaturesOrBuilder(int index) {
        return this.features_.get(index);
    }

    private void ensureFeaturesIsMutable() {
        if (!this.features_.isModifiable()) {
            this.features_ = GeneratedMessageLite.mutableCopy(this.features_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFeatures(int index, FeatureInfoProto value) {
        if (value != null) {
            ensureFeaturesIsMutable();
            this.features_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFeatures(int index, FeatureInfoProto.Builder builderForValue) {
        ensureFeaturesIsMutable();
        this.features_.set(index, (FeatureInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFeatures(FeatureInfoProto value) {
        if (value != null) {
            ensureFeaturesIsMutable();
            this.features_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFeatures(int index, FeatureInfoProto value) {
        if (value != null) {
            ensureFeaturesIsMutable();
            this.features_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFeatures(FeatureInfoProto.Builder builderForValue) {
        ensureFeaturesIsMutable();
        this.features_.add((FeatureInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFeatures(int index, FeatureInfoProto.Builder builderForValue) {
        ensureFeaturesIsMutable();
        this.features_.add(index, (FeatureInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllFeatures(Iterable<? extends FeatureInfoProto> values) {
        ensureFeaturesIsMutable();
        AbstractMessageLite.addAll(values, this.features_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFeatures() {
        this.features_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeFeatures(int index) {
        ensureFeaturesIsMutable();
        this.features_.remove(index);
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public List<PackageProto> getPackagesList() {
        return this.packages_;
    }

    public List<? extends PackageProtoOrBuilder> getPackagesOrBuilderList() {
        return this.packages_;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public int getPackagesCount() {
        return this.packages_.size();
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public PackageProto getPackages(int index) {
        return this.packages_.get(index);
    }

    public PackageProtoOrBuilder getPackagesOrBuilder(int index) {
        return this.packages_.get(index);
    }

    private void ensurePackagesIsMutable() {
        if (!this.packages_.isModifiable()) {
            this.packages_ = GeneratedMessageLite.mutableCopy(this.packages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackages(int index, PackageProto value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackages(int index, PackageProto.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.set(index, (PackageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(PackageProto value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(int index, PackageProto value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(PackageProto.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.add((PackageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(int index, PackageProto.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.add(index, (PackageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPackages(Iterable<? extends PackageProto> values) {
        ensurePackagesIsMutable();
        AbstractMessageLite.addAll(values, this.packages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackages() {
        this.packages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePackages(int index) {
        ensurePackagesIsMutable();
        this.packages_.remove(index);
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public List<SharedUserProto> getSharedUsersList() {
        return this.sharedUsers_;
    }

    public List<? extends SharedUserProtoOrBuilder> getSharedUsersOrBuilderList() {
        return this.sharedUsers_;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public int getSharedUsersCount() {
        return this.sharedUsers_.size();
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public SharedUserProto getSharedUsers(int index) {
        return this.sharedUsers_.get(index);
    }

    public SharedUserProtoOrBuilder getSharedUsersOrBuilder(int index) {
        return this.sharedUsers_.get(index);
    }

    private void ensureSharedUsersIsMutable() {
        if (!this.sharedUsers_.isModifiable()) {
            this.sharedUsers_ = GeneratedMessageLite.mutableCopy(this.sharedUsers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSharedUsers(int index, SharedUserProto value) {
        if (value != null) {
            ensureSharedUsersIsMutable();
            this.sharedUsers_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSharedUsers(int index, SharedUserProto.Builder builderForValue) {
        ensureSharedUsersIsMutable();
        this.sharedUsers_.set(index, (SharedUserProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedUsers(SharedUserProto value) {
        if (value != null) {
            ensureSharedUsersIsMutable();
            this.sharedUsers_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedUsers(int index, SharedUserProto value) {
        if (value != null) {
            ensureSharedUsersIsMutable();
            this.sharedUsers_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedUsers(SharedUserProto.Builder builderForValue) {
        ensureSharedUsersIsMutable();
        this.sharedUsers_.add((SharedUserProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedUsers(int index, SharedUserProto.Builder builderForValue) {
        ensureSharedUsersIsMutable();
        this.sharedUsers_.add(index, (SharedUserProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSharedUsers(Iterable<? extends SharedUserProto> values) {
        ensureSharedUsersIsMutable();
        AbstractMessageLite.addAll(values, this.sharedUsers_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSharedUsers() {
        this.sharedUsers_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSharedUsers(int index) {
        ensureSharedUsersIsMutable();
        this.sharedUsers_.remove(index);
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public List<String> getMessagesList() {
        return this.messages_;
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public int getMessagesCount() {
        return this.messages_.size();
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public String getMessages(int index) {
        return this.messages_.get(index);
    }

    @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
    public ByteString getMessagesBytes(int index) {
        return ByteString.copyFromUtf8(this.messages_.get(index));
    }

    private void ensureMessagesIsMutable() {
        if (!this.messages_.isModifiable()) {
            this.messages_ = GeneratedMessageLite.mutableCopy(this.messages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMessages(int index, String value) {
        if (value != null) {
            ensureMessagesIsMutable();
            this.messages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMessages(String value) {
        if (value != null) {
            ensureMessagesIsMutable();
            this.messages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllMessages(Iterable<String> values) {
        ensureMessagesIsMutable();
        AbstractMessageLite.addAll(values, this.messages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMessages() {
        this.messages_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMessagesBytes(ByteString value) {
        if (value != null) {
            ensureMessagesIsMutable();
            this.messages_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getRequiredVerifierPackage());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getVerifierPackage());
        }
        for (int i = 0; i < this.sharedLibraries_.size(); i++) {
            output.writeMessage(3, this.sharedLibraries_.get(i));
        }
        for (int i2 = 0; i2 < this.features_.size(); i2++) {
            output.writeMessage(4, this.features_.get(i2));
        }
        for (int i3 = 0; i3 < this.packages_.size(); i3++) {
            output.writeMessage(5, this.packages_.get(i3));
        }
        for (int i4 = 0; i4 < this.sharedUsers_.size(); i4++) {
            output.writeMessage(6, this.sharedUsers_.get(i4));
        }
        for (int i5 = 0; i5 < this.messages_.size(); i5++) {
            output.writeString(7, this.messages_.get(i5));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getRequiredVerifierPackage());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getVerifierPackage());
        }
        for (int i = 0; i < this.sharedLibraries_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.sharedLibraries_.get(i));
        }
        for (int i2 = 0; i2 < this.features_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.features_.get(i2));
        }
        for (int i3 = 0; i3 < this.packages_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.packages_.get(i3));
        }
        for (int i4 = 0; i4 < this.sharedUsers_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.sharedUsers_.get(i4));
        }
        int dataSize = 0;
        for (int i5 = 0; i5 < this.messages_.size(); i5++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.messages_.get(i5));
        }
        int size3 = size2 + dataSize + (getMessagesList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PackageServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PackageServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PackageServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PackageServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PackageServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PackageServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (PackageServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PackageServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PackageServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (PackageServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PackageServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PackageServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PackageServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PackageServiceDumpProto, Builder> implements PackageServiceDumpProtoOrBuilder {
        private Builder() {
            super(PackageServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public boolean hasRequiredVerifierPackage() {
            return ((PackageServiceDumpProto) this.instance).hasRequiredVerifierPackage();
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public PackageShortProto getRequiredVerifierPackage() {
            return ((PackageServiceDumpProto) this.instance).getRequiredVerifierPackage();
        }

        public Builder setRequiredVerifierPackage(PackageShortProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setRequiredVerifierPackage((PackageServiceDumpProto) value);
            return this;
        }

        public Builder setRequiredVerifierPackage(PackageShortProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setRequiredVerifierPackage((PackageServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeRequiredVerifierPackage(PackageShortProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).mergeRequiredVerifierPackage(value);
            return this;
        }

        public Builder clearRequiredVerifierPackage() {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).clearRequiredVerifierPackage();
            return this;
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public boolean hasVerifierPackage() {
            return ((PackageServiceDumpProto) this.instance).hasVerifierPackage();
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public PackageShortProto getVerifierPackage() {
            return ((PackageServiceDumpProto) this.instance).getVerifierPackage();
        }

        public Builder setVerifierPackage(PackageShortProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setVerifierPackage((PackageServiceDumpProto) value);
            return this;
        }

        public Builder setVerifierPackage(PackageShortProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setVerifierPackage((PackageServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeVerifierPackage(PackageShortProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).mergeVerifierPackage(value);
            return this;
        }

        public Builder clearVerifierPackage() {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).clearVerifierPackage();
            return this;
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public List<SharedLibraryProto> getSharedLibrariesList() {
            return Collections.unmodifiableList(((PackageServiceDumpProto) this.instance).getSharedLibrariesList());
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public int getSharedLibrariesCount() {
            return ((PackageServiceDumpProto) this.instance).getSharedLibrariesCount();
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public SharedLibraryProto getSharedLibraries(int index) {
            return ((PackageServiceDumpProto) this.instance).getSharedLibraries(index);
        }

        public Builder setSharedLibraries(int index, SharedLibraryProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setSharedLibraries((PackageServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setSharedLibraries(int index, SharedLibraryProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setSharedLibraries((PackageServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSharedLibraries(SharedLibraryProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addSharedLibraries((PackageServiceDumpProto) value);
            return this;
        }

        public Builder addSharedLibraries(int index, SharedLibraryProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addSharedLibraries((PackageServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addSharedLibraries(SharedLibraryProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addSharedLibraries((PackageServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addSharedLibraries(int index, SharedLibraryProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addSharedLibraries((PackageServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSharedLibraries(Iterable<? extends SharedLibraryProto> values) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addAllSharedLibraries(values);
            return this;
        }

        public Builder clearSharedLibraries() {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).clearSharedLibraries();
            return this;
        }

        public Builder removeSharedLibraries(int index) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).removeSharedLibraries(index);
            return this;
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public List<FeatureInfoProto> getFeaturesList() {
            return Collections.unmodifiableList(((PackageServiceDumpProto) this.instance).getFeaturesList());
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public int getFeaturesCount() {
            return ((PackageServiceDumpProto) this.instance).getFeaturesCount();
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public FeatureInfoProto getFeatures(int index) {
            return ((PackageServiceDumpProto) this.instance).getFeatures(index);
        }

        public Builder setFeatures(int index, FeatureInfoProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setFeatures((PackageServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setFeatures(int index, FeatureInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setFeatures((PackageServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addFeatures(FeatureInfoProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addFeatures((PackageServiceDumpProto) value);
            return this;
        }

        public Builder addFeatures(int index, FeatureInfoProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addFeatures((PackageServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addFeatures(FeatureInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addFeatures((PackageServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addFeatures(int index, FeatureInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addFeatures((PackageServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllFeatures(Iterable<? extends FeatureInfoProto> values) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addAllFeatures(values);
            return this;
        }

        public Builder clearFeatures() {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).clearFeatures();
            return this;
        }

        public Builder removeFeatures(int index) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).removeFeatures(index);
            return this;
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public List<PackageProto> getPackagesList() {
            return Collections.unmodifiableList(((PackageServiceDumpProto) this.instance).getPackagesList());
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public int getPackagesCount() {
            return ((PackageServiceDumpProto) this.instance).getPackagesCount();
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public PackageProto getPackages(int index) {
            return ((PackageServiceDumpProto) this.instance).getPackages(index);
        }

        public Builder setPackages(int index, PackageProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setPackages((PackageServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setPackages(int index, PackageProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setPackages((PackageServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPackages(PackageProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addPackages((PackageServiceDumpProto) value);
            return this;
        }

        public Builder addPackages(int index, PackageProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addPackages((PackageServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addPackages(PackageProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addPackages((PackageServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addPackages(int index, PackageProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addPackages((PackageServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPackages(Iterable<? extends PackageProto> values) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addAllPackages(values);
            return this;
        }

        public Builder clearPackages() {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).clearPackages();
            return this;
        }

        public Builder removePackages(int index) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).removePackages(index);
            return this;
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public List<SharedUserProto> getSharedUsersList() {
            return Collections.unmodifiableList(((PackageServiceDumpProto) this.instance).getSharedUsersList());
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public int getSharedUsersCount() {
            return ((PackageServiceDumpProto) this.instance).getSharedUsersCount();
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public SharedUserProto getSharedUsers(int index) {
            return ((PackageServiceDumpProto) this.instance).getSharedUsers(index);
        }

        public Builder setSharedUsers(int index, SharedUserProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setSharedUsers((PackageServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setSharedUsers(int index, SharedUserProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setSharedUsers((PackageServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSharedUsers(SharedUserProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addSharedUsers((PackageServiceDumpProto) value);
            return this;
        }

        public Builder addSharedUsers(int index, SharedUserProto value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addSharedUsers((PackageServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addSharedUsers(SharedUserProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addSharedUsers((PackageServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addSharedUsers(int index, SharedUserProto.Builder builderForValue) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addSharedUsers((PackageServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSharedUsers(Iterable<? extends SharedUserProto> values) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addAllSharedUsers(values);
            return this;
        }

        public Builder clearSharedUsers() {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).clearSharedUsers();
            return this;
        }

        public Builder removeSharedUsers(int index) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).removeSharedUsers(index);
            return this;
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public List<String> getMessagesList() {
            return Collections.unmodifiableList(((PackageServiceDumpProto) this.instance).getMessagesList());
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public int getMessagesCount() {
            return ((PackageServiceDumpProto) this.instance).getMessagesCount();
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public String getMessages(int index) {
            return ((PackageServiceDumpProto) this.instance).getMessages(index);
        }

        @Override // android.service.pm.PackageServiceDumpProtoOrBuilder
        public ByteString getMessagesBytes(int index) {
            return ((PackageServiceDumpProto) this.instance).getMessagesBytes(index);
        }

        public Builder setMessages(int index, String value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).setMessages(index, value);
            return this;
        }

        public Builder addMessages(String value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addMessages(value);
            return this;
        }

        public Builder addAllMessages(Iterable<String> values) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addAllMessages(values);
            return this;
        }

        public Builder clearMessages() {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).clearMessages();
            return this;
        }

        public Builder addMessagesBytes(ByteString value) {
            copyOnWrite();
            ((PackageServiceDumpProto) this.instance).addMessagesBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PackageServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.sharedLibraries_.makeImmutable();
                this.features_.makeImmutable();
                this.packages_.makeImmutable();
                this.sharedUsers_.makeImmutable();
                this.messages_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PackageServiceDumpProto other = (PackageServiceDumpProto) arg1;
                this.requiredVerifierPackage_ = (PackageShortProto) visitor.visitMessage(this.requiredVerifierPackage_, other.requiredVerifierPackage_);
                this.verifierPackage_ = (PackageShortProto) visitor.visitMessage(this.verifierPackage_, other.verifierPackage_);
                this.sharedLibraries_ = visitor.visitList(this.sharedLibraries_, other.sharedLibraries_);
                this.features_ = visitor.visitList(this.features_, other.features_);
                this.packages_ = visitor.visitList(this.packages_, other.packages_);
                this.sharedUsers_ = visitor.visitList(this.sharedUsers_, other.sharedUsers_);
                this.messages_ = visitor.visitList(this.messages_, other.messages_);
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
                        } else if (tag == 10) {
                            PackageShortProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (PackageShortProto.Builder) this.requiredVerifierPackage_.toBuilder();
                            }
                            this.requiredVerifierPackage_ = (PackageShortProto) input.readMessage(PackageShortProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.requiredVerifierPackage_);
                                this.requiredVerifierPackage_ = (PackageShortProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            PackageShortProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (PackageShortProto.Builder) this.verifierPackage_.toBuilder();
                            }
                            this.verifierPackage_ = (PackageShortProto) input.readMessage(PackageShortProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.verifierPackage_);
                                this.verifierPackage_ = (PackageShortProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            if (!this.sharedLibraries_.isModifiable()) {
                                this.sharedLibraries_ = GeneratedMessageLite.mutableCopy(this.sharedLibraries_);
                            }
                            this.sharedLibraries_.add((SharedLibraryProto) input.readMessage(SharedLibraryProto.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            if (!this.features_.isModifiable()) {
                                this.features_ = GeneratedMessageLite.mutableCopy(this.features_);
                            }
                            this.features_.add((FeatureInfoProto) input.readMessage(FeatureInfoProto.parser(), extensionRegistry));
                        } else if (tag == 42) {
                            if (!this.packages_.isModifiable()) {
                                this.packages_ = GeneratedMessageLite.mutableCopy(this.packages_);
                            }
                            this.packages_.add((PackageProto) input.readMessage(PackageProto.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            if (!this.sharedUsers_.isModifiable()) {
                                this.sharedUsers_ = GeneratedMessageLite.mutableCopy(this.sharedUsers_);
                            }
                            this.sharedUsers_.add((SharedUserProto) input.readMessage(SharedUserProto.parser(), extensionRegistry));
                        } else if (tag == 58) {
                            String s = input.readString();
                            if (!this.messages_.isModifiable()) {
                                this.messages_ = GeneratedMessageLite.mutableCopy(this.messages_);
                            }
                            this.messages_.add(s);
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
                    synchronized (PackageServiceDumpProto.class) {
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

    public static PackageServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PackageServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
