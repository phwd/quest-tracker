package com.facebook.secure.trustedapp.checker;

interface CheckerInternal extends Checker {
    Checker or(Checker checker);
}
