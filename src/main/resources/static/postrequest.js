$(document).ready(
		function() {

			// SUBMIT FORM
			$("#prepareSendForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					topicName : $("#topicName").val(),
					key : $("#key").val(),
					message : $("#message").val(),
					countMessage : $("#countMessage").val(),
					countThreads : $("#countThreads").val()
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "rest-home",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#postResultDiv").html(
									"Количество отправленных сообщений: " + result.data.countMessage  + "</p>");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});
			}
		})