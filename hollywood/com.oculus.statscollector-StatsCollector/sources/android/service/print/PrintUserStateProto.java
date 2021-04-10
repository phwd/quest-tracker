package android.service.print;

import android.content.ComponentNameProto;
import android.content.ComponentNameProtoOrBuilder;
import android.service.print.ActivePrintServiceProto;
import android.service.print.CachedPrintJobProto;
import android.service.print.InstalledPrintServiceProto;
import android.service.print.PrintSpoolerStateProto;
import android.service.print.PrinterDiscoverySessionProto;
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

public final class PrintUserStateProto extends GeneratedMessageLite<PrintUserStateProto, Builder> implements PrintUserStateProtoOrBuilder {
    public static final int ACTIVE_SERVICES_FIELD_NUMBER = 4;
    public static final int CACHED_PRINT_JOBS_FIELD_NUMBER = 5;
    private static final PrintUserStateProto DEFAULT_INSTANCE = new PrintUserStateProto();
    public static final int DISABLED_SERVICES_FIELD_NUMBER = 3;
    public static final int DISCOVERY_SESSIONS_FIELD_NUMBER = 6;
    public static final int INSTALLED_SERVICES_FIELD_NUMBER = 2;
    private static volatile Parser<PrintUserStateProto> PARSER = null;
    public static final int PRINT_SPOOLER_STATE_FIELD_NUMBER = 7;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private Internal.ProtobufList<ActivePrintServiceProto> activeServices_ = emptyProtobufList();
    private int bitField0_;
    private Internal.ProtobufList<CachedPrintJobProto> cachedPrintJobs_ = emptyProtobufList();
    private Internal.ProtobufList<ComponentNameProto> disabledServices_ = emptyProtobufList();
    private Internal.ProtobufList<PrinterDiscoverySessionProto> discoverySessions_ = emptyProtobufList();
    private Internal.ProtobufList<InstalledPrintServiceProto> installedServices_ = emptyProtobufList();
    private PrintSpoolerStateProto printSpoolerState_;
    private int userId_ = 0;

    private PrintUserStateProto() {
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public boolean hasUserId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public int getUserId() {
        return this.userId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(int value) {
        this.bitField0_ |= 1;
        this.userId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserId() {
        this.bitField0_ &= -2;
        this.userId_ = 0;
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public List<InstalledPrintServiceProto> getInstalledServicesList() {
        return this.installedServices_;
    }

    public List<? extends InstalledPrintServiceProtoOrBuilder> getInstalledServicesOrBuilderList() {
        return this.installedServices_;
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public int getInstalledServicesCount() {
        return this.installedServices_.size();
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public InstalledPrintServiceProto getInstalledServices(int index) {
        return this.installedServices_.get(index);
    }

    public InstalledPrintServiceProtoOrBuilder getInstalledServicesOrBuilder(int index) {
        return this.installedServices_.get(index);
    }

    private void ensureInstalledServicesIsMutable() {
        if (!this.installedServices_.isModifiable()) {
            this.installedServices_ = GeneratedMessageLite.mutableCopy(this.installedServices_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInstalledServices(int index, InstalledPrintServiceProto value) {
        if (value != null) {
            ensureInstalledServicesIsMutable();
            this.installedServices_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setInstalledServices(int index, InstalledPrintServiceProto.Builder builderForValue) {
        ensureInstalledServicesIsMutable();
        this.installedServices_.set(index, (InstalledPrintServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addInstalledServices(InstalledPrintServiceProto value) {
        if (value != null) {
            ensureInstalledServicesIsMutable();
            this.installedServices_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addInstalledServices(int index, InstalledPrintServiceProto value) {
        if (value != null) {
            ensureInstalledServicesIsMutable();
            this.installedServices_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addInstalledServices(InstalledPrintServiceProto.Builder builderForValue) {
        ensureInstalledServicesIsMutable();
        this.installedServices_.add((InstalledPrintServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addInstalledServices(int index, InstalledPrintServiceProto.Builder builderForValue) {
        ensureInstalledServicesIsMutable();
        this.installedServices_.add(index, (InstalledPrintServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllInstalledServices(Iterable<? extends InstalledPrintServiceProto> values) {
        ensureInstalledServicesIsMutable();
        AbstractMessageLite.addAll(values, this.installedServices_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearInstalledServices() {
        this.installedServices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeInstalledServices(int index) {
        ensureInstalledServicesIsMutable();
        this.installedServices_.remove(index);
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public List<ComponentNameProto> getDisabledServicesList() {
        return this.disabledServices_;
    }

    public List<? extends ComponentNameProtoOrBuilder> getDisabledServicesOrBuilderList() {
        return this.disabledServices_;
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public int getDisabledServicesCount() {
        return this.disabledServices_.size();
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public ComponentNameProto getDisabledServices(int index) {
        return this.disabledServices_.get(index);
    }

    public ComponentNameProtoOrBuilder getDisabledServicesOrBuilder(int index) {
        return this.disabledServices_.get(index);
    }

    private void ensureDisabledServicesIsMutable() {
        if (!this.disabledServices_.isModifiable()) {
            this.disabledServices_ = GeneratedMessageLite.mutableCopy(this.disabledServices_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisabledServices(int index, ComponentNameProto value) {
        if (value != null) {
            ensureDisabledServicesIsMutable();
            this.disabledServices_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisabledServices(int index, ComponentNameProto.Builder builderForValue) {
        ensureDisabledServicesIsMutable();
        this.disabledServices_.set(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisabledServices(ComponentNameProto value) {
        if (value != null) {
            ensureDisabledServicesIsMutable();
            this.disabledServices_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisabledServices(int index, ComponentNameProto value) {
        if (value != null) {
            ensureDisabledServicesIsMutable();
            this.disabledServices_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisabledServices(ComponentNameProto.Builder builderForValue) {
        ensureDisabledServicesIsMutable();
        this.disabledServices_.add((ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDisabledServices(int index, ComponentNameProto.Builder builderForValue) {
        ensureDisabledServicesIsMutable();
        this.disabledServices_.add(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDisabledServices(Iterable<? extends ComponentNameProto> values) {
        ensureDisabledServicesIsMutable();
        AbstractMessageLite.addAll(values, this.disabledServices_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisabledServices() {
        this.disabledServices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDisabledServices(int index) {
        ensureDisabledServicesIsMutable();
        this.disabledServices_.remove(index);
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public List<ActivePrintServiceProto> getActiveServicesList() {
        return this.activeServices_;
    }

    public List<? extends ActivePrintServiceProtoOrBuilder> getActiveServicesOrBuilderList() {
        return this.activeServices_;
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public int getActiveServicesCount() {
        return this.activeServices_.size();
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public ActivePrintServiceProto getActiveServices(int index) {
        return this.activeServices_.get(index);
    }

    public ActivePrintServiceProtoOrBuilder getActiveServicesOrBuilder(int index) {
        return this.activeServices_.get(index);
    }

    private void ensureActiveServicesIsMutable() {
        if (!this.activeServices_.isModifiable()) {
            this.activeServices_ = GeneratedMessageLite.mutableCopy(this.activeServices_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveServices(int index, ActivePrintServiceProto value) {
        if (value != null) {
            ensureActiveServicesIsMutable();
            this.activeServices_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveServices(int index, ActivePrintServiceProto.Builder builderForValue) {
        ensureActiveServicesIsMutable();
        this.activeServices_.set(index, (ActivePrintServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveServices(ActivePrintServiceProto value) {
        if (value != null) {
            ensureActiveServicesIsMutable();
            this.activeServices_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveServices(int index, ActivePrintServiceProto value) {
        if (value != null) {
            ensureActiveServicesIsMutable();
            this.activeServices_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveServices(ActivePrintServiceProto.Builder builderForValue) {
        ensureActiveServicesIsMutable();
        this.activeServices_.add((ActivePrintServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addActiveServices(int index, ActivePrintServiceProto.Builder builderForValue) {
        ensureActiveServicesIsMutable();
        this.activeServices_.add(index, (ActivePrintServiceProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllActiveServices(Iterable<? extends ActivePrintServiceProto> values) {
        ensureActiveServicesIsMutable();
        AbstractMessageLite.addAll(values, this.activeServices_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveServices() {
        this.activeServices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeActiveServices(int index) {
        ensureActiveServicesIsMutable();
        this.activeServices_.remove(index);
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public List<CachedPrintJobProto> getCachedPrintJobsList() {
        return this.cachedPrintJobs_;
    }

    public List<? extends CachedPrintJobProtoOrBuilder> getCachedPrintJobsOrBuilderList() {
        return this.cachedPrintJobs_;
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public int getCachedPrintJobsCount() {
        return this.cachedPrintJobs_.size();
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public CachedPrintJobProto getCachedPrintJobs(int index) {
        return this.cachedPrintJobs_.get(index);
    }

    public CachedPrintJobProtoOrBuilder getCachedPrintJobsOrBuilder(int index) {
        return this.cachedPrintJobs_.get(index);
    }

    private void ensureCachedPrintJobsIsMutable() {
        if (!this.cachedPrintJobs_.isModifiable()) {
            this.cachedPrintJobs_ = GeneratedMessageLite.mutableCopy(this.cachedPrintJobs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCachedPrintJobs(int index, CachedPrintJobProto value) {
        if (value != null) {
            ensureCachedPrintJobsIsMutable();
            this.cachedPrintJobs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCachedPrintJobs(int index, CachedPrintJobProto.Builder builderForValue) {
        ensureCachedPrintJobsIsMutable();
        this.cachedPrintJobs_.set(index, (CachedPrintJobProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCachedPrintJobs(CachedPrintJobProto value) {
        if (value != null) {
            ensureCachedPrintJobsIsMutable();
            this.cachedPrintJobs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCachedPrintJobs(int index, CachedPrintJobProto value) {
        if (value != null) {
            ensureCachedPrintJobsIsMutable();
            this.cachedPrintJobs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCachedPrintJobs(CachedPrintJobProto.Builder builderForValue) {
        ensureCachedPrintJobsIsMutable();
        this.cachedPrintJobs_.add((CachedPrintJobProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addCachedPrintJobs(int index, CachedPrintJobProto.Builder builderForValue) {
        ensureCachedPrintJobsIsMutable();
        this.cachedPrintJobs_.add(index, (CachedPrintJobProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllCachedPrintJobs(Iterable<? extends CachedPrintJobProto> values) {
        ensureCachedPrintJobsIsMutable();
        AbstractMessageLite.addAll(values, this.cachedPrintJobs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCachedPrintJobs() {
        this.cachedPrintJobs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeCachedPrintJobs(int index) {
        ensureCachedPrintJobsIsMutable();
        this.cachedPrintJobs_.remove(index);
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public List<PrinterDiscoverySessionProto> getDiscoverySessionsList() {
        return this.discoverySessions_;
    }

    public List<? extends PrinterDiscoverySessionProtoOrBuilder> getDiscoverySessionsOrBuilderList() {
        return this.discoverySessions_;
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public int getDiscoverySessionsCount() {
        return this.discoverySessions_.size();
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public PrinterDiscoverySessionProto getDiscoverySessions(int index) {
        return this.discoverySessions_.get(index);
    }

    public PrinterDiscoverySessionProtoOrBuilder getDiscoverySessionsOrBuilder(int index) {
        return this.discoverySessions_.get(index);
    }

    private void ensureDiscoverySessionsIsMutable() {
        if (!this.discoverySessions_.isModifiable()) {
            this.discoverySessions_ = GeneratedMessageLite.mutableCopy(this.discoverySessions_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDiscoverySessions(int index, PrinterDiscoverySessionProto value) {
        if (value != null) {
            ensureDiscoverySessionsIsMutable();
            this.discoverySessions_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDiscoverySessions(int index, PrinterDiscoverySessionProto.Builder builderForValue) {
        ensureDiscoverySessionsIsMutable();
        this.discoverySessions_.set(index, (PrinterDiscoverySessionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDiscoverySessions(PrinterDiscoverySessionProto value) {
        if (value != null) {
            ensureDiscoverySessionsIsMutable();
            this.discoverySessions_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDiscoverySessions(int index, PrinterDiscoverySessionProto value) {
        if (value != null) {
            ensureDiscoverySessionsIsMutable();
            this.discoverySessions_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDiscoverySessions(PrinterDiscoverySessionProto.Builder builderForValue) {
        ensureDiscoverySessionsIsMutable();
        this.discoverySessions_.add((PrinterDiscoverySessionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDiscoverySessions(int index, PrinterDiscoverySessionProto.Builder builderForValue) {
        ensureDiscoverySessionsIsMutable();
        this.discoverySessions_.add(index, (PrinterDiscoverySessionProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDiscoverySessions(Iterable<? extends PrinterDiscoverySessionProto> values) {
        ensureDiscoverySessionsIsMutable();
        AbstractMessageLite.addAll(values, this.discoverySessions_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDiscoverySessions() {
        this.discoverySessions_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDiscoverySessions(int index) {
        ensureDiscoverySessionsIsMutable();
        this.discoverySessions_.remove(index);
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public boolean hasPrintSpoolerState() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PrintUserStateProtoOrBuilder
    public PrintSpoolerStateProto getPrintSpoolerState() {
        PrintSpoolerStateProto printSpoolerStateProto = this.printSpoolerState_;
        return printSpoolerStateProto == null ? PrintSpoolerStateProto.getDefaultInstance() : printSpoolerStateProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintSpoolerState(PrintSpoolerStateProto value) {
        if (value != null) {
            this.printSpoolerState_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintSpoolerState(PrintSpoolerStateProto.Builder builderForValue) {
        this.printSpoolerState_ = (PrintSpoolerStateProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePrintSpoolerState(PrintSpoolerStateProto value) {
        PrintSpoolerStateProto printSpoolerStateProto = this.printSpoolerState_;
        if (printSpoolerStateProto == null || printSpoolerStateProto == PrintSpoolerStateProto.getDefaultInstance()) {
            this.printSpoolerState_ = value;
        } else {
            this.printSpoolerState_ = (PrintSpoolerStateProto) ((PrintSpoolerStateProto.Builder) PrintSpoolerStateProto.newBuilder(this.printSpoolerState_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrintSpoolerState() {
        this.printSpoolerState_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.userId_);
        }
        for (int i = 0; i < this.installedServices_.size(); i++) {
            output.writeMessage(2, this.installedServices_.get(i));
        }
        for (int i2 = 0; i2 < this.disabledServices_.size(); i2++) {
            output.writeMessage(3, this.disabledServices_.get(i2));
        }
        for (int i3 = 0; i3 < this.activeServices_.size(); i3++) {
            output.writeMessage(4, this.activeServices_.get(i3));
        }
        for (int i4 = 0; i4 < this.cachedPrintJobs_.size(); i4++) {
            output.writeMessage(5, this.cachedPrintJobs_.get(i4));
        }
        for (int i5 = 0; i5 < this.discoverySessions_.size(); i5++) {
            output.writeMessage(6, this.discoverySessions_.get(i5));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(7, getPrintSpoolerState());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.userId_);
        }
        for (int i = 0; i < this.installedServices_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.installedServices_.get(i));
        }
        for (int i2 = 0; i2 < this.disabledServices_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.disabledServices_.get(i2));
        }
        for (int i3 = 0; i3 < this.activeServices_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.activeServices_.get(i3));
        }
        for (int i4 = 0; i4 < this.cachedPrintJobs_.size(); i4++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.cachedPrintJobs_.get(i4));
        }
        for (int i5 = 0; i5 < this.discoverySessions_.size(); i5++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.discoverySessions_.get(i5));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(7, getPrintSpoolerState());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PrintUserStateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrintUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintUserStateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintUserStateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrintUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintUserStateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintUserStateProto parseFrom(InputStream input) throws IOException {
        return (PrintUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintUserStateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintUserStateProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrintUserStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintUserStateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintUserStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintUserStateProto parseFrom(CodedInputStream input) throws IOException {
        return (PrintUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintUserStateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintUserStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrintUserStateProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrintUserStateProto, Builder> implements PrintUserStateProtoOrBuilder {
        private Builder() {
            super(PrintUserStateProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public boolean hasUserId() {
            return ((PrintUserStateProto) this.instance).hasUserId();
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public int getUserId() {
            return ((PrintUserStateProto) this.instance).getUserId();
        }

        public Builder setUserId(int value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setUserId(value);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).clearUserId();
            return this;
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public List<InstalledPrintServiceProto> getInstalledServicesList() {
            return Collections.unmodifiableList(((PrintUserStateProto) this.instance).getInstalledServicesList());
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public int getInstalledServicesCount() {
            return ((PrintUserStateProto) this.instance).getInstalledServicesCount();
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public InstalledPrintServiceProto getInstalledServices(int index) {
            return ((PrintUserStateProto) this.instance).getInstalledServices(index);
        }

        public Builder setInstalledServices(int index, InstalledPrintServiceProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setInstalledServices((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder setInstalledServices(int index, InstalledPrintServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setInstalledServices((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addInstalledServices(InstalledPrintServiceProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addInstalledServices((PrintUserStateProto) value);
            return this;
        }

        public Builder addInstalledServices(int index, InstalledPrintServiceProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addInstalledServices((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder addInstalledServices(InstalledPrintServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addInstalledServices((PrintUserStateProto) builderForValue);
            return this;
        }

        public Builder addInstalledServices(int index, InstalledPrintServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addInstalledServices((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllInstalledServices(Iterable<? extends InstalledPrintServiceProto> values) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addAllInstalledServices(values);
            return this;
        }

        public Builder clearInstalledServices() {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).clearInstalledServices();
            return this;
        }

        public Builder removeInstalledServices(int index) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).removeInstalledServices(index);
            return this;
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public List<ComponentNameProto> getDisabledServicesList() {
            return Collections.unmodifiableList(((PrintUserStateProto) this.instance).getDisabledServicesList());
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public int getDisabledServicesCount() {
            return ((PrintUserStateProto) this.instance).getDisabledServicesCount();
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public ComponentNameProto getDisabledServices(int index) {
            return ((PrintUserStateProto) this.instance).getDisabledServices(index);
        }

        public Builder setDisabledServices(int index, ComponentNameProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setDisabledServices((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder setDisabledServices(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setDisabledServices((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDisabledServices(ComponentNameProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addDisabledServices((PrintUserStateProto) value);
            return this;
        }

        public Builder addDisabledServices(int index, ComponentNameProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addDisabledServices((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder addDisabledServices(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addDisabledServices((PrintUserStateProto) builderForValue);
            return this;
        }

        public Builder addDisabledServices(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addDisabledServices((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDisabledServices(Iterable<? extends ComponentNameProto> values) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addAllDisabledServices(values);
            return this;
        }

        public Builder clearDisabledServices() {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).clearDisabledServices();
            return this;
        }

        public Builder removeDisabledServices(int index) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).removeDisabledServices(index);
            return this;
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public List<ActivePrintServiceProto> getActiveServicesList() {
            return Collections.unmodifiableList(((PrintUserStateProto) this.instance).getActiveServicesList());
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public int getActiveServicesCount() {
            return ((PrintUserStateProto) this.instance).getActiveServicesCount();
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public ActivePrintServiceProto getActiveServices(int index) {
            return ((PrintUserStateProto) this.instance).getActiveServices(index);
        }

        public Builder setActiveServices(int index, ActivePrintServiceProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setActiveServices((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder setActiveServices(int index, ActivePrintServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setActiveServices((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addActiveServices(ActivePrintServiceProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addActiveServices((PrintUserStateProto) value);
            return this;
        }

        public Builder addActiveServices(int index, ActivePrintServiceProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addActiveServices((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder addActiveServices(ActivePrintServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addActiveServices((PrintUserStateProto) builderForValue);
            return this;
        }

        public Builder addActiveServices(int index, ActivePrintServiceProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addActiveServices((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllActiveServices(Iterable<? extends ActivePrintServiceProto> values) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addAllActiveServices(values);
            return this;
        }

        public Builder clearActiveServices() {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).clearActiveServices();
            return this;
        }

        public Builder removeActiveServices(int index) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).removeActiveServices(index);
            return this;
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public List<CachedPrintJobProto> getCachedPrintJobsList() {
            return Collections.unmodifiableList(((PrintUserStateProto) this.instance).getCachedPrintJobsList());
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public int getCachedPrintJobsCount() {
            return ((PrintUserStateProto) this.instance).getCachedPrintJobsCount();
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public CachedPrintJobProto getCachedPrintJobs(int index) {
            return ((PrintUserStateProto) this.instance).getCachedPrintJobs(index);
        }

        public Builder setCachedPrintJobs(int index, CachedPrintJobProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setCachedPrintJobs((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder setCachedPrintJobs(int index, CachedPrintJobProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setCachedPrintJobs((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addCachedPrintJobs(CachedPrintJobProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addCachedPrintJobs((PrintUserStateProto) value);
            return this;
        }

        public Builder addCachedPrintJobs(int index, CachedPrintJobProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addCachedPrintJobs((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder addCachedPrintJobs(CachedPrintJobProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addCachedPrintJobs((PrintUserStateProto) builderForValue);
            return this;
        }

        public Builder addCachedPrintJobs(int index, CachedPrintJobProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addCachedPrintJobs((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllCachedPrintJobs(Iterable<? extends CachedPrintJobProto> values) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addAllCachedPrintJobs(values);
            return this;
        }

        public Builder clearCachedPrintJobs() {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).clearCachedPrintJobs();
            return this;
        }

        public Builder removeCachedPrintJobs(int index) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).removeCachedPrintJobs(index);
            return this;
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public List<PrinterDiscoverySessionProto> getDiscoverySessionsList() {
            return Collections.unmodifiableList(((PrintUserStateProto) this.instance).getDiscoverySessionsList());
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public int getDiscoverySessionsCount() {
            return ((PrintUserStateProto) this.instance).getDiscoverySessionsCount();
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public PrinterDiscoverySessionProto getDiscoverySessions(int index) {
            return ((PrintUserStateProto) this.instance).getDiscoverySessions(index);
        }

        public Builder setDiscoverySessions(int index, PrinterDiscoverySessionProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setDiscoverySessions((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder setDiscoverySessions(int index, PrinterDiscoverySessionProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setDiscoverySessions((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addDiscoverySessions(PrinterDiscoverySessionProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addDiscoverySessions((PrintUserStateProto) value);
            return this;
        }

        public Builder addDiscoverySessions(int index, PrinterDiscoverySessionProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addDiscoverySessions((PrintUserStateProto) index, (int) value);
            return this;
        }

        public Builder addDiscoverySessions(PrinterDiscoverySessionProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addDiscoverySessions((PrintUserStateProto) builderForValue);
            return this;
        }

        public Builder addDiscoverySessions(int index, PrinterDiscoverySessionProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addDiscoverySessions((PrintUserStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllDiscoverySessions(Iterable<? extends PrinterDiscoverySessionProto> values) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).addAllDiscoverySessions(values);
            return this;
        }

        public Builder clearDiscoverySessions() {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).clearDiscoverySessions();
            return this;
        }

        public Builder removeDiscoverySessions(int index) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).removeDiscoverySessions(index);
            return this;
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public boolean hasPrintSpoolerState() {
            return ((PrintUserStateProto) this.instance).hasPrintSpoolerState();
        }

        @Override // android.service.print.PrintUserStateProtoOrBuilder
        public PrintSpoolerStateProto getPrintSpoolerState() {
            return ((PrintUserStateProto) this.instance).getPrintSpoolerState();
        }

        public Builder setPrintSpoolerState(PrintSpoolerStateProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setPrintSpoolerState((PrintUserStateProto) value);
            return this;
        }

        public Builder setPrintSpoolerState(PrintSpoolerStateProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).setPrintSpoolerState((PrintUserStateProto) builderForValue);
            return this;
        }

        public Builder mergePrintSpoolerState(PrintSpoolerStateProto value) {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).mergePrintSpoolerState(value);
            return this;
        }

        public Builder clearPrintSpoolerState() {
            copyOnWrite();
            ((PrintUserStateProto) this.instance).clearPrintSpoolerState();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrintUserStateProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.installedServices_.makeImmutable();
                this.disabledServices_.makeImmutable();
                this.activeServices_.makeImmutable();
                this.cachedPrintJobs_.makeImmutable();
                this.discoverySessions_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrintUserStateProto other = (PrintUserStateProto) arg1;
                this.userId_ = visitor.visitInt(hasUserId(), this.userId_, other.hasUserId(), other.userId_);
                this.installedServices_ = visitor.visitList(this.installedServices_, other.installedServices_);
                this.disabledServices_ = visitor.visitList(this.disabledServices_, other.disabledServices_);
                this.activeServices_ = visitor.visitList(this.activeServices_, other.activeServices_);
                this.cachedPrintJobs_ = visitor.visitList(this.cachedPrintJobs_, other.cachedPrintJobs_);
                this.discoverySessions_ = visitor.visitList(this.discoverySessions_, other.discoverySessions_);
                this.printSpoolerState_ = (PrintSpoolerStateProto) visitor.visitMessage(this.printSpoolerState_, other.printSpoolerState_);
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
                            this.userId_ = input.readInt32();
                        } else if (tag == 18) {
                            if (!this.installedServices_.isModifiable()) {
                                this.installedServices_ = GeneratedMessageLite.mutableCopy(this.installedServices_);
                            }
                            this.installedServices_.add((InstalledPrintServiceProto) input.readMessage(InstalledPrintServiceProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.disabledServices_.isModifiable()) {
                                this.disabledServices_ = GeneratedMessageLite.mutableCopy(this.disabledServices_);
                            }
                            this.disabledServices_.add((ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            if (!this.activeServices_.isModifiable()) {
                                this.activeServices_ = GeneratedMessageLite.mutableCopy(this.activeServices_);
                            }
                            this.activeServices_.add((ActivePrintServiceProto) input.readMessage(ActivePrintServiceProto.parser(), extensionRegistry));
                        } else if (tag == 42) {
                            if (!this.cachedPrintJobs_.isModifiable()) {
                                this.cachedPrintJobs_ = GeneratedMessageLite.mutableCopy(this.cachedPrintJobs_);
                            }
                            this.cachedPrintJobs_.add((CachedPrintJobProto) input.readMessage(CachedPrintJobProto.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            if (!this.discoverySessions_.isModifiable()) {
                                this.discoverySessions_ = GeneratedMessageLite.mutableCopy(this.discoverySessions_);
                            }
                            this.discoverySessions_.add((PrinterDiscoverySessionProto) input.readMessage(PrinterDiscoverySessionProto.parser(), extensionRegistry));
                        } else if (tag == 58) {
                            PrintSpoolerStateProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = (PrintSpoolerStateProto.Builder) this.printSpoolerState_.toBuilder();
                            }
                            this.printSpoolerState_ = (PrintSpoolerStateProto) input.readMessage(PrintSpoolerStateProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.printSpoolerState_);
                                this.printSpoolerState_ = (PrintSpoolerStateProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 2;
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
                    synchronized (PrintUserStateProto.class) {
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

    public static PrintUserStateProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrintUserStateProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
