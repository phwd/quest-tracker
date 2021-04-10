package com.facebook.quicklog.identifiers;

public class Comments {
    public static final int ATTACH_COMMENT_COMPOSER = 32964613;
    public static final int CLICK = 32964616;
    public static final int COMMENTS_TTRC = 32964609;
    public static final int COMPOSER_VISIBILITY = 32964618;
    public static final int COMPOSE_COMMENT = 32964612;
    public static final int CONVERSATION_GUIDE = 32964622;
    public static final int DELETE_COMMENT = 32964614;
    public static final int FLYOUT_LAUNCH = 32964620;
    public static final int FRAGMENT_DESTROY = 32964619;
    public static final int FUNNEL_EVENT = 32964617;
    public static final short MODULE_ID = 503;
    public static final int POST_COMMENT = 32964610;
    public static final int POST_COMMENT_RENDER = 32964621;
    public static final int RENDER_FEED_STORY = 32964615;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "COMMENTS_COMMENTS_TTRC";
            case 2:
                return "COMMENTS_POST_COMMENT";
            case 3:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 4:
                return "COMMENTS_COMPOSE_COMMENT";
            case 5:
                return "COMMENTS_ATTACH_COMMENT_COMPOSER";
            case 6:
                return "COMMENTS_DELETE_COMMENT";
            case 7:
                return "COMMENTS_RENDER_FEED_STORY";
            case 8:
                return "COMMENTS_CLICK";
            case 9:
                return "COMMENTS_FUNNEL_EVENT";
            case 10:
                return "COMMENTS_COMPOSER_VISIBILITY";
            case 11:
                return "COMMENTS_FRAGMENT_DESTROY";
            case 12:
                return "COMMENTS_FLYOUT_LAUNCH";
            case 13:
                return "COMMENTS_POST_COMMENT_RENDER";
            case 14:
                return "COMMENTS_CONVERSATION_GUIDE";
        }
    }
}
