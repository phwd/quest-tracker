package android.service.print;

import android.content.ComponentNameProto;
import android.content.ComponentNameProtoOrBuilder;
import android.service.print.PrintJobInfoProto;
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

public final class PrintSpoolerInternalStateProto extends GeneratedMessageLite<PrintSpoolerInternalStateProto, Builder> implements PrintSpoolerInternalStateProtoOrBuilder {
    public static final int APPROVED_SERVICES_FIELD_NUMBER = 3;
    private static final PrintSpoolerInternalStateProto DEFAULT_INSTANCE = new PrintSpoolerInternalStateProto();
    private static volatile Parser<PrintSpoolerInternalStateProto> PARSER = null;
    public static final int PRINT_JOBS_FIELD_NUMBER = 1;
    public static final int PRINT_JOB_FILES_FIELD_NUMBER = 2;
    private Internal.ProtobufList<ComponentNameProto> approvedServices_ = emptyProtobufList();
    private Internal.ProtobufList<String> printJobFiles_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<PrintJobInfoProto> printJobs_ = emptyProtobufList();

    private PrintSpoolerInternalStateProto() {
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public List<PrintJobInfoProto> getPrintJobsList() {
        return this.printJobs_;
    }

    public List<? extends PrintJobInfoProtoOrBuilder> getPrintJobsOrBuilderList() {
        return this.printJobs_;
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public int getPrintJobsCount() {
        return this.printJobs_.size();
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public PrintJobInfoProto getPrintJobs(int index) {
        return this.printJobs_.get(index);
    }

    public PrintJobInfoProtoOrBuilder getPrintJobsOrBuilder(int index) {
        return this.printJobs_.get(index);
    }

    private void ensurePrintJobsIsMutable() {
        if (!this.printJobs_.isModifiable()) {
            this.printJobs_ = GeneratedMessageLite.mutableCopy(this.printJobs_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintJobs(int index, PrintJobInfoProto value) {
        if (value != null) {
            ensurePrintJobsIsMutable();
            this.printJobs_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintJobs(int index, PrintJobInfoProto.Builder builderForValue) {
        ensurePrintJobsIsMutable();
        this.printJobs_.set(index, (PrintJobInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrintJobs(PrintJobInfoProto value) {
        if (value != null) {
            ensurePrintJobsIsMutable();
            this.printJobs_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrintJobs(int index, PrintJobInfoProto value) {
        if (value != null) {
            ensurePrintJobsIsMutable();
            this.printJobs_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrintJobs(PrintJobInfoProto.Builder builderForValue) {
        ensurePrintJobsIsMutable();
        this.printJobs_.add((PrintJobInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrintJobs(int index, PrintJobInfoProto.Builder builderForValue) {
        ensurePrintJobsIsMutable();
        this.printJobs_.add(index, (PrintJobInfoProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPrintJobs(Iterable<? extends PrintJobInfoProto> values) {
        ensurePrintJobsIsMutable();
        AbstractMessageLite.addAll(values, this.printJobs_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrintJobs() {
        this.printJobs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePrintJobs(int index) {
        ensurePrintJobsIsMutable();
        this.printJobs_.remove(index);
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public List<String> getPrintJobFilesList() {
        return this.printJobFiles_;
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public int getPrintJobFilesCount() {
        return this.printJobFiles_.size();
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public String getPrintJobFiles(int index) {
        return this.printJobFiles_.get(index);
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public ByteString getPrintJobFilesBytes(int index) {
        return ByteString.copyFromUtf8(this.printJobFiles_.get(index));
    }

    private void ensurePrintJobFilesIsMutable() {
        if (!this.printJobFiles_.isModifiable()) {
            this.printJobFiles_ = GeneratedMessageLite.mutableCopy(this.printJobFiles_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintJobFiles(int index, String value) {
        if (value != null) {
            ensurePrintJobFilesIsMutable();
            this.printJobFiles_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrintJobFiles(String value) {
        if (value != null) {
            ensurePrintJobFilesIsMutable();
            this.printJobFiles_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPrintJobFiles(Iterable<String> values) {
        ensurePrintJobFilesIsMutable();
        AbstractMessageLite.addAll(values, this.printJobFiles_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrintJobFiles() {
        this.printJobFiles_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPrintJobFilesBytes(ByteString value) {
        if (value != null) {
            ensurePrintJobFilesIsMutable();
            this.printJobFiles_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public List<ComponentNameProto> getApprovedServicesList() {
        return this.approvedServices_;
    }

    public List<? extends ComponentNameProtoOrBuilder> getApprovedServicesOrBuilderList() {
        return this.approvedServices_;
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public int getApprovedServicesCount() {
        return this.approvedServices_.size();
    }

    @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
    public ComponentNameProto getApprovedServices(int index) {
        return this.approvedServices_.get(index);
    }

    public ComponentNameProtoOrBuilder getApprovedServicesOrBuilder(int index) {
        return this.approvedServices_.get(index);
    }

    private void ensureApprovedServicesIsMutable() {
        if (!this.approvedServices_.isModifiable()) {
            this.approvedServices_ = GeneratedMessageLite.mutableCopy(this.approvedServices_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApprovedServices(int index, ComponentNameProto value) {
        if (value != null) {
            ensureApprovedServicesIsMutable();
            this.approvedServices_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setApprovedServices(int index, ComponentNameProto.Builder builderForValue) {
        ensureApprovedServicesIsMutable();
        this.approvedServices_.set(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApprovedServices(ComponentNameProto value) {
        if (value != null) {
            ensureApprovedServicesIsMutable();
            this.approvedServices_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApprovedServices(int index, ComponentNameProto value) {
        if (value != null) {
            ensureApprovedServicesIsMutable();
            this.approvedServices_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApprovedServices(ComponentNameProto.Builder builderForValue) {
        ensureApprovedServicesIsMutable();
        this.approvedServices_.add((ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addApprovedServices(int index, ComponentNameProto.Builder builderForValue) {
        ensureApprovedServicesIsMutable();
        this.approvedServices_.add(index, (ComponentNameProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllApprovedServices(Iterable<? extends ComponentNameProto> values) {
        ensureApprovedServicesIsMutable();
        AbstractMessageLite.addAll(values, this.approvedServices_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearApprovedServices() {
        this.approvedServices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeApprovedServices(int index) {
        ensureApprovedServicesIsMutable();
        this.approvedServices_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.printJobs_.size(); i++) {
            output.writeMessage(1, this.printJobs_.get(i));
        }
        for (int i2 = 0; i2 < this.printJobFiles_.size(); i2++) {
            output.writeString(2, this.printJobFiles_.get(i2));
        }
        for (int i3 = 0; i3 < this.approvedServices_.size(); i3++) {
            output.writeMessage(3, this.approvedServices_.get(i3));
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
        for (int i = 0; i < this.printJobs_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.printJobs_.get(i));
        }
        int dataSize = 0;
        for (int i2 = 0; i2 < this.printJobFiles_.size(); i2++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.printJobFiles_.get(i2));
        }
        int size3 = size2 + dataSize + (getPrintJobFilesList().size() * 1);
        for (int i3 = 0; i3 < this.approvedServices_.size(); i3++) {
            size3 += CodedOutputStream.computeMessageSize(3, this.approvedServices_.get(i3));
        }
        int size4 = size3 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size4;
        return size4;
    }

    public static PrintSpoolerInternalStateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrintSpoolerInternalStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintSpoolerInternalStateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintSpoolerInternalStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintSpoolerInternalStateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrintSpoolerInternalStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintSpoolerInternalStateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintSpoolerInternalStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintSpoolerInternalStateProto parseFrom(InputStream input) throws IOException {
        return (PrintSpoolerInternalStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintSpoolerInternalStateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintSpoolerInternalStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintSpoolerInternalStateProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrintSpoolerInternalStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintSpoolerInternalStateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintSpoolerInternalStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintSpoolerInternalStateProto parseFrom(CodedInputStream input) throws IOException {
        return (PrintSpoolerInternalStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintSpoolerInternalStateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintSpoolerInternalStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrintSpoolerInternalStateProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrintSpoolerInternalStateProto, Builder> implements PrintSpoolerInternalStateProtoOrBuilder {
        private Builder() {
            super(PrintSpoolerInternalStateProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public List<PrintJobInfoProto> getPrintJobsList() {
            return Collections.unmodifiableList(((PrintSpoolerInternalStateProto) this.instance).getPrintJobsList());
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public int getPrintJobsCount() {
            return ((PrintSpoolerInternalStateProto) this.instance).getPrintJobsCount();
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public PrintJobInfoProto getPrintJobs(int index) {
            return ((PrintSpoolerInternalStateProto) this.instance).getPrintJobs(index);
        }

        public Builder setPrintJobs(int index, PrintJobInfoProto value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).setPrintJobs((PrintSpoolerInternalStateProto) index, (int) value);
            return this;
        }

        public Builder setPrintJobs(int index, PrintJobInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).setPrintJobs((PrintSpoolerInternalStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPrintJobs(PrintJobInfoProto value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addPrintJobs((PrintSpoolerInternalStateProto) value);
            return this;
        }

        public Builder addPrintJobs(int index, PrintJobInfoProto value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addPrintJobs((PrintSpoolerInternalStateProto) index, (int) value);
            return this;
        }

        public Builder addPrintJobs(PrintJobInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addPrintJobs((PrintSpoolerInternalStateProto) builderForValue);
            return this;
        }

        public Builder addPrintJobs(int index, PrintJobInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addPrintJobs((PrintSpoolerInternalStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPrintJobs(Iterable<? extends PrintJobInfoProto> values) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addAllPrintJobs(values);
            return this;
        }

        public Builder clearPrintJobs() {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).clearPrintJobs();
            return this;
        }

        public Builder removePrintJobs(int index) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).removePrintJobs(index);
            return this;
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public List<String> getPrintJobFilesList() {
            return Collections.unmodifiableList(((PrintSpoolerInternalStateProto) this.instance).getPrintJobFilesList());
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public int getPrintJobFilesCount() {
            return ((PrintSpoolerInternalStateProto) this.instance).getPrintJobFilesCount();
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public String getPrintJobFiles(int index) {
            return ((PrintSpoolerInternalStateProto) this.instance).getPrintJobFiles(index);
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public ByteString getPrintJobFilesBytes(int index) {
            return ((PrintSpoolerInternalStateProto) this.instance).getPrintJobFilesBytes(index);
        }

        public Builder setPrintJobFiles(int index, String value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).setPrintJobFiles(index, value);
            return this;
        }

        public Builder addPrintJobFiles(String value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addPrintJobFiles(value);
            return this;
        }

        public Builder addAllPrintJobFiles(Iterable<String> values) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addAllPrintJobFiles(values);
            return this;
        }

        public Builder clearPrintJobFiles() {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).clearPrintJobFiles();
            return this;
        }

        public Builder addPrintJobFilesBytes(ByteString value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addPrintJobFilesBytes(value);
            return this;
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public List<ComponentNameProto> getApprovedServicesList() {
            return Collections.unmodifiableList(((PrintSpoolerInternalStateProto) this.instance).getApprovedServicesList());
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public int getApprovedServicesCount() {
            return ((PrintSpoolerInternalStateProto) this.instance).getApprovedServicesCount();
        }

        @Override // android.service.print.PrintSpoolerInternalStateProtoOrBuilder
        public ComponentNameProto getApprovedServices(int index) {
            return ((PrintSpoolerInternalStateProto) this.instance).getApprovedServices(index);
        }

        public Builder setApprovedServices(int index, ComponentNameProto value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).setApprovedServices((PrintSpoolerInternalStateProto) index, (int) value);
            return this;
        }

        public Builder setApprovedServices(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).setApprovedServices((PrintSpoolerInternalStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addApprovedServices(ComponentNameProto value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addApprovedServices((PrintSpoolerInternalStateProto) value);
            return this;
        }

        public Builder addApprovedServices(int index, ComponentNameProto value) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addApprovedServices((PrintSpoolerInternalStateProto) index, (int) value);
            return this;
        }

        public Builder addApprovedServices(ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addApprovedServices((PrintSpoolerInternalStateProto) builderForValue);
            return this;
        }

        public Builder addApprovedServices(int index, ComponentNameProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addApprovedServices((PrintSpoolerInternalStateProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllApprovedServices(Iterable<? extends ComponentNameProto> values) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).addAllApprovedServices(values);
            return this;
        }

        public Builder clearApprovedServices() {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).clearApprovedServices();
            return this;
        }

        public Builder removeApprovedServices(int index) {
            copyOnWrite();
            ((PrintSpoolerInternalStateProto) this.instance).removeApprovedServices(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrintSpoolerInternalStateProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.printJobs_.makeImmutable();
                this.printJobFiles_.makeImmutable();
                this.approvedServices_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrintSpoolerInternalStateProto other = (PrintSpoolerInternalStateProto) arg1;
                this.printJobs_ = visitor.visitList(this.printJobs_, other.printJobs_);
                this.printJobFiles_ = visitor.visitList(this.printJobFiles_, other.printJobFiles_);
                this.approvedServices_ = visitor.visitList(this.approvedServices_, other.approvedServices_);
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
                            if (!this.printJobs_.isModifiable()) {
                                this.printJobs_ = GeneratedMessageLite.mutableCopy(this.printJobs_);
                            }
                            this.printJobs_.add((PrintJobInfoProto) input.readMessage(PrintJobInfoProto.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            String s = input.readString();
                            if (!this.printJobFiles_.isModifiable()) {
                                this.printJobFiles_ = GeneratedMessageLite.mutableCopy(this.printJobFiles_);
                            }
                            this.printJobFiles_.add(s);
                        } else if (tag == 26) {
                            if (!this.approvedServices_.isModifiable()) {
                                this.approvedServices_ = GeneratedMessageLite.mutableCopy(this.approvedServices_);
                            }
                            this.approvedServices_.add((ComponentNameProto) input.readMessage(ComponentNameProto.parser(), extensionRegistry));
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
                    synchronized (PrintSpoolerInternalStateProto.class) {
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

    public static PrintSpoolerInternalStateProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrintSpoolerInternalStateProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
