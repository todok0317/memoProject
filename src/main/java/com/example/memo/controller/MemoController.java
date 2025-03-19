package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController // 이 클래스를 REST API의 컨트롤러로 사용하겠다는 의미, 데이터 (JSON)를 주고받음
@RequestMapping("/memos") // API의 기본 경로를 /memos로 하겠다고함.
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping // HTTP POST 요청을 처리하는 메서드 클라이언트가 새로운 메모를 생성할 때 사용
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) { // @RequestBody란? 클라이언트가 보낸 JSON 데이터를 자바 객체로 변환해주는 어노테이션

        // 식별자가 1씩 증가하도록 만듦.
        // memoList가 비어있으면 1로 설정, 있으면 키의 최댓값 (id의 최댓값)중에서 +1 해줘
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, requestDto.getTitle(), requestDto.getContents());

        // Inmemory DB에 Memo 저장
        memoList.put(memoId, memo);

        return new MemoResponseDto(memo);
    }
    @GetMapping("/{id}")
    public MemoResponseDto findMemoById(@PathVariable Long id) {

        Memo memo = memoList.get(id);

        return new MemoResponseDto(memo);
    }

    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto
    ) {
        Memo memo = memoList.get(id);

        memo.update(requestDto);

        return new MemoResponseDto(memo);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo (@PathVariable Long id) {
        memoList.remove(id);
    }

}
