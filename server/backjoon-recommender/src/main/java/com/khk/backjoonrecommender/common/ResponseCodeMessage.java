package com.khk.backjoonrecommender.common;

public interface ResponseCodeMessage {

    int SUCCESS = 200;
    int FAIL = 400;

    String REGISTER_SUCCESS = "success to register user";
    String REGISTER_FAIL = "fail to register user";
    String REGISTER_ALREADY_EXIST = "already registered user";
    String REGISTER_NOT_FOUND_BAEKJOONID = "baekJoonId is not existed";

    String SUCCESS_USER_DETAIL = "success to find username";


}
