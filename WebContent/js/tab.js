function tabClick(tab, isAChildTab) {
	
	
	var selectedTab = document.getElementById(tab);
	var tabsRow, contentArea;
	
	if(isAChildTab){
		tabsRow = getPreviousSibling(selectedTab.parentNode);
	}
	else {
		 tabsRow = document.getElementById( "tabsRow");
	}

	var childTabs = tabsRow.getElementsByTagName("div");
	
	for(var i = 0; i < childTabs.length; i++) {
		
		childTabs[i].setAttribute("class", "tabSheet");
	}
	
	contentArea = getNextsibling(tabsRow);
	var childTabsContent = contentArea.getElementsByTagName("div");
	
	for(var i = 0; i < childTabsContent.length; i++) {
		
		childTabsContent[i].style.display = 'none';
	}
	var tabElement = document.getElementById(tab);
	if (tabElement==null)
		{
		tabElement=childTabsContent[0];
		tab=tabElement.id;
		}
	
	tabElement.style.display = 'block';	
	var childTabElement = tabElement.getElementsByTagName("div");
	for(var i = 0; i < childTabElement.length; i++) {

		childTabElement[i].style.display = 'block';
	}	
	document.getElementById('li_'+tab).removeAttribute("class");
	document.getElementById('li_'+tab).setAttribute("class", "tabSheetActive");
}

function tab(tab) {
	
	tabClick(tab, false);
	createCookie('activeTab', tab, 0);
	if(tab=="tab2" ||tab == "tab4" || tab == "tab5")
	{
		var childActiveTab=readCookie('childActiveTab');
		var childContainer = document.getElementById(tab).getElementsByTagName("div");
		var content = childContainer[0].children;
		var childTabElement = content[1].children;
			for(var i = 0; i < childTabElement.length; i++) {

				if(childActiveTab != null && childActiveTab == childTabElement[i].id)
		 			{
		 				childTab(childActiveTab);
		 				return;
		 			}
				
			}
			
				displayFirstChildTab(tab);
				
	}
}

function childTab(childTab)
{
	tabClick(childTab, true);
	createCookie('childActiveTab', childTab, 0);
}

function createCookie(name, value, days) {
    var expires = '',
        date = new Date();
    if (days) {
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = '; expires=' + date.toGMTString();
    }
    document.cookie = name + '=' + value + expires + '; path=/';
}
 
function readCookie(name) {
	
    var nameEQ = name + '=',
        allCookies = document.cookie.split(';'),
        i,
        cookie;
    
    for (i = 0; i < allCookies.length; i += 1) {
    	
        cookie = allCookies[i];
        while (cookie.charAt(0) === ' ') {
            cookie = cookie.substring(1, cookie.length);
        }
        if (cookie.indexOf(nameEQ) === 0) {
            return cookie.substring(nameEQ.length, cookie.length);
        }
    }
    
    return null;
}
 
function eraseCookie(name) {
	
    createCookie(name, '', -1);
}

function restoreTab()
{
	var activeTab=readCookie('activeTab');
	if(activeTab != null) {
		
		tab(activeTab);
	}
	else {
		displayFirstTab();
	}
}

function displayFirstTab()
{ 
	var firstTab=getFirstChild(document.getElementById("tabsRow")).id;
	document.getElementById(firstTab).setAttribute("class", "tabSheetActive");
	var tabName=firstTab.split("_");
	tab(tabName[1]);
}

function displayFirstChildTab(tab)
{
	if(tab == 'tab2'){
    	var firstTab='li_childTabOne';
    	document.getElementById(firstTab).setAttribute("class", "tabSheetActive");
    	var tabName=firstTab.split("_");
    	childTab(tabName[1]);
    	}
	if(tab == 'tab4'){
    	var firstTab='li_childTabA';
    	document.getElementById(firstTab).setAttribute("class", "tabSheetActive");
    	var tabName=firstTab.split("_");
    	childTab(tabName[1]);
    	}
	if(tab == 'tab5'){
    	var firstTab='li_childTab1';
    	document.getElementById(firstTab).setAttribute("class", "tabSheetActive");
    	var tabName=firstTab.split("_");
    	childTab(tabName[1]);
    	}
}

function getFirstChild(n)
{
	
	x=n.firstChild;
	while (x.nodeType!=1) {
		
  		x=x.nextSibling;
  	}
	return x;
}

function getNextsibling(n)
{
   x=n.nextSibling;
while (x.nodeType!=1)
  {
  x=x.nextSibling;
  }
return x;
}

function getPreviousSibling(n)
{
x=n.previousSibling;
while (x.nodeType!=1)
  {
  x=x.previousSibling;
  }
return x;
}