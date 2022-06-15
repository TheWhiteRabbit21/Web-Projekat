$(document).ready(() => {
	$('#registracionaForma').submit((event) => {
		event.preventDefault();
		let username = $('#username').val();
		let password = $('#password').val();
		let ime = $('#ime').val();
		let prezime = $('#prezime').val();
		let datum = $('#datum').val();
        let pol = $('.pol').val();

		console.log('username', username);
		console.log('password', password);
		$.post({
				url: 'Projekat/registracija',
				data: JSON.stringify({username, password, ime, prezime, datum, pol}),
				contentType: 'application/json',

				success: function() {
					//window.location="./main.html";
                    window.location="./login.html";
                },
				
				statusCode: {
				    401: function() {
				    	$('#error').text("Greska pri unosu, ime moraju biti slova!");
						$("#error").show().delay(5000).fadeOut();
				    },

				    400: function() {
				    	$('#error').text("Greska pri unosu, korisnicko ime vec postoji!");
						$("#error").show().delay(5000).fadeOut();
				   },
				   
				   402: function() {
				    	$('#error').text("Greska pri unosu, prezime moraju biti slova!");
						$("#error").show().delay(5000).fadeOut();
				    },
				    403: function() {
				    	$('#error').text("Greska pri unosu, telefon mora biti broj!");
						$("#error").show().delay(5000).fadeOut();
				    },
				},	
            
			})
		})
})