$(document).ready(function () {
	
	let LocFlag = false;
	let FromLoc = ""; // 출발지
	let DesticationLoc = ""; // 목적지 
	
	// Click Event S
	function getPlaceName(){
		var class_info = $(this).children('.info');
		var placeName = class_info.children('h5').text();
		var placeLoc = class_info.children('span').eq(0).text();
		
		if(!LocFlag){
			FromLoc = placeLoc;
			LocFlag = true; // 사용자가 클릭
			
				  
		}else{
			DesticationLoc = placeLoc;
		}
		let info_msg = '출발지 : ' + FromLoc + "/" + '도착지 : ' + DesticationLoc;
		$("#fromLocation").val(FromLoc);
		$("#toLocation").val(DesticationLoc);
		console.log('출발지 : ' + FromLoc);    
		console.log('도착지 : ' +  DesticationLoc);
	}


	
function RegisterLocationAJAX(){
		var form = {
            FROM_LOCATION: FromLoc,
            TO_LOCATION: DesticationLoc,
            ReqDTTM: new Date().toDateString(),
            UserKey:""
        }
        
		return $.ajax({
			type: "POST",
			url: "/api/registerLocation",
			data:JSON.stringify(form),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8;"
		});
	}
	
	$(document).on('click', '.item', getPlaceName);
	

});