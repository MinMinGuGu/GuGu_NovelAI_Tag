package com.gugu.exception;

/**
 * 初始化标签数据至数据库时异常
 *
 * @author minmin
 * @date 2023 /02/04
 */
public class LoadDataException extends RuntimeException {
    /**
     * Instantiates a new Load data exception.
     */
    public LoadDataException() {
    }

    /**
     * Instantiates a new Load data exception.
     *
     * @param message the message
     */
    public LoadDataException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Load data exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public LoadDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Load data exception.
     *
     * @param cause the cause
     */
    public LoadDataException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Load data exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public LoadDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
