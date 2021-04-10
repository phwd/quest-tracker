package android.graphics;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PointProto extends GeneratedMessageLite<PointProto, Builder> implements PointProtoOrBuilder {
    private static final PointProto DEFAULT_INSTANCE = new PointProto();
    private static volatile Parser<PointProto> PARSER = null;
    public static final int X_FIELD_NUMBER = 1;
    public static final int Y_FIELD_NUMBER = 2;
    private int bitField0_;
    private int x_ = 0;
    private int y_ = 0;

    private PointProto() {
    }

    @Override // android.graphics.PointProtoOrBuilder
    public boolean hasX() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.graphics.PointProtoOrBuilder
    public int getX() {
        return this.x_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setX(int value) {
        this.bitField0_ |= 1;
        this.x_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearX() {
        this.bitField0_ &= -2;
        this.x_ = 0;
    }

    @Override // android.graphics.PointProtoOrBuilder
    public boolean hasY() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.graphics.PointProtoOrBuilder
    public int getY() {
        return this.y_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setY(int value) {
        this.bitField0_ |= 2;
        this.y_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearY() {
        this.bitField0_ &= -3;
        this.y_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.x_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.y_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.x_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.y_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PointProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PointProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PointProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PointProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PointProto parseFrom(InputStream input) throws IOException {
        return (PointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PointProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PointProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PointProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PointProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PointProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PointProto parseFrom(CodedInputStream input) throws IOException {
        return (PointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PointProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PointProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PointProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PointProto, Builder> implements PointProtoOrBuilder {
        private Builder() {
            super(PointProto.DEFAULT_INSTANCE);
        }

        @Override // android.graphics.PointProtoOrBuilder
        public boolean hasX() {
            return ((PointProto) this.instance).hasX();
        }

        @Override // android.graphics.PointProtoOrBuilder
        public int getX() {
            return ((PointProto) this.instance).getX();
        }

        public Builder setX(int value) {
            copyOnWrite();
            ((PointProto) this.instance).setX(value);
            return this;
        }

        public Builder clearX() {
            copyOnWrite();
            ((PointProto) this.instance).clearX();
            return this;
        }

        @Override // android.graphics.PointProtoOrBuilder
        public boolean hasY() {
            return ((PointProto) this.instance).hasY();
        }

        @Override // android.graphics.PointProtoOrBuilder
        public int getY() {
            return ((PointProto) this.instance).getY();
        }

        public Builder setY(int value) {
            copyOnWrite();
            ((PointProto) this.instance).setY(value);
            return this;
        }

        public Builder clearY() {
            copyOnWrite();
            ((PointProto) this.instance).clearY();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PointProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PointProto other = (PointProto) arg1;
                this.x_ = visitor.visitInt(hasX(), this.x_, other.hasX(), other.x_);
                this.y_ = visitor.visitInt(hasY(), this.y_, other.hasY(), other.y_);
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
                            this.x_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.y_ = input.readInt32();
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
                    synchronized (PointProto.class) {
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

    public static PointProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PointProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
