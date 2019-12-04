$(document).ready(function() {
	List();
	
	$("#RunBtn1").click(function() {
		Run1();
	});

	$("#StopBtn1").click(function() {
		Stop1();
	});
	
	$("#RunBtn2").click(function() {
		Run2();
	});

	$("#StopBtn2").click(function() {
		Stop2();
	});
	
});

	function List() {
		$.ajax({
				type : "GET",
				url : "/quartz/Quartz",
				datatype : 'json',
				success : function(data) {
					var obj = JSON.parse(data);
					var str = "<table class=\"table table-hover table table-bordered\">";
					str += "<tr>";
					str += "<td class=\"text-center success col-md-1\">INDEX</td>";
					str += "<td class=\"text-center success col-md-1\">TYPE1</td>";
					str += "<td class=\"text-center success col-md-1\">TYPE1_TIME</td>";
					str += "<td class=\"text-center success col-md-1\">TYPE2</td>";
					str += "<td class=\"text-center success col-md-1\">TYPE2_TIME</td>";
					str += "</tr>";

					for (var i = 0; i < obj.length; i++) {
						var num = obj[i].Num;
						var type1 = obj[i].Type1;
						var type1_Time = obj[i].Type1_Time;
						var type2 = obj[i].Type2;
						var type2_Time = obj[i].Type2_Time;

						str += "<tr><td> " + obj[i].Num + " </td>";
						str += "<td>" + obj[i].Type1 + " </td>";
						str += "<td>" + obj[i].Type1_Time + "</td>";
						str += "<td>" + obj[i].Type2 + "</td>";
						str += "<td>" + obj[i].Type2_Time + "</td></tr>";
					}
					str += "</table>";
					$('#quartzList').html(str);
					$("#RunBtn1").text("Running");
					$("#RunBtn2").text("Running");
					
				},
				error : function(data) {
					alert("실패");
				}
			});
	}
	
	function Run1(){
		$.ajax({
			type : "GET",
			url : "/quartz/QuartzStart1",
			datatype : 'json',
			success : function(data) {
				$("#RunBtn1").text("Running");
				$("#StopBtn1").text("Stop1");
				$("#RunBtn1").attr("disabled",true);
				$("#StopBtn1").removeAttr("disabled");
			},
			error : function(data) {
				alert("실패");
			}
		});
	}
	
	function Stop1(){
		$.ajax({
			type : "GET",
			url : "/quartz/QuartzStop1",
			datatype : 'json',
			success : function(data) {
				$("#RunBtn1").text("Run1");
				$("#StopBtn1").text("Stopped");
				$("#StopBtn1").attr("disabled",true);
				$("#RunBtn1").removeAttr("disabled");
			},
			error : function(data) {
				alert("실패");
			}
		});
	}
	
	function Run2(){
		$.ajax({
			type : "GET",
			url : "/quartz/QuartzStart2",
			datatype : 'json',
			success : function(data) {
				$("#RunBtn2").text("Running");
				$("#StopBtn2").text("Stop2");
				$("#RunBtn2").attr("disabled",true);
				$("#StopBtn2").removeAttr("disabled");
			},
			error : function(data) {
				alert("실패");
			}
		});
	}
	
	function Stop2(){
		$.ajax({
			type : "GET",
			url : "/quartz/QuartzStop2",
			datatype : 'json',
			success : function(data) {
				$("#RunBtn2").text("Run2");
				$("#StopBtn2").text("Stopped");
				$("#StopBtn2").attr("disabled",true);
				$("#RunBtn2").removeAttr("disabled");
			},
			error : function(data) {
				alert("실패");
			}
		});
	}
