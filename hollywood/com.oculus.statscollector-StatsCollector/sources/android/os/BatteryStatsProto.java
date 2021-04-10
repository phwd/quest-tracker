package android.os;

import android.os.SystemProto;
import android.os.UidProto;
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

public final class BatteryStatsProto extends GeneratedMessageLite<BatteryStatsProto, Builder> implements BatteryStatsProtoOrBuilder {
    private static final BatteryStatsProto DEFAULT_INSTANCE = new BatteryStatsProto();
    public static final int END_PLATFORM_VERSION_FIELD_NUMBER = 4;
    public static final int PARCEL_VERSION_FIELD_NUMBER = 2;
    private static volatile Parser<BatteryStatsProto> PARSER = null;
    public static final int REPORT_VERSION_FIELD_NUMBER = 1;
    public static final int START_PLATFORM_VERSION_FIELD_NUMBER = 3;
    public static final int SYSTEM_FIELD_NUMBER = 6;
    public static final int UIDS_FIELD_NUMBER = 5;
    private int bitField0_;
    private String endPlatformVersion_ = "";
    private long parcelVersion_ = 0;
    private int reportVersion_ = 0;
    private String startPlatformVersion_ = "";
    private SystemProto system_;
    private Internal.ProtobufList<UidProto> uids_ = emptyProtobufList();

    private BatteryStatsProto() {
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public boolean hasReportVersion() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public int getReportVersion() {
        return this.reportVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReportVersion(int value) {
        this.bitField0_ |= 1;
        this.reportVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReportVersion() {
        this.bitField0_ &= -2;
        this.reportVersion_ = 0;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public boolean hasParcelVersion() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public long getParcelVersion() {
        return this.parcelVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParcelVersion(long value) {
        this.bitField0_ |= 2;
        this.parcelVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearParcelVersion() {
        this.bitField0_ &= -3;
        this.parcelVersion_ = 0;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public boolean hasStartPlatformVersion() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public String getStartPlatformVersion() {
        return this.startPlatformVersion_;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public ByteString getStartPlatformVersionBytes() {
        return ByteString.copyFromUtf8(this.startPlatformVersion_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartPlatformVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.startPlatformVersion_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStartPlatformVersion() {
        this.bitField0_ &= -5;
        this.startPlatformVersion_ = getDefaultInstance().getStartPlatformVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStartPlatformVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.startPlatformVersion_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public boolean hasEndPlatformVersion() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public String getEndPlatformVersion() {
        return this.endPlatformVersion_;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public ByteString getEndPlatformVersionBytes() {
        return ByteString.copyFromUtf8(this.endPlatformVersion_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndPlatformVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.endPlatformVersion_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndPlatformVersion() {
        this.bitField0_ &= -9;
        this.endPlatformVersion_ = getDefaultInstance().getEndPlatformVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndPlatformVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.endPlatformVersion_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public List<UidProto> getUidsList() {
        return this.uids_;
    }

    public List<? extends UidProtoOrBuilder> getUidsOrBuilderList() {
        return this.uids_;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public int getUidsCount() {
        return this.uids_.size();
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public UidProto getUids(int index) {
        return this.uids_.get(index);
    }

    public UidProtoOrBuilder getUidsOrBuilder(int index) {
        return this.uids_.get(index);
    }

    private void ensureUidsIsMutable() {
        if (!this.uids_.isModifiable()) {
            this.uids_ = GeneratedMessageLite.mutableCopy(this.uids_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUids(int index, UidProto value) {
        if (value != null) {
            ensureUidsIsMutable();
            this.uids_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUids(int index, UidProto.Builder builderForValue) {
        ensureUidsIsMutable();
        this.uids_.set(index, (UidProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUids(UidProto value) {
        if (value != null) {
            ensureUidsIsMutable();
            this.uids_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUids(int index, UidProto value) {
        if (value != null) {
            ensureUidsIsMutable();
            this.uids_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUids(UidProto.Builder builderForValue) {
        ensureUidsIsMutable();
        this.uids_.add((UidProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUids(int index, UidProto.Builder builderForValue) {
        ensureUidsIsMutable();
        this.uids_.add(index, (UidProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUids(Iterable<? extends UidProto> values) {
        ensureUidsIsMutable();
        AbstractMessageLite.addAll(values, this.uids_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUids() {
        this.uids_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUids(int index) {
        ensureUidsIsMutable();
        this.uids_.remove(index);
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public boolean hasSystem() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.os.BatteryStatsProtoOrBuilder
    public SystemProto getSystem() {
        SystemProto systemProto = this.system_;
        return systemProto == null ? SystemProto.getDefaultInstance() : systemProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystem(SystemProto value) {
        if (value != null) {
            this.system_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystem(SystemProto.Builder builderForValue) {
        this.system_ = (SystemProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSystem(SystemProto value) {
        SystemProto systemProto = this.system_;
        if (systemProto == null || systemProto == SystemProto.getDefaultInstance()) {
            this.system_ = value;
        } else {
            this.system_ = (SystemProto) ((SystemProto.Builder) SystemProto.newBuilder(this.system_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystem() {
        this.system_ = null;
        this.bitField0_ &= -17;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.reportVersion_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.parcelVersion_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getStartPlatformVersion());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeString(4, getEndPlatformVersion());
        }
        for (int i = 0; i < this.uids_.size(); i++) {
            output.writeMessage(5, this.uids_.get(i));
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(6, getSystem());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.reportVersion_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.parcelVersion_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getStartPlatformVersion());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeStringSize(4, getEndPlatformVersion());
        }
        for (int i = 0; i < this.uids_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.uids_.get(i));
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(6, getSystem());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BatteryStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BatteryStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatteryStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatteryStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatteryStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BatteryStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatteryStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatteryStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatteryStatsProto parseFrom(InputStream input) throws IOException {
        return (BatteryStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatteryStatsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BatteryStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatteryStatsProto parseFrom(CodedInputStream input) throws IOException {
        return (BatteryStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatteryStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatteryStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatteryStatsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BatteryStatsProto, Builder> implements BatteryStatsProtoOrBuilder {
        private Builder() {
            super(BatteryStatsProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public boolean hasReportVersion() {
            return ((BatteryStatsProto) this.instance).hasReportVersion();
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public int getReportVersion() {
            return ((BatteryStatsProto) this.instance).getReportVersion();
        }

        public Builder setReportVersion(int value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setReportVersion(value);
            return this;
        }

        public Builder clearReportVersion() {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).clearReportVersion();
            return this;
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public boolean hasParcelVersion() {
            return ((BatteryStatsProto) this.instance).hasParcelVersion();
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public long getParcelVersion() {
            return ((BatteryStatsProto) this.instance).getParcelVersion();
        }

        public Builder setParcelVersion(long value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setParcelVersion(value);
            return this;
        }

        public Builder clearParcelVersion() {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).clearParcelVersion();
            return this;
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public boolean hasStartPlatformVersion() {
            return ((BatteryStatsProto) this.instance).hasStartPlatformVersion();
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public String getStartPlatformVersion() {
            return ((BatteryStatsProto) this.instance).getStartPlatformVersion();
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public ByteString getStartPlatformVersionBytes() {
            return ((BatteryStatsProto) this.instance).getStartPlatformVersionBytes();
        }

        public Builder setStartPlatformVersion(String value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setStartPlatformVersion(value);
            return this;
        }

        public Builder clearStartPlatformVersion() {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).clearStartPlatformVersion();
            return this;
        }

        public Builder setStartPlatformVersionBytes(ByteString value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setStartPlatformVersionBytes(value);
            return this;
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public boolean hasEndPlatformVersion() {
            return ((BatteryStatsProto) this.instance).hasEndPlatformVersion();
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public String getEndPlatformVersion() {
            return ((BatteryStatsProto) this.instance).getEndPlatformVersion();
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public ByteString getEndPlatformVersionBytes() {
            return ((BatteryStatsProto) this.instance).getEndPlatformVersionBytes();
        }

        public Builder setEndPlatformVersion(String value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setEndPlatformVersion(value);
            return this;
        }

        public Builder clearEndPlatformVersion() {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).clearEndPlatformVersion();
            return this;
        }

        public Builder setEndPlatformVersionBytes(ByteString value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setEndPlatformVersionBytes(value);
            return this;
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public List<UidProto> getUidsList() {
            return Collections.unmodifiableList(((BatteryStatsProto) this.instance).getUidsList());
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public int getUidsCount() {
            return ((BatteryStatsProto) this.instance).getUidsCount();
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public UidProto getUids(int index) {
            return ((BatteryStatsProto) this.instance).getUids(index);
        }

        public Builder setUids(int index, UidProto value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setUids((BatteryStatsProto) index, (int) value);
            return this;
        }

        public Builder setUids(int index, UidProto.Builder builderForValue) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setUids((BatteryStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUids(UidProto value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).addUids((BatteryStatsProto) value);
            return this;
        }

        public Builder addUids(int index, UidProto value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).addUids((BatteryStatsProto) index, (int) value);
            return this;
        }

        public Builder addUids(UidProto.Builder builderForValue) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).addUids((BatteryStatsProto) builderForValue);
            return this;
        }

        public Builder addUids(int index, UidProto.Builder builderForValue) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).addUids((BatteryStatsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUids(Iterable<? extends UidProto> values) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).addAllUids(values);
            return this;
        }

        public Builder clearUids() {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).clearUids();
            return this;
        }

        public Builder removeUids(int index) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).removeUids(index);
            return this;
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public boolean hasSystem() {
            return ((BatteryStatsProto) this.instance).hasSystem();
        }

        @Override // android.os.BatteryStatsProtoOrBuilder
        public SystemProto getSystem() {
            return ((BatteryStatsProto) this.instance).getSystem();
        }

        public Builder setSystem(SystemProto value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setSystem((BatteryStatsProto) value);
            return this;
        }

        public Builder setSystem(SystemProto.Builder builderForValue) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).setSystem((BatteryStatsProto) builderForValue);
            return this;
        }

        public Builder mergeSystem(SystemProto value) {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).mergeSystem(value);
            return this;
        }

        public Builder clearSystem() {
            copyOnWrite();
            ((BatteryStatsProto) this.instance).clearSystem();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BatteryStatsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.uids_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BatteryStatsProto other = (BatteryStatsProto) arg1;
                this.reportVersion_ = visitor.visitInt(hasReportVersion(), this.reportVersion_, other.hasReportVersion(), other.reportVersion_);
                this.parcelVersion_ = visitor.visitLong(hasParcelVersion(), this.parcelVersion_, other.hasParcelVersion(), other.parcelVersion_);
                this.startPlatformVersion_ = visitor.visitString(hasStartPlatformVersion(), this.startPlatformVersion_, other.hasStartPlatformVersion(), other.startPlatformVersion_);
                this.endPlatformVersion_ = visitor.visitString(hasEndPlatformVersion(), this.endPlatformVersion_, other.hasEndPlatformVersion(), other.endPlatformVersion_);
                this.uids_ = visitor.visitList(this.uids_, other.uids_);
                this.system_ = (SystemProto) visitor.visitMessage(this.system_, other.system_);
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
                            this.reportVersion_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.parcelVersion_ = input.readInt64();
                        } else if (tag == 26) {
                            String s = input.readString();
                            this.bitField0_ |= 4;
                            this.startPlatformVersion_ = s;
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            this.bitField0_ = 8 | this.bitField0_;
                            this.endPlatformVersion_ = s2;
                        } else if (tag == 42) {
                            if (!this.uids_.isModifiable()) {
                                this.uids_ = GeneratedMessageLite.mutableCopy(this.uids_);
                            }
                            this.uids_.add((UidProto) input.readMessage(UidProto.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            SystemProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder = (SystemProto.Builder) this.system_.toBuilder();
                            }
                            this.system_ = (SystemProto) input.readMessage(SystemProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.system_);
                                this.system_ = (SystemProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ = 16 | this.bitField0_;
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
                    synchronized (BatteryStatsProto.class) {
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

    public static BatteryStatsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatteryStatsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
