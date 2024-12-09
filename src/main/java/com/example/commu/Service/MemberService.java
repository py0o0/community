package com.example.commu.Service;

import com.example.commu.Domain.Member;
import com.example.commu.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public boolean checkId(String id) {
        return memberRepository.checkId(id);
    }

    public void join(Member member) {
        memberRepository.join(member);
    }

    public boolean accessLogin(Member member) {
        return memberRepository.accessLogin(member);
    }
}
