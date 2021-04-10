package com.android.server.am;

import android.util.Duration;
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

public final class AppTimeTrackerProto extends GeneratedMessageLite<AppTimeTrackerProto, Builder> implements AppTimeTrackerProtoOrBuilder {
    private static final AppTimeTrackerProto DEFAULT_INSTANCE = new AppTimeTrackerProto();
    public static final int PACKAGE_TIMES_FIELD_NUMBER = 3;
    private static volatile Parser<AppTimeTrackerProto> PARSER = null;
    public static final int RECEIVER_FIELD_NUMBER = 1;
    public static final int STARTED_PACKAGE_FIELD_NUMBER = 5;
    public static final int STARTED_TIME_FIELD_NUMBER = 4;
    public static final int TOTAL_DURATION_MS_FIELD_NUMBER = 2;
    private int bitField0_;
    private Internal.ProtobufList<PackageTime> packageTimes_ = emptyProtobufList();
    private String receiver_ = "";
    private String startedPackage_ = "";
    private Duration startedTime_;
    private long totalDurationMs_ = 0;

    public interface PackageTimeOrBuilder extends MessageLiteOrBuilder {
        long getDurationMs();

        String getPackage();

        ByteString getPackageBytes();

        boolean hasDurationMs();

        boolean hasPackage();
    }

    private AppTimeTrackerProto() {
    }

    public static final class PackageTime extends GeneratedMessageLite<PackageTime, Builder> implements PackageTimeOrBuilder {
        private static final PackageTime DEFAULT_INSTANCE = new PackageTime();
        public static final int DURATION_MS_FIELD_NUMBER = 2;
        public static final int PACKAGE_FIELD_NUMBER = 1;
        private static volatile Parser<PackageTime> PARSER;
        private int bitField0_;
        private long durationMs_ = 0;
        private String package_ = "";

        private PackageTime() {
        }

        @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
        public boolean hasPackage() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
        public String getPackage() {
            return this.package_;
        }

        @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
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

        @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
        public boolean hasDurationMs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
        public long getDurationMs() {
            return this.durationMs_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDurationMs(long value) {
            this.bitField0_ |= 2;
            this.durationMs_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDurationMs() {
            this.bitField0_ &= -3;
            this.durationMs_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt64(2, this.durationMs_);
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
                size2 += CodedOutputStream.computeInt64Size(2, this.durationMs_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PackageTime parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PackageTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageTime parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageTime parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PackageTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageTime parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageTime parseFrom(InputStream input) throws IOException {
            return (PackageTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageTime parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PackageTime parseDelimitedFrom(InputStream input) throws IOException {
            return (PackageTime) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageTime parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageTime) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PackageTime parseFrom(CodedInputStream input) throws IOException {
            return (PackageTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageTime parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PackageTime prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PackageTime, Builder> implements PackageTimeOrBuilder {
            private Builder() {
                super(PackageTime.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
            public boolean hasPackage() {
                return ((PackageTime) this.instance).hasPackage();
            }

            @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
            public String getPackage() {
                return ((PackageTime) this.instance).getPackage();
            }

            @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
            public ByteString getPackageBytes() {
                return ((PackageTime) this.instance).getPackageBytes();
            }

            public Builder setPackage(String value) {
                copyOnWrite();
                ((PackageTime) this.instance).setPackage(value);
                return this;
            }

            public Builder clearPackage() {
                copyOnWrite();
                ((PackageTime) this.instance).clearPackage();
                return this;
            }

            public Builder setPackageBytes(ByteString value) {
                copyOnWrite();
                ((PackageTime) this.instance).setPackageBytes(value);
                return this;
            }

            @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
            public boolean hasDurationMs() {
                return ((PackageTime) this.instance).hasDurationMs();
            }

            @Override // com.android.server.am.AppTimeTrackerProto.PackageTimeOrBuilder
            public long getDurationMs() {
                return ((PackageTime) this.instance).getDurationMs();
            }

            public Builder setDurationMs(long value) {
                copyOnWrite();
                ((PackageTime) this.instance).setDurationMs(value);
                return this;
            }

            public Builder clearDurationMs() {
                copyOnWrite();
                ((PackageTime) this.instance).clearDurationMs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new PackageTime();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    PackageTime other = (PackageTime) arg1;
                    this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                    this.durationMs_ = visitor.visitLong(hasDurationMs(), this.durationMs_, other.hasDurationMs(), other.durationMs_);
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
                                this.package_ = s;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.durationMs_ = input.readInt64();
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
                        synchronized (PackageTime.class) {
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

        public static PackageTime getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PackageTime> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public boolean hasReceiver() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public String getReceiver() {
        return this.receiver_;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public ByteString getReceiverBytes() {
        return ByteString.copyFromUtf8(this.receiver_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReceiver(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.receiver_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReceiver() {
        this.bitField0_ &= -2;
        this.receiver_ = getDefaultInstance().getReceiver();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReceiverBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.receiver_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public boolean hasTotalDurationMs() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public long getTotalDurationMs() {
        return this.totalDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalDurationMs(long value) {
        this.bitField0_ |= 2;
        this.totalDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalDurationMs() {
        this.bitField0_ &= -3;
        this.totalDurationMs_ = 0;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public List<PackageTime> getPackageTimesList() {
        return this.packageTimes_;
    }

    public List<? extends PackageTimeOrBuilder> getPackageTimesOrBuilderList() {
        return this.packageTimes_;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public int getPackageTimesCount() {
        return this.packageTimes_.size();
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public PackageTime getPackageTimes(int index) {
        return this.packageTimes_.get(index);
    }

    public PackageTimeOrBuilder getPackageTimesOrBuilder(int index) {
        return this.packageTimes_.get(index);
    }

    private void ensurePackageTimesIsMutable() {
        if (!this.packageTimes_.isModifiable()) {
            this.packageTimes_ = GeneratedMessageLite.mutableCopy(this.packageTimes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageTimes(int index, PackageTime value) {
        if (value != null) {
            ensurePackageTimesIsMutable();
            this.packageTimes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageTimes(int index, PackageTime.Builder builderForValue) {
        ensurePackageTimesIsMutable();
        this.packageTimes_.set(index, (PackageTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageTimes(PackageTime value) {
        if (value != null) {
            ensurePackageTimesIsMutable();
            this.packageTimes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageTimes(int index, PackageTime value) {
        if (value != null) {
            ensurePackageTimesIsMutable();
            this.packageTimes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageTimes(PackageTime.Builder builderForValue) {
        ensurePackageTimesIsMutable();
        this.packageTimes_.add((PackageTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPackageTimes(int index, PackageTime.Builder builderForValue) {
        ensurePackageTimesIsMutable();
        this.packageTimes_.add(index, (PackageTime) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPackageTimes(Iterable<? extends PackageTime> values) {
        ensurePackageTimesIsMutable();
        AbstractMessageLite.addAll(values, this.packageTimes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageTimes() {
        this.packageTimes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePackageTimes(int index) {
        ensurePackageTimesIsMutable();
        this.packageTimes_.remove(index);
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public boolean hasStartedTime() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public Duration getStartedTime() {
        Duration duration = this.startedTime_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartedTime(Duration value) {
        if (value != null) {
            this.startedTime_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartedTime(Duration.Builder builderForValue) {
        this.startedTime_ = (Duration) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStartedTime(Duration value) {
        Duration duration = this.startedTime_;
        if (duration == null || duration == Duration.getDefaultInstance()) {
            this.startedTime_ = value;
        } else {
            this.startedTime_ = (Duration) ((Duration.Builder) Duration.newBuilder(this.startedTime_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartedTime() {
        this.startedTime_ = null;
        this.bitField0_ &= -5;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public boolean hasStartedPackage() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public String getStartedPackage() {
        return this.startedPackage_;
    }

    @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
    public ByteString getStartedPackageBytes() {
        return ByteString.copyFromUtf8(this.startedPackage_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartedPackage(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.startedPackage_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartedPackage() {
        this.bitField0_ &= -9;
        this.startedPackage_ = getDefaultInstance().getStartedPackage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartedPackageBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.startedPackage_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getReceiver());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.totalDurationMs_);
        }
        for (int i = 0; i < this.packageTimes_.size(); i++) {
            output.writeMessage(3, this.packageTimes_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(4, getStartedTime());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(5, getStartedPackage());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getReceiver());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.totalDurationMs_);
        }
        for (int i = 0; i < this.packageTimes_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.packageTimes_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(4, getStartedTime());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(5, getStartedPackage());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static AppTimeTrackerProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AppTimeTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppTimeTrackerProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppTimeTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppTimeTrackerProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AppTimeTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AppTimeTrackerProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AppTimeTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AppTimeTrackerProto parseFrom(InputStream input) throws IOException {
        return (AppTimeTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppTimeTrackerProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppTimeTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppTimeTrackerProto parseDelimitedFrom(InputStream input) throws IOException {
        return (AppTimeTrackerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AppTimeTrackerProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppTimeTrackerProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AppTimeTrackerProto parseFrom(CodedInputStream input) throws IOException {
        return (AppTimeTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AppTimeTrackerProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AppTimeTrackerProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AppTimeTrackerProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AppTimeTrackerProto, Builder> implements AppTimeTrackerProtoOrBuilder {
        private Builder() {
            super(AppTimeTrackerProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public boolean hasReceiver() {
            return ((AppTimeTrackerProto) this.instance).hasReceiver();
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public String getReceiver() {
            return ((AppTimeTrackerProto) this.instance).getReceiver();
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public ByteString getReceiverBytes() {
            return ((AppTimeTrackerProto) this.instance).getReceiverBytes();
        }

        public Builder setReceiver(String value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setReceiver(value);
            return this;
        }

        public Builder clearReceiver() {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).clearReceiver();
            return this;
        }

        public Builder setReceiverBytes(ByteString value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setReceiverBytes(value);
            return this;
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public boolean hasTotalDurationMs() {
            return ((AppTimeTrackerProto) this.instance).hasTotalDurationMs();
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public long getTotalDurationMs() {
            return ((AppTimeTrackerProto) this.instance).getTotalDurationMs();
        }

        public Builder setTotalDurationMs(long value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setTotalDurationMs(value);
            return this;
        }

        public Builder clearTotalDurationMs() {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).clearTotalDurationMs();
            return this;
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public List<PackageTime> getPackageTimesList() {
            return Collections.unmodifiableList(((AppTimeTrackerProto) this.instance).getPackageTimesList());
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public int getPackageTimesCount() {
            return ((AppTimeTrackerProto) this.instance).getPackageTimesCount();
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public PackageTime getPackageTimes(int index) {
            return ((AppTimeTrackerProto) this.instance).getPackageTimes(index);
        }

        public Builder setPackageTimes(int index, PackageTime value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setPackageTimes((AppTimeTrackerProto) index, (int) value);
            return this;
        }

        public Builder setPackageTimes(int index, PackageTime.Builder builderForValue) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setPackageTimes((AppTimeTrackerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPackageTimes(PackageTime value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).addPackageTimes((AppTimeTrackerProto) value);
            return this;
        }

        public Builder addPackageTimes(int index, PackageTime value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).addPackageTimes((AppTimeTrackerProto) index, (int) value);
            return this;
        }

        public Builder addPackageTimes(PackageTime.Builder builderForValue) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).addPackageTimes((AppTimeTrackerProto) builderForValue);
            return this;
        }

        public Builder addPackageTimes(int index, PackageTime.Builder builderForValue) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).addPackageTimes((AppTimeTrackerProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPackageTimes(Iterable<? extends PackageTime> values) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).addAllPackageTimes(values);
            return this;
        }

        public Builder clearPackageTimes() {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).clearPackageTimes();
            return this;
        }

        public Builder removePackageTimes(int index) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).removePackageTimes(index);
            return this;
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public boolean hasStartedTime() {
            return ((AppTimeTrackerProto) this.instance).hasStartedTime();
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public Duration getStartedTime() {
            return ((AppTimeTrackerProto) this.instance).getStartedTime();
        }

        public Builder setStartedTime(Duration value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setStartedTime((AppTimeTrackerProto) value);
            return this;
        }

        public Builder setStartedTime(Duration.Builder builderForValue) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setStartedTime((AppTimeTrackerProto) builderForValue);
            return this;
        }

        public Builder mergeStartedTime(Duration value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).mergeStartedTime(value);
            return this;
        }

        public Builder clearStartedTime() {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).clearStartedTime();
            return this;
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public boolean hasStartedPackage() {
            return ((AppTimeTrackerProto) this.instance).hasStartedPackage();
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public String getStartedPackage() {
            return ((AppTimeTrackerProto) this.instance).getStartedPackage();
        }

        @Override // com.android.server.am.AppTimeTrackerProtoOrBuilder
        public ByteString getStartedPackageBytes() {
            return ((AppTimeTrackerProto) this.instance).getStartedPackageBytes();
        }

        public Builder setStartedPackage(String value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setStartedPackage(value);
            return this;
        }

        public Builder clearStartedPackage() {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).clearStartedPackage();
            return this;
        }

        public Builder setStartedPackageBytes(ByteString value) {
            copyOnWrite();
            ((AppTimeTrackerProto) this.instance).setStartedPackageBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new AppTimeTrackerProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.packageTimes_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                AppTimeTrackerProto other = (AppTimeTrackerProto) arg1;
                this.receiver_ = visitor.visitString(hasReceiver(), this.receiver_, other.hasReceiver(), other.receiver_);
                this.totalDurationMs_ = visitor.visitLong(hasTotalDurationMs(), this.totalDurationMs_, other.hasTotalDurationMs(), other.totalDurationMs_);
                this.packageTimes_ = visitor.visitList(this.packageTimes_, other.packageTimes_);
                this.startedTime_ = (Duration) visitor.visitMessage(this.startedTime_, other.startedTime_);
                this.startedPackage_ = visitor.visitString(hasStartedPackage(), this.startedPackage_, other.hasStartedPackage(), other.startedPackage_);
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
                            this.receiver_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.totalDurationMs_ = input.readInt64();
                        } else if (tag == 26) {
                            if (!this.packageTimes_.isModifiable()) {
                                this.packageTimes_ = GeneratedMessageLite.mutableCopy(this.packageTimes_);
                            }
                            this.packageTimes_.add((PackageTime) input.readMessage(PackageTime.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            Duration.Builder subBuilder = null;
                            if ((this.bitField0_ & 4) == 4) {
                                subBuilder = (Duration.Builder) this.startedTime_.toBuilder();
                            }
                            this.startedTime_ = (Duration) input.readMessage(Duration.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.startedTime_);
                                this.startedTime_ = (Duration) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag == 42) {
                            String s2 = input.readString();
                            this.bitField0_ |= 8;
                            this.startedPackage_ = s2;
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
                    synchronized (AppTimeTrackerProto.class) {
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

    public static AppTimeTrackerProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AppTimeTrackerProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
