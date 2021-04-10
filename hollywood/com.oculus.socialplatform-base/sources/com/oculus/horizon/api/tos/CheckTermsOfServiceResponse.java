package com.oculus.horizon.api.tos;

public class CheckTermsOfServiceResponse {
    public final Viewer viewer;

    public static class Viewer {
        public final String challenge_tos;
    }

    public String getTos() {
        return this.viewer.challenge_tos;
    }

    public boolean hasNewTos() {
        if (this.viewer.challenge_tos != null) {
            return true;
        }
        return false;
    }
}
