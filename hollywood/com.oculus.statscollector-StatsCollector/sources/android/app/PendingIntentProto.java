package android.app;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PendingIntentProto extends GeneratedMessageLite<PendingIntentProto, Builder> implements PendingIntentProtoOrBuilder {
    private static final PendingIntentProto DEFAULT_INSTANCE = new PendingIntentProto();
    private static volatile Parser<PendingIntentProto> PARSER = null;
    public static final int TARGET_FIELD_NUMBER = 1;
    private int bitField0_;
    private String target_ = "";

    private PendingIntentProto() {
    }

    @Override // android.app.PendingIntentProtoOrBuilder
    public boolean hasTarget() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.app.PendingIntentProtoOrBuilder
    public String getTarget() {
        return this.target_;
    }

    @Override // android.app.PendingIntentProtoOrBuilder
    public ByteString getTargetBytes() {
        return ByteString.copyFromUtf8(this.target_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTarget(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.target_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTarget() {
        this.bitField0_ &= -2;
        this.target_ = getDefaultInstance().getTarget();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTargetBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.target_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getTarget());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getTarget());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PendingIntentProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PendingIntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PendingIntentProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PendingIntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PendingIntentProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PendingIntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PendingIntentProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PendingIntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PendingIntentProto parseFrom(InputStream input) throws IOException {
        return (PendingIntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PendingIntentProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PendingIntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PendingIntentProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PendingIntentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PendingIntentProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PendingIntentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PendingIntentProto parseFrom(CodedInputStream input) throws IOException {
        return (PendingIntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PendingIntentProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PendingIntentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PendingIntentProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PendingIntentProto, Builder> implements PendingIntentProtoOrBuilder {
        private Builder() {
            super(PendingIntentProto.DEFAULT_INSTANCE);
        }

        @Override // android.app.PendingIntentProtoOrBuilder
        public boolean hasTarget() {
            return ((PendingIntentProto) this.instance).hasTarget();
        }

        @Override // android.app.PendingIntentProtoOrBuilder
        public String getTarget() {
            return ((PendingIntentProto) this.instance).getTarget();
        }

        @Override // android.app.PendingIntentProtoOrBuilder
        public ByteString getTargetBytes() {
            return ((PendingIntentProto) this.instance).getTargetBytes();
        }

        public Builder setTarget(String value) {
            copyOnWrite();
            ((PendingIntentProto) this.instance).setTarget(value);
            return this;
        }

        public Builder clearTarget() {
            copyOnWrite();
            ((PendingIntentProto) this.instance).clearTarget();
            return this;
        }

        public Builder setTargetBytes(ByteString value) {
            copyOnWrite();
            ((PendingIntentProto) this.instance).setTargetBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PendingIntentProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PendingIntentProto other = (PendingIntentProto) arg1;
                this.target_ = visitor.visitString(hasTarget(), this.target_, other.hasTarget(), other.target_);
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
                            this.target_ = s;
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
                    synchronized (PendingIntentProto.class) {
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

    public static PendingIntentProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PendingIntentProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
