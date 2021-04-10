package android.os;

import android.os.MessageProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class MessageQueueProto extends GeneratedMessageLite<MessageQueueProto, Builder> implements MessageQueueProtoOrBuilder {
    private static final MessageQueueProto DEFAULT_INSTANCE = new MessageQueueProto();
    public static final int IS_POLLING_LOCKED_FIELD_NUMBER = 2;
    public static final int IS_QUITTING_FIELD_NUMBER = 3;
    public static final int MESSAGES_FIELD_NUMBER = 1;
    private static volatile Parser<MessageQueueProto> PARSER;
    private int bitField0_;
    private boolean isPollingLocked_ = false;
    private boolean isQuitting_ = false;
    private Internal.ProtobufList<MessageProto> messages_ = emptyProtobufList();

    private MessageQueueProto() {
    }

    @Override // android.os.MessageQueueProtoOrBuilder
    public List<MessageProto> getMessagesList() {
        return this.messages_;
    }

    public List<? extends MessageProtoOrBuilder> getMessagesOrBuilderList() {
        return this.messages_;
    }

    @Override // android.os.MessageQueueProtoOrBuilder
    public int getMessagesCount() {
        return this.messages_.size();
    }

    @Override // android.os.MessageQueueProtoOrBuilder
    public MessageProto getMessages(int index) {
        return this.messages_.get(index);
    }

    public MessageProtoOrBuilder getMessagesOrBuilder(int index) {
        return this.messages_.get(index);
    }

    private void ensureMessagesIsMutable() {
        if (!this.messages_.isModifiable()) {
            this.messages_ = GeneratedMessageLite.mutableCopy(this.messages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMessages(int index, MessageProto value) {
        if (value != null) {
            ensureMessagesIsMutable();
            this.messages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMessages(int index, MessageProto.Builder builderForValue) {
        ensureMessagesIsMutable();
        this.messages_.set(index, (MessageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMessages(MessageProto value) {
        if (value != null) {
            ensureMessagesIsMutable();
            this.messages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMessages(int index, MessageProto value) {
        if (value != null) {
            ensureMessagesIsMutable();
            this.messages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMessages(MessageProto.Builder builderForValue) {
        ensureMessagesIsMutable();
        this.messages_.add((MessageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMessages(int index, MessageProto.Builder builderForValue) {
        ensureMessagesIsMutable();
        this.messages_.add(index, (MessageProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllMessages(Iterable<? extends MessageProto> values) {
        ensureMessagesIsMutable();
        AbstractMessageLite.addAll(values, this.messages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMessages() {
        this.messages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeMessages(int index) {
        ensureMessagesIsMutable();
        this.messages_.remove(index);
    }

    @Override // android.os.MessageQueueProtoOrBuilder
    public boolean hasIsPollingLocked() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.MessageQueueProtoOrBuilder
    public boolean getIsPollingLocked() {
        return this.isPollingLocked_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPollingLocked(boolean value) {
        this.bitField0_ |= 1;
        this.isPollingLocked_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPollingLocked() {
        this.bitField0_ &= -2;
        this.isPollingLocked_ = false;
    }

    @Override // android.os.MessageQueueProtoOrBuilder
    public boolean hasIsQuitting() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.MessageQueueProtoOrBuilder
    public boolean getIsQuitting() {
        return this.isQuitting_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsQuitting(boolean value) {
        this.bitField0_ |= 2;
        this.isQuitting_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsQuitting() {
        this.bitField0_ &= -3;
        this.isQuitting_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.messages_.size(); i++) {
            output.writeMessage(1, this.messages_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(2, this.isPollingLocked_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(3, this.isQuitting_);
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
        for (int i = 0; i < this.messages_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.messages_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isPollingLocked_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(3, this.isQuitting_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static MessageQueueProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MessageQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MessageQueueProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MessageQueueProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MessageQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static MessageQueueProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static MessageQueueProto parseFrom(InputStream input) throws IOException {
        return (MessageQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MessageQueueProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MessageQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MessageQueueProto parseDelimitedFrom(InputStream input) throws IOException {
        return (MessageQueueProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static MessageQueueProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MessageQueueProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static MessageQueueProto parseFrom(CodedInputStream input) throws IOException {
        return (MessageQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static MessageQueueProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (MessageQueueProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MessageQueueProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MessageQueueProto, Builder> implements MessageQueueProtoOrBuilder {
        private Builder() {
            super(MessageQueueProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.MessageQueueProtoOrBuilder
        public List<MessageProto> getMessagesList() {
            return Collections.unmodifiableList(((MessageQueueProto) this.instance).getMessagesList());
        }

        @Override // android.os.MessageQueueProtoOrBuilder
        public int getMessagesCount() {
            return ((MessageQueueProto) this.instance).getMessagesCount();
        }

        @Override // android.os.MessageQueueProtoOrBuilder
        public MessageProto getMessages(int index) {
            return ((MessageQueueProto) this.instance).getMessages(index);
        }

        public Builder setMessages(int index, MessageProto value) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).setMessages((MessageQueueProto) index, (int) value);
            return this;
        }

        public Builder setMessages(int index, MessageProto.Builder builderForValue) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).setMessages((MessageQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addMessages(MessageProto value) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).addMessages((MessageQueueProto) value);
            return this;
        }

        public Builder addMessages(int index, MessageProto value) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).addMessages((MessageQueueProto) index, (int) value);
            return this;
        }

        public Builder addMessages(MessageProto.Builder builderForValue) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).addMessages((MessageQueueProto) builderForValue);
            return this;
        }

        public Builder addMessages(int index, MessageProto.Builder builderForValue) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).addMessages((MessageQueueProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllMessages(Iterable<? extends MessageProto> values) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).addAllMessages(values);
            return this;
        }

        public Builder clearMessages() {
            copyOnWrite();
            ((MessageQueueProto) this.instance).clearMessages();
            return this;
        }

        public Builder removeMessages(int index) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).removeMessages(index);
            return this;
        }

        @Override // android.os.MessageQueueProtoOrBuilder
        public boolean hasIsPollingLocked() {
            return ((MessageQueueProto) this.instance).hasIsPollingLocked();
        }

        @Override // android.os.MessageQueueProtoOrBuilder
        public boolean getIsPollingLocked() {
            return ((MessageQueueProto) this.instance).getIsPollingLocked();
        }

        public Builder setIsPollingLocked(boolean value) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).setIsPollingLocked(value);
            return this;
        }

        public Builder clearIsPollingLocked() {
            copyOnWrite();
            ((MessageQueueProto) this.instance).clearIsPollingLocked();
            return this;
        }

        @Override // android.os.MessageQueueProtoOrBuilder
        public boolean hasIsQuitting() {
            return ((MessageQueueProto) this.instance).hasIsQuitting();
        }

        @Override // android.os.MessageQueueProtoOrBuilder
        public boolean getIsQuitting() {
            return ((MessageQueueProto) this.instance).getIsQuitting();
        }

        public Builder setIsQuitting(boolean value) {
            copyOnWrite();
            ((MessageQueueProto) this.instance).setIsQuitting(value);
            return this;
        }

        public Builder clearIsQuitting() {
            copyOnWrite();
            ((MessageQueueProto) this.instance).clearIsQuitting();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new MessageQueueProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.messages_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                MessageQueueProto other = (MessageQueueProto) arg1;
                this.messages_ = visitor.visitList(this.messages_, other.messages_);
                this.isPollingLocked_ = visitor.visitBoolean(hasIsPollingLocked(), this.isPollingLocked_, other.hasIsPollingLocked(), other.isPollingLocked_);
                this.isQuitting_ = visitor.visitBoolean(hasIsQuitting(), this.isQuitting_, other.hasIsQuitting(), other.isQuitting_);
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
                            if (!this.messages_.isModifiable()) {
                                this.messages_ = GeneratedMessageLite.mutableCopy(this.messages_);
                            }
                            this.messages_.add((MessageProto) input.readMessage(MessageProto.parser(), extensionRegistry));
                        } else if (tag == 16) {
                            this.bitField0_ |= 1;
                            this.isPollingLocked_ = input.readBool();
                        } else if (tag == 24) {
                            this.bitField0_ |= 2;
                            this.isQuitting_ = input.readBool();
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
                    synchronized (MessageQueueProto.class) {
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

    public static MessageQueueProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MessageQueueProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
