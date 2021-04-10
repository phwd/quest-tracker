package android.util;

import android.util.BinaryLogEntry;
import android.util.TextLogEntry;
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

public final class LogProto extends GeneratedMessageLite<LogProto, Builder> implements LogProtoOrBuilder {
    public static final int BINARY_LOGS_FIELD_NUMBER = 2;
    private static final LogProto DEFAULT_INSTANCE = new LogProto();
    private static volatile Parser<LogProto> PARSER = null;
    public static final int TEXT_LOGS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<BinaryLogEntry> binaryLogs_ = emptyProtobufList();
    private Internal.ProtobufList<TextLogEntry> textLogs_ = emptyProtobufList();

    private LogProto() {
    }

    @Override // android.util.LogProtoOrBuilder
    public List<TextLogEntry> getTextLogsList() {
        return this.textLogs_;
    }

    public List<? extends TextLogEntryOrBuilder> getTextLogsOrBuilderList() {
        return this.textLogs_;
    }

    @Override // android.util.LogProtoOrBuilder
    public int getTextLogsCount() {
        return this.textLogs_.size();
    }

    @Override // android.util.LogProtoOrBuilder
    public TextLogEntry getTextLogs(int index) {
        return this.textLogs_.get(index);
    }

    public TextLogEntryOrBuilder getTextLogsOrBuilder(int index) {
        return this.textLogs_.get(index);
    }

    private void ensureTextLogsIsMutable() {
        if (!this.textLogs_.isModifiable()) {
            this.textLogs_ = GeneratedMessageLite.mutableCopy(this.textLogs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTextLogs(int index, TextLogEntry value) {
        if (value != null) {
            ensureTextLogsIsMutable();
            this.textLogs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTextLogs(int index, TextLogEntry.Builder builderForValue) {
        ensureTextLogsIsMutable();
        this.textLogs_.set(index, (TextLogEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTextLogs(TextLogEntry value) {
        if (value != null) {
            ensureTextLogsIsMutable();
            this.textLogs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTextLogs(int index, TextLogEntry value) {
        if (value != null) {
            ensureTextLogsIsMutable();
            this.textLogs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTextLogs(TextLogEntry.Builder builderForValue) {
        ensureTextLogsIsMutable();
        this.textLogs_.add((TextLogEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTextLogs(int index, TextLogEntry.Builder builderForValue) {
        ensureTextLogsIsMutable();
        this.textLogs_.add(index, (TextLogEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTextLogs(Iterable<? extends TextLogEntry> values) {
        ensureTextLogsIsMutable();
        AbstractMessageLite.addAll(values, this.textLogs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTextLogs() {
        this.textLogs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTextLogs(int index) {
        ensureTextLogsIsMutable();
        this.textLogs_.remove(index);
    }

    @Override // android.util.LogProtoOrBuilder
    public List<BinaryLogEntry> getBinaryLogsList() {
        return this.binaryLogs_;
    }

    public List<? extends BinaryLogEntryOrBuilder> getBinaryLogsOrBuilderList() {
        return this.binaryLogs_;
    }

    @Override // android.util.LogProtoOrBuilder
    public int getBinaryLogsCount() {
        return this.binaryLogs_.size();
    }

    @Override // android.util.LogProtoOrBuilder
    public BinaryLogEntry getBinaryLogs(int index) {
        return this.binaryLogs_.get(index);
    }

    public BinaryLogEntryOrBuilder getBinaryLogsOrBuilder(int index) {
        return this.binaryLogs_.get(index);
    }

    private void ensureBinaryLogsIsMutable() {
        if (!this.binaryLogs_.isModifiable()) {
            this.binaryLogs_ = GeneratedMessageLite.mutableCopy(this.binaryLogs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBinaryLogs(int index, BinaryLogEntry value) {
        if (value != null) {
            ensureBinaryLogsIsMutable();
            this.binaryLogs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBinaryLogs(int index, BinaryLogEntry.Builder builderForValue) {
        ensureBinaryLogsIsMutable();
        this.binaryLogs_.set(index, (BinaryLogEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBinaryLogs(BinaryLogEntry value) {
        if (value != null) {
            ensureBinaryLogsIsMutable();
            this.binaryLogs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBinaryLogs(int index, BinaryLogEntry value) {
        if (value != null) {
            ensureBinaryLogsIsMutable();
            this.binaryLogs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBinaryLogs(BinaryLogEntry.Builder builderForValue) {
        ensureBinaryLogsIsMutable();
        this.binaryLogs_.add((BinaryLogEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBinaryLogs(int index, BinaryLogEntry.Builder builderForValue) {
        ensureBinaryLogsIsMutable();
        this.binaryLogs_.add(index, (BinaryLogEntry) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBinaryLogs(Iterable<? extends BinaryLogEntry> values) {
        ensureBinaryLogsIsMutable();
        AbstractMessageLite.addAll(values, this.binaryLogs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBinaryLogs() {
        this.binaryLogs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeBinaryLogs(int index) {
        ensureBinaryLogsIsMutable();
        this.binaryLogs_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.textLogs_.size(); i++) {
            output.writeMessage(1, this.textLogs_.get(i));
        }
        for (int i2 = 0; i2 < this.binaryLogs_.size(); i2++) {
            output.writeMessage(2, this.binaryLogs_.get(i2));
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
        for (int i = 0; i < this.textLogs_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.textLogs_.get(i));
        }
        for (int i2 = 0; i2 < this.binaryLogs_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.binaryLogs_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static LogProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (LogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LogProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LogProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (LogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static LogProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static LogProto parseFrom(InputStream input) throws IOException {
        return (LogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LogProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LogProto parseDelimitedFrom(InputStream input) throws IOException {
        return (LogProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static LogProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LogProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static LogProto parseFrom(CodedInputStream input) throws IOException {
        return (LogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static LogProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LogProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LogProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LogProto, Builder> implements LogProtoOrBuilder {
        private Builder() {
            super(LogProto.DEFAULT_INSTANCE);
        }

        @Override // android.util.LogProtoOrBuilder
        public List<TextLogEntry> getTextLogsList() {
            return Collections.unmodifiableList(((LogProto) this.instance).getTextLogsList());
        }

        @Override // android.util.LogProtoOrBuilder
        public int getTextLogsCount() {
            return ((LogProto) this.instance).getTextLogsCount();
        }

        @Override // android.util.LogProtoOrBuilder
        public TextLogEntry getTextLogs(int index) {
            return ((LogProto) this.instance).getTextLogs(index);
        }

        public Builder setTextLogs(int index, TextLogEntry value) {
            copyOnWrite();
            ((LogProto) this.instance).setTextLogs((LogProto) index, (int) value);
            return this;
        }

        public Builder setTextLogs(int index, TextLogEntry.Builder builderForValue) {
            copyOnWrite();
            ((LogProto) this.instance).setTextLogs((LogProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTextLogs(TextLogEntry value) {
            copyOnWrite();
            ((LogProto) this.instance).addTextLogs((LogProto) value);
            return this;
        }

        public Builder addTextLogs(int index, TextLogEntry value) {
            copyOnWrite();
            ((LogProto) this.instance).addTextLogs((LogProto) index, (int) value);
            return this;
        }

        public Builder addTextLogs(TextLogEntry.Builder builderForValue) {
            copyOnWrite();
            ((LogProto) this.instance).addTextLogs((LogProto) builderForValue);
            return this;
        }

        public Builder addTextLogs(int index, TextLogEntry.Builder builderForValue) {
            copyOnWrite();
            ((LogProto) this.instance).addTextLogs((LogProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTextLogs(Iterable<? extends TextLogEntry> values) {
            copyOnWrite();
            ((LogProto) this.instance).addAllTextLogs(values);
            return this;
        }

        public Builder clearTextLogs() {
            copyOnWrite();
            ((LogProto) this.instance).clearTextLogs();
            return this;
        }

        public Builder removeTextLogs(int index) {
            copyOnWrite();
            ((LogProto) this.instance).removeTextLogs(index);
            return this;
        }

        @Override // android.util.LogProtoOrBuilder
        public List<BinaryLogEntry> getBinaryLogsList() {
            return Collections.unmodifiableList(((LogProto) this.instance).getBinaryLogsList());
        }

        @Override // android.util.LogProtoOrBuilder
        public int getBinaryLogsCount() {
            return ((LogProto) this.instance).getBinaryLogsCount();
        }

        @Override // android.util.LogProtoOrBuilder
        public BinaryLogEntry getBinaryLogs(int index) {
            return ((LogProto) this.instance).getBinaryLogs(index);
        }

        public Builder setBinaryLogs(int index, BinaryLogEntry value) {
            copyOnWrite();
            ((LogProto) this.instance).setBinaryLogs((LogProto) index, (int) value);
            return this;
        }

        public Builder setBinaryLogs(int index, BinaryLogEntry.Builder builderForValue) {
            copyOnWrite();
            ((LogProto) this.instance).setBinaryLogs((LogProto) index, (int) builderForValue);
            return this;
        }

        public Builder addBinaryLogs(BinaryLogEntry value) {
            copyOnWrite();
            ((LogProto) this.instance).addBinaryLogs((LogProto) value);
            return this;
        }

        public Builder addBinaryLogs(int index, BinaryLogEntry value) {
            copyOnWrite();
            ((LogProto) this.instance).addBinaryLogs((LogProto) index, (int) value);
            return this;
        }

        public Builder addBinaryLogs(BinaryLogEntry.Builder builderForValue) {
            copyOnWrite();
            ((LogProto) this.instance).addBinaryLogs((LogProto) builderForValue);
            return this;
        }

        public Builder addBinaryLogs(int index, BinaryLogEntry.Builder builderForValue) {
            copyOnWrite();
            ((LogProto) this.instance).addBinaryLogs((LogProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllBinaryLogs(Iterable<? extends BinaryLogEntry> values) {
            copyOnWrite();
            ((LogProto) this.instance).addAllBinaryLogs(values);
            return this;
        }

        public Builder clearBinaryLogs() {
            copyOnWrite();
            ((LogProto) this.instance).clearBinaryLogs();
            return this;
        }

        public Builder removeBinaryLogs(int index) {
            copyOnWrite();
            ((LogProto) this.instance).removeBinaryLogs(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new LogProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.textLogs_.makeImmutable();
                this.binaryLogs_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                LogProto other = (LogProto) arg1;
                this.textLogs_ = visitor.visitList(this.textLogs_, other.textLogs_);
                this.binaryLogs_ = visitor.visitList(this.binaryLogs_, other.binaryLogs_);
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
                            if (!this.textLogs_.isModifiable()) {
                                this.textLogs_ = GeneratedMessageLite.mutableCopy(this.textLogs_);
                            }
                            this.textLogs_.add((TextLogEntry) input.readMessage(TextLogEntry.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            if (!this.binaryLogs_.isModifiable()) {
                                this.binaryLogs_ = GeneratedMessageLite.mutableCopy(this.binaryLogs_);
                            }
                            this.binaryLogs_.add((BinaryLogEntry) input.readMessage(BinaryLogEntry.parser(), extensionRegistry));
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
                    synchronized (LogProto.class) {
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

    public static LogProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LogProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
