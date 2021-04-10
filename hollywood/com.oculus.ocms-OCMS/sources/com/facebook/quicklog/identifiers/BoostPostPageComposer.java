package com.facebook.quicklog.identifiers;

public class BoostPostPageComposer {
    public static final short MODULE_ID = 246;
    public static final int TRANSITION_DISMISSED = 16121858;
    public static final int TRANSITION_DISPLAYED = 16121857;
    public static final int TRANSITION_FAILED = 16121859;
    public static final int TRANSITION_FINISHED = 16121860;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "BOOST_POST_PAGE_COMPOSER_TRANSITION_FINISHED" : "BOOST_POST_PAGE_COMPOSER_TRANSITION_FAILED" : "BOOST_POST_PAGE_COMPOSER_TRANSITION_DISMISSED" : "BOOST_POST_PAGE_COMPOSER_TRANSITION_DISPLAYED";
    }
}
