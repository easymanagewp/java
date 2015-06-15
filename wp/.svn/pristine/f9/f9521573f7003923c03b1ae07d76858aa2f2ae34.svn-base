window.WP = {
		"CONFIG" : {
			"JSON_RESP_STATUS" : {
				"SUCCESS" : 0,
				"FAIL" : -1,
				"EMPTY" : 1
			}
		}
}
WP.loadStyle = function(){
	$(".line_height").each(function(){
		var $element = $(this);
		var iHeight = $element.parent()[0].offsetHeight;
		$element.css("line-height",iHeight+"px");
	})

	$("button[href],a[myhref]").each(function(){
		
		var strHref = null;

		if(this.tagName=='A' || this.tagName=='a'){
			strHref = $(this).attr("myhref");
		}else{
			strHref = $(this).attr("href");
		}

		if(strHref.indexOf("#")==0){
			$(this).parent().hover(function(){
				$(strHref).css("display","block");
			},function(){
				$(strHref).css("display","none");
			});
		}else if(strHref.indexOf("dialog:")==0){
			$(this).on('click',function(){
				var _href = strHref.substring(7);
				$("body").append(
					$("<div class='dialog'>").append(
						$("<div class='dialog-title'>").append($("<button>X</button>").on('click',function(){
							$(this).parent().parent().remove();
						}))	
					).append(
						$("<div class='dialog-content'>").append(
							$("<iframe width='100%' height='100%' src='"+_href+"'>")
						)
					)
				)
			})
		}else{
			$(this).on("click",function(){
				window.location.href = strHref;
			})
		}
	})

	$(".submenu").each(function(){
		var $element = $(this);
		var pWidth = $element.parent()[0].offsetWidth;
		$element.find("button").css("width",pWidth+"px");
	})
}
$(function(){
	WP.loadStyle();
})