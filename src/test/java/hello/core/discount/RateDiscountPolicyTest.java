package hello.core.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;

class RateDiscountPolicyTest {

	RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

	@Test
	@DisplayName("VIP는 할인이 적용된다")
	void discount() {
		// given
		Member member = new Member(1L, "memberVip", Grade.VIP);

		// when
		int discount = rateDiscountPolicy.discount(member, 10000);

		// then
		Assertions.assertThat(discount).isEqualTo(1000);
	}

	@Test
	@DisplayName("VIP가 아니면 할인 안된다")
	void no_discount() {
		// given
		Member member = new Member(2L, "memberBasic", Grade.BASIC);

		// when
		int discount = rateDiscountPolicy.discount(member, 10000);

		// then
		Assertions.assertThat(discount).isEqualTo(0);
	}
}