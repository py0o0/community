package com.example.commu.Repository;

import com.example.commu.Domain.Member;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate sql;
    public boolean checkId(String id) {
        int cnt = sql.selectOne("Com.checkId", id);
        return cnt == 0;
    }

    public void join(Member member) {
        sql.insert("Com.join", member);
    }

    public boolean accessLogin(Member member) {
        int cnt = sql.selectOne("Com.accessLogin", member);
        System.out.println(cnt);
        return cnt == 1;
    }
}
