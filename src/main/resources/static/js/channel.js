$.fn.serializeObject = function () {
	var formData = {};
	var formArray = this.serializeArray();
	
	for(var i = 0, n = formArray.length; i < n; ++i)
		formData[formArray[i].name] = formArray[i].value;
	
	return formData;
};

function init_profile_upload_dialog() {
	$("#profile_upload_dialog").dialog({
		modal: true,
		autoOpen: false,
		width:"auto",
		show: "fade",
		hide:"fade",
		resizable: false,
		position: { my: "left top", at: "left bottom", of: "#profile_upload_button" },
		create: function(event, ui) {
			$(".ui-dialog-titlebar").hide(); // 타이틀 지우기
		},
		close: function(event, ui) {
			$('#wrap').show();
		},
	    open: function(event, ui) { 
	        $('.ui-widget-overlay').bind('click', function() { 
	            $("#profile_upload_dialog").dialog('close'); 
	        }); 
	    }
	});
}

function thumbnail_upload() {
	$("#profile_upload_dialog").dialog("open");
}

function init_video_upload_dialog() {
	$("#video_upload_dialog").dialog({
		modal: true,
		autoOpen: false,
		width: "auto",
		show: "fade",
		hide:"fade",
		resizable: false,
		position: { my: "center", at: "center", of: window },
		create: function(event, ui) {
			$(".ui-dialog-titlebar").hide(); // 타이틀 지우기
		},
		close: function(event, ui) {
			$('#wrap').show();
			$("#video_info_area").prop("style", "display: none;");
			$("#video_upload_area").prop("style", "");
		},
	    open: function(event, ui) { 
	    	$("#upload_close").bind("click", function() {
	            $("#video_upload_dialog").dialog('close'); 
	    	});
	    }
	});
}

function getNameLength() {
	var text = $("#name").val();
	const nameLimit = 100;

	if(text.length < nameLimit) {
		$("#name_size").text(text.length);
	} else {
		$("#name").value = text.substring(0, nameLimit);
		$("#name_size").text($("#name").val().length);
	}
}

function getNotesLength() {
	var text = $("#notes").val();
	const notesLimit = 5000;

	if(text.length < notesLimit) {
		$("#notes_size").text(text.length);
	} else {
		$("#notes").val(text.substring(0, notesLimit));
		$("#notes_size").text($("#notes").val().length);
	}
}
	
function video_upload() {
	$("#video_upload_dialog").dialog("open");
}

function getCategories() {
	$.ajax({
		url: categoriesUrl,
		type: "GET",
		success: function(response) {
			if(response.result) {
				console.log(response.data);
				var html = "";
				$.each(response.data, function(index, item) {
					html += "<option value='" + item.id + "'>" + item.category_name + "</option>";
				});
				$("#category").html(html);
			} else {
				alert(response.msg);
			}
		},
		error: function(xhr, status, error) {
			var err = eval("(" + xhr.responseText + ")");
			alert(err.Message);
		}
	});
}

function addFocus(target) {
	console.log(target + "focuss");
	$("#" + target + "_form_group").addClass("focus-div");
	$("#" + target + "_label").addClass("focus-label");
}

function removeFocus(target) {
	console.log(target + "out");
	$("#" + target + "_form_group").removeClass("focus-div");
	$("#" + target + "_label").removeClass("focus-label");
}

function formSubmit() {
	var name = $("#name").val();
	
	if(name.length == 0) {
		alert("제목은 필수 항목입니다.");
		return;
	}

	if(thumbnailPond.getFiles().length == 0) {
		alert("미리보기 이미지는 필수 항목입니다.");
		return;
	}
	
	var data = $("#video_info").serializeObject();
	
	$.ajax({
		url: saveUrl,
		type: "POST",
		data: data,
		success: function(response) {
			if(response.result) {
				alert("저장되었습니다.");
            	location.reload(true);
			} else {
				alert(response.msg);
			}
		},
		error: function(xhr, status, error) {
			var err = eval("(" + xhr.responseText + ")");
			alert(err.Message);
		}
	});
}

