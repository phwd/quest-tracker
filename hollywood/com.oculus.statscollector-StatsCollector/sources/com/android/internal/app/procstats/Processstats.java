package com.android.internal.app.procstats;

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

public final class Processstats {

    public interface ProcessStatsProtoOrBuilder extends MessageLiteOrBuilder {
    }

    private Processstats() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class ProcessStatsProto extends GeneratedMessageLite<ProcessStatsProto, Builder> implements ProcessStatsProtoOrBuilder {
        private static final ProcessStatsProto DEFAULT_INSTANCE = new ProcessStatsProto();
        private static volatile Parser<ProcessStatsProto> PARSER;

        private ProcessStatsProto() {
        }

        public enum MemoryFactor implements Internal.EnumLite {
            MEM_FACTOR_NORMAL(0),
            MEM_FACTOR_MODERATE(1),
            MEM_FACTOR_LOW(2),
            MEM_FACTOR_CRITICAL(3);
            
            public static final int MEM_FACTOR_CRITICAL_VALUE = 3;
            public static final int MEM_FACTOR_LOW_VALUE = 2;
            public static final int MEM_FACTOR_MODERATE_VALUE = 1;
            public static final int MEM_FACTOR_NORMAL_VALUE = 0;
            private static final Internal.EnumLiteMap<MemoryFactor> internalValueMap = new Internal.EnumLiteMap<MemoryFactor>() {
                /* class com.android.internal.app.procstats.Processstats.ProcessStatsProto.MemoryFactor.AnonymousClass1 */

                @Override // com.google.protobuf.Internal.EnumLiteMap
                public MemoryFactor findValueByNumber(int number) {
                    return MemoryFactor.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static MemoryFactor valueOf(int value2) {
                return forNumber(value2);
            }

            public static MemoryFactor forNumber(int value2) {
                if (value2 == 0) {
                    return MEM_FACTOR_NORMAL;
                }
                if (value2 == 1) {
                    return MEM_FACTOR_MODERATE;
                }
                if (value2 == 2) {
                    return MEM_FACTOR_LOW;
                }
                if (value2 != 3) {
                    return null;
                }
                return MEM_FACTOR_CRITICAL;
            }

            public static Internal.EnumLiteMap<MemoryFactor> internalGetValueMap() {
                return internalValueMap;
            }

            private MemoryFactor(int value2) {
                this.value = value2;
            }
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            this.unknownFields.writeTo(output);
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static ProcessStatsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProcessStatsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProcessStatsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProcessStatsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProcessStatsProto parseFrom(InputStream input) throws IOException {
            return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessStatsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProcessStatsProto parseDelimitedFrom(InputStream input) throws IOException {
            return (ProcessStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessStatsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessStatsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProcessStatsProto parseFrom(CodedInputStream input) throws IOException {
            return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProcessStatsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProcessStatsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ProcessStatsProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ProcessStatsProto, Builder> implements ProcessStatsProtoOrBuilder {
            private Builder() {
                super(ProcessStatsProto.DEFAULT_INSTANCE);
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ProcessStatsProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ProcessStatsProto processStatsProto = (ProcessStatsProto) arg1;
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                        synchronized (ProcessStatsProto.class) {
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

        public static ProcessStatsProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ProcessStatsProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
