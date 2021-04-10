package android.content;

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

public final class DeviceConfigurationProto extends GeneratedMessageLite<DeviceConfigurationProto, Builder> implements DeviceConfigurationProtoOrBuilder {
    public static final int CPU_ARCHITECTURES_FIELD_NUMBER = 12;
    private static final DeviceConfigurationProto DEFAULT_INSTANCE = new DeviceConfigurationProto();
    public static final int FEATURES_FIELD_NUMBER = 11;
    public static final int HAS_SECURE_SCREEN_LOCK_FIELD_NUMBER = 7;
    public static final int LOW_RAM_FIELD_NUMBER = 5;
    public static final int MAX_CORES_FIELD_NUMBER = 6;
    public static final int OPENGL_EXTENSIONS_FIELD_NUMBER = 9;
    public static final int OPENGL_VERSION_FIELD_NUMBER = 8;
    private static volatile Parser<DeviceConfigurationProto> PARSER = null;
    public static final int SHARED_LIBRARIES_FIELD_NUMBER = 10;
    public static final int STABLE_DENSITY_DPI_FIELD_NUMBER = 3;
    public static final int STABLE_SCREEN_HEIGHT_PX_FIELD_NUMBER = 2;
    public static final int STABLE_SCREEN_WIDTH_PX_FIELD_NUMBER = 1;
    public static final int TOTAL_RAM_FIELD_NUMBER = 4;
    private int bitField0_;
    private Internal.ProtobufList<String> cpuArchitectures_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> features_ = GeneratedMessageLite.emptyProtobufList();
    private boolean hasSecureScreenLock_ = false;
    private boolean lowRam_ = false;
    private int maxCores_ = 0;
    private Internal.ProtobufList<String> openglExtensions_ = GeneratedMessageLite.emptyProtobufList();
    private int openglVersion_ = 0;
    private Internal.ProtobufList<String> sharedLibraries_ = GeneratedMessageLite.emptyProtobufList();
    private int stableDensityDpi_ = 0;
    private int stableScreenHeightPx_ = 0;
    private int stableScreenWidthPx_ = 0;
    private long totalRam_ = 0;

    private DeviceConfigurationProto() {
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean hasStableScreenWidthPx() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getStableScreenWidthPx() {
        return this.stableScreenWidthPx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableScreenWidthPx(int value) {
        this.bitField0_ |= 1;
        this.stableScreenWidthPx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStableScreenWidthPx() {
        this.bitField0_ &= -2;
        this.stableScreenWidthPx_ = 0;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean hasStableScreenHeightPx() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getStableScreenHeightPx() {
        return this.stableScreenHeightPx_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableScreenHeightPx(int value) {
        this.bitField0_ |= 2;
        this.stableScreenHeightPx_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStableScreenHeightPx() {
        this.bitField0_ &= -3;
        this.stableScreenHeightPx_ = 0;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean hasStableDensityDpi() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getStableDensityDpi() {
        return this.stableDensityDpi_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStableDensityDpi(int value) {
        this.bitField0_ |= 4;
        this.stableDensityDpi_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStableDensityDpi() {
        this.bitField0_ &= -5;
        this.stableDensityDpi_ = 0;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean hasTotalRam() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public long getTotalRam() {
        return this.totalRam_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalRam(long value) {
        this.bitField0_ |= 8;
        this.totalRam_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalRam() {
        this.bitField0_ &= -9;
        this.totalRam_ = 0;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean hasLowRam() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean getLowRam() {
        return this.lowRam_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLowRam(boolean value) {
        this.bitField0_ |= 16;
        this.lowRam_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLowRam() {
        this.bitField0_ &= -17;
        this.lowRam_ = false;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean hasMaxCores() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getMaxCores() {
        return this.maxCores_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaxCores(int value) {
        this.bitField0_ |= 32;
        this.maxCores_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaxCores() {
        this.bitField0_ &= -33;
        this.maxCores_ = 0;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean hasHasSecureScreenLock() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean getHasSecureScreenLock() {
        return this.hasSecureScreenLock_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasSecureScreenLock(boolean value) {
        this.bitField0_ |= 64;
        this.hasSecureScreenLock_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasSecureScreenLock() {
        this.bitField0_ &= -65;
        this.hasSecureScreenLock_ = false;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public boolean hasOpenglVersion() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getOpenglVersion() {
        return this.openglVersion_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOpenglVersion(int value) {
        this.bitField0_ |= 128;
        this.openglVersion_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOpenglVersion() {
        this.bitField0_ &= -129;
        this.openglVersion_ = 0;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public List<String> getOpenglExtensionsList() {
        return this.openglExtensions_;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getOpenglExtensionsCount() {
        return this.openglExtensions_.size();
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public String getOpenglExtensions(int index) {
        return this.openglExtensions_.get(index);
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public ByteString getOpenglExtensionsBytes(int index) {
        return ByteString.copyFromUtf8(this.openglExtensions_.get(index));
    }

    private void ensureOpenglExtensionsIsMutable() {
        if (!this.openglExtensions_.isModifiable()) {
            this.openglExtensions_ = GeneratedMessageLite.mutableCopy(this.openglExtensions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOpenglExtensions(int index, String value) {
        if (value != null) {
            ensureOpenglExtensionsIsMutable();
            this.openglExtensions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOpenglExtensions(String value) {
        if (value != null) {
            ensureOpenglExtensionsIsMutable();
            this.openglExtensions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllOpenglExtensions(Iterable<String> values) {
        ensureOpenglExtensionsIsMutable();
        AbstractMessageLite.addAll(values, this.openglExtensions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearOpenglExtensions() {
        this.openglExtensions_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addOpenglExtensionsBytes(ByteString value) {
        if (value != null) {
            ensureOpenglExtensionsIsMutable();
            this.openglExtensions_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public List<String> getSharedLibrariesList() {
        return this.sharedLibraries_;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getSharedLibrariesCount() {
        return this.sharedLibraries_.size();
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public String getSharedLibraries(int index) {
        return this.sharedLibraries_.get(index);
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public ByteString getSharedLibrariesBytes(int index) {
        return ByteString.copyFromUtf8(this.sharedLibraries_.get(index));
    }

    private void ensureSharedLibrariesIsMutable() {
        if (!this.sharedLibraries_.isModifiable()) {
            this.sharedLibraries_ = GeneratedMessageLite.mutableCopy(this.sharedLibraries_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSharedLibraries(int index, String value) {
        if (value != null) {
            ensureSharedLibrariesIsMutable();
            this.sharedLibraries_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedLibraries(String value) {
        if (value != null) {
            ensureSharedLibrariesIsMutable();
            this.sharedLibraries_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSharedLibraries(Iterable<String> values) {
        ensureSharedLibrariesIsMutable();
        AbstractMessageLite.addAll(values, this.sharedLibraries_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSharedLibraries() {
        this.sharedLibraries_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSharedLibrariesBytes(ByteString value) {
        if (value != null) {
            ensureSharedLibrariesIsMutable();
            this.sharedLibraries_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public List<String> getFeaturesList() {
        return this.features_;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getFeaturesCount() {
        return this.features_.size();
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public String getFeatures(int index) {
        return this.features_.get(index);
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public ByteString getFeaturesBytes(int index) {
        return ByteString.copyFromUtf8(this.features_.get(index));
    }

    private void ensureFeaturesIsMutable() {
        if (!this.features_.isModifiable()) {
            this.features_ = GeneratedMessageLite.mutableCopy(this.features_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFeatures(int index, String value) {
        if (value != null) {
            ensureFeaturesIsMutable();
            this.features_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFeatures(String value) {
        if (value != null) {
            ensureFeaturesIsMutable();
            this.features_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllFeatures(Iterable<String> values) {
        ensureFeaturesIsMutable();
        AbstractMessageLite.addAll(values, this.features_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFeatures() {
        this.features_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFeaturesBytes(ByteString value) {
        if (value != null) {
            ensureFeaturesIsMutable();
            this.features_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public List<String> getCpuArchitecturesList() {
        return this.cpuArchitectures_;
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public int getCpuArchitecturesCount() {
        return this.cpuArchitectures_.size();
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public String getCpuArchitectures(int index) {
        return this.cpuArchitectures_.get(index);
    }

    @Override // android.content.DeviceConfigurationProtoOrBuilder
    public ByteString getCpuArchitecturesBytes(int index) {
        return ByteString.copyFromUtf8(this.cpuArchitectures_.get(index));
    }

    private void ensureCpuArchitecturesIsMutable() {
        if (!this.cpuArchitectures_.isModifiable()) {
            this.cpuArchitectures_ = GeneratedMessageLite.mutableCopy(this.cpuArchitectures_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuArchitectures(int index, String value) {
        if (value != null) {
            ensureCpuArchitecturesIsMutable();
            this.cpuArchitectures_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuArchitectures(String value) {
        if (value != null) {
            ensureCpuArchitecturesIsMutable();
            this.cpuArchitectures_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCpuArchitectures(Iterable<String> values) {
        ensureCpuArchitecturesIsMutable();
        AbstractMessageLite.addAll(values, this.cpuArchitectures_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuArchitectures() {
        this.cpuArchitectures_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCpuArchitecturesBytes(ByteString value) {
        if (value != null) {
            ensureCpuArchitecturesIsMutable();
            this.cpuArchitectures_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeUInt32(1, this.stableScreenWidthPx_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeUInt32(2, this.stableScreenHeightPx_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeUInt32(3, this.stableDensityDpi_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeUInt64(4, this.totalRam_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.lowRam_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeUInt32(6, this.maxCores_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.hasSecureScreenLock_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeUInt32(8, this.openglVersion_);
        }
        for (int i = 0; i < this.openglExtensions_.size(); i++) {
            output.writeString(9, this.openglExtensions_.get(i));
        }
        for (int i2 = 0; i2 < this.sharedLibraries_.size(); i2++) {
            output.writeString(10, this.sharedLibraries_.get(i2));
        }
        for (int i3 = 0; i3 < this.features_.size(); i3++) {
            output.writeString(11, this.features_.get(i3));
        }
        for (int i4 = 0; i4 < this.cpuArchitectures_.size(); i4++) {
            output.writeString(12, this.cpuArchitectures_.get(i4));
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
            size2 = 0 + CodedOutputStream.computeUInt32Size(1, this.stableScreenWidthPx_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeUInt32Size(2, this.stableScreenHeightPx_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeUInt32Size(3, this.stableDensityDpi_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeUInt64Size(4, this.totalRam_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.lowRam_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeUInt32Size(6, this.maxCores_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.hasSecureScreenLock_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeUInt32Size(8, this.openglVersion_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.openglExtensions_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.openglExtensions_.get(i));
        }
        int size3 = size2 + dataSize + (getOpenglExtensionsList().size() * 1);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.sharedLibraries_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeStringSizeNoTag(this.sharedLibraries_.get(i2));
        }
        int size4 = size3 + dataSize2 + (getSharedLibrariesList().size() * 1);
        int dataSize3 = 0;
        for (int i3 = 0; i3 < this.features_.size(); i3++) {
            dataSize3 += CodedOutputStream.computeStringSizeNoTag(this.features_.get(i3));
        }
        int size5 = size4 + dataSize3 + (getFeaturesList().size() * 1);
        int dataSize4 = 0;
        for (int i4 = 0; i4 < this.cpuArchitectures_.size(); i4++) {
            dataSize4 += CodedOutputStream.computeStringSizeNoTag(this.cpuArchitectures_.get(i4));
        }
        int size6 = size5 + dataSize4 + (getCpuArchitecturesList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size6;
        return size6;
    }

    public static DeviceConfigurationProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DeviceConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DeviceConfigurationProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DeviceConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DeviceConfigurationProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DeviceConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DeviceConfigurationProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DeviceConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DeviceConfigurationProto parseFrom(InputStream input) throws IOException {
        return (DeviceConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DeviceConfigurationProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DeviceConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DeviceConfigurationProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DeviceConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DeviceConfigurationProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DeviceConfigurationProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DeviceConfigurationProto parseFrom(CodedInputStream input) throws IOException {
        return (DeviceConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DeviceConfigurationProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DeviceConfigurationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DeviceConfigurationProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DeviceConfigurationProto, Builder> implements DeviceConfigurationProtoOrBuilder {
        private Builder() {
            super(DeviceConfigurationProto.DEFAULT_INSTANCE);
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean hasStableScreenWidthPx() {
            return ((DeviceConfigurationProto) this.instance).hasStableScreenWidthPx();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getStableScreenWidthPx() {
            return ((DeviceConfigurationProto) this.instance).getStableScreenWidthPx();
        }

        public Builder setStableScreenWidthPx(int value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setStableScreenWidthPx(value);
            return this;
        }

        public Builder clearStableScreenWidthPx() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearStableScreenWidthPx();
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean hasStableScreenHeightPx() {
            return ((DeviceConfigurationProto) this.instance).hasStableScreenHeightPx();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getStableScreenHeightPx() {
            return ((DeviceConfigurationProto) this.instance).getStableScreenHeightPx();
        }

        public Builder setStableScreenHeightPx(int value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setStableScreenHeightPx(value);
            return this;
        }

        public Builder clearStableScreenHeightPx() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearStableScreenHeightPx();
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean hasStableDensityDpi() {
            return ((DeviceConfigurationProto) this.instance).hasStableDensityDpi();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getStableDensityDpi() {
            return ((DeviceConfigurationProto) this.instance).getStableDensityDpi();
        }

        public Builder setStableDensityDpi(int value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setStableDensityDpi(value);
            return this;
        }

        public Builder clearStableDensityDpi() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearStableDensityDpi();
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean hasTotalRam() {
            return ((DeviceConfigurationProto) this.instance).hasTotalRam();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public long getTotalRam() {
            return ((DeviceConfigurationProto) this.instance).getTotalRam();
        }

        public Builder setTotalRam(long value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setTotalRam(value);
            return this;
        }

        public Builder clearTotalRam() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearTotalRam();
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean hasLowRam() {
            return ((DeviceConfigurationProto) this.instance).hasLowRam();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean getLowRam() {
            return ((DeviceConfigurationProto) this.instance).getLowRam();
        }

        public Builder setLowRam(boolean value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setLowRam(value);
            return this;
        }

        public Builder clearLowRam() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearLowRam();
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean hasMaxCores() {
            return ((DeviceConfigurationProto) this.instance).hasMaxCores();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getMaxCores() {
            return ((DeviceConfigurationProto) this.instance).getMaxCores();
        }

        public Builder setMaxCores(int value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setMaxCores(value);
            return this;
        }

        public Builder clearMaxCores() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearMaxCores();
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean hasHasSecureScreenLock() {
            return ((DeviceConfigurationProto) this.instance).hasHasSecureScreenLock();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean getHasSecureScreenLock() {
            return ((DeviceConfigurationProto) this.instance).getHasSecureScreenLock();
        }

        public Builder setHasSecureScreenLock(boolean value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setHasSecureScreenLock(value);
            return this;
        }

        public Builder clearHasSecureScreenLock() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearHasSecureScreenLock();
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public boolean hasOpenglVersion() {
            return ((DeviceConfigurationProto) this.instance).hasOpenglVersion();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getOpenglVersion() {
            return ((DeviceConfigurationProto) this.instance).getOpenglVersion();
        }

        public Builder setOpenglVersion(int value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setOpenglVersion(value);
            return this;
        }

        public Builder clearOpenglVersion() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearOpenglVersion();
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public List<String> getOpenglExtensionsList() {
            return Collections.unmodifiableList(((DeviceConfigurationProto) this.instance).getOpenglExtensionsList());
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getOpenglExtensionsCount() {
            return ((DeviceConfigurationProto) this.instance).getOpenglExtensionsCount();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public String getOpenglExtensions(int index) {
            return ((DeviceConfigurationProto) this.instance).getOpenglExtensions(index);
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public ByteString getOpenglExtensionsBytes(int index) {
            return ((DeviceConfigurationProto) this.instance).getOpenglExtensionsBytes(index);
        }

        public Builder setOpenglExtensions(int index, String value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setOpenglExtensions(index, value);
            return this;
        }

        public Builder addOpenglExtensions(String value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addOpenglExtensions(value);
            return this;
        }

        public Builder addAllOpenglExtensions(Iterable<String> values) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addAllOpenglExtensions(values);
            return this;
        }

        public Builder clearOpenglExtensions() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearOpenglExtensions();
            return this;
        }

        public Builder addOpenglExtensionsBytes(ByteString value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addOpenglExtensionsBytes(value);
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public List<String> getSharedLibrariesList() {
            return Collections.unmodifiableList(((DeviceConfigurationProto) this.instance).getSharedLibrariesList());
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getSharedLibrariesCount() {
            return ((DeviceConfigurationProto) this.instance).getSharedLibrariesCount();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public String getSharedLibraries(int index) {
            return ((DeviceConfigurationProto) this.instance).getSharedLibraries(index);
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public ByteString getSharedLibrariesBytes(int index) {
            return ((DeviceConfigurationProto) this.instance).getSharedLibrariesBytes(index);
        }

        public Builder setSharedLibraries(int index, String value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setSharedLibraries(index, value);
            return this;
        }

        public Builder addSharedLibraries(String value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addSharedLibraries(value);
            return this;
        }

        public Builder addAllSharedLibraries(Iterable<String> values) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addAllSharedLibraries(values);
            return this;
        }

        public Builder clearSharedLibraries() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearSharedLibraries();
            return this;
        }

        public Builder addSharedLibrariesBytes(ByteString value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addSharedLibrariesBytes(value);
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public List<String> getFeaturesList() {
            return Collections.unmodifiableList(((DeviceConfigurationProto) this.instance).getFeaturesList());
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getFeaturesCount() {
            return ((DeviceConfigurationProto) this.instance).getFeaturesCount();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public String getFeatures(int index) {
            return ((DeviceConfigurationProto) this.instance).getFeatures(index);
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public ByteString getFeaturesBytes(int index) {
            return ((DeviceConfigurationProto) this.instance).getFeaturesBytes(index);
        }

        public Builder setFeatures(int index, String value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setFeatures(index, value);
            return this;
        }

        public Builder addFeatures(String value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addFeatures(value);
            return this;
        }

        public Builder addAllFeatures(Iterable<String> values) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addAllFeatures(values);
            return this;
        }

        public Builder clearFeatures() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearFeatures();
            return this;
        }

        public Builder addFeaturesBytes(ByteString value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addFeaturesBytes(value);
            return this;
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public List<String> getCpuArchitecturesList() {
            return Collections.unmodifiableList(((DeviceConfigurationProto) this.instance).getCpuArchitecturesList());
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public int getCpuArchitecturesCount() {
            return ((DeviceConfigurationProto) this.instance).getCpuArchitecturesCount();
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public String getCpuArchitectures(int index) {
            return ((DeviceConfigurationProto) this.instance).getCpuArchitectures(index);
        }

        @Override // android.content.DeviceConfigurationProtoOrBuilder
        public ByteString getCpuArchitecturesBytes(int index) {
            return ((DeviceConfigurationProto) this.instance).getCpuArchitecturesBytes(index);
        }

        public Builder setCpuArchitectures(int index, String value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).setCpuArchitectures(index, value);
            return this;
        }

        public Builder addCpuArchitectures(String value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addCpuArchitectures(value);
            return this;
        }

        public Builder addAllCpuArchitectures(Iterable<String> values) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addAllCpuArchitectures(values);
            return this;
        }

        public Builder clearCpuArchitectures() {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).clearCpuArchitectures();
            return this;
        }

        public Builder addCpuArchitecturesBytes(ByteString value) {
            copyOnWrite();
            ((DeviceConfigurationProto) this.instance).addCpuArchitecturesBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DeviceConfigurationProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.openglExtensions_.makeImmutable();
                this.sharedLibraries_.makeImmutable();
                this.features_.makeImmutable();
                this.cpuArchitectures_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DeviceConfigurationProto other = (DeviceConfigurationProto) arg1;
                this.stableScreenWidthPx_ = visitor.visitInt(hasStableScreenWidthPx(), this.stableScreenWidthPx_, other.hasStableScreenWidthPx(), other.stableScreenWidthPx_);
                this.stableScreenHeightPx_ = visitor.visitInt(hasStableScreenHeightPx(), this.stableScreenHeightPx_, other.hasStableScreenHeightPx(), other.stableScreenHeightPx_);
                this.stableDensityDpi_ = visitor.visitInt(hasStableDensityDpi(), this.stableDensityDpi_, other.hasStableDensityDpi(), other.stableDensityDpi_);
                this.totalRam_ = visitor.visitLong(hasTotalRam(), this.totalRam_, other.hasTotalRam(), other.totalRam_);
                this.lowRam_ = visitor.visitBoolean(hasLowRam(), this.lowRam_, other.hasLowRam(), other.lowRam_);
                this.maxCores_ = visitor.visitInt(hasMaxCores(), this.maxCores_, other.hasMaxCores(), other.maxCores_);
                this.hasSecureScreenLock_ = visitor.visitBoolean(hasHasSecureScreenLock(), this.hasSecureScreenLock_, other.hasHasSecureScreenLock(), other.hasSecureScreenLock_);
                this.openglVersion_ = visitor.visitInt(hasOpenglVersion(), this.openglVersion_, other.hasOpenglVersion(), other.openglVersion_);
                this.openglExtensions_ = visitor.visitList(this.openglExtensions_, other.openglExtensions_);
                this.sharedLibraries_ = visitor.visitList(this.sharedLibraries_, other.sharedLibraries_);
                this.features_ = visitor.visitList(this.features_, other.features_);
                this.cpuArchitectures_ = visitor.visitList(this.cpuArchitectures_, other.cpuArchitectures_);
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
                            case 8:
                                this.bitField0_ |= 1;
                                this.stableScreenWidthPx_ = input.readUInt32();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.stableScreenHeightPx_ = input.readUInt32();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.stableDensityDpi_ = input.readUInt32();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.totalRam_ = input.readUInt64();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.lowRam_ = input.readBool();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.maxCores_ = input.readUInt32();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.hasSecureScreenLock_ = input.readBool();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.openglVersion_ = input.readUInt32();
                                break;
                            case 74:
                                String s = input.readString();
                                if (!this.openglExtensions_.isModifiable()) {
                                    this.openglExtensions_ = GeneratedMessageLite.mutableCopy(this.openglExtensions_);
                                }
                                this.openglExtensions_.add(s);
                                break;
                            case 82:
                                String s2 = input.readString();
                                if (!this.sharedLibraries_.isModifiable()) {
                                    this.sharedLibraries_ = GeneratedMessageLite.mutableCopy(this.sharedLibraries_);
                                }
                                this.sharedLibraries_.add(s2);
                                break;
                            case 90:
                                String s3 = input.readString();
                                if (!this.features_.isModifiable()) {
                                    this.features_ = GeneratedMessageLite.mutableCopy(this.features_);
                                }
                                this.features_.add(s3);
                                break;
                            case 98:
                                String s4 = input.readString();
                                if (!this.cpuArchitectures_.isModifiable()) {
                                    this.cpuArchitectures_ = GeneratedMessageLite.mutableCopy(this.cpuArchitectures_);
                                }
                                this.cpuArchitectures_.add(s4);
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
                    synchronized (DeviceConfigurationProto.class) {
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

    public static DeviceConfigurationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DeviceConfigurationProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
