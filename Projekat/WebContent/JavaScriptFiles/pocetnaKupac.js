function addIstorijuTreningaTr(it) {
	let tr = $('<tr></tr>');
	let tdSO = $('<td>' + it.sportskiObjekat + '</td>');
	let tdIme = $('<td>' + it.trening + '</td>');
	let tdVreme = $('<td>' + it.datumIVremePrijaveString + '</td>');

	tr.append(tdSO).append(tdIme).append(tdVreme);
	$('#tabelaIstorijeTreningaKorisnika tbody').append(tr);
}

$(document).ready(function() {

	$.get({
		url: 'rest/korisnikovaIstorijaTreninga',
		success: function(istorijaTreninga) {				
			for(let it of istorijaTreninga){
				addIstorijuTreningaTr(it);
			}
		}
	});

	
	
});

var clanarine = new Vue({
	el:'#tabelaClanarina',
	data: {
		clanarine: null,
		selectedClanarina: {}
	},
	mounted () {
        axios
          .get('rest/clanarine')
          .then(response => (this.clanarine = response.data))
    },
	methods: {
		selectClanarinu : function(clanarina){
			this.selectedClanarina = clanarina;
			
			$.post({
				url: 'rest/clanarinaPage',
				data: JSON.stringify({"tip" : this.selectedClanarina.tip, 
				"brojTermina" : this.selectedClanarina.brojTermina,
				 "cena" : this.selectedClanarina.cena}),
				contentType: 'application/json',
				
				success: function() {
				
				window.location="clanarinaPage.html";
				
				}
			});
		}
	}
});

var sportskiObjekti = new Vue({
	el:'#tabelaSportskihObjekata',
	data: {
		sportskiObjekti: null,
		selectedSportskiObjekat: {}
	},
	mounted () {
        axios
          .get('rest/sportskiObjekti')
          .then(response => (this.sportskiObjekti = response.data))
    },
	methods: {
		selectSportskiObjekat : function(sportskiObjekat){
			this.selectedSportskiObjekat = sportskiObjekat;
			
			
			$.post({
				url: 'rest/sportskiObjekatPage',
				data: JSON.stringify({"naziv" : this.selectedSportskiObjekat.naziv, 
				"tipObjekta" : this.selectedSportskiObjekat.tipObjekta,
				 "status" : this.selectedSportskiObjekat.status, "mapa" : this.selectedSportskiObjekat.mapa, 
				 "logo" : this.selectedSportskiObjekat.logo, "prosecnaOcena" : this.selectedSportskiObjekat.prosecnaOcena,
				 "radnoVreme" : this.selectedSportskiObjekat.radnoVreme}),
				contentType: 'application/json',
				
				success: function() {
				
				window.location="sportskiObjekatKupacPage.html";
				
				}
			});
		}
	}
	
	
});