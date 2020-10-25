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

function createProfilePond() {
	const inputProfileImage = document.querySelector(".profile_filepond");
	profilePond = FilePond.create(inputProfileImage, {
		imageResizeTargetWidth: 256,
		imageResizeMode: "contain",
		allowFileTypeValidation: true,
		acceptedFileTypes: ['image/png', 'image/jpeg'],
		allowFileSizeValidation: true,
		maxFileSize: "5MB",
		
		onaddfile: (error, file) => {
		    if (error) {
		        profilePond.removeFile();
		        alert(error.main + "\n" + error.sub);
		        return;
		    }
		    console.log('profile added', file.filename);
		},
		
		labelIdle: '프로필 이미지 업로드<span class="filepond--label-action"></span>',
		server: {
	    	process:(fieldName, file, metadata, load, error, progress, abort, transfer, options) => {
	
	            // fieldName is the name of the input field
	            // file is the actual file object to send
	            const formData = new FormData();
	            formData.append(fieldName, file, file.name);
	
	            const request = new XMLHttpRequest();
	            request.open('POST', uploadUrl + '/profile/image');
	
	            // Should call the progress method to update the progress to 100% before calling load
	            // Setting computable to false switches the loading indicator to infinite mode
	            request.upload.onprogress = (e) => {
	                progress(e.lengthComputable, e.loaded, e.total);
	            };
	
	            // Should call the load method when done and pass the returned server file id
	            // this server file id is then used later on when reverting or restoring a file
	            // so your server knows which file to return without exposing that info to the client
	            request.onload = function() {
	                if (request.status >= 200 && request.status < 300) {
	                    // the load method accepts either a string (id) or an object
	                    load(request.responseText);
	                    if(request.responseText) {
	                    	alert("프로필 이미지가 업로드 되었습니다.");
	                    	location.reload(true);
	                    }
	                }
	                else {
	                	profilePond.removeFile();
	                    // Can call the error method if something is wrong, should exit after
	                    error('oh no');
	                }
	            };
	
	            request.send(formData);
	            
	            // Should expose an abort method so the request can be cancelled
	            return {
	                abort: () => {
	                    // This function is entered if the user has tapped the cancel button
	                    request.abort();
	
	                    // Let FilePond know the request has been cancelled
	                    abort();
	                }
	            };
	        }
	    }
		
	});
}

function createVideoPond() {
	const inputVideo = document.querySelector(".video_filepond");
	videoPond = FilePond.create(inputVideo, {
		allowFileTypeValidation: true,
		acceptedFileTypes: ['video/*'],
		allowFileSizeValidation: false,
		
		onaddfile: (error, file) => {
		    if (error) {
		        videoPond.removeFile();
		        alert(error.main + "\n" + error.sub);
		        return;
		    }
		    console.log('video added', file.filename);
		},
		
		labelIdle: "<a><span style='font-size: 14px !important;'>동영상 파일을 드래그 앤 드롭하여 업로드</span>" +
					"<span style='font-size: 14px !important; color: #606060;'><br/>동영상을 게시하기 전에는 비공개로 설정됩니다.</span>" +
					"<span class='filepond--label-action'></span></a>",
		server: {
			process:(fieldName, file, metadata, load, error, progress, abort, transfer, options) => {
			
			    // fieldName is the name of the input field
			    // file is the actual file object to send
			    const formData = new FormData();
			    formData.append(fieldName, file, file.name);
			    			
			    const request = new XMLHttpRequest();
			    request.open('POST', uploadUrl + '/video');
			    
			    // Should call the progress method to update the progress to 100% before calling load
			    // Setting computable to false switches the loading indicator to infinite mode
			    request.upload.onprogress = (e) => {
			        progress(e.lengthComputable, e.loaded, e.total);
			    };
			
			    // Should call the load method when done and pass the returned server file id
			    // this server file id is then used later on when reverting or restoring a file
			    // so your server knows which file to return without exposing that info to the client
			    request.onload = function() {
			        if (request.status >= 200 && request.status < 300) {
			            // the load method accepts either a string (id) or an object
			            load(request.responseText);
			            if(request.responseText == null || request.responseText == "0") {
			            	alert("파일 업로드에 실패했습니다.");
			            } else {
			            	getCategories();
			            	console.log(request.responseText)
			            	var returnValue = request.responseText.split(",");
			            	$("#video_upload_area").prop("style", "display: none;");
			            	$("#video_info_area").prop("style", "");
			            	$("input[name=id]")[0].value = returnValue[0];
			            	$("#name").val(returnValue[1]);
			            	getNameLength();
			            }
				        videoPond.removeFile();
			        }
			        else {
				        videoPond.removeFile();
			            // Can call the error method if something is wrong, should exit after
			            error('oh no');
			        }
			    };
			
			    request.send(formData);
			    
			    // Should expose an abort method so the request can be cancelled
			    return {
			        abort: () => {
			            // This function is entered if the user has tapped the cancel button
			            request.abort();
			
			            // Let FilePond know the request has been cancelled
			            abort();
			        }
			    };
			}
		}		
	});
}

function createThumbnailPond() {
	const videoThumbnailImage = document.querySelector(".thumbnail_filepond");
	thumbnailPond = FilePond.create(videoThumbnailImage, {
		allowImageResize: true,
		imageResizeTargetWidth: 1280,
		imageResizeTargetHeight: 720,
		imageResizeMode: "force",
		imageResizeUpscale: true,
		allowFileTypeValidation: true,
		acceptedFileTypes: ['image/png', 'image/jpeg'],
		allowFileSizeValidation: true,
		maxFileSize: "5MB",
		
		onaddfile: (error, file) => {
		    if (error) {
		    	thumbnailPond.removeFile();
		        alert(error.main + "\n" + error.sub);
		        return;
		    }
		    console.log('thumbnail added', file.filename);
		},
		
		labelIdle: '<a><span style="font-size: 12px;">동영상의 내용을 알려주는 이미지를 등록하세요</span><span class="filepond--label-action"></span></a>',
		server: {
	    	process:(fieldName, file, metadata, load, error, progress, abort, transfer, options) => {
	
	            // fieldName is the name of the input field
	            // file is the actual file object to send
	            const formData = new FormData();
	            formData.append(fieldName, file, file.name);
	            formData.append("id", $("input[name=id]").val());
			    
	            const request = new XMLHttpRequest();
	            request.open('POST', uploadUrl + '/video/thumbnail');
	            
	            // Should call the progress method to update the progress to 100% before calling load
	            // Setting computable to false switches the loading indicator to infinite mode
	            request.upload.onprogress = (e) => {
	                progress(e.lengthComputable, e.loaded, e.total);
	            };
	            
	            // Should call the load method when done and pass the returned server file id
	            // this server file id is then used later on when reverting or restoring a file
	            // so your server knows which file to return without exposing that info to the client
	            request.onload = function() {
	                if (request.status >= 200 && request.status < 300) {
	                    // the load method accepts either a string (id) or an object
	                    load(request.responseText);
	                    if(request.responseText) {
	                    	if(request.responseText == "error") {
	                    		alert("미리보기 이미지 업로드 중 오류가 발생했습니다.");
	              				thumbnailPond.removeFile();
	                    	} else {
	                    		console.log(request.responseText);
	                    	}
	                    }
	                }
	                else {
	                	thumbnailPond.removeFile();
	                    // Can call the error method if something is wrong, should exit after
	                    error('oh no');
	                }
	            };
	
	            request.send(formData);
	            
	            // Should expose an abort method so the request can be cancelled
	            return {
	                abort: () => {
	                    // This function is entered if the user has tapped the cancel button
	                    request.abort();
	
	                    // Let FilePond know the request has been cancelled
	                    abort();
	                }
	            };
	        }
	    }
	});
}