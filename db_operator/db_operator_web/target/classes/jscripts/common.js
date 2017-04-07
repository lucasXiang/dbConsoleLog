String.prototype.trim = function(){
  return this.replace(/(^\s*)|(\s*$)/g, "");
}
function GetCharLength(str)
{
    var iLength = 0;  //记录字符的字节数
    for(var i = 0;i<str.length;i++)  //遍历字符串中的每个字符
    {
        if(str.charCodeAt(i) >255)   //如果当前字符的编码大于255
        {
            iLength += 3;    //所占字节数加2
        }
        else
        {
            iLength += 1;   //否则所占字节数加1
        }
    }
    return iLength;   //返回字符所占字节数
}
function getByName(str){
	return document.getElementsByName(str);
}
function getById(str){
	return document.getElementById(str);
}
function selOptions(formId, val) {
	var obj = document.getElementById(formId);
	if(obj != null){
		if(obj.options != null){
			for ( var i = 0; i < obj.options.length; i++) {
				if (obj.options[i].value == val) {
					obj.options[i].selected = true;
					break;
				}
			}
		}
	}
	
}

function selRadios(formId, val) {
	var ras = document.getElementsByName(formId);
	if(ras != null){
		for ( var i = 0; i < ras.length; i++) {
			if (ras[i].value == val) {
				ras[i].checked = true;
				break;
			}
		}
	}
	
}


function selCheckboxs(formId, val) {
	var ras = document.getElementsByName(formId);
	var valArr = val.split(",");
	if(valArr != null){
		for ( var i = 0; i < ras.length; i++) {
			if(isContain(valArr,ras[i].value)){
				ras[i].checked = true;
			}
		}
	}
}

function isContain(valArr,val){
	for(var j=0;j<valArr.length;j++){
		if (val == valArr[j]) {
			return true;
		}
	}
	return false;
}

function isSelectOne(formId){
	var ras = document.getElementsByName(formId);
	for ( var i = 0; i < ras.length; i++) {
		if (ras[i].check){
			return true;
		}
	}
	return false;
}
function checkAll(allObj, formName) {
	var als = document.getElementsByName(formName);

	for ( var i = 0; i < als.length; i++) {
		als[i].checked = allObj.checked;
	}
}


function DrawImage(ImgD, FitWidth, FitHeight) {
	var image = new Image();
	image.src = ImgD.src;
	if (image.width > 0 && image.height > 0) {
		if (image.width / image.height >= FitWidth / FitHeight) {
			if (image.width > FitWidth) {
				ImgD.width = FitWidth;
				ImgD.height = (image.height * FitWidth) / image.width;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		} else {
			if (image.height > FitHeight) {
				ImgD.height = FitHeight;
				ImgD.width = (image.width * FitHeight) / image.height;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		}
	}
}

/**
 * 格式化数字
 * @param s   要格式的数字
 * @param n   保留的小数位置
 * @returns {String}
 */
function formatNum(s, n)  
{  
	if(s==undefined || s.length==0){
		return "";
	}
   n = n > 0 && n <= 20 ? n : 2;  
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
   var l = s.split(".")[0].split("").reverse(),  
   r = s.split(".")[1];  
   t = "";  
   for(var i = 0; i < l.length; i ++ )  
   {  
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
   }  
   return t.split("").reverse().join("") + "." + r;  
}

/**
 * 获得字符串长度,一个汉字3个字节
 * @param data
 * @returns {Number}
 */
function getLengthB(data) {
	var i, cnt = 0;
	for (i = 0; i < data.length; i++) {
		if (escape(data.charAt(i)).length >= 4) {
			if (escape(data.charAt(i)) >= "%uFF65" && escape(data.charAt(i)) <= "%uFF9F") {
				cnt++;
			} else {
				cnt += 3;
			}
		} else {
			cnt++;
		}
	}
	return cnt;
}

/**
 * 字符串截取,1个汉字3个字节
 * @param str
 * @param len
 * @param place
 * @returns
 */
function sub_str(str,len){
	str = str.trim();
	var cnLen = "xxx"; // utf8 每个中文3长度
	if (str.replace(/[^\x00-\xff]/gi, cnLen).length <= len)
		return str;

	var temp_len = len;
	str = str.substr(0, len);
	while (str.replace(/[^\x00-\xff]/gi, cnLen).length > len) {
		str = str.substr(0, --temp_len) + "...";
	}

	return str;
}

