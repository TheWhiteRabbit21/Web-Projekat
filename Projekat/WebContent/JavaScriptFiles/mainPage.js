$(document).ready(function() {
	
	alert("Usao je u javaScript na Main Page-u");
	//let p = ('Korisnik ? je uspesno registrovan! Umem da ispisem sranja na js stranici?');
	//$('#potvrdaRegistracije').append(p);
	
	
	
	$.get({
		url: 'rest/register/uspesnaRegistracija',
        success: function(korisnik) {
			alert("Usao je u javaScript od uspesneRegistracije");
			/*let pkorisnik = korisnik;
			let p = $('Korisnik ' + pkorisnik + ' je uspesno registrovan!');
			$('#potvrdaRegistracije').append(p);
			*/
			
		}
	});
});