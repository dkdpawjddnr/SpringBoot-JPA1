package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // class 레벨이 @Transcational을 사용하면 메서드마다 트렌젝션이 적용됨.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional // 쓰기 전용 (데이터 변경할 때)
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    //@Transactional(readOnly = true) 읽기 전용 트렌젝션
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    
    //@Transactional(readOnly = true)
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }
}
