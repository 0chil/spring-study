package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Autowired //ac.getBean(MemberRepository.class) 해서 주입하는 것과 비슷하다
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	// 임시 테스트용
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
