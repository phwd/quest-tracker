package android.service.diskstats;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class DiskStatsAppSizesProto extends GeneratedMessageLite<DiskStatsAppSizesProto, Builder> implements DiskStatsAppSizesProtoOrBuilder {
    public static final int APP_DATA_SIZE_KB_FIELD_NUMBER = 4;
    public static final int APP_SIZE_KB_FIELD_NUMBER = 2;
    public static final int CACHE_SIZE_KB_FIELD_NUMBER = 3;
    private static final DiskStatsAppSizesProto DEFAULT_INSTANCE = new DiskStatsAppSizesProto();
    public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
    private static volatile Parser<DiskStatsAppSizesProto> PARSER;
    private long appDataSizeKb_ = 0;
    private long appSizeKb_ = 0;
    private int bitField0_;
    private long cacheSizeKb_ = 0;
    private String packageName_ = "";

    private DiskStatsAppSizesProto() {
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public boolean hasPackageName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public String getPackageName() {
        return this.packageName_;
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public ByteString getPackageNameBytes() {
        return ByteString.copyFromUtf8(this.packageName_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageName(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.packageName_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackageName() {
        this.bitField0_ &= -2;
        this.packageName_ = getDefaultInstance().getPackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackageNameBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.packageName_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public boolean hasAppSizeKb() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public long getAppSizeKb() {
        return this.appSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppSizeKb(long value) {
        this.bitField0_ |= 2;
        this.appSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppSizeKb() {
        this.bitField0_ &= -3;
        this.appSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public boolean hasCacheSizeKb() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public long getCacheSizeKb() {
        return this.cacheSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCacheSizeKb(long value) {
        this.bitField0_ |= 4;
        this.cacheSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCacheSizeKb() {
        this.bitField0_ &= -5;
        this.cacheSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public boolean hasAppDataSizeKb() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
    public long getAppDataSizeKb() {
        return this.appDataSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppDataSizeKb(long value) {
        this.bitField0_ |= 8;
        this.appDataSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppDataSizeKb() {
        this.bitField0_ &= -9;
        this.appDataSizeKb_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getPackageName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.appSizeKb_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.cacheSizeKb_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.appDataSizeKb_);
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getPackageName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.appSizeKb_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.cacheSizeKb_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.appDataSizeKb_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DiskStatsAppSizesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DiskStatsAppSizesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DiskStatsAppSizesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DiskStatsAppSizesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DiskStatsAppSizesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DiskStatsAppSizesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DiskStatsAppSizesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DiskStatsAppSizesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DiskStatsAppSizesProto parseFrom(InputStream input) throws IOException {
        return (DiskStatsAppSizesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsAppSizesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsAppSizesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DiskStatsAppSizesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DiskStatsAppSizesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsAppSizesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsAppSizesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DiskStatsAppSizesProto parseFrom(CodedInputStream input) throws IOException {
        return (DiskStatsAppSizesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsAppSizesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsAppSizesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DiskStatsAppSizesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DiskStatsAppSizesProto, Builder> implements DiskStatsAppSizesProtoOrBuilder {
        private Builder() {
            super(DiskStatsAppSizesProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public boolean hasPackageName() {
            return ((DiskStatsAppSizesProto) this.instance).hasPackageName();
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public String getPackageName() {
            return ((DiskStatsAppSizesProto) this.instance).getPackageName();
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ((DiskStatsAppSizesProto) this.instance).getPackageNameBytes();
        }

        public Builder setPackageName(String value) {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).setPackageName(value);
            return this;
        }

        public Builder clearPackageName() {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).clearPackageName();
            return this;
        }

        public Builder setPackageNameBytes(ByteString value) {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).setPackageNameBytes(value);
            return this;
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public boolean hasAppSizeKb() {
            return ((DiskStatsAppSizesProto) this.instance).hasAppSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public long getAppSizeKb() {
            return ((DiskStatsAppSizesProto) this.instance).getAppSizeKb();
        }

        public Builder setAppSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).setAppSizeKb(value);
            return this;
        }

        public Builder clearAppSizeKb() {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).clearAppSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public boolean hasCacheSizeKb() {
            return ((DiskStatsAppSizesProto) this.instance).hasCacheSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public long getCacheSizeKb() {
            return ((DiskStatsAppSizesProto) this.instance).getCacheSizeKb();
        }

        public Builder setCacheSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).setCacheSizeKb(value);
            return this;
        }

        public Builder clearCacheSizeKb() {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).clearCacheSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public boolean hasAppDataSizeKb() {
            return ((DiskStatsAppSizesProto) this.instance).hasAppDataSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsAppSizesProtoOrBuilder
        public long getAppDataSizeKb() {
            return ((DiskStatsAppSizesProto) this.instance).getAppDataSizeKb();
        }

        public Builder setAppDataSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).setAppDataSizeKb(value);
            return this;
        }

        public Builder clearAppDataSizeKb() {
            copyOnWrite();
            ((DiskStatsAppSizesProto) this.instance).clearAppDataSizeKb();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DiskStatsAppSizesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DiskStatsAppSizesProto other = (DiskStatsAppSizesProto) arg1;
                this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, other.hasPackageName(), other.packageName_);
                this.appSizeKb_ = visitor.visitLong(hasAppSizeKb(), this.appSizeKb_, other.hasAppSizeKb(), other.appSizeKb_);
                this.cacheSizeKb_ = visitor.visitLong(hasCacheSizeKb(), this.cacheSizeKb_, other.hasCacheSizeKb(), other.cacheSizeKb_);
                this.appDataSizeKb_ = visitor.visitLong(hasAppDataSizeKb(), this.appDataSizeKb_, other.hasAppDataSizeKb(), other.appDataSizeKb_);
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
                            this.packageName_ = s;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.appSizeKb_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.cacheSizeKb_ = input.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.appDataSizeKb_ = input.readInt64();
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
                    synchronized (DiskStatsAppSizesProto.class) {
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

    public static DiskStatsAppSizesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DiskStatsAppSizesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
