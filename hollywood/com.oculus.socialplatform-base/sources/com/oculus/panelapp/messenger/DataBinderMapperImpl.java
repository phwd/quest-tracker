package com.oculus.panelapp.messenger;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerAdminMessageItemV2BindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadItemBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadParticipantItemBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerDraftThreadParticipantTextInputBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerGroupThreadContainingBlockedBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerLeaveChatConfirmationBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2BindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemImageV2BindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemTextV2BindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsItemBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsPopupBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsSummaryItemBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerOneOnOneBlockedThreadBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerTabletOfflineViewBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerThreadItemV2BindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerThreadListNullStateBindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerViewV2BindingImpl;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerXmaItemV2BindingImpl;
import com.oculus.panelapp.messenger.databinding.MessageReactionsSummaryPopupBindingImpl;
import com.oculus.panelapp.messenger.databinding.MessengerFacepileBindingImpl;
import com.oculus.panelapp.messenger.databinding.MessengerReactionsPillBindingImpl;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERADMINMESSAGEITEMV2 = 1;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERDRAFTTHREADITEM = 2;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERDRAFTTHREADPARTICIPANTITEM = 3;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERDRAFTTHREADPARTICIPANTTEXTINPUT = 4;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERGROUPTHREADCONTAININGBLOCKED = 5;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERLEAVECHATCONFIRMATION = 6;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERMESSAGEITEMCONTAINERV2 = 7;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERMESSAGEITEMIMAGEV2 = 8;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERMESSAGEITEMTEXTV2 = 9;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERMESSAGEREACTIONSITEM = 10;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERMESSAGEREACTIONSPOPUP = 11;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERMESSAGEREACTIONSSUMMARYITEM = 12;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERONEONONEBLOCKEDTHREAD = 13;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERTABLETOFFLINEVIEW = 14;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERTHREADITEMV2 = 15;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERTHREADLISTNULLSTATE = 16;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERVIEWV2 = 17;
    public static final int LAYOUT_ANYTIMETABLETMESSENGERXMAITEMV2 = 18;
    public static final int LAYOUT_MESSAGEREACTIONSSUMMARYPOPUP = 19;
    public static final int LAYOUT_MESSENGERFACEPILE = 20;
    public static final int LAYOUT_MESSENGERREACTIONSPILL = 21;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(85);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "activeIndicator");
            sparseArray.put(2, "buttonText");
            sparseArray.put(3, "sideNavItem");
            sparseArray.put(4, "inactiveDrawable");
            sparseArray.put(5, "ctaText");
            sparseArray.put(6, "label");
            sparseArray.put(7, "badgeCount");
            sparseArray.put(8, "title");
            sparseArray.put(9, "isActive");
            sparseArray.put(10, "enabled");
            sparseArray.put(11, "showProgressPercentage");
            sparseArray.put(12, "activeDrawable");
            sparseArray.put(13, NotificationCompat$WearableExtender.KEY_BACKGROUND);
            sparseArray.put(14, "subtitle");
            sparseArray.put(15, "header");
            sparseArray.put(16, DialogProgressIndicator.DIALOG_PROGRESS_INDICATOR_PROGRESS_KEY);
            sparseArray.put(17, "viewModel");
            sparseArray.put(18, "titleIcon");
            sparseArray.put(19, "splash");
            sparseArray.put(20, "settingsVisibility");
            sparseArray.put(21, "chatVisibility");
            sparseArray.put(22, "peopleVisibility");
            sparseArray.put(23, "socialTabletSideNavViewModel");
            sparseArray.put(24, "icon");
            sparseArray.put(25, "progressDrawable");
            sparseArray.put(26, "text");
            sparseArray.put(27, "messageListVisibility");
            sparseArray.put(28, "reactionsTotalCount");
            sparseArray.put(29, "groupThreadBlockedViewManageBlockButtonText");
            sparseArray.put(30, "reactionsRemainingCount");
            sparseArray.put(31, DialogListItem.DIALOG_LIST_ITEM_TITLE_TEXT_KEY);
            sparseArray.put(32, "groupThreadBlockedViewTitleText");
            sparseArray.put(33, "isPartyButtonEnabled");
            sparseArray.put(34, "xmaBubbleViewModel");
            sparseArray.put(35, "firstReactionEmoji");
            sparseArray.put(36, "shouldShowFavicon");
            sparseArray.put(37, "isFacepileThreadIcon");
            sparseArray.put(38, "reportButtonVisibility");
            sparseArray.put(39, "messageDisplayText");
            sparseArray.put(40, "displayText");
            sparseArray.put(41, "secondReaction");
            sparseArray.put(42, "draftThreadHeaderVisibility");
            sparseArray.put(43, "attachmentSubtitle");
            sparseArray.put(44, "composerExpandedInputVisibility");
            sparseArray.put(45, "reactionTotalCountVisibility");
            sparseArray.put(46, "isPartyLinkLoading");
            sparseArray.put(47, "partyButtonText");
            sparseArray.put(48, "firstReactionUsername");
            sparseArray.put(49, "messengerViewModel");
            sparseArray.put(50, "composerBlockedViewVisibility");
            sparseArray.put(51, "composerBlockedViewContent");
            sparseArray.put(52, "currentThreadHeaderVisibility");
            sparseArray.put(53, "attachmentTitle");
            sparseArray.put(54, "shouldShowSenderInfo");
            sparseArray.put(55, "partyButtonDrawable");
            sparseArray.put(56, "thirdReactionEmoji");
            sparseArray.put(57, "shouldShowDisplayText");
            sparseArray.put(58, "secondReactionUsername");
            sparseArray.put(59, "composerCollapsedInputVisibility");
            sparseArray.put(60, "groupThreadBlockedViewBodyText");
            sparseArray.put(61, "currentThreadTitle");
            sparseArray.put(62, "displayTextTypeface");
            sparseArray.put(63, "reactionUsername");
            sparseArray.put(64, "reactionRemainingCountVisibility");
            sparseArray.put(65, "groupThreadBlockedViewManageBlockButtonVisibility");
            sparseArray.put(66, "thirdReaction");
            sparseArray.put(67, "contextMenuVisibility");
            sparseArray.put(68, "displayTextColor");
            sparseArray.put(69, "audioInputButtonVisibility");
            sparseArray.put(70, "reaction");
            sparseArray.put(71, "tabletTitleText");
            sparseArray.put(72, "thread");
            sparseArray.put(73, "message");
            sparseArray.put(74, "leftbarViewVisibility");
            sparseArray.put(75, "thirdReactionUsername");
            sparseArray.put(76, "firstReaction");
            sparseArray.put(77, "composeBarType");
            sparseArray.put(78, "composerVisibility");
            sparseArray.put(79, "secondReactionVisibility");
            sparseArray.put(80, "thirdReactionVisibility");
            sparseArray.put(81, "partyButtonVisibility");
            sparseArray.put(82, "secondReactionEmoji");
            sparseArray.put(83, "reactionEmoji");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(21);
            sKeys = hashMap;
            hashMap.put("layout/anytime_tablet_messenger_admin_message_item_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_admin_message_item_v2));
            HashMap<String, Integer> hashMap2 = sKeys;
            hashMap2.put("layout/anytime_tablet_messenger_draft_thread_item_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_draft_thread_item));
            hashMap2.put("layout/anytime_tablet_messenger_draft_thread_participant_item_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_draft_thread_participant_item));
            hashMap2.put("layout/anytime_tablet_messenger_draft_thread_participant_text_input_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_draft_thread_participant_text_input));
            hashMap2.put("layout/anytime_tablet_messenger_group_thread_containing_blocked_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_group_thread_containing_blocked));
            hashMap2.put("layout/anytime_tablet_messenger_leave_chat_confirmation_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_leave_chat_confirmation));
            hashMap2.put("layout/anytime_tablet_messenger_message_item_container_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_message_item_container_v2));
            hashMap2.put("layout/anytime_tablet_messenger_message_item_image_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_message_item_image_v2));
            hashMap2.put("layout/anytime_tablet_messenger_message_item_text_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_message_item_text_v2));
            hashMap2.put("layout/anytime_tablet_messenger_message_reactions_item_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_message_reactions_item));
            hashMap2.put("layout/anytime_tablet_messenger_message_reactions_popup_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_message_reactions_popup));
            hashMap2.put("layout/anytime_tablet_messenger_message_reactions_summary_item_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_message_reactions_summary_item));
            hashMap2.put("layout/anytime_tablet_messenger_one_on_one_blocked_thread_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_one_on_one_blocked_thread));
            hashMap2.put("layout/anytime_tablet_messenger_tablet_offline_view_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_tablet_offline_view));
            hashMap2.put("layout/anytime_tablet_messenger_thread_item_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_thread_item_v2));
            hashMap2.put("layout/anytime_tablet_messenger_thread_list_null_state_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_thread_list_null_state));
            hashMap2.put("layout/anytime_tablet_messenger_view_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_view_v2));
            hashMap2.put("layout/anytime_tablet_messenger_xma_item_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_messenger_xma_item_v2));
            hashMap2.put("layout/message_reactions_summary_popup_0", Integer.valueOf((int) R.layout.message_reactions_summary_popup));
            hashMap2.put("layout/messenger_facepile_0", Integer.valueOf((int) R.layout.messenger_facepile));
            hashMap2.put("layout/messenger_reactions_pill_0", Integer.valueOf((int) R.layout.messenger_reactions_pill));
        }
    }

    @Override // X.AnonymousClass1uS
    public List<AnonymousClass1uS> collectDependencies() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new AnonymousClass2Q8());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.socialtablet.DataBinderMapperImpl());
        arrayList.add(new com.oculus.tablet.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // X.AnonymousClass1uS
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(21);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.anytime_tablet_messenger_admin_message_item_v2, 1);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_draft_thread_item, 2);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_draft_thread_participant_item, 3);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_draft_thread_participant_text_input, 4);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_group_thread_containing_blocked, 5);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_leave_chat_confirmation, 6);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_message_item_container_v2, 7);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_message_item_image_v2, 8);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_message_item_text_v2, 9);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_message_reactions_item, 10);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_message_reactions_popup, 11);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_message_reactions_summary_item, 12);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_one_on_one_blocked_thread, 13);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_tablet_offline_view, 14);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_thread_item_v2, 15);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_thread_list_null_state, 16);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_view_v2, 17);
        sparseIntArray.put(R.layout.anytime_tablet_messenger_xma_item_v2, 18);
        sparseIntArray.put(R.layout.message_reactions_summary_popup, 19);
        sparseIntArray.put(R.layout.messenger_facepile, 20);
        sparseIntArray.put(R.layout.messenger_reactions_pill, 21);
    }

    @Override // X.AnonymousClass1uS
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // X.AnonymousClass1uS
    public AnonymousClass1uW getDataBinder(AbstractC003408r r4, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/anytime_tablet_messenger_admin_message_item_v2_0".equals(tag)) {
                        return new AnytimeTabletMessengerAdminMessageItemV2BindingImpl(r4, view);
                    }
                    StringBuilder sb = new StringBuilder("The tag for anytime_tablet_messenger_admin_message_item_v2 is invalid. Received: ");
                    sb.append(tag);
                    throw new IllegalArgumentException(sb.toString());
                case 2:
                    if ("layout/anytime_tablet_messenger_draft_thread_item_0".equals(tag)) {
                        return new AnytimeTabletMessengerDraftThreadItemBindingImpl(r4, view);
                    }
                    StringBuilder sb2 = new StringBuilder("The tag for anytime_tablet_messenger_draft_thread_item is invalid. Received: ");
                    sb2.append(tag);
                    throw new IllegalArgumentException(sb2.toString());
                case 3:
                    if ("layout/anytime_tablet_messenger_draft_thread_participant_item_0".equals(tag)) {
                        return new AnytimeTabletMessengerDraftThreadParticipantItemBindingImpl(r4, view);
                    }
                    StringBuilder sb3 = new StringBuilder("The tag for anytime_tablet_messenger_draft_thread_participant_item is invalid. Received: ");
                    sb3.append(tag);
                    throw new IllegalArgumentException(sb3.toString());
                case 4:
                    if ("layout/anytime_tablet_messenger_draft_thread_participant_text_input_0".equals(tag)) {
                        return new AnytimeTabletMessengerDraftThreadParticipantTextInputBindingImpl(r4, view);
                    }
                    StringBuilder sb4 = new StringBuilder("The tag for anytime_tablet_messenger_draft_thread_participant_text_input is invalid. Received: ");
                    sb4.append(tag);
                    throw new IllegalArgumentException(sb4.toString());
                case 5:
                    if ("layout/anytime_tablet_messenger_group_thread_containing_blocked_0".equals(tag)) {
                        return new AnytimeTabletMessengerGroupThreadContainingBlockedBindingImpl(r4, view);
                    }
                    StringBuilder sb5 = new StringBuilder("The tag for anytime_tablet_messenger_group_thread_containing_blocked is invalid. Received: ");
                    sb5.append(tag);
                    throw new IllegalArgumentException(sb5.toString());
                case 6:
                    if ("layout/anytime_tablet_messenger_leave_chat_confirmation_0".equals(tag)) {
                        return new AnytimeTabletMessengerLeaveChatConfirmationBindingImpl(r4, view);
                    }
                    StringBuilder sb6 = new StringBuilder("The tag for anytime_tablet_messenger_leave_chat_confirmation is invalid. Received: ");
                    sb6.append(tag);
                    throw new IllegalArgumentException(sb6.toString());
                case 7:
                    if ("layout/anytime_tablet_messenger_message_item_container_v2_0".equals(tag)) {
                        return new AnytimeTabletMessengerMessageItemContainerV2BindingImpl(r4, view);
                    }
                    StringBuilder sb7 = new StringBuilder("The tag for anytime_tablet_messenger_message_item_container_v2 is invalid. Received: ");
                    sb7.append(tag);
                    throw new IllegalArgumentException(sb7.toString());
                case 8:
                    if ("layout/anytime_tablet_messenger_message_item_image_v2_0".equals(tag)) {
                        return new AnytimeTabletMessengerMessageItemImageV2BindingImpl(r4, view);
                    }
                    StringBuilder sb8 = new StringBuilder("The tag for anytime_tablet_messenger_message_item_image_v2 is invalid. Received: ");
                    sb8.append(tag);
                    throw new IllegalArgumentException(sb8.toString());
                case 9:
                    if ("layout/anytime_tablet_messenger_message_item_text_v2_0".equals(tag)) {
                        return new AnytimeTabletMessengerMessageItemTextV2BindingImpl(r4, view);
                    }
                    StringBuilder sb9 = new StringBuilder("The tag for anytime_tablet_messenger_message_item_text_v2 is invalid. Received: ");
                    sb9.append(tag);
                    throw new IllegalArgumentException(sb9.toString());
                case 10:
                    if ("layout/anytime_tablet_messenger_message_reactions_item_0".equals(tag)) {
                        return new AnytimeTabletMessengerMessageReactionsItemBindingImpl(r4, view);
                    }
                    StringBuilder sb10 = new StringBuilder("The tag for anytime_tablet_messenger_message_reactions_item is invalid. Received: ");
                    sb10.append(tag);
                    throw new IllegalArgumentException(sb10.toString());
                case 11:
                    if ("layout/anytime_tablet_messenger_message_reactions_popup_0".equals(tag)) {
                        return new AnytimeTabletMessengerMessageReactionsPopupBindingImpl(r4, view);
                    }
                    StringBuilder sb11 = new StringBuilder("The tag for anytime_tablet_messenger_message_reactions_popup is invalid. Received: ");
                    sb11.append(tag);
                    throw new IllegalArgumentException(sb11.toString());
                case 12:
                    if ("layout/anytime_tablet_messenger_message_reactions_summary_item_0".equals(tag)) {
                        return new AnytimeTabletMessengerMessageReactionsSummaryItemBindingImpl(r4, view);
                    }
                    StringBuilder sb12 = new StringBuilder("The tag for anytime_tablet_messenger_message_reactions_summary_item is invalid. Received: ");
                    sb12.append(tag);
                    throw new IllegalArgumentException(sb12.toString());
                case 13:
                    if ("layout/anytime_tablet_messenger_one_on_one_blocked_thread_0".equals(tag)) {
                        return new AnytimeTabletMessengerOneOnOneBlockedThreadBindingImpl(r4, view);
                    }
                    StringBuilder sb13 = new StringBuilder("The tag for anytime_tablet_messenger_one_on_one_blocked_thread is invalid. Received: ");
                    sb13.append(tag);
                    throw new IllegalArgumentException(sb13.toString());
                case 14:
                    if ("layout/anytime_tablet_messenger_tablet_offline_view_0".equals(tag)) {
                        return new AnytimeTabletMessengerTabletOfflineViewBindingImpl(r4, view);
                    }
                    StringBuilder sb14 = new StringBuilder("The tag for anytime_tablet_messenger_tablet_offline_view is invalid. Received: ");
                    sb14.append(tag);
                    throw new IllegalArgumentException(sb14.toString());
                case 15:
                    if ("layout/anytime_tablet_messenger_thread_item_v2_0".equals(tag)) {
                        return new AnytimeTabletMessengerThreadItemV2BindingImpl(r4, view);
                    }
                    StringBuilder sb15 = new StringBuilder("The tag for anytime_tablet_messenger_thread_item_v2 is invalid. Received: ");
                    sb15.append(tag);
                    throw new IllegalArgumentException(sb15.toString());
                case 16:
                    if ("layout/anytime_tablet_messenger_thread_list_null_state_0".equals(tag)) {
                        return new AnytimeTabletMessengerThreadListNullStateBindingImpl(r4, view);
                    }
                    StringBuilder sb16 = new StringBuilder("The tag for anytime_tablet_messenger_thread_list_null_state is invalid. Received: ");
                    sb16.append(tag);
                    throw new IllegalArgumentException(sb16.toString());
                case 17:
                    if ("layout/anytime_tablet_messenger_view_v2_0".equals(tag)) {
                        return new AnytimeTabletMessengerViewV2BindingImpl(r4, view);
                    }
                    StringBuilder sb17 = new StringBuilder("The tag for anytime_tablet_messenger_view_v2 is invalid. Received: ");
                    sb17.append(tag);
                    throw new IllegalArgumentException(sb17.toString());
                case 18:
                    if ("layout/anytime_tablet_messenger_xma_item_v2_0".equals(tag)) {
                        return new AnytimeTabletMessengerXmaItemV2BindingImpl(r4, view);
                    }
                    StringBuilder sb18 = new StringBuilder("The tag for anytime_tablet_messenger_xma_item_v2 is invalid. Received: ");
                    sb18.append(tag);
                    throw new IllegalArgumentException(sb18.toString());
                case 19:
                    if ("layout/message_reactions_summary_popup_0".equals(tag)) {
                        return new MessageReactionsSummaryPopupBindingImpl(r4, view);
                    }
                    StringBuilder sb19 = new StringBuilder("The tag for message_reactions_summary_popup is invalid. Received: ");
                    sb19.append(tag);
                    throw new IllegalArgumentException(sb19.toString());
                case 20:
                    if ("layout/messenger_facepile_0".equals(tag)) {
                        return new MessengerFacepileBindingImpl(r4, view);
                    }
                    StringBuilder sb20 = new StringBuilder("The tag for messenger_facepile is invalid. Received: ");
                    sb20.append(tag);
                    throw new IllegalArgumentException(sb20.toString());
                case 21:
                    if ("layout/messenger_reactions_pill_0".equals(tag)) {
                        return new MessengerReactionsPillBindingImpl(r4, view);
                    }
                    StringBuilder sb21 = new StringBuilder("The tag for messenger_reactions_pill is invalid. Received: ");
                    sb21.append(tag);
                    throw new IllegalArgumentException(sb21.toString());
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    @Override // X.AnonymousClass1uS
    public AnonymousClass1uW getDataBinder(AbstractC003408r r3, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
