package senatskov.ilya.frontend.xml;

import java.util.Arrays;

public enum ResponseCodes {
    OK(0),
    USER_EXISTS(1),
    TECHINCAL_ERROR(2),
    USER_NOT_EXISTS(3),
    PASSWORD_NOT_CORRECT(4);
    
    private int code;
    
    private ResponseCodes(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    public static ResponseCodes getInstance(int code) {
        return Arrays.stream(ResponseCodes.values()).filter(e -> e.code == code).findFirst().orElse(null);
    }
}
