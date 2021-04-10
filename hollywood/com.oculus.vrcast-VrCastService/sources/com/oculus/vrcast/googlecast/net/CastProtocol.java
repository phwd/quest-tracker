package com.oculus.vrcast.googlecast.net;

import com.apple.dnssd.DNSSD;
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

public final class CastProtocol {

    public interface AuthChallengeOrBuilder extends MessageLiteOrBuilder {
        HashAlgorithm getHashAlgorithm();

        ByteString getSenderNonce();

        SignatureAlgorithm getSignatureAlgorithm();

        boolean hasHashAlgorithm();

        boolean hasSenderNonce();

        boolean hasSignatureAlgorithm();
    }

    public interface AuthErrorOrBuilder extends MessageLiteOrBuilder {
        AuthError.ErrorType getErrorType();

        boolean hasErrorType();
    }

    public interface AuthResponseOrBuilder extends MessageLiteOrBuilder {
        ByteString getClientAuthCertificate();

        ByteString getCrl();

        HashAlgorithm getHashAlgorithm();

        ByteString getIntermediateCertificate(int i);

        int getIntermediateCertificateCount();

        List<ByteString> getIntermediateCertificateList();

        ByteString getSenderNonce();

        ByteString getSignature();

        SignatureAlgorithm getSignatureAlgorithm();

        boolean hasClientAuthCertificate();

        boolean hasCrl();

        boolean hasHashAlgorithm();

        boolean hasSenderNonce();

        boolean hasSignature();

        boolean hasSignatureAlgorithm();
    }

    public interface CastMessageOrBuilder extends MessageLiteOrBuilder {
        String getDestinationId();

        ByteString getDestinationIdBytes();

        String getNamespace();

        ByteString getNamespaceBytes();

        ByteString getPayloadBinary();

        CastMessage.PayloadType getPayloadType();

        String getPayloadUtf8();

        ByteString getPayloadUtf8Bytes();

        CastMessage.ProtocolVersion getProtocolVersion();

        String getSourceId();

        ByteString getSourceIdBytes();

        boolean hasDestinationId();

        boolean hasNamespace();

        boolean hasPayloadBinary();

        boolean hasPayloadType();

        boolean hasPayloadUtf8();

        boolean hasProtocolVersion();

        boolean hasSourceId();
    }

    public interface DeviceAuthMessageOrBuilder extends MessageLiteOrBuilder {
        AuthChallenge getChallenge();

        AuthError getError();

        AuthResponse getResponse();

        boolean hasChallenge();

        boolean hasError();

        boolean hasResponse();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private CastProtocol() {
    }

    public enum SignatureAlgorithm implements Internal.EnumLite {
        UNSPECIFIED(0),
        RSASSA_PKCS1v15(1),
        RSASSA_PSS(2);
        
        public static final int RSASSA_PKCS1v15_VALUE = 1;
        public static final int RSASSA_PSS_VALUE = 2;
        public static final int UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<SignatureAlgorithm> internalValueMap = new Internal.EnumLiteMap<SignatureAlgorithm>() {
            /* class com.oculus.vrcast.googlecast.net.CastProtocol.SignatureAlgorithm.AnonymousClass1 */

            public SignatureAlgorithm findValueByNumber(int i) {
                return SignatureAlgorithm.forNumber(i);
            }
        };
        private final int value;

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static SignatureAlgorithm valueOf(int i) {
            return forNumber(i);
        }

        public static SignatureAlgorithm forNumber(int i) {
            if (i == 0) {
                return UNSPECIFIED;
            }
            if (i == 1) {
                return RSASSA_PKCS1v15;
            }
            if (i != 2) {
                return null;
            }
            return RSASSA_PSS;
        }

        public static Internal.EnumLiteMap<SignatureAlgorithm> internalGetValueMap() {
            return internalValueMap;
        }

        private SignatureAlgorithm(int i) {
            this.value = i;
        }
    }

    public enum HashAlgorithm implements Internal.EnumLite {
        SHA1(0),
        SHA256(1);
        
        public static final int SHA1_VALUE = 0;
        public static final int SHA256_VALUE = 1;
        private static final Internal.EnumLiteMap<HashAlgorithm> internalValueMap = new Internal.EnumLiteMap<HashAlgorithm>() {
            /* class com.oculus.vrcast.googlecast.net.CastProtocol.HashAlgorithm.AnonymousClass1 */

            public HashAlgorithm findValueByNumber(int i) {
                return HashAlgorithm.forNumber(i);
            }
        };
        private final int value;

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static HashAlgorithm valueOf(int i) {
            return forNumber(i);
        }

        public static HashAlgorithm forNumber(int i) {
            if (i == 0) {
                return SHA1;
            }
            if (i != 1) {
                return null;
            }
            return SHA256;
        }

        public static Internal.EnumLiteMap<HashAlgorithm> internalGetValueMap() {
            return internalValueMap;
        }

        private HashAlgorithm(int i) {
            this.value = i;
        }
    }

    public static final class CastMessage extends GeneratedMessageLite<CastMessage, Builder> implements CastMessageOrBuilder {
        private static final CastMessage DEFAULT_INSTANCE = new CastMessage();
        public static final int DESTINATION_ID_FIELD_NUMBER = 3;
        public static final int NAMESPACE_FIELD_NUMBER = 4;
        private static volatile Parser<CastMessage> PARSER = null;
        public static final int PAYLOAD_BINARY_FIELD_NUMBER = 7;
        public static final int PAYLOAD_TYPE_FIELD_NUMBER = 5;
        public static final int PAYLOAD_UTF8_FIELD_NUMBER = 6;
        public static final int PROTOCOL_VERSION_FIELD_NUMBER = 1;
        public static final int SOURCE_ID_FIELD_NUMBER = 2;
        private int bitField0_;
        private String destinationId_ = "";
        private byte memoizedIsInitialized = -1;
        private String namespace_ = "";
        private ByteString payloadBinary_ = ByteString.EMPTY;
        private int payloadType_ = 0;
        private String payloadUtf8_ = "";
        private int protocolVersion_ = 0;
        private String sourceId_ = "";

        private CastMessage() {
        }

        public enum ProtocolVersion implements Internal.EnumLite {
            CASTV2_1_0(0);
            
            public static final int CASTV2_1_0_VALUE = 0;
            private static final Internal.EnumLiteMap<ProtocolVersion> internalValueMap = new Internal.EnumLiteMap<ProtocolVersion>() {
                /* class com.oculus.vrcast.googlecast.net.CastProtocol.CastMessage.ProtocolVersion.AnonymousClass1 */

                public ProtocolVersion findValueByNumber(int i) {
                    return ProtocolVersion.forNumber(i);
                }
            };
            private final int value;

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ProtocolVersion valueOf(int i) {
                return forNumber(i);
            }

            public static ProtocolVersion forNumber(int i) {
                if (i != 0) {
                    return null;
                }
                return CASTV2_1_0;
            }

            public static Internal.EnumLiteMap<ProtocolVersion> internalGetValueMap() {
                return internalValueMap;
            }

            private ProtocolVersion(int i) {
                this.value = i;
            }
        }

        public enum PayloadType implements Internal.EnumLite {
            STRING(0),
            BINARY(1);
            
            public static final int BINARY_VALUE = 1;
            public static final int STRING_VALUE = 0;
            private static final Internal.EnumLiteMap<PayloadType> internalValueMap = new Internal.EnumLiteMap<PayloadType>() {
                /* class com.oculus.vrcast.googlecast.net.CastProtocol.CastMessage.PayloadType.AnonymousClass1 */

                public PayloadType findValueByNumber(int i) {
                    return PayloadType.forNumber(i);
                }
            };
            private final int value;

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static PayloadType valueOf(int i) {
                return forNumber(i);
            }

            public static PayloadType forNumber(int i) {
                if (i == 0) {
                    return STRING;
                }
                if (i != 1) {
                    return null;
                }
                return BINARY;
            }

            public static Internal.EnumLiteMap<PayloadType> internalGetValueMap() {
                return internalValueMap;
            }

            private PayloadType(int i) {
                this.value = i;
            }
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public boolean hasProtocolVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public ProtocolVersion getProtocolVersion() {
            ProtocolVersion forNumber = ProtocolVersion.forNumber(this.protocolVersion_);
            return forNumber == null ? ProtocolVersion.CASTV2_1_0 : forNumber;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProtocolVersion(ProtocolVersion protocolVersion) {
            if (protocolVersion != null) {
                this.bitField0_ |= 1;
                this.protocolVersion_ = protocolVersion.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProtocolVersion() {
            this.bitField0_ &= -2;
            this.protocolVersion_ = 0;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public boolean hasSourceId() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public String getSourceId() {
            return this.sourceId_;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public ByteString getSourceIdBytes() {
            return ByteString.copyFromUtf8(this.sourceId_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSourceId(String str) {
            if (str != null) {
                this.bitField0_ |= 2;
                this.sourceId_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSourceId() {
            this.bitField0_ &= -3;
            this.sourceId_ = getDefaultInstance().getSourceId();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSourceIdBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2;
                this.sourceId_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public boolean hasDestinationId() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public String getDestinationId() {
            return this.destinationId_;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public ByteString getDestinationIdBytes() {
            return ByteString.copyFromUtf8(this.destinationId_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDestinationId(String str) {
            if (str != null) {
                this.bitField0_ |= 4;
                this.destinationId_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDestinationId() {
            this.bitField0_ &= -5;
            this.destinationId_ = getDefaultInstance().getDestinationId();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDestinationIdBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 4;
                this.destinationId_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public boolean hasNamespace() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public String getNamespace() {
            return this.namespace_;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public ByteString getNamespaceBytes() {
            return ByteString.copyFromUtf8(this.namespace_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNamespace(String str) {
            if (str != null) {
                this.bitField0_ |= 8;
                this.namespace_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNamespace() {
            this.bitField0_ &= -9;
            this.namespace_ = getDefaultInstance().getNamespace();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNamespaceBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 8;
                this.namespace_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public boolean hasPayloadType() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public PayloadType getPayloadType() {
            PayloadType forNumber = PayloadType.forNumber(this.payloadType_);
            return forNumber == null ? PayloadType.STRING : forNumber;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPayloadType(PayloadType payloadType) {
            if (payloadType != null) {
                this.bitField0_ |= 16;
                this.payloadType_ = payloadType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPayloadType() {
            this.bitField0_ &= -17;
            this.payloadType_ = 0;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public boolean hasPayloadUtf8() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public String getPayloadUtf8() {
            return this.payloadUtf8_;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public ByteString getPayloadUtf8Bytes() {
            return ByteString.copyFromUtf8(this.payloadUtf8_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPayloadUtf8(String str) {
            if (str != null) {
                this.bitField0_ |= 32;
                this.payloadUtf8_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPayloadUtf8() {
            this.bitField0_ &= -33;
            this.payloadUtf8_ = getDefaultInstance().getPayloadUtf8();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPayloadUtf8Bytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 32;
                this.payloadUtf8_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public boolean hasPayloadBinary() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
        public ByteString getPayloadBinary() {
            return this.payloadBinary_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPayloadBinary(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 64;
                this.payloadBinary_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPayloadBinary() {
            this.bitField0_ &= -65;
            this.payloadBinary_ = getDefaultInstance().getPayloadBinary();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.protocolVersion_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeString(2, getSourceId());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeString(3, getDestinationId());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeString(4, getNamespace());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeEnum(5, this.payloadType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeString(6, getPayloadUtf8());
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeBytes(7, this.payloadBinary_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.protocolVersion_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeStringSize(2, getSourceId());
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeStringSize(3, getDestinationId());
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeStringSize(4, getNamespace());
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeEnumSize(5, this.payloadType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeStringSize(6, getPayloadUtf8());
            }
            if ((this.bitField0_ & 64) == 64) {
                i2 += CodedOutputStream.computeBytesSize(7, this.payloadBinary_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static CastMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CastMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CastMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CastMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CastMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CastMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CastMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CastMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CastMessage parseFrom(InputStream inputStream) throws IOException {
            return (CastMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CastMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CastMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CastMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CastMessage) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CastMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CastMessage) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CastMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CastMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CastMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CastMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CastMessage castMessage) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) castMessage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CastMessage, Builder> implements CastMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(CastMessage.DEFAULT_INSTANCE);
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public boolean hasProtocolVersion() {
                return ((CastMessage) this.instance).hasProtocolVersion();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public ProtocolVersion getProtocolVersion() {
                return ((CastMessage) this.instance).getProtocolVersion();
            }

            public Builder setProtocolVersion(ProtocolVersion protocolVersion) {
                copyOnWrite();
                ((CastMessage) this.instance).setProtocolVersion(protocolVersion);
                return this;
            }

            public Builder clearProtocolVersion() {
                copyOnWrite();
                ((CastMessage) this.instance).clearProtocolVersion();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public boolean hasSourceId() {
                return ((CastMessage) this.instance).hasSourceId();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public String getSourceId() {
                return ((CastMessage) this.instance).getSourceId();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public ByteString getSourceIdBytes() {
                return ((CastMessage) this.instance).getSourceIdBytes();
            }

            public Builder setSourceId(String str) {
                copyOnWrite();
                ((CastMessage) this.instance).setSourceId(str);
                return this;
            }

            public Builder clearSourceId() {
                copyOnWrite();
                ((CastMessage) this.instance).clearSourceId();
                return this;
            }

            public Builder setSourceIdBytes(ByteString byteString) {
                copyOnWrite();
                ((CastMessage) this.instance).setSourceIdBytes(byteString);
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public boolean hasDestinationId() {
                return ((CastMessage) this.instance).hasDestinationId();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public String getDestinationId() {
                return ((CastMessage) this.instance).getDestinationId();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public ByteString getDestinationIdBytes() {
                return ((CastMessage) this.instance).getDestinationIdBytes();
            }

            public Builder setDestinationId(String str) {
                copyOnWrite();
                ((CastMessage) this.instance).setDestinationId(str);
                return this;
            }

            public Builder clearDestinationId() {
                copyOnWrite();
                ((CastMessage) this.instance).clearDestinationId();
                return this;
            }

            public Builder setDestinationIdBytes(ByteString byteString) {
                copyOnWrite();
                ((CastMessage) this.instance).setDestinationIdBytes(byteString);
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public boolean hasNamespace() {
                return ((CastMessage) this.instance).hasNamespace();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public String getNamespace() {
                return ((CastMessage) this.instance).getNamespace();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public ByteString getNamespaceBytes() {
                return ((CastMessage) this.instance).getNamespaceBytes();
            }

            public Builder setNamespace(String str) {
                copyOnWrite();
                ((CastMessage) this.instance).setNamespace(str);
                return this;
            }

            public Builder clearNamespace() {
                copyOnWrite();
                ((CastMessage) this.instance).clearNamespace();
                return this;
            }

            public Builder setNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((CastMessage) this.instance).setNamespaceBytes(byteString);
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public boolean hasPayloadType() {
                return ((CastMessage) this.instance).hasPayloadType();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public PayloadType getPayloadType() {
                return ((CastMessage) this.instance).getPayloadType();
            }

            public Builder setPayloadType(PayloadType payloadType) {
                copyOnWrite();
                ((CastMessage) this.instance).setPayloadType(payloadType);
                return this;
            }

            public Builder clearPayloadType() {
                copyOnWrite();
                ((CastMessage) this.instance).clearPayloadType();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public boolean hasPayloadUtf8() {
                return ((CastMessage) this.instance).hasPayloadUtf8();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public String getPayloadUtf8() {
                return ((CastMessage) this.instance).getPayloadUtf8();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public ByteString getPayloadUtf8Bytes() {
                return ((CastMessage) this.instance).getPayloadUtf8Bytes();
            }

            public Builder setPayloadUtf8(String str) {
                copyOnWrite();
                ((CastMessage) this.instance).setPayloadUtf8(str);
                return this;
            }

            public Builder clearPayloadUtf8() {
                copyOnWrite();
                ((CastMessage) this.instance).clearPayloadUtf8();
                return this;
            }

            public Builder setPayloadUtf8Bytes(ByteString byteString) {
                copyOnWrite();
                ((CastMessage) this.instance).setPayloadUtf8Bytes(byteString);
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public boolean hasPayloadBinary() {
                return ((CastMessage) this.instance).hasPayloadBinary();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.CastMessageOrBuilder
            public ByteString getPayloadBinary() {
                return ((CastMessage) this.instance).getPayloadBinary();
            }

            public Builder setPayloadBinary(ByteString byteString) {
                copyOnWrite();
                ((CastMessage) this.instance).setPayloadBinary(byteString);
                return this;
            }

            public Builder clearPayloadBinary() {
                copyOnWrite();
                ((CastMessage) this.instance).clearPayloadBinary();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new CastMessage();
                case 2:
                    byte b = this.memoizedIsInitialized;
                    if (b == 1) {
                        return DEFAULT_INSTANCE;
                    }
                    if (b == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!hasProtocolVersion()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    } else if (!hasSourceId()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    } else if (!hasDestinationId()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    } else if (!hasNamespace()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    } else if (!hasPayloadType()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    } else {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 1;
                        }
                        return DEFAULT_INSTANCE;
                    }
                case 3:
                    return null;
                case 4:
                    return new Builder(null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CastMessage castMessage = (CastMessage) obj2;
                    this.protocolVersion_ = visitor.visitInt(hasProtocolVersion(), this.protocolVersion_, castMessage.hasProtocolVersion(), castMessage.protocolVersion_);
                    this.sourceId_ = visitor.visitString(hasSourceId(), this.sourceId_, castMessage.hasSourceId(), castMessage.sourceId_);
                    this.destinationId_ = visitor.visitString(hasDestinationId(), this.destinationId_, castMessage.hasDestinationId(), castMessage.destinationId_);
                    this.namespace_ = visitor.visitString(hasNamespace(), this.namespace_, castMessage.hasNamespace(), castMessage.namespace_);
                    this.payloadType_ = visitor.visitInt(hasPayloadType(), this.payloadType_, castMessage.hasPayloadType(), castMessage.payloadType_);
                    this.payloadUtf8_ = visitor.visitString(hasPayloadUtf8(), this.payloadUtf8_, castMessage.hasPayloadUtf8(), castMessage.payloadUtf8_);
                    this.payloadBinary_ = visitor.visitByteString(hasPayloadBinary(), this.payloadBinary_, castMessage.hasPayloadBinary(), castMessage.payloadBinary_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= castMessage.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (ProtocolVersion.forNumber(readEnum) == null) {
                                        super.mergeVarintField(1, readEnum);
                                    } else {
                                        this.bitField0_ |= 1;
                                        this.protocolVersion_ = readEnum;
                                    }
                                } else if (readTag == 18) {
                                    String readString = codedInputStream.readString();
                                    this.bitField0_ |= 2;
                                    this.sourceId_ = readString;
                                } else if (readTag == 26) {
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 4;
                                    this.destinationId_ = readString2;
                                } else if (readTag == 34) {
                                    String readString3 = codedInputStream.readString();
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.namespace_ = readString3;
                                } else if (readTag == 40) {
                                    int readEnum2 = codedInputStream.readEnum();
                                    if (PayloadType.forNumber(readEnum2) == null) {
                                        super.mergeVarintField(5, readEnum2);
                                    } else {
                                        this.bitField0_ |= 16;
                                        this.payloadType_ = readEnum2;
                                    }
                                } else if (readTag == 50) {
                                    String readString4 = codedInputStream.readString();
                                    this.bitField0_ |= 32;
                                    this.payloadUtf8_ = readString4;
                                } else if (readTag == 58) {
                                    this.bitField0_ |= 64;
                                    this.payloadBinary_ = codedInputStream.readBytes();
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case DNSSD.NO_AUTO_RENAME:
                    if (PARSER == null) {
                        synchronized (CastMessage.class) {
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

        public static CastMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CastMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.oculus.vrcast.googlecast.net.CastProtocol$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                int[] r0 = com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrcast.googlecast.net.CastProtocol.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class AuthChallenge extends GeneratedMessageLite<AuthChallenge, Builder> implements AuthChallengeOrBuilder {
        private static final AuthChallenge DEFAULT_INSTANCE = new AuthChallenge();
        public static final int HASH_ALGORITHM_FIELD_NUMBER = 3;
        private static volatile Parser<AuthChallenge> PARSER = null;
        public static final int SENDER_NONCE_FIELD_NUMBER = 2;
        public static final int SIGNATURE_ALGORITHM_FIELD_NUMBER = 1;
        private int bitField0_;
        private int hashAlgorithm_ = 0;
        private ByteString senderNonce_ = ByteString.EMPTY;
        private int signatureAlgorithm_ = 1;

        private AuthChallenge() {
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
        public boolean hasSignatureAlgorithm() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
        public SignatureAlgorithm getSignatureAlgorithm() {
            SignatureAlgorithm forNumber = SignatureAlgorithm.forNumber(this.signatureAlgorithm_);
            return forNumber == null ? SignatureAlgorithm.RSASSA_PKCS1v15 : forNumber;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
            if (signatureAlgorithm != null) {
                this.bitField0_ |= 1;
                this.signatureAlgorithm_ = signatureAlgorithm.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSignatureAlgorithm() {
            this.bitField0_ &= -2;
            this.signatureAlgorithm_ = 1;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
        public boolean hasSenderNonce() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
        public ByteString getSenderNonce() {
            return this.senderNonce_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSenderNonce(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2;
                this.senderNonce_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSenderNonce() {
            this.bitField0_ &= -3;
            this.senderNonce_ = getDefaultInstance().getSenderNonce();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
        public boolean hasHashAlgorithm() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
        public HashAlgorithm getHashAlgorithm() {
            HashAlgorithm forNumber = HashAlgorithm.forNumber(this.hashAlgorithm_);
            return forNumber == null ? HashAlgorithm.SHA1 : forNumber;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHashAlgorithm(HashAlgorithm hashAlgorithm) {
            if (hashAlgorithm != null) {
                this.bitField0_ |= 4;
                this.hashAlgorithm_ = hashAlgorithm.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHashAlgorithm() {
            this.bitField0_ &= -5;
            this.hashAlgorithm_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.signatureAlgorithm_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.senderNonce_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeEnum(3, this.hashAlgorithm_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.signatureAlgorithm_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.senderNonce_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeEnumSize(3, this.hashAlgorithm_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static AuthChallenge parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AuthChallenge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AuthChallenge parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AuthChallenge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AuthChallenge parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AuthChallenge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthChallenge parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AuthChallenge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AuthChallenge parseFrom(InputStream inputStream) throws IOException {
            return (AuthChallenge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthChallenge parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthChallenge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AuthChallenge parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthChallenge) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthChallenge parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthChallenge) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AuthChallenge parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AuthChallenge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AuthChallenge parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthChallenge) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AuthChallenge authChallenge) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) authChallenge);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AuthChallenge, Builder> implements AuthChallengeOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(AuthChallenge.DEFAULT_INSTANCE);
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
            public boolean hasSignatureAlgorithm() {
                return ((AuthChallenge) this.instance).hasSignatureAlgorithm();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
            public SignatureAlgorithm getSignatureAlgorithm() {
                return ((AuthChallenge) this.instance).getSignatureAlgorithm();
            }

            public Builder setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
                copyOnWrite();
                ((AuthChallenge) this.instance).setSignatureAlgorithm(signatureAlgorithm);
                return this;
            }

            public Builder clearSignatureAlgorithm() {
                copyOnWrite();
                ((AuthChallenge) this.instance).clearSignatureAlgorithm();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
            public boolean hasSenderNonce() {
                return ((AuthChallenge) this.instance).hasSenderNonce();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
            public ByteString getSenderNonce() {
                return ((AuthChallenge) this.instance).getSenderNonce();
            }

            public Builder setSenderNonce(ByteString byteString) {
                copyOnWrite();
                ((AuthChallenge) this.instance).setSenderNonce(byteString);
                return this;
            }

            public Builder clearSenderNonce() {
                copyOnWrite();
                ((AuthChallenge) this.instance).clearSenderNonce();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
            public boolean hasHashAlgorithm() {
                return ((AuthChallenge) this.instance).hasHashAlgorithm();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthChallengeOrBuilder
            public HashAlgorithm getHashAlgorithm() {
                return ((AuthChallenge) this.instance).getHashAlgorithm();
            }

            public Builder setHashAlgorithm(HashAlgorithm hashAlgorithm) {
                copyOnWrite();
                ((AuthChallenge) this.instance).setHashAlgorithm(hashAlgorithm);
                return this;
            }

            public Builder clearHashAlgorithm() {
                copyOnWrite();
                ((AuthChallenge) this.instance).clearHashAlgorithm();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new AuthChallenge();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder(null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    AuthChallenge authChallenge = (AuthChallenge) obj2;
                    this.signatureAlgorithm_ = visitor.visitInt(hasSignatureAlgorithm(), this.signatureAlgorithm_, authChallenge.hasSignatureAlgorithm(), authChallenge.signatureAlgorithm_);
                    this.senderNonce_ = visitor.visitByteString(hasSenderNonce(), this.senderNonce_, authChallenge.hasSenderNonce(), authChallenge.senderNonce_);
                    this.hashAlgorithm_ = visitor.visitInt(hasHashAlgorithm(), this.hashAlgorithm_, authChallenge.hasHashAlgorithm(), authChallenge.hashAlgorithm_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= authChallenge.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (SignatureAlgorithm.forNumber(readEnum) == null) {
                                        super.mergeVarintField(1, readEnum);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.signatureAlgorithm_ = readEnum;
                                    }
                                } else if (readTag == 18) {
                                    this.bitField0_ |= 2;
                                    this.senderNonce_ = codedInputStream.readBytes();
                                } else if (readTag == 24) {
                                    int readEnum2 = codedInputStream.readEnum();
                                    if (HashAlgorithm.forNumber(readEnum2) == null) {
                                        super.mergeVarintField(3, readEnum2);
                                    } else {
                                        this.bitField0_ |= 4;
                                        this.hashAlgorithm_ = readEnum2;
                                    }
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case DNSSD.NO_AUTO_RENAME:
                    if (PARSER == null) {
                        synchronized (AuthChallenge.class) {
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

        public static AuthChallenge getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AuthChallenge> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AuthResponse extends GeneratedMessageLite<AuthResponse, Builder> implements AuthResponseOrBuilder {
        public static final int CLIENT_AUTH_CERTIFICATE_FIELD_NUMBER = 2;
        public static final int CRL_FIELD_NUMBER = 7;
        private static final AuthResponse DEFAULT_INSTANCE = new AuthResponse();
        public static final int HASH_ALGORITHM_FIELD_NUMBER = 6;
        public static final int INTERMEDIATE_CERTIFICATE_FIELD_NUMBER = 3;
        private static volatile Parser<AuthResponse> PARSER = null;
        public static final int SENDER_NONCE_FIELD_NUMBER = 5;
        public static final int SIGNATURE_ALGORITHM_FIELD_NUMBER = 4;
        public static final int SIGNATURE_FIELD_NUMBER = 1;
        private int bitField0_;
        private ByteString clientAuthCertificate_;
        private ByteString crl_;
        private int hashAlgorithm_;
        private Internal.ProtobufList<ByteString> intermediateCertificate_;
        private byte memoizedIsInitialized = -1;
        private ByteString senderNonce_;
        private int signatureAlgorithm_;
        private ByteString signature_;

        private AuthResponse() {
            ByteString byteString = ByteString.EMPTY;
            this.signature_ = byteString;
            this.clientAuthCertificate_ = byteString;
            this.intermediateCertificate_ = GeneratedMessageLite.emptyProtobufList();
            this.signatureAlgorithm_ = 1;
            ByteString byteString2 = ByteString.EMPTY;
            this.senderNonce_ = byteString2;
            this.hashAlgorithm_ = 0;
            this.crl_ = byteString2;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public boolean hasSignature() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public ByteString getSignature() {
            return this.signature_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSignature(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 1;
                this.signature_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSignature() {
            this.bitField0_ &= -2;
            this.signature_ = getDefaultInstance().getSignature();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public boolean hasClientAuthCertificate() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public ByteString getClientAuthCertificate() {
            return this.clientAuthCertificate_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setClientAuthCertificate(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2;
                this.clientAuthCertificate_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearClientAuthCertificate() {
            this.bitField0_ &= -3;
            this.clientAuthCertificate_ = getDefaultInstance().getClientAuthCertificate();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public List<ByteString> getIntermediateCertificateList() {
            return this.intermediateCertificate_;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public int getIntermediateCertificateCount() {
            return this.intermediateCertificate_.size();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public ByteString getIntermediateCertificate(int i) {
            return this.intermediateCertificate_.get(i);
        }

        private void ensureIntermediateCertificateIsMutable() {
            if (!this.intermediateCertificate_.isModifiable()) {
                this.intermediateCertificate_ = GeneratedMessageLite.mutableCopy(this.intermediateCertificate_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntermediateCertificate(int i, ByteString byteString) {
            if (byteString != null) {
                ensureIntermediateCertificateIsMutable();
                this.intermediateCertificate_.set(i, byteString);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addIntermediateCertificate(ByteString byteString) {
            if (byteString != null) {
                ensureIntermediateCertificateIsMutable();
                this.intermediateCertificate_.add(byteString);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllIntermediateCertificate(Iterable<? extends ByteString> iterable) {
            ensureIntermediateCertificateIsMutable();
            AbstractMessageLite.addAll(iterable, this.intermediateCertificate_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIntermediateCertificate() {
            this.intermediateCertificate_ = GeneratedMessageLite.emptyProtobufList();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public boolean hasSignatureAlgorithm() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public SignatureAlgorithm getSignatureAlgorithm() {
            SignatureAlgorithm forNumber = SignatureAlgorithm.forNumber(this.signatureAlgorithm_);
            return forNumber == null ? SignatureAlgorithm.RSASSA_PKCS1v15 : forNumber;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
            if (signatureAlgorithm != null) {
                this.bitField0_ |= 4;
                this.signatureAlgorithm_ = signatureAlgorithm.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSignatureAlgorithm() {
            this.bitField0_ &= -5;
            this.signatureAlgorithm_ = 1;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public boolean hasSenderNonce() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public ByteString getSenderNonce() {
            return this.senderNonce_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSenderNonce(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 8;
                this.senderNonce_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSenderNonce() {
            this.bitField0_ &= -9;
            this.senderNonce_ = getDefaultInstance().getSenderNonce();
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public boolean hasHashAlgorithm() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public HashAlgorithm getHashAlgorithm() {
            HashAlgorithm forNumber = HashAlgorithm.forNumber(this.hashAlgorithm_);
            return forNumber == null ? HashAlgorithm.SHA1 : forNumber;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHashAlgorithm(HashAlgorithm hashAlgorithm) {
            if (hashAlgorithm != null) {
                this.bitField0_ |= 16;
                this.hashAlgorithm_ = hashAlgorithm.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHashAlgorithm() {
            this.bitField0_ &= -17;
            this.hashAlgorithm_ = 0;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public boolean hasCrl() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
        public ByteString getCrl() {
            return this.crl_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCrl(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 32;
                this.crl_ = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCrl() {
            this.bitField0_ &= -33;
            this.crl_ = getDefaultInstance().getCrl();
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.signature_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.clientAuthCertificate_);
            }
            for (int i = 0; i < this.intermediateCertificate_.size(); i++) {
                codedOutputStream.writeBytes(3, this.intermediateCertificate_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeEnum(4, this.signatureAlgorithm_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(5, this.senderNonce_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeEnum(6, this.hashAlgorithm_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBytes(7, this.crl_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, this.signature_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeBytesSize += CodedOutputStream.computeBytesSize(2, this.clientAuthCertificate_);
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.intermediateCertificate_.size(); i3++) {
                i2 += CodedOutputStream.computeBytesSizeNoTag(this.intermediateCertificate_.get(i3));
            }
            int size = computeBytesSize + i2 + (getIntermediateCertificateList().size() * 1);
            if ((this.bitField0_ & 4) == 4) {
                size += CodedOutputStream.computeEnumSize(4, this.signatureAlgorithm_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size += CodedOutputStream.computeBytesSize(5, this.senderNonce_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size += CodedOutputStream.computeEnumSize(6, this.hashAlgorithm_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size += CodedOutputStream.computeBytesSize(7, this.crl_);
            }
            int serializedSize = size + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static AuthResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AuthResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AuthResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AuthResponse parseFrom(InputStream inputStream) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AuthResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AuthResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AuthResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AuthResponse authResponse) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) authResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AuthResponse, Builder> implements AuthResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(AuthResponse.DEFAULT_INSTANCE);
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public boolean hasSignature() {
                return ((AuthResponse) this.instance).hasSignature();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public ByteString getSignature() {
                return ((AuthResponse) this.instance).getSignature();
            }

            public Builder setSignature(ByteString byteString) {
                copyOnWrite();
                ((AuthResponse) this.instance).setSignature(byteString);
                return this;
            }

            public Builder clearSignature() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearSignature();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public boolean hasClientAuthCertificate() {
                return ((AuthResponse) this.instance).hasClientAuthCertificate();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public ByteString getClientAuthCertificate() {
                return ((AuthResponse) this.instance).getClientAuthCertificate();
            }

            public Builder setClientAuthCertificate(ByteString byteString) {
                copyOnWrite();
                ((AuthResponse) this.instance).setClientAuthCertificate(byteString);
                return this;
            }

            public Builder clearClientAuthCertificate() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearClientAuthCertificate();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public List<ByteString> getIntermediateCertificateList() {
                return Collections.unmodifiableList(((AuthResponse) this.instance).getIntermediateCertificateList());
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public int getIntermediateCertificateCount() {
                return ((AuthResponse) this.instance).getIntermediateCertificateCount();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public ByteString getIntermediateCertificate(int i) {
                return ((AuthResponse) this.instance).getIntermediateCertificate(i);
            }

            public Builder setIntermediateCertificate(int i, ByteString byteString) {
                copyOnWrite();
                ((AuthResponse) this.instance).setIntermediateCertificate(i, byteString);
                return this;
            }

            public Builder addIntermediateCertificate(ByteString byteString) {
                copyOnWrite();
                ((AuthResponse) this.instance).addIntermediateCertificate(byteString);
                return this;
            }

            public Builder addAllIntermediateCertificate(Iterable<? extends ByteString> iterable) {
                copyOnWrite();
                ((AuthResponse) this.instance).addAllIntermediateCertificate(iterable);
                return this;
            }

            public Builder clearIntermediateCertificate() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearIntermediateCertificate();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public boolean hasSignatureAlgorithm() {
                return ((AuthResponse) this.instance).hasSignatureAlgorithm();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public SignatureAlgorithm getSignatureAlgorithm() {
                return ((AuthResponse) this.instance).getSignatureAlgorithm();
            }

            public Builder setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
                copyOnWrite();
                ((AuthResponse) this.instance).setSignatureAlgorithm(signatureAlgorithm);
                return this;
            }

            public Builder clearSignatureAlgorithm() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearSignatureAlgorithm();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public boolean hasSenderNonce() {
                return ((AuthResponse) this.instance).hasSenderNonce();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public ByteString getSenderNonce() {
                return ((AuthResponse) this.instance).getSenderNonce();
            }

            public Builder setSenderNonce(ByteString byteString) {
                copyOnWrite();
                ((AuthResponse) this.instance).setSenderNonce(byteString);
                return this;
            }

            public Builder clearSenderNonce() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearSenderNonce();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public boolean hasHashAlgorithm() {
                return ((AuthResponse) this.instance).hasHashAlgorithm();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public HashAlgorithm getHashAlgorithm() {
                return ((AuthResponse) this.instance).getHashAlgorithm();
            }

            public Builder setHashAlgorithm(HashAlgorithm hashAlgorithm) {
                copyOnWrite();
                ((AuthResponse) this.instance).setHashAlgorithm(hashAlgorithm);
                return this;
            }

            public Builder clearHashAlgorithm() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearHashAlgorithm();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public boolean hasCrl() {
                return ((AuthResponse) this.instance).hasCrl();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthResponseOrBuilder
            public ByteString getCrl() {
                return ((AuthResponse) this.instance).getCrl();
            }

            public Builder setCrl(ByteString byteString) {
                copyOnWrite();
                ((AuthResponse) this.instance).setCrl(byteString);
                return this;
            }

            public Builder clearCrl() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearCrl();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new AuthResponse();
                case 2:
                    byte b = this.memoizedIsInitialized;
                    if (b == 1) {
                        return DEFAULT_INSTANCE;
                    }
                    if (b == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!hasSignature()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    } else if (!hasClientAuthCertificate()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    } else {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 1;
                        }
                        return DEFAULT_INSTANCE;
                    }
                case 3:
                    this.intermediateCertificate_.makeImmutable();
                    return null;
                case 4:
                    return new Builder(null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    AuthResponse authResponse = (AuthResponse) obj2;
                    this.signature_ = visitor.visitByteString(hasSignature(), this.signature_, authResponse.hasSignature(), authResponse.signature_);
                    this.clientAuthCertificate_ = visitor.visitByteString(hasClientAuthCertificate(), this.clientAuthCertificate_, authResponse.hasClientAuthCertificate(), authResponse.clientAuthCertificate_);
                    this.intermediateCertificate_ = visitor.visitList(this.intermediateCertificate_, authResponse.intermediateCertificate_);
                    this.signatureAlgorithm_ = visitor.visitInt(hasSignatureAlgorithm(), this.signatureAlgorithm_, authResponse.hasSignatureAlgorithm(), authResponse.signatureAlgorithm_);
                    this.senderNonce_ = visitor.visitByteString(hasSenderNonce(), this.senderNonce_, authResponse.hasSenderNonce(), authResponse.senderNonce_);
                    this.hashAlgorithm_ = visitor.visitInt(hasHashAlgorithm(), this.hashAlgorithm_, authResponse.hasHashAlgorithm(), authResponse.hashAlgorithm_);
                    this.crl_ = visitor.visitByteString(hasCrl(), this.crl_, authResponse.hasCrl(), authResponse.crl_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= authResponse.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.bitField0_ |= 1;
                                    this.signature_ = codedInputStream.readBytes();
                                } else if (readTag == 18) {
                                    this.bitField0_ |= 2;
                                    this.clientAuthCertificate_ = codedInputStream.readBytes();
                                } else if (readTag == 26) {
                                    if (!this.intermediateCertificate_.isModifiable()) {
                                        this.intermediateCertificate_ = GeneratedMessageLite.mutableCopy(this.intermediateCertificate_);
                                    }
                                    this.intermediateCertificate_.add(codedInputStream.readBytes());
                                } else if (readTag == 32) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (SignatureAlgorithm.forNumber(readEnum) == null) {
                                        super.mergeVarintField(4, readEnum);
                                    } else {
                                        this.bitField0_ |= 4;
                                        this.signatureAlgorithm_ = readEnum;
                                    }
                                } else if (readTag == 42) {
                                    this.bitField0_ |= 8;
                                    this.senderNonce_ = codedInputStream.readBytes();
                                } else if (readTag == 48) {
                                    int readEnum2 = codedInputStream.readEnum();
                                    if (HashAlgorithm.forNumber(readEnum2) == null) {
                                        super.mergeVarintField(6, readEnum2);
                                    } else {
                                        this.bitField0_ |= 16;
                                        this.hashAlgorithm_ = readEnum2;
                                    }
                                } else if (readTag == 58) {
                                    this.bitField0_ |= 32;
                                    this.crl_ = codedInputStream.readBytes();
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case DNSSD.NO_AUTO_RENAME:
                    if (PARSER == null) {
                        synchronized (AuthResponse.class) {
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

        public static AuthResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AuthResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AuthError extends GeneratedMessageLite<AuthError, Builder> implements AuthErrorOrBuilder {
        private static final AuthError DEFAULT_INSTANCE = new AuthError();
        public static final int ERROR_TYPE_FIELD_NUMBER = 1;
        private static volatile Parser<AuthError> PARSER;
        private int bitField0_;
        private int errorType_ = 0;
        private byte memoizedIsInitialized = -1;

        private AuthError() {
        }

        public enum ErrorType implements Internal.EnumLite {
            INTERNAL_ERROR(0),
            NO_TLS(1),
            SIGNATURE_ALGORITHM_UNAVAILABLE(2);
            
            public static final int INTERNAL_ERROR_VALUE = 0;
            public static final int NO_TLS_VALUE = 1;
            public static final int SIGNATURE_ALGORITHM_UNAVAILABLE_VALUE = 2;
            private static final Internal.EnumLiteMap<ErrorType> internalValueMap = new Internal.EnumLiteMap<ErrorType>() {
                /* class com.oculus.vrcast.googlecast.net.CastProtocol.AuthError.ErrorType.AnonymousClass1 */

                public ErrorType findValueByNumber(int i) {
                    return ErrorType.forNumber(i);
                }
            };
            private final int value;

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ErrorType valueOf(int i) {
                return forNumber(i);
            }

            public static ErrorType forNumber(int i) {
                if (i == 0) {
                    return INTERNAL_ERROR;
                }
                if (i == 1) {
                    return NO_TLS;
                }
                if (i != 2) {
                    return null;
                }
                return SIGNATURE_ALGORITHM_UNAVAILABLE;
            }

            public static Internal.EnumLiteMap<ErrorType> internalGetValueMap() {
                return internalValueMap;
            }

            private ErrorType(int i) {
                this.value = i;
            }
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthErrorOrBuilder
        public boolean hasErrorType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthErrorOrBuilder
        public ErrorType getErrorType() {
            ErrorType forNumber = ErrorType.forNumber(this.errorType_);
            return forNumber == null ? ErrorType.INTERNAL_ERROR : forNumber;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setErrorType(ErrorType errorType) {
            if (errorType != null) {
                this.bitField0_ |= 1;
                this.errorType_ = errorType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearErrorType() {
            this.bitField0_ &= -2;
            this.errorType_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.errorType_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.errorType_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static AuthError parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AuthError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AuthError parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AuthError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AuthError parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AuthError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthError parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AuthError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AuthError parseFrom(InputStream inputStream) throws IOException {
            return (AuthError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthError parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AuthError parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthError) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthError parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthError) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AuthError parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AuthError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AuthError parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AuthError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AuthError authError) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) authError);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AuthError, Builder> implements AuthErrorOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(AuthError.DEFAULT_INSTANCE);
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthErrorOrBuilder
            public boolean hasErrorType() {
                return ((AuthError) this.instance).hasErrorType();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.AuthErrorOrBuilder
            public ErrorType getErrorType() {
                return ((AuthError) this.instance).getErrorType();
            }

            public Builder setErrorType(ErrorType errorType) {
                copyOnWrite();
                ((AuthError) this.instance).setErrorType(errorType);
                return this;
            }

            public Builder clearErrorType() {
                copyOnWrite();
                ((AuthError) this.instance).clearErrorType();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new AuthError();
                case 2:
                    byte b = this.memoizedIsInitialized;
                    if (b == 1) {
                        return DEFAULT_INSTANCE;
                    }
                    if (b == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!hasErrorType()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    }
                    if (booleanValue) {
                        this.memoizedIsInitialized = 1;
                    }
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder(null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    AuthError authError = (AuthError) obj2;
                    this.errorType_ = visitor.visitInt(hasErrorType(), this.errorType_, authError.hasErrorType(), authError.errorType_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= authError.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (ErrorType.forNumber(readEnum) == null) {
                                        super.mergeVarintField(1, readEnum);
                                    } else {
                                        this.bitField0_ |= 1;
                                        this.errorType_ = readEnum;
                                    }
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case DNSSD.NO_AUTO_RENAME:
                    if (PARSER == null) {
                        synchronized (AuthError.class) {
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

        public static AuthError getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AuthError> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DeviceAuthMessage extends GeneratedMessageLite<DeviceAuthMessage, Builder> implements DeviceAuthMessageOrBuilder {
        public static final int CHALLENGE_FIELD_NUMBER = 1;
        private static final DeviceAuthMessage DEFAULT_INSTANCE = new DeviceAuthMessage();
        public static final int ERROR_FIELD_NUMBER = 3;
        private static volatile Parser<DeviceAuthMessage> PARSER = null;
        public static final int RESPONSE_FIELD_NUMBER = 2;
        private int bitField0_;
        private AuthChallenge challenge_;
        private AuthError error_;
        private byte memoizedIsInitialized = -1;
        private AuthResponse response_;

        private DeviceAuthMessage() {
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
        public boolean hasChallenge() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
        public AuthChallenge getChallenge() {
            AuthChallenge authChallenge = this.challenge_;
            return authChallenge == null ? AuthChallenge.getDefaultInstance() : authChallenge;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChallenge(AuthChallenge authChallenge) {
            if (authChallenge != null) {
                this.challenge_ = authChallenge;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChallenge(AuthChallenge.Builder builder) {
            this.challenge_ = (AuthChallenge) builder.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeChallenge(AuthChallenge authChallenge) {
            AuthChallenge authChallenge2 = this.challenge_;
            if (authChallenge2 == null || authChallenge2 == AuthChallenge.getDefaultInstance()) {
                this.challenge_ = authChallenge;
            } else {
                this.challenge_ = (AuthChallenge) ((AuthChallenge.Builder) AuthChallenge.newBuilder(this.challenge_).mergeFrom((GeneratedMessageLite) authChallenge)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChallenge() {
            this.challenge_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
        public boolean hasResponse() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
        public AuthResponse getResponse() {
            AuthResponse authResponse = this.response_;
            return authResponse == null ? AuthResponse.getDefaultInstance() : authResponse;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setResponse(AuthResponse authResponse) {
            if (authResponse != null) {
                this.response_ = authResponse;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setResponse(AuthResponse.Builder builder) {
            this.response_ = (AuthResponse) builder.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeResponse(AuthResponse authResponse) {
            AuthResponse authResponse2 = this.response_;
            if (authResponse2 == null || authResponse2 == AuthResponse.getDefaultInstance()) {
                this.response_ = authResponse;
            } else {
                this.response_ = (AuthResponse) ((AuthResponse.Builder) AuthResponse.newBuilder(this.response_).mergeFrom((GeneratedMessageLite) authResponse)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearResponse() {
            this.response_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
        public boolean hasError() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
        public AuthError getError() {
            AuthError authError = this.error_;
            return authError == null ? AuthError.getDefaultInstance() : authError;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setError(AuthError authError) {
            if (authError != null) {
                this.error_ = authError;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setError(AuthError.Builder builder) {
            this.error_ = (AuthError) builder.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeError(AuthError authError) {
            AuthError authError2 = this.error_;
            if (authError2 == null || authError2 == AuthError.getDefaultInstance()) {
                this.error_ = authError;
            } else {
                this.error_ = (AuthError) ((AuthError.Builder) AuthError.newBuilder(this.error_).mergeFrom((GeneratedMessageLite) authError)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearError() {
            this.error_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(1, getChallenge());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, getResponse());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, getError());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getChallenge());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, getResponse());
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeMessageSize(3, getError());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static DeviceAuthMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DeviceAuthMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DeviceAuthMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DeviceAuthMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DeviceAuthMessage parseFrom(InputStream inputStream) throws IOException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceAuthMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceAuthMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DeviceAuthMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DeviceAuthMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DeviceAuthMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DeviceAuthMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DeviceAuthMessage deviceAuthMessage) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) deviceAuthMessage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DeviceAuthMessage, Builder> implements DeviceAuthMessageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(DeviceAuthMessage.DEFAULT_INSTANCE);
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
            public boolean hasChallenge() {
                return ((DeviceAuthMessage) this.instance).hasChallenge();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
            public AuthChallenge getChallenge() {
                return ((DeviceAuthMessage) this.instance).getChallenge();
            }

            public Builder setChallenge(AuthChallenge authChallenge) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).setChallenge((DeviceAuthMessage) authChallenge);
                return this;
            }

            public Builder setChallenge(AuthChallenge.Builder builder) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).setChallenge((DeviceAuthMessage) builder);
                return this;
            }

            public Builder mergeChallenge(AuthChallenge authChallenge) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).mergeChallenge(authChallenge);
                return this;
            }

            public Builder clearChallenge() {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).clearChallenge();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
            public boolean hasResponse() {
                return ((DeviceAuthMessage) this.instance).hasResponse();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
            public AuthResponse getResponse() {
                return ((DeviceAuthMessage) this.instance).getResponse();
            }

            public Builder setResponse(AuthResponse authResponse) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).setResponse((DeviceAuthMessage) authResponse);
                return this;
            }

            public Builder setResponse(AuthResponse.Builder builder) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).setResponse((DeviceAuthMessage) builder);
                return this;
            }

            public Builder mergeResponse(AuthResponse authResponse) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).mergeResponse(authResponse);
                return this;
            }

            public Builder clearResponse() {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).clearResponse();
                return this;
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
            public boolean hasError() {
                return ((DeviceAuthMessage) this.instance).hasError();
            }

            @Override // com.oculus.vrcast.googlecast.net.CastProtocol.DeviceAuthMessageOrBuilder
            public AuthError getError() {
                return ((DeviceAuthMessage) this.instance).getError();
            }

            public Builder setError(AuthError authError) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).setError((DeviceAuthMessage) authError);
                return this;
            }

            public Builder setError(AuthError.Builder builder) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).setError((DeviceAuthMessage) builder);
                return this;
            }

            public Builder mergeError(AuthError authError) {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).mergeError(authError);
                return this;
            }

            public Builder clearError() {
                copyOnWrite();
                ((DeviceAuthMessage) this.instance).clearError();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new DeviceAuthMessage();
                case 2:
                    byte b = this.memoizedIsInitialized;
                    if (b == 1) {
                        return DEFAULT_INSTANCE;
                    }
                    if (b == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (hasResponse() && !getResponse().isInitialized()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    } else if (!hasError() || getError().isInitialized()) {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 1;
                        }
                        return DEFAULT_INSTANCE;
                    } else {
                        if (booleanValue) {
                            this.memoizedIsInitialized = 0;
                        }
                        return null;
                    }
                case 3:
                    return null;
                case 4:
                    return new Builder(null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DeviceAuthMessage deviceAuthMessage = (DeviceAuthMessage) obj2;
                    this.challenge_ = (AuthChallenge) visitor.visitMessage(this.challenge_, deviceAuthMessage.challenge_);
                    this.response_ = (AuthResponse) visitor.visitMessage(this.response_, deviceAuthMessage.response_);
                    this.error_ = (AuthError) visitor.visitMessage(this.error_, deviceAuthMessage.error_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= deviceAuthMessage.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    AuthChallenge.Builder builder = (this.bitField0_ & 1) == 1 ? (AuthChallenge.Builder) this.challenge_.toBuilder() : null;
                                    this.challenge_ = (AuthChallenge) codedInputStream.readMessage(AuthChallenge.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom((GeneratedMessageLite) this.challenge_);
                                        this.challenge_ = (AuthChallenge) builder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (readTag == 18) {
                                    AuthResponse.Builder builder2 = (this.bitField0_ & 2) == 2 ? (AuthResponse.Builder) this.response_.toBuilder() : null;
                                    this.response_ = (AuthResponse) codedInputStream.readMessage(AuthResponse.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom((GeneratedMessageLite) this.response_);
                                        this.response_ = (AuthResponse) builder2.buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                } else if (readTag == 26) {
                                    AuthError.Builder builder3 = (this.bitField0_ & 4) == 4 ? (AuthError.Builder) this.error_.toBuilder() : null;
                                    this.error_ = (AuthError) codedInputStream.readMessage(AuthError.parser(), extensionRegistryLite);
                                    if (builder3 != null) {
                                        builder3.mergeFrom((GeneratedMessageLite) this.error_);
                                        this.error_ = (AuthError) builder3.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case DNSSD.NO_AUTO_RENAME:
                    if (PARSER == null) {
                        synchronized (DeviceAuthMessage.class) {
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

        public static DeviceAuthMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DeviceAuthMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
