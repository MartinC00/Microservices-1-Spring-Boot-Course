package com.udemy.microservices1.contact_list.util;

public class Constants {
    public static final byte[] TOKEN_KEY = "111111111111111111111111111111111111111111111".getBytes();
    public static final long TOKEN_LIFE_DURATION = 86_400_00; // 1 day
    public static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
