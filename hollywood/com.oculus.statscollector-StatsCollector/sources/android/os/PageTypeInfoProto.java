package android.os;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class PageTypeInfoProto extends GeneratedMessageLite<PageTypeInfoProto, Builder> implements PageTypeInfoProtoOrBuilder {
    public static final int BLOCKS_FIELD_NUMBER = 4;
    private static final PageTypeInfoProto DEFAULT_INSTANCE = new PageTypeInfoProto();
    public static final int MIGRATE_TYPES_FIELD_NUMBER = 3;
    public static final int PAGES_PER_BLOCK_FIELD_NUMBER = 2;
    public static final int PAGE_BLOCK_ORDER_FIELD_NUMBER = 1;
    private static volatile Parser<PageTypeInfoProto> PARSER;
    private int bitField0_;
    private Internal.ProtobufList<Block> blocks_ = emptyProtobufList();
    private Internal.ProtobufList<MigrateType> migrateTypes_ = emptyProtobufList();
    private int pageBlockOrder_ = 0;
    private int pagesPerBlock_ = 0;

    public interface BlockOrBuilder extends MessageLiteOrBuilder {
        int getCma();

        int getHighatomic();

        int getIsolate();

        int getMovable();

        int getNode();

        int getReclaimable();

        int getReserve();

        int getUnmovable();

        String getZone();

        ByteString getZoneBytes();

        boolean hasCma();

        boolean hasHighatomic();

        boolean hasIsolate();

        boolean hasMovable();

        boolean hasNode();

        boolean hasReclaimable();

        boolean hasReserve();

        boolean hasUnmovable();

        boolean hasZone();
    }

    public interface MigrateTypeOrBuilder extends MessageLiteOrBuilder {
        int getFreePagesCount(int i);

        int getFreePagesCountCount();

        List<Integer> getFreePagesCountList();

        int getNode();

        String getType();

        ByteString getTypeBytes();

        String getZone();

        ByteString getZoneBytes();

        boolean hasNode();

        boolean hasType();

        boolean hasZone();
    }

    private PageTypeInfoProto() {
    }

    public static final class MigrateType extends GeneratedMessageLite<MigrateType, Builder> implements MigrateTypeOrBuilder {
        private static final MigrateType DEFAULT_INSTANCE = new MigrateType();
        public static final int FREE_PAGES_COUNT_FIELD_NUMBER = 4;
        public static final int NODE_FIELD_NUMBER = 1;
        private static volatile Parser<MigrateType> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 3;
        public static final int ZONE_FIELD_NUMBER = 2;
        private int bitField0_;
        private Internal.IntList freePagesCount_ = emptyIntList();
        private int node_ = 0;
        private String type_ = "";
        private String zone_ = "";

        private MigrateType() {
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public boolean hasNode() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
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

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public boolean hasZone() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public String getZone() {
            return this.zone_;
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
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

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public String getType() {
            return this.type_;
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public ByteString getTypeBytes() {
            return ByteString.copyFromUtf8(this.type_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setType(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.type_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearType() {
            this.bitField0_ &= -5;
            this.type_ = getDefaultInstance().getType();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTypeBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.type_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public List<Integer> getFreePagesCountList() {
            return this.freePagesCount_;
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public int getFreePagesCountCount() {
            return this.freePagesCount_.size();
        }

        @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
        public int getFreePagesCount(int index) {
            return this.freePagesCount_.getInt(index);
        }

        private void ensureFreePagesCountIsMutable() {
            if (!this.freePagesCount_.isModifiable()) {
                this.freePagesCount_ = GeneratedMessageLite.mutableCopy(this.freePagesCount_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFreePagesCount(int index, int value) {
            ensureFreePagesCountIsMutable();
            this.freePagesCount_.setInt(index, value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addFreePagesCount(int value) {
            ensureFreePagesCountIsMutable();
            this.freePagesCount_.addInt(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllFreePagesCount(Iterable<? extends Integer> values) {
            ensureFreePagesCountIsMutable();
            AbstractMessageLite.addAll(values, this.freePagesCount_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFreePagesCount() {
            this.freePagesCount_ = emptyIntList();
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
                output.writeString(3, getType());
            }
            for (int i = 0; i < this.freePagesCount_.size(); i++) {
                output.writeInt32(4, this.freePagesCount_.getInt(i));
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
                size2 += CodedOutputStream.computeStringSize(3, getType());
            }
            int dataSize = 0;
            for (int i = 0; i < this.freePagesCount_.size(); i++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.freePagesCount_.getInt(i));
            }
            int size3 = size2 + dataSize + (getFreePagesCountList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static MigrateType parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MigrateType) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MigrateType parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MigrateType) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MigrateType parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MigrateType) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MigrateType parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MigrateType) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MigrateType parseFrom(InputStream input) throws IOException {
            return (MigrateType) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MigrateType parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MigrateType) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MigrateType parseDelimitedFrom(InputStream input) throws IOException {
            return (MigrateType) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MigrateType parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MigrateType) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MigrateType parseFrom(CodedInputStream input) throws IOException {
            return (MigrateType) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MigrateType parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MigrateType) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MigrateType prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MigrateType, Builder> implements MigrateTypeOrBuilder {
            private Builder() {
                super(MigrateType.DEFAULT_INSTANCE);
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public boolean hasNode() {
                return ((MigrateType) this.instance).hasNode();
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public int getNode() {
                return ((MigrateType) this.instance).getNode();
            }

            public Builder setNode(int value) {
                copyOnWrite();
                ((MigrateType) this.instance).setNode(value);
                return this;
            }

            public Builder clearNode() {
                copyOnWrite();
                ((MigrateType) this.instance).clearNode();
                return this;
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public boolean hasZone() {
                return ((MigrateType) this.instance).hasZone();
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public String getZone() {
                return ((MigrateType) this.instance).getZone();
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public ByteString getZoneBytes() {
                return ((MigrateType) this.instance).getZoneBytes();
            }

            public Builder setZone(String value) {
                copyOnWrite();
                ((MigrateType) this.instance).setZone(value);
                return this;
            }

            public Builder clearZone() {
                copyOnWrite();
                ((MigrateType) this.instance).clearZone();
                return this;
            }

            public Builder setZoneBytes(ByteString value) {
                copyOnWrite();
                ((MigrateType) this.instance).setZoneBytes(value);
                return this;
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public boolean hasType() {
                return ((MigrateType) this.instance).hasType();
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public String getType() {
                return ((MigrateType) this.instance).getType();
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public ByteString getTypeBytes() {
                return ((MigrateType) this.instance).getTypeBytes();
            }

            public Builder setType(String value) {
                copyOnWrite();
                ((MigrateType) this.instance).setType(value);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((MigrateType) this.instance).clearType();
                return this;
            }

            public Builder setTypeBytes(ByteString value) {
                copyOnWrite();
                ((MigrateType) this.instance).setTypeBytes(value);
                return this;
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public List<Integer> getFreePagesCountList() {
                return Collections.unmodifiableList(((MigrateType) this.instance).getFreePagesCountList());
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public int getFreePagesCountCount() {
                return ((MigrateType) this.instance).getFreePagesCountCount();
            }

            @Override // android.os.PageTypeInfoProto.MigrateTypeOrBuilder
            public int getFreePagesCount(int index) {
                return ((MigrateType) this.instance).getFreePagesCount(index);
            }

            public Builder setFreePagesCount(int index, int value) {
                copyOnWrite();
                ((MigrateType) this.instance).setFreePagesCount(index, value);
                return this;
            }

            public Builder addFreePagesCount(int value) {
                copyOnWrite();
                ((MigrateType) this.instance).addFreePagesCount(value);
                return this;
            }

            public Builder addAllFreePagesCount(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((MigrateType) this.instance).addAllFreePagesCount(values);
                return this;
            }

            public Builder clearFreePagesCount() {
                copyOnWrite();
                ((MigrateType) this.instance).clearFreePagesCount();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new MigrateType();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.freePagesCount_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    MigrateType other = (MigrateType) arg1;
                    this.node_ = visitor.visitInt(hasNode(), this.node_, other.hasNode(), other.node_);
                    this.zone_ = visitor.visitString(hasZone(), this.zone_, other.hasZone(), other.zone_);
                    this.type_ = visitor.visitString(hasType(), this.type_, other.hasType(), other.type_);
                    this.freePagesCount_ = visitor.visitIntList(this.freePagesCount_, other.freePagesCount_);
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
                                this.type_ = s2;
                            } else if (tag == 32) {
                                if (!this.freePagesCount_.isModifiable()) {
                                    this.freePagesCount_ = GeneratedMessageLite.mutableCopy(this.freePagesCount_);
                                }
                                this.freePagesCount_.addInt(input.readInt32());
                            } else if (tag == 34) {
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.freePagesCount_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.freePagesCount_ = GeneratedMessageLite.mutableCopy(this.freePagesCount_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.freePagesCount_.addInt(input.readInt32());
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
                        synchronized (MigrateType.class) {
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

        public static MigrateType getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MigrateType> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Block extends GeneratedMessageLite<Block, Builder> implements BlockOrBuilder {
        public static final int CMA_FIELD_NUMBER = 6;
        private static final Block DEFAULT_INSTANCE = new Block();
        public static final int HIGHATOMIC_FIELD_NUMBER = 9;
        public static final int ISOLATE_FIELD_NUMBER = 8;
        public static final int MOVABLE_FIELD_NUMBER = 5;
        public static final int NODE_FIELD_NUMBER = 1;
        private static volatile Parser<Block> PARSER = null;
        public static final int RECLAIMABLE_FIELD_NUMBER = 4;
        public static final int RESERVE_FIELD_NUMBER = 7;
        public static final int UNMOVABLE_FIELD_NUMBER = 3;
        public static final int ZONE_FIELD_NUMBER = 2;
        private int bitField0_;
        private int cma_ = 0;
        private int highatomic_ = 0;
        private int isolate_ = 0;
        private int movable_ = 0;
        private int node_ = 0;
        private int reclaimable_ = 0;
        private int reserve_ = 0;
        private int unmovable_ = 0;
        private String zone_ = "";

        private Block() {
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasNode() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
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

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasZone() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public String getZone() {
            return this.zone_;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
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

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasUnmovable() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public int getUnmovable() {
            return this.unmovable_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUnmovable(int value) {
            this.bitField0_ |= 4;
            this.unmovable_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUnmovable() {
            this.bitField0_ &= -5;
            this.unmovable_ = 0;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasReclaimable() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public int getReclaimable() {
            return this.reclaimable_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReclaimable(int value) {
            this.bitField0_ |= 8;
            this.reclaimable_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearReclaimable() {
            this.bitField0_ &= -9;
            this.reclaimable_ = 0;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasMovable() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public int getMovable() {
            return this.movable_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMovable(int value) {
            this.bitField0_ |= 16;
            this.movable_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMovable() {
            this.bitField0_ &= -17;
            this.movable_ = 0;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasCma() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public int getCma() {
            return this.cma_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCma(int value) {
            this.bitField0_ |= 32;
            this.cma_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCma() {
            this.bitField0_ &= -33;
            this.cma_ = 0;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasReserve() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public int getReserve() {
            return this.reserve_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReserve(int value) {
            this.bitField0_ |= 64;
            this.reserve_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearReserve() {
            this.bitField0_ &= -65;
            this.reserve_ = 0;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasIsolate() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public int getIsolate() {
            return this.isolate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsolate(int value) {
            this.bitField0_ |= 128;
            this.isolate_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsolate() {
            this.bitField0_ &= -129;
            this.isolate_ = 0;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public boolean hasHighatomic() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.os.PageTypeInfoProto.BlockOrBuilder
        public int getHighatomic() {
            return this.highatomic_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHighatomic(int value) {
            this.bitField0_ |= 256;
            this.highatomic_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHighatomic() {
            this.bitField0_ &= -257;
            this.highatomic_ = 0;
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
                output.writeInt32(3, this.unmovable_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.reclaimable_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.movable_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.cma_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.reserve_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(8, this.isolate_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt32(9, this.highatomic_);
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
                size2 += CodedOutputStream.computeInt32Size(3, this.unmovable_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.reclaimable_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.movable_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.cma_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.reserve_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(8, this.isolate_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt32Size(9, this.highatomic_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Block parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Block) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Block parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Block) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Block parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Block) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Block parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Block) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Block parseFrom(InputStream input) throws IOException {
            return (Block) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Block parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Block) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Block parseDelimitedFrom(InputStream input) throws IOException {
            return (Block) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Block parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Block) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Block parseFrom(CodedInputStream input) throws IOException {
            return (Block) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Block parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Block) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Block prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Block, Builder> implements BlockOrBuilder {
            private Builder() {
                super(Block.DEFAULT_INSTANCE);
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasNode() {
                return ((Block) this.instance).hasNode();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public int getNode() {
                return ((Block) this.instance).getNode();
            }

            public Builder setNode(int value) {
                copyOnWrite();
                ((Block) this.instance).setNode(value);
                return this;
            }

            public Builder clearNode() {
                copyOnWrite();
                ((Block) this.instance).clearNode();
                return this;
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasZone() {
                return ((Block) this.instance).hasZone();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public String getZone() {
                return ((Block) this.instance).getZone();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public ByteString getZoneBytes() {
                return ((Block) this.instance).getZoneBytes();
            }

            public Builder setZone(String value) {
                copyOnWrite();
                ((Block) this.instance).setZone(value);
                return this;
            }

            public Builder clearZone() {
                copyOnWrite();
                ((Block) this.instance).clearZone();
                return this;
            }

            public Builder setZoneBytes(ByteString value) {
                copyOnWrite();
                ((Block) this.instance).setZoneBytes(value);
                return this;
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasUnmovable() {
                return ((Block) this.instance).hasUnmovable();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public int getUnmovable() {
                return ((Block) this.instance).getUnmovable();
            }

            public Builder setUnmovable(int value) {
                copyOnWrite();
                ((Block) this.instance).setUnmovable(value);
                return this;
            }

            public Builder clearUnmovable() {
                copyOnWrite();
                ((Block) this.instance).clearUnmovable();
                return this;
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasReclaimable() {
                return ((Block) this.instance).hasReclaimable();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public int getReclaimable() {
                return ((Block) this.instance).getReclaimable();
            }

            public Builder setReclaimable(int value) {
                copyOnWrite();
                ((Block) this.instance).setReclaimable(value);
                return this;
            }

            public Builder clearReclaimable() {
                copyOnWrite();
                ((Block) this.instance).clearReclaimable();
                return this;
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasMovable() {
                return ((Block) this.instance).hasMovable();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public int getMovable() {
                return ((Block) this.instance).getMovable();
            }

            public Builder setMovable(int value) {
                copyOnWrite();
                ((Block) this.instance).setMovable(value);
                return this;
            }

            public Builder clearMovable() {
                copyOnWrite();
                ((Block) this.instance).clearMovable();
                return this;
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasCma() {
                return ((Block) this.instance).hasCma();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public int getCma() {
                return ((Block) this.instance).getCma();
            }

            public Builder setCma(int value) {
                copyOnWrite();
                ((Block) this.instance).setCma(value);
                return this;
            }

            public Builder clearCma() {
                copyOnWrite();
                ((Block) this.instance).clearCma();
                return this;
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasReserve() {
                return ((Block) this.instance).hasReserve();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public int getReserve() {
                return ((Block) this.instance).getReserve();
            }

            public Builder setReserve(int value) {
                copyOnWrite();
                ((Block) this.instance).setReserve(value);
                return this;
            }

            public Builder clearReserve() {
                copyOnWrite();
                ((Block) this.instance).clearReserve();
                return this;
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasIsolate() {
                return ((Block) this.instance).hasIsolate();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public int getIsolate() {
                return ((Block) this.instance).getIsolate();
            }

            public Builder setIsolate(int value) {
                copyOnWrite();
                ((Block) this.instance).setIsolate(value);
                return this;
            }

            public Builder clearIsolate() {
                copyOnWrite();
                ((Block) this.instance).clearIsolate();
                return this;
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public boolean hasHighatomic() {
                return ((Block) this.instance).hasHighatomic();
            }

            @Override // android.os.PageTypeInfoProto.BlockOrBuilder
            public int getHighatomic() {
                return ((Block) this.instance).getHighatomic();
            }

            public Builder setHighatomic(int value) {
                copyOnWrite();
                ((Block) this.instance).setHighatomic(value);
                return this;
            }

            public Builder clearHighatomic() {
                copyOnWrite();
                ((Block) this.instance).clearHighatomic();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Block();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Block other = (Block) arg1;
                    this.node_ = visitor.visitInt(hasNode(), this.node_, other.hasNode(), other.node_);
                    this.zone_ = visitor.visitString(hasZone(), this.zone_, other.hasZone(), other.zone_);
                    this.unmovable_ = visitor.visitInt(hasUnmovable(), this.unmovable_, other.hasUnmovable(), other.unmovable_);
                    this.reclaimable_ = visitor.visitInt(hasReclaimable(), this.reclaimable_, other.hasReclaimable(), other.reclaimable_);
                    this.movable_ = visitor.visitInt(hasMovable(), this.movable_, other.hasMovable(), other.movable_);
                    this.cma_ = visitor.visitInt(hasCma(), this.cma_, other.hasCma(), other.cma_);
                    this.reserve_ = visitor.visitInt(hasReserve(), this.reserve_, other.hasReserve(), other.reserve_);
                    this.isolate_ = visitor.visitInt(hasIsolate(), this.isolate_, other.hasIsolate(), other.isolate_);
                    this.highatomic_ = visitor.visitInt(hasHighatomic(), this.highatomic_, other.hasHighatomic(), other.highatomic_);
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
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.unmovable_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.reclaimable_ = input.readInt32();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.movable_ = input.readInt32();
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.cma_ = input.readInt32();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.reserve_ = input.readInt32();
                            } else if (tag == 64) {
                                this.bitField0_ |= 128;
                                this.isolate_ = input.readInt32();
                            } else if (tag == 72) {
                                this.bitField0_ |= 256;
                                this.highatomic_ = input.readInt32();
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
                        synchronized (Block.class) {
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

        public static Block getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Block> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public boolean hasPageBlockOrder() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public int getPageBlockOrder() {
        return this.pageBlockOrder_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPageBlockOrder(int value) {
        this.bitField0_ |= 1;
        this.pageBlockOrder_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPageBlockOrder() {
        this.bitField0_ &= -2;
        this.pageBlockOrder_ = 0;
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public boolean hasPagesPerBlock() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public int getPagesPerBlock() {
        return this.pagesPerBlock_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPagesPerBlock(int value) {
        this.bitField0_ |= 2;
        this.pagesPerBlock_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPagesPerBlock() {
        this.bitField0_ &= -3;
        this.pagesPerBlock_ = 0;
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public List<MigrateType> getMigrateTypesList() {
        return this.migrateTypes_;
    }

    public List<? extends MigrateTypeOrBuilder> getMigrateTypesOrBuilderList() {
        return this.migrateTypes_;
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public int getMigrateTypesCount() {
        return this.migrateTypes_.size();
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public MigrateType getMigrateTypes(int index) {
        return this.migrateTypes_.get(index);
    }

    public MigrateTypeOrBuilder getMigrateTypesOrBuilder(int index) {
        return this.migrateTypes_.get(index);
    }

    private void ensureMigrateTypesIsMutable() {
        if (!this.migrateTypes_.isModifiable()) {
            this.migrateTypes_ = GeneratedMessageLite.mutableCopy(this.migrateTypes_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMigrateTypes(int index, MigrateType value) {
        if (value != null) {
            ensureMigrateTypesIsMutable();
            this.migrateTypes_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMigrateTypes(int index, MigrateType.Builder builderForValue) {
        ensureMigrateTypesIsMutable();
        this.migrateTypes_.set(index, (MigrateType) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMigrateTypes(MigrateType value) {
        if (value != null) {
            ensureMigrateTypesIsMutable();
            this.migrateTypes_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMigrateTypes(int index, MigrateType value) {
        if (value != null) {
            ensureMigrateTypesIsMutable();
            this.migrateTypes_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMigrateTypes(MigrateType.Builder builderForValue) {
        ensureMigrateTypesIsMutable();
        this.migrateTypes_.add((MigrateType) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addMigrateTypes(int index, MigrateType.Builder builderForValue) {
        ensureMigrateTypesIsMutable();
        this.migrateTypes_.add(index, (MigrateType) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllMigrateTypes(Iterable<? extends MigrateType> values) {
        ensureMigrateTypesIsMutable();
        AbstractMessageLite.addAll(values, this.migrateTypes_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMigrateTypes() {
        this.migrateTypes_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeMigrateTypes(int index) {
        ensureMigrateTypesIsMutable();
        this.migrateTypes_.remove(index);
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public List<Block> getBlocksList() {
        return this.blocks_;
    }

    public List<? extends BlockOrBuilder> getBlocksOrBuilderList() {
        return this.blocks_;
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public int getBlocksCount() {
        return this.blocks_.size();
    }

    @Override // android.os.PageTypeInfoProtoOrBuilder
    public Block getBlocks(int index) {
        return this.blocks_.get(index);
    }

    public BlockOrBuilder getBlocksOrBuilder(int index) {
        return this.blocks_.get(index);
    }

    private void ensureBlocksIsMutable() {
        if (!this.blocks_.isModifiable()) {
            this.blocks_ = GeneratedMessageLite.mutableCopy(this.blocks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBlocks(int index, Block value) {
        if (value != null) {
            ensureBlocksIsMutable();
            this.blocks_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBlocks(int index, Block.Builder builderForValue) {
        ensureBlocksIsMutable();
        this.blocks_.set(index, (Block) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBlocks(Block value) {
        if (value != null) {
            ensureBlocksIsMutable();
            this.blocks_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBlocks(int index, Block value) {
        if (value != null) {
            ensureBlocksIsMutable();
            this.blocks_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBlocks(Block.Builder builderForValue) {
        ensureBlocksIsMutable();
        this.blocks_.add((Block) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBlocks(int index, Block.Builder builderForValue) {
        ensureBlocksIsMutable();
        this.blocks_.add(index, (Block) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBlocks(Iterable<? extends Block> values) {
        ensureBlocksIsMutable();
        AbstractMessageLite.addAll(values, this.blocks_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBlocks() {
        this.blocks_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeBlocks(int index) {
        ensureBlocksIsMutable();
        this.blocks_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeInt32(1, this.pageBlockOrder_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.pagesPerBlock_);
        }
        for (int i = 0; i < this.migrateTypes_.size(); i++) {
            output.writeMessage(3, this.migrateTypes_.get(i));
        }
        for (int i2 = 0; i2 < this.blocks_.size(); i2++) {
            output.writeMessage(4, this.blocks_.get(i2));
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
            size2 = 0 + CodedOutputStream.computeInt32Size(1, this.pageBlockOrder_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.pagesPerBlock_);
        }
        for (int i = 0; i < this.migrateTypes_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.migrateTypes_.get(i));
        }
        for (int i2 = 0; i2 < this.blocks_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.blocks_.get(i2));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PageTypeInfoProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PageTypeInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PageTypeInfoProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PageTypeInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PageTypeInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PageTypeInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PageTypeInfoProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PageTypeInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PageTypeInfoProto parseFrom(InputStream input) throws IOException {
        return (PageTypeInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PageTypeInfoProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PageTypeInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PageTypeInfoProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PageTypeInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PageTypeInfoProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PageTypeInfoProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PageTypeInfoProto parseFrom(CodedInputStream input) throws IOException {
        return (PageTypeInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PageTypeInfoProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PageTypeInfoProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PageTypeInfoProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PageTypeInfoProto, Builder> implements PageTypeInfoProtoOrBuilder {
        private Builder() {
            super(PageTypeInfoProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public boolean hasPageBlockOrder() {
            return ((PageTypeInfoProto) this.instance).hasPageBlockOrder();
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public int getPageBlockOrder() {
            return ((PageTypeInfoProto) this.instance).getPageBlockOrder();
        }

        public Builder setPageBlockOrder(int value) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).setPageBlockOrder(value);
            return this;
        }

        public Builder clearPageBlockOrder() {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).clearPageBlockOrder();
            return this;
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public boolean hasPagesPerBlock() {
            return ((PageTypeInfoProto) this.instance).hasPagesPerBlock();
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public int getPagesPerBlock() {
            return ((PageTypeInfoProto) this.instance).getPagesPerBlock();
        }

        public Builder setPagesPerBlock(int value) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).setPagesPerBlock(value);
            return this;
        }

        public Builder clearPagesPerBlock() {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).clearPagesPerBlock();
            return this;
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public List<MigrateType> getMigrateTypesList() {
            return Collections.unmodifiableList(((PageTypeInfoProto) this.instance).getMigrateTypesList());
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public int getMigrateTypesCount() {
            return ((PageTypeInfoProto) this.instance).getMigrateTypesCount();
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public MigrateType getMigrateTypes(int index) {
            return ((PageTypeInfoProto) this.instance).getMigrateTypes(index);
        }

        public Builder setMigrateTypes(int index, MigrateType value) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).setMigrateTypes((PageTypeInfoProto) index, (int) value);
            return this;
        }

        public Builder setMigrateTypes(int index, MigrateType.Builder builderForValue) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).setMigrateTypes((PageTypeInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addMigrateTypes(MigrateType value) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addMigrateTypes((PageTypeInfoProto) value);
            return this;
        }

        public Builder addMigrateTypes(int index, MigrateType value) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addMigrateTypes((PageTypeInfoProto) index, (int) value);
            return this;
        }

        public Builder addMigrateTypes(MigrateType.Builder builderForValue) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addMigrateTypes((PageTypeInfoProto) builderForValue);
            return this;
        }

        public Builder addMigrateTypes(int index, MigrateType.Builder builderForValue) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addMigrateTypes((PageTypeInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllMigrateTypes(Iterable<? extends MigrateType> values) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addAllMigrateTypes(values);
            return this;
        }

        public Builder clearMigrateTypes() {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).clearMigrateTypes();
            return this;
        }

        public Builder removeMigrateTypes(int index) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).removeMigrateTypes(index);
            return this;
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public List<Block> getBlocksList() {
            return Collections.unmodifiableList(((PageTypeInfoProto) this.instance).getBlocksList());
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public int getBlocksCount() {
            return ((PageTypeInfoProto) this.instance).getBlocksCount();
        }

        @Override // android.os.PageTypeInfoProtoOrBuilder
        public Block getBlocks(int index) {
            return ((PageTypeInfoProto) this.instance).getBlocks(index);
        }

        public Builder setBlocks(int index, Block value) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).setBlocks((PageTypeInfoProto) index, (int) value);
            return this;
        }

        public Builder setBlocks(int index, Block.Builder builderForValue) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).setBlocks((PageTypeInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addBlocks(Block value) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addBlocks((PageTypeInfoProto) value);
            return this;
        }

        public Builder addBlocks(int index, Block value) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addBlocks((PageTypeInfoProto) index, (int) value);
            return this;
        }

        public Builder addBlocks(Block.Builder builderForValue) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addBlocks((PageTypeInfoProto) builderForValue);
            return this;
        }

        public Builder addBlocks(int index, Block.Builder builderForValue) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addBlocks((PageTypeInfoProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllBlocks(Iterable<? extends Block> values) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).addAllBlocks(values);
            return this;
        }

        public Builder clearBlocks() {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).clearBlocks();
            return this;
        }

        public Builder removeBlocks(int index) {
            copyOnWrite();
            ((PageTypeInfoProto) this.instance).removeBlocks(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PageTypeInfoProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.migrateTypes_.makeImmutable();
                this.blocks_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PageTypeInfoProto other = (PageTypeInfoProto) arg1;
                this.pageBlockOrder_ = visitor.visitInt(hasPageBlockOrder(), this.pageBlockOrder_, other.hasPageBlockOrder(), other.pageBlockOrder_);
                this.pagesPerBlock_ = visitor.visitInt(hasPagesPerBlock(), this.pagesPerBlock_, other.hasPagesPerBlock(), other.pagesPerBlock_);
                this.migrateTypes_ = visitor.visitList(this.migrateTypes_, other.migrateTypes_);
                this.blocks_ = visitor.visitList(this.blocks_, other.blocks_);
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
                            this.pageBlockOrder_ = input.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.pagesPerBlock_ = input.readInt32();
                        } else if (tag == 26) {
                            if (!this.migrateTypes_.isModifiable()) {
                                this.migrateTypes_ = GeneratedMessageLite.mutableCopy(this.migrateTypes_);
                            }
                            this.migrateTypes_.add((MigrateType) input.readMessage(MigrateType.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            if (!this.blocks_.isModifiable()) {
                                this.blocks_ = GeneratedMessageLite.mutableCopy(this.blocks_);
                            }
                            this.blocks_.add((Block) input.readMessage(Block.parser(), extensionRegistry));
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
                    synchronized (PageTypeInfoProto.class) {
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

    public static PageTypeInfoProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PageTypeInfoProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
