package com.gugu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The type Result.
 *
 * @param <T> the type parameter
 * @author minmin
 * @version 1.0
 * @since 1.8
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * The constant SUCCESS.
     */
    public static final String SUCCESS = "success";
    /**
     * The constant FAIL.
     */
    public static final String FAIL = "fail";

    private Integer code;
    private String message;
    private T data;

    /**
     * Fast success result.
     *
     * @return the result
     */
    public static Result<String> fastSuccess() {
        return new Result<String>().setCode(200).setMessage(SUCCESS);
    }

    /**
     * Fast success result.
     *
     * @param data the data
     * @return the result
     */
    public static Result<Object> fastSuccess(Object data) {
        return new Result<Object>().setCode(200).setMessage(SUCCESS).setData(data);
    }

    /**
     * Fast fail result.
     *
     * @return the result
     */
    public static Result<String> fastFail() {
        return new Result<String>().setCode(404).setMessage(FAIL);
    }

    /**
     * Fast fail result.
     *
     * @param message the message
     * @return the result
     */
    public static Result<String> fastFail(String message) {
        return new Result<String>().setCode(400).setMessage(message);
    }

    /**
     * The type Builder.
     *
     * @param <B> the type parameter
     * @author minmin
     * @version 1.0
     * @since 1.8
     */
    public static class Builder<B> {
        private Integer code;
        private String message;
        private B data;

        /**
         * Code builder.
         *
         * @param code the code
         * @return the builder
         */
        public Builder<B> code(Integer code) {
            this.code = code;
            return this;
        }

        /**
         * Message builder.
         *
         * @param message the message
         * @return the builder
         */
        public Builder<B> message(String message) {
            this.message = message;
            return this;
        }

        /**
         * Data builder.
         *
         * @param data the data
         * @return the builder
         */
        public Builder<B> data(B data) {
            this.data = data;
            return this;
        }

        /**
         * Success builder.
         *
         * @return the builder
         */
        public Builder<B> success() {
            this.code = 200;
            this.message = SUCCESS;
            return this;
        }

        /**
         * Success builder.
         *
         * @param data the data
         * @return the builder
         */
        public Builder<B> success(B data) {
            this.code = 200;
            this.message = SUCCESS;
            this.data = data;
            return this;
        }

        /**
         * Fail builder.
         *
         * @return the builder
         */
        public Builder<B> fail() {
            this.code = 404;
            this.message = FAIL;
            return this;
        }

        /**
         * Fail builder.
         *
         * @param message the message
         * @return the builder
         */
        public Builder<B> fail(String message) {
            this.code = 400;
            this.message = message;
            return this;
        }

        /**
         * Build result.
         *
         * @return the result
         */
        public Result<B> build() {
            return new Result<>(code, message, data);
        }
    }
}
