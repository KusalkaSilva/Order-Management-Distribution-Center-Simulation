package com.distribution.simulation.dto.response.wrapper;


import com.distribution.simulation.enums.RestApiResponseStatus;



public class SimpleResponseWrapper<T> extends BaseResponseWrapper {

    private T content;

    public SimpleResponseWrapper(T content) {
        super(RestApiResponseStatus.OK.OK);
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
