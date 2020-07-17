package com.study.anyang.service;

import com.study.anyang.domain.Board;
import com.study.anyang.repository.BoardRepository;
import com.study.anyang.vo.PageRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository repository;

    public void register(Board board) throws Exception {
        repository.save(board);
    }

    public Board read(Long boardNo) throws Exception {
        return repository.getOne(boardNo);
    }

    public void modify(Board board) throws Exception {
        Board boardEntity = repository.getOne(board.getBoardNo());

        boardEntity.setTitle(board.getTitle());
        boardEntity.setContent(board.getContent());

        repository.save(boardEntity);
    }

    public void remove(Long boardNo) throws Exception {
        repository.deleteById(boardNo);
    }

    public Page<Board> list(PageRequestVO pageRequestVO) throws Exception {
        String searchType = pageRequestVO.getSearchType();
        String keyword = pageRequestVO.getKeyword();

        int pageNumber = pageRequestVO.getPage() - 1;
        int sizePerPage = pageRequestVO.getSizePerPage();

        Pageable pageRequest = PageRequest.of(pageNumber, sizePerPage, Sort.Direction.DESC, "boardNo");

        return repository.getSearchPage(searchType, keyword, pageRequest);
    }

}
