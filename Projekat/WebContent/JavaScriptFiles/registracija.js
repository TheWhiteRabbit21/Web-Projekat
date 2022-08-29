$(document).ready(() => {
	//window.alert("radi java script fajl");
	
	$('#registracionaForma').submit((event) => {
		
		//window.alert("radi reg forma dugme");
		
		event.preventDefault();
		let usernameVal = $('#username').val();
		let passwordVal = $('#password').val();
		let imeVal = $('#ime').val();
		let prezimeVal = $('#prezime').val();
		let datumRodjenjaVal = $('#datum').val();

		let username = document.getElementById("username")
		let ime = document.getElementById("ime")
		let prezime = document.getElementById("prezime")
		let pol = "";
		let uloga = 0; //kupac - trebalo bi da moze i uloga = "KUPAC"
		let deleted = false;
				
		let validationFlag = true;

		pol = proveriPol();
		
		//console.log(JSON.stringify({username, password, ime, prezime, pol, datumRodjenja, uloga, deleted}))
		
		if(!(username.value)){
			username.style.background = "red"
			validationFlag = false;
		} else if(!( ime.value && proveriSlova(ime))) {
			ime.style.background = "red"
			validationFlag = false;
		} else if (!(prezime.value && proveriSlova(prezime))) {
			prezime.style.background = "red"
			validationFlag = false;
		}
		
		//window.alert(username);
		
		if(validationFlag)
		{
			//window.alert("salje post zahtev");
			
			console.log(JSON.stringify({usernameVal, passwordVal, imeVal, prezimeVal, pol, datumRodjenjaVal, uloga, deleted}))
			
			
			$.post({
					url: 'rest/register/kupac',
					data: JSON.stringify({"username" : usernameVal, "password" : passwordVal, "ime" : imeVal, "prezime" : prezimeVal, pol, "datumRodjenja" : datumRodjenjaVal, uloga, deleted}),
					contentType: 'application/json',

					success: function() {
						//alert("usao je u success");
						//window.location="./main.html";
						window.location="login.html";
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
				
				
		}

	})
})

function proveriSlova(unos) {    
    return unos.value[0] >= 'A' && unos.value[0] <= 'Z';
}

function proveriPol() {
	let pol = $('#pol').val();
	if(!pol){console.log("Pol nije unesen!")}
	return pol;
}

