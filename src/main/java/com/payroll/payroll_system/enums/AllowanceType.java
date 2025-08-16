package com.payroll.payroll_system.enums;

import java.math.BigDecimal;

import static com.payroll.payroll_system.enums.Position.MANAGER;
import static com.payroll.payroll_system.enums.Position.STAFF;

public enum AllowanceType {
    TRANSPORT{
        @Override
        public double getAmountForPosition(Position position){
            switch (position) {
                case MANAGER:
                    return 5000.0;
                case STAFF:
                    return 3000.0;
                case INTERN:
                    return 500.0;
                default:
                    return 1000.0;
            }
        }
    },
    HOUSING{
        @Override
        public double getAmountForPosition(Position position){
            switch (position) {
                case MANAGER:
                    return 10000.0;
                case STAFF:
                    return  5000.0;
                case INTERN:
                    return 2000.0;
                default:
                    return 3000.0;
            }
        }
    };
    public abstract double getAmountForPosition(Position position);
}
