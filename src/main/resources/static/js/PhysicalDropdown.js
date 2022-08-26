function getSubCategory(catagoryName){
    var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));
    alert(appname);
	 document.getElementById('productobject').action = "/product/productsphotos?getQry=getSubsCatagory&catagoryName="+catagoryName;
	  document.getElementById('productobject').submit();
}
