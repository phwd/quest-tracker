package android.service.print;

import android.service.print.MarginsProto;
import android.service.print.MediaSizeProto;
import android.service.print.PrintAttributesProto;
import android.service.print.ResolutionProto;
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

public final class PrinterCapabilitiesProto extends GeneratedMessageLite<PrinterCapabilitiesProto, Builder> implements PrinterCapabilitiesProtoOrBuilder {
    public static final int COLOR_MODES_FIELD_NUMBER = 4;
    private static final PrinterCapabilitiesProto DEFAULT_INSTANCE = new PrinterCapabilitiesProto();
    public static final int DUPLEX_MODES_FIELD_NUMBER = 5;
    public static final int MEDIA_SIZES_FIELD_NUMBER = 2;
    public static final int MIN_MARGINS_FIELD_NUMBER = 1;
    private static volatile Parser<PrinterCapabilitiesProto> PARSER = null;
    public static final int RESOLUTIONS_FIELD_NUMBER = 3;
    private static final Internal.ListAdapter.Converter<Integer, PrintAttributesProto.ColorMode> colorModes_converter_ = new Internal.ListAdapter.Converter<Integer, PrintAttributesProto.ColorMode>() {
        /* class android.service.print.PrinterCapabilitiesProto.AnonymousClass1 */

        public PrintAttributesProto.ColorMode convert(Integer from) {
            PrintAttributesProto.ColorMode result = PrintAttributesProto.ColorMode.forNumber(from.intValue());
            return result == null ? PrintAttributesProto.ColorMode.__COLOR_MODE_UNUSED : result;
        }
    };
    private static final Internal.ListAdapter.Converter<Integer, PrintAttributesProto.DuplexMode> duplexModes_converter_ = new Internal.ListAdapter.Converter<Integer, PrintAttributesProto.DuplexMode>() {
        /* class android.service.print.PrinterCapabilitiesProto.AnonymousClass2 */

        public PrintAttributesProto.DuplexMode convert(Integer from) {
            PrintAttributesProto.DuplexMode result = PrintAttributesProto.DuplexMode.forNumber(from.intValue());
            return result == null ? PrintAttributesProto.DuplexMode.__DUPLEX_MODE_UNUSED : result;
        }
    };
    private int bitField0_;
    private Internal.IntList colorModes_ = emptyIntList();
    private Internal.IntList duplexModes_ = emptyIntList();
    private Internal.ProtobufList<MediaSizeProto> mediaSizes_ = emptyProtobufList();
    private MarginsProto minMargins_;
    private Internal.ProtobufList<ResolutionProto> resolutions_ = emptyProtobufList();

    private PrinterCapabilitiesProto() {
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public boolean hasMinMargins() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public MarginsProto getMinMargins() {
        MarginsProto marginsProto = this.minMargins_;
        return marginsProto == null ? MarginsProto.getDefaultInstance() : marginsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinMargins(MarginsProto value) {
        if (value != null) {
            this.minMargins_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinMargins(MarginsProto.Builder builderForValue) {
        this.minMargins_ = (MarginsProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMinMargins(MarginsProto value) {
        MarginsProto marginsProto = this.minMargins_;
        if (marginsProto == null || marginsProto == MarginsProto.getDefaultInstance()) {
            this.minMargins_ = value;
        } else {
            this.minMargins_ = (MarginsProto) ((MarginsProto.Builder) MarginsProto.newBuilder(this.minMargins_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinMargins() {
        this.minMargins_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public List<MediaSizeProto> getMediaSizesList() {
        return this.mediaSizes_;
    }

    public List<? extends MediaSizeProtoOrBuilder> getMediaSizesOrBuilderList() {
        return this.mediaSizes_;
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public int getMediaSizesCount() {
        return this.mediaSizes_.size();
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public MediaSizeProto getMediaSizes(int index) {
        return this.mediaSizes_.get(index);
    }

    public MediaSizeProtoOrBuilder getMediaSizesOrBuilder(int index) {
        return this.mediaSizes_.get(index);
    }

    private void ensureMediaSizesIsMutable() {
        if (!this.mediaSizes_.isModifiable()) {
            this.mediaSizes_ = GeneratedMessageLite.mutableCopy(this.mediaSizes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMediaSizes(int index, MediaSizeProto value) {
        if (value != null) {
            ensureMediaSizesIsMutable();
            this.mediaSizes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMediaSizes(int index, MediaSizeProto.Builder builderForValue) {
        ensureMediaSizesIsMutable();
        this.mediaSizes_.set(index, (MediaSizeProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMediaSizes(MediaSizeProto value) {
        if (value != null) {
            ensureMediaSizesIsMutable();
            this.mediaSizes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMediaSizes(int index, MediaSizeProto value) {
        if (value != null) {
            ensureMediaSizesIsMutable();
            this.mediaSizes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMediaSizes(MediaSizeProto.Builder builderForValue) {
        ensureMediaSizesIsMutable();
        this.mediaSizes_.add((MediaSizeProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMediaSizes(int index, MediaSizeProto.Builder builderForValue) {
        ensureMediaSizesIsMutable();
        this.mediaSizes_.add(index, (MediaSizeProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllMediaSizes(Iterable<? extends MediaSizeProto> values) {
        ensureMediaSizesIsMutable();
        AbstractMessageLite.addAll(values, this.mediaSizes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMediaSizes() {
        this.mediaSizes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeMediaSizes(int index) {
        ensureMediaSizesIsMutable();
        this.mediaSizes_.remove(index);
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public List<ResolutionProto> getResolutionsList() {
        return this.resolutions_;
    }

    public List<? extends ResolutionProtoOrBuilder> getResolutionsOrBuilderList() {
        return this.resolutions_;
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public int getResolutionsCount() {
        return this.resolutions_.size();
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public ResolutionProto getResolutions(int index) {
        return this.resolutions_.get(index);
    }

    public ResolutionProtoOrBuilder getResolutionsOrBuilder(int index) {
        return this.resolutions_.get(index);
    }

    private void ensureResolutionsIsMutable() {
        if (!this.resolutions_.isModifiable()) {
            this.resolutions_ = GeneratedMessageLite.mutableCopy(this.resolutions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResolutions(int index, ResolutionProto value) {
        if (value != null) {
            ensureResolutionsIsMutable();
            this.resolutions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setResolutions(int index, ResolutionProto.Builder builderForValue) {
        ensureResolutionsIsMutable();
        this.resolutions_.set(index, (ResolutionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResolutions(ResolutionProto value) {
        if (value != null) {
            ensureResolutionsIsMutable();
            this.resolutions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResolutions(int index, ResolutionProto value) {
        if (value != null) {
            ensureResolutionsIsMutable();
            this.resolutions_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResolutions(ResolutionProto.Builder builderForValue) {
        ensureResolutionsIsMutable();
        this.resolutions_.add((ResolutionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addResolutions(int index, ResolutionProto.Builder builderForValue) {
        ensureResolutionsIsMutable();
        this.resolutions_.add(index, (ResolutionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllResolutions(Iterable<? extends ResolutionProto> values) {
        ensureResolutionsIsMutable();
        AbstractMessageLite.addAll(values, this.resolutions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearResolutions() {
        this.resolutions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeResolutions(int index) {
        ensureResolutionsIsMutable();
        this.resolutions_.remove(index);
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public List<PrintAttributesProto.ColorMode> getColorModesList() {
        return new Internal.ListAdapter(this.colorModes_, colorModes_converter_);
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public int getColorModesCount() {
        return this.colorModes_.size();
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public PrintAttributesProto.ColorMode getColorModes(int index) {
        return colorModes_converter_.convert(Integer.valueOf(this.colorModes_.getInt(index)));
    }

    private void ensureColorModesIsMutable() {
        if (!this.colorModes_.isModifiable()) {
            this.colorModes_ = GeneratedMessageLite.mutableCopy(this.colorModes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setColorModes(int index, PrintAttributesProto.ColorMode value) {
        if (value != null) {
            ensureColorModesIsMutable();
            this.colorModes_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addColorModes(PrintAttributesProto.ColorMode value) {
        if (value != null) {
            ensureColorModesIsMutable();
            this.colorModes_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllColorModes(Iterable<? extends PrintAttributesProto.ColorMode> values) {
        ensureColorModesIsMutable();
        for (PrintAttributesProto.ColorMode value : values) {
            this.colorModes_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearColorModes() {
        this.colorModes_ = emptyIntList();
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public List<PrintAttributesProto.DuplexMode> getDuplexModesList() {
        return new Internal.ListAdapter(this.duplexModes_, duplexModes_converter_);
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public int getDuplexModesCount() {
        return this.duplexModes_.size();
    }

    @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
    public PrintAttributesProto.DuplexMode getDuplexModes(int index) {
        return duplexModes_converter_.convert(Integer.valueOf(this.duplexModes_.getInt(index)));
    }

    private void ensureDuplexModesIsMutable() {
        if (!this.duplexModes_.isModifiable()) {
            this.duplexModes_ = GeneratedMessageLite.mutableCopy(this.duplexModes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDuplexModes(int index, PrintAttributesProto.DuplexMode value) {
        if (value != null) {
            ensureDuplexModesIsMutable();
            this.duplexModes_.setInt(index, value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDuplexModes(PrintAttributesProto.DuplexMode value) {
        if (value != null) {
            ensureDuplexModesIsMutable();
            this.duplexModes_.addInt(value.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDuplexModes(Iterable<? extends PrintAttributesProto.DuplexMode> values) {
        ensureDuplexModesIsMutable();
        for (PrintAttributesProto.DuplexMode value : values) {
            this.duplexModes_.addInt(value.getNumber());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDuplexModes() {
        this.duplexModes_ = emptyIntList();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getMinMargins());
        }
        for (int i = 0; i < this.mediaSizes_.size(); i++) {
            output.writeMessage(2, this.mediaSizes_.get(i));
        }
        for (int i2 = 0; i2 < this.resolutions_.size(); i2++) {
            output.writeMessage(3, this.resolutions_.get(i2));
        }
        for (int i3 = 0; i3 < this.colorModes_.size(); i3++) {
            output.writeEnum(4, this.colorModes_.getInt(i3));
        }
        for (int i4 = 0; i4 < this.duplexModes_.size(); i4++) {
            output.writeEnum(5, this.duplexModes_.getInt(i4));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getMinMargins());
        }
        for (int i = 0; i < this.mediaSizes_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.mediaSizes_.get(i));
        }
        for (int i2 = 0; i2 < this.resolutions_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.resolutions_.get(i2));
        }
        int dataSize = 0;
        for (int i3 = 0; i3 < this.colorModes_.size(); i3++) {
            dataSize += CodedOutputStream.computeEnumSizeNoTag(this.colorModes_.getInt(i3));
        }
        int size3 = size2 + dataSize + (this.colorModes_.size() * 1);
        int dataSize2 = 0;
        for (int i4 = 0; i4 < this.duplexModes_.size(); i4++) {
            dataSize2 += CodedOutputStream.computeEnumSizeNoTag(this.duplexModes_.getInt(i4));
        }
        int size4 = size3 + dataSize2 + (this.duplexModes_.size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static PrinterCapabilitiesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrinterCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrinterCapabilitiesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrinterCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrinterCapabilitiesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrinterCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrinterCapabilitiesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrinterCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrinterCapabilitiesProto parseFrom(InputStream input) throws IOException {
        return (PrinterCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterCapabilitiesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrinterCapabilitiesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrinterCapabilitiesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterCapabilitiesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterCapabilitiesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrinterCapabilitiesProto parseFrom(CodedInputStream input) throws IOException {
        return (PrinterCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterCapabilitiesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterCapabilitiesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrinterCapabilitiesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrinterCapabilitiesProto, Builder> implements PrinterCapabilitiesProtoOrBuilder {
        private Builder() {
            super(PrinterCapabilitiesProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public boolean hasMinMargins() {
            return ((PrinterCapabilitiesProto) this.instance).hasMinMargins();
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public MarginsProto getMinMargins() {
            return ((PrinterCapabilitiesProto) this.instance).getMinMargins();
        }

        public Builder setMinMargins(MarginsProto value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).setMinMargins((PrinterCapabilitiesProto) value);
            return this;
        }

        public Builder setMinMargins(MarginsProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).setMinMargins((PrinterCapabilitiesProto) builderForValue);
            return this;
        }

        public Builder mergeMinMargins(MarginsProto value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).mergeMinMargins(value);
            return this;
        }

        public Builder clearMinMargins() {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).clearMinMargins();
            return this;
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public List<MediaSizeProto> getMediaSizesList() {
            return Collections.unmodifiableList(((PrinterCapabilitiesProto) this.instance).getMediaSizesList());
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public int getMediaSizesCount() {
            return ((PrinterCapabilitiesProto) this.instance).getMediaSizesCount();
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public MediaSizeProto getMediaSizes(int index) {
            return ((PrinterCapabilitiesProto) this.instance).getMediaSizes(index);
        }

        public Builder setMediaSizes(int index, MediaSizeProto value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).setMediaSizes((PrinterCapabilitiesProto) index, (int) value);
            return this;
        }

        public Builder setMediaSizes(int index, MediaSizeProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).setMediaSizes((PrinterCapabilitiesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addMediaSizes(MediaSizeProto value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addMediaSizes((PrinterCapabilitiesProto) value);
            return this;
        }

        public Builder addMediaSizes(int index, MediaSizeProto value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addMediaSizes((PrinterCapabilitiesProto) index, (int) value);
            return this;
        }

        public Builder addMediaSizes(MediaSizeProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addMediaSizes((PrinterCapabilitiesProto) builderForValue);
            return this;
        }

        public Builder addMediaSizes(int index, MediaSizeProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addMediaSizes((PrinterCapabilitiesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllMediaSizes(Iterable<? extends MediaSizeProto> values) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addAllMediaSizes(values);
            return this;
        }

        public Builder clearMediaSizes() {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).clearMediaSizes();
            return this;
        }

        public Builder removeMediaSizes(int index) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).removeMediaSizes(index);
            return this;
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public List<ResolutionProto> getResolutionsList() {
            return Collections.unmodifiableList(((PrinterCapabilitiesProto) this.instance).getResolutionsList());
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public int getResolutionsCount() {
            return ((PrinterCapabilitiesProto) this.instance).getResolutionsCount();
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public ResolutionProto getResolutions(int index) {
            return ((PrinterCapabilitiesProto) this.instance).getResolutions(index);
        }

        public Builder setResolutions(int index, ResolutionProto value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).setResolutions((PrinterCapabilitiesProto) index, (int) value);
            return this;
        }

        public Builder setResolutions(int index, ResolutionProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).setResolutions((PrinterCapabilitiesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addResolutions(ResolutionProto value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addResolutions((PrinterCapabilitiesProto) value);
            return this;
        }

        public Builder addResolutions(int index, ResolutionProto value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addResolutions((PrinterCapabilitiesProto) index, (int) value);
            return this;
        }

        public Builder addResolutions(ResolutionProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addResolutions((PrinterCapabilitiesProto) builderForValue);
            return this;
        }

        public Builder addResolutions(int index, ResolutionProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addResolutions((PrinterCapabilitiesProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllResolutions(Iterable<? extends ResolutionProto> values) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addAllResolutions(values);
            return this;
        }

        public Builder clearResolutions() {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).clearResolutions();
            return this;
        }

        public Builder removeResolutions(int index) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).removeResolutions(index);
            return this;
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public List<PrintAttributesProto.ColorMode> getColorModesList() {
            return ((PrinterCapabilitiesProto) this.instance).getColorModesList();
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public int getColorModesCount() {
            return ((PrinterCapabilitiesProto) this.instance).getColorModesCount();
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public PrintAttributesProto.ColorMode getColorModes(int index) {
            return ((PrinterCapabilitiesProto) this.instance).getColorModes(index);
        }

        public Builder setColorModes(int index, PrintAttributesProto.ColorMode value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).setColorModes(index, value);
            return this;
        }

        public Builder addColorModes(PrintAttributesProto.ColorMode value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addColorModes(value);
            return this;
        }

        public Builder addAllColorModes(Iterable<? extends PrintAttributesProto.ColorMode> values) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addAllColorModes(values);
            return this;
        }

        public Builder clearColorModes() {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).clearColorModes();
            return this;
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public List<PrintAttributesProto.DuplexMode> getDuplexModesList() {
            return ((PrinterCapabilitiesProto) this.instance).getDuplexModesList();
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public int getDuplexModesCount() {
            return ((PrinterCapabilitiesProto) this.instance).getDuplexModesCount();
        }

        @Override // android.service.print.PrinterCapabilitiesProtoOrBuilder
        public PrintAttributesProto.DuplexMode getDuplexModes(int index) {
            return ((PrinterCapabilitiesProto) this.instance).getDuplexModes(index);
        }

        public Builder setDuplexModes(int index, PrintAttributesProto.DuplexMode value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).setDuplexModes(index, value);
            return this;
        }

        public Builder addDuplexModes(PrintAttributesProto.DuplexMode value) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addDuplexModes(value);
            return this;
        }

        public Builder addAllDuplexModes(Iterable<? extends PrintAttributesProto.DuplexMode> values) {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).addAllDuplexModes(values);
            return this;
        }

        public Builder clearDuplexModes() {
            copyOnWrite();
            ((PrinterCapabilitiesProto) this.instance).clearDuplexModes();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrinterCapabilitiesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.mediaSizes_.makeImmutable();
                this.resolutions_.makeImmutable();
                this.colorModes_.makeImmutable();
                this.duplexModes_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrinterCapabilitiesProto other = (PrinterCapabilitiesProto) arg1;
                this.minMargins_ = (MarginsProto) visitor.visitMessage(this.minMargins_, other.minMargins_);
                this.mediaSizes_ = visitor.visitList(this.mediaSizes_, other.mediaSizes_);
                this.resolutions_ = visitor.visitList(this.resolutions_, other.resolutions_);
                this.colorModes_ = visitor.visitIntList(this.colorModes_, other.colorModes_);
                this.duplexModes_ = visitor.visitIntList(this.duplexModes_, other.duplexModes_);
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
                            MarginsProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (MarginsProto.Builder) this.minMargins_.toBuilder();
                            }
                            this.minMargins_ = (MarginsProto) input.readMessage(MarginsProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.minMargins_);
                                this.minMargins_ = (MarginsProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            if (!this.mediaSizes_.isModifiable()) {
                                this.mediaSizes_ = GeneratedMessageLite.mutableCopy(this.mediaSizes_);
                            }
                            this.mediaSizes_.add((MediaSizeProto) input.readMessage(MediaSizeProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.resolutions_.isModifiable()) {
                                this.resolutions_ = GeneratedMessageLite.mutableCopy(this.resolutions_);
                            }
                            this.resolutions_.add((ResolutionProto) input.readMessage(ResolutionProto.parser(), extensionRegistry));
                        } else if (tag == 32) {
                            if (!this.colorModes_.isModifiable()) {
                                this.colorModes_ = GeneratedMessageLite.mutableCopy(this.colorModes_);
                            }
                            int rawValue = input.readEnum();
                            if (PrintAttributesProto.ColorMode.forNumber(rawValue) == null) {
                                super.mergeVarintField(4, rawValue);
                            } else {
                                this.colorModes_.addInt(rawValue);
                            }
                        } else if (tag == 34) {
                            if (!this.colorModes_.isModifiable()) {
                                this.colorModes_ = GeneratedMessageLite.mutableCopy(this.colorModes_);
                            }
                            int oldLimit = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue2 = input.readEnum();
                                if (PrintAttributesProto.ColorMode.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(4, rawValue2);
                                } else {
                                    this.colorModes_.addInt(rawValue2);
                                }
                            }
                            input.popLimit(oldLimit);
                        } else if (tag == 40) {
                            if (!this.duplexModes_.isModifiable()) {
                                this.duplexModes_ = GeneratedMessageLite.mutableCopy(this.duplexModes_);
                            }
                            int rawValue3 = input.readEnum();
                            if (PrintAttributesProto.DuplexMode.forNumber(rawValue3) == null) {
                                super.mergeVarintField(5, rawValue3);
                            } else {
                                this.duplexModes_.addInt(rawValue3);
                            }
                        } else if (tag == 42) {
                            if (!this.duplexModes_.isModifiable()) {
                                this.duplexModes_ = GeneratedMessageLite.mutableCopy(this.duplexModes_);
                            }
                            int oldLimit2 = input.pushLimit(input.readRawVarint32());
                            while (input.getBytesUntilLimit() > 0) {
                                int rawValue4 = input.readEnum();
                                if (PrintAttributesProto.DuplexMode.forNumber(rawValue4) == null) {
                                    super.mergeVarintField(5, rawValue4);
                                } else {
                                    this.duplexModes_.addInt(rawValue4);
                                }
                            }
                            input.popLimit(oldLimit2);
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
                    synchronized (PrinterCapabilitiesProto.class) {
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

    public static PrinterCapabilitiesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrinterCapabilitiesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
