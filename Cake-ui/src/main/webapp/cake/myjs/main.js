/*图片预加载*/
$(".lazy").lazyload({
	"effect": "fadeIn",
	"effectspeed": 2000
});
/*轮播图*/
layui.use('carousel', function(){
  var carousel = layui.carousel;
  //建造实例
  carousel.render({
    elem: '#test1'
    ,width: '100%' //设置容器宽度
    ,height:'100%'
    /*,arrow: 'always' *///始终显示箭头
    //,anim: 'updown' //切换动画方式
  });
});
/*加入购物车动画*/
/*$(".insetCar").click(function(){
	/!*$(".div_Conent_div_Content_display").css("bottom","59px");*!/
	//$(".div_Conent_div_Content_display").css("overflow","hidden");
	$(".div_Conent_div_Content_display").css("bottom","-121px");
	//$(".div_Conent_div_Content_display").css("height","0px");
	$(this).parent().parent().find($(".div_Conent_div_Content_display")).css("bottom","59px");
	$(this).parent().parent().find($(".div_Conent_div_Content_display")).css("height","148px");
	$(this).parent().parent().find($(".div_Conent_div_Content_display")).css("overflow","visible");
});
$(".div_2_div").click(function(){
	$(".div_2_div div").css("display","none");
	$(".div_2_div").css("border","");
	$(".div_2_div").css("line-height","30px");
	$(this).children().css("display","block");
	$(this).css("border","1px solid red");
	$(this).css("line-height","10px");
});*/
/*$("body:not[.insertCar]").click(function(){
	$(".div_Conent_div_Content_display").css("bottom","-121px");
});*/