package android.stats.launcher;

import com.google.protobuf.Internal;

public enum LauncherState implements Internal.EnumLite {
    BACKGROUND(0),
    HOME(1),
    OVERVIEW(2),
    ALLAPPS(3);
    
    public static final int ALLAPPS_VALUE = 3;
    public static final int BACKGROUND_VALUE = 0;
    public static final int HOME_VALUE = 1;
    public static final int OVERVIEW_VALUE = 2;
    private static final Internal.EnumLiteMap<LauncherState> internalValueMap = new Internal.EnumLiteMap<LauncherState>() {
        /* class android.stats.launcher.LauncherState.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public LauncherState findValueByNumber(int number) {
            return LauncherState.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static LauncherState valueOf(int value2) {
        return forNumber(value2);
    }

    public static LauncherState forNumber(int value2) {
        if (value2 == 0) {
            return BACKGROUND;
        }
        if (value2 == 1) {
            return HOME;
        }
        if (value2 == 2) {
            return OVERVIEW;
        }
        if (value2 != 3) {
            return null;
        }
        return ALLAPPS;
    }

    public static Internal.EnumLiteMap<LauncherState> internalGetValueMap() {
        return internalValueMap;
    }

    private LauncherState(int value2) {
        this.value = value2;
    }
}
