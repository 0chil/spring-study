package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

public class AutowiredTest {

	@Test
	void autowiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // Singleton not guaranteed

		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println("beanDefinitionName = " + beanDefinitionName);
		}
	}

	/**
	 * Member 는 스프링 빈이 아님. 따라서 @Autowired를 사용해도 스프링 컨테이너가 찾아서 넣을 대상이 없어 오류 발생.
	 * 이때 옵션을 사용해 대상 빈이 없을 때 동작을 지정할 수 있음
	 * 첫번째 경우 : @Autowired(required = false)로 하면 주입할 대상이 없으면 아예 호출을 안함, 없어도 된다라고 명시해주는 셈
	 * 두번째 경우 : @Nullable 이라면 없는 경우 null로 주입함
	 * 세번째 경우 : Optional.empty가 들어감
	 */
	static class TestBean {

		@Autowired(required = false)
		public void setNoBean1(Member noBean1) {
			System.out.println("noBean1 = " + noBean1);
		}

		@Autowired
		public void setNoBean2(@Nullable Member noBean2) {
			System.out.println("noBean2 = " + noBean2);
		}

		@Autowired
		public void setNoBean3(Optional<Member> noBean3) {
			System.out.println("noBean3 = " + noBean3);
		}
	}
}
