package android.service.diskstats;

import android.service.diskstats.DiskStatsAppSizesProto;
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

public final class DiskStatsCachedValuesProto extends GeneratedMessageLite<DiskStatsCachedValuesProto, Builder> implements DiskStatsCachedValuesProtoOrBuilder {
    public static final int AGG_APPS_CACHE_SIZE_KB_FIELD_NUMBER = 2;
    public static final int AGG_APPS_DATA_SIZE_KB_FIELD_NUMBER = 10;
    public static final int AGG_APPS_SIZE_KB_FIELD_NUMBER = 1;
    public static final int APP_SIZES_FIELD_NUMBER = 9;
    public static final int AUDIO_SIZE_KB_FIELD_NUMBER = 5;
    private static final DiskStatsCachedValuesProto DEFAULT_INSTANCE = new DiskStatsCachedValuesProto();
    public static final int DOWNLOADS_SIZE_KB_FIELD_NUMBER = 6;
    public static final int OTHER_SIZE_KB_FIELD_NUMBER = 8;
    private static volatile Parser<DiskStatsCachedValuesProto> PARSER = null;
    public static final int PHOTOS_SIZE_KB_FIELD_NUMBER = 3;
    public static final int SYSTEM_SIZE_KB_FIELD_NUMBER = 7;
    public static final int VIDEOS_SIZE_KB_FIELD_NUMBER = 4;
    private long aggAppsCacheSizeKb_ = 0;
    private long aggAppsDataSizeKb_ = 0;
    private long aggAppsSizeKb_ = 0;
    private Internal.ProtobufList<DiskStatsAppSizesProto> appSizes_ = emptyProtobufList();
    private long audioSizeKb_ = 0;
    private int bitField0_;
    private long downloadsSizeKb_ = 0;
    private long otherSizeKb_ = 0;
    private long photosSizeKb_ = 0;
    private long systemSizeKb_ = 0;
    private long videosSizeKb_ = 0;

    private DiskStatsCachedValuesProto() {
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasAggAppsSizeKb() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getAggAppsSizeKb() {
        return this.aggAppsSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAggAppsSizeKb(long value) {
        this.bitField0_ |= 1;
        this.aggAppsSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAggAppsSizeKb() {
        this.bitField0_ &= -2;
        this.aggAppsSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasAggAppsCacheSizeKb() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getAggAppsCacheSizeKb() {
        return this.aggAppsCacheSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAggAppsCacheSizeKb(long value) {
        this.bitField0_ |= 2;
        this.aggAppsCacheSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAggAppsCacheSizeKb() {
        this.bitField0_ &= -3;
        this.aggAppsCacheSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasPhotosSizeKb() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getPhotosSizeKb() {
        return this.photosSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPhotosSizeKb(long value) {
        this.bitField0_ |= 4;
        this.photosSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPhotosSizeKb() {
        this.bitField0_ &= -5;
        this.photosSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasVideosSizeKb() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getVideosSizeKb() {
        return this.videosSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVideosSizeKb(long value) {
        this.bitField0_ |= 8;
        this.videosSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVideosSizeKb() {
        this.bitField0_ &= -9;
        this.videosSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasAudioSizeKb() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getAudioSizeKb() {
        return this.audioSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAudioSizeKb(long value) {
        this.bitField0_ |= 16;
        this.audioSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAudioSizeKb() {
        this.bitField0_ &= -17;
        this.audioSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasDownloadsSizeKb() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getDownloadsSizeKb() {
        return this.downloadsSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDownloadsSizeKb(long value) {
        this.bitField0_ |= 32;
        this.downloadsSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDownloadsSizeKb() {
        this.bitField0_ &= -33;
        this.downloadsSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasSystemSizeKb() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getSystemSizeKb() {
        return this.systemSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemSizeKb(long value) {
        this.bitField0_ |= 64;
        this.systemSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemSizeKb() {
        this.bitField0_ &= -65;
        this.systemSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasOtherSizeKb() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getOtherSizeKb() {
        return this.otherSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOtherSizeKb(long value) {
        this.bitField0_ |= 128;
        this.otherSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOtherSizeKb() {
        this.bitField0_ &= -129;
        this.otherSizeKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public List<DiskStatsAppSizesProto> getAppSizesList() {
        return this.appSizes_;
    }

    public List<? extends DiskStatsAppSizesProtoOrBuilder> getAppSizesOrBuilderList() {
        return this.appSizes_;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public int getAppSizesCount() {
        return this.appSizes_.size();
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public DiskStatsAppSizesProto getAppSizes(int index) {
        return this.appSizes_.get(index);
    }

    public DiskStatsAppSizesProtoOrBuilder getAppSizesOrBuilder(int index) {
        return this.appSizes_.get(index);
    }

    private void ensureAppSizesIsMutable() {
        if (!this.appSizes_.isModifiable()) {
            this.appSizes_ = GeneratedMessageLite.mutableCopy(this.appSizes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppSizes(int index, DiskStatsAppSizesProto value) {
        if (value != null) {
            ensureAppSizesIsMutable();
            this.appSizes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppSizes(int index, DiskStatsAppSizesProto.Builder builderForValue) {
        ensureAppSizesIsMutable();
        this.appSizes_.set(index, (DiskStatsAppSizesProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppSizes(DiskStatsAppSizesProto value) {
        if (value != null) {
            ensureAppSizesIsMutable();
            this.appSizes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppSizes(int index, DiskStatsAppSizesProto value) {
        if (value != null) {
            ensureAppSizesIsMutable();
            this.appSizes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppSizes(DiskStatsAppSizesProto.Builder builderForValue) {
        ensureAppSizesIsMutable();
        this.appSizes_.add((DiskStatsAppSizesProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppSizes(int index, DiskStatsAppSizesProto.Builder builderForValue) {
        ensureAppSizesIsMutable();
        this.appSizes_.add(index, (DiskStatsAppSizesProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllAppSizes(Iterable<? extends DiskStatsAppSizesProto> values) {
        ensureAppSizesIsMutable();
        AbstractMessageLite.addAll(values, this.appSizes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppSizes() {
        this.appSizes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAppSizes(int index) {
        ensureAppSizesIsMutable();
        this.appSizes_.remove(index);
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public boolean hasAggAppsDataSizeKb() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
    public long getAggAppsDataSizeKb() {
        return this.aggAppsDataSizeKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAggAppsDataSizeKb(long value) {
        this.bitField0_ |= 256;
        this.aggAppsDataSizeKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAggAppsDataSizeKb() {
        this.bitField0_ &= -257;
        this.aggAppsDataSizeKb_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt64(1, this.aggAppsSizeKb_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.aggAppsCacheSizeKb_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.photosSizeKb_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeInt64(4, this.videosSizeKb_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeInt64(5, this.audioSizeKb_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(6, this.downloadsSizeKb_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt64(7, this.systemSizeKb_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt64(8, this.otherSizeKb_);
        }
        for (int i = 0; i < this.appSizes_.size(); i++) {
            output.writeMessage(9, this.appSizes_.get(i));
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeInt64(10, this.aggAppsDataSizeKb_);
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
            size2 = 0 + CodedOutputStream.computeInt64Size(1, this.aggAppsSizeKb_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.aggAppsCacheSizeKb_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.photosSizeKb_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeInt64Size(4, this.videosSizeKb_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeInt64Size(5, this.audioSizeKb_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(6, this.downloadsSizeKb_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt64Size(7, this.systemSizeKb_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt64Size(8, this.otherSizeKb_);
        }
        for (int i = 0; i < this.appSizes_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(9, this.appSizes_.get(i));
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeInt64Size(10, this.aggAppsDataSizeKb_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DiskStatsCachedValuesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DiskStatsCachedValuesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DiskStatsCachedValuesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DiskStatsCachedValuesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DiskStatsCachedValuesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DiskStatsCachedValuesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DiskStatsCachedValuesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DiskStatsCachedValuesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DiskStatsCachedValuesProto parseFrom(InputStream input) throws IOException {
        return (DiskStatsCachedValuesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsCachedValuesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsCachedValuesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DiskStatsCachedValuesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DiskStatsCachedValuesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsCachedValuesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsCachedValuesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DiskStatsCachedValuesProto parseFrom(CodedInputStream input) throws IOException {
        return (DiskStatsCachedValuesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsCachedValuesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsCachedValuesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DiskStatsCachedValuesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DiskStatsCachedValuesProto, Builder> implements DiskStatsCachedValuesProtoOrBuilder {
        private Builder() {
            super(DiskStatsCachedValuesProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasAggAppsSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasAggAppsSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getAggAppsSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getAggAppsSizeKb();
        }

        public Builder setAggAppsSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setAggAppsSizeKb(value);
            return this;
        }

        public Builder clearAggAppsSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearAggAppsSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasAggAppsCacheSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasAggAppsCacheSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getAggAppsCacheSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getAggAppsCacheSizeKb();
        }

        public Builder setAggAppsCacheSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setAggAppsCacheSizeKb(value);
            return this;
        }

        public Builder clearAggAppsCacheSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearAggAppsCacheSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasPhotosSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasPhotosSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getPhotosSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getPhotosSizeKb();
        }

        public Builder setPhotosSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setPhotosSizeKb(value);
            return this;
        }

        public Builder clearPhotosSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearPhotosSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasVideosSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasVideosSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getVideosSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getVideosSizeKb();
        }

        public Builder setVideosSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setVideosSizeKb(value);
            return this;
        }

        public Builder clearVideosSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearVideosSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasAudioSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasAudioSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getAudioSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getAudioSizeKb();
        }

        public Builder setAudioSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setAudioSizeKb(value);
            return this;
        }

        public Builder clearAudioSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearAudioSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasDownloadsSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasDownloadsSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getDownloadsSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getDownloadsSizeKb();
        }

        public Builder setDownloadsSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setDownloadsSizeKb(value);
            return this;
        }

        public Builder clearDownloadsSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearDownloadsSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasSystemSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasSystemSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getSystemSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getSystemSizeKb();
        }

        public Builder setSystemSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setSystemSizeKb(value);
            return this;
        }

        public Builder clearSystemSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearSystemSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasOtherSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasOtherSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getOtherSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getOtherSizeKb();
        }

        public Builder setOtherSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setOtherSizeKb(value);
            return this;
        }

        public Builder clearOtherSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearOtherSizeKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public List<DiskStatsAppSizesProto> getAppSizesList() {
            return Collections.unmodifiableList(((DiskStatsCachedValuesProto) this.instance).getAppSizesList());
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public int getAppSizesCount() {
            return ((DiskStatsCachedValuesProto) this.instance).getAppSizesCount();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public DiskStatsAppSizesProto getAppSizes(int index) {
            return ((DiskStatsCachedValuesProto) this.instance).getAppSizes(index);
        }

        public Builder setAppSizes(int index, DiskStatsAppSizesProto value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setAppSizes((DiskStatsCachedValuesProto) index, (int) value);
            return this;
        }

        public Builder setAppSizes(int index, DiskStatsAppSizesProto.Builder builderForValue) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setAppSizes((DiskStatsCachedValuesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAppSizes(DiskStatsAppSizesProto value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).addAppSizes((DiskStatsCachedValuesProto) value);
            return this;
        }

        public Builder addAppSizes(int index, DiskStatsAppSizesProto value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).addAppSizes((DiskStatsCachedValuesProto) index, (int) value);
            return this;
        }

        public Builder addAppSizes(DiskStatsAppSizesProto.Builder builderForValue) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).addAppSizes((DiskStatsCachedValuesProto) builderForValue);
            return this;
        }

        public Builder addAppSizes(int index, DiskStatsAppSizesProto.Builder builderForValue) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).addAppSizes((DiskStatsCachedValuesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllAppSizes(Iterable<? extends DiskStatsAppSizesProto> values) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).addAllAppSizes(values);
            return this;
        }

        public Builder clearAppSizes() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearAppSizes();
            return this;
        }

        public Builder removeAppSizes(int index) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).removeAppSizes(index);
            return this;
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public boolean hasAggAppsDataSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).hasAggAppsDataSizeKb();
        }

        @Override // android.service.diskstats.DiskStatsCachedValuesProtoOrBuilder
        public long getAggAppsDataSizeKb() {
            return ((DiskStatsCachedValuesProto) this.instance).getAggAppsDataSizeKb();
        }

        public Builder setAggAppsDataSizeKb(long value) {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).setAggAppsDataSizeKb(value);
            return this;
        }

        public Builder clearAggAppsDataSizeKb() {
            copyOnWrite();
            ((DiskStatsCachedValuesProto) this.instance).clearAggAppsDataSizeKb();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DiskStatsCachedValuesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.appSizes_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DiskStatsCachedValuesProto other = (DiskStatsCachedValuesProto) arg1;
                this.aggAppsSizeKb_ = visitor.visitLong(hasAggAppsSizeKb(), this.aggAppsSizeKb_, other.hasAggAppsSizeKb(), other.aggAppsSizeKb_);
                this.aggAppsCacheSizeKb_ = visitor.visitLong(hasAggAppsCacheSizeKb(), this.aggAppsCacheSizeKb_, other.hasAggAppsCacheSizeKb(), other.aggAppsCacheSizeKb_);
                this.photosSizeKb_ = visitor.visitLong(hasPhotosSizeKb(), this.photosSizeKb_, other.hasPhotosSizeKb(), other.photosSizeKb_);
                this.videosSizeKb_ = visitor.visitLong(hasVideosSizeKb(), this.videosSizeKb_, other.hasVideosSizeKb(), other.videosSizeKb_);
                this.audioSizeKb_ = visitor.visitLong(hasAudioSizeKb(), this.audioSizeKb_, other.hasAudioSizeKb(), other.audioSizeKb_);
                this.downloadsSizeKb_ = visitor.visitLong(hasDownloadsSizeKb(), this.downloadsSizeKb_, other.hasDownloadsSizeKb(), other.downloadsSizeKb_);
                this.systemSizeKb_ = visitor.visitLong(hasSystemSizeKb(), this.systemSizeKb_, other.hasSystemSizeKb(), other.systemSizeKb_);
                this.otherSizeKb_ = visitor.visitLong(hasOtherSizeKb(), this.otherSizeKb_, other.hasOtherSizeKb(), other.otherSizeKb_);
                this.appSizes_ = visitor.visitList(this.appSizes_, other.appSizes_);
                this.aggAppsDataSizeKb_ = visitor.visitLong(hasAggAppsDataSizeKb(), this.aggAppsDataSizeKb_, other.hasAggAppsDataSizeKb(), other.aggAppsDataSizeKb_);
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
                                this.aggAppsSizeKb_ = input.readInt64();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.aggAppsCacheSizeKb_ = input.readInt64();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.photosSizeKb_ = input.readInt64();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.videosSizeKb_ = input.readInt64();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.audioSizeKb_ = input.readInt64();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.downloadsSizeKb_ = input.readInt64();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.systemSizeKb_ = input.readInt64();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.otherSizeKb_ = input.readInt64();
                                break;
                            case 74:
                                if (!this.appSizes_.isModifiable()) {
                                    this.appSizes_ = GeneratedMessageLite.mutableCopy(this.appSizes_);
                                }
                                this.appSizes_.add((DiskStatsAppSizesProto) input.readMessage(DiskStatsAppSizesProto.parser(), extensionRegistry));
                                break;
                            case 80:
                                this.bitField0_ |= 256;
                                this.aggAppsDataSizeKb_ = input.readInt64();
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
                    synchronized (DiskStatsCachedValuesProto.class) {
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

    public static DiskStatsCachedValuesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DiskStatsCachedValuesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
