package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// ctrl + shift + t 누르면 test 생성할 수 있음
// Service 어노테이션을 해야 memberService가 스프링 빈으로 등록됨
//@Service

// JPA를 쓰려면 항상 트랜젝션이 있어야됨(저장이나 변경할 때)
@Transactional
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // alt + insert 누르면 constructer, getter, setter 나옴
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        // ctrl + alt + v하면 자동으로 리턴 만들어줌
        // Optional<Member> result = memberRepository.findByName(member.getName());

        // 멤버에 값이 있으면 exception 발생
        // Optional<Member>로 변수 만드는 것보다 바로 사용하는게 더 좋음
        validateDuplicateMember(member);
        // intellij refactor this 단축키(함수로 빼기): ctrl + alt + shift + t
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원이름입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
