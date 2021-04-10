package android.service.print;

import android.service.print.PrinterIdProto;
import android.service.print.PrinterInfoProto;
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

public final class PrinterDiscoverySessionProto extends GeneratedMessageLite<PrinterDiscoverySessionProto, Builder> implements PrinterDiscoverySessionProtoOrBuilder {
    private static final PrinterDiscoverySessionProto DEFAULT_INSTANCE = new PrinterDiscoverySessionProto();
    public static final int DISCOVERY_REQUESTS_FIELD_NUMBER = 4;
    public static final int IS_DESTROYED_FIELD_NUMBER = 1;
    public static final int IS_PRINTER_DISCOVERY_IN_PROGRESS_FIELD_NUMBER = 2;
    private static volatile Parser<PrinterDiscoverySessionProto> PARSER = null;
    public static final int PRINTER_DISCOVERY_OBSERVERS_FIELD_NUMBER = 3;
    public static final int PRINTER_FIELD_NUMBER = 6;
    public static final int TRACKED_PRINTER_REQUESTS_FIELD_NUMBER = 5;
    private int bitField0_;
    private Internal.ProtobufList<String> discoveryRequests_ = GeneratedMessageLite.emptyProtobufList();
    private boolean isDestroyed_ = false;
    private boolean isPrinterDiscoveryInProgress_ = false;
    private Internal.ProtobufList<String> printerDiscoveryObservers_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<PrinterInfoProto> printer_ = emptyProtobufList();
    private Internal.ProtobufList<PrinterIdProto> trackedPrinterRequests_ = emptyProtobufList();

    private PrinterDiscoverySessionProto() {
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public boolean hasIsDestroyed() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public boolean getIsDestroyed() {
        return this.isDestroyed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDestroyed(boolean value) {
        this.bitField0_ |= 1;
        this.isDestroyed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDestroyed() {
        this.bitField0_ &= -2;
        this.isDestroyed_ = false;
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public boolean hasIsPrinterDiscoveryInProgress() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public boolean getIsPrinterDiscoveryInProgress() {
        return this.isPrinterDiscoveryInProgress_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPrinterDiscoveryInProgress(boolean value) {
        this.bitField0_ |= 2;
        this.isPrinterDiscoveryInProgress_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPrinterDiscoveryInProgress() {
        this.bitField0_ &= -3;
        this.isPrinterDiscoveryInProgress_ = false;
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public List<String> getPrinterDiscoveryObserversList() {
        return this.printerDiscoveryObservers_;
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public int getPrinterDiscoveryObserversCount() {
        return this.printerDiscoveryObservers_.size();
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public String getPrinterDiscoveryObservers(int index) {
        return this.printerDiscoveryObservers_.get(index);
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public ByteString getPrinterDiscoveryObserversBytes(int index) {
        return ByteString.copyFromUtf8(this.printerDiscoveryObservers_.get(index));
    }

    private void ensurePrinterDiscoveryObserversIsMutable() {
        if (!this.printerDiscoveryObservers_.isModifiable()) {
            this.printerDiscoveryObservers_ = GeneratedMessageLite.mutableCopy(this.printerDiscoveryObservers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrinterDiscoveryObservers(int index, String value) {
        if (value != null) {
            ensurePrinterDiscoveryObserversIsMutable();
            this.printerDiscoveryObservers_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrinterDiscoveryObservers(String value) {
        if (value != null) {
            ensurePrinterDiscoveryObserversIsMutable();
            this.printerDiscoveryObservers_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPrinterDiscoveryObservers(Iterable<String> values) {
        ensurePrinterDiscoveryObserversIsMutable();
        AbstractMessageLite.addAll(values, this.printerDiscoveryObservers_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrinterDiscoveryObservers() {
        this.printerDiscoveryObservers_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrinterDiscoveryObserversBytes(ByteString value) {
        if (value != null) {
            ensurePrinterDiscoveryObserversIsMutable();
            this.printerDiscoveryObservers_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public List<String> getDiscoveryRequestsList() {
        return this.discoveryRequests_;
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public int getDiscoveryRequestsCount() {
        return this.discoveryRequests_.size();
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public String getDiscoveryRequests(int index) {
        return this.discoveryRequests_.get(index);
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public ByteString getDiscoveryRequestsBytes(int index) {
        return ByteString.copyFromUtf8(this.discoveryRequests_.get(index));
    }

    private void ensureDiscoveryRequestsIsMutable() {
        if (!this.discoveryRequests_.isModifiable()) {
            this.discoveryRequests_ = GeneratedMessageLite.mutableCopy(this.discoveryRequests_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDiscoveryRequests(int index, String value) {
        if (value != null) {
            ensureDiscoveryRequestsIsMutable();
            this.discoveryRequests_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDiscoveryRequests(String value) {
        if (value != null) {
            ensureDiscoveryRequestsIsMutable();
            this.discoveryRequests_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDiscoveryRequests(Iterable<String> values) {
        ensureDiscoveryRequestsIsMutable();
        AbstractMessageLite.addAll(values, this.discoveryRequests_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDiscoveryRequests() {
        this.discoveryRequests_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDiscoveryRequestsBytes(ByteString value) {
        if (value != null) {
            ensureDiscoveryRequestsIsMutable();
            this.discoveryRequests_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public List<PrinterIdProto> getTrackedPrinterRequestsList() {
        return this.trackedPrinterRequests_;
    }

    public List<? extends PrinterIdProtoOrBuilder> getTrackedPrinterRequestsOrBuilderList() {
        return this.trackedPrinterRequests_;
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public int getTrackedPrinterRequestsCount() {
        return this.trackedPrinterRequests_.size();
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public PrinterIdProto getTrackedPrinterRequests(int index) {
        return this.trackedPrinterRequests_.get(index);
    }

    public PrinterIdProtoOrBuilder getTrackedPrinterRequestsOrBuilder(int index) {
        return this.trackedPrinterRequests_.get(index);
    }

    private void ensureTrackedPrinterRequestsIsMutable() {
        if (!this.trackedPrinterRequests_.isModifiable()) {
            this.trackedPrinterRequests_ = GeneratedMessageLite.mutableCopy(this.trackedPrinterRequests_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTrackedPrinterRequests(int index, PrinterIdProto value) {
        if (value != null) {
            ensureTrackedPrinterRequestsIsMutable();
            this.trackedPrinterRequests_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTrackedPrinterRequests(int index, PrinterIdProto.Builder builderForValue) {
        ensureTrackedPrinterRequestsIsMutable();
        this.trackedPrinterRequests_.set(index, (PrinterIdProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackedPrinterRequests(PrinterIdProto value) {
        if (value != null) {
            ensureTrackedPrinterRequestsIsMutable();
            this.trackedPrinterRequests_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackedPrinterRequests(int index, PrinterIdProto value) {
        if (value != null) {
            ensureTrackedPrinterRequestsIsMutable();
            this.trackedPrinterRequests_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackedPrinterRequests(PrinterIdProto.Builder builderForValue) {
        ensureTrackedPrinterRequestsIsMutable();
        this.trackedPrinterRequests_.add((PrinterIdProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackedPrinterRequests(int index, PrinterIdProto.Builder builderForValue) {
        ensureTrackedPrinterRequestsIsMutable();
        this.trackedPrinterRequests_.add(index, (PrinterIdProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTrackedPrinterRequests(Iterable<? extends PrinterIdProto> values) {
        ensureTrackedPrinterRequestsIsMutable();
        AbstractMessageLite.addAll(values, this.trackedPrinterRequests_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTrackedPrinterRequests() {
        this.trackedPrinterRequests_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTrackedPrinterRequests(int index) {
        ensureTrackedPrinterRequestsIsMutable();
        this.trackedPrinterRequests_.remove(index);
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public List<PrinterInfoProto> getPrinterList() {
        return this.printer_;
    }

    public List<? extends PrinterInfoProtoOrBuilder> getPrinterOrBuilderList() {
        return this.printer_;
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public int getPrinterCount() {
        return this.printer_.size();
    }

    @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
    public PrinterInfoProto getPrinter(int index) {
        return this.printer_.get(index);
    }

    public PrinterInfoProtoOrBuilder getPrinterOrBuilder(int index) {
        return this.printer_.get(index);
    }

    private void ensurePrinterIsMutable() {
        if (!this.printer_.isModifiable()) {
            this.printer_ = GeneratedMessageLite.mutableCopy(this.printer_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrinter(int index, PrinterInfoProto value) {
        if (value != null) {
            ensurePrinterIsMutable();
            this.printer_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrinter(int index, PrinterInfoProto.Builder builderForValue) {
        ensurePrinterIsMutable();
        this.printer_.set(index, (PrinterInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrinter(PrinterInfoProto value) {
        if (value != null) {
            ensurePrinterIsMutable();
            this.printer_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrinter(int index, PrinterInfoProto value) {
        if (value != null) {
            ensurePrinterIsMutable();
            this.printer_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrinter(PrinterInfoProto.Builder builderForValue) {
        ensurePrinterIsMutable();
        this.printer_.add((PrinterInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrinter(int index, PrinterInfoProto.Builder builderForValue) {
        ensurePrinterIsMutable();
        this.printer_.add(index, (PrinterInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPrinter(Iterable<? extends PrinterInfoProto> values) {
        ensurePrinterIsMutable();
        AbstractMessageLite.addAll(values, this.printer_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrinter() {
        this.printer_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePrinter(int index) {
        ensurePrinterIsMutable();
        this.printer_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.isDestroyed_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isPrinterDiscoveryInProgress_);
        }
        for (int i = 0; i < this.printerDiscoveryObservers_.size(); i++) {
            output.writeString(3, this.printerDiscoveryObservers_.get(i));
        }
        for (int i2 = 0; i2 < this.discoveryRequests_.size(); i2++) {
            output.writeString(4, this.discoveryRequests_.get(i2));
        }
        for (int i3 = 0; i3 < this.trackedPrinterRequests_.size(); i3++) {
            output.writeMessage(5, this.trackedPrinterRequests_.get(i3));
        }
        for (int i4 = 0; i4 < this.printer_.size(); i4++) {
            output.writeMessage(6, this.printer_.get(i4));
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isDestroyed_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isPrinterDiscoveryInProgress_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.printerDiscoveryObservers_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.printerDiscoveryObservers_.get(i));
        }
        int size3 = size2 + dataSize + (getPrinterDiscoveryObserversList().size() * 1);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.discoveryRequests_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeStringSizeNoTag(this.discoveryRequests_.get(i2));
        }
        int size4 = size3 + dataSize2 + (getDiscoveryRequestsList().size() * 1);
        for (int i3 = 0; i3 < this.trackedPrinterRequests_.size(); i3++) {
            size4 += CodedOutputStream.computeMessageSize(5, this.trackedPrinterRequests_.get(i3));
        }
        for (int i4 = 0; i4 < this.printer_.size(); i4++) {
            size4 += CodedOutputStream.computeMessageSize(6, this.printer_.get(i4));
        }
        int size5 = size4 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size5;
        return size5;
    }

    public static PrinterDiscoverySessionProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrinterDiscoverySessionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrinterDiscoverySessionProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrinterDiscoverySessionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrinterDiscoverySessionProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrinterDiscoverySessionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrinterDiscoverySessionProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrinterDiscoverySessionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrinterDiscoverySessionProto parseFrom(InputStream input) throws IOException {
        return (PrinterDiscoverySessionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterDiscoverySessionProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterDiscoverySessionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrinterDiscoverySessionProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrinterDiscoverySessionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterDiscoverySessionProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterDiscoverySessionProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrinterDiscoverySessionProto parseFrom(CodedInputStream input) throws IOException {
        return (PrinterDiscoverySessionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrinterDiscoverySessionProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrinterDiscoverySessionProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrinterDiscoverySessionProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrinterDiscoverySessionProto, Builder> implements PrinterDiscoverySessionProtoOrBuilder {
        private Builder() {
            super(PrinterDiscoverySessionProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public boolean hasIsDestroyed() {
            return ((PrinterDiscoverySessionProto) this.instance).hasIsDestroyed();
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public boolean getIsDestroyed() {
            return ((PrinterDiscoverySessionProto) this.instance).getIsDestroyed();
        }

        public Builder setIsDestroyed(boolean value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).setIsDestroyed(value);
            return this;
        }

        public Builder clearIsDestroyed() {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).clearIsDestroyed();
            return this;
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public boolean hasIsPrinterDiscoveryInProgress() {
            return ((PrinterDiscoverySessionProto) this.instance).hasIsPrinterDiscoveryInProgress();
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public boolean getIsPrinterDiscoveryInProgress() {
            return ((PrinterDiscoverySessionProto) this.instance).getIsPrinterDiscoveryInProgress();
        }

        public Builder setIsPrinterDiscoveryInProgress(boolean value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).setIsPrinterDiscoveryInProgress(value);
            return this;
        }

        public Builder clearIsPrinterDiscoveryInProgress() {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).clearIsPrinterDiscoveryInProgress();
            return this;
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public List<String> getPrinterDiscoveryObserversList() {
            return Collections.unmodifiableList(((PrinterDiscoverySessionProto) this.instance).getPrinterDiscoveryObserversList());
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public int getPrinterDiscoveryObserversCount() {
            return ((PrinterDiscoverySessionProto) this.instance).getPrinterDiscoveryObserversCount();
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public String getPrinterDiscoveryObservers(int index) {
            return ((PrinterDiscoverySessionProto) this.instance).getPrinterDiscoveryObservers(index);
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public ByteString getPrinterDiscoveryObserversBytes(int index) {
            return ((PrinterDiscoverySessionProto) this.instance).getPrinterDiscoveryObserversBytes(index);
        }

        public Builder setPrinterDiscoveryObservers(int index, String value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).setPrinterDiscoveryObservers(index, value);
            return this;
        }

        public Builder addPrinterDiscoveryObservers(String value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addPrinterDiscoveryObservers(value);
            return this;
        }

        public Builder addAllPrinterDiscoveryObservers(Iterable<String> values) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addAllPrinterDiscoveryObservers(values);
            return this;
        }

        public Builder clearPrinterDiscoveryObservers() {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).clearPrinterDiscoveryObservers();
            return this;
        }

        public Builder addPrinterDiscoveryObserversBytes(ByteString value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addPrinterDiscoveryObserversBytes(value);
            return this;
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public List<String> getDiscoveryRequestsList() {
            return Collections.unmodifiableList(((PrinterDiscoverySessionProto) this.instance).getDiscoveryRequestsList());
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public int getDiscoveryRequestsCount() {
            return ((PrinterDiscoverySessionProto) this.instance).getDiscoveryRequestsCount();
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public String getDiscoveryRequests(int index) {
            return ((PrinterDiscoverySessionProto) this.instance).getDiscoveryRequests(index);
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public ByteString getDiscoveryRequestsBytes(int index) {
            return ((PrinterDiscoverySessionProto) this.instance).getDiscoveryRequestsBytes(index);
        }

        public Builder setDiscoveryRequests(int index, String value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).setDiscoveryRequests(index, value);
            return this;
        }

        public Builder addDiscoveryRequests(String value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addDiscoveryRequests(value);
            return this;
        }

        public Builder addAllDiscoveryRequests(Iterable<String> values) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addAllDiscoveryRequests(values);
            return this;
        }

        public Builder clearDiscoveryRequests() {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).clearDiscoveryRequests();
            return this;
        }

        public Builder addDiscoveryRequestsBytes(ByteString value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addDiscoveryRequestsBytes(value);
            return this;
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public List<PrinterIdProto> getTrackedPrinterRequestsList() {
            return Collections.unmodifiableList(((PrinterDiscoverySessionProto) this.instance).getTrackedPrinterRequestsList());
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public int getTrackedPrinterRequestsCount() {
            return ((PrinterDiscoverySessionProto) this.instance).getTrackedPrinterRequestsCount();
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public PrinterIdProto getTrackedPrinterRequests(int index) {
            return ((PrinterDiscoverySessionProto) this.instance).getTrackedPrinterRequests(index);
        }

        public Builder setTrackedPrinterRequests(int index, PrinterIdProto value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).setTrackedPrinterRequests((PrinterDiscoverySessionProto) index, (int) value);
            return this;
        }

        public Builder setTrackedPrinterRequests(int index, PrinterIdProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).setTrackedPrinterRequests((PrinterDiscoverySessionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTrackedPrinterRequests(PrinterIdProto value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addTrackedPrinterRequests((PrinterDiscoverySessionProto) value);
            return this;
        }

        public Builder addTrackedPrinterRequests(int index, PrinterIdProto value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addTrackedPrinterRequests((PrinterDiscoverySessionProto) index, (int) value);
            return this;
        }

        public Builder addTrackedPrinterRequests(PrinterIdProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addTrackedPrinterRequests((PrinterDiscoverySessionProto) builderForValue);
            return this;
        }

        public Builder addTrackedPrinterRequests(int index, PrinterIdProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addTrackedPrinterRequests((PrinterDiscoverySessionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTrackedPrinterRequests(Iterable<? extends PrinterIdProto> values) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addAllTrackedPrinterRequests(values);
            return this;
        }

        public Builder clearTrackedPrinterRequests() {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).clearTrackedPrinterRequests();
            return this;
        }

        public Builder removeTrackedPrinterRequests(int index) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).removeTrackedPrinterRequests(index);
            return this;
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public List<PrinterInfoProto> getPrinterList() {
            return Collections.unmodifiableList(((PrinterDiscoverySessionProto) this.instance).getPrinterList());
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public int getPrinterCount() {
            return ((PrinterDiscoverySessionProto) this.instance).getPrinterCount();
        }

        @Override // android.service.print.PrinterDiscoverySessionProtoOrBuilder
        public PrinterInfoProto getPrinter(int index) {
            return ((PrinterDiscoverySessionProto) this.instance).getPrinter(index);
        }

        public Builder setPrinter(int index, PrinterInfoProto value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).setPrinter((PrinterDiscoverySessionProto) index, (int) value);
            return this;
        }

        public Builder setPrinter(int index, PrinterInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).setPrinter((PrinterDiscoverySessionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPrinter(PrinterInfoProto value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addPrinter((PrinterDiscoverySessionProto) value);
            return this;
        }

        public Builder addPrinter(int index, PrinterInfoProto value) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addPrinter((PrinterDiscoverySessionProto) index, (int) value);
            return this;
        }

        public Builder addPrinter(PrinterInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addPrinter((PrinterDiscoverySessionProto) builderForValue);
            return this;
        }

        public Builder addPrinter(int index, PrinterInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addPrinter((PrinterDiscoverySessionProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPrinter(Iterable<? extends PrinterInfoProto> values) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).addAllPrinter(values);
            return this;
        }

        public Builder clearPrinter() {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).clearPrinter();
            return this;
        }

        public Builder removePrinter(int index) {
            copyOnWrite();
            ((PrinterDiscoverySessionProto) this.instance).removePrinter(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrinterDiscoverySessionProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.printerDiscoveryObservers_.makeImmutable();
                this.discoveryRequests_.makeImmutable();
                this.trackedPrinterRequests_.makeImmutable();
                this.printer_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrinterDiscoverySessionProto other = (PrinterDiscoverySessionProto) arg1;
                this.isDestroyed_ = visitor.visitBoolean(hasIsDestroyed(), this.isDestroyed_, other.hasIsDestroyed(), other.isDestroyed_);
                this.isPrinterDiscoveryInProgress_ = visitor.visitBoolean(hasIsPrinterDiscoveryInProgress(), this.isPrinterDiscoveryInProgress_, other.hasIsPrinterDiscoveryInProgress(), other.isPrinterDiscoveryInProgress_);
                this.printerDiscoveryObservers_ = visitor.visitList(this.printerDiscoveryObservers_, other.printerDiscoveryObservers_);
                this.discoveryRequests_ = visitor.visitList(this.discoveryRequests_, other.discoveryRequests_);
                this.trackedPrinterRequests_ = visitor.visitList(this.trackedPrinterRequests_, other.trackedPrinterRequests_);
                this.printer_ = visitor.visitList(this.printer_, other.printer_);
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
                            this.isDestroyed_ = input.readBool();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.isPrinterDiscoveryInProgress_ = input.readBool();
                        } else if (tag == 26) {
                            String s = input.readString();
                            if (!this.printerDiscoveryObservers_.isModifiable()) {
                                this.printerDiscoveryObservers_ = GeneratedMessageLite.mutableCopy(this.printerDiscoveryObservers_);
                            }
                            this.printerDiscoveryObservers_.add(s);
                        } else if (tag == 34) {
                            String s2 = input.readString();
                            if (!this.discoveryRequests_.isModifiable()) {
                                this.discoveryRequests_ = GeneratedMessageLite.mutableCopy(this.discoveryRequests_);
                            }
                            this.discoveryRequests_.add(s2);
                        } else if (tag == 42) {
                            if (!this.trackedPrinterRequests_.isModifiable()) {
                                this.trackedPrinterRequests_ = GeneratedMessageLite.mutableCopy(this.trackedPrinterRequests_);
                            }
                            this.trackedPrinterRequests_.add((PrinterIdProto) input.readMessage(PrinterIdProto.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            if (!this.printer_.isModifiable()) {
                                this.printer_ = GeneratedMessageLite.mutableCopy(this.printer_);
                            }
                            this.printer_.add((PrinterInfoProto) input.readMessage(PrinterInfoProto.parser(), extensionRegistry));
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
                    synchronized (PrinterDiscoverySessionProto.class) {
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

    public static PrinterDiscoverySessionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrinterDiscoverySessionProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
