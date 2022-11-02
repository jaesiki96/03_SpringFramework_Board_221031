package com.its.board.controller;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    // 글 작성 페이지 출력
    @GetMapping("/save")
    public String saveForm() {
        return "boardPages/boardSave";
    }

    // 글 작성 & 파일 첨부
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.save(boardDTO);
        return "redirect:/board/";
    }

    // 글 목록 출력
    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "boardPages/boardList";
    }

    // 상세조회 -- 위에 RequestMapping 했기 때문에 GetMapping 만 사용
    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("조회: boardDTO = " + boardDTO);
        return "boardPages/boardDetail";
    }

    // 수정화면 요청
    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model) {
        // id 를 가지고 조회할 수 있는 메서드가 이미 위에 있기 때문에 findById 를 가져온다.
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "boardPages/boardUpdate";
    }

    // 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO);
        // 수정 처리 후 상세페이지 출력
        // 1. redirect 로 상세페이지 요청 (조회수가 하나 더 올라가는 오류가 있음)
//        return "redirect:/board?id=" + boardDTO.getId();
        // 2. DB 에서 가져와서 boardDetail 출력
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "boardPages/boardDetail";
    }

    // 삭제 화면 요청
    @GetMapping("/deleteCheck")
    public String deleteCheck(@RequestParam("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "boardPages/deleteCheck";
    }

    // 삭제 처리
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }
}
