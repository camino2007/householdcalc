package com.rxdroid.extensecalc.enums;


import com.rxdroid.extensecalc.R;

/**
 * Created by robert on 04.04.16.
 */
public enum  ErrorType {

    BAD_REQUEST(400, R.string.error_bad_request_msg), //400
    UNAUTHORIZED(401, R.string.error_mail_password_not_valid), //401
    NOT_FOUND(404, R.string.error_not_found),  //404
    IN_USE(409, R.string.error_in_use_msg), //409
    INTERNAL_SERVER(500, R.string.error_internal_server_msg), // 500
    SERVICE_UNAVAILABLE(503, R.string.error_service_unavailable_msg),  // 503
    TIME_OUT(504, R.string.error_time_out_msg), //504
    OFFLINE(-1, R.string.error_offline_msg),
    UNKNOWN(666, R.string.error_unknown_msg);

    private int mStateCode;
    private int mMessageId;

    ErrorType(int stateCode, int messageId) {
        this.mStateCode = stateCode;
        this.mMessageId = messageId;
    }

    public int getStateCode() {
        return mStateCode;
    }

    public int getMessageId() {
        return mMessageId;
    }
}

