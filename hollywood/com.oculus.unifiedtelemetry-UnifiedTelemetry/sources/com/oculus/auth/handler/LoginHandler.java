package com.oculus.auth.handler;

import X.DB;

public interface LoginHandler {
    DB<Void> afterLoginAsync();
}
