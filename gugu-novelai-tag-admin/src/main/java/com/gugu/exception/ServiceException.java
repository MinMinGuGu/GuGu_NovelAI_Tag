package com.gugu.exception;

/**
 * 表示Service层异常
 *
 * @author minmin
 * @version 1.0
 * @date 2022 /03/12
 * @since 1.8
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 2749375332059590679L;

    /**
     * Instantiates a new Service exception.
     */
    public ServiceException() {
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param cause the cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
