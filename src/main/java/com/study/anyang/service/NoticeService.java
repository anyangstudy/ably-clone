package com.study.anyang.service;

import java.util.List;

import com.study.anyang.repository.NoticeRepository;
import com.study.anyang.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository repository;

	public void register(Notice notice) throws Exception {
		repository.save(notice);
	}

	public Notice read(Long noticeNo) throws Exception {
		return repository.getOne(noticeNo);
	}

	public void modify(Notice notice) throws Exception {
		Notice noticeEntity = repository.getOne(notice.getNoticeNo());

		noticeEntity.setTitle(notice.getTitle());
		
		repository.save(noticeEntity);
	}

	public void remove(Long noticeNo) throws Exception {
		repository.deleteById(noticeNo);
	}

	public List<Notice> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "noticeNo"));
	}
	
}
