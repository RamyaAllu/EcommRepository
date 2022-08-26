function editSubCategory(catagoryName){
    var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));
    alert(appname);
	 document.getElementById('productobject').action = "/product/editimage?getQry=getSubsCatagory&catagoryName="+catagoryName;
	  document.getElementById('productobject').submit();
}