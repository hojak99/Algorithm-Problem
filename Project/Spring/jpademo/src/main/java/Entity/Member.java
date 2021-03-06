package Entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "MEMBER", indexes = @Index(name = "name_idx1", columnList = "name"))
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // IDENTITY 로 줄 시 테이블 create query 에 "generated by default as identity" 라고 붙음
    // 이렇게 하면 한 번에 100 개 메모리에 올려놓고 사용하고 다 쓰면 다시 메모리에 올려놓고 하는 식이라고 함. 성능 최적화 할 수 있다고 함.
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER")
//    @SequenceGenerator(name = "SEQ_MEMBER", sequenceName = "SEQ_MEMBER", allocationSize = 100)
    private Long id;

    private String name;

    private Long tempId;        // 이렇게 짜면 레퍼런스만 들고 있어서 또 찾아야 됨. join 쓰면 됨.

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

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }
}

