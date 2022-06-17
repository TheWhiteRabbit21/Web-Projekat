$(document).ready(function() {
	$.get({
		url: 'rest/register/uspesnaRegistracija',
        success: function(korisnik) {
			/*let pkorisnik = korisnik;
			let p = $('Korisnik ' + pkorisnik + ' je uspesno registrovan!');
			$('#potvrdaRegistracije').append(p);
			*/alert('trebalo bi da je ispisao ime');
			
		}
	});
});