package com.mycgv.vo;

import java.util.ArrayList;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CgvMovieVO {
	int rno;
	String mcategory, mname, mdesc, mfile1, mfile2, msfile1, msfile2, mdate, mid;
	CommonsMultipartFile[] files;
	ArrayList<String> mfiles = new ArrayList<String>();  //DB에 저장할 mfile 목록
	ArrayList<String> msfiles = new ArrayList<String>();  //DB에 저장할 msfile 목록
	
		
	public ArrayList<String> getMfiles() {
		return mfiles;
	}
	public void setMfiles(ArrayList<String> mfiles) {
		this.mfiles = mfiles;
	}
	
	public ArrayList<String> getMsfiles() {
		return msfiles;
	}
	public void setMsfiles(ArrayList<String> msfiles) {
		this.msfiles = msfiles;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getMcategory() {
		return mcategory;
	}
	public void setMcategory(String mcategory) {
		this.mcategory = mcategory;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMdesc() {
		return mdesc;
	}
	public void setMdesc(String mdesc) {
		this.mdesc = mdesc;
	}
	public String getMfile1() {
		return mfile1;
	}
	public void setMfile1(String mfile1) {
		this.mfile1 = mfile1;
	}
	public String getMfile2() {
		return mfile2;
	}
	public void setMfile2(String mfile2) {
		this.mfile2 = mfile2;
	}
	public String getMsfile1() {
		return msfile1;
	}
	public void setMsfile1(String msfile1) {
		this.msfile1 = msfile1;
	}
	public String getMsfile2() {
		return msfile2;
	}
	public void setMsfile2(String msfile2) {
		this.msfile2 = msfile2;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public CommonsMultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}
	
	
}
