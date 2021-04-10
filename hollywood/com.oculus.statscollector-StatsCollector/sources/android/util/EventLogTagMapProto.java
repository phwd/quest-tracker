package android.util;

import android.util.EventLogTag;
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

public final class EventLogTagMapProto extends GeneratedMessageLite<EventLogTagMapProto, Builder> implements EventLogTagMapProtoOrBuilder {
    private static final EventLogTagMapProto DEFAULT_INSTANCE = new EventLogTagMapProto();
    public static final int EVENT_LOG_TAGS_FIELD_NUMBER = 1;
    private static volatile Parser<EventLogTagMapProto> PARSER;
    private Internal.ProtobufList<EventLogTag> eventLogTags_ = emptyProtobufList();

    private EventLogTagMapProto() {
    }

    @Override // android.util.EventLogTagMapProtoOrBuilder
    public List<EventLogTag> getEventLogTagsList() {
        return this.eventLogTags_;
    }

    public List<? extends EventLogTagOrBuilder> getEventLogTagsOrBuilderList() {
        return this.eventLogTags_;
    }

    @Override // android.util.EventLogTagMapProtoOrBuilder
    public int getEventLogTagsCount() {
        return this.eventLogTags_.size();
    }

    @Override // android.util.EventLogTagMapProtoOrBuilder
    public EventLogTag getEventLogTags(int index) {
        return this.eventLogTags_.get(index);
    }

    public EventLogTagOrBuilder getEventLogTagsOrBuilder(int index) {
        return this.eventLogTags_.get(index);
    }

    private void ensureEventLogTagsIsMutable() {
        if (!this.eventLogTags_.isModifiable()) {
            this.eventLogTags_ = GeneratedMessageLite.mutableCopy(this.eventLogTags_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEventLogTags(int index, EventLogTag value) {
        if (value != null) {
            ensureEventLogTagsIsMutable();
            this.eventLogTags_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEventLogTags(int index, EventLogTag.Builder builderForValue) {
        ensureEventLogTagsIsMutable();
        this.eventLogTags_.set(index, (EventLogTag) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEventLogTags(EventLogTag value) {
        if (value != null) {
            ensureEventLogTagsIsMutable();
            this.eventLogTags_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEventLogTags(int index, EventLogTag value) {
        if (value != null) {
            ensureEventLogTagsIsMutable();
            this.eventLogTags_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEventLogTags(EventLogTag.Builder builderForValue) {
        ensureEventLogTagsIsMutable();
        this.eventLogTags_.add((EventLogTag) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEventLogTags(int index, EventLogTag.Builder builderForValue) {
        ensureEventLogTagsIsMutable();
        this.eventLogTags_.add(index, (EventLogTag) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllEventLogTags(Iterable<? extends EventLogTag> values) {
        ensureEventLogTagsIsMutable();
        AbstractMessageLite.addAll(values, this.eventLogTags_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEventLogTags() {
        this.eventLogTags_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeEventLogTags(int index) {
        ensureEventLogTagsIsMutable();
        this.eventLogTags_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.eventLogTags_.size(); i++) {
            output.writeMessage(1, this.eventLogTags_.get(i));
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
        for (int i = 0; i < this.eventLogTags_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.eventLogTags_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static EventLogTagMapProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (EventLogTagMapProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static EventLogTagMapProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (EventLogTagMapProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static EventLogTagMapProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (EventLogTagMapProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static EventLogTagMapProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (EventLogTagMapProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static EventLogTagMapProto parseFrom(InputStream input) throws IOException {
        return (EventLogTagMapProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static EventLogTagMapProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EventLogTagMapProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static EventLogTagMapProto parseDelimitedFrom(InputStream input) throws IOException {
        return (EventLogTagMapProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static EventLogTagMapProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EventLogTagMapProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static EventLogTagMapProto parseFrom(CodedInputStream input) throws IOException {
        return (EventLogTagMapProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static EventLogTagMapProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EventLogTagMapProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EventLogTagMapProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<EventLogTagMapProto, Builder> implements EventLogTagMapProtoOrBuilder {
        private Builder() {
            super(EventLogTagMapProto.DEFAULT_INSTANCE);
        }

        @Override // android.util.EventLogTagMapProtoOrBuilder
        public List<EventLogTag> getEventLogTagsList() {
            return Collections.unmodifiableList(((EventLogTagMapProto) this.instance).getEventLogTagsList());
        }

        @Override // android.util.EventLogTagMapProtoOrBuilder
        public int getEventLogTagsCount() {
            return ((EventLogTagMapProto) this.instance).getEventLogTagsCount();
        }

        @Override // android.util.EventLogTagMapProtoOrBuilder
        public EventLogTag getEventLogTags(int index) {
            return ((EventLogTagMapProto) this.instance).getEventLogTags(index);
        }

        public Builder setEventLogTags(int index, EventLogTag value) {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).setEventLogTags((EventLogTagMapProto) index, (int) value);
            return this;
        }

        public Builder setEventLogTags(int index, EventLogTag.Builder builderForValue) {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).setEventLogTags((EventLogTagMapProto) index, (int) builderForValue);
            return this;
        }

        public Builder addEventLogTags(EventLogTag value) {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).addEventLogTags((EventLogTagMapProto) value);
            return this;
        }

        public Builder addEventLogTags(int index, EventLogTag value) {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).addEventLogTags((EventLogTagMapProto) index, (int) value);
            return this;
        }

        public Builder addEventLogTags(EventLogTag.Builder builderForValue) {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).addEventLogTags((EventLogTagMapProto) builderForValue);
            return this;
        }

        public Builder addEventLogTags(int index, EventLogTag.Builder builderForValue) {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).addEventLogTags((EventLogTagMapProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllEventLogTags(Iterable<? extends EventLogTag> values) {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).addAllEventLogTags(values);
            return this;
        }

        public Builder clearEventLogTags() {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).clearEventLogTags();
            return this;
        }

        public Builder removeEventLogTags(int index) {
            copyOnWrite();
            ((EventLogTagMapProto) this.instance).removeEventLogTags(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new EventLogTagMapProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.eventLogTags_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.eventLogTags_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.eventLogTags_, ((EventLogTagMapProto) arg1).eventLogTags_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                            if (!this.eventLogTags_.isModifiable()) {
                                this.eventLogTags_ = GeneratedMessageLite.mutableCopy(this.eventLogTags_);
                            }
                            this.eventLogTags_.add((EventLogTag) input.readMessage(EventLogTag.parser(), extensionRegistry));
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
                    synchronized (EventLogTagMapProto.class) {
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

    public static EventLogTagMapProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EventLogTagMapProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
