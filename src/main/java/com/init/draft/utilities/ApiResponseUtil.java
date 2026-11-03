package com.expense.tracker.utilities;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.enums.ResponseStatus;

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