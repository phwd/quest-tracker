package android.os;

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

public final class BackTraceProto extends GeneratedMessageLite<BackTraceProto, Builder> implements BackTraceProtoOrBuilder {
    private static final BackTraceProto DEFAULT_INSTANCE = new BackTraceProto();
    private static volatile Parser<BackTraceProto> PARSER = null;
    public static final int TRACES_FIELD_NUMBER = 1;
    private Internal.ProtobufList<Stack> traces_ = emptyProtobufList();

    public interface StackOrBuilder extends MessageLiteOrBuilder {
        String getDump();

        ByteString getDumpBytes();

        long getDumpDurationNs();

        int getPid();

        boolean hasDump();

        boolean hasDumpDurationNs();

        boolean hasPid();
    }

    private BackTraceProto() {
    }

    public static final class Stack extends GeneratedMessageLite<Stack, Builder> implements StackOrBuilder {
        private static final Stack DEFAULT_INSTANCE = new Stack();
        public static final int DUMP_DURATION_NS_FIELD_NUMBER = 3;
        public static final int DUMP_FIELD_NUMBER = 2;
        private static volatile Parser<Stack> PARSER = null;
        public static final int PID_FIELD_NUMBER = 1;
        private int bitField0_;
        private long dumpDurationNs_ = 0;
        private String dump_ = "";
        private int pid_ = 0;

        private Stack() {
        }

        @Override // android.os.BackTraceProto.StackOrBuilder
        public boolean hasPid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.BackTraceProto.StackOrBuilder
        public int getPid() {
            return this.pid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPid(int value) {
            this.bitField0_ |= 1;
            this.pid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPid() {
            this.bitField0_ &= -2;
            this.pid_ = 0;
        }

        @Override // android.os.BackTraceProto.StackOrBuilder
        public boolean hasDump() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.BackTraceProto.StackOrBuilder
        public String getDump() {
            return this.dump_;
        }

        @Override // android.os.BackTraceProto.StackOrBuilder
        public ByteString getDumpBytes() {
            return ByteString.copyFromUtf8(this.dump_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDump(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.dump_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDump() {
            this.bitField0_ &= -3;
            this.dump_ = getDefaultInstance().getDump();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDumpBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.dump_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.BackTraceProto.StackOrBuilder
        public boolean hasDumpDurationNs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.BackTraceProto.StackOrBuilder
        public long getDumpDurationNs() {
            return this.dumpDurationNs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDumpDurationNs(long value) {
            this.bitField0_ |= 4;
            this.dumpDurationNs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDumpDurationNs() {
            this.bitField0_ &= -5;
            this.dumpDurationNs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.pid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getDump());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.dumpDurationNs_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.pid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getDump());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.dumpDurationNs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Stack parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Stack) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Stack parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Stack) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Stack parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Stack) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Stack parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Stack) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Stack parseFrom(InputStream input) throws IOException {
            return (Stack) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Stack parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Stack) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Stack parseDelimitedFrom(InputStream input) throws IOException {
            return (Stack) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Stack parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Stack) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Stack parseFrom(CodedInputStream input) throws IOException {
            return (Stack) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Stack parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Stack) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Stack prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Stack, Builder> implements StackOrBuilder {
            private Builder() {
                super(Stack.DEFAULT_INSTANCE);
            }

            @Override // android.os.BackTraceProto.StackOrBuilder
            public boolean hasPid() {
                return ((Stack) this.instance).hasPid();
            }

            @Override // android.os.BackTraceProto.StackOrBuilder
            public int getPid() {
                return ((Stack) this.instance).getPid();
            }

            public Builder setPid(int value) {
                copyOnWrite();
                ((Stack) this.instance).setPid(value);
                return this;
            }

            public Builder clearPid() {
                copyOnWrite();
                ((Stack) this.instance).clearPid();
                return this;
            }

            @Override // android.os.BackTraceProto.StackOrBuilder
            public boolean hasDump() {
                return ((Stack) this.instance).hasDump();
            }

            @Override // android.os.BackTraceProto.StackOrBuilder
            public String getDump() {
                return ((Stack) this.instance).getDump();
            }

            @Override // android.os.BackTraceProto.StackOrBuilder
            public ByteString getDumpBytes() {
                return ((Stack) this.instance).getDumpBytes();
            }

            public Builder setDump(String value) {
                copyOnWrite();
                ((Stack) this.instance).setDump(value);
                return this;
            }

            public Builder clearDump() {
                copyOnWrite();
                ((Stack) this.instance).clearDump();
                return this;
            }

            public Builder setDumpBytes(ByteString value) {
                copyOnWrite();
                ((Stack) this.instance).setDumpBytes(value);
                return this;
            }

            @Override // android.os.BackTraceProto.StackOrBuilder
            public boolean hasDumpDurationNs() {
                return ((Stack) this.instance).hasDumpDurationNs();
            }

            @Override // android.os.BackTraceProto.StackOrBuilder
            public long getDumpDurationNs() {
                return ((Stack) this.instance).getDumpDurationNs();
            }

            public Builder setDumpDurationNs(long value) {
                copyOnWrite();
                ((Stack) this.instance).setDumpDurationNs(value);
                return this;
            }

            public Builder clearDumpDurationNs() {
                copyOnWrite();
                ((Stack) this.instance).clearDumpDurationNs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Stack();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Stack other = (Stack) arg1;
                    this.pid_ = visitor.visitInt(hasPid(), this.pid_, other.hasPid(), other.pid_);
                    this.dump_ = visitor.visitString(hasDump(), this.dump_, other.hasDump(), other.dump_);
                    this.dumpDurationNs_ = visitor.visitLong(hasDumpDurationNs(), this.dumpDurationNs_, other.hasDumpDurationNs(), other.dumpDurationNs_);
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
                                this.pid_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.dump_ = s;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.dumpDurationNs_ = input.readInt64();
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
                        synchronized (Stack.class) {
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

        public static Stack getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Stack> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.BackTraceProtoOrBuilder
    public List<Stack> getTracesList() {
        return this.traces_;
    }

    public List<? extends StackOrBuilder> getTracesOrBuilderList() {
        return this.traces_;
    }

    @Override // android.os.BackTraceProtoOrBuilder
    public int getTracesCount() {
        return this.traces_.size();
    }

    @Override // android.os.BackTraceProtoOrBuilder
    public Stack getTraces(int index) {
        return this.traces_.get(index);
    }

    public StackOrBuilder getTracesOrBuilder(int index) {
        return this.traces_.get(index);
    }

    private void ensureTracesIsMutable() {
        if (!this.traces_.isModifiable()) {
            this.traces_ = GeneratedMessageLite.mutableCopy(this.traces_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTraces(int index, Stack value) {
        if (value != null) {
            ensureTracesIsMutable();
            this.traces_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTraces(int index, Stack.Builder builderForValue) {
        ensureTracesIsMutable();
        this.traces_.set(index, (Stack) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTraces(Stack value) {
        if (value != null) {
            ensureTracesIsMutable();
            this.traces_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTraces(int index, Stack value) {
        if (value != null) {
            ensureTracesIsMutable();
            this.traces_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTraces(Stack.Builder builderForValue) {
        ensureTracesIsMutable();
        this.traces_.add((Stack) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTraces(int index, Stack.Builder builderForValue) {
        ensureTracesIsMutable();
        this.traces_.add(index, (Stack) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTraces(Iterable<? extends Stack> values) {
        ensureTracesIsMutable();
        AbstractMessageLite.addAll(values, this.traces_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTraces() {
        this.traces_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTraces(int index) {
        ensureTracesIsMutable();
        this.traces_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.traces_.size(); i++) {
            output.writeMessage(1, this.traces_.get(i));
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
        for (int i = 0; i < this.traces_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.traces_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BackTraceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BackTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BackTraceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BackTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BackTraceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BackTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BackTraceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BackTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BackTraceProto parseFrom(InputStream input) throws IOException {
        return (BackTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BackTraceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BackTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BackTraceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BackTraceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BackTraceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BackTraceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BackTraceProto parseFrom(CodedInputStream input) throws IOException {
        return (BackTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BackTraceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BackTraceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BackTraceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BackTraceProto, Builder> implements BackTraceProtoOrBuilder {
        private Builder() {
            super(BackTraceProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.BackTraceProtoOrBuilder
        public List<Stack> getTracesList() {
            return Collections.unmodifiableList(((BackTraceProto) this.instance).getTracesList());
        }

        @Override // android.os.BackTraceProtoOrBuilder
        public int getTracesCount() {
            return ((BackTraceProto) this.instance).getTracesCount();
        }

        @Override // android.os.BackTraceProtoOrBuilder
        public Stack getTraces(int index) {
            return ((BackTraceProto) this.instance).getTraces(index);
        }

        public Builder setTraces(int index, Stack value) {
            copyOnWrite();
            ((BackTraceProto) this.instance).setTraces((BackTraceProto) index, (int) value);
            return this;
        }

        public Builder setTraces(int index, Stack.Builder builderForValue) {
            copyOnWrite();
            ((BackTraceProto) this.instance).setTraces((BackTraceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTraces(Stack value) {
            copyOnWrite();
            ((BackTraceProto) this.instance).addTraces((BackTraceProto) value);
            return this;
        }

        public Builder addTraces(int index, Stack value) {
            copyOnWrite();
            ((BackTraceProto) this.instance).addTraces((BackTraceProto) index, (int) value);
            return this;
        }

        public Builder addTraces(Stack.Builder builderForValue) {
            copyOnWrite();
            ((BackTraceProto) this.instance).addTraces((BackTraceProto) builderForValue);
            return this;
        }

        public Builder addTraces(int index, Stack.Builder builderForValue) {
            copyOnWrite();
            ((BackTraceProto) this.instance).addTraces((BackTraceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTraces(Iterable<? extends Stack> values) {
            copyOnWrite();
            ((BackTraceProto) this.instance).addAllTraces(values);
            return this;
        }

        public Builder clearTraces() {
            copyOnWrite();
            ((BackTraceProto) this.instance).clearTraces();
            return this;
        }

        public Builder removeTraces(int index) {
            copyOnWrite();
            ((BackTraceProto) this.instance).removeTraces(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BackTraceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.traces_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.traces_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.traces_, ((BackTraceProto) arg1).traces_);
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
                            if (!this.traces_.isModifiable()) {
                                this.traces_ = GeneratedMessageLite.mutableCopy(this.traces_);
                            }
                            this.traces_.add((Stack) input.readMessage(Stack.parser(), extensionRegistry));
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
                    synchronized (BackTraceProto.class) {
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

    public static BackTraceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BackTraceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
