package com.study.anyang.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="pds")
public class Pds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	private String itemName;

	private String description;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="item_id")
	private List<PdsFile> pdsFiles = new ArrayList<PdsFile>();	
	
	@Transient
	private String[] files;
	
	private Integer viewCnt = 0;
	
	@CreationTimestamp
	private Date regDate;
	@UpdateTimestamp
	private Date updDate;
	
	public void addItemFile(PdsFile itemFile) {
		pdsFiles.add(itemFile);
	}

	public void clearItemFile() {
		pdsFiles.clear();
	}
	
}
