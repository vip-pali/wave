package com.wave.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import com.wave.utils.MD5HashEncryption;

import com.wave.constants.WaveConstants;
import com.wave.daoInterfaces.RefCenterManager;
import com.wave.daoInterfaces.RefCourseManager;
import com.wave.daoInterfaces.RefDistrictManager;
import com.wave.daoInterfaces.RefStateManager;
import com.wave.daoInterfaces.StudentPaymentDetailsManager;
import com.wave.daoInterfaces.UserMasterManager;
import com.wave.pojo.RefCenter;
import com.wave.pojo.RefCourse;
import com.wave.pojo.RefDistrict;
//import com.visaka.utils.Util;
import com.wave.pojo.RefState;
import com.wave.pojo.StudentPaymentDetails;
import com.wave.pojo.UserMaster;
import com.wave.utils.BaseException;


public class UserAction extends BaseAction implements RequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4852498492719889107L;
	protected Map<String, Object> request;
	private int rndToken;
	private String rndCaptcha;
	private RefStateManager refStateManager;
	private RefDistrictManager refDistrictManager;
	private RefCenterManager refCenterManager;
	private UserMasterManager userMasterManager;
	private RefCourseManager refCourseManager;
	private StudentPaymentDetailsManager studentPaymentDetailsManager;
	private List<RefState> refStateList;
	private List<RefDistrict> refDistrictList;
	private List<RefCenter> refCenterList;
	private List<RefCourse> refCourseList;
	private String stateId;
	private String districtCodeId;
	private String centerName;
	private String centerCode;
	private String flag="";	
	private Integer pageSize;
	private Integer totalCountOfForms;
	public String centerId;
	private String centerUserEmailId;
	private String password;
	private StudentPaymentDetails studentPaymentDetails;
	private List<StudentPaymentDetails> studentPaymentDetailsList;
	private String generatedCenterId;
	private Integer courseId;
	private Integer studentPaymentId;
	private String adminEmailId;
	private RefCenter refCenter;
	 
	
	@Override
	public String execute()
	{
		return SUCCESS;
	}

	public String home() throws Exception
	{
		if (session.get(WaveConstants.USER_ID) != null)
		{
			return "userhome";
		}
		

		//rndToken = Util.generateRandomNumber();
		session.put("rndToken", rndToken);
		//setRndCaptcha("" + Util.generateRandomNumber4digit());

		return SUCCESS;
	}
	
	// take user to view all the available centers
	public String gotoViewCenters() {
			
			try {
				
				refStateList = refStateManager.findDistinctStateBasedOnCenter();
				refDistrictList = new ArrayList<RefDistrict>();
				request.put("viewCenter", "yes");
								
				if(refStateList.size() == 0){					
					
					addActionError("No center has been created yet, create center first");
				
				}
				
				return SUCCESS;
				
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ERROR;
	}
	
	public String viewCenters() {
		
		
		int i = 0;
		
		try {
		
		if(null == stateId || "-1".equals(stateId)){
			
			addActionError("Please select state");
			i++;
			
		}		
		
		
		if (i > 0){
			
			refStateList = refStateManager.findDistinctStateBasedOnCenter();
			refDistrictList = refDistrictManager.findDistricts(stateId);
			
			request.put("viewCenter", "yes");
			
			return INPUT;
		}
				
		setFlag("true");
		setPageSize(20);	
		
		if(null != stateId && "-1".equals(districtCodeId)){
			
			refCenterList = refCenterManager.findByStateId(stateId);
		
		}else if(null != stateId && null != districtCodeId){
			
			refCenterList = refCenterManager.findByStateIdAndDistrictId(stateId, districtCodeId);			
		}		
			
			if(refCenterList.size() > 0){						
				
				request.put("yesRecordExist", "yesRecordExist");
				request.put("viewCenter", "yes");
				
				refStateList = refStateManager.findDistinctStateBasedOnCenter();
				refDistrictList = refDistrictManager.findDistricts(stateId);
								
				setTotalCountOfForms(refCenterList.size());
				
				return SUCCESS;
				
			}			
			
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return ERROR;
	}
	
	public String gotoCenterUserPasswordPage() {
		
		try {
			
			refStateList = refStateManager.findDistinctStateBasedOnCenter();
			refDistrictList = new ArrayList<RefDistrict>();
			request.put("viewCenter", "yes");
							
			if(refStateList.size() == 0){					
				
				addActionError("No center has been created yet, create center first");
			
			}
			
			return SUCCESS;
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ERROR;
}
	
	public String fetchCenterUserPassword(){		
		
		int i = 0;
		
		try {
		
		if(null == stateId || "-1".equals(stateId)){
			
			addActionError("Please select state");
			i++;
			
		}		
		
		
		if (i > 0){
			
			refStateList = refStateManager.findDistinctStateBasedOnCenter();
			refDistrictList = refDistrictManager.findDistricts(stateId);
			
			request.put("viewCenter", "yes");
			
			return INPUT;
		}
				
		setFlag("true");
		setPageSize(20);	
		
		if(null != stateId && "-1".equals(districtCodeId)){
			
			refCenterList = refCenterManager.findByStateId(stateId);
		
		}else if(null != stateId && null != districtCodeId){
			
			refCenterList = refCenterManager.findByStateIdAndDistrictId(stateId, districtCodeId);			
		}		
			
			if(refCenterList.size() > 0){						
				
				request.put("yesRecordExist", "yesRecordExist");
				request.put("viewCenter", "yes");
				
				refStateList = refStateManager.findDistinctStateBasedOnCenter();
				refDistrictList = refDistrictManager.findDistricts(stateId);
								
				setTotalCountOfForms(refCenterList.size());
				
				return SUCCESS;
				
			}			
			
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return ERROR;
		
	}
	
	public String changeCenterUserPassword() {
		
		//request.put("viewCenter", "yes");
		request.put("enableChangePasswordPanel", "yes");
		
		//refStateList = new ArrayList<RefState>();
		//refDistrictList = new ArrayList<RefDistrict>();
		
		return SUCCESS;
	}
	
	public String saveCenterUserPassword(){
		
		System.out.println("Password is: "+password);
		System.out.println("Center id is: "+centerId);
		
		int i = 0;
		
		try {
			String newPassword = MD5HashEncryption.MD5(password);
			System.out.println("New Password is: "+newPassword);
			
			UserMaster um = userMasterManager.findByCenterId(Integer.parseInt(centerId));
			
			if(null != um){
				
				um.setPassword(newPassword);
				boolean res = false;
				//boolean res = userMasterManager.saveOrUpdate(um);		
				
				if(res){
					//password has been edited succesfully
					request.put("isPasswordEdited", "yes");
				
				}else{
					
					request.put("isPasswordEdited", "no");
				}
			}
		
		if(null == stateId || "-1".equals(stateId)){
			
			addActionError("Please select state");
			i++;
			
		}		
		
		
		if (i > 0){
			
			refStateList = refStateManager.findDistinctStateBasedOnCenter();
			refDistrictList = refDistrictManager.findDistricts(stateId);
			
			request.put("viewCenter", "yes");
			
			return INPUT;
		}
				
		setFlag("true");
		setPageSize(20);	
		
		if(null != stateId && "-1".equals(districtCodeId)){
			
			refCenterList = refCenterManager.findByStateId(stateId);
		
		}else if(null != stateId && null != districtCodeId){
			
			refCenterList = refCenterManager.findByStateIdAndDistrictId(stateId, districtCodeId);			
		}		
			
			if(refCenterList.size() > 0){						
				
				request.put("yesRecordExist", "yesRecordExist");
				request.put("viewCenter", "yes");
				
				refStateList = refStateManager.findDistinctStateBasedOnCenter();
				refDistrictList = refDistrictManager.findDistricts(stateId);
								
				setTotalCountOfForms(refCenterList.size());
				
				return SUCCESS;
				
			}			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return ERROR;
		
	}
	
	// take user to create center page
	public String gotoCreateCenter() {
		
		try {
			
			refStateList = refStateManager.findStateList();
			refDistrictList = new ArrayList<RefDistrict>();
			
			request.put("createCenter", "yes");
			
			return SUCCESS;
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ERROR;
	}
	
	// take user to create user's credential page
	public String gotoCreateUserCredential() {
		
		try {
			
			refStateList = refStateManager.findDistinctStateBasedOnCenter();
			refDistrictList = new ArrayList<RefDistrict>();
			refCenterList = new ArrayList<RefCenter>();
			
			if(refStateList.size() == 0){
				
				request.put("createCredential", "no");
				addActionError("No center has been created yet, create center first");
			
			}else{
				
				request.put("createCredential", "yes");
			}
			
			
			return SUCCESS;
									
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ERROR;
	}
	
	
	public String createUserCredential(){
		
		try {
			
		refStateList = refStateManager.findDistinctStateBasedOnCenter();
		refDistrictList = new ArrayList<RefDistrict>();
		refCenterList = new ArrayList<RefCenter>();
		
		request.put("createCredential", "yes");
		
		int i = 0;		
		
		
		
		if(null == stateId || "-1".equals(stateId)){
			
			addActionError("Please select state");
			i++;
			
		}
		
		if(null == districtCodeId || "-1".equals(districtCodeId)){
			
			addActionError("Please select district");
			i++;
			
		}
		
		if(null == centerId || "-1".equals(centerId)){
			
			addActionError("Please select center");
			i++;
			
		}
		
		if(null == centerUserEmailId.trim() || "".equals(centerUserEmailId.trim())){
			
			addActionError("Please enter email id");
			i++;
			
		}
		
		if(null != centerUserEmailId.trim() && !"".equals(centerUserEmailId.trim()))
		{
			if(!centerUserEmailId.trim().matches("^[a-z0-9_\\-\\.]+\\@[a-z0-9_\\-]+([\\.]{1}[a-z0-9_\\-]+)*\\.[a-z]{2,4}$")){
			
				addActionError("Please enter a valid Email Id");
				i++;
			}
			
		}
		
		if(null == password || "".equals(password)){
			
			addActionError("Please enter password");
			i++;
			
		}
		
		
		if (i > 0){			
			
			if(null != stateId && !"-1".equals(stateId)){
				
				refDistrictList = refDistrictManager.findDistricts(stateId);
			}
			
			if(null != stateId && !"-1".equals(stateId) && null != districtCodeId && !"-1".equals(districtCodeId)){
				
				refCenterList = refCenterManager.findByStateIdAndDistrictId(stateId, districtCodeId);
			}
			
			return INPUT;
		}
		
		boolean isCredentialAlreadyCreated = userMasterManager.isCredentialAlreadyCreated(Integer.parseInt(centerId));
		
		if(isCredentialAlreadyCreated){
			
			//center already created
			request.put("credentialAlreadyCreated", "yes");
			
			if(null != stateId && !"-1".equals(stateId)){
				
				refDistrictList = refDistrictManager.findDistricts(stateId);
			}
			
			if(null != stateId && !"-1".equals(stateId) && null != districtCodeId && !"-1".equals(districtCodeId)){
				
				refCenterList = refCenterManager.findByStateIdAndDistrictId(stateId, districtCodeId);
			}
			
			return INPUT;					
			
		}
		
		RefCenter refCenter = refCenterManager.findByCenterId(Integer.parseInt(centerId));
		
		UserMaster um = new UserMaster();
		
		um.setActiveStatus(1);
		um.setEmail(centerUserEmailId.trim());
		um.setPassword(password);
		um.setDateTime(new java.util.Date());
		um.setCenterId(refCenter);
		um.setCenterCode(refCenter.getCenterCode());
		um.setRoleId(2);
		
		boolean res = userMasterManager.saveOrUpdate(um);
		
		if(res){
			
			refDistrictList = refDistrictManager.findDistricts(stateId);
			refCenterList = refCenterManager.findByStateIdAndDistrictId(stateId, districtCodeId);
			setCenterUserEmailId("");
			request.put("operationSuccuss", "yes");
		}
		
		
		}catch(Exception e){
			
			e.printStackTrace();
			request.put("operationSuccuss", "no");
		}
		
		return ERROR;
	}	
	
	public String gotoAdminPage(){
		
		request.put("createCredential", "yes");
		
		return SUCCESS;
	}
	
	public String createAdminCredential(){
		
		try {
			
				
		request.put("createCredential", "yes");
		
		int i = 0;		
		
		
		
		
		
		if(null == adminEmailId || "".equals(adminEmailId)){
			
			addActionError("Please enter email id");
			i++;
			
		}
		
		if(null != adminEmailId && !"".equals(adminEmailId))
		{
			if(!adminEmailId.trim().matches("^[a-z0-9_\\-\\.]+\\@[a-z0-9_\\-]+([\\.]{1}[a-z0-9_\\-]+)*\\.[a-z]{2,4}$")){
			
				addActionError("Please enter a valid Email Id");
				i++;
			}
			
		}
		
		if(null == password || "".equals(password)){
			
			addActionError("Please enter password");
			i++;
			
		}
		
		
		if (i > 0){			
			
					
			return INPUT;
		}
		
		List<UserMaster> userMaster = userMasterManager.findByEmailId(adminEmailId);
		
		if(userMaster.size()>0){
			
			//admin already created
			request.put("credentialAlreadyCreated", "yes");		
					
			return INPUT;					
			
		}
		
				
		UserMaster um = new UserMaster();
		
		um.setActiveStatus(1);
		um.setEmail(adminEmailId);
		um.setPassword(password);
		um.setDateTime(new java.util.Date());		
		um.setRoleId(1);
		
		boolean res = userMasterManager.saveOrUpdate(um);
		
		if(res){
			
			
			setCenterUserEmailId("");
			request.put("operationSuccuss", "yes");
			
			return SUCCESS;
		}
		
		
		}catch(Exception e){
			
			e.printStackTrace();
			request.put("operationSuccuss", "no");
		}
		
		return ERROR;
	}
	// method is used to create/save center
	public String createCenter() {
		
		int i = 0;
		
		try {
		
		if(null == stateId || "-1".equals(stateId)){
			
			addActionError("Please select state");
			i++;
			
		}
		
		if(null == districtCodeId || "-1".equals(districtCodeId)){
			
			addActionError("Please select district");
			i++;
			
		}
		
		if(null == centerName || "".equals(centerName)){
			
			addActionError("Please enter center name");
			i++;
			
		}else{
			
			List<RefCenter> center = refCenterManager.findByCenterName(centerName.trim()); 
				
				if(center.size()>0){
					
					//already created
					addActionError("The center '"+centerName+"' is already created");
					i++;
			}
		}
		
		
		if (i > 0){
			
			refStateList = refStateManager.findStateList();
			refDistrictList = refDistrictManager.findByStateId(stateId);
			
			request.put("createCenter", "yes");
			
			return INPUT;
		}
		
		
		
		RefCenter refCenter = new RefCenter();
		
		RefState refState = refStateManager.findByStateId(stateId);
		RefDistrict refDistrict = refDistrictManager.findByDistirctId(districtCodeId);
		
		refCenter.setStateId(refState);
		refCenter.setDistrictId(refDistrict);
		refCenter.setName(centerName);
		
		//create center code
		Integer maxId = refCenterManager.findMaxId();
		Integer lastId = 0;		
		
		lastId = maxId + 1;	
		
		String centerId = stateId+"/"+districtCodeId+"/"+lastId;
		
		refCenter.setCenterCode(centerId);
		
		setFlag("true");
		setPageSize(20);	
		
			
			boolean res = refCenterManager.saveOrUpdate(refCenter);
			
			if(res){			
				
				request.put("operationSuccuss", "yes");		
				refCenterList = refCenterManager.findAll();
				
				if(refCenterList.size()>0){
					
					request.put("yesRecordExist", "yesRecordExist");
					request.put("createCenter", "yes");
					
					refStateList = refStateManager.findStateList();
					refDistrictList = refDistrictManager.findByStateId(stateId);
				}
				
				setTotalCountOfForms(refCenterList.size());
				
				return SUCCESS;
				
			}else{			
				
				request.put("operationSuccuss", "no");
			}
			
			
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ERROR;
	}
	
	// go to center landing page
	public String centerUserHome(){				
				
		try {
			
			UserMaster um = (UserMaster)session.get("userMaster");
			setGeneratedCenterId(um.getCenterCode());
			setCenterId(um.getCenterId().getId().toString());			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;		
	}
	
	// go to student payment page
	public String gotostudentPaymentDetailsPage(){				
					
			try {
				
				UserMaster um = (UserMaster)session.get("userMaster");
				setGeneratedCenterId(um.getCenterCode());
				setCenterId(um.getCenterId().getId().toString());
				
				refCourseList = refCourseManager.findAll();
				
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;
		}
	
	// saving student payment details
	public String saveStudentPaymentDetails() {		
		
		UserMaster um = (UserMaster)session.get("userMaster");
		setGeneratedCenterId(um.getCenterCode());			
		
		try {		
			
			refCourseList = refCourseManager.findAll();
			
			RefCenter refCenter = refCenterManager.findByCenterId(Integer.parseInt(centerId));
			studentPaymentDetails.setCenterId(refCenter);
			
			
			boolean isSaved = studentPaymentDetailsManager.saveOrUpdate(studentPaymentDetails);
			
			if(isSaved){
				
				//student payment details saved
				request.put("studentPaymentDetaildSavedSuccessfully", "yes");
				request.put("optionValue", studentPaymentDetails.getPaymentMode());
				
				return SUCCESS;
			}
			
		} catch (BaseException e) {			
			
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}		
		
		
		return ERROR;
	}
	
	public String gotoViewAllStudentPaymentDetails(){
		
		
		try {
			
			refCourseList = refCourseManager.findByStudentRegisteredCourses();
			
			if(refCourseList.size()>0){
			
				//student have payments records				
				
				return SUCCESS;
			
			}else{
				
				addActionError("No student payement details found!");
			}
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ERROR;
	}
	
	//fetch student payment details
	public String viewAllStudentPaymentDetailsList(){
		
		try {
												
			refCourseList = refCourseManager.findByStudentRegisteredCourses();
			studentPaymentDetailsList = studentPaymentDetailsManager.findByCourse(courseId);
			
			request.put("yesRecordExist", "yesRecordExist");
			
			setFlag("true");
			setPageSize(20);
			setTotalCountOfForms(studentPaymentDetailsList.size());
			
			return SUCCESS;
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ERROR;
	}
	
	public String editStudentPaymentDetails(){
		
		System.out.println("Editing: "+studentPaymentId);
		try {
			
			refCourseList = refCourseManager.findByStudentRegisteredCourses();
			studentPaymentDetails = studentPaymentDetailsManager.findById(studentPaymentId);	
			
			//setRefCenter(studentPaymentDetails.getCenterId());
			session.put("centerIdForEditingStuPaym", studentPaymentDetails.getCenterId());		
			
			String option = studentPaymentDetails.getPaymentMode().toString();		
			
			request.put("optionValue", option);		
			
			return SUCCESS;
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ERROR;
		
	}
	
	public String saveEditedStudentPaymentDetails(){
		
		System.out.println("saving edited: "+studentPaymentId);
		RefCenter refCenter = (RefCenter)session.get("centerIdForEditingStuPaym");
		
		System.out.println(refCenter);
		
		try {
			
			refCourseList = refCourseManager.findByStudentRegisteredCourses();			
			
			studentPaymentDetails.setCenterId(refCenter);
			
			boolean isSaved = studentPaymentDetailsManager.saveOrUpdate(studentPaymentDetails);
			
			if(isSaved){
				
				//student payment details saved after editing
				request.put("studentPaymentDetaildEditedSuccessfully", "yes");
							
				studentPaymentDetails = studentPaymentDetailsManager.findById(studentPaymentId);
				
				String option = studentPaymentDetails.getPaymentMode().toString();		
				
				request.put("optionValue", option);	
				
				return SUCCESS;			
			}	
						
			
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.put("studentPaymentDetaildEditedSuccessfully", "no");
		
		return ERROR;
		
	}

	public int getRndToken()
	{
		return rndToken;
	}

	public void setRndToken(int rndToken)
	{
		this.rndToken = rndToken;
	}

	public String getRndCaptcha()
	{
		return rndCaptcha;
	}

	public void setRndCaptcha(String rndCaptcha)
	{
		this.rndCaptcha = rndCaptcha;
	}

	/**
	 * @return the refStateManager
	 */
	public RefStateManager getRefStateManager() {
		return refStateManager;
	}

	/**
	 * @param refStateManager the refStateManager to set
	 */
	public void setRefStateManager(RefStateManager refStateManager) {
		this.refStateManager = refStateManager;
	}

	/**
	 * @return the refDistrictManager
	 */
	public RefDistrictManager getRefDistrictManager() {
		return refDistrictManager;
	}

	/**
	 * @param refDistrictManager the refDistrictManager to set
	 */
	public void setRefDistrictManager(RefDistrictManager refDistrictManager) {
		this.refDistrictManager = refDistrictManager;
	}

	/**
	 * @return the refStateList
	 */
	public List<RefState> getRefStateList() {
		return refStateList;
	}

	/**
	 * @param refStateList the refStateList to set
	 */
	public void setRefStateList(List<RefState> refStateList) {
		this.refStateList = refStateList;
	}

	/**
	 * @return the refDistrictList
	 */
	public List<RefDistrict> getRefDistrictList() {
		return refDistrictList;
	}

	/**
	 * @param refDistrictList the refDistrictList to set
	 */
	public void setRefDistrictList(List<RefDistrict> refDistrictList) {
		this.refDistrictList = refDistrictList;
	}

	/**
	 * @return the request
	 */
	public Map<String, Object> getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the districtCodeId
	 */
	public String getDistrictCodeId() {
		return districtCodeId;
	}

	/**
	 * @param districtCodeId the districtCodeId to set
	 */
	public void setDistrictCodeId(String districtCodeId) {
		this.districtCodeId = districtCodeId;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the refCenterManager
	 */
	public RefCenterManager getRefCenterManager() {
		return refCenterManager;
	}

	/**
	 * @param refCenterManager the refCenterManager to set
	 */
	public void setRefCenterManager(RefCenterManager refCenterManager) {
		this.refCenterManager = refCenterManager;
	}

	/**
	 * @return the centerCode
	 */
	public String getCenterCode() {
		return centerCode;
	}

	/**
	 * @param centerCode the centerCode to set
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	/**
	 * @return the refCenterList
	 */
	public List<RefCenter> getRefCenterList() {
		return refCenterList;
	}

	/**
	 * @param refCenterList the refCenterList to set
	 */
	public void setRefCenterList(List<RefCenter> refCenterList) {
		this.refCenterList = refCenterList;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalCountOfForms
	 */
	public Integer getTotalCountOfForms() {
		return totalCountOfForms;
	}

	/**
	 * @param totalCountOfForms the totalCountOfForms to set
	 */
	public void setTotalCountOfForms(Integer totalCountOfForms) {
		this.totalCountOfForms = totalCountOfForms;
	}

	/**
	 * @return the centerId
	 */
	public String getCenterId() {
		return centerId;
	}

	/**
	 * @param centerId the centerId to set
	 */
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	/**
	 * @return the centerUserEmailId
	 */
	public String getCenterUserEmailId() {
		return centerUserEmailId;
	}

	/**
	 * @param centerUserEmailId the centerUserEmailId to set
	 */
	public void setCenterUserEmailId(String centerUserEmailId) {
		this.centerUserEmailId = centerUserEmailId;
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
	 * @return the studentPaymentDetails
	 */
	public StudentPaymentDetails getStudentPaymentDetails() {
		return studentPaymentDetails;
	}

	/**
	 * @param studentPaymentDetails the studentPaymentDetails to set
	 */
	public void setStudentPaymentDetails(StudentPaymentDetails studentPaymentDetails) {
		this.studentPaymentDetails = studentPaymentDetails;
	}

	/**
	 * @return the refCourseManager
	 */
	public RefCourseManager getRefCourseManager() {
		return refCourseManager;
	}

	/**
	 * @param refCourseManager the refCourseManager to set
	 */
	public void setRefCourseManager(RefCourseManager refCourseManager) {
		this.refCourseManager = refCourseManager;
	}

	/**
	 * @return the refCourseList
	 */
	public List<RefCourse> getRefCourseList() {
		return refCourseList;
	}

	/**
	 * @param refCourseList the refCourseList to set
	 */
	public void setRefCourseList(List<RefCourse> refCourseList) {
		this.refCourseList = refCourseList;
	}

	/**
	 * @return the studentPaymentDetailsManager
	 */
	public StudentPaymentDetailsManager getStudentPaymentDetailsManager() {
		return studentPaymentDetailsManager;
	}

	/**
	 * @param studentPaymentDetailsManager the studentPaymentDetailsManager to set
	 */
	public void setStudentPaymentDetailsManager(
			StudentPaymentDetailsManager studentPaymentDetailsManager) {
		this.studentPaymentDetailsManager = studentPaymentDetailsManager;
	}

	/**
	 * @return the studentPaymentDetailsList
	 */
	public List<StudentPaymentDetails> getStudentPaymentDetailsList() {
		return studentPaymentDetailsList;
	}

	/**
	 * @param studentPaymentDetailsList the studentPaymentDetailsList to set
	 */
	public void setStudentPaymentDetailsList(
			List<StudentPaymentDetails> studentPaymentDetailsList) {
		this.studentPaymentDetailsList = studentPaymentDetailsList;
	}

	/**
	 * @return the generatedCenterId
	 */
	public String getGeneratedCenterId() {
		return generatedCenterId;
	}

	/**
	 * @param generatedCenterId the generatedCenterId to set
	 */
	public void setGeneratedCenterId(String generatedCenterId) {
		this.generatedCenterId = generatedCenterId;
	}

	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the studentPaymentId
	 */
	public Integer getStudentPaymentId() {
		return studentPaymentId;
	}

	/**
	 * @param studentPaymentId the studentPaymentId to set
	 */
	public void setStudentPaymentId(Integer studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
	}

	/**
	 * @return the adminEmailId
	 */
	public String getAdminEmailId() {
		return adminEmailId;
	}

	/**
	 * @param adminEmailId the adminEmailId to set
	 */
	public void setAdminEmailId(String adminEmailId) {
		this.adminEmailId = adminEmailId;
	}

	/**
	 * @return the refCenter
	 */
	public RefCenter getRefCenter() {
		return refCenter;
	}

	/**
	 * @param refCenter the refCenter to set
	 */
	public void setRefCenter(RefCenter refCenter) {
		this.refCenter = refCenter;
	}

	
	
	
	
	

	
	
	
	
	
	

	
	
	
	

	


	


}
