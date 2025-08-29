package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  // final이기에 초기화되지 않으면 오류 발생 -> 실수 방지
  private final MemberRepository memberRepository;

  // 필드 주입으로 @RequireArgsConstructor로 대체 가능
  //  @Autowired // 스프링 4.3 이후 생성자가 하나일 때 생략 가능
  //  public MemberService(MemberRepository memberRepository) {
  //    this.memberRepository = memberRepository;
  //  }

  // 회원 가입
  @Transactional
  public Long join(Member member) {
    validateDuplicateMember(member); // 중복 회원 검증
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    List<Member> findMembers = memberRepository.findByName(member.getName());
    if (!findMembers.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  // 회원 전체 조회
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  // 회원 단건 조회
  public Member findOne(Long memberId) {
    return memberRepository.findOne(memberId);
  }
}
