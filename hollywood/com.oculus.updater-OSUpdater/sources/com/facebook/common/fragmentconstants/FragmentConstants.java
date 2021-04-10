package com.facebook.common.fragmentconstants;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FragmentConstants {
    public static final String[] DEBUGGABLE_CONTENT_FRAGMENT_TAGS = {"chromeless:content:fragment:tag", "ufi:popover:content:fragment:tag", "transliteration:fragment:tag"};
    public static int LAST_TYPE = 930;
    public static String TREEHOUSE_BASIC_MODEL = "treehouse_basic_model";
    public static String TREEHOUSE_NAME_HINT = "treehouse_name_hint";

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContentFragmentType {
    }
}
