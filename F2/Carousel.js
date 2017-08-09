(function ($) {
    $.fn.carousel = function () {
         var show = $(".show"),
               ul = show.find("ul"),
              num = show.find(".num span"),
            width = show.find("ul li").eq(0).width(),
                i = 0, //图片索引值
             time = null,
            touch = true;
        //点击数字事件	
        num.on("click",function(){
            $(this).siblings().removeClass("active");
            $(this).addClass("active");
            i = $(this).index();
            ul.animate({"left":-width*i});
        });
        //自动播放
        function autoplay(){
            time = setInterval(function(){
            i++;
            //超过图片长度，从头播放
            if(i>num.length-1){i=0;}
            //模拟点击数字事件
            num.eq(i).trigger("click");
            },3000);
        }
        //自动播放
        autoplay();
        //鼠标覆盖在图片上，停止滚动				
        show.hover( function(){clearInterval(time);},autoplay);
	
	      //触摸滑动模块
        function k_touch() {
            var _start = 0, _end = 0, _content = document.getElementById("show");
            _content.addEventListener("touchstart", touchStart, false);
            _content.addEventListener("touchmove", touchMove, false);
            _content.addEventListener("touchend", touchEnd, false);
            function touchStart(event) {
	            var touch = event.targetTouches[0];
	            _start = touch.pageX;
            }
            function touchMove(event) {
	            var touch = event.targetTouches[0];
	            _end = (_start - touch.pageX);
            }

            function touchEnd(event) {
			    //右划
	            if (_end < -100) {
	    	        i--;
                //超过第一张图片，跳到尾部
			          if(i < 0){i = 2;}
                    //模拟点击数字事件
                    num.eq(i).trigger("click");
		            _end=0;
	            }
	    		//左划
	    		else if(_end > 100){
	        	    i++;
                //超过图片长度，从头播放
                if(i > num.length-1){i = 0;}
                //模拟点击数字事件
                num.eq(i).trigger("click");
	    	        _end=0;
	            }
            }
        }
	
	      if(touch){
	          k_touch();
        }
	
    };
})(jQuery);
