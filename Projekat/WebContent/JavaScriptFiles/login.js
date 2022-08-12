$(document).ready(function() {
	$('#loginForma').submit(function(event) {
		
		event.preventDefault();
		let username = $('input[name="username"]').val();
		let password = $('input[name="password"]').val();
		
		$('#error').hide();
		
		window.alert(username);
		window.alert(password);
		
		$.post({
			url: 'rest/login',
			data: JSON.stringify({username: username, password: password}),
			contentType: 'application/json',
			
			success: function() {
				$('#success').text('korisnik uspesno ulogovan!');
				$("#success").show().delay(3000).fadeOut();
				
				
			},
			statusCode: {
				400: function() {
					$('#error').text("Invalid username and/or password iz JS-a!");
					$("#error").show().delay(3000).fadeOut();
			   },
			},

		});
	});
});


