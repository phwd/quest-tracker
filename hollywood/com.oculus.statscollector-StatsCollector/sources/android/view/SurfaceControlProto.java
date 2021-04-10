package android.view;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class SurfaceControlProto extends GeneratedMessageLite<SurfaceControlProto, Builder> implements SurfaceControlProtoOrBuilder {
    private static final SurfaceControlProto DEFAULT_INSTANCE = new SurfaceControlProto();
    public static final int HASH_CODE_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser<SurfaceControlProto> PARSER;
    private int bitField0_;
    private int hashCode_ = 0;
    private String name_ = "";

    private SurfaceControlProto() {
    }

    @Override // android.view.SurfaceControlProtoOrBuilder
    public boolean hasHashCode() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.view.SurfaceControlProtoOrBuilder
    public int getHashCode() {
        return this.hashCode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHashCode(int value) {
        this.bitField0_ |= 1;
        this.hashCode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHashCode() {
        this.bitField0_ &= -2;
        this.hashCode_ = 0;
    }

    @Override // android.view.SurfaceControlProtoOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.view.SurfaceControlProtoOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // android.view.SurfaceControlProtoOrBuilder
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
            output.writeInt32(1, this.hashCode_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.hashCode_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getName());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static SurfaceControlProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SurfaceControlProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SurfaceControlProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SurfaceControlProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SurfaceControlProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SurfaceControlProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SurfaceControlProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SurfaceControlProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SurfaceControlProto parseFrom(InputStream input) throws IOException {
        return (SurfaceControlProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceControlProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceControlProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SurfaceControlProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SurfaceControlProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceControlProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceControlProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SurfaceControlProto parseFrom(CodedInputStream input) throws IOException {
        return (SurfaceControlProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SurfaceControlProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SurfaceControlProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SurfaceControlProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SurfaceControlProto, Builder> implements SurfaceControlProtoOrBuilder {
        private Builder() {
            super(SurfaceControlProto.DEFAULT_INSTANCE);
        }

        @Override // android.view.SurfaceControlProtoOrBuilder
        public boolean hasHashCode() {
            return ((SurfaceControlProto) this.instance).hasHashCode();
        }

        @Override // android.view.SurfaceControlProtoOrBuilder
        public int getHashCode() {
            return ((SurfaceControlProto) this.instance).getHashCode();
        }

        public Builder setHashCode(int value) {
            copyOnWrite();
            ((SurfaceControlProto) this.instance).setHashCode(value);
            return this;
        }

        public Builder clearHashCode() {
            copyOnWrite();
            ((SurfaceControlProto) this.instance).clearHashCode();
            return this;
        }

        @Override // android.view.SurfaceControlProtoOrBuilder
        public boolean hasName() {
            return ((SurfaceControlProto) this.instance).hasName();
        }

        @Override // android.view.SurfaceControlProtoOrBuilder
        public String getName() {
            return ((SurfaceControlProto) this.instance).getName();
        }

        @Override // android.view.SurfaceControlProtoOrBuilder
        public ByteString getNameBytes() {
            return ((SurfaceControlProto) this.instance).getNameBytes();
        }

        public Builder setName(String value) {
            copyOnWrite();
            ((SurfaceControlProto) this.instance).setName(value);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((SurfaceControlProto) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            copyOnWrite();
            ((SurfaceControlProto) this.instance).setNameBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SurfaceControlProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SurfaceControlProto other = (SurfaceControlProto) arg1;
                this.hashCode_ = visitor.visitInt(hasHashCode(), this.hashCode_, other.hasHashCode(), other.hashCode_);
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
                            this.hashCode_ = input.readInt32();
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
                    synchronized (SurfaceControlProto.class) {
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

    public static SurfaceControlProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SurfaceControlProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
