$(document).ready(function() {
	$('#loginForma').submit(function(event) {
		
		event.preventDefault();
		let username = $('input[name="username"]').val();
		let password = $('input[name="password"]').val();
		
		$('#error').hide();
		
		//window.alert(username);
		//window.alert(password);
		
		$.post({
			url: 'rest/login',
			data: JSON.stringify({username: username, password: password}),
			contentType: 'application/json',
			
			success: function(korisnik) {
				//$('#success').text('korisnik uspesno ulogovan!');
				//$("#success").show().delay(3000).fadeOut();
				console.log(korisnik);
				
				if(korisnik!=null)
					{
						//$('#odjava').attr('hidden', false); 
						if(korisnik.uloga == "KUPAC" || korisnik.uloga == "MENADZER") //uloga-0 -> kupac / uloga-1 -> menadzer
						{
							//window.location='./kupac.html';
							window.location='kupac.html';
						}
					 	else if(korisnik.uloga == "ADMINISTRATOR") //uloga-2 -> administrator
						{
							//window.location='./pocetnaAdmin.html';
							window.location='pocetnaAdmin.html';
						}
						else if(korisnik.uloga == "TRENER") //uloga-3 -> trener
						{
							//window.location='./trener.html';
							window.location='trener.html';
						}
					}
					else{
						$('#error').text("Invalid username and/or password iz JS-a!");
						$("#error").show().delay(3000).fadeOut();
					}
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


