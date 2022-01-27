package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	/**
	 * 동시성을 고민해보자
	 */

	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

		// ThreadA: A 사용자 10000원 주문
		statefulService1.order("userA", 10000);
		// ThreadB: B 사용자 20000원 주문
		statefulService2.order("userB", 20000);

		// ThreadA: 사용자A 주문 조회
		// 의도 : 사용자A의 주문 금액인 10000원
		// 결과 : 20000원
		// 원인 : statefulService1과 statefulService2는 싱글턴 패턴에 의해 같은 객체이다.
		// 그런데 상태를 변경하고 저장하는 코드로 인해 동시에 주문이 이뤄질 경우 후에 처리되는 20000원이 저장되게 된다.
		int price = statefulService1.getPrice(); // StatefulService의 price 필드에 의존하고 있다.
		System.out.println("price = " + price);

		// 의도와 다름
		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	@Test
	void statefulServiceSingletonFix() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

		// ThreadA: A 사용자 10000원 주문
		int userAPrice = statefulService1.orderFix("userA", 10000);
		// ThreadB: B 사용자 20000원 주문
		int userBPrice = statefulService2.orderFix("userB", 20000);

		System.out.println("price = " + userAPrice);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}