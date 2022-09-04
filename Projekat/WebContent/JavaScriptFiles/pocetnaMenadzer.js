function addSportskiObjekatTr(sportskiObjekat) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + sportskiObjekat.naziv + '</td>');
	let tdTipObjekta = $('<td>' + sportskiObjekat.tipObjekta + '</td>');
	let tdSadrzaj = $('<td>' + sportskiObjekat.sadrzaj + '</td>');
	let tdStatus = $('<td>' + sportskiObjekat.status + '</td>');
	let tdMapa = $('<td>' + sportskiObjekat.mapa + '</td>');
	let tdLogo = $('<td>' + sportskiObjekat.logo + '</td>');
	let tdProsecnaOcena = $('<td>' + sportskiObjekat.prosecnaOcena + '</td>');
	let tdRadnoVreme = $('<td>' + sportskiObjekat.radnoVreme + '</td>');
	
	tr.append(tdNaziv).append(tdTipObjekta).append(tdSadrzaj).append(tdStatus).append(tdMapa).append(tdLogo).append(tdProsecnaOcena).append(tdRadnoVreme);
	$('#tabelaSportskogObjekta tbody').append(tr);
}

function addTreneri(trener, counter){		
	let option = $('<option value="' + counter + '">' + trener.username +'</option>');
	$('#trener').append(option);
}

$(document).ready(function() {
	
	$.get({
		url: 'rest/prikaziSportskiObjekat/Menadzer',
        success: function(sportskiObjekat) {
			addSportskiObjekatTr(sportskiObjekat);
			$('#sportskiObjekatNaziv').append(sportskiObjekat.naziv);
		}
	});
	
	$.get({
		url: 'rest/treneri',
        success: function(treneri) {
			$('#trener').append('<option value="1"></option>');
			let counter = 1;
			for(let trener of treneri){
				addTreneri(trener, counter);
			}
		}
	});
	
	
	$('#dodajSadrzajForm').submit((event) => {
		
		event.preventDefault();
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});