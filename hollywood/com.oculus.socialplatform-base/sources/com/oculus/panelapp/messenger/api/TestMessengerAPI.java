package com.oculus.panelapp.messenger.api;

import X.AnonymousClass006;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.messengervr.fbshared.models.FBMessengerMessage;
import com.oculus.messengervr.fbshared.models.FBMessengerThread;
import com.oculus.messengervr.interfaces.BlockedByViewerStatus;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.messengervr.interfaces.MessengerThread;
import com.oculus.messengervr.interfaces.MessengerVrApi;
import com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder;
import com.oculus.socialplatform.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestMessengerAPI implements IMessengerAPI {
    public static String sMockProfilePicUri;
    public Context mContext;
    public MessengerThread mCurrentThread;
    public List<MessengerParticipant> mGroupThreadContainingBlockedParticipants = new ArrayList(Arrays.asList(new MessengerParticipant() {
        /* class com.oculus.panelapp.messenger.api.TestMessengerAPI.AnonymousClass3 */

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getName() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getNickname() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        public long getParticipantId() {
            return 0;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getProfilePictureUrl() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public BlockedByViewerStatus getBlockedByViewerStatus() {
            return BlockedByViewerStatus.FULLY_BLOCKED;
        }
    }, new MessengerParticipant() {
        /* class com.oculus.panelapp.messenger.api.TestMessengerAPI.AnonymousClass4 */

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public BlockedByViewerStatus getBlockedByViewerStatus() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getName() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getNickname() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        public long getParticipantId() {
            return 0;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getProfilePictureUrl() {
            return null;
        }
    }, new MessengerParticipant() {
        /* class com.oculus.panelapp.messenger.api.TestMessengerAPI.AnonymousClass5 */

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public BlockedByViewerStatus getBlockedByViewerStatus() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getName() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getNickname() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        public long getParticipantId() {
            return 0;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getProfilePictureUrl() {
            return null;
        }
    }));
    public MailboxListener mMailboxListener;
    public List<MessengerMessage> mMessageList;
    public List<MessengerParticipant> mOneOnOneBlockedThreadParticipants = new ArrayList(Arrays.asList(new MessengerParticipant() {
        /* class com.oculus.panelapp.messenger.api.TestMessengerAPI.AnonymousClass1 */

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getName() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getNickname() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        public long getParticipantId() {
            return 0;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getProfilePictureUrl() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public BlockedByViewerStatus getBlockedByViewerStatus() {
            return BlockedByViewerStatus.FULLY_BLOCKED;
        }
    }, new MessengerParticipant() {
        /* class com.oculus.panelapp.messenger.api.TestMessengerAPI.AnonymousClass2 */

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public BlockedByViewerStatus getBlockedByViewerStatus() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getName() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getNickname() {
            return null;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        public long getParticipantId() {
            return 0;
        }

        @Override // com.oculus.messengervr.interfaces.MessengerParticipant
        @Nullable
        public String getProfilePictureUrl() {
            return null;
        }
    }));
    public List<MessengerThread> mThreadList;
    public ThreadListener mThreadListener;
    public List<MessengerParticipant> mThreadParticipants = new ArrayList();

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void destroy() {
        this.mMailboxListener = null;
        this.mThreadListener = null;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    @Nullable
    public MessengerVrApi getInternalApi() {
        return null;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public String getUserID() {
        return "123123";
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void leaveGroupThread(MessengerThread messengerThread) {
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void sendMessage(String str, long j, @Nullable MessengerActionCallback messengerActionCallback, @Nullable MessengerActionCallback messengerActionCallback2) {
    }

    public static List<MessengerMessage> getMockMessagesData(Long l, int i) {
        int i2;
        String obj;
        int i3;
        ArrayList arrayList = new ArrayList();
        Integer num = 0;
        int i4 = 0;
        while (true) {
            i2 = 3;
            if (i4 >= 3) {
                break;
            }
            FBMessengerMessage.Builder builder = new FBMessengerMessage.Builder();
            StringBuilder sb = new StringBuilder();
            sb.append("message_");
            sb.append(i4);
            sb.append("_");
            sb.append(l);
            sb.append(i);
            builder.mMessageId = sb.toString();
            builder.mText = AnonymousClass006.A05(l.toString(), i, " A message from user_456", i4);
            builder.mSenderId = 123123L;
            builder.mSenderName = "user_456";
            builder.mSenderProfilePictureUrl = sMockProfilePicUri;
            builder.mTimestampMs = Long.valueOf((long) i4);
            builder.mIsAdminMessage = false;
            builder.mAttachmentType = num.intValue();
            builder.mAttachmentImageUrls = new String[0];
            builder.mStickerPreviewWidth = num.intValue();
            builder.mStickerPreviewHeight = num.intValue();
            arrayList.add(builder.build());
            i4++;
        }
        do {
            FBMessengerMessage.Builder builder2 = new FBMessengerMessage.Builder();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("message_");
            sb2.append(i2);
            sb2.append("_");
            sb2.append(l);
            sb2.append(i);
            builder2.mMessageId = sb2.toString();
            obj = l.toString();
            builder2.mText = AnonymousClass006.A05(obj, i, " Yo here's a message from 123. ", i2);
            builder2.mSenderId = 123123L;
            builder2.mSenderName = "you";
            builder2.mSenderProfilePictureUrl = sMockProfilePicUri;
            builder2.mTimestampMs = Long.valueOf((long) i2);
            builder2.mIsAdminMessage = false;
            builder2.mAttachmentType = num.intValue();
            builder2.mAttachmentImageUrls = new String[0];
            builder2.mStickerPreviewWidth = num.intValue();
            builder2.mStickerPreviewHeight = num.intValue();
            arrayList.add(builder2.build());
            i2++;
        } while (i2 < 6);
        for (i3 = 6; i3 < 9; i3++) {
            FBMessengerMessage.Builder builder3 = new FBMessengerMessage.Builder();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("message_");
            sb3.append(i3);
            sb3.append("_");
            sb3.append(l);
            sb3.append(i);
            builder3.mMessageId = sb3.toString();
            builder3.mText = AnonymousClass006.A05(obj, i, " Yo here's a message from 789. ", i3);
            builder3.mSenderId = 789L;
            builder3.mSenderName = "user_789";
            builder3.mSenderProfilePictureUrl = sMockProfilePicUri;
            builder3.mTimestampMs = Long.valueOf((long) i3);
            builder3.mIsAdminMessage = false;
            builder3.mAttachmentType = num.intValue();
            builder3.mAttachmentImageUrls = new String[0];
            builder3.mStickerPreviewWidth = num.intValue();
            builder3.mStickerPreviewHeight = num.intValue();
            arrayList.add(builder3.build());
        }
        FBMessengerMessage.Builder builder4 = new FBMessengerMessage.Builder();
        StringBuilder sb4 = new StringBuilder();
        sb4.append("message_");
        sb4.append(9);
        sb4.append("_");
        sb4.append(l);
        sb4.append(i);
        builder4.mMessageId = sb4.toString();
        builder4.mText = AnonymousClass006.A05(obj, i, " Yo here's a slightly longer message from 123. ", 9);
        builder4.mSenderId = 123L;
        builder4.mSenderName = "user_123";
        builder4.mSenderProfilePictureUrl = sMockProfilePicUri;
        builder4.mTimestampMs = Long.valueOf((long) 9);
        builder4.mIsAdminMessage = false;
        builder4.mAttachmentType = num.intValue();
        builder4.mAttachmentImageUrls = new String[0];
        builder4.mStickerPreviewWidth = num.intValue();
        builder4.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder4.build());
        int i5 = 10;
        do {
            FBMessengerMessage.Builder builder5 = new FBMessengerMessage.Builder();
            StringBuilder sb5 = new StringBuilder();
            sb5.append("message_");
            sb5.append(i5);
            sb5.append("_");
            sb5.append(l);
            sb5.append(i);
            builder5.mMessageId = sb5.toString();
            builder5.mText = AnonymousClass006.A05(obj, i, " Yo here's a message from 123. ", i5);
            builder5.mSenderId = 123123L;
            builder5.mSenderName = "you";
            builder5.mSenderProfilePictureUrl = sMockProfilePicUri;
            builder5.mTimestampMs = Long.valueOf(((long) i5) + BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME + 1);
            builder5.mIsAdminMessage = false;
            builder5.mAttachmentType = num.intValue();
            builder5.mAttachmentImageUrls = new String[0];
            builder5.mStickerPreviewWidth = num.intValue();
            builder5.mStickerPreviewHeight = num.intValue();
            arrayList.add(builder5.build());
            i5++;
        } while (i5 < 12);
        FBMessengerMessage.Builder builder6 = new FBMessengerMessage.Builder();
        StringBuilder sb6 = new StringBuilder();
        sb6.append("message_");
        sb6.append(12);
        sb6.append("_");
        sb6.append(l);
        sb6.append(i);
        builder6.mMessageId = sb6.toString();
        builder6.mText = AnonymousClass006.A08(obj, " Yo here's a really really long message from 456. It's a multi-line message. Maybe two or three lines. Testing testing testing. ", 12);
        builder6.mSenderId = 456L;
        builder6.mSenderName = "user_456";
        builder6.mSenderProfilePictureUrl = sMockProfilePicUri;
        builder6.mTimestampMs = Long.valueOf(((long) 12) + BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME + 1);
        builder6.mIsAdminMessage = false;
        builder6.mAttachmentType = num.intValue();
        builder6.mAttachmentImageUrls = new String[0];
        builder6.mStickerPreviewWidth = num.intValue();
        builder6.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder6.build());
        for (int i6 = 13; i6 < 15; i6++) {
            FBMessengerMessage.Builder builder7 = new FBMessengerMessage.Builder();
            StringBuilder sb7 = new StringBuilder();
            sb7.append("message_");
            sb7.append(i6);
            sb7.append("_");
            sb7.append(l);
            sb7.append(i);
            builder7.mMessageId = sb7.toString();
            builder7.mText = AnonymousClass006.A05(obj, i, " Yo here's a message from 456. ", i6);
            builder7.mSenderId = 456L;
            builder7.mSenderName = "user_456";
            builder7.mSenderProfilePictureUrl = sMockProfilePicUri;
            builder7.mTimestampMs = Long.valueOf(((long) i6) + (BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 2) + 2);
            builder7.mIsAdminMessage = false;
            builder7.mAttachmentType = num.intValue();
            builder7.mAttachmentImageUrls = new String[0];
            builder7.mStickerPreviewWidth = num.intValue();
            builder7.mStickerPreviewHeight = num.intValue();
            arrayList.add(builder7.build());
        }
        FBMessengerMessage.Builder builder8 = new FBMessengerMessage.Builder();
        StringBuilder sb8 = new StringBuilder();
        sb8.append("message_");
        sb8.append(15);
        sb8.append("_");
        sb8.append(l);
        sb8.append(i);
        builder8.mMessageId = sb8.toString();
        builder8.mText = AnonymousClass006.A08(obj, " Yo here's a really really long message from 789.  It's a multi-line message. Maybe two or three lines.  Testing testing testing. ", 15);
        builder8.mSenderId = 789L;
        builder8.mSenderName = "user_789";
        builder8.mSenderProfilePictureUrl = sMockProfilePicUri;
        builder8.mTimestampMs = Long.valueOf(((long) 15) + (BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 2) + 2);
        builder8.mIsAdminMessage = false;
        builder8.mAttachmentType = num.intValue();
        builder8.mAttachmentImageUrls = new String[0];
        builder8.mStickerPreviewWidth = num.intValue();
        builder8.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder8.build());
        for (int i7 = 16; i7 < 18; i7++) {
            FBMessengerMessage.Builder builder9 = new FBMessengerMessage.Builder();
            StringBuilder sb9 = new StringBuilder();
            sb9.append("message_");
            sb9.append(i7);
            sb9.append("_");
            sb9.append(l);
            sb9.append(i);
            builder9.mMessageId = sb9.toString();
            StringBuilder sb10 = new StringBuilder();
            sb10.append(obj);
            sb10.append(i);
            sb10.append(new String(Character.toChars(128587)));
            sb10.append(" Yo here's a message from 789. ");
            sb10.append(i7);
            builder9.mText = sb10.toString();
            builder9.mSenderId = 789L;
            builder9.mSenderName = "user_789";
            builder9.mSenderProfilePictureUrl = sMockProfilePicUri;
            builder9.mTimestampMs = Long.valueOf(((long) i7) + (BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 3) + 3);
            builder9.mIsAdminMessage = false;
            builder9.mAttachmentType = num.intValue();
            builder9.mAttachmentImageUrls = new String[0];
            builder9.mStickerPreviewWidth = num.intValue();
            builder9.mStickerPreviewHeight = num.intValue();
            arrayList.add(builder9.build());
        }
        FBMessengerMessage.Builder builder10 = new FBMessengerMessage.Builder();
        StringBuilder sb11 = new StringBuilder("message_18_");
        sb11.append(l);
        sb11.append(i);
        builder10.mMessageId = sb11.toString();
        builder10.mText = "";
        builder10.mSenderId = 789L;
        builder10.mSenderName = "user_789";
        builder10.mSenderProfilePictureUrl = sMockProfilePicUri;
        builder10.mTimestampMs = Long.valueOf((BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 3) + 18 + 3);
        builder10.mIsAdminMessage = false;
        builder10.mAttachmentType = Integer.valueOf(MessengerMessage.AttachmentType.IMAGE.ordinal()).intValue();
        builder10.mAttachmentImageUrls = new String[0];
        builder10.mStickerPreviewWidth = num.intValue();
        builder10.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder10.build());
        FBMessengerMessage.Builder builder11 = new FBMessengerMessage.Builder();
        StringBuilder sb12 = new StringBuilder("message_19_");
        sb12.append(l);
        sb12.append(i);
        builder11.mMessageId = sb12.toString();
        builder11.mText = "Jordan changed the group photo.";
        builder11.mSenderId = 111L;
        builder11.mSenderName = "";
        builder11.mSenderProfilePictureUrl = "";
        builder11.mTimestampMs = Long.valueOf((BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 3) + 19 + 3);
        builder11.mIsAdminMessage = true;
        builder11.mAttachmentType = num.intValue();
        builder11.mAttachmentImageUrls = new String[0];
        builder11.mStickerPreviewWidth = num.intValue();
        builder11.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder11.build());
        FBMessengerMessage.Builder builder12 = new FBMessengerMessage.Builder();
        StringBuilder sb13 = new StringBuilder("message_20_");
        sb13.append(l);
        sb13.append(i);
        builder12.mMessageId = sb13.toString();
        builder12.mText = "You opened this conversation through an ad. When you reply, Jasper's Market will see your public info and which ad you clicked.\n";
        builder12.mSenderId = 111L;
        builder12.mSenderName = "";
        builder12.mSenderProfilePictureUrl = "";
        builder12.mTimestampMs = Long.valueOf((BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 3) + 20 + 3);
        builder12.mIsAdminMessage = true;
        builder12.mAttachmentType = num.intValue();
        builder12.mAttachmentImageUrls = new String[0];
        builder12.mStickerPreviewWidth = num.intValue();
        builder12.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder12.build());
        FBMessengerMessage.Builder builder13 = new FBMessengerMessage.Builder();
        StringBuilder sb14 = new StringBuilder("message_21_");
        sb14.append(l);
        sb14.append(i);
        builder13.mMessageId = sb14.toString();
        builder13.mText = "Jordan left the group.";
        builder13.mSenderId = 111L;
        builder13.mSenderName = "";
        builder13.mSenderProfilePictureUrl = "";
        builder13.mTimestampMs = Long.valueOf((BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 3) + 21 + 3);
        builder13.mIsAdminMessage = true;
        builder13.mAttachmentType = num.intValue();
        builder13.mAttachmentImageUrls = new String[0];
        builder13.mStickerPreviewWidth = num.intValue();
        builder13.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder13.build());
        FBMessengerMessage.Builder builder14 = new FBMessengerMessage.Builder();
        StringBuilder sb15 = new StringBuilder("message_22_");
        sb15.append(l);
        sb15.append(i);
        builder14.mMessageId = sb15.toString();
        builder14.mText = AnonymousClass006.A05(obj, i, " Yo here's a message from 789. ", 22);
        builder14.mSenderId = 789L;
        builder14.mSenderName = "user_789";
        builder14.mSenderProfilePictureUrl = sMockProfilePicUri;
        builder14.mTimestampMs = Long.valueOf((BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 3) + 22 + 3);
        builder14.mIsAdminMessage = false;
        builder14.mAttachmentType = num.intValue();
        builder14.mAttachmentImageUrls = new String[0];
        builder14.mStickerPreviewWidth = num.intValue();
        builder14.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder14.build());
        FBMessengerMessage.Builder builder15 = new FBMessengerMessage.Builder();
        StringBuilder sb16 = new StringBuilder("message_23_");
        sb16.append(l);
        sb16.append(i);
        builder15.mMessageId = sb16.toString();
        builder15.mText = "";
        builder15.mSenderId = 789L;
        builder15.mSenderName = "user_789";
        builder15.mSenderProfilePictureUrl = sMockProfilePicUri;
        builder15.mTimestampMs = Long.valueOf((BaseMessageBubbleViewHolder.MESSAGE_GROUPING_OFFSET_TIME * 3) + 23 + 3);
        builder15.mIsAdminMessage = false;
        builder15.mAttachmentType = Integer.valueOf(MessengerMessage.AttachmentType.IMAGE.ordinal()).intValue();
        builder15.mAttachmentImageUrls = new String[0];
        builder15.mStickerPreviewWidth = num.intValue();
        builder15.mStickerPreviewHeight = num.intValue();
        arrayList.add(builder15.build());
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static List<MessengerThread> getMockThreadData(int i) {
        ArrayList arrayList = new ArrayList();
        FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
        builder.mThreadKey = Long.valueOf((long) i);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(" Best friends");
        builder.mThreadName = sb.toString();
        builder.mLastActivityTimestampMs = 0L;
        builder.mSnippet = "hey guys whats going on";
        builder.mIsUnread = true;
        builder.mIsMuted = false;
        arrayList.add(builder.build());
        FBMessengerThread.Builder builder2 = new FBMessengerThread.Builder();
        builder2.mThreadKey = Long.valueOf((long) (i + 1));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i);
        sb2.append(" Blocked User");
        builder2.mThreadName = sb2.toString();
        builder2.mLastActivityTimestampMs = 0L;
        builder2.mSnippet = "check this meeme";
        builder2.mIsUnread = true;
        builder2.mIsMuted = false;
        arrayList.add(builder2.build());
        FBMessengerThread.Builder builder3 = new FBMessengerThread.Builder();
        builder3.mThreadKey = Long.valueOf((long) (i + 2));
        StringBuilder sb3 = new StringBuilder();
        sb3.append(i);
        sb3.append(" Blocked user in group");
        builder3.mThreadName = sb3.toString();
        builder3.mLastActivityTimestampMs = 0L;
        builder3.mSnippet = "studying yo";
        builder3.mIsUnread = false;
        builder3.mIsMuted = false;
        arrayList.add(builder3.build());
        FBMessengerThread.Builder builder4 = new FBMessengerThread.Builder();
        builder4.mThreadKey = Long.valueOf((long) (i + 3));
        StringBuilder sb4 = new StringBuilder();
        sb4.append(i);
        sb4.append("Online friends");
        builder4.mThreadName = sb4.toString();
        builder4.mLastActivityTimestampMs = 0L;
        builder4.mSnippet = AnonymousClass006.A07(new String(Character.toChars(128545)), "playiinga game");
        builder4.mIsUnread = false;
        builder4.mIsMuted = false;
        arrayList.add(builder4.build());
        FBMessengerThread.Builder builder5 = new FBMessengerThread.Builder();
        builder5.mThreadKey = Long.valueOf((long) (i + 4));
        StringBuilder sb5 = new StringBuilder();
        sb5.append(i);
        sb5.append("gamer friends");
        builder5.mThreadName = sb5.toString();
        builder5.mLastActivityTimestampMs = 0L;
        builder5.mSnippet = "hanging out";
        builder5.mIsUnread = true;
        builder5.mIsMuted = false;
        arrayList.add(builder5.build());
        return arrayList;
    }

    private void updateThreadListener() {
        if (this.mThreadListener != null) {
            markThreadAsRead(this.mCurrentThread);
            this.mThreadListener.onThreadUpdate(this.mCurrentThread, this.mMessageList);
        }
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public MessengerThread getCurrentThread() {
        return this.mCurrentThread;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public List<MessengerMessage> getMessages() {
        return this.mMessageList;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public List<MessengerParticipant> getThreadParticipants() {
        if (this.mCurrentThread.getThreadKey() == 1) {
            return this.mOneOnOneBlockedThreadParticipants;
        }
        if (this.mCurrentThread.getThreadKey() == 2) {
            return this.mGroupThreadContainingBlockedParticipants;
        }
        return this.mThreadParticipants;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public List<MessengerThread> getThreads() {
        return this.mThreadList;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public MessengerAPIType getType() {
        return MessengerAPIType.TEST;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void initialize() {
        if (sMockProfilePicUri == null) {
            sMockProfilePicUri = getMockProfilePicUri(this.mContext);
        }
        List<MessengerThread> mockThreadData = getMockThreadData(0);
        this.mThreadList = mockThreadData;
        MessengerThread messengerThread = mockThreadData.get(0);
        this.mCurrentThread = messengerThread;
        this.mMessageList = getMockMessagesData(Long.valueOf(messengerThread.getThreadKey()), 0);
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void markThreadAsRead(MessengerThread messengerThread) {
        FBMessengerThread.Builder builder = new FBMessengerThread.Builder();
        builder.mThreadKey = Long.valueOf(messengerThread.getThreadKey());
        builder.mThreadName = messengerThread.getThreadName();
        builder.mLastActivityTimestampMs = Long.valueOf(messengerThread.getLastActivityTimestampMs());
        builder.mSnippet = messengerThread.getSnippet();
        builder.mThreadPictureUrl = messengerThread.getThreadPictureUrl();
        builder.mParticipantProfilePictureUrlsForThreadPicture = messengerThread.getParticipantProfilePictureUrlsForThreadPicture();
        builder.mIsUnread = false;
        builder.mIsMuted = messengerThread.isMuted();
        FBMessengerThread build = builder.build();
        List<MessengerThread> list = (List) this.mThreadList.stream().map(new Function(build) {
            /* class com.oculus.panelapp.messenger.api.$$Lambda$TestMessengerAPI$MWZ6xasUuhJJepaCk1uTmAZpCI2 */
            public final /* synthetic */ MessengerThread f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TestMessengerAPI.lambda$markThreadAsRead$0(MessengerThread.this, this.f$1, (MessengerThread) obj);
            }
        }).collect(Collectors.toList());
        this.mThreadList = list;
        this.mCurrentThread = build;
        this.mMailboxListener.onMailboxUpdate(list);
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void updateCurrentThread(long j, @Nullable MessengerActionCallback messengerActionCallback) {
        for (MessengerThread messengerThread : this.mThreadList) {
            if (messengerThread.getThreadKey() == j) {
                this.mCurrentThread = messengerThread;
            }
        }
        this.mMessageList = getMockMessagesData(Long.valueOf(j), 0);
        if (this.mThreadListener != null) {
            markThreadAsRead(this.mCurrentThread);
            this.mThreadListener.onThreadUpdate(this.mCurrentThread, this.mMessageList);
        }
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void updateThreadCount(int i) {
        ArrayList arrayList = new ArrayList(this.mThreadList);
        arrayList.addAll(getMockThreadData(i));
        this.mThreadList = arrayList;
        this.mMailboxListener.onMailboxUpdate(arrayList);
    }

    public TestMessengerAPI(Context context) {
        this.mContext = context;
    }

    public static String getMockProfilePicUri(Context context) {
        Resources resources = context.getResources();
        return String.valueOf(new Uri.Builder().scheme("android.resource").authority(resources.getResourcePackageName(R.drawable.anytime_tablet_messenger_sample_profile_photo)).appendPath(resources.getResourceTypeName(R.drawable.anytime_tablet_messenger_sample_profile_photo)).appendPath(resources.getResourceEntryName(R.drawable.anytime_tablet_messenger_sample_profile_photo)).build());
    }

    public static /* synthetic */ MessengerThread lambda$markThreadAsRead$0(MessengerThread messengerThread, MessengerThread messengerThread2, MessengerThread messengerThread3) {
        if (messengerThread3.getThreadKey() == messengerThread.getThreadKey()) {
            return messengerThread2;
        }
        return messengerThread3;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void updateMessageCount(int i) {
        List<MessengerMessage> mockMessagesData = getMockMessagesData(Long.valueOf(getCurrentThread().getThreadKey()), i);
        mockMessagesData.addAll(this.mMessageList);
        this.mMessageList = mockMessagesData;
        updateThreadListener();
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void setMailboxListener(MailboxListener mailboxListener) {
        this.mMailboxListener = mailboxListener;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void setThreadListener(ThreadListener threadListener) {
        this.mThreadListener = threadListener;
    }

    @Override // com.oculus.panelapp.messenger.api.IMessengerAPI
    public void sendMessage(String str, @Nullable MessengerActionCallback messengerActionCallback, @Nullable MessengerActionCallback messengerActionCallback2) {
        List<MessengerMessage> list = this.mMessageList;
        FBMessengerMessage.Builder builder = new FBMessengerMessage.Builder();
        builder.mMessageId = "new_message";
        builder.mText = str;
        builder.mSenderId = 789L;
        builder.mSenderName = "user_789";
        builder.mSenderProfilePictureUrl = ServiceContract.FOLLOW_UP_LINK;
        builder.mTimestampMs = 0L;
        Integer num = 0;
        builder.mIsAdminMessage = false;
        builder.mAttachmentType = num.intValue();
        builder.mAttachmentImageUrls = new String[0];
        builder.mStickerPreviewWidth = num.intValue();
        builder.mStickerPreviewHeight = num.intValue();
        list.add(builder.build());
        updateThreadListener();
    }
}
