package com.facebook.crudolib.urimap.componenthelper;

import javax.annotation.Nullable;

public interface ComponentHelperFactory {
    @Nullable
    ComponentHelper getHelperForActivity(String str);

    @Nullable
    ComponentHelper getHelperForFragment(int i);

    @Nullable
    ComponentHelper getHelperForName(String str);

    @Nullable
    String getHelperNameForActivity(String str);

    @Nullable
    String getHelperNameForFragment(int i);
}
