$(document).ready(() => {
	
	//--------------------------------------------------------------------------------------//
	var treningNaziv;

	function addTreningTr(trening){
		let tr = $('<tr></tr>');
		let tdNaziv = $('<td>' + trening.naziv + '</td>');
		let tdTip = $('<td>' + trening.tip + '</td>');
		let tdSportskiObjekat = $('<td>' + trening.sportskiObjekat + '</td>');
        let tdTrajanje = $('<td>' + trening.trajanje + '</td>');
        let tdOpis = $('<td>' + trening.opis + '</td>');
        let tdSlika = $('<td>' + trening.slika + '</td>');

		let tdOtkazi = document.createElement("button");

		tdOtkazi.innerHTML = "Otkazi trening";
		tdOtkazi.onclick = function()
		{
			$.post({
				url: 'rest/otkaziTrening',
				data: JSON.stringify({"naziv" : treningNaziv}),
				contentType: 'application/json',
				
				success: function() {
				
				window.location="pocetnaTrener.html";
				
				}
			});
		}
	
		tr.append(tdNaziv).append(tdTip).append(tdSportskiObjekat).append(tdTrajanje).append(tdOpis).append(tdSlika).append(tdOtkazi);
		$('#tabelaPrikazaTR tbody').append(tr);
	}
	
	$.get({
		url: 'rest/prikaziTrening',
		success: function(trening){
			
			treningNaziv = trening.naziv;

			addTreningTr(trening);
		}
	})
	
	
	
	
})