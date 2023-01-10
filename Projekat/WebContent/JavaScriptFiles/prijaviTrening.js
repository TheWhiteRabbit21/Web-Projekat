$(document).ready(() => {
	
	var treningNaziv;
	var treningTip;
	var treningSportskiObjekat;
	var treningTrajanje;
	var treningTrener;
    var treningOpis;
    var treningSlika;
    var treningCena;
	
	function addTreningTr(trening){
		let tr = $('<tr></tr>');
		let tdNaziv = $('<td>' + trening.naziv + '</td>');
		let tdTip = $('<td>' + trening.tip + '</td>');
		let tdSportskiObjekat = $('<td>' + trening.sportskiObjekat + '</td>');
        let tdTrajanje = $('<td>' + trening.trajanje + '</td>');
		let tdTrener = $('<td>' + trening.trener + '</td>')
        let tdOpis = $('<td>' + trening.opis + '</td>');
        let tdSlika = $('<td>' + trening.slika + '</td>');
		let tdCena = $('<td>' + trening.cena + '</td>');
		let tdPrijavi	= document.createElement("button");
		tdPrijavi.innerHTML = "Prijavi se";
		tdPrijavi.onclick = function()
        {
			$.post({
				url: 'rest/prijaviTrening',
				data: JSON.stringify({"naziv" : treningNaziv, 
                "tip" : treningTip,
				"sportskiObjekat" : treningSportskiObjekat,
                "trajanje" : treningTrajanje,
				"trener" : treningTrener,
                "opis" : treningOpis,
                "slika" : treningSlika,
				"cena" : treningCena}),
				contentType: 'application/json',
				
				success: function() {
				
				window.location="pocetnaKupac.html";
				
				}
			});	
		}
	
		tr.append(tdNaziv).append(tdTip).append(tdSportskiObjekat).append(tdTrajanje).append(tdTrener).append(tdOpis).append(tdSlika).append(tdCena).append(tdPrijavi);
		$('#tabelaPrikazaTreninga tbody').append(tr);
	}
	
	$.get({
		url: 'rest/getSadrzajZaPrikazati',
		success: function(trening){
			
            treningNaziv = trening.naziv;
            treningTip = trening.tip;
			treningSportskiObjekat = trening.sportskiObjekat;
            treningTrajanje = trening.trajanje;
			treningTrener = trening.trener;
            treningOpis = trening.opis;
            treningSlika = trening.slika;
            treningCena = trening.cena;

			addTreningTr(trening);
		}
	})
	
})