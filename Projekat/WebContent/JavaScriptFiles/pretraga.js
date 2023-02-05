function addSportskiObjekatTr(sportskiObjekat) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + sportskiObjekat.naziv + '</td>');
	let tdTipObjekta = $('<td>' + sportskiObjekat.tipObjekta + '</td>');
	let tdSadrzaj = $('<td>' + sportskiObjekat.sadrzaj + '</td>');
	let tdStatus = $('<td>' + sportskiObjekat.status + '</td>');
	let tdMapa = $('<td>' + sportskiObjekat.mapa + '</td>');
	let tdLogo = $('<td>' + sportskiObjekat.logo + '</td>');
	let tdProsecnaOcena = $('<td>' + sportskiObjekat.prosecnaOcena + '</td>');
	let tdRadnoVreme = $('<td>' + sportskiObjekat.radnoVreme + '</td>');
	
	tr.append(tdNaziv).append(tdTipObjekta).append(tdSadrzaj).append(tdStatus).append(tdMapa).append(tdLogo).append(tdProsecnaOcena).append(tdRadnoVreme);
	$('#tabelaSportskihObjekata tbody').append(tr);
}

$(document).ready(function() {
	/*$.get({
		//url: 'rest/products',
		url: 'rest/sportskiObjekti',
        success: function(sportskiObjekti) {
			for (let sportskiObjekat of sportskiObjekti) {
				addSportskiObjekatTr(sportskiObjekat);
			}
		}
	});*/


$('#sportskiObjektiform').submit((event) => {

	//window.alert("usao u submit");
	
    event.preventDefault();

    let naziv = $('#naziv').val();
    let tipObjekta = $('#tipObjekta').val();
    let prosecnaOcena = $('#prosecnaOcena').val();


    let url = 'rest/pretraga/';
    
    
    if(naziv === "" && tipObjekta === "" && prosecnaOcena === "0"){
		url = 'rest/sportskiObjekti';
		$.get({
        url: url,
        contentType: 'application/json',
        success: function(sportskiObjekti) {
            $("#tabelaSportskihObjekata tbody").html("");
            console.log(sportskiObjekti);
            for (let sportskiObjekat of sportskiObjekti) {
                addSportskiObjekatTr(sportskiObjekat);
            }
            $('#naziv').val('');
            $('#tipObjekta').val('');
            //$('#prosecnaOcena').val('');
        	},
    	})
	}
    else if(naziv === "" && tipObjekta === ""){
        url = 'rest/sportskiObjektiPoOceni/' + prosecnaOcena;
		$.get({
        url: url,
        contentType: 'application/json',
        success: function(sportskiObjekti) {
            $("#tabelaSportskihObjekata tbody").html("");
            console.log(sportskiObjekti);
            for (let sportskiObjekat of sportskiObjekti) {
                addSportskiObjekatTr(sportskiObjekat);
            }
            $('#naziv').val('');
            $('#tipObjekta').val('');
        	},
    	})
    }
    else if(naziv === ""){
    	url += "findTipObjekta/" + tipObjekta + "&" + prosecnaOcena;
    	$.get({
        url: url,
        contentType: 'application/json',
        success: function(sportskiObjekti) {
            $("#tabelaSportskihObjekata tbody").html("");
            console.log(sportskiObjekti);
            for (let sportskiObjekat of sportskiObjekti) {
                addSportskiObjekatTr(sportskiObjekat);
            }
            $('#naziv').val('');
            $('#tipObjekta').val('');
        	},
    	})
	}
	else if(tipObjekta === ""){
		url += "findNaziv/" + naziv + "&" + prosecnaOcena;
		$.get({
        url: url,
        contentType: 'application/json',
        success: function(sportskiObjekti) {
            $("#tabelaSportskihObjekata tbody").html("");
            console.log(sportskiObjekti);
            for (let sportskiObjekat of sportskiObjekti) {
                addSportskiObjekatTr(sportskiObjekat);
            }
            $('#naziv').val('');
            $('#tipObjekta').val('');
        	},
    	})
	}
	else{
		url += "findObjekat/" + naziv + "&" + tipObjekta + "&" + prosecnaOcena;
		$.get({
        url: url,
        contentType: 'application/json',
        success: function(sportskiObjekti) {
            $("#tabelaSportskihObjekata tbody").html("");
            console.log(sportskiObjekti);
            for (let sportskiObjekat of sportskiObjekti) {
                addSportskiObjekatTr(sportskiObjekat);
            }
            $('#naziv').val('');
            $('#tipObjekta').val('');
        	},
    	})
	}
    
    

	});

});
