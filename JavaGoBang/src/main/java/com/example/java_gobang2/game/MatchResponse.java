package com.example.java_gobang2.game;

/**
 * ClassName: MatchResponse
 * Description
 *
 * @Author wzy
 * @Create 2025/3/20 23:00
 * @Version 1.0
 */
public class MatchResponse {
    private boolean ok;
    private String reason;
    private String message;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
