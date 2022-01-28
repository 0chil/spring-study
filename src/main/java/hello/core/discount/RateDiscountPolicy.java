package hello.core.discount;

import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

	private static final int discountRate = 10;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP)
			return price * discountRate / 100;
		return 0;
	}
}
