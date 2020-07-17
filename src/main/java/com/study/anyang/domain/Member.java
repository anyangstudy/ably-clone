package com.study.anyang.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@EqualsAndHashCode(of="userNo")
@ToString
@Entity
@Table(name="member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String userId;

    @NotBlank
    @Column(length = 200, nullable = false)
    private String userPw;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String userName;

    private String job;

    private int coin;

    @CreationTimestamp
    private Date regDate;
    @UpdateTimestamp
    private Date updDate;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_no")
    private List<MemberAuth> authList = new ArrayList<MemberAuth>();

    public void addAuth(MemberAuth auth) {
        authList.add(auth);
    }

    public void clearAuthList() {
        authList.clear();
    }

}
