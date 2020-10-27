package com.dev.metube.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dev.metube.mapper.VideoMapper;
import com.dev.metube.model.LoginUserDetails;
import com.dev.metube.model.ResultList;
import com.dev.metube.model.SearchBase;
import com.dev.metube.model.Video;

@Service
public class ContentsService {
	
	@Autowired
	VideoMapper videoMapper;
	
	protected SearchBase searchBase = new SearchBase();
	protected ResultList resultList= new ResultList();
	
	public int insertFile(MultipartFile file, Video video) {
		if(file != null) {
			try {
				LoginUserDetails userDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				video.setRegUser(userDetails.getNo());
				videoMapper.insertFile(video);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return 0;
			}
		}
		return video.getId();
	}
	
	public String getPath(Integer id) {
		String path = "";
		
		if(id == null) {
			return null;
		}
		
		try {
			path = videoMapper.selectPathById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return path;
	}
	
	public Map<String, Object> updateContent(Video video) {
		Map<String, Object> result = new HashMap<>();
		try {
			int updated = videoMapper.update(video);
			if(updated < 1) {
				result.put("result", false);
				result.put("msg", "정상적으로 컨텐츠를 등록하는데 실패했습니다.");
				return result;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			result.put("result", false);
			result.put("msg", "컨텐츠 등록중 오류가 발생했습니다.");
			return result;
		}
		result.put("result", true);
		return result;
	}
	
	public Map<String, Object> getContentsListByGoPublic(String keyword, Integer currentIndex) {
		Map<String, Object> result = new HashMap<>();
		if(currentIndex == null) {
			result.put("result", false);
			result.put("msg", "페이징 정보는 NULL일 수 없습니다.");
			return result;
		}
		try {
			searchBase.setCurrentIndex(currentIndex);
			searchBase.setKeyword(keyword);
			resultList.setCurrentIndex(currentIndex);
			resultList.setTotalCount(videoMapper.selectContentsCountByPublicYes(searchBase));
			resultList.setResultList(videoMapper.selectCoutentsListByPublicYes(searchBase));
			result.put("data", resultList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			result.put("result", false);
			result.put("msg", "컨텐츠 조회중 오류가 발생했습니다.");
			return result;
		}
		result.put("result", true);
		return result;
	}
	
	public Video getContentsVideo(Integer id) {
		Video video = null;
		if(id == null) {
			return null;
		}
		
		try {
			video = videoMapper.selectVideoById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return video;
	}
}
