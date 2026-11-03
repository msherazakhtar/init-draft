package com.init.draft.utilities;

import com.init.draft.dtos.ApiResponse;
import com.init.draft.enums.ResponseStatus;

public class ApiResponseUtil {

    public static <T> ApiResponse<T> setApiResponse(
            ResponseStatus responseStatus,
            T data) {

        String message;

        message = (responseStatus == ResponseStatus.SUCCESS)
                ? "Requst Processed"
                : "Processing Error";

        return new ApiResponse<>(
                message,
                data,
                responseStatus);
    }
}