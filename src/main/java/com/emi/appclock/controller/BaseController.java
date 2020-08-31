package com.emi.appclock.controller;

import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class BaseController {
	protected final static Logger logger = Logger.getLogger(BaseController.class);
	protected int pageNo =1;
	public static  int pageSize = 10;
	public static String URL404 =  "/404.html";
	private final static String PARAM_PAGE_NO = "pageNo";

	protected String pageSizeName = "pageSize";

	@Autowired
	public HttpServletRequest request;
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		BaseController.pageSize = pageSize;
	}

	public ModelAndView redirect(String redirectUrl, Map<String,Object>...parament){
		ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
		if(null != parament && parament.length > 0){
			view.addAllObjects(parament[0]);
		}
		return view;
	}
	public ModelAndView redirect404(){
		return new ModelAndView(new RedirectView(URL404));
	}
}
