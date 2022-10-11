$(document).ready(() => {
	
	//--------------------------------------------------------------------------------------//
	
	
	
	function addClanarinaTr(clanarina){
		let tr = $('<tr></tr>');
		let tdTip = $('<td>' + clanarina.tip + '</td>');
		let tdBrojTermina = $('<td>' + clanarina.brojTermina + '</td>');
		let tdCena = $('<td>' + clanarina.cena + '</td>');
	
		tr.append(tdTip).append(tdBrojTermina).append(tdCena);
		$('#tabelaClanarina tbody').append(tr);
	}
	
	$.get({
		url: 'rest/clanarine',
		success: function(clanarine){
			for(let clanarina of clanarine){
				addClanarinaTr(clanarina);
			}
		}
	})
	
	
	
	
	
	//--------------------------------------------------------------------------------------//
	
})