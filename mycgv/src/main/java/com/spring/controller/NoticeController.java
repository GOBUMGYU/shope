package com.spring.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvNoticeVO;

@Controller
public class NoticeController {
	/**
	 * notice_list.do : �������� ��ü ����Ʈ 
	 */
	@RequestMapping(value="/notice_list.do", method=RequestMethod.GET)
	public String notice_list() {
		return "/notice/notice_list";
	}
	
	/**
	 * notice_list_json.do : �������� ��ü ����Ʈ�� Ajax�� ȣ��
	 */
	@ResponseBody
	@RequestMapping(value="notice_list_json.do", method = RequestMethod.GET
					, produces="text/plain; charset=UTF-8")
	public String notice_list(String rpage) {
		//DB���� �������� ��ü ����Ʈ ��������
		CgvNoticeDAO dao = new CgvNoticeDAO();

		//startCount, endCount
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = dao.totalCount();	//DB���� ������ ��ü ���
		
		//�� ������ �� ���
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}
		
		//��û ������ ���
		if(rpage != null){
			reqPage = Integer.parseInt(rpage);
			startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}


		ArrayList<CgvNoticeVO> list = dao.select(startCount,endCount);
		
		//{list:[{rno:1,ntitle:"ž��~",ndate:"2022-08-01",nhits:100},
		//         {�ι�° vo ��},{�¹�° vo ��}...]}
			
		//gson ���̺귯���� �̿��Ͽ� �ڹ� list ��ü�� JSON ��ü�� ��ȯ
		JsonObject jobject = new JsonObject(); //CgvNoticeVO
		JsonArray jarray = new JsonArray();  //ArrayList
		Gson gson = new Gson();
		
		for(CgvNoticeVO vo : list){
			JsonObject jo = new JsonObject();
			jo.addProperty("rno", vo.getRno());
			jo.addProperty("nid", vo.getNid());
			jo.addProperty("ntitle", vo.getNtitle());
			jo.addProperty("ndate", vo.getNdate());
			jo.addProperty("nhits", vo.getNhits());
			
			jarray.add(jo);
		}// [{rno:1,ntitle:��վ��,ndate:2022-08-01,nhits:100},... ]
		
		jobject.add("list", jarray); 
		jobject.addProperty("dbCount", dbCount);
		jobject.addProperty("pageSize", pageSize);
		jobject.addProperty("rpage", reqPage);
		//{list:[{rno:1,ntitle:��վ��,ndate:2022-08-01,nhits:100},. ],
		// dbCount:10, rpage:1, pageSize:5 	
		// }
//		out.write(gson.toJson(jobject));
		return gson.toJson(jobject);
	}
	
	/**
	 * notice_content_json.do : 
	 */
	@ResponseBody
	@RequestMapping(value = "notice_content_json.do", method = RequestMethod.GET
						, produces="text/plain; charset=UTF-8")
	public String notice_content_json(String nid) {
		
		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = dao.select(nid);
		
		if(vo != null){
			dao.updateHits(nid);
		}
		
		Gson gson = new Gson();
		JsonObject jobject = new JsonObject();
		jobject.addProperty("nid", vo.getNid());
		jobject.addProperty("ntitle", vo.getNtitle());
		jobject.addProperty("ncontent", vo.getNcontent());
		jobject.addProperty("nhits", vo.getNhits());
		jobject.addProperty("ndate", vo.getNdate());
		

		//out.write(gson.toJson(jobject));
		return gson.toJson(jobject);
	}
		
}
