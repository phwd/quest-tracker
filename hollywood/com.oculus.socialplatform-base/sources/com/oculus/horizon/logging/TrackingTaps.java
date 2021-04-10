package com.oculus.horizon.logging;

import java.util.Locale;

public class TrackingTaps {

    public enum Tap {
        BROWSE_ALL_GENRE,
        BROWSE_ALL_PRICE,
        BROWSE_ALL_SORT,
        FEED_STORE,
        LIBRARY_VIEW_DETAILS,
        LIBRARY_MOVE_ITEM;

        public String toString() {
            return super.toString().toLowerCase(Locale.US);
        }
    }
}
