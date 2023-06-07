package com.mino.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mino.domain.ImageVO;
import com.mino.mapper.AdminMapper;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class ImageCheckTask {
	
	@Autowired
	private AdminMapper adminMapper;
	
	private String getFolderYesterDay() {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Calendar cal = Calendar.getInstance();
			
			cal.add(Calendar.DATE, -1);
			
			String str = sdf.format(cal.getTime());
			
			return str.replace("-", File.separator);
		}	
	
	@Scheduled(cron="0 0 1 * * *") // 새벽1시마다 어제자 파일중 db에 정보없는 이미지 파일 삭제
	public void checkFiles() throws Exception{	
		
		log.warn("File Check Task Run..........");
		log.warn(new Date());
		log.warn("========================================");		
		
		// DB에 저장된 파일 리스트
		List<ImageVO> fileList = adminMapper.checkFileList();		
		
		
		// 비교 기준 파일 리스트(Path객체)
		List<Path> checkFilePath = new ArrayList<Path>();
			//원본 이미지
		fileList.forEach(item -> {
			Path path = Paths.get("C:\\Users\\PC\\Desktop\\upload", item.getUploadPath(), item.getUuid() + "_" + item.getFileName());
			checkFilePath.add(path);
		});		
			//썸네일 이미지
		fileList.forEach(item -> {
			Path path = Paths.get("C:\\Users\\PC\\Desktop\\upload", item.getUploadPath(), "s_" +  item.getUuid() + "_" +item.getFileName());
			checkFilePath.add(path);
		});
		
		
		// 디렉토리 파일 리스트
		File targetDir = Paths.get("C:\\Users\\PC\\Desktop\\upload", getFolderYesterDay()).toFile();
		File[] targetFile = targetDir.listFiles();
		
		
		// 삭제 대상 파일 리스트(분류)
		List<File> removeFileList = new ArrayList<File>(Arrays.asList(targetFile));		
		for(File file : targetFile){
			checkFilePath.forEach(checkFile ->{
				if(file.toPath().equals(checkFile)) 
					removeFileList.remove(file);	
			});
		}
		
		
		// 삭제 대상 파일 제거
		log.warn("file Delete : ");
		for(File file : removeFileList) {
			log.warn(file);
			file.delete();
		}		
		
		log.warn("========================================");
		
	}

}
