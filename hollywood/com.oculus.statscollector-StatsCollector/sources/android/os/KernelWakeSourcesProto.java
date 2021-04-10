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

public final class KernelWakeSourcesProto extends GeneratedMessageLite<KernelWakeSourcesProto, Builder> implements KernelWakeSourcesProtoOrBuilder {
    private static final KernelWakeSourcesProto DEFAULT_INSTANCE = new KernelWakeSourcesProto();
    private static volatile Parser<KernelWakeSourcesProto> PARSER = null;
    public static final int WAKEUP_SOURCES_FIELD_NUMBER = 1;
    private Internal.ProtobufList<WakeupSource> wakeupSources_ = emptyProtobufList();

    public interface WakeupSourceOrBuilder extends MessageLiteOrBuilder {
        int getActiveCount();

        long getActiveSince();

        int getEventCount();

        int getExpireCount();

        long getLastChange();

        long getMaxTime();

        String getName();

        ByteString getNameBytes();

        long getPreventSuspendTime();

        long getTotalTime();

        int getWakeupCount();

        boolean hasActiveCount();

        boolean hasActiveSince();

        boolean hasEventCount();

        boolean hasExpireCount();

        boolean hasLastChange();

        boolean hasMaxTime();

        boolean hasName();

        boolean hasPreventSuspendTime();

        boolean hasTotalTime();

        boolean hasWakeupCount();
    }

    private KernelWakeSourcesProto() {
    }

    public static final class WakeupSource extends GeneratedMessageLite<WakeupSource, Builder> implements WakeupSourceOrBuilder {
        public static final int ACTIVE_COUNT_FIELD_NUMBER = 2;
        public static final int ACTIVE_SINCE_FIELD_NUMBER = 6;
        private static final WakeupSource DEFAULT_INSTANCE = new WakeupSource();
        public static final int EVENT_COUNT_FIELD_NUMBER = 3;
        public static final int EXPIRE_COUNT_FIELD_NUMBER = 5;
        public static final int LAST_CHANGE_FIELD_NUMBER = 9;
        public static final int MAX_TIME_FIELD_NUMBER = 8;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<WakeupSource> PARSER = null;
        public static final int PREVENT_SUSPEND_TIME_FIELD_NUMBER = 10;
        public static final int TOTAL_TIME_FIELD_NUMBER = 7;
        public static final int WAKEUP_COUNT_FIELD_NUMBER = 4;
        private int activeCount_ = 0;
        private long activeSince_ = 0;
        private int bitField0_;
        private int eventCount_ = 0;
        private int expireCount_ = 0;
        private long lastChange_ = 0;
        private long maxTime_ = 0;
        private String name_ = "";
        private long preventSuspendTime_ = 0;
        private long totalTime_ = 0;
        private int wakeupCount_ = 0;

        private WakeupSource() {
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
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

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasActiveCount() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public int getActiveCount() {
            return this.activeCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveCount(int value) {
            this.bitField0_ |= 2;
            this.activeCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveCount() {
            this.bitField0_ &= -3;
            this.activeCount_ = 0;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasEventCount() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public int getEventCount() {
            return this.eventCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEventCount(int value) {
            this.bitField0_ |= 4;
            this.eventCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEventCount() {
            this.bitField0_ &= -5;
            this.eventCount_ = 0;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasWakeupCount() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public int getWakeupCount() {
            return this.wakeupCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWakeupCount(int value) {
            this.bitField0_ |= 8;
            this.wakeupCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWakeupCount() {
            this.bitField0_ &= -9;
            this.wakeupCount_ = 0;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasExpireCount() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public int getExpireCount() {
            return this.expireCount_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setExpireCount(int value) {
            this.bitField0_ |= 16;
            this.expireCount_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearExpireCount() {
            this.bitField0_ &= -17;
            this.expireCount_ = 0;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasActiveSince() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public long getActiveSince() {
            return this.activeSince_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setActiveSince(long value) {
            this.bitField0_ |= 32;
            this.activeSince_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearActiveSince() {
            this.bitField0_ &= -33;
            this.activeSince_ = 0;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasTotalTime() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public long getTotalTime() {
            return this.totalTime_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTotalTime(long value) {
            this.bitField0_ |= 64;
            this.totalTime_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTotalTime() {
            this.bitField0_ &= -65;
            this.totalTime_ = 0;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasMaxTime() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public long getMaxTime() {
            return this.maxTime_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaxTime(long value) {
            this.bitField0_ |= 128;
            this.maxTime_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaxTime() {
            this.bitField0_ &= -129;
            this.maxTime_ = 0;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasLastChange() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public long getLastChange() {
            return this.lastChange_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLastChange(long value) {
            this.bitField0_ |= 256;
            this.lastChange_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLastChange() {
            this.bitField0_ &= -257;
            this.lastChange_ = 0;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public boolean hasPreventSuspendTime() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
        public long getPreventSuspendTime() {
            return this.preventSuspendTime_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPreventSuspendTime(long value) {
            this.bitField0_ |= 512;
            this.preventSuspendTime_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPreventSuspendTime() {
            this.bitField0_ &= -513;
            this.preventSuspendTime_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.activeCount_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.eventCount_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.wakeupCount_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.expireCount_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt64(6, this.activeSince_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt64(7, this.totalTime_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt64(8, this.maxTime_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt64(9, this.lastChange_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt64(10, this.preventSuspendTime_);
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
                size2 += CodedOutputStream.computeInt32Size(2, this.activeCount_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.eventCount_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.wakeupCount_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.expireCount_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt64Size(6, this.activeSince_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt64Size(7, this.totalTime_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt64Size(8, this.maxTime_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt64Size(9, this.lastChange_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt64Size(10, this.preventSuspendTime_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static WakeupSource parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WakeupSource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeupSource parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeupSource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeupSource parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WakeupSource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeupSource parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeupSource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeupSource parseFrom(InputStream input) throws IOException {
            return (WakeupSource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupSource parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupSource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeupSource parseDelimitedFrom(InputStream input) throws IOException {
            return (WakeupSource) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupSource parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupSource) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeupSource parseFrom(CodedInputStream input) throws IOException {
            return (WakeupSource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeupSource parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeupSource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WakeupSource prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WakeupSource, Builder> implements WakeupSourceOrBuilder {
            private Builder() {
                super(WakeupSource.DEFAULT_INSTANCE);
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasName() {
                return ((WakeupSource) this.instance).hasName();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public String getName() {
                return ((WakeupSource) this.instance).getName();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public ByteString getNameBytes() {
                return ((WakeupSource) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setNameBytes(value);
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasActiveCount() {
                return ((WakeupSource) this.instance).hasActiveCount();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public int getActiveCount() {
                return ((WakeupSource) this.instance).getActiveCount();
            }

            public Builder setActiveCount(int value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setActiveCount(value);
                return this;
            }

            public Builder clearActiveCount() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearActiveCount();
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasEventCount() {
                return ((WakeupSource) this.instance).hasEventCount();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public int getEventCount() {
                return ((WakeupSource) this.instance).getEventCount();
            }

            public Builder setEventCount(int value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setEventCount(value);
                return this;
            }

            public Builder clearEventCount() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearEventCount();
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasWakeupCount() {
                return ((WakeupSource) this.instance).hasWakeupCount();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public int getWakeupCount() {
                return ((WakeupSource) this.instance).getWakeupCount();
            }

            public Builder setWakeupCount(int value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setWakeupCount(value);
                return this;
            }

            public Builder clearWakeupCount() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearWakeupCount();
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasExpireCount() {
                return ((WakeupSource) this.instance).hasExpireCount();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public int getExpireCount() {
                return ((WakeupSource) this.instance).getExpireCount();
            }

            public Builder setExpireCount(int value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setExpireCount(value);
                return this;
            }

            public Builder clearExpireCount() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearExpireCount();
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasActiveSince() {
                return ((WakeupSource) this.instance).hasActiveSince();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public long getActiveSince() {
                return ((WakeupSource) this.instance).getActiveSince();
            }

            public Builder setActiveSince(long value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setActiveSince(value);
                return this;
            }

            public Builder clearActiveSince() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearActiveSince();
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasTotalTime() {
                return ((WakeupSource) this.instance).hasTotalTime();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public long getTotalTime() {
                return ((WakeupSource) this.instance).getTotalTime();
            }

            public Builder setTotalTime(long value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setTotalTime(value);
                return this;
            }

            public Builder clearTotalTime() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearTotalTime();
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasMaxTime() {
                return ((WakeupSource) this.instance).hasMaxTime();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public long getMaxTime() {
                return ((WakeupSource) this.instance).getMaxTime();
            }

            public Builder setMaxTime(long value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setMaxTime(value);
                return this;
            }

            public Builder clearMaxTime() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearMaxTime();
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasLastChange() {
                return ((WakeupSource) this.instance).hasLastChange();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public long getLastChange() {
                return ((WakeupSource) this.instance).getLastChange();
            }

            public Builder setLastChange(long value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setLastChange(value);
                return this;
            }

            public Builder clearLastChange() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearLastChange();
                return this;
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public boolean hasPreventSuspendTime() {
                return ((WakeupSource) this.instance).hasPreventSuspendTime();
            }

            @Override // android.os.KernelWakeSourcesProto.WakeupSourceOrBuilder
            public long getPreventSuspendTime() {
                return ((WakeupSource) this.instance).getPreventSuspendTime();
            }

            public Builder setPreventSuspendTime(long value) {
                copyOnWrite();
                ((WakeupSource) this.instance).setPreventSuspendTime(value);
                return this;
            }

            public Builder clearPreventSuspendTime() {
                copyOnWrite();
                ((WakeupSource) this.instance).clearPreventSuspendTime();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new WakeupSource();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    WakeupSource other = (WakeupSource) arg1;
                    this.name_ = visitor.visitString(hasName(), this.name_, other.hasName(), other.name_);
                    this.activeCount_ = visitor.visitInt(hasActiveCount(), this.activeCount_, other.hasActiveCount(), other.activeCount_);
                    this.eventCount_ = visitor.visitInt(hasEventCount(), this.eventCount_, other.hasEventCount(), other.eventCount_);
                    this.wakeupCount_ = visitor.visitInt(hasWakeupCount(), this.wakeupCount_, other.hasWakeupCount(), other.wakeupCount_);
                    this.expireCount_ = visitor.visitInt(hasExpireCount(), this.expireCount_, other.hasExpireCount(), other.expireCount_);
                    this.activeSince_ = visitor.visitLong(hasActiveSince(), this.activeSince_, other.hasActiveSince(), other.activeSince_);
                    this.totalTime_ = visitor.visitLong(hasTotalTime(), this.totalTime_, other.hasTotalTime(), other.totalTime_);
                    this.maxTime_ = visitor.visitLong(hasMaxTime(), this.maxTime_, other.hasMaxTime(), other.maxTime_);
                    this.lastChange_ = visitor.visitLong(hasLastChange(), this.lastChange_, other.hasLastChange(), other.lastChange_);
                    this.preventSuspendTime_ = visitor.visitLong(hasPreventSuspendTime(), this.preventSuspendTime_, other.hasPreventSuspendTime(), other.preventSuspendTime_);
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
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 10:
                                    String s = input.readString();
                                    this.bitField0_ |= 1;
                                    this.name_ = s;
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.activeCount_ = input.readInt32();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.eventCount_ = input.readInt32();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.wakeupCount_ = input.readInt32();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.expireCount_ = input.readInt32();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.activeSince_ = input.readInt64();
                                    break;
                                case 56:
                                    this.bitField0_ |= 64;
                                    this.totalTime_ = input.readInt64();
                                    break;
                                case 64:
                                    this.bitField0_ |= 128;
                                    this.maxTime_ = input.readInt64();
                                    break;
                                case 72:
                                    this.bitField0_ |= 256;
                                    this.lastChange_ = input.readInt64();
                                    break;
                                case 80:
                                    this.bitField0_ |= 512;
                                    this.preventSuspendTime_ = input.readInt64();
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
                        synchronized (WakeupSource.class) {
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

        public static WakeupSource getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WakeupSource> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.KernelWakeSourcesProtoOrBuilder
    public List<WakeupSource> getWakeupSourcesList() {
        return this.wakeupSources_;
    }

    public List<? extends WakeupSourceOrBuilder> getWakeupSourcesOrBuilderList() {
        return this.wakeupSources_;
    }

    @Override // android.os.KernelWakeSourcesProtoOrBuilder
    public int getWakeupSourcesCount() {
        return this.wakeupSources_.size();
    }

    @Override // android.os.KernelWakeSourcesProtoOrBuilder
    public WakeupSource getWakeupSources(int index) {
        return this.wakeupSources_.get(index);
    }

    public WakeupSourceOrBuilder getWakeupSourcesOrBuilder(int index) {
        return this.wakeupSources_.get(index);
    }

    private void ensureWakeupSourcesIsMutable() {
        if (!this.wakeupSources_.isModifiable()) {
            this.wakeupSources_ = GeneratedMessageLite.mutableCopy(this.wakeupSources_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeupSources(int index, WakeupSource value) {
        if (value != null) {
            ensureWakeupSourcesIsMutable();
            this.wakeupSources_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeupSources(int index, WakeupSource.Builder builderForValue) {
        ensureWakeupSourcesIsMutable();
        this.wakeupSources_.set(index, (WakeupSource) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupSources(WakeupSource value) {
        if (value != null) {
            ensureWakeupSourcesIsMutable();
            this.wakeupSources_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupSources(int index, WakeupSource value) {
        if (value != null) {
            ensureWakeupSourcesIsMutable();
            this.wakeupSources_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupSources(WakeupSource.Builder builderForValue) {
        ensureWakeupSourcesIsMutable();
        this.wakeupSources_.add((WakeupSource) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeupSources(int index, WakeupSource.Builder builderForValue) {
        ensureWakeupSourcesIsMutable();
        this.wakeupSources_.add(index, (WakeupSource) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWakeupSources(Iterable<? extends WakeupSource> values) {
        ensureWakeupSourcesIsMutable();
        AbstractMessageLite.addAll(values, this.wakeupSources_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWakeupSources() {
        this.wakeupSources_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWakeupSources(int index) {
        ensureWakeupSourcesIsMutable();
        this.wakeupSources_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.wakeupSources_.size(); i++) {
            output.writeMessage(1, this.wakeupSources_.get(i));
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
        for (int i = 0; i < this.wakeupSources_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.wakeupSources_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static KernelWakeSourcesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (KernelWakeSourcesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static KernelWakeSourcesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (KernelWakeSourcesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static KernelWakeSourcesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (KernelWakeSourcesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static KernelWakeSourcesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (KernelWakeSourcesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static KernelWakeSourcesProto parseFrom(InputStream input) throws IOException {
        return (KernelWakeSourcesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static KernelWakeSourcesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KernelWakeSourcesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static KernelWakeSourcesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (KernelWakeSourcesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static KernelWakeSourcesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KernelWakeSourcesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static KernelWakeSourcesProto parseFrom(CodedInputStream input) throws IOException {
        return (KernelWakeSourcesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static KernelWakeSourcesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (KernelWakeSourcesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KernelWakeSourcesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<KernelWakeSourcesProto, Builder> implements KernelWakeSourcesProtoOrBuilder {
        private Builder() {
            super(KernelWakeSourcesProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.KernelWakeSourcesProtoOrBuilder
        public List<WakeupSource> getWakeupSourcesList() {
            return Collections.unmodifiableList(((KernelWakeSourcesProto) this.instance).getWakeupSourcesList());
        }

        @Override // android.os.KernelWakeSourcesProtoOrBuilder
        public int getWakeupSourcesCount() {
            return ((KernelWakeSourcesProto) this.instance).getWakeupSourcesCount();
        }

        @Override // android.os.KernelWakeSourcesProtoOrBuilder
        public WakeupSource getWakeupSources(int index) {
            return ((KernelWakeSourcesProto) this.instance).getWakeupSources(index);
        }

        public Builder setWakeupSources(int index, WakeupSource value) {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).setWakeupSources((KernelWakeSourcesProto) index, (int) value);
            return this;
        }

        public Builder setWakeupSources(int index, WakeupSource.Builder builderForValue) {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).setWakeupSources((KernelWakeSourcesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWakeupSources(WakeupSource value) {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).addWakeupSources((KernelWakeSourcesProto) value);
            return this;
        }

        public Builder addWakeupSources(int index, WakeupSource value) {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).addWakeupSources((KernelWakeSourcesProto) index, (int) value);
            return this;
        }

        public Builder addWakeupSources(WakeupSource.Builder builderForValue) {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).addWakeupSources((KernelWakeSourcesProto) builderForValue);
            return this;
        }

        public Builder addWakeupSources(int index, WakeupSource.Builder builderForValue) {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).addWakeupSources((KernelWakeSourcesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWakeupSources(Iterable<? extends WakeupSource> values) {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).addAllWakeupSources(values);
            return this;
        }

        public Builder clearWakeupSources() {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).clearWakeupSources();
            return this;
        }

        public Builder removeWakeupSources(int index) {
            copyOnWrite();
            ((KernelWakeSourcesProto) this.instance).removeWakeupSources(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new KernelWakeSourcesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.wakeupSources_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.wakeupSources_ = ((GeneratedMessageLite.Visitor) arg0).visitList(this.wakeupSources_, ((KernelWakeSourcesProto) arg1).wakeupSources_);
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
                            if (!this.wakeupSources_.isModifiable()) {
                                this.wakeupSources_ = GeneratedMessageLite.mutableCopy(this.wakeupSources_);
                            }
                            this.wakeupSources_.add((WakeupSource) input.readMessage(WakeupSource.parser(), extensionRegistry));
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
                    synchronized (KernelWakeSourcesProto.class) {
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

    public static KernelWakeSourcesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KernelWakeSourcesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
