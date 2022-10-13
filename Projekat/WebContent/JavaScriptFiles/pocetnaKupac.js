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













/*$(document).ready(() => {
	
	//--------------------------------------------------------------------------------------//
	
	
	
	function addClanarinaTr(clanarina){
		let tr = $('<tr></tr>');
		let tdTip = $('<td>' + clanarina.tip + '</td>');
		let tdBrojTermina = $('<td>' + clanarina.brojTermina + '</td>');
		let tdCena = $('<td>' + clanarina.cena + '</td>');
	
		tr.append(tdTip).append(tdBrojTermina).append(tdCena);
		$('#tabelaClanarina2 tbody').append(tr);
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
	
})*/