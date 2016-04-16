package com.rxdroid.extensecalc.model;

import java.util.Calendar;

/**
 * Created by rxdroid on 4/16/16.
 */
public class User {


    public User(Builder builder) {

    }

    public static class Builder {
        private String authToken;
        private Calendar expireDate;
        private String accountId;
        private String phone;

        public Builder authToken(String authToken) {
            this.authToken = authToken;
            return this;
        }

        public Builder expireDate(Calendar expireDate) {
            this.expireDate = expireDate;
            return this;
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
