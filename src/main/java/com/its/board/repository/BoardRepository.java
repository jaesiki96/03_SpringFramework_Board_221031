package com.its.board.repository;

import com.its.board.dto.BoardDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {
    @Autowired
    private SqlSessionTemplate sql;

    // 글 작성 & 파일 첨부
    public BoardDTO save(BoardDTO boardDTO) {
        System.out.println("insert 전 boardDTO = " + boardDTO);
        sql.insert("Board.save", boardDTO);
        System.out.println("insert 후 boardDTO = " + boardDTO);
        return boardDTO;
    }

    // 파일
    public void saveFileName(BoardDTO boardDTO) {
        sql.insert("Board.saveFile", boardDTO);
    }

    // 글 목록 출력
    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    // 상세 조회 -- Mapper 에서 순서 중요 (update -> select)
    // 1. 조회수 증가
    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    // 2. 상세 내용
    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    // 수정 처리
    public void update(BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO);
    }

    // 삭제 처리
    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }
}
