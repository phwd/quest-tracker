package android.service.print;

import android.service.print.PrintJobInfoProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class CachedPrintJobProto extends GeneratedMessageLite<CachedPrintJobProto, Builder> implements CachedPrintJobProtoOrBuilder {
    public static final int APP_ID_FIELD_NUMBER = 1;
    private static final CachedPrintJobProto DEFAULT_INSTANCE = new CachedPrintJobProto();
    private static volatile Parser<CachedPrintJobProto> PARSER = null;
    public static final int PRINT_JOB_FIELD_NUMBER = 2;
    private int appId_ = 0;
    private int bitField0_;
    private PrintJobInfoProto printJob_;

    private CachedPrintJobProto() {
    }

    @Override // android.service.print.CachedPrintJobProtoOrBuilder
    public boolean hasAppId() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.print.CachedPrintJobProtoOrBuilder
    public int getAppId() {
        return this.appId_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppId(int value) {
        this.bitField0_ |= 1;
        this.appId_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppId() {
        this.bitField0_ &= -2;
        this.appId_ = 0;
    }

    @Override // android.service.print.CachedPrintJobProtoOrBuilder
    public boolean hasPrintJob() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.print.CachedPrintJobProtoOrBuilder
    public PrintJobInfoProto getPrintJob() {
        PrintJobInfoProto printJobInfoProto = this.printJob_;
        return printJobInfoProto == null ? PrintJobInfoProto.getDefaultInstance() : printJobInfoProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintJob(PrintJobInfoProto value) {
        if (value != null) {
            this.printJob_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrintJob(PrintJobInfoProto.Builder builderForValue) {
        this.printJob_ = (PrintJobInfoProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePrintJob(PrintJobInfoProto value) {
        PrintJobInfoProto printJobInfoProto = this.printJob_;
        if (printJobInfoProto == null || printJobInfoProto == PrintJobInfoProto.getDefaultInstance()) {
            this.printJob_ = value;
        } else {
            this.printJob_ = (PrintJobInfoProto) ((PrintJobInfoProto.Builder) PrintJobInfoProto.newBuilder(this.printJob_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrintJob() {
        this.printJob_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.appId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(2, getPrintJob());
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.appId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(2, getPrintJob());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static CachedPrintJobProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (CachedPrintJobProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CachedPrintJobProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CachedPrintJobProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CachedPrintJobProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (CachedPrintJobProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static CachedPrintJobProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (CachedPrintJobProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static CachedPrintJobProto parseFrom(InputStream input) throws IOException {
        return (CachedPrintJobProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CachedPrintJobProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CachedPrintJobProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CachedPrintJobProto parseDelimitedFrom(InputStream input) throws IOException {
        return (CachedPrintJobProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static CachedPrintJobProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CachedPrintJobProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static CachedPrintJobProto parseFrom(CodedInputStream input) throws IOException {
        return (CachedPrintJobProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static CachedPrintJobProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (CachedPrintJobProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CachedPrintJobProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CachedPrintJobProto, Builder> implements CachedPrintJobProtoOrBuilder {
        private Builder() {
            super(CachedPrintJobProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.print.CachedPrintJobProtoOrBuilder
        public boolean hasAppId() {
            return ((CachedPrintJobProto) this.instance).hasAppId();
        }

        @Override // android.service.print.CachedPrintJobProtoOrBuilder
        public int getAppId() {
            return ((CachedPrintJobProto) this.instance).getAppId();
        }

        public Builder setAppId(int value) {
            copyOnWrite();
            ((CachedPrintJobProto) this.instance).setAppId(value);
            return this;
        }

        public Builder clearAppId() {
            copyOnWrite();
            ((CachedPrintJobProto) this.instance).clearAppId();
            return this;
        }

        @Override // android.service.print.CachedPrintJobProtoOrBuilder
        public boolean hasPrintJob() {
            return ((CachedPrintJobProto) this.instance).hasPrintJob();
        }

        @Override // android.service.print.CachedPrintJobProtoOrBuilder
        public PrintJobInfoProto getPrintJob() {
            return ((CachedPrintJobProto) this.instance).getPrintJob();
        }

        public Builder setPrintJob(PrintJobInfoProto value) {
            copyOnWrite();
            ((CachedPrintJobProto) this.instance).setPrintJob((CachedPrintJobProto) value);
            return this;
        }

        public Builder setPrintJob(PrintJobInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((CachedPrintJobProto) this.instance).setPrintJob((CachedPrintJobProto) builderForValue);
            return this;
        }

        public Builder mergePrintJob(PrintJobInfoProto value) {
            copyOnWrite();
            ((CachedPrintJobProto) this.instance).mergePrintJob(value);
            return this;
        }

        public Builder clearPrintJob() {
            copyOnWrite();
            ((CachedPrintJobProto) this.instance).clearPrintJob();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new CachedPrintJobProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                CachedPrintJobProto other = (CachedPrintJobProto) arg1;
                this.appId_ = visitor.visitInt(hasAppId(), this.appId_, other.hasAppId(), other.appId_);
                this.printJob_ = (PrintJobInfoProto) visitor.visitMessage(this.printJob_, other.printJob_);
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
                            this.appId_ = input.readInt32();
                        } else if (tag == 18) {
                            PrintJobInfoProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder = (PrintJobInfoProto.Builder) this.printJob_.toBuilder();
                            }
                            this.printJob_ = (PrintJobInfoProto) input.readMessage(PrintJobInfoProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.printJob_);
                                this.printJob_ = (PrintJobInfoProto) subBuilder.buildPartial();
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
                    synchronized (CachedPrintJobProto.class) {
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

    public static CachedPrintJobProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CachedPrintJobProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
