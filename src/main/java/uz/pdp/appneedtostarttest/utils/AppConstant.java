package uz.pdp.appneedtostarttest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface AppConstant {
    Integer NO_ITEMS_FOUND = 300032;
    Integer SERVER_ERROR = 500;
    Integer REQUIRED = 400;
    Integer CONFLICT = 401;
    Integer NOT_FOUND = 404;
    Integer ACCESS_DENIED = 405;

    String[] OPEN_PAGES = {
            AppConstant.BASE_PATH + "/swagger-ui/**",
            AppConstant.BASE_PATH + "/swagger-ui.html",
            AppConstant.BASE_PATH_V1+"/auth/**"

    };
    String BASE_PATH = "/api";
    String BASE_PATH_V1 = BASE_PATH + "/v1";

    String AUTHENTICATION_HEADER = "Authorization";
    ObjectMapper objectMapper = new ObjectMapper();

    String TOKEN_TYPE = "Bearer ";



    String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";


    String NUMBER_GREATER = "NUMBER_GREATER";
    String ONE_OF_LIST = "ONE_OF_LIST";
    String USER_ENTERED = "USER_ENTERED";
    String DEFAULT_PAGE = "0";
    String DEFAULT_PAGE_SIZE_FOR_SEARCH = "5";
    String DEFAULT_PAGE_SIZE = "10";
    String APPLICATION_NAME = "DIABLO AIRWAYS";
    String WHERE_CLAUSE = "deleted=false";
    String SQL_DELETED = "";
    String BEARER_TOKEN = "Bearer ";
}
