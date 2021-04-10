package com.android.server.usage;

import android.content.ConfigurationProto;
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

public final class IntervalStatsProto extends GeneratedMessageLite<IntervalStatsProto, Builder> implements IntervalStatsProtoOrBuilder {
    public static final int CONFIGURATIONS_FIELD_NUMBER = 21;
    private static final IntervalStatsProto DEFAULT_INSTANCE = new IntervalStatsProto();
    public static final int END_TIME_MS_FIELD_NUMBER = 1;
    public static final int EVENT_LOG_FIELD_NUMBER = 22;
    public static final int INTERACTIVE_FIELD_NUMBER = 10;
    public static final int KEYGUARD_HIDDEN_FIELD_NUMBER = 13;
    public static final int KEYGUARD_SHOWN_FIELD_NUMBER = 12;
    public static final int MAJOR_VERSION_FIELD_NUMBER = 3;
    public static final int MINOR_VERSION_FIELD_NUMBER = 4;
    public static final int NON_INTERACTIVE_FIELD_NUMBER = 11;
    public static final int PACKAGES_FIELD_NUMBER = 20;
    private static volatile Parser<IntervalStatsProto> PARSER = null;
    public static final int STRINGPOOL_FIELD_NUMBER = 2;
    private int bitField0_;
    private Internal.ProtobufList<Configuration> configurations_ = emptyProtobufList();
    private long endTimeMs_ = 0;
    private Internal.ProtobufList<Event> eventLog_ = emptyProtobufList();
    private CountAndTime interactive_;
    private CountAndTime keyguardHidden_;
    private CountAndTime keyguardShown_;
    private int majorVersion_ = 0;
    private int minorVersion_ = 0;
    private CountAndTime nonInteractive_;
    private Internal.ProtobufList<UsageStats> packages_ = emptyProtobufList();
    private StringPool stringpool_;

    public interface ConfigurationOrBuilder extends MessageLiteOrBuilder {
        boolean getActive();

        ConfigurationProto getConfig();

        int getCount();

        long getLastTimeActiveMs();

        long getTotalTimeActiveMs();

        boolean hasActive();

        boolean hasConfig();

        boolean hasCount();

        boolean hasLastTimeActiveMs();

        boolean hasTotalTimeActiveMs();
    }

    public interface CountAndTimeOrBuilder extends MessageLiteOrBuilder {
        int getCount();

        long getTimeMs();

        boolean hasCount();

        boolean hasTimeMs();
    }

    public interface EventOrBuilder extends MessageLiteOrBuilder {
        int getClassIndex();

        String getClass_();

        ByteString getClass_Bytes();

        ConfigurationProto getConfig();

        int getFlags();

        int getInstanceId();

        String getNotificationChannel();

        ByteString getNotificationChannelBytes();

        int getNotificationChannelIndex();

        String getPackage();

        ByteString getPackageBytes();

        int getPackageIndex();

        String getShortcutId();

        ByteString getShortcutIdBytes();

        int getStandbyBucket();

        int getTaskRootClassIndex();

        int getTaskRootPackageIndex();

        long getTimeMs();

        int getType();

        boolean hasClassIndex();

        boolean hasClass_();

        boolean hasConfig();

        boolean hasFlags();

        boolean hasInstanceId();

        boolean hasNotificationChannel();

        boolean hasNotificationChannelIndex();

        boolean hasPackage();

        boolean hasPackageIndex();

        boolean hasShortcutId();

        boolean hasStandbyBucket();

        boolean hasTaskRootClassIndex();

        boolean hasTaskRootPackageIndex();

        boolean hasTimeMs();

        boolean hasType();
    }

    public interface StringPoolOrBuilder extends MessageLiteOrBuilder {
        int getSize();

        String getStrings(int i);

        ByteString getStringsBytes(int i);

        int getStringsCount();

        List<String> getStringsList();

        boolean hasSize();
    }

    public interface UsageStatsOrBuilder extends MessageLiteOrBuilder {
        int getAppLaunchCount();

        UsageStats.ChooserAction getChooserActions(int i);

        int getChooserActionsCount();

        List<UsageStats.ChooserAction> getChooserActionsList();

        int getLastEvent();

        long getLastTimeActiveMs();

        long getLastTimeServiceUsedMs();

        long getLastTimeVisibleMs();

        String getPackage();

        ByteString getPackageBytes();

        int getPackageIndex();

        long getTotalTimeActiveMs();

        long getTotalTimeServiceUsedMs();

        long getTotalTimeVisibleMs();

        boolean hasAppLaunchCount();

        boolean hasLastEvent();

        boolean hasLastTimeActiveMs();

        boolean hasLastTimeServiceUsedMs();

        boolean hasLastTimeVisibleMs();

        boolean hasPackage();

        boolean hasPackageIndex();

        boolean hasTotalTimeActiveMs();

        boolean hasTotalTimeServiceUsedMs();

        boolean hasTotalTimeVisibleMs();
    }

    private IntervalStatsProto() {
    }

    public static final class StringPool extends GeneratedMessageLite<StringPool, Builder> implements StringPoolOrBuilder {
        private static final StringPool DEFAULT_INSTANCE = new StringPool();
        private static volatile Parser<StringPool> PARSER = null;
        public static final int SIZE_FIELD_NUMBER = 1;
        public static final int STRINGS_FIELD_NUMBER = 2;
        private int bitField0_;
        private int size_ = 0;
        private Internal.ProtobufList<String> strings_ = GeneratedMessageLite.emptyProtobufList();

        private StringPool() {
        }

        @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
        public boolean hasSize() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
        public int getSize() {
            return this.size_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSize(int value) {
            this.bitField0_ |= 1;
            this.size_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSize() {
            this.bitField0_ &= -2;
            this.size_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
        public List<String> getStringsList() {
            return this.strings_;
        }

        @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
        public int getStringsCount() {
            return this.strings_.size();
        }

        @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
        public String getStrings(int index) {
            return this.strings_.get(index);
        }

        @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
        public ByteString getStringsBytes(int index) {
            return ByteString.copyFromUtf8(this.strings_.get(index));
        }

        private void ensureStringsIsMutable() {
            if (!this.strings_.isModifiable()) {
                this.strings_ = GeneratedMessageLite.mutableCopy(this.strings_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStrings(int index, String value) {
            if (value != null) {
                ensureStringsIsMutable();
                this.strings_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStrings(String value) {
            if (value != null) {
                ensureStringsIsMutable();
                this.strings_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllStrings(Iterable<String> values) {
            ensureStringsIsMutable();
            AbstractMessageLite.addAll(values, this.strings_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStrings() {
            this.strings_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStringsBytes(ByteString value) {
            if (value != null) {
                ensureStringsIsMutable();
                this.strings_.add(value.toStringUtf8());
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.size_);
            }
            for (int i = 0; i < this.strings_.size(); i++) {
                output.writeString(2, this.strings_.get(i));
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.size_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.strings_.size(); i++) {
                dataSize += CodedOutputStream.computeStringSizeNoTag(this.strings_.get(i));
            }
            int size3 = size2 + dataSize + (getStringsList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static StringPool parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StringPool) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StringPool parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StringPool) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StringPool parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StringPool) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StringPool parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StringPool) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StringPool parseFrom(InputStream input) throws IOException {
            return (StringPool) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StringPool parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StringPool) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StringPool parseDelimitedFrom(InputStream input) throws IOException {
            return (StringPool) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StringPool parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StringPool) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StringPool parseFrom(CodedInputStream input) throws IOException {
            return (StringPool) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StringPool parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StringPool) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StringPool prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StringPool, Builder> implements StringPoolOrBuilder {
            private Builder() {
                super(StringPool.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
            public boolean hasSize() {
                return ((StringPool) this.instance).hasSize();
            }

            @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
            public int getSize() {
                return ((StringPool) this.instance).getSize();
            }

            public Builder setSize(int value) {
                copyOnWrite();
                ((StringPool) this.instance).setSize(value);
                return this;
            }

            public Builder clearSize() {
                copyOnWrite();
                ((StringPool) this.instance).clearSize();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
            public List<String> getStringsList() {
                return Collections.unmodifiableList(((StringPool) this.instance).getStringsList());
            }

            @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
            public int getStringsCount() {
                return ((StringPool) this.instance).getStringsCount();
            }

            @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
            public String getStrings(int index) {
                return ((StringPool) this.instance).getStrings(index);
            }

            @Override // com.android.server.usage.IntervalStatsProto.StringPoolOrBuilder
            public ByteString getStringsBytes(int index) {
                return ((StringPool) this.instance).getStringsBytes(index);
            }

            public Builder setStrings(int index, String value) {
                copyOnWrite();
                ((StringPool) this.instance).setStrings(index, value);
                return this;
            }

            public Builder addStrings(String value) {
                copyOnWrite();
                ((StringPool) this.instance).addStrings(value);
                return this;
            }

            public Builder addAllStrings(Iterable<String> values) {
                copyOnWrite();
                ((StringPool) this.instance).addAllStrings(values);
                return this;
            }

            public Builder clearStrings() {
                copyOnWrite();
                ((StringPool) this.instance).clearStrings();
                return this;
            }

            public Builder addStringsBytes(ByteString value) {
                copyOnWrite();
                ((StringPool) this.instance).addStringsBytes(value);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StringPool();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.strings_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StringPool other = (StringPool) arg1;
                    this.size_ = visitor.visitInt(hasSize(), this.size_, other.hasSize(), other.size_);
                    this.strings_ = visitor.visitList(this.strings_, other.strings_);
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
                                this.size_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                if (!this.strings_.isModifiable()) {
                                    this.strings_ = GeneratedMessageLite.mutableCopy(this.strings_);
                                }
                                this.strings_.add(s);
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
                        synchronized (StringPool.class) {
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

        public static StringPool getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StringPool> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class CountAndTime extends GeneratedMessageLite<CountAndTime, Builder> implements CountAndTimeOrBuilder {
        public static final int COUNT_FIELD_NUMBER = 1;
        private static final CountAndTime DEFAULT_INSTANCE = new CountAndTime();
        private static volatile Parser<CountAndTime> PARSER = null;
        public static final int TIME_MS_FIELD_NUMBER = 2;
        private int bitField0_;
        private int count_ = 0;
        private long timeMs_ = 0;

        private CountAndTime() {
        }

        @Override // com.android.server.usage.IntervalStatsProto.CountAndTimeOrBuilder
        public boolean hasCount() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.usage.IntervalStatsProto.CountAndTimeOrBuilder
        public int getCount() {
            return this.count_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCount(int value) {
            this.bitField0_ |= 1;
            this.count_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCount() {
            this.bitField0_ &= -2;
            this.count_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.CountAndTimeOrBuilder
        public boolean hasTimeMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.usage.IntervalStatsProto.CountAndTimeOrBuilder
        public long getTimeMs() {
            return this.timeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimeMs(long value) {
            this.bitField0_ |= 2;
            this.timeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimeMs() {
            this.bitField0_ &= -3;
            this.timeMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.count_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.timeMs_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.count_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.timeMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static CountAndTime parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CountAndTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CountAndTime parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CountAndTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CountAndTime parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CountAndTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CountAndTime parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CountAndTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CountAndTime parseFrom(InputStream input) throws IOException {
            return (CountAndTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CountAndTime parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CountAndTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CountAndTime parseDelimitedFrom(InputStream input) throws IOException {
            return (CountAndTime) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CountAndTime parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CountAndTime) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CountAndTime parseFrom(CodedInputStream input) throws IOException {
            return (CountAndTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CountAndTime parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CountAndTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CountAndTime prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CountAndTime, Builder> implements CountAndTimeOrBuilder {
            private Builder() {
                super(CountAndTime.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.usage.IntervalStatsProto.CountAndTimeOrBuilder
            public boolean hasCount() {
                return ((CountAndTime) this.instance).hasCount();
            }

            @Override // com.android.server.usage.IntervalStatsProto.CountAndTimeOrBuilder
            public int getCount() {
                return ((CountAndTime) this.instance).getCount();
            }

            public Builder setCount(int value) {
                copyOnWrite();
                ((CountAndTime) this.instance).setCount(value);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((CountAndTime) this.instance).clearCount();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.CountAndTimeOrBuilder
            public boolean hasTimeMs() {
                return ((CountAndTime) this.instance).hasTimeMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.CountAndTimeOrBuilder
            public long getTimeMs() {
                return ((CountAndTime) this.instance).getTimeMs();
            }

            public Builder setTimeMs(long value) {
                copyOnWrite();
                ((CountAndTime) this.instance).setTimeMs(value);
                return this;
            }

            public Builder clearTimeMs() {
                copyOnWrite();
                ((CountAndTime) this.instance).clearTimeMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new CountAndTime();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    CountAndTime other = (CountAndTime) arg1;
                    this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
                    this.timeMs_ = visitor.visitLong(hasTimeMs(), this.timeMs_, other.hasTimeMs(), other.timeMs_);
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
                                this.count_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.timeMs_ = input.readInt64();
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
                        synchronized (CountAndTime.class) {
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

        public static CountAndTime getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CountAndTime> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class UsageStats extends GeneratedMessageLite<UsageStats, Builder> implements UsageStatsOrBuilder {
        public static final int APP_LAUNCH_COUNT_FIELD_NUMBER = 6;
        public static final int CHOOSER_ACTIONS_FIELD_NUMBER = 7;
        private static final UsageStats DEFAULT_INSTANCE = new UsageStats();
        public static final int LAST_EVENT_FIELD_NUMBER = 5;
        public static final int LAST_TIME_ACTIVE_MS_FIELD_NUMBER = 3;
        public static final int LAST_TIME_SERVICE_USED_MS_FIELD_NUMBER = 8;
        public static final int LAST_TIME_VISIBLE_MS_FIELD_NUMBER = 10;
        public static final int PACKAGE_FIELD_NUMBER = 1;
        public static final int PACKAGE_INDEX_FIELD_NUMBER = 2;
        private static volatile Parser<UsageStats> PARSER = null;
        public static final int TOTAL_TIME_ACTIVE_MS_FIELD_NUMBER = 4;
        public static final int TOTAL_TIME_SERVICE_USED_MS_FIELD_NUMBER = 9;
        public static final int TOTAL_TIME_VISIBLE_MS_FIELD_NUMBER = 11;
        private int appLaunchCount_ = 0;
        private int bitField0_;
        private Internal.ProtobufList<ChooserAction> chooserActions_ = emptyProtobufList();
        private int lastEvent_ = 0;
        private long lastTimeActiveMs_ = 0;
        private long lastTimeServiceUsedMs_ = 0;
        private long lastTimeVisibleMs_ = 0;
        private int packageIndex_ = 0;
        private String package_ = "";
        private long totalTimeActiveMs_ = 0;
        private long totalTimeServiceUsedMs_ = 0;
        private long totalTimeVisibleMs_ = 0;

        public interface ChooserActionOrBuilder extends MessageLiteOrBuilder {
            ChooserAction.CategoryCount getCounts(int i);

            int getCountsCount();

            List<ChooserAction.CategoryCount> getCountsList();

            String getName();

            ByteString getNameBytes();

            boolean hasName();
        }

        private UsageStats() {
        }

        public static final class ChooserAction extends GeneratedMessageLite<ChooserAction, Builder> implements ChooserActionOrBuilder {
            public static final int COUNTS_FIELD_NUMBER = 3;
            private static final ChooserAction DEFAULT_INSTANCE = new ChooserAction();
            public static final int NAME_FIELD_NUMBER = 1;
            private static volatile Parser<ChooserAction> PARSER;
            private int bitField0_;
            private Internal.ProtobufList<CategoryCount> counts_ = emptyProtobufList();
            private String name_ = "";

            public interface CategoryCountOrBuilder extends MessageLiteOrBuilder {
                int getCount();

                String getName();

                ByteString getNameBytes();

                boolean hasCount();

                boolean hasName();
            }

            private ChooserAction() {
            }

            public static final class CategoryCount extends GeneratedMessageLite<CategoryCount, Builder> implements CategoryCountOrBuilder {
                public static final int COUNT_FIELD_NUMBER = 3;
                private static final CategoryCount DEFAULT_INSTANCE = new CategoryCount();
                public static final int NAME_FIELD_NUMBER = 1;
                private static volatile Parser<CategoryCount> PARSER;
                private int bitField0_;
                private int count_ = 0;
                private String name_ = "";

                private CategoryCount() {
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                public boolean hasName() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                public String getName() {
                    return this.name_;
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                public ByteString getNameBytes() {
                    return ByteString.copyFromUtf8(this.name_);
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setName(String value) {
                    if (value != null) {
                        this.bitField0_ |= 1;
                        this.name_ = value;
                        return;
                    }
                    throw new NullPointerException();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearName() {
                    this.bitField0_ &= -2;
                    this.name_ = getDefaultInstance().getName();
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setNameBytes(ByteString value) {
                    if (value != null) {
                        this.bitField0_ |= 1;
                        this.name_ = value.toStringUtf8();
                        return;
                    }
                    throw new NullPointerException();
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                public boolean hasCount() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                public int getCount() {
                    return this.count_;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void setCount(int value) {
                    this.bitField0_ |= 2;
                    this.count_ = value;
                }

                /* access modifiers changed from: private */
                /* access modifiers changed from: public */
                private void clearCount() {
                    this.bitField0_ &= -3;
                    this.count_ = 0;
                }

                @Override // com.google.protobuf.MessageLite
                public void writeTo(CodedOutputStream output) throws IOException {
                    if ((this.bitField0_ & 1) == 1) {
                        output.writeString(1, getName());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        output.writeInt32(3, this.count_);
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
                        size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        size2 += CodedOutputStream.computeInt32Size(3, this.count_);
                    }
                    int size3 = size2 + this.unknownFields.getSerializedSize();
                    this.memoizedSerializedSize = size3;
                    return size3;
                }

                public static CategoryCount parseFrom(ByteString data) throws InvalidProtocolBufferException {
                    return (CategoryCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                }

                public static CategoryCount parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return (CategoryCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                }

                public static CategoryCount parseFrom(byte[] data) throws InvalidProtocolBufferException {
                    return (CategoryCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
                }

                public static CategoryCount parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return (CategoryCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
                }

                public static CategoryCount parseFrom(InputStream input) throws IOException {
                    return (CategoryCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                }

                public static CategoryCount parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (CategoryCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static CategoryCount parseDelimitedFrom(InputStream input) throws IOException {
                    return (CategoryCount) parseDelimitedFrom(DEFAULT_INSTANCE, input);
                }

                public static CategoryCount parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (CategoryCount) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static CategoryCount parseFrom(CodedInputStream input) throws IOException {
                    return (CategoryCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
                }

                public static CategoryCount parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    return (CategoryCount) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
                }

                public static Builder newBuilder() {
                    return (Builder) DEFAULT_INSTANCE.toBuilder();
                }

                public static Builder newBuilder(CategoryCount prototype) {
                    return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
                }

                public static final class Builder extends GeneratedMessageLite.Builder<CategoryCount, Builder> implements CategoryCountOrBuilder {
                    private Builder() {
                        super(CategoryCount.DEFAULT_INSTANCE);
                    }

                    @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                    public boolean hasName() {
                        return ((CategoryCount) this.instance).hasName();
                    }

                    @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                    public String getName() {
                        return ((CategoryCount) this.instance).getName();
                    }

                    @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                    public ByteString getNameBytes() {
                        return ((CategoryCount) this.instance).getNameBytes();
                    }

                    public Builder setName(String value) {
                        copyOnWrite();
                        ((CategoryCount) this.instance).setName(value);
                        return this;
                    }

                    public Builder clearName() {
                        copyOnWrite();
                        ((CategoryCount) this.instance).clearName();
                        return this;
                    }

                    public Builder setNameBytes(ByteString value) {
                        copyOnWrite();
                        ((CategoryCount) this.instance).setNameBytes(value);
                        return this;
                    }

                    @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                    public boolean hasCount() {
                        return ((CategoryCount) this.instance).hasCount();
                    }

                    @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserAction.CategoryCountOrBuilder
                    public int getCount() {
                        return ((CategoryCount) this.instance).getCount();
                    }

                    public Builder setCount(int value) {
                        copyOnWrite();
                        ((CategoryCount) this.instance).setCount(value);
                        return this;
                    }

                    public Builder clearCount() {
                        copyOnWrite();
                        ((CategoryCount) this.instance).clearCount();
                        return this;
                    }
                }

                /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
                /* access modifiers changed from: protected */
                @Override // com.google.protobuf.GeneratedMessageLite
                public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                    switch (method) {
                        case NEW_MUTABLE_INSTANCE:
                            return new CategoryCount();
                        case IS_INITIALIZED:
                            return DEFAULT_INSTANCE;
                        case MAKE_IMMUTABLE:
                            return null;
                        case NEW_BUILDER:
                            return new Builder();
                        case VISIT:
                            GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                            CategoryCount other = (CategoryCount) arg1;
                            this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                            this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
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
                                    } else if (tag == 10) {
                                        String s = input.readString();
                                        this.bitField0_ |= 1;
                                        this.name_ = s;
                                    } else if (tag == 24) {
                                        this.bitField0_ |= 2;
                                        this.count_ = input.readInt32();
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
                                synchronized (CategoryCount.class) {
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

                public static CategoryCount getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<CategoryCount> parser() {
                    return DEFAULT_INSTANCE.getParserForType();
                }
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
            public String getName() {
                return this.name_;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
            public ByteString getNameBytes() {
                return ByteString.copyFromUtf8(this.name_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setName(String value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.name_ = value;
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearName() {
                this.bitField0_ &= -2;
                this.name_ = getDefaultInstance().getName();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setNameBytes(ByteString value) {
                if (value != null) {
                    this.bitField0_ |= 1;
                    this.name_ = value.toStringUtf8();
                    return;
                }
                throw new NullPointerException();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
            public List<CategoryCount> getCountsList() {
                return this.counts_;
            }

            public List<? extends CategoryCountOrBuilder> getCountsOrBuilderList() {
                return this.counts_;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
            public int getCountsCount() {
                return this.counts_.size();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
            public CategoryCount getCounts(int index) {
                return this.counts_.get(index);
            }

            public CategoryCountOrBuilder getCountsOrBuilder(int index) {
                return this.counts_.get(index);
            }

            private void ensureCountsIsMutable() {
                if (!this.counts_.isModifiable()) {
                    this.counts_ = GeneratedMessageLite.mutableCopy(this.counts_);
                }
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCounts(int index, CategoryCount value) {
                if (value != null) {
                    ensureCountsIsMutable();
                    this.counts_.set(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void setCounts(int index, CategoryCount.Builder builderForValue) {
                ensureCountsIsMutable();
                this.counts_.set(index, (CategoryCount) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addCounts(CategoryCount value) {
                if (value != null) {
                    ensureCountsIsMutable();
                    this.counts_.add(value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addCounts(int index, CategoryCount value) {
                if (value != null) {
                    ensureCountsIsMutable();
                    this.counts_.add(index, value);
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addCounts(CategoryCount.Builder builderForValue) {
                ensureCountsIsMutable();
                this.counts_.add((CategoryCount) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addCounts(int index, CategoryCount.Builder builderForValue) {
                ensureCountsIsMutable();
                this.counts_.add(index, (CategoryCount) builderForValue.build());
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void addAllCounts(Iterable<? extends CategoryCount> values) {
                ensureCountsIsMutable();
                AbstractMessageLite.addAll(values, this.counts_);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void clearCounts() {
                this.counts_ = emptyProtobufList();
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void removeCounts(int index) {
                ensureCountsIsMutable();
                this.counts_.remove(index);
            }

            @Override // com.google.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    output.writeString(1, getName());
                }
                for (int i = 0; i < this.counts_.size(); i++) {
                    output.writeMessage(3, this.counts_.get(i));
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
                    size2 = 0 + CodedOutputStream.computeStringSize(1, getName());
                }
                for (int i = 0; i < this.counts_.size(); i++) {
                    size2 += CodedOutputStream.computeMessageSize(3, this.counts_.get(i));
                }
                int size3 = size2 + this.unknownFields.getSerializedSize();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static ChooserAction parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (ChooserAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ChooserAction parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ChooserAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ChooserAction parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (ChooserAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static ChooserAction parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (ChooserAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static ChooserAction parseFrom(InputStream input) throws IOException {
                return (ChooserAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ChooserAction parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ChooserAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ChooserAction parseDelimitedFrom(InputStream input) throws IOException {
                return (ChooserAction) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static ChooserAction parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ChooserAction) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static ChooserAction parseFrom(CodedInputStream input) throws IOException {
                return (ChooserAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static ChooserAction parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (ChooserAction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Builder newBuilder(ChooserAction prototype) {
                return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<ChooserAction, Builder> implements ChooserActionOrBuilder {
                private Builder() {
                    super(ChooserAction.DEFAULT_INSTANCE);
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
                public boolean hasName() {
                    return ((ChooserAction) this.instance).hasName();
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
                public String getName() {
                    return ((ChooserAction) this.instance).getName();
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
                public ByteString getNameBytes() {
                    return ((ChooserAction) this.instance).getNameBytes();
                }

                public Builder setName(String value) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).setName(value);
                    return this;
                }

                public Builder clearName() {
                    copyOnWrite();
                    ((ChooserAction) this.instance).clearName();
                    return this;
                }

                public Builder setNameBytes(ByteString value) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).setNameBytes(value);
                    return this;
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
                public List<CategoryCount> getCountsList() {
                    return Collections.unmodifiableList(((ChooserAction) this.instance).getCountsList());
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
                public int getCountsCount() {
                    return ((ChooserAction) this.instance).getCountsCount();
                }

                @Override // com.android.server.usage.IntervalStatsProto.UsageStats.ChooserActionOrBuilder
                public CategoryCount getCounts(int index) {
                    return ((ChooserAction) this.instance).getCounts(index);
                }

                public Builder setCounts(int index, CategoryCount value) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).setCounts((ChooserAction) index, (int) value);
                    return this;
                }

                public Builder setCounts(int index, CategoryCount.Builder builderForValue) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).setCounts((ChooserAction) index, (int) builderForValue);
                    return this;
                }

                public Builder addCounts(CategoryCount value) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).addCounts((ChooserAction) value);
                    return this;
                }

                public Builder addCounts(int index, CategoryCount value) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).addCounts((ChooserAction) index, (int) value);
                    return this;
                }

                public Builder addCounts(CategoryCount.Builder builderForValue) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).addCounts((ChooserAction) builderForValue);
                    return this;
                }

                public Builder addCounts(int index, CategoryCount.Builder builderForValue) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).addCounts((ChooserAction) index, (int) builderForValue);
                    return this;
                }

                public Builder addAllCounts(Iterable<? extends CategoryCount> values) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).addAllCounts(values);
                    return this;
                }

                public Builder clearCounts() {
                    copyOnWrite();
                    ((ChooserAction) this.instance).clearCounts();
                    return this;
                }

                public Builder removeCounts(int index) {
                    copyOnWrite();
                    ((ChooserAction) this.instance).removeCounts(index);
                    return this;
                }
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageLite
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                switch (method) {
                    case NEW_MUTABLE_INSTANCE:
                        return new ChooserAction();
                    case IS_INITIALIZED:
                        return DEFAULT_INSTANCE;
                    case MAKE_IMMUTABLE:
                        this.counts_.makeImmutable();
                        return null;
                    case NEW_BUILDER:
                        return new Builder();
                    case VISIT:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                        ChooserAction other = (ChooserAction) arg1;
                        this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                        this.counts_ = visitor.visitList(this.counts_, other.counts_);
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
                                    this.name_ = s;
                                } else if (tag == 26) {
                                    if (!this.counts_.isModifiable()) {
                                        this.counts_ = GeneratedMessageLite.mutableCopy(this.counts_);
                                    }
                                    this.counts_.add((CategoryCount) input.readMessage(CategoryCount.parser(), extensionRegistry));
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
                            synchronized (ChooserAction.class) {
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

            public static ChooserAction getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ChooserAction> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasPackage() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public String getPackage() {
            return this.package_;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public ByteString getPackageBytes() {
            return ByteString.copyFromUtf8(this.package_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackage(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.package_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackage() {
            this.bitField0_ &= -2;
            this.package_ = getDefaultInstance().getPackage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.package_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasPackageIndex() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public int getPackageIndex() {
            return this.packageIndex_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageIndex(int value) {
            this.bitField0_ |= 2;
            this.packageIndex_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackageIndex() {
            this.bitField0_ &= -3;
            this.packageIndex_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasLastTimeActiveMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public long getLastTimeActiveMs() {
            return this.lastTimeActiveMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastTimeActiveMs(long value) {
            this.bitField0_ |= 4;
            this.lastTimeActiveMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastTimeActiveMs() {
            this.bitField0_ &= -5;
            this.lastTimeActiveMs_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasTotalTimeActiveMs() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public long getTotalTimeActiveMs() {
            return this.totalTimeActiveMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalTimeActiveMs(long value) {
            this.bitField0_ |= 8;
            this.totalTimeActiveMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalTimeActiveMs() {
            this.bitField0_ &= -9;
            this.totalTimeActiveMs_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasLastEvent() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public int getLastEvent() {
            return this.lastEvent_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastEvent(int value) {
            this.bitField0_ |= 16;
            this.lastEvent_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastEvent() {
            this.bitField0_ &= -17;
            this.lastEvent_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasAppLaunchCount() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public int getAppLaunchCount() {
            return this.appLaunchCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAppLaunchCount(int value) {
            this.bitField0_ |= 32;
            this.appLaunchCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAppLaunchCount() {
            this.bitField0_ &= -33;
            this.appLaunchCount_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public List<ChooserAction> getChooserActionsList() {
            return this.chooserActions_;
        }

        public List<? extends ChooserActionOrBuilder> getChooserActionsOrBuilderList() {
            return this.chooserActions_;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public int getChooserActionsCount() {
            return this.chooserActions_.size();
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public ChooserAction getChooserActions(int index) {
            return this.chooserActions_.get(index);
        }

        public ChooserActionOrBuilder getChooserActionsOrBuilder(int index) {
            return this.chooserActions_.get(index);
        }

        private void ensureChooserActionsIsMutable() {
            if (!this.chooserActions_.isModifiable()) {
                this.chooserActions_ = GeneratedMessageLite.mutableCopy(this.chooserActions_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChooserActions(int index, ChooserAction value) {
            if (value != null) {
                ensureChooserActionsIsMutable();
                this.chooserActions_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChooserActions(int index, ChooserAction.Builder builderForValue) {
            ensureChooserActionsIsMutable();
            this.chooserActions_.set(index, (ChooserAction) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChooserActions(ChooserAction value) {
            if (value != null) {
                ensureChooserActionsIsMutable();
                this.chooserActions_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChooserActions(int index, ChooserAction value) {
            if (value != null) {
                ensureChooserActionsIsMutable();
                this.chooserActions_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChooserActions(ChooserAction.Builder builderForValue) {
            ensureChooserActionsIsMutable();
            this.chooserActions_.add((ChooserAction) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChooserActions(int index, ChooserAction.Builder builderForValue) {
            ensureChooserActionsIsMutable();
            this.chooserActions_.add(index, (ChooserAction) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllChooserActions(Iterable<? extends ChooserAction> values) {
            ensureChooserActionsIsMutable();
            AbstractMessageLite.addAll(values, this.chooserActions_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChooserActions() {
            this.chooserActions_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeChooserActions(int index) {
            ensureChooserActionsIsMutable();
            this.chooserActions_.remove(index);
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasLastTimeServiceUsedMs() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public long getLastTimeServiceUsedMs() {
            return this.lastTimeServiceUsedMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastTimeServiceUsedMs(long value) {
            this.bitField0_ |= 64;
            this.lastTimeServiceUsedMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastTimeServiceUsedMs() {
            this.bitField0_ &= -65;
            this.lastTimeServiceUsedMs_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasTotalTimeServiceUsedMs() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public long getTotalTimeServiceUsedMs() {
            return this.totalTimeServiceUsedMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalTimeServiceUsedMs(long value) {
            this.bitField0_ |= 128;
            this.totalTimeServiceUsedMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalTimeServiceUsedMs() {
            this.bitField0_ &= -129;
            this.totalTimeServiceUsedMs_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasLastTimeVisibleMs() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public long getLastTimeVisibleMs() {
            return this.lastTimeVisibleMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastTimeVisibleMs(long value) {
            this.bitField0_ |= 256;
            this.lastTimeVisibleMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastTimeVisibleMs() {
            this.bitField0_ &= -257;
            this.lastTimeVisibleMs_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public boolean hasTotalTimeVisibleMs() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
        public long getTotalTimeVisibleMs() {
            return this.totalTimeVisibleMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalTimeVisibleMs(long value) {
            this.bitField0_ |= 512;
            this.totalTimeVisibleMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalTimeVisibleMs() {
            this.bitField0_ &= -513;
            this.totalTimeVisibleMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.packageIndex_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.lastTimeActiveMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt64(4, this.totalTimeActiveMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.lastEvent_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.appLaunchCount_);
            }
            for (int i = 0; i < this.chooserActions_.size(); i++) {
                output.writeMessage(7, this.chooserActions_.get(i));
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(8, this.lastTimeServiceUsedMs_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt64(9, this.totalTimeServiceUsedMs_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(10, this.lastTimeVisibleMs_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(11, this.totalTimeVisibleMs_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.packageIndex_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.lastTimeActiveMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt64Size(4, this.totalTimeActiveMs_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.lastEvent_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.appLaunchCount_);
            }
            for (int i = 0; i < this.chooserActions_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(7, this.chooserActions_.get(i));
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(8, this.lastTimeServiceUsedMs_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt64Size(9, this.totalTimeServiceUsedMs_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(10, this.lastTimeVisibleMs_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(11, this.totalTimeVisibleMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static UsageStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (UsageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UsageStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UsageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UsageStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (UsageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UsageStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UsageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UsageStats parseFrom(InputStream input) throws IOException {
            return (UsageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UsageStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UsageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UsageStats parseDelimitedFrom(InputStream input) throws IOException {
            return (UsageStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static UsageStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UsageStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UsageStats parseFrom(CodedInputStream input) throws IOException {
            return (UsageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UsageStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UsageStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UsageStats prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UsageStats, Builder> implements UsageStatsOrBuilder {
            private Builder() {
                super(UsageStats.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasPackage() {
                return ((UsageStats) this.instance).hasPackage();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public String getPackage() {
                return ((UsageStats) this.instance).getPackage();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public ByteString getPackageBytes() {
                return ((UsageStats) this.instance).getPackageBytes();
            }

            public Builder setPackage(String value) {
                copyOnWrite();
                ((UsageStats) this.instance).setPackage(value);
                return this;
            }

            public Builder clearPackage() {
                copyOnWrite();
                ((UsageStats) this.instance).clearPackage();
                return this;
            }

            public Builder setPackageBytes(ByteString value) {
                copyOnWrite();
                ((UsageStats) this.instance).setPackageBytes(value);
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasPackageIndex() {
                return ((UsageStats) this.instance).hasPackageIndex();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public int getPackageIndex() {
                return ((UsageStats) this.instance).getPackageIndex();
            }

            public Builder setPackageIndex(int value) {
                copyOnWrite();
                ((UsageStats) this.instance).setPackageIndex(value);
                return this;
            }

            public Builder clearPackageIndex() {
                copyOnWrite();
                ((UsageStats) this.instance).clearPackageIndex();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasLastTimeActiveMs() {
                return ((UsageStats) this.instance).hasLastTimeActiveMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public long getLastTimeActiveMs() {
                return ((UsageStats) this.instance).getLastTimeActiveMs();
            }

            public Builder setLastTimeActiveMs(long value) {
                copyOnWrite();
                ((UsageStats) this.instance).setLastTimeActiveMs(value);
                return this;
            }

            public Builder clearLastTimeActiveMs() {
                copyOnWrite();
                ((UsageStats) this.instance).clearLastTimeActiveMs();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasTotalTimeActiveMs() {
                return ((UsageStats) this.instance).hasTotalTimeActiveMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public long getTotalTimeActiveMs() {
                return ((UsageStats) this.instance).getTotalTimeActiveMs();
            }

            public Builder setTotalTimeActiveMs(long value) {
                copyOnWrite();
                ((UsageStats) this.instance).setTotalTimeActiveMs(value);
                return this;
            }

            public Builder clearTotalTimeActiveMs() {
                copyOnWrite();
                ((UsageStats) this.instance).clearTotalTimeActiveMs();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasLastEvent() {
                return ((UsageStats) this.instance).hasLastEvent();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public int getLastEvent() {
                return ((UsageStats) this.instance).getLastEvent();
            }

            public Builder setLastEvent(int value) {
                copyOnWrite();
                ((UsageStats) this.instance).setLastEvent(value);
                return this;
            }

            public Builder clearLastEvent() {
                copyOnWrite();
                ((UsageStats) this.instance).clearLastEvent();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasAppLaunchCount() {
                return ((UsageStats) this.instance).hasAppLaunchCount();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public int getAppLaunchCount() {
                return ((UsageStats) this.instance).getAppLaunchCount();
            }

            public Builder setAppLaunchCount(int value) {
                copyOnWrite();
                ((UsageStats) this.instance).setAppLaunchCount(value);
                return this;
            }

            public Builder clearAppLaunchCount() {
                copyOnWrite();
                ((UsageStats) this.instance).clearAppLaunchCount();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public List<ChooserAction> getChooserActionsList() {
                return Collections.unmodifiableList(((UsageStats) this.instance).getChooserActionsList());
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public int getChooserActionsCount() {
                return ((UsageStats) this.instance).getChooserActionsCount();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public ChooserAction getChooserActions(int index) {
                return ((UsageStats) this.instance).getChooserActions(index);
            }

            public Builder setChooserActions(int index, ChooserAction value) {
                copyOnWrite();
                ((UsageStats) this.instance).setChooserActions((UsageStats) index, (int) value);
                return this;
            }

            public Builder setChooserActions(int index, ChooserAction.Builder builderForValue) {
                copyOnWrite();
                ((UsageStats) this.instance).setChooserActions((UsageStats) index, (int) builderForValue);
                return this;
            }

            public Builder addChooserActions(ChooserAction value) {
                copyOnWrite();
                ((UsageStats) this.instance).addChooserActions((UsageStats) value);
                return this;
            }

            public Builder addChooserActions(int index, ChooserAction value) {
                copyOnWrite();
                ((UsageStats) this.instance).addChooserActions((UsageStats) index, (int) value);
                return this;
            }

            public Builder addChooserActions(ChooserAction.Builder builderForValue) {
                copyOnWrite();
                ((UsageStats) this.instance).addChooserActions((UsageStats) builderForValue);
                return this;
            }

            public Builder addChooserActions(int index, ChooserAction.Builder builderForValue) {
                copyOnWrite();
                ((UsageStats) this.instance).addChooserActions((UsageStats) index, (int) builderForValue);
                return this;
            }

            public Builder addAllChooserActions(Iterable<? extends ChooserAction> values) {
                copyOnWrite();
                ((UsageStats) this.instance).addAllChooserActions(values);
                return this;
            }

            public Builder clearChooserActions() {
                copyOnWrite();
                ((UsageStats) this.instance).clearChooserActions();
                return this;
            }

            public Builder removeChooserActions(int index) {
                copyOnWrite();
                ((UsageStats) this.instance).removeChooserActions(index);
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasLastTimeServiceUsedMs() {
                return ((UsageStats) this.instance).hasLastTimeServiceUsedMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public long getLastTimeServiceUsedMs() {
                return ((UsageStats) this.instance).getLastTimeServiceUsedMs();
            }

            public Builder setLastTimeServiceUsedMs(long value) {
                copyOnWrite();
                ((UsageStats) this.instance).setLastTimeServiceUsedMs(value);
                return this;
            }

            public Builder clearLastTimeServiceUsedMs() {
                copyOnWrite();
                ((UsageStats) this.instance).clearLastTimeServiceUsedMs();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasTotalTimeServiceUsedMs() {
                return ((UsageStats) this.instance).hasTotalTimeServiceUsedMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public long getTotalTimeServiceUsedMs() {
                return ((UsageStats) this.instance).getTotalTimeServiceUsedMs();
            }

            public Builder setTotalTimeServiceUsedMs(long value) {
                copyOnWrite();
                ((UsageStats) this.instance).setTotalTimeServiceUsedMs(value);
                return this;
            }

            public Builder clearTotalTimeServiceUsedMs() {
                copyOnWrite();
                ((UsageStats) this.instance).clearTotalTimeServiceUsedMs();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasLastTimeVisibleMs() {
                return ((UsageStats) this.instance).hasLastTimeVisibleMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public long getLastTimeVisibleMs() {
                return ((UsageStats) this.instance).getLastTimeVisibleMs();
            }

            public Builder setLastTimeVisibleMs(long value) {
                copyOnWrite();
                ((UsageStats) this.instance).setLastTimeVisibleMs(value);
                return this;
            }

            public Builder clearLastTimeVisibleMs() {
                copyOnWrite();
                ((UsageStats) this.instance).clearLastTimeVisibleMs();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public boolean hasTotalTimeVisibleMs() {
                return ((UsageStats) this.instance).hasTotalTimeVisibleMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.UsageStatsOrBuilder
            public long getTotalTimeVisibleMs() {
                return ((UsageStats) this.instance).getTotalTimeVisibleMs();
            }

            public Builder setTotalTimeVisibleMs(long value) {
                copyOnWrite();
                ((UsageStats) this.instance).setTotalTimeVisibleMs(value);
                return this;
            }

            public Builder clearTotalTimeVisibleMs() {
                copyOnWrite();
                ((UsageStats) this.instance).clearTotalTimeVisibleMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new UsageStats();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.chooserActions_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    UsageStats other = (UsageStats) arg1;
                    this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                    this.packageIndex_ = visitor.visitInt(hasPackageIndex(), this.packageIndex_, other.hasPackageIndex(), other.packageIndex_);
                    this.lastTimeActiveMs_ = visitor.visitLong(hasLastTimeActiveMs(), this.lastTimeActiveMs_, other.hasLastTimeActiveMs(), other.lastTimeActiveMs_);
                    this.totalTimeActiveMs_ = visitor.visitLong(hasTotalTimeActiveMs(), this.totalTimeActiveMs_, other.hasTotalTimeActiveMs(), other.totalTimeActiveMs_);
                    this.lastEvent_ = visitor.visitInt(hasLastEvent(), this.lastEvent_, other.hasLastEvent(), other.lastEvent_);
                    this.appLaunchCount_ = visitor.visitInt(hasAppLaunchCount(), this.appLaunchCount_, other.hasAppLaunchCount(), other.appLaunchCount_);
                    this.chooserActions_ = visitor.visitList(this.chooserActions_, other.chooserActions_);
                    this.lastTimeServiceUsedMs_ = visitor.visitLong(hasLastTimeServiceUsedMs(), this.lastTimeServiceUsedMs_, other.hasLastTimeServiceUsedMs(), other.lastTimeServiceUsedMs_);
                    this.totalTimeServiceUsedMs_ = visitor.visitLong(hasTotalTimeServiceUsedMs(), this.totalTimeServiceUsedMs_, other.hasTotalTimeServiceUsedMs(), other.totalTimeServiceUsedMs_);
                    this.lastTimeVisibleMs_ = visitor.visitLong(hasLastTimeVisibleMs(), this.lastTimeVisibleMs_, other.hasLastTimeVisibleMs(), other.lastTimeVisibleMs_);
                    this.totalTimeVisibleMs_ = visitor.visitLong(hasTotalTimeVisibleMs(), this.totalTimeVisibleMs_, other.hasTotalTimeVisibleMs(), other.totalTimeVisibleMs_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 10:
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.package_ = s;
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.packageIndex_ = input.readInt32();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.lastTimeActiveMs_ = input.readInt64();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.totalTimeActiveMs_ = input.readInt64();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.lastEvent_ = input.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.appLaunchCount_ = input.readInt32();
                                    break;
                                case 58:
                                    if (!this.chooserActions_.isModifiable()) {
                                        this.chooserActions_ = GeneratedMessageLite.mutableCopy(this.chooserActions_);
                                    }
                                    this.chooserActions_.add((ChooserAction) input.readMessage(ChooserAction.parser(), extensionRegistry));
                                    break;
                                case 64:
                                    this.bitField0_ |= 64;
                                    this.lastTimeServiceUsedMs_ = input.readInt64();
                                    break;
                                case 72:
                                    this.bitField0_ |= 128;
                                    this.totalTimeServiceUsedMs_ = input.readInt64();
                                    break;
                                case 80:
                                    this.bitField0_ |= 256;
                                    this.lastTimeVisibleMs_ = input.readInt64();
                                    break;
                                case 88:
                                    this.bitField0_ |= 512;
                                    this.totalTimeVisibleMs_ = input.readInt64();
                                    break;
                                default:
                                    if (parseUnknownField(tag, input)) {
                                        break;
                                    } else {
                                        done = true;
                                        break;
                                    }
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
                        synchronized (UsageStats.class) {
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

        public static UsageStats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UsageStats> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Configuration extends GeneratedMessageLite<Configuration, Builder> implements ConfigurationOrBuilder {
        public static final int ACTIVE_FIELD_NUMBER = 5;
        public static final int CONFIG_FIELD_NUMBER = 1;
        public static final int COUNT_FIELD_NUMBER = 4;
        private static final Configuration DEFAULT_INSTANCE = new Configuration();
        public static final int LAST_TIME_ACTIVE_MS_FIELD_NUMBER = 2;
        private static volatile Parser<Configuration> PARSER = null;
        public static final int TOTAL_TIME_ACTIVE_MS_FIELD_NUMBER = 3;
        private boolean active_ = false;
        private int bitField0_;
        private ConfigurationProto config_;
        private int count_ = 0;
        private long lastTimeActiveMs_ = 0;
        private long totalTimeActiveMs_ = 0;

        private Configuration() {
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public boolean hasConfig() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public ConfigurationProto getConfig() {
            ConfigurationProto configurationProto = this.config_;
            return configurationProto == null ? ConfigurationProto.getDefaultInstance() : configurationProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setConfig(ConfigurationProto value) {
            if (value != null) {
                this.config_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setConfig(ConfigurationProto.Builder builderForValue) {
            this.config_ = (ConfigurationProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeConfig(ConfigurationProto value) {
            ConfigurationProto configurationProto = this.config_;
            if (configurationProto == null || configurationProto == ConfigurationProto.getDefaultInstance()) {
                this.config_ = value;
            } else {
                this.config_ = (ConfigurationProto) ((ConfigurationProto.Builder) ConfigurationProto.newBuilder(this.config_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearConfig() {
            this.config_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public boolean hasLastTimeActiveMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public long getLastTimeActiveMs() {
            return this.lastTimeActiveMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastTimeActiveMs(long value) {
            this.bitField0_ |= 2;
            this.lastTimeActiveMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastTimeActiveMs() {
            this.bitField0_ &= -3;
            this.lastTimeActiveMs_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public boolean hasTotalTimeActiveMs() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public long getTotalTimeActiveMs() {
            return this.totalTimeActiveMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalTimeActiveMs(long value) {
            this.bitField0_ |= 4;
            this.totalTimeActiveMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalTimeActiveMs() {
            this.bitField0_ &= -5;
            this.totalTimeActiveMs_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public boolean hasCount() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public int getCount() {
            return this.count_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCount(int value) {
            this.bitField0_ |= 8;
            this.count_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCount() {
            this.bitField0_ &= -9;
            this.count_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public boolean hasActive() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
        public boolean getActive() {
            return this.active_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActive(boolean value) {
            this.bitField0_ |= 16;
            this.active_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActive() {
            this.bitField0_ &= -17;
            this.active_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getConfig());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.lastTimeActiveMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt64(3, this.totalTimeActiveMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.count_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBool(5, this.active_);
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getConfig());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt64Size(2, this.lastTimeActiveMs_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt64Size(3, this.totalTimeActiveMs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.count_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBoolSize(5, this.active_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Configuration parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Configuration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Configuration parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Configuration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Configuration parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Configuration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Configuration parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Configuration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Configuration parseFrom(InputStream input) throws IOException {
            return (Configuration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Configuration parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Configuration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Configuration parseDelimitedFrom(InputStream input) throws IOException {
            return (Configuration) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Configuration parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Configuration) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Configuration parseFrom(CodedInputStream input) throws IOException {
            return (Configuration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Configuration parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Configuration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Configuration prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Configuration, Builder> implements ConfigurationOrBuilder {
            private Builder() {
                super(Configuration.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public boolean hasConfig() {
                return ((Configuration) this.instance).hasConfig();
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public ConfigurationProto getConfig() {
                return ((Configuration) this.instance).getConfig();
            }

            public Builder setConfig(ConfigurationProto value) {
                copyOnWrite();
                ((Configuration) this.instance).setConfig((Configuration) value);
                return this;
            }

            public Builder setConfig(ConfigurationProto.Builder builderForValue) {
                copyOnWrite();
                ((Configuration) this.instance).setConfig((Configuration) builderForValue);
                return this;
            }

            public Builder mergeConfig(ConfigurationProto value) {
                copyOnWrite();
                ((Configuration) this.instance).mergeConfig(value);
                return this;
            }

            public Builder clearConfig() {
                copyOnWrite();
                ((Configuration) this.instance).clearConfig();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public boolean hasLastTimeActiveMs() {
                return ((Configuration) this.instance).hasLastTimeActiveMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public long getLastTimeActiveMs() {
                return ((Configuration) this.instance).getLastTimeActiveMs();
            }

            public Builder setLastTimeActiveMs(long value) {
                copyOnWrite();
                ((Configuration) this.instance).setLastTimeActiveMs(value);
                return this;
            }

            public Builder clearLastTimeActiveMs() {
                copyOnWrite();
                ((Configuration) this.instance).clearLastTimeActiveMs();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public boolean hasTotalTimeActiveMs() {
                return ((Configuration) this.instance).hasTotalTimeActiveMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public long getTotalTimeActiveMs() {
                return ((Configuration) this.instance).getTotalTimeActiveMs();
            }

            public Builder setTotalTimeActiveMs(long value) {
                copyOnWrite();
                ((Configuration) this.instance).setTotalTimeActiveMs(value);
                return this;
            }

            public Builder clearTotalTimeActiveMs() {
                copyOnWrite();
                ((Configuration) this.instance).clearTotalTimeActiveMs();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public boolean hasCount() {
                return ((Configuration) this.instance).hasCount();
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public int getCount() {
                return ((Configuration) this.instance).getCount();
            }

            public Builder setCount(int value) {
                copyOnWrite();
                ((Configuration) this.instance).setCount(value);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((Configuration) this.instance).clearCount();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public boolean hasActive() {
                return ((Configuration) this.instance).hasActive();
            }

            @Override // com.android.server.usage.IntervalStatsProto.ConfigurationOrBuilder
            public boolean getActive() {
                return ((Configuration) this.instance).getActive();
            }

            public Builder setActive(boolean value) {
                copyOnWrite();
                ((Configuration) this.instance).setActive(value);
                return this;
            }

            public Builder clearActive() {
                copyOnWrite();
                ((Configuration) this.instance).clearActive();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Configuration();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Configuration other = (Configuration) arg1;
                    this.config_ = (ConfigurationProto) visitor.visitMessage(this.config_, other.config_);
                    this.lastTimeActiveMs_ = visitor.visitLong(hasLastTimeActiveMs(), this.lastTimeActiveMs_, other.hasLastTimeActiveMs(), other.lastTimeActiveMs_);
                    this.totalTimeActiveMs_ = visitor.visitLong(hasTotalTimeActiveMs(), this.totalTimeActiveMs_, other.hasTotalTimeActiveMs(), other.totalTimeActiveMs_);
                    this.count_ = visitor.visitInt(hasCount(), this.count_, other.hasCount(), other.count_);
                    this.active_ = visitor.visitBoolean(hasActive(), this.active_, other.hasActive(), other.active_);
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
                                ConfigurationProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (ConfigurationProto.Builder) this.config_.toBuilder();
                                }
                                this.config_ = (ConfigurationProto) input.readMessage(ConfigurationProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.config_);
                                    this.config_ = (ConfigurationProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.lastTimeActiveMs_ = input.readInt64();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.totalTimeActiveMs_ = input.readInt64();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.count_ = input.readInt32();
                            } else if (tag == 40) {
                                this.bitField0_ = 16 | this.bitField0_;
                                this.active_ = input.readBool();
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
                        synchronized (Configuration.class) {
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

        public static Configuration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Configuration> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Event extends GeneratedMessageLite<Event, Builder> implements EventOrBuilder {
        public static final int CLASS_FIELD_NUMBER = 3;
        public static final int CLASS_INDEX_FIELD_NUMBER = 4;
        public static final int CONFIG_FIELD_NUMBER = 8;
        private static final Event DEFAULT_INSTANCE = new Event();
        public static final int FLAGS_FIELD_NUMBER = 6;
        public static final int INSTANCE_ID_FIELD_NUMBER = 14;
        public static final int NOTIFICATION_CHANNEL_FIELD_NUMBER = 12;
        public static final int NOTIFICATION_CHANNEL_INDEX_FIELD_NUMBER = 13;
        public static final int PACKAGE_FIELD_NUMBER = 1;
        public static final int PACKAGE_INDEX_FIELD_NUMBER = 2;
        private static volatile Parser<Event> PARSER = null;
        public static final int SHORTCUT_ID_FIELD_NUMBER = 9;
        public static final int STANDBY_BUCKET_FIELD_NUMBER = 11;
        public static final int TASK_ROOT_CLASS_INDEX_FIELD_NUMBER = 16;
        public static final int TASK_ROOT_PACKAGE_INDEX_FIELD_NUMBER = 15;
        public static final int TIME_MS_FIELD_NUMBER = 5;
        public static final int TYPE_FIELD_NUMBER = 7;
        private int bitField0_;
        private int classIndex_ = 0;
        private String class__ = "";
        private ConfigurationProto config_;
        private int flags_ = 0;
        private int instanceId_ = 0;
        private int notificationChannelIndex_ = 0;
        private String notificationChannel_ = "";
        private int packageIndex_ = 0;
        private String package_ = "";
        private String shortcutId_ = "";
        private int standbyBucket_ = 0;
        private int taskRootClassIndex_ = 0;
        private int taskRootPackageIndex_ = 0;
        private long timeMs_ = 0;
        private int type_ = 0;

        private Event() {
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasPackage() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public String getPackage() {
            return this.package_;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public ByteString getPackageBytes() {
            return ByteString.copyFromUtf8(this.package_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackage(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.package_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackage() {
            this.bitField0_ &= -2;
            this.package_ = getDefaultInstance().getPackage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.package_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasPackageIndex() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getPackageIndex() {
            return this.packageIndex_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageIndex(int value) {
            this.bitField0_ |= 2;
            this.packageIndex_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackageIndex() {
            this.bitField0_ &= -3;
            this.packageIndex_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasClass_() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public String getClass_() {
            return this.class__;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public ByteString getClass_Bytes() {
            return ByteString.copyFromUtf8(this.class__);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClass_(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.class__ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearClass_() {
            this.bitField0_ &= -5;
            this.class__ = getDefaultInstance().getClass_();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClass_Bytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.class__ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasClassIndex() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getClassIndex() {
            return this.classIndex_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClassIndex(int value) {
            this.bitField0_ |= 8;
            this.classIndex_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearClassIndex() {
            this.bitField0_ &= -9;
            this.classIndex_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasTimeMs() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public long getTimeMs() {
            return this.timeMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTimeMs(long value) {
            this.bitField0_ |= 16;
            this.timeMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTimeMs() {
            this.bitField0_ &= -17;
            this.timeMs_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasFlags() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getFlags() {
            return this.flags_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFlags(int value) {
            this.bitField0_ |= 32;
            this.flags_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFlags() {
            this.bitField0_ &= -33;
            this.flags_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getType() {
            return this.type_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setType(int value) {
            this.bitField0_ |= 64;
            this.type_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearType() {
            this.bitField0_ &= -65;
            this.type_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasConfig() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public ConfigurationProto getConfig() {
            ConfigurationProto configurationProto = this.config_;
            return configurationProto == null ? ConfigurationProto.getDefaultInstance() : configurationProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setConfig(ConfigurationProto value) {
            if (value != null) {
                this.config_ = value;
                this.bitField0_ |= 128;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setConfig(ConfigurationProto.Builder builderForValue) {
            this.config_ = (ConfigurationProto) builderForValue.build();
            this.bitField0_ |= 128;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeConfig(ConfigurationProto value) {
            ConfigurationProto configurationProto = this.config_;
            if (configurationProto == null || configurationProto == ConfigurationProto.getDefaultInstance()) {
                this.config_ = value;
            } else {
                this.config_ = (ConfigurationProto) ((ConfigurationProto.Builder) ConfigurationProto.newBuilder(this.config_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 128;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearConfig() {
            this.config_ = null;
            this.bitField0_ &= -129;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasShortcutId() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public String getShortcutId() {
            return this.shortcutId_;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public ByteString getShortcutIdBytes() {
            return ByteString.copyFromUtf8(this.shortcutId_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShortcutId(String value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.shortcutId_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearShortcutId() {
            this.bitField0_ &= -257;
            this.shortcutId_ = getDefaultInstance().getShortcutId();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShortcutIdBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 256;
                this.shortcutId_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasStandbyBucket() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getStandbyBucket() {
            return this.standbyBucket_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStandbyBucket(int value) {
            this.bitField0_ |= 512;
            this.standbyBucket_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStandbyBucket() {
            this.bitField0_ &= -513;
            this.standbyBucket_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasNotificationChannel() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public String getNotificationChannel() {
            return this.notificationChannel_;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public ByteString getNotificationChannelBytes() {
            return ByteString.copyFromUtf8(this.notificationChannel_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNotificationChannel(String value) {
            if (value != null) {
                this.bitField0_ |= 1024;
                this.notificationChannel_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNotificationChannel() {
            this.bitField0_ &= -1025;
            this.notificationChannel_ = getDefaultInstance().getNotificationChannel();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNotificationChannelBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1024;
                this.notificationChannel_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasNotificationChannelIndex() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getNotificationChannelIndex() {
            return this.notificationChannelIndex_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNotificationChannelIndex(int value) {
            this.bitField0_ |= 2048;
            this.notificationChannelIndex_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNotificationChannelIndex() {
            this.bitField0_ &= -2049;
            this.notificationChannelIndex_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasInstanceId() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getInstanceId() {
            return this.instanceId_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInstanceId(int value) {
            this.bitField0_ |= 4096;
            this.instanceId_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInstanceId() {
            this.bitField0_ &= -4097;
            this.instanceId_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasTaskRootPackageIndex() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getTaskRootPackageIndex() {
            return this.taskRootPackageIndex_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTaskRootPackageIndex(int value) {
            this.bitField0_ |= 8192;
            this.taskRootPackageIndex_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTaskRootPackageIndex() {
            this.bitField0_ &= -8193;
            this.taskRootPackageIndex_ = 0;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public boolean hasTaskRootClassIndex() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
        public int getTaskRootClassIndex() {
            return this.taskRootClassIndex_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTaskRootClassIndex(int value) {
            this.bitField0_ |= 16384;
            this.taskRootClassIndex_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTaskRootClassIndex() {
            this.bitField0_ &= -16385;
            this.taskRootClassIndex_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.packageIndex_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getClass_());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.classIndex_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt64(5, this.timeMs_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.flags_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.type_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeMessage(8, getConfig());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeString(9, getShortcutId());
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt32(11, this.standbyBucket_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeString(12, getNotificationChannel());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(13, this.notificationChannelIndex_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(14, this.instanceId_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt32(15, this.taskRootPackageIndex_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeInt32(16, this.taskRootClassIndex_);
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.packageIndex_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getClass_());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.classIndex_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt64Size(5, this.timeMs_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.flags_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.type_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeMessageSize(8, getConfig());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeStringSize(9, getShortcutId());
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt32Size(11, this.standbyBucket_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeStringSize(12, getNotificationChannel());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(13, this.notificationChannelIndex_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(14, this.instanceId_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt32Size(15, this.taskRootPackageIndex_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeInt32Size(16, this.taskRootClassIndex_);
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

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasPackage() {
                return ((Event) this.instance).hasPackage();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public String getPackage() {
                return ((Event) this.instance).getPackage();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public ByteString getPackageBytes() {
                return ((Event) this.instance).getPackageBytes();
            }

            public Builder setPackage(String value) {
                copyOnWrite();
                ((Event) this.instance).setPackage(value);
                return this;
            }

            public Builder clearPackage() {
                copyOnWrite();
                ((Event) this.instance).clearPackage();
                return this;
            }

            public Builder setPackageBytes(ByteString value) {
                copyOnWrite();
                ((Event) this.instance).setPackageBytes(value);
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasPackageIndex() {
                return ((Event) this.instance).hasPackageIndex();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getPackageIndex() {
                return ((Event) this.instance).getPackageIndex();
            }

            public Builder setPackageIndex(int value) {
                copyOnWrite();
                ((Event) this.instance).setPackageIndex(value);
                return this;
            }

            public Builder clearPackageIndex() {
                copyOnWrite();
                ((Event) this.instance).clearPackageIndex();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasClass_() {
                return ((Event) this.instance).hasClass_();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public String getClass_() {
                return ((Event) this.instance).getClass_();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public ByteString getClass_Bytes() {
                return ((Event) this.instance).getClass_Bytes();
            }

            public Builder setClass_(String value) {
                copyOnWrite();
                ((Event) this.instance).setClass_(value);
                return this;
            }

            public Builder clearClass_() {
                copyOnWrite();
                ((Event) this.instance).clearClass_();
                return this;
            }

            public Builder setClass_Bytes(ByteString value) {
                copyOnWrite();
                ((Event) this.instance).setClass_Bytes(value);
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasClassIndex() {
                return ((Event) this.instance).hasClassIndex();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getClassIndex() {
                return ((Event) this.instance).getClassIndex();
            }

            public Builder setClassIndex(int value) {
                copyOnWrite();
                ((Event) this.instance).setClassIndex(value);
                return this;
            }

            public Builder clearClassIndex() {
                copyOnWrite();
                ((Event) this.instance).clearClassIndex();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasTimeMs() {
                return ((Event) this.instance).hasTimeMs();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public long getTimeMs() {
                return ((Event) this.instance).getTimeMs();
            }

            public Builder setTimeMs(long value) {
                copyOnWrite();
                ((Event) this.instance).setTimeMs(value);
                return this;
            }

            public Builder clearTimeMs() {
                copyOnWrite();
                ((Event) this.instance).clearTimeMs();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasFlags() {
                return ((Event) this.instance).hasFlags();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getFlags() {
                return ((Event) this.instance).getFlags();
            }

            public Builder setFlags(int value) {
                copyOnWrite();
                ((Event) this.instance).setFlags(value);
                return this;
            }

            public Builder clearFlags() {
                copyOnWrite();
                ((Event) this.instance).clearFlags();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasType() {
                return ((Event) this.instance).hasType();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getType() {
                return ((Event) this.instance).getType();
            }

            public Builder setType(int value) {
                copyOnWrite();
                ((Event) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((Event) this.instance).clearType();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasConfig() {
                return ((Event) this.instance).hasConfig();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public ConfigurationProto getConfig() {
                return ((Event) this.instance).getConfig();
            }

            public Builder setConfig(ConfigurationProto value) {
                copyOnWrite();
                ((Event) this.instance).setConfig((Event) value);
                return this;
            }

            public Builder setConfig(ConfigurationProto.Builder builderForValue) {
                copyOnWrite();
                ((Event) this.instance).setConfig((Event) builderForValue);
                return this;
            }

            public Builder mergeConfig(ConfigurationProto value) {
                copyOnWrite();
                ((Event) this.instance).mergeConfig(value);
                return this;
            }

            public Builder clearConfig() {
                copyOnWrite();
                ((Event) this.instance).clearConfig();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasShortcutId() {
                return ((Event) this.instance).hasShortcutId();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public String getShortcutId() {
                return ((Event) this.instance).getShortcutId();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public ByteString getShortcutIdBytes() {
                return ((Event) this.instance).getShortcutIdBytes();
            }

            public Builder setShortcutId(String value) {
                copyOnWrite();
                ((Event) this.instance).setShortcutId(value);
                return this;
            }

            public Builder clearShortcutId() {
                copyOnWrite();
                ((Event) this.instance).clearShortcutId();
                return this;
            }

            public Builder setShortcutIdBytes(ByteString value) {
                copyOnWrite();
                ((Event) this.instance).setShortcutIdBytes(value);
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasStandbyBucket() {
                return ((Event) this.instance).hasStandbyBucket();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getStandbyBucket() {
                return ((Event) this.instance).getStandbyBucket();
            }

            public Builder setStandbyBucket(int value) {
                copyOnWrite();
                ((Event) this.instance).setStandbyBucket(value);
                return this;
            }

            public Builder clearStandbyBucket() {
                copyOnWrite();
                ((Event) this.instance).clearStandbyBucket();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasNotificationChannel() {
                return ((Event) this.instance).hasNotificationChannel();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public String getNotificationChannel() {
                return ((Event) this.instance).getNotificationChannel();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public ByteString getNotificationChannelBytes() {
                return ((Event) this.instance).getNotificationChannelBytes();
            }

            public Builder setNotificationChannel(String value) {
                copyOnWrite();
                ((Event) this.instance).setNotificationChannel(value);
                return this;
            }

            public Builder clearNotificationChannel() {
                copyOnWrite();
                ((Event) this.instance).clearNotificationChannel();
                return this;
            }

            public Builder setNotificationChannelBytes(ByteString value) {
                copyOnWrite();
                ((Event) this.instance).setNotificationChannelBytes(value);
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasNotificationChannelIndex() {
                return ((Event) this.instance).hasNotificationChannelIndex();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getNotificationChannelIndex() {
                return ((Event) this.instance).getNotificationChannelIndex();
            }

            public Builder setNotificationChannelIndex(int value) {
                copyOnWrite();
                ((Event) this.instance).setNotificationChannelIndex(value);
                return this;
            }

            public Builder clearNotificationChannelIndex() {
                copyOnWrite();
                ((Event) this.instance).clearNotificationChannelIndex();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasInstanceId() {
                return ((Event) this.instance).hasInstanceId();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getInstanceId() {
                return ((Event) this.instance).getInstanceId();
            }

            public Builder setInstanceId(int value) {
                copyOnWrite();
                ((Event) this.instance).setInstanceId(value);
                return this;
            }

            public Builder clearInstanceId() {
                copyOnWrite();
                ((Event) this.instance).clearInstanceId();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasTaskRootPackageIndex() {
                return ((Event) this.instance).hasTaskRootPackageIndex();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getTaskRootPackageIndex() {
                return ((Event) this.instance).getTaskRootPackageIndex();
            }

            public Builder setTaskRootPackageIndex(int value) {
                copyOnWrite();
                ((Event) this.instance).setTaskRootPackageIndex(value);
                return this;
            }

            public Builder clearTaskRootPackageIndex() {
                copyOnWrite();
                ((Event) this.instance).clearTaskRootPackageIndex();
                return this;
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public boolean hasTaskRootClassIndex() {
                return ((Event) this.instance).hasTaskRootClassIndex();
            }

            @Override // com.android.server.usage.IntervalStatsProto.EventOrBuilder
            public int getTaskRootClassIndex() {
                return ((Event) this.instance).getTaskRootClassIndex();
            }

            public Builder setTaskRootClassIndex(int value) {
                copyOnWrite();
                ((Event) this.instance).setTaskRootClassIndex(value);
                return this;
            }

            public Builder clearTaskRootClassIndex() {
                copyOnWrite();
                ((Event) this.instance).clearTaskRootClassIndex();
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
                    this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                    this.packageIndex_ = visitor.visitInt(hasPackageIndex(), this.packageIndex_, other.hasPackageIndex(), other.packageIndex_);
                    this.class__ = visitor.visitString(hasClass_(), this.class__, other.hasClass_(), other.class__);
                    this.classIndex_ = visitor.visitInt(hasClassIndex(), this.classIndex_, other.hasClassIndex(), other.classIndex_);
                    this.timeMs_ = visitor.visitLong(hasTimeMs(), this.timeMs_, other.hasTimeMs(), other.timeMs_);
                    this.flags_ = visitor.visitInt(hasFlags(), this.flags_, other.hasFlags(), other.flags_);
                    this.type_ = visitor.visitInt(hasType(), this.type_, other.hasType(), other.type_);
                    this.config_ = (ConfigurationProto) visitor.visitMessage(this.config_, other.config_);
                    this.shortcutId_ = visitor.visitString(hasShortcutId(), this.shortcutId_, other.hasShortcutId(), other.shortcutId_);
                    this.standbyBucket_ = visitor.visitInt(hasStandbyBucket(), this.standbyBucket_, other.hasStandbyBucket(), other.standbyBucket_);
                    this.notificationChannel_ = visitor.visitString(hasNotificationChannel(), this.notificationChannel_, other.hasNotificationChannel(), other.notificationChannel_);
                    this.notificationChannelIndex_ = visitor.visitInt(hasNotificationChannelIndex(), this.notificationChannelIndex_, other.hasNotificationChannelIndex(), other.notificationChannelIndex_);
                    this.instanceId_ = visitor.visitInt(hasInstanceId(), this.instanceId_, other.hasInstanceId(), other.instanceId_);
                    this.taskRootPackageIndex_ = visitor.visitInt(hasTaskRootPackageIndex(), this.taskRootPackageIndex_, other.hasTaskRootPackageIndex(), other.taskRootPackageIndex_);
                    this.taskRootClassIndex_ = visitor.visitInt(hasTaskRootClassIndex(), this.taskRootClassIndex_, other.hasTaskRootClassIndex(), other.taskRootClassIndex_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 10:
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.package_ = s;
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.packageIndex_ = input.readInt32();
                                    break;
                                case 26:
                                    String s2 = input.readString();
                                    this.bitField0_ |= 4;
                                    this.class__ = s2;
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.classIndex_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.timeMs_ = input.readInt64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.flags_ = input.readInt32();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.type_ = input.readInt32();
                                    break;
                                case 66:
                                    ConfigurationProto.Builder subBuilder = null;
                                    if ((this.bitField0_ & 128) == 128) {
                                        subBuilder = (ConfigurationProto.Builder) this.config_.toBuilder();
                                    }
                                    this.config_ = (ConfigurationProto) input.readMessage(ConfigurationProto.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.config_);
                                        this.config_ = (ConfigurationProto) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 128;
                                    break;
                                case 74:
                                    String s3 = input.readString();
                                    this.bitField0_ |= 256;
                                    this.shortcutId_ = s3;
                                    break;
                                case 88:
                                    this.bitField0_ |= 512;
                                    this.standbyBucket_ = input.readInt32();
                                    break;
                                case 98:
                                    String s4 = input.readString();
                                    this.bitField0_ |= 1024;
                                    this.notificationChannel_ = s4;
                                    break;
                                case 104:
                                    this.bitField0_ |= 2048;
                                    this.notificationChannelIndex_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 4096;
                                    this.instanceId_ = input.readInt32();
                                    break;
                                case 120:
                                    this.bitField0_ |= 8192;
                                    this.taskRootPackageIndex_ = input.readInt32();
                                    break;
                                case 128:
                                    this.bitField0_ |= 16384;
                                    this.taskRootClassIndex_ = input.readInt32();
                                    break;
                                default:
                                    if (parseUnknownField(tag, input)) {
                                        break;
                                    } else {
                                        done = true;
                                        break;
                                    }
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

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public boolean hasEndTimeMs() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public long getEndTimeMs() {
        return this.endTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndTimeMs(long value) {
        this.bitField0_ |= 1;
        this.endTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndTimeMs() {
        this.bitField0_ &= -2;
        this.endTimeMs_ = 0;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public boolean hasStringpool() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public StringPool getStringpool() {
        StringPool stringPool = this.stringpool_;
        return stringPool == null ? StringPool.getDefaultInstance() : stringPool;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStringpool(StringPool value) {
        if (value != null) {
            this.stringpool_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStringpool(StringPool.Builder builderForValue) {
        this.stringpool_ = (StringPool) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStringpool(StringPool value) {
        StringPool stringPool = this.stringpool_;
        if (stringPool == null || stringPool == StringPool.getDefaultInstance()) {
            this.stringpool_ = value;
        } else {
            this.stringpool_ = (StringPool) ((StringPool.Builder) StringPool.newBuilder(this.stringpool_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStringpool() {
        this.stringpool_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public boolean hasMajorVersion() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public int getMajorVersion() {
        return this.majorVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMajorVersion(int value) {
        this.bitField0_ |= 4;
        this.majorVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMajorVersion() {
        this.bitField0_ &= -5;
        this.majorVersion_ = 0;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public boolean hasMinorVersion() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public int getMinorVersion() {
        return this.minorVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinorVersion(int value) {
        this.bitField0_ |= 8;
        this.minorVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinorVersion() {
        this.bitField0_ &= -9;
        this.minorVersion_ = 0;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public boolean hasInteractive() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public CountAndTime getInteractive() {
        CountAndTime countAndTime = this.interactive_;
        return countAndTime == null ? CountAndTime.getDefaultInstance() : countAndTime;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInteractive(CountAndTime value) {
        if (value != null) {
            this.interactive_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInteractive(CountAndTime.Builder builderForValue) {
        this.interactive_ = (CountAndTime) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeInteractive(CountAndTime value) {
        CountAndTime countAndTime = this.interactive_;
        if (countAndTime == null || countAndTime == CountAndTime.getDefaultInstance()) {
            this.interactive_ = value;
        } else {
            this.interactive_ = (CountAndTime) ((CountAndTime.Builder) CountAndTime.newBuilder(this.interactive_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInteractive() {
        this.interactive_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public boolean hasNonInteractive() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public CountAndTime getNonInteractive() {
        CountAndTime countAndTime = this.nonInteractive_;
        return countAndTime == null ? CountAndTime.getDefaultInstance() : countAndTime;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNonInteractive(CountAndTime value) {
        if (value != null) {
            this.nonInteractive_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNonInteractive(CountAndTime.Builder builderForValue) {
        this.nonInteractive_ = (CountAndTime) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNonInteractive(CountAndTime value) {
        CountAndTime countAndTime = this.nonInteractive_;
        if (countAndTime == null || countAndTime == CountAndTime.getDefaultInstance()) {
            this.nonInteractive_ = value;
        } else {
            this.nonInteractive_ = (CountAndTime) ((CountAndTime.Builder) CountAndTime.newBuilder(this.nonInteractive_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNonInteractive() {
        this.nonInteractive_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public boolean hasKeyguardShown() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public CountAndTime getKeyguardShown() {
        CountAndTime countAndTime = this.keyguardShown_;
        return countAndTime == null ? CountAndTime.getDefaultInstance() : countAndTime;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardShown(CountAndTime value) {
        if (value != null) {
            this.keyguardShown_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardShown(CountAndTime.Builder builderForValue) {
        this.keyguardShown_ = (CountAndTime) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeKeyguardShown(CountAndTime value) {
        CountAndTime countAndTime = this.keyguardShown_;
        if (countAndTime == null || countAndTime == CountAndTime.getDefaultInstance()) {
            this.keyguardShown_ = value;
        } else {
            this.keyguardShown_ = (CountAndTime) ((CountAndTime.Builder) CountAndTime.newBuilder(this.keyguardShown_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardShown() {
        this.keyguardShown_ = null;
        this.bitField0_ &= -65;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public boolean hasKeyguardHidden() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public CountAndTime getKeyguardHidden() {
        CountAndTime countAndTime = this.keyguardHidden_;
        return countAndTime == null ? CountAndTime.getDefaultInstance() : countAndTime;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardHidden(CountAndTime value) {
        if (value != null) {
            this.keyguardHidden_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKeyguardHidden(CountAndTime.Builder builderForValue) {
        this.keyguardHidden_ = (CountAndTime) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeKeyguardHidden(CountAndTime value) {
        CountAndTime countAndTime = this.keyguardHidden_;
        if (countAndTime == null || countAndTime == CountAndTime.getDefaultInstance()) {
            this.keyguardHidden_ = value;
        } else {
            this.keyguardHidden_ = (CountAndTime) ((CountAndTime.Builder) CountAndTime.newBuilder(this.keyguardHidden_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKeyguardHidden() {
        this.keyguardHidden_ = null;
        this.bitField0_ &= -129;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public List<UsageStats> getPackagesList() {
        return this.packages_;
    }

    public List<? extends UsageStatsOrBuilder> getPackagesOrBuilderList() {
        return this.packages_;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public int getPackagesCount() {
        return this.packages_.size();
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public UsageStats getPackages(int index) {
        return this.packages_.get(index);
    }

    public UsageStatsOrBuilder getPackagesOrBuilder(int index) {
        return this.packages_.get(index);
    }

    private void ensurePackagesIsMutable() {
        if (!this.packages_.isModifiable()) {
            this.packages_ = GeneratedMessageLite.mutableCopy(this.packages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackages(int index, UsageStats value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackages(int index, UsageStats.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.set(index, (UsageStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(UsageStats value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(int index, UsageStats value) {
        if (value != null) {
            ensurePackagesIsMutable();
            this.packages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(UsageStats.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.add((UsageStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackages(int index, UsageStats.Builder builderForValue) {
        ensurePackagesIsMutable();
        this.packages_.add(index, (UsageStats) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPackages(Iterable<? extends UsageStats> values) {
        ensurePackagesIsMutable();
        AbstractMessageLite.addAll(values, this.packages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackages() {
        this.packages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePackages(int index) {
        ensurePackagesIsMutable();
        this.packages_.remove(index);
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public List<Configuration> getConfigurationsList() {
        return this.configurations_;
    }

    public List<? extends ConfigurationOrBuilder> getConfigurationsOrBuilderList() {
        return this.configurations_;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public int getConfigurationsCount() {
        return this.configurations_.size();
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public Configuration getConfigurations(int index) {
        return this.configurations_.get(index);
    }

    public ConfigurationOrBuilder getConfigurationsOrBuilder(int index) {
        return this.configurations_.get(index);
    }

    private void ensureConfigurationsIsMutable() {
        if (!this.configurations_.isModifiable()) {
            this.configurations_ = GeneratedMessageLite.mutableCopy(this.configurations_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigurations(int index, Configuration value) {
        if (value != null) {
            ensureConfigurationsIsMutable();
            this.configurations_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConfigurations(int index, Configuration.Builder builderForValue) {
        ensureConfigurationsIsMutable();
        this.configurations_.set(index, (Configuration) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigurations(Configuration value) {
        if (value != null) {
            ensureConfigurationsIsMutable();
            this.configurations_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigurations(int index, Configuration value) {
        if (value != null) {
            ensureConfigurationsIsMutable();
            this.configurations_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigurations(Configuration.Builder builderForValue) {
        ensureConfigurationsIsMutable();
        this.configurations_.add((Configuration) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addConfigurations(int index, Configuration.Builder builderForValue) {
        ensureConfigurationsIsMutable();
        this.configurations_.add(index, (Configuration) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllConfigurations(Iterable<? extends Configuration> values) {
        ensureConfigurationsIsMutable();
        AbstractMessageLite.addAll(values, this.configurations_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConfigurations() {
        this.configurations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeConfigurations(int index) {
        ensureConfigurationsIsMutable();
        this.configurations_.remove(index);
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public List<Event> getEventLogList() {
        return this.eventLog_;
    }

    public List<? extends EventOrBuilder> getEventLogOrBuilderList() {
        return this.eventLog_;
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public int getEventLogCount() {
        return this.eventLog_.size();
    }

    @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
    public Event getEventLog(int index) {
        return this.eventLog_.get(index);
    }

    public EventOrBuilder getEventLogOrBuilder(int index) {
        return this.eventLog_.get(index);
    }

    private void ensureEventLogIsMutable() {
        if (!this.eventLog_.isModifiable()) {
            this.eventLog_ = GeneratedMessageLite.mutableCopy(this.eventLog_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEventLog(int index, Event value) {
        if (value != null) {
            ensureEventLogIsMutable();
            this.eventLog_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEventLog(int index, Event.Builder builderForValue) {
        ensureEventLogIsMutable();
        this.eventLog_.set(index, (Event) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEventLog(Event value) {
        if (value != null) {
            ensureEventLogIsMutable();
            this.eventLog_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEventLog(int index, Event value) {
        if (value != null) {
            ensureEventLogIsMutable();
            this.eventLog_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEventLog(Event.Builder builderForValue) {
        ensureEventLogIsMutable();
        this.eventLog_.add((Event) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addEventLog(int index, Event.Builder builderForValue) {
        ensureEventLogIsMutable();
        this.eventLog_.add(index, (Event) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllEventLog(Iterable<? extends Event> values) {
        ensureEventLogIsMutable();
        AbstractMessageLite.addAll(values, this.eventLog_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEventLog() {
        this.eventLog_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeEventLog(int index) {
        ensureEventLogIsMutable();
        this.eventLog_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.endTimeMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getStringpool());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(3, this.majorVersion_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt32(4, this.minorVersion_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(10, getInteractive());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(11, getNonInteractive());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(12, getKeyguardShown());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(13, getKeyguardHidden());
        }
        for (int i = 0; i < this.packages_.size(); i++) {
            output.writeMessage(20, this.packages_.get(i));
        }
        for (int i2 = 0; i2 < this.configurations_.size(); i2++) {
            output.writeMessage(21, this.configurations_.get(i2));
        }
        for (int i3 = 0; i3 < this.eventLog_.size(); i3++) {
            output.writeMessage(22, this.eventLog_.get(i3));
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.endTimeMs_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getStringpool());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(3, this.majorVersion_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt32Size(4, this.minorVersion_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(10, getInteractive());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(11, getNonInteractive());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(12, getKeyguardShown());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(13, getKeyguardHidden());
        }
        for (int i = 0; i < this.packages_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(20, this.packages_.get(i));
        }
        for (int i2 = 0; i2 < this.configurations_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(21, this.configurations_.get(i2));
        }
        for (int i3 = 0; i3 < this.eventLog_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(22, this.eventLog_.get(i3));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static IntervalStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IntervalStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntervalStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntervalStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntervalStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IntervalStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IntervalStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IntervalStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IntervalStatsProto parseFrom(InputStream input) throws IOException {
        return (IntervalStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntervalStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntervalStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntervalStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IntervalStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IntervalStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntervalStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IntervalStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (IntervalStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IntervalStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IntervalStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IntervalStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IntervalStatsProto, Builder> implements IntervalStatsProtoOrBuilder {
        private Builder() {
            super(IntervalStatsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public boolean hasEndTimeMs() {
            return ((IntervalStatsProto) this.instance).hasEndTimeMs();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public long getEndTimeMs() {
            return ((IntervalStatsProto) this.instance).getEndTimeMs();
        }

        public Builder setEndTimeMs(long value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setEndTimeMs(value);
            return this;
        }

        public Builder clearEndTimeMs() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearEndTimeMs();
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public boolean hasStringpool() {
            return ((IntervalStatsProto) this.instance).hasStringpool();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public StringPool getStringpool() {
            return ((IntervalStatsProto) this.instance).getStringpool();
        }

        public Builder setStringpool(StringPool value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setStringpool((IntervalStatsProto) value);
            return this;
        }

        public Builder setStringpool(StringPool.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setStringpool((IntervalStatsProto) builderForValue);
            return this;
        }

        public Builder mergeStringpool(StringPool value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).mergeStringpool(value);
            return this;
        }

        public Builder clearStringpool() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearStringpool();
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public boolean hasMajorVersion() {
            return ((IntervalStatsProto) this.instance).hasMajorVersion();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public int getMajorVersion() {
            return ((IntervalStatsProto) this.instance).getMajorVersion();
        }

        public Builder setMajorVersion(int value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setMajorVersion(value);
            return this;
        }

        public Builder clearMajorVersion() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearMajorVersion();
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public boolean hasMinorVersion() {
            return ((IntervalStatsProto) this.instance).hasMinorVersion();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public int getMinorVersion() {
            return ((IntervalStatsProto) this.instance).getMinorVersion();
        }

        public Builder setMinorVersion(int value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setMinorVersion(value);
            return this;
        }

        public Builder clearMinorVersion() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearMinorVersion();
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public boolean hasInteractive() {
            return ((IntervalStatsProto) this.instance).hasInteractive();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public CountAndTime getInteractive() {
            return ((IntervalStatsProto) this.instance).getInteractive();
        }

        public Builder setInteractive(CountAndTime value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setInteractive((IntervalStatsProto) value);
            return this;
        }

        public Builder setInteractive(CountAndTime.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setInteractive((IntervalStatsProto) builderForValue);
            return this;
        }

        public Builder mergeInteractive(CountAndTime value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).mergeInteractive(value);
            return this;
        }

        public Builder clearInteractive() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearInteractive();
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public boolean hasNonInteractive() {
            return ((IntervalStatsProto) this.instance).hasNonInteractive();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public CountAndTime getNonInteractive() {
            return ((IntervalStatsProto) this.instance).getNonInteractive();
        }

        public Builder setNonInteractive(CountAndTime value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setNonInteractive((IntervalStatsProto) value);
            return this;
        }

        public Builder setNonInteractive(CountAndTime.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setNonInteractive((IntervalStatsProto) builderForValue);
            return this;
        }

        public Builder mergeNonInteractive(CountAndTime value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).mergeNonInteractive(value);
            return this;
        }

        public Builder clearNonInteractive() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearNonInteractive();
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public boolean hasKeyguardShown() {
            return ((IntervalStatsProto) this.instance).hasKeyguardShown();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public CountAndTime getKeyguardShown() {
            return ((IntervalStatsProto) this.instance).getKeyguardShown();
        }

        public Builder setKeyguardShown(CountAndTime value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setKeyguardShown((IntervalStatsProto) value);
            return this;
        }

        public Builder setKeyguardShown(CountAndTime.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setKeyguardShown((IntervalStatsProto) builderForValue);
            return this;
        }

        public Builder mergeKeyguardShown(CountAndTime value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).mergeKeyguardShown(value);
            return this;
        }

        public Builder clearKeyguardShown() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearKeyguardShown();
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public boolean hasKeyguardHidden() {
            return ((IntervalStatsProto) this.instance).hasKeyguardHidden();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public CountAndTime getKeyguardHidden() {
            return ((IntervalStatsProto) this.instance).getKeyguardHidden();
        }

        public Builder setKeyguardHidden(CountAndTime value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setKeyguardHidden((IntervalStatsProto) value);
            return this;
        }

        public Builder setKeyguardHidden(CountAndTime.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setKeyguardHidden((IntervalStatsProto) builderForValue);
            return this;
        }

        public Builder mergeKeyguardHidden(CountAndTime value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).mergeKeyguardHidden(value);
            return this;
        }

        public Builder clearKeyguardHidden() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearKeyguardHidden();
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public List<UsageStats> getPackagesList() {
            return Collections.unmodifiableList(((IntervalStatsProto) this.instance).getPackagesList());
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public int getPackagesCount() {
            return ((IntervalStatsProto) this.instance).getPackagesCount();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public UsageStats getPackages(int index) {
            return ((IntervalStatsProto) this.instance).getPackages(index);
        }

        public Builder setPackages(int index, UsageStats value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setPackages((IntervalStatsProto) index, (int) value);
            return this;
        }

        public Builder setPackages(int index, UsageStats.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setPackages((IntervalStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPackages(UsageStats value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addPackages((IntervalStatsProto) value);
            return this;
        }

        public Builder addPackages(int index, UsageStats value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addPackages((IntervalStatsProto) index, (int) value);
            return this;
        }

        public Builder addPackages(UsageStats.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addPackages((IntervalStatsProto) builderForValue);
            return this;
        }

        public Builder addPackages(int index, UsageStats.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addPackages((IntervalStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPackages(Iterable<? extends UsageStats> values) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addAllPackages(values);
            return this;
        }

        public Builder clearPackages() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearPackages();
            return this;
        }

        public Builder removePackages(int index) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).removePackages(index);
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public List<Configuration> getConfigurationsList() {
            return Collections.unmodifiableList(((IntervalStatsProto) this.instance).getConfigurationsList());
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public int getConfigurationsCount() {
            return ((IntervalStatsProto) this.instance).getConfigurationsCount();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public Configuration getConfigurations(int index) {
            return ((IntervalStatsProto) this.instance).getConfigurations(index);
        }

        public Builder setConfigurations(int index, Configuration value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setConfigurations((IntervalStatsProto) index, (int) value);
            return this;
        }

        public Builder setConfigurations(int index, Configuration.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setConfigurations((IntervalStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addConfigurations(Configuration value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addConfigurations((IntervalStatsProto) value);
            return this;
        }

        public Builder addConfigurations(int index, Configuration value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addConfigurations((IntervalStatsProto) index, (int) value);
            return this;
        }

        public Builder addConfigurations(Configuration.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addConfigurations((IntervalStatsProto) builderForValue);
            return this;
        }

        public Builder addConfigurations(int index, Configuration.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addConfigurations((IntervalStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllConfigurations(Iterable<? extends Configuration> values) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addAllConfigurations(values);
            return this;
        }

        public Builder clearConfigurations() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearConfigurations();
            return this;
        }

        public Builder removeConfigurations(int index) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).removeConfigurations(index);
            return this;
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public List<Event> getEventLogList() {
            return Collections.unmodifiableList(((IntervalStatsProto) this.instance).getEventLogList());
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public int getEventLogCount() {
            return ((IntervalStatsProto) this.instance).getEventLogCount();
        }

        @Override // com.android.server.usage.IntervalStatsProtoOrBuilder
        public Event getEventLog(int index) {
            return ((IntervalStatsProto) this.instance).getEventLog(index);
        }

        public Builder setEventLog(int index, Event value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setEventLog((IntervalStatsProto) index, (int) value);
            return this;
        }

        public Builder setEventLog(int index, Event.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).setEventLog((IntervalStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addEventLog(Event value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addEventLog((IntervalStatsProto) value);
            return this;
        }

        public Builder addEventLog(int index, Event value) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addEventLog((IntervalStatsProto) index, (int) value);
            return this;
        }

        public Builder addEventLog(Event.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addEventLog((IntervalStatsProto) builderForValue);
            return this;
        }

        public Builder addEventLog(int index, Event.Builder builderForValue) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addEventLog((IntervalStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllEventLog(Iterable<? extends Event> values) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).addAllEventLog(values);
            return this;
        }

        public Builder clearEventLog() {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).clearEventLog();
            return this;
        }

        public Builder removeEventLog(int index) {
            copyOnWrite();
            ((IntervalStatsProto) this.instance).removeEventLog(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IntervalStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.packages_.makeImmutable();
                this.configurations_.makeImmutable();
                this.eventLog_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IntervalStatsProto other = (IntervalStatsProto) arg1;
                this.endTimeMs_ = visitor.visitLong(hasEndTimeMs(), this.endTimeMs_, other.hasEndTimeMs(), other.endTimeMs_);
                this.stringpool_ = (StringPool) visitor.visitMessage(this.stringpool_, other.stringpool_);
                this.majorVersion_ = visitor.visitInt(hasMajorVersion(), this.majorVersion_, other.hasMajorVersion(), other.majorVersion_);
                this.minorVersion_ = visitor.visitInt(hasMinorVersion(), this.minorVersion_, other.hasMinorVersion(), other.minorVersion_);
                this.interactive_ = (CountAndTime) visitor.visitMessage(this.interactive_, other.interactive_);
                this.nonInteractive_ = (CountAndTime) visitor.visitMessage(this.nonInteractive_, other.nonInteractive_);
                this.keyguardShown_ = (CountAndTime) visitor.visitMessage(this.keyguardShown_, other.keyguardShown_);
                this.keyguardHidden_ = (CountAndTime) visitor.visitMessage(this.keyguardHidden_, other.keyguardHidden_);
                this.packages_ = visitor.visitList(this.packages_, other.packages_);
                this.configurations_ = visitor.visitList(this.configurations_, other.configurations_);
                this.eventLog_ = visitor.visitList(this.eventLog_, other.eventLog_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.endTimeMs_ = input.readInt64();
                                break;
                            case 18:
                                StringPool.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (StringPool.Builder) this.stringpool_.toBuilder();
                                }
                                this.stringpool_ = (StringPool) input.readMessage(StringPool.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.stringpool_);
                                    this.stringpool_ = (StringPool) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.majorVersion_ = input.readInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.minorVersion_ = input.readInt32();
                                break;
                            case 82:
                                CountAndTime.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder2 = (CountAndTime.Builder) this.interactive_.toBuilder();
                                }
                                this.interactive_ = (CountAndTime) input.readMessage(CountAndTime.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.interactive_);
                                    this.interactive_ = (CountAndTime) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 90:
                                CountAndTime.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder3 = (CountAndTime.Builder) this.nonInteractive_.toBuilder();
                                }
                                this.nonInteractive_ = (CountAndTime) input.readMessage(CountAndTime.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.nonInteractive_);
                                    this.nonInteractive_ = (CountAndTime) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 98:
                                CountAndTime.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder4 = (CountAndTime.Builder) this.keyguardShown_.toBuilder();
                                }
                                this.keyguardShown_ = (CountAndTime) input.readMessage(CountAndTime.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.keyguardShown_);
                                    this.keyguardShown_ = (CountAndTime) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 106:
                                CountAndTime.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder5 = (CountAndTime.Builder) this.keyguardHidden_.toBuilder();
                                }
                                this.keyguardHidden_ = (CountAndTime) input.readMessage(CountAndTime.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.keyguardHidden_);
                                    this.keyguardHidden_ = (CountAndTime) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 162:
                                if (!this.packages_.isModifiable()) {
                                    this.packages_ = GeneratedMessageLite.mutableCopy(this.packages_);
                                }
                                this.packages_.add((UsageStats) input.readMessage(UsageStats.parser(), extensionRegistry));
                                break;
                            case 170:
                                if (!this.configurations_.isModifiable()) {
                                    this.configurations_ = GeneratedMessageLite.mutableCopy(this.configurations_);
                                }
                                this.configurations_.add((Configuration) input.readMessage(Configuration.parser(), extensionRegistry));
                                break;
                            case 178:
                                if (!this.eventLog_.isModifiable()) {
                                    this.eventLog_ = GeneratedMessageLite.mutableCopy(this.eventLog_);
                                }
                                this.eventLog_.add((Event) input.readMessage(Event.parser(), extensionRegistry));
                                break;
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (IntervalStatsProto.class) {
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

    public static IntervalStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IntervalStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
