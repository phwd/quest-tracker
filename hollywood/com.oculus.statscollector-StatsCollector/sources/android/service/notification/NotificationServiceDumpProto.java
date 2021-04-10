package android.service.notification;

import android.service.notification.ListenersDisablingEffectsProto;
import android.service.notification.ManagedServicesProto;
import android.service.notification.NotificationRecordProto;
import android.service.notification.RankingHelperProto;
import android.service.notification.ZenModeProto;
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

public final class NotificationServiceDumpProto extends GeneratedMessageLite<NotificationServiceDumpProto, Builder> implements NotificationServiceDumpProtoOrBuilder {
    public static final int CONDITION_PROVIDERS_FIELD_NUMBER = 7;
    private static final NotificationServiceDumpProto DEFAULT_INSTANCE = new NotificationServiceDumpProto();
    public static final int LISTENERS_DISABLING_EFFECTS_FIELD_NUMBER = 5;
    public static final int LISTENER_HINTS_FIELD_NUMBER = 4;
    public static final int NOTIFICATION_ASSISTANTS_FIELD_NUMBER = 6;
    public static final int NOTIFICATION_LISTENERS_FIELD_NUMBER = 3;
    private static volatile Parser<NotificationServiceDumpProto> PARSER = null;
    public static final int RANKING_CONFIG_FIELD_NUMBER = 8;
    public static final int RECORDS_FIELD_NUMBER = 1;
    public static final int ZEN_FIELD_NUMBER = 2;
    private int bitField0_;
    private ManagedServicesProto conditionProviders_;
    private int listenerHints_ = 0;
    private Internal.ProtobufList<ListenersDisablingEffectsProto> listenersDisablingEffects_ = emptyProtobufList();
    private ManagedServicesProto notificationAssistants_;
    private ManagedServicesProto notificationListeners_;
    private RankingHelperProto rankingConfig_;
    private Internal.ProtobufList<NotificationRecordProto> records_ = emptyProtobufList();
    private ZenModeProto zen_;

    private NotificationServiceDumpProto() {
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public List<NotificationRecordProto> getRecordsList() {
        return this.records_;
    }

    public List<? extends NotificationRecordProtoOrBuilder> getRecordsOrBuilderList() {
        return this.records_;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public int getRecordsCount() {
        return this.records_.size();
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public NotificationRecordProto getRecords(int index) {
        return this.records_.get(index);
    }

    public NotificationRecordProtoOrBuilder getRecordsOrBuilder(int index) {
        return this.records_.get(index);
    }

    private void ensureRecordsIsMutable() {
        if (!this.records_.isModifiable()) {
            this.records_ = GeneratedMessageLite.mutableCopy(this.records_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecords(int index, NotificationRecordProto value) {
        if (value != null) {
            ensureRecordsIsMutable();
            this.records_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecords(int index, NotificationRecordProto.Builder builderForValue) {
        ensureRecordsIsMutable();
        this.records_.set(index, (NotificationRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecords(NotificationRecordProto value) {
        if (value != null) {
            ensureRecordsIsMutable();
            this.records_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecords(int index, NotificationRecordProto value) {
        if (value != null) {
            ensureRecordsIsMutable();
            this.records_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecords(NotificationRecordProto.Builder builderForValue) {
        ensureRecordsIsMutable();
        this.records_.add((NotificationRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecords(int index, NotificationRecordProto.Builder builderForValue) {
        ensureRecordsIsMutable();
        this.records_.add(index, (NotificationRecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRecords(Iterable<? extends NotificationRecordProto> values) {
        ensureRecordsIsMutable();
        AbstractMessageLite.addAll(values, this.records_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRecords() {
        this.records_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRecords(int index) {
        ensureRecordsIsMutable();
        this.records_.remove(index);
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public boolean hasZen() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public ZenModeProto getZen() {
        ZenModeProto zenModeProto = this.zen_;
        return zenModeProto == null ? ZenModeProto.getDefaultInstance() : zenModeProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZen(ZenModeProto value) {
        if (value != null) {
            this.zen_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setZen(ZenModeProto.Builder builderForValue) {
        this.zen_ = (ZenModeProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeZen(ZenModeProto value) {
        ZenModeProto zenModeProto = this.zen_;
        if (zenModeProto == null || zenModeProto == ZenModeProto.getDefaultInstance()) {
            this.zen_ = value;
        } else {
            this.zen_ = (ZenModeProto) ((ZenModeProto.Builder) ZenModeProto.newBuilder(this.zen_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearZen() {
        this.zen_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public boolean hasNotificationListeners() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public ManagedServicesProto getNotificationListeners() {
        ManagedServicesProto managedServicesProto = this.notificationListeners_;
        return managedServicesProto == null ? ManagedServicesProto.getDefaultInstance() : managedServicesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotificationListeners(ManagedServicesProto value) {
        if (value != null) {
            this.notificationListeners_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotificationListeners(ManagedServicesProto.Builder builderForValue) {
        this.notificationListeners_ = (ManagedServicesProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNotificationListeners(ManagedServicesProto value) {
        ManagedServicesProto managedServicesProto = this.notificationListeners_;
        if (managedServicesProto == null || managedServicesProto == ManagedServicesProto.getDefaultInstance()) {
            this.notificationListeners_ = value;
        } else {
            this.notificationListeners_ = (ManagedServicesProto) ((ManagedServicesProto.Builder) ManagedServicesProto.newBuilder(this.notificationListeners_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotificationListeners() {
        this.notificationListeners_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public boolean hasListenerHints() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public int getListenerHints() {
        return this.listenerHints_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenerHints(int value) {
        this.bitField0_ |= 4;
        this.listenerHints_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearListenerHints() {
        this.bitField0_ &= -5;
        this.listenerHints_ = 0;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public List<ListenersDisablingEffectsProto> getListenersDisablingEffectsList() {
        return this.listenersDisablingEffects_;
    }

    public List<? extends ListenersDisablingEffectsProtoOrBuilder> getListenersDisablingEffectsOrBuilderList() {
        return this.listenersDisablingEffects_;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public int getListenersDisablingEffectsCount() {
        return this.listenersDisablingEffects_.size();
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public ListenersDisablingEffectsProto getListenersDisablingEffects(int index) {
        return this.listenersDisablingEffects_.get(index);
    }

    public ListenersDisablingEffectsProtoOrBuilder getListenersDisablingEffectsOrBuilder(int index) {
        return this.listenersDisablingEffects_.get(index);
    }

    private void ensureListenersDisablingEffectsIsMutable() {
        if (!this.listenersDisablingEffects_.isModifiable()) {
            this.listenersDisablingEffects_ = GeneratedMessageLite.mutableCopy(this.listenersDisablingEffects_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenersDisablingEffects(int index, ListenersDisablingEffectsProto value) {
        if (value != null) {
            ensureListenersDisablingEffectsIsMutable();
            this.listenersDisablingEffects_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setListenersDisablingEffects(int index, ListenersDisablingEffectsProto.Builder builderForValue) {
        ensureListenersDisablingEffectsIsMutable();
        this.listenersDisablingEffects_.set(index, (ListenersDisablingEffectsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addListenersDisablingEffects(ListenersDisablingEffectsProto value) {
        if (value != null) {
            ensureListenersDisablingEffectsIsMutable();
            this.listenersDisablingEffects_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addListenersDisablingEffects(int index, ListenersDisablingEffectsProto value) {
        if (value != null) {
            ensureListenersDisablingEffectsIsMutable();
            this.listenersDisablingEffects_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addListenersDisablingEffects(ListenersDisablingEffectsProto.Builder builderForValue) {
        ensureListenersDisablingEffectsIsMutable();
        this.listenersDisablingEffects_.add((ListenersDisablingEffectsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addListenersDisablingEffects(int index, ListenersDisablingEffectsProto.Builder builderForValue) {
        ensureListenersDisablingEffectsIsMutable();
        this.listenersDisablingEffects_.add(index, (ListenersDisablingEffectsProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllListenersDisablingEffects(Iterable<? extends ListenersDisablingEffectsProto> values) {
        ensureListenersDisablingEffectsIsMutable();
        AbstractMessageLite.addAll(values, this.listenersDisablingEffects_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearListenersDisablingEffects() {
        this.listenersDisablingEffects_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeListenersDisablingEffects(int index) {
        ensureListenersDisablingEffectsIsMutable();
        this.listenersDisablingEffects_.remove(index);
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public boolean hasNotificationAssistants() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public ManagedServicesProto getNotificationAssistants() {
        ManagedServicesProto managedServicesProto = this.notificationAssistants_;
        return managedServicesProto == null ? ManagedServicesProto.getDefaultInstance() : managedServicesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotificationAssistants(ManagedServicesProto value) {
        if (value != null) {
            this.notificationAssistants_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotificationAssistants(ManagedServicesProto.Builder builderForValue) {
        this.notificationAssistants_ = (ManagedServicesProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNotificationAssistants(ManagedServicesProto value) {
        ManagedServicesProto managedServicesProto = this.notificationAssistants_;
        if (managedServicesProto == null || managedServicesProto == ManagedServicesProto.getDefaultInstance()) {
            this.notificationAssistants_ = value;
        } else {
            this.notificationAssistants_ = (ManagedServicesProto) ((ManagedServicesProto.Builder) ManagedServicesProto.newBuilder(this.notificationAssistants_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotificationAssistants() {
        this.notificationAssistants_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public boolean hasConditionProviders() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public ManagedServicesProto getConditionProviders() {
        ManagedServicesProto managedServicesProto = this.conditionProviders_;
        return managedServicesProto == null ? ManagedServicesProto.getDefaultInstance() : managedServicesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConditionProviders(ManagedServicesProto value) {
        if (value != null) {
            this.conditionProviders_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConditionProviders(ManagedServicesProto.Builder builderForValue) {
        this.conditionProviders_ = (ManagedServicesProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeConditionProviders(ManagedServicesProto value) {
        ManagedServicesProto managedServicesProto = this.conditionProviders_;
        if (managedServicesProto == null || managedServicesProto == ManagedServicesProto.getDefaultInstance()) {
            this.conditionProviders_ = value;
        } else {
            this.conditionProviders_ = (ManagedServicesProto) ((ManagedServicesProto.Builder) ManagedServicesProto.newBuilder(this.conditionProviders_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConditionProviders() {
        this.conditionProviders_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public boolean hasRankingConfig() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
    public RankingHelperProto getRankingConfig() {
        RankingHelperProto rankingHelperProto = this.rankingConfig_;
        return rankingHelperProto == null ? RankingHelperProto.getDefaultInstance() : rankingHelperProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRankingConfig(RankingHelperProto value) {
        if (value != null) {
            this.rankingConfig_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRankingConfig(RankingHelperProto.Builder builderForValue) {
        this.rankingConfig_ = (RankingHelperProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRankingConfig(RankingHelperProto value) {
        RankingHelperProto rankingHelperProto = this.rankingConfig_;
        if (rankingHelperProto == null || rankingHelperProto == RankingHelperProto.getDefaultInstance()) {
            this.rankingConfig_ = value;
        } else {
            this.rankingConfig_ = (RankingHelperProto) ((RankingHelperProto.Builder) RankingHelperProto.newBuilder(this.rankingConfig_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRankingConfig() {
        this.rankingConfig_ = null;
        this.bitField0_ &= -33;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.records_.size(); i++) {
            output.writeMessage(1, this.records_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(2, getZen());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(3, getNotificationListeners());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeInt32(4, this.listenerHints_);
        }
        for (int i2 = 0; i2 < this.listenersDisablingEffects_.size(); i2++) {
            output.writeMessage(5, this.listenersDisablingEffects_.get(i2));
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(6, getNotificationAssistants());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(7, getConditionProviders());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(8, getRankingConfig());
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
        for (int i = 0; i < this.records_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.records_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(2, getZen());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(3, getNotificationListeners());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeInt32Size(4, this.listenerHints_);
        }
        for (int i2 = 0; i2 < this.listenersDisablingEffects_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(5, this.listenersDisablingEffects_.get(i2));
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(6, getNotificationAssistants());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(7, getConditionProviders());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(8, getRankingConfig());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static NotificationServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (NotificationServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (NotificationServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static NotificationServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (NotificationServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static NotificationServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (NotificationServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (NotificationServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static NotificationServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (NotificationServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static NotificationServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (NotificationServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(NotificationServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NotificationServiceDumpProto, Builder> implements NotificationServiceDumpProtoOrBuilder {
        private Builder() {
            super(NotificationServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public List<NotificationRecordProto> getRecordsList() {
            return Collections.unmodifiableList(((NotificationServiceDumpProto) this.instance).getRecordsList());
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public int getRecordsCount() {
            return ((NotificationServiceDumpProto) this.instance).getRecordsCount();
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public NotificationRecordProto getRecords(int index) {
            return ((NotificationServiceDumpProto) this.instance).getRecords(index);
        }

        public Builder setRecords(int index, NotificationRecordProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setRecords((NotificationServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setRecords(int index, NotificationRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setRecords((NotificationServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRecords(NotificationRecordProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addRecords((NotificationServiceDumpProto) value);
            return this;
        }

        public Builder addRecords(int index, NotificationRecordProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addRecords((NotificationServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addRecords(NotificationRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addRecords((NotificationServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addRecords(int index, NotificationRecordProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addRecords((NotificationServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRecords(Iterable<? extends NotificationRecordProto> values) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addAllRecords(values);
            return this;
        }

        public Builder clearRecords() {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).clearRecords();
            return this;
        }

        public Builder removeRecords(int index) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).removeRecords(index);
            return this;
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public boolean hasZen() {
            return ((NotificationServiceDumpProto) this.instance).hasZen();
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public ZenModeProto getZen() {
            return ((NotificationServiceDumpProto) this.instance).getZen();
        }

        public Builder setZen(ZenModeProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setZen((NotificationServiceDumpProto) value);
            return this;
        }

        public Builder setZen(ZenModeProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setZen((NotificationServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeZen(ZenModeProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).mergeZen(value);
            return this;
        }

        public Builder clearZen() {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).clearZen();
            return this;
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public boolean hasNotificationListeners() {
            return ((NotificationServiceDumpProto) this.instance).hasNotificationListeners();
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public ManagedServicesProto getNotificationListeners() {
            return ((NotificationServiceDumpProto) this.instance).getNotificationListeners();
        }

        public Builder setNotificationListeners(ManagedServicesProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setNotificationListeners((NotificationServiceDumpProto) value);
            return this;
        }

        public Builder setNotificationListeners(ManagedServicesProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setNotificationListeners((NotificationServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeNotificationListeners(ManagedServicesProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).mergeNotificationListeners(value);
            return this;
        }

        public Builder clearNotificationListeners() {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).clearNotificationListeners();
            return this;
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public boolean hasListenerHints() {
            return ((NotificationServiceDumpProto) this.instance).hasListenerHints();
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public int getListenerHints() {
            return ((NotificationServiceDumpProto) this.instance).getListenerHints();
        }

        public Builder setListenerHints(int value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setListenerHints(value);
            return this;
        }

        public Builder clearListenerHints() {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).clearListenerHints();
            return this;
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public List<ListenersDisablingEffectsProto> getListenersDisablingEffectsList() {
            return Collections.unmodifiableList(((NotificationServiceDumpProto) this.instance).getListenersDisablingEffectsList());
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public int getListenersDisablingEffectsCount() {
            return ((NotificationServiceDumpProto) this.instance).getListenersDisablingEffectsCount();
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public ListenersDisablingEffectsProto getListenersDisablingEffects(int index) {
            return ((NotificationServiceDumpProto) this.instance).getListenersDisablingEffects(index);
        }

        public Builder setListenersDisablingEffects(int index, ListenersDisablingEffectsProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setListenersDisablingEffects((NotificationServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setListenersDisablingEffects(int index, ListenersDisablingEffectsProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setListenersDisablingEffects((NotificationServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addListenersDisablingEffects(ListenersDisablingEffectsProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addListenersDisablingEffects((NotificationServiceDumpProto) value);
            return this;
        }

        public Builder addListenersDisablingEffects(int index, ListenersDisablingEffectsProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addListenersDisablingEffects((NotificationServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addListenersDisablingEffects(ListenersDisablingEffectsProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addListenersDisablingEffects((NotificationServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addListenersDisablingEffects(int index, ListenersDisablingEffectsProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addListenersDisablingEffects((NotificationServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllListenersDisablingEffects(Iterable<? extends ListenersDisablingEffectsProto> values) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).addAllListenersDisablingEffects(values);
            return this;
        }

        public Builder clearListenersDisablingEffects() {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).clearListenersDisablingEffects();
            return this;
        }

        public Builder removeListenersDisablingEffects(int index) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).removeListenersDisablingEffects(index);
            return this;
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public boolean hasNotificationAssistants() {
            return ((NotificationServiceDumpProto) this.instance).hasNotificationAssistants();
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public ManagedServicesProto getNotificationAssistants() {
            return ((NotificationServiceDumpProto) this.instance).getNotificationAssistants();
        }

        public Builder setNotificationAssistants(ManagedServicesProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setNotificationAssistants((NotificationServiceDumpProto) value);
            return this;
        }

        public Builder setNotificationAssistants(ManagedServicesProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setNotificationAssistants((NotificationServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeNotificationAssistants(ManagedServicesProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).mergeNotificationAssistants(value);
            return this;
        }

        public Builder clearNotificationAssistants() {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).clearNotificationAssistants();
            return this;
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public boolean hasConditionProviders() {
            return ((NotificationServiceDumpProto) this.instance).hasConditionProviders();
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public ManagedServicesProto getConditionProviders() {
            return ((NotificationServiceDumpProto) this.instance).getConditionProviders();
        }

        public Builder setConditionProviders(ManagedServicesProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setConditionProviders((NotificationServiceDumpProto) value);
            return this;
        }

        public Builder setConditionProviders(ManagedServicesProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setConditionProviders((NotificationServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeConditionProviders(ManagedServicesProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).mergeConditionProviders(value);
            return this;
        }

        public Builder clearConditionProviders() {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).clearConditionProviders();
            return this;
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public boolean hasRankingConfig() {
            return ((NotificationServiceDumpProto) this.instance).hasRankingConfig();
        }

        @Override // android.service.notification.NotificationServiceDumpProtoOrBuilder
        public RankingHelperProto getRankingConfig() {
            return ((NotificationServiceDumpProto) this.instance).getRankingConfig();
        }

        public Builder setRankingConfig(RankingHelperProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setRankingConfig((NotificationServiceDumpProto) value);
            return this;
        }

        public Builder setRankingConfig(RankingHelperProto.Builder builderForValue) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).setRankingConfig((NotificationServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeRankingConfig(RankingHelperProto value) {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).mergeRankingConfig(value);
            return this;
        }

        public Builder clearRankingConfig() {
            copyOnWrite();
            ((NotificationServiceDumpProto) this.instance).clearRankingConfig();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new NotificationServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.records_.makeImmutable();
                this.listenersDisablingEffects_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                NotificationServiceDumpProto other = (NotificationServiceDumpProto) arg1;
                this.records_ = visitor.visitList(this.records_, other.records_);
                this.zen_ = (ZenModeProto) visitor.visitMessage(this.zen_, other.zen_);
                this.notificationListeners_ = (ManagedServicesProto) visitor.visitMessage(this.notificationListeners_, other.notificationListeners_);
                this.listenerHints_ = visitor.visitInt(hasListenerHints(), this.listenerHints_, other.hasListenerHints(), other.listenerHints_);
                this.listenersDisablingEffects_ = visitor.visitList(this.listenersDisablingEffects_, other.listenersDisablingEffects_);
                this.notificationAssistants_ = (ManagedServicesProto) visitor.visitMessage(this.notificationAssistants_, other.notificationAssistants_);
                this.conditionProviders_ = (ManagedServicesProto) visitor.visitMessage(this.conditionProviders_, other.conditionProviders_);
                this.rankingConfig_ = (RankingHelperProto) visitor.visitMessage(this.rankingConfig_, other.rankingConfig_);
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
                            if (!this.records_.isModifiable()) {
                                this.records_ = GeneratedMessageLite.mutableCopy(this.records_);
                            }
                            this.records_.add((NotificationRecordProto) input.readMessage(NotificationRecordProto.parser(), extensionRegistry));
                        } else if (tag == 18) {
                            ZenModeProto.Builder subBuilder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                subBuilder = (ZenModeProto.Builder) this.zen_.toBuilder();
                            }
                            this.zen_ = (ZenModeProto) input.readMessage(ZenModeProto.parser(), extensionRegistry);
                            if (subBuilder != null) {
                                subBuilder.mergeFrom((GeneratedMessageLite) this.zen_);
                                this.zen_ = (ZenModeProto) subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 26) {
                            ManagedServicesProto.Builder subBuilder2 = null;
                            if ((this.bitField0_ & 2) == 2) {
                                subBuilder2 = (ManagedServicesProto.Builder) this.notificationListeners_.toBuilder();
                            }
                            this.notificationListeners_ = (ManagedServicesProto) input.readMessage(ManagedServicesProto.parser(), extensionRegistry);
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom((GeneratedMessageLite) this.notificationListeners_);
                                this.notificationListeners_ = (ManagedServicesProto) subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 32) {
                            this.bitField0_ |= 4;
                            this.listenerHints_ = input.readInt32();
                        } else if (tag == 42) {
                            if (!this.listenersDisablingEffects_.isModifiable()) {
                                this.listenersDisablingEffects_ = GeneratedMessageLite.mutableCopy(this.listenersDisablingEffects_);
                            }
                            this.listenersDisablingEffects_.add((ListenersDisablingEffectsProto) input.readMessage(ListenersDisablingEffectsProto.parser(), extensionRegistry));
                        } else if (tag == 50) {
                            ManagedServicesProto.Builder subBuilder3 = null;
                            if ((this.bitField0_ & 8) == 8) {
                                subBuilder3 = (ManagedServicesProto.Builder) this.notificationAssistants_.toBuilder();
                            }
                            this.notificationAssistants_ = (ManagedServicesProto) input.readMessage(ManagedServicesProto.parser(), extensionRegistry);
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom((GeneratedMessageLite) this.notificationAssistants_);
                                this.notificationAssistants_ = (ManagedServicesProto) subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        } else if (tag == 58) {
                            ManagedServicesProto.Builder subBuilder4 = null;
                            if ((this.bitField0_ & 16) == 16) {
                                subBuilder4 = (ManagedServicesProto.Builder) this.conditionProviders_.toBuilder();
                            }
                            this.conditionProviders_ = (ManagedServicesProto) input.readMessage(ManagedServicesProto.parser(), extensionRegistry);
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom((GeneratedMessageLite) this.conditionProviders_);
                                this.conditionProviders_ = (ManagedServicesProto) subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 16;
                        } else if (tag == 66) {
                            RankingHelperProto.Builder subBuilder5 = null;
                            if ((this.bitField0_ & 32) == 32) {
                                subBuilder5 = (RankingHelperProto.Builder) this.rankingConfig_.toBuilder();
                            }
                            this.rankingConfig_ = (RankingHelperProto) input.readMessage(RankingHelperProto.parser(), extensionRegistry);
                            if (subBuilder5 != null) {
                                subBuilder5.mergeFrom((GeneratedMessageLite) this.rankingConfig_);
                                this.rankingConfig_ = (RankingHelperProto) subBuilder5.buildPartial();
                            }
                            this.bitField0_ = 32 | this.bitField0_;
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
                    synchronized (NotificationServiceDumpProto.class) {
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

    public static NotificationServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NotificationServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
