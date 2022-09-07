function addTrening(trening) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + trening.naziv + '</td>');
	let tdTipTreninga = $('<td>' + trening.tip + '</td>');
	let tdSportskiObjekat = $('<td>' + trening.sportskiObjekat + '</td>');
	let tdTrajanje = $('<td>' + trening.trajanje + '</td>');
	let tdOpis = $('<td>' + trening.opis + '</td>');
	let tdSlika = $('<td>' + trening.slika + '</td>');
	if(trening.tip == 'personalni'){	
		let tdOtkaziDugme = $('<td> <button id="OtkaziDugme"> Otkazi </button> </td>');
		tr.append(tdNaziv).append(tdTipTreninga).append(tdSportskiObjekat).append(tdTrajanje).append(tdOpis).append(tdSlika).append(tdOtkaziDugme);
		$('#tabelaSadrzaja tbody').append(tr);
	}
	else{
		let tdOtkaziDugme = $('<td></td>');
		tr.append(tdNaziv).append(tdTipTreninga).append(tdSportskiObjekat).append(tdTrajanje).append(tdOpis).append(tdSlika).append(tdOtkaziDugme);
		$('#tabelaSadrzaja tbody').append(tr);
	}
	
	
}

function prikaziTreninge(k){
	
	let trener = k;
	let url = 'rest/prikaziTreninge/' + trener;
	
	$.get({
		url: url,
		contentType: 'application/json',
        success: function(treninzi) {
			for (let trening of treninzi) 
			{
                addTrening(trening);
            }
		}
	});
}



$(document).ready(function() {

	$.get({
		url: 'rest/getTrenutniKorisnik',
        success: function(k) {
			prikaziTreninge(k);
		}
	});

	
	
});















/*$(document).ready(function() {

	var trener = "";

	$.get({
		url: 'rest/getTrenutniKorisnik',
        success: function(k) {
			trener = k;
		}
	});

	var url = 'rest/prikaziTreninge/' + trener;
	
	$.get({
		url: url,
		contentType: 'application/json',
        success: function(treninzi) {
			for (let trening of treninzi) 
			{
                addTrening(trening);
            }
		}
	});
	
});*/