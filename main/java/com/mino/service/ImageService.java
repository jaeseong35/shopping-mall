package com.mino.service;

import java.util.List;

import com.mino.domain.ImageVO;

public interface ImageService {
	
	public List<ImageVO> getImageList(int itemId);

}
