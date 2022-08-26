function editDigitalSubCategories(categoryName){
var appname=window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));
alert(appname)
alert(categoryName)

document.getElementById('digitalproductbyid').action = "/Dgproducts/saveEditDigitalProduct?getQry=getSubsCatagory&categoryName="+categoryName
alert(document.getElementById('digitalproductbyid').action);
document.getElementById('digitalproductbyid').submit();
}