$(document).ready(() => {
	
	var treningNaziv;
	var treningTip;
	var treningTrajanje;
    var treningOpis;
    var treningSlika;
    var treningCena;
	
	function addTreningTr(trening){
		let tr = $('<tr></tr>');
		let tdNaziv = $('<td>' + trening.naziv + '</td>');
		let tdTip = $('<td>' + trening.tip + '</td>');
        let tdTrajanje = $('<td>' + trening.trajanje + '</td>');
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
                "trajanje" : treningTrajanje,
                "opis" : treningOpis,
                "slika" : treningSlika,
				"cena" : treningCena}),
				contentType: 'application/json',
				
				success: function() {
				
				window.location="pocetnaKupac.html";
				
				}
			});	
		}
	
		tr.append(tdNaziv).append(tdTip).append(tdTrajanje).append(tdOpis).append(tdSlika).append(tdCena).append(tdPrijavi);
		$('#tabelaPrikazaTreninga tbody').append(tr);
	}
	
	$.get({
		url: 'rest/getSadrzajZaPrikazati',
		success: function(trening){
			
            treningNaziv = trening.naziv;
            treningTip = trening.tip;
            treningTrajanje = trening.trajanje;
            treningOpis = trening.opis;
            treningSlika = trening.slika;
            treningCena = trening.cena;

			addTreningTr(trening);
		}
	})
	
})