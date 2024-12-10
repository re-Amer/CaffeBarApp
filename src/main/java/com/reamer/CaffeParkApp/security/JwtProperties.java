package com.reamer.CaffeParkApp.security;

public class JwtProperties {
    public static final String SECRET = "P`n4GQ@Kqx=<?Azv_^C8[>w;SUdNL/7]";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}