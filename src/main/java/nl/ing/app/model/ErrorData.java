package nl.ing.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorData {

    private String message;
    private String details;
    private String code;
    private String severity;


    public ErrorData(String message, String code) {
        this.message = message;
        this.code = code;
    }


    public ErrorData(String message, String code, String severity) {
        this.message = message;
        this.code = code;
        this.severity = severity;
    }
}
