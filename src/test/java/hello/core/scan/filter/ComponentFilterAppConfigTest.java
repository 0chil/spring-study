package hello.core.scan.filter;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class ComponentFilterAppConfigTest {

	@Test
	void filterScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		BeanA beanA = ac.getBean("beanA", BeanA.class);

		assertThat(beanA).isNotNull();

		assertThatThrownBy(() -> ac.getBean("beanB", BeanB.class))
			.isInstanceOf(NoSuchBeanDefinitionException.class);
	}

	@Configuration
	@ComponentScan(
		includeFilters = @Filter(classes = MyIncludeComponent.class),
		excludeFilters = @Filter(classes = MyExcludeComponent.class)
	)
	static class ComponentFilterAppConfig {

	}
}
