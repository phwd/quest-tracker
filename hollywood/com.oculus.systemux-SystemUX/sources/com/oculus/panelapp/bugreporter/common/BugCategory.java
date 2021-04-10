package com.oculus.panelapp.bugreporter.common;

import android.content.Context;
import com.oculus.panelapp.bugreporter.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.PackageUtil;
import java.util.ArrayList;

public enum BugCategory {
    SELECT_CATEGORY(-1, R.string.bug_report_category_select),
    THIRD_PARTY(1581534861914814L, R.string.bug_report_category_third_party),
    HOME(1645131112370468L, R.string.bug_report_category_home),
    PROFILE(533287180610100L, R.string.bug_report_category_profile),
    SEARCH(291338521333692L, R.string.bug_report_category_search),
    STORE(480777592860512L, R.string.bug_report_category_store),
    UNIVERSAL_MENU(408489493442496L, R.string.bug_report_category_universal_menu),
    LIBRARY(806991799798308L, R.string.bug_report_category_library),
    FRIENDS(213046719727669L, R.string.bug_report_category_friends),
    MESSENGER(696455407747455L, R.string.bug_report_category_messenger),
    SHARING(2425648914353612L, R.string.bug_report_category_sharing),
    CAMERA_ROLL(335846201102873L, R.string.bug_report_category_camera_roll),
    NOTIFICATIONS(1124679407891870L, R.string.bug_report_category_notifications),
    SETTINGS(114079779229687L, R.string.bug_report_category_settings),
    GUARDIAN(655389535045016L, R.string.bug_report_category_guardian),
    AUDIO(548722229320297L, R.string.bug_report_category_audio),
    VOICE_COMMANDS(1907936829475117L, R.string.bug_report_category_voice_commands),
    VOICE_DICTATION(685391025660623L, R.string.bug_report_category_voice_dictation),
    BROWSER(493182581368648L, R.string.bug_report_category_browser),
    TV(191123448754281L, R.string.bug_report_category_tv),
    VENUES(1870816053237558L, R.string.bug_report_category_venues),
    GALLERY(776289276181812L, R.string.bug_report_category_gallery),
    MOVE(719029768649341L, R.string.bug_report_category_fitness),
    SCOREBOARDS(321565932595351L, R.string.bug_report_category_scoreboards),
    LINK(879135026166919L, R.string.bug_report_category_oculus_link),
    LOCALIZATION(879135026166919L, R.string.bug_report_category_content_localization, true),
    TIMER(3633617010054369L, R.string.bug_report_category_timer),
    OTHER(793062804509072L, R.string.bug_report_category_other);
    
    private final long mId;
    private final boolean mInternalOnly;
    private final int mLabelId;

    private BugCategory(long j, int i) {
        this(j, i, false);
    }

    private BugCategory(long j, int i, boolean z) {
        this.mId = j;
        this.mLabelId = i;
        this.mInternalOnly = z;
    }

    public long getId() {
        return this.mId;
    }

    public int getLabelId() {
        return this.mLabelId;
    }

    public static ArrayList<BugCategory> getEnabledValues(boolean z, Context context) {
        ArrayList<BugCategory> arrayList = new ArrayList<>();
        BugCategory[] values = values();
        for (BugCategory bugCategory : values) {
            if ((!bugCategory.mInternalOnly || z) && (bugCategory != TIMER || HorizonUtil.isApplicationInstalledByPackageName(context, PackageUtil.PACKAGE_NAME_TIMER))) {
                arrayList.add(bugCategory);
            }
        }
        return arrayList;
    }

    public static BugCategory fromSystemUX(SystemUXRoute systemUXRoute) {
        switch (systemUXRoute) {
            case DEFAULT_BROWSER:
                return BROWSER;
            case FRIENDS:
                return FRIENDS;
            case HOME:
                return HOME;
            case LIBRARY:
                return LIBRARY;
            case PROFILE:
                return PROFILE;
            case SEARCH:
                return SEARCH;
            case STORE:
                return STORE;
            case TV:
                return TV;
            default:
                return SELECT_CATEGORY;
        }
    }
}
