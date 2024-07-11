package com.untilwed.jpaweb.service;

import com.untilwed.jpaweb.domain.Member;
import com.untilwed.jpaweb.repository.MemberRepository;
import com.untilwed.jpaweb.repository.MemberRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //트랜잭션을 적옹하여 예외 발생시 롤백함
//!주의 @Transactional은 RuntimeException과 그 자식들인 Unchecked예외만 롤백하여
//다른 예외도 롤백하고 싶다면 옵션을 설정해야한다.
public class MemberService {

    @Autowired
    MemberRepositoryV2 memberRepository;

    /**
     * 회원가입
     */
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) throws EmptyResultDataAccessException {
        try{
            return memberRepository.findById(memberId).get();
        } catch (EmptyResultDataAccessException ex){
            throw new EmptyResultDataAccessException("해당 회원은 존재하지 않습니다.", 1);
        }

    }
}
