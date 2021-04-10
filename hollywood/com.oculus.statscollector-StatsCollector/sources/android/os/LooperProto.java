package android.os;

import android.os.MessageQueueProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class LooperProto extends GeneratedMessageLite<LooperProto, Builder> implements LooperProtoOrBuilder {
    private static final LooperProto DEFAULT_INSTANCE = new LooperProto();
    private static volatile Parser<LooperProto> PARSER = null;
    public static final int QUEUE_FIELD_NUMBER = 3;
    public static final int THREAD_ID_FIELD_NUMBER = 2;
    public static final int THREAD_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private MessageQueueProto queue_;
    private long threadId_ = 0;
    private String threadName_ = "";

    private LooperProto() {
    }

    @Override // android.os.LooperProtoOrBuilder
    public boolean hasThreadName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.LooperProtoOrBuilder
    public String getThreadName() {
        return this.threadName_;
    }

    @Override // android.os.LooperProtoOrBuilder
    public ByteString getThreadNameBytes() {
        return ByteString.copyFromUtf8(this.threadName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setThreadName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.threadName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearThreadName() {
        this.bitField0_ &= -2;
        this.threadName_ = getDefaultInstance().getThreadName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setThreadNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.threadName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.LooperProtoOrBuilder
    public boolean hasThreadId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.LooperProtoOrBuilder
    public long getThreadId() {
        return this.threadId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setThreadId(long value) {
        this.bitField0_ |= 2;
        this.threadId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearThreadId() {
        this.bitField0_ &= -3;
        this.threadId_ = 0;
    }

    @Override // android.os.LooperProtoOrBuilder
    public boolean hasQueue() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.LooperProtoOrBuilder
    public MessageQueueProto getQueue() {
        MessageQueueProto messageQueueProto = this.queue_;
        return messageQueueProto == null ? MessageQueueProto.getDefaultInstance() : messageQueueProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setQueue(MessageQueueProto value) {
        if (value != null) {
            this.queue_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setQueue(MessageQueueProto.Builder builderForValue) {
        this.queue_ = (MessageQueueProto) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeQueue(MessageQueueProto value) {
        MessageQueueProto messageQueueProto = this.queue_;
        if (messageQueueProto == null || messageQueueProto == MessageQueueProto.getDefaultInstance()) {
            this.queue_ = value;
        } else {
            this.queue_ = (MessageQueueProto) ((MessageQueueProto.Builder) MessageQueueProto.newBuilder(this.queue_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearQueue() {
        this.queue_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getThreadName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.threadId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(3, getQueue());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getThreadName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.threadId_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(3, getQueue());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static LooperProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (LooperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LooperProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LooperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LooperProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (LooperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LooperProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LooperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LooperProto parseFrom(InputStream input) throws IOException {
        return (LooperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LooperProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LooperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LooperProto parseDelimitedFrom(InputStream input) throws IOException {
        return (LooperProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static LooperProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LooperProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LooperProto parseFrom(CodedInputStream input) throws IOException {
        return (LooperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LooperProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LooperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LooperProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LooperProto, Builder> implements LooperProtoOrBuilder {
        private Builder() {
            super(LooperProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.LooperProtoOrBuilder
        public boolean hasThreadName() {
            return ((LooperProto) this.instance).hasThreadName();
        }

        @Override // android.os.LooperProtoOrBuilder
        public String getThreadName() {
            return ((LooperProto) this.instance).getThreadName();
        }

        @Override // android.os.LooperProtoOrBuilder
        public ByteString getThreadNameBytes() {
            return ((LooperProto) this.instance).getThreadNameBytes();
        }

        public Builder setThreadName(String value) {
            copyOnWrite();
            ((LooperProto) this.instance).setThreadName(value);
            return this;
        }

        public Builder clearThreadName() {
            copyOnWrite();
            ((LooperProto) this.instance).clearThreadName();
            return this;
        }

        public Builder setThreadNameBytes(ByteString value) {
            copyOnWrite();
            ((LooperProto) this.instance).setThreadNameBytes(value);
            return this;
        }

        @Override // android.os.LooperProtoOrBuilder
        public boolean hasThreadId() {
            return ((LooperProto) this.instance).hasThreadId();
        }

        @Override // android.os.LooperProtoOrBuilder
        public long getThreadId() {
            return ((LooperProto) this.instance).getThreadId();
        }

        public Builder setThreadId(long value) {
            copyOnWrite();
            ((LooperProto) this.instance).setThreadId(value);
            return this;
        }

        public Builder clearThreadId() {
            copyOnWrite();
            ((LooperProto) this.instance).clearThreadId();
            return this;
        }

        @Override // android.os.LooperProtoOrBuilder
        public boolean hasQueue() {
            return ((LooperProto) this.instance).hasQueue();
        }

        @Override // android.os.LooperProtoOrBuilder
        public MessageQueueProto getQueue() {
            return ((LooperProto) this.instance).getQueue();
        }

        public Builder setQueue(MessageQueueProto value) {
            copyOnWrite();
            ((LooperProto) this.instance).setQueue((LooperProto) value);
            return this;
        }

        public Builder setQueue(MessageQueueProto.Builder builderForValue) {
            copyOnWrite();
            ((LooperProto) this.instance).setQueue((LooperProto) builderForValue);
            return this;
        }

        public Builder mergeQueue(MessageQueueProto value) {
            copyOnWrite();
            ((LooperProto) this.instance).mergeQueue(value);
            return this;
        }

        public Builder clearQueue() {
            copyOnWrite();
            ((LooperProto) this.instance).clearQueue();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new LooperProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                LooperProto other = (LooperProto) arg1;
                this.threadName_ = visitor.visitString(hasThreadName(), this.threadName_, other.hasThreadName(), other.threadName_);
                this.threadId_ = visitor.visitLong(hasThreadId(), this.threadId_, other.hasThreadId(), other.threadId_);
                this.queue_ = (MessageQueueProto) visitor.visitMessage(this.queue_, other.queue_);
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
                            String s = input.readString();
                            this.bitField0_ |= 1;
                            this.threadName_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.threadId_ = input.readInt64();
                        } else if (tag == 26) {
                            MessageQueueProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (MessageQueueProto.Builder) this.queue_.toBuilder();
                            }
                            this.queue_ = (MessageQueueProto) input.readMessage(MessageQueueProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.queue_);
                                this.queue_ = (MessageQueueProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
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
                    synchronized (LooperProto.class) {
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

    public static LooperProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LooperProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
