package com.study.anyang.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="logNo")
@ToString
@Entity
@Table(name="access_log")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logNo;

    @Column(length = 200, nullable = false)
    private String requestUri;

    @Column(length = 100, nullable = false)
    private String className;

    @Column(length = 50, nullable = false)
    private String classSimpleName;

    @Column(length = 100, nullable = false)
    private String methodName;

    @Column(length = 50, nullable = false)
    private String remoteAddr;

    @CreationTimestamp
    private Date regDate;
    @UpdateTimestamp
    private Date updDate;

}
