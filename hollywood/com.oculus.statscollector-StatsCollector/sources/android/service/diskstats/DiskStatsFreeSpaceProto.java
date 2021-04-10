package android.service.diskstats;

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

public final class DiskStatsFreeSpaceProto extends GeneratedMessageLite<DiskStatsFreeSpaceProto, Builder> implements DiskStatsFreeSpaceProtoOrBuilder {
    public static final int AVAILABLE_SPACE_KB_FIELD_NUMBER = 2;
    private static final DiskStatsFreeSpaceProto DEFAULT_INSTANCE = new DiskStatsFreeSpaceProto();
    public static final int FOLDER_FIELD_NUMBER = 1;
    private static volatile Parser<DiskStatsFreeSpaceProto> PARSER = null;
    public static final int TOTAL_SPACE_KB_FIELD_NUMBER = 3;
    private long availableSpaceKb_ = 0;
    private int bitField0_;
    private int folder_ = 0;
    private long totalSpaceKb_ = 0;

    private DiskStatsFreeSpaceProto() {
    }

    public enum Folder implements Internal.EnumLite {
        FOLDER_DATA(0),
        FOLDER_CACHE(1),
        FOLDER_SYSTEM(2);
        
        public static final int FOLDER_CACHE_VALUE = 1;
        public static final int FOLDER_DATA_VALUE = 0;
        public static final int FOLDER_SYSTEM_VALUE = 2;
        private static final Internal.EnumLiteMap<Folder> internalValueMap = new Internal.EnumLiteMap<Folder>() {
            /* class android.service.diskstats.DiskStatsFreeSpaceProto.Folder.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Folder findValueByNumber(int number) {
                return Folder.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Folder valueOf(int value2) {
            return forNumber(value2);
        }

        public static Folder forNumber(int value2) {
            if (value2 == 0) {
                return FOLDER_DATA;
            }
            if (value2 == 1) {
                return FOLDER_CACHE;
            }
            if (value2 != 2) {
                return null;
            }
            return FOLDER_SYSTEM;
        }

        public static Internal.EnumLiteMap<Folder> internalGetValueMap() {
            return internalValueMap;
        }

        private Folder(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
    public boolean hasFolder() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
    public Folder getFolder() {
        Folder result = Folder.forNumber(this.folder_);
        return result == null ? Folder.FOLDER_DATA : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFolder(Folder value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.folder_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFolder() {
        this.bitField0_ &= -2;
        this.folder_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
    public boolean hasAvailableSpaceKb() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
    public long getAvailableSpaceKb() {
        return this.availableSpaceKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAvailableSpaceKb(long value) {
        this.bitField0_ |= 2;
        this.availableSpaceKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAvailableSpaceKb() {
        this.bitField0_ &= -3;
        this.availableSpaceKb_ = 0;
    }

    @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
    public boolean hasTotalSpaceKb() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
    public long getTotalSpaceKb() {
        return this.totalSpaceKb_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTotalSpaceKb(long value) {
        this.bitField0_ |= 4;
        this.totalSpaceKb_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTotalSpaceKb() {
        this.bitField0_ &= -5;
        this.totalSpaceKb_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.folder_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt64(2, this.availableSpaceKb_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt64(3, this.totalSpaceKb_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.folder_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt64Size(2, this.availableSpaceKb_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt64Size(3, this.totalSpaceKb_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static DiskStatsFreeSpaceProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (DiskStatsFreeSpaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DiskStatsFreeSpaceProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DiskStatsFreeSpaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DiskStatsFreeSpaceProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (DiskStatsFreeSpaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static DiskStatsFreeSpaceProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (DiskStatsFreeSpaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static DiskStatsFreeSpaceProto parseFrom(InputStream input) throws IOException {
        return (DiskStatsFreeSpaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsFreeSpaceProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsFreeSpaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DiskStatsFreeSpaceProto parseDelimitedFrom(InputStream input) throws IOException {
        return (DiskStatsFreeSpaceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsFreeSpaceProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsFreeSpaceProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static DiskStatsFreeSpaceProto parseFrom(CodedInputStream input) throws IOException {
        return (DiskStatsFreeSpaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static DiskStatsFreeSpaceProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (DiskStatsFreeSpaceProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DiskStatsFreeSpaceProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DiskStatsFreeSpaceProto, Builder> implements DiskStatsFreeSpaceProtoOrBuilder {
        private Builder() {
            super(DiskStatsFreeSpaceProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
        public boolean hasFolder() {
            return ((DiskStatsFreeSpaceProto) this.instance).hasFolder();
        }

        @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
        public Folder getFolder() {
            return ((DiskStatsFreeSpaceProto) this.instance).getFolder();
        }

        public Builder setFolder(Folder value) {
            copyOnWrite();
            ((DiskStatsFreeSpaceProto) this.instance).setFolder(value);
            return this;
        }

        public Builder clearFolder() {
            copyOnWrite();
            ((DiskStatsFreeSpaceProto) this.instance).clearFolder();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
        public boolean hasAvailableSpaceKb() {
            return ((DiskStatsFreeSpaceProto) this.instance).hasAvailableSpaceKb();
        }

        @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
        public long getAvailableSpaceKb() {
            return ((DiskStatsFreeSpaceProto) this.instance).getAvailableSpaceKb();
        }

        public Builder setAvailableSpaceKb(long value) {
            copyOnWrite();
            ((DiskStatsFreeSpaceProto) this.instance).setAvailableSpaceKb(value);
            return this;
        }

        public Builder clearAvailableSpaceKb() {
            copyOnWrite();
            ((DiskStatsFreeSpaceProto) this.instance).clearAvailableSpaceKb();
            return this;
        }

        @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
        public boolean hasTotalSpaceKb() {
            return ((DiskStatsFreeSpaceProto) this.instance).hasTotalSpaceKb();
        }

        @Override // android.service.diskstats.DiskStatsFreeSpaceProtoOrBuilder
        public long getTotalSpaceKb() {
            return ((DiskStatsFreeSpaceProto) this.instance).getTotalSpaceKb();
        }

        public Builder setTotalSpaceKb(long value) {
            copyOnWrite();
            ((DiskStatsFreeSpaceProto) this.instance).setTotalSpaceKb(value);
            return this;
        }

        public Builder clearTotalSpaceKb() {
            copyOnWrite();
            ((DiskStatsFreeSpaceProto) this.instance).clearTotalSpaceKb();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new DiskStatsFreeSpaceProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                DiskStatsFreeSpaceProto other = (DiskStatsFreeSpaceProto) arg1;
                this.folder_ = visitor.visitInt(hasFolder(), this.folder_, other.hasFolder(), other.folder_);
                this.availableSpaceKb_ = visitor.visitLong(hasAvailableSpaceKb(), this.availableSpaceKb_, other.hasAvailableSpaceKb(), other.availableSpaceKb_);
                this.totalSpaceKb_ = visitor.visitLong(hasTotalSpaceKb(), this.totalSpaceKb_, other.hasTotalSpaceKb(), other.totalSpaceKb_);
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
                            int rawValue = input.readEnum();
                            if (Folder.forNumber(rawValue) == null) {
                                super.mergeVarintField(1, rawValue);
                            } else {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.folder_ = rawValue;
                            }
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.availableSpaceKb_ = input.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.totalSpaceKb_ = input.readInt64();
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
                    synchronized (DiskStatsFreeSpaceProto.class) {
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

    public static DiskStatsFreeSpaceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DiskStatsFreeSpaceProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
