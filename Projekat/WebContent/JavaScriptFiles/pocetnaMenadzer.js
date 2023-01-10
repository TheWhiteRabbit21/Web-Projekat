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
	$('#tabelaSportskogObjekta tbody').append(tr);
}

function addSadrzajiTr(sadrzaj) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + sadrzaj.naziv + '</td>');
	let tdTip = $('<td>' + sadrzaj.tip + '</td>');
	let tdTrajanje = $('<td>' + sadrzaj.trajanje + '</td>');
	let tdTrener = $('<td>' + sadrzaj.trener + '</td>');
	let tdOpis = $('<td>' + sadrzaj.opis + '</td>');
	let tdSlika = $('<td>' + sadrzaj.slika + '</td>');
	let tdCena = $('<td>' + sadrzaj.cena + '</td>');

	tr.append(tdNaziv).append(tdTip).append(tdTrajanje).append(tdTrener).append(tdOpis).append(tdSlika).append(tdCena);
	$('#tabelaSadrzaja tbody').append(tr);
}

function addTreneri(trener, counter){		
	let option = $('<option value="' + counter + '">' + trener.username +'</option>');
	$('#trener').append(option);
}

$(document).ready(function() {
	
	var sportskiObjekat = "";
	
	$.get({
		url: 'rest/prikaziSportskiObjekat/Menadzer',
        success: function(so) {
			addSportskiObjekatTr(so);
			$('#sportskiObjekatNaziv').append(so.naziv);
			sportskiObjekat = so.naziv;
			
			var sadrzaji = new Vue({
				el: '#tabelaSadrzaja',
				data: {
					sadrzaji: null,
					selectedSadrzaj: {}
				},
				mounted() {
					axios
					.get('rest/prikaziTreningeSportskogObjekta/' + so.naziv)
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
								window.location="sadrzajPage.html";
							}
						});
					}
				}
			});


		}
	});
	
	$.get({
		url: 'rest/treneri',
        success: function(treneri) {
			$('#trener').append('<option value="1">-</option>');
			let counter = 1;
			for(let trener of treneri){
				addTreneri(trener, counter);
			}
		}
	});
	
	
	$('#dodajSadrzajForm').submit((event) => {
		
		event.preventDefault();
		let naziv = $('#naziv').val();
		let tip = $('#tip').val();
		//let sportskiObjekatNaziv = $('#sportskiObjekatNaziv').val();
		let slika = $('#slika').val();
		let trajanje = $('#trajanje').val();
		let opis = $('#opis').val();
		let cena = $('#cena').val();

		//let trenerNum = document.getElementById('trener').value;
		let trenerProba = document.getElementById('trener');
		let trener = trenerProba.options[trenerProba.selectedIndex].text;
		
		//console.log(JSON.stringify({naziv, tip, sportskiObjekatNaziv, slika, trajanje, opis, trener, cena}))
		
		//if(trenerNum == '1'){trener = "-";}
		
		$.post({
				url: 'rest/dodajSadrzaj',
				data: JSON.stringify({naziv, tip, sportskiObjekat, trajanje, trener, opis, slika, cena}),
				contentType: 'application/json',
				success: function() {					
					$('#successSadrzaj').text("Uspesno dodat novi sadrzaj sportskom objektu!");
					$("#successSadrzaj").show().delay(5000).fadeOut();
				},
				statusCode: {
					400: function() {
						$('#errorSadrzaj').text("Greska pri unosu, ime vec postoji!");
						$("#errorSadrzaj").show().delay(5000).fadeOut();
					},
				},	
			})
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});