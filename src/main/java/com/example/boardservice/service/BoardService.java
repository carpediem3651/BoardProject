package com.example.boardservice.service;

import com.example.boardservice.dao.BoardDao;
import com.example.boardservice.dto.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardDao boardDao;
    @Transactional
    public void addBoard(int userId, String title, String content) {
        boardDao.addBoard(userId, title, content);
    }

    @Transactional(readOnly = true) // 조회 할 때는 readOnly = true로 해주면 성능이 올라간다.
    public int getTotalCount() {
        return boardDao.getTotalCount();
    }

    @Transactional(readOnly = true)
    public List<Board> getBoards(int page) {
        return boardDao.getBoards(page);
    }
    @Transactional
    public Board getBoard(int boardId) {
        return getBoard(boardId, true);
    }

//    updateViewCnt가 true면 글의 조회수 증가, false면 글의 조회수 증가x
    @Transactional
    public Board getBoard(int boardId, boolean updateViewCnt) {
        Board board = boardDao.getBoard(boardId);
        if(updateViewCnt) {
            boardDao.updateViewCnt(boardId);
        }
        return board;
    }

    @Transactional
    public void deleteBoard(int userId, int boardId) {
        Board board = boardDao.getBoard(boardId);
        if (board.getUserId() == userId) {
            boardDao.deleteBoard(boardId);
        }
    }

    @Transactional
    public void deleteBoard(int boardId) {
        boardDao.deleteBoard(boardId);
    }

    @Transactional
    public void updateBoard(int boardId, String title, String content) {
        boardDao.updateBoard(boardId, title, content);
    }
}
