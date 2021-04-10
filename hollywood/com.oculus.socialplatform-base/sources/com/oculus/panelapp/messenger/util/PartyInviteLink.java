package com.oculus.panelapp.messenger.util;

import android.net.Uri;
import java.util.Optional;
import java.util.regex.Pattern;

public class PartyInviteLink {
    public static final String NONCE_PARAM_NAME = "link_id";
    public static final Pattern PARTY_INVITE_LINK_PATTERN = Pattern.compile(PARTY_INVITE_PATTERN);
    public static final String PARTY_INVITE_PATTERN = "^https://www.oculus.com/vr/[0-9]*/\\?[-a-zA-Z0-9+&@#/%?=_]*notation=party_invite[-a-zA-Z0-9+&@#/%?=_]*$";
    public final Optional<String> mNonce;
    public final String mPartyId;

    public static boolean validate(String str) {
        if (!PARTY_INVITE_LINK_PATTERN.matcher(str).find()) {
            return false;
        }
        return true;
    }

    public String getPartyId() {
        return this.mPartyId;
    }

    public Optional<String> getPartyNonce() {
        return this.mNonce;
    }

    public PartyInviteLink(String str, Optional<String> optional) {
        this.mPartyId = str;
        this.mNonce = optional;
    }

    public static PartyInviteLink fromString(String str) {
        if (!validate(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        return new PartyInviteLink(parse.getPathSegments().get(1), Optional.ofNullable(parse.getQueryParameter(NONCE_PARAM_NAME)));
    }
}
