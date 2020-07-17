package com.study.anyang.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="userItemNo")
@ToString
@Entity
@Table(name="user_item")
public class UserItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userItemNo;
	
	private Long userNo;
	private Long itemId;
	
	@Transient
	private String itemName;
	@Transient
	private Integer price;
	@Transient
	private String description;
	@Transient
	private String pictureUrl;
	
	@CreationTimestamp
	private Date regDate;
	@UpdateTimestamp
	private Date updDate;

}
