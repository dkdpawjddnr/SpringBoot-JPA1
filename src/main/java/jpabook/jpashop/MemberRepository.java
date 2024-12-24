package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

// Entity같은 걸 찾아주는 역할
// ComponentScan 대상이됨(자동 Spring Bean등록)
@Repository
public class MemberRepository {

    // Entity 매니저 설정
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
