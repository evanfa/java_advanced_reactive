package com.jpa.hibernate.data.enums;

import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.impl.STAlgTypeImpl;

import java.util.Map;
import java.util.Set;

public enum CommonStates {
    NEW,
    SUBMITTED,
    APPROVED,
    CANCELED,
    CLOSED,
    FINISHED;

    private static final Map<CommonStates, Set<CommonStates>> allowedStates = Map.of(
            NEW, Set.of(SUBMITTED,APPROVED,CANCELED, CLOSED, FINISHED),
            SUBMITTED, Set.of(APPROVED, CANCELED, CLOSED, FINISHED),
            APPROVED, Set.of(CANCELED, CLOSED, FINISHED),
            CANCELED, Set.of(CLOSED),
            FINISHED, Set.of()
    );

    public static boolean isAllowedStates(CommonStates cmd, CommonStates newCmd){
        return allowedStates.get(cmd).contains(newCmd);
    }
}
