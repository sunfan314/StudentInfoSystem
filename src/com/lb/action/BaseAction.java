package com.lb.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author LiBing
 * @usage	可用于需要设置会话与请求的场景
 */
@SuppressWarnings("all")
public class BaseAction extends ActionSupport implements SessionAware,ServletRequestAware {

	protected Map<String,Object> session;
	protected HttpServletRequest request;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
   
}
