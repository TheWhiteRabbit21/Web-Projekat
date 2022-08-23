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
	let tdMenadzer = $('<td>' + sportskiObjekat.menadzer + '</td>');
	
	tr.append(tdNaziv).append(tdTipObjekta).append(tdSadrzaj).append(tdStatus).append(tdMapa).append(tdLogo).append(tdProsecnaOcena).append(tdRadnoVreme).append(tdMenadzer);
	//tr.click(clickClosure(sportskiObjekat));
	$('#tabelaSportskihObjekata tbody').append(tr);
}

$(document).ready(function() {
	$.get({
		url: 'rest/sportskiObjekti',
        success: function(sportskiObjekti) {
			for (let sportskiObjekat of sportskiObjekti) {
				addSportskiObjekatTr(sportskiObjekat);
			}
		}
	});
});