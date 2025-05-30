package org.combs.authentication_service.validation;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class Violation {
    private final String fieldName;
    private final String message;

}
