package com.oculus.vrshell.config;

import java.util.ArrayList;

public class GatekeepersConfiguration {
    private String[] mGatekeepers = new String[0];
    private boolean mIsValid = false;

    public String[] getGatekeepers() {
        return this.mGatekeepers;
    }

    public void updateGatekeepers(ArrayList<String> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            this.mGatekeepers = (String[]) arrayList.toArray(new String[0]);
        }
        this.mIsValid = true;
    }

    public boolean isValid() {
        return this.mIsValid;
    }
}
