package org.viettel.ocs.rulemgt.exception;

public class CorrelationException extends RuntimeException {
    public CorrelationException(String msg, Exception e) {
        super(msg, e);
    }

    public CorrelationException(String msg) {
        super(msg);
    }
}
