package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
//    id나 name을 가져올 때 null이 반환할 수 있는데
//    요즘은 optional 객체로 감싸서 반한하는걸 선호
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
