package android.service.print;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class MarginsProto extends GeneratedMessageLite<MarginsProto, Builder> implements MarginsProtoOrBuilder {
    public static final int BOTTOM_MILS_FIELD_NUMBER = 4;
    private static final MarginsProto DEFAULT_INSTANCE = new MarginsProto();
    public static final int LEFT_MILS_FIELD_NUMBER = 2;
    private static volatile Parser<MarginsProto> PARSER = null;
    public static final int RIGHT_MILS_FIELD_NUMBER = 3;
    public static final int TOP_MILS_FIELD_NUMBER = 1;
    private int bitField0_;
    private int bottomMils_ = 0;
    private int leftMils_ = 0;
    private int rightMils_ = 0;
    private int topMils_ = 0;

    private MarginsProto() {
    }

    @Override // android.service.print.MarginsProtoOrBuilder
    public boolean hasTopMils() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.MarginsProtoOrBuilder
    public int getTopMils() {
        return this.topMils_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTopMils(int value) {
        this.bitField0_ |= 1;
        this.topMils_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTopMils() {
        this.bitField0_ &= -2;
        this.topMils_ = 0;
    }

    @Override // android.service.print.MarginsProtoOrBuilder
    public boolean hasLeftMils() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.MarginsProtoOrBuilder
    public int getLeftMils() {
        return this.leftMils_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLeftMils(int value) {
        this.bitField0_ |= 2;
        this.leftMils_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLeftMils() {
        this.bitField0_ &= -3;
        this.leftMils_ = 0;
    }

    @Override // android.service.print.MarginsProtoOrBuilder
    public boolean hasRightMils() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.MarginsProtoOrBuilder
    public int getRightMils() {
        return this.rightMils_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRightMils(int value) {
        this.bitField0_ |= 4;
        this.rightMils_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRightMils() {
        this.bitField0_ &= -5;
        this.rightMils_ = 0;
    }

    @Override // android.service.print.MarginsProtoOrBuilder
    public boolean hasBottomMils() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.MarginsProtoOrBuilder
    public int getBottomMils() {
        return this.bottomMils_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBottomMils(int value) {
        this.bitField0_ |= 8;
        this.bottomMils_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBottomMils() {
        this.bitField0_ &= -9;
        this.bottomMils_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.topMils_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.leftMils_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.rightMils_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.bottomMils_);
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.topMils_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.leftMils_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.rightMils_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.bottomMils_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static MarginsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MarginsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MarginsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MarginsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MarginsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MarginsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MarginsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MarginsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MarginsProto parseFrom(InputStream input) throws IOException {
        return (MarginsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MarginsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MarginsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MarginsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (MarginsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MarginsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MarginsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MarginsProto parseFrom(CodedInputStream input) throws IOException {
        return (MarginsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MarginsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MarginsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MarginsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MarginsProto, Builder> implements MarginsProtoOrBuilder {
        private Builder() {
            super(MarginsProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.MarginsProtoOrBuilder
        public boolean hasTopMils() {
            return ((MarginsProto) this.instance).hasTopMils();
        }

        @Override // android.service.print.MarginsProtoOrBuilder
        public int getTopMils() {
            return ((MarginsProto) this.instance).getTopMils();
        }

        public Builder setTopMils(int value) {
            copyOnWrite();
            ((MarginsProto) this.instance).setTopMils(value);
            return this;
        }

        public Builder clearTopMils() {
            copyOnWrite();
            ((MarginsProto) this.instance).clearTopMils();
            return this;
        }

        @Override // android.service.print.MarginsProtoOrBuilder
        public boolean hasLeftMils() {
            return ((MarginsProto) this.instance).hasLeftMils();
        }

        @Override // android.service.print.MarginsProtoOrBuilder
        public int getLeftMils() {
            return ((MarginsProto) this.instance).getLeftMils();
        }

        public Builder setLeftMils(int value) {
            copyOnWrite();
            ((MarginsProto) this.instance).setLeftMils(value);
            return this;
        }

        public Builder clearLeftMils() {
            copyOnWrite();
            ((MarginsProto) this.instance).clearLeftMils();
            return this;
        }

        @Override // android.service.print.MarginsProtoOrBuilder
        public boolean hasRightMils() {
            return ((MarginsProto) this.instance).hasRightMils();
        }

        @Override // android.service.print.MarginsProtoOrBuilder
        public int getRightMils() {
            return ((MarginsProto) this.instance).getRightMils();
        }

        public Builder setRightMils(int value) {
            copyOnWrite();
            ((MarginsProto) this.instance).setRightMils(value);
            return this;
        }

        public Builder clearRightMils() {
            copyOnWrite();
            ((MarginsProto) this.instance).clearRightMils();
            return this;
        }

        @Override // android.service.print.MarginsProtoOrBuilder
        public boolean hasBottomMils() {
            return ((MarginsProto) this.instance).hasBottomMils();
        }

        @Override // android.service.print.MarginsProtoOrBuilder
        public int getBottomMils() {
            return ((MarginsProto) this.instance).getBottomMils();
        }

        public Builder setBottomMils(int value) {
            copyOnWrite();
            ((MarginsProto) this.instance).setBottomMils(value);
            return this;
        }

        public Builder clearBottomMils() {
            copyOnWrite();
            ((MarginsProto) this.instance).clearBottomMils();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new MarginsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                MarginsProto other = (MarginsProto) arg1;
                this.topMils_ = visitor.visitInt(hasTopMils(), this.topMils_, other.hasTopMils(), other.topMils_);
                this.leftMils_ = visitor.visitInt(hasLeftMils(), this.leftMils_, other.hasLeftMils(), other.leftMils_);
                this.rightMils_ = visitor.visitInt(hasRightMils(), this.rightMils_, other.hasRightMils(), other.rightMils_);
                this.bottomMils_ = visitor.visitInt(hasBottomMils(), this.bottomMils_, other.hasBottomMils(), other.bottomMils_);
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
                            this.topMils_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.leftMils_ = input.readInt32();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.rightMils_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.bottomMils_ = input.readInt32();
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
                    synchronized (MarginsProto.class) {
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

    public static MarginsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MarginsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
