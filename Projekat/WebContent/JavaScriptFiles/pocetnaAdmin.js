$(document).ready(() => {
	
	//--------------------------------------------------------------------------------------//
	
	function addKorisnikTr(korisnik){
		let tr = $('<tr></tr>');
		let tdUsername = $('<td>' + korisnik.username + '</td>');
		let tdIme = $('<td>' + korisnik.ime + '</td>');
		let tdPrezime = $('<td>' + korisnik.prezime + '</td>');
		let tdPol = $('<td>' + korisnik.pol + '</td>');
		let tdUloga = $('<td>' + korisnik.uloga + '</td>');
		let tdDatumRodjenja = $('<td>' + korisnik.datumRodjenja + '</td>');
	
		tr.append(tdUsername).append(tdIme).append(tdPrezime).append(tdPol).append(tdUloga).append(tdDatumRodjenja);
		$('#tabelaKorisnika tbody').append(tr);
	}
	
	$.get({
		url: 'rest/korisnici',
		success: function(korisnici){
			for(let korisnik of korisnici){
				addKorisnikTr(korisnik);
			}
		}
	})
	
	
	
	//--------------------------------------------------------------------------------------//
	
	$('#dodajKorisnikaForm').submit((event) => {
		
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
		let uloga = "";
		let deleted = false;
				
		let validationFlag = true;

		pol = proveriPol();
		uloga = proveriUlogu();
		
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
					url: 'rest/register/korisnik',
					data: JSON.stringify({"username" : usernameVal, "password" : passwordVal, "ime" : imeVal, "prezime" : prezimeVal, pol, "datumRodjenja" : datumRodjenjaVal, uloga, deleted}),
					contentType: 'application/json',

					success: function() {
						$('#successKorisnik').text("Uspesno dodat novi korisnik!");
						$("#successKorisnik").show().delay(5000).fadeOut();
					},
					statusCode: {
						401: function() {
							$('#errorKorisnik').text("Greska pri unosu, ime moraju biti slova!");
							$("#errorKorisnik").show().delay(5000).fadeOut();
						},

						400: function() {
							$('#errorKorisnik').text("Greska pri unosu, korisnicko ime vec postoji!");
							$("#errorKorisnik").show().delay(5000).fadeOut();
						},
						
						402: function() {
							$('#errorKorisnik').text("Greska pri unosu, prezime moraju biti slova!");
							$("#errorKorisnik").show().delay(5000).fadeOut();
						},
						403: function() {
							$('#errorKorisnik').text("Greska pri unosu, telefon mora biti broj!");
							$("#errorKorisnik").show().delay(5000).fadeOut();
						},
					},	
				})
				
				
		}
		
		
		
	})
		
	//--------------------------------------------------------------------------------------//
	
	var menadzeriBezSO = false
	
	function addMenadzerOption(menadzer, counter){
		
		let option = $('<option value="' + counter + '">' + menadzer.username +'</option>');
		$('#menadzer').append(option);
	
	}
	
	$.get({
		url: 'rest/menadzeri',
		success: function(menadzeri){
			//ovde dodati prvi red za value=1
			$('#menadzer').append('<option value="1"></option>');
			let counter = 1;
			for(let menadzer of menadzeri){
				if(menadzer.sportskiObjekat == ''){
					counter++;
					addMenadzerOption(menadzer, counter);
					menadzeriBezSO = true;
				}
			}
			if(!menadzeriBezSO){
				document.getElementById("menadzer").style.display = "none";
			}
			else{document.getElementById("dodajMenadzera").style.display = "none";}
		}
	})
		
	
		
	$('#dodajSportskiObjekatForm').submit((event) => {
		
		event.preventDefault();
		let naziv = $('#naziv').val();
		let tipObjekta = $('#tipObjekta').val();
		//let sadrzaj = $('#sadrzaj').val();
		//let sadrzaj = "";
		let status = "radi";
		let mapa = $('#mapa').val();
		let logo = $('#logo').val();
		let prosecnaOcena = $('#prosecnaOcena').val();
		let radnoVreme = $('#radnoVreme').val();
		
		let menadzerNum = document.getElementById("menadzer").value;
		
		let menadzerProba = document.getElementById('menadzer');
		let menadzer = menadzerProba.options[menadzerProba.selectedIndex].text;
		
		let menadzerUsername = $('#usernameMenadzer').val();
		let menadzerPassword = $('#passwordMenadzer').val();
		let menadzerIme	= $('#imeMenadzer').val();
		let menadzerPrezime = $('#prezimeMenadzer').val();
		let menadzerDatum	= $('#datumMenadzer').val();
		let menadzerPol	= $('#polMenadzer').val();
		
		
		//window.alert(menadzer);
		
		console.log(JSON.stringify({naziv, tipObjekta, /*sadrzaj, */
			status, mapa, logo, prosecnaOcena, radnoVreme, menadzer}))
			
		console.log(JSON.stringify({menadzerUsername, menadzerPassword, menadzerIme, 
		menadzerPrezime, menadzerDatum, menadzerPol}))
			
		
		if(menadzerNum != '1')
		{
		$.post({
				url: 'rest/dodajSportskiObjekat',
				data: JSON.stringify({naziv, tipObjekta, /*sadrzaj, */
					status, mapa, logo, prosecnaOcena, radnoVreme, menadzer}),
				contentType: 'application/json',
				success: function() {
					$('#successDodajSO').text("Uspesno dodat novi sportski Objekat!");
					$("#successDodajSO").show().delay(5000).fadeOut();
				},
				statusCode: {
					400: function() {
						$('#errorDodajSO').text("Greska pri unosu, ime vec postoji!");
						$("#errorDodajSO").show().delay(5000).fadeOut();
					},
				},	
			})
		}
		else{
			let menadzerUloga = "MENADZER";
			$.post({
				url: 'rest/register/menadzer',
				data: JSON.stringify({"username" : menadzerUsername, "password" : menadzerPassword, "ime" : menadzerIme, 
				"prezime" : menadzerPrezime, "datumRodjenja" : menadzerDatum, "pol" : menadzerPol, "uloga" : menadzerUloga, "sportskiObjekat" : naziv}),
				contentType: 'application/json',
				success: function() {					
					$('#successDodajMenadzera').text("Uspesno dodat novi menadzer!");
					$("#successDodajMenadzera").show().delay(5000).fadeOut();
				},
				statusCode: {
					400: function() {
						$('#errorDodajMenadzera').text("Greska pri unosu, ime vec postoji!");
						$("#errorDodajMenadzera").show().delay(5000).fadeOut();
					},
				},	
			})
			
			$.post({
				url: 'rest/dodajSportskiObjekat',
				data: JSON.stringify({naziv, tipObjekta, /*sadrzaj, */
					status, mapa, logo, prosecnaOcena, radnoVreme, "menadzer" : menadzerUsername}),
				contentType: 'application/json',
				success: function() {					
					$('#successDodajSO').text("Uspesno dodat novi sportski Objekat!");
					$("#successDodajSO").show().delay(5000).fadeOut();
				},
				statusCode: {
					400: function() {
						$('#errorDodajSO').text("Greska pri unosu, ime vec postoji!");
						$("#errorDodajSO").show().delay(5000).fadeOut();
					},
				},	
			})
		}
		
	})
		
		
		
	//--------------------------------------------------------------------------------------//
		
	
function proveriSlova(unos) 
{    
   	return unos.value[0] >= 'A' && unos.value[0] <= 'Z';
}

function proveriPol() 
{
	let pol = $('#pol').val();
	if(!pol){console.log("Pol nije unesen!")}
	return pol;
}

function proveriUlogu() 
{
	let uloga = $('#uloga').val();
	if(!uloga){console.log("Uloga nije unesena!")}
	return uloga;
}
	

	//--------------------------------------------------------------------------------------//
	
	
})