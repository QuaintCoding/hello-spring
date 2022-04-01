package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
//    동시성 문제가 있어 공유되는 변수는 ConcurrentHashMap을 쓰는데
//    지금은 간단한 예제이니 그냥 HashMap 쓴다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
//        null이 반환될 가능성이 있으면 Optional로 감쌈
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
//        해시 맵에서 값들만 stream으로 뽑아와 filter를 통해 거르고 한개 찾은걸 반환한다.(없으면 null)
//        스트림이란 람다를 활용할 수 있는 기술 중 하나이다.
//        컬렉션에 저장되어 있는 요소들을 하나씩 참고하며 반복처리를 가능하게 한다.(loop돌리기)
//        스트림은 일회용이다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
