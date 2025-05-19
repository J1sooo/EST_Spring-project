package com.estsoft.demo.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

public class UserCouponTest {
    @Test
    public void testUserCoupon() {
        // TDD - 1.유저 생성, 2.현재쿠폰갯수를확인(0)
        User user = new User("id");
        assertThat(user.countTotalCoupon()).isEqualTo(0);

        // 3. 쿠폰 발급
        ICoupon coupon = new DummyCoupon();  // 쿠폰 Dummy 객체 생성
        user.addCoupon(coupon);

        // 4. 발급 후 쿠폰 갯수 확인
        assertThat(user.countTotalCoupon()).isEqualTo(1);
    }

    // Mock 개념 활용해서 가짜 객체 처리
    @Test
    public void testUserCouponWithMock() {
        User user = new User("area00");
        assertThat(user.countTotalCoupon()).isEqualTo(0); // 쿠폰 수령 전

        ICoupon coupon = Mockito.mock(ICoupon.class);

        Mockito.doReturn(true).when(coupon).isValid();
//        Mockito.when(coupon.isValid()).thenReturn(true);

        user.addCoupon(coupon);
        assertThat(user.countTotalCoupon()).isEqualTo(1); // 쿠폰 수령 후 쿠폰수 검증
    }
}