package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 없어도 SpringBoot 어플리케이션을 실행하면 @SpringBootApplication 에 의해서 자동으로 스캔되어 빈들이 등록 된다.
/**
 * @Component 붙은 애들을 스캔해서 스프링 빈으로 등록해줌. (자동 등록)
 * 그렇다면 @Bean은? @Configuration이 등록함 (수동 등록)
 * @Configuration도 @Component 이므로 등록이 되는데, 우리는 AppConfig.class가 아니라 AutoAppConfig.class 를 사용하고 싶으므로
 * @Configuration은 자동 등록이 되면 안된다. (예제 코드를 유지)
 * 그리고 excludeFilters를 제거하면 AppConfig의 memberService와 자동등록된 memberServiceImpl이 두 개 등록되어 타입으로 찾을 수 없다.
 *  + 제거 시 다른 @Configuration이 같이 로드되어 빈으로 저장된다.
 */
public class AutoAppConfig {

}
