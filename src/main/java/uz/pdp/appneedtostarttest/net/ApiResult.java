package uz.pdp.appneedtostarttest.net;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Muhammad Mo'minov
 * 06.11.2021
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> implements Serializable {
    private Boolean success = false;
    private String message;
    private T data;
    private List<ErrorData> errors;

    //RESPONSE WITH BOOLEAN (SUCCESS OR FAIL)
    private ApiResult(Boolean success) {
        this.success = success;
    }

    //SUCCESS RESPONSE WITH DATA
    private ApiResult(T data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    //SUCCESS RESPONSE WITH DATA AND MESSAGE
    private ApiResult(T data, Boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    //SUCCESS RESPONSE WITH MESSAGE
    private ApiResult(String message) {
        this.message = message;
        this.success = Boolean.TRUE;
    }

    //ERROR RESPONSE WITH MESSAGE AND ERROR CODE
    private ApiResult(String errorMsg, Integer errorCode) {
        this.success = false;
        this.errors = Collections.singletonList(new ErrorData(errorMsg, errorCode));
    }


    //ERROR RESPONSE WITH ERROR DATA LIST
    private ApiResult(List<ErrorData> errors) {
        this.success = false;
        this.errors = errors;
    }

    public static <E> ApiResult<E> success(E data) {
        return new ApiResult<>(data, true);
    }

    public static <E> ApiResult<E> success(E data, boolean notMessage) {
        return new ApiResult<>(data, true);
    }

    public static <E> ApiResult<E> success(E data, String message) {
        return new ApiResult<>(data, true, message);
    }

    public static <E> ApiResult<E> success() {
        return new ApiResult<>(true);
    }

    public static ApiResult<String> success(String message) {
        return new ApiResult<>(message);
    }

    public static ApiResult<ErrorData> errorResponse(List<ErrorData> errors) {
        return new ApiResult<>(errors);
    }

    public static ApiResult<ErrorData> errorResponse(String errorMsg, Integer errorCode) {
        return new ApiResult<>(errorMsg, errorCode);
    }

    /**
     * If a data is present, returns the data, otherwise throws an exception
     * produced by the exception supplying function.
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exception) throws X {
        if (data != null && success) {
            return data;
        } else {
            throw exception.get();
        }
    }

    /**
     * If a value is present, performs the given action with the value,
     * otherwise does nothing.
     */
    public void ifPresent(Consumer<? super T> action) {
        if (data != null) {
            action.accept(data);
        }
    }

    /**
     * If a data is present, and data type is list and data not empty returns the data, otherwise throws an exception
     * produced by the exception supplying function.
     */
    public <X extends Throwable> T orElseEmptyThrow(Supplier<? extends X> exception) throws X {
        if (success && data instanceof List && !((List<?>) data).isEmpty()) {
            return data;
        } else {
            throw exception.get();
        }
    }

    /**
     * If a data is present, returns the data, otherwise returns other
     */
    public T orElse(T other) {
        return data != null ? data : other;
    }

    /**
     * If a value is present, returns the value, otherwise returns the result
     * produced by the supplying function.
     */
    public T orElseGet(Supplier<? extends T> supplier) {
        return data != null ? data : supplier.get();
    }

    /**
     * if a success is false throws an exception  produced by the exception supplying function.
     */
    public <X extends Throwable> void noSuccessThrow(Supplier<? extends X> exception) throws X {
        if (!success) throw exception.get();
    }

}
