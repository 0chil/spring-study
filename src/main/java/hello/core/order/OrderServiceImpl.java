package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	// @Autowired // 생성자가 유일한 경우에는 생략해도 같은 효과
	// public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
	// 	this.memberRepository = memberRepository;
	// 	this.discountPolicy = discountPolicy;
	// }

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice); // 단일 책임 원칙 준수 Good, "할인은 할인 인터페이스가 알아서 해라"

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 임시 테스트용
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
