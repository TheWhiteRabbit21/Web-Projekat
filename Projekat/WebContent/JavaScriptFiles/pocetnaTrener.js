function addGrupniTrening(trening) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + trening.naziv + '</td>');
	let tdTipTreninga = $('<td>' + trening.tip + '</td>');
	let tdSportskiObjekat = $('<td>' + trening.sportskiObjekat + '</td>');
	let tdTrajanje = $('<td>' + trening.trajanje + '</td>');
	let tdOpis = $('<td>' + trening.opis + '</td>');
	let tdSlika = $('<td>' + trening.slika + '</td>');

	tr.append(tdNaziv).append(tdTipTreninga).append(tdSportskiObjekat).append(tdTrajanje).append(tdOpis).append(tdSlika);
	$('#tabelaGrupnihTreninga tbody').append(tr);


}

function addOstaliTrening(trening) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + trening.naziv + '</td>');
	let tdTipTreninga = $('<td>' + trening.tip + '</td>');
	let tdSportskiObjekat = $('<td>' + trening.sportskiObjekat + '</td>');
	let tdTrajanje = $('<td>' + trening.trajanje + '</td>');
	let tdOpis = $('<td>' + trening.opis + '</td>');
	let tdSlika = $('<td>' + trening.slika + '</td>');

	tr.append(tdNaziv).append(tdTipTreninga).append(tdSportskiObjekat).append(tdTrajanje).append(tdOpis).append(tdSlika);
	$('#tabelaOstalihTreninga tbody').append(tr);

}

function prikaziTreninge(k) {

	let trener = k;
	let urlGrupniTreninzi = 'rest/prikaziGrupneTreningeTrenera/' + trener;
	let urlOstaliTreninzi = 'rest/prikaziOstaleTreningeTrenera/' + trener;

	$.get({
		url: urlGrupniTreninzi,
		contentType: 'application/json',
		success: function (treninzi) {
			for (let trening of treninzi) {
				addGrupniTrening(trening);
			}
		}
	});

	$.get({
		url: urlOstaliTreninzi,
		contentType: 'application/json',
		success: function (treninzi) {
			for (let trening of treninzi) {
				addOstaliTrening(trening);
			}
		}
	});
}

$(document).ready(function () {

	$.get({
		url: 'rest/getTrenutniKorisnik',
		success: function (t) {
			
			prikaziTreninge(t);

			var clanarine = new Vue({
				el: '#tabelaPersonalnihTreninga',
				data: {
					personalniTreninzi: null,
					selectedTrening: {}
				},
				mounted() {
					axios
						.get('rest/prikaziPersonalneTreningeTrenera/' + t) //ili samo trenutniKorisnik
						.then(response => (this.personalniTreninzi = response.data))
				},
				methods: {
					selectTrening: function (personalniTrening) {
						this.selectedTrening = personalniTrening;
		
						$.post({
							url: 'rest/personalniTreningPage',
							data: JSON.stringify({
								"naziv": this.selectedTrening.naziv,
								"tip": this.selectedTrening.tip,
								"sportskiObjekat": this.selectedTrening.sportskiObjekat,
								"trajanje": this.selectedTrening.trajanje,
								"opis": this.selectedTrening.opis,
								"slika": this.selectedTrening.slika,
							}),
							contentType: 'application/json',
		
							success: function () {
		
								window.location = "personalniTreningPage.html";
		
							}
						});
					}
				}
			});




		}
	});


	

});

























/*function addTrening(trening) {
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
	let url = 'rest/prikaziTreningeTrenera/' + trener;
	
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

*/













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