$(document).ready(() => {
	
	//--------------------------------------------------------------------------------------//
	var clanarinaTip;
	var clanarinaBrojTermina;
	var clanarinaCena;
	
	
	function addClanarinaTr(clanarina){
		let tr = $('<tr></tr>');
		let tdTip = $('<td>' + clanarina.tip + '</td>');
		let tdBrojTermina = $('<td>' + clanarina.brojTermina + '</td>');
		let tdCena = $('<td>' + clanarina.cena + '</td>');
		//let tdPlati	= $('<td> <button id="dugmePlati">Plati</button> </td>');
		let tdPlati	= document.createElement("button");
		tdPlati.innerHTML = "Plati";
		tdPlati.onclick = function(){
			
			$.post({
				url: 'rest/platiClanarinu',
				data: JSON.stringify({"tip" : clanarinaTip, 
				"brojTermina" : clanarinaBrojTermina,
				 "cena" : clanarinaCena}),
				contentType: 'application/json',
				
				success: function() {
				
				window.location="pocetnaKupac.html";
				
				}
			});
			
		}
	
		tr.append(tdTip).append(tdBrojTermina).append(tdCena).append(tdPlati);
		$('#tabelaPrikazaCL tbody').append(tr);
	}
	
	$.get({
		url: 'rest/prikaziClanarinu',
		success: function(clanarina){
			
			clanarinaTip = clanarina.tip;
			clanarinaBrojTermina = clanarina.brojTermina;
			clanarinaCena = clanarina.cena;
			
			addClanarinaTr(clanarina);
		}
	})
	
	
	//--------------------------------------------------------------------------------------//
	
	/*$("#dugmePlati").click(function(){
   
		$.post({
				url: 'rest/platiClanarinu',
				data: JSON.stringify({"tip" : clanarinaTip, 
				"brojTermina" : clanarinaBrojTermina,
				 "cena" : clanarinaCena}),
				contentType: 'application/json',
				
				success: function() {
				
				window.location="pocetnaKupac.html";
				
				}
			});
  });*/
	
	
	
	
	
	
	
	
	
	
})