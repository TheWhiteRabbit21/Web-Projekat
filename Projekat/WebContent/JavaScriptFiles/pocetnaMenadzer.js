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
	
	tr.append(tdNaziv).append(tdTip).append(tdTrajanje).append(tdTrener).append(tdOpis).append(tdSlika);
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
	
	$.get({
		url: 'rest/sadrzajSportskogObjekta',
        success: function(sadrzaji) {
			for(let sadrzaj of sadrzaji){
				addSadrzajiTr(sadrzaj);
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
		
		//let trenerNum = document.getElementById('trener').value;
		let trenerProba = document.getElementById('trener');
		let trener = trenerProba.options[trenerProba.selectedIndex].text;
		
		//console.log(JSON.stringify({naziv, tip, sportskiObjekatNaziv, slika, trajanje, opis, trener}))
		
		//if(trenerNum == '1'){trener = "-";}
		
		$.post({
				url: 'rest/dodajSadrzaj',
				data: JSON.stringify({naziv, tip, sportskiObjekat, trajanje, trener, opis, slika}),
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