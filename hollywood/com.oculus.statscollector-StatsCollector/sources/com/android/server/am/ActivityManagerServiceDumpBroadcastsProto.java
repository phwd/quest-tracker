package com.android.server.am;

import android.os.LooperProto;
import com.android.server.IntentResolverProto;
import com.android.server.am.BroadcastQueueProto;
import com.android.server.am.ReceiverListProto;
import com.android.server.am.StickyBroadcastProto;
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

public final class ActivityManagerServiceDumpBroadcastsProto extends GeneratedMessageLite<ActivityManagerServiceDumpBroadcastsProto, Builder> implements ActivityManagerServiceDumpBroadcastsProtoOrBuilder {
    public static final int BROADCAST_QUEUE_FIELD_NUMBER = 3;
    private static final ActivityManagerServiceDumpBroadcastsProto DEFAULT_INSTANCE = new ActivityManagerServiceDumpBroadcastsProto();
    public static final int HANDLER_FIELD_NUMBER = 5;
    private static volatile Parser<ActivityManagerServiceDumpBroadcastsProto> PARSER = null;
    public static final int RECEIVER_LIST_FIELD_NUMBER = 1;
    public static final int RECEIVER_RESOLVER_FIELD_NUMBER = 2;
    public static final int STICKY_BROADCASTS_FIELD_NUMBER = 4;
    private int bitField0_;
    private Internal.ProtobufList<BroadcastQueueProto> broadcastQueue_ = emptyProtobufList();
    private MainHandler handler_;
    private Internal.ProtobufList<ReceiverListProto> receiverList_ = emptyProtobufList();
    private IntentResolverProto receiverResolver_;
    private Internal.ProtobufList<StickyBroadcastProto> stickyBroadcasts_ = emptyProtobufList();

    public interface MainHandlerOrBuilder extends MessageLiteOrBuilder {
        String getHandler();

        ByteString getHandlerBytes();

        LooperProto getLooper();

        boolean hasHandler();

        boolean hasLooper();
    }

    private ActivityManagerServiceDumpBroadcastsProto() {
    }

    public static final class MainHandler extends GeneratedMessageLite<MainHandler, Builder> implements MainHandlerOrBuilder {
        private static final MainHandler DEFAULT_INSTANCE = new MainHandler();
        public static final int HANDLER_FIELD_NUMBER = 1;
        public static final int LOOPER_FIELD_NUMBER = 2;
        private static volatile Parser<MainHandler> PARSER;
        private int bitField0_;
        private String handler_ = "";
        private LooperProto looper_;

        private MainHandler() {
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
        public boolean hasHandler() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
        public String getHandler() {
            return this.handler_;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
        public ByteString getHandlerBytes() {
            return ByteString.copyFromUtf8(this.handler_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHandler(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.handler_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHandler() {
            this.bitField0_ &= -2;
            this.handler_ = getDefaultInstance().getHandler();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHandlerBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.handler_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
        public boolean hasLooper() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
        public LooperProto getLooper() {
            LooperProto looperProto = this.looper_;
            return looperProto == null ? LooperProto.getDefaultInstance() : looperProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLooper(LooperProto value) {
            if (value != null) {
                this.looper_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLooper(LooperProto.Builder builderForValue) {
            this.looper_ = (LooperProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeLooper(LooperProto value) {
            LooperProto looperProto = this.looper_;
            if (looperProto == null || looperProto == LooperProto.getDefaultInstance()) {
                this.looper_ = value;
            } else {
                this.looper_ = (LooperProto) ((LooperProto.Builder) LooperProto.newBuilder(this.looper_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLooper() {
            this.looper_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getHandler());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getLooper());
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getHandler());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getLooper());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static MainHandler parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (MainHandler) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MainHandler parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MainHandler) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MainHandler parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (MainHandler) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static MainHandler parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (MainHandler) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static MainHandler parseFrom(InputStream input) throws IOException {
            return (MainHandler) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MainHandler parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MainHandler) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MainHandler parseDelimitedFrom(InputStream input) throws IOException {
            return (MainHandler) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static MainHandler parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MainHandler) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static MainHandler parseFrom(CodedInputStream input) throws IOException {
            return (MainHandler) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static MainHandler parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (MainHandler) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MainHandler prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MainHandler, Builder> implements MainHandlerOrBuilder {
            private Builder() {
                super(MainHandler.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
            public boolean hasHandler() {
                return ((MainHandler) this.instance).hasHandler();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
            public String getHandler() {
                return ((MainHandler) this.instance).getHandler();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
            public ByteString getHandlerBytes() {
                return ((MainHandler) this.instance).getHandlerBytes();
            }

            public Builder setHandler(String value) {
                copyOnWrite();
                ((MainHandler) this.instance).setHandler(value);
                return this;
            }

            public Builder clearHandler() {
                copyOnWrite();
                ((MainHandler) this.instance).clearHandler();
                return this;
            }

            public Builder setHandlerBytes(ByteString value) {
                copyOnWrite();
                ((MainHandler) this.instance).setHandlerBytes(value);
                return this;
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
            public boolean hasLooper() {
                return ((MainHandler) this.instance).hasLooper();
            }

            @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProto.MainHandlerOrBuilder
            public LooperProto getLooper() {
                return ((MainHandler) this.instance).getLooper();
            }

            public Builder setLooper(LooperProto value) {
                copyOnWrite();
                ((MainHandler) this.instance).setLooper((MainHandler) value);
                return this;
            }

            public Builder setLooper(LooperProto.Builder builderForValue) {
                copyOnWrite();
                ((MainHandler) this.instance).setLooper((MainHandler) builderForValue);
                return this;
            }

            public Builder mergeLooper(LooperProto value) {
                copyOnWrite();
                ((MainHandler) this.instance).mergeLooper(value);
                return this;
            }

            public Builder clearLooper() {
                copyOnWrite();
                ((MainHandler) this.instance).clearLooper();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new MainHandler();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    MainHandler other = (MainHandler) arg1;
                    this.handler_ = visitor.visitString(hasHandler(), this.handler_, other.hasHandler(), other.handler_);
                    this.looper_ = (LooperProto) visitor.visitMessage(this.looper_, other.looper_);
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
                                String s = input.readString();
                                this.bitField0_ |= 1;
                                this.handler_ = s;
                            } else if (tag == 18) {
                                LooperProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder = (LooperProto.Builder) this.looper_.toBuilder();
                                }
                                this.looper_ = (LooperProto) input.readMessage(LooperProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.looper_);
                                    this.looper_ = (LooperProto) subBuilder.buildPartial();
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
                        synchronized (MainHandler.class) {
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

        public static MainHandler getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MainHandler> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public List<ReceiverListProto> getReceiverListList() {
        return this.receiverList_;
    }

    public List<? extends ReceiverListProtoOrBuilder> getReceiverListOrBuilderList() {
        return this.receiverList_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public int getReceiverListCount() {
        return this.receiverList_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public ReceiverListProto getReceiverList(int index) {
        return this.receiverList_.get(index);
    }

    public ReceiverListProtoOrBuilder getReceiverListOrBuilder(int index) {
        return this.receiverList_.get(index);
    }

    private void ensureReceiverListIsMutable() {
        if (!this.receiverList_.isModifiable()) {
            this.receiverList_ = GeneratedMessageLite.mutableCopy(this.receiverList_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReceiverList(int index, ReceiverListProto value) {
        if (value != null) {
            ensureReceiverListIsMutable();
            this.receiverList_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReceiverList(int index, ReceiverListProto.Builder builderForValue) {
        ensureReceiverListIsMutable();
        this.receiverList_.set(index, (ReceiverListProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addReceiverList(ReceiverListProto value) {
        if (value != null) {
            ensureReceiverListIsMutable();
            this.receiverList_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addReceiverList(int index, ReceiverListProto value) {
        if (value != null) {
            ensureReceiverListIsMutable();
            this.receiverList_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addReceiverList(ReceiverListProto.Builder builderForValue) {
        ensureReceiverListIsMutable();
        this.receiverList_.add((ReceiverListProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addReceiverList(int index, ReceiverListProto.Builder builderForValue) {
        ensureReceiverListIsMutable();
        this.receiverList_.add(index, (ReceiverListProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllReceiverList(Iterable<? extends ReceiverListProto> values) {
        ensureReceiverListIsMutable();
        AbstractMessageLite.addAll(values, this.receiverList_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReceiverList() {
        this.receiverList_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeReceiverList(int index) {
        ensureReceiverListIsMutable();
        this.receiverList_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public boolean hasReceiverResolver() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public IntentResolverProto getReceiverResolver() {
        IntentResolverProto intentResolverProto = this.receiverResolver_;
        return intentResolverProto == null ? IntentResolverProto.getDefaultInstance() : intentResolverProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReceiverResolver(IntentResolverProto value) {
        if (value != null) {
            this.receiverResolver_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReceiverResolver(IntentResolverProto.Builder builderForValue) {
        this.receiverResolver_ = (IntentResolverProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeReceiverResolver(IntentResolverProto value) {
        IntentResolverProto intentResolverProto = this.receiverResolver_;
        if (intentResolverProto == null || intentResolverProto == IntentResolverProto.getDefaultInstance()) {
            this.receiverResolver_ = value;
        } else {
            this.receiverResolver_ = (IntentResolverProto) ((IntentResolverProto.Builder) IntentResolverProto.newBuilder(this.receiverResolver_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReceiverResolver() {
        this.receiverResolver_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public List<BroadcastQueueProto> getBroadcastQueueList() {
        return this.broadcastQueue_;
    }

    public List<? extends BroadcastQueueProtoOrBuilder> getBroadcastQueueOrBuilderList() {
        return this.broadcastQueue_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public int getBroadcastQueueCount() {
        return this.broadcastQueue_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public BroadcastQueueProto getBroadcastQueue(int index) {
        return this.broadcastQueue_.get(index);
    }

    public BroadcastQueueProtoOrBuilder getBroadcastQueueOrBuilder(int index) {
        return this.broadcastQueue_.get(index);
    }

    private void ensureBroadcastQueueIsMutable() {
        if (!this.broadcastQueue_.isModifiable()) {
            this.broadcastQueue_ = GeneratedMessageLite.mutableCopy(this.broadcastQueue_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcastQueue(int index, BroadcastQueueProto value) {
        if (value != null) {
            ensureBroadcastQueueIsMutable();
            this.broadcastQueue_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcastQueue(int index, BroadcastQueueProto.Builder builderForValue) {
        ensureBroadcastQueueIsMutable();
        this.broadcastQueue_.set(index, (BroadcastQueueProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBroadcastQueue(BroadcastQueueProto value) {
        if (value != null) {
            ensureBroadcastQueueIsMutable();
            this.broadcastQueue_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBroadcastQueue(int index, BroadcastQueueProto value) {
        if (value != null) {
            ensureBroadcastQueueIsMutable();
            this.broadcastQueue_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBroadcastQueue(BroadcastQueueProto.Builder builderForValue) {
        ensureBroadcastQueueIsMutable();
        this.broadcastQueue_.add((BroadcastQueueProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addBroadcastQueue(int index, BroadcastQueueProto.Builder builderForValue) {
        ensureBroadcastQueueIsMutable();
        this.broadcastQueue_.add(index, (BroadcastQueueProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllBroadcastQueue(Iterable<? extends BroadcastQueueProto> values) {
        ensureBroadcastQueueIsMutable();
        AbstractMessageLite.addAll(values, this.broadcastQueue_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBroadcastQueue() {
        this.broadcastQueue_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeBroadcastQueue(int index) {
        ensureBroadcastQueueIsMutable();
        this.broadcastQueue_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public List<StickyBroadcastProto> getStickyBroadcastsList() {
        return this.stickyBroadcasts_;
    }

    public List<? extends StickyBroadcastProtoOrBuilder> getStickyBroadcastsOrBuilderList() {
        return this.stickyBroadcasts_;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public int getStickyBroadcastsCount() {
        return this.stickyBroadcasts_.size();
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public StickyBroadcastProto getStickyBroadcasts(int index) {
        return this.stickyBroadcasts_.get(index);
    }

    public StickyBroadcastProtoOrBuilder getStickyBroadcastsOrBuilder(int index) {
        return this.stickyBroadcasts_.get(index);
    }

    private void ensureStickyBroadcastsIsMutable() {
        if (!this.stickyBroadcasts_.isModifiable()) {
            this.stickyBroadcasts_ = GeneratedMessageLite.mutableCopy(this.stickyBroadcasts_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStickyBroadcasts(int index, StickyBroadcastProto value) {
        if (value != null) {
            ensureStickyBroadcastsIsMutable();
            this.stickyBroadcasts_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStickyBroadcasts(int index, StickyBroadcastProto.Builder builderForValue) {
        ensureStickyBroadcastsIsMutable();
        this.stickyBroadcasts_.set(index, (StickyBroadcastProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStickyBroadcasts(StickyBroadcastProto value) {
        if (value != null) {
            ensureStickyBroadcastsIsMutable();
            this.stickyBroadcasts_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStickyBroadcasts(int index, StickyBroadcastProto value) {
        if (value != null) {
            ensureStickyBroadcastsIsMutable();
            this.stickyBroadcasts_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStickyBroadcasts(StickyBroadcastProto.Builder builderForValue) {
        ensureStickyBroadcastsIsMutable();
        this.stickyBroadcasts_.add((StickyBroadcastProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addStickyBroadcasts(int index, StickyBroadcastProto.Builder builderForValue) {
        ensureStickyBroadcastsIsMutable();
        this.stickyBroadcasts_.add(index, (StickyBroadcastProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllStickyBroadcasts(Iterable<? extends StickyBroadcastProto> values) {
        ensureStickyBroadcastsIsMutable();
        AbstractMessageLite.addAll(values, this.stickyBroadcasts_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStickyBroadcasts() {
        this.stickyBroadcasts_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeStickyBroadcasts(int index) {
        ensureStickyBroadcastsIsMutable();
        this.stickyBroadcasts_.remove(index);
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public boolean hasHandler() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
    public MainHandler getHandler() {
        MainHandler mainHandler = this.handler_;
        return mainHandler == null ? MainHandler.getDefaultInstance() : mainHandler;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHandler(MainHandler value) {
        if (value != null) {
            this.handler_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHandler(MainHandler.Builder builderForValue) {
        this.handler_ = (MainHandler) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHandler(MainHandler value) {
        MainHandler mainHandler = this.handler_;
        if (mainHandler == null || mainHandler == MainHandler.getDefaultInstance()) {
            this.handler_ = value;
        } else {
            this.handler_ = (MainHandler) ((MainHandler.Builder) MainHandler.newBuilder(this.handler_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHandler() {
        this.handler_ = null;
        this.bitField0_ &= -3;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.receiverList_.size(); i++) {
            output.writeMessage(1, this.receiverList_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(2, getReceiverResolver());
        }
        for (int i2 = 0; i2 < this.broadcastQueue_.size(); i2++) {
            output.writeMessage(3, this.broadcastQueue_.get(i2));
        }
        for (int i3 = 0; i3 < this.stickyBroadcasts_.size(); i3++) {
            output.writeMessage(4, this.stickyBroadcasts_.get(i3));
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(5, getHandler());
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
        for (int i = 0; i < this.receiverList_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.receiverList_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(2, getReceiverResolver());
        }
        for (int i2 = 0; i2 < this.broadcastQueue_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.broadcastQueue_.get(i2));
        }
        for (int i3 = 0; i3 < this.stickyBroadcasts_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.stickyBroadcasts_.get(i3));
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(5, getHandler());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpBroadcastsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpBroadcastsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpBroadcastsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ActivityManagerServiceDumpBroadcastsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceDumpBroadcastsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpBroadcastsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ActivityManagerServiceDumpBroadcastsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpBroadcastsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseFrom(CodedInputStream input) throws IOException {
        return (ActivityManagerServiceDumpBroadcastsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ActivityManagerServiceDumpBroadcastsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ActivityManagerServiceDumpBroadcastsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ActivityManagerServiceDumpBroadcastsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ActivityManagerServiceDumpBroadcastsProto, Builder> implements ActivityManagerServiceDumpBroadcastsProtoOrBuilder {
        private Builder() {
            super(ActivityManagerServiceDumpBroadcastsProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public List<ReceiverListProto> getReceiverListList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpBroadcastsProto) this.instance).getReceiverListList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public int getReceiverListCount() {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).getReceiverListCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public ReceiverListProto getReceiverList(int index) {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).getReceiverList(index);
        }

        public Builder setReceiverList(int index, ReceiverListProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setReceiverList((ActivityManagerServiceDumpBroadcastsProto) index, (int) value);
            return this;
        }

        public Builder setReceiverList(int index, ReceiverListProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setReceiverList((ActivityManagerServiceDumpBroadcastsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addReceiverList(ReceiverListProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addReceiverList((ActivityManagerServiceDumpBroadcastsProto) value);
            return this;
        }

        public Builder addReceiverList(int index, ReceiverListProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addReceiverList((ActivityManagerServiceDumpBroadcastsProto) index, (int) value);
            return this;
        }

        public Builder addReceiverList(ReceiverListProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addReceiverList((ActivityManagerServiceDumpBroadcastsProto) builderForValue);
            return this;
        }

        public Builder addReceiverList(int index, ReceiverListProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addReceiverList((ActivityManagerServiceDumpBroadcastsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllReceiverList(Iterable<? extends ReceiverListProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addAllReceiverList(values);
            return this;
        }

        public Builder clearReceiverList() {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).clearReceiverList();
            return this;
        }

        public Builder removeReceiverList(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).removeReceiverList(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public boolean hasReceiverResolver() {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).hasReceiverResolver();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public IntentResolverProto getReceiverResolver() {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).getReceiverResolver();
        }

        public Builder setReceiverResolver(IntentResolverProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setReceiverResolver((ActivityManagerServiceDumpBroadcastsProto) value);
            return this;
        }

        public Builder setReceiverResolver(IntentResolverProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setReceiverResolver((ActivityManagerServiceDumpBroadcastsProto) builderForValue);
            return this;
        }

        public Builder mergeReceiverResolver(IntentResolverProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).mergeReceiverResolver(value);
            return this;
        }

        public Builder clearReceiverResolver() {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).clearReceiverResolver();
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public List<BroadcastQueueProto> getBroadcastQueueList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpBroadcastsProto) this.instance).getBroadcastQueueList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public int getBroadcastQueueCount() {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).getBroadcastQueueCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public BroadcastQueueProto getBroadcastQueue(int index) {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).getBroadcastQueue(index);
        }

        public Builder setBroadcastQueue(int index, BroadcastQueueProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setBroadcastQueue((ActivityManagerServiceDumpBroadcastsProto) index, (int) value);
            return this;
        }

        public Builder setBroadcastQueue(int index, BroadcastQueueProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setBroadcastQueue((ActivityManagerServiceDumpBroadcastsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addBroadcastQueue(BroadcastQueueProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addBroadcastQueue((ActivityManagerServiceDumpBroadcastsProto) value);
            return this;
        }

        public Builder addBroadcastQueue(int index, BroadcastQueueProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addBroadcastQueue((ActivityManagerServiceDumpBroadcastsProto) index, (int) value);
            return this;
        }

        public Builder addBroadcastQueue(BroadcastQueueProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addBroadcastQueue((ActivityManagerServiceDumpBroadcastsProto) builderForValue);
            return this;
        }

        public Builder addBroadcastQueue(int index, BroadcastQueueProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addBroadcastQueue((ActivityManagerServiceDumpBroadcastsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllBroadcastQueue(Iterable<? extends BroadcastQueueProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addAllBroadcastQueue(values);
            return this;
        }

        public Builder clearBroadcastQueue() {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).clearBroadcastQueue();
            return this;
        }

        public Builder removeBroadcastQueue(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).removeBroadcastQueue(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public List<StickyBroadcastProto> getStickyBroadcastsList() {
            return Collections.unmodifiableList(((ActivityManagerServiceDumpBroadcastsProto) this.instance).getStickyBroadcastsList());
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public int getStickyBroadcastsCount() {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).getStickyBroadcastsCount();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public StickyBroadcastProto getStickyBroadcasts(int index) {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).getStickyBroadcasts(index);
        }

        public Builder setStickyBroadcasts(int index, StickyBroadcastProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setStickyBroadcasts((ActivityManagerServiceDumpBroadcastsProto) index, (int) value);
            return this;
        }

        public Builder setStickyBroadcasts(int index, StickyBroadcastProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setStickyBroadcasts((ActivityManagerServiceDumpBroadcastsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addStickyBroadcasts(StickyBroadcastProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addStickyBroadcasts((ActivityManagerServiceDumpBroadcastsProto) value);
            return this;
        }

        public Builder addStickyBroadcasts(int index, StickyBroadcastProto value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addStickyBroadcasts((ActivityManagerServiceDumpBroadcastsProto) index, (int) value);
            return this;
        }

        public Builder addStickyBroadcasts(StickyBroadcastProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addStickyBroadcasts((ActivityManagerServiceDumpBroadcastsProto) builderForValue);
            return this;
        }

        public Builder addStickyBroadcasts(int index, StickyBroadcastProto.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addStickyBroadcasts((ActivityManagerServiceDumpBroadcastsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllStickyBroadcasts(Iterable<? extends StickyBroadcastProto> values) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).addAllStickyBroadcasts(values);
            return this;
        }

        public Builder clearStickyBroadcasts() {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).clearStickyBroadcasts();
            return this;
        }

        public Builder removeStickyBroadcasts(int index) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).removeStickyBroadcasts(index);
            return this;
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public boolean hasHandler() {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).hasHandler();
        }

        @Override // com.android.server.am.ActivityManagerServiceDumpBroadcastsProtoOrBuilder
        public MainHandler getHandler() {
            return ((ActivityManagerServiceDumpBroadcastsProto) this.instance).getHandler();
        }

        public Builder setHandler(MainHandler value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setHandler((ActivityManagerServiceDumpBroadcastsProto) value);
            return this;
        }

        public Builder setHandler(MainHandler.Builder builderForValue) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).setHandler((ActivityManagerServiceDumpBroadcastsProto) builderForValue);
            return this;
        }

        public Builder mergeHandler(MainHandler value) {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).mergeHandler(value);
            return this;
        }

        public Builder clearHandler() {
            copyOnWrite();
            ((ActivityManagerServiceDumpBroadcastsProto) this.instance).clearHandler();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ActivityManagerServiceDumpBroadcastsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.receiverList_.makeImmutable();
                this.broadcastQueue_.makeImmutable();
                this.stickyBroadcasts_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ActivityManagerServiceDumpBroadcastsProto other = (ActivityManagerServiceDumpBroadcastsProto) arg1;
                this.receiverList_ = visitor.visitList(this.receiverList_, other.receiverList_);
                this.receiverResolver_ = (IntentResolverProto) visitor.visitMessage(this.receiverResolver_, other.receiverResolver_);
                this.broadcastQueue_ = visitor.visitList(this.broadcastQueue_, other.broadcastQueue_);
                this.stickyBroadcasts_ = visitor.visitList(this.stickyBroadcasts_, other.stickyBroadcasts_);
                this.handler_ = (MainHandler) visitor.visitMessage(this.handler_, other.handler_);
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
                            if (!this.receiverList_.isModifiable()) {
                                this.receiverList_ = GeneratedMessageLite.mutableCopy(this.receiverList_);
                            }
                            this.receiverList_.add((ReceiverListProto) input.readMessage(ReceiverListProto.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            IntentResolverProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (IntentResolverProto.Builder) this.receiverResolver_.toBuilder();
                            }
                            this.receiverResolver_ = (IntentResolverProto) input.readMessage(IntentResolverProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.receiverResolver_);
                                this.receiverResolver_ = (IntentResolverProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 26) {
                            if (!this.broadcastQueue_.isModifiable()) {
                                this.broadcastQueue_ = GeneratedMessageLite.mutableCopy(this.broadcastQueue_);
                            }
                            this.broadcastQueue_.add((BroadcastQueueProto) input.readMessage(BroadcastQueueProto.parser(), extensionRegistry));
                        } else if (tag == 34) {
                            if (!this.stickyBroadcasts_.isModifiable()) {
                                this.stickyBroadcasts_ = GeneratedMessageLite.mutableCopy(this.stickyBroadcasts_);
                            }
                            this.stickyBroadcasts_.add((StickyBroadcastProto) input.readMessage(StickyBroadcastProto.parser(), extensionRegistry));
                        } else if (tag == 42) {
                            MainHandler.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (MainHandler.Builder) this.handler_.toBuilder();
                            }
                            this.handler_ = (MainHandler) input.readMessage(MainHandler.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.handler_);
                                this.handler_ = (MainHandler) subBuilder2.buildPartial();
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
                    synchronized (ActivityManagerServiceDumpBroadcastsProto.class) {
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

    public static ActivityManagerServiceDumpBroadcastsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ActivityManagerServiceDumpBroadcastsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
