package com.khk.backjoonrecommender.exception.handler.advice;

import com.khk.backjoonrecommender.common.ResponseCodeMessage;
import com.khk.backjoonrecommender.exception.BaekJoonIdNotFoundException;
import com.khk.backjoonrecommender.exception.handler.AlreadyRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.khk.backjoonrecommender.common.ResponseCodeMessage.*;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaekJoonIdNotFoundException.class)
    public ErrorResult baekJoonIdNotFoundExHandler(BaekJoonIdNotFoundException e) {
        return new ErrorResult(FAIL, REGISTER_NOT_FOUND_BAEKJOONID);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyRegisteredException.class)
    public ErrorResult alreadyUserExHandler(AlreadyRegisteredException e) {
        return new ErrorResult(FAIL, REGISTER_ALREADY_EXIST);
    }
}