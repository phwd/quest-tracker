package android.service.notification;

import android.app.NotificationChannelGroupProto;
import android.app.NotificationChannelGroupProtoOrBuilder;
import android.app.NotificationChannelProto;
import android.app.NotificationChannelProtoOrBuilder;
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

public final class RankingHelperProto extends GeneratedMessageLite<RankingHelperProto, Builder> implements RankingHelperProtoOrBuilder {
    private static final RankingHelperProto DEFAULT_INSTANCE = new RankingHelperProto();
    public static final int NOTIFICATION_SIGNAL_EXTRACTORS_FIELD_NUMBER = 1;
    private static volatile Parser<RankingHelperProto> PARSER = null;
    public static final int RECORDS_FIELD_NUMBER = 2;
    public static final int RECORDS_RESTORED_WITHOUT_UID_FIELD_NUMBER = 3;
    private Internal.ProtobufList<String> notificationSignalExtractors_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<RecordProto> recordsRestoredWithoutUid_ = emptyProtobufList();
    private Internal.ProtobufList<RecordProto> records_ = emptyProtobufList();

    public interface RecordProtoOrBuilder extends MessageLiteOrBuilder {
        NotificationChannelGroupProto getChannelGroups(int i);

        int getChannelGroupsCount();

        List<NotificationChannelGroupProto> getChannelGroupsList();

        NotificationChannelProto getChannels(int i);

        int getChannelsCount();

        List<NotificationChannelProto> getChannelsList();

        int getImportance();

        String getPackage();

        ByteString getPackageBytes();

        int getPriority();

        boolean getShowBadge();

        int getUid();

        int getVisibility();

        boolean hasImportance();

        boolean hasPackage();

        boolean hasPriority();

        boolean hasShowBadge();

        boolean hasUid();

        boolean hasVisibility();
    }

    private RankingHelperProto() {
    }

    public static final class RecordProto extends GeneratedMessageLite<RecordProto, Builder> implements RecordProtoOrBuilder {
        public static final int CHANNELS_FIELD_NUMBER = 7;
        public static final int CHANNEL_GROUPS_FIELD_NUMBER = 8;
        private static final RecordProto DEFAULT_INSTANCE = new RecordProto();
        public static final int IMPORTANCE_FIELD_NUMBER = 3;
        public static final int PACKAGE_FIELD_NUMBER = 1;
        private static volatile Parser<RecordProto> PARSER = null;
        public static final int PRIORITY_FIELD_NUMBER = 4;
        public static final int SHOW_BADGE_FIELD_NUMBER = 6;
        public static final int UID_FIELD_NUMBER = 2;
        public static final int VISIBILITY_FIELD_NUMBER = 5;
        private int bitField0_;
        private Internal.ProtobufList<NotificationChannelGroupProto> channelGroups_ = emptyProtobufList();
        private Internal.ProtobufList<NotificationChannelProto> channels_ = emptyProtobufList();
        private int importance_ = 0;
        private String package_ = "";
        private int priority_ = 0;
        private boolean showBadge_ = false;
        private int uid_ = 0;
        private int visibility_ = 0;

        private RecordProto() {
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public boolean hasPackage() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public String getPackage() {
            return this.package_;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public ByteString getPackageBytes() {
            return ByteString.copyFromUtf8(this.package_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackage(String value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.package_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPackage() {
            this.bitField0_ &= -2;
            this.package_ = getDefaultInstance().getPackage();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPackageBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 1;
                this.package_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 2;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -3;
            this.uid_ = 0;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public boolean hasImportance() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public int getImportance() {
            return this.importance_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setImportance(int value) {
            this.bitField0_ |= 4;
            this.importance_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearImportance() {
            this.bitField0_ &= -5;
            this.importance_ = 0;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public boolean hasPriority() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public int getPriority() {
            return this.priority_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPriority(int value) {
            this.bitField0_ |= 8;
            this.priority_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPriority() {
            this.bitField0_ &= -9;
            this.priority_ = 0;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public boolean hasVisibility() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public int getVisibility() {
            return this.visibility_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVisibility(int value) {
            this.bitField0_ |= 16;
            this.visibility_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVisibility() {
            this.bitField0_ &= -17;
            this.visibility_ = 0;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public boolean hasShowBadge() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public boolean getShowBadge() {
            return this.showBadge_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShowBadge(boolean value) {
            this.bitField0_ |= 32;
            this.showBadge_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearShowBadge() {
            this.bitField0_ &= -33;
            this.showBadge_ = false;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public List<NotificationChannelProto> getChannelsList() {
            return this.channels_;
        }

        public List<? extends NotificationChannelProtoOrBuilder> getChannelsOrBuilderList() {
            return this.channels_;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public int getChannelsCount() {
            return this.channels_.size();
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public NotificationChannelProto getChannels(int index) {
            return this.channels_.get(index);
        }

        public NotificationChannelProtoOrBuilder getChannelsOrBuilder(int index) {
            return this.channels_.get(index);
        }

        private void ensureChannelsIsMutable() {
            if (!this.channels_.isModifiable()) {
                this.channels_ = GeneratedMessageLite.mutableCopy(this.channels_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChannels(int index, NotificationChannelProto value) {
            if (value != null) {
                ensureChannelsIsMutable();
                this.channels_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChannels(int index, NotificationChannelProto.Builder builderForValue) {
            ensureChannelsIsMutable();
            this.channels_.set(index, (NotificationChannelProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChannels(NotificationChannelProto value) {
            if (value != null) {
                ensureChannelsIsMutable();
                this.channels_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChannels(int index, NotificationChannelProto value) {
            if (value != null) {
                ensureChannelsIsMutable();
                this.channels_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChannels(NotificationChannelProto.Builder builderForValue) {
            ensureChannelsIsMutable();
            this.channels_.add((NotificationChannelProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChannels(int index, NotificationChannelProto.Builder builderForValue) {
            ensureChannelsIsMutable();
            this.channels_.add(index, (NotificationChannelProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllChannels(Iterable<? extends NotificationChannelProto> values) {
            ensureChannelsIsMutable();
            AbstractMessageLite.addAll(values, this.channels_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChannels() {
            this.channels_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeChannels(int index) {
            ensureChannelsIsMutable();
            this.channels_.remove(index);
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public List<NotificationChannelGroupProto> getChannelGroupsList() {
            return this.channelGroups_;
        }

        public List<? extends NotificationChannelGroupProtoOrBuilder> getChannelGroupsOrBuilderList() {
            return this.channelGroups_;
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public int getChannelGroupsCount() {
            return this.channelGroups_.size();
        }

        @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
        public NotificationChannelGroupProto getChannelGroups(int index) {
            return this.channelGroups_.get(index);
        }

        public NotificationChannelGroupProtoOrBuilder getChannelGroupsOrBuilder(int index) {
            return this.channelGroups_.get(index);
        }

        private void ensureChannelGroupsIsMutable() {
            if (!this.channelGroups_.isModifiable()) {
                this.channelGroups_ = GeneratedMessageLite.mutableCopy(this.channelGroups_);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChannelGroups(int index, NotificationChannelGroupProto value) {
            if (value != null) {
                ensureChannelGroupsIsMutable();
                this.channelGroups_.set(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setChannelGroups(int index, NotificationChannelGroupProto.Builder builderForValue) {
            ensureChannelGroupsIsMutable();
            this.channelGroups_.set(index, (NotificationChannelGroupProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChannelGroups(NotificationChannelGroupProto value) {
            if (value != null) {
                ensureChannelGroupsIsMutable();
                this.channelGroups_.add(value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChannelGroups(int index, NotificationChannelGroupProto value) {
            if (value != null) {
                ensureChannelGroupsIsMutable();
                this.channelGroups_.add(index, value);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChannelGroups(NotificationChannelGroupProto.Builder builderForValue) {
            ensureChannelGroupsIsMutable();
            this.channelGroups_.add((NotificationChannelGroupProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addChannelGroups(int index, NotificationChannelGroupProto.Builder builderForValue) {
            ensureChannelGroupsIsMutable();
            this.channelGroups_.add(index, (NotificationChannelGroupProto) builderForValue.build());
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAllChannelGroups(Iterable<? extends NotificationChannelGroupProto> values) {
            ensureChannelGroupsIsMutable();
            AbstractMessageLite.addAll(values, this.channelGroups_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearChannelGroups() {
            this.channelGroups_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void removeChannelGroups(int index) {
            ensureChannelGroupsIsMutable();
            this.channelGroups_.remove(index);
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeString(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.uid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeSInt32(3, this.importance_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.priority_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeSInt32(5, this.visibility_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBool(6, this.showBadge_);
            }
            for (int i = 0; i < this.channels_.size(); i++) {
                output.writeMessage(7, this.channels_.get(i));
            }
            for (int i2 = 0; i2 < this.channelGroups_.size(); i2++) {
                output.writeMessage(8, this.channelGroups_.get(i2));
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
                size2 = 0 + CodedOutputStream.computeStringSize(1, getPackage());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.uid_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeSInt32Size(3, this.importance_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.priority_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeSInt32Size(5, this.visibility_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBoolSize(6, this.showBadge_);
            }
            for (int i = 0; i < this.channels_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(7, this.channels_.get(i));
            }
            for (int i2 = 0; i2 < this.channelGroups_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(8, this.channelGroups_.get(i2));
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static RecordProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (RecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RecordProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RecordProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (RecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RecordProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RecordProto parseFrom(InputStream input) throws IOException {
            return (RecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RecordProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RecordProto parseDelimitedFrom(InputStream input) throws IOException {
            return (RecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static RecordProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RecordProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RecordProto parseFrom(CodedInputStream input) throws IOException {
            return (RecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RecordProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RecordProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RecordProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RecordProto, Builder> implements RecordProtoOrBuilder {
            private Builder() {
                super(RecordProto.DEFAULT_INSTANCE);
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public boolean hasPackage() {
                return ((RecordProto) this.instance).hasPackage();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public String getPackage() {
                return ((RecordProto) this.instance).getPackage();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public ByteString getPackageBytes() {
                return ((RecordProto) this.instance).getPackageBytes();
            }

            public Builder setPackage(String value) {
                copyOnWrite();
                ((RecordProto) this.instance).setPackage(value);
                return this;
            }

            public Builder clearPackage() {
                copyOnWrite();
                ((RecordProto) this.instance).clearPackage();
                return this;
            }

            public Builder setPackageBytes(ByteString value) {
                copyOnWrite();
                ((RecordProto) this.instance).setPackageBytes(value);
                return this;
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public boolean hasUid() {
                return ((RecordProto) this.instance).hasUid();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public int getUid() {
                return ((RecordProto) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((RecordProto) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((RecordProto) this.instance).clearUid();
                return this;
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public boolean hasImportance() {
                return ((RecordProto) this.instance).hasImportance();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public int getImportance() {
                return ((RecordProto) this.instance).getImportance();
            }

            public Builder setImportance(int value) {
                copyOnWrite();
                ((RecordProto) this.instance).setImportance(value);
                return this;
            }

            public Builder clearImportance() {
                copyOnWrite();
                ((RecordProto) this.instance).clearImportance();
                return this;
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public boolean hasPriority() {
                return ((RecordProto) this.instance).hasPriority();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public int getPriority() {
                return ((RecordProto) this.instance).getPriority();
            }

            public Builder setPriority(int value) {
                copyOnWrite();
                ((RecordProto) this.instance).setPriority(value);
                return this;
            }

            public Builder clearPriority() {
                copyOnWrite();
                ((RecordProto) this.instance).clearPriority();
                return this;
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public boolean hasVisibility() {
                return ((RecordProto) this.instance).hasVisibility();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public int getVisibility() {
                return ((RecordProto) this.instance).getVisibility();
            }

            public Builder setVisibility(int value) {
                copyOnWrite();
                ((RecordProto) this.instance).setVisibility(value);
                return this;
            }

            public Builder clearVisibility() {
                copyOnWrite();
                ((RecordProto) this.instance).clearVisibility();
                return this;
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public boolean hasShowBadge() {
                return ((RecordProto) this.instance).hasShowBadge();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public boolean getShowBadge() {
                return ((RecordProto) this.instance).getShowBadge();
            }

            public Builder setShowBadge(boolean value) {
                copyOnWrite();
                ((RecordProto) this.instance).setShowBadge(value);
                return this;
            }

            public Builder clearShowBadge() {
                copyOnWrite();
                ((RecordProto) this.instance).clearShowBadge();
                return this;
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public List<NotificationChannelProto> getChannelsList() {
                return Collections.unmodifiableList(((RecordProto) this.instance).getChannelsList());
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public int getChannelsCount() {
                return ((RecordProto) this.instance).getChannelsCount();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public NotificationChannelProto getChannels(int index) {
                return ((RecordProto) this.instance).getChannels(index);
            }

            public Builder setChannels(int index, NotificationChannelProto value) {
                copyOnWrite();
                ((RecordProto) this.instance).setChannels((RecordProto) index, (int) value);
                return this;
            }

            public Builder setChannels(int index, NotificationChannelProto.Builder builderForValue) {
                copyOnWrite();
                ((RecordProto) this.instance).setChannels((RecordProto) index, (int) builderForValue);
                return this;
            }

            public Builder addChannels(NotificationChannelProto value) {
                copyOnWrite();
                ((RecordProto) this.instance).addChannels((RecordProto) value);
                return this;
            }

            public Builder addChannels(int index, NotificationChannelProto value) {
                copyOnWrite();
                ((RecordProto) this.instance).addChannels((RecordProto) index, (int) value);
                return this;
            }

            public Builder addChannels(NotificationChannelProto.Builder builderForValue) {
                copyOnWrite();
                ((RecordProto) this.instance).addChannels((RecordProto) builderForValue);
                return this;
            }

            public Builder addChannels(int index, NotificationChannelProto.Builder builderForValue) {
                copyOnWrite();
                ((RecordProto) this.instance).addChannels((RecordProto) index, (int) builderForValue);
                return this;
            }

            public Builder addAllChannels(Iterable<? extends NotificationChannelProto> values) {
                copyOnWrite();
                ((RecordProto) this.instance).addAllChannels(values);
                return this;
            }

            public Builder clearChannels() {
                copyOnWrite();
                ((RecordProto) this.instance).clearChannels();
                return this;
            }

            public Builder removeChannels(int index) {
                copyOnWrite();
                ((RecordProto) this.instance).removeChannels(index);
                return this;
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public List<NotificationChannelGroupProto> getChannelGroupsList() {
                return Collections.unmodifiableList(((RecordProto) this.instance).getChannelGroupsList());
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public int getChannelGroupsCount() {
                return ((RecordProto) this.instance).getChannelGroupsCount();
            }

            @Override // android.service.notification.RankingHelperProto.RecordProtoOrBuilder
            public NotificationChannelGroupProto getChannelGroups(int index) {
                return ((RecordProto) this.instance).getChannelGroups(index);
            }

            public Builder setChannelGroups(int index, NotificationChannelGroupProto value) {
                copyOnWrite();
                ((RecordProto) this.instance).setChannelGroups((RecordProto) index, (int) value);
                return this;
            }

            public Builder setChannelGroups(int index, NotificationChannelGroupProto.Builder builderForValue) {
                copyOnWrite();
                ((RecordProto) this.instance).setChannelGroups((RecordProto) index, (int) builderForValue);
                return this;
            }

            public Builder addChannelGroups(NotificationChannelGroupProto value) {
                copyOnWrite();
                ((RecordProto) this.instance).addChannelGroups((RecordProto) value);
                return this;
            }

            public Builder addChannelGroups(int index, NotificationChannelGroupProto value) {
                copyOnWrite();
                ((RecordProto) this.instance).addChannelGroups((RecordProto) index, (int) value);
                return this;
            }

            public Builder addChannelGroups(NotificationChannelGroupProto.Builder builderForValue) {
                copyOnWrite();
                ((RecordProto) this.instance).addChannelGroups((RecordProto) builderForValue);
                return this;
            }

            public Builder addChannelGroups(int index, NotificationChannelGroupProto.Builder builderForValue) {
                copyOnWrite();
                ((RecordProto) this.instance).addChannelGroups((RecordProto) index, (int) builderForValue);
                return this;
            }

            public Builder addAllChannelGroups(Iterable<? extends NotificationChannelGroupProto> values) {
                copyOnWrite();
                ((RecordProto) this.instance).addAllChannelGroups(values);
                return this;
            }

            public Builder clearChannelGroups() {
                copyOnWrite();
                ((RecordProto) this.instance).clearChannelGroups();
                return this;
            }

            public Builder removeChannelGroups(int index) {
                copyOnWrite();
                ((RecordProto) this.instance).removeChannelGroups(index);
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new RecordProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.channels_.makeImmutable();
                    this.channelGroups_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    RecordProto other = (RecordProto) arg1;
                    this.package_ = visitor.visitString(hasPackage(), this.package_, other.hasPackage(), other.package_);
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.importance_ = visitor.visitInt(hasImportance(), this.importance_, other.hasImportance(), other.importance_);
                    this.priority_ = visitor.visitInt(hasPriority(), this.priority_, other.hasPriority(), other.priority_);
                    this.visibility_ = visitor.visitInt(hasVisibility(), this.visibility_, other.hasVisibility(), other.visibility_);
                    this.showBadge_ = visitor.visitBoolean(hasShowBadge(), this.showBadge_, other.hasShowBadge(), other.showBadge_);
                    this.channels_ = visitor.visitList(this.channels_, other.channels_);
                    this.channelGroups_ = visitor.visitList(this.channelGroups_, other.channelGroups_);
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
                                this.package_ = s;
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.uid_ = input.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.importance_ = input.readSInt32();
                            } else if (tag == 32) {
                                this.bitField0_ |= 8;
                                this.priority_ = input.readInt32();
                            } else if (tag == 40) {
                                this.bitField0_ = 16 | this.bitField0_;
                                this.visibility_ = input.readSInt32();
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.showBadge_ = input.readBool();
                            } else if (tag == 58) {
                                if (!this.channels_.isModifiable()) {
                                    this.channels_ = GeneratedMessageLite.mutableCopy(this.channels_);
                                }
                                this.channels_.add((NotificationChannelProto) input.readMessage(NotificationChannelProto.parser(), extensionRegistry));
                            } else if (tag == 66) {
                                if (!this.channelGroups_.isModifiable()) {
                                    this.channelGroups_ = GeneratedMessageLite.mutableCopy(this.channelGroups_);
                                }
                                this.channelGroups_.add((NotificationChannelGroupProto) input.readMessage(NotificationChannelGroupProto.parser(), extensionRegistry));
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
                        synchronized (RecordProto.class) {
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

        public static RecordProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RecordProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public List<String> getNotificationSignalExtractorsList() {
        return this.notificationSignalExtractors_;
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public int getNotificationSignalExtractorsCount() {
        return this.notificationSignalExtractors_.size();
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public String getNotificationSignalExtractors(int index) {
        return this.notificationSignalExtractors_.get(index);
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public ByteString getNotificationSignalExtractorsBytes(int index) {
        return ByteString.copyFromUtf8(this.notificationSignalExtractors_.get(index));
    }

    private void ensureNotificationSignalExtractorsIsMutable() {
        if (!this.notificationSignalExtractors_.isModifiable()) {
            this.notificationSignalExtractors_ = GeneratedMessageLite.mutableCopy(this.notificationSignalExtractors_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotificationSignalExtractors(int index, String value) {
        if (value != null) {
            ensureNotificationSignalExtractorsIsMutable();
            this.notificationSignalExtractors_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNotificationSignalExtractors(String value) {
        if (value != null) {
            ensureNotificationSignalExtractorsIsMutable();
            this.notificationSignalExtractors_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllNotificationSignalExtractors(Iterable<String> values) {
        ensureNotificationSignalExtractorsIsMutable();
        AbstractMessageLite.addAll(values, this.notificationSignalExtractors_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotificationSignalExtractors() {
        this.notificationSignalExtractors_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNotificationSignalExtractorsBytes(ByteString value) {
        if (value != null) {
            ensureNotificationSignalExtractorsIsMutable();
            this.notificationSignalExtractors_.add(value.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public List<RecordProto> getRecordsList() {
        return this.records_;
    }

    public List<? extends RecordProtoOrBuilder> getRecordsOrBuilderList() {
        return this.records_;
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public int getRecordsCount() {
        return this.records_.size();
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public RecordProto getRecords(int index) {
        return this.records_.get(index);
    }

    public RecordProtoOrBuilder getRecordsOrBuilder(int index) {
        return this.records_.get(index);
    }

    private void ensureRecordsIsMutable() {
        if (!this.records_.isModifiable()) {
            this.records_ = GeneratedMessageLite.mutableCopy(this.records_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecords(int index, RecordProto value) {
        if (value != null) {
            ensureRecordsIsMutable();
            this.records_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecords(int index, RecordProto.Builder builderForValue) {
        ensureRecordsIsMutable();
        this.records_.set(index, (RecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecords(RecordProto value) {
        if (value != null) {
            ensureRecordsIsMutable();
            this.records_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecords(int index, RecordProto value) {
        if (value != null) {
            ensureRecordsIsMutable();
            this.records_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecords(RecordProto.Builder builderForValue) {
        ensureRecordsIsMutable();
        this.records_.add((RecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecords(int index, RecordProto.Builder builderForValue) {
        ensureRecordsIsMutable();
        this.records_.add(index, (RecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRecords(Iterable<? extends RecordProto> values) {
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

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public List<RecordProto> getRecordsRestoredWithoutUidList() {
        return this.recordsRestoredWithoutUid_;
    }

    public List<? extends RecordProtoOrBuilder> getRecordsRestoredWithoutUidOrBuilderList() {
        return this.recordsRestoredWithoutUid_;
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public int getRecordsRestoredWithoutUidCount() {
        return this.recordsRestoredWithoutUid_.size();
    }

    @Override // android.service.notification.RankingHelperProtoOrBuilder
    public RecordProto getRecordsRestoredWithoutUid(int index) {
        return this.recordsRestoredWithoutUid_.get(index);
    }

    public RecordProtoOrBuilder getRecordsRestoredWithoutUidOrBuilder(int index) {
        return this.recordsRestoredWithoutUid_.get(index);
    }

    private void ensureRecordsRestoredWithoutUidIsMutable() {
        if (!this.recordsRestoredWithoutUid_.isModifiable()) {
            this.recordsRestoredWithoutUid_ = GeneratedMessageLite.mutableCopy(this.recordsRestoredWithoutUid_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecordsRestoredWithoutUid(int index, RecordProto value) {
        if (value != null) {
            ensureRecordsRestoredWithoutUidIsMutable();
            this.recordsRestoredWithoutUid_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRecordsRestoredWithoutUid(int index, RecordProto.Builder builderForValue) {
        ensureRecordsRestoredWithoutUidIsMutable();
        this.recordsRestoredWithoutUid_.set(index, (RecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecordsRestoredWithoutUid(RecordProto value) {
        if (value != null) {
            ensureRecordsRestoredWithoutUidIsMutable();
            this.recordsRestoredWithoutUid_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecordsRestoredWithoutUid(int index, RecordProto value) {
        if (value != null) {
            ensureRecordsRestoredWithoutUidIsMutable();
            this.recordsRestoredWithoutUid_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecordsRestoredWithoutUid(RecordProto.Builder builderForValue) {
        ensureRecordsRestoredWithoutUidIsMutable();
        this.recordsRestoredWithoutUid_.add((RecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addRecordsRestoredWithoutUid(int index, RecordProto.Builder builderForValue) {
        ensureRecordsRestoredWithoutUidIsMutable();
        this.recordsRestoredWithoutUid_.add(index, (RecordProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllRecordsRestoredWithoutUid(Iterable<? extends RecordProto> values) {
        ensureRecordsRestoredWithoutUidIsMutable();
        AbstractMessageLite.addAll(values, this.recordsRestoredWithoutUid_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRecordsRestoredWithoutUid() {
        this.recordsRestoredWithoutUid_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeRecordsRestoredWithoutUid(int index) {
        ensureRecordsRestoredWithoutUidIsMutable();
        this.recordsRestoredWithoutUid_.remove(index);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.notificationSignalExtractors_.size(); i++) {
            output.writeString(1, this.notificationSignalExtractors_.get(i));
        }
        for (int i2 = 0; i2 < this.records_.size(); i2++) {
            output.writeMessage(2, this.records_.get(i2));
        }
        for (int i3 = 0; i3 < this.recordsRestoredWithoutUid_.size(); i3++) {
            output.writeMessage(3, this.recordsRestoredWithoutUid_.get(i3));
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
        for (int i = 0; i < this.notificationSignalExtractors_.size(); i++) {
            dataSize += CodedOutputStream.computeStringSizeNoTag(this.notificationSignalExtractors_.get(i));
        }
        int size2 = 0 + dataSize + (getNotificationSignalExtractorsList().size() * 1);
        for (int i2 = 0; i2 < this.records_.size(); i2++) {
            size2 += CodedOutputStream.computeMessageSize(2, this.records_.get(i2));
        }
        for (int i3 = 0; i3 < this.recordsRestoredWithoutUid_.size(); i3++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.recordsRestoredWithoutUid_.get(i3));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static RankingHelperProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RankingHelperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RankingHelperProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RankingHelperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RankingHelperProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RankingHelperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RankingHelperProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RankingHelperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RankingHelperProto parseFrom(InputStream input) throws IOException {
        return (RankingHelperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RankingHelperProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RankingHelperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RankingHelperProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RankingHelperProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RankingHelperProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RankingHelperProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RankingHelperProto parseFrom(CodedInputStream input) throws IOException {
        return (RankingHelperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RankingHelperProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RankingHelperProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RankingHelperProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RankingHelperProto, Builder> implements RankingHelperProtoOrBuilder {
        private Builder() {
            super(RankingHelperProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public List<String> getNotificationSignalExtractorsList() {
            return Collections.unmodifiableList(((RankingHelperProto) this.instance).getNotificationSignalExtractorsList());
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public int getNotificationSignalExtractorsCount() {
            return ((RankingHelperProto) this.instance).getNotificationSignalExtractorsCount();
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public String getNotificationSignalExtractors(int index) {
            return ((RankingHelperProto) this.instance).getNotificationSignalExtractors(index);
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public ByteString getNotificationSignalExtractorsBytes(int index) {
            return ((RankingHelperProto) this.instance).getNotificationSignalExtractorsBytes(index);
        }

        public Builder setNotificationSignalExtractors(int index, String value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).setNotificationSignalExtractors(index, value);
            return this;
        }

        public Builder addNotificationSignalExtractors(String value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addNotificationSignalExtractors(value);
            return this;
        }

        public Builder addAllNotificationSignalExtractors(Iterable<String> values) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addAllNotificationSignalExtractors(values);
            return this;
        }

        public Builder clearNotificationSignalExtractors() {
            copyOnWrite();
            ((RankingHelperProto) this.instance).clearNotificationSignalExtractors();
            return this;
        }

        public Builder addNotificationSignalExtractorsBytes(ByteString value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addNotificationSignalExtractorsBytes(value);
            return this;
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public List<RecordProto> getRecordsList() {
            return Collections.unmodifiableList(((RankingHelperProto) this.instance).getRecordsList());
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public int getRecordsCount() {
            return ((RankingHelperProto) this.instance).getRecordsCount();
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public RecordProto getRecords(int index) {
            return ((RankingHelperProto) this.instance).getRecords(index);
        }

        public Builder setRecords(int index, RecordProto value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).setRecords((RankingHelperProto) index, (int) value);
            return this;
        }

        public Builder setRecords(int index, RecordProto.Builder builderForValue) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).setRecords((RankingHelperProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRecords(RecordProto value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addRecords((RankingHelperProto) value);
            return this;
        }

        public Builder addRecords(int index, RecordProto value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addRecords((RankingHelperProto) index, (int) value);
            return this;
        }

        public Builder addRecords(RecordProto.Builder builderForValue) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addRecords((RankingHelperProto) builderForValue);
            return this;
        }

        public Builder addRecords(int index, RecordProto.Builder builderForValue) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addRecords((RankingHelperProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRecords(Iterable<? extends RecordProto> values) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addAllRecords(values);
            return this;
        }

        public Builder clearRecords() {
            copyOnWrite();
            ((RankingHelperProto) this.instance).clearRecords();
            return this;
        }

        public Builder removeRecords(int index) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).removeRecords(index);
            return this;
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public List<RecordProto> getRecordsRestoredWithoutUidList() {
            return Collections.unmodifiableList(((RankingHelperProto) this.instance).getRecordsRestoredWithoutUidList());
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public int getRecordsRestoredWithoutUidCount() {
            return ((RankingHelperProto) this.instance).getRecordsRestoredWithoutUidCount();
        }

        @Override // android.service.notification.RankingHelperProtoOrBuilder
        public RecordProto getRecordsRestoredWithoutUid(int index) {
            return ((RankingHelperProto) this.instance).getRecordsRestoredWithoutUid(index);
        }

        public Builder setRecordsRestoredWithoutUid(int index, RecordProto value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).setRecordsRestoredWithoutUid((RankingHelperProto) index, (int) value);
            return this;
        }

        public Builder setRecordsRestoredWithoutUid(int index, RecordProto.Builder builderForValue) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).setRecordsRestoredWithoutUid((RankingHelperProto) index, (int) builderForValue);
            return this;
        }

        public Builder addRecordsRestoredWithoutUid(RecordProto value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addRecordsRestoredWithoutUid((RankingHelperProto) value);
            return this;
        }

        public Builder addRecordsRestoredWithoutUid(int index, RecordProto value) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addRecordsRestoredWithoutUid((RankingHelperProto) index, (int) value);
            return this;
        }

        public Builder addRecordsRestoredWithoutUid(RecordProto.Builder builderForValue) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addRecordsRestoredWithoutUid((RankingHelperProto) builderForValue);
            return this;
        }

        public Builder addRecordsRestoredWithoutUid(int index, RecordProto.Builder builderForValue) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addRecordsRestoredWithoutUid((RankingHelperProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllRecordsRestoredWithoutUid(Iterable<? extends RecordProto> values) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).addAllRecordsRestoredWithoutUid(values);
            return this;
        }

        public Builder clearRecordsRestoredWithoutUid() {
            copyOnWrite();
            ((RankingHelperProto) this.instance).clearRecordsRestoredWithoutUid();
            return this;
        }

        public Builder removeRecordsRestoredWithoutUid(int index) {
            copyOnWrite();
            ((RankingHelperProto) this.instance).removeRecordsRestoredWithoutUid(index);
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new RankingHelperProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.notificationSignalExtractors_.makeImmutable();
                this.records_.makeImmutable();
                this.recordsRestoredWithoutUid_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                RankingHelperProto other = (RankingHelperProto) arg1;
                this.notificationSignalExtractors_ = visitor.visitList(this.notificationSignalExtractors_, other.notificationSignalExtractors_);
                this.records_ = visitor.visitList(this.records_, other.records_);
                this.recordsRestoredWithoutUid_ = visitor.visitList(this.recordsRestoredWithoutUid_, other.recordsRestoredWithoutUid_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                            if (!this.notificationSignalExtractors_.isModifiable()) {
                                this.notificationSignalExtractors_ = GeneratedMessageLite.mutableCopy(this.notificationSignalExtractors_);
                            }
                            this.notificationSignalExtractors_.add(s);
                        } else if (tag == 18) {
                            if (!this.records_.isModifiable()) {
                                this.records_ = GeneratedMessageLite.mutableCopy(this.records_);
                            }
                            this.records_.add((RecordProto) input.readMessage(RecordProto.parser(), extensionRegistry));
                        } else if (tag == 26) {
                            if (!this.recordsRestoredWithoutUid_.isModifiable()) {
                                this.recordsRestoredWithoutUid_ = GeneratedMessageLite.mutableCopy(this.recordsRestoredWithoutUid_);
                            }
                            this.recordsRestoredWithoutUid_.add((RecordProto) input.readMessage(RecordProto.parser(), extensionRegistry));
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
                    synchronized (RankingHelperProto.class) {
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

    public static RankingHelperProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RankingHelperProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
