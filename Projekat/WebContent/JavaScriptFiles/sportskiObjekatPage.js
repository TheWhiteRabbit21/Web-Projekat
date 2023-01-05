function addSportskiObjekatTr(sportskiObjekat) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + sportskiObjekat.naziv + '</td>');
	let tdTipObjekta = $('<td>' + sportskiObjekat.tipObjekta + '</td>');
	let tdStatus = $('<td>' + sportskiObjekat.status + '</td>');
	let tdMapa = $('<td>' + sportskiObjekat.mapa + '</td>');
	let tdLogo = $('<td>' + sportskiObjekat.logo + '</td>');
	let tdProsecnaOcena = $('<td>' + sportskiObjekat.prosecnaOcena + '</td>');
	let tdRadnoVreme = $('<td>' + sportskiObjekat.radnoVreme + '</td>');
	
	tr.append(tdNaziv).append(tdTipObjekta).append(tdStatus).append(tdMapa).append(tdLogo).append(tdProsecnaOcena).append(tdRadnoVreme);
	$('#tabelaPrikazaSO tbody').append(tr);
	//tr.click(clickClosure(sportskiObjekat));
}

function addTrening(trening) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + trening.naziv + '</td>');
	let tdTipTreninga = $('<td>' + trening.tip + '</td>');
	let tdTrajanje = $('<td>' + trening.trajanje + '</td>');
	let tdOpis = $('<td>' + trening.opis + '</td>');
	let tdSlika = $('<td>' + trening.slika + '</td>');
	let tdCena = $('<td>' + trening.cena + '</td>');
	tr.append(tdNaziv).append(tdTipTreninga).append(tdTrajanje).append(tdOpis).append(tdSlika).append(tdCena);
	$('#tabelaSadrzaja tbody').append(tr);
}

$(document).ready(function() {
	
	$.get({
		url: 'rest/prikaziSportskiObjekat',
        success: function(sportskiObjekat) {
			addSportskiObjekatTr(sportskiObjekat);
			
			$.get({
				url: 'rest/prikaziTreningeSportskogObjekta/' + sportskiObjekat.naziv,
				contentType: 'application/json',
      			success: function(treninzi) {
					for (let trening of treninzi) 
					{
                		addTrening(trening);
            		}
				}
			});
			
		}
	});
	
});