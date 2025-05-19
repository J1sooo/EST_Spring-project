package com.estsoft.demo.mock;

public interface ICoupon {
    String getName();                // 쿠폰 이름
    boolean isValid();               // 쿠폰 유효 여부 확인
    int getDiscountPercent();        // 할인율
    boolean isApplicable(Item item);  // 해당 아이템에 적용 가능 여부
    void doExpire();
}