package hello.hellospring.domain;

import javax.persistence.*;

// 이제부터 JPA가 관리하는 Entity가 됨
@Entity
public class Member {
//    시스템이 정하는 id
    // PK와 DB가 알아서 생성해주므로 identity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
