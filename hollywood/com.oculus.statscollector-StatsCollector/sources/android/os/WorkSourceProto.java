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

public final class WorkSourceProto extends GeneratedMessageLite<WorkSourceProto, Builder> implements WorkSourceProtoOrBuilder {
    private static final WorkSourceProto DEFAULT_INSTANCE = new WorkSourceProto();
    private static volatile Parser<WorkSourceProto> PARSER = null;
    public static final int WORK_CHAINS_FIELD_NUMBER = 2;
    public static final int WORK_SOURCE_CONTENTS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<WorkChain> workChains_ = emptyProtobufList();
    private Internal.ProtobufList<WorkSourceContentProto> workSourceContents_ = emptyProtobufList();

    public interface WorkChainOrBuilder extends MessageLiteOrBuilder {
        WorkSourceContentProto getNodes(int i);

        int getNodesCount();

        List<WorkSourceContentProto> getNodesList();
    }

    public interface WorkSourceContentProtoOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        int getUid();

        boolean hasName();

        boolean hasUid();
    }

    private WorkSourceProto() {
    }

    public static final class WorkSourceContentProto extends GeneratedMessageLite<WorkSourceContentProto, Builder> implements WorkSourceContentProtoOrBuilder {
        private static final WorkSourceContentProto DEFAULT_INSTANCE = new WorkSourceContentProto();
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<WorkSourceContentProto> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private String name_ = "";
        private int uid_ = 0;

        private WorkSourceContentProto() {
        }

        @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 1;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -2;
            this.uid_ = 0;
        }

        @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setName(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.name_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearName() {
            this.bitField0_ &= -3;
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.name_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getName());
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getName());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WorkSourceContentProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WorkSourceContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WorkSourceContentProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WorkSourceContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WorkSourceContentProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WorkSourceContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WorkSourceContentProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WorkSourceContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WorkSourceContentProto parseFrom(InputStream input) throws IOException {
            return (WorkSourceContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WorkSourceContentProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WorkSourceContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WorkSourceContentProto parseDelimitedFrom(InputStream input) throws IOException {
            return (WorkSourceContentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WorkSourceContentProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WorkSourceContentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WorkSourceContentProto parseFrom(CodedInputStream input) throws IOException {
            return (WorkSourceContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WorkSourceContentProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WorkSourceContentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WorkSourceContentProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WorkSourceContentProto, Builder> implements WorkSourceContentProtoOrBuilder {
            private Builder() {
                super(WorkSourceContentProto.DEFAULT_INSTANCE);
            }

            @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
            public boolean hasUid() {
                return ((WorkSourceContentProto) this.instance).hasUid();
            }

            @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
            public int getUid() {
                return ((WorkSourceContentProto) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((WorkSourceContentProto) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((WorkSourceContentProto) this.instance).clearUid();
                return this;
            }

            @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
            public boolean hasName() {
                return ((WorkSourceContentProto) this.instance).hasName();
            }

            @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
            public String getName() {
                return ((WorkSourceContentProto) this.instance).getName();
            }

            @Override // android.os.WorkSourceProto.WorkSourceContentProtoOrBuilder
            public ByteString getNameBytes() {
                return ((WorkSourceContentProto) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((WorkSourceContentProto) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((WorkSourceContentProto) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((WorkSourceContentProto) this.instance).setNameBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WorkSourceContentProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WorkSourceContentProto other = (WorkSourceContentProto) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
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
                                this.uid_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.name_ = s;
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
                        synchronized (WorkSourceContentProto.class) {
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

        public static WorkSourceContentProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WorkSourceContentProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WorkChain extends GeneratedMessageLite<WorkChain, Builder> implements WorkChainOrBuilder {
        private static final WorkChain DEFAULT_INSTANCE = new WorkChain();
        public static final int NODES_FIELD_NUMBER = 1;
        private static volatile Parser<WorkChain> PARSER;
        private Internal.ProtobufList<WorkSourceContentProto> nodes_ = emptyProtobufList();

        private WorkChain() {
        }

        @Override // android.os.WorkSourceProto.WorkChainOrBuilder
        public List<WorkSourceContentProto> getNodesList() {
            return this.nodes_;
        }

        public List<? extends WorkSourceContentProtoOrBuilder> getNodesOrBuilderList() {
            return this.nodes_;
        }

        @Override // android.os.WorkSourceProto.WorkChainOrBuilder
        public int getNodesCount() {
            return this.nodes_.size();
        }

        @Override // android.os.WorkSourceProto.WorkChainOrBuilder
        public WorkSourceContentProto getNodes(int index) {
            return this.nodes_.get(index);
        }

        public WorkSourceContentProtoOrBuilder getNodesOrBuilder(int index) {
            return this.nodes_.get(index);
        }

        private void ensureNodesIsMutable() {
            if (!this.nodes_.isModifiable()) {
                this.nodes_ = GeneratedMessageLite.mutableCopy(this.nodes_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNodes(int index, WorkSourceContentProto value) {
            if (value != null) {
                ensureNodesIsMutable();
                this.nodes_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNodes(int index, WorkSourceContentProto.Builder builderForValue) {
            ensureNodesIsMutable();
            this.nodes_.set(index, (WorkSourceContentProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addNodes(WorkSourceContentProto value) {
            if (value != null) {
                ensureNodesIsMutable();
                this.nodes_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addNodes(int index, WorkSourceContentProto value) {
            if (value != null) {
                ensureNodesIsMutable();
                this.nodes_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addNodes(WorkSourceContentProto.Builder builderForValue) {
            ensureNodesIsMutable();
            this.nodes_.add((WorkSourceContentProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addNodes(int index, WorkSourceContentProto.Builder builderForValue) {
            ensureNodesIsMutable();
            this.nodes_.add(index, (WorkSourceContentProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllNodes(Iterable<? extends WorkSourceContentProto> values) {
            ensureNodesIsMutable();
            AbstractMessageLite.addAll(values, this.nodes_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNodes() {
            this.nodes_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeNodes(int index) {
            ensureNodesIsMutable();
            this.nodes_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.nodes_.size(); i++) {
                output.writeMessage(1, this.nodes_.get(i));
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
            for (int i = 0; i < this.nodes_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.nodes_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WorkChain parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WorkChain) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WorkChain parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WorkChain) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WorkChain parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WorkChain) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WorkChain parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WorkChain) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WorkChain parseFrom(InputStream input) throws IOException {
            return (WorkChain) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WorkChain parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WorkChain) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WorkChain parseDelimitedFrom(InputStream input) throws IOException {
            return (WorkChain) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WorkChain parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WorkChain) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WorkChain parseFrom(CodedInputStream input) throws IOException {
            return (WorkChain) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WorkChain parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WorkChain) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WorkChain prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WorkChain, Builder> implements WorkChainOrBuilder {
            private Builder() {
                super(WorkChain.DEFAULT_INSTANCE);
            }

            @Override // android.os.WorkSourceProto.WorkChainOrBuilder
            public List<WorkSourceContentProto> getNodesList() {
                return Collections.unmodifiableList(((WorkChain) this.instance).getNodesList());
            }

            @Override // android.os.WorkSourceProto.WorkChainOrBuilder
            public int getNodesCount() {
                return ((WorkChain) this.instance).getNodesCount();
            }

            @Override // android.os.WorkSourceProto.WorkChainOrBuilder
            public WorkSourceContentProto getNodes(int index) {
                return ((WorkChain) this.instance).getNodes(index);
            }

            public Builder setNodes(int index, WorkSourceContentProto value) {
                copyOnWrite();
                ((WorkChain) this.instance).setNodes((WorkChain) index, (int) value);
                return this;
            }

            public Builder setNodes(int index, WorkSourceContentProto.Builder builderForValue) {
                copyOnWrite();
                ((WorkChain) this.instance).setNodes((WorkChain) index, (int) builderForValue);
                return this;
            }

            public Builder addNodes(WorkSourceContentProto value) {
                copyOnWrite();
                ((WorkChain) this.instance).addNodes((WorkChain) value);
                return this;
            }

            public Builder addNodes(int index, WorkSourceContentProto value) {
                copyOnWrite();
                ((WorkChain) this.instance).addNodes((WorkChain) index, (int) value);
                return this;
            }

            public Builder addNodes(WorkSourceContentProto.Builder builderForValue) {
                copyOnWrite();
                ((WorkChain) this.instance).addNodes((WorkChain) builderForValue);
                return this;
            }

            public Builder addNodes(int index, WorkSourceContentProto.Builder builderForValue) {
                copyOnWrite();
                ((WorkChain) this.instance).addNodes((WorkChain) index, (int) builderForValue);
                return this;
            }

            public Builder addAllNodes(Iterable<? extends WorkSourceContentProto> values) {
                copyOnWrite();
                ((WorkChain) this.instance).addAllNodes(values);
                return this;
            }

            public Builder clearNodes() {
                copyOnWrite();
                ((WorkChain) this.instance).clearNodes();
                return this;
            }

            public Builder removeNodes(int index) {
                copyOnWrite();
                ((WorkChain) this.instance).removeNodes(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WorkChain();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.nodes_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    this.nodes_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.nodes_, ((WorkChain) arg1).nodes_);
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
                                if (!this.nodes_.isModifiable()) {
                                    this.nodes_ = GeneratedMessageLite.mutableCopy(this.nodes_);
                                }
                                this.nodes_.add((WorkSourceContentProto) input.readMessage(WorkSourceContentProto.parser(), extensionRegistry));
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
                        synchronized (WorkChain.class) {
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

        public static WorkChain getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WorkChain> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.WorkSourceProtoOrBuilder
    public List<WorkSourceContentProto> getWorkSourceContentsList() {
        return this.workSourceContents_;
    }

    public List<? extends WorkSourceContentProtoOrBuilder> getWorkSourceContentsOrBuilderList() {
        return this.workSourceContents_;
    }

    @Override // android.os.WorkSourceProtoOrBuilder
    public int getWorkSourceContentsCount() {
        return this.workSourceContents_.size();
    }

    @Override // android.os.WorkSourceProtoOrBuilder
    public WorkSourceContentProto getWorkSourceContents(int index) {
        return this.workSourceContents_.get(index);
    }

    public WorkSourceContentProtoOrBuilder getWorkSourceContentsOrBuilder(int index) {
        return this.workSourceContents_.get(index);
    }

    private void ensureWorkSourceContentsIsMutable() {
        if (!this.workSourceContents_.isModifiable()) {
            this.workSourceContents_ = GeneratedMessageLite.mutableCopy(this.workSourceContents_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWorkSourceContents(int index, WorkSourceContentProto value) {
        if (value != null) {
            ensureWorkSourceContentsIsMutable();
            this.workSourceContents_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWorkSourceContents(int index, WorkSourceContentProto.Builder builderForValue) {
        ensureWorkSourceContentsIsMutable();
        this.workSourceContents_.set(index, (WorkSourceContentProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWorkSourceContents(WorkSourceContentProto value) {
        if (value != null) {
            ensureWorkSourceContentsIsMutable();
            this.workSourceContents_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWorkSourceContents(int index, WorkSourceContentProto value) {
        if (value != null) {
            ensureWorkSourceContentsIsMutable();
            this.workSourceContents_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWorkSourceContents(WorkSourceContentProto.Builder builderForValue) {
        ensureWorkSourceContentsIsMutable();
        this.workSourceContents_.add((WorkSourceContentProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWorkSourceContents(int index, WorkSourceContentProto.Builder builderForValue) {
        ensureWorkSourceContentsIsMutable();
        this.workSourceContents_.add(index, (WorkSourceContentProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWorkSourceContents(Iterable<? extends WorkSourceContentProto> values) {
        ensureWorkSourceContentsIsMutable();
        AbstractMessageLite.addAll(values, this.workSourceContents_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWorkSourceContents() {
        this.workSourceContents_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWorkSourceContents(int index) {
        ensureWorkSourceContentsIsMutable();
        this.workSourceContents_.remove(index);
    }

    @Override // android.os.WorkSourceProtoOrBuilder
    public List<WorkChain> getWorkChainsList() {
        return this.workChains_;
    }

    public List<? extends WorkChainOrBuilder> getWorkChainsOrBuilderList() {
        return this.workChains_;
    }

    @Override // android.os.WorkSourceProtoOrBuilder
    public int getWorkChainsCount() {
        return this.workChains_.size();
    }

    @Override // android.os.WorkSourceProtoOrBuilder
    public WorkChain getWorkChains(int index) {
        return this.workChains_.get(index);
    }

    public WorkChainOrBuilder getWorkChainsOrBuilder(int index) {
        return this.workChains_.get(index);
    }

    private void ensureWorkChainsIsMutable() {
        if (!this.workChains_.isModifiable()) {
            this.workChains_ = GeneratedMessageLite.mutableCopy(this.workChains_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWorkChains(int index, WorkChain value) {
        if (value != null) {
            ensureWorkChainsIsMutable();
            this.workChains_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWorkChains(int index, WorkChain.Builder builderForValue) {
        ensureWorkChainsIsMutable();
        this.workChains_.set(index, (WorkChain) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWorkChains(WorkChain value) {
        if (value != null) {
            ensureWorkChainsIsMutable();
            this.workChains_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWorkChains(int index, WorkChain value) {
        if (value != null) {
            ensureWorkChainsIsMutable();
            this.workChains_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWorkChains(WorkChain.Builder builderForValue) {
        ensureWorkChainsIsMutable();
        this.workChains_.add((WorkChain) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWorkChains(int index, WorkChain.Builder builderForValue) {
        ensureWorkChainsIsMutable();
        this.workChains_.add(index, (WorkChain) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWorkChains(Iterable<? extends WorkChain> values) {
        ensureWorkChainsIsMutable();
        AbstractMessageLite.addAll(values, this.workChains_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWorkChains() {
        this.workChains_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWorkChains(int index) {
        ensureWorkChainsIsMutable();
        this.workChains_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.workSourceContents_.size(); i++) {
            output.writeMessage(1, this.workSourceContents_.get(i));
        }
        for (int i2 = 0; i2 < this.workChains_.size(); i2++) {
            output.writeMessage(2, this.workChains_.get(i2));
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
        for (int i = 0; i < this.workSourceContents_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.workSourceContents_.get(i));
        }
        for (int i2 = 0; i2 < this.workChains_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.workChains_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static WorkSourceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (WorkSourceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WorkSourceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WorkSourceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WorkSourceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (WorkSourceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static WorkSourceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (WorkSourceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static WorkSourceProto parseFrom(InputStream input) throws IOException {
        return (WorkSourceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WorkSourceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WorkSourceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WorkSourceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (WorkSourceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static WorkSourceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WorkSourceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static WorkSourceProto parseFrom(CodedInputStream input) throws IOException {
        return (WorkSourceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static WorkSourceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (WorkSourceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WorkSourceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WorkSourceProto, Builder> implements WorkSourceProtoOrBuilder {
        private Builder() {
            super(WorkSourceProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.WorkSourceProtoOrBuilder
        public List<WorkSourceContentProto> getWorkSourceContentsList() {
            return Collections.unmodifiableList(((WorkSourceProto) this.instance).getWorkSourceContentsList());
        }

        @Override // android.os.WorkSourceProtoOrBuilder
        public int getWorkSourceContentsCount() {
            return ((WorkSourceProto) this.instance).getWorkSourceContentsCount();
        }

        @Override // android.os.WorkSourceProtoOrBuilder
        public WorkSourceContentProto getWorkSourceContents(int index) {
            return ((WorkSourceProto) this.instance).getWorkSourceContents(index);
        }

        public Builder setWorkSourceContents(int index, WorkSourceContentProto value) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).setWorkSourceContents((WorkSourceProto) index, (int) value);
            return this;
        }

        public Builder setWorkSourceContents(int index, WorkSourceContentProto.Builder builderForValue) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).setWorkSourceContents((WorkSourceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWorkSourceContents(WorkSourceContentProto value) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addWorkSourceContents((WorkSourceProto) value);
            return this;
        }

        public Builder addWorkSourceContents(int index, WorkSourceContentProto value) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addWorkSourceContents((WorkSourceProto) index, (int) value);
            return this;
        }

        public Builder addWorkSourceContents(WorkSourceContentProto.Builder builderForValue) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addWorkSourceContents((WorkSourceProto) builderForValue);
            return this;
        }

        public Builder addWorkSourceContents(int index, WorkSourceContentProto.Builder builderForValue) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addWorkSourceContents((WorkSourceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWorkSourceContents(Iterable<? extends WorkSourceContentProto> values) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addAllWorkSourceContents(values);
            return this;
        }

        public Builder clearWorkSourceContents() {
            copyOnWrite();
            ((WorkSourceProto) this.instance).clearWorkSourceContents();
            return this;
        }

        public Builder removeWorkSourceContents(int index) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).removeWorkSourceContents(index);
            return this;
        }

        @Override // android.os.WorkSourceProtoOrBuilder
        public List<WorkChain> getWorkChainsList() {
            return Collections.unmodifiableList(((WorkSourceProto) this.instance).getWorkChainsList());
        }

        @Override // android.os.WorkSourceProtoOrBuilder
        public int getWorkChainsCount() {
            return ((WorkSourceProto) this.instance).getWorkChainsCount();
        }

        @Override // android.os.WorkSourceProtoOrBuilder
        public WorkChain getWorkChains(int index) {
            return ((WorkSourceProto) this.instance).getWorkChains(index);
        }

        public Builder setWorkChains(int index, WorkChain value) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).setWorkChains((WorkSourceProto) index, (int) value);
            return this;
        }

        public Builder setWorkChains(int index, WorkChain.Builder builderForValue) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).setWorkChains((WorkSourceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWorkChains(WorkChain value) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addWorkChains((WorkSourceProto) value);
            return this;
        }

        public Builder addWorkChains(int index, WorkChain value) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addWorkChains((WorkSourceProto) index, (int) value);
            return this;
        }

        public Builder addWorkChains(WorkChain.Builder builderForValue) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addWorkChains((WorkSourceProto) builderForValue);
            return this;
        }

        public Builder addWorkChains(int index, WorkChain.Builder builderForValue) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addWorkChains((WorkSourceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWorkChains(Iterable<? extends WorkChain> values) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).addAllWorkChains(values);
            return this;
        }

        public Builder clearWorkChains() {
            copyOnWrite();
            ((WorkSourceProto) this.instance).clearWorkChains();
            return this;
        }

        public Builder removeWorkChains(int index) {
            copyOnWrite();
            ((WorkSourceProto) this.instance).removeWorkChains(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new WorkSourceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.workSourceContents_.makeImmutable();
                this.workChains_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                WorkSourceProto other = (WorkSourceProto) arg1;
                this.workSourceContents_ = visitor.visitList(this.workSourceContents_, other.workSourceContents_);
                this.workChains_ = visitor.visitList(this.workChains_, other.workChains_);
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
                            if (!this.workSourceContents_.isModifiable()) {
                                this.workSourceContents_ = GeneratedMessageLite.mutableCopy(this.workSourceContents_);
                            }
                            this.workSourceContents_.add((WorkSourceContentProto) input.readMessage(WorkSourceContentProto.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            if (!this.workChains_.isModifiable()) {
                                this.workChains_ = GeneratedMessageLite.mutableCopy(this.workChains_);
                            }
                            this.workChains_.add((WorkChain) input.readMessage(WorkChain.parser(), extensionRegistry));
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
                    synchronized (WorkSourceProto.class) {
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

    public static WorkSourceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WorkSourceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
