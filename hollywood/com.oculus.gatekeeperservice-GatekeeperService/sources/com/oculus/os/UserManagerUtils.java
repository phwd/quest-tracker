package com.oculus.os;

import android.os.IUserManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Set;

public class UserManagerUtils {
    private static final String TAG = "UserManagerUtils";

    public boolean activateSecurityPrincipals(int userId, Set<String> principals) {
        if (principals == null || principals.size() == 0) {
            throw new IllegalArgumentException("Invalid input argument: empty principals");
        }
        try {
            return IUserManager.Stub.asInterface(ServiceManager.getService("user")).activateSecurityPrincipals(userId, new ArrayList<>(principals));
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException: ", e);
            return false;
        }
    }

    public boolean deactivateSecurityPrincipals(int userId, Set<String> principals) {
        if (principals == null || principals.size() == 0) {
            throw new IllegalArgumentException("Invalid input argument: empty principals");
        }
        try {
            return IUserManager.Stub.asInterface(ServiceManager.getService("user")).deactivateSecurityPrincipals(userId, new ArrayList<>(principals));
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException: ", e);
            return false;
        }
    }
}
