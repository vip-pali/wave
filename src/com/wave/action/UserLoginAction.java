package com.wave.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.struts2.dispatcher.SessionMap;

import com.wave.constants.WaveConstants;
import com.wave.daoInterfaces.UserMasterManager;
import com.wave.pojo.UserMaster;
import com.wave.utils.MD5HashEncryption;
import com.wave.utils.Util;
import com.opensymphony.xwork2.ActionContext;

public class UserLoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3148826956066017413L;
	private String userId;
	private String rndToken;
	private String password;
	private String captcha;
	private String userType;
	private String emailId;

	private UserMasterManager userMasterManager;
	private UserMaster userMaster;
	private List<UserMaster> userMasterList;




	@Override
	public String execute() throws Exception
	{
		InputStream userIdPropertiesIS = Util.class.getResourceAsStream("/userId.properties");
		Properties configProp = new Properties();

		try {
			
			configProp.load(userIdPropertiesIS);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		//String adminUserId = configProp.getProperty("wave.adminuserid");
					
		userMasterList = userMasterManager.findByEmailId(userId);
		System.out.println(userMaster);

		if(userMasterList.size()>0){
			userMaster = userMasterList.get(0);
		}

		if(userMasterList.size()>0){

			if(userMaster.getActiveStatus() == 0)
			{
				addActionError("Account is not Activated");
				captcha="";
				return INPUT;
			
			}
		}else{

			captcha="";
			addActionError("Invalid UserId or Password");
			((SessionMap) this.session).invalidate();
			session.put("AUTHENTICATED", new Boolean(true));

			userId = "";
			rndToken = "" + Util.generateRandomNumber();
			session.put("rndToken", rndToken);
			return INPUT;
		}


		if (userMaster != null)
		{
			if (password.equals(MD5HashEncryption.MD5(getServletRequest().getSession().getAttribute("rndToken") + userMaster.getPassword())))
			{

				// re-initiating session id
				this.refreshSession();
				String returnType = null;

				//if data-user password matched!
				try
				{
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.MINUTE, 15);					

					session.put(WaveConstants.USER_ID, userMaster.getEmail());
					session.put(WaveConstants.USER_MASTER, userMaster);
					
					if(userMaster.getRoleId() == 1){
						
						session.put("userType", WaveConstants.ROLE_ADMIN);
						returnType = "adminUser";
					
					}else{
						
						session.put("userType", WaveConstants.ROLE_GENERAL);
						returnType = "centerUser";
					}
					//session.put(WaveConstants.USER_ROLE_ID, RoleConstants.USER_ROLE);				
					
					

					rndToken = "" + Util.generateRandomNumber();
					session.put("rndToken", rndToken);
					
					return returnType;

				}catch (Exception e)
				{
					e.printStackTrace();
				}

			}else{

				//if data-user password do not matched!

				addActionError("Invalid UserId or Password");
				captcha="";
				((SessionMap) this.session).invalidate();
				this.session = ActionContext.getContext().getSession();
				session.put("AUTHENTICATED", new Boolean(true));

				userId = "";
				rndToken = "" + Util.generateRandomNumber();
				getServletRequest().getSession().setAttribute("rndToken", rndToken);
				return INPUT;

			}
		}

		return SUCCESS;
	}

	public void refreshSession()
	{
		SessionMap session = (SessionMap) ActionContext.getContext().getSession();

		//invalidate
		session.invalidate();

		//renew servlet session
		session.put("renewServletSession", null);
		session.remove("renewServletSession");

		//populate the struts session
		session.entrySet();
	}



	@Override
	public void validate()
	{

		//String captchaKey = (String) getSession().get(WaveConstants.CAPTCHA_KEY);

		int i = 0;
		try
		{

			if ((userId == null) || userId.length() == 0)
			{
				addActionError("Email Id is required");
				i++;
			}
			
			if(!userId.contains("@")){
				
				addActionError("Invalid Email Id");
				i++;
			}
			
			if ((password == null) || password.length() == 0)
			{
				addActionError("Password is required");
				i++;
			}
			/*if ((captcha == null) || captcha.length() == 0)
			{
				addActionError("Captcha Code is required");
				i++;
			}

			if (!captcha.equals(captchaKey) && captcha.length() > 0)
			{
				addActionError("Invalid Captcha Code");
				i++;
				captcha="";
			}*/
			if (i > 0)
			{
				((SessionMap) this.session).invalidate();
				this.session = ActionContext.getContext().getSession();
				session.put("AUTHENTICATED", new Boolean(true));

				userId = "";
				rndToken = "" + Util.generateRandomNumber();
				getServletRequest().getSession().setAttribute("rndToken", rndToken);

			}
			i = 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		getServletRequest().setAttribute("ActionErrors", getActionErrors());
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the rndToken
	 */
	public String getRndToken() {
		return rndToken;
	}

	/**
	 * @param rndToken the rndToken to set
	 */
	public void setRndToken(String rndToken) {
		this.rndToken = rndToken;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the userMasterManager
	 */
	public UserMasterManager getUserMasterManager() {
		return userMasterManager;
	}

	/**
	 * @param userMasterManager the userMasterManager to set
	 */
	public void setUserMasterManager(UserMasterManager userMasterManager) {
		this.userMasterManager = userMasterManager;
	}

	/**
	 * @return the userMaster
	 */
	public UserMaster getUserMaster() {
		return userMaster;
	}

	/**
	 * @param userMaster the userMaster to set
	 */
	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	/**
	 * @return the userMasterList
	 */
	public List<UserMaster> getUserMasterList() {
		return userMasterList;
	}

	/**
	 * @param userMasterList the userMasterList to set
	 */
	public void setUserMasterList(List<UserMaster> userMasterList) {
		this.userMasterList = userMasterList;
	}

	














}
