package android.service.print;

import android.content.ComponentNameProto;
import android.service.print.PrinterIdProto;
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

public final class ActivePrintServiceProto extends GeneratedMessageLite<ActivePrintServiceProto, Builder> implements ActivePrintServiceProtoOrBuilder {
    public static final int COMPONENT_NAME_FIELD_NUMBER = 1;
    private static final ActivePrintServiceProto DEFAULT_INSTANCE = new ActivePrintServiceProto();
    public static final int HAS_ACTIVE_PRINT_JOBS_FIELD_NUMBER = 5;
    public static final int HAS_DISCOVERY_SESSION_FIELD_NUMBER = 4;
    public static final int IS_BOUND_FIELD_NUMBER = 3;
    public static final int IS_DESTROYED_FIELD_NUMBER = 2;
    public static final int IS_DISCOVERING_PRINTERS_FIELD_NUMBER = 6;
    private static volatile Parser<ActivePrintServiceProto> PARSER = null;
    public static final int TRACKED_PRINTERS_FIELD_NUMBER = 7;
    private int bitField0_;
    private ComponentNameProto componentName_;
    private boolean hasActivePrintJobs_ = false;
    private boolean hasDiscoverySession_ = false;
    private boolean isBound_ = false;
    private boolean isDestroyed_ = false;
    private boolean isDiscoveringPrinters_ = false;
    private Internal.ProtobufList<PrinterIdProto> trackedPrinters_ = emptyProtobufList();

    private ActivePrintServiceProto() {
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean hasComponentName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public ComponentNameProto getComponentName() {
        ComponentNameProto componentNameProto = this.componentName_;
        return componentNameProto == null ? ComponentNameProto.getDefaultInstance() : componentNameProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponentName(ComponentNameProto value) {
        if (value != null) {
            this.componentName_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setComponentName(ComponentNameProto.Builder builderForValue) {
        this.componentName_ = (ComponentNameProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeComponentName(ComponentNameProto value) {
        ComponentNameProto componentNameProto = this.componentName_;
        if (componentNameProto == null || componentNameProto == ComponentNameProto.getDefaultInstance()) {
            this.componentName_ = value;
        } else {
            this.componentName_ = (ComponentNameProto) ((ComponentNameProto.Builder) ComponentNameProto.newBuilder(this.componentName_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearComponentName() {
        this.componentName_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean hasIsDestroyed() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean getIsDestroyed() {
        return this.isDestroyed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDestroyed(boolean value) {
        this.bitField0_ |= 2;
        this.isDestroyed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDestroyed() {
        this.bitField0_ &= -3;
        this.isDestroyed_ = false;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean hasIsBound() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean getIsBound() {
        return this.isBound_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsBound(boolean value) {
        this.bitField0_ |= 4;
        this.isBound_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsBound() {
        this.bitField0_ &= -5;
        this.isBound_ = false;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean hasHasDiscoverySession() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean getHasDiscoverySession() {
        return this.hasDiscoverySession_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasDiscoverySession(boolean value) {
        this.bitField0_ |= 8;
        this.hasDiscoverySession_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasDiscoverySession() {
        this.bitField0_ &= -9;
        this.hasDiscoverySession_ = false;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean hasHasActivePrintJobs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean getHasActivePrintJobs() {
        return this.hasActivePrintJobs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasActivePrintJobs(boolean value) {
        this.bitField0_ |= 16;
        this.hasActivePrintJobs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasActivePrintJobs() {
        this.bitField0_ &= -17;
        this.hasActivePrintJobs_ = false;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean hasIsDiscoveringPrinters() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public boolean getIsDiscoveringPrinters() {
        return this.isDiscoveringPrinters_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDiscoveringPrinters(boolean value) {
        this.bitField0_ |= 32;
        this.isDiscoveringPrinters_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDiscoveringPrinters() {
        this.bitField0_ &= -33;
        this.isDiscoveringPrinters_ = false;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public List<PrinterIdProto> getTrackedPrintersList() {
        return this.trackedPrinters_;
    }

    public List<? extends PrinterIdProtoOrBuilder> getTrackedPrintersOrBuilderList() {
        return this.trackedPrinters_;
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public int getTrackedPrintersCount() {
        return this.trackedPrinters_.size();
    }

    @Override // android.service.print.ActivePrintServiceProtoOrBuilder
    public PrinterIdProto getTrackedPrinters(int index) {
        return this.trackedPrinters_.get(index);
    }

    public PrinterIdProtoOrBuilder getTrackedPrintersOrBuilder(int index) {
        return this.trackedPrinters_.get(index);
    }

    private void ensureTrackedPrintersIsMutable() {
        if (!this.trackedPrinters_.isModifiable()) {
            this.trackedPrinters_ = GeneratedMessageLite.mutableCopy(this.trackedPrinters_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTrackedPrinters(int index, PrinterIdProto value) {
        if (value != null) {
            ensureTrackedPrintersIsMutable();
            this.trackedPrinters_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTrackedPrinters(int index, PrinterIdProto.Builder builderForValue) {
        ensureTrackedPrintersIsMutable();
        this.trackedPrinters_.set(index, (PrinterIdProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackedPrinters(PrinterIdProto value) {
        if (value != null) {
            ensureTrackedPrintersIsMutable();
            this.trackedPrinters_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackedPrinters(int index, PrinterIdProto value) {
        if (value != null) {
            ensureTrackedPrintersIsMutable();
            this.trackedPrinters_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackedPrinters(PrinterIdProto.Builder builderForValue) {
        ensureTrackedPrintersIsMutable();
        this.trackedPrinters_.add((PrinterIdProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addTrackedPrinters(int index, PrinterIdProto.Builder builderForValue) {
        ensureTrackedPrintersIsMutable();
        this.trackedPrinters_.add(index, (PrinterIdProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllTrackedPrinters(Iterable<? extends PrinterIdProto> values) {
        ensureTrackedPrintersIsMutable();
        AbstractMessageLite.addAll(values, this.trackedPrinters_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTrackedPrinters() {
        this.trackedPrinters_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeTrackedPrinters(int index) {
        ensureTrackedPrintersIsMutable();
        this.trackedPrinters_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getComponentName());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isDestroyed_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(3, this.isBound_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.hasDiscoverySession_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.hasActivePrintJobs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.isDiscoveringPrinters_);
        }
        for (int i = 0; i < this.trackedPrinters_.size(); i++) {
            output.writeMessage(7, this.trackedPrinters_.get(i));
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getComponentName());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isDestroyed_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(3, this.isBound_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.hasDiscoverySession_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.hasActivePrintJobs_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.isDiscoveringPrinters_);
        }
        for (int i = 0; i < this.trackedPrinters_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(7, this.trackedPrinters_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivePrintServiceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivePrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivePrintServiceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivePrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivePrintServiceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivePrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivePrintServiceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivePrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivePrintServiceProto parseFrom(InputStream input) throws IOException {
        return (ActivePrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivePrintServiceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivePrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivePrintServiceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivePrintServiceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivePrintServiceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivePrintServiceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivePrintServiceProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivePrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivePrintServiceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivePrintServiceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivePrintServiceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivePrintServiceProto, Builder> implements ActivePrintServiceProtoOrBuilder {
        private Builder() {
            super(ActivePrintServiceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean hasComponentName() {
            return ((ActivePrintServiceProto) this.instance).hasComponentName();
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public ComponentNameProto getComponentName() {
            return ((ActivePrintServiceProto) this.instance).getComponentName();
        }

        public Builder setComponentName(ComponentNameProto value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setComponentName((ActivePrintServiceProto) value);
            return this;
        }

        public Builder setComponentName(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setComponentName((ActivePrintServiceProto) builderForValue);
            return this;
        }

        public Builder mergeComponentName(ComponentNameProto value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).mergeComponentName(value);
            return this;
        }

        public Builder clearComponentName() {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).clearComponentName();
            return this;
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean hasIsDestroyed() {
            return ((ActivePrintServiceProto) this.instance).hasIsDestroyed();
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean getIsDestroyed() {
            return ((ActivePrintServiceProto) this.instance).getIsDestroyed();
        }

        public Builder setIsDestroyed(boolean value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setIsDestroyed(value);
            return this;
        }

        public Builder clearIsDestroyed() {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).clearIsDestroyed();
            return this;
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean hasIsBound() {
            return ((ActivePrintServiceProto) this.instance).hasIsBound();
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean getIsBound() {
            return ((ActivePrintServiceProto) this.instance).getIsBound();
        }

        public Builder setIsBound(boolean value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setIsBound(value);
            return this;
        }

        public Builder clearIsBound() {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).clearIsBound();
            return this;
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean hasHasDiscoverySession() {
            return ((ActivePrintServiceProto) this.instance).hasHasDiscoverySession();
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean getHasDiscoverySession() {
            return ((ActivePrintServiceProto) this.instance).getHasDiscoverySession();
        }

        public Builder setHasDiscoverySession(boolean value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setHasDiscoverySession(value);
            return this;
        }

        public Builder clearHasDiscoverySession() {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).clearHasDiscoverySession();
            return this;
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean hasHasActivePrintJobs() {
            return ((ActivePrintServiceProto) this.instance).hasHasActivePrintJobs();
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean getHasActivePrintJobs() {
            return ((ActivePrintServiceProto) this.instance).getHasActivePrintJobs();
        }

        public Builder setHasActivePrintJobs(boolean value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setHasActivePrintJobs(value);
            return this;
        }

        public Builder clearHasActivePrintJobs() {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).clearHasActivePrintJobs();
            return this;
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean hasIsDiscoveringPrinters() {
            return ((ActivePrintServiceProto) this.instance).hasIsDiscoveringPrinters();
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public boolean getIsDiscoveringPrinters() {
            return ((ActivePrintServiceProto) this.instance).getIsDiscoveringPrinters();
        }

        public Builder setIsDiscoveringPrinters(boolean value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setIsDiscoveringPrinters(value);
            return this;
        }

        public Builder clearIsDiscoveringPrinters() {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).clearIsDiscoveringPrinters();
            return this;
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public List<PrinterIdProto> getTrackedPrintersList() {
            return Collections.unmodifiableList(((ActivePrintServiceProto) this.instance).getTrackedPrintersList());
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public int getTrackedPrintersCount() {
            return ((ActivePrintServiceProto) this.instance).getTrackedPrintersCount();
        }

        @Override // android.service.print.ActivePrintServiceProtoOrBuilder
        public PrinterIdProto getTrackedPrinters(int index) {
            return ((ActivePrintServiceProto) this.instance).getTrackedPrinters(index);
        }

        public Builder setTrackedPrinters(int index, PrinterIdProto value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setTrackedPrinters((ActivePrintServiceProto) index, (int) value);
            return this;
        }

        public Builder setTrackedPrinters(int index, PrinterIdProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).setTrackedPrinters((ActivePrintServiceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addTrackedPrinters(PrinterIdProto value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).addTrackedPrinters((ActivePrintServiceProto) value);
            return this;
        }

        public Builder addTrackedPrinters(int index, PrinterIdProto value) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).addTrackedPrinters((ActivePrintServiceProto) index, (int) value);
            return this;
        }

        public Builder addTrackedPrinters(PrinterIdProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).addTrackedPrinters((ActivePrintServiceProto) builderForValue);
            return this;
        }

        public Builder addTrackedPrinters(int index, PrinterIdProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).addTrackedPrinters((ActivePrintServiceProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllTrackedPrinters(Iterable<? extends PrinterIdProto> values) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).addAllTrackedPrinters(values);
            return this;
        }

        public Builder clearTrackedPrinters() {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).clearTrackedPrinters();
            return this;
        }

        public Builder removeTrackedPrinters(int index) {
            copyOnWrite();
            ((ActivePrintServiceProto) this.instance).removeTrackedPrinters(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivePrintServiceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.trackedPrinters_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivePrintServiceProto other = (ActivePrintServiceProto) arg1;
                this.componentName_ = (ComponentNameProto) visitor.visitMessage(this.componentName_, other.componentName_);
                this.isDestroyed_ = visitor.visitBoolean(hasIsDestroyed(), this.isDestroyed_, other.hasIsDestroyed(), other.isDestroyed_);
                this.isBound_ = visitor.visitBoolean(hasIsBound(), this.isBound_, other.hasIsBound(), other.isBound_);
                this.hasDiscoverySession_ = visitor.visitBoolean(hasHasDiscoverySession(), this.hasDiscoverySession_, other.hasHasDiscoverySession(), other.hasDiscoverySession_);
                this.hasActivePrintJobs_ = visitor.visitBoolean(hasHasActivePrintJobs(), this.hasActivePrintJobs_, other.hasHasActivePrintJobs(), other.hasActivePrintJobs_);
                this.isDiscoveringPrinters_ = visitor.visitBoolean(hasIsDiscoveringPrinters(), this.isDiscoveringPrinters_, other.hasIsDiscoveringPrinters(), other.isDiscoveringPrinters_);
                this.trackedPrinters_ = visitor.visitList(this.trackedPrinters_, other.trackedPrinters_);
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
                            ComponentNameProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ComponentNameProto.Builder) this.componentName_.toBuilder();
                            }
                            this.componentName_ = (ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.componentName_);
                                this.componentName_ = (ComponentNameProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.isDestroyed_ = input.readBool();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.isBound_ = input.readBool();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.hasDiscoverySession_ = input.readBool();
                        } else if (tag == 40) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.hasActivePrintJobs_ = input.readBool();
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.isDiscoveringPrinters_ = input.readBool();
                        } else if (tag == 58) {
                            if (!this.trackedPrinters_.isModifiable()) {
                                this.trackedPrinters_ = GeneratedMessageLite.mutableCopy(this.trackedPrinters_);
                            }
                            this.trackedPrinters_.add((PrinterIdProto) input.readMessage(PrinterIdProto.parser(), extensionRegistry));
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
                    synchronized (ActivePrintServiceProto.class) {
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

    public static ActivePrintServiceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivePrintServiceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
