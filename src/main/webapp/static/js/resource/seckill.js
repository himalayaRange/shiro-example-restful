//JavaScript模块化
var seckill={
	//封装秒杀相关的ajax url
   URL:{
	   now: function () {
           return '/seckill/time/now';
       },
       exposer:function(seckillId){
    	   return '/seckill/'+seckillId+"/exposer";
       },
       execution:function(seckillId,md5){
    	   return '/seckill/'+seckillId+'/'+md5+'/execution';
       }
   },
   
   //验证手机号
   validatePhone:function(phone){
	 if(phone && phone.length==11 && !isNaN(phone)){
		 return true;
	 }else{
		return false;
	 }
   }, 
   //倒计时
   countdown:function(prefix,seckillId,nowTime,startTime,endTime){
	   var seckillBox=$('#seckill-box');
	   if(nowTime>endTime){
		   seckillBox.html('秒杀结束');
	   }else if(nowTime<startTime){
		   seckillBox.html('秒杀未开始');
		   //倒计时
		   var killTime=new Date(startTime+1000);
		   //jquery提供的countdown倒计时服务
		   seckillBox.countdown(killTime,function(event){
			   //时间格式
			   var format=event.strftime('秒杀倒计时：%D天  %H时  %M分  %S秒');
			   seckillBox.html(format);
		   }).on('finish.countdown',function(){
			   seckill.handlerSeckillkill(prefix,seckillId,seckillBox);
		   });
	   }else{
		   //秒杀开始
		   	seckill.handlerSeckillkill(prefix,seckillId,seckillBox);
	   }
   },
   handlerSeckillkill:function(prefix,seckillId,node){
	 //获取秒杀地址，控制实现逻辑，执行秒杀
	  node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
	  $.post(prefix+seckill.URL.exposer(seckillId),{},function(result){
		  //回调函数中执行秒杀交互流程
		  if(result && result['success']){
			  var exposer=result['data'];
			  if(exposer['exposed']){
				  //开启秒杀
				  var md5=exposer['md5'];
				  var killUrl=seckill.URL.execution(seckillId, md5);
				  console.log('killUrl'+killUrl);
				  //绑定一次事件，防止点击按钮请求过多对服务器的压力很大
				  $('#killBtn').one('click',function(){
					  //禁用按钮
					  $(this).addClass('disabled')
					  //发送秒杀请求
					  $.post(prefix+killUrl,{},function(result){
						  if(result && result['success']){
							  var killResult=result['data'];
							  var state=killResult['state'];
							  var stateInfo=killResult['stateInfo'];
							  //显示秒杀结果
							  node.html('<span class="label label-success">'+stateInfo+'</span>');
						  }else{
							  console.log('result:'+result);
						  }
					  });
				  });
				  node.show();
			  }else{
				  //未开启秒杀，由于各个pc机器的时间差异导致
				  var now=exposer['now'];
				  var start=exposer['start'];
				  var end=exposer['end'];
				  //重新走倒计时逻辑
				  seckill.countdown(prefix, seckillId, now, start, end);
			  }
		  }else{
			  console.log('result:'+result);
		  }
	  });
   },
   //详情页秒杀逻辑
   detail:{
	   //详情页初始化
	   init:function(params){
		   var killPhone=$.cookie('killPhone');
		   //验证手机号，先从cookie中判断是否登录
		   if(!seckill.validatePhone(killPhone)){
			   //未登录，弹出登录modal
			   var killPhoneModal=$('#killPhoneModal');
			   killPhoneModal.modal({
                   show: true, //显示弹出层
                   backdrop: 'static', //禁止位置关闭
                   keyboard: false  //关闭键盘事件
               });
			   $('#killPhoneBtn').click(function(){
				   var inputPhone=$('#killphoneKey').val();
				   if(seckill.validatePhone(inputPhone)){
					   //电话写入cookie
					   $.cookie('killPhone', inputPhone, {expires: 7, path: '/wangyi/seckill'});
					  //刷新页面 
					   window.location.reload();
				   }else{
					   $('#killphoneMessage').hide().html("<label class='label label-danger'>手机号码错误！</label>").show(300);
				   }
			   });
		   }
		   var startTime=params['startTime'];
		   var endTime=params['endTime'];
		   var seckillId=params['seckillId'];
		   //已经登录的倒计时
		   var prefix= params['prefix'];
		   $.get(prefix+seckill.URL.now(), {}, function (result) {
               if(result && result['success']){
                   var nowTime = result['data'];
                   seckill.countdown(prefix,seckillId, nowTime, startTime, endTime);
               }else{
                   console.log('result : ' + result);
               }
           });
	   }
   }
};