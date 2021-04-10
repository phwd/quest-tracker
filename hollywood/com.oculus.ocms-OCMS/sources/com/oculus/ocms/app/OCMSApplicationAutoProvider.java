package com.oculus.ocms.app;

import com.facebook.inject.AbstractComponentProvider;

public class OCMSApplicationAutoProvider extends AbstractComponentProvider<OCMSApplication> {
    public void inject(OCMSApplication oCMSApplication) {
        OCMSApplication._UL_staticInjectMe(this, oCMSApplication);
    }

    public boolean equals(Object obj) {
        return obj instanceof OCMSApplicationAutoProvider;
    }
}
