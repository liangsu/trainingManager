$(function(){
	//开始时间
	var startDate = $("#startDate").val();
	$("#startDate").datepicker({
		autoclose: true,
		startDate:new Date()
	}).on("hide",function(){
		var d = $("#startDate").datepicker("getDate");
		if(d == "Invalid Date"){
			$("#startDate").val(startDate);
		}else{
			
		}
	});
	
	$("#startDateBtn").click(function(){
		$("#startDate").datepicker("show");
	});
	
	//结束时间
	$("#endDate").datepicker({
		autoclose: true,
		startDate: startDate
	});
	$("#endDateBtn").click(function(){
		$("#endDate").datepicker("show");
	});

	//评价时间
	$("#evaluateDate").datepicker({
		autoclose: true,
	});
	$("#evaluateDateBtn").click(function(){
		$("#evaluateDate").datepicker("show");
	});

	//表单提交
	function submitForm(){
		$("#setForm").submit();
	}
});


