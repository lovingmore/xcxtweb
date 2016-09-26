package com.sh.util;

import java.util.HashMap;

public class Pager {

	public static int PAGE_SIZE = 10;

	private int size = 10;

	private int pages;

	private int count;

	public Pager(int count) {
		super();
		this.count = count;
	}

	public Pager(int size, int count) {
		super();
		this.size = size;
		this.count = count;
	}

	public int getPages() {
		if (count % size != 0) {
			this.pages = (count / size) + 1;
		}else{
			this.pages = (count / size);
		}
		
		return pages;
	}

	public static void main(String[] args) {
		Pager page = new Pager(15);
		System.out.println(page.getPages());
	}
	
	public static HashMap<String,Integer> isExistsPage(int pageId, int pageSize,int dataCount) {
		int tempCount = 0;
		int nextflag = 0;//0：不出那种下一页，1：存在下一页。
		int lastflag = 0;//0：不出那种下一页，1：存在下一页。
		//pageSize=10;dataCount=25;pageId=1;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if(dataCount>pageSize){
			tempCount = pageId*pageSize;
			if(dataCount>tempCount){
				if(pageId==1){
					nextflag=1;
					lastflag=0;
					map.put("nextflag", nextflag);
					map.put("lastflag", lastflag);
					map.put("pageId", pageId);
				}else{
					nextflag=1;
					lastflag=1;
					map.put("nextflag", nextflag);
					map.put("lastflag", lastflag);
					map.put("pageId", pageId);
				}
			}else{
				nextflag=0;
				lastflag=1;
				map.put("nextflag", nextflag);
				map.put("lastflag", lastflag);
				map.put("pageId", pageId);
			}			
		}else{
			nextflag=0;
			lastflag=0;
			map.put("nextflag", nextflag);
			map.put("lastflag", lastflag);
			map.put("pageId", pageId);
		}
		return map;
		
	}
}
