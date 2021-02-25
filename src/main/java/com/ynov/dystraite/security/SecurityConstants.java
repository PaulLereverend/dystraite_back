package com.ynov.dystraite.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs"; //TODO
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/sign-up";
    public static final String SIGN_IN_URL = "/login";
    public static final String REQUEST_TOKEN_URL = "/users/request-token";
    public static final String CHANGE_PASSWORD_URL = "/users/change-password/**";
}
