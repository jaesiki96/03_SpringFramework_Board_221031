package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 & 파일 첨부
    public void save(BoardDTO boardDTO) throws IOException {
        /*  throw ~ : 아래 transferTo 빨간 줄 클릭하고 생김 -> 위에 1 related problem 클릭하면 repository 넘어가서 빨간 줄 add

            1. BoardDTO 객체에 담긴 파일을 꺼냄
            2. 파일의 원본 이름을 가져옴 (originalFileName)
            3. 서버 관리용 이름으로 만듦 (storedFileName)
            4. originalFileName, storedFileName 을 dto 객체에 담음
            5. 파일 실제 저장 위치 지정
            6. 파일 저장 처리
            7. repository 로 dto 객체 전달
            --------------------------------
            [우변 작성 후 alt + enter] ↓ 아래 (34번 줄 부터 = 붙어 있는 항목에 해당)
         */
        if (!boardDTO.getBoardFile().isEmpty()) {
            System.out.println("파일있음");
            MultipartFile boardFile = boardDTO.getBoardFile(); // 1.
            String originalFilename = boardFile.getOriginalFilename(); // 2.
            System.out.println("originalFilename = " + originalFilename);
            System.out.println(System.currentTimeMillis()); // 1000분의 1 어쩌구 저쩌구 ex) 1667367607372
            String storedFileName = System.currentTimeMillis() + "-" + originalFilename; // 3. ex) storedFileName = 1667367607372-그림1.png
            System.out.println("storedFileName = " + storedFileName);
            boardDTO.setOriginalFileName(originalFilename); // 4.
            boardDTO.setStoredFileName(storedFileName); // 4.
            String savePath = "D:\\spring_img\\" + storedFileName; // 5.
            boardFile.transferTo(new File(savePath)); // 6. 빨간 줄 뜨면 add exception~ 클릭
            boardDTO.setFileAttached(1); // 플래그? 파일이 있냐
            BoardDTO savedBoard = boardRepository.save(boardDTO); // 7.
            boardRepository.saveFileName(savedBoard);
        } else {
            System.out.println("파일없음");
            boardDTO.setFileAttached(0); // 파일이 없냐
            boardRepository.save(boardDTO);
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

    // 수정 처리
    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    // 삭제 처리
    public void delete(Long id) {
        boardRepository.delete(id);
    }
}

