package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepositoryOriginal = ac.getBean("memberRepository", MemberRepository.class);

		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();

		System.out.println("memberRepository1 = " + memberRepository1);
		System.out.println("memberRepository2 = " + memberRepository2);
		System.out.println("memberRepositoryOriginal = " + memberRepositoryOriginal);

		System.out.println("orderService = " + orderService);

		assertThat(memberRepository1).isSameAs(memberRepositoryOriginal);
		assertThat(memberRepository2).isSameAs(memberRepositoryOriginal);

		// memberRepository()를 여러번 호출해서 다른 객체가 나올 줄 알았는데 싱글톤 보장이 잘되네?
	}

	void noConfigurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		OrderServiceImpl orderService2 = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepositoryOriginal = ac.getBean("memberRepository", MemberRepository.class);

		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();

		System.out.println("memberRepository1 = " + memberRepository1);
		System.out.println("memberRepository2 = " + memberRepository2);
		System.out.println("memberRepositoryOriginal = " + memberRepositoryOriginal);

		System.out.println("orderService = " + orderService);
		System.out.println("orderService2 = " + orderService2);

		assertThat(memberRepository1).isNotSameAs(memberRepositoryOriginal);
		assertThat(memberRepository2).isNotSameAs(memberRepositoryOriginal);
	}

	@Test
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);

		System.out.println("bean.getClass() = " + bean.getClass());
	}
}
