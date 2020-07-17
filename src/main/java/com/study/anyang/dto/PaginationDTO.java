package com.study.anyang.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;

@Getter
public class PaginationDTO<T> {
	
	private Page<T> page;
	
	private Pageable prevPageable;
	private Pageable nextPageable;
	
	private int currentPageNumber;
	private int totalPageCount; 
	
	private Pageable currentPageable;
	
	private List<Pageable> pageableList;
	
	public PaginationDTO(Page<T> page){
		this.page = page;
		
		this.currentPageable = page.getPageable();
		
		this.currentPageNumber = this.currentPageable.getPageNumber() + 1; 
		
		this.totalPageCount = page.getTotalPages();
		
		this.pageableList = new ArrayList<>();
		
		calcPages();
	}
	
	private void calcPages(){
		int endPageNumber = (int)(Math.ceil(this.currentPageNumber/10.0)* 10);
		
		int startPageNumber = endPageNumber - 9; 
		
		Pageable startPageable = this.currentPageable;
		
		for(int i = startPageNumber; i < this.currentPageNumber; i++){
			startPageable = startPageable.previousOrFirst();
		}
		
		this.prevPageable = startPageable.getPageNumber() <= 0 ? null : startPageable.previousOrFirst();
		
		if(this.totalPageCount < endPageNumber){
			endPageNumber = this.totalPageCount;
			this.nextPageable = null;
		}
		
		for(int i = startPageNumber ; i <= endPageNumber; i++){
			pageableList.add(startPageable);
			startPageable = startPageable.next();
		}
		
		this.nextPageable = startPageable.getPageNumber() + 1 < totalPageCount ? startPageable : null;
	}

	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.build();
	
		return uriComponents.toUriString();
	}

}
