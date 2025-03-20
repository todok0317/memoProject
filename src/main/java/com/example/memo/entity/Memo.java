package com.example.memo.entity;

import com.example.memo.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 생성
public class Memo {

    private Long id; //long을 쓴 이유는 래퍼클래스로 null도 있을 수 있고 더 범위가 크기에 사용
    private String title;
    private String contents;

    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void updateTitle(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
    }


}
