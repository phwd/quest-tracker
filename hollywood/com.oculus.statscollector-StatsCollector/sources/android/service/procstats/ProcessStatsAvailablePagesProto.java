package android.service.procstats;

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

public final class ProcessStatsAvailablePagesProto extends GeneratedMessageLite<ProcessStatsAvailablePagesProto, Builder> implements ProcessStatsAvailablePagesProtoOrBuilder {
    private static final ProcessStatsAvailablePagesProto DEFAULT_INSTANCE = new ProcessStatsAvailablePagesProto();
    public static final int LABEL_FIELD_NUMBER = 3;
    public static final int NODE_FIELD_NUMBER = 1;
    public static final int PAGES_PER_ORDER_FIELD_NUMBER = 4;
    private static volatile Parser<ProcessStatsAvailablePagesProto> PARSER = null;
    public static final int ZONE_FIELD_NUMBER = 2;
    private int bitField0_;
    private String label_ = "";
    private int node_ = 0;
    private Internal.IntList pagesPerOrder_ = emptyIntList();
    private String zone_ = "";

    private ProcessStatsAvailablePagesProto() {
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public boolean hasNode() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public int getNode() {
        return this.node_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNode(int value) {
        this.bitField0_ |= 1;
        this.node_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNode() {
        this.bitField0_ &= -2;
        this.node_ = 0;
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public boolean hasZone() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public String getZone() {
        return this.zone_;
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public ByteString getZoneBytes() {
        return ByteString.copyFromUtf8(this.zone_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZone(String value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.zone_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearZone() {
        this.bitField0_ &= -3;
        this.zone_ = getDefaultInstance().getZone();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZoneBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.zone_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public boolean hasLabel() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public String getLabel() {
        return this.label_;
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public ByteString getLabelBytes() {
        return ByteString.copyFromUtf8(this.label_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabel(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.label_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLabel() {
        this.bitField0_ &= -5;
        this.label_ = getDefaultInstance().getLabel();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLabelBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.label_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public List<Integer> getPagesPerOrderList() {
        return this.pagesPerOrder_;
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public int getPagesPerOrderCount() {
        return this.pagesPerOrder_.size();
    }

    @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
    public int getPagesPerOrder(int index) {
        return this.pagesPerOrder_.getInt(index);
    }

    private void ensurePagesPerOrderIsMutable() {
        if (!this.pagesPerOrder_.isModifiable()) {
            this.pagesPerOrder_ = GeneratedMessageLite.mutableCopy(this.pagesPerOrder_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPagesPerOrder(int index, int value) {
        ensurePagesPerOrderIsMutable();
        this.pagesPerOrder_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPagesPerOrder(int value) {
        ensurePagesPerOrderIsMutable();
        this.pagesPerOrder_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllPagesPerOrder(Iterable<? extends Integer> values) {
        ensurePagesPerOrderIsMutable();
        AbstractMessageLite.addAll(values, this.pagesPerOrder_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPagesPerOrder() {
        this.pagesPerOrder_ = emptyIntList();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.node_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeString(2, getZone());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(3, getLabel());
        }
        for (int i = 0; i < this.pagesPerOrder_.size(); i++) {
            output.writeInt32(4, this.pagesPerOrder_.getInt(i));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.node_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeStringSize(2, getZone());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(3, getLabel());
        }
        int dataSize = 0;
        for (int i = 0; i < this.pagesPerOrder_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.pagesPerOrder_.getInt(i));
        }
        int size3 = size2 + dataSize + (getPagesPerOrderList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ProcessStatsAvailablePagesProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ProcessStatsAvailablePagesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsAvailablePagesProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsAvailablePagesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsAvailablePagesProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ProcessStatsAvailablePagesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ProcessStatsAvailablePagesProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ProcessStatsAvailablePagesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ProcessStatsAvailablePagesProto parseFrom(InputStream input) throws IOException {
        return (ProcessStatsAvailablePagesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsAvailablePagesProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsAvailablePagesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsAvailablePagesProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ProcessStatsAvailablePagesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsAvailablePagesProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsAvailablePagesProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ProcessStatsAvailablePagesProto parseFrom(CodedInputStream input) throws IOException {
        return (ProcessStatsAvailablePagesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ProcessStatsAvailablePagesProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ProcessStatsAvailablePagesProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProcessStatsAvailablePagesProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProcessStatsAvailablePagesProto, Builder> implements ProcessStatsAvailablePagesProtoOrBuilder {
        private Builder() {
            super(ProcessStatsAvailablePagesProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public boolean hasNode() {
            return ((ProcessStatsAvailablePagesProto) this.instance).hasNode();
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public int getNode() {
            return ((ProcessStatsAvailablePagesProto) this.instance).getNode();
        }

        public Builder setNode(int value) {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).setNode(value);
            return this;
        }

        public Builder clearNode() {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).clearNode();
            return this;
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public boolean hasZone() {
            return ((ProcessStatsAvailablePagesProto) this.instance).hasZone();
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public String getZone() {
            return ((ProcessStatsAvailablePagesProto) this.instance).getZone();
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public ByteString getZoneBytes() {
            return ((ProcessStatsAvailablePagesProto) this.instance).getZoneBytes();
        }

        public Builder setZone(String value) {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).setZone(value);
            return this;
        }

        public Builder clearZone() {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).clearZone();
            return this;
        }

        public Builder setZoneBytes(ByteString value) {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).setZoneBytes(value);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public boolean hasLabel() {
            return ((ProcessStatsAvailablePagesProto) this.instance).hasLabel();
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public String getLabel() {
            return ((ProcessStatsAvailablePagesProto) this.instance).getLabel();
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public ByteString getLabelBytes() {
            return ((ProcessStatsAvailablePagesProto) this.instance).getLabelBytes();
        }

        public Builder setLabel(String value) {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).setLabel(value);
            return this;
        }

        public Builder clearLabel() {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).clearLabel();
            return this;
        }

        public Builder setLabelBytes(ByteString value) {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).setLabelBytes(value);
            return this;
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public List<Integer> getPagesPerOrderList() {
            return Collections.unmodifiableList(((ProcessStatsAvailablePagesProto) this.instance).getPagesPerOrderList());
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public int getPagesPerOrderCount() {
            return ((ProcessStatsAvailablePagesProto) this.instance).getPagesPerOrderCount();
        }

        @Override // android.service.procstats.ProcessStatsAvailablePagesProtoOrBuilder
        public int getPagesPerOrder(int index) {
            return ((ProcessStatsAvailablePagesProto) this.instance).getPagesPerOrder(index);
        }

        public Builder setPagesPerOrder(int index, int value) {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).setPagesPerOrder(index, value);
            return this;
        }

        public Builder addPagesPerOrder(int value) {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).addPagesPerOrder(value);
            return this;
        }

        public Builder addAllPagesPerOrder(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).addAllPagesPerOrder(values);
            return this;
        }

        public Builder clearPagesPerOrder() {
            copyOnWrite();
            ((ProcessStatsAvailablePagesProto) this.instance).clearPagesPerOrder();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ProcessStatsAvailablePagesProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.pagesPerOrder_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ProcessStatsAvailablePagesProto other = (ProcessStatsAvailablePagesProto) arg1;
                this.node_ = visitor.visitInt(hasNode(), this.node_, other.hasNode(), other.node_);
                this.zone_ = visitor.visitString(hasZone(), this.zone_, other.hasZone(), other.zone_);
                this.label_ = visitor.visitString(hasLabel(), this.label_, other.hasLabel(), other.label_);
                this.pagesPerOrder_ = visitor.visitIntList(this.pagesPerOrder_, other.pagesPerOrder_);
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
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.node_ = input.readInt32();
                        } else if (tag == 18) {
                            String s = input.readString();
                            this.bitField0_ |= 2;
                            this.zone_ = s;
                        } else if (tag == 26) {
                            String s2 = input.readString();
                            this.bitField0_ |= 4;
                            this.label_ = s2;
                        } else if (tag == 32) {
                            if (!this.pagesPerOrder_.isModifiable()) {
                                this.pagesPerOrder_ = GeneratedMessageLite.mutableCopy(this.pagesPerOrder_);
                            }
                            this.pagesPerOrder_.addInt(input.readInt32());
                        } else if (tag == 34) {
                            int limit = input.pushLimit(input.readRawVarint32());
                            if (!this.pagesPerOrder_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                this.pagesPerOrder_ = GeneratedMessageLite.mutableCopy(this.pagesPerOrder_);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                this.pagesPerOrder_.addInt(input.readInt32());
                            }
                            input.popLimit(limit);
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
                    synchronized (ProcessStatsAvailablePagesProto.class) {
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

    public static ProcessStatsAvailablePagesProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProcessStatsAvailablePagesProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
