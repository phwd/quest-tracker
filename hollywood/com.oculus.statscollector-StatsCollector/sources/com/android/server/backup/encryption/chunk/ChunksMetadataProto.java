package com.android.server.backup.encryption.chunk;

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

public final class ChunksMetadataProto {

    public interface ChunkListingOrBuilder extends MessageLiteOrBuilder {
        ChunkOrderingType getChunkOrderingType();

        Chunk getChunks(int i);

        int getChunksCount();

        List<Chunk> getChunksList();

        CipherType getCipherType();

        String getDocumentId();

        ByteString getDocumentIdBytes();

        ByteString getFingerprintMixerSalt();

        boolean hasChunkOrderingType();

        boolean hasCipherType();

        boolean hasDocumentId();

        boolean hasFingerprintMixerSalt();
    }

    public interface ChunkOrBuilder extends MessageLiteOrBuilder {
        ByteString getHash();

        int getLength();

        boolean hasHash();

        boolean hasLength();
    }

    public interface ChunkOrderingOrBuilder extends MessageLiteOrBuilder {
        ByteString getChecksum();

        int getStarts(int i);

        int getStartsCount();

        List<Integer> getStartsList();

        boolean hasChecksum();
    }

    public interface ChunksMetadataOrBuilder extends MessageLiteOrBuilder {
        ChecksumType getChecksumType();

        ByteString getChunkOrdering();

        ChunkOrderingType getChunkOrderingType();

        CipherType getCipherType();

        boolean hasChecksumType();

        boolean hasChunkOrdering();

        boolean hasChunkOrderingType();

        boolean hasCipherType();
    }

    private ChunksMetadataProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum CipherType implements Internal.EnumLite {
        UNKNOWN_CIPHER_TYPE(0),
        AES_256_GCM(1);
        
        public static final int AES_256_GCM_VALUE = 1;
        public static final int UNKNOWN_CIPHER_TYPE_VALUE = 0;
        private static final Internal.EnumLiteMap<CipherType> internalValueMap = new Internal.EnumLiteMap<CipherType>() {
            /* class com.android.server.backup.encryption.chunk.ChunksMetadataProto.CipherType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public CipherType findValueByNumber(int number) {
                return CipherType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CipherType valueOf(int value2) {
            return forNumber(value2);
        }

        public static CipherType forNumber(int value2) {
            if (value2 == 0) {
                return UNKNOWN_CIPHER_TYPE;
            }
            if (value2 != 1) {
                return null;
            }
            return AES_256_GCM;
        }

        public static Internal.EnumLiteMap<CipherType> internalGetValueMap() {
            return internalValueMap;
        }

        private CipherType(int value2) {
            this.value = value2;
        }
    }

    public enum ChecksumType implements Internal.EnumLite {
        UNKNOWN_CHECKSUM_TYPE(0),
        SHA_256(1);
        
        public static final int SHA_256_VALUE = 1;
        public static final int UNKNOWN_CHECKSUM_TYPE_VALUE = 0;
        private static final Internal.EnumLiteMap<ChecksumType> internalValueMap = new Internal.EnumLiteMap<ChecksumType>() {
            /* class com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChecksumType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ChecksumType findValueByNumber(int number) {
                return ChecksumType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ChecksumType valueOf(int value2) {
            return forNumber(value2);
        }

        public static ChecksumType forNumber(int value2) {
            if (value2 == 0) {
                return UNKNOWN_CHECKSUM_TYPE;
            }
            if (value2 != 1) {
                return null;
            }
            return SHA_256;
        }

        public static Internal.EnumLiteMap<ChecksumType> internalGetValueMap() {
            return internalValueMap;
        }

        private ChecksumType(int value2) {
            this.value = value2;
        }
    }

    public enum ChunkOrderingType implements Internal.EnumLite {
        CHUNK_ORDERING_TYPE_UNSPECIFIED(0),
        EXPLICIT_STARTS(1),
        INLINE_LENGTHS(2);
        
        public static final int CHUNK_ORDERING_TYPE_UNSPECIFIED_VALUE = 0;
        public static final int EXPLICIT_STARTS_VALUE = 1;
        public static final int INLINE_LENGTHS_VALUE = 2;
        private static final Internal.EnumLiteMap<ChunkOrderingType> internalValueMap = new Internal.EnumLiteMap<ChunkOrderingType>() {
            /* class com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingType.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ChunkOrderingType findValueByNumber(int number) {
                return ChunkOrderingType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ChunkOrderingType valueOf(int value2) {
            return forNumber(value2);
        }

        public static ChunkOrderingType forNumber(int value2) {
            if (value2 == 0) {
                return CHUNK_ORDERING_TYPE_UNSPECIFIED;
            }
            if (value2 == 1) {
                return EXPLICIT_STARTS;
            }
            if (value2 != 2) {
                return null;
            }
            return INLINE_LENGTHS;
        }

        public static Internal.EnumLiteMap<ChunkOrderingType> internalGetValueMap() {
            return internalValueMap;
        }

        private ChunkOrderingType(int value2) {
            this.value = value2;
        }
    }

    public static final class Chunk extends GeneratedMessageLite<Chunk, Builder> implements ChunkOrBuilder {
        private static final Chunk DEFAULT_INSTANCE = new Chunk();
        public static final int HASH_FIELD_NUMBER = 1;
        public static final int LENGTH_FIELD_NUMBER = 2;
        private static volatile Parser<Chunk> PARSER;
        private int bitField0_;
        private ByteString hash_ = ByteString.EMPTY;
        private int length_ = 0;

        private Chunk() {
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrBuilder
        public boolean hasHash() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrBuilder
        public ByteString getHash() {
            return this.hash_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHash(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.hash_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHash() {
            this.bitField0_ &= -2;
            this.hash_ = getDefaultInstance().getHash();
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrBuilder
        public boolean hasLength() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrBuilder
        public int getLength() {
            return this.length_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLength(int value) {
            this.bitField0_ |= 2;
            this.length_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLength() {
            this.bitField0_ &= -3;
            this.length_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, this.hash_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.length_);
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
                size2 = 0 + CodedOutputStream.computeBytesSize(1, this.hash_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.length_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Chunk parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Chunk) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Chunk parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Chunk) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Chunk parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Chunk) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Chunk parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Chunk) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Chunk parseFrom(InputStream input) throws IOException {
            return (Chunk) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Chunk parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Chunk) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Chunk parseDelimitedFrom(InputStream input) throws IOException {
            return (Chunk) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Chunk parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Chunk) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Chunk parseFrom(CodedInputStream input) throws IOException {
            return (Chunk) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Chunk parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Chunk) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Chunk prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Chunk, Builder> implements ChunkOrBuilder {
            private Builder() {
                super(Chunk.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrBuilder
            public boolean hasHash() {
                return ((Chunk) this.instance).hasHash();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrBuilder
            public ByteString getHash() {
                return ((Chunk) this.instance).getHash();
            }

            public Builder setHash(ByteString value) {
                copyOnWrite();
                ((Chunk) this.instance).setHash(value);
                return this;
            }

            public Builder clearHash() {
                copyOnWrite();
                ((Chunk) this.instance).clearHash();
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrBuilder
            public boolean hasLength() {
                return ((Chunk) this.instance).hasLength();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrBuilder
            public int getLength() {
                return ((Chunk) this.instance).getLength();
            }

            public Builder setLength(int value) {
                copyOnWrite();
                ((Chunk) this.instance).setLength(value);
                return this;
            }

            public Builder clearLength() {
                copyOnWrite();
                ((Chunk) this.instance).clearLength();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Chunk();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Chunk other = (Chunk) arg1;
                    this.hash_ = visitor.visitByteString(hasHash(), this.hash_, other.hasHash(), other.hash_);
                    this.length_ = visitor.visitInt(hasLength(), this.length_, other.hasLength(), other.length_);
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
                            } else if (tag == 10) {
                                this.bitField0_ |= 1;
                                this.hash_ = input.readBytes();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.length_ = input.readInt32();
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
                        synchronized (Chunk.class) {
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

        public static Chunk getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Chunk> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ChunkListing extends GeneratedMessageLite<ChunkListing, Builder> implements ChunkListingOrBuilder {
        public static final int CHUNKS_FIELD_NUMBER = 1;
        public static final int CHUNK_ORDERING_TYPE_FIELD_NUMBER = 5;
        public static final int CIPHER_TYPE_FIELD_NUMBER = 2;
        private static final ChunkListing DEFAULT_INSTANCE = new ChunkListing();
        public static final int DOCUMENT_ID_FIELD_NUMBER = 3;
        public static final int FINGERPRINT_MIXER_SALT_FIELD_NUMBER = 4;
        private static volatile Parser<ChunkListing> PARSER;
        private int bitField0_;
        private int chunkOrderingType_ = 0;
        private Internal.ProtobufList<Chunk> chunks_ = emptyProtobufList();
        private int cipherType_ = 0;
        private String documentId_ = "";
        private ByteString fingerprintMixerSalt_ = ByteString.EMPTY;

        private ChunkListing() {
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public List<Chunk> getChunksList() {
            return this.chunks_;
        }

        public List<? extends ChunkOrBuilder> getChunksOrBuilderList() {
            return this.chunks_;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public int getChunksCount() {
            return this.chunks_.size();
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public Chunk getChunks(int index) {
            return this.chunks_.get(index);
        }

        public ChunkOrBuilder getChunksOrBuilder(int index) {
            return this.chunks_.get(index);
        }

        private void ensureChunksIsMutable() {
            if (!this.chunks_.isModifiable()) {
                this.chunks_ = GeneratedMessageLite.mutableCopy(this.chunks_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChunks(int index, Chunk value) {
            if (value != null) {
                ensureChunksIsMutable();
                this.chunks_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChunks(int index, Chunk.Builder builderForValue) {
            ensureChunksIsMutable();
            this.chunks_.set(index, (Chunk) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChunks(Chunk value) {
            if (value != null) {
                ensureChunksIsMutable();
                this.chunks_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChunks(int index, Chunk value) {
            if (value != null) {
                ensureChunksIsMutable();
                this.chunks_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChunks(Chunk.Builder builderForValue) {
            ensureChunksIsMutable();
            this.chunks_.add((Chunk) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChunks(int index, Chunk.Builder builderForValue) {
            ensureChunksIsMutable();
            this.chunks_.add(index, (Chunk) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllChunks(Iterable<? extends Chunk> values) {
            ensureChunksIsMutable();
            AbstractMessageLite.addAll(values, this.chunks_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChunks() {
            this.chunks_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeChunks(int index) {
            ensureChunksIsMutable();
            this.chunks_.remove(index);
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public boolean hasCipherType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public CipherType getCipherType() {
            CipherType result = CipherType.forNumber(this.cipherType_);
            return result == null ? CipherType.UNKNOWN_CIPHER_TYPE : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCipherType(CipherType value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.cipherType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCipherType() {
            this.bitField0_ &= -2;
            this.cipherType_ = 0;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public boolean hasChunkOrderingType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public ChunkOrderingType getChunkOrderingType() {
            ChunkOrderingType result = ChunkOrderingType.forNumber(this.chunkOrderingType_);
            return result == null ? ChunkOrderingType.CHUNK_ORDERING_TYPE_UNSPECIFIED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChunkOrderingType(ChunkOrderingType value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.chunkOrderingType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChunkOrderingType() {
            this.bitField0_ &= -3;
            this.chunkOrderingType_ = 0;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public boolean hasDocumentId() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public String getDocumentId() {
            return this.documentId_;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public ByteString getDocumentIdBytes() {
            return ByteString.copyFromUtf8(this.documentId_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDocumentId(String value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.documentId_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDocumentId() {
            this.bitField0_ &= -5;
            this.documentId_ = getDefaultInstance().getDocumentId();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDocumentIdBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.documentId_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public boolean hasFingerprintMixerSalt() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
        public ByteString getFingerprintMixerSalt() {
            return this.fingerprintMixerSalt_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFingerprintMixerSalt(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.fingerprintMixerSalt_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearFingerprintMixerSalt() {
            this.bitField0_ &= -9;
            this.fingerprintMixerSalt_ = getDefaultInstance().getFingerprintMixerSalt();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            for (int i = 0; i < this.chunks_.size(); i++) {
                output.writeMessage(1, this.chunks_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(2, this.cipherType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeString(3, getDocumentId());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, this.fingerprintMixerSalt_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(5, this.chunkOrderingType_);
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
            for (int i = 0; i < this.chunks_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.chunks_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeEnumSize(2, this.cipherType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeStringSize(3, getDocumentId());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, this.fingerprintMixerSalt_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(5, this.chunkOrderingType_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ChunkListing parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ChunkListing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ChunkListing parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ChunkListing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ChunkListing parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ChunkListing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ChunkListing parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ChunkListing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ChunkListing parseFrom(InputStream input) throws IOException {
            return (ChunkListing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunkListing parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunkListing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ChunkListing parseDelimitedFrom(InputStream input) throws IOException {
            return (ChunkListing) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunkListing parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunkListing) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ChunkListing parseFrom(CodedInputStream input) throws IOException {
            return (ChunkListing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunkListing parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunkListing) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ChunkListing prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ChunkListing, Builder> implements ChunkListingOrBuilder {
            private Builder() {
                super(ChunkListing.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public List<Chunk> getChunksList() {
                return Collections.unmodifiableList(((ChunkListing) this.instance).getChunksList());
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public int getChunksCount() {
                return ((ChunkListing) this.instance).getChunksCount();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public Chunk getChunks(int index) {
                return ((ChunkListing) this.instance).getChunks(index);
            }

            public Builder setChunks(int index, Chunk value) {
                copyOnWrite();
                ((ChunkListing) this.instance).setChunks((ChunkListing) index, (int) value);
                return this;
            }

            public Builder setChunks(int index, Chunk.Builder builderForValue) {
                copyOnWrite();
                ((ChunkListing) this.instance).setChunks((ChunkListing) index, (int) builderForValue);
                return this;
            }

            public Builder addChunks(Chunk value) {
                copyOnWrite();
                ((ChunkListing) this.instance).addChunks((ChunkListing) value);
                return this;
            }

            public Builder addChunks(int index, Chunk value) {
                copyOnWrite();
                ((ChunkListing) this.instance).addChunks((ChunkListing) index, (int) value);
                return this;
            }

            public Builder addChunks(Chunk.Builder builderForValue) {
                copyOnWrite();
                ((ChunkListing) this.instance).addChunks((ChunkListing) builderForValue);
                return this;
            }

            public Builder addChunks(int index, Chunk.Builder builderForValue) {
                copyOnWrite();
                ((ChunkListing) this.instance).addChunks((ChunkListing) index, (int) builderForValue);
                return this;
            }

            public Builder addAllChunks(Iterable<? extends Chunk> values) {
                copyOnWrite();
                ((ChunkListing) this.instance).addAllChunks(values);
                return this;
            }

            public Builder clearChunks() {
                copyOnWrite();
                ((ChunkListing) this.instance).clearChunks();
                return this;
            }

            public Builder removeChunks(int index) {
                copyOnWrite();
                ((ChunkListing) this.instance).removeChunks(index);
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public boolean hasCipherType() {
                return ((ChunkListing) this.instance).hasCipherType();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public CipherType getCipherType() {
                return ((ChunkListing) this.instance).getCipherType();
            }

            public Builder setCipherType(CipherType value) {
                copyOnWrite();
                ((ChunkListing) this.instance).setCipherType(value);
                return this;
            }

            public Builder clearCipherType() {
                copyOnWrite();
                ((ChunkListing) this.instance).clearCipherType();
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public boolean hasChunkOrderingType() {
                return ((ChunkListing) this.instance).hasChunkOrderingType();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public ChunkOrderingType getChunkOrderingType() {
                return ((ChunkListing) this.instance).getChunkOrderingType();
            }

            public Builder setChunkOrderingType(ChunkOrderingType value) {
                copyOnWrite();
                ((ChunkListing) this.instance).setChunkOrderingType(value);
                return this;
            }

            public Builder clearChunkOrderingType() {
                copyOnWrite();
                ((ChunkListing) this.instance).clearChunkOrderingType();
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public boolean hasDocumentId() {
                return ((ChunkListing) this.instance).hasDocumentId();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public String getDocumentId() {
                return ((ChunkListing) this.instance).getDocumentId();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public ByteString getDocumentIdBytes() {
                return ((ChunkListing) this.instance).getDocumentIdBytes();
            }

            public Builder setDocumentId(String value) {
                copyOnWrite();
                ((ChunkListing) this.instance).setDocumentId(value);
                return this;
            }

            public Builder clearDocumentId() {
                copyOnWrite();
                ((ChunkListing) this.instance).clearDocumentId();
                return this;
            }

            public Builder setDocumentIdBytes(ByteString value) {
                copyOnWrite();
                ((ChunkListing) this.instance).setDocumentIdBytes(value);
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public boolean hasFingerprintMixerSalt() {
                return ((ChunkListing) this.instance).hasFingerprintMixerSalt();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkListingOrBuilder
            public ByteString getFingerprintMixerSalt() {
                return ((ChunkListing) this.instance).getFingerprintMixerSalt();
            }

            public Builder setFingerprintMixerSalt(ByteString value) {
                copyOnWrite();
                ((ChunkListing) this.instance).setFingerprintMixerSalt(value);
                return this;
            }

            public Builder clearFingerprintMixerSalt() {
                copyOnWrite();
                ((ChunkListing) this.instance).clearFingerprintMixerSalt();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ChunkListing();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.chunks_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ChunkListing other = (ChunkListing) arg1;
                    this.chunks_ = visitor.visitList(this.chunks_, other.chunks_);
                    this.cipherType_ = visitor.visitInt(hasCipherType(), this.cipherType_, other.hasCipherType(), other.cipherType_);
                    this.chunkOrderingType_ = visitor.visitInt(hasChunkOrderingType(), this.chunkOrderingType_, other.hasChunkOrderingType(), other.chunkOrderingType_);
                    this.documentId_ = visitor.visitString(hasDocumentId(), this.documentId_, other.hasDocumentId(), other.documentId_);
                    this.fingerprintMixerSalt_ = visitor.visitByteString(hasFingerprintMixerSalt(), this.fingerprintMixerSalt_, other.hasFingerprintMixerSalt(), other.fingerprintMixerSalt_);
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
                                if (!this.chunks_.isModifiable()) {
                                    this.chunks_ = GeneratedMessageLite.mutableCopy(this.chunks_);
                                }
                                this.chunks_.add((Chunk) input.readMessage(Chunk.parser(), extensionRegistry));
                            } else if (tag == 16) {
                                int rawValue = input.readEnum();
                                if (CipherType.forNumber(rawValue) == null) {
                                    super.mergeVarintField(2, rawValue);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.cipherType_ = rawValue;
                                }
                            } else if (tag == 26) {
                                String s = input.readString();
                                this.bitField0_ |= 4;
                                this.documentId_ = s;
                            } else if (tag == 34) {
                                this.bitField0_ |= 8;
                                this.fingerprintMixerSalt_ = input.readBytes();
                            } else if (tag == 40) {
                                int rawValue2 = input.readEnum();
                                if (ChunkOrderingType.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(5, rawValue2);
                                } else {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.chunkOrderingType_ = rawValue2;
                                }
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
                        synchronized (ChunkListing.class) {
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

        public static ChunkListing getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ChunkListing> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ChunkOrdering extends GeneratedMessageLite<ChunkOrdering, Builder> implements ChunkOrderingOrBuilder {
        public static final int CHECKSUM_FIELD_NUMBER = 2;
        private static final ChunkOrdering DEFAULT_INSTANCE = new ChunkOrdering();
        private static volatile Parser<ChunkOrdering> PARSER = null;
        public static final int STARTS_FIELD_NUMBER = 1;
        private int bitField0_;
        private ByteString checksum_ = ByteString.EMPTY;
        private int startsMemoizedSerializedSize = -1;
        private Internal.IntList starts_ = emptyIntList();

        private ChunkOrdering() {
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
        public List<Integer> getStartsList() {
            return this.starts_;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
        public int getStartsCount() {
            return this.starts_.size();
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
        public int getStarts(int index) {
            return this.starts_.getInt(index);
        }

        private void ensureStartsIsMutable() {
            if (!this.starts_.isModifiable()) {
                this.starts_ = GeneratedMessageLite.mutableCopy(this.starts_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setStarts(int index, int value) {
            ensureStartsIsMutable();
            this.starts_.setInt(index, value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addStarts(int value) {
            ensureStartsIsMutable();
            this.starts_.addInt(value);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllStarts(Iterable<? extends Integer> values) {
            ensureStartsIsMutable();
            AbstractMessageLite.addAll(values, this.starts_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearStarts() {
            this.starts_ = emptyIntList();
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
        public boolean hasChecksum() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
        public ByteString getChecksum() {
            return this.checksum_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChecksum(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.checksum_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChecksum() {
            this.bitField0_ &= -2;
            this.checksum_ = getDefaultInstance().getChecksum();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if (getStartsList().size() > 0) {
                output.writeRawVarint32(10);
                output.writeRawVarint32(this.startsMemoizedSerializedSize);
            }
            for (int i = 0; i < this.starts_.size(); i++) {
                output.writeInt32NoTag(this.starts_.getInt(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(2, this.checksum_);
            }
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int dataSize = 0;
            for (int i = 0; i < this.starts_.size(); i++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.starts_.getInt(i));
            }
            int size2 = 0 + dataSize;
            if (!getStartsList().isEmpty()) {
                size2 = size2 + 1 + CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            this.startsMemoizedSerializedSize = dataSize;
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeBytesSize(2, this.checksum_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ChunkOrdering parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ChunkOrdering) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ChunkOrdering parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ChunkOrdering) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ChunkOrdering parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ChunkOrdering) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ChunkOrdering parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ChunkOrdering) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ChunkOrdering parseFrom(InputStream input) throws IOException {
            return (ChunkOrdering) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunkOrdering parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunkOrdering) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ChunkOrdering parseDelimitedFrom(InputStream input) throws IOException {
            return (ChunkOrdering) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunkOrdering parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunkOrdering) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ChunkOrdering parseFrom(CodedInputStream input) throws IOException {
            return (ChunkOrdering) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunkOrdering parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunkOrdering) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ChunkOrdering prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ChunkOrdering, Builder> implements ChunkOrderingOrBuilder {
            private Builder() {
                super(ChunkOrdering.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
            public List<Integer> getStartsList() {
                return Collections.unmodifiableList(((ChunkOrdering) this.instance).getStartsList());
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
            public int getStartsCount() {
                return ((ChunkOrdering) this.instance).getStartsCount();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
            public int getStarts(int index) {
                return ((ChunkOrdering) this.instance).getStarts(index);
            }

            public Builder setStarts(int index, int value) {
                copyOnWrite();
                ((ChunkOrdering) this.instance).setStarts(index, value);
                return this;
            }

            public Builder addStarts(int value) {
                copyOnWrite();
                ((ChunkOrdering) this.instance).addStarts(value);
                return this;
            }

            public Builder addAllStarts(Iterable<? extends Integer> values) {
                copyOnWrite();
                ((ChunkOrdering) this.instance).addAllStarts(values);
                return this;
            }

            public Builder clearStarts() {
                copyOnWrite();
                ((ChunkOrdering) this.instance).clearStarts();
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
            public boolean hasChecksum() {
                return ((ChunkOrdering) this.instance).hasChecksum();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunkOrderingOrBuilder
            public ByteString getChecksum() {
                return ((ChunkOrdering) this.instance).getChecksum();
            }

            public Builder setChecksum(ByteString value) {
                copyOnWrite();
                ((ChunkOrdering) this.instance).setChecksum(value);
                return this;
            }

            public Builder clearChecksum() {
                copyOnWrite();
                ((ChunkOrdering) this.instance).clearChecksum();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ChunkOrdering();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.starts_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ChunkOrdering other = (ChunkOrdering) arg1;
                    this.starts_ = visitor.visitIntList(this.starts_, other.starts_);
                    this.checksum_ = visitor.visitByteString(hasChecksum(), this.checksum_, other.hasChecksum(), other.checksum_);
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
                                if (!this.starts_.isModifiable()) {
                                    this.starts_ = GeneratedMessageLite.mutableCopy(this.starts_);
                                }
                                this.starts_.addInt(input.readInt32());
                            } else if (tag == 10) {
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.starts_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.starts_ = GeneratedMessageLite.mutableCopy(this.starts_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.starts_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                            } else if (tag == 18) {
                                this.bitField0_ |= 1;
                                this.checksum_ = input.readBytes();
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
                        synchronized (ChunkOrdering.class) {
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

        public static ChunkOrdering getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ChunkOrdering> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ChunksMetadata extends GeneratedMessageLite<ChunksMetadata, Builder> implements ChunksMetadataOrBuilder {
        public static final int CHECKSUM_TYPE_FIELD_NUMBER = 3;
        public static final int CHUNK_ORDERING_FIELD_NUMBER = 2;
        public static final int CHUNK_ORDERING_TYPE_FIELD_NUMBER = 5;
        public static final int CIPHER_TYPE_FIELD_NUMBER = 1;
        private static final ChunksMetadata DEFAULT_INSTANCE = new ChunksMetadata();
        private static volatile Parser<ChunksMetadata> PARSER;
        private int bitField0_;
        private int checksumType_ = 0;
        private int chunkOrderingType_ = 0;
        private ByteString chunkOrdering_ = ByteString.EMPTY;
        private int cipherType_ = 0;

        private ChunksMetadata() {
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
        public boolean hasCipherType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
        public CipherType getCipherType() {
            CipherType result = CipherType.forNumber(this.cipherType_);
            return result == null ? CipherType.UNKNOWN_CIPHER_TYPE : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCipherType(CipherType value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.cipherType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCipherType() {
            this.bitField0_ &= -2;
            this.cipherType_ = 0;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
        public boolean hasChunkOrderingType() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
        public ChunkOrderingType getChunkOrderingType() {
            ChunkOrderingType result = ChunkOrderingType.forNumber(this.chunkOrderingType_);
            return result == null ? ChunkOrderingType.CHUNK_ORDERING_TYPE_UNSPECIFIED : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChunkOrderingType(ChunkOrderingType value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.chunkOrderingType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChunkOrderingType() {
            this.bitField0_ &= -3;
            this.chunkOrderingType_ = 0;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
        public boolean hasChunkOrdering() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
        public ByteString getChunkOrdering() {
            return this.chunkOrdering_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChunkOrdering(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 4;
                this.chunkOrdering_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChunkOrdering() {
            this.bitField0_ &= -5;
            this.chunkOrdering_ = getDefaultInstance().getChunkOrdering();
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
        public boolean hasChecksumType() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
        public ChecksumType getChecksumType() {
            ChecksumType result = ChecksumType.forNumber(this.checksumType_);
            return result == null ? ChecksumType.UNKNOWN_CHECKSUM_TYPE : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChecksumType(ChecksumType value) {
            if (value != null) {
                this.bitField0_ |= 8;
                this.checksumType_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChecksumType() {
            this.bitField0_ &= -9;
            this.checksumType_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.cipherType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(2, this.chunkOrdering_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(3, this.checksumType_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(5, this.chunkOrderingType_);
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
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.cipherType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(2, this.chunkOrdering_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(3, this.checksumType_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(5, this.chunkOrderingType_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ChunksMetadata parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ChunksMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ChunksMetadata parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ChunksMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ChunksMetadata parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ChunksMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ChunksMetadata parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ChunksMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ChunksMetadata parseFrom(InputStream input) throws IOException {
            return (ChunksMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunksMetadata parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunksMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ChunksMetadata parseDelimitedFrom(InputStream input) throws IOException {
            return (ChunksMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunksMetadata parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunksMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ChunksMetadata parseFrom(CodedInputStream input) throws IOException {
            return (ChunksMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ChunksMetadata parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ChunksMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ChunksMetadata prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ChunksMetadata, Builder> implements ChunksMetadataOrBuilder {
            private Builder() {
                super(ChunksMetadata.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
            public boolean hasCipherType() {
                return ((ChunksMetadata) this.instance).hasCipherType();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
            public CipherType getCipherType() {
                return ((ChunksMetadata) this.instance).getCipherType();
            }

            public Builder setCipherType(CipherType value) {
                copyOnWrite();
                ((ChunksMetadata) this.instance).setCipherType(value);
                return this;
            }

            public Builder clearCipherType() {
                copyOnWrite();
                ((ChunksMetadata) this.instance).clearCipherType();
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
            public boolean hasChunkOrderingType() {
                return ((ChunksMetadata) this.instance).hasChunkOrderingType();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
            public ChunkOrderingType getChunkOrderingType() {
                return ((ChunksMetadata) this.instance).getChunkOrderingType();
            }

            public Builder setChunkOrderingType(ChunkOrderingType value) {
                copyOnWrite();
                ((ChunksMetadata) this.instance).setChunkOrderingType(value);
                return this;
            }

            public Builder clearChunkOrderingType() {
                copyOnWrite();
                ((ChunksMetadata) this.instance).clearChunkOrderingType();
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
            public boolean hasChunkOrdering() {
                return ((ChunksMetadata) this.instance).hasChunkOrdering();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
            public ByteString getChunkOrdering() {
                return ((ChunksMetadata) this.instance).getChunkOrdering();
            }

            public Builder setChunkOrdering(ByteString value) {
                copyOnWrite();
                ((ChunksMetadata) this.instance).setChunkOrdering(value);
                return this;
            }

            public Builder clearChunkOrdering() {
                copyOnWrite();
                ((ChunksMetadata) this.instance).clearChunkOrdering();
                return this;
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
            public boolean hasChecksumType() {
                return ((ChunksMetadata) this.instance).hasChecksumType();
            }

            @Override // com.android.server.backup.encryption.chunk.ChunksMetadataProto.ChunksMetadataOrBuilder
            public ChecksumType getChecksumType() {
                return ((ChunksMetadata) this.instance).getChecksumType();
            }

            public Builder setChecksumType(ChecksumType value) {
                copyOnWrite();
                ((ChunksMetadata) this.instance).setChecksumType(value);
                return this;
            }

            public Builder clearChecksumType() {
                copyOnWrite();
                ((ChunksMetadata) this.instance).clearChecksumType();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ChunksMetadata();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ChunksMetadata other = (ChunksMetadata) arg1;
                    this.cipherType_ = visitor.visitInt(hasCipherType(), this.cipherType_, other.hasCipherType(), other.cipherType_);
                    this.chunkOrderingType_ = visitor.visitInt(hasChunkOrderingType(), this.chunkOrderingType_, other.hasChunkOrderingType(), other.chunkOrderingType_);
                    this.chunkOrdering_ = visitor.visitByteString(hasChunkOrdering(), this.chunkOrdering_, other.hasChunkOrdering(), other.chunkOrdering_);
                    this.checksumType_ = visitor.visitInt(hasChecksumType(), this.checksumType_, other.hasChecksumType(), other.checksumType_);
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
                                if (CipherType.forNumber(rawValue) == null) {
                                    super.mergeVarintField(1, rawValue);
                                } else {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.cipherType_ = rawValue;
                                }
                            } else if (tag == 18) {
                                this.bitField0_ |= 4;
                                this.chunkOrdering_ = input.readBytes();
                            } else if (tag == 24) {
                                int rawValue2 = input.readEnum();
                                if (ChecksumType.forNumber(rawValue2) == null) {
                                    super.mergeVarintField(3, rawValue2);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.checksumType_ = rawValue2;
                                }
                            } else if (tag == 40) {
                                int rawValue3 = input.readEnum();
                                if (ChunkOrderingType.forNumber(rawValue3) == null) {
                                    super.mergeVarintField(5, rawValue3);
                                } else {
                                    this.bitField0_ |= 2;
                                    this.chunkOrderingType_ = rawValue3;
                                }
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
                        synchronized (ChunksMetadata.class) {
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

        public static ChunksMetadata getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ChunksMetadata> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
