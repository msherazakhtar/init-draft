package com.expense.tracker.dtos;

import com.expense.tracker.enums.ResponseStatus;

public record ApiResponse<T>(String responseMessage, T responseData,
                ResponseStatus responseStatus) {

}
