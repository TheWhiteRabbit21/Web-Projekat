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
				"tipObjekta" : this.selectedSportskiObjekat.tipObjekta, "sadrzaj" : this.selectedSportskiObjekat.sadrzaj,
				 "status" : this.selectedSportskiObjekat.status, "mapa" : this.selectedSportskiObjekat.mapa, 
				 "logo" : this.selectedSportskiObjekat.logo, "prosecnaOcena" : this.selectedSportskiObjekat.prosecnaOcena,
				 "radnoVreme" : this.selectedSportskiObjekat.radnoVreme}),
				contentType: 'application/json',
				
				success: function() {
				
				window.location="sportskiObjekatPage.html";
				
				}
			});
		}
	}
	
	
	
	
	
	
	
});



/*function addSportskiObjekatTr(sportskiObjekat) {
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
	$('#tabelaSportskihObjekata tbody').append(tr);
	//tr.click(clickClosure(sportskiObjekat));
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
	
	
	
	
});*/


