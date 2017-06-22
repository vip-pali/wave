package com.wave.action;

import java.io.File;
import com.wave.constants.WaveConstants;
import com.wave.daoInterfaces.UserMasterManager;
import com.opensymphony.xwork2.ActionContext;

/**
 * user logout method 
 */
public class UserLogout extends BaseAction {

	
	private static final long serialVersionUID = -1991967657434410555L;
	private String rndToken;
	private UserMasterManager userMasterManager;	

	@Override
	public String execute() throws Exception
	{
		try {
			
				this.session=ActionContext.getContext().getSession();
				String userId = (String)session.get(WaveConstants.USER_ID);			
				//String directory =FileUtil.getserverLocationPath()+userId ;

				//File f1 = new File(directory);
				//deleteDir(f1);
				removeUser();

				return SUCCESS;			

		}catch(Exception e){
			e.printStackTrace();
		}

		return ERROR;
	}
	
	
		
	
	public UserMasterManager getUserMasterManager() {
		return userMasterManager;
	}
	
	public void setUserMasterManager(UserMasterManager userMasterManager) {
		this.userMasterManager = userMasterManager;
	}
	
	public String getRndToken() {
		return rndToken;
	}
	public void setRndToken(String rndToken) {
		this.rndToken = rndToken;
	}

	
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {			
			String[] children = dir.list();
			
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}
}
