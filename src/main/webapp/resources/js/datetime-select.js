/**
 * 
 */

Date.prototype.format = function(fmt) { 
     var o = { 
        "M+" : this.getMonth()+1,                 //月份 
        "d+" : this.getDate(),                    //日 
        "h+" : this.getHours(),                   //小时 
        "m+" : this.getMinutes(),                 //分 
        "s+" : this.getSeconds(),                 //秒 
        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
        "S"  : this.getMilliseconds()             //毫秒 
    }; 
    if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
     for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
         }
     }
    return fmt; 
}

function initDateTimeSelect(startTagId, endTagId, range) {
	// $("#" + startTagId)
	// $("#timeStart").datetimebox('setValue', '2012-6-1 12:30:56');	
	var dates = getDateTimeByRange(range);
	$("#" + startTagId).datetimebox('setValue', dates[0].format("yyyy-MM-dd hh:mm:ss"));
	$("#" + endTagId).datetimebox('setValue', dates[1].format("yyyy-MM-dd hh:mm:ss"));
}

function getDateTimeByRange(range) {
	
	var dateTimeArray = [];

	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth(); // attention: this value start from 0
	var day = now.getDate();
	
	switch (range) {
	case "1":
		var start = new Date();
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);
		var end = new Date();
		end.setHours(23);
		end.setMinutes(59);
		end.setSeconds(59);
		dateTimeArray = [start, end];
		break;
	case "2":
		var start = new Date(year, month, 1);
		var end = new Date(new Date(year, month + 1, 1).getTime() - 1000*60*60*24);
		end.setHours(23);
		end.setMinutes(59);
		end.setSeconds(59);
		dateTimeArray = [start, end];
		break;
	case "3":
		var start = new Date(year, 0, 1);
		var end = new Date(new Date(year, 11, 31).getTime() - 1000*60*60*24);
		end.setHours(23);
		end.setMinutes(59);
		end.setSeconds(59);
		dateTimeArray = [start, end];
		break;
	case "4":
		var start = new Date(new Date().getTime() - 1000*60*60*24);
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);
		var end = new Date(new Date().getTime() - 1000*60*60*24);
		end.setHours(23);
		end.setMinutes(59);
		end.setSeconds(59);
		dateTimeArray = [start, end];
		break;
	case "5":
		var start = new Date(new Date(year, month, 1).getTime() - 1000*60*60*24);
		start.setDate(1)
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);
		
		var end = new Date(new Date(year, month, 1).getTime() - 1000*60*60*24);
		end.setHours(23);
		end.setMinutes(59);
		end.setSeconds(59); 
		dateTimeArray = [start, end];
		break;
	case "6":
		var start = new Date(new Date(year, 0, 1).getTime() - 1000*60*60*24);
		start.setMonth(0);
		start.setDate(1)
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);
		
		var end = new Date(new Date(year, 0, 1).getTime() - 1000*60*60*24);
		end.setHours(23);
		end.setMinutes(59);
		end.setSeconds(59); 
		dateTimeArray = [start, end];
		break;
	default:
		dateTimeArray = ["", ""];
		break;
	}
	return dateTimeArray;
}