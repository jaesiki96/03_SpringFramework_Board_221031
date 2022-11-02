package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    // 글 작성
    public boolean save(BoardDTO boardDTO) {
        int result = boardRepository.save(boardDTO);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 글 목록
    public List<BoardDTO> findAll() {
        List<BoardDTO> boardDTOList = boardRepository.findAll();
        return boardDTOList;
    }

    // 조회수 증가
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    // 상세 조회
    public BoardDTO findById(Long id) {
        // 상세내용 가져와서 리턴
        return boardRepository.findById(id);
    }

    // 수정처리
    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }
}

