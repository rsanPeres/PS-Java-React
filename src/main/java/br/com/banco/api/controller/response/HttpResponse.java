package br.com.banco.api.controller.response;

public class HttpResponse<T> {
    private int responseCode;
    private String responseMessage;
    private T responseBody;

    public HttpResponse(int responseCode, String responseMessage, T responseBody) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseBody = responseBody;
    }
}
