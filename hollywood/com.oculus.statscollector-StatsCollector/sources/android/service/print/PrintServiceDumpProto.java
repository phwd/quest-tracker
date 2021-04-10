package android.service.print;

import android.service.print.PrintUserStateProto;
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

public final class PrintServiceDumpProto extends GeneratedMessageLite<PrintServiceDumpProto, Builder> implements PrintServiceDumpProtoOrBuilder {
    private static final PrintServiceDumpProto DEFAULT_INSTANCE = new PrintServiceDumpProto();
    private static volatile Parser<PrintServiceDumpProto> PARSER = null;
    public static final int USER_STATES_FIELD_NUMBER = 1;
    private Internal.ProtobufList<PrintUserStateProto> userStates_ = emptyProtobufList();

    private PrintServiceDumpProto() {
    }

    @Override // android.service.print.PrintServiceDumpProtoOrBuilder
    public List<PrintUserStateProto> getUserStatesList() {
        return this.userStates_;
    }

    public List<? extends PrintUserStateProtoOrBuilder> getUserStatesOrBuilderList() {
        return this.userStates_;
    }

    @Override // android.service.print.PrintServiceDumpProtoOrBuilder
    public int getUserStatesCount() {
        return this.userStates_.size();
    }

    @Override // android.service.print.PrintServiceDumpProtoOrBuilder
    public PrintUserStateProto getUserStates(int index) {
        return this.userStates_.get(index);
    }

    public PrintUserStateProtoOrBuilder getUserStatesOrBuilder(int index) {
        return this.userStates_.get(index);
    }

    private void ensureUserStatesIsMutable() {
        if (!this.userStates_.isModifiable()) {
            this.userStates_ = GeneratedMessageLite.mutableCopy(this.userStates_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserStates(int index, PrintUserStateProto value) {
        if (value != null) {
            ensureUserStatesIsMutable();
            this.userStates_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserStates(int index, PrintUserStateProto.Builder builderForValue) {
        ensureUserStatesIsMutable();
        this.userStates_.set(index, (PrintUserStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserStates(PrintUserStateProto value) {
        if (value != null) {
            ensureUserStatesIsMutable();
            this.userStates_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserStates(int index, PrintUserStateProto value) {
        if (value != null) {
            ensureUserStatesIsMutable();
            this.userStates_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserStates(PrintUserStateProto.Builder builderForValue) {
        ensureUserStatesIsMutable();
        this.userStates_.add((PrintUserStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUserStates(int index, PrintUserStateProto.Builder builderForValue) {
        ensureUserStatesIsMutable();
        this.userStates_.add(index, (PrintUserStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUserStates(Iterable<? extends PrintUserStateProto> values) {
        ensureUserStatesIsMutable();
        AbstractMessageLite.addAll(values, this.userStates_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserStates() {
        this.userStates_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUserStates(int index) {
        ensureUserStatesIsMutable();
        this.userStates_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.userStates_.size(); i++) {
            output.writeMessage(1, this.userStates_.get(i));
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
        for (int i = 0; i < this.userStates_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.userStates_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PrintServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (PrintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrintServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (PrintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrintServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrintServiceDumpProto, Builder> implements PrintServiceDumpProtoOrBuilder {
        private Builder() {
            super(PrintServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrintServiceDumpProtoOrBuilder
        public List<PrintUserStateProto> getUserStatesList() {
            return Collections.unmodifiableList(((PrintServiceDumpProto) this.instance).getUserStatesList());
        }

        @Override // android.service.print.PrintServiceDumpProtoOrBuilder
        public int getUserStatesCount() {
            return ((PrintServiceDumpProto) this.instance).getUserStatesCount();
        }

        @Override // android.service.print.PrintServiceDumpProtoOrBuilder
        public PrintUserStateProto getUserStates(int index) {
            return ((PrintServiceDumpProto) this.instance).getUserStates(index);
        }

        public Builder setUserStates(int index, PrintUserStateProto value) {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).setUserStates((PrintServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setUserStates(int index, PrintUserStateProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).setUserStates((PrintServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUserStates(PrintUserStateProto value) {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).addUserStates((PrintServiceDumpProto) value);
            return this;
        }

        public Builder addUserStates(int index, PrintUserStateProto value) {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).addUserStates((PrintServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addUserStates(PrintUserStateProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).addUserStates((PrintServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addUserStates(int index, PrintUserStateProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).addUserStates((PrintServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUserStates(Iterable<? extends PrintUserStateProto> values) {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).addAllUserStates(values);
            return this;
        }

        public Builder clearUserStates() {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).clearUserStates();
            return this;
        }

        public Builder removeUserStates(int index) {
            copyOnWrite();
            ((PrintServiceDumpProto) this.instance).removeUserStates(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrintServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.userStates_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.userStates_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.userStates_, ((PrintServiceDumpProto) arg1).userStates_);
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
                            if (!this.userStates_.isModifiable()) {
                                this.userStates_ = GeneratedMessageLite.mutableCopy(this.userStates_);
                            }
                            this.userStates_.add((PrintUserStateProto) input.readMessage(PrintUserStateProto.parser(), extensionRegistry));
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
                    synchronized (PrintServiceDumpProto.class) {
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

    public static PrintServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrintServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
