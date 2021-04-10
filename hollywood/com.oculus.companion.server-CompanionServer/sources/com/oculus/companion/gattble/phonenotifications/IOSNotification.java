package com.oculus.companion.gattble.phonenotifications;

import android.support.coordinatorlayout.R$styleable;
import android.util.Log;
import java.io.EOFException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.Locale;

/* access modifiers changed from: package-private */
public class IOSNotification {
    final String appID;
    final int categoryCount;
    final CategoryID categoryID;
    final Date date;
    final EnumSet<EventFlag> eventFlags;
    final EventID eventID;
    final String message;
    final int messageSize;
    final String negativeActionLabel;
    final int notificationUID;
    final String positiveActionLabel;
    final String subtitle;
    final String title;

    private IOSNotification(EventID eventID2, EnumSet<EventFlag> enumSet, CategoryID categoryID2, int i, int i2, String str, String str2, String str3, String str4, int i3, Date date2, String str5, String str6) {
        this.eventID = eventID2;
        this.eventFlags = enumSet;
        this.categoryID = categoryID2;
        this.categoryCount = i;
        this.notificationUID = i2;
        this.appID = str;
        this.title = str2;
        this.subtitle = str3;
        this.message = str4;
        this.messageSize = i3;
        this.date = date2;
        this.positiveActionLabel = str5;
        this.negativeActionLabel = str6;
    }

    public String toString() {
        return String.format(Locale.US, "%s<event_id=%s, event_flags=%s, category_id=%s, category_count=%d, notification_uid=%d, app_id=%s, title=%s, subtitle=%s, message=%s, message_size=%d date=%s, positive_action_label=%s, negative_action_label=%s>", IOSNotification.class.getSimpleName(), this.eventID, this.eventFlags, this.categoryID, Integer.valueOf(this.categoryCount), Integer.valueOf(this.notificationUID), this.appID, this.title, this.subtitle, this.message, Integer.valueOf(this.messageSize), this.date, this.positiveActionLabel, this.negativeActionLabel);
    }

    /* access modifiers changed from: package-private */
    public static class Builder {
        private static final String TAG = "Builder";
        private String mAppID;
        private int mCategoryCount;
        private CategoryID mCategoryID;
        private Date mDate;
        private EnumSet<EventFlag> mEventFlags;
        private EventID mEventID;
        private String mMessage;
        private int mMessageSize;
        private String mNegativeActionLabel;
        private int mNotificationUID;
        private String mPositiveActionLabel;
        private EnumSet<State> mStatesCurrent = EnumSet.noneOf(State.class);
        private final EnumSet<State> mStatesExpected = EnumSet.complementOf(EnumSet.of(State.POSITIVE_ACTION_LABEL_SET, State.NEGATIVE_ACTION_LABEL_SET));
        private String mSubtitle;
        private String mTitle;
        private ANCSByteArrayOutputStream mUnprocessedPayload;

        /* access modifiers changed from: private */
        public enum State {
            VALIDATED,
            APP_ID_SET,
            TITLE_SET,
            SUBTITLE_SET,
            MESSAGE_SET,
            MESSAGE_SIZE_SET,
            DATE_SET,
            POSITIVE_ACTION_LABEL_SET,
            NEGATIVE_ACTION_LABEL_SET
        }

        Builder(byte[] bArr) throws IOException {
            ANCSByteArrayInputStream aNCSByteArrayInputStream = new ANCSByteArrayInputStream(bArr);
            this.mEventID = intToEventID(aNCSByteArrayInputStream.readInt8());
            this.mEventFlags = intToEventFlags(aNCSByteArrayInputStream.readInt8());
            this.mCategoryID = intToCategoryID(aNCSByteArrayInputStream.readInt8());
            this.mCategoryCount = aNCSByteArrayInputStream.readInt8();
            this.mNotificationUID = aNCSByteArrayInputStream.readInt32();
            aNCSByteArrayInputStream.close();
            if (this.mEventFlags.contains(EventFlag.POSITIVE_ACTION)) {
                this.mStatesExpected.add(State.POSITIVE_ACTION_LABEL_SET);
            }
            if (this.mEventFlags.contains(EventFlag.NEGATIVE_ACTION)) {
                this.mStatesExpected.add(State.NEGATIVE_ACTION_LABEL_SET);
            }
            this.mUnprocessedPayload = new ANCSByteArrayOutputStream();
        }

        /* access modifiers changed from: package-private */
        public void appendDataSourcePayload(byte[] bArr) throws IOException {
            String str;
            this.mUnprocessedPayload.write(bArr);
            ANCSByteArrayInputStream aNCSByteArrayInputStream = new ANCSByteArrayInputStream(this.mUnprocessedPayload.toByteArray());
            this.mUnprocessedPayload.reset();
            boolean z = true;
            if (!this.mStatesCurrent.contains(State.VALIDATED)) {
                int readInt8 = aNCSByteArrayInputStream.readInt8();
                if (readInt8 != 0) {
                    Log.e(TAG, String.format(Locale.US, "Unexpected Command ID: Expecting %d whereas %d is received", 0, Integer.valueOf(readInt8)));
                    aNCSByteArrayInputStream.close();
                    return;
                }
                int readInt32 = aNCSByteArrayInputStream.readInt32();
                int i = this.mNotificationUID;
                if (readInt32 != i) {
                    Log.e(TAG, String.format(Locale.US, "Unexpected Notification UID: Expecting %d whereas %d is received", Integer.valueOf(i), Integer.valueOf(readInt32)));
                    aNCSByteArrayInputStream.close();
                    return;
                }
                this.mStatesCurrent.add(State.VALIDATED);
            }
            while (aNCSByteArrayInputStream.available() > 0) {
                aNCSByteArrayInputStream.mark();
                try {
                    int readInt82 = aNCSByteArrayInputStream.readInt8();
                    int readInt16 = aNCSByteArrayInputStream.readInt16();
                    if (readInt16 > 0) {
                        str = aNCSByteArrayInputStream.readString(readInt16);
                    } else {
                        str = null;
                    }
                    switch (readInt82) {
                        case 0:
                            this.mAppID = str;
                            this.mStatesCurrent.add(State.APP_ID_SET);
                            break;
                        case 1:
                            this.mTitle = str;
                            this.mStatesCurrent.add(State.TITLE_SET);
                            break;
                        case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                            this.mSubtitle = str;
                            this.mStatesCurrent.add(State.SUBTITLE_SET);
                            break;
                        case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                            this.mMessage = str;
                            this.mStatesCurrent.add(State.MESSAGE_SET);
                            break;
                        case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                            this.mMessageSize = str != null ? Integer.parseInt(str) : 0;
                            this.mStatesCurrent.add(State.MESSAGE_SIZE_SET);
                            break;
                        case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                            if (str != null) {
                                try {
                                    this.mDate = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.US).parse(str);
                                } catch (ParseException e) {
                                    Log.e(TAG, "Unable to parse Date " + str, e);
                                }
                            }
                            this.mStatesCurrent.add(State.DATE_SET);
                            break;
                        case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                            this.mPositiveActionLabel = str;
                            this.mStatesCurrent.add(State.POSITIVE_ACTION_LABEL_SET);
                            break;
                        case 7:
                            this.mNegativeActionLabel = str;
                            this.mStatesCurrent.add(State.NEGATIVE_ACTION_LABEL_SET);
                            break;
                        default:
                            Log.w(TAG, "Unrecognized Attribute ID " + readInt82);
                            break;
                    }
                    aNCSByteArrayInputStream.close();
                } catch (EOFException e2) {
                    aNCSByteArrayInputStream.reset();
                    int available = aNCSByteArrayInputStream.available();
                    byte[] bArr2 = new byte[available];
                    if (aNCSByteArrayInputStream.read(bArr2, 0, available) != available) {
                        z = false;
                    }
                    aNCSByteArrayInputStream.close();
                    if (z) {
                        this.mUnprocessedPayload.write(bArr2);
                        return;
                    }
                    Log.e(TAG, "Unable to persist unprocessed payload for notification " + this.mNotificationUID, e2);
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public byte[] buildGetNotificationAttributesCommand() {
            try {
                ANCSByteArrayOutputStream aNCSByteArrayOutputStream = new ANCSByteArrayOutputStream();
                aNCSByteArrayOutputStream.writeByte(0);
                aNCSByteArrayOutputStream.writeInt32(this.mNotificationUID);
                aNCSByteArrayOutputStream.writeByte(0);
                aNCSByteArrayOutputStream.writeByte(1);
                aNCSByteArrayOutputStream.writeInt16(Short.MAX_VALUE);
                aNCSByteArrayOutputStream.writeByte(2);
                aNCSByteArrayOutputStream.writeInt16(Short.MAX_VALUE);
                aNCSByteArrayOutputStream.writeByte(3);
                aNCSByteArrayOutputStream.writeInt16(Short.MAX_VALUE);
                aNCSByteArrayOutputStream.writeByte(4);
                aNCSByteArrayOutputStream.writeByte(5);
                if (this.mEventFlags.contains(EventFlag.POSITIVE_ACTION)) {
                    aNCSByteArrayOutputStream.writeByte(6);
                    this.mStatesExpected.add(State.POSITIVE_ACTION_LABEL_SET);
                }
                if (this.mEventFlags.contains(EventFlag.NEGATIVE_ACTION)) {
                    aNCSByteArrayOutputStream.writeByte(7);
                    this.mStatesExpected.add(State.NEGATIVE_ACTION_LABEL_SET);
                }
                byte[] byteArray = aNCSByteArrayOutputStream.toByteArray();
                aNCSByteArrayOutputStream.close();
                return byteArray;
            } catch (IOException e) {
                String str = TAG;
                Log.e(str, "Unable to populate Get Notification Attributes command for notification " + this.mNotificationUID, e);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public IOSNotification build() {
            if (!isComplete()) {
                Log.i(TAG, String.format(Locale.US, "Unable to build Notification %d: Notification is still waiting for more data", Integer.valueOf(this.mNotificationUID)));
                return null;
            }
            try {
                this.mUnprocessedPayload.close();
                return new IOSNotification(this.mEventID, this.mEventFlags, this.mCategoryID, this.mCategoryCount, this.mNotificationUID, this.mAppID, this.mTitle, this.mSubtitle, this.mMessage, this.mMessageSize, this.mDate, this.mPositiveActionLabel, this.mNegativeActionLabel);
            } catch (IOException e) {
                String str = TAG;
                Log.e(str, "Unable to build Notification " + this.mNotificationUID, e);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public EventID getEventID() {
            return this.mEventID;
        }

        /* access modifiers changed from: package-private */
        public boolean hasEventFlag(EventFlag eventFlag) {
            return this.mEventFlags.contains(eventFlag);
        }

        private boolean isComplete() {
            return this.mStatesCurrent.equals(this.mStatesExpected);
        }

        private static EventID intToEventID(int i) {
            if (i == 0) {
                return EventID.NOTIFICATION_ADDED;
            }
            if (i == 1) {
                return EventID.NOTIFICATION_MODIFIED;
            }
            if (i == 2) {
                return EventID.NOTIFICATION_REMOVED;
            }
            String str = TAG;
            Log.w(str, "Unrecognized Event ID " + i);
            return EventID.UNKNOWN;
        }

        private static EnumSet<EventFlag> intToEventFlags(int i) {
            EnumSet<EventFlag> noneOf = EnumSet.noneOf(EventFlag.class);
            if ((i & 1) == 1) {
                noneOf.add(EventFlag.SILENT);
            }
            if ((i & 2) == 2) {
                noneOf.add(EventFlag.IMPORTANT);
            }
            if ((i & 4) == 4) {
                noneOf.add(EventFlag.PRE_EXISTING);
            }
            if ((i & 8) == 8) {
                noneOf.add(EventFlag.POSITIVE_ACTION);
            }
            if ((i & 16) == 16) {
                noneOf.add(EventFlag.NEGATIVE_ACTION);
            }
            return noneOf;
        }

        private static CategoryID intToCategoryID(int i) {
            switch (i) {
                case 0:
                    return CategoryID.OTHER;
                case 1:
                    return CategoryID.INCOMING_CALL;
                case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                    return CategoryID.MISSED_CALL;
                case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                    return CategoryID.VOICE_MAIL;
                case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                    return CategoryID.SOCIAL;
                case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                    return CategoryID.SCHEDULE;
                case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                    return CategoryID.EMAIL;
                case 7:
                    return CategoryID.NEWS;
                case 8:
                    return CategoryID.HEALTH_AND_FITNESS;
                case 9:
                    return CategoryID.BUSINESS_AND_FINANCE;
                case 10:
                    return CategoryID.LOCATION;
                case 11:
                    return CategoryID.ENTERTAINMENT;
                default:
                    String str = TAG;
                    Log.w(str, "Unrecognized Category ID " + i);
                    return CategoryID.UNKNOWN;
            }
        }
    }
}
