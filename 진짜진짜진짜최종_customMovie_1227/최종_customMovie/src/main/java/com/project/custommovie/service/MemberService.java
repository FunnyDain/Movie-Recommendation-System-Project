package com.project.custommovie.service;

import com.project.custommovie.dto.MemberDTO;
import com.project.custommovie.entity.MemberEntity;
import com.project.custommovie.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        // repository의 svae메서드 호출(조건. entity객체를 넘겨줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
                1. 회원이 입력한 아이디로 DB에서 조회를 함
                2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<MemberEntity> byMember_id = memberRepository.findByMemberid(memberDTO.getMemberid());

        if(byMember_id.isPresent()) {
            //조회 결과가 있다(해당 아이디를 가진 회원 정보가 있다
            MemberEntity memberEntity = byMember_id.get();
            if(memberEntity.getMemberpassword().equals(memberDTO.getMemberpassword())) {
                //비밀번호가 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }else {
                //비밀번호 불일치
                return null;
            }

        }else {
            //조회 결과가 없다.
            return null;
        }
    }


}
