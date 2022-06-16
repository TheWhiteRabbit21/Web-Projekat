$(document).ready(() => {
	//window.alert("radi java script fajl");
	
	$('#registracionaForma').submit((event) => {
		
		//window.alert("radi reg forma dugme");
		
		event.preventDefault();
		let username = $('#username').val();
		let lozinka = $('#password').val();
		let ime = $('#ime').val();
		let prezime = $('#prezime').val();
        //let pol = $('.pol').val();
		let datumRodjenja = $('#datum').val();

		let pol = 1;
		let uloga = 0;
		let deleted = false;
		
		//console.log('username', username);
		//console.log('password', password);
		
		console.log(JSON.stringify({username, lozinka, ime, prezime, pol, datumRodjenja, uloga, deleted}))
		
		
		$.post({
				url: 'rest/register',
				data: JSON.stringify({username, lozinka, ime, prezime, pol, datumRodjenja, uloga, deleted}),
				contentType: 'application/json',

				success: function() {
					//window.location="./main.html";
                    window.location="./index.html";
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