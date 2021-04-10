package android.service.notification;

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

public final class ZenPolicyProto extends GeneratedMessageLite<ZenPolicyProto, Builder> implements ZenPolicyProtoOrBuilder {
    public static final int ALARMS_FIELD_NUMBER = 6;
    public static final int AMBIENT_FIELD_NUMBER = 14;
    public static final int BADGE_FIELD_NUMBER = 13;
    public static final int CALLS_FIELD_NUMBER = 4;
    private static final ZenPolicyProto DEFAULT_INSTANCE = new ZenPolicyProto();
    public static final int EVENTS_FIELD_NUMBER = 2;
    public static final int FULL_SCREEN_INTENT_FIELD_NUMBER = 9;
    public static final int LIGHTS_FIELD_NUMBER = 10;
    public static final int MEDIA_FIELD_NUMBER = 7;
    public static final int MESSAGES_FIELD_NUMBER = 3;
    public static final int NOTIFICATION_LIST_FIELD_NUMBER = 15;
    private static volatile Parser<ZenPolicyProto> PARSER = null;
    public static final int PEEK_FIELD_NUMBER = 11;
    public static final int PRIORITY_CALLS_FIELD_NUMBER = 16;
    public static final int PRIORITY_MESSAGES_FIELD_NUMBER = 17;
    public static final int REMINDERS_FIELD_NUMBER = 1;
    public static final int REPEAT_CALLERS_FIELD_NUMBER = 5;
    public static final int STATUS_BAR_FIELD_NUMBER = 12;
    public static final int SYSTEM_FIELD_NUMBER = 8;
    private int alarms_ = 0;
    private int ambient_ = 0;
    private int badge_ = 0;
    private int bitField0_;
    private int calls_ = 0;
    private int events_ = 0;
    private int fullScreenIntent_ = 0;
    private int lights_ = 0;
    private int media_ = 0;
    private int messages_ = 0;
    private int notificationList_ = 0;
    private int peek_ = 0;
    private int priorityCalls_ = 0;
    private int priorityMessages_ = 0;
    private int reminders_ = 0;
    private int repeatCallers_ = 0;
    private int statusBar_ = 0;
    private int system_ = 0;

    private ZenPolicyProto() {
    }

    public enum State implements Internal.EnumLite {
        STATE_UNSET(0),
        STATE_ALLOW(1),
        STATE_DISALLOW(2);
        
        public static final int STATE_ALLOW_VALUE = 1;
        public static final int STATE_DISALLOW_VALUE = 2;
        public static final int STATE_UNSET_VALUE = 0;
        private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() {
            /* class android.service.notification.ZenPolicyProto.State.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public State findValueByNumber(int number) {
                return State.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static State valueOf(int value2) {
            return forNumber(value2);
        }

        public static State forNumber(int value2) {
            if (value2 == 0) {
                return STATE_UNSET;
            }
            if (value2 == 1) {
                return STATE_ALLOW;
            }
            if (value2 != 2) {
                return null;
            }
            return STATE_DISALLOW;
        }

        public static Internal.EnumLiteMap<State> internalGetValueMap() {
            return internalValueMap;
        }

        private State(int value2) {
            this.value = value2;
        }
    }

    public enum Sender implements Internal.EnumLite {
        SENDER_UNSET(0),
        SENDER_ANY(1),
        SENDER_CONTACTS(2),
        SENDER_STARRED(3),
        SENDER_NONE(4);
        
        public static final int SENDER_ANY_VALUE = 1;
        public static final int SENDER_CONTACTS_VALUE = 2;
        public static final int SENDER_NONE_VALUE = 4;
        public static final int SENDER_STARRED_VALUE = 3;
        public static final int SENDER_UNSET_VALUE = 0;
        private static final Internal.EnumLiteMap<Sender> internalValueMap = new Internal.EnumLiteMap<Sender>() {
            /* class android.service.notification.ZenPolicyProto.Sender.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Sender findValueByNumber(int number) {
                return Sender.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Sender valueOf(int value2) {
            return forNumber(value2);
        }

        public static Sender forNumber(int value2) {
            if (value2 == 0) {
                return SENDER_UNSET;
            }
            if (value2 == 1) {
                return SENDER_ANY;
            }
            if (value2 == 2) {
                return SENDER_CONTACTS;
            }
            if (value2 == 3) {
                return SENDER_STARRED;
            }
            if (value2 != 4) {
                return null;
            }
            return SENDER_NONE;
        }

        public static Internal.EnumLiteMap<Sender> internalGetValueMap() {
            return internalValueMap;
        }

        private Sender(int value2) {
            this.value = value2;
        }
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasReminders() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getReminders() {
        State result = State.forNumber(this.reminders_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setReminders(State value) {
        if (value != null) {
            this.bitField0_ |= 1;
            this.reminders_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearReminders() {
        this.bitField0_ &= -2;
        this.reminders_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasEvents() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getEvents() {
        State result = State.forNumber(this.events_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEvents(State value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.events_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEvents() {
        this.bitField0_ &= -3;
        this.events_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasMessages() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getMessages() {
        State result = State.forNumber(this.messages_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMessages(State value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.messages_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMessages() {
        this.bitField0_ &= -5;
        this.messages_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasCalls() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getCalls() {
        State result = State.forNumber(this.calls_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCalls(State value) {
        if (value != null) {
            this.bitField0_ |= 8;
            this.calls_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCalls() {
        this.bitField0_ &= -9;
        this.calls_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasRepeatCallers() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getRepeatCallers() {
        State result = State.forNumber(this.repeatCallers_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRepeatCallers(State value) {
        if (value != null) {
            this.bitField0_ |= 16;
            this.repeatCallers_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRepeatCallers() {
        this.bitField0_ &= -17;
        this.repeatCallers_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasAlarms() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getAlarms() {
        State result = State.forNumber(this.alarms_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarms(State value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.alarms_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlarms() {
        this.bitField0_ &= -33;
        this.alarms_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasMedia() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getMedia() {
        State result = State.forNumber(this.media_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMedia(State value) {
        if (value != null) {
            this.bitField0_ |= 64;
            this.media_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMedia() {
        this.bitField0_ &= -65;
        this.media_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasSystem() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getSystem() {
        State result = State.forNumber(this.system_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystem(State value) {
        if (value != null) {
            this.bitField0_ |= 128;
            this.system_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystem() {
        this.bitField0_ &= -129;
        this.system_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasFullScreenIntent() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getFullScreenIntent() {
        State result = State.forNumber(this.fullScreenIntent_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFullScreenIntent(State value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.fullScreenIntent_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFullScreenIntent() {
        this.bitField0_ &= -257;
        this.fullScreenIntent_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasLights() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getLights() {
        State result = State.forNumber(this.lights_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLights(State value) {
        if (value != null) {
            this.bitField0_ |= 512;
            this.lights_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLights() {
        this.bitField0_ &= -513;
        this.lights_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasPeek() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getPeek() {
        State result = State.forNumber(this.peek_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPeek(State value) {
        if (value != null) {
            this.bitField0_ |= 1024;
            this.peek_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPeek() {
        this.bitField0_ &= -1025;
        this.peek_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasStatusBar() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getStatusBar() {
        State result = State.forNumber(this.statusBar_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatusBar(State value) {
        if (value != null) {
            this.bitField0_ |= 2048;
            this.statusBar_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatusBar() {
        this.bitField0_ &= -2049;
        this.statusBar_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasBadge() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getBadge() {
        State result = State.forNumber(this.badge_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBadge(State value) {
        if (value != null) {
            this.bitField0_ |= 4096;
            this.badge_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBadge() {
        this.bitField0_ &= -4097;
        this.badge_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasAmbient() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getAmbient() {
        State result = State.forNumber(this.ambient_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAmbient(State value) {
        if (value != null) {
            this.bitField0_ |= 8192;
            this.ambient_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAmbient() {
        this.bitField0_ &= -8193;
        this.ambient_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasNotificationList() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public State getNotificationList() {
        State result = State.forNumber(this.notificationList_);
        return result == null ? State.STATE_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotificationList(State value) {
        if (value != null) {
            this.bitField0_ |= 16384;
            this.notificationList_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotificationList() {
        this.bitField0_ &= -16385;
        this.notificationList_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasPriorityCalls() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public Sender getPriorityCalls() {
        Sender result = Sender.forNumber(this.priorityCalls_);
        return result == null ? Sender.SENDER_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriorityCalls(Sender value) {
        if (value != null) {
            this.bitField0_ |= 32768;
            this.priorityCalls_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPriorityCalls() {
        this.bitField0_ &= -32769;
        this.priorityCalls_ = 0;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public boolean hasPriorityMessages() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // android.service.notification.ZenPolicyProtoOrBuilder
    public Sender getPriorityMessages() {
        Sender result = Sender.forNumber(this.priorityMessages_);
        return result == null ? Sender.SENDER_UNSET : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPriorityMessages(Sender value) {
        if (value != null) {
            this.bitField0_ |= 65536;
            this.priorityMessages_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPriorityMessages() {
        this.bitField0_ &= -65537;
        this.priorityMessages_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeEnum(1, this.reminders_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(2, this.events_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.messages_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeEnum(4, this.calls_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeEnum(5, this.repeatCallers_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeEnum(6, this.alarms_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeEnum(7, this.media_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeEnum(8, this.system_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeEnum(9, this.fullScreenIntent_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeEnum(10, this.lights_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeEnum(11, this.peek_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeEnum(12, this.statusBar_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeEnum(13, this.badge_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeEnum(14, this.ambient_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeEnum(15, this.notificationList_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeEnum(16, this.priorityCalls_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeEnum(17, this.priorityMessages_);
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
            size2 = 0 + CodedOutputStream.computeEnumSize(1, this.reminders_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(2, this.events_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.messages_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeEnumSize(4, this.calls_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeEnumSize(5, this.repeatCallers_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeEnumSize(6, this.alarms_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeEnumSize(7, this.media_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeEnumSize(8, this.system_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeEnumSize(9, this.fullScreenIntent_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeEnumSize(10, this.lights_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeEnumSize(11, this.peek_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeEnumSize(12, this.statusBar_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeEnumSize(13, this.badge_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeEnumSize(14, this.ambient_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeEnumSize(15, this.notificationList_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeEnumSize(16, this.priorityCalls_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeEnumSize(17, this.priorityMessages_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static ZenPolicyProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ZenPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ZenPolicyProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ZenPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ZenPolicyProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ZenPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ZenPolicyProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ZenPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ZenPolicyProto parseFrom(InputStream input) throws IOException {
        return (ZenPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenPolicyProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ZenPolicyProto parseDelimitedFrom(InputStream input) throws IOException {
        return (ZenPolicyProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenPolicyProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenPolicyProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ZenPolicyProto parseFrom(CodedInputStream input) throws IOException {
        return (ZenPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ZenPolicyProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ZenPolicyProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ZenPolicyProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ZenPolicyProto, Builder> implements ZenPolicyProtoOrBuilder {
        private Builder() {
            super(ZenPolicyProto.DEFAULT_INSTANCE);
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasReminders() {
            return ((ZenPolicyProto) this.instance).hasReminders();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getReminders() {
            return ((ZenPolicyProto) this.instance).getReminders();
        }

        public Builder setReminders(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setReminders(value);
            return this;
        }

        public Builder clearReminders() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearReminders();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasEvents() {
            return ((ZenPolicyProto) this.instance).hasEvents();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getEvents() {
            return ((ZenPolicyProto) this.instance).getEvents();
        }

        public Builder setEvents(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setEvents(value);
            return this;
        }

        public Builder clearEvents() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearEvents();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasMessages() {
            return ((ZenPolicyProto) this.instance).hasMessages();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getMessages() {
            return ((ZenPolicyProto) this.instance).getMessages();
        }

        public Builder setMessages(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setMessages(value);
            return this;
        }

        public Builder clearMessages() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearMessages();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasCalls() {
            return ((ZenPolicyProto) this.instance).hasCalls();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getCalls() {
            return ((ZenPolicyProto) this.instance).getCalls();
        }

        public Builder setCalls(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setCalls(value);
            return this;
        }

        public Builder clearCalls() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearCalls();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasRepeatCallers() {
            return ((ZenPolicyProto) this.instance).hasRepeatCallers();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getRepeatCallers() {
            return ((ZenPolicyProto) this.instance).getRepeatCallers();
        }

        public Builder setRepeatCallers(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setRepeatCallers(value);
            return this;
        }

        public Builder clearRepeatCallers() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearRepeatCallers();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasAlarms() {
            return ((ZenPolicyProto) this.instance).hasAlarms();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getAlarms() {
            return ((ZenPolicyProto) this.instance).getAlarms();
        }

        public Builder setAlarms(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setAlarms(value);
            return this;
        }

        public Builder clearAlarms() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearAlarms();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasMedia() {
            return ((ZenPolicyProto) this.instance).hasMedia();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getMedia() {
            return ((ZenPolicyProto) this.instance).getMedia();
        }

        public Builder setMedia(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setMedia(value);
            return this;
        }

        public Builder clearMedia() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearMedia();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasSystem() {
            return ((ZenPolicyProto) this.instance).hasSystem();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getSystem() {
            return ((ZenPolicyProto) this.instance).getSystem();
        }

        public Builder setSystem(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setSystem(value);
            return this;
        }

        public Builder clearSystem() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearSystem();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasFullScreenIntent() {
            return ((ZenPolicyProto) this.instance).hasFullScreenIntent();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getFullScreenIntent() {
            return ((ZenPolicyProto) this.instance).getFullScreenIntent();
        }

        public Builder setFullScreenIntent(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setFullScreenIntent(value);
            return this;
        }

        public Builder clearFullScreenIntent() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearFullScreenIntent();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasLights() {
            return ((ZenPolicyProto) this.instance).hasLights();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getLights() {
            return ((ZenPolicyProto) this.instance).getLights();
        }

        public Builder setLights(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setLights(value);
            return this;
        }

        public Builder clearLights() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearLights();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasPeek() {
            return ((ZenPolicyProto) this.instance).hasPeek();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getPeek() {
            return ((ZenPolicyProto) this.instance).getPeek();
        }

        public Builder setPeek(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setPeek(value);
            return this;
        }

        public Builder clearPeek() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearPeek();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasStatusBar() {
            return ((ZenPolicyProto) this.instance).hasStatusBar();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getStatusBar() {
            return ((ZenPolicyProto) this.instance).getStatusBar();
        }

        public Builder setStatusBar(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setStatusBar(value);
            return this;
        }

        public Builder clearStatusBar() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearStatusBar();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasBadge() {
            return ((ZenPolicyProto) this.instance).hasBadge();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getBadge() {
            return ((ZenPolicyProto) this.instance).getBadge();
        }

        public Builder setBadge(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setBadge(value);
            return this;
        }

        public Builder clearBadge() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearBadge();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasAmbient() {
            return ((ZenPolicyProto) this.instance).hasAmbient();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getAmbient() {
            return ((ZenPolicyProto) this.instance).getAmbient();
        }

        public Builder setAmbient(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setAmbient(value);
            return this;
        }

        public Builder clearAmbient() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearAmbient();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasNotificationList() {
            return ((ZenPolicyProto) this.instance).hasNotificationList();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public State getNotificationList() {
            return ((ZenPolicyProto) this.instance).getNotificationList();
        }

        public Builder setNotificationList(State value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setNotificationList(value);
            return this;
        }

        public Builder clearNotificationList() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearNotificationList();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasPriorityCalls() {
            return ((ZenPolicyProto) this.instance).hasPriorityCalls();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public Sender getPriorityCalls() {
            return ((ZenPolicyProto) this.instance).getPriorityCalls();
        }

        public Builder setPriorityCalls(Sender value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setPriorityCalls(value);
            return this;
        }

        public Builder clearPriorityCalls() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearPriorityCalls();
            return this;
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public boolean hasPriorityMessages() {
            return ((ZenPolicyProto) this.instance).hasPriorityMessages();
        }

        @Override // android.service.notification.ZenPolicyProtoOrBuilder
        public Sender getPriorityMessages() {
            return ((ZenPolicyProto) this.instance).getPriorityMessages();
        }

        public Builder setPriorityMessages(Sender value) {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).setPriorityMessages(value);
            return this;
        }

        public Builder clearPriorityMessages() {
            copyOnWrite();
            ((ZenPolicyProto) this.instance).clearPriorityMessages();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new ZenPolicyProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                ZenPolicyProto other = (ZenPolicyProto) arg1;
                this.reminders_ = visitor.visitInt(hasReminders(), this.reminders_, other.hasReminders(), other.reminders_);
                this.events_ = visitor.visitInt(hasEvents(), this.events_, other.hasEvents(), other.events_);
                this.messages_ = visitor.visitInt(hasMessages(), this.messages_, other.hasMessages(), other.messages_);
                this.calls_ = visitor.visitInt(hasCalls(), this.calls_, other.hasCalls(), other.calls_);
                this.repeatCallers_ = visitor.visitInt(hasRepeatCallers(), this.repeatCallers_, other.hasRepeatCallers(), other.repeatCallers_);
                this.alarms_ = visitor.visitInt(hasAlarms(), this.alarms_, other.hasAlarms(), other.alarms_);
                this.media_ = visitor.visitInt(hasMedia(), this.media_, other.hasMedia(), other.media_);
                this.system_ = visitor.visitInt(hasSystem(), this.system_, other.hasSystem(), other.system_);
                this.fullScreenIntent_ = visitor.visitInt(hasFullScreenIntent(), this.fullScreenIntent_, other.hasFullScreenIntent(), other.fullScreenIntent_);
                this.lights_ = visitor.visitInt(hasLights(), this.lights_, other.hasLights(), other.lights_);
                this.peek_ = visitor.visitInt(hasPeek(), this.peek_, other.hasPeek(), other.peek_);
                this.statusBar_ = visitor.visitInt(hasStatusBar(), this.statusBar_, other.hasStatusBar(), other.statusBar_);
                this.badge_ = visitor.visitInt(hasBadge(), this.badge_, other.hasBadge(), other.badge_);
                this.ambient_ = visitor.visitInt(hasAmbient(), this.ambient_, other.hasAmbient(), other.ambient_);
                this.notificationList_ = visitor.visitInt(hasNotificationList(), this.notificationList_, other.hasNotificationList(), other.notificationList_);
                this.priorityCalls_ = visitor.visitInt(hasPriorityCalls(), this.priorityCalls_, other.hasPriorityCalls(), other.priorityCalls_);
                this.priorityMessages_ = visitor.visitInt(hasPriorityMessages(), this.priorityMessages_, other.hasPriorityMessages(), other.priorityMessages_);
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
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                int rawValue = input.readEnum();
                                if (State.forNumber(rawValue) != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.reminders_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(1, rawValue);
                                    break;
                                }
                            case 16:
                                int rawValue2 = input.readEnum();
                                if (State.forNumber(rawValue2) != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.events_ = rawValue2;
                                    break;
                                } else {
                                    super.mergeVarintField(2, rawValue2);
                                    break;
                                }
                            case 24:
                                int rawValue3 = input.readEnum();
                                if (State.forNumber(rawValue3) != null) {
                                    this.bitField0_ = 4 | this.bitField0_;
                                    this.messages_ = rawValue3;
                                    break;
                                } else {
                                    super.mergeVarintField(3, rawValue3);
                                    break;
                                }
                            case 32:
                                int rawValue4 = input.readEnum();
                                if (State.forNumber(rawValue4) != null) {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.calls_ = rawValue4;
                                    break;
                                } else {
                                    super.mergeVarintField(4, rawValue4);
                                    break;
                                }
                            case 40:
                                int rawValue5 = input.readEnum();
                                if (State.forNumber(rawValue5) != null) {
                                    this.bitField0_ = 16 | this.bitField0_;
                                    this.repeatCallers_ = rawValue5;
                                    break;
                                } else {
                                    super.mergeVarintField(5, rawValue5);
                                    break;
                                }
                            case 48:
                                int rawValue6 = input.readEnum();
                                if (State.forNumber(rawValue6) != null) {
                                    this.bitField0_ |= 32;
                                    this.alarms_ = rawValue6;
                                    break;
                                } else {
                                    super.mergeVarintField(6, rawValue6);
                                    break;
                                }
                            case 56:
                                int rawValue7 = input.readEnum();
                                if (State.forNumber(rawValue7) != null) {
                                    this.bitField0_ |= 64;
                                    this.media_ = rawValue7;
                                    break;
                                } else {
                                    super.mergeVarintField(7, rawValue7);
                                    break;
                                }
                            case 64:
                                int rawValue8 = input.readEnum();
                                if (State.forNumber(rawValue8) != null) {
                                    this.bitField0_ |= 128;
                                    this.system_ = rawValue8;
                                    break;
                                } else {
                                    super.mergeVarintField(8, rawValue8);
                                    break;
                                }
                            case 72:
                                int rawValue9 = input.readEnum();
                                if (State.forNumber(rawValue9) != null) {
                                    this.bitField0_ |= 256;
                                    this.fullScreenIntent_ = rawValue9;
                                    break;
                                } else {
                                    super.mergeVarintField(9, rawValue9);
                                    break;
                                }
                            case 80:
                                int rawValue10 = input.readEnum();
                                if (State.forNumber(rawValue10) != null) {
                                    this.bitField0_ |= 512;
                                    this.lights_ = rawValue10;
                                    break;
                                } else {
                                    super.mergeVarintField(10, rawValue10);
                                    break;
                                }
                            case 88:
                                int rawValue11 = input.readEnum();
                                if (State.forNumber(rawValue11) != null) {
                                    this.bitField0_ |= 1024;
                                    this.peek_ = rawValue11;
                                    break;
                                } else {
                                    super.mergeVarintField(11, rawValue11);
                                    break;
                                }
                            case 96:
                                int rawValue12 = input.readEnum();
                                if (State.forNumber(rawValue12) != null) {
                                    this.bitField0_ |= 2048;
                                    this.statusBar_ = rawValue12;
                                    break;
                                } else {
                                    super.mergeVarintField(12, rawValue12);
                                    break;
                                }
                            case 104:
                                int rawValue13 = input.readEnum();
                                if (State.forNumber(rawValue13) != null) {
                                    this.bitField0_ |= 4096;
                                    this.badge_ = rawValue13;
                                    break;
                                } else {
                                    super.mergeVarintField(13, rawValue13);
                                    break;
                                }
                            case 112:
                                int rawValue14 = input.readEnum();
                                if (State.forNumber(rawValue14) != null) {
                                    this.bitField0_ |= 8192;
                                    this.ambient_ = rawValue14;
                                    break;
                                } else {
                                    super.mergeVarintField(14, rawValue14);
                                    break;
                                }
                            case 120:
                                int rawValue15 = input.readEnum();
                                if (State.forNumber(rawValue15) != null) {
                                    this.bitField0_ |= 16384;
                                    this.notificationList_ = rawValue15;
                                    break;
                                } else {
                                    super.mergeVarintField(15, rawValue15);
                                    break;
                                }
                            case 128:
                                int rawValue16 = input.readEnum();
                                if (Sender.forNumber(rawValue16) != null) {
                                    this.bitField0_ |= 32768;
                                    this.priorityCalls_ = rawValue16;
                                    break;
                                } else {
                                    super.mergeVarintField(16, rawValue16);
                                    break;
                                }
                            case 136:
                                int rawValue17 = input.readEnum();
                                if (Sender.forNumber(rawValue17) != null) {
                                    this.bitField0_ |= 65536;
                                    this.priorityMessages_ = rawValue17;
                                    break;
                                } else {
                                    super.mergeVarintField(17, rawValue17);
                                    break;
                                }
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
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
                    synchronized (ZenPolicyProto.class) {
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

    public static ZenPolicyProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ZenPolicyProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
