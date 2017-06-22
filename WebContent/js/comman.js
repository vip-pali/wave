function changeUserStatus(active,userId,roleId,roleStateId,roleDistricId)
{
	if(roleId=='1')
	{
		alert('Super User Administrator can not be Inactived');
	}else
	{
		window.location.href="changeuserstatus?active="+active+"&userId="+userId+"&roleId="+roleId+"&roleStateId="+roleStateId+"&roleDistricId="+roleDistricId;
	}
}

function capcthaAutoCompleteOff()
{
	document.getElementById('captcha').setAttribute('autocomplete', 'off');
	document.getElementById('captcha').value='';
}
function enablePrefill(checkbox, div) {
	
	var prefill = document.getElementById(checkbox);
	if(prefill==null)
		{
		return;
		}
	if(prefill.checked) {
		document.getElementById(div).style.display='block';
	}
	else {
		document.getElementById(div).style.display='none';
	}
	
}