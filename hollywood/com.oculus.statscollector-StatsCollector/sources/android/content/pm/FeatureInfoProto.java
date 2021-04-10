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

public final class FeatureInfoProto extends GeneratedMessageLite<FeatureInfoProto, Builder> implements FeatureInfoProtoOrBuilder {
    private static final FeatureInfoProto DEFAULT_INSTANCE = new FeatureInfoProto();
    public static final int FLAGS_FIELD_NUMBER = 4;
    public static final int GLES_VERSION_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<FeatureInfoProto> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 2;
    private int bitField0_;
    private int flags_ = 0;
    private String glesVersion_ = "";
    private String name_ = "";
    private int version_ = 0;

    private FeatureInfoProto() {
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
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

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public boolean hasVersion() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public int getVersion() {
        return this.version_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVersion(int value) {
        this.bitField0_ |= 2;
        this.version_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVersion() {
        this.bitField0_ &= -3;
        this.version_ = 0;
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public boolean hasGlesVersion() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public String getGlesVersion() {
        return this.glesVersion_;
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public ByteString getGlesVersionBytes() {
        return ByteString.copyFromUtf8(this.glesVersion_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlesVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.glesVersion_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGlesVersion() {
        this.bitField0_ &= -5;
        this.glesVersion_ = getDefaultInstance().getGlesVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGlesVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.glesVersion_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.content.pm.FeatureInfoProtoOrBuilder
    public int getFlags() {
        return this.flags_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFlags(int value) {
        this.bitField0_ |= 8;
        this.flags_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFlags() {
        this.bitField0_ &= -9;
        this.flags_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.version_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getGlesVersion());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.flags_);
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
            size2 += CodedOutputStream.computeInt32Size(2, this.version_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getGlesVersion());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.flags_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static FeatureInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (FeatureInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FeatureInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FeatureInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FeatureInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (FeatureInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static FeatureInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (FeatureInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static FeatureInfoProto parseFrom(InputStream input) throws IOException {
        return (FeatureInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FeatureInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FeatureInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FeatureInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (FeatureInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static FeatureInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FeatureInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static FeatureInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (FeatureInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static FeatureInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (FeatureInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FeatureInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FeatureInfoProto, Builder> implements FeatureInfoProtoOrBuilder {
        private Builder() {
            super(FeatureInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public boolean hasName() {
            return ((FeatureInfoProto) this.instance).hasName();
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public String getName() {
            return ((FeatureInfoProto) this.instance).getName();
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public ByteString getNameBytes() {
            return ((FeatureInfoProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).setNameBytes(value);
            return this;
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public boolean hasVersion() {
            return ((FeatureInfoProto) this.instance).hasVersion();
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public int getVersion() {
            return ((FeatureInfoProto) this.instance).getVersion();
        }

        public Builder setVersion(int value) {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).setVersion(value);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).clearVersion();
            return this;
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public boolean hasGlesVersion() {
            return ((FeatureInfoProto) this.instance).hasGlesVersion();
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public String getGlesVersion() {
            return ((FeatureInfoProto) this.instance).getGlesVersion();
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public ByteString getGlesVersionBytes() {
            return ((FeatureInfoProto) this.instance).getGlesVersionBytes();
        }

        public Builder setGlesVersion(String value) {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).setGlesVersion(value);
            return this;
        }

        public Builder clearGlesVersion() {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).clearGlesVersion();
            return this;
        }

        public Builder setGlesVersionBytes(ByteString value) {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).setGlesVersionBytes(value);
            return this;
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public boolean hasFlags() {
            return ((FeatureInfoProto) this.instance).hasFlags();
        }

        @Override // android.content.pm.FeatureInfoProtoOrBuilder
        public int getFlags() {
            return ((FeatureInfoProto) this.instance).getFlags();
        }

        public Builder setFlags(int value) {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).setFlags(value);
            return this;
        }

        public Builder clearFlags() {
            copyOnWrite();
            ((FeatureInfoProto) this.instance).clearFlags();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new FeatureInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                FeatureInfoProto other = (FeatureInfoProto) arg1;
                this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                this.version_ = visitor.visitInt(hasVersion(), this.version_, other.hasVersion(), other.version_);
                this.glesVersion_ = visitor.visitString(hasGlesVersion(), this.glesVersion_, other.hasGlesVersion(), other.glesVersion_);
                this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
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
                            this.version_ = input.readInt32();
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.glesVersion_ = s2;
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.flags_ = input.readInt32();
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
                    synchronized (FeatureInfoProto.class) {
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

    public static FeatureInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FeatureInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
