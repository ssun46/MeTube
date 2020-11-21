package com.dev.metube.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dev.metube.mapper.VoteMapper;
import com.dev.metube.model.LoginUserDetails;
import com.dev.metube.model.Vote;

@Service
public class VoteService {

	@Autowired
	VoteMapper voteMapper;
	
	public boolean checkVoted(Vote vote) {
		if(vote != null) {	
			try {
				return voteMapper.selectExist(vote);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.getStackTrace();
			}
		}
		return false;
	}
	
	public Map<String, Object> votes(Vote vote) {
		Map<String, Object> rtnMap = new HashMap<>();
		if(vote == null || vote.getVideo_id() == null) {
			rtnMap.put("result", false);
			rtnMap.put("msg", "콘텐츠 ID는 NULL일 수 없습니다.");
			return rtnMap;
		}
		
		try {
			LoginUserDetails userDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			vote.setUser_id(userDetails.getNo());
			if(checkVoted(vote)) {
				rtnMap.put("result", false);
				rtnMap.put("msg", "이미 투표한 콘텐츠입니다.");
				return rtnMap;
			}
			
			if(voteMapper.insert(vote) < 1) {
				rtnMap.put("result", false);
				rtnMap.put("msg", "투표된 데이터가 없습니다.");
				return rtnMap;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
			rtnMap.put("result", false);
			rtnMap.put("msg", "쿼리 조회중 오류가 발생했습니다.");
			return rtnMap;
		}
		rtnMap.put("result", true);
		return rtnMap;
	}
	
	public Map<String, Object> cancel(Vote vote) {
		Map<String, Object> rtnMap = new HashMap<>();
		if(vote == null || vote.getVideo_id() == null) {
			rtnMap.put("result", false);
			rtnMap.put("msg", "콘텐츠 ID는 NULL일 수 없습니다.");
			return rtnMap;
		}
		
		try {
			LoginUserDetails userDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			vote.setUser_id(userDetails.getNo());
			if(!checkVoted(vote)) {
				rtnMap.put("result", false);
				rtnMap.put("msg", "투표 기록이 없습니다.");
				return rtnMap;
			}
			
			if(voteMapper.delete(vote) < 1) {
				rtnMap.put("result", false);
				rtnMap.put("msg", "투표 취소된 데이터가 없습니다.");
				return rtnMap;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
			rtnMap.put("result", false);
			rtnMap.put("msg", "쿼리 조회중 오류가 발생했습니다.");
			return rtnMap;
		}
		rtnMap.put("result", true);
		return rtnMap;
	}
}
