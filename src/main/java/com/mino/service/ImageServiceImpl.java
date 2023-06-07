package com.mino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mino.domain.ImageVO;
import com.mino.mapper.ImageMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	public List<ImageVO> getImageList(int itemId) {
		
		return imageMapper.getImageList(itemId);
	}
	

}
