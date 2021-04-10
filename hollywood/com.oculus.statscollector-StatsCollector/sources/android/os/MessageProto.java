package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class MessageProto extends GeneratedMessageLite<MessageProto, Builder> implements MessageProtoOrBuilder {
    public static final int ARG1_FIELD_NUMBER = 4;
    public static final int ARG2_FIELD_NUMBER = 5;
    public static final int BARRIER_FIELD_NUMBER = 8;
    public static final int CALLBACK_FIELD_NUMBER = 2;
    private static final MessageProto DEFAULT_INSTANCE = new MessageProto();
    public static final int OBJ_FIELD_NUMBER = 6;
    private static volatile Parser<MessageProto> PARSER = null;
    public static final int TARGET_FIELD_NUMBER = 7;
    public static final int WHAT_FIELD_NUMBER = 3;
    public static final int WHEN_FIELD_NUMBER = 1;
    private int arg1_ = 0;
    private int arg2_ = 0;
    private int barrier_ = 0;
    private int bitField0_;
    private String callback_ = "";
    private String obj_ = "";
    private String target_ = "";
    private int what_ = 0;
    private long when_ = 0;

    private MessageProto() {
    }

    @Override // android.os.MessageProtoOrBuilder
    public boolean hasWhen() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.MessageProtoOrBuilder
    public long getWhen() {
        return this.when_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhen(long value) {
        this.bitField0_ |= 1;
        this.when_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWhen() {
        this.bitField0_ &= -2;
        this.when_ = 0;
    }

    @Override // android.os.MessageProtoOrBuilder
    public boolean hasCallback() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.MessageProtoOrBuilder
    public String getCallback() {
        return this.callback_;
    }

    @Override // android.os.MessageProtoOrBuilder
    public ByteString getCallbackBytes() {
        return ByteString.copyFromUtf8(this.callback_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCallback(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.callback_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCallback() {
        this.bitField0_ &= -3;
        this.callback_ = getDefaultInstance().getCallback();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCallbackBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.callback_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.MessageProtoOrBuilder
    public boolean hasWhat() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.MessageProtoOrBuilder
    public int getWhat() {
        return this.what_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhat(int value) {
        this.bitField0_ |= 4;
        this.what_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWhat() {
        this.bitField0_ &= -5;
        this.what_ = 0;
    }

    @Override // android.os.MessageProtoOrBuilder
    public boolean hasArg1() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.MessageProtoOrBuilder
    public int getArg1() {
        return this.arg1_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setArg1(int value) {
        this.bitField0_ |= 8;
        this.arg1_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearArg1() {
        this.bitField0_ &= -9;
        this.arg1_ = 0;
    }

    @Override // android.os.MessageProtoOrBuilder
    public boolean hasArg2() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.os.MessageProtoOrBuilder
    public int getArg2() {
        return this.arg2_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setArg2(int value) {
        this.bitField0_ |= 16;
        this.arg2_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearArg2() {
        this.bitField0_ &= -17;
        this.arg2_ = 0;
    }

    @Override // android.os.MessageProtoOrBuilder
    public boolean hasObj() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.os.MessageProtoOrBuilder
    public String getObj() {
        return this.obj_;
    }

    @Override // android.os.MessageProtoOrBuilder
    public ByteString getObjBytes() {
        return ByteString.copyFromUtf8(this.obj_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setObj(String value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.obj_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearObj() {
        this.bitField0_ &= -33;
        this.obj_ = getDefaultInstance().getObj();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setObjBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.obj_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.MessageProtoOrBuilder
    public boolean hasTarget() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.os.MessageProtoOrBuilder
    public String getTarget() {
        return this.target_;
    }

    @Override // android.os.MessageProtoOrBuilder
    public ByteString getTargetBytes() {
        return ByteString.copyFromUtf8(this.target_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTarget(String value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.target_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTarget() {
        this.bitField0_ &= -65;
        this.target_ = getDefaultInstance().getTarget();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTargetBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.target_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.MessageProtoOrBuilder
    public boolean hasBarrier() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.os.MessageProtoOrBuilder
    public int getBarrier() {
        return this.barrier_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBarrier(int value) {
        this.bitField0_ |= 128;
        this.barrier_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBarrier() {
        this.bitField0_ &= -129;
        this.barrier_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.when_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getCallback());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.what_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.arg1_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt32(5, this.arg2_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeString(6, getObj());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeString(7, getTarget());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.barrier_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.when_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getCallback());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.what_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.arg1_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt32Size(5, this.arg2_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeStringSize(6, getObj());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeStringSize(7, getTarget());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.barrier_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static MessageProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MessageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MessageProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MessageProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MessageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MessageProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MessageProto parseFrom(InputStream input) throws IOException {
        return (MessageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MessageProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MessageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MessageProto parseDelimitedFrom(InputStream input) throws IOException {
        return (MessageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MessageProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MessageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MessageProto parseFrom(CodedInputStream input) throws IOException {
        return (MessageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MessageProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MessageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MessageProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MessageProto, Builder> implements MessageProtoOrBuilder {
        private Builder() {
            super(MessageProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.MessageProtoOrBuilder
        public boolean hasWhen() {
            return ((MessageProto) this.instance).hasWhen();
        }

        @Override // android.os.MessageProtoOrBuilder
        public long getWhen() {
            return ((MessageProto) this.instance).getWhen();
        }

        public Builder setWhen(long value) {
            copyOnWrite();
            ((MessageProto) this.instance).setWhen(value);
            return this;
        }

        public Builder clearWhen() {
            copyOnWrite();
            ((MessageProto) this.instance).clearWhen();
            return this;
        }

        @Override // android.os.MessageProtoOrBuilder
        public boolean hasCallback() {
            return ((MessageProto) this.instance).hasCallback();
        }

        @Override // android.os.MessageProtoOrBuilder
        public String getCallback() {
            return ((MessageProto) this.instance).getCallback();
        }

        @Override // android.os.MessageProtoOrBuilder
        public ByteString getCallbackBytes() {
            return ((MessageProto) this.instance).getCallbackBytes();
        }

        public Builder setCallback(String value) {
            copyOnWrite();
            ((MessageProto) this.instance).setCallback(value);
            return this;
        }

        public Builder clearCallback() {
            copyOnWrite();
            ((MessageProto) this.instance).clearCallback();
            return this;
        }

        public Builder setCallbackBytes(ByteString value) {
            copyOnWrite();
            ((MessageProto) this.instance).setCallbackBytes(value);
            return this;
        }

        @Override // android.os.MessageProtoOrBuilder
        public boolean hasWhat() {
            return ((MessageProto) this.instance).hasWhat();
        }

        @Override // android.os.MessageProtoOrBuilder
        public int getWhat() {
            return ((MessageProto) this.instance).getWhat();
        }

        public Builder setWhat(int value) {
            copyOnWrite();
            ((MessageProto) this.instance).setWhat(value);
            return this;
        }

        public Builder clearWhat() {
            copyOnWrite();
            ((MessageProto) this.instance).clearWhat();
            return this;
        }

        @Override // android.os.MessageProtoOrBuilder
        public boolean hasArg1() {
            return ((MessageProto) this.instance).hasArg1();
        }

        @Override // android.os.MessageProtoOrBuilder
        public int getArg1() {
            return ((MessageProto) this.instance).getArg1();
        }

        public Builder setArg1(int value) {
            copyOnWrite();
            ((MessageProto) this.instance).setArg1(value);
            return this;
        }

        public Builder clearArg1() {
            copyOnWrite();
            ((MessageProto) this.instance).clearArg1();
            return this;
        }

        @Override // android.os.MessageProtoOrBuilder
        public boolean hasArg2() {
            return ((MessageProto) this.instance).hasArg2();
        }

        @Override // android.os.MessageProtoOrBuilder
        public int getArg2() {
            return ((MessageProto) this.instance).getArg2();
        }

        public Builder setArg2(int value) {
            copyOnWrite();
            ((MessageProto) this.instance).setArg2(value);
            return this;
        }

        public Builder clearArg2() {
            copyOnWrite();
            ((MessageProto) this.instance).clearArg2();
            return this;
        }

        @Override // android.os.MessageProtoOrBuilder
        public boolean hasObj() {
            return ((MessageProto) this.instance).hasObj();
        }

        @Override // android.os.MessageProtoOrBuilder
        public String getObj() {
            return ((MessageProto) this.instance).getObj();
        }

        @Override // android.os.MessageProtoOrBuilder
        public ByteString getObjBytes() {
            return ((MessageProto) this.instance).getObjBytes();
        }

        public Builder setObj(String value) {
            copyOnWrite();
            ((MessageProto) this.instance).setObj(value);
            return this;
        }

        public Builder clearObj() {
            copyOnWrite();
            ((MessageProto) this.instance).clearObj();
            return this;
        }

        public Builder setObjBytes(ByteString value) {
            copyOnWrite();
            ((MessageProto) this.instance).setObjBytes(value);
            return this;
        }

        @Override // android.os.MessageProtoOrBuilder
        public boolean hasTarget() {
            return ((MessageProto) this.instance).hasTarget();
        }

        @Override // android.os.MessageProtoOrBuilder
        public String getTarget() {
            return ((MessageProto) this.instance).getTarget();
        }

        @Override // android.os.MessageProtoOrBuilder
        public ByteString getTargetBytes() {
            return ((MessageProto) this.instance).getTargetBytes();
        }

        public Builder setTarget(String value) {
            copyOnWrite();
            ((MessageProto) this.instance).setTarget(value);
            return this;
        }

        public Builder clearTarget() {
            copyOnWrite();
            ((MessageProto) this.instance).clearTarget();
            return this;
        }

        public Builder setTargetBytes(ByteString value) {
            copyOnWrite();
            ((MessageProto) this.instance).setTargetBytes(value);
            return this;
        }

        @Override // android.os.MessageProtoOrBuilder
        public boolean hasBarrier() {
            return ((MessageProto) this.instance).hasBarrier();
        }

        @Override // android.os.MessageProtoOrBuilder
        public int getBarrier() {
            return ((MessageProto) this.instance).getBarrier();
        }

        public Builder setBarrier(int value) {
            copyOnWrite();
            ((MessageProto) this.instance).setBarrier(value);
            return this;
        }

        public Builder clearBarrier() {
            copyOnWrite();
            ((MessageProto) this.instance).clearBarrier();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new MessageProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                MessageProto other = (MessageProto) arg1;
                this.when_ = visitor.visitLong(hasWhen(), this.when_, other.hasWhen(), other.when_);
                this.callback_ = visitor.visitString(hasCallback(), this.callback_, other.hasCallback(), other.callback_);
                this.what_ = visitor.visitInt(hasWhat(), this.what_, other.hasWhat(), other.what_);
                this.arg1_ = visitor.visitInt(hasArg1(), this.arg1_, other.hasArg1(), other.arg1_);
                this.arg2_ = visitor.visitInt(hasArg2(), this.arg2_, other.hasArg2(), other.arg2_);
                this.obj_ = visitor.visitString(hasObj(), this.obj_, other.hasObj(), other.obj_);
                this.target_ = visitor.visitString(hasTarget(), this.target_, other.hasTarget(), other.target_);
                this.barrier_ = visitor.visitInt(hasBarrier(), this.barrier_, other.hasBarrier(), other.barrier_);
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
                            this.when_ = input.readInt64();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.callback_ = s;
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.what_ = input.readInt32();
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.arg1_ = input.readInt32();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
                            this.arg2_ = input.readInt32();
                        } else if (tag == 50) {
                            String s2 = input.readString();
                            this.bitField0_ = 32 | this.bitField0_;
                            this.obj_ = s2;
                        } else if (tag == 58) {
                            String s3 = input.readString();
                            this.bitField0_ = 64 | this.bitField0_;
                            this.target_ = s3;
                        } else if (tag == 64) {
                            this.bitField0_ |= 128;
                            this.barrier_ = input.readInt32();
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
                    synchronized (MessageProto.class) {
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

    public static MessageProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MessageProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
