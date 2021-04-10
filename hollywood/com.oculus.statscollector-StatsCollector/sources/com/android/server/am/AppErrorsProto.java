package com.android.server.am;

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

public final class AppErrorsProto extends GeneratedMessageLite<AppErrorsProto, Builder> implements AppErrorsProtoOrBuilder {
    public static final int BAD_PROCESSES_FIELD_NUMBER = 3;
    private static final AppErrorsProto DEFAULT_INSTANCE = new AppErrorsProto();
    public static final int NOW_UPTIME_MS_FIELD_NUMBER = 1;
    private static volatile Parser<AppErrorsProto> PARSER = null;
    public static final int PROCESS_CRASH_TIMES_FIELD_NUMBER = 2;
    private Internal.ProtobufList<BadProcess> badProcesses_ = emptyProtobufList();
    private int bitField0_;
    private long nowUptimeMs_ = 0;
    private Internal.ProtobufList<ProcessCrashTime> processCrashTimes_ = emptyProtobufList();

    public interface BadProcessOrBuilder extends MessageLiteOrBuilder {
        BadProcess.Entry getEntries(int i);

        int getEntriesCount();

        List<BadProcess.Entry> getEntriesList();

        String getProcessName();

        ByteString getProcessNameBytes();

        boolean hasProcessName();
    }

    public interface ProcessCrashTimeOrBuilder extends MessageLiteOrBuilder {
        ProcessCrashTime.Entry getEntries(int i);

        int getEntriesCount();

        List<ProcessCrashTime.Entry> getEntriesList();

        String getProcessName();

        ByteString getProcessNameBytes();

        boolean hasProcessName();
    }

    private AppErrorsProto() {
    }

    public static final class ProcessCrashTime extends GeneratedMessageLite<ProcessCrashTime, Builder> implements ProcessCrashTimeOrBuilder {
        private static final ProcessCrashTime DEFAULT_INSTANCE = new ProcessCrashTime();
        public static final int ENTRIES_FIELD_NUMBER = 2;
        private static volatile Parser<ProcessCrashTime> PARSER = null;
        public static final int PROCESS_NAME_FIELD_NUMBER = 1;
        private int bitField0_;
        private Internal.ProtobufList<Entry> entries_ = emptyProtobufList();
        private String processName_ = "";

        public interface EntryOrBuilder extends MessageLiteOrBuilder {
            long getLastCrashedAtMs();

            int getUid();

            boolean hasLastCrashedAtMs();

            boolean hasUid();
        }

        private ProcessCrashTime() {
        }

        public static final class Entry extends GeneratedMessageLite<Entry, Builder> implements EntryOrBuilder {
            private static final Entry DEFAULT_INSTANCE = new Entry();
            public static final int LAST_CRASHED_AT_MS_FIELD_NUMBER = 2;
            private static volatile Parser<Entry> PARSER = null;
            public static final int UID_FIELD_NUMBER = 1;
            private int bitField0_;
            private long lastCrashedAtMs_ = 0;
            private int uid_ = 0;

            private Entry() {
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTime.EntryOrBuilder
            public boolean hasUid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTime.EntryOrBuilder
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

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTime.EntryOrBuilder
            public boolean hasLastCrashedAtMs() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTime.EntryOrBuilder
            public long getLastCrashedAtMs() {
                return this.lastCrashedAtMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setLastCrashedAtMs(long value) {
                this.bitField0_ |= 2;
                this.lastCrashedAtMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearLastCrashedAtMs() {
                this.bitField0_ &= -3;
                this.lastCrashedAtMs_ = 0;
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.uid_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt64(2, this.lastCrashedAtMs_);
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
                    size2 += CodedOutputStream.computeInt64Size(2, this.lastCrashedAtMs_);
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Entry parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Entry parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Entry parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Entry parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Entry parseFrom(InputStream input) throws IOException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Entry parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Entry parseDelimitedFrom(InputStream input) throws IOException {
                return (Entry) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Entry parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Entry) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Entry parseFrom(CodedInputStream input) throws IOException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Entry parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Entry prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Entry, Builder> implements EntryOrBuilder {
                private Builder() {
                    super(Entry.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.AppErrorsProto.ProcessCrashTime.EntryOrBuilder
                public boolean hasUid() {
                    return ((Entry) this.instance).hasUid();
                }

                @Override // com.android.server.am.AppErrorsProto.ProcessCrashTime.EntryOrBuilder
                public int getUid() {
                    return ((Entry) this.instance).getUid();
                }

                public Builder setUid(int value) {
                    copyOnWrite();
                    ((Entry) this.instance).setUid(value);
                    return this;
                }

                public Builder clearUid() {
                    copyOnWrite();
                    ((Entry) this.instance).clearUid();
                    return this;
                }

                @Override // com.android.server.am.AppErrorsProto.ProcessCrashTime.EntryOrBuilder
                public boolean hasLastCrashedAtMs() {
                    return ((Entry) this.instance).hasLastCrashedAtMs();
                }

                @Override // com.android.server.am.AppErrorsProto.ProcessCrashTime.EntryOrBuilder
                public long getLastCrashedAtMs() {
                    return ((Entry) this.instance).getLastCrashedAtMs();
                }

                public Builder setLastCrashedAtMs(long value) {
                    copyOnWrite();
                    ((Entry) this.instance).setLastCrashedAtMs(value);
                    return this;
                }

                public Builder clearLastCrashedAtMs() {
                    copyOnWrite();
                    ((Entry) this.instance).clearLastCrashedAtMs();
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Entry();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Entry other = (Entry) arg1;
                        this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                        this.lastCrashedAtMs_ = visitor.visitLong(hasLastCrashedAtMs(), this.lastCrashedAtMs_, other.hasLastCrashedAtMs(), other.lastCrashedAtMs_);
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
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.lastCrashedAtMs_ = input.readInt64();
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
                            synchronized (Entry.class) {
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

            public static Entry getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Entry> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
        public boolean hasProcessName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
        public String getProcessName() {
            return this.processName_;
        }

        @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
        public ByteString getProcessNameBytes() {
            return ByteString.copyFromUtf8(this.processName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessName(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.processName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcessName() {
            this.bitField0_ &= -2;
            this.processName_ = getDefaultInstance().getProcessName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.processName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
        public List<Entry> getEntriesList() {
            return this.entries_;
        }

        public List<? extends EntryOrBuilder> getEntriesOrBuilderList() {
            return this.entries_;
        }

        @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
        public int getEntriesCount() {
            return this.entries_.size();
        }

        @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
        public Entry getEntries(int index) {
            return this.entries_.get(index);
        }

        public EntryOrBuilder getEntriesOrBuilder(int index) {
            return this.entries_.get(index);
        }

        private void ensureEntriesIsMutable() {
            if (!this.entries_.isModifiable()) {
                this.entries_ = GeneratedMessageLite.mutableCopy(this.entries_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEntries(int index, Entry value) {
            if (value != null) {
                ensureEntriesIsMutable();
                this.entries_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEntries(int index, Entry.Builder builderForValue) {
            ensureEntriesIsMutable();
            this.entries_.set(index, (Entry) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEntries(Entry value) {
            if (value != null) {
                ensureEntriesIsMutable();
                this.entries_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEntries(int index, Entry value) {
            if (value != null) {
                ensureEntriesIsMutable();
                this.entries_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEntries(Entry.Builder builderForValue) {
            ensureEntriesIsMutable();
            this.entries_.add((Entry) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEntries(int index, Entry.Builder builderForValue) {
            ensureEntriesIsMutable();
            this.entries_.add(index, (Entry) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllEntries(Iterable<? extends Entry> values) {
            ensureEntriesIsMutable();
            AbstractMessageLite.addAll(values, this.entries_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEntries() {
            this.entries_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeEntries(int index) {
            ensureEntriesIsMutable();
            this.entries_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getProcessName());
            }
            for (int i = 0; i < this.entries_.size(); i++) {
                output.writeMessage(2, this.entries_.get(i));
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getProcessName());
            }
            for (int i = 0; i < this.entries_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.entries_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ProcessCrashTime parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ProcessCrashTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProcessCrashTime parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProcessCrashTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProcessCrashTime parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ProcessCrashTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProcessCrashTime parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProcessCrashTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProcessCrashTime parseFrom(InputStream input) throws IOException {
            return (ProcessCrashTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessCrashTime parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessCrashTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProcessCrashTime parseDelimitedFrom(InputStream input) throws IOException {
            return (ProcessCrashTime) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessCrashTime parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessCrashTime) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProcessCrashTime parseFrom(CodedInputStream input) throws IOException {
            return (ProcessCrashTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessCrashTime parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessCrashTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ProcessCrashTime prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ProcessCrashTime, Builder> implements ProcessCrashTimeOrBuilder {
            private Builder() {
                super(ProcessCrashTime.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
            public boolean hasProcessName() {
                return ((ProcessCrashTime) this.instance).hasProcessName();
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
            public String getProcessName() {
                return ((ProcessCrashTime) this.instance).getProcessName();
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
            public ByteString getProcessNameBytes() {
                return ((ProcessCrashTime) this.instance).getProcessNameBytes();
            }

            public Builder setProcessName(String value) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).setProcessName(value);
                return this;
            }

            public Builder clearProcessName() {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).clearProcessName();
                return this;
            }

            public Builder setProcessNameBytes(ByteString value) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).setProcessNameBytes(value);
                return this;
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
            public List<Entry> getEntriesList() {
                return Collections.unmodifiableList(((ProcessCrashTime) this.instance).getEntriesList());
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
            public int getEntriesCount() {
                return ((ProcessCrashTime) this.instance).getEntriesCount();
            }

            @Override // com.android.server.am.AppErrorsProto.ProcessCrashTimeOrBuilder
            public Entry getEntries(int index) {
                return ((ProcessCrashTime) this.instance).getEntries(index);
            }

            public Builder setEntries(int index, Entry value) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).setEntries((ProcessCrashTime) index, (int) value);
                return this;
            }

            public Builder setEntries(int index, Entry.Builder builderForValue) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).setEntries((ProcessCrashTime) index, (int) builderForValue);
                return this;
            }

            public Builder addEntries(Entry value) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).addEntries((ProcessCrashTime) value);
                return this;
            }

            public Builder addEntries(int index, Entry value) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).addEntries((ProcessCrashTime) index, (int) value);
                return this;
            }

            public Builder addEntries(Entry.Builder builderForValue) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).addEntries((ProcessCrashTime) builderForValue);
                return this;
            }

            public Builder addEntries(int index, Entry.Builder builderForValue) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).addEntries((ProcessCrashTime) index, (int) builderForValue);
                return this;
            }

            public Builder addAllEntries(Iterable<? extends Entry> values) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).addAllEntries(values);
                return this;
            }

            public Builder clearEntries() {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).clearEntries();
                return this;
            }

            public Builder removeEntries(int index) {
                copyOnWrite();
                ((ProcessCrashTime) this.instance).removeEntries(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ProcessCrashTime();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.entries_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ProcessCrashTime other = (ProcessCrashTime) arg1;
                    this.processName_ = visitor.visitString(hasProcessName(), this.processName_, other.hasProcessName(), other.processName_);
                    this.entries_ = visitor.visitList(this.entries_, other.entries_);
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
                                this.processName_ = s;
                            } else if (tag == 18) {
                                if (!this.entries_.isModifiable()) {
                                    this.entries_ = GeneratedMessageLite.mutableCopy(this.entries_);
                                }
                                this.entries_.add((Entry) input.readMessage(Entry.parser(), extensionRegistry));
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
                        synchronized (ProcessCrashTime.class) {
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

        public static ProcessCrashTime getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ProcessCrashTime> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class BadProcess extends GeneratedMessageLite<BadProcess, Builder> implements BadProcessOrBuilder {
        private static final BadProcess DEFAULT_INSTANCE = new BadProcess();
        public static final int ENTRIES_FIELD_NUMBER = 2;
        private static volatile Parser<BadProcess> PARSER = null;
        public static final int PROCESS_NAME_FIELD_NUMBER = 1;
        private int bitField0_;
        private Internal.ProtobufList<Entry> entries_ = emptyProtobufList();
        private String processName_ = "";

        public interface EntryOrBuilder extends MessageLiteOrBuilder {
            long getCrashedAtMs();

            String getLongMsg();

            ByteString getLongMsgBytes();

            String getShortMsg();

            ByteString getShortMsgBytes();

            String getStack();

            ByteString getStackBytes();

            int getUid();

            boolean hasCrashedAtMs();

            boolean hasLongMsg();

            boolean hasShortMsg();

            boolean hasStack();

            boolean hasUid();
        }

        private BadProcess() {
        }

        public static final class Entry extends GeneratedMessageLite<Entry, Builder> implements EntryOrBuilder {
            public static final int CRASHED_AT_MS_FIELD_NUMBER = 2;
            private static final Entry DEFAULT_INSTANCE = new Entry();
            public static final int LONG_MSG_FIELD_NUMBER = 4;
            private static volatile Parser<Entry> PARSER = null;
            public static final int SHORT_MSG_FIELD_NUMBER = 3;
            public static final int STACK_FIELD_NUMBER = 5;
            public static final int UID_FIELD_NUMBER = 1;
            private int bitField0_;
            private long crashedAtMs_ = 0;
            private String longMsg_ = "";
            private String shortMsg_ = "";
            private String stack_ = "";
            private int uid_ = 0;

            private Entry() {
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public boolean hasUid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
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

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public boolean hasCrashedAtMs() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public long getCrashedAtMs() {
                return this.crashedAtMs_;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCrashedAtMs(long value) {
                this.bitField0_ |= 2;
                this.crashedAtMs_ = value;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearCrashedAtMs() {
                this.bitField0_ &= -3;
                this.crashedAtMs_ = 0;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public boolean hasShortMsg() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public String getShortMsg() {
                return this.shortMsg_;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public ByteString getShortMsgBytes() {
                return ByteString.copyFromUtf8(this.shortMsg_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setShortMsg(String value) {
                if (value != null) {
                    this.bitField0_ |= 4;
                    this.shortMsg_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearShortMsg() {
                this.bitField0_ &= -5;
                this.shortMsg_ = getDefaultInstance().getShortMsg();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setShortMsgBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 4;
                    this.shortMsg_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public boolean hasLongMsg() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public String getLongMsg() {
                return this.longMsg_;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public ByteString getLongMsgBytes() {
                return ByteString.copyFromUtf8(this.longMsg_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setLongMsg(String value) {
                if (value != null) {
                    this.bitField0_ |= 8;
                    this.longMsg_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearLongMsg() {
                this.bitField0_ &= -9;
                this.longMsg_ = getDefaultInstance().getLongMsg();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setLongMsgBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 8;
                    this.longMsg_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public boolean hasStack() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public String getStack() {
                return this.stack_;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
            public ByteString getStackBytes() {
                return ByteString.copyFromUtf8(this.stack_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStack(String value) {
                if (value != null) {
                    this.bitField0_ |= 16;
                    this.stack_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearStack() {
                this.bitField0_ &= -17;
                this.stack_ = getDefaultInstance().getStack();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setStackBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 16;
                    this.stack_ = value.toStringUtf8();
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
                    output.writeInt64(2, this.crashedAtMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeString(3, getShortMsg());
                }
                if ((this.bitField0_ & 8) == 8) {
                    output.writeString(4, getLongMsg());
                }
                if ((this.bitField0_ & 16) == 16) {
                    output.writeString(5, getStack());
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
                    size2 += CodedOutputStream.computeInt64Size(2, this.crashedAtMs_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeStringSize(3, getShortMsg());
                }
                if ((this.bitField0_ & 8) == 8) {
                    size2 += CodedOutputStream.computeStringSize(4, getLongMsg());
                }
                if ((this.bitField0_ & 16) == 16) {
                    size2 += CodedOutputStream.computeStringSize(5, getStack());
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Entry parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Entry parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Entry parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static Entry parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static Entry parseFrom(InputStream input) throws IOException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Entry parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Entry parseDelimitedFrom(InputStream input) throws IOException {
                return (Entry) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static Entry parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Entry) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Entry parseFrom(CodedInputStream input) throws IOException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static Entry parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (Entry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(Entry prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<Entry, Builder> implements EntryOrBuilder {
                private Builder() {
                    super(Entry.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public boolean hasUid() {
                    return ((Entry) this.instance).hasUid();
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public int getUid() {
                    return ((Entry) this.instance).getUid();
                }

                public Builder setUid(int value) {
                    copyOnWrite();
                    ((Entry) this.instance).setUid(value);
                    return this;
                }

                public Builder clearUid() {
                    copyOnWrite();
                    ((Entry) this.instance).clearUid();
                    return this;
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public boolean hasCrashedAtMs() {
                    return ((Entry) this.instance).hasCrashedAtMs();
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public long getCrashedAtMs() {
                    return ((Entry) this.instance).getCrashedAtMs();
                }

                public Builder setCrashedAtMs(long value) {
                    copyOnWrite();
                    ((Entry) this.instance).setCrashedAtMs(value);
                    return this;
                }

                public Builder clearCrashedAtMs() {
                    copyOnWrite();
                    ((Entry) this.instance).clearCrashedAtMs();
                    return this;
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public boolean hasShortMsg() {
                    return ((Entry) this.instance).hasShortMsg();
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public String getShortMsg() {
                    return ((Entry) this.instance).getShortMsg();
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public ByteString getShortMsgBytes() {
                    return ((Entry) this.instance).getShortMsgBytes();
                }

                public Builder setShortMsg(String value) {
                    copyOnWrite();
                    ((Entry) this.instance).setShortMsg(value);
                    return this;
                }

                public Builder clearShortMsg() {
                    copyOnWrite();
                    ((Entry) this.instance).clearShortMsg();
                    return this;
                }

                public Builder setShortMsgBytes(ByteString value) {
                    copyOnWrite();
                    ((Entry) this.instance).setShortMsgBytes(value);
                    return this;
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public boolean hasLongMsg() {
                    return ((Entry) this.instance).hasLongMsg();
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public String getLongMsg() {
                    return ((Entry) this.instance).getLongMsg();
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public ByteString getLongMsgBytes() {
                    return ((Entry) this.instance).getLongMsgBytes();
                }

                public Builder setLongMsg(String value) {
                    copyOnWrite();
                    ((Entry) this.instance).setLongMsg(value);
                    return this;
                }

                public Builder clearLongMsg() {
                    copyOnWrite();
                    ((Entry) this.instance).clearLongMsg();
                    return this;
                }

                public Builder setLongMsgBytes(ByteString value) {
                    copyOnWrite();
                    ((Entry) this.instance).setLongMsgBytes(value);
                    return this;
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public boolean hasStack() {
                    return ((Entry) this.instance).hasStack();
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public String getStack() {
                    return ((Entry) this.instance).getStack();
                }

                @Override // com.android.server.am.AppErrorsProto.BadProcess.EntryOrBuilder
                public ByteString getStackBytes() {
                    return ((Entry) this.instance).getStackBytes();
                }

                public Builder setStack(String value) {
                    copyOnWrite();
                    ((Entry) this.instance).setStack(value);
                    return this;
                }

                public Builder clearStack() {
                    copyOnWrite();
                    ((Entry) this.instance).clearStack();
                    return this;
                }

                public Builder setStackBytes(ByteString value) {
                    copyOnWrite();
                    ((Entry) this.instance).setStackBytes(value);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new Entry();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        Entry other = (Entry) arg1;
                        this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                        this.crashedAtMs_ = visitor.visitLong(hasCrashedAtMs(), this.crashedAtMs_, other.hasCrashedAtMs(), other.crashedAtMs_);
                        this.shortMsg_ = visitor.visitString(hasShortMsg(), this.shortMsg_, other.hasShortMsg(), other.shortMsg_);
                        this.longMsg_ = visitor.visitString(hasLongMsg(), this.longMsg_, other.hasLongMsg(), other.longMsg_);
                        this.stack_ = visitor.visitString(hasStack(), this.stack_, other.hasStack(), other.stack_);
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
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.crashedAtMs_ = input.readInt64();
                                } else if (tag == 26) {
                                    String s = input.readString();
                                    this.bitField0_ |= 4;
                                    this.shortMsg_ = s;
                                } else if (tag == 34) {
                                    String s2 = input.readString();
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.longMsg_ = s2;
                                } else if (tag == 42) {
                                    String s3 = input.readString();
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.stack_ = s3;
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
                            synchronized (Entry.class) {
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

            public static Entry getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Entry> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
        public boolean hasProcessName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
        public String getProcessName() {
            return this.processName_;
        }

        @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
        public ByteString getProcessNameBytes() {
            return ByteString.copyFromUtf8(this.processName_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessName(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.processName_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcessName() {
            this.bitField0_ &= -2;
            this.processName_ = getDefaultInstance().getProcessName();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessNameBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.processName_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
        public List<Entry> getEntriesList() {
            return this.entries_;
        }

        public List<? extends EntryOrBuilder> getEntriesOrBuilderList() {
            return this.entries_;
        }

        @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
        public int getEntriesCount() {
            return this.entries_.size();
        }

        @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
        public Entry getEntries(int index) {
            return this.entries_.get(index);
        }

        public EntryOrBuilder getEntriesOrBuilder(int index) {
            return this.entries_.get(index);
        }

        private void ensureEntriesIsMutable() {
            if (!this.entries_.isModifiable()) {
                this.entries_ = GeneratedMessageLite.mutableCopy(this.entries_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEntries(int index, Entry value) {
            if (value != null) {
                ensureEntriesIsMutable();
                this.entries_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEntries(int index, Entry.Builder builderForValue) {
            ensureEntriesIsMutable();
            this.entries_.set(index, (Entry) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEntries(Entry value) {
            if (value != null) {
                ensureEntriesIsMutable();
                this.entries_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEntries(int index, Entry value) {
            if (value != null) {
                ensureEntriesIsMutable();
                this.entries_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEntries(Entry.Builder builderForValue) {
            ensureEntriesIsMutable();
            this.entries_.add((Entry) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addEntries(int index, Entry.Builder builderForValue) {
            ensureEntriesIsMutable();
            this.entries_.add(index, (Entry) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllEntries(Iterable<? extends Entry> values) {
            ensureEntriesIsMutable();
            AbstractMessageLite.addAll(values, this.entries_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEntries() {
            this.entries_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeEntries(int index) {
            ensureEntriesIsMutable();
            this.entries_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getProcessName());
            }
            for (int i = 0; i < this.entries_.size(); i++) {
                output.writeMessage(2, this.entries_.get(i));
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getProcessName());
            }
            for (int i = 0; i < this.entries_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.entries_.get(i));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static BadProcess parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BadProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BadProcess parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BadProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BadProcess parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BadProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BadProcess parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BadProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BadProcess parseFrom(InputStream input) throws IOException {
            return (BadProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BadProcess parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BadProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BadProcess parseDelimitedFrom(InputStream input) throws IOException {
            return (BadProcess) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BadProcess parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BadProcess) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BadProcess parseFrom(CodedInputStream input) throws IOException {
            return (BadProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BadProcess parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BadProcess) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BadProcess prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BadProcess, Builder> implements BadProcessOrBuilder {
            private Builder() {
                super(BadProcess.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
            public boolean hasProcessName() {
                return ((BadProcess) this.instance).hasProcessName();
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
            public String getProcessName() {
                return ((BadProcess) this.instance).getProcessName();
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
            public ByteString getProcessNameBytes() {
                return ((BadProcess) this.instance).getProcessNameBytes();
            }

            public Builder setProcessName(String value) {
                copyOnWrite();
                ((BadProcess) this.instance).setProcessName(value);
                return this;
            }

            public Builder clearProcessName() {
                copyOnWrite();
                ((BadProcess) this.instance).clearProcessName();
                return this;
            }

            public Builder setProcessNameBytes(ByteString value) {
                copyOnWrite();
                ((BadProcess) this.instance).setProcessNameBytes(value);
                return this;
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
            public List<Entry> getEntriesList() {
                return Collections.unmodifiableList(((BadProcess) this.instance).getEntriesList());
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
            public int getEntriesCount() {
                return ((BadProcess) this.instance).getEntriesCount();
            }

            @Override // com.android.server.am.AppErrorsProto.BadProcessOrBuilder
            public Entry getEntries(int index) {
                return ((BadProcess) this.instance).getEntries(index);
            }

            public Builder setEntries(int index, Entry value) {
                copyOnWrite();
                ((BadProcess) this.instance).setEntries((BadProcess) index, (int) value);
                return this;
            }

            public Builder setEntries(int index, Entry.Builder builderForValue) {
                copyOnWrite();
                ((BadProcess) this.instance).setEntries((BadProcess) index, (int) builderForValue);
                return this;
            }

            public Builder addEntries(Entry value) {
                copyOnWrite();
                ((BadProcess) this.instance).addEntries((BadProcess) value);
                return this;
            }

            public Builder addEntries(int index, Entry value) {
                copyOnWrite();
                ((BadProcess) this.instance).addEntries((BadProcess) index, (int) value);
                return this;
            }

            public Builder addEntries(Entry.Builder builderForValue) {
                copyOnWrite();
                ((BadProcess) this.instance).addEntries((BadProcess) builderForValue);
                return this;
            }

            public Builder addEntries(int index, Entry.Builder builderForValue) {
                copyOnWrite();
                ((BadProcess) this.instance).addEntries((BadProcess) index, (int) builderForValue);
                return this;
            }

            public Builder addAllEntries(Iterable<? extends Entry> values) {
                copyOnWrite();
                ((BadProcess) this.instance).addAllEntries(values);
                return this;
            }

            public Builder clearEntries() {
                copyOnWrite();
                ((BadProcess) this.instance).clearEntries();
                return this;
            }

            public Builder removeEntries(int index) {
                copyOnWrite();
                ((BadProcess) this.instance).removeEntries(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new BadProcess();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.entries_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    BadProcess other = (BadProcess) arg1;
                    this.processName_ = visitor.visitString(hasProcessName(), this.processName_, other.hasProcessName(), other.processName_);
                    this.entries_ = visitor.visitList(this.entries_, other.entries_);
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
                                this.processName_ = s;
                            } else if (tag == 18) {
                                if (!this.entries_.isModifiable()) {
                                    this.entries_ = GeneratedMessageLite.mutableCopy(this.entries_);
                                }
                                this.entries_.add((Entry) input.readMessage(Entry.parser(), extensionRegistry));
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
                        synchronized (BadProcess.class) {
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

        public static BadProcess getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BadProcess> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.AppErrorsProtoOrBuilder
    public boolean hasNowUptimeMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.AppErrorsProtoOrBuilder
    public long getNowUptimeMs() {
        return this.nowUptimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNowUptimeMs(long value) {
        this.bitField0_ |= 1;
        this.nowUptimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNowUptimeMs() {
        this.bitField0_ &= -2;
        this.nowUptimeMs_ = 0;
    }

    @Override // com.android.server.am.AppErrorsProtoOrBuilder
    public List<ProcessCrashTime> getProcessCrashTimesList() {
        return this.processCrashTimes_;
    }

    public List<? extends ProcessCrashTimeOrBuilder> getProcessCrashTimesOrBuilderList() {
        return this.processCrashTimes_;
    }

    @Override // com.android.server.am.AppErrorsProtoOrBuilder
    public int getProcessCrashTimesCount() {
        return this.processCrashTimes_.size();
    }

    @Override // com.android.server.am.AppErrorsProtoOrBuilder
    public ProcessCrashTime getProcessCrashTimes(int index) {
        return this.processCrashTimes_.get(index);
    }

    public ProcessCrashTimeOrBuilder getProcessCrashTimesOrBuilder(int index) {
        return this.processCrashTimes_.get(index);
    }

    private void ensureProcessCrashTimesIsMutable() {
        if (!this.processCrashTimes_.isModifiable()) {
            this.processCrashTimes_ = GeneratedMessageLite.mutableCopy(this.processCrashTimes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessCrashTimes(int index, ProcessCrashTime value) {
        if (value != null) {
            ensureProcessCrashTimesIsMutable();
            this.processCrashTimes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessCrashTimes(int index, ProcessCrashTime.Builder builderForValue) {
        ensureProcessCrashTimesIsMutable();
        this.processCrashTimes_.set(index, (ProcessCrashTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcessCrashTimes(ProcessCrashTime value) {
        if (value != null) {
            ensureProcessCrashTimesIsMutable();
            this.processCrashTimes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcessCrashTimes(int index, ProcessCrashTime value) {
        if (value != null) {
            ensureProcessCrashTimesIsMutable();
            this.processCrashTimes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcessCrashTimes(ProcessCrashTime.Builder builderForValue) {
        ensureProcessCrashTimesIsMutable();
        this.processCrashTimes_.add((ProcessCrashTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addProcessCrashTimes(int index, ProcessCrashTime.Builder builderForValue) {
        ensureProcessCrashTimesIsMutable();
        this.processCrashTimes_.add(index, (ProcessCrashTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllProcessCrashTimes(Iterable<? extends ProcessCrashTime> values) {
        ensureProcessCrashTimesIsMutable();
        AbstractMessageLite.addAll(values, this.processCrashTimes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessCrashTimes() {
        this.processCrashTimes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeProcessCrashTimes(int index) {
        ensureProcessCrashTimesIsMutable();
        this.processCrashTimes_.remove(index);
    }

    @Override // com.android.server.am.AppErrorsProtoOrBuilder
    public List<BadProcess> getBadProcessesList() {
        return this.badProcesses_;
    }

    public List<? extends BadProcessOrBuilder> getBadProcessesOrBuilderList() {
        return this.badProcesses_;
    }

    @Override // com.android.server.am.AppErrorsProtoOrBuilder
    public int getBadProcessesCount() {
        return this.badProcesses_.size();
    }

    @Override // com.android.server.am.AppErrorsProtoOrBuilder
    public BadProcess getBadProcesses(int index) {
        return this.badProcesses_.get(index);
    }

    public BadProcessOrBuilder getBadProcessesOrBuilder(int index) {
        return this.badProcesses_.get(index);
    }

    private void ensureBadProcessesIsMutable() {
        if (!this.badProcesses_.isModifiable()) {
            this.badProcesses_ = GeneratedMessageLite.mutableCopy(this.badProcesses_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBadProcesses(int index, BadProcess value) {
        if (value != null) {
            ensureBadProcessesIsMutable();
            this.badProcesses_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBadProcesses(int index, BadProcess.Builder builderForValue) {
        ensureBadProcessesIsMutable();
        this.badProcesses_.set(index, (BadProcess) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBadProcesses(BadProcess value) {
        if (value != null) {
            ensureBadProcessesIsMutable();
            this.badProcesses_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBadProcesses(int index, BadProcess value) {
        if (value != null) {
            ensureBadProcessesIsMutable();
            this.badProcesses_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBadProcesses(BadProcess.Builder builderForValue) {
        ensureBadProcessesIsMutable();
        this.badProcesses_.add((BadProcess) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBadProcesses(int index, BadProcess.Builder builderForValue) {
        ensureBadProcessesIsMutable();
        this.badProcesses_.add(index, (BadProcess) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBadProcesses(Iterable<? extends BadProcess> values) {
        ensureBadProcessesIsMutable();
        AbstractMessageLite.addAll(values, this.badProcesses_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBadProcesses() {
        this.badProcesses_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeBadProcesses(int index) {
        ensureBadProcessesIsMutable();
        this.badProcesses_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.nowUptimeMs_);
        }
        for (int i = 0; i < this.processCrashTimes_.size(); i++) {
            output.writeMessage(2, this.processCrashTimes_.get(i));
        }
        for (int i2 = 0; i2 < this.badProcesses_.size(); i2++) {
            output.writeMessage(3, this.badProcesses_.get(i2));
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.nowUptimeMs_);
        }
        for (int i = 0; i < this.processCrashTimes_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.processCrashTimes_.get(i));
        }
        for (int i2 = 0; i2 < this.badProcesses_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.badProcesses_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AppErrorsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AppErrorsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppErrorsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppErrorsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppErrorsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AppErrorsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppErrorsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppErrorsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppErrorsProto parseFrom(InputStream input) throws IOException {
        return (AppErrorsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppErrorsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppErrorsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppErrorsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AppErrorsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AppErrorsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppErrorsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppErrorsProto parseFrom(CodedInputStream input) throws IOException {
        return (AppErrorsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppErrorsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppErrorsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AppErrorsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AppErrorsProto, Builder> implements AppErrorsProtoOrBuilder {
        private Builder() {
            super(AppErrorsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.AppErrorsProtoOrBuilder
        public boolean hasNowUptimeMs() {
            return ((AppErrorsProto) this.instance).hasNowUptimeMs();
        }

        @Override // com.android.server.am.AppErrorsProtoOrBuilder
        public long getNowUptimeMs() {
            return ((AppErrorsProto) this.instance).getNowUptimeMs();
        }

        public Builder setNowUptimeMs(long value) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).setNowUptimeMs(value);
            return this;
        }

        public Builder clearNowUptimeMs() {
            copyOnWrite();
            ((AppErrorsProto) this.instance).clearNowUptimeMs();
            return this;
        }

        @Override // com.android.server.am.AppErrorsProtoOrBuilder
        public List<ProcessCrashTime> getProcessCrashTimesList() {
            return Collections.unmodifiableList(((AppErrorsProto) this.instance).getProcessCrashTimesList());
        }

        @Override // com.android.server.am.AppErrorsProtoOrBuilder
        public int getProcessCrashTimesCount() {
            return ((AppErrorsProto) this.instance).getProcessCrashTimesCount();
        }

        @Override // com.android.server.am.AppErrorsProtoOrBuilder
        public ProcessCrashTime getProcessCrashTimes(int index) {
            return ((AppErrorsProto) this.instance).getProcessCrashTimes(index);
        }

        public Builder setProcessCrashTimes(int index, ProcessCrashTime value) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).setProcessCrashTimes((AppErrorsProto) index, (int) value);
            return this;
        }

        public Builder setProcessCrashTimes(int index, ProcessCrashTime.Builder builderForValue) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).setProcessCrashTimes((AppErrorsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addProcessCrashTimes(ProcessCrashTime value) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addProcessCrashTimes((AppErrorsProto) value);
            return this;
        }

        public Builder addProcessCrashTimes(int index, ProcessCrashTime value) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addProcessCrashTimes((AppErrorsProto) index, (int) value);
            return this;
        }

        public Builder addProcessCrashTimes(ProcessCrashTime.Builder builderForValue) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addProcessCrashTimes((AppErrorsProto) builderForValue);
            return this;
        }

        public Builder addProcessCrashTimes(int index, ProcessCrashTime.Builder builderForValue) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addProcessCrashTimes((AppErrorsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllProcessCrashTimes(Iterable<? extends ProcessCrashTime> values) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addAllProcessCrashTimes(values);
            return this;
        }

        public Builder clearProcessCrashTimes() {
            copyOnWrite();
            ((AppErrorsProto) this.instance).clearProcessCrashTimes();
            return this;
        }

        public Builder removeProcessCrashTimes(int index) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).removeProcessCrashTimes(index);
            return this;
        }

        @Override // com.android.server.am.AppErrorsProtoOrBuilder
        public List<BadProcess> getBadProcessesList() {
            return Collections.unmodifiableList(((AppErrorsProto) this.instance).getBadProcessesList());
        }

        @Override // com.android.server.am.AppErrorsProtoOrBuilder
        public int getBadProcessesCount() {
            return ((AppErrorsProto) this.instance).getBadProcessesCount();
        }

        @Override // com.android.server.am.AppErrorsProtoOrBuilder
        public BadProcess getBadProcesses(int index) {
            return ((AppErrorsProto) this.instance).getBadProcesses(index);
        }

        public Builder setBadProcesses(int index, BadProcess value) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).setBadProcesses((AppErrorsProto) index, (int) value);
            return this;
        }

        public Builder setBadProcesses(int index, BadProcess.Builder builderForValue) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).setBadProcesses((AppErrorsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addBadProcesses(BadProcess value) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addBadProcesses((AppErrorsProto) value);
            return this;
        }

        public Builder addBadProcesses(int index, BadProcess value) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addBadProcesses((AppErrorsProto) index, (int) value);
            return this;
        }

        public Builder addBadProcesses(BadProcess.Builder builderForValue) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addBadProcesses((AppErrorsProto) builderForValue);
            return this;
        }

        public Builder addBadProcesses(int index, BadProcess.Builder builderForValue) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addBadProcesses((AppErrorsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllBadProcesses(Iterable<? extends BadProcess> values) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).addAllBadProcesses(values);
            return this;
        }

        public Builder clearBadProcesses() {
            copyOnWrite();
            ((AppErrorsProto) this.instance).clearBadProcesses();
            return this;
        }

        public Builder removeBadProcesses(int index) {
            copyOnWrite();
            ((AppErrorsProto) this.instance).removeBadProcesses(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AppErrorsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.processCrashTimes_.makeImmutable();
                this.badProcesses_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AppErrorsProto other = (AppErrorsProto) arg1;
                this.nowUptimeMs_ = visitor.visitLong(hasNowUptimeMs(), this.nowUptimeMs_, other.hasNowUptimeMs(), other.nowUptimeMs_);
                this.processCrashTimes_ = visitor.visitList(this.processCrashTimes_, other.processCrashTimes_);
                this.badProcesses_ = visitor.visitList(this.badProcesses_, other.badProcesses_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.nowUptimeMs_ = input.readInt64();
                        } else if (tag == 18) {
                            if (!this.processCrashTimes_.isModifiable()) {
                                this.processCrashTimes_ = GeneratedMessageLite.mutableCopy(this.processCrashTimes_);
                            }
                            this.processCrashTimes_.add((ProcessCrashTime) input.readMessage(ProcessCrashTime.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.badProcesses_.isModifiable()) {
                                this.badProcesses_ = GeneratedMessageLite.mutableCopy(this.badProcesses_);
                            }
                            this.badProcesses_.add((BadProcess) input.readMessage(BadProcess.parser(), extensionRegistry));
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
                    synchronized (AppErrorsProto.class) {
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

    public static AppErrorsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AppErrorsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
