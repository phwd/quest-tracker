package android.service.print;

import android.service.print.PageRangeProto;
import android.service.print.PrintAttributesProto;
import android.service.print.PrintDocumentInfoProto;
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

public final class PrintJobInfoProto extends GeneratedMessageLite<PrintJobInfoProto, Builder> implements PrintJobInfoProtoOrBuilder {
    public static final int ATTRIBUTES_FIELD_NUMBER = 7;
    public static final int CREATION_TIME_FIELD_NUMBER = 6;
    private static final PrintJobInfoProto DEFAULT_INSTANCE = new PrintJobInfoProto();
    public static final int DOCUMENT_INFO_FIELD_NUMBER = 8;
    public static final int HAS_ADVANCED_OPTIONS_FIELD_NUMBER = 11;
    public static final int IS_CANCELING_FIELD_NUMBER = 9;
    public static final int LABEL_FIELD_NUMBER = 1;
    public static final int PAGES_FIELD_NUMBER = 10;
    private static volatile Parser<PrintJobInfoProto> PARSER = null;
    public static final int PRINTER_FIELD_NUMBER = 4;
    public static final int PRINT_JOB_ID_FIELD_NUMBER = 2;
    public static final int PROGRESS_FIELD_NUMBER = 12;
    public static final int STATE_FIELD_NUMBER = 3;
    public static final int STATUS_FIELD_NUMBER = 13;
    public static final int TAG_FIELD_NUMBER = 5;
    private PrintAttributesProto attributes_;
    private int bitField0_;
    private long creationTime_ = 0;
    private PrintDocumentInfoProto documentInfo_;
    private boolean hasAdvancedOptions_ = false;
    private boolean isCanceling_ = false;
    private String label_ = "";
    private Internal.ProtobufList<PageRangeProto> pages_ = emptyProtobufList();
    private String printJobId_ = "";
    private PrinterIdProto printer_;
    private float progress_ = 0.0f;
    private int state_ = 0;
    private String status_ = "";
    private String tag_ = "";

    private PrintJobInfoProto() {
    }

    public enum State implements Internal.EnumLite {
        STATE_UNKNOWN(0),
        STATE_CREATED(1),
        STATE_QUEUED(2),
        STATE_STARTED(3),
        STATE_BLOCKED(4),
        STATE_COMPLETED(5),
        STATE_FAILED(6),
        STATE_CANCELED(7);
        
        public static final int STATE_BLOCKED_VALUE = 4;
        public static final int STATE_CANCELED_VALUE = 7;
        public static final int STATE_COMPLETED_VALUE = 5;
        public static final int STATE_CREATED_VALUE = 1;
        public static final int STATE_FAILED_VALUE = 6;
        public static final int STATE_QUEUED_VALUE = 2;
        public static final int STATE_STARTED_VALUE = 3;
        public static final int STATE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() {
            /* class android.service.print.PrintJobInfoProto.State.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public State findValueByNumber(int number) {
                return State.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static State valueOf(int value2) {
            return forNumber(value2);
        }

        public static State forNumber(int value2) {
            switch (value2) {
                case 0:
                    return STATE_UNKNOWN;
                case 1:
                    return STATE_CREATED;
                case 2:
                    return STATE_QUEUED;
                case 3:
                    return STATE_STARTED;
                case 4:
                    return STATE_BLOCKED;
                case 5:
                    return STATE_COMPLETED;
                case 6:
                    return STATE_FAILED;
                case 7:
                    return STATE_CANCELED;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<State> internalGetValueMap() {
            return internalValueMap;
        }

        private State(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasLabel() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public String getLabel() {
        return this.label_;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public ByteString getLabelBytes() {
        return ByteString.copyFromUtf8(this.label_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabel(String value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.label_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLabel() {
        this.bitField0_ &= -2;
        this.label_ = getDefaultInstance().getLabel();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabelBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.label_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasPrintJobId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public String getPrintJobId() {
        return this.printJobId_;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public ByteString getPrintJobIdBytes() {
        return ByteString.copyFromUtf8(this.printJobId_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintJobId(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.printJobId_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrintJobId() {
        this.bitField0_ &= -3;
        this.printJobId_ = getDefaultInstance().getPrintJobId();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintJobIdBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.printJobId_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasState() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public State getState() {
        State result = State.forNumber(this.state_);
        return result == null ? State.STATE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(State value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.state_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearState() {
        this.bitField0_ &= -5;
        this.state_ = 0;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasPrinter() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public PrinterIdProto getPrinter() {
        PrinterIdProto printerIdProto = this.printer_;
        return printerIdProto == null ? PrinterIdProto.getDefaultInstance() : printerIdProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrinter(PrinterIdProto value) {
        if (value != null) {
            this.printer_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrinter(PrinterIdProto.Builder builderForValue) {
        this.printer_ = (PrinterIdProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePrinter(PrinterIdProto value) {
        PrinterIdProto printerIdProto = this.printer_;
        if (printerIdProto == null || printerIdProto == PrinterIdProto.getDefaultInstance()) {
            this.printer_ = value;
        } else {
            this.printer_ = (PrinterIdProto) ((PrinterIdProto.Builder) PrinterIdProto.newBuilder(this.printer_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrinter() {
        this.printer_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasTag() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public String getTag() {
        return this.tag_;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public ByteString getTagBytes() {
        return ByteString.copyFromUtf8(this.tag_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTag(String value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.tag_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTag() {
        this.bitField0_ &= -17;
        this.tag_ = getDefaultInstance().getTag();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTagBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.tag_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasCreationTime() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public long getCreationTime() {
        return this.creationTime_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCreationTime(long value) {
        this.bitField0_ |= 32;
        this.creationTime_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCreationTime() {
        this.bitField0_ &= -33;
        this.creationTime_ = 0;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasAttributes() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public PrintAttributesProto getAttributes() {
        PrintAttributesProto printAttributesProto = this.attributes_;
        return printAttributesProto == null ? PrintAttributesProto.getDefaultInstance() : printAttributesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAttributes(PrintAttributesProto value) {
        if (value != null) {
            this.attributes_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAttributes(PrintAttributesProto.Builder builderForValue) {
        this.attributes_ = (PrintAttributesProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAttributes(PrintAttributesProto value) {
        PrintAttributesProto printAttributesProto = this.attributes_;
        if (printAttributesProto == null || printAttributesProto == PrintAttributesProto.getDefaultInstance()) {
            this.attributes_ = value;
        } else {
            this.attributes_ = (PrintAttributesProto) ((PrintAttributesProto.Builder) PrintAttributesProto.newBuilder(this.attributes_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAttributes() {
        this.attributes_ = null;
        this.bitField0_ &= -65;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasDocumentInfo() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public PrintDocumentInfoProto getDocumentInfo() {
        PrintDocumentInfoProto printDocumentInfoProto = this.documentInfo_;
        return printDocumentInfoProto == null ? PrintDocumentInfoProto.getDefaultInstance() : printDocumentInfoProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDocumentInfo(PrintDocumentInfoProto value) {
        if (value != null) {
            this.documentInfo_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDocumentInfo(PrintDocumentInfoProto.Builder builderForValue) {
        this.documentInfo_ = (PrintDocumentInfoProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDocumentInfo(PrintDocumentInfoProto value) {
        PrintDocumentInfoProto printDocumentInfoProto = this.documentInfo_;
        if (printDocumentInfoProto == null || printDocumentInfoProto == PrintDocumentInfoProto.getDefaultInstance()) {
            this.documentInfo_ = value;
        } else {
            this.documentInfo_ = (PrintDocumentInfoProto) ((PrintDocumentInfoProto.Builder) PrintDocumentInfoProto.newBuilder(this.documentInfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDocumentInfo() {
        this.documentInfo_ = null;
        this.bitField0_ &= -129;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasIsCanceling() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean getIsCanceling() {
        return this.isCanceling_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsCanceling(boolean value) {
        this.bitField0_ |= 256;
        this.isCanceling_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsCanceling() {
        this.bitField0_ &= -257;
        this.isCanceling_ = false;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public List<PageRangeProto> getPagesList() {
        return this.pages_;
    }

    public List<? extends PageRangeProtoOrBuilder> getPagesOrBuilderList() {
        return this.pages_;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public int getPagesCount() {
        return this.pages_.size();
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public PageRangeProto getPages(int index) {
        return this.pages_.get(index);
    }

    public PageRangeProtoOrBuilder getPagesOrBuilder(int index) {
        return this.pages_.get(index);
    }

    private void ensurePagesIsMutable() {
        if (!this.pages_.isModifiable()) {
            this.pages_ = GeneratedMessageLite.mutableCopy(this.pages_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPages(int index, PageRangeProto value) {
        if (value != null) {
            ensurePagesIsMutable();
            this.pages_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPages(int index, PageRangeProto.Builder builderForValue) {
        ensurePagesIsMutable();
        this.pages_.set(index, (PageRangeProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPages(PageRangeProto value) {
        if (value != null) {
            ensurePagesIsMutable();
            this.pages_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPages(int index, PageRangeProto value) {
        if (value != null) {
            ensurePagesIsMutable();
            this.pages_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPages(PageRangeProto.Builder builderForValue) {
        ensurePagesIsMutable();
        this.pages_.add((PageRangeProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPages(int index, PageRangeProto.Builder builderForValue) {
        ensurePagesIsMutable();
        this.pages_.add(index, (PageRangeProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPages(Iterable<? extends PageRangeProto> values) {
        ensurePagesIsMutable();
        AbstractMessageLite.addAll(values, this.pages_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPages() {
        this.pages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removePages(int index) {
        ensurePagesIsMutable();
        this.pages_.remove(index);
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasHasAdvancedOptions() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean getHasAdvancedOptions() {
        return this.hasAdvancedOptions_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHasAdvancedOptions(boolean value) {
        this.bitField0_ |= 512;
        this.hasAdvancedOptions_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHasAdvancedOptions() {
        this.bitField0_ &= -513;
        this.hasAdvancedOptions_ = false;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasProgress() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public float getProgress() {
        return this.progress_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProgress(float value) {
        this.bitField0_ |= 1024;
        this.progress_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProgress() {
        this.bitField0_ &= -1025;
        this.progress_ = 0.0f;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public boolean hasStatus() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public String getStatus() {
        return this.status_;
    }

    @Override // android.service.print.PrintJobInfoProtoOrBuilder
    public ByteString getStatusBytes() {
        return ByteString.copyFromUtf8(this.status_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatus(String value) {
        if (value != null) {
            this.bitField0_ |= 2048;
            this.status_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatus() {
        this.bitField0_ &= -2049;
        this.status_ = getDefaultInstance().getStatus();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatusBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2048;
            this.status_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeString(1, getLabel());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getPrintJobId());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.state_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(4, getPrinter());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeString(5, getTag());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeInt64(6, this.creationTime_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(7, getAttributes());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(8, getDocumentInfo());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeBool(9, this.isCanceling_);
        }
        for (int i = 0; i < this.pages_.size(); i++) {
            output.writeMessage(10, this.pages_.get(i));
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeBool(11, this.hasAdvancedOptions_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeFloat(12, this.progress_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeString(13, getStatus());
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
            size2 = 0 + CodedOutputStream.computeStringSize(1, getLabel());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getPrintJobId());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.state_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(4, getPrinter());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeStringSize(5, getTag());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeInt64Size(6, this.creationTime_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(7, getAttributes());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(8, getDocumentInfo());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeBoolSize(9, this.isCanceling_);
        }
        for (int i = 0; i < this.pages_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(10, this.pages_.get(i));
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeBoolSize(11, this.hasAdvancedOptions_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeFloatSize(12, this.progress_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeStringSize(13, getStatus());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PrintJobInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PrintJobInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintJobInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintJobInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintJobInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PrintJobInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PrintJobInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PrintJobInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PrintJobInfoProto parseFrom(InputStream input) throws IOException {
        return (PrintJobInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintJobInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintJobInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintJobInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PrintJobInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintJobInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintJobInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PrintJobInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (PrintJobInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PrintJobInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PrintJobInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PrintJobInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PrintJobInfoProto, Builder> implements PrintJobInfoProtoOrBuilder {
        private Builder() {
            super(PrintJobInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasLabel() {
            return ((PrintJobInfoProto) this.instance).hasLabel();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public String getLabel() {
            return ((PrintJobInfoProto) this.instance).getLabel();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public ByteString getLabelBytes() {
            return ((PrintJobInfoProto) this.instance).getLabelBytes();
        }

        public Builder setLabel(String value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setLabel(value);
            return this;
        }

        public Builder clearLabel() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearLabel();
            return this;
        }

        public Builder setLabelBytes(ByteString value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setLabelBytes(value);
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasPrintJobId() {
            return ((PrintJobInfoProto) this.instance).hasPrintJobId();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public String getPrintJobId() {
            return ((PrintJobInfoProto) this.instance).getPrintJobId();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public ByteString getPrintJobIdBytes() {
            return ((PrintJobInfoProto) this.instance).getPrintJobIdBytes();
        }

        public Builder setPrintJobId(String value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setPrintJobId(value);
            return this;
        }

        public Builder clearPrintJobId() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearPrintJobId();
            return this;
        }

        public Builder setPrintJobIdBytes(ByteString value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setPrintJobIdBytes(value);
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasState() {
            return ((PrintJobInfoProto) this.instance).hasState();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public State getState() {
            return ((PrintJobInfoProto) this.instance).getState();
        }

        public Builder setState(State value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearState();
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasPrinter() {
            return ((PrintJobInfoProto) this.instance).hasPrinter();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public PrinterIdProto getPrinter() {
            return ((PrintJobInfoProto) this.instance).getPrinter();
        }

        public Builder setPrinter(PrinterIdProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setPrinter((PrintJobInfoProto) value);
            return this;
        }

        public Builder setPrinter(PrinterIdProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setPrinter((PrintJobInfoProto) builderForValue);
            return this;
        }

        public Builder mergePrinter(PrinterIdProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).mergePrinter(value);
            return this;
        }

        public Builder clearPrinter() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearPrinter();
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasTag() {
            return ((PrintJobInfoProto) this.instance).hasTag();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public String getTag() {
            return ((PrintJobInfoProto) this.instance).getTag();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public ByteString getTagBytes() {
            return ((PrintJobInfoProto) this.instance).getTagBytes();
        }

        public Builder setTag(String value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setTag(value);
            return this;
        }

        public Builder clearTag() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearTag();
            return this;
        }

        public Builder setTagBytes(ByteString value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setTagBytes(value);
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasCreationTime() {
            return ((PrintJobInfoProto) this.instance).hasCreationTime();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public long getCreationTime() {
            return ((PrintJobInfoProto) this.instance).getCreationTime();
        }

        public Builder setCreationTime(long value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setCreationTime(value);
            return this;
        }

        public Builder clearCreationTime() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearCreationTime();
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasAttributes() {
            return ((PrintJobInfoProto) this.instance).hasAttributes();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public PrintAttributesProto getAttributes() {
            return ((PrintJobInfoProto) this.instance).getAttributes();
        }

        public Builder setAttributes(PrintAttributesProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setAttributes((PrintJobInfoProto) value);
            return this;
        }

        public Builder setAttributes(PrintAttributesProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setAttributes((PrintJobInfoProto) builderForValue);
            return this;
        }

        public Builder mergeAttributes(PrintAttributesProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).mergeAttributes(value);
            return this;
        }

        public Builder clearAttributes() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearAttributes();
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasDocumentInfo() {
            return ((PrintJobInfoProto) this.instance).hasDocumentInfo();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public PrintDocumentInfoProto getDocumentInfo() {
            return ((PrintJobInfoProto) this.instance).getDocumentInfo();
        }

        public Builder setDocumentInfo(PrintDocumentInfoProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setDocumentInfo((PrintJobInfoProto) value);
            return this;
        }

        public Builder setDocumentInfo(PrintDocumentInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setDocumentInfo((PrintJobInfoProto) builderForValue);
            return this;
        }

        public Builder mergeDocumentInfo(PrintDocumentInfoProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).mergeDocumentInfo(value);
            return this;
        }

        public Builder clearDocumentInfo() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearDocumentInfo();
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasIsCanceling() {
            return ((PrintJobInfoProto) this.instance).hasIsCanceling();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean getIsCanceling() {
            return ((PrintJobInfoProto) this.instance).getIsCanceling();
        }

        public Builder setIsCanceling(boolean value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setIsCanceling(value);
            return this;
        }

        public Builder clearIsCanceling() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearIsCanceling();
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public List<PageRangeProto> getPagesList() {
            return Collections.unmodifiableList(((PrintJobInfoProto) this.instance).getPagesList());
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public int getPagesCount() {
            return ((PrintJobInfoProto) this.instance).getPagesCount();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public PageRangeProto getPages(int index) {
            return ((PrintJobInfoProto) this.instance).getPages(index);
        }

        public Builder setPages(int index, PageRangeProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setPages((PrintJobInfoProto) index, (int) value);
            return this;
        }

        public Builder setPages(int index, PageRangeProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setPages((PrintJobInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addPages(PageRangeProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).addPages((PrintJobInfoProto) value);
            return this;
        }

        public Builder addPages(int index, PageRangeProto value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).addPages((PrintJobInfoProto) index, (int) value);
            return this;
        }

        public Builder addPages(PageRangeProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).addPages((PrintJobInfoProto) builderForValue);
            return this;
        }

        public Builder addPages(int index, PageRangeProto.Builder builderForValue) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).addPages((PrintJobInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllPages(Iterable<? extends PageRangeProto> values) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).addAllPages(values);
            return this;
        }

        public Builder clearPages() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearPages();
            return this;
        }

        public Builder removePages(int index) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).removePages(index);
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasHasAdvancedOptions() {
            return ((PrintJobInfoProto) this.instance).hasHasAdvancedOptions();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean getHasAdvancedOptions() {
            return ((PrintJobInfoProto) this.instance).getHasAdvancedOptions();
        }

        public Builder setHasAdvancedOptions(boolean value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setHasAdvancedOptions(value);
            return this;
        }

        public Builder clearHasAdvancedOptions() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearHasAdvancedOptions();
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasProgress() {
            return ((PrintJobInfoProto) this.instance).hasProgress();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public float getProgress() {
            return ((PrintJobInfoProto) this.instance).getProgress();
        }

        public Builder setProgress(float value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setProgress(value);
            return this;
        }

        public Builder clearProgress() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearProgress();
            return this;
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public boolean hasStatus() {
            return ((PrintJobInfoProto) this.instance).hasStatus();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public String getStatus() {
            return ((PrintJobInfoProto) this.instance).getStatus();
        }

        @Override // android.service.print.PrintJobInfoProtoOrBuilder
        public ByteString getStatusBytes() {
            return ((PrintJobInfoProto) this.instance).getStatusBytes();
        }

        public Builder setStatus(String value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setStatus(value);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).clearStatus();
            return this;
        }

        public Builder setStatusBytes(ByteString value) {
            copyOnWrite();
            ((PrintJobInfoProto) this.instance).setStatusBytes(value);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PrintJobInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.pages_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PrintJobInfoProto other = (PrintJobInfoProto) arg1;
                this.label_ = visitor.visitString(hasLabel(), this.label_, other.hasLabel(), other.label_);
                this.printJobId_ = visitor.visitString(hasPrintJobId(), this.printJobId_, other.hasPrintJobId(), other.printJobId_);
                this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
                this.printer_ = (PrinterIdProto) visitor.visitMessage(this.printer_, other.printer_);
                this.tag_ = visitor.visitString(hasTag(), this.tag_, other.hasTag(), other.tag_);
                this.creationTime_ = visitor.visitLong(hasCreationTime(), this.creationTime_, other.hasCreationTime(), other.creationTime_);
                this.attributes_ = (PrintAttributesProto) visitor.visitMessage(this.attributes_, other.attributes_);
                this.documentInfo_ = (PrintDocumentInfoProto) visitor.visitMessage(this.documentInfo_, other.documentInfo_);
                this.isCanceling_ = visitor.visitBoolean(hasIsCanceling(), this.isCanceling_, other.hasIsCanceling(), other.isCanceling_);
                this.pages_ = visitor.visitList(this.pages_, other.pages_);
                this.hasAdvancedOptions_ = visitor.visitBoolean(hasHasAdvancedOptions(), this.hasAdvancedOptions_, other.hasHasAdvancedOptions(), other.hasAdvancedOptions_);
                this.progress_ = visitor.visitFloat(hasProgress(), this.progress_, other.hasProgress(), other.progress_);
                this.status_ = visitor.visitString(hasStatus(), this.status_, other.hasStatus(), other.status_);
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
                            case 10:
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.label_ = s;
                                break;
                            case 18:
                                String s2 = input.readString();
                                this.bitField0_ |= 2;
                                this.printJobId_ = s2;
                                break;
                            case 24:
                                int rawValue = input.readEnum();
                                if (State.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 4;
                                    this.state_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(3, rawValue);
                                    break;
                                }
                            case 34:
                                PrinterIdProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder = (PrinterIdProto.Builder) this.printer_.toBuilder();
                                }
                                this.printer_ = (PrinterIdProto) input.readMessage(PrinterIdProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.printer_);
                                    this.printer_ = (PrinterIdProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 42:
                                String s3 = input.readString();
                                this.bitField0_ |= 16;
                                this.tag_ = s3;
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.creationTime_ = input.readInt64();
                                break;
                            case 58:
                                PrintAttributesProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder2 = (PrintAttributesProto.Builder) this.attributes_.toBuilder();
                                }
                                this.attributes_ = (PrintAttributesProto) input.readMessage(PrintAttributesProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.attributes_);
                                    this.attributes_ = (PrintAttributesProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 66:
                                PrintDocumentInfoProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder3 = (PrintDocumentInfoProto.Builder) this.documentInfo_.toBuilder();
                                }
                                this.documentInfo_ = (PrintDocumentInfoProto) input.readMessage(PrintDocumentInfoProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.documentInfo_);
                                    this.documentInfo_ = (PrintDocumentInfoProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.isCanceling_ = input.readBool();
                                break;
                            case 82:
                                if (!this.pages_.isModifiable()) {
                                    this.pages_ = GeneratedMessageLite.mutableCopy(this.pages_);
                                }
                                this.pages_.add((PageRangeProto) input.readMessage(PageRangeProto.parser(), extensionRegistry));
                                break;
                            case 88:
                                this.bitField0_ |= 512;
                                this.hasAdvancedOptions_ = input.readBool();
                                break;
                            case 101:
                                this.bitField0_ |= 1024;
                                this.progress_ = input.readFloat();
                                break;
                            case 106:
                                String s4 = input.readString();
                                this.bitField0_ |= 2048;
                                this.status_ = s4;
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
                    synchronized (PrintJobInfoProto.class) {
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

    public static PrintJobInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PrintJobInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
