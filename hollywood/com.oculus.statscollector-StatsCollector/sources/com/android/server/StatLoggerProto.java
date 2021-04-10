package com.android.server;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class StatLoggerProto extends GeneratedMessageLite<StatLoggerProto, Builder> implements StatLoggerProtoOrBuilder {
    private static final StatLoggerProto DEFAULT_INSTANCE = new StatLoggerProto();
    public static final int EVENTS_FIELD_NUMBER = 1;
    private static volatile Parser<StatLoggerProto> PARSER;
    private Internal.ProtobufList<Event> events_ = emptyProtobufList();

    public interface EventOrBuilder extends MessageLiteOrBuilder {
        int getCount();

        int getEventId();

        String getLabel();

        ByteString getLabelBytes();

        long getTotalDurationMicros();

        boolean hasCount();

        boolean hasEventId();

        boolean hasLabel();

        boolean hasTotalDurationMicros();
    }

    private StatLoggerProto() {
    }

    public static final class Event extends GeneratedMessageLite<Event, Builder> implements EventOrBuilder {
        public static final int COUNT_FIELD_NUMBER = 3;
        private static final Event DEFAULT_INSTANCE = new Event();
        public static final int EVENT_ID_FIELD_NUMBER = 1;
        public static final int LABEL_FIELD_NUMBER = 2;
        private static volatile Parser<Event> PARSER = null;
        public static final int TOTAL_DURATION_MICROS_FIELD_NUMBER = 4;
        private int bitField0_;
        private int count_ = 0;
        private int eventId_ = 0;
        private String label_ = "";
        private long totalDurationMicros_ = 0;

        private Event() {
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public boolean hasEventId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public int getEventId() {
            return this.eventId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEventId(int value) {
            this.bitField0_ |= 1;
            this.eventId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEventId() {
            this.bitField0_ &= -2;
            this.eventId_ = 0;
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public boolean hasLabel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public String getLabel() {
            return this.label_;
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public ByteString getLabelBytes() {
            return ByteString.copyFromUtf8(this.label_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLabel(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.label_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLabel() {
            this.bitField0_ &= -3;
            this.label_ = getDefaultInstance().getLabel();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLabelBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.label_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public boolean hasCount() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public int getCount() {
            return this.count_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCount(int value) {
            this.bitField0_ |= 4;
            this.count_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCount() {
            this.bitField0_ &= -5;
            this.count_ = 0;
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public boolean hasTotalDurationMicros() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.StatLoggerProto.EventOrBuilder
        public long getTotalDurationMicros() {
            return this.totalDurationMicros_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalDurationMicros(long value) {
            this.bitField0_ |= 8;
            this.totalDurationMicros_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalDurationMicros() {
            this.bitField0_ &= -9;
            this.totalDurationMicros_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.eventId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getLabel());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.count_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.totalDurationMicros_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.eventId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getLabel());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.count_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.totalDurationMicros_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Event parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Event parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Event parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Event parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Event parseFrom(InputStream input) throws IOException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Event parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Event parseDelimitedFrom(InputStream input) throws IOException {
            return (Event) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Event parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Event) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Event parseFrom(CodedInputStream input) throws IOException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Event parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Event prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Event, Builder> implements EventOrBuilder {
            private Builder() {
                super(Event.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public boolean hasEventId() {
                return ((Event) this.instance).hasEventId();
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public int getEventId() {
                return ((Event) this.instance).getEventId();
            }

            public Builder setEventId(int value) {
                copyOnWrite();
                ((Event) this.instance).setEventId(value);
                return this;
            }

            public Builder clearEventId() {
                copyOnWrite();
                ((Event) this.instance).clearEventId();
                return this;
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public boolean hasLabel() {
                return ((Event) this.instance).hasLabel();
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public String getLabel() {
                return ((Event) this.instance).getLabel();
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public ByteString getLabelBytes() {
                return ((Event) this.instance).getLabelBytes();
            }

            public Builder setLabel(String value) {
                copyOnWrite();
                ((Event) this.instance).setLabel(value);
                return this;
            }

            public Builder clearLabel() {
                copyOnWrite();
                ((Event) this.instance).clearLabel();
                return this;
            }

            public Builder setLabelBytes(ByteString value) {
                copyOnWrite();
                ((Event) this.instance).setLabelBytes(value);
                return this;
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public boolean hasCount() {
                return ((Event) this.instance).hasCount();
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public int getCount() {
                return ((Event) this.instance).getCount();
            }

            public Builder setCount(int value) {
                copyOnWrite();
                ((Event) this.instance).setCount(value);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((Event) this.instance).clearCount();
                return this;
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public boolean hasTotalDurationMicros() {
                return ((Event) this.instance).hasTotalDurationMicros();
            }

            @Override // com.android.server.StatLoggerProto.EventOrBuilder
            public long getTotalDurationMicros() {
                return ((Event) this.instance).getTotalDurationMicros();
            }

            public Builder setTotalDurationMicros(long value) {
                copyOnWrite();
                ((Event) this.instance).setTotalDurationMicros(value);
                return this;
            }

            public Builder clearTotalDurationMicros() {
                copyOnWrite();
                ((Event) this.instance).clearTotalDurationMicros();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Event();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Event other = (Event) arg1;
                    this.eventId_ = visitor.visitInt(hasEventId(), this.eventId_, other.hasEventId(), other.eventId_);
                    this.label_ = visitor.visitString(hasLabel(), this.label_, other.hasLabel(), other.label_);
                    this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
                    this.totalDurationMicros_ = visitor.visitLong(hasTotalDurationMicros(), this.totalDurationMicros_, other.hasTotalDurationMicros(), other.totalDurationMicros_);
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
                                this.eventId_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.label_ = s;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.count_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.totalDurationMicros_ = input.readInt64();
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
                        synchronized (Event.class) {
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

        public static Event getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Event> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.StatLoggerProtoOrBuilder
    public List<Event> getEventsList() {
        return this.events_;
    }

    public List<? extends EventOrBuilder> getEventsOrBuilderList() {
        return this.events_;
    }

    @Override // com.android.server.StatLoggerProtoOrBuilder
    public int getEventsCount() {
        return this.events_.size();
    }

    @Override // com.android.server.StatLoggerProtoOrBuilder
    public Event getEvents(int index) {
        return this.events_.get(index);
    }

    public EventOrBuilder getEventsOrBuilder(int index) {
        return this.events_.get(index);
    }

    private void ensureEventsIsMutable() {
        if (!this.events_.isModifiable()) {
            this.events_ = GeneratedMessageLite.mutableCopy(this.events_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEvents(int index, Event value) {
        if (value != null) {
            ensureEventsIsMutable();
            this.events_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEvents(int index, Event.Builder builderForValue) {
        ensureEventsIsMutable();
        this.events_.set(index, (Event) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEvents(Event value) {
        if (value != null) {
            ensureEventsIsMutable();
            this.events_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEvents(int index, Event value) {
        if (value != null) {
            ensureEventsIsMutable();
            this.events_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEvents(Event.Builder builderForValue) {
        ensureEventsIsMutable();
        this.events_.add((Event) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEvents(int index, Event.Builder builderForValue) {
        ensureEventsIsMutable();
        this.events_.add(index, (Event) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllEvents(Iterable<? extends Event> values) {
        ensureEventsIsMutable();
        AbstractMessageLite.addAll(values, this.events_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEvents() {
        this.events_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeEvents(int index) {
        ensureEventsIsMutable();
        this.events_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.events_.size(); i++) {
            output.writeMessage(1, this.events_.get(i));
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
        for (int i = 0; i < this.events_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.events_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static StatLoggerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StatLoggerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StatLoggerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StatLoggerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StatLoggerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StatLoggerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StatLoggerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StatLoggerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StatLoggerProto parseFrom(InputStream input) throws IOException {
        return (StatLoggerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StatLoggerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatLoggerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StatLoggerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (StatLoggerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StatLoggerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatLoggerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StatLoggerProto parseFrom(CodedInputStream input) throws IOException {
        return (StatLoggerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StatLoggerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StatLoggerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StatLoggerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StatLoggerProto, Builder> implements StatLoggerProtoOrBuilder {
        private Builder() {
            super(StatLoggerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.StatLoggerProtoOrBuilder
        public List<Event> getEventsList() {
            return Collections.unmodifiableList(((StatLoggerProto) this.instance).getEventsList());
        }

        @Override // com.android.server.StatLoggerProtoOrBuilder
        public int getEventsCount() {
            return ((StatLoggerProto) this.instance).getEventsCount();
        }

        @Override // com.android.server.StatLoggerProtoOrBuilder
        public Event getEvents(int index) {
            return ((StatLoggerProto) this.instance).getEvents(index);
        }

        public Builder setEvents(int index, Event value) {
            copyOnWrite();
            ((StatLoggerProto) this.instance).setEvents((StatLoggerProto) index, (int) value);
            return this;
        }

        public Builder setEvents(int index, Event.Builder builderForValue) {
            copyOnWrite();
            ((StatLoggerProto) this.instance).setEvents((StatLoggerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addEvents(Event value) {
            copyOnWrite();
            ((StatLoggerProto) this.instance).addEvents((StatLoggerProto) value);
            return this;
        }

        public Builder addEvents(int index, Event value) {
            copyOnWrite();
            ((StatLoggerProto) this.instance).addEvents((StatLoggerProto) index, (int) value);
            return this;
        }

        public Builder addEvents(Event.Builder builderForValue) {
            copyOnWrite();
            ((StatLoggerProto) this.instance).addEvents((StatLoggerProto) builderForValue);
            return this;
        }

        public Builder addEvents(int index, Event.Builder builderForValue) {
            copyOnWrite();
            ((StatLoggerProto) this.instance).addEvents((StatLoggerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllEvents(Iterable<? extends Event> values) {
            copyOnWrite();
            ((StatLoggerProto) this.instance).addAllEvents(values);
            return this;
        }

        public Builder clearEvents() {
            copyOnWrite();
            ((StatLoggerProto) this.instance).clearEvents();
            return this;
        }

        public Builder removeEvents(int index) {
            copyOnWrite();
            ((StatLoggerProto) this.instance).removeEvents(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new StatLoggerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.events_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.events_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.events_, ((StatLoggerProto) arg1).events_);
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
                            if (!this.events_.isModifiable()) {
                                this.events_ = GeneratedMessageLite.mutableCopy(this.events_);
                            }
                            this.events_.add((Event) input.readMessage(Event.parser(), extensionRegistry));
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
                    synchronized (StatLoggerProto.class) {
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

    public static StatLoggerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StatLoggerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
