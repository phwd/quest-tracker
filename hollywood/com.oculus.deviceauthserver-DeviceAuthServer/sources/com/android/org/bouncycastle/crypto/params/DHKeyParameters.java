package com.android.org.bouncycastle.crypto.params;

public class DHKeyParameters extends AsymmetricKeyParameter {
    private DHParameters params;

    protected DHKeyParameters(boolean isPrivate, DHParameters params2) {
        super(isPrivate);
        this.params = params2;
    }

    public DHParameters getParameters() {
        return this.params;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHKeyParameters)) {
            return false;
        }
        DHKeyParameters dhKey = (DHKeyParameters) obj;
        DHParameters dHParameters = this.params;
        if (dHParameters != null) {
            return dHParameters.equals(dhKey.getParameters());
        }
        if (dhKey.getParameters() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int code = !isPrivate();
        DHParameters dHParameters = this.params;
        if (dHParameters != null) {
            return code ^ dHParameters.hashCode();
        }
        return code;
    }
}
