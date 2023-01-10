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




$(document).ready(function() {
	
	$.get({
		url: 'rest/prikaziSportskiObjekat',
        success: function(sportskiObjekat) {
			addSportskiObjekatTr(sportskiObjekat);

            var sadrzaji = new Vue({
				el: '#tabelaSadrzaja',
				data: {
					sadrzaji: null,
					selectedSadrzaj: {}
				},
				mounted() {
					axios
					.get('rest/prikaziTreningeSportskogObjekta/' + sportskiObjekat.naziv)
					.then(response => (this.sadrzaji = response.data))
				},
				methods: {
					selectSadrzaj : function(sadrzaj){
						this.selectedSadrzaj = sadrzaj;

						$.post({
							url: 'rest/sadrzajPageEdit',
							data: JSON.stringify({"naziv" : this.selectedSadrzaj.naziv,
							"tip" : this.selectedSadrzaj.tip,
							"trajanje" : this.selectedSadrzaj.trajanje,
							"trener" : this.selectedSadrzaj.trener,
							"opis" : this.selectedSadrzaj.opis,
							"slika": this.selectedSadrzaj.slika,
							"cena" : this.selectedSadrzaj.cena}),
							contentType: 'application/json',

							success: function() {
								window.location="prijaviTrening.html";
							}
						});
					}
				}
			});


		}
	});
	
});