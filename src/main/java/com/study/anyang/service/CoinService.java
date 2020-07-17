package com.study.anyang.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.study.anyang.domain.ChargeCoin;
import com.study.anyang.domain.PayCoin;
import com.study.anyang.repository.ChargeCoinRepository;
import com.study.anyang.repository.MemberRepository;
import com.study.anyang.repository.PayCoinRepository;
import com.study.anyang.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CoinService {

	@Autowired
	private ChargeCoinRepository chargeCoinRepository;
	
	@Autowired
	private PayCoinRepository payCoinRepository;
	
	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	public void charge(ChargeCoin chargeCoin) throws Exception {
		Member memberEntity = memberRepository.getOne(chargeCoin.getUserNo());
		
		int coin = memberEntity.getCoin();
		int amount = chargeCoin.getAmount();
		
		memberEntity.setCoin(coin + amount);
	
		memberRepository.save(memberEntity);
		
		chargeCoinRepository.save(chargeCoin);
	}
	
	public List<ChargeCoin> list(Long userNo) throws Exception {
		return chargeCoinRepository.findAll(Sort.by(Direction.DESC, "historyNo"));
	}
	
	public List<PayCoin> listPayHistory(Long userNo) throws Exception {
		List<Object[]> valueArrays = payCoinRepository.listPayHistory(userNo);
		
		List<PayCoin> payCoinList = new ArrayList<PayCoin>();
		for(Object[] valueArray : valueArrays) {
			PayCoin payCoin = new PayCoin();
			
			payCoin.setHistoryNo((Long)valueArray[0]);
			payCoin.setUserNo((Long)valueArray[1]);
			payCoin.setItemId((Long)valueArray[2]);
			payCoin.setItemName((String)valueArray[3]);
			payCoin.setAmount((int)valueArray[4]);
			payCoin.setRegDate((Date)valueArray[5]);
			
			payCoinList.add(payCoin);
		}
		
		return payCoinList;
	}
	
}
