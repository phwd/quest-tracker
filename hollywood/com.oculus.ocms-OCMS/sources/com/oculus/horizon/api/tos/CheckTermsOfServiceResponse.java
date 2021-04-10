package com.oculus.horizon.api.tos;

public class CheckTermsOfServiceResponse {
    public Viewer viewer;

    public static class Viewer {
        public String challenge_tos;
    }

    public boolean hasNewTos() {
        return this.viewer.challenge_tos != null;
    }

    public String getTos() {
        return this.viewer.challenge_tos;
    }
}
