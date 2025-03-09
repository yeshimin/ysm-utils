package com.yeshimin.common.validation;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 枚举值校验器测试
 */
public class EnumValueTests {

    @Test
    public void testIsValid() {
        // validate
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Order order1 = new Order();
        order1.setStatus(1);
        Set<ConstraintViolation<Order>> violations1 = validator.validate(order1);
        if (violations1.isEmpty()) {
            assert true;
        } else {
            assert false;
            violations1.forEach(violation -> {
                System.out.println("order1: " + violation.getMessage());
            });
        }

        Order order2 = new Order();
        order2.setStatus(6);
        Set<ConstraintViolation<Order>> violations2 = validator.validate(order2);
        if (violations2.isEmpty()) {
            assert false;
        } else {
            assert true;
            violations2.forEach(violation -> {
                System.out.println("order2: " + violation.getMessage());
            });
        }

    }

    public static class Order {
        @EnumValue(enumClass = OrderStatusEnum.class)
        private Integer status;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

    enum OrderStatusEnum {
        CREATED(1, "已创建"),
        PAID(2, "已支付"),
        DELIVERED(3, "已发货"),
        RECEIVED(4, "已收货"),
        CANCELED(5, "已取消");

        private final int code;
        private final String desc;

        OrderStatusEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
