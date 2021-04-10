package com.oculus.panelapp.parties;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.panelapp.parties.databinding.InviteToPartyCardBindingImpl;
import com.oculus.panelapp.parties.databinding.InviteToPartyCtaButtonBindingImpl;
import com.oculus.panelapp.parties.databinding.PartiesTabletMainBindingImpl;
import com.oculus.panelapp.parties.databinding.PartiesTabletPartyHeaderBindingImpl;
import com.oculus.panelapp.parties.databinding.PartyTravelFooterBindingImpl;
import com.oculus.panelapp.parties.databinding.PartyUserCardBindingImpl;
import com.oculus.provider.OculusContent;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_INVITETOPARTYCARD = 1;
    public static final int LAYOUT_INVITETOPARTYCTABUTTON = 2;
    public static final int LAYOUT_PARTIESTABLETMAIN = 3;
    public static final int LAYOUT_PARTIESTABLETPARTYHEADER = 4;
    public static final int LAYOUT_PARTYTRAVELFOOTER = 5;
    public static final int LAYOUT_PARTYUSERCARD = 6;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(77);
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
            sparseArray.put(27, "footerSecondaryActionButtonText");
            sparseArray.put(28, "footerAppText");
            sparseArray.put(29, "shouldShowAudioConnected");
            sparseArray.put(30, "shouldShowSubtitleSeparator");
            sparseArray.put(31, "shouldShowCtaSpinner");
            sparseArray.put(32, "descriptionText");
            sparseArray.put(33, "fbActionEnabled");
            sparseArray.put(34, "footerPrimaryActionButtonBackground");
            sparseArray.put(35, "shouldShowLeftCta");
            sparseArray.put(36, "isHoverOverlayShowing");
            sparseArray.put(37, "footerShouldShowChangeDestinationButton");
            sparseArray.put(38, "rightCtaIcon");
            sparseArray.put(39, "shouldShowPartyFooter");
            sparseArray.put(40, "partyTravelFooterViewModel");
            sparseArray.put(41, "shouldShowMuteOverlay");
            sparseArray.put(42, "shouldShowCta");
            sparseArray.put(43, "shouldShowPartyLoading");
            sparseArray.put(44, "footerShouldShowSecondaryActionButton");
            sparseArray.put(45, "footerPrimaryActionButtonText");
            sparseArray.put(46, "mutePartyVolume");
            sparseArray.put(47, "shouldShowPartyDescriptionText");
            sparseArray.put(48, "isLoading");
            sparseArray.put(49, "shouldShowPartyAccessControls");
            sparseArray.put(50, "shouldShowOverflowMenu");
            sparseArray.put(51, "groupLaunchStatusText");
            sparseArray.put(52, "partiesViewModel");
            sparseArray.put(53, "isPartyActive");
            sparseArray.put(54, "party");
            sparseArray.put(55, "footerShouldEnableButtons");
            sparseArray.put(56, "subtitleText");
            sparseArray.put(57, "partySpotsAvailable");
            sparseArray.put(58, "isReady");
            sparseArray.put(59, "partyUserCardViewModel");
            sparseArray.put(60, "hasLinkSharing");
            sparseArray.put(61, "inviteToPartyCardViewModel");
            sparseArray.put(62, "isOnline");
            sparseArray.put(63, "leftCtaIcon");
            sparseArray.put(64, "footerShouldShowDestinationInfo");
            sparseArray.put(65, "footerShouldShowPrimaryActionButton");
            sparseArray.put(66, "shouldShowSpeakingIndicator");
            sparseArray.put(67, "footerShouldShowPrimaryActionButtonSpinner");
            sparseArray.put(68, "footerDestinationText");
            sparseArray.put(69, "shouldShowGroupLaunch");
            sparseArray.put(70, "footerShouldShowSecondaryActionButtonSpinner");
            sparseArray.put(71, "shouldUseActiveTextColor");
            sparseArray.put(72, "ctaIcon");
            sparseArray.put(73, "footerStatusText");
            sparseArray.put(74, OculusContent.Profile.USERNAME);
            sparseArray.put(75, "shouldShowRightCta");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(6);
            sKeys = hashMap;
            hashMap.put("layout/invite_to_party_card_0", Integer.valueOf((int) R.layout.invite_to_party_card));
            HashMap<String, Integer> hashMap2 = sKeys;
            hashMap2.put("layout/invite_to_party_cta_button_0", Integer.valueOf((int) R.layout.invite_to_party_cta_button));
            hashMap2.put("layout/parties_tablet_main_0", Integer.valueOf((int) R.layout.parties_tablet_main));
            hashMap2.put("layout/parties_tablet_party_header_0", Integer.valueOf((int) R.layout.parties_tablet_party_header));
            hashMap2.put("layout/party_travel_footer_0", Integer.valueOf((int) R.layout.party_travel_footer));
            hashMap2.put("layout/party_user_card_0", Integer.valueOf((int) R.layout.party_user_card));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(6);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.invite_to_party_card, 1);
        sparseIntArray.put(R.layout.invite_to_party_cta_button, 2);
        sparseIntArray.put(R.layout.parties_tablet_main, 3);
        sparseIntArray.put(R.layout.parties_tablet_party_header, 4);
        sparseIntArray.put(R.layout.party_travel_footer, 5);
        sparseIntArray.put(R.layout.party_user_card, 6);
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
                    if ("layout/invite_to_party_card_0".equals(tag)) {
                        return new InviteToPartyCardBindingImpl(r4, view);
                    }
                    StringBuilder sb = new StringBuilder("The tag for invite_to_party_card is invalid. Received: ");
                    sb.append(tag);
                    throw new IllegalArgumentException(sb.toString());
                case 2:
                    if ("layout/invite_to_party_cta_button_0".equals(tag)) {
                        return new InviteToPartyCtaButtonBindingImpl(r4, view);
                    }
                    StringBuilder sb2 = new StringBuilder("The tag for invite_to_party_cta_button is invalid. Received: ");
                    sb2.append(tag);
                    throw new IllegalArgumentException(sb2.toString());
                case 3:
                    if ("layout/parties_tablet_main_0".equals(tag)) {
                        return new PartiesTabletMainBindingImpl(r4, view);
                    }
                    StringBuilder sb3 = new StringBuilder("The tag for parties_tablet_main is invalid. Received: ");
                    sb3.append(tag);
                    throw new IllegalArgumentException(sb3.toString());
                case 4:
                    if ("layout/parties_tablet_party_header_0".equals(tag)) {
                        return new PartiesTabletPartyHeaderBindingImpl(r4, view);
                    }
                    StringBuilder sb4 = new StringBuilder("The tag for parties_tablet_party_header is invalid. Received: ");
                    sb4.append(tag);
                    throw new IllegalArgumentException(sb4.toString());
                case 5:
                    if ("layout/party_travel_footer_0".equals(tag)) {
                        return new PartyTravelFooterBindingImpl(r4, view);
                    }
                    StringBuilder sb5 = new StringBuilder("The tag for party_travel_footer is invalid. Received: ");
                    sb5.append(tag);
                    throw new IllegalArgumentException(sb5.toString());
                case 6:
                    if ("layout/party_user_card_0".equals(tag)) {
                        return new PartyUserCardBindingImpl(r4, view);
                    }
                    StringBuilder sb6 = new StringBuilder("The tag for party_user_card is invalid. Received: ");
                    sb6.append(tag);
                    throw new IllegalArgumentException(sb6.toString());
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
