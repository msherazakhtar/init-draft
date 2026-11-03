package com.init.draft.dtos;

import com.init.draft.enums.ResponseStatus;

public record ApiResponse<T>(String responseMessage, T responseData,
                ResponseStatus responseStatus) {

}
