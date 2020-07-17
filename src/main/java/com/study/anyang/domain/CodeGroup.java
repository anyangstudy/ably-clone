package com.study.anyang.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "codeDetails")
@Entity
@EqualsAndHashCode(of="groupCode")
@Table(name="code_group")
public class CodeGroup {

	@Id
	@Column(length = 3)
	private String groupCode;
	
	@Column(length = 30, nullable = false)
	private String groupName;
	
	@Column(length = 1)
	private String useYn = "Y";
	
	@CreationTimestamp
	private Date regDate;
	@UpdateTimestamp
	private Date updDate;

	@OneToMany
	@JoinColumn(name="groupCode")
	private List<CodeDetail> codeDetails;
	
}
