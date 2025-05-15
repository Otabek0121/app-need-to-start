package uz.pdp.appneedtostarttest.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import uz.pdp.appneedtostarttest.net.ErrorData;
import uz.pdp.appneedtostarttest.utils.AppConstant;
import uz.pdp.appneedtostarttest.utils.MessageService;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException {

    private String userMsg;
    private HttpStatus status;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private List<ErrorData> errors;
    private Integer errorCode;

    private RestException(String resourceName, String fieldName, Object fieldValue, String userMsg) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.userMsg = userMsg;
        this.status = HttpStatus.BAD_REQUEST;
        this.errorCode = AppConstant.NO_ITEMS_FOUND;
    }

    private RestException(String resourceName, String fieldName, Object fieldValue, String userMsg, HttpStatus status) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.userMsg = userMsg;
        this.status = status;
    }

    private RestException(String userMsg, HttpStatus status) {
        super(userMsg);
        this.userMsg = userMsg;
        this.status = status;
    }

    private RestException(String userMsg, int errorCode, HttpStatus status) {
        super(userMsg);
        this.errors = Collections.singletonList(new ErrorData(userMsg, errorCode));
        this.userMsg = userMsg;
        this.status = status;
    }

    private RestException(HttpStatus status, List<ErrorData> errors) {
        this.status = status;
        this.errors = errors;
    }

    public RestException(String s) {

    }

    public static RestException restThrow(String userMsg, HttpStatus httpStatus) {
        return new RestException(userMsg, httpStatus);
    }

    public static RestException restThrow(List<ErrorData> errors, HttpStatus status) {
        return new RestException(status, errors);
    }

    public static RestException restThrow(String message) {
        return new RestException(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param resourceKey - {@link org.springframework.context.MessageSource} bo'yicha kelishi kerak. Masalan "GROUP"
     * @return Guruh topilmadi!
     */
    public static RestException notFound(String resourceKey) {
        return new RestException(
                MessageService.notFound(resourceKey),
                HttpStatus.NOT_FOUND
        );
    }

    public static RestException otherServiceError(String serviceName) {
        return new RestException(
                MessageService.otherServiceError(serviceName),
                HttpStatus.BAD_REQUEST
        );
    }

    public static RestException badRequest(String resourceKey) {
        return new RestException(
                MessageService.getMessage(resourceKey),
                HttpStatus.BAD_REQUEST
        );
    }

    public static RestException alreadyExists(String resourceKey) {
        return new RestException(
                MessageService.alreadyExists(resourceKey),
                HttpStatus.CONFLICT
        );
    }

    public static RestException attackResponse() {
        return new RestException(
                MessageService.getMessage("ATTACK_RESPONSE"),
                HttpStatus.BAD_REQUEST
        );
    }

    public static RestException forbidden() {
        return new RestException(
                MessageService.getMessage("FORBIDDEN"),
                HttpStatus.BAD_REQUEST
        );
    }

    public static RestException forbidden(List<String> notPermissions) {
        /*return new RestException(
                "Forbidden. You don't have permissions : " + msg,
                HttpStatus.BAD_REQUEST
        );*/
        return new RestException(
                String.format("Forbidden. You don't have permission : %s", notPermissions),
                HttpStatus.BAD_REQUEST
        );
    }

    public static RestException internalServerError() {
        return new RestException(
                MessageService.getMessage("INTERNAL_SERVER_ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    public static RestException required(String fieldName) {
        ErrorData errorData = new ErrorData(
                fieldName + " is required",
                fieldName,
                400
        );
        return new RestException(HttpStatus.BAD_REQUEST,List.of(errorData));
    }
}
