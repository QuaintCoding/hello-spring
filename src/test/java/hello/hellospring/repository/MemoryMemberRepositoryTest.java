package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
//        repository.findById(member.getId())는 Optional 객체이므로 get()으로 까준다.
        Member result = repository.findById(member.getId()).get();

//        객체 x와 y가 일치함을 확인
//        x(예상 값)와 y(실제 값)가 같으면 테스트 통과
//        jupiter꺼
//        Assertions.assertEquals(member, result);

//        assertj꺼(가독성이 더 좋음)
//        asswerThat에서 alt+enter 누르면 static import 할 수 있고
//        Assertions 안쓰고 사용가능하다.
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

//        shift + f6 누르면 변수 이름 일괄 변경 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

//    전체로 테스트를 돌리면 findByName이 오류가 뜸
//    테스트 순서는 보장이 안됨 
//    그래서 findAll에서 repository에 이미 어떤 값이 저장이 되어 있으므로
//    테스트가 정상적으로 안됨 -> 따라서 테스트 끝날 때마다 데이터를 clear 해줘야됨
//    하는 방법: AfterEach 어노테이션을 사용하여 테스트 끝날 때마다 clearStore 호출
}
