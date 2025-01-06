package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 자동으로 스프링 빈으로 자동 등록을 해준다.
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    //@RequiredArsConstructor의 사용으로 생략이 가능
    //@PersistenceContext  JPA의 EntityManager를 주입해준다.
    //private EntityManager em;

    public void save(Member member){
        em.persist(member); // persist하면 영속성컨텍스트에 Member 객체를 넣는다.
        // insert 역할
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);// 단건조회로 타입, pk를 넣어주면 된다.
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) //Jpql을 사용하였고, sql이랑 거의 똑같고
                // from의 대상이 테이블이 아니라 Entity로 보면 된다.
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
