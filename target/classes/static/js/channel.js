function init_profile_upload_dialog() {
	$("#profile_upload_dialog").dialog({
		modal: true,
		autoOpen: false,
		width:"auto",
		show: "drop",
		hide:"drop",
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
	console.log(1);
	$("#profile_upload_dialog").dialog("open");
}