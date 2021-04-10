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

public final class RectProto extends GeneratedMessageLite<RectProto, Builder> implements RectProtoOrBuilder {
    public static final int BOTTOM_FIELD_NUMBER = 4;
    private static final RectProto DEFAULT_INSTANCE = new RectProto();
    public static final int LEFT_FIELD_NUMBER = 1;
    private static volatile Parser<RectProto> PARSER = null;
    public static final int RIGHT_FIELD_NUMBER = 3;
    public static final int TOP_FIELD_NUMBER = 2;
    private int bitField0_;
    private int bottom_ = 0;
    private int left_ = 0;
    private int right_ = 0;
    private int top_ = 0;

    private RectProto() {
    }

    @Override // android.graphics.RectProtoOrBuilder
    public boolean hasLeft() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.graphics.RectProtoOrBuilder
    public int getLeft() {
        return this.left_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLeft(int value) {
        this.bitField0_ |= 1;
        this.left_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLeft() {
        this.bitField0_ &= -2;
        this.left_ = 0;
    }

    @Override // android.graphics.RectProtoOrBuilder
    public boolean hasTop() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.graphics.RectProtoOrBuilder
    public int getTop() {
        return this.top_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTop(int value) {
        this.bitField0_ |= 2;
        this.top_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTop() {
        this.bitField0_ &= -3;
        this.top_ = 0;
    }

    @Override // android.graphics.RectProtoOrBuilder
    public boolean hasRight() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.graphics.RectProtoOrBuilder
    public int getRight() {
        return this.right_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRight(int value) {
        this.bitField0_ |= 4;
        this.right_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRight() {
        this.bitField0_ &= -5;
        this.right_ = 0;
    }

    @Override // android.graphics.RectProtoOrBuilder
    public boolean hasBottom() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.graphics.RectProtoOrBuilder
    public int getBottom() {
        return this.bottom_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBottom(int value) {
        this.bitField0_ |= 8;
        this.bottom_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBottom() {
        this.bitField0_ &= -9;
        this.bottom_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.left_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.top_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.right_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.bottom_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.left_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.top_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.right_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.bottom_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RectProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RectProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RectProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RectProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RectProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RectProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RectProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RectProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RectProto parseFrom(InputStream input) throws IOException {
        return (RectProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RectProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RectProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RectProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RectProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RectProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RectProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RectProto parseFrom(CodedInputStream input) throws IOException {
        return (RectProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RectProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RectProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RectProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RectProto, Builder> implements RectProtoOrBuilder {
        private Builder() {
            super(RectProto.DEFAULT_INSTANCE);
        }

        @Override // android.graphics.RectProtoOrBuilder
        public boolean hasLeft() {
            return ((RectProto) this.instance).hasLeft();
        }

        @Override // android.graphics.RectProtoOrBuilder
        public int getLeft() {
            return ((RectProto) this.instance).getLeft();
        }

        public Builder setLeft(int value) {
            copyOnWrite();
            ((RectProto) this.instance).setLeft(value);
            return this;
        }

        public Builder clearLeft() {
            copyOnWrite();
            ((RectProto) this.instance).clearLeft();
            return this;
        }

        @Override // android.graphics.RectProtoOrBuilder
        public boolean hasTop() {
            return ((RectProto) this.instance).hasTop();
        }

        @Override // android.graphics.RectProtoOrBuilder
        public int getTop() {
            return ((RectProto) this.instance).getTop();
        }

        public Builder setTop(int value) {
            copyOnWrite();
            ((RectProto) this.instance).setTop(value);
            return this;
        }

        public Builder clearTop() {
            copyOnWrite();
            ((RectProto) this.instance).clearTop();
            return this;
        }

        @Override // android.graphics.RectProtoOrBuilder
        public boolean hasRight() {
            return ((RectProto) this.instance).hasRight();
        }

        @Override // android.graphics.RectProtoOrBuilder
        public int getRight() {
            return ((RectProto) this.instance).getRight();
        }

        public Builder setRight(int value) {
            copyOnWrite();
            ((RectProto) this.instance).setRight(value);
            return this;
        }

        public Builder clearRight() {
            copyOnWrite();
            ((RectProto) this.instance).clearRight();
            return this;
        }

        @Override // android.graphics.RectProtoOrBuilder
        public boolean hasBottom() {
            return ((RectProto) this.instance).hasBottom();
        }

        @Override // android.graphics.RectProtoOrBuilder
        public int getBottom() {
            return ((RectProto) this.instance).getBottom();
        }

        public Builder setBottom(int value) {
            copyOnWrite();
            ((RectProto) this.instance).setBottom(value);
            return this;
        }

        public Builder clearBottom() {
            copyOnWrite();
            ((RectProto) this.instance).clearBottom();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RectProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RectProto other = (RectProto) arg1;
                this.left_ = visitor.visitInt(hasLeft(), this.left_, other.hasLeft(), other.left_);
                this.top_ = visitor.visitInt(hasTop(), this.top_, other.hasTop(), other.top_);
                this.right_ = visitor.visitInt(hasRight(), this.right_, other.hasRight(), other.right_);
                this.bottom_ = visitor.visitInt(hasBottom(), this.bottom_, other.hasBottom(), other.bottom_);
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
                            this.left_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.top_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.right_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.bottom_ = input.readInt32();
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
                    synchronized (RectProto.class) {
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

    public static RectProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RectProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
