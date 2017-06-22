package com.wave.action;

import com.wave.constants.WaveConstants;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.aspectj.lang.annotation.Aspect;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Aspect
public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1845075806458173048L;

	public Map<String, Object> session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//private Logging logging;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}


	public void setServletResponse(HttpServletResponse response) {

		this.response = response;

		


		if(getSession()==null)
		{
			//System.out.println("session is null");
			
		}
		else if(null!= getSession().get(WaveConstants.USER_ID))
		{
			


			// Set to expire far in the past.
			this.response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");

			// Set standard HTTP/1.1 no-cache headers.
			this.response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

			// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			this.response.addHeader("Cache-Control", "post-check=0, pre-check=0");

			// Set standard HTTP/1.0 no-cache header.
			this.response.setHeader("Pragma", "no-cache");

			/*}*/

		}
		else if(null!=getSession() && null == getSession().get(WaveConstants.USER_ID))
		{

			//System.out.println("session is not null and outside login area");

		}
		else if( null == getSession().get(WaveConstants.USER_ID))
		{

			//	System.out.println("session is null and inside login area : redirecting to home page");
			try {
				this.response.sendRedirect("/wave");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}

	public HttpServletResponse getServletResponse() {
		return this.response;
	}

	public void setSession(Map session){
		session = this.getSession();
	}

	public Map getSession(){

		this.session=ActionContext.getContext().getSession();
		return session;
	}

	public void removeUser() {

		if(session.get(WaveConstants.USER_MASTER)!=null){
			getSession().remove(WaveConstants.USER_MASTER);
		}

		setSession(null);

		((SessionMap)this.session).invalidate();

		/*HttpSession session=request.getSession(false);

		session.invalidate();
		session=null;*/

		response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
		response.setHeader("Cache-Control","must-revalidate");
		response.setHeader("Expires","0");
		response.setDateHeader("Expires", -1); //Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility


	}



	

	

}
