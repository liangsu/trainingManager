package edu.lsnu.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class abc extends TagSupport{

	private String href;
	private String cssClass;
	
	@Override
	public int doStartTag() throws JspException {
		System.out.println("============ doStartTag ==============");
		System.out.println("href:"+href);
		if(href.equals("SKIP_PAGE")){
			return SKIP_PAGE;
		}else if(href.equals("SKIP_BODY")){
			return SKIP_BODY;
		}else if(href.equals("EVAL_BODY_AGAIN")){
			return EVAL_BODY_AGAIN;
		}else if(href.equals("EVAL_BODY_INCLUDE")){
			return EVAL_BODY_INCLUDE;
		}
		return EVAL_PAGE;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
}
