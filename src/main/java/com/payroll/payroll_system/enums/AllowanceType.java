package com.payroll.payroll_system.enums;

import java.math.BigDecimal;

import static com.payroll.payroll_system.enums.Position.MANAGER;
import static com.payroll.payroll_system.enums.Position.STAFF;

public enum AllowanceType {
    TRANSPORT{
        @Override
        public BigDecimal getAmountForPosition(Position position){
            switch (position) {
                case MANAGER:
                    return new BigDecimal("5000");
                case STAFF:
                    return new BigDecimal("3000");
                default:
                    return new BigDecimal("1000");
            }
        }
    },
    HOUSING{
        @Override
        public BigDecimal getAmountForPosition(Position position){
            switch (position) {
                case MANAGER:
                    return new BigDecimal("10000");
                case STAFF:
                    return new BigDecimal("5000");
                default:
                    return new BigDecimal("3000");
            }
        }
    };
    public abstract BigDecimal getAmountForPosition(Position position);
}
